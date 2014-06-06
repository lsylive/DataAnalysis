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
 * �Աȷ�����һʵ��
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
		//ע�ᵽ֪ͨ��
		this.startTime=System.currentTimeMillis();
//		this.allowTime=startTime+permittime;
		SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		Calendar cal=Calendar.getInstance();
		cal.setTimeInMillis(startTime);
		log.info("begin thread id="+this.getId()+" begin at "+format.format(cal.getTime()));
		CompareInfoNotify compareInfoNotify=(CompareInfoNotify)WebContextBeanFactory.getBean("compareInfoNotify");
		compareInfoNotify.registerObserver(this);
		//��ʼִ�бȶ�,��������״̬λ
		CompareInfoService compareInfoService=(CompareInfoService)WebContextBeanFactory.getBean("compareInfoService");
		compareInfoService.startRunCompareInfo(compareInfo.getId());
		//��ȡ�Ա����ò�ȡ�������Ӧָ��ֵ
		List<CompareTableRelation> mainList=new ArrayList<CompareTableRelation>();
		List<CompareTableRelation> slaveList=new ArrayList<CompareTableRelation>();
		List<String> pkidList=new ArrayList<String>();
		List<String> indList=new ArrayList<String>();
		//List<Map<String, String>> mainParamList=compareInfoService.getMainQueryParam(compId, mainList, slaveList, pkidList,indList);
		CompareTableRelation mainComp=mainList.get(0);
		Map<String,String> mainTabMap=tableConfigPool.getTableMap(mainComp.getDtId().toString());
		//�����Ա�����
		CompareRunInfo runinfo=new CompareRunInfo();
		runinfo.setCompId(compareInfo.getId());
		//runinfo.setStartTime(new Date());
		runinfo.setPtableName(mainTabMap.get("name"));
		CompareRunInfoService compareRunInfoService=(CompareRunInfoService)WebContextBeanFactory.getBean("compareRunInfoService");
		compareRunInfoService.createCompareRunInfo(runinfo);
		Integer runId=runinfo.getId();
		
		//ע������,����ʵ��ID,��Ҫִ�е�����ID
		List<Map<String,String>> mapList=new ArrayList<Map<String,String>>();
		for(CompareTableRelation slave:slaveList){
			Map<String,String> map1=tableConfigPool.getTableMap(slave.getDtId().toString());
			mapList.add(map1);
		}
		//compareRunInfoService.createCompareRunMainJob(compId, runId, mapList);
		//�����߳�,��ʼ�Ա�
		
		for(CompareTableRelation slave:slaveList){
			Map<String,String> map1=tableConfigPool.getTableMap(slave.getDtId().toString());
			//CompareInfoJobThread thread=new CompareInfoJobThread(compId,runId,map1,indList,pkidList,mainParamList);
			//thread.run();
		}
		//����ִ�����
		log.info("finish thread id="+this.getId()+" at "+format.format(new Date()));
	}
	/**
	 * �۲���ģʽʵ��
	 */
	public void update(Observer o, Object arg) {
		// TODO Auto-generated method stub
		boolean mustinterrupt=((Boolean) arg).booleanValue();
		
		try{
//			long currentTime=Long.parseLong(arg.toString());
			//��������ִ�е�ʱ�䷶Χ��ǿ�ƽ������̣���ע������
			if(mustinterrupt){
				//ֹͣ�����ӽ���
				int num=Thread.activeCount();
				Thread[] actvieThread=new Thread[num];
				Thread.enumerate(actvieThread);
				for(int i=0;i<num;i++){
					if(actvieThread[i]!=null)
					{
						actvieThread[i].interrupt();
					}
				}
				//ֹͣ������
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
		//ֹͣ�����ӽ���
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
