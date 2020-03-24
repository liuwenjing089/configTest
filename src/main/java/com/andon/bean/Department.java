package com.andon.bean;

public class Department {

	private String uuid;
	private String department_name;
	private String company;
	private String parent_department;

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public String getDepartment_name() {
		return department_name;
	}

	public void setDepartment_name(String department_name) {
		this.department_name = department_name;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getParent_department() {
		return parent_department;
	}

	public void setParent_department(String parent_department) {
		this.parent_department = parent_department;
	}
}
