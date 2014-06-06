
package com.liusy.analysis.template.model;

import com.liusy.analysis.template.model.base.Metadata;
import java.io.Serializable;

// Referenced classes of package com.liusy.analysis.template.model:
//			Consts

public class DiagramParameter extends Metadata
	implements Cloneable, Serializable
{

	private static final long serialVersionUID = 1L;
	protected String polyLineFlag;
	protected String mutliSelect;
	protected String codeSet;
	protected String editFlg;
	protected String value;
	protected String defalutValue;
	public static String POLYLINEFLAG = "polyLineFlag";
	public static String MUTLISELECT = "mutliSelect";
	public static String CODESET = "codeSet";
	public static String META_TYPE_STRING = "01";
	public static String META_TYPE_DATE = "02";
	public static String META_TYPE_NUMERIC = "03";

	public DiagramParameter()
	{
		polyLineFlag = Consts.NO;
		mutliSelect = Consts.NO;
		codeSet = "";
		editFlg = "";
		value = "";
		defalutValue = "";
	}

	public boolean equals(Object obj)
	{
		if (obj instanceof DiagramParameter)
		{
			DiagramParameter o = (DiagramParameter)obj;
			return name.equals(o.getName());
		} else
		{
			return false;
		}
	}

	public String getPolyLineFlag()
	{
		return polyLineFlag;
	}

	public void setPolyLineFlag(String polyLineFlag)
	{
		this.polyLineFlag = polyLineFlag;
	}

	public String getEditFlg()
	{
		return editFlg;
	}

	public void setEditFlg(String editFlg)
	{
		this.editFlg = editFlg;
	}

	public Object clone()
	{
		DiagramParameter dp = new DiagramParameter();
		dp.setCnName(this.cnName);
		dp.setCodeSet(this.codeSet);
		dp.setDataType(this.dataType);
		dp.setDefalutValue(this.defalutValue);
		dp.setDescription(this.description);
		dp.setEditFlg(this.editFlg);
		dp.setPolyLineFlag(this.polyLineFlag);
		dp.setId(this.id);
		dp.setLength(this.length);
		dp.setMutliSelect(this.mutliSelect);
		dp.setName(this.name);
		dp.setPrecision(this.precision);
		dp.setValue(this.value);
		return dp;
	}

	public String toXML()
	{
		StringBuffer sb = new StringBuffer("");
		sb.append("      <name>").append(name).append("</name>\r\n");
		sb.append("      <cname>").append(cnName).append("</cname>\r\n");
		sb.append("      <type>").append(getDataType(dataType)).append("</type>\r\n");
		sb.append("      <polyLine>").append(polyLineFlag).append("</polyLine>\r\n");
		sb.append("      <mutliSelect>").append(mutliSelect).append("</mutliSelect>\r\n");
		if (codeSet != null && codeSet.length() > 0)
			sb.append("      <codeset>").append(codeSet).append("</codeset>\r\n");
		if (value != null && value.length() > 0)
			sb.append("      <value>").append(value).append("</value>\r\n");
		if (defalutValue != null && defalutValue.length() > 0)
			sb.append("      <defalutValue>").append(defalutValue).append("</defalutValue>\r\n");
		return sb.toString();
	}

	public static String getDataType(String type)
	{
		if (type.equals(META_TYPE_STRING))
			return "string";
		if (type.equals(META_TYPE_NUMERIC))
			return "number";
		if (type.equals(META_TYPE_DATE))
			return "date";
		else
			return "string";
	}

	public String getCodeSet()
	{
		return codeSet;
	}

	public void setCodeSet(String codeSet)
	{
		this.codeSet = codeSet;
	}

	public String getMutliSelect()
	{
		return mutliSelect;
	}

	public void setMutliSelect(String mutliSelect)
	{
		this.mutliSelect = mutliSelect;
	}

	public String getValue()
	{
		return value;
	}

	public void setValue(String value)
	{
		this.value = value;
	}

	public String getDefalutValue()
	{
		return defalutValue;
	}

	public void setDefalutValue(String defalutValue)
	{
		this.defalutValue = defalutValue;
	}

}
