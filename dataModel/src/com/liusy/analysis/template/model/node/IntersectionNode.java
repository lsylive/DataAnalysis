
package com.liusy.analysis.template.model.node;

import com.liusy.analysis.template.model.Diagram;
import com.liusy.analysis.template.model.base.Metadata;
import com.liusy.analysis.template.model.connection.Connection;
import com.liusy.analysis.template.model.vo.DataSet;
import com.liusy.analysis.template.model.vo.FieldConfig;

import java.util.*;

public class IntersectionNode extends Node<Object>
implements INode
{

	private static final long serialVersionUID = 1L;
	 private List<List<Metadata>> incomingMetadata = new ArrayList<List<Metadata>>();
	  private List<Metadata> metas = new ArrayList<Metadata>();

	public String getExtraConfigs()
	{
		return null;
	}

	protected Object clone()
		throws CloneNotSupportedException
	{
		return super.clone();
	}

	public List<FieldConfig>  getFieldConfigs()
	{
		return null;
	}

	public IntersectionNode()
	{
		incomingMetadata = new ArrayList();
		metas = new ArrayList();
		 this.name = "交集转换";
	}

	public List<Metadata> getMeta()
	{
		List<Connection> list = getIncomingConnections();
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

	public List<Map<String, Object>> run(List inputDataSets)
	{
		if (runFlag)
			return resultSet;
		List<Map<String, Object>> resultList = new ArrayList<Map<String, Object>>();
		boolean mark = false;
		if (inputDataSets.size() == 0 || inputDataSets == null)
		{
			runFlag = true;
			resultSet = new ArrayList();
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
		{
			resultSet = resultList;
			runFlag = true;
			return resultSet;
		}
		metas = getMeta();
		List tempResultMap = new ArrayList();
		tempResultMap.addAll(((DataSet)inputDataSets.get(0)).getResultSet());
		for (int a = 1; a < inputDataSets.size(); a++)
		{
			List target = new ArrayList();
			target.addAll(((DataSet)inputDataSets.get(a)).getResultSet());
			tempResultMap = intersectionResultSet(tempResultMap, target);
		}

		resultList = tempResultMap;
		runFlag = true;
		resultSet = resultList;
		return resultSet;
	}

	public List<Map<String, Object>> intersectionResultSet(List tempDataMap, List comparedDataMap)
	{
		List<Map<String, Object>> intersectionResultSet = new ArrayList<Map<String, Object>>();
		if (tempDataMap.size() == 0 || comparedDataMap.size() == 0)
			return intersectionResultSet;
		if (tempDataMap.size() != 0 && comparedDataMap.size() != 0)
		{
			intersectionResultSet = tempDataMap;
			for (int i = 0; i < tempDataMap.size(); i++)
			{
				Map tempRowData = (Map)tempDataMap.get(i);
				for (int j = 0; j < comparedDataMap.size(); j++)
				{
					Map comparedRowData = (Map)comparedDataMap.get(j);
					if (!tempRowData.equals(comparedRowData))
						continue;
					intersectionResultSet.add(comparedRowData);
					tempDataMap.remove(tempRowData);
					comparedDataMap.remove(comparedRowData);
					j--;
					i--;
					break;
				}

			}

		}
		return intersectionResultSet;
	}

	public boolean isSameStructure(List FirstDataFieldList, List SecondDataFieldList)
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
				First.remove(FirstMetadata);
				Second.remove(SecondMetadata);
				break;
			}

			if (j == First.size() - 1)
				return true;
		}

		return true;
	}

	public List<List<Metadata>>  getIncomingMetadatas(List<Integer> nodeIdList)
	{
		List<List<Metadata>>  incomingMetadatas = new ArrayList<List<Metadata>> ();
		for (int i = 0; i < nodeIdList.size(); i++)
		{
			INode node = getDiagram().getNodeById(((Integer)nodeIdList.get(i)).intValue());
			List<Metadata> listMeta = node.getMeta();
			incomingMetadatas.add(listMeta);
		}

		return incomingMetadatas;
	}
}
