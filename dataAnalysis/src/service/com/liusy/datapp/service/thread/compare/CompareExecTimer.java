package com.liusy.datapp.service.thread.compare;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.Set;
import java.util.TimerTask;
import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.liusy.core.util.Const;
import com.liusy.datapp.model.compare.CompareInfo;
import com.liusy.datapp.model.compare.CompareRunInfo;
import com.liusy.datapp.service.compare.CompareInfoService;
import com.liusy.datapp.service.compare.CompareRunInfoService;

public class CompareExecTimer extends TimerTask{
	private CompareInfoService compareInfoService;
	private CompareRunInfoService compareRunInfoService;
	private CopyOfCompareTaskExecutor copyOfCompareTaskExecutor;
	
	public static Set<Integer> runningCompareIds = new HashSet<Integer>();
	private static Map<Integer,Long> runningDates = new HashMap<Integer,Long>();
	private static int runTimes = 0;
	
	private Log log=LogFactory.getLog(getClass());
	public void run() {
		runTimes ++;
		log.info("正在扫描对比查询运行...");
		List<CompareInfo> toStartCompareList = compareInfoService.getMustStartCompare();
		for(CompareInfo compare : toStartCompareList)
		{
			if (compareInfoService.hasRunFinished(compare.getId())) runningCompareIds.remove(compare.getId());
			
			if (runTimes >1 && runningCompareIds.contains(compare.getId())) continue;
			
			if (compare.getCompareType().equals(CompareInfo.AUTO) &&
					runningDates.get(compare.getId()) != null &&
					(new Date().getTime() - runningDates.get(compare.getId()))/1000 < 60 
			)
				continue;
			Collection<CompareRunInfo> breakPointRunInfos = compareRunInfoService.getBreakPointCompareRunInfo(compare.getId());
			if (breakPointRunInfos==null || breakPointRunInfos.size() == 0)
			{
				log.info(new java.util.Date());
				log.info("开始运行对比查询：" + compare.getCompareName());
				this.copyOfCompareTaskExecutor.execCompare(compare,null);
			}
			else
			{
				for(CompareRunInfo runInfo:breakPointRunInfos)
				{
					log.info(new java.util.Date());
					log.info("恢复运行对比查询:" + compare.getCompareName());			
					this.copyOfCompareTaskExecutor.execCompare(compare,runInfo);
				}
			}
			runningCompareIds.add(compare.getId());
			runningDates.put(compare.getId(), new Date().getTime());
		}
		

		
		
		
	}
	
	public void setCopyOfCompareTaskExecutor(CopyOfCompareTaskExecutor copyOfCompareTaskExecutor) {
		this.copyOfCompareTaskExecutor = copyOfCompareTaskExecutor;
	}

	public void setCompareInfoService(CompareInfoService compareInfoService) {
		this.compareInfoService = compareInfoService;
	}

	public void setCompareRunInfoService(CompareRunInfoService compareRunInfoService) {
		this.compareRunInfoService = compareRunInfoService;
	}

	
}
