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
import com.liusy.datapp.dao.query.SynthesisTempSpaceDao;
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
import com.liusy.datapp.service.compare.CompareSlaveResultService;
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

public class CopyOfCompareTaskExecutor {
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
	 private CompareSlaveResultService compareSlaveResultService;
	 private ResourceTableService resourceTableService;
	 private SynthesisTempSpaceDao synthesisTempSpaceDao;
	 
	 private CompareInfoJobThread compareInfoJobThread;
	 
	 private int mainPeiceCount;
	 private int slavePeiceCount;
	 private int threadPendingSeconds;
	 private String needContinue;
	 private int threadCountPerSlave;

	 
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

	public CopyOfCompareTaskExecutor(TaskExecutor taskExecutor) {
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
	                 		
	                 		//׼��ִ�жԱ�,��ȡ�Ա��������
	                 		Collection<CompareTableRelation> slaveList=compareTableRelationService.findByFields(CompareTableRelation.PROP_COMP_ID, compare.getId());
	                 		String[] mainFieldArray = compare.getColList().split(",");
	                 		Collection<CompareFilter> filters = compareFilterService.findByField(CompareFilter.PROP_COMP_ID, compare.getId());
	                 		ResourceTable resourceTable = resourceTableService.findResourceTable(compare.getDtId());
	                 		boolean isPersonalTable = false;
	                 		if (resourceTable.getIsSpaceTable()!=null && resourceTable.getIsSpaceTable().equals(Const.IS_SPACCETABLE))
	                 		{
	                 			isPersonalTable = true;
	                 		}
	                
	                 		//��ȡ����Ա��ֶ�ֵ�б�
	                 		Map<String,String> mainTabMap=tableConfigPool.getTableMap(compare.getDtId().toString());
	            			ResourceColumn pkobj=resourceColumnService.findResoucrePKColumn(Integer.valueOf(mainTabMap.get("id")));
	            			List<ColumnPoolObj> columnList=tableConfigPool.getTableColumnPool(compare.getDtId().toString());
	                 		Map<String,List<String>> columnIndMap=new HashMap<String, List<String>>();
	                 		
	                 		//�޸ĶԱ������¼
	                 		compareRunInfo.setPtableName(mainTabMap.get("name"));
	                 		compareRunInfo.setStatus(Const.STATUS_RUNNING);
	                 		compareRunInfo.setFinishTime(null);
	                 		compareRunInfoService.updateCompareRunInfo(compareRunInfo);
	                 		
	                 		Integer runId=compareRunInfo.getId();
	                 		
	                 		compare.setRunStatus(Const.STATUS_RUNNING);
	                 		compare.setEndTime(null);
	        				compareInfoService.updateCompareInfo(compare);
	        				
	        				//��ʼ���ԱȽ�������¼
	        				List<Map<String,String>> mapList=new ArrayList<Map<String,String>>();
	                 		for(CompareTableRelation slave:slaveList){
	                 			Map<String,String> map1=tableConfigPool.getTableMap(slave.getDtId().toString());
	                 			mapList.add(map1);
	                 		}
	                 		compareMainResultService.createCompareMainResultByRunId(runId, mapList);
	                 		
	                 		String querySql = compareInfoService.getCompareQuerySql(mainTabMap, columnList, mainFieldArray,pkobj,columnIndMap,filters);
	                 		int mainRecordCount = 0;
	                 		if (!isPersonalTable)
	                 		{
	                 			mainRecordCount = synthesisQueryForCompareService.queryCountBySql(querySql);
	                 		}
	                 		else
	                 		{
	                 			mainRecordCount = synthesisTempSpaceDao.queryCountBySql(querySql);
	                 		}
	                 		
	                 		int pageCount = 0;
	                 		PageQuery pageQuery = new PageQuery();
	                 		if (mainPeiceCount >0)
	                 		{
	                 			pageCount = mainRecordCount / mainPeiceCount + 1;
	                 			if (mainRecordCount % mainPeiceCount == 0) pageCount --;
	                 				pageQuery.setPageCount(String.valueOf(pageCount));
	                 				pageQuery.setPageSize(String.valueOf(mainPeiceCount));
	                 				pageQuery.setRecordCount(String.valueOf(mainRecordCount));
	                 				//pageQuery.setOrder(pkobj.getName());
	                 				//pageQuery.setOrderDirection(PageQuery.ASC);
	                 		}
	                 	
	                 		if (isPersonalTable || mainPeiceCount <=0 || (filters!=null && filters.size() > 0)) ////�������������ˣ����������ǴӸ��˿ռ���Դ����ȡ,��һ��ȫ����ȡ
	                 		{
	                 			pageCount = 1;
	                 			pageQuery.setPageSize("0");
	                 			pageQuery.setPageCount("1");
	                 		}
	                 		log.info("����:" + mainTabMap.get("name") + "���ȶԼ�¼��Ϊ:"+ mainRecordCount +" ϵͳ����Ϊ" + pageCount + "ҳ������ȡ");
	                 		
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
		                 		
		                 		//��ȡ�ӱ��Ա��ֶ�ֵ�б�
		                 		ResourceColumn pkobj1=resourceColumnService.findResoucrePKColumn(Integer.valueOf(slaveTabMap.get("id")));
		            			List<ColumnPoolObj> columnList1=tableConfigPool.getTableColumnPool(slaveTabMap.get("id"));
		                 		Map<String,List<String>> columnIndMap1=new HashMap<String, List<String>>();
		                 		
		                 		
		                 		String querySql1 = compareInfoService.getCompareQuerySql(slaveTabMap, columnList1, slaveFieldArray,pkobj1,columnIndMap1,null);
		                 		int slaveRecordCount = synthesisQueryForCompareService.queryCountBySql(querySql1);
		                 		
		                 		//Ϊ�ӱ�ȶԷ����߳�
//		                 		int threadsCount = slaveRecordCount / slavePeiceCount + 1;
//		                 		if (slaveRecordCount % slavePeiceCount == 0) threadsCount --;
//		                 		for(int threadIndex = 0; threadIndex < threadsCount; threadIndex ++)
//		                 		{
//		                 			long startLine = threadIndex * slavePeiceCount;
//		                 			long endLine =(threadIndex + 1) * slavePeiceCount;
//
//			                 		String threadInfo = "����(" + mainTabMap.get("name")+")��" + i + "ҳ�ʹӱ�(" + slaveTabMap.get("name") + ")��" +  startLine + "-"+ endLine +"����¼";
//			                 		log.info("׼��ִ��" + threadInfo + "�ȶ�");
//			                 		Thread.sleep(threadPendingSeconds * 1000);
//		                 			new CopyOfCompareInfoJobThread(threadInfo,compId,runId,mainTabMap,mainFieldArray,mainPkIdList,mainResultList,
//		                 					slaveTabMap,slaveFieldArray,
//		                 					i,threadIndex,null,needContinue,
//		                 					threadsCount,slavePeiceCount,slaveRecordCount,columnIndMap1,querySql1,compareInfoService,compareSlaveResultService).start();
//		                 			
//		                 		}
		                 		
		                 		int totalPageCount = slaveRecordCount / slavePeiceCount + 1;
		                 		if (slaveRecordCount % slavePeiceCount == 0) totalPageCount --;
		                 		if (totalPageCount < threadCountPerSlave) threadCountPerSlave = totalPageCount;
		                 		log.info("�ӱ�:" + slaveTabMap.get("name") + "���ȶԼ�¼��Ϊ:" + slaveRecordCount + " ϵͳ������" + threadCountPerSlave + "���߳̽��бȶ�");
		                 		for(int threadIndex = 0; threadIndex < threadCountPerSlave; threadIndex ++)
		                 		{
		                 			
		                 			String threadInfo = "����(" + mainTabMap.get("name")+")��"+ i +"ҳ�ʹӱ�(" + slaveTabMap.get("name") + ")��"+threadIndex+"ҳ��¼";
			                 		log.info("�߳�:"+i+"-"+threadIndex+" ׼��ִ��" + threadInfo + "�ȶ�");
			                 		Thread.sleep(threadPendingSeconds * 1000);
			                 		new CopyOfCompareInfoJobThread(threadInfo,compId,runId,mainTabMap,mainFieldArray,mainPkIdList,mainResultList,
		                 					slaveTabMap,slaveFieldArray,
		                 					i,threadIndex,null,needContinue,
		                 					threadCountPerSlave,totalPageCount,slavePeiceCount,slaveRecordCount,columnIndMap1,querySql1,compareInfoService,compareSlaveResultService,compareRunThreadService,compareRunInfoService).start();
			                 		
		                 		}		                 		
	                 			}
	                 		}
	                 		//Thread.currentThread().join();
	                 		//����ִ�����
	                 			                 		
	                 		long endTime =System.currentTimeMillis();
	                    	//compareRunInfo.setFinishTime(new Timestamp(endTime));
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



	public void setCompareSlaveResultService(
			CompareSlaveResultService compareSlaveResultService) {
		this.compareSlaveResultService = compareSlaveResultService;
	}

	public void setThreadPendingSeconds(int threadPendingSeconds) {
		this.threadPendingSeconds = threadPendingSeconds;
	}

	public void setThreadCountPerSlave(int threadCountPerSlave) {
		this.threadCountPerSlave = threadCountPerSlave;
	}

	public void setSlavePeiceCount(int slavePeiceCount) {
		this.slavePeiceCount = slavePeiceCount;
	}

	public void setResourceTableService(ResourceTableService resourceTableService) {
		this.resourceTableService = resourceTableService;
	}

	public void setSynthesisTempSpaceDao(SynthesisTempSpaceDao synthesisTempSpaceDao) {
		this.synthesisTempSpaceDao = synthesisTempSpaceDao;
	}

	 

	 
}
