
package com.liusy.analysis.template.model.dialogProperties;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.liusy.analysis.template.model.base.Metadata;
import com.liusy.analysis.template.model.vo.FieldConfig;
import com.liusy.analysis.template.model.vo.GrathFieldConfig;
import com.liusy.analysis.template.model.vo.LinearGrathConfig;

public class LineChartInterfaceProperties
	implements Cloneable, Serializable
{

	private static final long serialVersionUID = 1L;
	  private List<Metadata> fields;
	  private List<GrathFieldConfig> fieldConfigs2; 
	  private List<FieldConfig> fieldConfigs;
	  private LinearGrathConfig linearGraphCfig;

	public LineChartInterfaceProperties()
	{
		fields = new ArrayList<Metadata>();
		fieldConfigs2 = new ArrayList<GrathFieldConfig>();
		fieldConfigs = new ArrayList<FieldConfig>();
		linearGraphCfig = new LinearGrathConfig();
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
		FieldConfig fieldConfig;
		for (Iterator iterator = fieldConfigs2.iterator(); iterator.hasNext(); fieldConfigs.add(fieldConfig))
		{
			GrathFieldConfig item = (GrathFieldConfig)iterator.next();
			fieldConfig = new FieldConfig();
			fieldConfig.setAlign(item.getAlign());
			fieldConfig.setCnName(item.getCnName());
			fieldConfig.setCodeSet(item.getCodeSet());
			fieldConfig.setDataFormat(item.getDataFormat());
			fieldConfig.setName(item.getName());
			fieldConfig.setVisible(item.getVisible());
			fieldConfig.setWidth(item.getWidth());
		}

		return fieldConfigs;
	}

	  public void setFieldConfigs2(List<GrathFieldConfig> fieldConfigs) {
		    this.fieldConfigs2 = fieldConfigs;
		  }
		  public List<GrathFieldConfig> getFieldConfigs2() {
		    return this.fieldConfigs2;
		  }

	public boolean equals(Object obj)
	{
		if (obj == null)
			return false;
		if (this == obj)
			return true;
		if (obj.getClass() != getClass())
			return false;
		LineChartInterfaceProperties other = (LineChartInterfaceProperties)obj;
		return getFieldConfigs2().equals(other.getFieldConfigs2()) && getFields().equals(other.getFields()) && getLinearGraphCfig().equals(other.getLinearGraphCfig());
	}

	public Object clone()
		throws CloneNotSupportedException
	{
		return super.clone();
	}

	public LinearGrathConfig getLinearGraphCfig()
	{
		return linearGraphCfig;
	}

	public void setLinearGraphCfig(LinearGrathConfig linearGraphCfig)
	{
		this.linearGraphCfig = linearGraphCfig;
	}
}
