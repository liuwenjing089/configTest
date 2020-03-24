package com.andon.dao;

import org.apache.ibatis.annotations.Param;

import com.andon.bean.dto.EchartsReport;

public interface EchartsRepairDao {
	//获取故障分类图表数据
    public EchartsReport getFaultClassification(@Param("lineTypeProduction") int lineTypeProduction);

	//获取维修用时分类图表数据
    public EchartsReport getMaintenanceTimeClassification(@Param("lineTypeProduction") int lineTypeProduction);
}
