package com.liusy.datapp.web.query.form;

import com.liusy.dataapplatform.base.form.QueryForm;

public class SubjectClassfiyForm extends QueryForm{
	

	private String						id;

	private String[]					ids;
	private String 						catagoryId;
	//private String 						catagoryName;


	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String[] getIds() {
		return ids;
	}

	public void setIds(String[] ids) {
		this.ids = ids;
	}

	

	public String getCatagoryId() {
		return catagoryId;
	}

	public void setCatagoryId(String catagoryId) {
		this.catagoryId = catagoryId;
	}

}
