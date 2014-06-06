package com.liusy.datapp.web.datastandard.form;

import com.liusy.dataapplatform.base.form.QueryForm;

public class StandardDataMetaForm extends QueryForm {
	private static final long serialVersionUID = 1L;
	
	private String categoryName;

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
}
