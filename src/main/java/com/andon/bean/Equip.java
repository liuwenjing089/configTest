package com.andon.bean;

import java.io.Serializable;

public class Equip extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    //设备id
    private int id;
    //s生产线id
    private int lineId;
    //工厂
    private int factory;
    //资产编码
    private String assetNum;
    //设备编码
    private String equipNum;
    //设备名称
    private String equipName;
    //别名
    private String anotherName;
    //设备描述
    private String equipDescription;
    //附属设备
    private String subsidiaryEq;
    //是否瓶颈设备
    private int isBottleneck;
    //制造商
    private String manufactory;
    //制造国别
    private String country;
    //制造年份
    private String manuYear;
    //使用开始时间
    private String useBeginTime;
    //年限
    private String yearsLimit;
    //型号
    private String equipModel;
    //规格
    private String standard;

    //预留字段
    private String undefined1;
    private String undefined2;
    private String undefined3;
    
    //工厂名称
    private String factoryName;

    public int getFactory() {
        return factory;
    }

    public void setFactory(int factory) {
        this.factory = factory;
    }

    public String getAssetNum() {
        return assetNum;
    }

    public void setAssetNum(String assetNum) {
        this.assetNum = assetNum;
    }

    public String getEquipNum() {
        return equipNum;
    }

    public void setEquipNum(String equipNum) {
        this.equipNum = equipNum;
    }

    public String getSubsidiaryEq() {
        return subsidiaryEq;
    }

    public void setSubsidiaryEq(String subsidiaryEq) {
        this.subsidiaryEq = subsidiaryEq;
    }

    public int getIsBottleneck() {
        return isBottleneck;
    }

    public void setIsBottleneck(int isBottleneck) {
        this.isBottleneck = isBottleneck;
    }

    public String getManufactory() {
        return manufactory;
    }

    public void setManufactory(String manufactory) {
        this.manufactory = manufactory;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getManuYear() {
        return manuYear;
    }

    public void setManuYear(String manuYear) {
        this.manuYear = manuYear;
    }

    public int getLineId() {
        return lineId;
    }

    public void setLineId(int lineId) {
        this.lineId = lineId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEquipName() {
        return equipName;
    }

    public void setEquipName(String equipName) {
        this.equipName = equipName;
    }

    public String getEquipDescription() {
        return equipDescription;
    }

    public void setEquipDescription(String equipDescription) {
        this.equipDescription = equipDescription;
    }

    public String getUseBeginTime() {
        return useBeginTime;
    }

    public void setUseBeginTime(String useBeginTime) {
        this.useBeginTime = useBeginTime;
    }

    public String getYearsLimit() {
        return yearsLimit;
    }

    public void setYearsLimit(String yearsLimit) {
        this.yearsLimit = yearsLimit;
    }

    public String getEquipModel() {
        return equipModel;
    }

    public void setEquipModel(String equipModel) {
        this.equipModel = equipModel;
    }

    public String getStandard() {
        return standard;
    }

    public void setStandard(String standard) {
        this.standard = standard;
    }

    public String getAnotherName() {
        return anotherName;
    }

    public void setAnotherName(String anotherName) {
        this.anotherName = anotherName;
    }

    public String getUndefined1() {
        return undefined1;
    }

    public void setUndefined1(String undefined1) {
        this.undefined1 = undefined1;
    }

    public String getUndefined2() {
        return undefined2;
    }

    public void setUndefined2(String undefined2) {
        this.undefined2 = undefined2;
    }

    public String getUndefined3() {
        return undefined3;
    }

    public void setUndefined3(String undefined3) {
        this.undefined3 = undefined3;
    }

	public String getFactoryName() {
		return factoryName;
	}

	public void setFactoryName(String factoryName) {
		this.factoryName = factoryName;
	}

}
