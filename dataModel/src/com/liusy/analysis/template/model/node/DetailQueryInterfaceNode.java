
package com.liusy.analysis.template.model.node;

import com.liusy.analysis.template.model.Consts;
import com.liusy.analysis.template.model.Diagram;
import com.liusy.analysis.template.model.base.Metadata;
import com.liusy.analysis.template.model.dialogProperties.DetailQueryInterfaceProperties;
import com.liusy.analysis.template.model.util.QueryUtil;
import com.liusy.analysis.template.model.vo.*;
import java.util.*;

// Referenced classes of package com.liusy.analysis.template.model.node:
//			Node, INode, IRunAlone

public class DetailQueryInterfaceNode extends Node<DetailQueryInterfaceProperties>
implements INode, IRunAlone
{

	private static final long serialVersionUID = 1L;

	protected Object clone()
		throws CloneNotSupportedException
	{
		return super.clone();
	}

	public DetailQueryInterfaceNode()
	{
		this.name = "详细信息配置";
		properties = new DetailQueryInterfaceProperties();
	}

	public List<Metadata> getMeta()
	{
		return ((DetailQueryInterfaceProperties)properties).getMeta();
	}

	public DataTable getDataTable()
	{
		return ((DetailQueryInterfaceProperties)properties).getDataTable();
	}

	public List<Map<String,Object>> run(List<DataSet> inputDataSets)
	{
		if (runFlag)
		{
			return resultSet;
		} else
		{
			Map parameterValues = getDiagram().getParameterValues();
			String sql = ((DetailQueryInterfaceProperties)properties).getSQL(parameterValues);
			try {
				resultSet = QueryUtil.query(diagram, sql);
				runFlag = true;
			} catch (Exception e) {
				// TODO: handle exception
			}
			return resultSet;
		}
	}

	public List<FieldConfig> getFieldConfigs()
	{
		return ((DetailQueryInterfaceProperties)properties).getFieldConfigs();
	}

	public String getExtraConfigs()
	{
		return null;
	}

	public DataTable getDataTable(String pkId)
	{
		DataTable dt = ((DetailQueryInterfaceProperties)properties).getDataTable();
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
		{
			Map record = (Map)resultSet.get(0);
			List<FieldConfig> fieldConfigs = ((DetailQueryInterfaceProperties)properties).getFieldConfigs();
			for (Iterator iter = record.keySet().iterator(); iter.hasNext();)
			{
				String colName = (String)iter.next();
				Object value = record.get(colName);
				String strValue;
				if (value == null)
					strValue = "";
				else
					strValue = value.toString();
				for (Iterator iterator = fieldConfigs.iterator(); iterator.hasNext();)
				{
					FieldConfig fieldCfig = (FieldConfig)iterator.next();
					if (colName.equals(fieldCfig.getName()) && !"".equals(fieldCfig.getCodeSet()))
					{
						List codeList = diagram.getCodeSetList();
						for (Iterator iterator1 = codeList.iterator(); iterator1.hasNext();)
						{
							CodeSet cs = (CodeSet)iterator1.next();
							if (cs.getName().equals(fieldCfig.getCodeSet()))
							{
								List codes = cs.getCodes();
								for (Iterator iterator2 = codes.iterator(); iterator2.hasNext();)
								{
									Code code = (Code)iterator2.next();
									if (value.equals(code.getValue()))
									{
										value = code.getKey();
										record.put(colName, value);
										break;
									}
								}

								break;
							}
						}

						break;
					}
				}

			}

			dt.setRecord(record);
		} else
		{
			dt.setRecord(null);
		}
		return dt;
	}

	public String getPkname()
	{
		return ((DetailQueryInterfaceProperties)properties).getDataTable().getPkName();
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
}
