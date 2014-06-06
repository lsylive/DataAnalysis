package com.liusy.datapp.dao.system.config;

import java.io.Serializable;

import com.liusy.dataapplatform.base.dao.BaseDao;
import com.liusy.dataapplatform.base.exception.DAOException;

import com.liusy.datapp.model.system.config.SysCode;

public interface SysCodeDao extends BaseDao {

	public SysCode cast(Object object);
	
	public SysCode get(Serializable id) throws DAOException;

	public SysCode load(Serializable id) throws DAOException;	
	
	public Serializable save(SysCode sysCode) throws DAOException;

	public void saveOrUpdate(SysCode sysCode) throws DAOException;

	public void update(SysCode sysCode) throws DAOException;
	
	public void delete(Serializable id) throws DAOException;

	public void refresh(SysCode sysCode) throws DAOException;	
	
}
