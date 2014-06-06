package com.liusy.datapp.web.compare.form;

import com.liusy.dataapplatform.base.form.QueryForm;

public class CompareInfoForm extends QueryForm{
	private String id;
	private String name;
	private String action;
	private String infoId;
	public String getAction() {
		return action;
	}
	public void setAction(String action) {
		this.action = action;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getInfoId() {
		return infoId;
	}
	public void setInfoId(String infoId) {
		this.infoId = infoId;
	}

}
