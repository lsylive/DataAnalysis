


//   ColumnCfgPageVO.java

package com.liusy.datapp.util;


public class ColumnCfgPageVO
{

	private String columnId;
	private String name;
	private String cname;
	private String isFilter;
	private String filterOperator;
	private String isSubject;
	private String isSortable;
	private String isShown;
	private String seqNo;
	private String fuzzyQuery;
	private String batchQuery;
	private String homonymQuery;
	private String stQuery;

	public ColumnCfgPageVO()
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

	public String getFilterOperator()
	{
		return filterOperator;
	}

	public void setFilterOperator(String filterOperator)
	{
		this.filterOperator = filterOperator;
	}

	public String getFuzzyQuery()
	{
		return fuzzyQuery;
	}

	public void setFuzzyQuery(String fuzzyQuery)
	{
		this.fuzzyQuery = fuzzyQuery;
	}

	public String getHomonymQuery()
	{
		return homonymQuery;
	}

	public void setHomonymQuery(String homonymQuery)
	{
		this.homonymQuery = homonymQuery;
	}

	public String getIsFilter()
	{
		return isFilter;
	}

	public void setIsFilter(String isFilter)
	{
		this.isFilter = isFilter;
	}

	public String getIsShown()
	{
		return isShown;
	}

	public void setIsShown(String isShown)
	{
		this.isShown = isShown;
	}

	public String getIsSortable()
	{
		return isSortable;
	}

	public void setIsSortable(String isSortable)
	{
		this.isSortable = isSortable;
	}

	public String getIsSubject()
	{
		return isSubject;
	}

	public void setIsSubject(String isSubject)
	{
		this.isSubject = isSubject;
	}

	public String getSeqNo()
	{
		return seqNo;
	}

	public void setSeqNo(String seqNo)
	{
		this.seqNo = seqNo;
	}

	public String getStQuery()
	{
		return stQuery;
	}

	public void setStQuery(String stQuery)
	{
		this.stQuery = stQuery;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public String getCname()
	{
		return cname;
	}

	public void setCname(String cname)
	{
		this.cname = cname;
	}
}
