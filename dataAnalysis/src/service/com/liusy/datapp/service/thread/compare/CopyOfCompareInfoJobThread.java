package com.liusy.datapp.service.thread.compare;

import java.sql.Timestamp;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.RejectedExecutionException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.core.task.TaskExecutor;

import com.liusy.core.util.Const;
import com.liusy.dataapplatform.base.util.PageQuery;
import com.liusy.datapp.model.compare.CompareInfo;
import com.liusy.datapp.model.compare.CompareRunInfo;
import com.liusy.datapp.model.compare.CompareRunThread;
import com.liusy.datapp.model.compare.CompareSlaveResult;
import com.liusy.datapp.service.compare.CompareInfoService;
import com.liusy.datapp.service.compare.CompareRunInfoService;
import com.liusy.datapp.service.compare.CompareRunThreadService;
import com.liusy.datapp.service.compare.CompareSlaveResultService;

public class CopyOfCompareInfoJobThread extends Thread{
	private Log log=LogFactory.getLog(getClass());
	
	private String threadInfo;
	private Integer compId;
	private Integer runId;

	private Map<String,String> mainTabMap;
	private List<String> mainPkIdList;
	private List<Map<String, String>> mainResultList;

	private Map<String, String> slaveTableMap;
	private String[] slaveFieldArray;
	private boolean needContinue;
	private int mtPage;
	private int threadIndex;
	private CompareRunThread compareRunThread;
	
	private int threadCountPerSlave;
	private int pageCount;
	private int pageSize;
	private long recordCount;
	private Map<String,List<String>> columnIndMap1;
	private String querySql1;
	private long startLine;
	private long endLine;

	private CompareSlaveResultService compareSlaveResultService;
	private CompareRunInfoService compareRunInfoService;
	private CompareRunThreadService compareRunThreadService;
	private CompareInfoService compareInfoService;
	

	public  CopyOfCompareInfoJobThread(){}

	public  CopyOfCompareInfoJobThread(String threadInfoParam,Integer compIdParam, Integer runIdParam, 
			Map<String,String> mainTabMapParam,String[] mainFieldArray,List<String> mainPkIdListParam,List<Map<String, String>> mainResultListParam,
			Map<String, String> slaveTableMapParam, String[] slaveFieldArrayParam,
			int mtPageParam,int threadIndexParam,CompareRunThread compareRunThreadParam,String needContinueParam,
			int threadCountPerSlaveParam,int pageCountParam,int pageSizeParam,long recordCountParam,Map<String,List<String>> columnIndMapParam,String querySqlParam,
			CompareInfoService CompareInfoService,CompareSlaveResultService compareSlaveResultService,CompareRunThreadService compareRunThreadService,CompareRunInfoService compareRunInfoService
	) {
		threadInfo = threadInfoParam;
		runId = runIdParam;
		compId = compIdParam;
		mainTabMap = mainTabMapParam;
		mainResultList = mainResultListParam;
		slaveTableMap = slaveTableMapParam;
		slaveFieldArray = slaveFieldArrayParam;
		mainPkIdList = mainPkIdListParam;
		needContinue = needContinueParam.equals("true")?true:false;
		
		mtPage = mtPageParam;
		threadIndex = threadIndexParam;
		compareRunThread = compareRunThreadParam;
	
		threadCountPerSlave = threadCountPerSlaveParam;
		pageCount= pageCountParam;
		pageSize = pageSizeParam;
		recordCount = recordCountParam;
		columnIndMap1 = columnIndMapParam;
		querySql1 = querySqlParam;
		this.compareInfoService =  CompareInfoService;
		this.compareSlaveResultService = compareSlaveResultService;
		this.compareRunInfoService = compareRunInfoService;
		this.compareRunThreadService = compareRunThreadService;

	}

	public void run(){
				
				long startTime =System.currentTimeMillis();
				//log.info("线程:"+ threadIndex + " 开始执行");
				
				CompareRunInfo compareRunInfo = compareRunInfoService.findCompareRunInfo(runId);
				
				CompareRunThread thread = new CompareRunThread();
				thread.setStartTime(new Timestamp(startTime));
				thread.setRunId(runId);
				thread.setThStatus(CompareRunThread.THREAD_RUNNING);
				thread.setMtPage(mtPage);
				thread.setStPage(threadIndex);
				thread.setMtLine(0);
				thread.setStId(Integer.parseInt(slaveTableMap.get("id")));
				compareRunThreadService.createCompareRunThread(thread);

				long pageNumberCount1 =  recordCount / pageSize ;
					if (recordCount % pageSize  !=0) pageNumberCount1++;
					long pageNumberCount = pageNumberCount1 / threadCountPerSlave;
					if (pageNumberCount1 % threadCountPerSlave !=0) pageNumberCount++;
					
					for(int pageNum=0; pageNum < pageNumberCount;pageNum ++){
					
						PageQuery pageQuery1 = new PageQuery();
						pageQuery1.setPageCount(String.valueOf(pageCount));
						pageQuery1.setPageSize(String.valueOf(pageSize));
						pageQuery1.setRecordCount(String.valueOf(recordCount));
						pageQuery1.setQuerySql(querySql1);
         			             		
             			long currPageNumber = threadIndex * pageNumberCount + pageNum + 1;
             			pageQuery1.setPageNumber(String.valueOf(currPageNumber));
             			long startLine = (currPageNumber-1) * pageSize;
             			long endLine =currPageNumber * pageSize;
             			log.info("线程:"+ mtPage+"-" + threadIndex + " 正在比对从表第" +  startLine + "-"+ endLine +"条记录");
             			
             			List<String> slavePkIdList=new LinkedList<String>();
             			List<Map<String,String>> slaveResultList=compareInfoService.queryDataBaseForCompare(false,slaveFieldArray,
             				slavePkIdList, columnIndMap1,querySql1,pageQuery1);
             			if (slaveResultList == null) continue;
             			
             			
             			//结果比对与记录
    					for (int i = 0; i < mainResultList.size(); i++) {  						
    						Integer pMatchCount = new Integer(0);
    						Map<String, String> mainResultMap = mainResultList.get(i);
    						Collection<String> mainResultValues = mainResultMap.values();
    						if (mainResultValues.isEmpty())	continue;

    						StringBuffer sbSlaveMatchedIds = new StringBuffer();
    						Collection<String> sbSlaveMatchedIdStrings = new LinkedList<String>();
    						
    						for (int jj = 0; jj < slaveResultList.size(); jj++) {
    							
    							Map<String, String> slaveResultMap = slaveResultList.get(jj);
    							Collection<String> slaveResultValues = slaveResultMap.values();
    							if (slaveResultValues.isEmpty()) continue;
    						
    							// System.out.println("record: id-" +
    							// mainPkIdList.get(i)+ " value-" + mainResultMap
    							// + " slaveTableName-" + slaveTableMap.get("name")+
    							// "id-" + slavePkIdList.get(j)+ " value-" +
    							// slaveResultMap);
    							if (Arrays.equals(mainResultValues.toArray(),slaveResultValues.toArray())) {
//    								log.info("!!match(Thread-"+ Thread.currentThread().getId() +"):mainTableName-"+ mainTabMap.get("name")+  " id-"	+ mainPkIdList.get(i) + " value-"+ mainResultMap 
//    										+ "  slaveTableName-"+ slaveTableMap.get("name") + " id-"+ slavePkIdList.get(j) + " value-"+ slaveResultMap);
    								sbSlaveMatchedIds.append(slavePkIdList.get(jj)).append(",");
    								pMatchCount++;
    								if (pMatchCount == 1000)
    								{// where in 语句限制
    									sbSlaveMatchedIdStrings.add(sbSlaveMatchedIds.toString());
    									sbSlaveMatchedIds.delete(0,sbSlaveMatchedIds.length());
    									pMatchCount =0;
    								}
    								else if (sbSlaveMatchedIds.length() > 2000)
    								{// varchar2(4000)限制
    									String temp = sbSlaveMatchedIds.substring(0,2000);
    									sbSlaveMatchedIdStrings.add(temp.substring(0,temp.lastIndexOf(",")) + ",");
    									int lastIndex = sbSlaveMatchedIds.substring(0,2000).lastIndexOf(",");
    									sbSlaveMatchedIds.delete(0, lastIndex+1);
    									pMatchCount = 0;
    								}	
    							}
    						}
    						if (sbSlaveMatchedIds.length() >0) sbSlaveMatchedIdStrings.add(sbSlaveMatchedIds.toString());
    						//将匹配结果记入数据库
    						if (sbSlaveMatchedIdStrings.size() > 0)
    						{
    						
    								for(String slaveMatchedIds : sbSlaveMatchedIdStrings){
    									CompareSlaveResult slaveResult=new CompareSlaveResult();
    									slaveResult.setPkId(Integer.parseInt(mainPkIdList.get(i)));
    									slaveResult.setRunId(runId);
    									slaveResult.setSlaveTableName(slaveTableMap.get("name"));
    									slaveResult.setStatus(Const.STATUS_COLUMN_FINISH);
    									slaveResult.setSlavePk(slaveMatchedIds.substring(0, slaveMatchedIds.length()-1));
    									compareSlaveResultService.createCompareSlaveResult(slaveResult);
    								}
    						}

    					}
    					
					}
					long endTime =System.currentTimeMillis();
					thread.setFinishTime(new Timestamp(endTime));
					thread.setThStatus(CompareRunThread.THREAD_FINISHED);
					compareRunThreadService.updateCompareRunThread(thread);
					log.info("线程:"+ mtPage+"-" + threadIndex + " 执行结束");
					
					//判断其它线程状态
					Collection<CompareRunThread> threads = compareRunThreadService.getCompareRunThreadsByRunId(runId);
					boolean runFinished = true;
					for(CompareRunThread t : threads)
					{
						if (t.getThStatus()!=null && t.getThStatus().equals(CompareRunThread.THREAD_FINISHED))
						{
							continue;
						}
						else
						{
							runFinished = false;
						}
					}
					if (runFinished)
					{
						compareRunInfo.setFinishTime(new Timestamp(endTime));
                 		compareRunInfo.setStatus(Const.STAUTS_FINISH);
                 		compareRunInfoService.updateCompareRunInfo(compareRunInfo);
                 		CompareInfo compare = compareInfoService.findCompareInfo(compId);
                 		compare.setRunStatus(Const.STAUTS_FINISH);
                 		compareInfoService.updateCompareInfo(compare);
                 		log.info("对比查询：" + compare.getCompareName() +  " 的本次比对过程全部结束");
					}
					
					
}
	public void setCompareSlaveResultService(
			CompareSlaveResultService compareSlaveResultService) {
		this.compareSlaveResultService = compareSlaveResultService;
	}

	public void setCompareRunInfoService(CompareRunInfoService compareRunInfoService) {
		this.compareRunInfoService = compareRunInfoService;
	}

	public void setCompareRunThreadService(
			CompareRunThreadService compareRunThreadService) {
		this.compareRunThreadService = compareRunThreadService;
	}

	public void setCompareInfoService(CompareInfoService compareInfoService) {
		this.compareInfoService = compareInfoService;
	}

}
