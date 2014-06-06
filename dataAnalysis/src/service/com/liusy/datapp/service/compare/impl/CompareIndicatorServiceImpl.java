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
import com.liusy.datapp.model.compare.CompareIndicator;
import com.liusy.datapp.service.compare.CompareIndicatorService;

public class CompareIndicatorServiceImpl implements CompareIndicatorService {
	private static final long serialVersionUID = 1L;

	public CompareIndicator findCompareIndicator(Serializable id) throws ServiceException {
		try {
			return compareIndicatorDao.get(id);
		}
		catch (DAOException e) {
			throw new ServiceException(e);
		}
	}

	public void createCompareIndicator(CompareIndicator compareIndicator) throws ServiceException {
		try {
			compareIndicatorDao.save(compareIndicator);
		}
		catch (DAOException e) {
			throw new ServiceException(e);
		}
	}

	public void updateCompareIndicator(CompareIndicator compareIndicator) throws ServiceException {
		try {
			CompareIndicator tmp = compareIndicatorDao.get(compareIndicator.getId());
			ConvertUtil.convertToModelForUpdate(tmp, compareIndicator);			
			compareIndicatorDao.update(tmp);
		}
		catch (DAOException e) {
			throw new ServiceException(e);
		}
		catch (Exception e) {
			// TODO: handle exception
			throw new ServiceException(e);
		}
	}

	public void removeCompareIndicator(Serializable id) throws ServiceException {
		try {
			compareIndicatorDao.delete(id);
		}
		catch (DAOException e) {
			throw new ServiceException(e);
		}
	}

	public void removeCompareIndicators(Serializable[] ids) throws ServiceException {
		try {
			compareIndicatorDao.deleteAll(ids);
		}
		catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	public PageQuery queryCompareIndicator(PageQuery pageQuery) throws ServiceException {
		try {
			PageQuery  query=compareIndicatorDao.queryBySelectId(pageQuery);
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
			return compareIndicatorDao.findByField(fieldName, fieldValue);
		}catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	private CompareIndicatorDao	compareIndicatorDao;

	/**
	 * ×¢ÈëDAO
	 * 
	 * @see com.liusy.core.service.CompareIndicatorService#setCompareIndicatorDao(CompareIndicator
	 *      compareIndicatorDao)
	 */
	public void setCompareIndicatorDao(CompareIndicatorDao compareIndicatorDao) {
		this.compareIndicatorDao = compareIndicatorDao;
	}
}

