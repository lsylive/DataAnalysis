package com.liusy.datapp.web.query.form;

import com.liusy.dataapplatform.base.form.QueryForm;

public class SubjectSynthesisCfgForm extends QueryForm{
	private String						id;

	private String[]					ids;
	private String 						subjectId;
	private String 						catagoryId;

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

	public String getSubjectId() {
		return subjectId;
	}

	public void setSubjectId(String subjectId) {
		this.subjectId = subjectId;
	}

	public String getCatagoryId() {
		return catagoryId;
	}

	public void setCatagoryId(String catagoryId) {
		this.catagoryId = catagoryId;
	}

}
