package com.liusy.datapp.dao.analysis.base;

import java.io.Serializable;

import org.hibernate.criterion.Order;

import com.liusy.dataapplatform.base.dao.HibernateDao;
import com.liusy.dataapplatform.base.exception.DAOException;
import com.liusy.datapp.model.analysis.AnalysisDiagram;

public abstract class BaseAnalysisDiagramDaoImpl extends HibernateDao {
   private static final long serialVersionUID = 1L;


	public Class getReferenceClass() {
		return AnalysisDiagram.class;
	}

	public Order getDefaultOrder() {
		return null;
	}

	public AnalysisDiagram cast(Object object) {
		return (AnalysisDiagram) object;
	}

	public AnalysisDiagram get(Serializable id)  throws DAOException{
		return (AnalysisDiagram) super.get(id);
	}

	public AnalysisDiagram load(Serializable id)  throws DAOException{
		return (AnalysisDiagram) super.load(id);
	}

	public Serializable save(AnalysisDiagram analysisDiagram)  throws DAOException{
		return super.save(analysisDiagram);
	}

	public void saveOrUpdate(AnalysisDiagram analysisDiagram)  throws DAOException{
		super.saveOrUpdate(analysisDiagram);
	}

	public void update(AnalysisDiagram analysisDiagram)  throws DAOException{
		super.update(analysisDiagram);
	}

	public void delete(AnalysisDiagram analysisDiagram)  throws DAOException{
		super.delete(analysisDiagram);
	}

	public void refresh(AnalysisDiagram analysisDiagram)  throws DAOException{
		super.refresh(analysisDiagram);
	}

	public String getTableName() {
		return AnalysisDiagram.REF_TABLE;
	}
	
}
