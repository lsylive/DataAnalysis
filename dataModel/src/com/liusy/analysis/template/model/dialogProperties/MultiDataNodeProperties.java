
package com.liusy.analysis.template.model.dialogProperties;

import com.liusy.analysis.template.model.Consts;
import com.liusy.analysis.template.model.base.Metadata;
import com.liusy.analysis.template.model.util.ExpressUtil;
import com.liusy.analysis.template.model.vo.*;
import java.io.PrintStream;
import java.io.Serializable;
import java.util.*;

public class MultiDataNodeProperties
	implements Cloneable, Serializable
{

	private static final long serialVersionUID = 1L;
	private String additionSql;
	  private List<DataField> dataFieldList;
	  private List<Filter> filterList;
	  private List<MultiTablesDataField> tablesList;
	  private List<MultiTableConnectFilter> connectFilterList;


	public MultiDataNodeProperties()
	{
		additionSql = "";
		dataFieldList = new ArrayList<DataField>();
		filterList = new ArrayList<Filter>();
		tablesList = new ArrayList<MultiTablesDataField>();
		connectFilterList = new ArrayList<MultiTableConnectFilter>();
	}

	public List<Metadata> getMeta()
	{
		List<Metadata> metas = new ArrayList<Metadata>();
		for (Iterator iterator = dataFieldList.iterator(); iterator.hasNext();)
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

	public String[] getAliasTableName(List tablesList)
	{
		String tableAliasName[] = (String[])null;
		if (tablesList != null)
		{
			tableAliasName = new String[tablesList.size()];
			for (int i = 0; i < tablesList.size(); i++)
			{
				MultiTablesDataField a = (MultiTablesDataField)tablesList.get(i);
				String aliasName = a.getTableAliasName();
				tableAliasName[i] = aliasName;
			}

		} else
		{
			tableAliasName = (new String[] {
				""
			});
		}
		return tableAliasName;
	}

	public String getSQL(Map parameters)
	{
		StringBuffer sql = new StringBuffer("");
		StringBuffer order = new StringBuffer("");
		StringBuffer aggregate = new StringBuffer("");
		List aggregateList = new ArrayList();
		List orderList = new ArrayList();
		List fieldNameList = new ArrayList();
		DataField df =null;
		for (Iterator iterator = dataFieldList.iterator(); iterator.hasNext(); fieldNameList.add(df.getName()))
			df = (DataField)iterator.next();

		for (Iterator iterator1 = dataFieldList.iterator(); iterator1.hasNext();)
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
					sql.append((new StringBuilder(String.valueOf(df.getAggregate()))).append("(").append(df.getName()).append(") as ").append(df.getAliasName()).append("\r\n").toString());
				} else
				{
					order = ExpressUtil.parseExpress(order, df, "MultiTable");
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
			aggregate = ExpressUtil.parseExpress(aggregate, d, "MultiTable");
		}

		for (int i = orderList.size() - 1; i >= 0; i--)
		{
			DataField d = (DataField)orderList.get(i);
			if (i == orderList.size() - 1)
				order.append("order by ");
			else
				order.append(",");
			if (d.getAggregate() != null && d.getAggregate().trim().length() > 0)
				order.append((new StringBuilder(String.valueOf(d.getAggregate()))).append("(").append(d.getName()).append(")").toString());
			else
				order = ExpressUtil.parseExpress(order, d, "MultiTable");
			order.append((new StringBuilder(" ")).append(d.getSortDirect()).toString());
		}

		if (tablesList != null && tablesList.size() > 0)
		{
			sql.append("from ");
			if (connectFilterList != null && connectFilterList.size() > 0)
			{
				MultiTableConnectFilter firstconnectFilter = (MultiTableConnectFilter)connectFilterList.get(0);
				String firstFilterTableAliasName = firstconnectFilter.getTableAliasName();
				String firstFilterTableEnName = firstconnectFilter.getTableEnName();
				String connectOperator = firstconnectFilter.getOperator();
				String connectTableEnName = firstconnectFilter.getConnectedEnTableName();
				String connectTableAliasName = firstconnectFilter.getConnectedTableAliasName();
				String connectedConditions = firstconnectFilter.getConnectedConditions().trim();
				sql.append((new StringBuilder(String.valueOf(firstFilterTableEnName))).append(" ").append(firstFilterTableAliasName).append("\r\n").toString());
				if (connectOperator.equals(Consts.CONNECT_IN))
					sql.append("inner join ");
				else
				if (connectOperator.equals(Consts.CONNECT_LEFT))
					sql.append("left join ");
				else
				if (connectOperator.equals(Consts.CONNECT_RIGHT))
					sql.append("right join ");
				sql.append((new StringBuilder(String.valueOf(connectTableEnName))).append(" ").append(connectTableAliasName).toString());
				sql.append((new StringBuilder(" on ")).append(connectedConditions).append("\r\n").toString());
				for (int j = 1; j < connectFilterList.size(); j++)
				{
					MultiTableConnectFilter connectFilter = (MultiTableConnectFilter)connectFilterList.get(j);
					String filterTableAliasName = connectFilter.getConnectedTableAliasName();
					String filterTableEnName = connectFilter.getConnectedEnTableName();
					String filterConnectedConditions = connectFilter.getConnectedConditions().trim();
					String operator = connectFilter.getOperator();
					if (operator.equals(Consts.CONNECT_IN))
						sql.append("inner join ");
					else
					if (operator.equals(Consts.CONNECT_LEFT))
						sql.append("left join ");
					else
					if (operator.equals(Consts.CONNECT_RIGHT))
						sql.append("right join ");
					sql.append((new StringBuilder(String.valueOf(filterTableEnName))).append(" ").append(filterTableAliasName).toString());
					sql.append((new StringBuilder(" on ")).append(filterConnectedConditions).append("\r\n").toString());
				}

			} else
			{
				for (int i = 0; i < tablesList.size(); i++)
				{
					MultiTablesDataField a = (MultiTablesDataField)tablesList.get(i);
					String tableAliasName = a.getTableAliasName();
					String tableName = a.getTableName();
					if (i != tablesList.size() - 1)
						sql.append((new StringBuilder(String.valueOf(tableName))).append(" ").append(tableAliasName).append(",").toString());
					else
						sql.append((new StringBuilder(String.valueOf(tableName))).append(" ").append(tableAliasName).append("\r\n").toString());
				}

			}
		}
		StringBuffer sb = new StringBuffer("");
		StringBuffer sbHaving = new StringBuffer("");
		for (Iterator iterator2 = filterList.iterator(); iterator2.hasNext();)
		{
			Filter f = (Filter)iterator2.next();
			for (Iterator iterator3 = dataFieldList.iterator(); iterator3.hasNext();)
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
						sbHaving.append((new StringBuilder("(")).append(df.getAggregate()).append("(").append(df.getName()).append(")").toString()).append(" between ");
						String s[] = f.getExpression().split(";");
						sbHaving.append((new StringBuilder(String.valueOf(s[0]))).append(" and ").append(s[1]).append(")").toString());
					} else
					if (f.getOperator().equals(Consts.OPERATOR_IN))
					{
						sbHaving.append((new StringBuilder("(")).append(df.getAggregate()).append("(").append(df.getName()).append(")").toString()).append(" in ");
						genInSQL(sbHaving, f.getExpression(), Consts.DATATYPE_NUMERIC);
						sbHaving.append(")");
					} else
					{
						sbHaving.append((new StringBuilder(String.valueOf(df.getAggregate()))).append("(").append(df.getName()).append(")").toString()).append(f.getOperator()).append(f.getExpression());
					}
				} else
				{
					StringBuffer tempSB = new StringBuffer("");
					if (sb.length() == 0)
						sb.append("where ");
					else
						sb.append(" and ");
					if (f.getOperator().equals(Consts.OPERATOR_BETWEEN))
					{
						tempSB = ExpressUtil.parseExpress(tempSB, df, "MultiTable");
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
						tempSB = ExpressUtil.parseExpress(tempSB, df, "MultiTable");
						sb.append("(").append(tempSB.toString()).append(" in ");
						genInSQL(sb, f.getExpression(), df.getDataType());
						sb.append(")");
					} else
					if (f.getOperator().equals(Consts.OPERATOR_NULL))
					{
						tempSB = ExpressUtil.parseExpress(tempSB, df, "MultiTable");
						sb.append("(").append(tempSB.toString()).append(" is null)");
					} else
					if (f.getOperator().equals(Consts.OPERATOR_NOTNULL))
					{
						tempSB = ExpressUtil.parseExpress(tempSB, df, "MultiTable");
						sb.append("(").append(tempSB.toString()).append(" is not null)");
					} else
					{
						tempSB = ExpressUtil.parseExpress(tempSB, df, "MultiTable");
						sb.append(tempSB.toString()).append(" ").append(f.getOperator()).append(" ");
						if (df.getDataType().equals(Consts.DATATYPE_NUMERIC))
							sb.append(f.getExpression());
						else
							sb.append((new StringBuilder("'")).append(f.getExpression()).append("'").toString());
					}
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
							sbHaving.append((new StringBuilder("(")).append(df.getAggregate()).append("(").append(df.getName()).append(")").toString()).append(" between ");
							String s[] = str.split(";");
							sbHaving.append((new StringBuilder(String.valueOf(s[0]))).append(" and ").append(s[1]).append(")").toString());
						} else
						if (f.getOperator().equals(Consts.OPERATOR_IN))
						{
							sbHaving.append((new StringBuilder("(")).append(df.getAggregate()).append("(").append(df.getName()).append(")").toString()).append(" in ");
							genInSQL(sbHaving, str, Consts.DATATYPE_NUMERIC);
							sbHaving.append(")");
						} else
						{
							sbHaving.append((new StringBuilder(String.valueOf(df.getAggregate()))).append("(").append(df.getName()).append(")").toString()).append(f.getOperator()).append(str);
						}
					} else
					{
						StringBuffer tempSB = new StringBuffer("");
						if (sb.length() == 0)
							sb.append("where ");
						else
							sb.append(" and ");
						if (f.getOperator().equals(Consts.OPERATOR_BETWEEN))
						{
							tempSB = ExpressUtil.parseExpress(tempSB, df, "MultiTable");
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
							tempSB = ExpressUtil.parseExpress(tempSB, df, "MultiTable");
							sb.append("(").append(tempSB.toString()).append(" in ");
							genInSQL(sb, str, df.getDataType());
							sb.append(")");
						} else
						if (f.getOperator().equals(Consts.OPERATOR_NULL))
						{
							tempSB = ExpressUtil.parseExpress(tempSB, df, "MultiTable");
							sb.append("(").append(tempSB.toString()).append(" is null)");
						} else
						if (f.getOperator().equals(Consts.OPERATOR_NOTNULL))
						{
							tempSB = ExpressUtil.parseExpress(tempSB, df, "MultiTable");
							sb.append("(").append(tempSB.toString()).append(" is not null)");
						} else
						{
							tempSB = ExpressUtil.parseExpress(tempSB, df, "MultiTable");
							sb.append(tempSB.toString()).append(" ").append(f.getOperator()).append(" ");
							if (df.getDataType().equals(Consts.DATATYPE_NUMERIC))
								sb.append(str);
							else
								sb.append((new StringBuilder("'")).append(str).append("'").toString());
						}
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
			sql.append((new StringBuilder()).append(sb).append("\r\n").toString());
		if (aggregate.length() > 0)
			sql.append((new StringBuilder()).append(aggregate).append("\r\n").toString());
		if (sbHaving.length() > 0)
			sql.append((new StringBuilder()).append(sbHaving).append("\r\n").toString());
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

	public boolean equals(Object obj)
	{
		if (obj == null)
			return false;
		if (this == obj)
			return true;
		if (obj.getClass() != getClass())
			return false;
		MultiDataNodeProperties other = (MultiDataNodeProperties)obj;
		return getAdditionSql().equals(other.getAdditionSql()) && getDataFieldList().equals(other.getDataFieldList()) && getFilterList().equals(other.getFilterList()) && getTablesList().equals(other.getTablesList()) && getConnectFilterList().equals(other.getConnectFilterList());
	}

	public Object clone()
		throws CloneNotSupportedException
	{
		return super.clone();
	}


	public List<DataField> getDataFieldList() {
		return dataFieldList;
	}

	public void setDataFieldList(List<DataField> dataFieldList) {
		this.dataFieldList = dataFieldList;
	}

	public List<Filter> getFilterList() {
		return filterList;
	}

	public void setFilterList(List<Filter> filterList) {
		this.filterList = filterList;
	}

	public List<MultiTablesDataField> getTablesList() {
		return tablesList;
	}

	public void setTablesList(List<MultiTablesDataField> tablesList) {
		this.tablesList = tablesList;
	}

	public List<MultiTableConnectFilter> getConnectFilterList() {
		return connectFilterList;
	}

	public void setConnectFilterList(List<MultiTableConnectFilter> connectFilterList) {
		this.connectFilterList = connectFilterList;
	}

	public static long getSerialVersionUID()
	{
		return 1L;
	}
}
