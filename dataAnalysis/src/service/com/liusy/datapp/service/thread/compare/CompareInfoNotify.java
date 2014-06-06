package com.liusy.datapp.service.thread.compare;


import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class CompareInfoNotify {
	Vector<CompareThreadObserver> itsObserver=new Vector<CompareThreadObserver>();
	private int seqnum = 0;   
	private int globalNum = 1;  
	private long lastRunTime;
	public CompareInfoNotify(){
		lastRunTime=System.currentTimeMillis();
		
	}
	public void Notify(){
		//通知所有进程执行时间点是否超过
		Vector<CompareThreadObserver> copyVect=null;
		
		int localSeqnum;   
		   
		synchronized(this){   
			copyVect=itsObserver;
			seqnum++;   
			localSeqnum = seqnum;   
		}   
		while(localSeqnum != globalNum){   
			//Only to wait
			try{
				wait(1000);
			}catch (Exception e) {
				// TODO: handle exception
			}
		}   

		for(int i=0;i<copyVect.size();i++){
			CompareThreadObserver observer=copyVect.get(i);
			//线程Id
			//获取需要结束的线程
			List<Long> mustterminateThreadList=new ArrayList<Long>();
			if(observer!=null){
				Long threadId=Long.valueOf(observer.getId());
				boolean mustTerminate=false;
				if(mustterminateThreadList.contains(threadId))
					mustTerminate=true;
				//观察者模式通知
				observer.update(observer, mustTerminate);
				
			}else
				itsObserver.remove(i);
		} 
		
	}
	
	public synchronized  void registerObserver(CompareThreadObserver observer){
		this.itsObserver.add(observer);
	}
	public synchronized void unregisterObserver(CompareThreadObserver observer,String result,Integer compId,long startTime,long currentTime){
		itsObserver.remove(observer);
	}

	public long getLastRunTime() {
		return lastRunTime;
	}

	public void setLastRunTime(long lastRunTime) {
		this.lastRunTime = lastRunTime;
	}

}
