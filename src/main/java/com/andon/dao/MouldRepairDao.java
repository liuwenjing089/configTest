package com.andon.dao;

import org.apache.ibatis.annotations.Param;

import com.andon.bean.MouldRepair;
import com.andon.bean.dto.TestMouldRepair;

public interface MouldRepairDao {
		
	/**
	 * 新建模具报修
	 * 
	 * @param TestMouldRepair
	 */
	public void insertMouldRepair(MouldRepair mouldRepair);
	
	/**
	 * 根据模具报修表Id查询该条记录所有信息
	 * @param id
	 */
	public MouldRepair selectMouldRepairById(@Param("id") int id, @Param("isActive") int isActive);
	
	/**
	 * 更新模具报修表
	 * 
	 * @param TestMouldRepair
	 */
	public void updateMouldRepairById(MouldRepair mouldRepair);
	
	/**
	 * 班长确认
	 * 
	 * @param TestMouldRepair
	 */
	public void MouldRepairConfirm(MouldRepair mouldRepair);

	public MouldRepair selectLineReason(@Param("id") int id);

	/**
	 * 根据模具报修表Id查询该条记录所有信息
	 * @param id
	 */
	public TestMouldRepair selectMouldRepairByIds(@Param("id") int id, @Param("isActive")int isActive);

	
	
	//以下为预防再发中的内容
	public TestMouldRepair selectMouldRepairByIdsFromPrevention(@Param("deId")String deId, @Param("isActive")int isActive);

	public MouldRepair selectMouldRepairPreventionById(@Param("uuid")String uuid, @Param("isActive")int isActive);
}
