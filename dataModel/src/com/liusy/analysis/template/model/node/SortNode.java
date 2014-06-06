
package com.liusy.analysis.template.model.node;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.liusy.analysis.template.model.Consts;
import com.liusy.analysis.template.model.base.Metadata;
import com.liusy.analysis.template.model.dialogProperties.SortProperties;
import com.liusy.analysis.template.model.vo.DataField;
import com.liusy.analysis.template.model.vo.DataSet;

public class SortNode extends Node<SortProperties>
  implements INode
{

	private String expression;
	private static final long serialVersionUID = 1L;
	private DateFormat dateFormat;

	protected Object clone()
		throws CloneNotSupportedException
	{
		return super.clone();
	}

	public SortNode()
	{
		expression = "";
		dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
	    this.name = "排序";
		properties = new SortProperties();
	}

	public List getMeta()
	{
		return ((SortProperties)properties).getMeta();
	}

	public List run(List inputDataSets)
	{
		if (runFlag)
			return resultSet;
		if (inputDataSets == null)
		{
			runFlag = true;
			resultSet = null;
			return resultSet;
		}
		List source = ((DataSet)inputDataSets.get(0)).getResultSet();
		List fields = new ArrayList();
		for (Iterator iterator = ((SortProperties)properties).getFields().iterator(); iterator.hasNext();)
		{
			DataField d = (DataField)iterator.next();
			if (d.getSortDirect() != null && !d.getSortDirect().equals(Consts.SORTDIRECT_NONE))
				fields.add(d);
		}

		if (fields.size() == 0)
		{
			runFlag = true;
			resultSet = source;
			return resultSet;
		} else
		{
			sort(source, 0, source.size(), fields);
			runFlag = true;
			resultSet = source;
			return resultSet;
		}
	}

	private void changePos(List source, int i, int j)
	{
		if (i == j)
			return;
		int itmp;
		int jtmp;
		if (i < j)
		{
			itmp = i;
			jtmp = j;
		} else
		{
			itmp = j;
			jtmp = i;
		}
		Map tmp = (Map)source.get(itmp);
		source.remove(itmp);
		source.add(itmp, (Map)source.get(jtmp - 1));
		source.remove(jtmp);
		source.add(jtmp, tmp);
	}

	private boolean change(Map obj1, Map obj2, List fields)
	{
		for (int i = 0; i < fields.size(); i++)
		{
			int res = compare(obj1, obj2, ((DataField)fields.get(i)).getName(), ((DataField)fields.get(i)).getDataType());
			if (((DataField)fields.get(i)).getSortDirect().equals(Consts.SORTDIRECT_ASC))
			{
				if (res < 0)
					return true;
				if (res > 0)
					return false;
			} else
			{
				if (res < 0)
					return false;
				if (res > 0)
					return true;
			}
		}

		return false;
	}

	private int compare(Map obj1, Map obj2, String fieldName, String dataType)
	{
		Object v1 = obj1.get(fieldName);
		Object v2 = obj2.get(fieldName);
		if (v1 == null && v2 == null)
			return 0;
		if (v1 == null)
			return -1;
		if (v2 == null)
			return 1;
		Double d1;
		Double d2;
		if (dataType.equals(Consts.DATATYPE_NUMERIC))
		{
			try
			{
				d1 = Double.valueOf(Double.parseDouble(v1.toString()));
			}
			catch (NumberFormatException nfe)
			{
				d1 = null;
			}
			try
			{
				d2 = Double.valueOf(Double.parseDouble(v2.toString()));
			}
			catch (NumberFormatException nfe)
			{
				d2 = null;
			}
			if (d1 == null && d2 == null)
				return 0;
			if (d1 == null)
				return -1;
			if (d2 == null)
				return 1;
			if (d1.doubleValue() < d2.doubleValue())
				return -1;
			return d1 != d2 ? 1 : 0;
		}
		int res;
		Date d11;
		Date p2;
		if (dataType.equals(Consts.DATATYPE_DATE))
		{
			try
			{
				d11 = dateFormat.parse(v1.toString());
			}
			catch (ParseException pe)
			{
				d11 = null;
			}
			try
			{
				p2 = dateFormat.parse(v2.toString());
			}
			catch (ParseException pe)
			{
				p2 = null;
			}
			if (d11 == null && p2 == null)
				return 0;
			if (d11 == null)
				return -1;
			if (p2 == null)
				return 1;
			res = d11.compareTo(p2);
			if (res < 0)
				return -1;
			return res != 0 ? 1 : 0;
		}
		res = v1.toString().compareToIgnoreCase( v2.toString());
		if (res < 0)
			return -1;
		return res != 0 ? 1 : 0;
	}

	public void sort(List source, int left, int right, List fields)
	{
		int i = left;
		int j = right;
		Map middle = (Map)source.get(left);
		do
		{
			while (++i < right - 1) 
			{
				Map tmp = (Map)source.get(i);
				if (!change(tmp, middle, fields))
					break;
			}
			while (--j > left) 
			{
				Map tmp = (Map)source.get(j);
				if (change(tmp, middle, fields))
					break;
			}
			if (i >= j)
				break;
			changePos(source, i, j);
		} while (true);
		changePos(source, left, j);
		if (left < j)
			sort(source, left, j, fields);
		if (right > i)
			sort(source, i, right, fields);
	}

	public String getExtraConfigs()
	{
		return null;
	}

	public List getFieldConfigs()
	{
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