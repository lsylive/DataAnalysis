package com.liusy.datapp.dao.system.org;

import java.io.Serializable;

import com.liusy.dataapplatform.base.dao.BaseDao;
import com.liusy.dataapplatform.base.exception.DAOException;
import com.liusy.datapp.model.system.org.SysResourceRole;

public interface SysResourceRoleDao extends BaseDao {

	public SysResourceRole cast(Object object);
	
	public SysResourceRole get(Serializable id) throws DAOException;

	public SysResourceRole load(Serializable id) throws DAOException;	
	
	public Serializable save(SysResourceRole sysResourceRole) throws DAOException;

	public void saveOrUpdate(SysResourceRole sysResourceRole) throws DAOException;

	public void update(SysResourceRole sysResourceRole) throws DAOException;
	
	public void delete(Serializable id) throws DAOException;

	public void refresh(SysResourceRole sysResourceRole) throws DAOException;	
	
}
