package com.andon.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.andon.bean.dto.EquipRepairReport;
import com.andon.bean.dto.EquipReport;
import com.andon.bean.dto.EquipReportOutPut;
import com.andon.bean.dto.EquipSpotReport;
import com.andon.commons.ConstantCode;
import com.andon.dao.ReportDao;
import com.andon.service.ReportService;

@Service
@Transactional
public class ReportServiceImpl implements ReportService {
	
    @Autowired
    private ReportDao reportDao;
	//设备维修报表统计1
	@Override
	public EquipReport lineReport() throws Exception {
		EquipReport equipReport = new EquipReport();
		
		List<EquipReportOutPut> productionLineList = new ArrayList<EquipReportOutPut>();
		List<EquipReportOutPut> moldingMachineList = new ArrayList<EquipReportOutPut>();
		
		List<EquipReportOutPut> equipReportList = reportDao.equipReport();
		if(equipReportList != null && equipReportList.size() > 0){
			
			for(EquipReportOutPut e: equipReportList){
				if(e.getLineType() == ConstantCode.LINE_TYPE_PRODUCTION){
					
					e.setFaultItem(ConstantCode.SPORT_TYPE_PRODUCTION);
					productionLineList.add(e);
					
				}else if(e.getLineType() == ConstantCode.LINE_TYPE_MACHINE){
					
					e.setFaultItem(ConstantCode.SPORT_TYPE_MACHINE);
					moldingMachineList.add(e);
				}
			}
		}

		
		equipReport.setProductionLineList(productionLineList);
		equipReport.setMoldingMachineList(moldingMachineList);
		
		return equipReport;
	}
	//设备维修报表统计2
	@Override
	public List<EquipRepairReport> equipReport() throws Exception {
		List<EquipRepairReport> equipRepairReportList = reportDao.equipRepairReport();
		return equipRepairReportList;
	}

	//设备上个月点检完成率
	@Override
	public EquipSpotReport equipSpotLastMonth() throws Exception {
		EquipSpotReport equipSpotReport = reportDao.equipSpotLastMonth();
		BigDecimal value = equipSpotReport.getCompletionRate().setScale(4, BigDecimal.ROUND_HALF_UP);
		equipSpotReport.setCompletionRate(value);
		return equipSpotReport;
	}

    
}
