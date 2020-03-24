package com.andon.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.andon.bean.Mould;
import com.andon.bean.MouldPreventionType;
import com.andon.bean.MouldRepair;
import com.andon.bean.Repair;
import com.andon.bean.SameRecurrenceRapir;
import com.andon.bean.dto.WorkNameOutput;
import com.andon.commons.Constant;
import com.andon.dao.EquipRepairKyDao;
import com.andon.dao.MouldDao;
import com.andon.dao.MouldPreventionTypeDao;
import com.andon.dao.MouldRepairDao;
import com.andon.dao.RepairDao;
import com.andon.service.RepairService;

@Service
@Transactional
public class RepairServiceImpl implements RepairService {

    @Autowired
    private RepairDao repairDao;

    @Autowired
    private EquipRepairKyDao equipRepairKyDao;

    @Autowired
    private MouldDao mouldDao;

    @Autowired
    private MouldRepairDao mouldRepairDao;
    
    @Autowired
    private MouldPreventionTypeDao mouldPreventionTypeDao;

    @Override
    public int add(Repair repair) {
        repairDao.insert(repair);
        return repair.getId();
    }

    @Override
    public List<Repair> getRepair(String factory, int type, int state, String beginTime, String endTime, String createUser,String workMan, String repairNumber, String userClass, int beginIndex, int pageSize) throws Exception{
        List<Repair> res = new ArrayList<>();
        
        List<Repair> repairs = new ArrayList<Repair>();
        
        //根据不同权限获取设备或者模具的数据
        if("0".equals(userClass)){
        	//查全部
        	
        }else if("1".equals(userClass)){
        	//查设备
        	type = 1;
        }else if("2".equals(userClass)){
        	
        	//查模具
        	type = 2;
        }
        
        //根据搜索条件区间来判断如果只填了开始时间或结束时间则只计算这一天的结果
        if(StringUtils.isNotBlank(beginTime) && StringUtils.isBlank(endTime)){
        	endTime = beginTime;
        }else if(StringUtils.isNotBlank(endTime) && StringUtils.isBlank(beginTime)){
        	beginTime = endTime;
        }
        
        
        repairs = repairDao.selectRepairFromView(factory, type,state,beginTime,endTime,createUser,workMan, repairNumber, beginIndex,pageSize);
        
        //获取用户列表用于姓名显示
        //List<User> user = userDao.selectUserBaoquan();
        
        if(repairs!=null&&repairs.size()>0){
            for(Repair repair:repairs){
                if(repair.getType()==1){
                    WorkNameOutput workNameOutput = equipRepairKyDao.selectWorkName(repair.getId());
                    if(workNameOutput!= null){
                        repair.setEquipName(workNameOutput.getEquipName());
                        repair.setAppearance(workNameOutput.getAppearance());
                        repair.setLineName(workNameOutput.getBeltlineName());
                    }
                }else{
                    Mould mould = mouldDao.selectMouldById(repair.getDetailId(),1);
                    if(mould!= null){
                        repair.setEquipName(mould.getAnotherName());
                        repair.setVehicleType(mould.getVehicleType());
                    }
                    MouldRepair mouldRepair = mouldRepairDao.selectLineReason(repair.getId());
                    if(mouldRepair!= null){
                        repair.setAppearance(mouldRepair.getAppearance());
                        repair.setLineName(mouldRepair.getLineName());
                    }
                }
                res.add(repair);
                //判断是否填写ky
                int kyCount = equipRepairKyDao.selectKyCount(repair.getId());
                if(kyCount==0){
                    repair.setIsKy(0);
                }else{
                    repair.setIsKy(1);
                }
                
                //将报修者显示为姓名

            }
        }
        return res;
    }

    @Override
    public int getRepairCount(String factory, int type, int state, String beginTime, String endTime, String createUser,String workMan, String repairNumber, String userClass) throws Exception{
    	
        //根据不同权限获取设备或者模具的数据
        if("0".equals(userClass)){
        	//查全部

        }else if("1".equals(userClass)){
        	//查设备
        	type = 1;
        }else if("2".equals(userClass)){
        	
        	//查模具
        	type = 2;
        }
        
        //根据搜索条件区间来判断如果只填了开始时间或结束时间则只计算这一天的结果
        if(StringUtils.isNotBlank(beginTime) && StringUtils.isBlank(endTime)){
        	endTime = beginTime;
        }else if(StringUtils.isNotBlank(endTime) && StringUtils.isBlank(beginTime)){
        	beginTime = endTime;
        }
        
        return repairDao.selectRepairCount(factory, type,state,beginTime,endTime,createUser,workMan, repairNumber);
    }

    @Override
    public void update(Repair repair) {
        repairDao.update(repair);
    }

    //查询当前报修编号最大的当中最后2位数字
	@Override
	public Map<String, Object> selectRepairNum() {
		
		return repairDao.selectRepairNum();
	}

	@Override
	public List<SameRecurrenceRapir> getRepairToMouldPrevention(String factory, int type, int state, String beginTime,
			String endTime, String createUser, String workMan, String userClass, String groupKey, int beginIndex,
			int pageSize) throws Exception {

        
        List<SameRecurrenceRapir> repairs = new ArrayList<SameRecurrenceRapir>();
        
        //根据group查询出所有的报修记录的id
        
        MouldPreventionType mouldPreventionType = new MouldPreventionType();
        mouldPreventionType.setGroupKey(groupKey);
        mouldPreventionType.setIsActive(Constant.ACTIVE_VALID);
        List<MouldPreventionType> mouldPreventionTypeList = mouldPreventionTypeDao.SelectRepairListByMouldPreventionTypeGroupKey(mouldPreventionType);
        
        //根据搜索条件区间来判断如果只填了开始时间或结束时间则只计算这一天的结果
        if(StringUtils.isNotBlank(beginTime) && StringUtils.isBlank(endTime)){
        	endTime = beginTime;
        }else if(StringUtils.isNotBlank(endTime) && StringUtils.isBlank(beginTime)){
        	beginTime = endTime;
        }
        
        
        repairs = repairDao.selectRepairFromViewToMouldPrevention(factory, type,state,beginTime,endTime,createUser,workMan, null, beginIndex,pageSize, mouldPreventionTypeList);
        

        return repairs;
	}

	@Override
	public int getRepairCountToMouldPrevention(String factory, int type, int state, String beginTime, String endTime,
			String createUser, String workMan, String userClass, String groupKey) throws Exception {
	   	
        //根据group查询出所有的报修记录的id
        
        MouldPreventionType mouldPreventionType = new MouldPreventionType();
        mouldPreventionType.setGroupKey(groupKey);
        mouldPreventionType.setIsActive(Constant.ACTIVE_VALID);
        List<MouldPreventionType> mouldPreventionTypeList = mouldPreventionTypeDao.SelectRepairListByMouldPreventionTypeGroupKey(mouldPreventionType);
		
        //根据搜索条件区间来判断如果只填了开始时间或结束时间则只计算这一天的结果
        if(StringUtils.isNotBlank(beginTime) && StringUtils.isBlank(endTime)){
        	endTime = beginTime;
        }else if(StringUtils.isNotBlank(endTime) && StringUtils.isBlank(beginTime)){
        	beginTime = endTime;
        }
        
        return repairDao.selectRepairCountToMouldPrevention(factory, type,state,beginTime,endTime,createUser,workMan, null, mouldPreventionTypeList);
	}
}
