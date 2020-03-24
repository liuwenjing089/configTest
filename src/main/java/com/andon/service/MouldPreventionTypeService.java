package com.andon.service;

import java.util.List;

import com.andon.bean.MouldPreventionType;

public interface MouldPreventionTypeService {
	/**
	 * 新建类型
	 * 
	 * @param Mould
	 */
	public void insertMouldPreventionType(MouldPreventionType mouldPreventionType) throws Exception;
	
	
	/**
	 * 查询属于该模具的类型集合group by key   
	 * 
	 * @param Mould
	 */
	public List<MouldPreventionType> getMouldPreventionTypeList(int reId) throws Exception;
	
	/**
	 * 根据group key 查询所有报修记录
	 * 
	 * @param Mould
	 */
	public List<MouldPreventionType> SelectRepairListByMouldPreventionTypeGroupKey(MouldPreventionType mouldPreventionType) throws Exception;

	//查询模具故障类型
	public List<MouldPreventionType> getMouldPreventionTypeListToPreventionRepair(String reId);
}
