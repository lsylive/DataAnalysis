package com.liusy.datapp.dao.datastandard.base;

import java.io.Serializable;
import org.hibernate.criterion.Order;
import com.liusy.dataapplatform.base.dao.HibernateDao;
import com.liusy.dataapplatform.base.exception.DAOException;

import com.liusy.datapp.model.datastandard.StandardDataMeta;

public abstract class BaseStandardDataMetaDaoImpl extends HibernateDao {
   private static final long serialVersionUID = 1L;


	public Class getReferenceClass() {
		return StandardDataMeta.class;
	}

	public Order getDefaultOrder() {
		return null;
	}

	public StandardDataMeta cast(Object object) {
		return (StandardDataMeta) object;
	}

	public StandardDataMeta get(Serializable id)  throws DAOException{
		return (StandardDataMeta) super.get(id);
	}

	public StandardDataMeta load(Serializable id)  throws DAOException{
		return (StandardDataMeta) super.load(id);
	}

	public Serializable save(StandardDataMeta standardDataMeta)  throws DAOException{
		return super.save(standardDataMeta);
	}

	public void saveOrUpdate(StandardDataMeta standardDataMeta)  throws DAOException{
		super.saveOrUpdate(standardDataMeta);
	}

	public void update(StandardDataMeta standardDataMeta)  throws DAOException{
		super.update(standardDataMeta);
	}

	public void delete(StandardDataMeta standardDataMeta)  throws DAOException{
		super.delete(standardDataMeta);
	}

	public void refresh(StandardDataMeta standardDataMeta)  throws DAOException{
		super.refresh(standardDataMeta);
	}
	public String getTableName() {
		return StandardDataMeta.REF_TABLE;
	}
}
