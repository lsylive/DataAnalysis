


//   CodeSet.java

package com.liusy.dataapplatform.code;

import java.util.List;

public class CodeSet
{

	protected String codeSetId;
	protected String codeSetNo;
	protected String name;
	protected List codes;

	public CodeSet()
	{
		codeSetId = null;
		name = null;
	}

	public String getCodeSetId()
	{
		return codeSetId;
	}

	public void setCodeSetId(String codeSetId)
	{
		this.codeSetId = codeSetId;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public String getCodeSetNo()
	{
		return codeSetNo;
	}

	public void setCodeSetNo(String codeSetNo)
	{
		this.codeSetNo = codeSetNo;
	}

	public List getCodes()
	{
		return codes;
	}

	public void setCodes(List codes)
	{
		this.codes = codes;
	}
}
