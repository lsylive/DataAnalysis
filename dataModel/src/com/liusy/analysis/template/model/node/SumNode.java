
package com.liusy.analysis.template.model.node;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.liusy.analysis.template.model.Consts;
import com.liusy.analysis.template.model.base.Metadata;
import com.liusy.analysis.template.model.dialogProperties.SumNodeProperties;
import com.liusy.analysis.template.model.vo.DataSet;
import com.liusy.analysis.template.model.vo.FieldConfig;
import com.liusy.analysis.template.model.vo.SumConfig;

// Referenced classes of package com.liusy.analysis.template.model.node:
//			Node, INode

public class SumNode extends Node<SumNodeProperties>
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

	public SumNode()
	{
		   this.name = "分组统计";
		properties = new SumNodeProperties();
	}

	  public List<Metadata> getMeta() {
		    return ((SumNodeProperties)this.properties).getMeta();
		  }

		  public List<Map<String, Object>> run(List<DataSet> inputDataSets) {
		if (runFlag)
			return resultSet;
		String conditionalStr = "";
		ArrayList compStrAry = new ArrayList();
		List sumConfigList = ((SumNodeProperties)properties).getInputFieldList();
		List comparedList = new ArrayList();
		comparedList.addAll(((DataSet)inputDataSets.get(0)).getResultSet());
		for (int i = 0; i < sumConfigList.size(); i++)
		{
			SumConfig sumConfig = (SumConfig)sumConfigList.get(i);
			if (sumConfig.getSumCondition().equals(Consts.YES))
				compStrAry.add(sumConfig.getName());
			else
			if (sumConfig.getSumResult().equals(Consts.YES))
				conditionalStr = sumConfig.getName();
		}

		for (int i = 0; i < comparedList.size() && sumConfigList.size() > 0; i++)
		{
			Map tempMap = (Map)comparedList.get(i);
			boolean same = true;
			int conditionalCount = 1;
			for (int j = i + 1; j < comparedList.size();)
			{
				Map compMap = (Map)comparedList.get(j);
				for (int k = 0; k < compStrAry.size(); k++)
				{
					if (tempMap.get(compStrAry.get(k)).toString().trim().equals(compMap.get(compStrAry.get(k)).toString().trim()))
					{
						same = true;
						continue;
					}
					same = false;
					j++;
					break;
				}

				if (same)
				{
					conditionalCount++;
					comparedList.remove(j);
				}
			}

			tempMap.put(conditionalStr, Integer.valueOf(conditionalCount));
		}

		resultSet = comparedList;
		runFlag = true;
		return resultSet;
	}

	public List<FieldConfig> getFieldConfigs()
	{
		return null;
	}
}
