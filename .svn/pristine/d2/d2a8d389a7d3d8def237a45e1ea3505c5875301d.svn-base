package com.andon.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.andon.bean.MouldPreventionType;
import com.andon.bean.RepairPrevention;

public interface RepairPreventionDao {
    //新建修理
    public void insert(RepairPrevention repairPrevention);
    //获取检索修理
    public int selectRepairCount(@Param("factory")String factory, @Param("type")int type,@Param("state")int state,@Param("beginTime")String beginTime,@Param("endTime")String endTime,@Param("createUser")String createUser,@Param("workMan")String workMan, @Param("repairNumber")String repairNumber, @Param("mouldId")int mouldId, @Param("reId")String reId);
    //更新
    public void update(RepairPrevention repairPrevention);
	public List<RepairPrevention> selectRepairFromView(@Param("factory")String factory, @Param("type")int type,@Param("state")int state,@Param("beginTime")String beginTime,@Param("endTime")String endTime,@Param("createUser")String createUser,@Param("workMan")String workMan, @Param("repairNumber")String repairNumber, @Param("mouldId")int mouldId, 
			@Param("reId")String reId, @Param("beginIndex")int beginIndex, @Param("pageSize")int pageSize);
	
	//查询当前报修编号最大的当中最后2位数字
	public Map<String, Object> selectRepairNum();
	
	//根据id查询详情
	public RepairPrevention selectRepairDetail(@Param("id")String id, @Param("isActive")int isActive);
	
	//检索相同报修类型记录
	public List<RepairPrevention> selectRepairFromViewToMouldPrevention(@Param("factory")String factory, @Param("type")int type, @Param("state")int state, @Param("beginTime")String beginTime,
			@Param("endTime")String endTime, @Param("createUser")String createUser, @Param("workMan")String workMan, @Param("repairNumber")String repairNumber, @Param("beginIndex")int beginIndex,  @Param("pageSize")int pageSize, @Param("mouldPreventionTypeList")List<MouldPreventionType> mouldPreventionTypeList);
	
	//检索相同报修类型记录数量
	public int selectRepairCountToMouldPrevention(@Param("factory")String factory, @Param("type")int type, @Param("state")int state, @Param("beginTime")String beginTime, @Param("endTime")String endTime,
			@Param("createUser")String createUser, @Param("workMan")String workMan, @Param("repairNumber")String repairNumber, @Param("mouldPreventionTypeList")List<MouldPreventionType> mouldPreventionTypeList);
	
}
