package com.liusy.datapp.dao.system.org.base;

import java.io.Serializable;

import org.hibernate.criterion.Order;

import com.liusy.dataapplatform.base.dao.HibernateDao;
import com.liusy.dataapplatform.base.exception.DAOException;
import com.liusy.datapp.model.system.org.SysResourceRole;

public abstract class BaseSysResourceRoleDaoImpl extends HibernateDao {
   private static final long serialVersionUID = 1L;


	public Class getReferenceClass() {
		return SysResourceRole.class;
	}

	public Order getDefaultOrder() {
		return null;
	}

	public SysResourceRole cast(Object object) {
		return (SysResourceRole) object;
	}

	public SysResourceRole get(Serializable id)  throws DAOException{
		return (SysResourceRole) super.get(id);
	}

	public SysResourceRole load(Serializable id)  throws DAOException{
		return (SysResourceRole) super.load(id);
	}

	public Serializable save(SysResourceRole sysResourceRole)  throws DAOException{
		return super.save(sysResourceRole);
	}

	public void saveOrUpdate(SysResourceRole sysResourceRole)  throws DAOException{
		super.saveOrUpdate(sysResourceRole);
	}

	public void update(SysResourceRole sysResourceRole)  throws DAOException{
		super.update(sysResourceRole);
	}

	public void delete(SysResourceRole sysResourceRole)  throws DAOException{
		super.delete(sysResourceRole);
	}

	public void refresh(SysResourceRole sysResourceRole)  throws DAOException{
		super.refresh(sysResourceRole);
	}

	public String getTableName() {
		return SysResourceRole.REF_TABLE;
	}
	
}
