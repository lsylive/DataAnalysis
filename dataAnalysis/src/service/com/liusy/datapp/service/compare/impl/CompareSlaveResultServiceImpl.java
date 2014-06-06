package com.liusy.datapp.service.compare.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.hibernate.criterion.Order;

import com.liusy.dataapplatform.base.exception.DAOException;
import com.liusy.dataapplatform.base.exception.ServiceException;
import com.liusy.dataapplatform.base.util.Condition;
import com.liusy.dataapplatform.base.util.ConvertUtil;
import com.liusy.dataapplatform.base.util.ICondition;
import com.liusy.dataapplatform.base.util.PageQuery;

import com.liusy.datapp.dao.compare.CompareSlaveResultDao;
import com.liusy.datapp.model.compare.CompareRunInfo;
import com.liusy.datapp.model.compare.CompareSlaveResult;
import com.liusy.datapp.service.compare.CompareRunInfoService;
import com.liusy.datapp.service.compare.CompareSlaveResultService;

public class CompareSlaveResultServiceImpl implements CompareSlaveResultService {
	private static final long serialVersionUID = 1L;

	public CompareSlaveResult findCompareSlaveResult(Serializable id) throws ServiceException {
		try {
			return compareSlaveResultDao.get(id);
		}
		catch (DAOException e) {
			throw new ServiceException(e);
		}
	}

	public void createCompareSlaveResult(CompareSlaveResult compareSlaveResult) throws ServiceException {
		try {
			compareSlaveResultDao.save(compareSlaveResult);
		}
		catch (DAOException e) {
			throw new ServiceException(e);
		}
	}

	public void updateCompareSlaveResult(CompareSlaveResult compareSlaveResult) throws ServiceException {
		try {
			CompareSlaveResult tmp = compareSlaveResultDao.get(compareSlaveResult.getId());
			ConvertUtil.convertToModelForUpdate(tmp, compareSlaveResult);			
			compareSlaveResultDao.update(tmp);
		}
		catch (DAOException e) {
			throw new ServiceException(e);
		}
		catch (Exception e) {
			// TODO: handle exception
			throw new ServiceException(e);
		}
	}

	public void removeCompareSlaveResult(Serializable id) throws ServiceException {
		try {
			compareSlaveResultDao.delete(id);
		}
		catch (DAOException e) {
			throw new ServiceException(e);
		}
	}

	public void removeCompareSlaveResults(Serializable[] ids) throws ServiceException {
		try {
			compareSlaveResultDao.deleteAll(ids);
		}
		catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	public PageQuery queryCompareSlaveResult(PageQuery pageQuery) throws ServiceException {
		try {
			PageQuery  query=compareSlaveResultDao.queryBySelectId(pageQuery);
			pageQuery.setRecordSet(query.getRecordSet());
			pageQuery.setRecordCount(query.getRecordCount());
			return pageQuery;
		}
		catch (Exception e) {
			throw new ServiceException(e);
		}
	}
	
	public List<CompareSlaveResult> getSlaveResultsByRunId(Integer runId)	throws ServiceException {
		try{
			List<CompareSlaveResult> results = this.compareSlaveResultDao.findByField(CompareSlaveResult.PROP_RUN_ID, runId);
			return results;
		}
		catch(Exception e)
		{
			throw new ServiceException(e);
		}
}
	public int getSlaveResultsCountByRunId(Integer runId)	throws ServiceException {
		try{
			//List<CompareSlaveResult> results = this.compareSlaveResultDao.findByField(CompareSlaveResult.PROP_RUN_ID, runId);
			List<Map> results =compareSlaveResultDao.queryBySql("select sum(count) pMatchCount from t_compare_slaveresult t where run_id="+runId);
			int pMatchCount = 0;
			if (results.get(0).get("pMatchCount")!=null && results.get(0).get("pMatchCount").toString().length() > 0)
			{
				pMatchCount = Integer.parseInt(results.get(0).get("pMatchCount").toString());
			}
			return pMatchCount;
		}
		catch(Exception e)
		{
			throw new ServiceException(e);
		}
}


	private CompareSlaveResultDao	compareSlaveResultDao;


	/**
	 * ×¢ÈëDAO
	 * 
	 * @see com.liusy.core.service.CompareSlaveResultService#setCompareSlaveResultDao(CompareSlaveResult
	 *      compareSlaveResultDao)
	 */
	public void setCompareSlaveResultDao(CompareSlaveResultDao compareSlaveResultDao) {
		this.compareSlaveResultDao = compareSlaveResultDao;
	}

	public Collection<Serializable> getMainPkIdsByRunIdAndSlaveTableName(Integer runId,String slaveTableName) throws ServiceException
	{
		try{
			String[] fieldNames = new String[]{CompareSlaveResult.PROP_RUN_ID,CompareSlaveResult.PROP_SLAVETABLE_NAME};
			Object[] fieldValues = new Object[]{runId,slaveTableName};
			List<CompareSlaveResult> results = compareSlaveResultDao.findByFields(fieldNames,fieldValues);
			//List results  =compareSlaveResultDao.queryBySql("select count(id) count from "+compareSlaveResultDao.getTableName()+" where run_Id="+runId+" and slaveTable_Name='"+slaveTableName+"'");
			
			Collection<Serializable> mainPkIds = new LinkedHashSet<Serializable>();
			for(CompareSlaveResult result : results)
			{
				if (result.getPkId()!=null)
				{
					mainPkIds.add(result.getPkId());
				}
			}
			return mainPkIds;
			//return results;
		}
		catch(Exception e)
		{
			throw new ServiceException(e);
		}
		
	}
	public String getMainPkIdsByRunIdAndSlaveTableNameCount(Integer runId,String slaveTableName) throws ServiceException
	{
		try{
			String[] fieldNames = new String[]{CompareSlaveResult.PROP_RUN_ID,CompareSlaveResult.PROP_SLAVETABLE_NAME};
			Object[] fieldValues = new Object[]{runId,slaveTableName};
			List<Map> results  =compareSlaveResultDao.queryBySql("select count(id) count from "+compareSlaveResultDao.getTableName()+" where run_Id="+runId+" and slaveTable_Name='"+slaveTableName+"'");
			return results.get(0).get("count").toString();
		}
		catch(Exception e)
		{
			throw new ServiceException(e);
		}
		
	}
	public String[] getSlavePkIdsArrByRunIdAndSlaveTableNameAndPkId(Integer runId,
			String slaveTableName, Integer pkId) throws ServiceException {
		try{
			String[] fieldNames = new String[]{CompareSlaveResult.PROP_RUN_ID,CompareSlaveResult.PROP_SLAVETABLE_NAME,CompareSlaveResult.PROP_PK_ID};
			Object[] fieldValues = new Object[]{runId,slaveTableName,pkId};
			List<CompareSlaveResult> results = compareSlaveResultDao.findByFields(fieldNames,fieldValues);
			
			String[] slavePkIdsArr = new String[results.size()];
			int index = 0;
			for(CompareSlaveResult result : results)
			{
				if (result.getSlavePk()!=null)
				{
					slavePkIdsArr[index] = result.getSlavePk();
				}
				index ++;
			}
			
			return slavePkIdsArr;
		}
		catch(Exception e)
		{
			throw new ServiceException(e);
		}
		
		
	}

	public Collection<CompareSlaveResult> getCompareSlaveResultByRunIdAndSlaveTableName(
			Integer runId, String slaveTableName) throws ServiceException {
		try{
			String[] fieldNames = new String[]{CompareSlaveResult.PROP_RUN_ID,CompareSlaveResult.PROP_SLAVETABLE_NAME};
			Object[] fieldValues = new Object[]{runId,slaveTableName};
			List<CompareSlaveResult> results = compareSlaveResultDao.findByFields(fieldNames,fieldValues);
			return results;
		}
		catch(Exception e)
		{
			throw new ServiceException(e);
		}
	}




}

