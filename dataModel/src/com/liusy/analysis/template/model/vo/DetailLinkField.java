
package com.liusy.analysis.template.model.vo;

import com.liusy.analysis.template.model.Consts;
import java.io.Serializable;

public class DetailLinkField
	implements Cloneable, Serializable
{

	private static final long serialVersionUID = 1L;
	private String linkType;
	private int diagramId;
	private String diagramName;
	private int nodeId;
	private String nodeName;
	private String parameters;
	private String relateParameters;

	public DetailLinkField()
	{
		linkType = Consts.DETAIL_LINKTYPE_NODE;
		diagramId = 0;
		diagramName = "";
		nodeId = 0;
		nodeName = "";
		parameters = "";
		relateParameters = "";
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

	public int getNodeId()
	{
		return nodeId;
	}

	public void setNodeId(int nodeId)
	{
		this.nodeId = nodeId;
	}

	public String getNodeName()
	{
		return nodeName;
	}

	public void setNodeName(String nodeName)
	{
		this.nodeName = nodeName;
	}

	public String getParameters()
	{
		return parameters;
	}

	public void setParameters(String parameters)
	{
		this.parameters = parameters;
	}

	public String getLinkType()
	{
		return linkType;
	}

	public void setLinkType(String linkType)
	{
		this.linkType = linkType;
	}

	public int getDiagramId()
	{
		return diagramId;
	}

	public void setDiagramId(int diagramId)
	{
		this.diagramId = diagramId;
	}

	public String getDiagramName()
	{
		return diagramName;
	}

	public void setDiagramName(String diagramName)
	{
		this.diagramName = diagramName;
	}

	public String getRelateParameters()
	{
		return relateParameters;
	}

	public void setRelateParameters(String relateParameters)
	{
		this.relateParameters = relateParameters;
	}
}
