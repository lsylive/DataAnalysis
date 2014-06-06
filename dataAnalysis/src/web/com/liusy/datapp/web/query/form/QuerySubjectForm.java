package com.liusy.datapp.web.query.form;



import com.liusy.dataapplatform.base.form.QueryForm;

public class QuerySubjectForm extends QueryForm{


	private String						id;

	private String[]					ids;
	private String 						selectedItemId;
	private String[] 						tabId;
	private String    					selectedTabId;
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

	public String getSelectedItemId() {
		return selectedItemId;
	}

	public void setSelectedItemId(String selectedItemId) {
		this.selectedItemId = selectedItemId;
	}

	public String[] getTabId() {
		return tabId;
	}

	public void setTabId(String[] tabId) {
		this.tabId = tabId;
	}

	public String getSelectedTabId() {
		return selectedTabId;
	}

	public void setSelectedTabId(String selectedTabId) {
		this.selectedTabId = selectedTabId;
	}

	public String getCatagoryId() {
		return catagoryId;
	}

	public void setCatagoryId(String catagoryId) {
		this.catagoryId = catagoryId;
	}


}
