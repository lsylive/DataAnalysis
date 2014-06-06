package com.liusy.datapp.web.analysis.form;

import java.util.HashMap;
import java.util.Map;

import com.liusy.dataapplatform.base.form.QueryForm;

public class AnalysisQueryForm extends QueryForm{
	private String templateId;
	private String tableName;
	private Map parameters;
	
	public AnalysisQueryForm(){
		parameters=new HashMap();
	}
	
	public String getTableName() {
		return tableName;
	}
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
	public String getTemplateId() {
		return templateId;
	}
	public void setTemplateId(String templateId) {
		this.templateId = templateId;
	}
	public Map getParameters() {
		return parameters;
	}
	public void setParameters(Map parameters) {
		this.parameters = parameters;
	}
	

}