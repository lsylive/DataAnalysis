
package com.liusy.analysis.template.model.node;

import com.liusy.analysis.template.model.Consts;
import com.liusy.analysis.template.model.Diagram;
import com.liusy.analysis.template.model.base.Metadata;
import com.liusy.analysis.template.model.dialogProperties.DataNodeProperties;
import com.liusy.analysis.template.model.util.QueryUtil;
import com.liusy.analysis.template.model.vo.DataField;
import com.liusy.analysis.template.model.vo.DataTable;
import com.liusy.analysis.template.model.vo.FieldConfig;

import java.util.*;
import org.apache.log4j.Logger;

// Referenced classes of package com.liusy.analysis.template.model.node:
//			Node, INode, IRunAlone

public class DataNode extends Node<DataNodeProperties>
implements INode, IRunAlone
{

	private static Logger log = Logger.getLogger(DataNode.class);
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

	public DataNode()
	{
		 this.name = "表查询";
		properties = new DataNodeProperties();
	}

	public 	List<Metadata> getMeta()
	{
		return ((DataNodeProperties)properties).getMeta();
	}

	public DataTable getDataTable()
	{
		return ((DataNodeProperties)properties).getDataTable();
	}

	public List<Map<String, Object>>  run(List inputDataSets)
	{
		if (runFlag)
			return resultSet;
		Map parameterValues = getDiagram().getParameterValues();
		String sql = ((DataNodeProperties)properties).getSQL(parameterValues);
		try {
			resultSet = QueryUtil.query(diagram, sql);
		} catch (Exception e) {
		e.printStackTrace();
		}
		runFlag = true;
		if (resultSet != null)
			log.info((new StringBuilder("Node '")).append(name).append("返回记录数：").append(resultSet.size()).toString());
		return resultSet;
	}

	public DataTable getDataTable(String pkId)
	{
		DataTable dt = ((DataNodeProperties)properties).getDataTable();
		String sql = (new StringBuilder("select * from ")).append(dt.getName()).append(" where ").append(dt.getPkName()).append("=").toString();
		String dataType = getType(dt.getFields(), dt.getPkName());
		if (dataType == null)
		{
			dt.setRecord(null);
			return dt;
		}
		if (dataType.equals(Consts.DATATYPE_NUMERIC))
			sql = (new StringBuilder(String.valueOf(sql))).append(pkId).toString();
		else
			sql = (new StringBuilder(String.valueOf(sql))).append("'").append(pkId).append("'").toString();
		try {
			resultSet = QueryUtil.query(diagram, sql);
		} catch (Exception e) {
		e.printStackTrace();
		}
		if (resultSet != null && resultSet.size() > 0)
			dt.setRecord((Map)resultSet.get(0));
		else
			dt.setRecord(null);
		return dt;
	}

	private String getType(List<DataField> fields, String fieldName)
	{
		for (Iterator iterator = fields.iterator(); iterator.hasNext();)
		{
			DataField df = (DataField)iterator.next();
			if (df.getName().equalsIgnoreCase(fieldName))
				return df.getDataType();
		}

		return null;
	}

	public List<FieldConfig> getFieldConfigs()
	{
		return null;
	}

}
