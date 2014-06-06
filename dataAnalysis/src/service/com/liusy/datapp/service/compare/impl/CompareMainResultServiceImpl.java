package com.liusy.datapp.service.compare.impl;

import java.io.Serializable;
import java.util.ArrayList;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;


import com.liusy.core.util.Const;
import com.liusy.dataapplatform.base.exception.DAOException;
import com.liusy.dataapplatform.base.exception.ServiceException;
import com.liusy.dataapplatform.base.util.ConvertUtil;

import com.liusy.dataapplatform.base.util.PageQuery;

import com.liusy.datapp.dao.compare.CompareMainResultDao;
import com.liusy.datapp.model.compare.CompareMainResult;
import com.liusy.datapp.model.compare.CompareRunInfo;
import com.liusy.datapp.model.compare.CompareSlaveResult;
import com.liusy.datapp.service.compare.CompareMainResultService;
import com.liusy.datapp.service.compare.CompareSlaveResultService;

public class CompareMainResultServiceImpl implements CompareMainResultService {
	private static final long serialVersionUID = 1L;

	public CompareMainResult findCompareMainResult(Serializable id) throws ServiceException {
		try {
			return compareMainResultDao.get(id);
		}
		catch (DAOException e) {
			throw new ServiceException(e);
		}
	}

	public void createCompareMainResult(CompareMainResult compareMainResult) throws ServiceException {
		try {
			compareMainResultDao.save(compareMainResult);
		}
		catch (DAOException e) {
			throw new ServiceException(e);
		}
	}

	public void updateCompareMainResult(CompareMainResult compareMainResult) throws ServiceException {
		try {
			CompareMainResult tmp = compareMainResultDao.get(compareMainResult.getId());
			ConvertUtil.convertToModelForUpdate(tmp, compareMainResult);			
			compareMainResultDao.update(tmp);
		}
		catch (DAOException e) {
			throw new ServiceException(e);
		}
		catch (Exception e) {
			// TODO: handle exception
			throw new ServiceException(e);
		}
	}

	public void removeCompareMainResult(Serializable id) throws ServiceException {
		try {
			compareMainResultDao.delete(id);
		}
		catch (DAOException e) {
			throw new ServiceException(e);
		}
	}

	public void removeCompareMainResults(Serializable[] ids) throws ServiceException {
		try {
			compareMainResultDao.deleteAll(ids);
		}
		catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	public PageQuery queryCompareMainResult(PageQuery pageQuery) throws ServiceException {
		try {
			PageQuery  query=compareMainResultDao.queryBySelectId(pageQuery);
			pageQuery.setRecordSet(query.getRecordSet());
			pageQuery.setRecordCount(query.getRecordCount());
			return pageQuery;
		}
		catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	public void setStatusTagByRunId(Integer runId,Integer pkId,String status) throws ServiceException{
		List<CompareMainResult> mainList=compareMainResultDao.findByField(CompareMainResult.PROP_RUN_ID,runId);
		CompareMainResult result=mainList.get(0);
		CompareMainResult tmp=findCompareMainResult(result.getId());
		tmp.setStatus(status);
		tmp.setProcessPkId(pkId);
		compareMainResultDao.update(tmp);
	}
	public List findMainResultByFields(String[] fieldNames,Object[] fieldValues) throws ServiceException{
		try{
			return compareMainResultDao.findByFields(fieldNames, fieldValues);
		}catch (Exception e) {
			throw new ServiceException(e);
		}
	}
	
	public void createCompareMainResultByRunId(Integer runId,List<Map<String,String>> tableMapList) throws ServiceException{
		try{
			String[] fieldNames = new String[]{CompareMainResult.PROP_RUN_ID,CompareMainResult.PROP_SLAVETABLE_NAME};
			Object[] fieldValues = new Object[2];
			for(Map<String,String> map:tableMapList){
				fieldValues[0] = runId;
				fieldValues[1] = map.get("name");
				List list = this.findMainResultByFields(fieldNames, fieldValues);
				if (list==null || list.size() == 0){
					CompareMainResult result=new CompareMainResult();
					result.setRunId(runId);
					result.setSlaveTableName(map.get("name"));
					result.setStatus(Const.STATUS_RUNNING);
					createCompareMainResult(result);
				}
			}
			
		}catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException(e);
		}
	}

	private CompareMainResultDao	compareMainResultDao;
	private CompareSlaveResultService compareSlaveResultService;

	/**
	 * ×¢ÈëDAO
	 * 
	 * @see com.liusy.core.service.CompareMainResultService#setCompareMainResultDao(CompareMainResult
	 *      compareMainResultDao)
	 */
	public void setCompareMainResultDao(CompareMainResultDao compareMainResultDao) {
		this.compareMainResultDao = compareMainResultDao;
	}

	public List<CompareMainResult> getCompareMainResultListByRunId(
			Serializable runId) throws ServiceException {
		
	try {
		return this.compareMainResultDao.findByField(CompareMainResult.PROP_RUN_ID, runId);
	}
	catch (DAOException e) {
		throw new ServiceException(e);
	}

	}

	public void deleleResultByRunIdAndSlaveTableName(Integer runId,
			String slaveTableName) throws ServiceException {
		try {
			Collection<CompareSlaveResult> slaveResults ;
			if (slaveTableName !=null){
				slaveResults = compareSlaveResultService.getCompareSlaveResultByRunIdAndSlaveTableName(runId,slaveTableName);
			}
			else
			{
				slaveResults = compareSlaveResultService.getSlaveResultsByRunId(runId);
			}
				
			Serializable[] toDeleles = new Serializable[slaveResults.size()];
			int index = 0;
			for(CompareSlaveResult result : slaveResults)
			{
				toDeleles[index++] = result.getId();
			}
			compareSlaveResultService.removeCompareSlaveResults(toDeleles);
			
			String[] fieldNames = null;
			Object[] fieldValues = null;
			Collection<CompareMainResult> mainResults = null;
			
			if (slaveTableName !=null){
				fieldNames = new String[]{CompareMainResult.PROP_RUN_ID,CompareMainResult.PROP_SLAVETABLE_NAME};
				fieldValues = new Object[]{runId,slaveTableName};
				mainResults = findMainResultByFields(fieldNames,fieldValues);
			}
			else
			{
				fieldNames = new String[]{CompareMainResult.PROP_RUN_ID};
				fieldValues = new Object[]{runId};
				mainResults = findMainResultByFields(fieldNames, fieldValues);
			}
			
			Serializable[] toDeleles1 = new Serializable[mainResults.size()];
			index = 0;
			for(CompareMainResult result : mainResults)
			{
				toDeleles1[index++] = result.getId();
			}
			this.removeCompareMainResults(toDeleles1);
			
		}
		catch (DAOException e) {
			throw new ServiceException(e);
		}
		
	}

	public void setCompareSlaveResultService(
			CompareSlaveResultService compareSlaveResultService) {
		this.compareSlaveResultService = compareSlaveResultService;
	}
}

