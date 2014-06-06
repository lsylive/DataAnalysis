package com.liusy.datapp.dao.compare.base;

import java.io.Serializable;
import org.hibernate.criterion.Order;
import com.liusy.dataapplatform.base.dao.HibernateDao;
import com.liusy.dataapplatform.base.exception.DAOException;

import com.liusy.datapp.model.compare.CompareRunThread;

public abstract class BaseCompareRunThreadDaoImpl extends HibernateDao {
   private static final long serialVersionUID = 1L;


	public Class getReferenceClass() {
		return CompareRunThread.class;
	}

	public Order getDefaultOrder() {
		return null;
	}

	public CompareRunThread cast(Object object) {
		return (CompareRunThread) object;
	}

	public CompareRunThread get(Serializable id)  throws DAOException{
		return (CompareRunThread) super.get(id);
	}

	public CompareRunThread load(Serializable id)  throws DAOException{
		return (CompareRunThread) super.load(id);
	}

	public Serializable save(CompareRunThread compareRunThread)  throws DAOException{
		return super.save(compareRunThread);
	}

	public void saveOrUpdate(CompareRunThread compareRunThread)  throws DAOException{
		super.saveOrUpdate(compareRunThread);
	}

	public void update(CompareRunThread compareRunThread)  throws DAOException{
		super.update(compareRunThread);
	}

	public void delete(CompareRunThread compareRunThread)  throws DAOException{
		super.delete(compareRunThread);
	}

	public void refresh(CompareRunThread compareRunThread)  throws DAOException{
		super.refresh(compareRunThread);
	}
}
