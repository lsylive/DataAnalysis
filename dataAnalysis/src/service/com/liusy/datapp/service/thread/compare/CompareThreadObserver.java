package com.liusy.datapp.service.thread.compare;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


import com.liusy.datapp.model.compare.CompareInfo;
import com.liusy.datapp.model.compare.CompareRunInfo;
import com.liusy.datapp.model.compare.CompareTableRelation;
import com.liusy.datapp.service.compare.CompareInfoService;
import com.liusy.datapp.service.compare.CompareMainResultService;
import com.liusy.datapp.service.compare.CompareRunInfoService;
import com.liusy.datapp.service.pool.TableConfigPool;
import com.liusy.datapp.service.thread.Observer;
import com.liusy.datapp.util.WebContextBeanFactory;
/**
 * 对比分析单一实例
 *
 */
public class CompareThreadObserver extends Thread implements Observer {
	
	private static boolean isRunning=false;
	private Log log=LogFactory.getLog(getClass());
	private long startTime;
	private long allowTime;
	private long permittime;
	private Integer compId;
	private CompareInfo compareInfo;
	private TableConfigPool tableConfigPool;
	public CompareThreadObserver(CompareInfo compareInfo,TableConfigPool tableConfigPool){
//		this.permittime=permittime;
//		this.compId=compId;
		this.compareInfo=compareInfo;
		this.tableConfigPool=tableConfigPool;
	}
	public void run() {
		//注册到通知器
		this.startTime=System.currentTimeMillis();
//		this.allowTime=startTime+permittime;
		SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		Calendar cal=Calendar.getInstance();
		cal.setTimeInMillis(startTime);
		log.info("begin thread id="+this.getId()+" begin at "+format.format(cal.getTime()));
		CompareInfoNotify compareInfoNotify=(CompareInfoNotify)WebContextBeanFactory.getBean("compareInfoNotify");
		compareInfoNotify.registerObserver(this);
		//开始执行比对,设置主表状态位
		CompareInfoService compareInfoService=(CompareInfoService)WebContextBeanFactory.getBean("compareInfoService");
		compareInfoService.startRunCompareInfo(compareInfo.getId());
		//获取对比配置并取出主表对应指标值
		List<CompareTableRelation> mainList=new ArrayList<CompareTableRelation>();
		List<CompareTableRelation> slaveList=new ArrayList<CompareTableRelation>();
		List<String> pkidList=new ArrayList<String>();
		List<String> indList=new ArrayList<String>();
		//List<Map<String, String>> mainParamList=compareInfoService.getMainQueryParam(compId, mainList, slaveList, pkidList,indList);
		CompareTableRelation mainComp=mainList.get(0);
		Map<String,String> mainTabMap=tableConfigPool.getTableMap(mainComp.getDtId().toString());
		//新增对比任务
		CompareRunInfo runinfo=new CompareRunInfo();
		runinfo.setCompId(compareInfo.getId());
		//runinfo.setStartTime(new Date());
		runinfo.setPtableName(mainTabMap.get("name"));
		CompareRunInfoService compareRunInfoService=(CompareRunInfoService)WebContextBeanFactory.getBean("compareRunInfoService");
		compareRunInfoService.createCompareRunInfo(runinfo);
		Integer runId=runinfo.getId();
		
		//注册任务,生成实例ID,需要执行的主表ID
		List<Map<String,String>> mapList=new ArrayList<Map<String,String>>();
		for(CompareTableRelation slave:slaveList){
			Map<String,String> map1=tableConfigPool.getTableMap(slave.getDtId().toString());
			mapList.add(map1);
		}
		//compareRunInfoService.createCompareRunMainJob(compId, runId, mapList);
		//启动线程,开始对比
		
		for(CompareTableRelation slave:slaveList){
			Map<String,String> map1=tableConfigPool.getTableMap(slave.getDtId().toString());
			//CompareInfoJobThread thread=new CompareInfoJobThread(compId,runId,map1,indList,pkidList,mainParamList);
			//thread.run();
		}
		//正常执行完成
		log.info("finish thread id="+this.getId()+" at "+format.format(new Date()));
	}
	/**
	 * 观察者模式实现
	 */
	public void update(Observer o, Object arg) {
		// TODO Auto-generated method stub
		boolean mustinterrupt=((Boolean) arg).booleanValue();
		
		try{
//			long currentTime=Long.parseLong(arg.toString());
			//超过允许执行的时间范围，强制结束进程，并注销监听
			if(mustinterrupt){
				//停止所有子进程
				int num=Thread.activeCount();
				Thread[] actvieThread=new Thread[num];
				Thread.enumerate(actvieThread);
				for(int i=0;i<num;i++){
					if(actvieThread[i]!=null)
					{
						actvieThread[i].interrupt();
					}
				}
				//停止主进程
				this.interrupt();
			}
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			if(mustinterrupt && !this.isInterrupted())
				this.interrupt();
		}
	}
	@Override
	public void interrupt() {
		// TODO Auto-generated method stub
		//停止所有子进程
		int num=Thread.activeCount();
		Thread[] actvieThread=new Thread[num];
		Thread.enumerate(actvieThread);
		for(int i=0;i<num;i++){
			if(actvieThread[i]!=null)
			{
				actvieThread[i].interrupt();
			}
		}
		super.interrupt();
	}

}
