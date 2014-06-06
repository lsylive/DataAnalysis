package com.liusy.datapp.service.analysis;

import java.io.Serializable;
import java.util.Collection;

import com.liusy.dataapplatform.base.exception.ServiceException;
import com.liusy.dataapplatform.base.util.PagerObject;
import com.liusy.dataapplatform.base.util.PageQuery;

import com.liusy.datapp.dao.analysis.AnalysisTemplateDao;
import com.liusy.datapp.model.analysis.AnalysisTemplate;

public interface AnalysisTemplateService {

	public AnalysisTemplate findAnalysisTemplate(Serializable id) throws ServiceException;

	public void createAnalysisTemplate(AnalysisTemplate analysisTemplate) throws ServiceException;

	public void updateAnalysisTemplate(AnalysisTemplate analysisTemplate) throws ServiceException;

	public void removeAnalysisTemplate(Serializable id) throws ServiceException;

	public PageQuery queryAnalysisTemplate(PageQuery pageQuery) throws ServiceException;

	public void removeAnalysisTemplates(Serializable[] ids) throws ServiceException;  
}

