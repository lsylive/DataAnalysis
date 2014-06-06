package com.liusy.datapp.service.analysis;

import java.io.Serializable;
import java.util.Collection;

import com.liusy.dataapplatform.base.exception.ServiceException;
import com.liusy.dataapplatform.base.util.PagerObject;
import com.liusy.dataapplatform.base.util.PageQuery;

import com.liusy.datapp.dao.analysis.AnalysisDatasetDao;
import com.liusy.datapp.model.analysis.AnalysisDataset;

public interface AnalysisDatasetService {

	public AnalysisDataset findAnalysisDataset(Serializable id) throws ServiceException;

	public void createAnalysisDataset(AnalysisDataset analysisDataset) throws ServiceException;

	public void updateAnalysisDataset(AnalysisDataset analysisDataset) throws ServiceException;

	public void removeAnalysisDataset(Serializable id) throws ServiceException;

	public PageQuery queryAnalysisDataset(PageQuery pageQuery) throws ServiceException;

	public void removeAnalysisDatasets(Serializable[] ids) throws ServiceException;  
}

