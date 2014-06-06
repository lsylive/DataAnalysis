package com.liusy.datapp.dao.datastandard.base;

import java.io.Serializable;
import org.hibernate.criterion.Order;
import com.liusy.dataapplatform.base.dao.HibernateDao;
import com.liusy.dataapplatform.base.exception.DAOException;

import com.liusy.datapp.model.datastandard.StandardCode;

public abstract class BaseStandardCodeDaoImpl extends HibernateDao {
   private static final long serialVersionUID = 1L;


	public Class getReferenceClass() {
		return StandardCode.class;
	}

	public Order getDefaultOrder() {
		return null;
	}

	public StandardCode cast(Object object) {
		return (StandardCode) object;
	}

	public StandardCode get(Serializable id)  throws DAOException{
		return (StandardCode) super.get(id);
	}

	public StandardCode load(Serializable id)  throws DAOException{
		return (StandardCode) super.load(id);
	}

	public Serializable save(StandardCode standardCode)  throws DAOException{
		return super.save(standardCode);
	}

	public void saveOrUpdate(StandardCode standardCode)  throws DAOException{
		super.saveOrUpdate(standardCode);
	}

	public void update(StandardCode standardCode)  throws DAOException{
		super.update(standardCode);
	}

	public void delete(StandardCode standardCode)  throws DAOException{
		super.delete(standardCode);
	}

	public void refresh(StandardCode standardCode)  throws DAOException{
		super.refresh(standardCode);
	}
	public String getTableName() {
		return StandardCode.REF_TABLE;
	}

}
