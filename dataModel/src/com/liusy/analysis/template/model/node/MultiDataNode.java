
package com.liusy.analysis.template.model.node;

import java.util.List;
import java.util.Map;

import com.liusy.analysis.template.model.base.Metadata;
import com.liusy.analysis.template.model.dialogProperties.MultiDataNodeProperties;
import com.liusy.analysis.template.model.util.QueryUtil;
import com.liusy.analysis.template.model.vo.DataSet;
import com.liusy.analysis.template.model.vo.FieldConfig;


public class MultiDataNode extends Node<MultiDataNodeProperties>
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

	public MultiDataNode()
	{
		this.name = "多表查询";
		properties = new MultiDataNodeProperties();
	}

	public List<Metadata> getMeta()
	{
		return ((MultiDataNodeProperties)properties).getMeta();
	}

	public List<Map<String,Object>> run(List<DataSet> inputDataSets)
	{
		if (runFlag)
		{
			return resultSet;
		} else
		{
			java.util.Map parameterValues = getDiagram().getParameterValues();
			String sql = ((MultiDataNodeProperties)properties).getSQL(parameterValues);
			try {
				resultSet = QueryUtil.query(diagram, sql);
			} catch (Exception e) {
				e.printStackTrace();
			}
		
			runFlag = true;
			return resultSet;
		}
	}

	public List<FieldConfig> getFieldConfigs()
	{
		return null;
	}
}
