


//   PagerObject.java

package com.liusy.dataapplatform.base.util;

import java.io.Serializable;
import java.util.List;

public class PagerObject
	implements Serializable
{

	public static final int PAGESIZE = 10;
	private int pageSize;
	private List items;
	private int totalCount;
	private int startIndex;
	private int pages;

	public PagerObject()
	{
		pageSize = 10;
		startIndex = 0;
		pages = 0;
	}

	public static int getPAGESIZE()
	{
		return 10;
	}

	public List getItems()
	{
		return items;
	}

	public void setItems(List items)
	{
		this.items = items;
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
