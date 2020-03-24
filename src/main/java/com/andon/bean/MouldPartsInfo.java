package com.andon.bean;

import java.io.Serializable;

public class MouldPartsInfo extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    //部品基础信息表uuid
    private String uuid;
    //损坏部品品番
    private String partsNum;
    //部品分类
    private String partsType;
    //部品分类
    private String partsName;
    
    
	public String getUuid() {
		return uuid;
	}
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public String getPartsNum() {
		return partsNum;
	}
	public void setPartsNum(String partsNum) {
		this.partsNum = partsNum;
	}
	public String getPartsType() {
		return partsType;
	}
	public void setPartsType(String partsType) {
		this.partsType = partsType;
	}
	public String getPartsName() {
		return partsName;
	}
	public void setPartsName(String partsName) {
		this.partsName = partsName;
	}


}
