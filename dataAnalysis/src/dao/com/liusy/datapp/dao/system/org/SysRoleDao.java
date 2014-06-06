package com.liusy.datapp.dao.system.org;

import java.io.Serializable;

import com.liusy.dataapplatform.base.dao.BaseDao;
import com.liusy.dataapplatform.base.exception.DAOException;

import com.liusy.datapp.model.system.org.SysRole;

public interface SysRoleDao extends BaseDao {

	public SysRole cast(Object object);
	
	public SysRole get(Serializable id) throws DAOException;

	public SysRole load(Serializable id) throws DAOException;	
	
	public Serializable save(SysRole sysRole) throws DAOException;

	public void saveOrUpdate(SysRole sysRole) throws DAOException;

	public void update(SysRole sysRole) throws DAOException;
	
	public void delete(Serializable id) throws DAOException;

	public void refresh(SysRole sysRole) throws DAOException;	
	
}
