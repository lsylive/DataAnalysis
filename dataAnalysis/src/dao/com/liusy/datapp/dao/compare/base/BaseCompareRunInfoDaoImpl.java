package com.liusy.datapp.dao.compare.base;

import java.io.Serializable;
import org.hibernate.criterion.Order;
import com.liusy.dataapplatform.base.dao.HibernateDao;
import com.liusy.dataapplatform.base.exception.DAOException;

import com.liusy.datapp.model.compare.CompareRunInfo;

public abstract class BaseCompareRunInfoDaoImpl extends HibernateDao {
   private static final long serialVersionUID = 1L;


	public Class getReferenceClass() {
		return CompareRunInfo.class;
	}

	public Order getDefaultOrder() {
		return null;
	}

	public CompareRunInfo cast(Object object) {
		return (CompareRunInfo) object;
	}

	public CompareRunInfo get(Serializable id)  throws DAOException{
		return (CompareRunInfo) super.get(id);
	}

	public CompareRunInfo load(Serializable id)  throws DAOException{
		return (CompareRunInfo) super.load(id);
	}

	public Serializable save(CompareRunInfo compareRunInfo)  throws DAOException{
		return super.save(compareRunInfo);
	}

	public void saveOrUpdate(CompareRunInfo compareRunInfo)  throws DAOException{
		super.saveOrUpdate(compareRunInfo);
	}

	public void update(CompareRunInfo compareRunInfo)  throws DAOException{
		super.update(compareRunInfo);
	}

	public void delete(CompareRunInfo compareRunInfo)  throws DAOException{
		super.delete(compareRunInfo);
	}

	public void refresh(CompareRunInfo compareRunInfo)  throws DAOException{
		super.refresh(compareRunInfo);
	}
}
