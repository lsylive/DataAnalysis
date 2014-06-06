package com.liusy.datapp.web.dynamicquery.form;

import com.liusy.dataapplatform.base.form.QueryForm;

public class UserDefinedQueryForm extends QueryForm{
	private String id;
	private String tableId;
	private String[] oper;
	private String[] linkoper;
	private String[] prevoper;
	private String[] colid;
	private String[] nextoper;
	private String[] paramvalue1;
	private String[] paramvalue2;
	private String action1;
	private String queryName;
	private String condId;

	public String getTableId() {
		return tableId;
	}

	public void setTableId(String tableId) {
		this.tableId = tableId;
	}

	public String[] getColid() {
		return colid;
	}

	public void setColid(String[] colid) {
		this.colid = colid;
	}

	public String[] getLinkoper() {
		return linkoper;
	}

	public void setLinkoper(String[] linkoper) {
		this.linkoper = linkoper;
	}

	public String[] getNextoper() {
		return nextoper;
	}

	public void setNextoper(String[] nextoper) {
		this.nextoper = nextoper;
	}

	public String[] getOper() {
		return oper;
	}

	public void setOper(String[] oper) {
		this.oper = oper;
	}

	public String[] getParamvalue1() {
		return paramvalue1;
	}

	public void setParamvalue1(String[] paramvalue) {
		this.paramvalue1 = paramvalue;
	}

	public String[] getPrevoper() {
		return prevoper;
	}

	public void setPrevoper(String[] prevoper) {
		this.prevoper = prevoper;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String[] getParamvalue2() {
		return paramvalue2;
	}

	public void setParamvalue2(String[] paramvalue2) {
		this.paramvalue2 = paramvalue2;
	}

	public String getAction1() {
		return action1;
	}

	public void setAction1(String action1) {
		this.action1 = action1;
	}

	public String getCondId() {
		return condId;
	}

	public void setCondId(String condId) {
		this.condId = condId;
	}

	public String getQueryName() {
		return queryName;
	}

	public void setQueryName(String queryName) {
		this.queryName = queryName;
	}
	

}
