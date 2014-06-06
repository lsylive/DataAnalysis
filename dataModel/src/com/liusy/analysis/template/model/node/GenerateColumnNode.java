
package com.liusy.analysis.template.model.node;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import net.sourceforge.jeval.Evaluator;

import org.apache.log4j.Logger;

import com.liusy.analysis.template.model.Consts;
import com.liusy.analysis.template.model.base.Metadata;
import com.liusy.analysis.template.model.dialogProperties.GenerateColumnProperties;
import com.liusy.analysis.template.model.vo.DataSet;
import com.liusy.analysis.template.model.vo.FieldConfig;
import com.liusy.analysis.template.model.vo.GenerateDataField;


public class GenerateColumnNode extends Node<GenerateColumnProperties>
implements INode
{

	private static Logger log = Logger.getLogger(GenerateColumnNode.class);
	private static final long serialVersionUID = 1L;

	public Object clone()
		throws CloneNotSupportedException
	{
		return super.clone();
	}

	public GenerateColumnNode()
	{
	    this.name = "生成列";
		properties = new GenerateColumnProperties();
	}

	public List<Metadata> getMeta() 
	{
		List<Metadata> list = new ArrayList<Metadata>();
		for (Iterator iterator = ((GenerateColumnProperties)properties).getFields().iterator(); iterator.hasNext();)
		{
			GenerateDataField gdf = (GenerateDataField)iterator.next();
			if (gdf.getVisible().equals(Consts.YES))
				list.add(gdf.getMeta());
		}

		return list;
	}

	public List<Map<String, Object>> run(List inputDataSets)
	{
		if (runFlag)
			return resultSet;
		if (inputDataSets == null)
		{
			runFlag = true;
			resultSet = null;
			return resultSet;
		}
		List<GenerateDataField> metas = ((GenerateColumnProperties)properties).getFields();
		List<Map<String, Object>>  list = ((DataSet)inputDataSets.get(0)).getResultSet();
		Map<String, String> parameters = diagram.getParameterValues();
		Evaluator evaluator = new Evaluator();
		setParameters(metas, parameters);
		for (Iterator iterator = list.iterator(); iterator.hasNext();)
		{
			Map row = (Map)iterator.next();
			for (Iterator iterator1 = metas.iterator(); iterator1.hasNext();)
			{
				GenerateDataField gdf = (GenerateDataField)iterator1.next();
				if (gdf.isGenerate())
				{
					row.put(gdf.getName(), getValue(evaluator, gdf, row));
					System.out.println(row.toString());
				}
			}

			for (Iterator iterator2 = metas.iterator(); iterator2.hasNext();)
			{
				GenerateDataField gdf = (GenerateDataField)iterator2.next();
				if (gdf.getVisible().equals(Consts.NO))
					row.remove(gdf.getName());
			}

		}

		runFlag = true;
		resultSet = list;
		return resultSet;
	}

	private void setParameters(List metas, Map parameters)
	{
		for (Iterator iterator = metas.iterator(); iterator.hasNext();)
		{
			GenerateDataField gdf = (GenerateDataField)iterator.next();
			if (gdf.isGenerate())
			{
				String exp = gdf.getExpression();
				for (Iterator it = parameters.keySet().iterator(); it.hasNext();)
				{
					String key = (String)it.next();
					exp = exp.replaceAll((new StringBuilder("\\{")).append(key).append("\\}").toString(), (String)parameters.get(key));
				}

				gdf.setExpression(exp);
			}
		}

	}

	private String getValue(Evaluator evaluator, GenerateDataField gdf, Map row)
	{
		String exp;
		exp = gdf.getExpression();
		if (exp == null || exp.length() == 0)
			return "";
		for (Iterator it = row.keySet().iterator(); it.hasNext();)
		{
			String key = (String)it.next();
			if (exp.indexOf((new StringBuilder("[")).append(key).append("]").toString()) >= 0)
			{
				Object obj = row.get(key);
				if (obj == null)
					if (gdf.getDataType().equals(Consts.DATATYPE_STRING))
						exp = exp.replaceAll((new StringBuilder("\\[")).append(key).append("\\]").toString(), "''");
					else
						return "";
				String v;
				if (gdf.getDataType().equals(Consts.DATATYPE_NUMERIC))
					v = obj.toString();
				else
					v = (new StringBuilder("'")).append(obj.toString()).append("'").toString();
				exp = exp.replaceAll((new StringBuilder("\\[")).append(key).append("\\]").toString(), v);
			}
		}

		try {
			String res = evaluator.evaluate(exp);
			if (gdf.getDataType().equals(Consts.DATATYPE_NUMERIC))
				return res;
			return res.substring(1, res.length() - 1);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}

	public String getExtraConfigs()
	{
		return null;
	}

	public List<FieldConfig> getFieldConfigs()
	{
		return null;
	}

}
