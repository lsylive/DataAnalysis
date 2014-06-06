
package com.liusy.analysis.template.model.node;

import com.liusy.analysis.template.model.Diagram;
import com.liusy.analysis.template.model.base.Metadata;
import com.liusy.analysis.template.model.connection.Connection;
import com.liusy.analysis.template.model.vo.DataSet;
import com.liusy.analysis.template.model.vo.FieldConfig;

import java.util.*;

// Referenced classes of package com.liusy.analysis.template.model.node:
//			Node, INode

public class UnionNode extends Node<Object>
implements INode
{

	private static final long serialVersionUID = 1L;
	 private List<List<Metadata>> incomingMetadata;
	  private List<Metadata> metas;
	  List<String> endMetaName;

	public String getExtraConfigs()
	{
		return null;
	}

	protected Object clone()
		throws CloneNotSupportedException
	{
		return super.clone();
	}

	  public List<FieldConfig> getFieldConfigs()
	  {
	    return null;
	  }

	public UnionNode()
	{
		incomingMetadata = new ArrayList();
		metas = new ArrayList();
		endMetaName = new ArrayList();
	    this.name = "合集转换";
	}

	  public List<Metadata> getMeta() {
		List list = getIncomingConnections();
		List nodeIdList = new ArrayList();
		INode node = null;
		for (int i = 0; i < list.size(); i++)
		{
			node = ((Connection)list.get(i)).getSource();
			nodeIdList.add(Integer.valueOf(node.getId()));
		}

		incomingMetadata = getIncomingMetadatas(nodeIdList);
		if (!isSameMeta())
		{
			return metas;
		} else
		{
			metas = node.getMeta();
			return metas;
		}
	}

	public boolean isSameMeta()
	{
		boolean mark = false;
		List firstMetadataList = new ArrayList();
		firstMetadataList.addAll((List)incomingMetadata.get(0));
		List comparedMetadataList = new ArrayList();
		for (int j = 1; j < incomingMetadata.size(); j++)
		{
			comparedMetadataList.addAll((List)incomingMetadata.get(j));
			mark = isSameStructure(firstMetadataList, comparedMetadataList);
			if (!mark)
				return mark;
		}

		return mark;
	}

	  public List<Map<String, Object>> run(List<DataSet> inputDataSets) {
		if (runFlag)
			return resultSet;
		List resultList = new ArrayList();
		boolean mark = false;
		if (inputDataSets.size() == 0 || inputDataSets == null)
		{
			runFlag = true;
			resultSet = null;
			return resultSet;
		}
		if (inputDataSets.size() == 1)
		{
			resultSet = ((DataSet)inputDataSets.get(0)).getResultSet();
			runFlag = true;
			return resultSet;
		}
		List nodeIdList = new ArrayList();
		for (int i = 0; i < inputDataSets.size(); i++)
			nodeIdList.add(Integer.valueOf(((DataSet)inputDataSets.get(i)).getId()));

		incomingMetadata = null;
		incomingMetadata = getIncomingMetadatas(nodeIdList);
		mark = isSameMeta();
		if (!mark)
			return resultList;
		metas = getMeta();
		List tempResultMap = new ArrayList();
		tempResultMap.addAll(((DataSet)inputDataSets.get(0)).getResultSet());
		for (int a = 1; a < inputDataSets.size(); a++)
		{
			List target = new ArrayList();
			target.addAll(((DataSet)inputDataSets.get(a)).getResultSet());
			tempResultMap = UnionResultSet(tempResultMap, target);
		}

		resultList = tempResultMap;
		resultSet = resultList;
		runFlag = true;
		return resultSet;
	}

	  public List<Map<String, Object>> UnionResultSet(List<Map<String, Object>> tempDataMap, List<Map<String, Object>> comparedDataMap) {
		List unionResultSet = new ArrayList();
		if (tempDataMap.size() == 0 && comparedDataMap.size() == 0)
			return unionResultSet;
		if (tempDataMap.size() == 0 && comparedDataMap.size() != 0)
		{
			unionResultSet = comparedDataMap;
			return unionResultSet;
		}
		if (comparedDataMap.size() == 0 && tempDataMap.size() != 0)
		{
			unionResultSet = tempDataMap;
			return unionResultSet;
		}
		if (tempDataMap.size() != 0 && comparedDataMap.size() != 0)
		{
			unionResultSet.addAll(tempDataMap);
			boolean b = true;
			for (int j = 0; j < comparedDataMap.size(); j++)
			{
				Map comparedRowData = (Map)comparedDataMap.get(j);
				int i;
				for (i = 0; i < tempDataMap.size(); i++)
				{
					Map tempResultSetData = (Map)tempDataMap.get(i);
					if (comparedRowData.equals(tempResultSetData))
					{
						b = true;
						comparedDataMap.remove(comparedRowData);
						j--;
						break;
					}
					b = false;
				}

				if (!b && i == tempDataMap.size())
				{
					unionResultSet.add(comparedRowData);
					comparedDataMap.remove(comparedRowData);
					j--;
				}
			}

		}
		return unionResultSet;
	}

	  public boolean isSameStructure(List<Metadata> FirstDataFieldList, List<Metadata> SecondDataFieldList)
	  {
		List First = FirstDataFieldList;
		List Second = SecondDataFieldList;
		if (First.size() == 0 || Second.size() == 0 || First.size() != Second.size())
			return false;
		for (int j = 0; j < First.size(); j++)
		{
			for (int k = 0; k < Second.size(); k++)
			{
				Metadata FirstMetadata = (Metadata)First.get(j);
				Metadata SecondMetadata = (Metadata)Second.get(k);
				if (FirstMetadata == null || SecondMetadata == null)
					return false;
				if (!FirstMetadata.getDataType().equals(SecondMetadata.getDataType()) || !FirstMetadata.getName().equals(SecondMetadata.getName()))
				{
					if (k == Second.size() - 1)
						return false;
					continue;
				}
				Second.remove(SecondMetadata);
				break;
			}

			if (j == First.size() - 1)
				return true;
		}

		return true;
	}

	  public List<List<Metadata>> getIncomingMetadatas(List<Integer> nodeIdList)
	  {
		List incomingMetadatas = new ArrayList();
		for (int i = 0; i < nodeIdList.size(); i++)
		{
			INode node = getDiagram().getNodeById(((Integer)nodeIdList.get(i)).intValue());
			List listMeta = node.getMeta();
			incomingMetadatas.add(listMeta);
		}

		return incomingMetadatas;
	}
}
