package com.liusy.datapp.service.pool;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.liusy.dataapplatform.base.util.ConvertUtil;
import com.liusy.datapp.model.query.QuerySubject;
import com.liusy.datapp.model.query.SubjectColumnCfg;
import com.liusy.datapp.model.query.SubjectTableRelation;
import com.liusy.datapp.model.resource.ResourceTable;
import com.liusy.datapp.service.query.QuerySubjectService;
import com.liusy.datapp.service.query.SubjectColumnCfgService;
import com.liusy.datapp.service.query.SubjectTableRelationService;
import com.liusy.datapp.service.resource.ResourceTableService;
import com.liusy.datapp.util.poolobj.SubjectColumnConfigPoolObj;
import com.liusy.datapp.util.poolobj.SubjectConfigPoolObj;

public class SubjectConfigPool implements Serializable {
	
	private Map<String, List<SubjectColumnConfigPoolObj>> subjectColumnPoolMap = new HashMap<String, List<SubjectColumnConfigPoolObj>>();
	private Map<String,SubjectConfigPoolObj>  subjectPoolMap=new HashMap<String, SubjectConfigPoolObj>();
	

	/**
	 * 获取主题指标配置缓存对象
	 * 
	 * @param subjectId
	 * @return
	 */
	public List<SubjectColumnConfigPoolObj> getSubjectColumnConfigPool(String subjectId,boolean isBatch) {
		List<SubjectColumnConfigPoolObj> retList = null;
		List<SubjectColumnCfg> list = null;
		try {
			//如果是批查则将非批查的字段排除
			if (isBatch) {
				list = subjectColumnCfgService.findAllConfigByOrderForBatch(subjectId);
			}else {
				list = subjectColumnCfgService.findAllConfigByOrder(subjectId);
			}
			if (list != null) {
				retList = new ArrayList<SubjectColumnConfigPoolObj>();
				for (SubjectColumnCfg cfg : list) {
					SubjectColumnConfigPoolObj poolobj = new SubjectColumnConfigPoolObj();
					ConvertUtil.convertToPool(poolobj, cfg);

					retList.add(poolobj);
				}
			}
				subjectColumnPoolMap.put(subjectId, retList);
			
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
			retList = null;
		}
		return retList;
	}
	
	
	public SubjectConfigPoolObj getSubjectConfigPool(String subjectId){
		SubjectConfigPoolObj poolobj=null;
		try{
		if(subjectPoolMap.get(subjectId)!=null)
			poolobj=subjectPoolMap.get(subjectId);
		else{
			QuerySubject subject=querySubjectService.findQuerySubject(Integer.valueOf(subjectId));
			poolobj=new SubjectConfigPoolObj();
			ConvertUtil.convertToPool(poolobj, subject);
			List<SubjectTableRelation> tableList=subjectTableRelationService.findSubjectTableBySubjectId(subjectId);
			List<Map<String,String>> tableMapList=new ArrayList<Map<String,String>>();
			for(SubjectTableRelation relation:tableList){
				Map<String, String> tableMap=new HashMap<String, String>();
				ResourceTable table=resourceTableService.findResourceTable(relation.getDtId());
				tableMap.put("id", relation.getDtId().toString());
				tableMap.put("name", table.getName());
				tableMap.put("cnName", table.getCnName());
				tableMap.put("securityLevel", table.getSecurityLevel());
				tableMapList.add(tableMap);
			}
			poolobj.setTableMapList(tableMapList);
		}
		}catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
		}
		return poolobj;
	}

	/**
	 * 清除主题指标配置缓存对象
	 * 
	 * @param subjectId
	 */
	public void clearSubjectConfigPool(String subjectId) {
		if (subjectColumnPoolMap.get(subjectId) != null)
			subjectColumnPoolMap.put(subjectId, null);
		if(subjectPoolMap.get(subjectId)!=null)
			subjectPoolMap.put(subjectId, null);
	}
	
	private SubjectColumnCfgService subjectColumnCfgService;
	private QuerySubjectService querySubjectService;
	private SubjectTableRelationService subjectTableRelationService;
	private ResourceTableService resourceTableService;

	private final Log logger = LogFactory.getLog(this.getClass());

	public void setSubjectColumnCfgService(
			SubjectColumnCfgService subjectColumnCfgService) {
		this.subjectColumnCfgService = subjectColumnCfgService;
	}

	public void setQuerySubjectService(QuerySubjectService querySubjectService) {
		this.querySubjectService = querySubjectService;
	}

	public void setSubjectTableRelationService(
			SubjectTableRelationService subjectTableRelationService) {
		this.subjectTableRelationService = subjectTableRelationService;
	}

	public void setResourceTableService(ResourceTableService resourceTableService) {
		this.resourceTableService = resourceTableService;
	}

}
