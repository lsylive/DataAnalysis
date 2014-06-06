
package com.liusy.analysis.template.model.dialogProperties;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.liusy.analysis.template.model.base.Metadata;

public class JudgeNodeProperties
	implements Cloneable, Serializable
{

	private static final long serialVersionUID = 1L;
	private String expression;
	private List<Metadata>  fields;

	public JudgeNodeProperties()
	{
		expression = "";
		fields = new ArrayList<Metadata> ();
	}

	public List<Metadata>  getFields()
	{
		return fields;
	}

	public void setFields(List<Metadata>  fields)
	{
		this.fields = fields;
	}

	public String getExpression()
	{
		return expression;
	}

	public void setExpression(String expression)
	{
		this.expression = expression;
	}

	public boolean equals(Object obj)
	{
		if (obj == null)
			return false;
		if (this == obj)
			return true;
		if (obj.getClass() != getClass())
			return false;
		JudgeNodeProperties other = (JudgeNodeProperties)obj;
		return getExpression().equals(other.getExpression()) && getFields().equals(other.getFields());
	}

	public Object clone()
		throws CloneNotSupportedException
	{
		return super.clone();
	}
}
