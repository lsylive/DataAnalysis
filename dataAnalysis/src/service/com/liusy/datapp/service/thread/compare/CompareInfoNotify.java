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
		//֪ͨ���н���ִ��ʱ����Ƿ񳬹�
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
			//�߳�Id
			//��ȡ��Ҫ�������߳�
			List<Long> mustterminateThreadList=new ArrayList<Long>();
			if(observer!=null){
				Long threadId=Long.valueOf(observer.getId());
				boolean mustTerminate=false;
				if(mustterminateThreadList.contains(threadId))
					mustTerminate=true;
				//�۲���ģʽ֪ͨ
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
