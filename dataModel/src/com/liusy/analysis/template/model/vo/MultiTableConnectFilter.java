
package com.liusy.analysis.template.model.vo;

import java.io.Serializable;

public class MultiTableConnectFilter
	implements Cloneable, Serializable
{

	private static final long serialVersionUID = 1L;
	private String tableEnName;
	private String tableAliasName;
	private String connectedEnTableName;
	private String connectedTableAliasName;
	private String connectedConditions;
	private String operator;

	public MultiTableConnectFilter()
	{
	}

	public MultiTableConnectFilter clone()
	{
		MultiTableConnectFilter df = new MultiTableConnectFilter();
		df.setTableAliasName(tableAliasName);
		df.setTableEnName(tableEnName);
		df.setConnectedEnTableName(connectedEnTableName);
		df.setConnectedConditions(connectedConditions);
		df.setConnectedTableAliasName(connectedTableAliasName);
		df.setOperator(operator);
		return df;
	}

	public String getOperator()
	{
		return operator;
	}

	public void setOperator(String operator)
	{
		this.operator = operator;
	}

	public String getTableAliasName()
	{
		return tableAliasName;
	}

	public void setTableAliasName(String tableAliasName)
	{
		this.tableAliasName = tableAliasName;
	}

	public String getConnectedTableAliasName()
	{
		return connectedTableAliasName;
	}

	public void setConnectedTableAliasName(String connectedTableAliasName)
	{
		this.connectedTableAliasName = connectedTableAliasName;
	}

	public static long getSerialVersionUID()
	{
		return 1L;
	}

	public String getTableEnName()
	{
		return tableEnName;
	}

	public void setTableEnName(String tableEnName)
	{
		this.tableEnName = tableEnName;
	}

	public String getConnectedEnTableName()
	{
		return connectedEnTableName;
	}

	public void setConnectedEnTableName(String connectedEnTableName)
	{
		this.connectedEnTableName = connectedEnTableName;
	}

	public String getConnectedConditions()
	{
		return connectedConditions;
	}

	public void setConnectedConditions(String connectedConditions)
	{
		this.connectedConditions = connectedConditions;
	}


}
