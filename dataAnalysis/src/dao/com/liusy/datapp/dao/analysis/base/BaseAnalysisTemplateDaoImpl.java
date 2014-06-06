package com.liusy.datapp.dao.analysis.base;

import java.io.Serializable;
import org.hibernate.criterion.Order;
import com.liusy.dataapplatform.base.dao.HibernateDao;
import com.liusy.dataapplatform.base.exception.DAOException;

import com.liusy.datapp.model.analysis.AnalysisTemplate;

public abstract class BaseAnalysisTemplateDaoImpl extends HibernateDao {
   private static final long serialVersionUID = 1L;


	public Class getReferenceClass() {
		return AnalysisTemplate.class;
	}

	public Order getDefaultOrder() {
		return null;
	}

	public AnalysisTemplate cast(Object object) {
		return (AnalysisTemplate) object;
	}

	public AnalysisTemplate get(Serializable id)  throws DAOException{
		return (AnalysisTemplate) super.get(id);
	}

	public AnalysisTemplate load(Serializable id)  throws DAOException{
		return (AnalysisTemplate) super.load(id);
	}

	public Serializable save(AnalysisTemplate analysisTemplate)  throws DAOException{
		return super.save(analysisTemplate);
	}

	public void saveOrUpdate(AnalysisTemplate analysisTemplate)  throws DAOException{
		super.saveOrUpdate(analysisTemplate);
	}

	public void update(AnalysisTemplate analysisTemplate)  throws DAOException{
		super.update(analysisTemplate);
	}

	public void delete(AnalysisTemplate analysisTemplate)  throws DAOException{
		super.delete(analysisTemplate);
	}

	public void refresh(AnalysisTemplate analysisTemplate)  throws DAOException{
		super.refresh(analysisTemplate);
	}

	public String getTableName() {
		return AnalysisTemplate.REF_TABLE;
	}
	
}
