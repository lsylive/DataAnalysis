
package com.liusy.analysis.template.model.vo;

import com.liusy.analysis.template.model.Consts;
import com.liusy.analysis.template.model.base.Metadata;
import java.io.Serializable;

public class SumConfig
	implements Cloneable, Serializable
{

	private static final long serialVersionUID = 1L;
	protected String id;
	protected String cnName;
	protected String name;
	protected String dataType;
	protected Integer lenth;
	protected Integer precision;
	protected String description;
	protected String sumCondition;
	protected String sumResult;

	public SumConfig()
	{
		id = "";
		cnName = "";
		name = "";
		dataType = "";
		lenth = Integer.valueOf(0);
		precision = Integer.valueOf(0);
		description = "";
		sumCondition = "";
		sumResult = "";
	}

	public SumConfig(Metadata md)
	{
		id = "";
		cnName = "";
		name = "";
		dataType = "";
		lenth = Integer.valueOf(0);
		precision = Integer.valueOf(0);
		description = "";
		sumCondition = "";
		sumResult = "";
		id = md.getId();
		cnName = md.getCnName();
		name = md.getName();
		dataType = md.getDataType();
		lenth = md.getLength();
		precision = md.getPrecision();
		description = md.getDescription();
	}

	public SumConfig clone()
	{
		SumConfig sc = new SumConfig();
		sc.setId(id);
		sc.setCnName(cnName);
		sc.setName(name);
		sc.setDataType(dataType);
		sc.setLenth(lenth);
		sc.setPrecision(precision);
		sc.setDescription(description);
		sc.setSumCondition(sumCondition);
		sc.setSumResult(sumResult);
		return sc;
	}

	public String getId()
	{
		return id;
	}

	public void setId(String id)
	{
		this.id = id;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public String getCnName()
	{
		return cnName;
	}

	public void setCnName(String cnName)
	{
		this.cnName = cnName;
	}

	public String getDataType()
	{
		return dataType;
	}

	public void setDataType(String dataType)
	{
		this.dataType = dataType;
	}

	public String getSumCondition()
	{
		return sumCondition;
	}

	public void setSumCondition(String sumCondition)
	{
		this.sumCondition = sumCondition;
	}

	public String getSumResult()
	{
		return sumResult;
	}

	public void setSumResult(String sumResult)
	{
		this.sumResult = sumResult;
	}

	public Integer getLenth()
	{
		return lenth;
	}

	public void setLenth(Integer lenth)
	{
		this.lenth = lenth;
	}

	public Integer getPrecision()
	{
		return precision;
	}

	public void setPrecision(Integer precision)
	{
		this.precision = precision;
	}

	public String getDescription()
	{
		return description;
	}

	public void setDescription(String description)
	{
		this.description = description;
	}

	public String toXML()
	{
		StringBuffer sb = new StringBuffer("");
		sb.append("      <id>").append(id).append("</id>\r\n");
		sb.append("      <cname>").append(cnName).append("</cname>\r\n");
		sb.append("      <name>").append(name).append("</name>\r\n");
		sb.append("      <dataType>").append(dataType).append("</dataType>\r\n");
		sb.append("      <lenth>").append(lenth).append("</lenth>\r\n");
		sb.append("      <precision>").append(precision).append("</precision>\r\n");
		sb.append("      <description>").append(description).append("</description>\r\n");
		if (sumCondition.equals(Consts.NO))
			sb.append("      <sumCondition>false</sumCondition>\r\n");
		else
		if (sumCondition.equals(Consts.YES))
			sb.append("      <sumCondition>true</sumCondition>\r\n");
		if (sumResult.equals(Consts.NO))
			sb.append("      <sumResult>false</sumResult>\r\n");
		else
		if (sumResult.equals(Consts.YES))
			sb.append("      <sumResult>true</sumResult>\r\n");
		return sb.toString();
	}


}
