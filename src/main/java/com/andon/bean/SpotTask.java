package com.andon.bean;

import java.io.Serializable;

public class SpotTask extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    private int id;
    //1:设备 2：摸具
    private int spotType;
    //任务名称
    private String spotName;
    //设备（任务周期） 模具（使用次数）
    private String spotInterval;
    //任务开始时间
    private String beginTime;
    //任务结束时间
    private String endTime;
    //状态  0：未完成 1：已完成
    private int state;

    //非数据库字段
    private String divName;
    private int detailId;
    
    //所属工厂
    private String factory;

    public String getDivName() {
        return divName;
    }

    public void setDivName(String divName) {
        this.divName = divName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSpotType() {
        return spotType;
    }

    public void setSpotType(int spotType) {
        this.spotType = spotType;
    }

    public String getSpotName() {
        return spotName;
    }

    public void setSpotName(String spotName) {
        this.spotName = spotName;
    }

    public String getSpotInterval() {
        return spotInterval;
    }

    public void setSpotInterval(String spotInterval) {
        this.spotInterval = spotInterval;
    }

    public String getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(String beginTime) {
        this.beginTime = beginTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public int getDetailId() {
        return detailId;
    }

    public void setDetailId(int detailId) {
        this.detailId = detailId;
    }

	public String getFactory() {
		return factory;
	}

	public void setFactory(String factory) {
		this.factory = factory;
	}

}
