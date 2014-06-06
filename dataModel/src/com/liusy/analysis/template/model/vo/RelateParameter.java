
package com.liusy.analysis.template.model.vo;


public class RelateParameter
{

	protected String cnName;
	protected String name;
	protected String relateCnName;
	protected String relateName;

	public RelateParameter(String cnName, String name)
	{
		this.cnName = "";
		this.name = "";
		relateCnName = "";
		relateName = "";
		this.cnName = cnName;
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

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public String getRelateCnName()
	{
		return relateCnName;
	}

	public void setRelateCnName(String relateCnName)
	{
		this.relateCnName = relateCnName;
	}

	public String getRelateName()
	{
		return relateName;
	}

	public void setRelateName(String relateName)
	{
		this.relateName = relateName;
	}
}
