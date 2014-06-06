package com.liusy.datapp.service.compare.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.hibernate.criterion.Order;

import com.liusy.dataapplatform.base.exception.DAOException;
import com.liusy.dataapplatform.base.exception.ServiceException;
import com.liusy.dataapplatform.base.util.Condition;
import com.liusy.dataapplatform.base.util.ConvertUtil;
import com.liusy.dataapplatform.base.util.ICondition;
import com.liusy.dataapplatform.base.util.PageQuery;

import com.liusy.datapp.dao.compare.CompareRunThreadDao;
import com.liusy.datapp.model.compare.CompareRunThread;
import com.liusy.datapp.service.compare.CompareRunThreadService;

public class CompareRunThreadServiceImpl implements CompareRunThreadService {
	private static final long serialVersionUID = 1L;

	public CompareRunThread findCompareRunThread(Serializable id) throws ServiceException {
		try {
			return compareRunThreadDao.get(id);
		}
		catch (DAOException e) {
			throw new ServiceException(e);
		}
	}

	public void createCompareRunThread(CompareRunThread compareRunThread) throws ServiceException {
		try {
			compareRunThreadDao.save(compareRunThread);
		}
		catch (DAOException e) {
			throw new ServiceException(e);
		}
	}

	public void updateCompareRunThread(CompareRunThread compareRunThread) throws ServiceException {
		try {
			CompareRunThread tmp = compareRunThreadDao.get(compareRunThread.getId());
			ConvertUtil.convertToModelForUpdate(tmp, compareRunThread);			
			compareRunThreadDao.update(tmp);
		}
		catch (DAOException e) {
			throw new ServiceException(e);
		}
		catch (Exception e) {
			// TODO: handle exception
			throw new ServiceException(e);
		}
	}

	public void removeCompareRunThread(Serializable id) throws ServiceException {
		try {
			compareRunThreadDao.delete(id);
		}
		catch (DAOException e) {
			throw new ServiceException(e);
		}
	}

	public void removeCompareRunThreads(Serializable[] ids) throws ServiceException {
		try {
			compareRunThreadDao.deleteAll(ids);
		}
		catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	public PageQuery queryCompareRunThread(PageQuery pageQuery) throws ServiceException {
		try {
			PageQuery  query=compareRunThreadDao.queryBySelectId(pageQuery);
			pageQuery.setRecordSet(query.getRecordSet());
			pageQuery.setRecordCount(query.getRecordCount());
			return pageQuery;
		}
		catch (Exception e) {
			throw new ServiceException(e);
		}
	}
	public Collection findByFields(String fieldName,Object fieldValue) throws ServiceException{
		try{
			return compareRunThreadDao.findByField(fieldName, fieldValue);
		}catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	private CompareRunThreadDao	compareRunThreadDao;

	public void setCompareRunThreadDao(CompareRunThreadDao compareRunThreadDao) {
		this.compareRunThreadDao = compareRunThreadDao;
	}

	public void removeCompareRunThreadByRunId(Serializable runId)
			throws ServiceException {
		try{
			Collection<CompareRunThread> list = this.findByFields(CompareRunThread.PROP_RUN_ID, runId);
			Serializable[] toDeleles = new Serializable[list.size()];
			int index = 0;
			for(CompareRunThread compareRunThread: list)
			{
				toDeleles[index++] = compareRunThread.getId();
			}
			this.removeCompareRunThreads(toDeleles);
		}
		catch(Exception e)
		{
			throw new ServiceException(e);
		}
		
	}

	public CompareRunThread getBreakPointCompareRunThread(Integer runId,
			Integer mtPage, Integer stId, Integer stPage)
			throws ServiceException {
		CompareRunThread compareRunThread = null;
		try{
			String[] fields = new String[]{CompareRunThread.PROP_RUN_ID,CompareRunThread.PROP_MT_PAGE,CompareRunThread.PROP_ST_ID,CompareRunThread.PROP_ST_PAGE};
			Object[] values = new Object[]{runId,mtPage,stId,stPage};
			List<CompareRunThread> list = this.compareRunThreadDao.findByFields(fields, values);
			if (list!=null && list.size()>0)
			{
				return list.get(0);
			}
		}
		catch(Exception e)
		{
			throw new ServiceException(e);
		}
		return compareRunThread;
	}

	public Collection<CompareRunThread> getStoppedCompareRunThreadsByRunId(
			Integer runId) throws ServiceException {
		Collection<CompareRunThread> list = new ArrayList<CompareRunThread>();
		try{
			String[] fields = new String[]{CompareRunThread.PROP_RUN_ID,CompareRunThread.PROP_TH_STATUS};
			Object[] values = new Object[]{runId,CompareRunThread.THREAD_STOPPED};
			list = this.compareRunThreadDao.findByFields(fields, values);
			values = new Object[]{runId,CompareRunThread.THREAD_RUNNING};
			list.addAll(this.compareRunThreadDao.findByFields(fields, values));
			return list;
		}
		catch(Exception e)
		{
			throw new ServiceException(e);
		}
	}

	public Collection<CompareRunThread> getCompareRunThreadsByRunId(
			Integer runId) throws ServiceException {
		Collection<CompareRunThread> list = new ArrayList<CompareRunThread>();
		try{
			list = this.compareRunThreadDao.findByField(CompareRunThread.PROP_RUN_ID, runId);
			return list;
		}
		catch(Exception e)
		{
			throw new ServiceException(e);
		}
		
	}
}

