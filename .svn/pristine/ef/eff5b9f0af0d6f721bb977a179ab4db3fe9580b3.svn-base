package com.andon.service;

import java.util.List;

import com.andon.bean.MouldTaskTime;
import com.andon.bean.Repair;
import com.andon.bean.RepairPrevention;

public interface MouldTaskTimeService {
    //新建
    public void insert(MouldTaskTime mouldTaskTime) throws Exception;

    //更新
    public void update(MouldTaskTime mouldTaskTime) throws Exception;
	
	//查询结束时间为空的记录
	public MouldTaskTime selectMouldTaskTime(MouldTaskTime mouldTaskTime)throws Exception;
	
	//查询已存在的开始结束的条目数量
	public int selectCount(MouldTaskTime mouldTaskTime)throws Exception;
	
	//查询该报修记录的各种状态用于在页面上展示不同的按钮形态
	public int selectMouldTaskTimeStauts(MouldTaskTime mouldTaskTime)throws Exception;

	//处理报修列表上的的样式
	public List<Repair> addMouldRepaitCss(List<Repair> getRepair)throws Exception;
	
	//处理报修列表上的的样式
	public List<RepairPrevention> addMouldRepaitPreventionCss(List<RepairPrevention> getRepair)throws Exception;
}
