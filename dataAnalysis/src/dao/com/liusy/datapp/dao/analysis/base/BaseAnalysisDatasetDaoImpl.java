package com.liusy.datapp.dao.analysis.base;

import java.io.Serializable;
import org.hibernate.criterion.Order;
import com.liusy.dataapplatform.base.dao.HibernateDao;
import com.liusy.dataapplatform.base.exception.DAOException;

import com.liusy.datapp.model.analysis.AnalysisDataset;

public abstract class BaseAnalysisDatasetDaoImpl extends HibernateDao {
   private static final long serialVersionUID = 1L;


	public Class getReferenceClass() {
		return AnalysisDataset.class;
	}

	public Order getDefaultOrder() {
		return null;
	}

	public AnalysisDataset cast(Object object) {
		return (AnalysisDataset) object;
	}

	public AnalysisDataset get(Serializable id)  throws DAOException{
		return (AnalysisDataset) super.get(id);
	}

	public AnalysisDataset load(Serializable id)  throws DAOException{
		return (AnalysisDataset) super.load(id);
	}

	public Serializable save(AnalysisDataset analysisDataset)  throws DAOException{
		return super.save(analysisDataset);
	}

	public void saveOrUpdate(AnalysisDataset analysisDataset)  throws DAOException{
		super.saveOrUpdate(analysisDataset);
	}

	public void update(AnalysisDataset analysisDataset)  throws DAOException{
		super.update(analysisDataset);
	}

	public void delete(AnalysisDataset analysisDataset)  throws DAOException{
		super.delete(analysisDataset);
	}

	public void refresh(AnalysisDataset analysisDataset)  throws DAOException{
		super.refresh(analysisDataset);
	}

	public String getTableName() {
		return AnalysisDataset.REF_TABLE;
	}
	
}
