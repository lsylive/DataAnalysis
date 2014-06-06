
package com.liusy.analysis.template.model.node;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.liusy.analysis.template.model.Diagram;
import com.liusy.analysis.template.model.base.Metadata;
import com.liusy.analysis.template.model.dialogProperties.SubDiagramNodeProperties;
import com.liusy.analysis.template.model.vo.DataSet;
import com.liusy.analysis.template.model.vo.DataTable;
import com.liusy.analysis.template.model.vo.FieldConfig;

// Referenced classes of package com.liusy.analysis.template.model.node:
//			Node, INode, IRunAlone

public class SubDiagramNode extends Node<SubDiagramNodeProperties>
implements INode, IRunAlone
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

	public SubDiagramNode()
	{
		this.name = "子查询";
		properties = new SubDiagramNodeProperties();
	}

	  public List<Metadata> getMeta() {
		    return ((SubDiagramNodeProperties)this.properties).getMeta();
		  }

		  public List<Map<String, Object>> run(List<DataSet> inputDataSets) {
		List resultSet = new ArrayList();
		if (runFlag)
		{
			return resultSet;
		} else
		{
			Diagram diagram = ((SubDiagramNodeProperties)properties).getDiagram();
			diagram.setParameterValues(getDiagram().getParameterValues());
			diagram.setDbConnection(getDiagram().getDbConnection());
			resultSet = diagram.run();
			runFlag = true;
			return resultSet;
		}
	}

	public List<FieldConfig> getFieldConfigs()
	{
		return null;
	}

	public DataTable getDataTable()
	{
		return null;
	}

	public DataTable getDataTable(String pkId)
	{
		return null;
	}
}
