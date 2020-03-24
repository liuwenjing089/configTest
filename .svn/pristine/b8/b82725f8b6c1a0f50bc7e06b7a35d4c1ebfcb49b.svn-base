package com.andon.bean;

import java.util.Date;

import org.codehaus.jackson.map.annotate.JsonSerialize;

/**
 * 通用返回结构。（手机端）
 * @author Administrator
 *
 */
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class CommonResult {
	private Integer ret;//0失败1成功
	private Object data;//函数结果
	private Long queryTime;//查询相应时间 
	public Integer getRet() {
		return ret;
	}
	public void setRet(Integer ret) {
		this.ret = ret;
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
	public Long getQueryTime() {
		return queryTime;
	}
	public void setQueryTime(Long queryTime) {
		this.queryTime = queryTime;
	}
	public CommonResult(){
		Date now = new Date();
		queryTime=now.getTime();
	}
}
