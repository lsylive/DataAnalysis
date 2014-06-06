
package com.liusy.analysis.template.model.node;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import net.sourceforge.jeval.Evaluator;

import com.liusy.analysis.template.model.Consts;
import com.liusy.analysis.template.model.base.Metadata;
import com.liusy.analysis.template.model.dialogProperties.FilterProperties;
import com.liusy.analysis.template.model.vo.DataSet;
import com.liusy.analysis.template.model.vo.FieldConfig;


public class FilterNode extends Node<FilterProperties>
implements INode
{

	private String expression;
	private static final long serialVersionUID = 1L;

	protected Object clone()
		throws CloneNotSupportedException
	{
		return super.clone();
	}

	public FilterNode()
	{
		expression = "";
		   this.name = "过滤记录";
		properties = new FilterProperties();
	}

	  public List<Metadata> getMeta()
	  {
	    return ((FilterProperties)this.properties).getFields();
	  }

	  public List<Map<String, Object>> run(List<DataSet> inputDataSets) {
	    if (this.runFlag) return this.resultSet;

	    if (inputDataSets == null) {
	      this.runFlag = true;
	      this.resultSet = null;
	      return this.resultSet;
	    }
	    Map metas = getMeta(((FilterProperties)this.properties).getFields());
	    List<Map<String,Object>> source = ((DataSet)inputDataSets.get(0)).getResultSet();
	    List<Map<String,Object>> target = new ArrayList();
	    Map parameters = this.diagram.getParameterValues();
	    Evaluator evaluator = new Evaluator();

	    String exp = setParameters(this.expression, parameters);
	    if ((exp == null) || (exp.length() == 0)) {
	      this.runFlag = true;
	      this.resultSet = source;
	      return this.resultSet;
	    }

	    for (Map<String,Object> row : source) {
	      if (!parsefilter(evaluator, exp, metas, row)) continue; target.add(row);
	    }

	    this.runFlag = true;
	    this.resultSet = target;
	    return this.resultSet;
	  }

	  private boolean parsefilter(Evaluator evaluator, String exp, Map<String, Metadata> metas, Map<String, Object> row) {
	    Iterator it = row.keySet().iterator();
	    while (it.hasNext()) {
	      String key = (String)it.next();
	      if (exp.indexOf("[" + key + "]") < 0)
	        continue;
	      Object obj = row.get(key);
	      Metadata mt = (Metadata)metas.get(key);

	      if ((exp.length() > 6) && (exp.substring(0, 6).equalsIgnoreCase("isnull"))) {
	        return obj == null;
	      }

	      if ((exp.length() > 9) && (exp.substring(0, 9).equalsIgnoreCase("isnotnull"))) {
	        return obj != null;
	      }

	      if (obj == null)
	        if (mt.getDataType().equals(Consts.DATATYPE_STRING)) exp = exp.replaceAll("\\[" + key + "\\]", "''"); else
	          return true;
	      String v;
	      if (mt.getDataType().equals(Consts.DATATYPE_NUMERIC)) v = obj.toString(); else
	        v = "'" + obj.toString() + "'";
	      exp = exp.replaceAll("\\[" + key + "\\]", v);
	    }
	    try {
	      String res = evaluator.evaluate(exp);
	      return !res.equals("0.0");
	    }
	    catch (Exception ee) {
	    }
	    return true;
	  }

	  private String setParameters(String exp, Map<String, String> parameters)
	  {
	    String res = exp;
	    if(null ==parameters)return null;
	    Iterator it = parameters.keySet().iterator();
	    while (it.hasNext()) {
	      String key = (String)it.next();
	      res = res.replaceAll("\\{" + key + "\\}", (String)parameters.get(key));
	    }
	    return res;
	  }

	  public String getExtraConfigs() {
	    return null;
	  }

	  public List<FieldConfig> getFieldConfigs() {
	    return null;
	  }

	  public String getExpression() {
	    return this.expression;
	  }

	  public void setExpression(String expression) {
	    this.expression = expression;
	  }
	}