package com.liusy.datapp.service.analysis;

import java.io.Serializable;
import java.util.Collection;

import com.liusy.dataapplatform.base.exception.ServiceException;
import com.liusy.dataapplatform.base.util.PagerObject;
import com.liusy.dataapplatform.base.util.PageQuery;

import com.liusy.datapp.dao.analysis.AnalysisTemplateConfigDao;
import com.liusy.datapp.model.analysis.AnalysisTemplateConfig;

public interface AnalysisTemplateConfigService {

	public AnalysisTemplateConfig findAnalysisTemplateConfig(Serializable id) throws ServiceException;

	public void createAnalysisTemplateConfig(AnalysisTemplateConfig analysisTemplateConfig) throws ServiceException;

	public void updateAnalysisTemplateConfig(AnalysisTemplateConfig analysisTemplateConfig) throws ServiceException;

	public void removeAnalysisTemplateConfig(Serializable id) throws ServiceException;

	public PageQuery queryAnalysisTemplateConfig(PageQuery pageQuery) throws ServiceException;

	public void removeAnalysisTemplateConfigs(Serializable[] ids) throws ServiceException;  
}

