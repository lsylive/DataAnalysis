
package com.liusy.analysis.template.model.node;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.liusy.analysis.template.model.base.Metadata;
import com.liusy.analysis.template.model.dialogProperties.NetChartInterfaceProperties;
import com.liusy.analysis.template.model.vo.DataSet;
import com.liusy.analysis.template.model.vo.FieldConfig;


public class NetChartInterfaceNode extends  Node<NetChartInterfaceProperties>
implements INode
{

	private static final long serialVersionUID = 1L;

	protected Object clone()
		throws CloneNotSupportedException
	{
		return super.clone();
	}

	public NetChartInterfaceNode()
	{
		  this.name = "网状图配置";
		properties = new NetChartInterfaceProperties();
	}

	  public List<Metadata> getMeta() {
		    return ((NetChartInterfaceProperties)this.properties).getFields();
		  }

		  public List<Map<String, Object>> run(List<DataSet> inputDataSets) {
		    if (inputDataSets != null) return ((DataSet)inputDataSets.get(0)).getResultSet();
		    return null;
		  }

		  public List<FieldConfig> getFieldConfigs() {
		    return ((NetChartInterfaceProperties)this.properties).getFieldConfigs();
		  }

	public String getExtraConfigs()
	{
		Map configs = ((NetChartInterfaceProperties)properties).getChartConfig();
		if (configs == null)
			return null;
		StringBuffer sb = new StringBuffer("<chartConfig>\r\n");
		String key;
		String value;
		for (Iterator iterator = configs.keySet().iterator(); iterator.hasNext(); sb.append((new StringBuilder("<")).append(key).append(">").append(value).append("</").append(key).append(">\r\n").toString()))
		{
			key = (String)iterator.next();
			value = (String)configs.get(key);
			if (value == null)
				value = "";
		}

		sb.append("</chartConfig>\r\n");
		return sb.toString();
	}
}
