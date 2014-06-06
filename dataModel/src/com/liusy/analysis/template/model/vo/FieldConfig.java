
package com.liusy.analysis.template.model.vo;

import com.liusy.analysis.template.model.Consts;
import com.liusy.analysis.template.model.base.Metadata;
import java.io.Serializable;

// Referenced classes of package com.liusy.analysis.template.model.vo:
//			DetailLinkField

public class FieldConfig
	implements Cloneable, Serializable
{

	private static final long serialVersionUID = 1L;
	protected String cnName;
	protected String name;
	protected String dataType;
	protected String visible;
	protected String width;
	protected String align;
	protected String dataFormat;
	protected String codeSet;
	protected DetailLinkField detailLink;

	public FieldConfig()
	{
		cnName = "";
		name = "";
		dataType = "";
		visible = "1";
		width = "100";
		align = "left";
		dataFormat = "";
		codeSet = "";
		detailLink = new DetailLinkField();
	}

	public FieldConfig(Metadata md)
	{
		cnName = "";
		name = "";
		dataType = "";
		visible = "1";
		width = "100";
		align = "left";
		dataFormat = "";
		codeSet = "";
		detailLink = new DetailLinkField();
		cnName = md.getCnName();
		name = md.getName();
		dataType = md.getDataType();
	}


	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public String getWidth()
	{
		return width;
	}

	public void setWidth(String width)
	{
		this.width = width;
	}

	public String getCnName()
	{
		return cnName;
	}

	public void setCnName(String cnName)
	{
		this.cnName = cnName;
	}

	public String getAlign()
	{
		return align;
	}

	public void setAlign(String align)
	{
		this.align = align;
	}

	public String getDataFormat()
	{
		return dataFormat;
	}

	public void setDataFormat(String dataFormat)
	{
		this.dataFormat = dataFormat;
	}

	public String getVisible()
	{
		return visible;
	}

	public void setVisible(String visible)
	{
		this.visible = visible;
	}

	public String toXML()
	{
		StringBuffer sb = new StringBuffer("");
		sb.append("      <name>").append(name).append("</name>\r\n");
		sb.append("      <cname>").append(cnName).append("</cname>\r\n");
		sb.append("      <dataType>").append(dataType).append("</dataType>\r\n");
		sb.append("      <dataformat>").append(dataFormat).append("</dataformat>\r\n");
		sb.append("      <align>").append(align).append("</align>\r\n");
		sb.append("      <width>").append(width).append("</width>\r\n");
		if (visible.equals(Consts.NO))
			sb.append("      <visible>false</visible>\r\n");
		if (codeSet != null && codeSet.length() > 0)
			sb.append((new StringBuilder("      <codeset>")).append(codeSet).append("</codeset>\r\n").toString());
		if (detailLink != null && (detailLink.getNodeId() > 0 || detailLink.getDiagramId() > 0))
		{
			sb.append("      <link>\r\n");
			sb.append((new StringBuilder("         <linkType>")).append(detailLink.getLinkType() != null ? detailLink.getLinkType() : "").append("</linkType>\r\n").toString());
			sb.append((new StringBuilder("         <nodeId>")).append(detailLink.getNodeId()).append("</nodeId>\r\n").toString());
			sb.append((new StringBuilder("         <nodeName>")).append(detailLink.getNodeName() != null ? detailLink.getNodeName() : "").append("</nodeName>\r\n").toString());
			sb.append((new StringBuilder("         <diagramId>")).append(detailLink.getDiagramId()).append("</diagramId>\r\n").toString());
			sb.append((new StringBuilder("         <diagramName>")).append(detailLink.getDiagramName() != null ? detailLink.getDiagramName() : "").append("</diagramName>\r\n").toString());
			sb.append((new StringBuilder("         <parameters>")).append(detailLink.getParameters() != null ? detailLink.getParameters() : "").append("</parameters>\r\n").toString());
			sb.append((new StringBuilder("         <relateParameters>")).append(detailLink.getRelateParameters() != null ? detailLink.getRelateParameters() : "").append("</relateParameters>\r\n").toString());
			sb.append("      </link>\r\n");
		}
		return sb.toString();
	}

	public String getCodeSet()
	{
		return codeSet;
	}

	public void setCodeSet(String codeSet)
	{
		this.codeSet = codeSet;
	}

	public String getDataType()
	{
		return dataType;
	}

	public void setDataType(String dataType)
	{
		this.dataType = dataType;
	}

	public DetailLinkField getDetailLink()
	{
		return detailLink;
	}

	public void setDetailLink(DetailLinkField link)
	{
		detailLink = link;
	}

	public FieldConfig clone() 
	{
		FieldConfig fc = new FieldConfig();
		fc.setAlign(align);
		fc.setCnName(cnName);
		fc.setDataFormat(dataFormat);
		fc.setName(name);
		fc.setVisible(visible);
		fc.setWidth(width);
		fc.setCodeSet(codeSet);
		fc.setDataType(dataType);
		fc.setDetailLink(detailLink);
		return fc;
	}
}
