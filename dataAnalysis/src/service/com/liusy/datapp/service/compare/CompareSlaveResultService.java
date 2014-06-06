package com.liusy.datapp.service.compare;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import com.liusy.dataapplatform.base.exception.ServiceException;
import com.liusy.dataapplatform.base.util.PagerObject;
import com.liusy.dataapplatform.base.util.PageQuery;

import com.liusy.datapp.dao.compare.CompareSlaveResultDao;
import com.liusy.datapp.model.compare.CompareSlaveResult;

public interface CompareSlaveResultService {

	public CompareSlaveResult findCompareSlaveResult(Serializable id) throws ServiceException;

	public void createCompareSlaveResult(CompareSlaveResult compareSlaveResult) throws ServiceException;

	public void updateCompareSlaveResult(CompareSlaveResult compareSlaveResult) throws ServiceException;

	public void removeCompareSlaveResult(Serializable id) throws ServiceException;

	public PageQuery queryCompareSlaveResult(PageQuery pageQuery) throws ServiceException;

	public void removeCompareSlaveResults(Serializable[] ids) throws ServiceException; 
	
	public List<CompareSlaveResult> getSlaveResultsByRunId(Integer runId) throws ServiceException;
	
	public Collection<Serializable> getMainPkIdsByRunIdAndSlaveTableName(Integer runId,String slaveTableName) throws ServiceException;
	
	public String[] getSlavePkIdsArrByRunIdAndSlaveTableNameAndPkId(Integer runId,String slaveTableName,Integer pkId) throws ServiceException;
	
	public Collection<CompareSlaveResult> getCompareSlaveResultByRunIdAndSlaveTableName(Integer runId,String slaveTableName) throws ServiceException;

	public int getSlaveResultsCountByRunId(Integer runId);
	
	public String getMainPkIdsByRunIdAndSlaveTableNameCount(Integer runId,String slaveTableName) throws ServiceException;
	
	}

