package com.andon.service;

import java.util.List;

import com.andon.bean.Mould;
import com.andon.bean.dto.MouldRepairHistory;

public interface MouldService {
	
	/**
	 * 根据条件检索模具表数量
	 * @param Mould
	 * @return int
	 * @throws Exception 
	 */
	public int getMouldCount(Mould mould) throws Exception;
		
	/**
	 * 根据条件检索模具表列表
	 * @param Mould 
	 * @return List
	 * @throws Exception 
	 */	
	public List<Mould> getMouldList(Mould mould) throws Exception;
	
	
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
	public Mould selectMouldById(int id);
	
	/**
	 * 更新模具
	 * 
	 * @param Mould
	 */
	public void updateMouldById(Mould mould) throws Exception;

    //根据模具的车种和图号搜索唯一的 车种记录
	public List<Mould> selectMouldByVehicleTypeAndFigureNumber(Mould mould) throws Exception;
	
	/**
	 * 根据模具id查询维修记录
	 * 
	 * @param MouldId
	 */
	public List<MouldRepairHistory> mouldRepairHistoryList(int id);

	/**
	 * 根据条件检索模具表列表不分页
	 * @param Mould 
	 * @return List
	 * @throws Exception 
	 */	
	public List<Mould> getMouldLists(Mould mould);
}
