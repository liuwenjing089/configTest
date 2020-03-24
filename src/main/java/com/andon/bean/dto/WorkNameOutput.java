package com.andon.bean.dto;

import com.andon.bean.BaseEntity;

import java.io.Serializable;

public class WorkNameOutput extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    private int id;
    private String beltlineName;
    private String equipName;
    private String appearance;

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

    public String getEquipName() {
        return equipName;
    }

    public void setEquipName(String equipName) {
        this.equipName = equipName;
    }

    public String getAppearance() {
        return appearance;
    }

    public void setAppearance(String appearance) {
        this.appearance = appearance;
    }
}
