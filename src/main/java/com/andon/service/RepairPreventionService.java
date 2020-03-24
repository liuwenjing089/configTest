package com.andon.service;

import java.util.List;
import java.util.Map;

import com.andon.bean.Repair;
import com.andon.bean.RepairPrevention;
import com.andon.bean.dto.TestMouldRepair;

public interface RepairPreventionService {

    List<RepairPrevention> getRepair(String factory, int type,int state,String beginTime,String endTime,String createUser,String workMan,String repairNumber, String userClass , int mouldId, String reId, int beginIndex,int pageSize) throws Exception;
    int getRepairCount(String factory, int type,int state,String beginTime,String endTime,String createUser,String workMan, String repairNumber, String userClass, int mouldId, String reId) throws Exception;
    void update(RepairPrevention repair);
    //根据报修uuid查询试模记录
	TestMouldRepair selectMouldRepairByIds(String deId);
//    
//    //查询当前报修编号最大的当中最后2位数字
//	public Map<String, Object> selectRepairNum();
//	
//    List<Repair> getRepairToMouldPrevention(String factory, int type,int state,String beginTime,String endTime,String createUser,String workMan, String userClass, String groupKey, int beginIndex,int pageSize) throws Exception;
//    int getRepairCountToMouldPrevention(String factory, int type,int state,String beginTime,String endTime,String createUser,String workMan, String userClass, String groupKey) throws Exception;

}
