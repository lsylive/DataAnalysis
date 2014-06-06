package com.liusy.datapp.web.datastandard.form;

import com.liusy.dataapplatform.base.form.QueryForm;

public class StandardCategoryForm extends QueryForm {
	private static final long serialVersionUID = 1L;
	
	private String parentName;
	private String cityName;

	public void setParentName(String parentName){
		this.parentName = parentName;
	}

	public String getParentName(){
		return this.parentName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public String getCityName() {
		return this.cityName;
	}
}
