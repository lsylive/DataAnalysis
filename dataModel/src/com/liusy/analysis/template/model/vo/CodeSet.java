
package com.liusy.analysis.template.model.vo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

// Referenced classes of package com.liusy.analysis.template.model.vo:
//			Code

public class CodeSet
	implements Cloneable, Serializable
{

	private static final long serialVersionUID = 1L;
	private String id;
	private String name;
	private String cnName;
	private String codesetType;
	private String description;
	private List<Code> codes;

	public CodeSet()
	{
		id = "";
		name = "";
		cnName = "";
		codesetType = "";
		description = "";
		codes = new ArrayList();
	}

	public String getId()
	{
		return id;
	}

	public String getDescription()
	{
		return description;
	}

	public void setDescription(String description)
	{
		this.description = description;
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

	public void setCnName(String cname)
	{
		cnName = cname;
	}

	public List<Code> getCodes()
	{
		return codes;
	}

	public void setCodes(List codes)
	{
		this.codes = codes;
	}

	public String getCodesetType()
	{
		return codesetType;
	}

	public void setCodesetType(String codesetType)
	{
		this.codesetType = codesetType;
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

	public String toXML()
	{
		StringBuffer sb = new StringBuffer("");
		sb.append("   <codeset name=\"").append(name != null ? name : "").append("\">\r\n");
		if (codes == null)
			codes = new ArrayList();
		sb.append("      <code label=\"\" data=\"\" />\r\n");
		for (int i = 0; i < codes.size(); i++)
			sb.append((new StringBuilder("      ")).append(((Code)codes.get(i)).toXML()).toString());

		sb.append("   </codeset>\r\n");
		return sb.toString();
	}
}
