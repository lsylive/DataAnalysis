package com.liusy.datapp.web.dynamicquery.form;

import com.liusy.dataapplatform.base.form.QueryForm;

public class ClassifySubjectQueryForm extends QueryForm{
	private String id;
	private String cataId;
	private String[] selTable;
	private String querySql;
	private String tabId;
	private String uid;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getQuerySql() {
		return querySql;
	}
	public void setQuerySql(String querySql) {
		this.querySql = querySql;
	}
	public String[] getSelTable() {
		return selTable;
	}
	public void setSelTable(String[] selTable) {
		this.selTable = selTable;
	}
	public String getTabId() {
		return tabId;
	}
	public void setTabId(String tabId) {
		this.tabId = tabId;
	}
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	public String getCataId() {
		return cataId;
	}
	public void setCataId(String cataId) {
		this.cataId = cataId;
	}
	
}
