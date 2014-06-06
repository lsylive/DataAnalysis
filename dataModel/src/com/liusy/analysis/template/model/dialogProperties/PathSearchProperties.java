
package com.liusy.analysis.template.model.dialogProperties;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.liusy.analysis.template.model.vo.SearchField;

public class PathSearchProperties
	implements Cloneable, Serializable
{

	private static final long serialVersionUID = 1L;
	private String startValue;
	private String endValue;
	private int searchDepth;
	private List<SearchField> fields;


	public PathSearchProperties()
	{
		fields = new ArrayList<SearchField>();
	}

	public List<SearchField> getFields()
	{
		return fields;
	}

	public void setFields(List<SearchField> fields)
	{
		this.fields = fields;
	}

	public int getSearchDepth()
	{
		return searchDepth;
	}

	public void setSearchDepth(int searchDepth)
	{
		this.searchDepth = searchDepth;
	}

	public String getStartValue()
	{
		return startValue;
	}

	public void setStartValue(String startValue)
	{
		this.startValue = startValue;
	}

	public String getEndValue()
	{
		return endValue;
	}

	public void setEndValue(String endValue)
	{
		this.endValue = endValue;
	}
}
