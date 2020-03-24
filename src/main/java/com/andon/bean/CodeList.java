package com.andon.bean;

import java.io.Serializable;

public class CodeList extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    //主键id
    private int id;
    //父子关系 1父 2子
    private int parentId;
    //类型 1点检类型 2设备故障分类 3设备维修用时分类 4摸具故障类型 5.6....模具损坏部件
    private int codeType;
    //code名称
    private String codeName;
    //预留字段
    private String Info1;
    private String Info2;
    private String Info3;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getParentId() {
        return parentId;
    }

    public void setParentId(int parentId) {
        this.parentId = parentId;
    }

    public int getCodeType() {
        return codeType;
    }

    public void setCodeType(int codeType) {
        this.codeType = codeType;
    }

    public String getCodeName() {
        return codeName;
    }

    public void setCodeName(String codeName) {
        this.codeName = codeName;
    }

    public String getInfo1() {
        return Info1;
    }

    public void setInfo1(String info1) {
        Info1 = info1;
    }

    public String getInfo2() {
        return Info2;
    }

    public void setInfo2(String info2) {
        Info2 = info2;
    }

    public String getInfo3() {
        return Info3;
    }

    public void setInfo3(String info3) {
        Info3 = info3;
    }
}
