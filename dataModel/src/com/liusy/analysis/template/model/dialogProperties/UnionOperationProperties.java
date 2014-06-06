
package com.liusy.analysis.template.model.dialogProperties;

import java.io.Serializable;
import java.util.*;

import com.liusy.analysis.template.model.node.INode;
import com.liusy.analysis.template.model.vo.UnionOperationConfig;
import com.liusy.analysis.template.model.vo.UnionOperationField;

public class UnionOperationProperties
	implements Cloneable, Serializable
{

	private static final long serialVersionUID = 1L;
	  private List<UnionOperationConfig> unionOperationConfigList;
	  private List<UnionOperationField> unionOperationExpressionList;
	  private String[] nodeId = { "" };
	  private Map<String, String[]> fieldList;
	  private List<INode> nodeList;
	  private String outNodeStruct = "";

	public UnionOperationProperties()
	{
		unionOperationConfigList = new ArrayList<UnionOperationConfig>();
		unionOperationExpressionList = new ArrayList<UnionOperationField>();
		fieldList = new HashMap<String, String[]>();
		nodeList = new ArrayList<INode>();
		outNodeStruct = "";
	}

	public List<UnionOperationConfig> getUnionOperationConfigList()
	{
		return unionOperationConfigList;
	}

	public void setUnionOperationConfigList(List<UnionOperationConfig> unionOperationConfig)
	{
		unionOperationConfigList = unionOperationConfig;
	}

	public String[] getNodeId()
	{
		return nodeId;
	}

	public void setNodeId(String nodeId[])
	{
		this.nodeId = nodeId;
	}

	public List<UnionOperationField> getUnionOperationFilterList()
	{
		return unionOperationExpressionList;
	}

	public void setUnionOperationFilterList(List<UnionOperationField> unionOperationExpressionList)
	{
		this.unionOperationExpressionList = unionOperationExpressionList;
	}

	public Map<String, String[]> getFieldList()
	{
		return fieldList;
	}

	public void setFieldList(Map<String, String[]> fieldList)
	{
		this.fieldList = fieldList;
	}

	public List<INode> getNodeList()
	{
		return nodeList;
	}

	public void setNodeList(List<INode> nodeList)
	{
		this.nodeList = nodeList;
	}

	public String getOutNodeStruct()
	{
		return outNodeStruct;
	}

	public void setOutNodeStruct(String outNodeStruct)
	{
		this.outNodeStruct = outNodeStruct;
	}
}
