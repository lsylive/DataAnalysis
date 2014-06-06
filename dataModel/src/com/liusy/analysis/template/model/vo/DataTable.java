
package com.liusy.analysis.template.model.vo;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public class DataTable
	implements Cloneable, Serializable
{

	private static final long serialVersionUID = 1L;
	private String id;
	private String pkName;
	private String name;
	private String cnName;
	private String catalogId;
	private String catalogName;
	  private List<DataField> fields;
	  private Map<String, Object> record;

	public DataTable()
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

	public String getCnName()
	{
		return cnName;
	}

	public void setCnName(String cnName)
	{
		this.cnName = cnName;
	}

	public String getCatalogId()
	{
		return catalogId;
	}

	public void setCatalogId(String catalogId)
	{
		this.catalogId = catalogId;
	}

	public String getCatalogName()
	{
		return catalogName;
	}

	public void setCatalogName(String catalogName)
	{
		this.catalogName = catalogName;
	}

	public List<DataField> getFields()
	{
		return fields;
	}

	public void setFields(List<DataField> fields)
	{
		this.fields = fields;
	}

	public Map<String, Object> getRecord()
	{
		return record;
	}

	public void setRecord(Map<String, Object> record)
	{
		this.record = record;
	}

	public String getPkName()
	{
		return pkName;
	}

	public void setPkName(String pkName)
	{
		this.pkName = pkName;
	}
}
