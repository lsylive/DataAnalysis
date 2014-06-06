package com.liusy.datapp.dao.datastandard.base;

import java.io.Serializable;
import org.hibernate.criterion.Order;
import com.liusy.dataapplatform.base.dao.HibernateDao;
import com.liusy.dataapplatform.base.exception.DAOException;

import com.liusy.datapp.model.datastandard.StandardCodeset;

public abstract class BaseStandardCodesetDaoImpl extends HibernateDao {
   private static final long serialVersionUID = 1L;


	public Class getReferenceClass() {
		return StandardCodeset.class;
	}

	public Order getDefaultOrder() {
		return null;
	}

	public StandardCodeset cast(Object object) {
		return (StandardCodeset) object;
	}

	public StandardCodeset get(Serializable id)  throws DAOException{
		return (StandardCodeset) super.get(id);
	}

	public StandardCodeset load(Serializable id)  throws DAOException{
		return (StandardCodeset) super.load(id);
	}

	public Serializable save(StandardCodeset standardCodeset)  throws DAOException{
		return super.save(standardCodeset);
	}

	public void saveOrUpdate(StandardCodeset standardCodeset)  throws DAOException{
		super.saveOrUpdate(standardCodeset);
	}

	public void update(StandardCodeset standardCodeset)  throws DAOException{
		super.update(standardCodeset);
	}

	public void delete(StandardCodeset standardCodeset)  throws DAOException{
		super.delete(standardCodeset);
	}

	public void refresh(StandardCodeset standardCodeset)  throws DAOException{
		super.refresh(standardCodeset);
	}
	public String getTableName() {
		return StandardCodeset.REF_TABLE;
	}
}
