package com.andon.bean;

import java.io.Serializable;

public class MouldFaultParts extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    //模具损坏部品id
    private int id;
    //关联mould_repair id
    private String reId;
    
    //部品基础信息表uuid
    private String mouldPartsInfoUuid;
    //部品品番
    private String faultPartsNum;
    //部品分类
    private String partsType;
    //部品名称
    private String partsName;
    //维修内容
    private String content;
    //维修内容中文显示
    private String contentName;
    
    //数量
    private String quantity;  
    
    private String partsNum;
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getReId() {
        return reId;
    }

    public void setReId(String reId) {
        this.reId = reId;
    }

    public String getFaultPartsNum() {
        return faultPartsNum;
    }

    public void setFaultPartsNum(String faultPartsNum) {
        this.faultPartsNum = faultPartsNum;
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

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getQuantity() {
		return quantity;
	}

	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}

	public String getMouldPartsInfoUuid() {
		return mouldPartsInfoUuid;
	}

	public void setMouldPartsInfoUuid(String mouldPartsInfoUuid) {
		this.mouldPartsInfoUuid = mouldPartsInfoUuid;
	}

	public String getPartsNum() {
		return partsNum;
	}

	public void setPartsNum(String partsNum) {
		this.partsNum = partsNum;
	}

	public String getContentName() {
		return contentName;
	}

	public void setContentName(String contentName) {
		this.contentName = contentName;
	}
    
    
}
