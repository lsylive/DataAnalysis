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

import com.liusy.datapp.dao.analysis.AnalysisTemplateConfigDao;
import com.liusy.datapp.model.analysis.AnalysisTemplateConfig;
import com.liusy.datapp.service.analysis.AnalysisTemplateConfigService;

public class AnalysisTemplateConfigServiceImpl implements AnalysisTemplateConfigService {
	private static final long serialVersionUID = 1L;

	public AnalysisTemplateConfig findAnalysisTemplateConfig(Serializable id) throws ServiceException {
		try {
			return analysisTemplateConfigDao.get(id);
		}
		catch (DAOException e) {
			throw new ServiceException(e);
		}
	}

	public void createAnalysisTemplateConfig(AnalysisTemplateConfig analysisTemplateConfig) throws ServiceException {
		try {
			analysisTemplateConfigDao.save(analysisTemplateConfig);
		}
		catch (DAOException e) {
			throw new ServiceException(e);
		}
	}

	public void updateAnalysisTemplateConfig(AnalysisTemplateConfig analysisTemplateConfig) throws ServiceException {
		try {
			AnalysisTemplateConfig tmp = analysisTemplateConfigDao.get(analysisTemplateConfig.getId());
			ConvertUtil.convertToModelForUpdate(tmp, analysisTemplateConfig);			
			analysisTemplateConfigDao.update(tmp);
		}
		catch (DAOException e) {
			throw new ServiceException(e);
		}
		catch (Exception e) {
			// TODO: handle exception
			throw new ServiceException(e);
		}
	}

	public void removeAnalysisTemplateConfig(Serializable id) throws ServiceException {
		try {
			analysisTemplateConfigDao.delete(id);
		}
		catch (DAOException e) {
			throw new ServiceException(e);
		}
	}

	public void removeAnalysisTemplateConfigs(Serializable[] ids) throws ServiceException {
		try {
			analysisTemplateConfigDao.deleteAll(ids);
		}
		catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	public PageQuery queryAnalysisTemplateConfig(PageQuery pageQuery) throws ServiceException {
		try {
			PageQuery  query=analysisTemplateConfigDao.queryBySelectId(pageQuery);
			pageQuery.setRecordSet(query.getRecordSet());
			pageQuery.setRecordCount(query.getRecordCount());
			return pageQuery;
		}
		catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	private AnalysisTemplateConfigDao	analysisTemplateConfigDao;

	/**
	 * ×¢ÈëDAO
	 * 
	 * @see com.liusy.core.service.AnalysisTemplateConfigService#setAnalysisTemplateConfigDao(AnalysisTemplateConfig
	 *      analysisTemplateConfigDao)
	 */
	public void setAnalysisTemplateConfigDao(AnalysisTemplateConfigDao analysisTemplateConfigDao) {
		this.analysisTemplateConfigDao = analysisTemplateConfigDao;
	}
}

