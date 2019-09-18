package com.itheima.exception;

public class SysException extends Exception{
	
	private String message;
	
	public SysException(String message){
		this.message = message;
	}
	
	public void setMessage(String message){
		this.message = message;
	}
	public String getMessage(){
		return message;
	}
}