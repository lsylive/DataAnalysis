
package com.liusy.analysis.template.model.vo;

import com.liusy.analysis.template.model.Consts;
import com.liusy.analysis.template.model.base.Metadata;
import java.io.Serializable;

public class InputField extends Metadata
	implements Cloneable, Serializable
{

	private static final long serialVersionUID = 1L;
	private String output;
	private String express;
	private String operator;

	public InputField()
	{
		output = Consts.YES;
		express = "";
		operator = "";
	}

	public InputField(Metadata mt)
	{
		output = Consts.YES;
		express = "";
		operator = "";
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

	public InputField clone()
	{
		return (InputField)super.clone();
	}

	public String getOutput()
	{
		return output;
	}

	public void setOutput(String output)
	{
		this.output = output;
	}

	public String getExpress()
	{
		return express;
	}

	public void setExpress(String express)
	{
		this.express = express;
	}

	public String getOperator()
	{
		return operator;
	}

	public void setOperator(String operator)
	{
		this.operator = operator;
	}


}
