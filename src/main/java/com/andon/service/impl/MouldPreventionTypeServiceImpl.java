package com.andon.service.impl;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.andon.bean.MouldPreventionType;
import com.andon.bean.Repair;
import com.andon.bean.RepairPrevention;
import com.andon.commons.Constant;
import com.andon.dao.MouldPreventionTypeDao;
import com.andon.dao.RepairDao;
import com.andon.dao.RepairPreventionDao;
import com.andon.service.MouldPreventionTypeService;
import com.andon.utils.DateUtils;
import com.andon.utils.UUIDString;

@Service
@Transactional
public class MouldPreventionTypeServiceImpl implements MouldPreventionTypeService {

    @Autowired
    private MouldPreventionTypeDao mouldPreventionTypeDao;
    
    @Autowired
    private RepairDao repairDao;
    
    @Autowired
    private RepairPreventionDao repairPreventionDao;
	/**
	 * 新建类型
	 * 
	 * @param Mould
	 */
	@Override
	public void insertMouldPreventionType(MouldPreventionType mouldPreventionType) throws Exception{
		
		mouldPreventionType.setCreateTime(DateUtils.getNowDate());
		mouldPreventionType.setUpdateTime(DateUtils.getNowDate());
		mouldPreventionType.setIsActive(Constant.ACTIVE_VALID);
		//判断该维修记录在模具故障类型表里是否存在，如果存在则更新,不存在则插入
		MouldPreventionType result = mouldPreventionTypeDao.selectMouldPreventionTypeByReId(mouldPreventionType);
		if(result == null){
			
			if(StringUtils.isBlank(mouldPreventionType.getGroupKey())){
				mouldPreventionType.setGroupKey(UUIDString.getPartsKeyByUUId());
			}
			
			if(StringUtils.isBlank(mouldPreventionType.getRemarks())){
				mouldPreventionType.setRemarks(UUIDString.getPartsKeyByUUId());
			}
			
			mouldPreventionTypeDao.insertMouldPreventionType(mouldPreventionType);
			
		}else{
			mouldPreventionType.setUuid(result.getUuid());
			mouldPreventionTypeDao.updateMouldPreventionType(mouldPreventionType);		
		}
		
	}

	/**
	 * 查询属于该模具的类型集合group by key   
	 * 
	 * @param Mould
	 */
	@Override
	public List<MouldPreventionType> getMouldPreventionTypeList(int reId) throws Exception{
		
		Repair repair = repairDao.selectRepairDetail(reId, Constant.ACTIVE_VALID);
		
		MouldPreventionType mouldPreventionType = new MouldPreventionType();
		mouldPreventionType.setMouldId(repair.getDetailId());
		mouldPreventionType.setIsActive(Constant.ACTIVE_VALID);
		
		List<MouldPreventionType> mouldPreventionTypeList = mouldPreventionTypeDao.getMouldPreventionTypeList(mouldPreventionType);
		return mouldPreventionTypeList;
	}

	/**
	 * 根据group key 查询所有报修记录
	 * 
	 * @param Mould
	 */
	@Override
	public List<MouldPreventionType> SelectRepairListByMouldPreventionTypeGroupKey(
			MouldPreventionType mouldPreventionType) throws Exception{
		// TODO Auto-generated method stub
		return null;
	}

	//查询模具故障类型
	@Override
	public List<MouldPreventionType> getMouldPreventionTypeListToPreventionRepair(String reId) {
		
		RepairPrevention repair = repairPreventionDao.selectRepairDetail(reId, Constant.ACTIVE_VALID);
		
		MouldPreventionType mouldPreventionType = new MouldPreventionType();
		mouldPreventionType.setMouldId(repair.getDetailId());
		mouldPreventionType.setIsActive(Constant.ACTIVE_VALID);
		
		List<MouldPreventionType> mouldPreventionTypeList = mouldPreventionTypeDao.getMouldPreventionTypeList(mouldPreventionType);
		return mouldPreventionTypeList;
	}

}
