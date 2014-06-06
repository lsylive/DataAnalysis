package com.liusy.datapp.web.datastandard.form;

import com.liusy.dataapplatform.base.form.QueryForm;

public class StandardCodesetForm extends QueryForm {
	private static final long serialVersionUID = 1;
	private String cityCode;
	
	public String getCityCode() {
		return cityCode;
	}
	public void setCityCode(String cityCode) {
		this.cityCode = cityCode;
	}
	
	private String categoryName;

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
}
