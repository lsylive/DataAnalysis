
package com.liusy.analysis.template.model.node;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.liusy.analysis.template.model.base.Metadata;
import com.liusy.analysis.template.model.dialogProperties.TimeLineChartInterfaceProperties;
import com.liusy.analysis.template.model.vo.DataSet;
import com.liusy.analysis.template.model.vo.FieldConfig;

// Referenced classes of package com.liusy.analysis.template.model.node:
//			Node, INode

public class TimeLineChartInterfaceNode extends Node<TimeLineChartInterfaceProperties>
implements INode
{

	private int ChartType;
	private static final long serialVersionUID = 1L;

	protected Object clone()
		throws CloneNotSupportedException
	{
		return super.clone();
	}

	public TimeLineChartInterfaceNode()
	{
		ChartType = -1;
		   this.name = "时序轨迹图配置";
		properties = new TimeLineChartInterfaceProperties();
	}

	  public List<Metadata> getMeta() {
		    return ((TimeLineChartInterfaceProperties)this.properties).getFields();
		  }

		  public List<Map<String, Object>> run(List<DataSet> inputDataSets) {
		    if (inputDataSets != null) return ((DataSet)inputDataSets.get(0)).getResultSet();
		    return null;
		  }

		  public List<FieldConfig> getFieldConfigs() {
		    return ((TimeLineChartInterfaceProperties)this.properties).getFieldConfigs();
		  }
	public String getExtraConfigs()
	{
		Map configs = ((TimeLineChartInterfaceProperties)properties).getChartConfig();
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

	public int getChartType()
	{
		return ChartType;
	}

	public void setChartType(int chartType)
	{
		ChartType = chartType;
	}

	public static long getSerialVersionUID()
	{
		return 1L;
	}
}
