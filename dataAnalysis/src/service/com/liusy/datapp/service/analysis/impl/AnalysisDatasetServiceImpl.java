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

import com.liusy.datapp.dao.analysis.AnalysisDatasetDao;
import com.liusy.datapp.model.analysis.AnalysisDataset;
import com.liusy.datapp.service.analysis.AnalysisDatasetService;

public class AnalysisDatasetServiceImpl implements AnalysisDatasetService {
	private static final long serialVersionUID = 1L;

	public AnalysisDataset findAnalysisDataset(Serializable id) throws ServiceException {
		try {
			return analysisDatasetDao.get(id);
		}
		catch (DAOException e) {
			throw new ServiceException(e);
		}
	}

	public void createAnalysisDataset(AnalysisDataset analysisDataset) throws ServiceException {
		try {
			analysisDatasetDao.save(analysisDataset);
		}
		catch (DAOException e) {
			throw new ServiceException(e);
		}
	}

	public void updateAnalysisDataset(AnalysisDataset analysisDataset) throws ServiceException {
		try {
			AnalysisDataset tmp = analysisDatasetDao.get(analysisDataset.getId());
			ConvertUtil.convertToModelForUpdate(tmp, analysisDataset);			
			analysisDatasetDao.update(tmp);
		}
		catch (DAOException e) {
			throw new ServiceException(e);
		}
		catch (Exception e) {
			// TODO: handle exception
			throw new ServiceException(e);
		}
	}

	public void removeAnalysisDataset(Serializable id) throws ServiceException {
		try {
			analysisDatasetDao.delete(id);
		}
		catch (DAOException e) {
			throw new ServiceException(e);
		}
	}

	public void removeAnalysisDatasets(Serializable[] ids) throws ServiceException {
		try {
			analysisDatasetDao.deleteAll(ids);
		}
		catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	public PageQuery queryAnalysisDataset(PageQuery pageQuery) throws ServiceException {
		try {
			PageQuery  query=analysisDatasetDao.queryBySelectId(pageQuery);
			pageQuery.setRecordSet(query.getRecordSet());
			pageQuery.setRecordCount(query.getRecordCount());
			return pageQuery;
		}
		catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	private AnalysisDatasetDao	analysisDatasetDao;

	/**
	 * ×¢ÈëDAO
	 * 
	 * @see com.liusy.core.service.AnalysisDatasetService#setAnalysisDatasetDao(AnalysisDataset
	 *      analysisDatasetDao)
	 */
	public void setAnalysisDatasetDao(AnalysisDatasetDao analysisDatasetDao) {
		this.analysisDatasetDao = analysisDatasetDao;
	}
}

