package com.liusy.datapp.dao.compare.base;

import java.io.Serializable;
import org.hibernate.criterion.Order;
import com.liusy.dataapplatform.base.dao.HibernateDao;
import com.liusy.dataapplatform.base.exception.DAOException;

import com.liusy.datapp.model.compare.CompareSlaveResult;

public abstract class BaseCompareSlaveResultDaoImpl extends HibernateDao {
   private static final long serialVersionUID = 1L;


	public Class getReferenceClass() {
		return CompareSlaveResult.class;
	}

	public Order getDefaultOrder() {
		return null;
	}

	public CompareSlaveResult cast(Object object) {
		return (CompareSlaveResult) object;
	}

	public CompareSlaveResult get(Serializable id)  throws DAOException{
		return (CompareSlaveResult) super.get(id);
	}

	public CompareSlaveResult load(Serializable id)  throws DAOException{
		return (CompareSlaveResult) super.load(id);
	}

	public Serializable save(CompareSlaveResult compareSlaveResult)  throws DAOException{
		return super.save(compareSlaveResult);
	}

	public void saveOrUpdate(CompareSlaveResult compareSlaveResult)  throws DAOException{
		super.saveOrUpdate(compareSlaveResult);
	}

	public void update(CompareSlaveResult compareSlaveResult)  throws DAOException{
		super.update(compareSlaveResult);
	}

	public void delete(CompareSlaveResult compareSlaveResult)  throws DAOException{
		super.delete(compareSlaveResult);
	}

	public void refresh(CompareSlaveResult compareSlaveResult)  throws DAOException{
		super.refresh(compareSlaveResult);
	}
}
