package com.liusy.datapp.service.compare.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.criterion.Order;

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

import com.liusy.datapp.dao.compare.CompareIndicatorDao;
import com.liusy.datapp.dao.compare.CompareMainResultDao;
import com.liusy.datapp.dao.compare.CompareRunInfoDao;
import com.liusy.datapp.dao.compare.CompareSlaveResultDao;
import com.liusy.datapp.model.compare.CompareInfo;
import com.liusy.datapp.model.compare.CompareMainResult;
import com.liusy.datapp.model.compare.CompareRunInfo;
import com.liusy.datapp.model.compare.CompareRunThread;
import com.liusy.datapp.model.compare.CompareSlaveResult;
import com.liusy.datapp.model.resource.ResourceColumn;
import com.liusy.datapp.service.compare.CompareInfoService;
import com.liusy.datapp.service.compare.CompareMainResultService;
import com.liusy.datapp.service.compare.CompareRunInfoService;
import com.liusy.datapp.service.compare.CompareRunThreadService;
import com.liusy.datapp.service.compare.CompareSlaveResultService;
import com.liusy.datapp.service.dynamicquery.SynthesisColumnGenService;
import com.liusy.datapp.service.pool.QueryMetaIndPool;
import com.liusy.datapp.service.pool.TableConfigPool;
import com.liusy.datapp.service.query.SynthesisQueryService;
import com.liusy.datapp.service.resource.ResourceColumnService;
import com.liusy.datapp.service.thread.compare.CompareTaskExecutor;
import com.liusy.datapp.util.poolobj.ColumnPoolObj;

public class CompareRunInfoServiceImpl implements CompareRunInfoService {
	private static final long serialVersionUID = 1L;
	private Log log=LogFactory.getLog(getClass());
	
	public CompareRunInfo findCompareRunInfo(Serializable id) throws ServiceException {
		try {
			return compareRunInfoDao.get(id);
		}
		catch (DAOException e) {
			throw new ServiceException(e);
		}
	}

	public void createCompareRunInfo(CompareRunInfo compareRunInfo) throws ServiceException {
		try {
			compareRunInfoDao.save(compareRunInfo);
		}
		catch (DAOException e) {
			throw new ServiceException(e);
		}
	}

	public void updateCompareRunInfo(CompareRunInfo compareRunInfo) throws ServiceException {
		try {
			CompareRunInfo tmp = compareRunInfoDao.get(compareRunInfo.getId());
			ConvertUtil.convertToModelForUpdate(tmp, compareRunInfo);			
			compareRunInfoDao.update(tmp);
		}
		catch (DAOException e) {
			throw new ServiceException(e);
		}
		catch (Exception e) {
			// TODO: handle exception
			throw new ServiceException(e);
		}
	}

	public void removeCompareRunInfo(Serializable id) throws ServiceException {
		try {
			compareRunThreadService.removeCompareRunThreadByRunId(id);
			compareMainResultService.deleleResultByRunIdAndSlaveTableName(Integer.parseInt(String.valueOf(id)),null);
			compareRunInfoDao.delete(id);
		}
		catch (DAOException e) {
			throw new ServiceException(e);
		}
	}

	public void removeCompareRunInfos(Serializable[] ids) throws ServiceException {
		try {
			for(Serializable id : ids)
			{
				removeCompareRunInfo(id);
			}
		}
		catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	public PageQuery queryCompareRunInfo(PageQuery pageQuery) throws ServiceException {
		try {
			PageQuery  query=compareRunInfoDao.queryBySelectId(pageQuery);
			pageQuery.setRecordSet(query.getRecordSet());
			pageQuery.setRecordCount(query.getRecordCount());
			return pageQuery;
		}
		catch (Exception e) {
			throw new ServiceException(e);
		}
	}


	public boolean executeCompare(Integer runId,Integer pkobjId,Map<String,String> tableMap,List<String> indList,Map<String,String> indValueMap) throws ServiceException{
		boolean processOk=true;
		Date startTime=new Date();
		
		try{
			Map<String,String> tablepoolMap=tableConfigPool.getTableMap(tableMap.get("id"));
			ResourceColumn pkobj=resourceColumnService.findResoucrePKColumn(Integer.valueOf(tableMap.get("id")));
			List<ColumnPoolObj> columnpoolList=tableConfigPool.getTableColumnPool(tableMap.get("id"));
			//初始添加
			
			Map<String,String> queryMap=getQueryParam(tablepoolMap, columnpoolList, indList, indValueMap);
			
			String sqlstr="select "+pkobj.getName()+" as ID from "+tablepoolMap.get("sql");
			List<Map<String,String>> result=synthesisQueryService.queryBySql(sqlstr);
			StringBuffer buffer=new StringBuffer();
			if(result!=null && !result.isEmpty()){
				for(Map<String,String> resultMap:result){
					String idval=resultMap.get("ID");
					buffer.append(idval+",");
				}
			}
			if(buffer.length()>0){
				String resultid=buffer.substring(0,buffer.length()-1);
				//设置结果和完成时间
				CompareSlaveResult slaveResult=new CompareSlaveResult();
				slaveResult.setPkId(pkobjId);
				slaveResult.setRunId(runId);
				slaveResult.setSlaveTableName(tableMap.get("name"));
				slaveResult.setRunTime(startTime);
				slaveResult.setFinishTime(new Date());
				slaveResult.setSlavePk(resultid);
				slaveResult.setStatus(Const.STATUS_COLUMN_FINISH);
				compareSlaveResultService.createCompareSlaveResult(slaveResult);
			}
			
		}catch (Exception e) {
			e.printStackTrace();
			processOk=false;
			//记录失败的
			CompareSlaveResult slaveResult=new CompareSlaveResult();
			slaveResult.setPkId(pkobjId);
			slaveResult.setRunId(runId);
			slaveResult.setSlaveTableName(tableMap.get("name"));
			slaveResult.setRunTime(startTime);
			slaveResult.setFinishTime(new Date());
			slaveResult.setStatus(Const.STATUS_COLUMN_FAILED);
			compareSlaveResultService.createCompareSlaveResult(slaveResult);
		}finally{
			setStatusTagByRunId(runId, pkobjId, Const.STATUS_RUNNING);
		}
		return processOk;
	}
	public void setStatusTagByRunId(Integer runId,Integer pkId,String status) throws ServiceException{
		compareMainResultService.setStatusTagByRunId(runId, pkId, status);
	}
	
//	public void increPMatchCountByRunId(Integer runId,int pMatchCount) throws ServiceException {
//		try {
//			CompareRunInfo runInfo = this.findCompareRunInfo(runId);
//			if (runInfo!=null)
//			{
//				int currCount = runInfo.getPmatchCount();
//				runInfo.setPmatchCount(currCount + pMatchCount);
//				this.updateCompareRunInfo(runInfo);
//			}
//		}
//		catch (Exception e) {
//			throw new ServiceException(e);
//		}
//		
//	}
	
	public Integer computePMatchCountByRunId(Integer runId) throws ServiceException {
		try {
			CompareRunInfo runInfo = findCompareRunInfo(runId);
			if (runInfo!=null)
			{
				int pMatchCount = compareSlaveResultService.getSlaveResultsCountByRunId(runId);
				/*
				List<CompareSlaveResult> results = compareSlaveResultService.getSlaveResultsCountByRunId(runId);
				int pMatchCount = 0;
				for(CompareSlaveResult r : results)
				{
					String slavePks = r.getSlavePk();
					if (slavePks!=null && slavePks.length() > 0)
					{
						pMatchCount += slavePks.split(",").length;
					}
				}
				*/
				runInfo.setPmatchCount(pMatchCount);
				updateCompareRunInfo(runInfo);
				return pMatchCount;
			}
			else
				return 0;
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException(e);
		}

}

	
	private Map<String, String> getQueryParam(Map<String, String> tablepoolMap,
			List<ColumnPoolObj> columnpoolList, List<String> indList,
			Map<String, String> indValueMap) throws Exception {

		// 单个表查询语句
		StringBuffer buffer = new StringBuffer();
		// 字段缓存对象

		CollectionMapConvert<ColumnPoolObj> convert = new CollectionMapConvert<ColumnPoolObj>();
		// 表中指标对应的字段
		Map<String, List<ColumnPoolObj>> map = convert.convertToMapByParentKey(columnpoolList, "indicatorId");

		for (String str : indList) {
			Map<String, String> indMap = queryMetaIndPool.getIndicatorPool(str);
			String indId = str;
			// 指标存在且表中包括该指标
			if (indMap != null && map.get(indId) != null) {
				List<ColumnPoolObj> list = map.get(indId);
				List<QueryParam> paramList = new ArrayList<QueryParam>();
				for (ColumnPoolObj colpool : list) {

					String val = indValueMap.get(indId);

					if (val != null && !"".equals(val)) {
						String[] valArr = val.split(":");
						for (int k = 0; k < valArr.length; k++) {
							paramList.add(new QueryParam(colpool.getName(), synthesisColumnGenService.filterColumnType(colpool),Const.FILTER_OPER_EQUAL, valArr[k], ""));
						}
					}

				}
				String gensql = sybaseSqlGen.getQueryStringPart(paramList,QueryParam.OPER_OR);
				if (gensql != null && !"".equals(gensql.trim()))
					buffer.append("(" + gensql + ")" + QueryParam.OPER_AND);
			}
		}

		Map<String, String> tableMap = new HashMap<String, String>();
		// 获取对应表的查询语句和表名
		tableMap = tablepoolMap;
		String querysql = buffer.length() > 0 ? " and "
				+ buffer.substring(0, buffer.length() - 5) : "";
		tableMap.put("sql", tablepoolMap.get("name") + " where 1=1" + querysql);
		return tableMap;
	}
	
	public void exceJob(Integer compId, Integer runIdParam, 
			Map<String,String> mainTabMapParam,String[] mainFieldArray,List<String> mainPkIdListParam,List<Map<String, String>> mainResultListParam,
			Map<String, String> slaveTableMapParam, String[] slaveFieldArray,
			List<String> slavePkIdListParam,
			List<Map<String, String>> slaveResultListParam) {
		
		final Integer runId = runIdParam;
		final Map<String,String> mainTabMap = mainTabMapParam;
		final List<Map<String, String>> mainResultList = mainResultListParam;
		final List<Map<String, String>> slaveResultList = slaveResultListParam;
		final Map<String, String> slaveTableMap = slaveTableMapParam;
		final List<String> slavePkIdList = slavePkIdListParam;
		final List<String> mainPkIdList = mainPkIdListParam;
		
		
				
				try {
					
					for (int i = 0; i < mainResultList.size(); i++) {
						Integer pMatchCount = new Integer(0);
						Map<String, String> mainResultMap = mainResultList.get(i);
						Collection<String> mainResultValues = mainResultMap.values();
						if (mainResultValues.isEmpty())	continue;

						StringBuffer sbSlaveMatchedIds = new StringBuffer();
						for (int j = 0; j < slaveResultList.size(); j++) {
							Map<String, String> slaveResultMap = slaveResultList.get(j);
							Collection<String> slaveResultValues = slaveResultMap.values();
							if (slaveResultValues.isEmpty()) continue;

							// System.out.println("record: id-" +
							// mainPkIdList.get(i)+ " value-" + mainResultMap
							// + " slaveTableName-" + slaveTableMap.get("name")+
							// "id-" + slavePkIdList.get(j)+ " value-" +
							// slaveResultMap);
							if (Arrays.equals(mainResultValues.toArray(),slaveResultValues.toArray())) {
								log.info("!!match(Thread-"+ Thread.currentThread().getId() +"):mainTableName-"+ mainTabMap.get("name")+  " id-"	+ mainPkIdList.get(i) + " value-"+ mainResultMap 
										+ "  slaveTableName-"+ slaveTableMap.get("name") + " id-"+ slavePkIdList.get(j) + " value-"+ slaveResultMap);
								sbSlaveMatchedIds.append(slavePkIdList.get(j)).append(",");	
								pMatchCount  ++;
								
							}	
						}
						
						//将匹配结果记入数据库
						if (sbSlaveMatchedIds.length() > 0)
						{
							synchronized(this){
								PageQuery pageQuery = new PageQuery();
								pageQuery.setPageCount("0");
								pageQuery.setSelectParamId("FIND_DEF_SLAVE_RESULT");
								Map<String,String> params = new HashMap<String,String>();
								params.put(CompareSlaveResult.PROP_RUN_ID, String.valueOf(runId));
								params.put(CompareSlaveResult.PROP_PK_ID, mainPkIdList.get(i));
								params.put(CompareSlaveResult.PROP_SLAVETABLE_NAME, slaveTableMap.get("name"));
								pageQuery.setParameters(params);
								pageQuery = compareSlaveResultService.queryCompareSlaveResult(pageQuery);
								CompareSlaveResult slaveResult=new CompareSlaveResult();
								
								List<Map<String,String>> results = pageQuery.getRecordSet();
								slaveResult.setPkId(Integer.parseInt(mainPkIdList.get(i)));
								slaveResult.setRunId(runId);
								slaveResult.setSlaveTableName(slaveTableMap.get("name"));
								slaveResult.setStatus(Const.STATUS_COLUMN_FINISH);
								
//								if (results == null || results.size()==0)
//								{
									String slavePkToRecord = sbSlaveMatchedIds.deleteCharAt(sbSlaveMatchedIds.length()-1).toString();
									if (slavePkToRecord.length() > 4000)
									{//记录截取
										slavePkToRecord = slavePkToRecord.substring(0,4000);
										slavePkToRecord = slavePkToRecord.substring(0,slavePkToRecord.lastIndexOf(","));
									}
									slaveResult.setSlavePk(slavePkToRecord);
									compareSlaveResultService.createCompareSlaveResult(slaveResult);
//								}
//								else
//								{
//									Map<String,String> record = results.get(0);
//									String currSlavePk = record.get(CompareSlaveResult.PROP_SLAVE_PK.toUpperCase());
//									slaveResult.setId(Integer.parseInt(record.get("ID")));
//									String slavePkToRecord = currSlavePk + "," + sbSlaveMatchedIds.deleteCharAt(sbSlaveMatchedIds.length()-1).toString();
//									if (slavePkToRecord.length() > 4000)
//									{//记录截取
//										slavePkToRecord = slavePkToRecord.substring(0,4000);
//										slavePkToRecord = slavePkToRecord.substring(0,slavePkToRecord.lastIndexOf(","));
//									}
//									slaveResult.setSlavePk(slavePkToRecord);
//									slaveResult.setCount(currSlavePk.split(",").length);
//									compareSlaveResultService.updateCompareSlaveResult(slaveResult);
//								}
								
								
								
//								compareRunInfoService.increPMatchCountByRunId(runId,pMatchCount);

							}
						}
					}
				} catch (Exception e) {
					e.printStackTrace();

				}

		
	}

	
	private BaseSqlGen sybaseSqlGen;
	private QueryMetaIndPool queryMetaIndPool;
	private TableConfigPool tableConfigPool;
	private SynthesisQueryService synthesisQueryService;
	private SynthesisColumnGenService synthesisColumnGenService;
	private CompareRunInfoDao	compareRunInfoDao;
	private CompareMainResultService compareMainResultService;
	private ResourceColumnService resourceColumnService;
	private CompareSlaveResultService compareSlaveResultService;
	private CompareInfoService compareInfoService;
	private CompareRunThreadService compareRunThreadService;

	/**
	 * 注入DAO
	 * 
	 * @see com.liusy.core.service.CompareRunInfoService#setCompareRunInfoDao(CompareRunInfo
	 *      compareRunInfoDao)
	 */
	public void setCompareRunInfoDao(CompareRunInfoDao compareRunInfoDao) {
		this.compareRunInfoDao = compareRunInfoDao;
	}

	public void setCompareSlaveResultService(
			CompareSlaveResultService compareSlaveResultService) {
		this.compareSlaveResultService = compareSlaveResultService;
	}

	public List<CompareRunInfo> getCompareRunInfoListByCompId(Serializable id)
			throws ServiceException {
		try {
			return this.compareRunInfoDao.findByField(CompareRunInfo.PROP_COMP_ID, id);
		}
		catch (DAOException e) {
			throw new ServiceException(e);
		}
	}

	public void setCompareMainResultService(
			CompareMainResultService compareMainResultService) {
		this.compareMainResultService = compareMainResultService;
	}

	public void stopCompareRunInfos(Serializable[] ids) throws ServiceException {
		try {
			CompareRunInfo compareRunInfo = null;
			for(Serializable id : ids)
			{
				compareRunInfo = this.findCompareRunInfo(id);
				compareRunInfo.setStatus(Const.STATUS_STOPPED);
				this.updateCompareRunInfo(compareRunInfo);
			}
		}
		catch (DAOException e) {
			throw new ServiceException(e);
		}
		
	}
	
	public void resumeCompareRunInfos(Serializable[] ids) throws ServiceException {
		try {
			CompareRunInfo compareRunInfo = null;
			for(Serializable id : ids)
			{
				compareRunInfo = this.findCompareRunInfo(id);
				compareRunInfo.setStatus(Const.STATUS_RUNNING);
				compareRunInfo.setFinishTime(null);
				this.updateCompareRunInfo(compareRunInfo);
				
				CompareInfo compareInfo = compareInfoService.findCompareInfo(compareRunInfo.getCompId());
				compareInfo.setComapreStatus(Const.STATUS_RUNNING);
				compareInfo.setEndTime(null);
				compareInfoService.updateCompareInfo(compareInfo);
				//compareTaskExecutor.execCompare(compareInfo,compareRunInfo);
			}
		}
		catch (DAOException e) {
			throw new ServiceException(e);
		}
		
	}

	public void setCompareInfoService(CompareInfoService compareInfoService) {
		this.compareInfoService = compareInfoService;
	}


	public void setCompareRunThreadService(
			CompareRunThreadService compareRunThreadService) {
		this.compareRunThreadService = compareRunThreadService;
	}

	public Collection<CompareRunInfo> getBreakPointCompareRunInfo(Integer compId)
			throws ServiceException {
		Collection<CompareRunInfo> breakPointRunInfos = new ArrayList<CompareRunInfo>();
		List<CompareRunInfo> runInfos  = this.getCompareRunInfoListByCompId(compId);
		for(CompareRunInfo runInfo:runInfos)
		{
			Collection<CompareRunThread> stoppedThreads =  this.compareRunThreadService.getStoppedCompareRunThreadsByRunId(runInfo.getId());
			if (stoppedThreads != null && stoppedThreads.size() > 0) breakPointRunInfos.add(runInfo);
		}
		return breakPointRunInfos;
	}
}

