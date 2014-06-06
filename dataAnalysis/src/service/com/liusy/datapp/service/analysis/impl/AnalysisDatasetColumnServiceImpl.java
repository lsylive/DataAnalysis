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

import com.liusy.datapp.dao.analysis.AnalysisDatasetColumnDao;
import com.liusy.datapp.model.analysis.AnalysisDatasetColumn;
import com.liusy.datapp.service.analysis.AnalysisDatasetColumnService;

public class AnalysisDatasetColumnServiceImpl implements AnalysisDatasetColumnService {
	private static final long serialVersionUID = 1L;

	public AnalysisDatasetColumn findAnalysisDatasetColumn(Serializable id) throws ServiceException {
		try {
			return analysisDatasetColumnDao.get(id);
		}
		catch (DAOException e) {
			throw new ServiceException(e);
		}
	}

	public void createAnalysisDatasetColumn(AnalysisDatasetColumn analysisDatasetColumn) throws ServiceException {
		try {
			analysisDatasetColumnDao.save(analysisDatasetColumn);
		}
		catch (DAOException e) {
			throw new ServiceException(e);
		}
	}

	public void updateAnalysisDatasetColumn(AnalysisDatasetColumn analysisDatasetColumn) throws ServiceException {
		try {
			AnalysisDatasetColumn tmp = analysisDatasetColumnDao.get(analysisDatasetColumn.getId());
			ConvertUtil.convertToModelForUpdate(tmp, analysisDatasetColumn);			
			analysisDatasetColumnDao.update(tmp);
		}
		catch (DAOException e) {
			throw new ServiceException(e);
		}
		catch (Exception e) {
			// TODO: handle exception
			throw new ServiceException(e);
		}
	}

	public void removeAnalysisDatasetColumn(Serializable id) throws ServiceException {
		try {
			analysisDatasetColumnDao.delete(id);
		}
		catch (DAOException e) {
			throw new ServiceException(e);
		}
	}

	public void removeAnalysisDatasetColumns(Serializable[] ids) throws ServiceException {
		try {
			analysisDatasetColumnDao.deleteAll(ids);
		}
		catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	public PageQuery queryAnalysisDatasetColumn(PageQuery pageQuery) throws ServiceException {
		try {
			PageQuery  query=analysisDatasetColumnDao.queryBySelectId(pageQuery);
			pageQuery.setRecordSet(query.getRecordSet());
			pageQuery.setRecordCount(query.getRecordCount());
			return pageQuery;
		}
		catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	private AnalysisDatasetColumnDao	analysisDatasetColumnDao;

	/**
	 * ×¢ÈëDAO
	 * 
	 * @see com.liusy.core.service.AnalysisDatasetColumnService#setAnalysisDatasetColumnDao(AnalysisDatasetColumn
	 *      analysisDatasetColumnDao)
	 */
	public void setAnalysisDatasetColumnDao(AnalysisDatasetColumnDao analysisDatasetColumnDao) {
		this.analysisDatasetColumnDao = analysisDatasetColumnDao;
	}
}

