package com.liusy.datapp.dao.system.org.base;

import java.io.Serializable;
import org.hibernate.criterion.Order;
import com.liusy.dataapplatform.base.dao.HibernateDao;
import com.liusy.dataapplatform.base.exception.DAOException;

import com.liusy.datapp.model.system.org.SysUserRole;

public abstract class BaseSysUserRoleDaoImpl extends HibernateDao {
   private static final long serialVersionUID = 1L;


	public Class getReferenceClass() {
		return SysUserRole.class;
	}

	public Order getDefaultOrder() {
		return null;
	}

	public SysUserRole cast(Object object) {
		return (SysUserRole) object;
	}

	public SysUserRole get(Serializable id)  throws DAOException{
		return (SysUserRole) super.get(id);
	}

	public SysUserRole load(Serializable id)  throws DAOException{
		return (SysUserRole) super.load(id);
	}

	public Serializable save(SysUserRole sysUserRole)  throws DAOException{
		return super.save(sysUserRole);
	}

	public void saveOrUpdate(SysUserRole sysUserRole)  throws DAOException{
		super.saveOrUpdate(sysUserRole);
	}

	public void update(SysUserRole sysUserRole)  throws DAOException{
		super.update(sysUserRole);
	}

	public void delete(SysUserRole sysUserRole)  throws DAOException{
		super.delete(sysUserRole);
	}

	public void refresh(SysUserRole sysUserRole)  throws DAOException{
		super.refresh(sysUserRole);
	}

	public String getTableName() {
		return SysUserRole.REF_TABLE;
	}
	
}
