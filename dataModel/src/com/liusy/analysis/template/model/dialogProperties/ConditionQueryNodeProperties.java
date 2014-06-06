
package com.liusy.analysis.template.model.dialogProperties;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.liusy.analysis.template.model.Consts;
import com.liusy.analysis.template.model.base.Metadata;
import com.liusy.analysis.template.model.util.ExpressUtil;
import com.liusy.analysis.template.model.vo.DataField;
import com.liusy.analysis.template.model.vo.Filter;
import com.liusy.analysis.template.model.vo.InputField;


public class ConditionQueryNodeProperties
  implements Cloneable, Serializable
{
  public static int DATASET_NEW = 0;
  public static int DATASET_APPEND = 1;
  private static final long serialVersionUID = 1L;
  private String tableName = "";
  private String name = "";
  private String additionSql = "";
  private int datasetType = DATASET_NEW;
  private List<DataField> fields = new ArrayList<DataField>();
  private List<Filter> filters = new ArrayList<Filter>();
  private List<InputField> inputFieldList = new ArrayList<InputField>();

  public String getTableName() {
    return this.tableName;
  }

  public void setTableName(String tableName) {
    this.tableName = tableName;
  }

  public List<DataField> getFields() {
    return this.fields;
  }

  public void setFields(List<DataField> fields) {
    this.fields = fields;
  }

  public List<Metadata> getMeta() {
    List<Metadata>  metas = new ArrayList<Metadata>();

    if (this.datasetType == DATASET_APPEND) {
      for (InputField idf : this.inputFieldList) {
        if (!idf.getOutput().equals(Consts.YES)) continue; metas.add(idf.getMeta());
      }
    }
    else {
      for (InputField idf : this.inputFieldList) {
        if ((!idf.getOutput().equals(Consts.YES)) || (containField(idf.getName(), this.fields))) continue; metas.add(idf.getMeta());
      }
      for (DataField df : this.fields) {
        if (df.getOutput().equals(Consts.YES)) {
          Metadata mt = df.getMeta();
          mt.setName(df.getAliasName());
          metas.add(mt);
        }
      }
    }
    return metas;
  }

  private boolean containField(String key, List<DataField> dfs) {
    for (DataField df : dfs) {
      if (key.equals(df.getAliasName())) return true;
    }
    return false;
  }

  public String getSQL(Map<String, String> parameters)
  {
    StringBuffer sql = new StringBuffer("");
    StringBuffer order = new StringBuffer("");
    StringBuffer aggregate = new StringBuffer("");

    List aggregateList = new ArrayList();
    List orderList = new ArrayList();
    DataField d;
    for (DataField df : this.fields) {
      if (!df.getOutput().equals(Consts.NO)) {
        if (sql.length() == 0) sql.append("select "); else
          sql.append("      ,");
        if (df.getAggregate().length() > 0) {
          sql.append(df.getAggregate() + "(t." + df.getName() + ") as " + df.getAliasName() + "\r\n");
        }
        else {
          order = ExpressUtil.parseExpress(order, df);
          String str = order.toString();
          order.delete(0, order.length());

          if (parameters != null) {
            Iterator its = parameters.keySet().iterator();
            while (its.hasNext()) {
              String p = (String)its.next();
              str = str.replaceAll("\\{" + p + "\\}", (String)parameters.get(p));
            }
          }
          sql.append(str + " as " + df.getAliasName() + "\r\n");
        }

        if ((df.getAggregateNo() != null) && (df.getAggregateNo().length() > 0)) {
          if (aggregateList.size() == 0) { aggregateList.add(df);
          } else
          {
            for (int i = 0; i < aggregateList.size(); i++) {
              d = (DataField)aggregateList.get(i);
              if (Integer.valueOf(d.getAggregateNo()).intValue() < Integer.valueOf(df.getAggregateNo()).intValue()) break;
              aggregateList.add(i, df);
            }
           
          }

        }

        if (df.getSortNo().length() > 0) {
          if (orderList.size() == 0) { orderList.add(df);
          } else
          {
            for (int i = 0; i < orderList.size(); i++) {
              d = (DataField)orderList.get(i);
              if (Integer.valueOf(d.getSortNo()).intValue() < Integer.valueOf(df.getSortNo()).intValue()) break;
              orderList.add(i, df);
            }
          
          }
        }
      }
    }

    for (int i = aggregateList.size() - 1; i >= 0; i--) {
      d = (DataField)aggregateList.get(i);
      if (i == aggregateList.size() - 1) aggregate.append("group by "); else
        aggregate.append(",");
      aggregate = ExpressUtil.parseExpress(aggregate, d);
    }

    for (int i = orderList.size() - 1; i >= 0; i--) {
      d = (DataField)orderList.get(i);
      if (i == orderList.size() - 1) order.append("order by "); else
        order.append(",");
      if ((d.getAggregate() != null) && (d.getAggregate().trim().length() > 0)) {
        order.append(d.getAggregate() + "(t." + d.getName() + ")");
      }
      else {
        order = ExpressUtil.parseExpress(order, d);
      }
      order.append(" " + d.getSortDirect());
    }

    if (this.name.length() > 0) sql.append("from  " + this.name + " t\r\n");

    StringBuffer sb = new StringBuffer("");
    StringBuffer sbHaving = new StringBuffer("");
    for (Filter f : this.filters) {
      DataField df = null;
      for (DataField dd : this.fields) {
        if (f.getField().equalsIgnoreCase(dd.getAliasName())) {
          df = dd;
          break;
        }
      }
      if (parameters == null) {
        if ((df.getAggregate() != null) && (df.getAggregate().trim().length() > 0)) {
          if (sbHaving.length() == 0) sbHaving.append("having "); else
            sbHaving.append(" and ");
          if (f.getOperator().equals(Consts.OPERATOR_BETWEEN)) {
            sbHaving.append("(" + df.getAggregate() + "(t." + df.getName() + ")").append(" between ");
            String[] s = f.getExpression().split(";");
            sbHaving.append(s[0] + " and " + s[1] + ")");
          }
          else if (f.getOperator().equals(Consts.OPERATOR_IN)) {
            sbHaving.append("(" + df.getAggregate() + "(t." + df.getName() + ")").append(" in ");
            genInSQL(sbHaving, f.getExpression(), Consts.DATATYPE_NUMERIC);
            sbHaving.append(")");
          }
          else {
            sbHaving.append(df.getAggregate() + "(t." + df.getName() + ")").append(f.getOperator()).append(f.getExpression());
          }
        }
        else {
          StringBuffer tempSB = new StringBuffer("");
          if (sb.length() == 0) sb.append("where "); else
            sb.append(" and ");
          if (f.getOperator().equals(Consts.OPERATOR_BETWEEN)) {
            tempSB = ExpressUtil.parseExpress(tempSB, df);
            sb.append("(").append(tempSB.toString()).append(" between ");

            String[] s = f.getExpression().split(";");
            if (df.getDataType().equals(Consts.DATATYPE_NUMERIC)) sb.append(s[0] + " and " + s[1]); 
            else if (df.getDataType().equals(Consts.DATATYPE_DATE))
                sb.append("date'" + s[0] + "' and date'" + s[1] + "'");
            else
              sb.append("'" + s[0] + "' and '" + s[1] + "'");
            sb.append(")");
          }
          else if (f.getOperator().equals(Consts.OPERATOR_IN)) {
            tempSB = ExpressUtil.parseExpress(tempSB, df);
            sb.append("(").append(tempSB.toString()).append(" in ");

            genInSQL(sb, f.getExpression(), df.getDataType());
            sb.append(")");
          }
          else {
            tempSB = ExpressUtil.parseExpress(tempSB, df);
            sb.append(tempSB.toString()).append(" ").append(f.getOperator()).append(" ");

            if (df.getDataType().equals(Consts.DATATYPE_NUMERIC)) sb.append(f.getExpression()); else
              sb.append("'" + f.getExpression() + "'");
          }
        }
      }
      else {
        String str = f.getExpression();
        Iterator it = parameters.keySet().iterator();
        while (it.hasNext()) {
          String p = (String)it.next();
          str = str.replaceAll("\\{" + p + "\\}", (String)parameters.get(p));
        }
        if ((str.length() == 0) || (
          (str.indexOf("{") >= 0) && (str.indexOf("}") >= 0)))
          continue;
        if ((df.getAggregate() != null) && (df.getAggregate().trim().length() > 0))
        {
          if (sbHaving.length() == 0) sbHaving.append("having "); else
            sbHaving.append(" and ");
          if (f.getOperator().equals(Consts.OPERATOR_BETWEEN)) {
            sbHaving.append("(" + df.getAggregate() + "(t." + df.getName() + ")").append(" between ");
            String[] s = str.split(";");
            sbHaving.append(s[0] + " and " + s[1] + ")");
          }
          else if (f.getOperator().equals(Consts.OPERATOR_IN)) {
            sbHaving.append("(" + df.getAggregate() + "(t." + df.getName() + ")").append(" in ");
            genInSQL(sbHaving, str, Consts.DATATYPE_NUMERIC);
            sbHaving.append(")");
          }
          else {
            sbHaving.append(df.getAggregate() + "(t." + df.getName() + ")").append(f.getOperator()).append(str);
          }
        }
        else {
          StringBuffer tempSB = new StringBuffer("");

          if (sb.length() == 0) sb.append("where "); else
            sb.append(" and ");
          if (f.getOperator().equals(Consts.OPERATOR_BETWEEN)) {
            tempSB = ExpressUtil.parseExpress(tempSB, df);
            sb.append("(").append(tempSB.toString()).append(" between ");

            String[] s = str.split(";");
            if (df.getDataType().equals(Consts.DATATYPE_NUMERIC)) sb.append(s[0] + " and " + s[1]); 
            else if (df.getDataType().equals(Consts.DATATYPE_DATE))
            	sb.append("date'" + s[0] + "' and date'" + s[1] + "'");
            else 
              sb.append("'" + s[0] + "' and '" + s[1] + "'");
            sb.append(")");
          }
          else if (f.getOperator().equals(Consts.OPERATOR_IN)) {
            tempSB = ExpressUtil.parseExpress(tempSB, df);
            sb.append("(").append(tempSB.toString()).append(" in ");

            genInSQL(sb, str, df.getDataType());
            sb.append(")");
          }
          else {
            tempSB = ExpressUtil.parseExpress(tempSB, df);
            sb.append(tempSB.toString()).append(" ").append(f.getOperator()).append(" ");

            if (df.getDataType().equals(Consts.DATATYPE_NUMERIC)) sb.append(str); else {
              sb.append("'" + str + "'");
            }
          }
        }
      }
    }
    if (this.additionSql.length() > 0) {
      if (sb.length() == 0) sb.append("where "); else
        sb.append(" and ");
      sb.append(this.additionSql + "\r\n");
    }

    if (sb.length() > 0) sql.append(sb + "\r\n");
    if (aggregate.length() > 0) sql.append(aggregate + "\r\n");
    if (sbHaving.length() > 0) sql.append(sbHaving + "\r\n");
    if (order.length() > 0) sql.append(order + "\r\n");
    System.out.println(sql.toString());
    return sql.toString();
  }

  private void genInSQL(StringBuffer sb, String values, String datatype) {
    String[] s = values.split(";");
    StringBuffer sbtmp = new StringBuffer("(");
    for (String str : s) {
      if (sbtmp.length() > 1) sbtmp.append(",");
      if (datatype.equals(Consts.DATATYPE_NUMERIC)) sbtmp.append(str); else
        sbtmp.append("'" + str + "'");
    }
    sb.append(sbtmp).append(")");
  }

  public String getAdditionSql() {
    return this.additionSql;
  }

  public void setAdditionSql(String additionSql) {
    this.additionSql = additionSql;
  }

  public List<Filter> getFilters() {
    return this.filters;
  }

  public void setFilters(List<Filter> filters) {
    this.filters = filters;
  }

  public String getName() {
    return this.name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public boolean equals(Object obj) {
    if (obj == null) return false;
    if (this == obj) return true;
    if (obj.getClass() != getClass()) return false;
    ConditionQueryNodeProperties other = (ConditionQueryNodeProperties)obj;

    return (getName().equals(other.getName())) && (getTableName().equals(other.getTableName())) && (getAdditionSql().equals(other.getAdditionSql())) && 
      (getFields().equals(other.getFields())) && (getFilters().equals(other.getFilters()));
  }

  public Object clone()
    throws CloneNotSupportedException
  {
    return super.clone();
  }

  public List<InputField> getInputFieldList() {
    return this.inputFieldList;
  }

  public void setInputFieldList(List<InputField> inputFieldList) {
    this.inputFieldList = inputFieldList;
  }

  public int getDatasetType() {
    return this.datasetType;
  }

  public void setDatasetType(int datasetType) {
    this.datasetType = datasetType;
  }
}