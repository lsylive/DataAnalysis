
package com.liusy.analysis.template.model.node;

import java.awt.Point;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import net.sourceforge.jeval.EvaluationException;
import net.sourceforge.jeval.Evaluator;

import org.apache.log4j.Logger;

import com.liusy.analysis.template.model.Consts;
import com.liusy.analysis.template.model.base.Metadata;
import com.liusy.analysis.template.model.dialogProperties.ConditionQueryNodeProperties;
import com.liusy.analysis.template.model.dialogProperties.DataNodeProperties;
import com.liusy.analysis.template.model.util.QueryUtil;
import com.liusy.analysis.template.model.vo.DataField;
import com.liusy.analysis.template.model.vo.DataSet;
import com.liusy.analysis.template.model.vo.FieldConfig;
import com.liusy.analysis.template.model.vo.InputField;

// Referenced classes of package com.liusy.analysis.template.model.node:
//			Node, INode

public class ConditionQueryNode extends Node<ConditionQueryNodeProperties>
implements INode
{
  private static Logger log = Logger.getLogger(ConditionQueryNode.class);
  private static final long serialVersionUID = 1L;

  protected Object clone()
		    throws CloneNotSupportedException
		  {
		    return super.clone();
		  }

  public String getExtraConfigs() {
    return null;
  }

  public ConditionQueryNode()
  {
    this.name = "条件表查询";
    this.properties = new ConditionQueryNodeProperties();
  }

  public List<Metadata> getMeta() {
    return ((ConditionQueryNodeProperties)this.properties).getMeta();
  }

  private boolean valid(Map<String, Object> row, Map<String, String> parameters) {
    String exp = "";

    List<InputField> inputFields = ((ConditionQueryNodeProperties)this.properties).getInputFieldList();
    for (InputField ipt : inputFields) {
      if ((ipt.getExpress() != null) && (!ipt.getExpress().equals("")) && (ipt.getOperator() != null) && (!ipt.getOperator().equals(""))) {
        String fieldName = ipt.getName();
        String fieldValue = (String)row.get(fieldName);
        if ((fieldValue == null) || (fieldValue.equals(""))) return false;
        if (exp.equals("")) exp = "(" + fieldValue + ipt.getOperator() + ipt.getExpress() + ")"; else
          exp = " and (" + fieldValue + ipt.getOperator() + ipt.getExpress() + ")";
      }
    }
    Iterator it = parameters.keySet().iterator();
    while (it.hasNext()) {
      String p = (String)it.next();
      exp = exp.replaceAll("\\{" + p + "\\}", (String)parameters.get(p));
    }

    if (exp.equals("")) return true;
    Evaluator evaluator = new Evaluator();
    try {
      return evaluator.getBooleanResult(exp);
    } catch (EvaluationException e) {
    }
    return false;
  }
  public List<Map<String, Object>> run(List<DataSet> inputDataSets)
  {
    if (this.runFlag) return this.resultSet;
    Map<String, String> parameterValues = getDiagram().getParameterValues();
    if(null!=inputDataSets)
    {
    List<Map<String,Object>> inputSet = ((DataSet)inputDataSets.get(0)).getResultSet();
    List res = new ArrayList();
    for (Map<String,Object> inputRow : inputSet) {
	      if (!valid(inputRow, parameterValues))
	        continue;
	      Map paras = new HashMap();
	      paras.putAll(parameterValues);
	      for (String key : inputRow.keySet()) {
	        Object obj = inputRow.get(key);
	        if (obj == null) continue; paras.put(Consts.ROW_PARAMETER_PRE + key, obj.toString());
	      }
	      String sql = ((ConditionQueryNodeProperties)this.properties).getSQL(paras);
	      if (sql.length() > 0) {
	        List<Map> rs = QueryUtil.query(this.diagram, sql);
	        if ((rs == null) || (rs.size() == 0))
	          continue;
	        if (((ConditionQueryNodeProperties)this.properties).getDatasetType() == ConditionQueryNodeProperties.DATASET_NEW) {
	          List<InputField> inputFields = ((ConditionQueryNodeProperties)this.properties).getInputFieldList();
	          for (Map queryRow : rs) {
	            for (InputField inp : inputFields) {
	              if ((inp.getOutput().equals(Consts.YES)) && (!containField(inp.getName(), ((ConditionQueryNodeProperties)this.properties).getFields()))) {
	                queryRow.put(inp.getName(), inputRow.get(inp.getName()));
	              }
	            }
	          }
	        }
	        res.addAll(rs);
	      }
	    }
	    if (this.resultSet == null) this.resultSet = new ArrayList();
	    if (((ConditionQueryNodeProperties)this.properties).getDatasetType() == ConditionQueryNodeProperties.DATASET_APPEND) this.resultSet.addAll(inputSet);
	    this.resultSet.addAll(res);
    }
    else
    {
        String sql = ((ConditionQueryNodeProperties)this.properties).getSQL(parameterValues);
		try {
			resultSet = QueryUtil.query(diagram, sql);
		} catch (Exception e) {
		e.printStackTrace();
		}
    }


    this.runFlag = true;
    if (this.resultSet != null) log.info("Node '" + this.name + "' 返回记录数：" + this.resultSet.size());
    return this.resultSet;
  }

  private boolean containField(String key, List<DataField> dfs) {
    for (DataField df : dfs) {
      if (key.equals(df.getAliasName())) return true;
    }
    return false;
  }

  public List<FieldConfig> getFieldConfigs() {
    return null;
  }

}