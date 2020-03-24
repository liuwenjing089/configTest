package com.andon.bean;

import java.io.Serializable;

public class Beltline extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    //生产线id
    private int id;
    
    //工厂
    private int factory;
    
    //生产线名称
    private String beltlineName;
    //生产线描述
    private String beltlineDescription;
    //生产线类型 0 生产线  1 成型机
    private int lineType;
    
    private String factoryName;

    public int getLineType() {
        return lineType;
    }

    public void setLineType(int lineType) {
        this.lineType = lineType;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBeltlineName() {
        return beltlineName;
    }

    public void setBeltlineName(String beltlineName) {
        this.beltlineName = beltlineName;
    }

    public String getBeltlineDescription() {
        return beltlineDescription;
    }

    public void setBeltlineDescription(String beltlineDescription) {
        this.beltlineDescription = beltlineDescription;
    }

	public int getFactory() {
		return factory;
	}

	public void setFactory(int factory) {
		this.factory = factory;
	}

	public String getFactoryName() {
		return factoryName;
	}

	public void setFactoryName(String factoryName) {
		this.factoryName = factoryName;
	}
    
    
}
