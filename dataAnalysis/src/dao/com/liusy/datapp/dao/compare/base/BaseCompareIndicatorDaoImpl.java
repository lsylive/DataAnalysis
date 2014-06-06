package com.liusy.datapp.dao.compare.base;

import java.io.Serializable;
import org.hibernate.criterion.Order;
import com.liusy.dataapplatform.base.dao.HibernateDao;
import com.liusy.dataapplatform.base.exception.DAOException;

import com.liusy.datapp.model.compare.CompareIndicator;

public abstract class BaseCompareIndicatorDaoImpl extends HibernateDao {
   private static final long serialVersionUID = 1L;


	public Class getReferenceClass() {
		return CompareIndicator.class;
	}

	public Order getDefaultOrder() {
		return null;
	}

	public CompareIndicator cast(Object object) {
		return (CompareIndicator) object;
	}

	public CompareIndicator get(Serializable id)  throws DAOException{
		return (CompareIndicator) super.get(id);
	}

	public CompareIndicator load(Serializable id)  throws DAOException{
		return (CompareIndicator) super.load(id);
	}

	public Serializable save(CompareIndicator compareIndicator)  throws DAOException{
		return super.save(compareIndicator);
	}

	public void saveOrUpdate(CompareIndicator compareIndicator)  throws DAOException{
		super.saveOrUpdate(compareIndicator);
	}

	public void update(CompareIndicator compareIndicator)  throws DAOException{
		super.update(compareIndicator);
	}

	public void delete(CompareIndicator compareIndicator)  throws DAOException{
		super.delete(compareIndicator);
	}

	public void refresh(CompareIndicator compareIndicator)  throws DAOException{
		super.refresh(compareIndicator);
	}
}
