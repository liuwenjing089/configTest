package com.andon.bean.dto;

public class TestMouldRepair {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	

	private int repairId;
	
	private String repairIdString;
		
	private String repairNumber; //报修编号

	private String vehicleType;//模具车种
	
	private String figureNumber;//模具品番
	
	private String lineName;//成型机名称
	
	private String phenomenalDescription;//故障现象
	
	private String state;//状态
	
	private String applicantName;//报修人姓名
	
	private String reportRepairTime;//报修时间

	public int getRepairId() {
		return repairId;
	}

	public void setRepairId(int repairId) {
		this.repairId = repairId;
	}

	public String getRepairNumber() {
		return repairNumber;
	}

	public void setRepairNumber(String repairNumber) {
		this.repairNumber = repairNumber;
	}

	public String getVehicleType() {
		return vehicleType;
	}

	public void setVehicleType(String vehicleType) {
		this.vehicleType = vehicleType;
	}

	public String getFigureNumber() {
		return figureNumber;
	}

	public void setFigureNumber(String figureNumber) {
		this.figureNumber = figureNumber;
	}

	public String getLineName() {
		return lineName;
	}

	public void setLineName(String lineName) {
		this.lineName = lineName;
	}

	public String getPhenomenalDescription() {
		return phenomenalDescription;
	}

	public void setPhenomenalDescription(String phenomenalDescription) {
		this.phenomenalDescription = phenomenalDescription;
	}



	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}



	public String getApplicantName() {
		return applicantName;
	}

	public void setApplicantName(String applicantName) {
		this.applicantName = applicantName;
	}

	public String getReportRepairTime() {
		return reportRepairTime;
	}

	public void setReportRepairTime(String reportRepairTime) {
		this.reportRepairTime = reportRepairTime;
	}

	public String getRepairIdString() {
		return repairIdString;
	}

	public void setRepairIdString(String repairIdString) {
		this.repairIdString = repairIdString;
	}
	

    
}
