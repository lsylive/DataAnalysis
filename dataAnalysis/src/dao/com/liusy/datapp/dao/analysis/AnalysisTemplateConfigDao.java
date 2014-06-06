package com.liusy.datapp.dao.analysis;

import java.io.Serializable;

import com.liusy.dataapplatform.base.dao.BaseDao;
import com.liusy.dataapplatform.base.exception.DAOException;

import com.liusy.datapp.model.analysis.AnalysisTemplateConfig;

public interface AnalysisTemplateConfigDao extends BaseDao {

	public AnalysisTemplateConfig cast(Object object);
	
	public AnalysisTemplateConfig get(Serializable id) throws DAOException;

	public AnalysisTemplateConfig load(Serializable id) throws DAOException;	
	
	public Serializable save(AnalysisTemplateConfig analysisTemplateConfig) throws DAOException;

	public void saveOrUpdate(AnalysisTemplateConfig analysisTemplateConfig) throws DAOException;

	public void update(AnalysisTemplateConfig analysisTemplateConfig) throws DAOException;
	
	public void delete(Serializable id) throws DAOException;

	public void refresh(AnalysisTemplateConfig analysisTemplateConfig) throws DAOException;	
	
}
