package com.liusy.datapp.service.analysis;

import java.io.Serializable;
import java.util.Collection;

import com.liusy.dataapplatform.base.exception.ServiceException;
import com.liusy.dataapplatform.base.util.PagerObject;
import com.liusy.dataapplatform.base.util.PageQuery;

import com.liusy.datapp.dao.analysis.AnalysisDatasetConditionDao;
import com.liusy.datapp.model.analysis.AnalysisDatasetCondition;

public interface AnalysisDatasetConditionService {

	public AnalysisDatasetCondition findAnalysisDatasetCondition(Serializable id) throws ServiceException;

	public void createAnalysisDatasetCondition(AnalysisDatasetCondition analysisDatasetCondition) throws ServiceException;

	public void updateAnalysisDatasetCondition(AnalysisDatasetCondition analysisDatasetCondition) throws ServiceException;

	public void removeAnalysisDatasetCondition(Serializable id) throws ServiceException;

	public PageQuery queryAnalysisDatasetCondition(PageQuery pageQuery) throws ServiceException;

	public void removeAnalysisDatasetConditions(Serializable[] ids) throws ServiceException;  
}

