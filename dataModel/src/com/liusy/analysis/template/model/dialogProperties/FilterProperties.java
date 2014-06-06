
package com.liusy.analysis.template.model.dialogProperties;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.liusy.analysis.template.model.base.Metadata;

public class FilterProperties
	implements Cloneable, Serializable
{

	private static final long serialVersionUID = 1L;
	private String filterString;
	private List<Metadata> fields = new ArrayList<Metadata>();


	public String getFilterString()
	{
		return filterString;
	}

	public void setFilterString(String filterString)
	{
		this.filterString = filterString;
	}

	public List<Metadata> getFields()
	{
		return fields;
	}

	public void setFields(List<Metadata> fields)
	{
		this.fields = fields;
	}
}
