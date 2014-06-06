package com.liusy.datapp.dao.datastandard.base;

import java.io.Serializable;
import org.hibernate.criterion.Order;
import com.liusy.dataapplatform.base.dao.HibernateDao;
import com.liusy.dataapplatform.base.exception.DAOException;

import com.liusy.datapp.model.datastandard.StandardCategory;

public abstract class BaseStandardCategoryDaoImpl extends HibernateDao {
   private static final long serialVersionUID = 1L;


	public Class getReferenceClass() {
		return StandardCategory.class;
	}

	public Order getDefaultOrder() {
		return null;
	}

	public StandardCategory cast(Object object) {
		return (StandardCategory) object;
	}

	public StandardCategory get(Serializable id)  throws DAOException{
		return (StandardCategory) super.get(id);
	}

	public StandardCategory load(Serializable id)  throws DAOException{
		return (StandardCategory) super.load(id);
	}

	public Serializable save(StandardCategory standardCategory)  throws DAOException{
		return super.save(standardCategory);
	}

	public void saveOrUpdate(StandardCategory standardCategory)  throws DAOException{
		super.saveOrUpdate(standardCategory);
	}

	public void update(StandardCategory standardCategory)  throws DAOException{
		super.update(standardCategory);
	}

	public void delete(StandardCategory standardCategory)  throws DAOException{
		super.delete(standardCategory);
	}

	public void refresh(StandardCategory standardCategory)  throws DAOException{
		super.refresh(standardCategory);
	}
	public String getTableName() {
		return StandardCategory.REF_TABLE;
	}
	
}
