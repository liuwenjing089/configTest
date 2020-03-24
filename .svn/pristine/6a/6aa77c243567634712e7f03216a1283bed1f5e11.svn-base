package com.andon.service;

import java.util.List;
import java.util.Map;

import com.andon.bean.Repair;
import com.andon.bean.SameRecurrenceRapir;

public interface RepairService {
    //添加
    int add(Repair repair);
    List<Repair> getRepair(String factory, int type,int state,String beginTime,String endTime,String createUser,String workMan,String repairNumber, String userClass, int beginIndex,int pageSize) throws Exception;
    int getRepairCount(String factory, int type,int state,String beginTime,String endTime,String createUser,String workMan, String repairNumber, String userClass) throws Exception;
    void update(Repair repair);
    
    //查询当前报修编号最大的当中最后2位数字
	public Map<String, Object> selectRepairNum();
	
    List<SameRecurrenceRapir> getRepairToMouldPrevention(String factory, int type,int state,String beginTime,String endTime,String createUser,String workMan, String userClass, String groupKey, int beginIndex,int pageSize) throws Exception;
    int getRepairCountToMouldPrevention(String factory, int type,int state,String beginTime,String endTime,String createUser,String workMan, String userClass, String groupKey) throws Exception;
}
