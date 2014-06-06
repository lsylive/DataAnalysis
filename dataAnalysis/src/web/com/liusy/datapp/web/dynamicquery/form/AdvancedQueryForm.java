package com.liusy.datapp.web.dynamicquery.form;

import com.liusy.dataapplatform.base.form.QueryForm;

public class AdvancedQueryForm extends QueryForm{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String tabId;
	private String condId;
	private String queryName;
	private String tableId;
	private String action1;
	
	public String getAction1() {
		return action1;
	}

	public void setAction1(String action1) {
		this.action1 = action1;
	}

	public String getTabId()
	{
		return tabId;
	}

	public void setTabId(String tabId)
	{
		this.tabId = tabId;
	}

	public String getCondId()
	{
		return condId;
	}

	public void setCondId(String condId)
	{
		this.condId = condId;
	}

	public String getQueryName()
	{
		return queryName;
	}

	public void setQueryName(String queryName)
	{
		this.queryName = queryName;
	}

	public String getTableId()
	{
		return tableId;
	}

	public void setTableId(String tableId)
	{
		this.tableId = tableId;
	}
}
