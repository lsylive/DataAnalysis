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

public class CompareInfoJobThread{
	private TaskExecutor jobExecutor;
	private Log log=LogFactory.getLog(getClass());
	
	private Integer compId;
	private Integer runId;

	private Map<String,String> mainTabMap;
	private String[] mainFieldArray;
	private List<String> mainPkIdList;
	private List<Map<String, String>> mainResultList;

	private Map<String, String> slaveTableMap;
	private String[] slaveFieldArray;
	private List<String> slavePkIdList;
	private List<Map<String, String>> slaveResultList;

	private CompareSlaveResultService compareSlaveResultService;
	private CompareRunInfoService compareRunInfoService;
	private CompareRunThreadService compareRunThreadService;
	private CompareInfoService compareInfoService;

	public CompareInfoJobThread(TaskExecutor jobExecutor) {
		this.jobExecutor = jobExecutor;
	}

	public void exceJob(Integer compIdParam, Integer runIdParam, 
			Map<String,String> mainTabMapParam,String[] mainFieldArray,List<String> mainPkIdListParam,List<Map<String, String>> mainResultListParam,
			Map<String, String> slaveTableMapParam, String[] slaveFieldArray,
			List<String> slavePkIdListParam,
			List<Map<String, String>> slaveResultListParam,
			int mtPageParam,int stPageParam,CompareRunThread compareRunThreadParam,String needContinueParam
	) {
		
		final Integer runId = runIdParam;
		final Integer compId = compIdParam;
		final Map<String,String> mainTabMap = mainTabMapParam;
		final List<Map<String, String>> mainResultList = mainResultListParam;
		final List<Map<String, String>> slaveResultList = slaveResultListParam;
		final Map<String, String> slaveTableMap = slaveTableMapParam;
		final List<String> slavePkIdList = slavePkIdListParam;
		final List<String> mainPkIdList = mainPkIdListParam;
		final boolean needContinue = needContinueParam.equals("true")?true:false;
		
		final int mtPage = mtPageParam;
		final int stPage = stPageParam;
		final CompareRunThread compareRunThread = compareRunThreadParam;


		jobExecutor.execute(new Runnable() {
			public void run() {
				String threadInfo = "主表(" + mainTabMap.get("name")+")第" + mtPage + "页和从表(" + slaveTableMap.get("name") + ")第" +  stPage +"页";
				log.info("正在执行" + threadInfo + "比对");
				CompareRunThread thread = compareRunThread;
				try {
					int mtLine = 0;
					long startTime =System.currentTimeMillis();
					
					CompareRunInfo compareRunInfo = null;
					if (needContinue){
					//判断用户是否选择了停止运行
						compareRunInfo = compareRunInfoService.findCompareRunInfo(runId);
						if (compareRunInfo.getStatus().equals(Const.STATUS_STOPPED))
						{
							CompareInfo tempCompare = compareInfoService.findCompareInfo(compId);
							tempCompare.setRunStatus(Const.STATUS_STOPPED);
							compareInfoService.updateCompareInfo(tempCompare);
							return;
						}
					
						//更新线程信息
						if (thread == null){//新页
							thread = new CompareRunThread();
							thread.setStartTime(new Timestamp(startTime));
							thread.setRunId(runId);
							thread.setThStatus(CompareRunThread.THREAD_RUNNING);
							thread.setMtPage(mtPage);
							thread.setStPage(stPage);
							thread.setMtLine(0);
							thread.setStId(Integer.parseInt(slaveTableMap.get("id")));
							compareRunThreadService.createCompareRunThread(thread);
						}
						else
						{//续传页
							thread.setStartTime(new Timestamp(startTime));
							thread.setThStatus(CompareRunThread.THREAD_RUNNING);
							mtLine = thread.getMtLine();
							compareRunThreadService.updateCompareRunThread(thread);
						}
					
					}
					
					
					
					for (int i = mtLine; i < mainResultList.size(); i++) {
						if (needContinue){
						//判断用户是否选择了停止运行
						compareRunInfo = compareRunInfoService.findCompareRunInfo(runId);
						if (compareRunInfo.getStatus().equals(Const.STATUS_STOPPED))
						{
							thread.setThStatus(CompareRunThread.THREAD_STOPPED);
							thread.setMtLine(i);
							long stopTime =System.currentTimeMillis();
							//thread.setFinishTime(new Timestamp(stopTime));
							compareRunThreadService.updateCompareRunThread(thread);
							
							CompareInfo tempCompare = compareInfoService.findCompareInfo(compId);
	                 		tempCompare.setRunStatus(Const.STATUS_STOPPED);
	        				compareInfoService.updateCompareInfo(tempCompare);
							return;
						}
						}
						
						Integer pMatchCount = new Integer(0);
						Map<String, String> mainResultMap = mainResultList.get(i);
						Collection<String> mainResultValues = mainResultMap.values();
						if (mainResultValues.isEmpty())	continue;

						StringBuffer sbSlaveMatchedIds = new StringBuffer();
						Collection<String> sbSlaveMatchedIdStrings = new LinkedList<String>();
						
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
//								log.info("!!match(Thread-"+ Thread.currentThread().getId() +"):mainTableName-"+ mainTabMap.get("name")+  " id-"	+ mainPkIdList.get(i) + " value-"+ mainResultMap 
//										+ "  slaveTableName-"+ slaveTableMap.get("name") + " id-"+ slavePkIdList.get(j) + " value-"+ slaveResultMap);
								sbSlaveMatchedIds.append(slavePkIdList.get(j)).append(",");
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
							//synchronized(this){
								for(String slaveMatchedIds : sbSlaveMatchedIdStrings){
//								PageQuery pageQuery = new PageQuery();
//								pageQuery.setPageCount("0");
//								pageQuery.setSelectParamId("FIND_DEF_SLAVE_RESULT");
//								Map<String,String> params = new HashMap<String,String>();
//								params.put(CompareSlaveResult.PROP_RUN_ID, String.valueOf(runId));
//								params.put(CompareSlaveResult.PROP_PK_ID, mainPkIdList.get(i));
//								params.put(CompareSlaveResult.PROP_SLAVETABLE_NAME, slaveTableMap.get("name"));
//								pageQuery.setParameters(params);
//								pageQuery = compareSlaveResultService.queryCompareSlaveResult(pageQuery);
//								List<Map<String,String>> results = pageQuery.getRecordSet();
								
								CompareSlaveResult slaveResult=new CompareSlaveResult();
								slaveResult.setPkId(Integer.parseInt(mainPkIdList.get(i)));
								slaveResult.setRunId(runId);
								slaveResult.setSlaveTableName(slaveTableMap.get("name"));
								slaveResult.setStatus(Const.STATUS_COLUMN_FINISH);
								slaveResult.setSlavePk(slaveMatchedIds.substring(0, slaveMatchedIds.length()-1));
//								if (results == null || results.size()==0)
//								{
									
									compareSlaveResultService.createCompareSlaveResult(slaveResult);
//								}
//								else
//								{
//									slaveResult.setSlavePk(slavePkToRecord);
//									slaveResult.setCount(currSlavePk.split(",").length);
//									compareSlaveResultService.updateCompareSlaveResult(slaveResult);
//								}
								
									
									
//								compareRunInfoService.increPMatchCountByRunId(runId,pMatchCount);
							}
							//}
						}
						if (needContinue){
						thread.setMtLine(i);
						compareRunThreadService.updateCompareRunThread(thread);
						}
					}
					if (needContinue){
					long endTime =System.currentTimeMillis();
					thread.setFinishTime(new Timestamp(endTime));
					thread.setThStatus(CompareRunThread.THREAD_FINISHED);
					compareRunThreadService.updateCompareRunThread(thread);
					}
				} catch (Exception e) {
					e.printStackTrace();
					if (needContinue){
					long endTime =System.currentTimeMillis();
					thread.setFinishTime(new Timestamp(endTime));
					thread.setThStatus(CompareRunThread.THREAD_FAILED);
					compareRunThreadService.updateCompareRunThread(thread);
					}
				}
				log.info("结束执行" + threadInfo + "比对");

			}
		});
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
