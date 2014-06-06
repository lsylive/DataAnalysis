
package com.liusy.analysis.template.model.vo;

import com.liusy.analysis.template.model.Consts;
import com.liusy.analysis.template.model.base.Metadata;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class GenerateDataField extends Metadata
	implements Cloneable, Serializable
{

	private static final long serialVersionUID = 1L;
	private boolean generate;
	private String visible;
	private String expression;
	private String codeSet;
	private String expressionType;
	private Map properties;

	public GenerateDataField()
	{
		generate = false;
		visible = Consts.YES;
		expression = "";
		codeSet = "";
		expressionType = Consts.GENCOLUMN_EXPRESSION;
		properties = new HashMap();
	}

	public GenerateDataField(Metadata mt)
	{
		generate = false;
		visible = Consts.YES;
		expression = "";
		codeSet = "";
		expressionType = Consts.GENCOLUMN_EXPRESSION;
		properties = new HashMap();
		setId(mt.getId());
		setCnName(mt.getCnName());
		setDataType(mt.getDataType());
		setDescription(mt.getDescription());
		setName(mt.getName());
		setLength(mt.getLength());
		setPrecision(mt.getPrecision());
	}

	public Metadata getMeta()
	{
		Metadata mt = new Metadata();
		mt.setId(getId());
		mt.setCnName(getCnName());
		mt.setDataType(getDataType());
		mt.setDescription(getDescription());
		mt.setName(getName());
		mt.setLength(getLength());
		mt.setPrecision(getPrecision());
		return mt;
	}

	public boolean isGenerate()
	{
		return generate;
	}

	public void setGenerate(boolean generate)
	{
		this.generate = generate;
	}

	public String getExpression()
	{
		return expression;
	}

	public void setExpression(String expression)
	{
		this.expression = expression;
	}

	public String getVisible()
	{
		return visible;
	}

	public void setVisible(String visible)
	{
		this.visible = visible;
	}

	public String getExpressionType()
	{
		return expressionType;
	}

	public void setExpressionType(String expressionType)
	{
		this.expressionType = expressionType;
	}

	public Map getProperties()
	{
		return properties;
	}

	public void setProperties(Map properties)
	{
		this.properties = properties;
	}

	public GenerateDataField clone()
	{
		return (GenerateDataField)super.clone();
	}

	public boolean equals(Object obj)
	{
		if (obj == null)
			return false;
		if (this == obj)
			return true;
		if (obj.getClass() != getClass())
			return false;
		GenerateDataField other = (GenerateDataField)obj;
		return name.equals(other.name) && cnName.equals(other.cnName) && dataType.equals(other.dataType) && length != null && other.length != null && length.equals(other.length) && precision.equals(other.precision) && description.equals(other.description) && (!generate) != other.generate && (visible.equals(other.visible) && expression.equals(other.expression) && expressionType.equals(other.expressionType) && properties.equals(other.properties));
	}

	public String getCodeSet()
	{
		return codeSet;
	}

	public void setCodeSet(String codeSet)
	{
		this.codeSet = codeSet;
	}


}
