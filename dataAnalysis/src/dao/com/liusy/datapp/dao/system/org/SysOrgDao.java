package com.liusy.datapp.dao.system.org;

import java.io.Serializable;

import com.liusy.dataapplatform.base.dao.BaseDao;
import com.liusy.dataapplatform.base.exception.DAOException;

import com.liusy.datapp.model.system.org.SysOrg;

public interface SysOrgDao extends BaseDao {

	public SysOrg cast(Object object);
	
	public SysOrg get(Serializable id) throws DAOException;

	public SysOrg load(Serializable id) throws DAOException;	
	
	public Serializable save(SysOrg sysOrg) throws DAOException;

	public void saveOrUpdate(SysOrg sysOrg) throws DAOException;

	public void update(SysOrg sysOrg) throws DAOException;
	
	public void delete(Serializable id) throws DAOException;

	public void refresh(SysOrg sysOrg) throws DAOException;	
	
}
