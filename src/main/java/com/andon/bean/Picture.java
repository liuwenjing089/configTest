package com.andon.bean;

public class Picture extends BaseEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
    private String uuid;   //主键
    
    private String url;    //图片路径
    
    private String pictureName;   //图片名称
    
    private String remarks;  //描述，备注
    
    private String undefiend1; //  未确定
    
    private String undefiend2; //  未确定
    
    private String undefiend3; //  未确定

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getPictureName() {
		return pictureName;
	}

	public void setPictureName(String pictureName) {
		this.pictureName = pictureName;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getUndefiend1() {
		return undefiend1;
	}

	public void setUndefiend1(String undefiend1) {
		this.undefiend1 = undefiend1;
	}

	public String getUndefiend2() {
		return undefiend2;
	}

	public void setUndefiend2(String undefiend2) {
		this.undefiend2 = undefiend2;
	}

	public String getUndefiend3() {
		return undefiend3;
	}

	public void setUndefiend3(String undefiend3) {
		this.undefiend3 = undefiend3;
	}
    
    
    
}
