
package com.liusy.analysis.template.model.node;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import net.sourceforge.jeval.Evaluator;

import com.liusy.analysis.template.model.Consts;
import com.liusy.analysis.template.model.base.Metadata;
import com.liusy.analysis.template.model.dialogProperties.PathSearchProperties;
import com.liusy.analysis.template.model.vo.DataSet;
import com.liusy.analysis.template.model.vo.FieldConfig;
import com.liusy.analysis.template.model.vo.SearchField;

public class PathSearchNode extends Node<PathSearchProperties>
implements INode
{
	private String expression;
	private static final long serialVersionUID = 1L;
	private String dataType;
	private DateFormat dateFormat;
	private String startField;
	private String endField;

	protected Object clone()
		throws CloneNotSupportedException
	{
		return super.clone();
	}

	public PathSearchNode()
	{
		expression = "";
		dataType = "";
		dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		startField = null;
		endField = null;
	    this.name = "路径搜索算法";
		properties = new PathSearchProperties();
	}

	public List<Metadata> getMeta()
	{
		List<Metadata> mts = new ArrayList<Metadata>();
		SearchField sf;
		for (Iterator iterator = ((PathSearchProperties)properties).getFields().iterator(); iterator.hasNext(); mts.add(sf.getMeta()))
			sf = (SearchField)iterator.next();

		return mts;
	}

	  public List<Map<String, Object>> run(List<DataSet> inputDataSets)
	  {
		if (runFlag)
			return resultSet;
		if (inputDataSets == null)
		{
			runFlag = true;
			resultSet = null;
			return resultSet;
		}
		List<Map<String, Object>> source = ((DataSet)inputDataSets.get(0)).getResultSet();
		List<Map<String, Object>> target = new ArrayList<Map<String, Object>>();
		Map parameters = diagram.getParameterValues();
		int deepLen = ((PathSearchProperties)properties).getSearchDepth();
		dataType = getDataType();
		for (Iterator iterator = ((PathSearchProperties)properties).getFields().iterator(); iterator.hasNext();)
		{
			SearchField sf = (SearchField)iterator.next();
			if (sf.getSearchSequence().equals(Consts.SEARCH_START))
				startField = sf.getName();
			if (sf.getSearchSequence().equals(Consts.SEARCH_END))
				endField = sf.getName();
		}

		if (startField == null || endField == null)
		{
			runFlag = true;
			resultSet = null;
			return resultSet;
		}
		String startvalue = eval(((PathSearchProperties)properties).getStartValue(), parameters);
		String endvalue = eval(((PathSearchProperties)properties).getEndValue(), parameters);
		if (startvalue == null || endvalue == null)
		{
			runFlag = true;
			resultSet = null;
			return resultSet;
		}
		List target1 = new ArrayList();
		for (Iterator iterator1 = source.iterator(); iterator1.hasNext();)
		{
			Map row = (Map)iterator1.next();
			if (containValue(row, startField, startvalue))
				if (containValue(row, endField, endvalue))
				{
					target1.add(row);
				} else
				{
					List nextrows = getRow(source, row, deepLen, endvalue);
					if (nextrows != null && nextrows.size() > 0)
					{
						target1.add(row);
						target1.addAll(nextrows);
					}
				}
		}

		for (Iterator iterator2 = target1.iterator(); iterator2.hasNext();)
		{
			Map s = (Map)iterator2.next();
			boolean res = false;
			Object ss = s.get(startField);
			Object se = s.get(endField);
			for (Iterator iterator3 = target.iterator(); iterator3.hasNext();)
			{
				Map t = (Map)iterator3.next();
				if (containValue(t, startField, ss) && containValue(t, endField, se))
				{
					res = true;
					break;
				}
			}

			if (!res)
				target.add(s);
		}

		runFlag = true;
		resultSet = target;
		return resultSet;
	}

	  private List<Map<String, Object>> getRow(List<Map<String, Object>> source, Map<String, Object> input, int deepLen, String endvalue) {
		List<Map<String, Object>> nextrows = new ArrayList<Map<String, Object>>();
		int len = deepLen - 1;
		Object startvalue = input.get(endField);
		Object nextvalue = null;
		for (Iterator iterator = source.iterator(); iterator.hasNext();)
		{
			Map<String, Object> row = (Map)iterator.next();
			if (containValue(row, startField, startvalue))
			{
				nextvalue = row.get(endField);
				if (equalValue(nextvalue, endvalue))
				{
					nextrows.add(row);
				} else
				{
					if (len == 0)
						return nextrows;
					List nrows = getRow(source, row, len, endvalue);
					if (nrows != null && nrows.size() > 0)
					{
						nextrows.add(row);
						nextrows.addAll(nrows);
					}
				}
			}
		}

		return nextrows;
	}

	private boolean equalValue(Object value1, Object value2)
	{
		if (value1 == null || value2 == null)
			return false;
		double nub;
		double val;
		try {
			nub = Double.parseDouble(value1.toString());
			val = Double.parseDouble(value2.toString());
			return nub == val;
		} catch (Exception e) {
		}
		Date dat;
		Date dal;
		try {
		dat = dateFormat.parse(value1.toString());
		dal = dateFormat.parse(value2.toString());
		return dat.equals(dal);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return value1.toString().equals(value2.toString());
	}

	private String getDataType()
	{
		String dt1 = null;
		String dt2 = null;
		for (Iterator iterator = ((PathSearchProperties)properties).getFields().iterator(); iterator.hasNext();)
		{
			SearchField sf = (SearchField)iterator.next();
			if (sf.getSearchSequence().equals(Consts.SEARCH_START))
				dt1 = sf.getDataType();
			if (sf.getSearchSequence().equals(Consts.SEARCH_END))
				dt2 = sf.getDataType();
		}

		if (dt1 != null)
		{
			if (dt2 == null)
				return dt1;
			if (dt1.equals(dt2))
				return dt1;
			else
				return Consts.DATATYPE_STRING;
		}
		if (dt2 != null)
			return dt2;
		else
			return Consts.DATATYPE_STRING;
	}
	  private String eval(String expression, Map<String, String> parameters) {
		String value;
		String express;
		Evaluator evaluator;
		value = null;
		express = expression;
		for (Iterator it = parameters.keySet().iterator(); it.hasNext();)
		{
			String key = (String)it.next();
			express = express.replaceAll((new StringBuilder("\\{")).append(key).append("\\}").toString(), (String)parameters.get(key));
		}
		try {
			evaluator = new Evaluator();
			value = evaluator.evaluate(express, false, false);
			return value;
		} catch (Exception e) {
			e.printStackTrace();
		}
				
		return null;
	}

	  private void addRow(List<Map<String, Object>> target, Map<String, Object> value)
	  {
		boolean res = true;
		for (Iterator iterator = target.iterator(); iterator.hasNext();)
		{
			Map row = (Map)iterator.next();
			if (value.equals(row))
			{
				res = false;
				break;
			}
		}

		if (res)
			target.add(value);
	}

	private boolean containValue(Map row, String fieldName, Object value)
	{
		Object obj;
		obj = row.get(fieldName);
		if (obj == null || value == null)
			return false;
		double nub;
		double val;
		try {
			nub = Double.parseDouble(obj.toString());
			val = Double.parseDouble(value.toString());
			return nub == val;
		} catch (Exception e) {
			e.printStackTrace();
		}

		Date dat;
		Date dal;
		try {
			dat = dateFormat.parse(obj.toString());
			dal = dateFormat.parse(value.toString());
			return dat.equals(dal);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return obj.toString().equals(value.toString());
	}

	public String getExtraConfigs()
	{
		return null;
	}

	  public List<FieldConfig> getFieldConfigs() {
		    return null;
		  }

	public String getExpression()
	{
		return expression;
	}

	public void setExpression(String expression)
	{
		this.expression = expression;
	}
}
