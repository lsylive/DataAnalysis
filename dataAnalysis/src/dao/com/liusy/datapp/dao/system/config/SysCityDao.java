package com.liusy.datapp.dao.system.config;

import java.io.Serializable;

import com.liusy.dataapplatform.base.dao.BaseDao;
import com.liusy.dataapplatform.base.exception.DAOException;

import com.liusy.datapp.model.system.config.SysCity;

public interface SysCityDao extends BaseDao {

	public SysCity cast(Object object);
	
	public SysCity get(Serializable id) throws DAOException;

	public SysCity load(Serializable id) throws DAOException;	
	
	public Serializable save(SysCity sysCity) throws DAOException;

	public void saveOrUpdate(SysCity sysCity) throws DAOException;

	public void update(SysCity sysCity) throws DAOException;
	
	public void delete(Serializable id) throws DAOException;

	public void refresh(SysCity sysCity) throws DAOException;	
	
}
