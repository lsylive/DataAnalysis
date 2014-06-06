package com.liusy.datapp.web.dynamicquery.form;

import com.liusy.dataapplatform.base.form.QueryForm;

public class QueryDetailForm extends QueryForm{
	private String id;
	private String tabId;
	private String tabArr;
	private String idArr;
	private String action1;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getIdArr() {
		return idArr;
	}
	public void setIdArr(String idArr) {
		this.idArr = idArr;
	}
	public String getTabArr() {
		return tabArr;
	}
	public void setTabArr(String tabArr) {
		this.tabArr = tabArr;
	}
	public String getTabId() {
		return tabId;
	}
	public void setTabId(String tabId) {
		this.tabId = tabId;
	}
	public String getAction1() {
		return action1;
	}
	public void setAction1(String action1) {
		this.action1 = action1;
	}

}
