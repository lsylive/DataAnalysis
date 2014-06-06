package com.liusy.datapp.dao.resource.base;

import java.io.Serializable;
import org.hibernate.criterion.Order;
import com.liusy.dataapplatform.base.dao.HibernateDao;
import com.liusy.dataapplatform.base.exception.DAOException;

import com.liusy.datapp.model.resource.ResourceColumnUserCfg;

public abstract class BaseResourceColumnUserCfgDaoImpl extends HibernateDao {
   private static final long serialVersionUID = 1L;


	public Class getReferenceClass() {
		return ResourceColumnUserCfg.class;
	}

	public Order getDefaultOrder() {
		return null;
	}

	public ResourceColumnUserCfg cast(Object object) {
		return (ResourceColumnUserCfg) object;
	}

	public ResourceColumnUserCfg get(Serializable id)  throws DAOException{
		return (ResourceColumnUserCfg) super.get(id);
	}

	public ResourceColumnUserCfg load(Serializable id)  throws DAOException{
		return (ResourceColumnUserCfg) super.load(id);
	}

	public Serializable save(ResourceColumnUserCfg resourceColumnUserCfg)  throws DAOException{
		return super.save(resourceColumnUserCfg);
	}

	public void saveOrUpdate(ResourceColumnUserCfg resourceColumnUserCfg)  throws DAOException{
		super.saveOrUpdate(resourceColumnUserCfg);
	}

	public void update(ResourceColumnUserCfg resourceColumnUserCfg)  throws DAOException{
		super.update(resourceColumnUserCfg);
	}

	public void delete(ResourceColumnUserCfg resourceColumnUserCfg)  throws DAOException{
		super.delete(resourceColumnUserCfg);
	}

	public void refresh(ResourceColumnUserCfg resourceColumnUserCfg)  throws DAOException{
		super.refresh(resourceColumnUserCfg);
	}
}
