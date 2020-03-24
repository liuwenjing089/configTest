package com.andon.bean;

public class SpotCheck extends BaseEntity{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int id;

	private int ruleId;
	
	private int spotDetailId; //任务明细表ID
	
	private int type;  //类型（1.设备  2.模具）

	private String spotTypeName;//点检类型
	private String principalNumbe;  //编号（设备存  设备编号，模具存  图号）
	
	private String principalName;  //名称（设备存  机器名称，模具存  手输）
	
	private String department;  //部门（仅设备使用）
	private String spotInspection;

	private String spotPosition;  //点检部位

	private String checkProject;  //检查项目（模具）   点检基准（设备）
	
	private String checkMethod;  //检查项目（模具）   点检基准（设备）
	
	private String spotInterval;  //间隔
	
	private String planTime;  //计划时间
	
	private int spotState;  //点检状态
	
	private String spotTime;  //点检时间
	
	private int isUnusual;  //有无异常  0无  1有
	
	private String unusualText;  //异常内容
	
	private int isUpdate;  //有无修正 0无  1有
	
	private String updateText;  //修正内容
	
	private String remarks;  //备注
	
	private String prictureUrl;  //图片路径

	private String uploadUrl;  //上传图片路径

	//传值用
	private int state;
	private String mouldRuleUrl;  //模具总图

	public String getMouldRuleUrl() {
		return mouldRuleUrl;
	}

	public void setMouldRuleUrl(String mouldRuleUrl) {
		this.mouldRuleUrl = mouldRuleUrl;
	}

	public String getUploadUrl() {
		return uploadUrl;
	}

	public void setUploadUrl(String uploadUrl) {
		this.uploadUrl = uploadUrl;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public String getSpotTypeName() {
		return spotTypeName;
	}

	public void setSpotTypeName(String spotTypeName) {
		this.spotTypeName = spotTypeName;
	}

	public String getSpotInspection() {
		return spotInspection;
	}

	public void setSpotInspection(String spotInspection) {
		this.spotInspection = spotInspection;
	}

	public int getRuleId() {
		return ruleId;
	}

	public void setRuleId(int ruleId) {
		this.ruleId = ruleId;
	}

	public String getSpotInterval() {
		return spotInterval;
	}

	public void setSpotInterval(String spotInterval) {
		this.spotInterval = spotInterval;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getSpotDetailId() {
		return spotDetailId;
	}

	public void setSpotDetailId(int spotDetailId) {
		this.spotDetailId = spotDetailId;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getPrincipalNumbe() {
		return principalNumbe;
	}

	public void setPrincipalNumbe(String principalNumbe) {
		this.principalNumbe = principalNumbe;
	}

	public String getPrincipalName() {
		return principalName;
	}

	public void setPrincipalName(String principalName) {
		this.principalName = principalName;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getSpotPosition() {
		return spotPosition;
	}

	public void setSpotPosition(String spotPosition) {
		this.spotPosition = spotPosition;
	}

	public String getCheckProject() {
		return checkProject;
	}

	public void setCheckProject(String checkProject) {
		this.checkProject = checkProject;
	}

	public String getCheckMethod() {
		return checkMethod;
	}

	public void setCheckMethod(String checkMethod) {
		this.checkMethod = checkMethod;
	}

	public String getPlanTime() {
		return planTime;
	}

	public void setPlanTime(String planTime) {
		this.planTime = planTime;
	}

	public int getSpotState() {
		return spotState;
	}

	public void setSpotState(int spotState) {
		this.spotState = spotState;
	}

	public String getSpotTime() {
		return spotTime;
	}

	public void setSpotTime(String spotTime) {
		this.spotTime = spotTime;
	}

	public int getIsUnusual() {
		return isUnusual;
	}

	public void setIsUnusual(int isUnusual) {
		this.isUnusual = isUnusual;
	}

	public String getUnusualText() {
		return unusualText;
	}

	public void setUnusualText(String unusualText) {
		this.unusualText = unusualText;
	}

	public int getIsUpdate() {
		return isUpdate;
	}

	public void setIsUpdate(int isUpdate) {
		this.isUpdate = isUpdate;
	}

	public String getUpdateText() {
		return updateText;
	}

	public void setUpdateText(String updateText) {
		this.updateText = updateText;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getPrictureUrl() {
		return prictureUrl;
	}

	public void setPrictureUrl(String prictureUrl) {
		this.prictureUrl = prictureUrl;
	}

}
