


//   ColumnConfigPoolObj.java

package com.liusy.datapp.util.poolobj;

import java.io.Serializable;

public class ColumnConfigPoolObj
	implements Serializable
{

	private String indId;
	private String columnId;
	private String isSortable;
	private String isSubject;
	private String isShown;
	private String displayWidth;

	public ColumnConfigPoolObj()
	{
	}

	public String getColumnId()
	{
		return columnId;
	}

	public void setColumnId(String columnId)
	{
		this.columnId = columnId;
	}

	public String getIndId()
	{
		return indId;
	}

	public void setIndId(String indId)
	{
		this.indId = indId;
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

	public String getDisplayWidth()
	{
		return displayWidth;
	}

	public void setDisplayWidth(String displayWidth)
	{
		this.displayWidth = displayWidth;
	}
}
