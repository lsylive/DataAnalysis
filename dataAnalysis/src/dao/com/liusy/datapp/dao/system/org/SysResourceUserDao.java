package com.liusy.datapp.dao.system.org;

import java.io.Serializable;

import com.liusy.dataapplatform.base.dao.BaseDao;
import com.liusy.dataapplatform.base.exception.DAOException;

import com.liusy.datapp.model.system.org.SysResourceUser;

public interface SysResourceUserDao extends BaseDao {

	public SysResourceUser cast(Object object);
	
	public SysResourceUser get(Serializable id) throws DAOException;

	public SysResourceUser load(Serializable id) throws DAOException;	
	
	public Serializable save(SysResourceUser sysResourceUser) throws DAOException;

	public void saveOrUpdate(SysResourceUser sysResourceUser) throws DAOException;

	public void update(SysResourceUser sysResourceUser) throws DAOException;
	
	public void delete(Serializable id) throws DAOException;

	public void refresh(SysResourceUser sysResourceUser) throws DAOException;	
	
}
