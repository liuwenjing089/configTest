package com.andon.bean.dto;

import com.andon.bean.BaseEntity;
import com.andon.validateInterface.First;

import java.io.Serializable;

import org.hibernate.validator.constraints.NotEmpty;

public class RepairInput extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    
    private int detailId;
    private String appearance;
    private String reportRepairTime;
	@NotEmpty(message = "{RepairInput.stamp.empty}", groups = {First.class})
	private String stamp; //防重复提交标记
	
    public int getDetailId() {
        return detailId;
    }

    public void setDetailId(int detailId) {
        this.detailId = detailId;
    }

    public String getAppearance() {
        return appearance;
    }

    public void setAppearance(String appearance) {
        this.appearance = appearance;
    }

    public String getReportRepairTime() {
        return reportRepairTime;
    }

    public void setReportRepairTime(String reportRepairTime) {
        this.reportRepairTime = reportRepairTime;
    }

	public String getStamp() {
		return stamp;
	}

	public void setStamp(String stamp) {
		this.stamp = stamp;
	}
    
    
}
