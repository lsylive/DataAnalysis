package com.liusy.datapp.web.dynamicquery.form;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.liusy.dataapplatform.base.form.QueryForm;
import com.liusy.datapp.model.datastandard.StandardCode;

public class CommSubjectQueryForm extends QueryForm{
	private String id;
	private String[] selTable;
	private String querySql;
	private String tableId;
	private String uid;
	private String cataId;
	private String action1;
	private String queryName;
	private String idArr;
	private String condId;
	private String isspecial;
	
	
	public String getIsspecial() {
		return isspecial;
	}

	public void setIsspecial(String isspecial) {
		this.isspecial = isspecial;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String[] getSelTable() {
		return selTable;
	}

	public void setSelTable(String[] selTable) {
		this.selTable = selTable;
	}

	public String getQuerySql() {
		return querySql;
	}

	public void setQuerySql(String querySql) {
		this.querySql = querySql;
	}

	public String getTableId() {
		return tableId;
	}

	public void setTableId(String tableId) {
		this.tableId = tableId;
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

	public String getAction1() {
		return action1;
	}

	public void setAction1(String action1) {
		this.action1 = action1;
	}

	public String getQueryName() {
		return queryName;
	}

	public void setQueryName(String queryName) {
		this.queryName = queryName;
	}

	public String getIdArr() {
		return idArr;
	}

	public void setIdArr(String idArr) {
		this.idArr = idArr;
	}

	public String getCondId() {
		return condId;
	}

	public void setCondId(String condId) {
		this.condId = condId;
	}

	
	

}
