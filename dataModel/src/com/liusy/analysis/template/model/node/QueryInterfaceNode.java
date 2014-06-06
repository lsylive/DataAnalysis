
package com.liusy.analysis.template.model.node;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.liusy.analysis.template.model.Consts;
import com.liusy.analysis.template.model.base.Metadata;
import com.liusy.analysis.template.model.dialogProperties.QueryInterfaceProperties;
import com.liusy.analysis.template.model.vo.DataSet;
import com.liusy.analysis.template.model.vo.FieldConfig;


public class QueryInterfaceNode extends Node<QueryInterfaceProperties>
implements INode
{

	private static final long serialVersionUID = 1L;

	protected Object clone()
		throws CloneNotSupportedException
	{
		return super.clone();
	}

	public QueryInterfaceNode()
	{
	    this.name = "查询配置";
		properties = new QueryInterfaceProperties();
	}

	  public List<Metadata> getMeta() {
		    return ((QueryInterfaceProperties)this.properties).getFields();
		  }

		  public List<Map<String, Object>> run(List<DataSet> inputDataSets) {
		List fieldConfigs = new ArrayList();
		fieldConfigs = ((QueryInterfaceProperties)properties).getFieldConfigs();
		if (inputDataSets != null)
		{
			List source = ((DataSet)inputDataSets.get(0)).getResultSet();
			List target = new ArrayList();
			target.addAll(source);
			for (Iterator iterator = target.iterator(); iterator.hasNext();)
			{
				Map map = (Map)iterator.next();
				for (Iterator iterator1 = fieldConfigs.iterator(); iterator1.hasNext();)
				{
					FieldConfig fc = (FieldConfig)iterator1.next();
					if (fc.getDataFormat() != null && fc.getDataFormat().length() > 0)
					{
						String format = fc.getDataFormat();
						String key = fc.getName();
						if (fc.getDataType() != null && fc.getDataType().equals(Consts.DATATYPE_DATE))
						{
							SimpleDateFormat sdf = new SimpleDateFormat(format);
							Object value = (Date)map.get(key);
							try
							{
								value = sdf.format(value);
							}
							catch (Exception e)
							{
								System.out.println(e.getMessage());
								value = map.get(key);
							}
							map.put(key, value);
						}
						if (fc.getDataType() != null && fc.getDataType().equals(Consts.DATATYPE_NUMERIC))
						{
							DecimalFormat df = new DecimalFormat(format);
							Object value = (Double)map.get(key);
							try
							{
								value = df.format(value);
							}
							catch (Exception e)
							{
								System.out.println(e.getMessage());
								value = map.get(key);
							}
							map.put(key, value);
						}
					}
				}

			}

			return target;
		} else
		{
			return null;
		}
	}

	public List<FieldConfig> getFieldConfigs()
	{
		return ((QueryInterfaceProperties)properties).getFieldConfigs();
	}

	public String getExtraConfigs()
	{
		return null;
	}
}
