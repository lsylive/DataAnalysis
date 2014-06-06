package com.liusy.datapp.dao.system.org.base;

import java.io.Serializable;

import org.hibernate.criterion.Order;

import com.liusy.dataapplatform.base.dao.HibernateDao;
import com.liusy.dataapplatform.base.exception.DAOException;
import com.liusy.datapp.model.system.org.SysUser;

public abstract class BaseSysUserDaoImpl extends HibernateDao {

	public Class getReferenceClass() {
		return SysUser.class;
	}

	public Order getDefaultOrder() {
		return null;
	}

	public SysUser cast(Object object) {
		return (SysUser) object;
	}

	public SysUser get(Serializable id) throws DAOException{
		return (SysUser) super.get(id);
	}

	public SysUser load(Serializable id) throws DAOException{
		return (SysUser) super.load(id);
	}

	public Serializable save(SysUser sysUser) throws DAOException{
		return super.save(sysUser);
	}

	public void saveOrUpdate(SysUser sysUser) throws DAOException{
		super.saveOrUpdate(sysUser);
	}

	public void update(SysUser sysUser) throws DAOException{
		super.update(sysUser);
	}


	public void delete(SysUser sysUserInfo) throws DAOException{
		super.delete(sysUserInfo);
	}

	public void refresh(SysUser sysUserInfo) throws DAOException{
		super.refresh(sysUserInfo);
	}
	public String getTableName() {
		return SysUser.REF_TABLE;
	}

}
