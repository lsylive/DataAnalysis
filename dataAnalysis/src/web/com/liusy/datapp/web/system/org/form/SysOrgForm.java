package com.liusy.datapp.web.system.org.form;

import java.util.HashMap;
import java.util.Map;

import com.liusy.dataapplatform.base.form.BaseForm;

public class SysOrgForm extends BaseForm {

	private static final long		serialVersionUID	= 1L;

	private Map<String, String>	record;

	private String						id;

	private String[]					ids;

	public Map<String, String> getRecord() {
		if (record == null) record = new HashMap<String, String>();
		return record;
	}

	public void setRecord(Map<String, String> record) {
		this.record = record;
	}

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

}
