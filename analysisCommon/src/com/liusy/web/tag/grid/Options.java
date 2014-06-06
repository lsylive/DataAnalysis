


//   Options.java

package com.liusy.web.tag.grid;

import com.liusy.web.tag.base.Base;

public class Options extends Base
{

	private String collection;
	private String label;
	private String value;

	public String getCollection()
	{
		return collection;
	}

	public void setCollection(String x)
	{
		collection = x;
	}

	public String getLabel()
	{
		return label;
	}

	public void setLabel(String x)
	{
		label = x;
	}

	public String getValue()
	{
		return value;
	}

	public void setValue(String x)
	{
		value = x;
	}

	public Options()
	{
		collection = null;
		label = null;
		value = null;
	}
}
