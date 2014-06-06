package com.liusy.datapp.dao.analysis.base;

import java.io.Serializable;
import org.hibernate.criterion.Order;
import com.liusy.dataapplatform.base.dao.HibernateDao;
import com.liusy.dataapplatform.base.exception.DAOException;

import com.liusy.datapp.model.analysis.AnalysisDatasetCondition;

public abstract class BaseAnalysisDatasetConditionDaoImpl extends HibernateDao {
   private static final long serialVersionUID = 1L;


	public Class getReferenceClass() {
		return AnalysisDatasetCondition.class;
	}

	public Order getDefaultOrder() {
		return null;
	}

	public AnalysisDatasetCondition cast(Object object) {
		return (AnalysisDatasetCondition) object;
	}

	public AnalysisDatasetCondition get(Serializable id)  throws DAOException{
		return (AnalysisDatasetCondition) super.get(id);
	}

	public AnalysisDatasetCondition load(Serializable id)  throws DAOException{
		return (AnalysisDatasetCondition) super.load(id);
	}

	public Serializable save(AnalysisDatasetCondition analysisDatasetCondition)  throws DAOException{
		return super.save(analysisDatasetCondition);
	}

	public void saveOrUpdate(AnalysisDatasetCondition analysisDatasetCondition)  throws DAOException{
		super.saveOrUpdate(analysisDatasetCondition);
	}

	public void update(AnalysisDatasetCondition analysisDatasetCondition)  throws DAOException{
		super.update(analysisDatasetCondition);
	}

	public void delete(AnalysisDatasetCondition analysisDatasetCondition)  throws DAOException{
		super.delete(analysisDatasetCondition);
	}

	public void refresh(AnalysisDatasetCondition analysisDatasetCondition)  throws DAOException{
		super.refresh(analysisDatasetCondition);
	}

	public String getTableName() {
		return AnalysisDatasetCondition.REF_TABLE;
	}
	
}
