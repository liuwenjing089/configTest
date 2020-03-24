package com.andon.bean;

import java.util.List;

import org.hibernate.validator.constraints.NotEmpty;

import com.andon.validateInterface.First;

public class MouldRepair extends BaseEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int id;
	
	private String reId;
	
	private String mouldUser;  //模具担当
	
	private String shiftLeader;  //生产班长
	
	private String preservationDepartment;  //保全系长
	
	private String preservationSectionChief; //保全科长
	
	private String maintenanceDependencyDep;  //维修依赖部门
	
	private String maintenanceDependencyUse;  //维修依赖者

	private String warehouseTimeStart;  //再发防止计划实施日期   开始
	
	
	private String warehouseTimeEnd;  //再发防止计划实施日期    结束
	
	
	private int failurePeriod;  //故障时期 
	
	private String phenomenalDescription;  //故障内容

	private String appearance;
	
	private String reason;  //原因
	
	private String emergencyDisposal;  //紧急处置
	
	private String permanentGame;  //永久对策
	
	private String faultLocationUrl;  //模具不良照片
	
	private String productUrl;  //制品不良照片
	
	private String formingMachineUrl;  //维修照片
	
	private String maintenanceCompletedUrl;  //维修完成照片
	
	private String repairNumber; //报修编号

	private int faultType; //故障类型
	private String faultParts; //损坏部件
	private String faultReason; //故障原因
	private int line;//成型机
	
	//用于页面显示
	private String shiftLeaderView; //生产线班长名字
	private String mouldUserView; //品保班长名字
	private String preservationDepartmentView; //保全系长名字
	private String preservationSectionChiefView; //保全科长名字
	
	//传值使用
	private int mouldId;
	
	private String applicant;

	private String reportRepairTime;
	
	private String orderTime;
	
    private String beginTime;
    
    private String endTime;
    
    private String confirmationTime;
    
    private String qualityMonitorTime; //模具品保确认时间
    
    private String preDepChiefConTime;
    
    private String preSecChiefConTime;
    
    private String figureNumber;
    
    private String anotherName;
    
    private String model;
    
    private int state;
    
    private boolean flag;

    private List<MouldFaultParts> mouldFaultPartsList;
	private String partsListStr;


	//生产线名称
	private String lineName;
	
	@NotEmpty(message = "{MouldRepair.stamp.empty}", groups = {First.class})
	private String stamp; //防重复提交标记
	
	private String typeGroupKey; //所在模具报修类型的groupKey

	public String getAppearance() {
		return appearance;
	}

	public void setAppearance(String appearance) {
		this.appearance = appearance;
	}

	private List<MouldFaultParts> partsList;

	public List<MouldFaultParts> getPartsList() {
		return partsList;
	}

	public void setPartsList(List<MouldFaultParts> partsList) {
		this.partsList = partsList;
	}

	public int getFaultType() {
		return faultType;
	}

	public void setFaultType(int faultType) {
		this.faultType = faultType;
	}

	public String getFaultParts() {
		return faultParts;
	}

	public void setFaultParts(String faultParts) {
		this.faultParts = faultParts;
	}

	public String getFaultReason() {
		return faultReason;
	}

	public void setFaultReason(String faultReason) {
		this.faultReason = faultReason;
	}



	public String getLineName() {
		return lineName;
	}

	public void setLineName(String lineName) {
		this.lineName = lineName;
	}

	public List<MouldFaultParts> getMouldFaultPartsList() {
		return mouldFaultPartsList;
	}

	public void setMouldFaultPartsList(List<MouldFaultParts> mouldFaultPartsList) {
		this.mouldFaultPartsList = mouldFaultPartsList;
	}

	public String getPartsListStr() {
		return partsListStr;
	}

	public void setPartsListStr(String partsListStr) {
		this.partsListStr = partsListStr;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getMouldId() {
		return mouldId;
	}

	public void setMouldId(int mouldId) {
		this.mouldId = mouldId;
	}

	public String getMouldUser() {
		return mouldUser;
	}

	public void setMouldUser(String mouldUser) {
		this.mouldUser = mouldUser;
	}

	public String getShiftLeader() {
		return shiftLeader;
	}

	public void setShiftLeader(String shiftLeader) {
		this.shiftLeader = shiftLeader;
	}

	public String getPreservationDepartment() {
		return preservationDepartment;
	}

	public void setPreservationDepartment(String preservationDepartment) {
		this.preservationDepartment = preservationDepartment;
	}

	public String getMaintenanceDependencyDep() {
		return maintenanceDependencyDep;
	}

	public void setMaintenanceDependencyDep(String maintenanceDependencyDep) {
		this.maintenanceDependencyDep = maintenanceDependencyDep;
	}

	public String getMaintenanceDependencyUse() {
		return maintenanceDependencyUse;
	}

	public void setMaintenanceDependencyUse(String maintenanceDependencyUse) {
		this.maintenanceDependencyUse = maintenanceDependencyUse;
	}

	public String getWarehouseTimeStart() {
		return warehouseTimeStart;
	}

	public void setWarehouseTimeStart(String warehouseTimeStart) {
		this.warehouseTimeStart = warehouseTimeStart;
	}

	public String getWarehouseTimeEnd() {
		return warehouseTimeEnd;
	}

	public void setWarehouseTimeEnd(String warehouseTimeEnd) {
		this.warehouseTimeEnd = warehouseTimeEnd;
	}

	public int getFailurePeriod() {
		return failurePeriod;
	}

	public void setFailurePeriod(int failurePeriod) {
		this.failurePeriod = failurePeriod;
	}

	public String getPhenomenalDescription() {
		return phenomenalDescription;
	}

	public void setPhenomenalDescription(String phenomenalDescription) {
		this.phenomenalDescription = phenomenalDescription;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public String getEmergencyDisposal() {
		return emergencyDisposal;
	}

	public void setEmergencyDisposal(String emergencyDisposal) {
		this.emergencyDisposal = emergencyDisposal;
	}

	public String getPermanentGame() {
		return permanentGame;
	}

	public void setPermanentGame(String permanentGame) {
		this.permanentGame = permanentGame;
	}

	public String getFaultLocationUrl() {
		return faultLocationUrl;
	}

	public void setFaultLocationUrl(String faultLocationUrl) {
		this.faultLocationUrl = faultLocationUrl;
	}

	public String getProductUrl() {
		return productUrl;
	}

	public void setProductUrl(String productUrl) {
		this.productUrl = productUrl;
	}

	public String getFormingMachineUrl() {
		return formingMachineUrl;
	}

	public void setFormingMachineUrl(String formingMachineUrl) {
		this.formingMachineUrl = formingMachineUrl;
	}

	public String getReId() {
		return reId;
	}

	public void setReId(String reId) {
		this.reId = reId;
	}

	public String getApplicant() {
		return applicant;
	}

	public void setApplicant(String applicant) {
		this.applicant = applicant;
	}

	public String getReportRepairTime() {
		return reportRepairTime;
	}

	public void setReportRepairTime(String reportRepairTime) {
		this.reportRepairTime = reportRepairTime;
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

	public String getFigureNumber() {
		return figureNumber;
	}

	public void setFigureNumber(String figureNumber) {
		this.figureNumber = figureNumber;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public String getPreservationSectionChief() {
		return preservationSectionChief;
	}

	public void setPreservationSectionChief(String preservationSectionChief) {
		this.preservationSectionChief = preservationSectionChief;
	}

	public String getOrderTime() {
		return orderTime;
	}

	public void setOrderTime(String orderTime) {
		this.orderTime = orderTime;
	}

	public String getConfirmationTime() {
		return confirmationTime;
	}

	public void setConfirmationTime(String confirmationTime) {
		this.confirmationTime = confirmationTime;
	}

	public String getPreDepChiefConTime() {
		return preDepChiefConTime;
	}

	public void setPreDepChiefConTime(String preDepChiefConTime) {
		this.preDepChiefConTime = preDepChiefConTime;
	}

	public String getPreSecChiefConTime() {
		return preSecChiefConTime;
	}

	public void setPreSecChiefConTime(String preSecChiefConTime) {
		this.preSecChiefConTime = preSecChiefConTime;
	}

	public boolean isFlag() {
		return flag;
	}

	public void setFlag(boolean flag) {
		this.flag = flag;
	}

	public int getLine() {
		return line;
	}

	public void setLine(int line) {
		this.line = line;
	}

	public String getStamp() {
		return stamp;
	}

	public void setStamp(String stamp) {
		this.stamp = stamp;
	}

	public String getMaintenanceCompletedUrl() {
		return maintenanceCompletedUrl;
	}

	public void setMaintenanceCompletedUrl(String maintenanceCompletedUrl) {
		this.maintenanceCompletedUrl = maintenanceCompletedUrl;
	}


	public String getShiftLeaderView() {
		return shiftLeaderView;
	}

	public void setShiftLeaderView(String shiftLeaderView) {
		this.shiftLeaderView = shiftLeaderView;
	}

	public String getPreservationDepartmentView() {
		return preservationDepartmentView;
	}

	public void setPreservationDepartmentView(String preservationDepartmentView) {
		this.preservationDepartmentView = preservationDepartmentView;
	}

	public String getPreservationSectionChiefView() {
		return preservationSectionChiefView;
	}

	public void setPreservationSectionChiefView(String preservationSectionChiefView) {
		this.preservationSectionChiefView = preservationSectionChiefView;
	}

	public String getRepairNumber() {
		return repairNumber;
	}

	public void setRepairNumber(String repairNumber) {
		this.repairNumber = repairNumber;
	}

	public String getAnotherName() {
		return anotherName;
	}

	public void setAnotherName(String anotherName) {
		this.anotherName = anotherName;
	}

	public String getMouldUserView() {
		return mouldUserView;
	}

	public void setMouldUserView(String mouldUserView) {
		this.mouldUserView = mouldUserView;
	}

	public String getQualityMonitorTime() {
		return qualityMonitorTime;
	}

	public void setQualityMonitorTime(String qualityMonitorTime) {
		this.qualityMonitorTime = qualityMonitorTime;
	}

	public String getTypeGroupKey() {
		return typeGroupKey;
	}

	public void setTypeGroupKey(String typeGroupKey) {
		this.typeGroupKey = typeGroupKey;
	}
 
	
    
}
