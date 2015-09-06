package com.mcerp.model;

public class Push {

	String msg;
	public Push() {
		super();
		
	}
	public Push(String messageType) {
		// TODO Auto-generated constructor stub
		this.msg=messageType;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

}
