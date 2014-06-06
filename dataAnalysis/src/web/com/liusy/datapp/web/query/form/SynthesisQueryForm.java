package com.liusy.datapp.web.query.form;


import java.util.Map;

import com.liusy.dataapplatform.base.form.BaseForm;

public class SynthesisQueryForm extends BaseForm{
	private Map<String, String>	record;

	private String						id;

	private String[]					ids;

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

	public Map<String, String> getRecord() {
		return record;
	}

	public void setRecord(Map<String, String> record) {
		this.record = record;
	}
	
	

}
