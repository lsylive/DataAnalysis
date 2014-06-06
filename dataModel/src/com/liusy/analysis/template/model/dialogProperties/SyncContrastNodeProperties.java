
package com.liusy.analysis.template.model.dialogProperties;

import com.liusy.analysis.template.model.Diagram;
import com.liusy.analysis.template.model.base.Metadata;
import com.liusy.analysis.template.model.vo.SyncContrastMetaConfig;
import java.io.Serializable;
import java.util.*;

public class SyncContrastNodeProperties
	implements Cloneable, Serializable
{

	private static final long serialVersionUID = 1L;
	private List<SyncContrastMetaConfig> synContrastMetaList;
	private String contrastField;
	private Diagram diagram;
	private Map<Integer, List<Metadata>> metaMap;


	public SyncContrastNodeProperties()
	{
		synContrastMetaList = new ArrayList<SyncContrastMetaConfig>();
		contrastField = "";
		metaMap = new HashMap<Integer, List<Metadata>>();
	}
	  public List<Metadata> getMeta() {
		List mts = new ArrayList();
		SyncContrastMetaConfig df;
		for (Iterator iterator = synContrastMetaList.iterator(); iterator.hasNext(); mts.add(df))
			df = (SyncContrastMetaConfig)iterator.next();

		return mts;
	}

	  public List<String> getSyncContrastFieldList() {
		List syncContrastFieldList = new ArrayList();
		String cf = "";
		Map params = new HashMap();
		params = diagram.getParameterValues();
		if (contrastField == null || contrastField.length() < 2)
			return syncContrastFieldList;
		for (Iterator iterator = params.entrySet().iterator(); iterator.hasNext();)
		{
			java.util.Map.Entry entry = (java.util.Map.Entry)iterator.next();
			String key = (String)entry.getKey();
			String txt = contrastField.trim().substring(1, contrastField.length() - 1);
			if (txt.equals(key))
			{
				cf = (String)entry.getValue();
				String syncContrastMetaArray[] = cf.split(";");
				for (int i = 0; i < syncContrastMetaArray.length; i++)
					syncContrastFieldList.add(syncContrastMetaArray[i]);

				break;
			}
		}

		return syncContrastFieldList;
	}

	public List<SyncContrastMetaConfig> getSynContrastMetaList()
	{
		return synContrastMetaList;
	}
	  public List<SyncContrastMetaConfig> FecthSynContrastMetaList() {
		List synContrastMetaList = new ArrayList();
		List metaList = new ArrayList();
		List commonMeta = new ArrayList();
		if (metaMap == null || metaMap.size() == 0)
			return synContrastMetaList;
		List meta;
		for (Iterator iterator = metaMap.entrySet().iterator(); iterator.hasNext(); metaList.add(meta))
		{
			java.util.Map.Entry entry = (java.util.Map.Entry)iterator.next();
			meta = (List)entry.getValue();
		}

		List f = (List)metaList.get(0);
		commonMeta.addAll(f);
		for (int i = 1; i < metaList.size(); i++)
		{
			List compared = (List)metaList.get(i);
			commonMeta = FetchCommonMeta(commonMeta, compared);
		}

		SyncContrastMetaConfig SyncContrastMeta;
		for (Iterator iterator1 = commonMeta.iterator(); iterator1.hasNext(); synContrastMetaList.add(SyncContrastMeta))
		{
			Metadata metaa = (Metadata)iterator1.next();
			SyncContrastMeta = new SyncContrastMetaConfig(metaa);
		}

		return synContrastMetaList;
	}

	  public List<Metadata> FetchCommonMeta(List<Metadata> source, List<Metadata> compared)
	  {
		List commonMeta = new ArrayList();
		if (source.size() == 0 || compared.size() == 0)
			return commonMeta;
		for (int j = 0; j < source.size(); j++)
		{
			Metadata sourceMetadata = (Metadata)source.get(j);
			for (int k = 0; k < compared.size(); k++)
			{
				Metadata comparedMetadata = (Metadata)compared.get(k);
				if (sourceMetadata.getDataType().equals(comparedMetadata.getDataType()) && sourceMetadata.getName().equals(comparedMetadata.getName()))
					commonMeta.add(sourceMetadata);
			}

		}

		return commonMeta;
	}
	  public void setSynContrastMetaList(List<SyncContrastMetaConfig> synContrastMetaList) {
		    this.synContrastMetaList = synContrastMetaList;
		  }

		  public static long getSerialVersionUID() {
		    return 1L;
		  }

		  public Map<Integer, List<Metadata>> getMetaMap() {
		    return this.metaMap;
		  }

		  public void setMetaMap(Map<Integer, List<Metadata>> metaMap) {
		    this.metaMap = metaMap;
		  }

		  public String getContrastField()
		  {
		    return this.contrastField;
		  }

		  public void setContrastField(String contrastField) {
		    this.contrastField = contrastField;
		  }

		  public Diagram getDiagram() {
		    return this.diagram;
		  }

		  public void setDiagram(Diagram diagram) {
		    this.diagram = diagram;
		  }
}
