package com.liusy.datapp.web.system.org.form;

import org.apache.struts.action.ActionForm;

public class LoginForm extends ActionForm{
	private String userName;
	private String password;
	private String verfiyCode;
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getVerfiyCode() {
		return verfiyCode;
	}
	public void setVerfiyCode(String verfiyCode) {
		this.verfiyCode = verfiyCode;
	}

}
