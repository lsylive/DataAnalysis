package com.liusy.analysis.template.model.base;


public class ComboValue
{

	private String name;
	private String value;

	public ComboValue()
	{
		name = "NAME";
		value = "VALUE";
		name = "";
		value = "";
	}

	public ComboValue(String name, String value)
	{
		this.name = "NAME";
		this.value = "VALUE";
		this.name = name;
		this.value = value;
	}

	public String toString()
	{
		return name;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public String getValue()
	{
		return value;
	}

	public void setValue(String value)
	{
		this.value = value;
	}
}
