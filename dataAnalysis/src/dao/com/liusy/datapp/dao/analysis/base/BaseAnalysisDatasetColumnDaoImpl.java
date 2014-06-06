package com.liusy.datapp.dao.analysis.base;

import java.io.Serializable;
import org.hibernate.criterion.Order;
import com.liusy.dataapplatform.base.dao.HibernateDao;
import com.liusy.dataapplatform.base.exception.DAOException;

import com.liusy.datapp.model.analysis.AnalysisDatasetColumn;

public abstract class BaseAnalysisDatasetColumnDaoImpl extends HibernateDao {
   private static final long serialVersionUID = 1L;


	public Class getReferenceClass() {
		return AnalysisDatasetColumn.class;
	}

	public Order getDefaultOrder() {
		return null;
	}

	public AnalysisDatasetColumn cast(Object object) {
		return (AnalysisDatasetColumn) object;
	}

	public AnalysisDatasetColumn get(Serializable id)  throws DAOException{
		return (AnalysisDatasetColumn) super.get(id);
	}

	public AnalysisDatasetColumn load(Serializable id)  throws DAOException{
		return (AnalysisDatasetColumn) super.load(id);
	}

	public Serializable save(AnalysisDatasetColumn analysisDatasetColumn)  throws DAOException{
		return super.save(analysisDatasetColumn);
	}

	public void saveOrUpdate(AnalysisDatasetColumn analysisDatasetColumn)  throws DAOException{
		super.saveOrUpdate(analysisDatasetColumn);
	}

	public void update(AnalysisDatasetColumn analysisDatasetColumn)  throws DAOException{
		super.update(analysisDatasetColumn);
	}

	public void delete(AnalysisDatasetColumn analysisDatasetColumn)  throws DAOException{
		super.delete(analysisDatasetColumn);
	}

	public void refresh(AnalysisDatasetColumn analysisDatasetColumn)  throws DAOException{
		super.refresh(analysisDatasetColumn);
	}

	public String getTableName() {
		return AnalysisDatasetColumn.REF_TABLE;
	}
	
}
