package com.liusy.datapp.dao.resource.base;

import java.io.Serializable;
import org.hibernate.criterion.Order;
import com.liusy.dataapplatform.base.dao.HibernateDao;
import com.liusy.dataapplatform.base.exception.DAOException;

import com.liusy.datapp.model.resource.ResourceTable;

public abstract class BaseResourceTableDaoImpl extends HibernateDao {
   private static final long serialVersionUID = 1L;


	public Class getReferenceClass() {
		return ResourceTable.class;
	}

	public Order getDefaultOrder() {
		return null;
	}

	public ResourceTable cast(Object object) {
		return (ResourceTable) object;
	}

	public ResourceTable get(Serializable id)  throws DAOException{
		return (ResourceTable) super.get(id);
	}

	public ResourceTable load(Serializable id)  throws DAOException{
		return (ResourceTable) super.load(id);
	}

	public Serializable save(ResourceTable resourceTable)  throws DAOException{
		return super.save(resourceTable);
	}

	public void saveOrUpdate(ResourceTable resourceTable)  throws DAOException{
		super.saveOrUpdate(resourceTable);
	}

	public void update(ResourceTable resourceTable)  throws DAOException{
		super.update(resourceTable);
	}

	public void delete(ResourceTable resourceTable)  throws DAOException{
		super.delete(resourceTable);
	}

	public void refresh(ResourceTable resourceTable)  throws DAOException{
		super.refresh(resourceTable);
	}
	public String getTableName() {
		return ResourceTable.REF_TABLE;
	}
}
