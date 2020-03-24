package com.andon.bean;

import java.util.List;

public class FaultCombox {
    private int Id;
    private String name;
    private List<String> subPartsList;
    private List<String> subPartsIds;

    public List<String> getSubPartsIds() {
        return subPartsIds;
    }

    public void setSubPartsIds(List<String> subPartsIds) {
        this.subPartsIds = subPartsIds;
    }

    public int getId() {
        return Id;
    }
    public void setId(int id) {
        Id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public List<String> getSubPartsList() {
        return subPartsList;
    }

    public void setSubPartsList(List<String> subPartsList) {
        this.subPartsList = subPartsList;
    }
}
