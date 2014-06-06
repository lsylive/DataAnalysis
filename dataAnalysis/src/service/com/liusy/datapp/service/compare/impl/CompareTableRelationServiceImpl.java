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

import com.liusy.datapp.dao.compare.CompareIndicatorDao;
import com.liusy.datapp.dao.compare.CompareTableRelationDao;
import com.liusy.datapp.model.compare.CompareIndicator;
import com.liusy.datapp.model.compare.CompareTableRelation;
import com.liusy.datapp.service.compare.CompareTableRelationService;

public class CompareTableRelationServiceImpl implements CompareTableRelationService {
	private static final long serialVersionUID = 1L;

	public CompareTableRelation findCompareTableRelation(Serializable id) throws ServiceException {
		try {
			return compareTableRelationDao.get(id);
		}
		catch (DAOException e) {
			throw new ServiceException(e);
		}
	}

	public void createCompareTableRelation(CompareTableRelation compareTableRelation) throws ServiceException {
		try {
			compareTableRelationDao.save(compareTableRelation);
		}
		catch (DAOException e) {
			throw new ServiceException(e);
		}
	}

	public void updateCompareTableRelation(CompareTableRelation compareTableRelation) throws ServiceException {
		try {
			CompareTableRelation tmp = compareTableRelationDao.get(compareTableRelation.getId());
			ConvertUtil.convertToModelForUpdate(tmp, compareTableRelation);			
			compareTableRelationDao.update(tmp);
		}
		catch (DAOException e) {
			throw new ServiceException(e);
		}
		catch (Exception e) {
			// TODO: handle exception
			throw new ServiceException(e);
		}
	}

	public void removeCompareTableRelation(Serializable id) throws ServiceException {
		try {
			compareTableRelationDao.delete(id);
		}
		catch (DAOException e) {
			throw new ServiceException(e);
		}
	}

	public void removeCompareTableRelations(Serializable[] ids) throws ServiceException {
		try {
			compareTableRelationDao.deleteAll(ids);
		}
		catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	public PageQuery queryCompareTableRelation(PageQuery pageQuery) throws ServiceException {
		try {
			PageQuery  query=compareTableRelationDao.queryBySelectId(pageQuery);
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
			return compareTableRelationDao.findByField(fieldName, fieldValue);
		}catch (Exception e) {
			throw new ServiceException(e);
		}
	}
	public void modifyCompareTableIndicator(Integer compId,String mainTabId,String slaveTabIds,String indIds) throws ServiceException{
		try{
			compareIndicatorDao.deleteByField(CompareIndicator.PROP_COMP_ID, compId);
			compareTableRelationDao.deleteByField(CompareTableRelation.PROP_COMP_ID, compId);
			//Add main
			CompareTableRelation main=new CompareTableRelation();
			main.setCompId(compId);
			main.setDtId(Integer.valueOf(mainTabId));
			
			compareTableRelationDao.save(main);
			String[] slaveIds=slaveTabIds.split(",");
			for(int i=0;i<slaveIds.length;i++){
				CompareTableRelation slave=new CompareTableRelation();
				slave.setCompId(compId);
				slave.setDtId(Integer.valueOf(slaveIds[i]));
				
				compareTableRelationDao.save(slave);
			}
			String[] indArr=indIds.split(",");
			for(int j=0;j<indArr.length;j++){
				CompareIndicator ind=new CompareIndicator();
				ind.setCompId(compId);
				ind.setIndId(Integer.valueOf(indArr[j]));
				compareIndicatorDao.save(ind);
			}
		}catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	private CompareTableRelationDao	compareTableRelationDao;
	private CompareIndicatorDao compareIndicatorDao;

	/**
	 * ×¢ÈëDAO
	 * 
	 * @see com.liusy.core.service.CompareTableRelationService#setCompareTableRelationDao(CompareTableRelation
	 *      compareTableRelationDao)
	 */
	public void setCompareTableRelationDao(CompareTableRelationDao compareTableRelationDao) {
		this.compareTableRelationDao = compareTableRelationDao;
	}

	public void setCompareIndicatorDao(CompareIndicatorDao compareIndicatorDao) {
		this.compareIndicatorDao = compareIndicatorDao;
	}

	public void removeCompareTableRelationByCompareId(Serializable id)
			throws ServiceException {
		this.compareTableRelationDao.deleteByField(CompareTableRelation.PROP_COMP_ID, Integer.parseInt(String.valueOf(id)));
		
	}

	public CompareTableRelation getCompareTableRelationByCompIdAndDtId(
			Integer compId, Integer dtId) throws ServiceException {
		try {
			String[] fieldNames = new String[]{CompareTableRelation.PROP_COMP_ID,CompareTableRelation.PROP_DT_ID};
			Object[] fieldValues = new Object[]{compId,dtId};
			List<CompareTableRelation> list = compareTableRelationDao.findByFields(fieldNames, fieldValues);
			if (list!=null && list.size()>0)
			{
				return list.get(0);
			}
		}
		catch (DAOException e) {
			throw new ServiceException(e);
		}
		return null;
	}
}

