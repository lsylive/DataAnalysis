
package com.liusy.analysis.template.model.dialogProperties;

import com.liusy.analysis.template.model.DiagramParameter;
import com.liusy.analysis.template.model.base.Metadata;
import java.io.Serializable;
import java.util.*;

public class ParametersNodeProperties
	implements Cloneable, Serializable
{

	private static final long serialVersionUID = 1L;
	private List<DiagramParameter> paramList;
	private String MultiParamCnName[];
	private String MultiParamName[];

	public ParametersNodeProperties()
	{
		paramList = new ArrayList<DiagramParameter>();
	}

	 public List<Metadata> getMeta()
	{
		List<Metadata> metas = new ArrayList<Metadata>();
		Metadata mt;
		for (Iterator iterator = paramList.iterator(); iterator.hasNext(); metas.add(mt))
		{
			DiagramParameter df = (DiagramParameter)iterator.next();
			mt = new Metadata();
			mt.setCnName(df.getCnName());
			mt.setDataType(df.getDataType());
			mt.setDescription(df.getDescription());
			mt.setId(df.getId());
			mt.setLength(df.getLength());
			mt.setName(df.getName());
			mt.setPrecision(df.getPrecision());
		}

		return metas;
	}

	public String[] getMultiParamName()
	{
		return MultiParamName;
	}

	public void setMultiParamName(String multiParamName[])
	{
		MultiParamName = multiParamName;
	}


	  public List<DiagramParameter> getParamList() 
	{
		return paramList;
	}

	public void setParamList(List<DiagramParameter> paramList)
	{
		this.paramList = paramList;
	}

	public String[] getMultiParamCnName()
	{
		return MultiParamCnName;
	}

	public void setMultiParamCnName(String multiParamCnName[])
	{
		MultiParamCnName = multiParamCnName;
	}
}
