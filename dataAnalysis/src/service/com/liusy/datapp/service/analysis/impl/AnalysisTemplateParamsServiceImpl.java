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

import com.liusy.datapp.dao.analysis.AnalysisTemplateParamsDao;
import com.liusy.datapp.model.analysis.AnalysisTemplateParams;
import com.liusy.datapp.service.analysis.AnalysisTemplateParamsService;

public class AnalysisTemplateParamsServiceImpl implements AnalysisTemplateParamsService {
	private static final long serialVersionUID = 1L;

	public AnalysisTemplateParams findAnalysisTemplateParams(Serializable id) throws ServiceException {
		try {
			return analysisTemplateParamsDao.get(id);
		}
		catch (DAOException e) {
			throw new ServiceException(e);
		}
	}

	public void createAnalysisTemplateParams(AnalysisTemplateParams analysisTemplateParams) throws ServiceException {
		try {
			analysisTemplateParamsDao.save(analysisTemplateParams);
		}
		catch (DAOException e) {
			throw new ServiceException(e);
		}
	}

	public void updateAnalysisTemplateParams(AnalysisTemplateParams analysisTemplateParams) throws ServiceException {
		try {
			AnalysisTemplateParams tmp = analysisTemplateParamsDao.get(analysisTemplateParams.getId());
			ConvertUtil.convertToModelForUpdate(tmp, analysisTemplateParams);			
			analysisTemplateParamsDao.update(tmp);
		}
		catch (DAOException e) {
			throw new ServiceException(e);
		}
		catch (Exception e) {
			// TODO: handle exception
			throw new ServiceException(e);
		}
	}

	public void removeAnalysisTemplateParams(Serializable id) throws ServiceException {
		try {
			analysisTemplateParamsDao.delete(id);
		}
		catch (DAOException e) {
			throw new ServiceException(e);
		}
	}

	public void removeAnalysisTemplateParamss(Serializable[] ids) throws ServiceException {
		try {
			analysisTemplateParamsDao.deleteAll(ids);
		}
		catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	public PageQuery queryAnalysisTemplateParams(PageQuery pageQuery) throws ServiceException {
		try {
			PageQuery  query=analysisTemplateParamsDao.queryBySelectId(pageQuery);
			pageQuery.setRecordSet(query.getRecordSet());
			pageQuery.setRecordCount(query.getRecordCount());
			return pageQuery;
		}
		catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	private AnalysisTemplateParamsDao	analysisTemplateParamsDao;

	/**
	 * ×¢ÈëDAO
	 * 
	 * @see com.liusy.core.service.AnalysisTemplateParamsService#setAnalysisTemplateParamsDao(AnalysisTemplateParams
	 *      analysisTemplateParamsDao)
	 */
	public void setAnalysisTemplateParamsDao(AnalysisTemplateParamsDao analysisTemplateParamsDao) {
		this.analysisTemplateParamsDao = analysisTemplateParamsDao;
	}
}

