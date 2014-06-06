package com.liusy.datapp.dao.compare.base;

import java.io.Serializable;
import org.hibernate.criterion.Order;
import com.liusy.dataapplatform.base.dao.HibernateDao;
import com.liusy.dataapplatform.base.exception.DAOException;

import com.liusy.datapp.model.compare.CompareMainResult;

public abstract class BaseCompareMainResultDaoImpl extends HibernateDao {
   private static final long serialVersionUID = 1L;


	public Class getReferenceClass() {
		return CompareMainResult.class;
	}

	public Order getDefaultOrder() {
		return null;
	}

	public CompareMainResult cast(Object object) {
		return (CompareMainResult) object;
	}

	public CompareMainResult get(Serializable id)  throws DAOException{
		return (CompareMainResult) super.get(id);
	}

	public CompareMainResult load(Serializable id)  throws DAOException{
		return (CompareMainResult) super.load(id);
	}

	public Serializable save(CompareMainResult compareMainResult)  throws DAOException{
		return super.save(compareMainResult);
	}

	public void saveOrUpdate(CompareMainResult compareMainResult)  throws DAOException{
		super.saveOrUpdate(compareMainResult);
	}

	public void update(CompareMainResult compareMainResult)  throws DAOException{
		super.update(compareMainResult);
	}

	public void delete(CompareMainResult compareMainResult)  throws DAOException{
		super.delete(compareMainResult);
	}

	public void refresh(CompareMainResult compareMainResult)  throws DAOException{
		super.refresh(compareMainResult);
	}
}
