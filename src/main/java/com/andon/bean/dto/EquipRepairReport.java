package com.andon.bean.dto;

import java.io.Serializable;

public class EquipRepairReport implements Serializable {
    private static final long serialVersionUID = 1L;

    //设备名称
    private String equipName;
    //平均间隔时间
    private String averageIntervalTime;
    //维修时长
    private String maintenanceTime;
    //平均维修时长
    private String averageMaintenanceTime;
	public String getEquipName() {
		return equipName;
	}
	public void setEquipName(String equipName) {
		this.equipName = equipName;
	}
	public String getAverageIntervalTime() {
		return averageIntervalTime;
	}
	public void setAverageIntervalTime(String averageIntervalTime) {
		this.averageIntervalTime = averageIntervalTime;
	}
	public String getMaintenanceTime() {
		return maintenanceTime;
	}
	public void setMaintenanceTime(String maintenanceTime) {
		this.maintenanceTime = maintenanceTime;
	}
	public String getAverageMaintenanceTime() {
		return averageMaintenanceTime;
	}
	public void setAverageMaintenanceTime(String averageMaintenanceTime) {
		this.averageMaintenanceTime = averageMaintenanceTime;
	}
    
    
    

}
