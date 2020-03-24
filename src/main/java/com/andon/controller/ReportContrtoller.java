package com.andon.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.andon.bean.dto.EquipRepairReport;
import com.andon.bean.dto.EquipReport;
import com.andon.commons.ExceptionCode;
import com.andon.service.ReportService;
import com.andon.utils.PrintException;

@Controller
@RequestMapping("/report")
public class ReportContrtoller extends BaseController{
    @Autowired
    private ReportService reportService;

    @RequestMapping(value = "/lineReport.do", method = RequestMethod.GET)
    public @ResponseBody Object lineReport(HttpServletRequest request) {

        try {
        	EquipReport equipReport = reportService.lineReport();
        	
            return resultHandler(equipReport);
        } catch (Exception e) {
        	e.printStackTrace();
        	e.printStackTrace(PrintException.printException());
            return resultHandler(exceptionHandle(ExceptionCode.ERROR_CODE));
        }
    }
    
    @RequestMapping(value = "/equipReport.do", method = RequestMethod.GET)
    public @ResponseBody Object equipReport(HttpServletRequest request) {

        try {
        	List<EquipRepairReport> equipRepairReport = reportService.equipReport();
        	
            return resultHandler(equipRepairReport);
        } catch (Exception e) {
        	e.printStackTrace();
        	e.printStackTrace(PrintException.printException());
            return resultHandler(exceptionHandle(ExceptionCode.ERROR_CODE));
        }
    }


}
