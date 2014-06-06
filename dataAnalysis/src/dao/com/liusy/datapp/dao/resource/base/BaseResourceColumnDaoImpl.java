package com.liusy.datapp.dao.resource.base;

import java.io.Serializable;
import org.hibernate.criterion.Order;
import com.liusy.dataapplatform.base.dao.HibernateDao;
import com.liusy.dataapplatform.base.exception.DAOException;

import com.liusy.datapp.model.resource.ResourceColumn;

public abstract class BaseResourceColumnDaoImpl extends HibernateDao {
   private static final long serialVersionUID = 1L;


	public Class getReferenceClass() {
		return ResourceColumn.class;
	}

	public Order getDefaultOrder() {
		return null;
	}

	public ResourceColumn cast(Object object) {
		return (ResourceColumn) object;
	}

	public ResourceColumn get(Serializable id)  throws DAOException{
		return (ResourceColumn) super.get(id);
	}

	public ResourceColumn load(Serializable id)  throws DAOException{
		return (ResourceColumn) super.load(id);
	}

	public Serializable save(ResourceColumn resourceColumn)  throws DAOException{
		return super.save(resourceColumn);
	}

	public void saveOrUpdate(ResourceColumn resourceColumn)  throws DAOException{
		super.saveOrUpdate(resourceColumn);
	}

	public void update(ResourceColumn resourceColumn)  throws DAOException{
		super.update(resourceColumn);
	}

	public void delete(ResourceColumn resourceColumn)  throws DAOException{
		super.delete(resourceColumn);
	}

	public void refresh(ResourceColumn resourceColumn)  throws DAOException{
		super.refresh(resourceColumn);
	}
	public String getTableName() {
		return ResourceColumn.REF_TABLE;
	}
}
