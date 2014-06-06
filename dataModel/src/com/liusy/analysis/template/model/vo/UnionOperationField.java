
package com.liusy.analysis.template.model.vo;

import com.liusy.analysis.template.model.Consts;
import java.io.Serializable;

public class UnionOperationField
	implements Cloneable, Serializable
{

	private static final long serialVersionUID = 1L;
	private String cnName;
	private String name;
	private String dataType;
	private String expression;

	public UnionOperationField()
	{
		cnName = "";
		name = "";
		dataType = Consts.DATATYPE_STRING;
		expression = "";
	}

	public UnionOperationField clone()
	{
		UnionOperationField opf = new UnionOperationField();
		opf.setCnName(cnName);
		opf.setName(name);
		opf.setDataType(dataType);
		opf.setExpression(expression);
		return opf;
	}

	public String getCnName()
	{
		return cnName;
	}

	public void setCnName(String cnName)
	{
		this.cnName = cnName;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public String getDataType()
	{
		return dataType;
	}

	public void setDataType(String dataType)
	{
		this.dataType = dataType;
	}

	public String getExpression()
	{
		return expression;
	}

	public void setExpression(String expression)
	{
		this.expression = expression;
	}


}
