package com.andon.bean.dto;

import java.io.Serializable;

public class SpotDetailEquip implements Serializable {
    private static final long serialVersionUID = 1L;
    private int id;
    private int taskId;
    private int equipId;
    private String assetNum;
    private String equipNum;
    private String equipName;
    private String equipModel;
    private String standard;
    private String classConfirmMan;
    private int state;
    private int confirmState;
    private String spotInterval;
    private String beginTime;
    private int beginIndex;
    private int pageSize;

    public int getTaskId() {
        return taskId;
    }

    public void setTaskId(int taskId) {
        this.taskId = taskId;
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

    public String getClassConfirmMan() {
        return classConfirmMan;
    }

    public void setClassConfirmMan(String classConfirmMan) {
        this.classConfirmMan = classConfirmMan;
    }

    public int getEquipId() {
        return equipId;
    }

    public void setEquipId(int equipId) {
        this.equipId = equipId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getEquipName() {
        return equipName;
    }

    public void setEquipName(String equipName) {
        this.equipName = equipName;
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

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public int getConfirmState() {
        return confirmState;
    }

    public void setConfirmState(int confirmState) {
        this.confirmState = confirmState;
    }

    public int getBeginIndex() {
        return beginIndex;
    }

    public void setBeginIndex(int beginIndex) {
        this.beginIndex = beginIndex;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }
}
