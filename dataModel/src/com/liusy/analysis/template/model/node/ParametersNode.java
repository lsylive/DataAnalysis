
package com.liusy.analysis.template.model.node;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.liusy.analysis.template.model.base.Metadata;
import com.liusy.analysis.template.model.dialogProperties.ParametersNodeProperties;
import com.liusy.analysis.template.model.vo.DataSet;
import com.liusy.analysis.template.model.vo.FieldConfig;


public class ParametersNode extends Node<ParametersNodeProperties>
implements INode
{

	private static final long serialVersionUID = 1L;

	protected Object clone()
		throws CloneNotSupportedException
	{
		return super.clone();
	}

	public String getExtraConfigs()
	{
		return null;
	}

	public ParametersNode()
	{
		   this.name = "参数表";
		properties = new ParametersNodeProperties();
	}

	public List<Metadata> getMeta()
	{
		return ((ParametersNodeProperties)properties).getMeta();
	}

	  public List<Map<String, Object>> run(List<DataSet> inputDataSets) {
		List<Map<String, Object>> resultSet = new ArrayList<Map<String, Object>>();
		if (runFlag)
			return resultSet;
		Map parameterValues = getDiagram().getParameterValues();
		if (parameterValues == null || parameterValues.size() == 0)
			return resultSet;
		Map mapValueByIndex = new HashMap();
		mapValueByIndex = filter(parameterValues);
		int a[] = new int[mapValueByIndex.size()];
		int i = 0;
		for (Iterator iterator = mapValueByIndex.entrySet().iterator(); iterator.hasNext();)
		{
			java.util.Map.Entry entry = (java.util.Map.Entry)iterator.next();
			String key = (String)entry.getKey();
			a[i] = Integer.parseInt(key);
			i++;
		}

		Sort(a);
		for (int j = 0; j < a.length; j++)
		{
			String key = String.valueOf(a[j]);
			Map row = (Map)mapValueByIndex.get(key);
			resultSet.add(row);
		}

		List<Metadata> metaList = ((ParametersNodeProperties)properties).getMeta();
		for (Iterator iterator1 = metaList.iterator(); iterator1.hasNext();)
		{
			Metadata meta = (Metadata)iterator1.next();
			String metaName = meta.getName();
			for (Iterator iterator2 = resultSet.iterator(); iterator2.hasNext();)
			{
				Map row = (Map)iterator2.next();
				if (row.get(metaName) == null)
				{
					row.put(metaName, "");
					break;
				}
			}

		}

		runFlag = true;
		return resultSet;
	}

	public static void Sort(int a[])
	{
		for (int i = 0; i < a.length; i++)
		{
			for (int j = i + 1; j < a.length; j++)
				if (a[i] > a[j])
				{
					int temp = a[j];
					a[j] = a[i];
					a[i] = temp;
				}

		}

	}

	  public Map<String, Map<String, Object>> filter(Map<String, String> parameterValues)
	  {
		Map<String, Map<String, Object>> result = new HashMap<String, Map<String, Object>>();
		List<Metadata> metaList = ((ParametersNodeProperties)properties).getMeta();
		for (Iterator iterator = parameterValues.entrySet().iterator(); iterator.hasNext();)
		{
			java.util.Map.Entry entry = (java.util.Map.Entry)iterator.next();
			String key = (String)entry.getKey();
			String index = "0";
			String fieldName = "field";
			if (key.indexOf("_e") > 0 || key.indexOf("_s") > 0)
			{
				int lastUnderLineIndex1 = key.lastIndexOf("_");
				String s1 = key.substring(0, lastUnderLineIndex1);
				if (s1.lastIndexOf("_") >= 0)
				{
					int lastUnderLineIndex2 = s1.lastIndexOf("_");
					index = s1.substring(lastUnderLineIndex2 + 1, s1.length());
					fieldName = s1.substring(0, lastUnderLineIndex2);
					if (key.indexOf("_e") > 0)
						fieldName = (new StringBuilder(String.valueOf(fieldName))).append("_e").toString();
					if (key.indexOf("_s") > 0)
						fieldName = (new StringBuilder(String.valueOf(fieldName))).append("_s").toString();
				}
			} else
			if (key.lastIndexOf("_") > 0)
			{
				int lastUnderLineIndex = key.lastIndexOf("_");
				index = key.substring(lastUnderLineIndex + 1, key.length());
				fieldName = key.substring(0, lastUnderLineIndex);
			}
			for (Iterator iterator1 = metaList.iterator(); iterator1.hasNext();)
			{
				Metadata meta = (Metadata)iterator1.next();
				String metaName = meta.getName();
				if (metaName.indexOf(fieldName) >= 0)
				{
					Object value = entry.getValue() != null ? ((Object) ((String)entry.getValue())) : "";
					if (result.get(index) != null)
					{
						Map source = (Map)result.get(index);
						source.put(fieldName, value);
					} else
					{
						Map map = new HashMap();
						map.put(fieldName, value);
						result.put(index, map);
					}
				}
			}

		}

		return result;
	}

	public int countStr(String source, char regstr)
	{
		int num = 0;
		for (int i = 0; i < source.length(); i++)
		{
			char c = source.charAt(i);
			if (regstr == c)
				num++;
		}

		return num;
	}

	public List<FieldConfig> getFieldConfigs()
	{
		return null;
	}
}
