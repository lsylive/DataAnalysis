
package com.liusy.analysis.template.model.vo;

import java.io.Serializable;

public class MultiTablesDataField
	implements Cloneable, Serializable
{

	private static final long serialVersionUID = 1L;
	private String tableCnName;
	private String tableAliasName;
	private String tableName;

	public MultiTablesDataField()
	{
		tableCnName = "";
		tableAliasName = "";
		tableName = "";
	}

	public Object clone()
	{
		MultiTablesDataField df = new MultiTablesDataField();
		df.setTableAliasName(tableAliasName);
		df.setTableCnName(tableCnName);
		df.setTableName(tableName);
		return df;
	}

	public String getTableCnName()
	{
		return tableCnName;
	}

	public void setTableCnName(String tableCnName)
	{
		this.tableCnName = tableCnName;
	}

	public String getTableAliasName()
	{
		return tableAliasName;
	}

	public void setTableAliasName(String tableAliasName)
	{
		this.tableAliasName = tableAliasName;
	}

	public static long getSerialVersionUID()
	{
		return 1L;
	}

	public String getTableName()
	{
		return tableName;
	}

	public void setTableName(String tableName)
	{
		this.tableName = tableName;
	}
}
