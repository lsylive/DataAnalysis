


//   ExcelSheetColProp.java

package com.liusy.dataapplatform.base.util;

import java.util.List;

public class ExcelSheetColProp
{

	private String headerName[];
	private String columnName[];
	private List columnList;
	private String columnType[];
	private String sheetName;
	private int startRow;
	private int startCol;
	private Integer tableId;

	public ExcelSheetColProp()
	{
		startRow = 2;
		startCol = 1;
	}

	public List getColumnList()
	{
		return columnList;
	}

	public void setColumnList(List columnList)
	{
		this.columnList = columnList;
	}

	public String[] getColumnName()
	{
		return columnName;
	}

	public void setColumnName(String columnName[])
	{
		this.columnName = columnName;
	}

	public String[] getColumnType()
	{
		return columnType;
	}

	public void setColumnType(String columnType[])
	{
		this.columnType = columnType;
	}

	public String[] getHeaderName()
	{
		return headerName;
	}

	public void setHeaderName(String headerName[])
	{
		this.headerName = headerName;
	}

	public String getSheetName()
	{
		return sheetName;
	}

	public void setSheetName(String sheetName)
	{
		this.sheetName = sheetName;
	}

	public Integer getTableId()
	{
		return tableId;
	}

	public void setTableId(Integer tableId)
	{
		this.tableId = tableId;
	}

	public int getStartCol()
	{
		return startCol;
	}

	public void setStartCol(int startCol)
	{
		this.startCol = startCol;
	}

	public int getStartRow()
	{
		return startRow;
	}

	public void setStartRow(int startRow)
	{
		this.startRow = startRow;
	}
}
