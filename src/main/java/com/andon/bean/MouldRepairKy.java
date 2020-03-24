package com.andon.bean;

import java.io.Serializable;

public class MouldRepairKy extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    private int id;
    private int reId;  //维修主表id
    private String workName; //作业名
    private String proName;  //工序名
    private String assetsNum; //资产管理编号
    private String workTips;  //作业指示事项
    private String commandMan; //作业指示者
    private String workMan; //作业者签字
    private int check1; //保全作业全体
    private int check2; //共同作业
    private int check3; //安全装置无效化作业
    private int check4; //重量物处置作业
    private int check5; //高空作业
    private int check6; //动火作业
    private int check7; //缺氧危险作业
    private int check8; //高压电器机械使用作业

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getReId() {
        return reId;
    }

    public void setReId(int reId) {
        this.reId = reId;
    }

    public String getWorkName() {
        return workName;
    }

    public void setWorkName(String workName) {
        this.workName = workName;
    }

    public String getProName() {
        return proName;
    }

    public void setProName(String proName) {
        this.proName = proName;
    }

    public String getAssetsNum() {
        return assetsNum;
    }

    public void setAssetsNum(String assetsNum) {
        this.assetsNum = assetsNum;
    }

    public String getWorkTips() {
        return workTips;
    }

    public void setWorkTips(String workTips) {
        this.workTips = workTips;
    }

    public String getCommandMan() {
        return commandMan;
    }

    public void setCommandMan(String commandMan) {
        this.commandMan = commandMan;
    }

    public String getWorkMan() {
        return workMan;
    }

    public void setWorkMan(String workMan) {
        this.workMan = workMan;
    }

    public int getCheck1() {
        return check1;
    }

    public void setCheck1(int check1) {
        this.check1 = check1;
    }

    public int getCheck2() {
        return check2;
    }

    public void setCheck2(int check2) {
        this.check2 = check2;
    }

    public int getCheck3() {
        return check3;
    }

    public void setCheck3(int check3) {
        this.check3 = check3;
    }

    public int getCheck4() {
        return check4;
    }

    public void setCheck4(int check4) {
        this.check4 = check4;
    }

    public int getCheck5() {
        return check5;
    }

    public void setCheck5(int check5) {
        this.check5 = check5;
    }

    public int getCheck6() {
        return check6;
    }

    public void setCheck6(int check6) {
        this.check6 = check6;
    }

    public int getCheck7() {
        return check7;
    }

    public void setCheck7(int check7) {
        this.check7 = check7;
    }

	public int getCheck8() {
		return check8;
	}

	public void setCheck8(int check8) {
		this.check8 = check8;
	}
    
}
