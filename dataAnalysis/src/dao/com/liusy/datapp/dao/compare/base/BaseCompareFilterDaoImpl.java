package com.liusy.datapp.dao.compare.base;

import java.io.Serializable;
import org.hibernate.criterion.Order;
import com.liusy.dataapplatform.base.dao.HibernateDao;
import com.liusy.dataapplatform.base.exception.DAOException;

import com.liusy.datapp.model.compare.CompareFilter;

public abstract class BaseCompareFilterDaoImpl extends HibernateDao {
   private static final long serialVersionUID = 1L;


	public Class getReferenceClass() {
		return CompareFilter.class;
	}

	public Order getDefaultOrder() {
		return null;
	}

	public CompareFilter cast(Object object) {
		return (CompareFilter) object;
	}

	public CompareFilter get(Serializable id)  throws DAOException{
		return (CompareFilter) super.get(id);
	}

	public CompareFilter load(Serializable id)  throws DAOException{
		return (CompareFilter) super.load(id);
	}

	public Serializable save(CompareFilter compareFilter)  throws DAOException{
		return super.save(compareFilter);
	}

	public void saveOrUpdate(CompareFilter compareFilter)  throws DAOException{
		super.saveOrUpdate(compareFilter);
	}

	public void update(CompareFilter compareFilter)  throws DAOException{
		super.update(compareFilter);
	}

	public void delete(CompareFilter compareFilter)  throws DAOException{
		super.delete(compareFilter);
	}

	public void refresh(CompareFilter compareFilter)  throws DAOException{
		super.refresh(compareFilter);
	}
}
