package com.liusy.datapp.dao.system.config;

import java.io.Serializable;

import com.liusy.dataapplatform.base.dao.BaseDao;
import com.liusy.dataapplatform.base.exception.DAOException;

import com.liusy.datapp.model.system.config.SysResource;

public interface SysResourceDao extends BaseDao {

	public SysResource cast(Object object);
	
	public SysResource get(Serializable id) throws DAOException;

	public SysResource load(Serializable id) throws DAOException;	
	
	public Serializable save(SysResource sysResource) throws DAOException;

	public void saveOrUpdate(SysResource sysResource) throws DAOException;

	public void update(SysResource sysResource) throws DAOException;
	
	public void delete(Serializable id) throws DAOException;

	public void refresh(SysResource sysResource) throws DAOException;	
	
}
