package com.andon.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.andon.bean.dto.EchartsReport;
import com.andon.commons.ConstantCode;
import com.andon.dao.EchartsRepairDao;
import com.andon.service.EchartsReportService;
import com.github.abel533.echarts.AxisPointer;
import com.github.abel533.echarts.Legend;
import com.github.abel533.echarts.Tooltip;
import com.github.abel533.echarts.axis.CategoryAxis;
import com.github.abel533.echarts.axis.ValueAxis;
import com.github.abel533.echarts.code.PointerType;
import com.github.abel533.echarts.code.SeriesType;
import com.github.abel533.echarts.code.Trigger;
import com.github.abel533.echarts.json.GsonOption;
import com.github.abel533.echarts.series.Line;

@Service
@Transactional
public class EchartsReportServiceImpl implements EchartsReportService {
	
    @Autowired
    private EchartsRepairDao EchartsReport;

    //获取生产故障分类图表数据
	@Override
	public GsonOption getProductionFaultClassification() throws Exception {
		EchartsReport echartsReport = EchartsReport.getFaultClassification(ConstantCode.LINE_TYPE_PRODUCTION);
		
		if(echartsReport == null){
			EchartsReport echartsReportFalse = new EchartsReport();
			echartsReportFalse.setPoorDesign("0");
			echartsReportFalse.setPoorOperation("0");
			echartsReportFalse.setBadCleaning("0");
			echartsReportFalse.setAgeing("0");
			echartsReport = echartsReportFalse;
		}
		//柱状图
		GsonOption gsonOption = new GsonOption();
		//设置标题
		gsonOption.title().text("生产故障分类");
		
		//设置柱状图代表的含义
		Legend legend = new Legend();
		legend.data("件数");
		gsonOption.legend(legend);
		
		// 坐标轴指示器，坐标轴触发有效
		Tooltip tooltip = new Tooltip();
		tooltip.trigger(Trigger.axis);
		
		// 默认为直线，可选为：'line' | 'shadow'
		AxisPointer axisPointer = new AxisPointer();
		axisPointer.type(PointerType.shadow);		
		tooltip.axisPointer(axisPointer);
		gsonOption.tooltip(tooltip);

		//设置横坐标类型标题
        CategoryAxis categoryAxis = new CategoryAxis();
		categoryAxis.data("设计不良","操作不良","清扫不良","老化不良");
		gsonOption.xAxis(categoryAxis);
		
		//设置纵坐标单位
	    ValueAxis valueAxis = new ValueAxis();
	    valueAxis.name("（件）");
		gsonOption.yAxis(valueAxis);
		
		//设置数据
		Line line = new Line();
		//柱体颜色
		line.itemStyle().normal().color("blue");
		//鼠标移动到柱体后显示的索引
		line.name("件数");
		line.type(SeriesType.bar);
		line.data(echartsReport.getPoorDesign(), echartsReport.getPoorOperation(), echartsReport.getBadCleaning(), echartsReport.getAgeing());
		gsonOption.series(line);
		
		return gsonOption;
	}

	//获取成型故障分类图表数据
	@Override
	public GsonOption getFormingFaultClassification() throws Exception {
		EchartsReport echartsReport = EchartsReport.getFaultClassification(ConstantCode.LINE_TYPE_MACHINE);
		if(echartsReport == null){
			EchartsReport echartsReportFalse = new EchartsReport();
			echartsReportFalse.setPoorDesign("0");
			echartsReportFalse.setPoorOperation("0");
			echartsReportFalse.setBadCleaning("0");
			echartsReportFalse.setAgeing("0");
			echartsReport = echartsReportFalse;
		}
		
		//柱状图
		GsonOption gsonOption = new GsonOption();
		//设置标题
		gsonOption.title().text("成型故障分类");
		
		//设置柱状图代表的含义
		Legend legend = new Legend();
		legend.data("件数");
		gsonOption.legend(legend);
		
		// 坐标轴指示器，坐标轴触发有效
		Tooltip tooltip = new Tooltip();
		tooltip.trigger(Trigger.axis);
		
		// 默认为直线，可选为：'line' | 'shadow'
		AxisPointer axisPointer = new AxisPointer();
		axisPointer.type(PointerType.shadow);		
		tooltip.axisPointer(axisPointer);
		gsonOption.tooltip(tooltip);

		//设置横坐标类型标题
        CategoryAxis categoryAxis = new CategoryAxis();
		categoryAxis.data("设计不良","操作不良","清扫不良","老化不良");
		gsonOption.xAxis(categoryAxis);
		
		//设置纵坐标单位
	    ValueAxis valueAxis = new ValueAxis();
	    valueAxis.name("（件）");
		gsonOption.yAxis(valueAxis);
		
		//设置数据
		Line line = new Line();
		//柱体颜色
		line.itemStyle().normal().color("blue");
		//鼠标移动到柱体后显示的索引
		line.name("件数");
		line.type(SeriesType.bar);
		line.data(echartsReport.getPoorDesign(), echartsReport.getPoorOperation(), echartsReport.getBadCleaning(), echartsReport.getAgeing());
		gsonOption.series(line);
		
		return gsonOption;
	}

	//获取总故障分类图表数据
	@Override
	public GsonOption getTotalFaultClassification() {
		EchartsReport echartsReport = EchartsReport.getFaultClassification(ConstantCode.LINE_TYPE_TOTAL);
		if(echartsReport == null){
			EchartsReport echartsReportFalse = new EchartsReport();
			echartsReportFalse.setPoorDesign("0");
			echartsReportFalse.setPoorOperation("0");
			echartsReportFalse.setBadCleaning("0");
			echartsReportFalse.setAgeing("0");
			echartsReport = echartsReportFalse;
		}
		//柱状图
		GsonOption gsonOption = new GsonOption();
		//设置标题
		gsonOption.title().text("总故障分类");
		
		//设置柱状图代表的含义
		Legend legend = new Legend();
		legend.data("件数");
		gsonOption.legend(legend);
		
		// 坐标轴指示器，坐标轴触发有效
		Tooltip tooltip = new Tooltip();
		tooltip.trigger(Trigger.axis);
		
		// 默认为直线，可选为：'line' | 'shadow'
		AxisPointer axisPointer = new AxisPointer();
		axisPointer.type(PointerType.shadow);		
		tooltip.axisPointer(axisPointer);
		gsonOption.tooltip(tooltip);

		//设置横坐标类型标题
        CategoryAxis categoryAxis = new CategoryAxis();
		categoryAxis.data("设计不良","操作不良","清扫不良","老化不良");
		gsonOption.xAxis(categoryAxis);
		
		//设置纵坐标单位
	    ValueAxis valueAxis = new ValueAxis();
	    valueAxis.name("（件）");
		gsonOption.yAxis(valueAxis);
		
		//设置数据
		Line line = new Line();
		//柱体颜色
//		line.itemStyle().normal().color("blue");
		//鼠标移动到柱体后显示的索引
		line.name("件数");
		line.type(SeriesType.bar);
		line.data(echartsReport.getPoorDesign(), echartsReport.getPoorOperation(), echartsReport.getBadCleaning(), echartsReport.getAgeing());
		gsonOption.series(line);
		
		return gsonOption;
	}

	//获取生产维修用时分类图表数据
	@Override
	public GsonOption getProductionMaintenanceTimeClassification() {
		EchartsReport echartsReport = EchartsReport.getMaintenanceTimeClassification(ConstantCode.LINE_TYPE_PRODUCTION);
		if(echartsReport == null){
			EchartsReport echartsReportFalse = new EchartsReport();
			echartsReportFalse.setFindFault("0");
			echartsReportFalse.setMaintenanceTime("0");
			echartsReportFalse.setProductUseTime("0");
			echartsReportFalse.setJobInconvenience("0");
			echartsReport = echartsReportFalse;
		}
		//柱状图
		GsonOption gsonOption = new GsonOption();
		//设置标题
		gsonOption.title().text("生产维修用时分类");
		
		//设置柱状图代表的含义
		Legend legend = new Legend();
		legend.data("H");
		gsonOption.legend(legend);
		
		// 坐标轴指示器，坐标轴触发有效
		Tooltip tooltip = new Tooltip();
		tooltip.trigger(Trigger.axis);
		
		// 默认为直线，可选为：'line' | 'shadow'
		AxisPointer axisPointer = new AxisPointer();
		axisPointer.type(PointerType.shadow);		
		tooltip.axisPointer(axisPointer);
		gsonOption.tooltip(tooltip);

		//设置横坐标类型标题
        CategoryAxis categoryAxis = new CategoryAxis();
		categoryAxis.data("查找故障","维修用时","备品用时","作业不便");
		gsonOption.xAxis(categoryAxis);
		
		//设置纵坐标单位
	    ValueAxis valueAxis = new ValueAxis();
	    valueAxis.name("（H）");
		gsonOption.yAxis(valueAxis);
		
		//设置数据
		Line line = new Line();
		//柱体颜色
		line.itemStyle().normal().color("blue");
		//鼠标移动到柱体后显示的索引
		line.name("H");
		line.type(SeriesType.bar);
		line.data(echartsReport.getFindFault(), echartsReport.getMaintenanceTime(), echartsReport.getProductUseTime(), echartsReport.getJobInconvenience());
		gsonOption.series(line);
		
		return gsonOption;
	}

    //获取成型维修用时分类图表数据
	@Override
	public GsonOption getFormingMaintenanceTimeClassification() {
		EchartsReport echartsReport = EchartsReport.getMaintenanceTimeClassification(ConstantCode.LINE_TYPE_MACHINE);
		
		if(echartsReport == null){
			EchartsReport echartsReportFalse = new EchartsReport();
			echartsReportFalse.setFindFault("0");
			echartsReportFalse.setMaintenanceTime("0");
			echartsReportFalse.setProductUseTime("0");
			echartsReportFalse.setJobInconvenience("0");
			echartsReport = echartsReportFalse;
		}
		
		//柱状图
		GsonOption gsonOption = new GsonOption();
		//设置标题
		gsonOption.title().text("成型维修用时分类");
		
		//设置柱状图代表的含义
		Legend legend = new Legend();
		legend.data("H");
		gsonOption.legend(legend);
		
		// 坐标轴指示器，坐标轴触发有效
		Tooltip tooltip = new Tooltip();
		tooltip.trigger(Trigger.axis);
		
		// 默认为直线，可选为：'line' | 'shadow'
		AxisPointer axisPointer = new AxisPointer();
		axisPointer.type(PointerType.shadow);		
		tooltip.axisPointer(axisPointer);
		gsonOption.tooltip(tooltip);

		//设置横坐标类型标题
        CategoryAxis categoryAxis = new CategoryAxis();
		categoryAxis.data("查找故障","维修用时","备品用时","作业不便");
		gsonOption.xAxis(categoryAxis);
		
		//设置纵坐标单位
	    ValueAxis valueAxis = new ValueAxis();
	    valueAxis.name("（H）");
		gsonOption.yAxis(valueAxis);
		
		//设置数据
		Line line = new Line();
		//柱体颜色
		line.itemStyle().normal().color("blue");
		//鼠标移动到柱体后显示的索引
		line.name("H");
		line.type(SeriesType.bar);
		line.data(echartsReport.getFindFault(), echartsReport.getMaintenanceTime(), echartsReport.getProductUseTime(), echartsReport.getJobInconvenience());
		gsonOption.series(line);
		
		return gsonOption;
	}

    //获取总维修用时分类图表数据
	@Override
	public GsonOption getTotalMaintenanceTimeClassification() {
		EchartsReport echartsReport = EchartsReport.getMaintenanceTimeClassification(ConstantCode.LINE_TYPE_TOTAL);
		if(echartsReport == null){
			EchartsReport echartsReportFalse = new EchartsReport();
			echartsReportFalse.setFindFault("0");
			echartsReportFalse.setMaintenanceTime("0");
			echartsReportFalse.setProductUseTime("0");
			echartsReportFalse.setJobInconvenience("0");
			echartsReport = echartsReportFalse;
		}
		
		//柱状图
		GsonOption gsonOption = new GsonOption();
		//设置标题
		gsonOption.title().text("总维修用时分类");
		
		//设置柱状图代表的含义
		Legend legend = new Legend();
		legend.data("H");
		gsonOption.legend(legend);
		
		// 坐标轴指示器，坐标轴触发有效
		Tooltip tooltip = new Tooltip();
		tooltip.trigger(Trigger.axis);
		
		// 默认为直线，可选为：'line' | 'shadow'
		AxisPointer axisPointer = new AxisPointer();
		axisPointer.type(PointerType.shadow);		
		tooltip.axisPointer(axisPointer);
		gsonOption.tooltip(tooltip);

		//设置横坐标类型标题
        CategoryAxis categoryAxis = new CategoryAxis();
		categoryAxis.data("查找故障","维修用时","备品用时","作业不便");
		gsonOption.xAxis(categoryAxis);
		
		//设置纵坐标单位
	    ValueAxis valueAxis = new ValueAxis();
	    valueAxis.name("（H）");
		gsonOption.yAxis(valueAxis);
		
		//设置数据
		Line line = new Line();
		//柱体颜色
//		line.itemStyle().normal().color("blue");
		//鼠标移动到柱体后显示的索引
		line.name("H");
		line.type(SeriesType.bar);
		line.data(echartsReport.getFindFault(), echartsReport.getMaintenanceTime(), echartsReport.getProductUseTime(), echartsReport.getJobInconvenience());
		gsonOption.series(line);
		
		return gsonOption;
	}

	
}
