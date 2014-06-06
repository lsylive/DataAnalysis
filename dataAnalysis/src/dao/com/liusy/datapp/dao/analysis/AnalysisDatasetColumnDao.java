package com.liusy.datapp.dao.analysis;

import java.io.Serializable;

import com.liusy.dataapplatform.base.dao.BaseDao;
import com.liusy.dataapplatform.base.exception.DAOException;

import com.liusy.datapp.model.analysis.AnalysisDatasetColumn;

public interface AnalysisDatasetColumnDao extends BaseDao {

	public AnalysisDatasetColumn cast(Object object);
	
	public AnalysisDatasetColumn get(Serializable id) throws DAOException;

	public AnalysisDatasetColumn load(Serializable id) throws DAOException;	
	
	public Serializable save(AnalysisDatasetColumn analysisDatasetColumn) throws DAOException;

	public void saveOrUpdate(AnalysisDatasetColumn analysisDatasetColumn) throws DAOException;

	public void update(AnalysisDatasetColumn analysisDatasetColumn) throws DAOException;
	
	public void delete(Serializable id) throws DAOException;

	public void refresh(AnalysisDatasetColumn analysisDatasetColumn) throws DAOException;	
	
}
