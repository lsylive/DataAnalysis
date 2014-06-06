package com.liusy.datapp.dao.system.org.base;

import java.io.Serializable;

import org.hibernate.criterion.Order;

import com.liusy.dataapplatform.base.dao.HibernateDao;
import com.liusy.dataapplatform.base.exception.DAOException;
import com.liusy.datapp.model.system.org.SysRole;

public abstract class BaseSysRoleDaoImpl extends HibernateDao {
   private static final long serialVersionUID = 1L;


	public Class getReferenceClass() {
		return SysRole.class;
	}

	public Order getDefaultOrder() {
		return null;
	}

	public SysRole cast(Object object) {
		return (SysRole) object;
	}

	public SysRole get(Serializable id)  throws DAOException{
		return (SysRole) super.get(id);
	}

	public SysRole load(Serializable id)  throws DAOException{
		return (SysRole) super.load(id);
	}

	public Serializable save(SysRole sysRole)  throws DAOException{
		return super.save(sysRole);
	}

	public void saveOrUpdate(SysRole sysRole)  throws DAOException{
		super.saveOrUpdate(sysRole);
	}

	public void update(SysRole sysRole)  throws DAOException{
		super.update(sysRole);
	}

	public void delete(SysRole sysRole)  throws DAOException{
		super.delete(sysRole);
	}

	public void refresh(SysRole sysRole)  throws DAOException{
		super.refresh(sysRole);
	}
	public String getTableName() {
		return SysRole.REF_TABLE;
	}
}
