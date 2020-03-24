package com.andon.dao;

import java.util.List;
import java.util.Map;

import com.andon.bean.MouldTaskTime;
import com.andon.bean.dto.RepairMouldState;

public interface MouldTaskTimeDao {
    //新建
    public void insert(MouldTaskTime mouldTaskTime);

    //更新
    public void update(MouldTaskTime mouldTaskTime);
	
	//查询结束时间为空的记录
	public MouldTaskTime selectMouldTaskTime(MouldTaskTime mouldTaskTime);
	
	//查询已存在的开始结束的条目数量
	public int selectCount(MouldTaskTime mouldTaskTime);

	//查询报修模具的各种状态
	public List<RepairMouldState> selectRepairMouldState(List<Integer> mouldRepairIds);

	public List<RepairMouldState> selectRepairPreventionMouldState(List<String> mouldRepairIds);
	
}
