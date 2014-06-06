package com.liusy.datapp.dao.compare.base;

import java.io.Serializable;
import org.hibernate.criterion.Order;
import com.liusy.dataapplatform.base.dao.HibernateDao;
import com.liusy.dataapplatform.base.exception.DAOException;

import com.liusy.datapp.model.compare.CompareInfo;

public abstract class BaseCompareInfoDaoImpl extends HibernateDao {
   private static final long serialVersionUID = 1L;


	public Class getReferenceClass() {
		return CompareInfo.class;
	}

	public Order getDefaultOrder() {
		return null;
	}

	public CompareInfo cast(Object object) {
		return (CompareInfo) object;
	}

	public CompareInfo get(Serializable id)  throws DAOException{
		return (CompareInfo) super.get(id);
	}

	public CompareInfo load(Serializable id)  throws DAOException{
		return (CompareInfo) super.load(id);
	}

	public Serializable save(CompareInfo compareInfo)  throws DAOException{
		return super.save(compareInfo);
	}

	public void saveOrUpdate(CompareInfo compareInfo)  throws DAOException{
		super.saveOrUpdate(compareInfo);
	}

	public void update(CompareInfo compareInfo)  throws DAOException{
		super.update(compareInfo);
	}

	public void delete(CompareInfo compareInfo)  throws DAOException{
		super.delete(compareInfo);
	}

	public void refresh(CompareInfo compareInfo)  throws DAOException{
		super.refresh(compareInfo);
	}
}
