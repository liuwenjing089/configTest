package com.andon.dao;

import java.util.List;

import com.andon.bean.dto.EquipRepairReport;
import com.andon.bean.dto.EquipReportOutPut;
import com.andon.bean.dto.EquipSpotReport;

public interface ReportDao {
	
	//设备维修报表统计1
	List<EquipReportOutPut> equipReport();
	//设备维修报表统计2
	List<EquipRepairReport> equipRepairReport();
	
	//设备上个月点检完成率
	EquipSpotReport equipSpotLastMonth();
}
