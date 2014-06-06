package com.liusy.datapp.dao.analysis;

import java.io.Serializable;

import com.liusy.dataapplatform.base.dao.BaseDao;
import com.liusy.dataapplatform.base.exception.DAOException;

import com.liusy.datapp.model.analysis.AnalysisDataset;

public interface AnalysisDatasetDao extends BaseDao {

	public AnalysisDataset cast(Object object);
	
	public AnalysisDataset get(Serializable id) throws DAOException;

	public AnalysisDataset load(Serializable id) throws DAOException;	
	
	public Serializable save(AnalysisDataset analysisDataset) throws DAOException;

	public void saveOrUpdate(AnalysisDataset analysisDataset) throws DAOException;

	public void update(AnalysisDataset analysisDataset) throws DAOException;
	
	public void delete(Serializable id) throws DAOException;

	public void refresh(AnalysisDataset analysisDataset) throws DAOException;	
	
}
