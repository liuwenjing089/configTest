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
import com.andon.bean.RepairPrevention;
import com.andon.bean.dto.TestMouldRepair;
import com.andon.bean.dto.WorkNameOutput;
import com.andon.commons.Constant;
import com.andon.dao.EquipRepairKyDao;
import com.andon.dao.MouldDao;
import com.andon.dao.MouldPreventionTypeDao;
import com.andon.dao.MouldRepairDao;
import com.andon.dao.RepairDao;
import com.andon.dao.RepairPreventionDao;
import com.andon.dao.UserDao;
import com.andon.service.RepairPreventionService;
import com.andon.service.RepairService;

@Service
@Transactional
public class RepairPreventionServiceImpl implements RepairPreventionService {

    @Autowired
    private RepairPreventionDao repairPreventionDao;

    @Autowired
    private EquipRepairKyDao equipRepairKyDao;

    @Autowired
    private MouldDao mouldDao;

    @Autowired
    private MouldRepairDao mouldRepairDao;
    
    @Autowired
    private MouldPreventionTypeDao mouldPreventionTypeDao;


    @Override
    public List<RepairPrevention> getRepair(String factory, int type, int state, String beginTime, String endTime, String createUser,String workMan, String repairNumber, String userClass, int mouldId, String reId, int beginIndex, int pageSize) throws Exception{

        
        List<RepairPrevention> repairs = new ArrayList<RepairPrevention>();
        
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
        
        
        repairs = repairPreventionDao.selectRepairFromView(factory, type,state,beginTime,endTime,createUser,workMan, repairNumber, mouldId, reId, beginIndex,pageSize);
        
        return repairs;
    }

    @Override
    public int getRepairCount(String factory, int type, int state, String beginTime, String endTime, String createUser,String workMan, String repairNumber, String userClass, int mouldId, String reId) throws Exception{
    	
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
        
        return repairPreventionDao.selectRepairCount(factory, type,state,beginTime,endTime,createUser,workMan, repairNumber, mouldId, reId);
    }

    //更新
    @Override
    public void update(RepairPrevention repair) {
    	repairPreventionDao.update(repair);
    }

    //根据报修uuid查询试模记录
	@Override
	public TestMouldRepair selectMouldRepairByIds(String deId) {
		TestMouldRepair testMouldRepair = mouldRepairDao.selectMouldRepairByIdsFromPrevention(deId, Constant.ACTIVE_VALID);
		return testMouldRepair;
	}
}
