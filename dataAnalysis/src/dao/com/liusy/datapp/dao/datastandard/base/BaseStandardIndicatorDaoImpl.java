package com.liusy.datapp.dao.datastandard.base;

import java.io.Serializable;
import org.hibernate.criterion.Order;
import com.liusy.dataapplatform.base.dao.HibernateDao;
import com.liusy.dataapplatform.base.exception.DAOException;

import com.liusy.datapp.model.datastandard.StandardIndicator;

public abstract class BaseStandardIndicatorDaoImpl extends HibernateDao {
   private static final long serialVersionUID = 1L;


	public Class getReferenceClass() {
		return StandardIndicator.class;
	}

	public Order getDefaultOrder() {
		return null;
	}

	public StandardIndicator cast(Object object) {
		return (StandardIndicator) object;
	}

	public StandardIndicator get(Serializable id)  throws DAOException{
		return (StandardIndicator) super.get(id);
	}

	public StandardIndicator load(Serializable id)  throws DAOException{
		return (StandardIndicator) super.load(id);
	}

	public Serializable save(StandardIndicator standardIndicator)  throws DAOException{
		return super.save(standardIndicator);
	}

	public void saveOrUpdate(StandardIndicator standardIndicator)  throws DAOException{
		super.saveOrUpdate(standardIndicator);
	}

	public void update(StandardIndicator standardIndicator)  throws DAOException{
		super.update(standardIndicator);
	}

	public void delete(StandardIndicator standardIndicator)  throws DAOException{
		super.delete(standardIndicator);
	}

	public void refresh(StandardIndicator standardIndicator)  throws DAOException{
		super.refresh(standardIndicator);
	}
	public String getTableName() {
		return StandardIndicator.REF_TABLE;
	}
}
