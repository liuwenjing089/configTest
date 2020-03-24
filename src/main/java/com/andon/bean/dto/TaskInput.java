package com.andon.bean.dto;

import java.io.Serializable;

public class TaskInput implements Serializable {
    private static final long serialVersionUID = 1L;
    private String spotName;
    private int spotType;
    private String spotInterval;
    private String beginTime;
    private String endTime;
    private int year;
    private int state;
    private int times;

    public String getSpotName() {
        return spotName;
    }

    public void setSpotName(String spotName) {
        this.spotName = spotName;
    }

    public int getSpotType() {
        return spotType;
    }

    public void setSpotType(int spotType) {
        this.spotType = spotType;
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

    public int getTimes() {
        return times;
    }

    public void setTimes(int times) {
        this.times = times;
    }

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}
    
    
}
