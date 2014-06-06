package com.liusy.datapp.service.compare;

import java.io.Serializable;
import java.util.Collection;

import com.liusy.dataapplatform.base.exception.ServiceException;
import com.liusy.dataapplatform.base.util.PagerObject;
import com.liusy.dataapplatform.base.util.PageQuery;

import com.liusy.datapp.dao.compare.CompareRunThreadDao;
import com.liusy.datapp.model.compare.CompareRunThread;

public interface CompareRunThreadService {

	public CompareRunThread findCompareRunThread(Serializable id) throws ServiceException;

	public void createCompareRunThread(CompareRunThread compareRunThread) throws ServiceException;

	public void updateCompareRunThread(CompareRunThread compareRunThread) throws ServiceException;

	public void removeCompareRunThread(Serializable id) throws ServiceException;

	public PageQuery queryCompareRunThread(PageQuery pageQuery) throws ServiceException;

	public void removeCompareRunThreads(Serializable[] ids) throws ServiceException;
	
	public void removeCompareRunThreadByRunId(Serializable runId) throws ServiceException;
	public Collection findByFields(String fieldName,Object fieldValue) throws ServiceException;
	
	public CompareRunThread getBreakPointCompareRunThread(Integer runId,Integer mtPage,Integer stId,Integer stPage) throws ServiceException;
	
	public Collection<CompareRunThread> getStoppedCompareRunThreadsByRunId(Integer runId) throws ServiceException;
	public Collection<CompareRunThread> getCompareRunThreadsByRunId(Integer runId) throws ServiceException;
}

