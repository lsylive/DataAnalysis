package com.liusy.datapp.dao.analysis.base;

import java.io.Serializable;
import org.hibernate.criterion.Order;
import com.liusy.dataapplatform.base.dao.HibernateDao;
import com.liusy.dataapplatform.base.exception.DAOException;

import com.liusy.datapp.model.analysis.AnalysisTemplateParams;

public abstract class BaseAnalysisTemplateParamsDaoImpl extends HibernateDao {
   private static final long serialVersionUID = 1L;


	public Class getReferenceClass() {
		return AnalysisTemplateParams.class;
	}

	public Order getDefaultOrder() {
		return null;
	}

	public AnalysisTemplateParams cast(Object object) {
		return (AnalysisTemplateParams) object;
	}

	public AnalysisTemplateParams get(Serializable id)  throws DAOException{
		return (AnalysisTemplateParams) super.get(id);
	}

	public AnalysisTemplateParams load(Serializable id)  throws DAOException{
		return (AnalysisTemplateParams) super.load(id);
	}

	public Serializable save(AnalysisTemplateParams analysisTemplateParams)  throws DAOException{
		return super.save(analysisTemplateParams);
	}

	public void saveOrUpdate(AnalysisTemplateParams analysisTemplateParams)  throws DAOException{
		super.saveOrUpdate(analysisTemplateParams);
	}

	public void update(AnalysisTemplateParams analysisTemplateParams)  throws DAOException{
		super.update(analysisTemplateParams);
	}

	public void delete(AnalysisTemplateParams analysisTemplateParams)  throws DAOException{
		super.delete(analysisTemplateParams);
	}

	public void refresh(AnalysisTemplateParams analysisTemplateParams)  throws DAOException{
		super.refresh(analysisTemplateParams);
	}

	public String getTableName() {
		return AnalysisTemplateParams.REF_TABLE;
	}
	
}
