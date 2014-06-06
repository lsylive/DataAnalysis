package com.liusy.datapp.service.compare.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Arrays;

import org.hibernate.criterion.Order;

import zeal.util.GB2Big5;

import com.liusy.core.util.CollectionMapConvert;
import com.liusy.core.util.Const;
import com.liusy.dataapplatform.base.exception.DAOException;
import com.liusy.dataapplatform.base.exception.ServiceException;
import com.liusy.dataapplatform.base.util.BaseSqlGen;
import com.liusy.dataapplatform.base.util.Condition;
import com.liusy.dataapplatform.base.util.ConvertUtil;
import com.liusy.dataapplatform.base.util.ICondition;
import com.liusy.dataapplatform.base.util.PageQuery;
import com.liusy.dataapplatform.base.util.QueryParam;

import com.liusy.datapp.dao.compare.CompareFilterDao;
import com.liusy.datapp.dao.compare.CompareIndicatorDao;
import com.liusy.datapp.dao.compare.CompareInfoDao;
import com.liusy.datapp.dao.compare.CompareRunInfoDao;
import com.liusy.datapp.dao.compare.CompareTableRelationDao;
import com.liusy.datapp.dao.query.SynthesisTempSpaceDao;
import com.liusy.datapp.model.compare.CompareFilter;
import com.liusy.datapp.model.compare.CompareIndicator;
import com.liusy.datapp.model.compare.CompareInfo;
import com.liusy.datapp.model.compare.CompareRunInfo;
import com.liusy.datapp.model.compare.CompareRunThread;
import com.liusy.datapp.model.compare.CompareTableRelation;
import com.liusy.datapp.model.resource.ResourceColumn;
import com.liusy.datapp.service.compare.CompareFilterService;
import com.liusy.datapp.service.compare.CompareInfoService;
import com.liusy.datapp.service.compare.CompareRunInfoService;
import com.liusy.datapp.service.compare.CompareRunThreadService;
import com.liusy.datapp.service.compare.CompareTableRelationService;
import com.liusy.datapp.service.dynamicquery.SynthesisColumnGenService;
import com.liusy.datapp.service.pool.QueryMetaIndPool;
import com.liusy.datapp.service.pool.SubjectConfigPool;
import com.liusy.datapp.service.pool.TableConfigPool;
import com.liusy.datapp.service.query.SynthesisQueryForCompareService;
import com.liusy.datapp.service.query.SynthesisQueryService;
import com.liusy.datapp.service.resource.ResourceColumnService;
import com.liusy.datapp.service.thread.compare.CompareExecTimer;
import com.liusy.datapp.util.poolobj.ColumnPoolObj;
import com.liusy.datapp.util.poolobj.SubjectColumnConfigPoolObj;
import com.liusy.datapp.web.dynamicquery.form.CommSubjectQueryForm;

public class CompareInfoServiceImpl implements CompareInfoService {
	private static final long serialVersionUID = 1L;
	private ResourceColumnService resourColumnService;
	private SynthesisTempSpaceDao synthesisTempSpaceDao; 
	
	public CompareInfo findCompareInfo(Serializable id) throws ServiceException {
		try {
			return compareInfoDao.get(id);
		}
		catch (DAOException e) {
			throw new ServiceException(e);
		}
	}
	

	public List<CompareInfo> findAll() throws ServiceException {
		try{
			List<CompareInfo> list=compareInfoDao.findAll();
			return list;
		}
		catch (DAOException e) {
			throw new ServiceException(e);
		}
		
	}


	public void createCompareInfo(CompareInfo compareInfo) throws ServiceException {
		try {
			compareInfoDao.save(compareInfo);
		}
		catch (DAOException e) {
			throw new ServiceException(e);
		}
	}

	public void updateCompareInfo(CompareInfo compareInfo) throws ServiceException {
		try {
			CompareInfo tmp = compareInfoDao.get(compareInfo.getId());
			ConvertUtil.convertToModelForUpdate(tmp, compareInfo);			
			compareInfoDao.update(tmp);
		}
		catch (DAOException e) {
			throw new ServiceException(e);
		}
		catch (Exception e) {
			// TODO: handle exception
			throw new ServiceException(e);
		}
	}

	public void removeCompareInfo(Serializable id) throws ServiceException {
		try {
			this.compareTableRelationService.removeCompareTableRelationByCompareId(id);
			this.compareFilterService.removeCompareFiltersByCompId(id);
			CompareExecTimer.runningCompareIds.remove(id);
			compareInfoDao.delete(id);
		}
		catch (DAOException e) {
			throw new ServiceException(e);
		}
	}

	public void removeCompareInfos(Serializable[] ids) throws ServiceException {
		try {
			for (Serializable id : ids)
			{
				removeCompareInfo(id);
			}
		}
		catch (Exception e) {
			throw new ServiceException(e);
		}
	}
	public void startCompareInfo(final Serializable[] ids) throws ServiceException {
		try{
			compareInfoDao.changeCompareInfoStatus(ids, Const.TAG_ENABLE);
		}catch (Exception e) {
			throw new ServiceException(e);
		}
	}
	public void stopCompareInfo(final Serializable[] ids) throws ServiceException {
		try{
			compareInfoDao.changeCompareInfoStatus(ids, Const.TAG_DISABLE);
		}catch (Exception e) {
			throw new ServiceException(e);
		}
	}
	public void startRunCompareInfo(final Object ids) throws ServiceException {
		try{
			compareInfoDao.changeCompareInfoRunStatus(ids, Const.TAG_ENABLE);
		}catch (Exception e) {
			throw new ServiceException(e);
		}
	}
	public void stopRunCompareInfo(final Object ids) throws ServiceException {
		try{
			compareInfoDao.changeCompareInfoRunStatus(ids, Const.TAG_DISABLE);
		}catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	public PageQuery queryCompareInfo(PageQuery pageQuery) throws ServiceException {
		try {
			PageQuery  query=compareInfoDao.queryBySelectId(pageQuery);
			pageQuery.setRecordSet(query.getRecordSet());
			pageQuery.setRecordCount(query.getRecordCount());
			return pageQuery;
		}
		catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	
	public List<CompareInfo> getMustStartCompare() throws ServiceException{
		Calendar cal=Calendar.getInstance();
		int curhour=cal.get(Calendar.HOUR_OF_DAY);
		int curminute=cal.get(Calendar.MINUTE);
		int curdate=cal.get(Calendar.DAY_OF_MONTH);
		int curday=cal.get(Calendar.DAY_OF_WEEK) -1;
		int curmonth=cal.get(Calendar.MONTH) + 1;
		
		List<CompareInfo> list=compareInfoDao.findAll();
		List<CompareInfo> retList=new ArrayList<CompareInfo>();
		try{
		for(CompareInfo compare:list){
			//是否自动
			if (compare.getCompareType().equals(CompareInfo.MANUAL)) continue;
			//是否启用
			if(compare.getComapreStatus().equals(Const.TAG_DISABLE)) continue;
			//是否是暂停状态
			if(compare.getRunStatus()!=null && compare.getRunStatus().equals(Const.STATUS_STOPPED)) continue;
			//开始查找
			//每天
			if(compare.getRunCycle().equals(Const.RUN_CYCLE_DAY)){
				if(curhour == compare.getRunHour() && curminute == compare.getRunMinute()){
					retList.add(compare);
				}
			}
			//每周
			else if(compare.getRunCycle().equals(Const.RUN_CYCLE_WEEK)){
				if(curday==compare.getRunDay() && curhour == compare.getRunHour() && curminute == compare.getRunMinute()){
					retList.add(compare);
				}
			}
			//每月
			else if (compare.getRunCycle().equals(Const.RUN_CYCLE_MONTH))
			{
				if (curdate == compare.getRunDate() && curhour == compare.getRunHour() && curminute == compare.getRunMinute())
				{
					retList.add(compare);
				}
			}
			//仅一次
			else if (compare.getRunCycle().equals(Const.RUN_CYCLE_ONCE) && 
					(compare.getRunStatus()==null) || compare.getRunStatus().equals(""))
			{
				if (curhour==compare.getRunHour() && curminute == compare.getRunMinute())
				{
					retList.add(compare);
				}
			}				
		}
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new ServiceException(e);
		}
		return retList;
		
	}
	
	
//	public List<Map<String,String>> getQueryResult(int tableId,List<String> pkidList,String[] mainFieldArray) throws ServiceException{
//		List<Map<String,String>> resultList=new ArrayList<Map<String,String>>();
//		try{
//			Map<String,String> tableMap=tableConfigPool.getTableMap(String.valueOf(tableId));
//			ResourceColumn pkobj=resourceColumnService.findResoucrePKColumn(Integer.valueOf(tableMap.get("id")));
//			List<ColumnPoolObj> columnList=tableConfigPool.getTableColumnPool(String.valueOf(tableId));
//			
//			String querySql = getQuerySql(tableMap, columnList, mainFieldArray,pkobj,pkidList);
//		
//			
//		}catch (Exception e) {
//			e.printStackTrace();
//			throw new ServiceException(e);
//		}
//		return resultList;
//	}
	
	public String getCompareQuerySql(Map<String,String> tablepoolMap,List<ColumnPoolObj> columnpoolList,String[] compareFieldIdArray,ResourceColumn pkobj,Map<String,List<String>> columnIndMap,Collection<CompareFilter> filters) 
	throws Exception{
		
		StringBuffer sqlbuffer=new StringBuffer();
		Collection compareFieldIds = Arrays.asList(compareFieldIdArray);
		for(int i=0;i<columnpoolList.size();i++){
			ColumnPoolObj obj=columnpoolList.get(i);
			if (pkobj!=null && !obj.getName().equalsIgnoreCase(pkobj.getName())
			&& 	compareFieldIds.contains(obj.getId())
			) 	sqlbuffer.append(","+obj.getName());
			List<String> columnNameList=new ArrayList<String>();
			if (obj!=null)
			{
				columnNameList.add(obj.getName());
				columnIndMap.put(obj.getId(), columnNameList);
			}
		}
		
		
		if(sqlbuffer.length()>0){
			String sqlstr="";
			sqlstr = "select "+pkobj.getName()+" as ID"+sqlbuffer.toString()+" from "+tablepoolMap.get("name");
			StringBuffer sbWhere = new StringBuffer();
			if (filters !=null && filters.size() > 0)
			{
				List<QueryParam> paramList = new ArrayList<QueryParam>();
				for(CompareFilter filter:filters)
				{
					int columnId = filter.getColumnId();
					ResourceColumn  column = resourceColumnService.findResourceColumn(new Integer(columnId));
					if (column==null) continue;
					
//					public final static String COLUMN_TYPE_STRING="1";
//					public final static String COLUMN_TYPE_INT="2";
//					public final static String COLUMN_TYPE_DOUBLE="3";
//					public final static String COLUMN_TYPE_LONG="4";
//					public final static String COLUMN_TYPE_DATE="5";
//					public final static String COLUMN_TYPE_TIMESTAMP="6";
//					
//					public static String	META_TYPE_STRING		= "01";
//					public static String	META_TYPE_DATE			= "02";
//					public static String	META_TYPE_NUMERIC		= "03";
					
					String columnDataType = column.getDataType();
					String paramDataType = null;
					switch(Integer.parseInt(String.valueOf(columnDataType.charAt(1))))
					{
					case 1: paramDataType= QueryParam.COLUMN_TYPE_STRING;break;
					case 2: paramDataType= QueryParam.COLUMN_TYPE_DATE;break;
					case 3: paramDataType= QueryParam.COLUMN_TYPE_INT;break;
					default:
					}
					QueryParam  param = 
						new QueryParam(column.getName(),paramDataType,filter.getFilterOperator(),filter.getFilterValue());
					param.setCombineOper(QueryParam.OPER_AND);
					param.setNextoper(null);
					param.setPrevoper(null);
					paramList.add(param);
				}
				sqlstr += (" where 1=1 and " + sybaseSqlGen.getQueryStringPart(paramList));
			}
			
			return sqlstr;
		}
		else
		{
			throw new Exception("没有对应的字段");
		}
	}

	public List<Map<String,String>> queryDataBaseForCompare(boolean isPersonalTable,String[] fieldArray,
			List<String> queryIdList, Map<String, List<String>> columnIndMap,String sqlstr,PageQuery pageQuery) {
		
		List<Map<String,String>> retList=new LinkedList<Map<String,String>>();
		
		//String pageSql = sybaseSqlGen.generatePageSql(sqlstr, pageQuery);
		PageQuery pageQueryRet = null;
		if (!isPersonalTable)
		{
			pageQueryRet = synthesisQueryForCompareService.queryBySqlAndPage(sqlstr,pageQuery);
		}
		else
		{
			pageQueryRet = synthesisTempSpaceDao.queryBySql(sqlstr, pageQuery);
		}
		
		
		if (pageQueryRet == null) return null;
		List<Map<String,String>> resultList= pageQueryRet.getRecordSet();
		if(resultList!=null && !resultList.isEmpty()){
			for(int k=0;k<resultList.size();k++){
				Map<String,String> retMap=new LinkedHashMap<String, String>();
				Map<String,String> resultMap=resultList.get(k);
				queryIdList.add(resultMap.get("ID"));
				for(int j=0;j<fieldArray.length;j++){
					List<String> colNameList=columnIndMap.get(fieldArray[j]);
					StringBuffer nameBuffer=new StringBuffer();
					for(String str:colNameList){
						String value=resultMap.get(str);
						if(value!=null && value.length()>0){
							nameBuffer.append(value.trim()+":");
						}
					}
					if(nameBuffer.length()>0)
						retMap.put(fieldArray[j], nameBuffer.substring(0,nameBuffer.length()-1));
				}
				retList.add(retMap);
			}
		return retList;
		}
		else return null;
	}
	
	public CompareInfo getCompareInfoByRunId(Integer runId)	throws ServiceException {
		try{
			CompareRunInfo runInfo = compareRunInfoDao.get(runId);
			if(runInfo!=null)
			{
				return  this.findCompareInfo(runInfo.getCompId());
			}
		}catch (Exception e) {
			throw new ServiceException(e);
		}
		return null;
}
	
	public boolean hasRunFinished(Serializable id) throws ServiceException {
		List<CompareRunInfo> runInfos = compareRunInfoDao.findByField(CompareRunInfo.PROP_COMP_ID, id);
		if (runInfos != null)
		{
			for(CompareRunInfo runInfo: runInfos)
			{
				Collection<CompareRunThread> threads = compareRunThreadService.getCompareRunThreadsByRunId(runInfo.getId());
				for(CompareRunThread thread : threads)
				{
					if (thread.getThStatus()==null 
							|| thread.getThStatus().equals("")
							|| thread.getThStatus().equals(CompareRunThread.THREAD_RUNNING))
					{
						return false;
					}
				}
			}
		}
		return true;
	}
	


	private CompareInfoDao	compareInfoDao;
	private CompareTableRelationDao compareTableRelationDao;
	private CompareFilterDao compareFilterDao;
	private TableConfigPool tableConfigPool;
	private SynthesisQueryForCompareService synthesisQueryForCompareService;
	private CompareIndicatorDao compareIndicatorDao;
	private QueryMetaIndPool queryMetaIndPool;
	private BaseSqlGen sybaseSqlGen;
	private SynthesisColumnGenService synthesisColumnGenService;
	private ResourceColumnService resourceColumnService;
	private CompareTableRelationService compareTableRelationService;
	private CompareFilterService compareFilterService;
	private CompareRunThreadService compareRunThreadService;
	private CompareRunInfoDao compareRunInfoDao;
	

	/**
	 * 注入DAO
	 * 
	 * @see com.liusy.core.service.CompareInfoService#setCompareInfoDao(CompareInfo
	 *      compareInfoDao)
	 */
	public void setCompareInfoDao(CompareInfoDao compareInfoDao) {
		this.compareInfoDao = compareInfoDao;
	}

	public void setTableConfigPool(TableConfigPool tableConfigPool) {
		this.tableConfigPool = tableConfigPool;
	}



	public void setCompareIndicatorDao(CompareIndicatorDao compareIndicatorDao) {
		this.compareIndicatorDao = compareIndicatorDao;
	}

	public CompareFilterDao getCompareFilterDao() {
		return compareFilterDao;
	}

	public void setCompareFilterDao(CompareFilterDao compareFilterDao) {
		this.compareFilterDao = compareFilterDao;
	}

	public CompareTableRelationService getCompareTableRelationService() {
		return compareTableRelationService;
	}

	public void setCompareTableRelationService(
			CompareTableRelationService compareTableRelationService) {
		this.compareTableRelationService = compareTableRelationService;
	}

	public CompareFilterService getCompareFilterService() {
		return compareFilterService;
	}

	public void setCompareFilterService(CompareFilterService compareFilterService) {
		this.compareFilterService = compareFilterService;
	}

	public void setCompareTableRelationDao(
			CompareTableRelationDao compareTableRelationDao) {
		this.compareTableRelationDao = compareTableRelationDao;
	}

	public void setQueryMetaIndPool(QueryMetaIndPool queryMetaIndPool) {
		this.queryMetaIndPool = queryMetaIndPool;
	}

	public void setSynthesisColumnGenService(
			SynthesisColumnGenService synthesisColumnGenService) {
		this.synthesisColumnGenService = synthesisColumnGenService;
	}

	public void setResourceColumnService(ResourceColumnService resourceColumnService) {
		this.resourceColumnService = resourceColumnService;
	}



	public void setResourColumnService(ResourceColumnService resourColumnService) {
		this.resourColumnService = resourColumnService;
	}

	public void setSybaseSqlGen(BaseSqlGen sybaseSqlGen) {
		this.sybaseSqlGen = sybaseSqlGen;
	}



	public void setCompareRunInfoDao(CompareRunInfoDao compareRunInfoDao) {
		this.compareRunInfoDao = compareRunInfoDao;
	}

	public void setSynthesisQueryForCompareService(
			SynthesisQueryForCompareService synthesisQueryForCompareService) {
		this.synthesisQueryForCompareService = synthesisQueryForCompareService;
	}



	public void setCompareRunThreadService(
			CompareRunThreadService compareRunThreadService) {
		this.compareRunThreadService = compareRunThreadService;
	}


	public void setSynthesisTempSpaceDao(SynthesisTempSpaceDao synthesisTempSpaceDao) {
		this.synthesisTempSpaceDao = synthesisTempSpaceDao;
	}



	

	



}

