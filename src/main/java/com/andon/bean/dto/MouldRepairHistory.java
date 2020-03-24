package com.andon.bean.dto;

import java.io.Serializable;
import java.util.List;

import com.andon.bean.BaseEntity;
import com.andon.bean.MouldFaultParts;

public class MouldRepairHistory  extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    private int mouldeId;
    private String factory;
    private String vehicleType;
    private String anotherName;
    private String figureNumber;
    private String repairId;
    private String reportRepairTime;
    private String orderTime;
    private String endTime;
    private String confirmationTime;
    private String qualityMonitorTime;
    private String preDepChiefConTime;
    private String preSecChiefConTime;   
    private int failurePeriod;
    private String phenomenalDescription;
    private String faultType;
    private String faultReason;
    private String emergencyDisposal;
    
    private String mouldUser;
    private String shiftLeader;
    private String preservationDepartment;
    private String preservationSectionChief;
    
    private int faultTypeCodeListId;
    private String faultTypeCodeName;
    private int faultReasonCodeListId;
    private String faultReasonCodeName;
    
    private String mouldUserName;
    private String shiftLeaderName;
    private String preservationDepartmentName;
    private String preservationSectionChiefName;
    
    private int testMouldResult;
    private String testMouldEndTime;
    
    private List<MouldFaultParts> mouldFaultPartsList;
    
	public int getMouldeId() {
		return mouldeId;
	}
	public void setMouldeId(int mouldeId) {
		this.mouldeId = mouldeId;
	}
	public String getRepairId() {
		return repairId;
	}
	public void setRepairId(String repairId) {
		this.repairId = repairId;
	}
	public String getReportRepairTime() {
		return reportRepairTime;
	}
	public void setReportRepairTime(String reportRepairTime) {
		this.reportRepairTime = reportRepairTime;
	}
	public String getOrderTime() {
		return orderTime;
	}
	public void setOrderTime(String orderTime) {
		this.orderTime = orderTime;
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

	public int getTestMouldResult() {
		return testMouldResult;
	}
	public void setTestMouldResult(int testMouldResult) {
		this.testMouldResult = testMouldResult;
	}
	public String getTestMouldEndTime() {
		return testMouldEndTime;
	}
	public void setTestMouldEndTime(String testMouldEndTime) {
		this.testMouldEndTime = testMouldEndTime;
	}
	public String getVehicleType() {
		return vehicleType;
	}
	public void setVehicleType(String vehicleType) {
		this.vehicleType = vehicleType;
	}
	public String getAnotherName() {
		return anotherName;
	}
	public void setAnotherName(String anotherName) {
		this.anotherName = anotherName;
	}
	public String getFigureNumber() {
		return figureNumber;
	}
	public void setFigureNumber(String figureNumber) {
		this.figureNumber = figureNumber;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	public String getConfirmationTime() {
		return confirmationTime;
	}
	public void setConfirmationTime(String confirmationTime) {
		this.confirmationTime = confirmationTime;
	}
	public String getQualityMonitorTime() {
		return qualityMonitorTime;
	}
	public void setQualityMonitorTime(String qualityMonitorTime) {
		this.qualityMonitorTime = qualityMonitorTime;
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
	public String getFaultType() {
		return faultType;
	}
	public void setFaultType(String faultType) {
		this.faultType = faultType;
	}
	public String getFaultReason() {
		return faultReason;
	}
	public void setFaultReason(String faultReason) {
		this.faultReason = faultReason;
	}
	public String getEmergencyDisposal() {
		return emergencyDisposal;
	}
	public void setEmergencyDisposal(String emergencyDisposal) {
		this.emergencyDisposal = emergencyDisposal;
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
	public String getPreservationSectionChief() {
		return preservationSectionChief;
	}
	public void setPreservationSectionChief(String preservationSectionChief) {
		this.preservationSectionChief = preservationSectionChief;
	}
	public int getFaultTypeCodeListId() {
		return faultTypeCodeListId;
	}
	public void setFaultTypeCodeListId(int faultTypeCodeListId) {
		this.faultTypeCodeListId = faultTypeCodeListId;
	}
	public String getFaultTypeCodeName() {
		return faultTypeCodeName;
	}
	public void setFaultTypeCodeName(String faultTypeCodeName) {
		this.faultTypeCodeName = faultTypeCodeName;
	}
	public int getFaultReasonCodeListId() {
		return faultReasonCodeListId;
	}
	public void setFaultReasonCodeListId(int faultReasonCodeListId) {
		this.faultReasonCodeListId = faultReasonCodeListId;
	}
	public String getFaultReasonCodeName() {
		return faultReasonCodeName;
	}
	public void setFaultReasonCodeName(String faultReasonCodeName) {
		this.faultReasonCodeName = faultReasonCodeName;
	}
	public String getMouldUserName() {
		return mouldUserName;
	}
	public void setMouldUserName(String mouldUserName) {
		this.mouldUserName = mouldUserName;
	}
	public String getShiftLeaderName() {
		return shiftLeaderName;
	}
	public void setShiftLeaderName(String shiftLeaderName) {
		this.shiftLeaderName = shiftLeaderName;
	}
	public String getPreservationDepartmentName() {
		return preservationDepartmentName;
	}
	public void setPreservationDepartmentName(String preservationDepartmentName) {
		this.preservationDepartmentName = preservationDepartmentName;
	}
	public String getPreservationSectionChiefName() {
		return preservationSectionChiefName;
	}
	public void setPreservationSectionChiefName(String preservationSectionChiefName) {
		this.preservationSectionChiefName = preservationSectionChiefName;
	}
	public List<MouldFaultParts> getMouldFaultPartsList() {
		return mouldFaultPartsList;
	}
	public void setMouldFaultPartsList(List<MouldFaultParts> mouldFaultPartsList) {
		this.mouldFaultPartsList = mouldFaultPartsList;
	}
	public String getFactory() {
		return factory;
	}
	public void setFactory(String factory) {
		this.factory = factory;
	}
    
    
    
}
