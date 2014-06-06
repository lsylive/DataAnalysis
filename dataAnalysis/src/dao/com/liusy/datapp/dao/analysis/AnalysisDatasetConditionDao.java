package com.liusy.datapp.dao.analysis;

import java.io.Serializable;

import com.liusy.dataapplatform.base.dao.BaseDao;
import com.liusy.dataapplatform.base.exception.DAOException;

import com.liusy.datapp.model.analysis.AnalysisDatasetCondition;

public interface AnalysisDatasetConditionDao extends BaseDao {

	public AnalysisDatasetCondition cast(Object object);
	
	public AnalysisDatasetCondition get(Serializable id) throws DAOException;

	public AnalysisDatasetCondition load(Serializable id) throws DAOException;	
	
	public Serializable save(AnalysisDatasetCondition analysisDatasetCondition) throws DAOException;

	public void saveOrUpdate(AnalysisDatasetCondition analysisDatasetCondition) throws DAOException;

	public void update(AnalysisDatasetCondition analysisDatasetCondition) throws DAOException;
	
	public void delete(Serializable id) throws DAOException;

	public void refresh(AnalysisDatasetCondition analysisDatasetCondition) throws DAOException;	
	
}
