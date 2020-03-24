package com.andon.dao;

import com.andon.bean.EquipRepairKy;
import com.andon.bean.dto.WorkNameOutput;

import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface EquipRepairKyDao {
    //添加ky
    public void insert(EquipRepairKy equipRepairKy);
    //获取作业名
    WorkNameOutput selectWorkName(@Param("id")int id);
    public int selectKyCount(@Param("reId")int reId);
    //获取ky详情
	public Map<String, Object> getKyDetail(@Param("reId")int id);
	
	//修改ky
	public void update(EquipRepairKy equipRepairKy);
}
