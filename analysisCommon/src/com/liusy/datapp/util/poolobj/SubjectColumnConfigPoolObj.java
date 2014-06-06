


//   SubjectColumnConfigPoolObj.java

package com.liusy.datapp.util.poolobj;

import java.io.Serializable;

public class SubjectColumnConfigPoolObj
	implements Serializable
{

	private String stQuery;
	private String filterOperator;
	private String batchQuery;
	private String indId;
	private String codeSetNo;
	private String name;
	private String cnName;
	private String homonymQuery;
	private String filterValue;
	private String isFilter;
	private String subjectId;
	private String fuzzyQuery;
	private String dataType;

	public SubjectColumnConfigPoolObj()
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

	public String getFilterOperator()
	{
		return filterOperator;
	}

	public void setFilterOperator(String filterOperator)
	{
		this.filterOperator = filterOperator;
	}

	public String getFilterValue()
	{
		return filterValue;
	}

	public void setFilterValue(String filterValue)
	{
		this.filterValue = filterValue;
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

	public String getIndId()
	{
		return indId;
	}

	public void setIndId(String indId)
	{
		this.indId = indId;
	}

	public String getIsFilter()
	{
		return isFilter;
	}

	public void setIsFilter(String isFilter)
	{
		this.isFilter = isFilter;
	}

	public String getStQuery()
	{
		return stQuery;
	}

	public void setStQuery(String stQuery)
	{
		this.stQuery = stQuery;
	}

	public String getSubjectId()
	{
		return subjectId;
	}

	public void setSubjectId(String subjectId)
	{
		this.subjectId = subjectId;
	}

	public String getCnName()
	{
		return cnName;
	}

	public void setCnName(String cnName)
	{
		this.cnName = cnName;
	}

	public String getCodeSetNo()
	{
		return codeSetNo;
	}

	public void setCodeSetNo(String codeSetNo)
	{
		this.codeSetNo = codeSetNo;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public String getDataType()
	{
		return dataType;
	}

	public void setDataType(String dataType)
	{
		this.dataType = dataType;
	}
}
