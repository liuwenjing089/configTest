package com.andon.bean;

public class TestMould extends BaseEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


    private int id;
    
    private String deId;
    
    private String predictedTestMouldTime;
    
    private String testMouldStartTime;
    
    private String testMouldEndTime;
    
    private int testMouldResult;
    
    private int completeness;
    
    private String remarks;
    
    private String remarks1;
    
    private String remarks2;

	public int getId() {
		return id;
	}

	public String getPredictedTestMouldTime() {
		return predictedTestMouldTime;
	}

	public void setPredictedTestMouldTime(String predictedTestMouldTime) {
		this.predictedTestMouldTime = predictedTestMouldTime;
	}



	public String getTestMouldStartTime() {
		return testMouldStartTime;
	}

	public void setTestMouldStartTime(String testMouldStartTime) {
		this.testMouldStartTime = testMouldStartTime;
	}

	public String getTestMouldEndTime() {
		return testMouldEndTime;
	}

	public void setTestMouldEndTime(String testMouldEndTime) {
		this.testMouldEndTime = testMouldEndTime;
	}

	public int getTestMouldResult() {
		return testMouldResult;
	}

	public void setTestMouldResult(int testMouldResult) {
		this.testMouldResult = testMouldResult;
	}

	public int getCompleteness() {
		return completeness;
	}

	public void setCompleteness(int completeness) {
		this.completeness = completeness;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getRemarks1() {
		return remarks1;
	}

	public void setRemarks1(String remarks1) {
		this.remarks1 = remarks1;
	}

	public String getRemarks2() {
		return remarks2;
	}

	public void setRemarks2(String remarks2) {
		this.remarks2 = remarks2;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDeId() {
		return deId;
	}

	public void setDeId(String deId) {
		this.deId = deId;
	}


    
}
