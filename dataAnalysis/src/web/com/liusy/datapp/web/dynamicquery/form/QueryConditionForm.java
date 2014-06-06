package com.liusy.datapp.web.dynamicquery.form;

import com.liusy.dataapplatform.base.form.QueryForm;

public class QueryConditionForm extends QueryForm{
	private String id;
	private String subjectId;
	private String tableId;
	private String queryName;
	private String condId;
	private String[] selTable;
	private String[] oper;
	private String[] linkoper;
	private String[] prevoper;
	private String[] colid;
	private String[] nextoper;
	private String[] paramvalue1;
	private String[] paramvalue2;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getSubjectId() {
		return subjectId;
	}
	public void setSubjectId(String subjectId) {
		this.subjectId = subjectId;
	}
	public String getTableId() {
		return tableId;
	}
	public void setTableId(String tableId) {
		this.tableId = tableId;
	}
	public String getQueryName() {
		return queryName;
	}
	public void setQueryName(String queryName) {
		this.queryName = queryName;
	}
	public String getCondId() {
		return condId;
	}
	public void setCondId(String condId) {
		this.condId = condId;
	}
	public String[] getSelTable() {
		return selTable;
	}
	public void setSelTable(String[] selTable) {
		this.selTable = selTable;
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
	public void setParamvalue1(String[] paramvalue1) {
		this.paramvalue1 = paramvalue1;
	}
	public String[] getParamvalue2() {
		return paramvalue2;
	}
	public void setParamvalue2(String[] paramvalue2) {
		this.paramvalue2 = paramvalue2;
	}
	public String[] getPrevoper() {
		return prevoper;
	}
	public void setPrevoper(String[] prevoper) {
		this.prevoper = prevoper;
	}
	public String[] getColid() {
		return colid;
	}
	public void setColid(String[] colid) {
		this.colid = colid;
	}


}
