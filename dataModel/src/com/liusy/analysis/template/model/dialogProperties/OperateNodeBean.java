
package com.liusy.analysis.template.model.dialogProperties;

import java.io.Serializable;

public class OperateNodeBean
	implements Cloneable, Serializable
{

	private static final long serialVersionUID = 1L;
	private String name;
	private String operateType;
	private String subOperareType;

	public OperateNodeBean()
	{
		name = "";
		operateType = "";
		subOperareType = "";
	}

	public String getOperateType()
	{
		return operateType;
	}

	public void setOperateType(String operateType)
	{
		this.operateType = operateType;
	}

	public String getSubOperareType()
	{
		return subOperareType;
	}

	public void setSubOperareType(String subOperareType)
	{
		this.subOperareType = subOperareType;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public boolean equals(Object obj)
	{
		if (obj == null)
			return false;
		if (this == obj)
			return true;
		if (obj.getClass() != getClass())
			return false;
		OperateNodeBean other = (OperateNodeBean)obj;
		return getName().equals(other.getName()) && getOperateType().equals(other.getOperateType()) && getSubOperareType().equals(other.getSubOperareType());
	}
}
