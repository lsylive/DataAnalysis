package com.liusy.datapp.service.analysis;

import java.io.Serializable;
import java.util.Collection;

import com.liusy.dataapplatform.base.exception.ServiceException;
import com.liusy.dataapplatform.base.util.PagerObject;
import com.liusy.dataapplatform.base.util.PageQuery;

import com.liusy.datapp.dao.analysis.AnalysisDatasetColumnDao;
import com.liusy.datapp.model.analysis.AnalysisDatasetColumn;

public interface AnalysisDatasetColumnService {

	public AnalysisDatasetColumn findAnalysisDatasetColumn(Serializable id) throws ServiceException;

	public void createAnalysisDatasetColumn(AnalysisDatasetColumn analysisDatasetColumn) throws ServiceException;

	public void updateAnalysisDatasetColumn(AnalysisDatasetColumn analysisDatasetColumn) throws ServiceException;

	public void removeAnalysisDatasetColumn(Serializable id) throws ServiceException;

	public PageQuery queryAnalysisDatasetColumn(PageQuery pageQuery) throws ServiceException;

	public void removeAnalysisDatasetColumns(Serializable[] ids) throws ServiceException;  
}

