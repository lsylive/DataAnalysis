package com.liusy.datapp.dao.system.config.base;

import java.io.Serializable;
import org.hibernate.criterion.Order;
import com.liusy.dataapplatform.base.dao.HibernateDao;
import com.liusy.dataapplatform.base.exception.DAOException;

import com.liusy.datapp.model.system.config.SysResource;

public abstract class BaseSysResourceDaoImpl extends HibernateDao {
   private static final long serialVersionUID = 1L;


	public Class getReferenceClass() {
		return SysResource.class;
	}

	public Order getDefaultOrder() {
		return null;
	}

	public SysResource cast(Object object) {
		return (SysResource) object;
	}

	public SysResource get(Serializable id)  throws DAOException{
		return (SysResource) super.get(id);
	}

	public SysResource load(Serializable id)  throws DAOException{
		return (SysResource) super.load(id);
	}

	public Serializable save(SysResource sysResource)  throws DAOException{
		return super.save(sysResource);
	}

	public void saveOrUpdate(SysResource sysResource)  throws DAOException{
		super.saveOrUpdate(sysResource);
	}

	public void update(SysResource sysResource)  throws DAOException{
		super.update(sysResource);
	}

	public void delete(SysResource sysResource)  throws DAOException{
		super.delete(sysResource);
	}

	public void refresh(SysResource sysResource)  throws DAOException{
		super.refresh(sysResource);
	}

	public String getTableName() {
		return SysResource.REF_TABLE;
	}
	
}
