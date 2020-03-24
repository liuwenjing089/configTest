package com.andon.dao;

import java.util.List;

import com.andon.bean.MouldPreventionType;

public interface MouldPreventionTypeDao {
	/**
	 * 新建类型
	 * 
	 * @param Mould
	 */
	public void insertMouldPreventionType(MouldPreventionType mouldPreventionType);
	
	
	/**
	 * 查询属于该模具的类型集合group by key    
	 * 
	 * @param Mould
	 */
	public List<MouldPreventionType> getMouldPreventionTypeList(MouldPreventionType mouldPreventionType);
	
	/**
	 * 根据group key 查询所有报修记录
	 * 
	 * @param Mould
	 */
	public List<MouldPreventionType> SelectRepairListByMouldPreventionTypeGroupKey(MouldPreventionType mouldPreventionType);
	
	
	/**
	 * 根据报修id查询查询是否已存在记录
	 * 
	 * @param Mould
	 */
	public MouldPreventionType selectMouldPreventionTypeByReId(MouldPreventionType mouldPreventionType);
	
	
	/**
	 * 更新
	 * 
	 * @param Mould
	 */
	public void updateMouldPreventionType(MouldPreventionType mouldPreventionType);
}
