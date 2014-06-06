package com.liusy.datapp.dao.analysis;

import java.io.Serializable;

import com.liusy.dataapplatform.base.dao.BaseDao;
import com.liusy.dataapplatform.base.exception.DAOException;

import com.liusy.datapp.model.analysis.AnalysisTemplate;

public interface AnalysisTemplateDao extends BaseDao {

	public AnalysisTemplate cast(Object object);
	
	public AnalysisTemplate get(Serializable id) throws DAOException;

	public AnalysisTemplate load(Serializable id) throws DAOException;	
	
	public Serializable save(AnalysisTemplate analysisTemplate) throws DAOException;

	public void saveOrUpdate(AnalysisTemplate analysisTemplate) throws DAOException;

	public void update(AnalysisTemplate analysisTemplate) throws DAOException;
	
	public void delete(Serializable id) throws DAOException;

	public void refresh(AnalysisTemplate analysisTemplate) throws DAOException;	
	
}
