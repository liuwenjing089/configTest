package com.andon.bean.charts;

import java.util.List;

public class ChartsData {

 
    private String mainTitle = "";
    

    private String subtitle = "";


    private String yTitle = "";
    
 
    private XDatas xs;
    
    private List<YDatas> ys;

    public String getMainTitle() {
		return mainTitle;
	}

	public void setMainTitle(String mainTitle) {
		this.mainTitle = mainTitle;
	}

	public String getSubtitle() {
		return subtitle;
	}

	public void setSubtitle(String subtitle) {
		this.subtitle = subtitle;
	}

	public String getyTitle() {
		return yTitle;
	}

	public void setyTitle(String yTitle) {
		this.yTitle = yTitle;
	}

	public XDatas getXs() {
		return xs;
	}

	public void setXs(XDatas xs) {
		this.xs = xs;
	}

	public List<YDatas> getYs() {
		return ys;
	}

	public void setYs(List<YDatas> ys) {
		this.ys = ys;
	}
}
