package com.xxfff.core.exception;

public class ServiceException extends BaseException{
	private static final long serialVersionUID = -2138977656175120150L;
	
	public ServiceException(Exception e, Integer errorCode, String msg) {
		super(e, errorCode, msg);
	}
}
