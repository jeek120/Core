package com.xxfff.core.exception;

public class BaseException extends Exception{
	private static final long serialVersionUID = -7259491947932511561L;
    protected Exception e;
    protected Integer errno;
    protected String msg;
    
	public BaseException(Exception e, Integer errno, String msg) {
		super();
		this.e = e;
		this.errno = errno;
		this.msg = msg;
	}

	public Exception getE() {
		return e;
	}

	public void setE(Exception e) {
		this.e = e;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Integer getErrno() {
		return errno;
	}

	public void setErrno(Integer errno) {
		this.errno = errno;
	}
}
