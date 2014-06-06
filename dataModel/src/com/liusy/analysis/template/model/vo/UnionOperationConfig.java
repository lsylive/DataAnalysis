
package com.liusy.analysis.template.model.vo;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class UnionOperationConfig
	implements Cloneable, Serializable
{

	private static final long serialVersionUID = 1L;
	private Map relateMetaList;

	public UnionOperationConfig()
	{
		relateMetaList = new HashMap();
	}

	public UnionOperationConfig clone()
	{
		UnionOperationConfig fc = new UnionOperationConfig();
		fc.setRelateMetaList(relateMetaList);
		return fc;
	}

	public Map getRelateMetaList()
	{
		return relateMetaList;
	}

	public void setRelateMetaList(Map relateMetaList)
	{
		this.relateMetaList = relateMetaList;
	}

	public String getFieldNameByNodeId(String nodeid)
	{
		return (String)getRelateMetaList().get(nodeid);
	}


}
