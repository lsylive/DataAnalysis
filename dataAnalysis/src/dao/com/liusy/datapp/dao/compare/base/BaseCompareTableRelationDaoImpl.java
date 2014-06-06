package com.liusy.datapp.dao.compare.base;

import java.io.Serializable;
import org.hibernate.criterion.Order;
import com.liusy.dataapplatform.base.dao.HibernateDao;
import com.liusy.dataapplatform.base.exception.DAOException;

import com.liusy.datapp.model.compare.CompareTableRelation;

public abstract class BaseCompareTableRelationDaoImpl extends HibernateDao {
   private static final long serialVersionUID = 1L;


	public Class getReferenceClass() {
		return CompareTableRelation.class;
	}

	public Order getDefaultOrder() {
		return null;
	}

	public CompareTableRelation cast(Object object) {
		return (CompareTableRelation) object;
	}

	public CompareTableRelation get(Serializable id)  throws DAOException{
		return (CompareTableRelation) super.get(id);
	}

	public CompareTableRelation load(Serializable id)  throws DAOException{
		return (CompareTableRelation) super.load(id);
	}

	public Serializable save(CompareTableRelation compareTableRelation)  throws DAOException{
		return super.save(compareTableRelation);
	}

	public void saveOrUpdate(CompareTableRelation compareTableRelation)  throws DAOException{
		super.saveOrUpdate(compareTableRelation);
	}

	public void update(CompareTableRelation compareTableRelation)  throws DAOException{
		super.update(compareTableRelation);
	}

	public void delete(CompareTableRelation compareTableRelation)  throws DAOException{
		super.delete(compareTableRelation);
	}

	public void refresh(CompareTableRelation compareTableRelation)  throws DAOException{
		super.refresh(compareTableRelation);
	}
}
