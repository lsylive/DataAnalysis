
package com.liusy.analysis.template.model.dialogProperties;

import com.liusy.analysis.template.model.Consts;
import com.liusy.analysis.template.model.base.Metadata;
import com.liusy.analysis.template.model.util.ExpressUtil;
import com.liusy.analysis.template.model.vo.*;
import java.io.PrintStream;
import java.io.Serializable;
import java.util.*;

// Referenced classes of package com.liusy.analysis.template.model.dialogProperties:
//			DataNodeProperties

public class DetailQueryInterfaceProperties
	implements Cloneable, Serializable
{

	private static final long serialVersionUID = 1L;
	private String tableName;
	private String name;
	private String additionSql;
	private List<DataField> fields;
	private List<Filter> filters;
	private List<FieldConfig> fieldConfigs;
	private DataTable dataTable;

	public DetailQueryInterfaceProperties()
	{
		tableName = "";
		name = "";
		additionSql = "";
		fields = new ArrayList();
		filters = new ArrayList();
		fieldConfigs = new ArrayList();
	}

	public String getTableName()
	{
		return tableName;
	}

	public void setTableName(String tableName)
	{
		this.tableName = tableName;
	}

	public List<DataField> getFields()
	{
		return fields;
	}

	public void setFields(List fields)
	{
		this.fields = fields;
	}

	public List getMeta()
	{
		List metas = new ArrayList();
		for (Iterator iterator = fields.iterator(); iterator.hasNext();)
		{
			DataField df = (DataField)iterator.next();
			if (df.getOutput().equals(Consts.YES))
			{
				Metadata mt = new Metadata();
				mt.setCnName(df.getCnName());
				mt.setDataType(df.getDataType());
				mt.setDescription(df.getDescription());
				mt.setId(df.getId());
				mt.setLength(df.getLength());
				mt.setName(df.getAliasName());
				mt.setPrecision(df.getPrecision());
				metas.add(mt);
			}
		}

		return metas;
	}

	public String getSQL(Map parameters)
	{
		StringBuffer sql = new StringBuffer("");
		StringBuffer order = new StringBuffer("");
		StringBuffer aggregate = new StringBuffer("");
		List aggregateList = new ArrayList();
		List orderList = new ArrayList();
		List fieldNameList = new ArrayList();
		DataField df= null;
		for (Iterator iterator = fields.iterator(); iterator.hasNext(); fieldNameList.add(df.getName()))
			df = (DataField)iterator.next();

		for (Iterator iterator1 = fields.iterator(); iterator1.hasNext();)
		{
			df = (DataField)iterator1.next();
			if (!df.getOutput().equals(Consts.NO))
			{
				if (sql.length() == 0)
					sql.append("select ");
				else
					sql.append("      ,");
				if (df.getAggregate().length() > 0)
				{
					sql.append((new StringBuilder(String.valueOf(df.getAggregate()))).append("(t.").append(df.getName()).append(") as ").append(df.getAliasName()).append("\r\n").toString());
				} else
				{
					order = ExpressUtil.parseExpress(order, df);
					sql.append((new StringBuilder(String.valueOf(order.toString()))).append(" as ").append(df.getAliasName()).append("\r\n").toString());
					order.delete(0, order.length());
				}
				if (df.getAggregateNo() != null && df.getAggregateNo().length() > 0)
					if (aggregateList.size() == 0)
					{
						aggregateList.add(df);
					} else
					{
						int i;
						for (i = 0; i < aggregateList.size(); i++)
						{
							DataField d = (DataField)aggregateList.get(i);
							if (Integer.valueOf(d.getAggregateNo()).intValue() < Integer.valueOf(df.getAggregateNo()).intValue())
								break;
						}

						aggregateList.add(i, df);
					}
				if (df.getSortNo().length() > 0)
					if (orderList.size() == 0)
					{
						orderList.add(df);
					} else
					{
						int i;
						for (i = 0; i < orderList.size(); i++)
						{
							DataField d = (DataField)orderList.get(i);
							if (Integer.valueOf(d.getSortNo()).intValue() < Integer.valueOf(df.getSortNo()).intValue())
								break;
						}

						orderList.add(i, df);
					}
			}
		}

		for (int i = aggregateList.size() - 1; i >= 0; i--)
		{
			DataField d = (DataField)aggregateList.get(i);
			if (i == aggregateList.size() - 1)
				aggregate.append("group by ");
			else
				aggregate.append(",");
			aggregate = ExpressUtil.parseExpress(aggregate, d);
		}

		for (int i = orderList.size() - 1; i >= 0; i--)
		{
			DataField d = (DataField)orderList.get(i);
			if (i == orderList.size() - 1)
				order.append("order by ");
			else
				order.append(",");
			if (d.getAggregate() != null && d.getAggregate().trim().length() > 0)
				order.append((new StringBuilder(String.valueOf(d.getAggregate()))).append("(t.").append(d.getName()).append(")").toString());
			else
				order = ExpressUtil.parseExpress(order, d);
			order.append((new StringBuilder(" ")).append(d.getSortDirect()).toString());
		}

		if (name.length() > 0)
			sql.append((new StringBuilder("from  ")).append(name).append(" t\r\n").toString());
		StringBuffer sb = new StringBuffer("");
		StringBuffer sbHaving = new StringBuffer("");
		for (Iterator iterator2 = filters.iterator(); iterator2.hasNext();)
		{
			Filter f = (Filter)iterator2.next();
			for (Iterator iterator3 = fields.iterator(); iterator3.hasNext();)
			{
				DataField d = (DataField)iterator3.next();
				if (f.getField().equalsIgnoreCase(d.getAliasName()))
				{
					df = d;
					break;
				}
			}

			if (parameters == null)
			{
				if (df.getAggregate() != null && df.getAggregate().trim().length() > 0)
				{
					if (sbHaving.length() == 0)
						sbHaving.append("having ");
					else
						sbHaving.append(" and ");
					if (f.getOperator().equals(Consts.OPERATOR_BETWEEN))
					{
						sbHaving.append((new StringBuilder("(")).append(df.getAggregate()).append("(t.").append(df.getName()).append(")").toString()).append(" between ");
						String s[] = f.getExpression().split(";");
						sbHaving.append((new StringBuilder(String.valueOf(s[0]))).append(" and ").append(s[1]).append(")").toString());
					} else
					if (f.getOperator().equals(Consts.OPERATOR_IN))
					{
						sbHaving.append((new StringBuilder("(")).append(df.getAggregate()).append("(t.").append(df.getName()).append(")").toString()).append(" in ");
						genInSQL(sbHaving, f.getExpression(), Consts.DATATYPE_NUMERIC);
						sbHaving.append(")");
					} else
					{
						sbHaving.append((new StringBuilder(String.valueOf(df.getAggregate()))).append("(t.").append(df.getName()).append(")").toString()).append(f.getOperator()).append(f.getExpression());
					}
					sbHaving.append("\r\n ");
				} else
				{
					StringBuffer tempSB = new StringBuffer("");
					if (sb.length() == 0)
						sb.append("where ");
					else
						sb.append(" and ");
					if (f.getOperator().equals(Consts.OPERATOR_BETWEEN))
					{
						tempSB = ExpressUtil.parseExpress(tempSB, df);
						sb.append("(").append(tempSB.toString()).append(" between ");
						String s[] = f.getExpression().split(";");
						if (df.getDataType().equals(Consts.DATATYPE_NUMERIC))
							sb.append((new StringBuilder(String.valueOf(s[0]))).append(" and ").append(s[1]).toString());
						else
							sb.append((new StringBuilder("'")).append(s[0]).append("' and '").append(s[1]).append("'").toString());
						sb.append(")");
					} else
					if (f.getOperator().equals(Consts.OPERATOR_IN))
					{
						tempSB = ExpressUtil.parseExpress(tempSB, df);
						sb.append("(").append(tempSB.toString()).append(" in ");
						genInSQL(sb, f.getExpression(), df.getDataType());
						sb.append(")");
					} else
					if (f.getOperator().equals(Consts.OPERATOR_NULL))
					{
						tempSB = ExpressUtil.parseExpress(tempSB, df);
						sb.append("(").append(tempSB.toString()).append(" is null)");
					} else
					if (f.getOperator().equals(Consts.OPERATOR_NOTNULL))
					{
						tempSB = ExpressUtil.parseExpress(tempSB, df);
						sb.append("(").append(tempSB.toString()).append(" is not null)");
					} else
					{
						tempSB = ExpressUtil.parseExpress(tempSB, df);
						sb.append(tempSB.toString()).append(" ").append(f.getOperator()).append(" ");
						if (df.getDataType().equals(Consts.DATATYPE_NUMERIC))
							sb.append(f.getExpression());
						else
							sb.append((new StringBuilder("'")).append(f.getExpression()).append("'").toString());
					}
					sb.append("\r\n ");
				}
			} else
			{
				String str = (new StringBuilder(String.valueOf(f.getExpression()))).toString();
				for (Iterator it = parameters.keySet().iterator(); it.hasNext();)
				{
					String p = (String)it.next();
					str = str.replaceAll((new StringBuilder("\\{")).append(p).append("\\}").toString(), (String)parameters.get(p));
				}

				if (str.length() != 0 && (str.indexOf("{") < 0 || str.indexOf("}") < 0))
					if (df.getAggregate() != null && df.getAggregate().trim().length() > 0)
					{
						if (sbHaving.length() == 0)
							sbHaving.append("having ");
						else
							sbHaving.append(" and ");
						if (f.getOperator().equals(Consts.OPERATOR_BETWEEN))
						{
							sbHaving.append((new StringBuilder("(")).append(df.getAggregate()).append("(t.").append(df.getName()).append(")").toString()).append(" between ");
							String s[] = str.split(";");
							sbHaving.append((new StringBuilder(String.valueOf(s[0]))).append(" and ").append(s[1]).append(")").toString());
						} else
						if (f.getOperator().equals(Consts.OPERATOR_IN))
						{
							sbHaving.append((new StringBuilder("(")).append(df.getAggregate()).append("(t.").append(df.getName()).append(")").toString()).append(" in ");
							genInSQL(sbHaving, str, Consts.DATATYPE_NUMERIC);
							sbHaving.append(")");
						} else
						{
							sbHaving.append((new StringBuilder(String.valueOf(df.getAggregate()))).append("(t.").append(df.getName()).append(")").toString()).append(f.getOperator()).append(str);
						}
						sbHaving.append("\r\n ");
					} else
					{
						StringBuffer tempSB = new StringBuffer("");
						if (sb.length() == 0)
							sb.append("where ");
						else
							sb.append(" and ");
						if (f.getOperator().equals(Consts.OPERATOR_BETWEEN))
						{
							tempSB = ExpressUtil.parseExpress(tempSB, df);
							sb.append("(").append(tempSB.toString()).append(" between ");
							String s[] = str.split(";");
							if (df.getDataType().equals(Consts.DATATYPE_NUMERIC))
								sb.append((new StringBuilder(String.valueOf(s[0]))).append(" and ").append(s[1]).toString());
							else
								sb.append((new StringBuilder("'")).append(s[0]).append("' and '").append(s[1]).append("'").toString());
							sb.append(")");
						} else
						if (f.getOperator().equals(Consts.OPERATOR_IN))
						{
							tempSB = ExpressUtil.parseExpress(tempSB, df);
							sb.append("(").append(tempSB.toString()).append(" in ");
							genInSQL(sb, str, df.getDataType());
							sb.append(")");
						} else
						if (f.getOperator().equals(Consts.OPERATOR_NULL))
						{
							tempSB = ExpressUtil.parseExpress(tempSB, df);
							sb.append("(").append(tempSB.toString()).append(" is null)");
						} else
						if (f.getOperator().equals(Consts.OPERATOR_NOTNULL))
						{
							tempSB = ExpressUtil.parseExpress(tempSB, df);
							sb.append("(").append(tempSB.toString()).append(" is not null)");
						} else
						{
							tempSB = ExpressUtil.parseExpress(tempSB, df);
							sb.append(tempSB.toString()).append(" ").append(f.getOperator()).append(" ");
							if (df.getDataType().equals(Consts.DATATYPE_NUMERIC))
								sb.append(str);
							else
								sb.append((new StringBuilder("'")).append(str).append("'").toString());
						}
						sb.append("\r\n ");
					}
			}
		}

		if (additionSql.length() > 0)
		{
			if (sb.length() == 0)
				sb.append("where ");
			else
				sb.append(" and ");
			sb.append((new StringBuilder(String.valueOf(additionSql))).append("\r\n").toString());
		}
		if (sb.length() > 0)
			sql.append(sb);
		if (aggregate.length() > 0)
			sql.append((new StringBuilder()).append(aggregate).append("\r\n").toString());
		if (sbHaving.length() > 0)
			sql.append(sbHaving);
		if (order.length() > 0)
			sql.append((new StringBuilder()).append(order).append("\r\n").toString());
		System.out.println(sql.toString());
		return sql.toString();
	}

	private void genInSQL(StringBuffer sb, String values, String datatype)
	{
		String s[] = values.split(";");
		StringBuffer sbtmp = new StringBuffer("(");
		String as[];
		int j = (as = s).length;
		for (int i = 0; i < j; i++)
		{
			String str = as[i];
			if (sbtmp.length() > 1)
				sbtmp.append(",");
			if (datatype.equals(Consts.DATATYPE_NUMERIC))
				sbtmp.append(str);
			else
				sbtmp.append((new StringBuilder("'")).append(str).append("'").toString());
		}

		sb.append(sbtmp).append(")");
	}

	public String getAdditionSql()
	{
		return additionSql;
	}

	public void setAdditionSql(String additionSql)
	{
		this.additionSql = additionSql;
	}

	public List<Filter> getFilters()
	{
		return filters;
	}

	public void setFilters(List<Filter> filters)
	{
		this.filters = filters;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public boolean equals(Object obj)
	{
		if (obj == null)
			return false;
		if (this == obj)
			return true;
		if (obj.getClass() != getClass())
			return false;
		DataNodeProperties other = (DataNodeProperties)obj;
		return getName().equals(other.getName()) && getTableName().equals(other.getTableName()) && getAdditionSql().equals(other.getAdditionSql()) && getFields().equals(other.getFields()) && getFilters().equals(other.getFilters());
	}

	public Object clone()
		throws CloneNotSupportedException
	{
		return super.clone();
	}

	public DataTable getDataTable()
	{
		return dataTable;
	}

	public void setDataTable(DataTable dataTable)
	{
		this.dataTable = dataTable;
	}

	public List<FieldConfig> getFieldConfigs()
	{
		return fieldConfigs;
	}

	public void setFieldConfigs(List fieldConfigs)
	{
		this.fieldConfigs = fieldConfigs;
	}
}
