
package com.liusy.analysis.template.model.dialogProperties;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.liusy.analysis.template.model.base.Metadata;
import com.liusy.analysis.template.model.vo.FieldConfig;

public class StarChartInterfaceProperties
	implements Cloneable, Serializable
{

	private static final long serialVersionUID = 1L;
	  private List<Metadata> fields;
	  private List<FieldConfig> fieldConfigs ;
	  private Map<String, String> chartConfig;


	public StarChartInterfaceProperties()
	{
		fields = new ArrayList<Metadata>();
		fieldConfigs = new ArrayList<FieldConfig>();
		chartConfig = new HashMap<String, String>();
	}


	public List<Metadata> getFields() {
		return fields;
	}


	public void setFields(List<Metadata> fields) {
		this.fields = fields;
	}


	public List<FieldConfig> getFieldConfigs() {
		return fieldConfigs;
	}


	public void setFieldConfigs(List<FieldConfig> fieldConfigs) {
		this.fieldConfigs = fieldConfigs;
	}


	public Map<String, String> getChartConfig() {
		return chartConfig;
	}


	public void setChartConfig(Map<String, String> chartConfig) {
		this.chartConfig = chartConfig;
	}


}
