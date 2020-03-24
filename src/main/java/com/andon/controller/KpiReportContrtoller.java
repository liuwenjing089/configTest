package com.andon.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.andon.bean.dto.EquipSpotReport;
import com.andon.commons.ExceptionCode;
import com.andon.service.ReportService;
import com.andon.utils.PrintException;

@Controller
@RequestMapping("/kpiReport")
public class KpiReportContrtoller extends BaseController{
    @Autowired
    private ReportService reportService;

    //设备上个月点检完成率
    @RequestMapping(value = "/equipSpotLastMonth.do", method = RequestMethod.GET)
    public @ResponseBody Object equipSpotLastMonth(HttpServletRequest request) {

        try {
        	EquipSpotReport equipSpotReport = reportService.equipSpotLastMonth();
        	
            return resultHandler(equipSpotReport);
        } catch (Exception e) {
        	e.printStackTrace(PrintException.printException());
            return resultHandler(exceptionHandle(ExceptionCode.ERROR_CODE));
        }
    }
}
