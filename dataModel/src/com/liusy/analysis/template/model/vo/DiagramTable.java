
package com.liusy.analysis.template.model.vo;

import java.sql.Blob;

public class DiagramTable
{

	private String id;
	private String name;
	private String creater;
	private String cteateData;
	private String description;
	private Blob body;
	private String type;

	public DiagramTable()
	{
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

	public String getCreater()
	{
		return creater;
	}

	public void setCreater(String creater)
	{
		this.creater = creater;
	}

	public String getCteateData()
	{
		return cteateData;
	}

	public void setCteateData(String cteateData)
	{
		this.cteateData = cteateData;
	}

	public String getDescription()
	{
		return description;
	}

	public void setDescription(String description)
	{
		this.description = description;
	}

	public Blob getBody()
	{
		return body;
	}

	public void setBody(Blob body)
	{
		this.body = body;
	}

	public String getType()
	{
		return type;
	}

	public void setType(String type)
	{
		this.type = type;
	}
}
