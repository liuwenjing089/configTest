package com.andon.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.andon.bean.Mould;
import com.andon.bean.dto.MouldRepairHistory;

public interface MouldDao {

	/**
	 * 根据条件检索模具表数量
	 * @param Mould
	 * @return int
	 */
	public int getMouldCount(Mould mould);
		
	/**
	 * 根据条件检索模具表列表
	 * @param Mould 
	 * @return List
	 */	
	public List<Mould> getMouldList(Mould mould);
	
	
	/**
	 * 新建模具
	 * 
	 * @param Mould
	 */
	public void insertMould(Mould mould);
	
	
	/**
	 * 效验名是否重复
	 * 
	 * @param Mould
	 */
	public Integer checkForRecurrence(Mould mould);
	
	
	/**
	 * 根据模具表Id查询该条记录所有信息
	 * @param id
	 */
	public Mould selectMouldById(@Param("id") int id, @Param("isActive") int isActive);
	
	/**
	 * 更新模具
	 * 
	 * @param Mould
	 */
	public void updateMouldById(Mould mould);

	
	/**
	 * 根据模具的车种和图号搜索唯一的 车种记录
	 * 
	 * @param Mould
	 */
	public List<Mould> selectMouldByVehicleTypeAndFigureNumber(Mould mould);
	
	
	/**
	 * 根据模具id查询维修记录
	 * 
	 * @param MouldId
	 */
	public List<MouldRepairHistory> mouldRepairHistoryList(@Param("id") int id);

	public List<Mould> getMouldListNew(Mould mould);
}
