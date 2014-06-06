
package com.liusy.analysis.template.model.dialogProperties;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.liusy.analysis.template.model.base.Metadata;
import com.liusy.analysis.template.model.vo.FieldConfig;

public class TimeLineChartInterfaceProperties
implements Cloneable, Serializable
{
 private static final long serialVersionUID = 1L;
 private List<Metadata> fields = new ArrayList();
 private List<FieldConfig> fieldConfigs = new ArrayList();
 private Map<String, String> chartConfig = new HashMap();

 public List<Metadata> getFields() {
   return this.fields;
 }

 public void setFields(List<Metadata> fields) {
   this.fields = fields;
 }

 public List<FieldConfig> getFieldConfigs() {
   return this.fieldConfigs;
 }

 public void setFieldConfigs(List<FieldConfig> fieldConfigs) {
   this.fieldConfigs = fieldConfigs;
 }

 public Map<String, String> getChartConfig() {
   return this.chartConfig;
 }

 public void setChartConfig(Map<String, String> chartConfig) {
   this.chartConfig = chartConfig;
 }
}