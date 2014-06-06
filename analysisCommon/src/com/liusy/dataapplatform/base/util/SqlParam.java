


//   SqlParam.java

package com.liusy.dataapplatform.base.util;

import java.util.List;

public class SqlParam
{

	private String groupByString;
	private String Fields;
	private String fromString;
	private String orderString;
	private String havingString;
	private String whereString;
	private List paramList;
	private List classifyList;
	private String selectParamId;
	private String sortCol;
	private String sortType;
	public static final int PAGESIZE = 10;
	private int pageSize;
	protected List recordSet;
	private int totalCount;
	private int startIndex;
	private int pages;

	public SqlParam()
	{
		pageSize = 10;
		startIndex = 0;
		pages = 0;
	}

	public String getFromString()
	{
		return fromString;
	}

	public void setFromString(String fromString)
	{
		this.fromString = fromString;
	}

	public String getGroupByString()
	{
		return groupByString;
	}

	public void setGroupByString(String groupByString)
	{
		this.groupByString = groupByString;
	}

	public String getHavingString()
	{
		return havingString;
	}

	public void setHavingString(String havingString)
	{
		this.havingString = havingString;
	}

	public String getOrderString()
	{
		return orderString;
	}

	public void setOrderString(String orderString)
	{
		this.orderString = orderString;
	}

	public String getFields()
	{
		return Fields;
	}

	public void setFields(String fields)
	{
		Fields = fields;
	}

	public List getClassifyList()
	{
		return classifyList;
	}

	public void setClassifyList(List classifyList)
	{
		this.classifyList = classifyList;
	}

	public List getParamList()
	{
		return paramList;
	}

	public void setParamList(List paramList)
	{
		this.paramList = paramList;
	}

	public void setResultField(String as[])
	{
	}

	public String getWhereString()
	{
		return whereString;
	}

	public void setWhereString(String whereString)
	{
		this.whereString = whereString;
	}

	public String getSelectParamId()
	{
		return selectParamId;
	}

	public void setSelectParamId(String selectParamId)
	{
		this.selectParamId = selectParamId;
	}

	public String getSortCol()
	{
		return sortCol;
	}

	public void setSortCol(String sortCol)
	{
		this.sortCol = sortCol;
	}

	public String getSortType()
	{
		return sortType;
	}

	public void setSortType(String sortType)
	{
		this.sortType = sortType;
	}

	public int getPages()
	{
		return pages;
	}

	public void setPages(int pages)
	{
		this.pages = pages;
	}

	public int getPageSize()
	{
		return pageSize;
	}

	public void setPageSize(int pageSize)
	{
		this.pageSize = pageSize;
	}

	public List getRecordSet()
	{
		return recordSet;
	}

	public void setRecordSet(List recordSet)
	{
		this.recordSet = recordSet;
	}

	public int getStartIndex()
	{
		return startIndex;
	}

	public void setStartIndex(int startIndex)
	{
		this.startIndex = startIndex;
	}

	public int getTotalCount()
	{
		return totalCount;
	}

	public void setTotalCount(int totalCount)
	{
		this.totalCount = totalCount;
	}
}
