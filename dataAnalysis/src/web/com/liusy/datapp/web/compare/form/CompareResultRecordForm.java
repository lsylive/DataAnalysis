package com.liusy.datapp.web.compare.form;

import com.liusy.dataapplatform.base.form.QueryForm;

public class CompareResultRecordForm extends QueryForm{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String tabId;
	private String condId;
	private String queryName;
	private String tableId;
	
	private Integer runId;
	private String tableEnName;
	private String tableCnName;
	private String viewType;
	private String mainPkId;
	
	private String isPersonalTable;
	
	
	public String getMainPkId() {
		return mainPkId;
	}
	public void setMainPkId(String mainPkId) {
		this.mainPkId = mainPkId;
	}
	public String getTableEnName() {
		return tableEnName;
	}
	public void setTableEnName(String tableEnName) {
		this.tableEnName = tableEnName;
	}
	public Integer getRunId() {
		return runId;
	}
	public void setRunId(Integer runId) {
		this.runId = runId;
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
	public String getViewType() {
		return viewType;
	}
	public void setViewType(String viewType) {
		this.viewType = viewType;
	}
	public String getIsPersonalTable() {
		return isPersonalTable;
	}
	public void setIsPersonalTable(String isPersonalTable) {
		this.isPersonalTable = isPersonalTable;
	}
	public String getTableCnName() {
		return tableCnName;
	}
	public void setTableCnName(String tableCnName) {
		this.tableCnName = tableCnName;
	}
}
