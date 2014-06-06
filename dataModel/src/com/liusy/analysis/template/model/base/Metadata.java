
package com.liusy.analysis.template.model.base;

import java.io.Serializable;

public class Metadata
	implements Cloneable, Serializable
{

	private static final long serialVersionUID = 1L;
	protected String id;
	protected String name;
	protected String cnName;
	protected String dataType;
	protected Integer length;
	protected Integer precision;
	protected String description;
	public static String META_NAME;
	public static String META_CNNAME;
	public static String META_DATATYPE;
	public static String META_LENGTH;
	public static String META_PRECISION;
	public static String META_DESCRIPTION;
	public static String NAMES[];

	public Metadata()
	{
		id = "";
		name = "";
		cnName = "";
		dataType = "";
		length = Integer.valueOf(0);
		precision = Integer.valueOf(0);
		description = "";
	}

	public Object clone()
	{
		try {
			return super.clone();	
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
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

	public String getDataType()
	{
		return dataType;
	}

	public void setDataType(String dataType)
	{
		this.dataType = dataType;
	}

	public Integer getLength()
	{
		return length;
	}

	public void setLength(Integer length)
	{
		this.length = length;
	}

	public Integer getPrecision()
	{
		return precision;
	}

	public void setPrecision(Integer precision)
	{
		this.precision = precision;
	}

	public String getDescription()
	{
		return description;
	}

	public void setDescription(String description)
	{
		this.description = description;
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
		StringBuffer xmlStr = new StringBuffer("");
		xmlStr.append("<name>").append(name).append("</name>\r\n");
		xmlStr.append("<cname>").append(cnName).append("</cname>\r\n");
		xmlStr.append("<dataType>").append(dataType).append("</dataType>\r\n");
		xmlStr.append("<length>").append(length).append("</length>\r\n");
		xmlStr.append("<precision>").append(precision).append("</precision>\r\n");
		xmlStr.append("<description>").append(description).append("</description>\r\n");
		return xmlStr.toString();
	}

	public boolean equals(Object obj)
	{
		if (obj == null)
			return false;
		if (this == obj)
			return true;
		if (obj.getClass() != getClass())
			return false;
		Metadata other = (Metadata)obj;
		return name.equals(other.name) && cnName.equals(other.cnName) && dataType.equals(other.dataType) && length.equals(other.length) && precision.equals(other.precision) && description.equals(other.description);
	}

	static 
	{
		META_NAME = "name";
		META_CNNAME = "cnName";
		META_DATATYPE = "dataType";
		META_LENGTH = "length";
		META_PRECISION = "precision";
		META_DESCRIPTION = "description";
		NAMES = (new String[] {
			META_NAME, META_CNNAME, META_DATATYPE, META_LENGTH, META_PRECISION, META_DESCRIPTION
		});
	}
}
