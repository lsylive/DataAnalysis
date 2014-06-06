package com.liusy.datapp.service.util;

import java.io.Serializable;

public class SynthesisUserColumnCfgParam implements Serializable{
	private String id;
	private String columnId;
	private String name;
	private String cname;
	private String sercurityLevel;
	public String getColumnId() {
		return columnId;
	}
	public void setColumnId(String columnId) {
		this.columnId = columnId;
	}
	public String getSercurityLevel() {
		return sercurityLevel;
	}
	public void setSercurityLevel(String sercurityLevel) {
		this.sercurityLevel = sercurityLevel;
	}
	public String getCname() {
		return cname;
	}
	public void setCname(String cname) {
		this.cname = cname;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	

}
