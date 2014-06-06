package com.liusy.datapp.dao.analysis;

import java.io.Serializable;

import com.liusy.dataapplatform.base.dao.BaseDao;
import com.liusy.dataapplatform.base.exception.DAOException;

import com.liusy.datapp.model.analysis.AnalysisTemplateParams;

public interface AnalysisTemplateParamsDao extends BaseDao {

	public AnalysisTemplateParams cast(Object object);
	
	public AnalysisTemplateParams get(Serializable id) throws DAOException;

	public AnalysisTemplateParams load(Serializable id) throws DAOException;	
	
	public Serializable save(AnalysisTemplateParams analysisTemplateParams) throws DAOException;

	public void saveOrUpdate(AnalysisTemplateParams analysisTemplateParams) throws DAOException;

	public void update(AnalysisTemplateParams analysisTemplateParams) throws DAOException;
	
	public void delete(Serializable id) throws DAOException;

	public void refresh(AnalysisTemplateParams analysisTemplateParams) throws DAOException;	
	
}
