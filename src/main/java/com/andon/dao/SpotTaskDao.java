package com.andon.dao;

import com.andon.bean.EquipSpot;
import com.andon.bean.SpotTask;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SpotTaskDao {
    //增删该查任务
    public int insert(List<SpotTask> spotTasks);
    public List<SpotTask> selectTask(@Param("spotType")int spotType,@Param("state")int state,@Param("spotInterval")String spotInterval,@Param("beginTime")String beginTime,@Param("endTime")String endTime,@Param("beginIndex")int beginIndex,@Param("pageSize")int pageSize, @Param("factory")String factory);
    public int selectTaskCount(@Param("spotType")int spotType,@Param("state")int state,@Param("spotInterval")String spotInterval,@Param("beginTime")String beginTime,@Param("endTime")String endTime, @Param("factory")String factory);
    public void deleteTask(@Param("id")int id,@Param("userName") String userName);
    //检验相同设备相同周期的任务有没有在同月重复的记录
    public int selectTaskRepeat(SpotTask spotTask);
    public void updateState(@Param("id")int id);
    
    //查询点检任务日历
	public List<SpotTask> getTaskToCalender(@Param("factory")String factory, @Param("isActive")int isActive);
    public void updateTaskTime(@Param("id")int id,@Param("beginTime")String beginTime,@Param("endTime")String endTime);

    SpotTask selectTaskById(@Param("id")int id);
    public List<Integer> selectDivName(@Param("id")int id,@Param("spotType")int spotType);
    
    //根据选择的id到设备点检视图中筛选
	public List<EquipSpot> checkSpot(@Param("spotInterval")int spotInterval, @Param("ids")String[] ids);
	
	//批量删除task主表
	public void batchDeleteTask(@Param("ids")String[] taskIds, @Param("userName")String userName);
}
