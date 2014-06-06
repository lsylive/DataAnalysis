package com.liusy.datapp.service.analysis;

import java.io.Serializable;
import java.util.Collection;

import com.liusy.dataapplatform.base.exception.ServiceException;
import com.liusy.dataapplatform.base.util.PagerObject;
import com.liusy.dataapplatform.base.util.PageQuery;

import com.liusy.datapp.dao.analysis.AnalysisTemplateParamsDao;
import com.liusy.datapp.model.analysis.AnalysisTemplateParams;

public interface AnalysisTemplateParamsService {

	public AnalysisTemplateParams findAnalysisTemplateParams(Serializable id) throws ServiceException;

	public void createAnalysisTemplateParams(AnalysisTemplateParams analysisTemplateParams) throws ServiceException;

	public void updateAnalysisTemplateParams(AnalysisTemplateParams analysisTemplateParams) throws ServiceException;

	public void removeAnalysisTemplateParams(Serializable id) throws ServiceException;

	public PageQuery queryAnalysisTemplateParams(PageQuery pageQuery) throws ServiceException;

	public void removeAnalysisTemplateParamss(Serializable[] ids) throws ServiceException;  
}

