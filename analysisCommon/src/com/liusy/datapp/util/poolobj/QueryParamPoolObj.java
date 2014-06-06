


//   QueryParamPoolObj.java

package com.liusy.datapp.util.poolobj;

import java.io.Serializable;

public class QueryParamPoolObj
	implements Serializable
{

	private String columnId;
	private String stQuery;
	private String filterOperator;
	private String batchQuery;
	private String homonymQuery;
	private String columnName;
	private String displayName;
	private String isFilter;

	public QueryParamPoolObj()
	{
	}

	public String getBatchQuery()
	{
		return batchQuery;
	}

	public void setBatchQuery(String batchQuery)
	{
		this.batchQuery = batchQuery;
	}

	public String getColumnId()
	{
		return columnId;
	}

	public void setColumnId(String columnId)
	{
		this.columnId = columnId;
	}

	public String getColumnName()
	{
		return columnName;
	}

	public void setColumnName(String columnName)
	{
		this.columnName = columnName;
	}

	public String getDisplayName()
	{
		return displayName;
	}

	public void setDisplayName(String displayName)
	{
		this.displayName = displayName;
	}

	public String getFilterOperator()
	{
		return filterOperator;
	}

	public void setFilterOperator(String filterOperator)
	{
		this.filterOperator = filterOperator;
	}

	public String getHomonymQuery()
	{
		return homonymQuery;
	}

	public void setHomonymQuery(String homonymQuery)
	{
		this.homonymQuery = homonymQuery;
	}

	public String getStQuery()
	{
		return stQuery;
	}

	public void setStQuery(String stQuery)
	{
		this.stQuery = stQuery;
	}

	public String getIsFilter()
	{
		return isFilter;
	}

	public void setIsFilter(String isFilter)
	{
		this.isFilter = isFilter;
	}
}
