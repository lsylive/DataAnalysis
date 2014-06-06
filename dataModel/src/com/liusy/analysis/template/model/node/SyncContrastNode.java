
package com.liusy.analysis.template.model.node;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.liusy.analysis.template.model.Consts;
import com.liusy.analysis.template.model.base.Metadata;
import com.liusy.analysis.template.model.dialogProperties.SyncContrastNodeProperties;
import com.liusy.analysis.template.model.vo.DataSet;
import com.liusy.analysis.template.model.vo.FieldConfig;
import com.liusy.analysis.template.model.vo.SyncContrastMetaConfig;

// Referenced classes of package com.liusy.analysis.template.model.node:
//			Node, INode

public class SyncContrastNode extends Node<SyncContrastNodeProperties>
implements INode
{

	private static final long serialVersionUID = 1L;

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

	public SyncContrastNode()
	{
		  this.name = "同步对比";
		properties = new SyncContrastNodeProperties();
	}

	  public List<Metadata> getMeta() {
		    return ((SyncContrastNodeProperties)this.properties).getMeta();
		  }

		  public List<Map<String, Object>> run(List<DataSet> inputDataSets) {
		 
		if (runFlag)
			return resultSet;
		if (inputDataSets == null)
		{
			runFlag = true;
			resultSet = new ArrayList();
			return resultSet;
		}
		List synContrastMetaList = ((SyncContrastNodeProperties)properties).getSynContrastMetaList();
		if (synContrastMetaList == null || synContrastMetaList.size() == 0)
		{
			runFlag = true;
			resultSet = new ArrayList();
			return resultSet;
		}
		List ids = new ArrayList();
		for (Iterator iterator = inputDataSets.iterator(); iterator.hasNext();)
		{
			DataSet t = (DataSet)iterator.next();
			if (t.getResultSet() != null)
				ids.add(t);
		}

		if (ids.size() == 0)
		{
			resultSet = new ArrayList();
			runFlag = true;
			return resultSet;
		}
		List tempResultMap = new ArrayList();
		tempResultMap.addAll(((DataSet)ids.get(0)).getResultSet());
		for (int a = 1; a < ids.size(); a++)
		{
			List target = new ArrayList();
			target.addAll(((DataSet)ids.get(a)).getResultSet());
			tempResultMap = intersectionResultSet(tempResultMap, target);
		}

		runFlag = true;
		resultSet = tempResultMap;
		return resultSet;
	}

		public List<Map<String, Object>> intersectionResultSet(List<Map<String, Object>> tempDataMap, List<Map<String, Object>> comparedDataMap) {
		List intersectionResultSet = new ArrayList();
		List synContrastMetaList = ((SyncContrastNodeProperties)properties).getSynContrastMetaList();
		List syncContrastMetaList = ((SyncContrastNodeProperties)properties).getSyncContrastFieldList();
		if (syncContrastMetaList.size() == 0 || syncContrastMetaList == null)
			return intersectionResultSet;
		if (tempDataMap == null || comparedDataMap == null)
			return intersectionResultSet;
		if (tempDataMap.size() == 0 || comparedDataMap.size() == 0)
			return intersectionResultSet;
		if (tempDataMap.size() != 0 && comparedDataMap.size() != 0)
		{
			for (int i = 0; i < tempDataMap.size(); i++)
			{
				boolean compard = true;
				Map tempRowData = (Map)tempDataMap.get(i);
				for (int k = 0; k < intersectionResultSet.size(); k++)
				{
					Map CommonData = (Map)intersectionResultSet.get(k);
					boolean mark = true;
					Iterator iterator = synContrastMetaList.iterator();
					while (iterator.hasNext()) 
					{
						SyncContrastMetaConfig SyncContrastMeta = (SyncContrastMetaConfig)iterator.next();
						if (!SyncContrastMeta.getIsContrastMeta().equals(Consts.YES) || !syncContrastMetaList.contains(SyncContrastMeta.getName()))
							continue;
						String tempdata = tempRowData.get(SyncContrastMeta.getName()) != null ? tempRowData.get(SyncContrastMeta.getName()).toString().trim() : "";
						String commdata = CommonData.get(SyncContrastMeta.getName()) != null ? CommonData.get(SyncContrastMeta.getName()).toString().trim() : "";
						if (tempdata.equals(commdata))
							continue;
						mark = false;
						break;
					}
					if (!mark)
						continue;
					compard = false;
					intersectionResultSet.add(tempRowData);
					tempDataMap.remove(tempRowData);
					i--;
					break;
				}

				if (compard)
				{
					boolean flag = false;
					for (int j = 0; j < comparedDataMap.size(); j++)
					{
						Map comparedRowData = (Map)comparedDataMap.get(j);
						boolean mark = true;
						Iterator iterator1 = synContrastMetaList.iterator();
						while (iterator1.hasNext()) 
						{
							SyncContrastMetaConfig SyncContrastMeta = (SyncContrastMetaConfig)iterator1.next();
							if (!SyncContrastMeta.getIsContrastMeta().equals(Consts.YES) || !syncContrastMetaList.contains(SyncContrastMeta.getName()))
								continue;
							String tempdata = tempRowData.get(SyncContrastMeta.getName()) != null ? tempRowData.get(SyncContrastMeta.getName()).toString().trim() : "";
							String compareddata = comparedRowData.get(SyncContrastMeta.getName()) != null ? comparedRowData.get(SyncContrastMeta.getName()).toString().trim() : "";
							if (tempdata.equals(compareddata))
								continue;
							mark = false;
							break;
						}
						if (mark)
						{
							intersectionResultSet.add(comparedRowData);
							comparedDataMap.remove(comparedRowData);
							j--;
							flag = true;
						}
					}

					if (flag)
					{
						intersectionResultSet.add(tempRowData);
						tempDataMap.remove(tempRowData);
						i--;
					}
				}
			}

		}
		return intersectionResultSet;
	}
}
