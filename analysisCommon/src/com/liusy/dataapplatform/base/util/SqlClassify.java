


//   SqlClassify.java

package com.liusy.dataapplatform.base.util;

import java.util.List;

public class SqlClassify
{

	private List queryList;
	private String unionType;

	public SqlClassify()
	{
	}

	public List getQueryList()
	{
		return queryList;
	}

	public void setQueryList(List queryList)
	{
		this.queryList = queryList;
	}

	public String getUnionType()
	{
		return unionType;
	}

	public void setUnionType(String unionType)
	{
		this.unionType = unionType;
	}
}
