package com.liusy.datapp.dao.system.org;

import java.io.Serializable;

import com.liusy.dataapplatform.base.dao.BaseDao;
import com.liusy.dataapplatform.base.exception.DAOException;

import com.liusy.datapp.model.system.org.SysUserRole;

public interface SysUserRoleDao extends BaseDao {

	public SysUserRole cast(Object object);
	
	public SysUserRole get(Serializable id) throws DAOException;

	public SysUserRole load(Serializable id) throws DAOException;	
	
	public Serializable save(SysUserRole sysUserRole) throws DAOException;

	public void saveOrUpdate(SysUserRole sysUserRole) throws DAOException;

	public void update(SysUserRole sysUserRole) throws DAOException;
	
	public void delete(Serializable id) throws DAOException;

	public void refresh(SysUserRole sysUserRole) throws DAOException;	
	
}
