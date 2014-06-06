package com.liusy.datapp.dao.analysis.base;

import java.io.Serializable;
import org.hibernate.criterion.Order;
import com.liusy.dataapplatform.base.dao.HibernateDao;
import com.liusy.dataapplatform.base.exception.DAOException;

import com.liusy.datapp.model.analysis.AnalysisTemplateConfig;

public abstract class BaseAnalysisTemplateConfigDaoImpl extends HibernateDao {
   private static final long serialVersionUID = 1L;


	public Class getReferenceClass() {
		return AnalysisTemplateConfig.class;
	}

	public Order getDefaultOrder() {
		return null;
	}

	public AnalysisTemplateConfig cast(Object object) {
		return (AnalysisTemplateConfig) object;
	}

	public AnalysisTemplateConfig get(Serializable id)  throws DAOException{
		return (AnalysisTemplateConfig) super.get(id);
	}

	public AnalysisTemplateConfig load(Serializable id)  throws DAOException{
		return (AnalysisTemplateConfig) super.load(id);
	}

	public Serializable save(AnalysisTemplateConfig analysisTemplateConfig)  throws DAOException{
		return super.save(analysisTemplateConfig);
	}

	public void saveOrUpdate(AnalysisTemplateConfig analysisTemplateConfig)  throws DAOException{
		super.saveOrUpdate(analysisTemplateConfig);
	}

	public void update(AnalysisTemplateConfig analysisTemplateConfig)  throws DAOException{
		super.update(analysisTemplateConfig);
	}

	public void delete(AnalysisTemplateConfig analysisTemplateConfig)  throws DAOException{
		super.delete(analysisTemplateConfig);
	}

	public void refresh(AnalysisTemplateConfig analysisTemplateConfig)  throws DAOException{
		super.refresh(analysisTemplateConfig);
	}

	public String getTableName() {
		return AnalysisTemplateConfig.REF_TABLE;
	}
	
}
