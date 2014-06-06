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

import com.liusy.datapp.dao.analysis.AnalysisTemplateDao;
import com.liusy.datapp.model.analysis.AnalysisTemplate;
import com.liusy.datapp.service.analysis.AnalysisTemplateService;

public class AnalysisTemplateServiceImpl implements AnalysisTemplateService {
	private static final long serialVersionUID = 1L;

	public AnalysisTemplate findAnalysisTemplate(Serializable id) throws ServiceException {
		try {
			return analysisTemplateDao.get(id);
		}
		catch (DAOException e) {
			throw new ServiceException(e);
		}
	}

	public void createAnalysisTemplate(AnalysisTemplate analysisTemplate) throws ServiceException {
		try {
			analysisTemplateDao.save(analysisTemplate);
		}
		catch (DAOException e) {
			throw new ServiceException(e);
		}
	}

	public void updateAnalysisTemplate(AnalysisTemplate analysisTemplate) throws ServiceException {
		try {
			AnalysisTemplate tmp = analysisTemplateDao.get(analysisTemplate.getId());
			ConvertUtil.convertToModelForUpdate(tmp, analysisTemplate);			
			analysisTemplateDao.update(tmp);
		}
		catch (DAOException e) {
			throw new ServiceException(e);
		}
		catch (Exception e) {
			// TODO: handle exception
			throw new ServiceException(e);
		}
	}

	public void removeAnalysisTemplate(Serializable id) throws ServiceException {
		try {
			analysisTemplateDao.delete(id);
		}
		catch (DAOException e) {
			throw new ServiceException(e);
		}
	}

	public void removeAnalysisTemplates(Serializable[] ids) throws ServiceException {
		try {
			analysisTemplateDao.deleteAll(ids);
		}
		catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	public PageQuery queryAnalysisTemplate(PageQuery pageQuery) throws ServiceException {
		try {
			PageQuery  query=analysisTemplateDao.queryBySelectId(pageQuery);
			pageQuery.setRecordSet(query.getRecordSet());
			pageQuery.setRecordCount(query.getRecordCount());
			return pageQuery;
		}
		catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	private AnalysisTemplateDao	analysisTemplateDao;

	/**
	 * ×¢ÈëDAO
	 * 
	 * @see com.liusy.core.service.AnalysisTemplateService#setAnalysisTemplateDao(AnalysisTemplate
	 *      analysisTemplateDao)
	 */
	public void setAnalysisTemplateDao(AnalysisTemplateDao analysisTemplateDao) {
		this.analysisTemplateDao = analysisTemplateDao;
	}
}

