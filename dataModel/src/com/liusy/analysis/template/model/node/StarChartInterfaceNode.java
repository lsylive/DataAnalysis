
package com.liusy.analysis.template.model.node;

import com.liusy.analysis.template.model.base.Metadata;
import com.liusy.analysis.template.model.dialogProperties.SortProperties;
import com.liusy.analysis.template.model.dialogProperties.StarChartInterfaceProperties;
import com.liusy.analysis.template.model.vo.DataSet;
import com.liusy.analysis.template.model.vo.FieldConfig;

import java.util.*;


public class StarChartInterfaceNode extends  Node<StarChartInterfaceProperties>
implements INode
{

	private static final long serialVersionUID = 1L;

	protected Object clone()
		throws CloneNotSupportedException
	{
		return super.clone();
	}

	public StarChartInterfaceNode()
	{
	    this.name = "星形图配置";
		properties = new StarChartInterfaceProperties();
	}

	public List<Metadata> getMeta()
	{
		return ((StarChartInterfaceProperties)properties).getFields();
	}

	  public List<Map<String, Object>> run(List<DataSet> inputDataSets) {
	    if (inputDataSets != null) return ((DataSet)inputDataSets.get(0)).getResultSet();
	    return null;
	  }

	  public List<FieldConfig> getFieldConfigs() {
	    return ((StarChartInterfaceProperties)this.properties).getFieldConfigs();
	  }

	  public String getExtraConfigs() {
	    Map<String,String> configs = ((StarChartInterfaceProperties)this.properties).getChartConfig();
	    if (configs == null) return null;

	    StringBuffer sb = new StringBuffer("<chartConfig>\r\n");
	    for (String key : configs.keySet()) {
	      String value = (String)configs.get(key);
	      if (value == null) value = "";
	      sb.append("<" + key + ">" + value + "</" + key + ">\r\n");
	    }
	    sb.append("</chartConfig>\r\n");
	    return sb.toString();
	  }
	}