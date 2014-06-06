
package com.liusy.analysis.template.model.vo;

import java.io.Serializable;

// Referenced classes of package com.liusy.analysis.template.model.vo:
//			CodeSet

public class Code
	implements Cloneable, Serializable
{

	private static final long serialVersionUID = 1L;
	private String key;
	private String value;
	private CodeSet codeSet;
	private String id;

	public Code()
	{
		key = "";
		value = "";
		codeSet = new CodeSet();
		id = "";
	}

	public String getKey()
	{
		return key;
	}

	public void setKey(String key)
	{
		this.key = key;
	}

	public String getValue()
	{
		return value;
	}

	public void setValue(String value)
	{
		this.value = value;
	}

	public CodeSet getCodeSet()
	{
		return codeSet;
	}

	public void setCodeSet(CodeSet codeSet)
	{
		this.codeSet = codeSet;
	}

	public String getId()
	{
		return id;
	}

	public void setId(String id)
	{
		this.id = id;
	}

	public String toXML()
	{
		return (new StringBuilder("<code label=\"")).append(key != null ? key : "").append("\" data=\"").append(value != null ? value : "").append("\" />\r\n").toString();
	}

	public Object clone()
	{
		try {
			return super.clone();
		} catch (CloneNotSupportedException e) {
			System.out.println(e.getMessage());
		}
		return null;
	}
}
