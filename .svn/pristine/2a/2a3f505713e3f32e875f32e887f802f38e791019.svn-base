package com.andon.service;

import java.util.List;
import java.util.Map;

import com.andon.bean.SpotTask;
import com.andon.bean.dto.TaskInput;


public interface SpotTaskService {

    void add(TaskInput taskInputs,String user, String[] ids, String factory) throws Exception;
    
    void addTaskByOne(TaskInput taskInputs,String user, String[] ids, String factory) throws Exception;
    
    List<SpotTask> selectTask(int spotType, int state, String spotInterval, String beginTime, String endTime,int beginIndex,int pageSize, String factory);
    int selectTaskCount(int spotType,int state,String spotInterval,String beginTime,String endTime, String factory);
    void deleteTask(int id, String userName);
    //查询点检任务日历
    Map<String, Object> getTaskToCalender(String factory) throws Exception;

    void updateTask(int taskId,String beginTime,int deId,String user, String factory);
    
    //验证要点检的设备是否都存在指定周期的规则
    Map<String, Object> checkSpot(int spotType, int spotInterval, String[] ids) throws Exception;
    
    //批量删除
	void batchDeleteTask(String batchDeleteId, String userName) throws Exception;
}
