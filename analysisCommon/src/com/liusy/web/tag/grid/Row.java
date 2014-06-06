


//   Row.java

package com.liusy.web.tag.grid;

import java.util.ArrayList;

public class Row
{

	private ArrayList cells;
	private String key;

	public Row()
	{
		cells = new ArrayList();
		key = "";
	}

	public ArrayList getCells()
	{
		return cells;
	}

	public void setCells(ArrayList newCells)
	{
		cells = newCells;
	}

	public String getKey()
	{
		return key;
	}

	public void setKey(String newkey)
	{
		key = newkey;
	}
}
