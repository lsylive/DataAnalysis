package com.liusy.datapp.dao.resource.base;

import java.io.Serializable;
import org.hibernate.criterion.Order;
import com.liusy.dataapplatform.base.dao.HibernateDao;
import com.liusy.dataapplatform.base.exception.DAOException;

import com.liusy.datapp.model.resource.ResourceTableUserCfg;

public abstract class BaseResourceTableUserCfgDaoImpl extends HibernateDao {
   private static final long serialVersionUID = 1L;


	public Class getReferenceClass() {
		return ResourceTableUserCfg.class;
	}

	public Order getDefaultOrder() {
		return null;
	}

	public ResourceTableUserCfg cast(Object object) {
		return (ResourceTableUserCfg) object;
	}

	public ResourceTableUserCfg get(Serializable id)  throws DAOException{
		return (ResourceTableUserCfg) super.get(id);
	}

	public ResourceTableUserCfg load(Serializable id)  throws DAOException{
		return (ResourceTableUserCfg) super.load(id);
	}

	public Serializable save(ResourceTableUserCfg resourceTableUserCfg)  throws DAOException{
		return super.save(resourceTableUserCfg);
	}

	public void saveOrUpdate(ResourceTableUserCfg resourceTableUserCfg)  throws DAOException{
		super.saveOrUpdate(resourceTableUserCfg);
	}

	public void update(ResourceTableUserCfg resourceTableUserCfg)  throws DAOException{
		super.update(resourceTableUserCfg);
	}

	public void delete(ResourceTableUserCfg resourceTableUserCfg)  throws DAOException{
		super.delete(resourceTableUserCfg);
	}

	public void refresh(ResourceTableUserCfg resourceTableUserCfg)  throws DAOException{
		super.refresh(resourceTableUserCfg);
	}
}
