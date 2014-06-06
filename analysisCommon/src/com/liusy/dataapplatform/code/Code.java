


//   Code.java

package com.liusy.dataapplatform.code;


public class Code
{

	private String codeName;
	private String value;
	private String parentCodeId;
	private String parentCodeValue;

	public Code(String codeName, String value)
	{
		this.codeName = codeName;
		this.value = value;
		parentCodeId = null;
		parentCodeValue = null;
	}

	public Code(String codeName, String value, String pcodeid, String pvalue)
	{
		this.codeName = codeName;
		this.value = value;
		parentCodeId = pcodeid;
		parentCodeValue = pvalue;
	}

	public Code()
	{
		codeName = null;
		value = null;
	}

	public String getCodeName()
	{
		return codeName;
	}

	public void setCodeName(String codeName)
	{
		this.codeName = codeName;
	}

	public String getValue()
	{
		return value;
	}

	public void setValue(String value)
	{
		this.value = value;
	}

	public String getParentCodeValue()
	{
		return parentCodeValue;
	}

	public void setParentCodeValue(String parentCodeValue)
	{
		this.parentCodeValue = parentCodeValue;
	}

	public String getParentCodeId()
	{
		return parentCodeId;
	}

	public void setParentCodeId(String parentCodeId)
	{
		this.parentCodeId = parentCodeId;
	}
}
