package com.andon.service;

import java.util.List;

import com.andon.bean.MouldFaultParts;
import com.andon.bean.MouldPartsInfo;
import com.andon.bean.MouldRepair;
import com.andon.bean.dto.TestMouldRepair;

public interface MouldRepairService {

	/**
	 * 新建模具报修
	 * 
	 * @param TestMouldRepair
	 */
	public void insertMouldRepair(MouldRepair mouldRepair) throws Exception;
	
	/**
	 * 根据模具报修表Id查询该条记录所有信息
	 * @param id
	 */
	public MouldRepair selectMouldRepairById(int id) throws Exception;
	
	/**
	 * 更新模具报修表
	 * 
	 * @param TestMouldRepair
	 */
	public void updateMouldRepairById(MouldRepair mouldRepair) throws Exception;

	
	/**
	 * 班长确认
	 * @param id 
	 * 
	 * @param userName
	 * @param userType 
	 * @throws Exception 
	 */
	public void mouldRepairByMonitorConfirm(int id, String userName, int userType) throws Exception;

	/**
	 * 根据模具报修表Id查询该条记录所有信息
	 * @param id
	 */
	public TestMouldRepair selectMouldRepairByIds(int id) throws Exception;

	//各级别确认 + 插入模具报修种类
	public void mouldRepairByMonitorConfirmAndAddType(int id, String userName, int userType, int mouldId) throws Exception;
	
	/**
	 * //获取部品分类
	 * @param mouldPartsInfo 
	 * @throws Exception 
	 */
	public List<MouldPartsInfo> selectMouldPartsInfoToPartsType(MouldPartsInfo mouldPartsInfo) throws Exception;
	
	/**
	 * //根据部品分类获取部品名称

	 * @param mouldPartsInfo 
	 * @throws Exception 
	 */
	public List<MouldPartsInfo> selectMouldPartsInfoToPartsName(MouldPartsInfo mouldPartsInfo) throws Exception;
	
	/**
	 * //根据部品分类和部品名称获取部品品番或型号

	 * @param mouldPartsInfo 
	 * @throws Exception 
	 */
	public List<MouldPartsInfo> selectMouldPartsInfoToPartsNum(MouldPartsInfo mouldPartsInfo) throws Exception;
	
	/**
	 * //根据报修编号获取模具损坏部件维修详情
	 * @param reId
	 * @throws Exception 
	 */
	public List<MouldFaultParts> selectMouldFaultPartsDetail(String reId) throws Exception;
	
}
