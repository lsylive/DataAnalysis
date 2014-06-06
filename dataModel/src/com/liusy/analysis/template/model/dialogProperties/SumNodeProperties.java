
package com.liusy.analysis.template.model.dialogProperties;

import com.liusy.analysis.template.model.base.Metadata;
import com.liusy.analysis.template.model.vo.SumConfig;
import java.io.Serializable;
import java.util.*;

public class SumNodeProperties
	implements Cloneable, Serializable
{

	public static int DATASET_NEW = 0;
	public static int DATASET_APPEND = 1;
	private static final long serialVersionUID = 2L;
	private String name;
	private int datasetType;
	  private List<SumConfig> inputFieldList = new ArrayList<SumConfig>();



	public SumNodeProperties()
	{
		name = "";
		datasetType = DATASET_NEW;
		inputFieldList = new ArrayList<SumConfig>();
	}

	  public List<Metadata> getMeta() {
		List metas = new ArrayList();
		Metadata mt;
		for (Iterator iterator = inputFieldList.iterator(); iterator.hasNext(); metas.add(mt))
		{
			SumConfig sc = (SumConfig)iterator.next();
			mt = new Metadata();
			mt.setCnName(sc.getCnName());
			mt.setName(sc.getName());
			mt.setDataType(sc.getDataType());
			mt.setDescription(sc.getDescription());
			mt.setId(sc.getId());
			mt.setLength(sc.getLenth());
			mt.setPrecision(sc.getPrecision());
		}

		return metas;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public boolean equals(Object obj)
	{
		if (obj == null)
			return false;
		if (this == obj)
			return true;
		if (obj.getClass() != getClass())
			return false;
		SumNodeProperties other = (SumNodeProperties)obj;
		return getName().equals(other.getName());
	}

	public Object clone()
		throws CloneNotSupportedException
	{
		return super.clone();
	}

	public List<SumConfig> getInputFieldList()
	{
		return inputFieldList;
	}

	public void setInputFieldList(List<SumConfig> inputFieldList)
	{
		this.inputFieldList = inputFieldList;
	}

	public int getDatasetType()
	{
		return datasetType;
	}

	public void setDatasetType(int datasetType)
	{
		this.datasetType = datasetType;
	}

}
