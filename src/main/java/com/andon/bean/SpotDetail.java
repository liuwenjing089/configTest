package com.andon.bean;

import java.io.Serializable;

public class SpotDetail extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    private int id;
    //任务ID
    private int taskId;
    //1:设备 2：摸具
    private int type;
    //设备模具ID
    private int detailId;
    //点检状态 0：未完成1：已完成
    private int state;
    //确认状态  科长 系长
    private int confirmState;
    //型点检确认人
    private String typeConfirmMan;
    //型点检确认时间
    private String typeConfirmTime;
    //型班长确认人
    private String classConfirmMan;
    //型班长确认时间
    private String classConfirmTime;
    //成型班长品质确认人
    private String formingConfirmMan;
    //成型班长品质确认时间
    private String formingConfirmTime;
    //系长确认
    private String confirmCommander;
    //系长确认时间
    private String confirmCommanderTime;
    //科长确认
    private String confirmChief;
    //科长确认时间
    private String confirmChiefTime;
    //点检保全
    private String spotMan;

    public String getSpotMan() {
        return spotMan;
    }

    public void setSpotMan(String spotMan) {
        this.spotMan = spotMan;
    }

    public String getConfirmCommander() {
        return confirmCommander;
    }

    public void setConfirmCommander(String confirmCommander) {
        this.confirmCommander = confirmCommander;
    }

    public String getConfirmChief() {
        return confirmChief;
    }

    public void setConfirmChief(String confirmChief) {
        this.confirmChief = confirmChief;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTaskId() {
        return taskId;
    }

    public void setTaskId(int taskId) {
        this.taskId = taskId;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getDetailId() {
        return detailId;
    }

    public void setDetailId(int detailId) {
        this.detailId = detailId;
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

    public String getTypeConfirmMan() {
        return typeConfirmMan;
    }

    public void setTypeConfirmMan(String typeConfirmMan) {
        this.typeConfirmMan = typeConfirmMan;
    }

    public String getTypeConfirmTime() {
        return typeConfirmTime;
    }

    public void setTypeConfirmTime(String typeConfirmTime) {
        this.typeConfirmTime = typeConfirmTime;
    }

    public String getClassConfirmMan() {
        return classConfirmMan;
    }

    public void setClassConfirmMan(String classConfirmMan) {
        this.classConfirmMan = classConfirmMan;
    }

    public String getClassConfirmTime() {
        return classConfirmTime;
    }

    public void setClassConfirmTime(String classConfirmTime) {
        this.classConfirmTime = classConfirmTime;
    }

    public String getFormingConfirmMan() {
        return formingConfirmMan;
    }

    public void setFormingConfirmMan(String formingConfirmMan) {
        this.formingConfirmMan = formingConfirmMan;
    }

    public String getFormingConfirmTime() {
        return formingConfirmTime;
    }

    public void setFormingConfirmTime(String formingConfirmTime) {
        this.formingConfirmTime = formingConfirmTime;
    }

    public String getConfirmCommanderTime() {
        return confirmCommanderTime;
    }

    public void setConfirmCommanderTime(String confirmCommanderTime) {
        this.confirmCommanderTime = confirmCommanderTime;
    }

    public String getConfirmChiefTime() {
        return confirmChiefTime;
    }

    public void setConfirmChiefTime(String confirmChiefTime) {
        this.confirmChiefTime = confirmChiefTime;
    }
}
