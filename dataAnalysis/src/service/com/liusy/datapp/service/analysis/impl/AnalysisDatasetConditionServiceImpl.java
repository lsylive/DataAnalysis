package com.liusy.datapp.service.analysis.impl;

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

import com.liusy.datapp.dao.analysis.AnalysisDatasetConditionDao;
import com.liusy.datapp.model.analysis.AnalysisDatasetCondition;
import com.liusy.datapp.service.analysis.AnalysisDatasetConditionService;

public class AnalysisDatasetConditionServiceImpl implements AnalysisDatasetConditionService {
	private static final long serialVersionUID = 1L;

	public AnalysisDatasetCondition findAnalysisDatasetCondition(Serializable id) throws ServiceException {
		try {
			return analysisDatasetConditionDao.get(id);
		}
		catch (DAOException e) {
			throw new ServiceException(e);
		}
	}

	public void createAnalysisDatasetCondition(AnalysisDatasetCondition analysisDatasetCondition) throws ServiceException {
		try {
			analysisDatasetConditionDao.save(analysisDatasetCondition);
		}
		catch (DAOException e) {
			throw new ServiceException(e);
		}
	}

	public void updateAnalysisDatasetCondition(AnalysisDatasetCondition analysisDatasetCondition) throws ServiceException {
		try {
			AnalysisDatasetCondition tmp = analysisDatasetConditionDao.get(analysisDatasetCondition.getId());
			ConvertUtil.convertToModelForUpdate(tmp, analysisDatasetCondition);			
			analysisDatasetConditionDao.update(tmp);
		}
		catch (DAOException e) {
			throw new ServiceException(e);
		}
		catch (Exception e) {
			// TODO: handle exception
			throw new ServiceException(e);
		}
	}

	public void removeAnalysisDatasetCondition(Serializable id) throws ServiceException {
		try {
			analysisDatasetConditionDao.delete(id);
		}
		catch (DAOException e) {
			throw new ServiceException(e);
		}
	}

	public void removeAnalysisDatasetConditions(Serializable[] ids) throws ServiceException {
		try {
			analysisDatasetConditionDao.deleteAll(ids);
		}
		catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	public PageQuery queryAnalysisDatasetCondition(PageQuery pageQuery) throws ServiceException {
		try {
			PageQuery  query=analysisDatasetConditionDao.queryBySelectId(pageQuery);
			pageQuery.setRecordSet(query.getRecordSet());
			pageQuery.setRecordCount(query.getRecordCount());
			return pageQuery;
		}
		catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	private AnalysisDatasetConditionDao	analysisDatasetConditionDao;

	/**
	 * ×¢ÈëDAO
	 * 
	 * @see com.liusy.core.service.AnalysisDatasetConditionService#setAnalysisDatasetConditionDao(AnalysisDatasetCondition
	 *      analysisDatasetConditionDao)
	 */
	public void setAnalysisDatasetConditionDao(AnalysisDatasetConditionDao analysisDatasetConditionDao) {
		this.analysisDatasetConditionDao = analysisDatasetConditionDao;
	}
}

