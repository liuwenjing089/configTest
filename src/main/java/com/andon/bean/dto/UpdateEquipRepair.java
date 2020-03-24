package com.andon.bean.dto;

import com.andon.bean.BaseEntity;

import java.io.Serializable;

public class UpdateEquipRepair  extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    private int id;
    private int reId;
    private String equipUseDepartment;
    private String equipUseSystem;
    private String equipUseClass;
    private String equipState;
    private String operator;
    private String safetyDeviceConfirm;
    private String maintainer;
    private String taskConfirmMan;
    private String mainTaskMan;
    private String subTaskMan;
    private String newaddTaskMan;
    private String supplier;
    private int isfirstEpisode;
    private String appearance;
    private String reason;
    private String management;
    private String preventPlan;
    private String saveConfirmChief;
    private String saveConfirmCommander;
    private int faultType;
    private int repairUsetimeType;
    private int isOverNum;
    private String locationUrl;
    private String partsListStr;
    //是否影响稼动  1影响  2不影响
    private int cropMovement;
    
    //故障现象描述   维修人填写
    private String faultDescription;
    
    //保留字段
    private String undefined1;
    
    private String undefined2;
    
    private String undefined3;
    
    private int state;     //repair主表状态
    
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

    public String getEquipState() {
        return equipState;
    }

    public void setEquipState(String equipState) {
        this.equipState = equipState;
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

    public int getIsfirstEpisode() {
        return isfirstEpisode;
    }

    public void setIsfirstEpisode(int isfirstEpisode) {
        this.isfirstEpisode = isfirstEpisode;
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

    public void setSaveConfirmCommander(String saveConfirmCommander) {
        this.saveConfirmCommander = saveConfirmCommander;
    }

    public int getFaultType() {
        return faultType;
    }

    public void setFaultType(int faultType) {
        this.faultType = faultType;
    }

    public int getRepairUsetimeType() {
        return repairUsetimeType;
    }

    public void setRepairUsetimeType(int repairUsetimeType) {
        this.repairUsetimeType = repairUsetimeType;
    }

    public int getIsOverNum() {
        return isOverNum;
    }

    public void setIsOverNum(int isOverNum) {
        this.isOverNum = isOverNum;
    }

    public String getLocationUrl() {
        return locationUrl;
    }

    public void setLocationUrl(String locationUrl) {
        this.locationUrl = locationUrl;
    }

    public String getPartsListStr() {
        return partsListStr;
    }

    public void setPartsListStr(String partsListStr) {
        this.partsListStr = partsListStr;
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

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}
    
}
