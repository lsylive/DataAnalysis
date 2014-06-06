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

import com.liusy.datapp.dao.compare.CompareFilterDao;
import com.liusy.datapp.model.compare.CompareFilter;
import com.liusy.datapp.service.compare.CompareFilterService;

public class CompareFilterServiceImpl implements CompareFilterService {
	private static final long serialVersionUID = 1L;

	public CompareFilter findCompareFilter(Serializable id) throws ServiceException {
		try {
			return compareFilterDao.get(id);
		}
		catch (DAOException e) {
			throw new ServiceException(e);
		}
	}

	public void createCompareFilter(CompareFilter compareFilter) throws ServiceException {
		try {
			compareFilterDao.save(compareFilter);
		}
		catch (DAOException e) {
			throw new ServiceException(e);
		}
	}

	public void updateCompareFilter(CompareFilter compareFilter) throws ServiceException {
		try {
			CompareFilter tmp = compareFilterDao.get(compareFilter.getId());
			ConvertUtil.convertToModelForUpdate(tmp, compareFilter);			
			compareFilterDao.update(tmp);
		}
		catch (DAOException e) {
			throw new ServiceException(e);
		}
		catch (Exception e) {
			// TODO: handle exception
			throw new ServiceException(e);
		}
	}

	public void removeCompareFilter(Serializable id) throws ServiceException {
		try {
			compareFilterDao.delete(id);
		}
		catch (DAOException e) {
			throw new ServiceException(e);
		}
	}

	public void removeCompareFilters(Serializable[] ids) throws ServiceException {
		try {
			compareFilterDao.deleteAll(ids);
		}
		catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	public PageQuery queryCompareFilter(PageQuery pageQuery) throws ServiceException {
		try {
			PageQuery  query=compareFilterDao.queryBySelectId(pageQuery);
			pageQuery.setRecordSet(query.getRecordSet());
			pageQuery.setRecordCount(query.getRecordCount());
			return pageQuery;
		}
		catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	private CompareFilterDao	compareFilterDao;

	/**
	 * ×¢ÈëDAO
	 * 
	 * @see com.liusy.core.service.CompareFilterService#setCompareFilterDao(CompareFilter
	 *      compareFilterDao)
	 */
	public void setCompareFilterDao(CompareFilterDao compareFilterDao) {
		this.compareFilterDao = compareFilterDao;
	}

	public void removeCompareFiltersByCompId(Serializable compId)
			throws ServiceException {
		this.compareFilterDao.deleteByField(CompareFilter.PROP_COMP_ID, Integer.parseInt(compId.toString()));
		
	}

	public Collection<CompareFilter> findByField(String fieldName,
			Object fieldValue) throws ServiceException {
		
		return this.compareFilterDao.findByField(fieldName, fieldValue);
	}
}

