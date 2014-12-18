package com.xxfff.core.model;

/**
 * 公共数据状态
 * @author jeek
 */
public class CommonStatus{
	/** 状态 大于0代表成功 小于0代表失败*/
	private Integer status;
	/** 提示信息 */
	private String msg;
	private Object obj;
	
	public CommonStatus(Integer status) {
		super();
		this.status = status;
	}
	
	public CommonStatus(Integer status, String msg) {
		super();
		this.status = status;
		this.msg = msg;
	}
	public CommonStatus(Integer status, String msg, Object obj) {
		super();
		this.status = status;
		this.msg = msg;
		this.obj = obj;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public Object getObj() {
		return obj;
	}
	public void setObj(Object obj) {
		this.obj = obj;
	}
}
