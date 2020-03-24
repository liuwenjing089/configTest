package com.andon.bean;

import java.io.Serializable;

public class EquipRepair extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    private int id;
    //设备Id
    private int reId;
    //安全环境问题总结
    private String equipUseDepartment;
    //设备使用系
    private String equipUseSystem;
    //设备使用班
    private String equipUseClass;
    //设备状态（参数）调整
    private String equipState;
    //操作者
    private String operator;
    //安全装置及3S确认
    private String safetyDeviceConfirm;
    //维修者
    private String maintainer;
    //作业完了确认者-班长
    private String taskConfirmMan;
    //主作业者
    private String mainTaskMan;
    //副作业者
    private String subTaskMan;
    //新增作业者
    private String newaddTaskMan;
    //供应商
    private String supplier;
    //是否首发
    private int isfirstEpisode;
    //现象
    private String appearance;
    //原因
    private String reason;
    //处置
    private String management;
    //再发防止策
    private String preventPlan;
    //保全确认科长
    private String saveConfirmChief;
    //保全确认系长
    private String saveConfirmCommander;
    //故障分类
    private int faultType;
    //维修用时分类
    private int repairUsetimeType;
    //完了代码1完了2应急处置
    private int isOverNum;
    //上传图片地址
    private String locationUrl;

    //ky作业名显示用
    private int line;
    
    //是否影响稼动  1影响  2不影响
    private int cropMovement;
    
    //故障现象描述   维修人填写
    private String faultDescription;
    
    //保留字段
    private String undefined1;
    
    private String undefined2;
    
    private String undefined3;
    

    public String getLocationUrl() {
        return locationUrl;
    }

    public void setLocationUrl(String locationUrl) {
        this.locationUrl = locationUrl;
    }

    public int getIsOverNum() {
        return isOverNum;
    }

    public void setIsOverNum(int isOverNum) {
        this.isOverNum = isOverNum;
    }

    public String getEquipState() {
        return equipState;
    }

    public void setEquipState(String equipState) {
        this.equipState = equipState;
    }

    public int getIsfirstEpisode() {
        return isfirstEpisode;
    }

    public void setIsfirstEpisode(int isfirstEpisode) {
        this.isfirstEpisode = isfirstEpisode;
    }

    public void setFaultType(int faultType) {
        this.faultType = faultType;
    }

    public void setRepairUsetimeType(int repairUsetimeType) {
        this.repairUsetimeType = repairUsetimeType;
    }

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

    public String getEquipUseDepartment() {
        return equipUseDepartment;
    }

    public void setEquipUseDepartment(String equipUseDepartment) {
        this.equipUseDepartment = equipUseDepartment;
    }

    public String getEquipUseSystem() {
        return equipUseSystem;
    }

    public void setEquipUseSystem(String equipUseSystem) {
        this.equipUseSystem = equipUseSystem;
    }

    public String getEquipUseClass() {
        return equipUseClass;
    }

    public void setEquipUseClass(String equipUseClass) {
        this.equipUseClass = equipUseClass;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public String getSafetyDeviceConfirm() {
        return safetyDeviceConfirm;
    }

    public void setSafetyDeviceConfirm(String safetyDeviceConfirm) {
        this.safetyDeviceConfirm = safetyDeviceConfirm;
    }

    public String getMaintainer() {
        return maintainer;
    }

    public void setMaintainer(String maintainer) {
        this.maintainer = maintainer;
    }

    public String getTaskConfirmMan() {
        return taskConfirmMan;
    }

    public void setTaskConfirmMan(String taskConfirmMan) {
        this.taskConfirmMan = taskConfirmMan;
    }

    public String getMainTaskMan() {
        return mainTaskMan;
    }

    public void setMainTaskMan(String mainTaskMan) {
        this.mainTaskMan = mainTaskMan;
    }

    public String getSubTaskMan() {
        return subTaskMan;
    }

    public void setSubTaskMan(String subTaskMan) {
        this.subTaskMan = subTaskMan;
    }

    public String getNewaddTaskMan() {
        return newaddTaskMan;
    }

    public void setNewaddTaskMan(String newaddTaskMan) {
        this.newaddTaskMan = newaddTaskMan;
    }

    public String getSupplier() {
        return supplier;
    }

    public void setSupplier(String supplier) {
        this.supplier = supplier;
    }

    public String getAppearance() {
        return appearance;
    }

    public void setAppearance(String appearance) {
        this.appearance = appearance;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getManagement() {
        return management;
    }

    public void setManagement(String management) {
        this.management = management;
    }

    public String getPreventPlan() {
        return preventPlan;
    }

    public void setPreventPlan(String preventPlan) {
        this.preventPlan = preventPlan;
    }

    public String getSaveConfirmChief() {
        return saveConfirmChief;
    }

    public void setSaveConfirmChief(String saveConfirmChief) {
        this.saveConfirmChief = saveConfirmChief;
    }

    public String getSaveConfirmCommander() {
        return saveConfirmCommander;
    }

    public int getFaultType() {
        return faultType;
    }

    public int getRepairUsetimeType() {
        return repairUsetimeType;
    }

    public void setSaveConfirmCommander(String saveConfirmCommander) {
        this.saveConfirmCommander = saveConfirmCommander;
    }

	public int getLine() {
		return line;
	}

	public void setLine(int line) {
		this.line = line;
	}

	public int getCropMovement() {
		return cropMovement;
	}

	public void setCropMovement(int cropMovement) {
		this.cropMovement = cropMovement;
	}

	public String getFaultDescription() {
		return faultDescription;
	}

	public void setFaultDescription(String faultDescription) {
		this.faultDescription = faultDescription;
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
 
	
}

