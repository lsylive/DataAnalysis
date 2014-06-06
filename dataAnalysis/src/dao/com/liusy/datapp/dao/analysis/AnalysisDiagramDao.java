package com.liusy.datapp.dao.analysis;

import java.io.Serializable;

import com.liusy.dataapplatform.base.dao.BaseDao;
import com.liusy.dataapplatform.base.exception.DAOException;
import com.liusy.datapp.model.analysis.AnalysisDiagram;

public interface AnalysisDiagramDao extends BaseDao {

	public AnalysisDiagram cast(Object object);
	
	public AnalysisDiagram get(Serializable id) throws DAOException;

	public AnalysisDiagram load(Serializable id) throws DAOException;	
	
	public Serializable save(AnalysisDiagram analysisDiagram) throws DAOException;

	public void saveOrUpdate(AnalysisDiagram analysisDiagram) throws DAOException;

	public void update(AnalysisDiagram analysisDiagram) throws DAOException;
	
	public void delete(Serializable id) throws DAOException;

	public void refresh(AnalysisDiagram analysisDiagram) throws DAOException;	
}
