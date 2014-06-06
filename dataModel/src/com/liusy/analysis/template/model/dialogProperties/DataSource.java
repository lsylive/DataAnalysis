
package com.liusy.analysis.template.model.dialogProperties;

import java.io.Serializable;

public class DataSource
	implements Cloneable, Serializable
{

	private static final long serialVersionUID = 1L;
	private String dataBaseName;
	private String tableName;

	public DataSource()
	{
		dataBaseName = "";
		tableName = "";
	}

	public String getDataBaseName()
	{
		return dataBaseName;
	}

	public void setDataBaseName(String dataBaseName)
	{
		this.dataBaseName = dataBaseName;
	}

	public String getTableName()
	{
		return tableName;
	}

	public void setTableName(String tableName)
	{
		this.tableName = tableName;
	}

	public boolean equals(Object obj)
	{
		if (obj == null)
			return false;
		if (this == obj)
			return true;
		if (obj.getClass() != getClass())
			return false;
		DataSource other = (DataSource)obj;
		return getTableName().equals(other.getTableName()) && getDataBaseName().equals(other.getDataBaseName());
	}
}
