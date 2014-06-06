package com.liusy.datapp.service.compare;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import com.liusy.dataapplatform.base.exception.DAOException;
import com.liusy.dataapplatform.base.exception.ServiceException;
import com.liusy.dataapplatform.base.util.PagerObject;
import com.liusy.dataapplatform.base.util.PageQuery;

import com.liusy.datapp.dao.compare.CompareMainResultDao;
import com.liusy.datapp.model.compare.CompareMainResult;
import com.liusy.datapp.model.compare.CompareRunInfo;

public interface CompareMainResultService {

	public CompareMainResult findCompareMainResult(Serializable id) throws ServiceException;

	public void createCompareMainResult(CompareMainResult compareMainResult) throws ServiceException;

	public void updateCompareMainResult(CompareMainResult compareMainResult) throws ServiceException;

	public void removeCompareMainResult(Serializable id) throws ServiceException;

	public PageQuery queryCompareMainResult(PageQuery pageQuery) throws ServiceException;

	public void removeCompareMainResults(Serializable[] ids) throws ServiceException;
	public void setStatusTagByRunId(Integer runId,Integer pkId,String status) throws ServiceException;
	public List findMainResultByFields(String[] fieldNames,Object[] fieldValues) throws ServiceException;
	
	public void createCompareMainResultByRunId(Integer runId,List<Map<String,String>> tableMapList) throws ServiceException;
	
	public void deleleResultByRunIdAndSlaveTableName(Integer runId,String slaveTableName) throws ServiceException;
	
	public List<CompareMainResult> getCompareMainResultListByRunId(Serializable runId)	throws ServiceException ;

}

