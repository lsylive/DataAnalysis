package com.liusy.datapp.web.dynamicquery.form;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.struts.upload.FormFile;

import com.liusy.dataapplatform.base.form.BaseForm;
import com.liusy.dataapplatform.base.form.QueryForm;

public class SubjectBatchQueryForm extends QueryForm{
	private String id;
	private String[] selTable;
	private String querySql;
	private String tableId;
	private String uid;
	private String pos;
	private String action1;
	private FormFile filename;
	private String queryName;
	private String condId;
	private List<Map<String, String>> param;
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
	public List<Map<String, String>> getParam() {
		if(param==null)
			param=new ArrayList<Map<String,String>>();
		return param;
	}
	public void setParam(List<Map<String, String>> param) {
		this.param = param;
	}
	public String getPos() {
		return pos;
	}
	public void setPos(String pos) {
		this.pos = pos;
	}
	public String getAction1() {
		return action1;
	}
	public void setAction1(String action1) {
		this.action1 = action1;
	}
	public FormFile getFilename() {
		return filename;
	}
	public void setFilename(FormFile filename) {
		this.filename = filename;
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

}
