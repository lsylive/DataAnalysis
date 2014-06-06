package com.liusy.datapp.service.thread.compare;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.RejectedExecutionException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.core.task.TaskExecutor;

import com.liusy.core.util.Const;
import com.liusy.dataapplatform.base.util.PageQuery;
import com.liusy.datapp.model.compare.CompareFilter;
import com.liusy.datapp.model.compare.CompareInfo;
import com.liusy.datapp.model.compare.CompareRunInfo;
import com.liusy.datapp.model.compare.CompareRunThread;
import com.liusy.datapp.model.compare.CompareTableRelation;
import com.liusy.datapp.model.resource.ResourceColumn;
import com.liusy.datapp.model.resource.ResourceTable;
import com.liusy.datapp.service.compare.CompareFilterService;
import com.liusy.datapp.service.compare.CompareInfoService;
import com.liusy.datapp.service.compare.CompareMainResultService;
import com.liusy.datapp.service.compare.CompareRunInfoService;
import com.liusy.datapp.service.compare.CompareTableRelationService;
import com.liusy.datapp.service.compare.CompareRunThreadService;
import com.liusy.datapp.service.pool.TableConfigPool;
import com.liusy.datapp.service.query.SynthesisQueryService;
import com.liusy.datapp.service.query.SynthesisQueryForCompareService;
import com.liusy.datapp.service.resource.ResourceColumnService;
import com.liusy.datapp.service.resource.ResourceTableService;
import com.liusy.datapp.util.ThreadPool;
import com.liusy.datapp.util.WebContextBeanFactory;
import com.liusy.datapp.util.poolobj.ColumnPoolObj;

public class CompareTaskExecutor {
	 private TaskExecutor taskExecutor;
	 private Log log=LogFactory.getLog(getClass());
	 
	 private TableConfigPool tableConfigPool;
	 private ResourceColumnService resourceColumnService;
	 private CompareInfoService compareInfoService;
	 private CompareRunInfoService compareRunInfoService;
	 private CompareMainResultService compareMainResultService;
	 private CompareTableRelationService compareTableRelationService;
	 private CompareFilterService compareFilterService;
	 private SynthesisQueryForCompareService synthesisQueryForCompareService;
	 private CompareRunThreadService compareRunThreadService;
	 private ResourceTableService resourceTableService;
	 
	 private CompareInfoJobThread compareInfoJobThread;
	 
	 private int mainPeiceCount;
	 private int slavePeiceCount;
	 private String needContinue;

	 
	 public void setCompareInfoJobThread(CompareInfoJobThread compareInfoJobThread) {
		this.compareInfoJobThread = compareInfoJobThread;
	}

	public void setTableConfigPool(TableConfigPool tableConfigPool) {
		this.tableConfigPool = tableConfigPool;
	}


	public void setCompareInfoService(CompareInfoService compareInfoService) {
		this.compareInfoService = compareInfoService;
	}

	public CompareRunInfoService getCompareRunInfoService() {
		return compareRunInfoService;
	}

	public void setCompareRunInfoService(CompareRunInfoService compareRunInfoService) {
		this.compareRunInfoService = compareRunInfoService;
	}

	public CompareTaskExecutor(TaskExecutor taskExecutor) {
		    this.taskExecutor = taskExecutor;
		  }

	public void execCompare(CompareInfo compareInfo,CompareRunInfo runInfo) { 
		 final int compId = compareInfo.getId();
		 final CompareRunInfo compareRunInfo1 = runInfo;
		 final CompareInfo compare = compareInfo;
	          taskExecutor.execute(new Runnable(){           
	            public void run() {
	            	boolean stopped = false;
	            	CompareRunInfo compareRunInfo = compareRunInfo1;
	            	if (compareRunInfo1 == null)
	            	{
	            		compareRunInfo=new CompareRunInfo();
	            		compareRunInfo.setCompId(compId);
	            		compareRunInfo.setStatus(Const.STATUS_RUNNING);
	            		compareRunInfoService.createCompareRunInfo(compareRunInfo);
	            		
	            	}
	            	
	                     try {
	                    	long startTime =System.currentTimeMillis();
	                    	compareRunInfo.setStartTime(new Timestamp(startTime));
	                 		SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
	                 		Calendar cal=Calendar.getInstance();
	                 		cal.setTimeInMillis(startTime);
	                 		log.info("begin main compare thread: id="+Thread.currentThread().getId()+" begin at "+format.format(cal.getTime()));
	                 		
	                 		//准备执行对比,获取对比相关配置
	                 		Collection<CompareTableRelation> slaveList=compareTableRelationService.findByFields(CompareTableRelation.PROP_COMP_ID, compare.getId());
	                 		String[] mainFieldArray = compare.getColList().split(",");
	                 		Collection<CompareFilter> filters = compareFilterService.findByField(CompareFilter.PROP_COMP_ID, compare.getId());
	                 		ResourceTable resourceTable = resourceTableService.findResourceTable(compare.getDtId());
	                 		boolean isPersonalTable = false;
	                 		if (resourceTable.getIsSpaceTable()!=null && resourceTable.getIsSpaceTable().equals(Const.IS_SPACCETABLE))
	                 		{
	                 			isPersonalTable = true;
	                 		}
	                
	                 		//获取主表对比字段值列表
	                 		Map<String,String> mainTabMap=tableConfigPool.getTableMap(compare.getDtId().toString());
	            			ResourceColumn pkobj=resourceColumnService.findResoucrePKColumn(Integer.valueOf(mainTabMap.get("id")));
	            			List<ColumnPoolObj> columnList=tableConfigPool.getTableColumnPool(compare.getDtId().toString());
	                 		Map<String,List<String>> columnIndMap=new HashMap<String, List<String>>();
	                 		
	                 		//修改对比任务记录
	                 		compareRunInfo.setPtableName(mainTabMap.get("name"));
	                 		compareRunInfo.setStatus(Const.STATUS_RUNNING);
	                 		compareRunInfo.setFinishTime(null);
	                 		compareRunInfoService.updateCompareRunInfo(compareRunInfo);
	                 		
	                 		Integer runId=compareRunInfo.getId();
	                 		
	                 		compare.setRunStatus(Const.STATUS_RUNNING);
	                 		compare.setEndTime(null);
	        				compareInfoService.updateCompareInfo(compare);
	        				
	        				//初始化对比结果主表记录
	        				List<Map<String,String>> mapList=new ArrayList<Map<String,String>>();
	                 		for(CompareTableRelation slave:slaveList){
	                 			Map<String,String> map1=tableConfigPool.getTableMap(slave.getDtId().toString());
	                 			mapList.add(map1);
	                 		}
	                 		compareMainResultService.createCompareMainResultByRunId(runId, mapList);
	                 		
	                 		String querySql = compareInfoService.getCompareQuerySql(mainTabMap, columnList, mainFieldArray,pkobj,columnIndMap,filters);
	                 		int mainRecordCount = synthesisQueryForCompareService.queryCountBySql(querySql);
	                 		
	                 		int pageCount = mainRecordCount / mainPeiceCount + 1;
	                 		if (mainRecordCount % mainPeiceCount == 0) pageCount --;
	                 		PageQuery pageQuery = new PageQuery();
	                 		pageQuery.setPageCount(String.valueOf(pageCount));
	                 		pageQuery.setPageSize(String.valueOf(mainPeiceCount));
	                 		pageQuery.setRecordCount(String.valueOf(mainRecordCount));
	                 		//pageQuery.setOrder(pkobj.getName());
	                 		//pageQuery.setOrderDirection(PageQuery.ASC);
	                 		
	                 	
	                 		if (filters!=null && filters.size() > 0) //如果设了主表过滤,则一次全部提取
	                 		{
	                 			pageCount = 1;
	                 			pageQuery.setPageSize("0");
	                 			pageQuery.setPageCount("1");
	                 		}
	                 		
	                 		for(int i = 0; i < pageCount; i ++)
	                 		{
	                 			pageQuery.setPageNumber(String.valueOf(i + 1));
	                 			List<String> mainPkIdList=new LinkedList<String>();
	                 			List<Map<String,String>> mainResultList = compareInfoService.queryDataBaseForCompare(isPersonalTable,mainFieldArray,
	                 					mainPkIdList, columnIndMap,querySql,pageQuery);
	                 			if (mainResultList==null) continue;
	                 			
	                 		for(CompareTableRelation slave:slaveList){
	                 			Map<String,String> slaveTabMap=tableConfigPool.getTableMap(slave.getDtId().toString());
		                 		String[] slaveFieldArray = slave.getColList().split(",");
		                 		
		                 		//获取从表被对比字段值列表
		                 		ResourceColumn pkobj1=resourceColumnService.findResoucrePKColumn(Integer.valueOf(slaveTabMap.get("id")));
		            			List<ColumnPoolObj> columnList1=tableConfigPool.getTableColumnPool(slaveTabMap.get("id"));
		                 		Map<String,List<String>> columnIndMap1=new HashMap<String, List<String>>();
		                 		
		                 		
		                 		String querySql1 = compareInfoService.getCompareQuerySql(slaveTabMap, columnList1, slaveFieldArray,pkobj1,columnIndMap1,null);
		                 		int slaveRecordCount = synthesisQueryForCompareService.queryCountBySql(querySql1);
		                 		
		                 		int pageCount1 = slaveRecordCount / slavePeiceCount + 1;
		                 		if (slaveRecordCount % slavePeiceCount == 0) pageCount1 --;
		                 		PageQuery pageQuery1 = new PageQuery();
		                 		pageQuery1.setPageCount(String.valueOf(pageCount1));
		                 		pageQuery1.setPageSize(String.valueOf(slavePeiceCount));
		                 		pageQuery1.setRecordCount(String.valueOf(slaveRecordCount));
		                 		//pageQuery1.setOrder(pkobj1.getName());
		                 		//pageQuery1.setOrderDirection(PageQuery.ASC);
		                 		
		                 		for(int j = 0; j < pageCount1; j ++)
		                 		{
		                 			
		                 			int mtPage = i, stPage = j,mtLine = 0;
		                 			
		                 			CompareRunThread compareRunThread = null;
		                 			if (needContinue.equals("true")){
		                 			//判断用户是否选择了停止运行
		    						CompareRunInfo tempRun = compareRunInfoService.findCompareRunInfo(runId);
		    						if (tempRun.getStatus().equals(Const.STATUS_STOPPED))
		    						{
		    							stopped = true;
		    							long endTime =System.currentTimeMillis();
		    							tempRun.setFinishTime(new Timestamp(endTime));
		    							tempRun.setStatus(Const.STATUS_STOPPED);
		    							compareRunInfo.setFinishTime(new Timestamp(endTime));
		    							compareRunInfo.setStatus(Const.STATUS_STOPPED);
				                 		compareRunInfoService.updateCompareRunInfo(tempRun);
				                 		
				                 		CompareInfo tempCompare = compareInfoService.findCompareInfo(compId);
				                 		tempCompare.setRunStatus(Const.STATUS_STOPPED);
				        				compareInfoService.updateCompareInfo(tempCompare);
		    							break;
		    						}
		    						//判断是否有被暂停或已完成的线程
		    						compareRunThread = compareRunThreadService.getBreakPointCompareRunThread(runId, mtPage, Integer.valueOf(slaveTabMap.get("id")), stPage);
		    						if (compareRunThread !=null)
		    						{
		    							if (compareRunThread.getThStatus().equals(CompareRunThread.THREAD_FINISHED))
		    							{//当前页已完成
		    								continue;
		    							}
		    							else if (compareRunThread.getThStatus().equals(CompareRunThread.THREAD_STOPPED))
		    							{//当前页被暂停,取断点数据继续运行
		    								mtLine = compareRunThread.getMtLine(); 
		    							}
		    						}
		                 			}
		                 			pageQuery1.setPageNumber(String.valueOf(stPage + 1));
		                 			List<String> slavePkIdList=new LinkedList<String>();
		                 			List<Map<String,String>> slaveResultList=compareInfoService.queryDataBaseForCompare(false,slaveFieldArray,
		                 				slavePkIdList, columnIndMap1,querySql1,pageQuery1);
		                 			if (slaveResultList == null) continue;
		                 			//启动线程,开始对比
		                 			String thCode = mainTabMap.get("name")+"-" + mtPage + ":" +slaveTabMap.get("name") +"-" + stPage;
		                 			log.info("准备进入线程:" + thCode);
		                 			try{
		                 				compareInfoJobThread.exceJob(compId,runId,mainTabMap,mainFieldArray,mainPkIdList,mainResultList,slaveTabMap,slaveFieldArray,slavePkIdList,slaveResultList,mtPage,stPage,compareRunThread,needContinue);
		                 			//CopyOfCompareInfoJobThread compareInfoJobThread = new CopyOfCompareInfoJobThread(compId,runId,mainTabMap,mainFieldArray,mainPkIdList,mainResultList,slaveTabMap,slaveFieldArray,slavePkIdList,slaveResultList,mtPage,stPage,compareRunThread,needContinue);
		                 			//ThreadPool.exec(compareInfoJobThread);
		                 			}
		                 			catch(RejectedExecutionException re)
		                 			{
		                 				log.error("线程:" + thCode + " 被丢弃");
		                 			}
		            
		                 		}
		                 		if (stopped) break;
	                 			}
	                 		if (stopped) break;
	                 			
	                 		}
	                 		//正常执行完成
	                 			                 		
	                 		long endTime =System.currentTimeMillis();
	                    	compareRunInfo.setFinishTime(new Timestamp(endTime));
	                 		log.info("main compare thread id="+Thread.currentThread().getId()+" finish at "+format.format(endTime));
	                 		String status = (stopped == true)?Const.STATUS_STOPPED:Const.STATUS_RUNNING;
	                 		compareRunInfo.setStatus(status);
	                 		compareRunInfoService.updateCompareRunInfo(compareRunInfo);
	                 		//compare.setRunStatus(Const.STAUTS_FINISH);
	        				//compareInfoService.updateCompareInfo(compare);
	                     } catch (Exception e) {
	                        e.printStackTrace();
	                        long endTime =System.currentTimeMillis();
	                    	compareRunInfo.setFinishTime(new Timestamp(endTime));
	                        compareRunInfo.setStatus(Const.STATUS_FAILED);
	                        compareRunInfo.setMemo(e.toString());
		                 	compareRunInfoService.updateCompareRunInfo(compareRunInfo);
		                 	compare.setRunStatus(Const.STATUS_FAILED);
	        				compareInfoService.updateCompareInfo(compare);
	        				CompareExecTimer.runningCompareIds.remove(compId);
	                     }
	                   
	            }
	          });
	        
	 }

	public void setCompareTableRelationService(
			CompareTableRelationService compareTableRelationService) {
		this.compareTableRelationService = compareTableRelationService;
	}

	public void setResourceColumnService(ResourceColumnService resourceColumnService) {
		this.resourceColumnService = resourceColumnService;
	}

	public void setMainPeiceCount(int mainPieceCount) {
		this.mainPeiceCount = mainPieceCount;
	}

	public void setSlavePeiceCount(int slavePieceCount) {
		this.slavePeiceCount = slavePieceCount;
	}



	public void setCompareMainResultService(
			CompareMainResultService compareMainResultService) {
		this.compareMainResultService = compareMainResultService;
	}

	public void setCompareFilterService(CompareFilterService compareFilterService) {
		this.compareFilterService = compareFilterService;
	}

	public void setCompareRunThreadService(
			CompareRunThreadService compareRunThreadService) {
		this.compareRunThreadService = compareRunThreadService;
	}

	public void setSynthesisQueryForCompareService(
			SynthesisQueryForCompareService synthesisQueryForCompareService) {
		this.synthesisQueryForCompareService = synthesisQueryForCompareService;
	}

	public String getNeedContinue() {
		return needContinue;
	}

	public void setNeedContinue(String needContinue) {
		this.needContinue = needContinue;
	}

	public void setResourceTableService(ResourceTableService resourceTableService) {
		this.resourceTableService = resourceTableService;
	}

	 

	 
}
