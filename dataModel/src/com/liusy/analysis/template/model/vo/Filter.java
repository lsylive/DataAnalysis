
package com.liusy.analysis.template.model.vo;

import java.io.Serializable;

public class Filter
	implements Cloneable, Serializable
{

	private static final long serialVersionUID = 1L;
	private String fieldName;
	private String cnName;
	private String operator;
	private String data;

	public Filter()
	{
	}

	public Filter clone()
	{
		Filter df = new Filter();
		df.setCnName(cnName);
		df.setField(fieldName);
		df.setOperator(operator);
		df.setExpression(data);
		return df;
	}

	public String getField()
	{
		return fieldName;
	}

	public void setField(String fieldName)
	{
		this.fieldName = fieldName;
	}

	public String getOperator()
	{
		return operator;
	}

	public void setOperator(String operator)
	{
		this.operator = operator;
	}

	public String getExpression()
	{
		return data;
	}

	public void setExpression(String data)
	{
		this.data = data;
	}

	public String getCnName()
	{
		return cnName;
	}

	public void setCnName(String cnName)
	{
		this.cnName = cnName;
	}

}
