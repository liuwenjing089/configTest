package com.andon.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.andon.commons.ExceptionCode;
import com.andon.service.EchartsReportService;
import com.andon.utils.PrintException;
import com.github.abel533.echarts.json.GsonOption;

import net.sf.json.JSONObject;

@Controller
@RequestMapping("/echartsReport")
public class EchartsReportContrtoller extends BaseController{
    @Autowired
    private EchartsReportService echartsReportService;

    //获取生产故障分类图表数据
    @RequestMapping(value = "/productionFaultClassification.do", method = RequestMethod.GET)
    public @ResponseBody Object productionFaultClassification(HttpServletRequest request) {

        try {
        	GsonOption option = echartsReportService.getProductionFaultClassification();
        	String result = option.toString();
        	JSONObject jo = JSONObject.fromObject(result);
        	
            return resultHandler(jo);
        } catch (Exception e) {
        	e.printStackTrace();
        	e.printStackTrace(PrintException.printException());
            return resultHandler(exceptionHandle(ExceptionCode.ERROR_CODE));
        }
    }
    
    //获取成型故障分类图表数据
    @RequestMapping(value = "/formingFaultClassification.do", method = RequestMethod.GET)
    public @ResponseBody Object formingFaultClassification(HttpServletRequest request) {

        try {
        	GsonOption option = echartsReportService.getFormingFaultClassification();
        	String result = option.toString();
        	JSONObject jo = JSONObject.fromObject(result);
        	
            return resultHandler(jo);
        } catch (Exception e) {
        	e.printStackTrace();
        	e.printStackTrace(PrintException.printException());
            return resultHandler(exceptionHandle(ExceptionCode.ERROR_CODE));
        }
    }
    
    //获取总故障分类图表数据
    @RequestMapping(value = "/totalFaultClassification.do", method = RequestMethod.GET)
    public @ResponseBody Object totalFaultClassification(HttpServletRequest request) {

        try {
        	GsonOption option = echartsReportService.getTotalFaultClassification();
        	String result = option.toString();
        	JSONObject jo = JSONObject.fromObject(result);
        	
            return resultHandler(jo);
        } catch (Exception e) {
        	e.printStackTrace();
        	e.printStackTrace(PrintException.printException());
            return resultHandler(exceptionHandle(ExceptionCode.ERROR_CODE));
        }
    }
    
	//获取生产维修用时分类图表数据
    @RequestMapping(value = "/productionMaintenanceTimeClassification.do", method = RequestMethod.GET)
    public @ResponseBody Object productionMaintenanceTimeClassification(HttpServletRequest request) {

        try {
        	GsonOption option = echartsReportService.getProductionMaintenanceTimeClassification();
        	String result = option.toString();
        	JSONObject jo = JSONObject.fromObject(result);
        	
            return resultHandler(jo);
        } catch (Exception e) {
        	e.printStackTrace();
        	e.printStackTrace(PrintException.printException());
            return resultHandler(exceptionHandle(ExceptionCode.ERROR_CODE));
        }
    }
    
    //获取成型维修用时分类图表数据
    @RequestMapping(value = "/formingMaintenanceTimeClassification.do", method = RequestMethod.GET)
    public @ResponseBody Object formingMaintenanceTimeClassification(HttpServletRequest request) {

        try {
        	GsonOption option = echartsReportService.getFormingMaintenanceTimeClassification();
        	String result = option.toString();
        	JSONObject jo = JSONObject.fromObject(result);
        	
            return resultHandler(jo);
        } catch (Exception e) {
        	e.printStackTrace();
        	e.printStackTrace(PrintException.printException());
            return resultHandler(exceptionHandle(ExceptionCode.ERROR_CODE));
        }
    }
    
    //获取总维修用时分类图表数据
    @RequestMapping(value = "/totalMaintenanceTimeClassification.do", method = RequestMethod.GET)
    public @ResponseBody Object totalMaintenanceTimeClassification(HttpServletRequest request) {

        try {
        	GsonOption option = echartsReportService.getTotalMaintenanceTimeClassification();
        	String result = option.toString();
        	JSONObject jo = JSONObject.fromObject(result);
            return resultHandler(jo);
        } catch (Exception e) {
        	e.printStackTrace();
        	e.printStackTrace(PrintException.printException());
            return resultHandler(exceptionHandle(ExceptionCode.ERROR_CODE));
        }
    }
    
}
