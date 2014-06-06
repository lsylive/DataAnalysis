
package com.liusy.analysis.template.model.dialogProperties;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.liusy.analysis.template.model.base.Metadata;
import com.liusy.analysis.template.model.vo.FieldConfig;
import com.liusy.analysis.template.model.vo.GrathFieldConfig;
import com.liusy.analysis.template.model.vo.StaticGrathConfig;

public class StatisticInterfaceProperties
	implements Cloneable, Serializable
{

	private static final long serialVersionUID = 1L;
	  private List<Metadata> fields ;
	  private List<GrathFieldConfig> fieldConfigs2;
	  private List<FieldConfig> fieldConfigs ;
	  private StaticGrathConfig staticGrathCfig ;


	public StatisticInterfaceProperties()
	{
		fields = new ArrayList<Metadata>();
		fieldConfigs2 = new ArrayList<GrathFieldConfig>();
		fieldConfigs = new ArrayList<FieldConfig>();
		staticGrathCfig = new StaticGrathConfig();
	}

	public List<Metadata> getFields()
	{
		return fields;
	}

	public void setFields(List<Metadata> fields)
	{
		this.fields = fields;
	}

	public List<FieldConfig> getFieldConfigs()
	{
		GrathFieldConfig item;
		for (Iterator iterator = fieldConfigs2.iterator(); iterator.hasNext(); fieldConfigs.add(item))
			item = (GrathFieldConfig)iterator.next();

		return fieldConfigs;
	}

	public void setFieldConfigs2(List<GrathFieldConfig> fieldConfigs)
	{
		fieldConfigs2 = fieldConfigs;
	}

	public List<GrathFieldConfig> getFieldConfigs2()
	{
		return fieldConfigs2;
	}

	public StaticGrathConfig getStaticGrathCfig()
	{
		return staticGrathCfig;
	}

	public void setStaticGrathCfig(StaticGrathConfig staticGrathCfig)
	{
		this.staticGrathCfig = staticGrathCfig;
	}

	public boolean equals(Object obj)
	{
		if (obj == null)
			return false;
		if (this == obj)
			return true;
		if (obj.getClass() != getClass())
			return false;
		StatisticInterfaceProperties other = (StatisticInterfaceProperties)obj;
		return getFieldConfigs2().equals(other.getFieldConfigs2()) && getFields().equals(other.getFields()) && getStaticGrathCfig().equals(other.getStaticGrathCfig());
	}

	public Object clone()
		throws CloneNotSupportedException
	{
		return super.clone();
	}
}
