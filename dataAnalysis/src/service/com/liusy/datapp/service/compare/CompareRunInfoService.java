package com.liusy.datapp.service.compare;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import com.liusy.dataapplatform.base.exception.ServiceException;
import com.liusy.dataapplatform.base.util.PagerObject;
import com.liusy.dataapplatform.base.util.PageQuery;

import com.liusy.datapp.dao.compare.CompareRunInfoDao;
import com.liusy.datapp.model.compare.CompareRunInfo;

public interface CompareRunInfoService {

	public CompareRunInfo findCompareRunInfo(Serializable id) throws ServiceException;

	public void createCompareRunInfo(CompareRunInfo compareRunInfo) throws ServiceException;

	public void updateCompareRunInfo(CompareRunInfo compareRunInfo) throws ServiceException;

	public void removeCompareRunInfo(Serializable id) throws ServiceException;

	public PageQuery queryCompareRunInfo(PageQuery pageQuery) throws ServiceException;

	public void removeCompareRunInfos(Serializable[] ids) throws ServiceException;
	
	public List<CompareRunInfo> getCompareRunInfoListByCompId(Serializable id) throws ServiceException;
	
	public boolean executeCompare(Integer runId,Integer pkobjId,Map<String,String> tableMap,List<String> indList,Map<String,String> indValueMap) throws ServiceException;
	public void setStatusTagByRunId(Integer runId,Integer pkId,String status) throws ServiceException;
	
	//public void increPMatchCountByRunId(Integer runId,int pMatchCount) throws ServiceException;
	public Integer computePMatchCountByRunId(Integer runId) throws ServiceException;
	
	public void stopCompareRunInfos(Serializable[] ids) throws ServiceException;
	
	public void resumeCompareRunInfos(Serializable[] ids) throws ServiceException;
	
	
	public void exceJob(Integer compId, Integer runIdParam, 
			Map<String,String> mainTabMapParam,String[] mainFieldArray,List<String> mainPkIdListParam,List<Map<String, String>> mainResultListParam,
			Map<String, String> slaveTableMapParam, String[] slaveFieldArray,
			List<String> slavePkIdListParam,
			List<Map<String, String>> slaveResultListParam);
	
	public Collection<CompareRunInfo> getBreakPointCompareRunInfo(Integer compId) throws ServiceException;
}

