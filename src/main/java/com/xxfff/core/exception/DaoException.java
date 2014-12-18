package com.xxfff.core.exception;

public class DaoException extends BaseException{
	private static final long serialVersionUID = -6682718428858500746L;
	
	public DaoException(Exception e, Integer errorCode, String msg) {
		super(e, errorCode, msg);
	}
}
