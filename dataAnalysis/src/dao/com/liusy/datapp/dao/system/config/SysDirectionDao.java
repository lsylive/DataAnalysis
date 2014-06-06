package com.liusy.datapp.dao.system.config;

import java.io.Serializable;

import com.liusy.dataapplatform.base.dao.BaseDao;
import com.liusy.dataapplatform.base.exception.DAOException;

import com.liusy.datapp.model.system.config.SysDirection;

public interface SysDirectionDao extends BaseDao {

	public SysDirection cast(Object object);
	
	public SysDirection get(Serializable id) throws DAOException;

	public SysDirection load(Serializable id) throws DAOException;	
	
	public Serializable save(SysDirection sysDirection) throws DAOException;

	public void saveOrUpdate(SysDirection sysDirection) throws DAOException;

	public void update(SysDirection sysDirection) throws DAOException;
	
	public void delete(Serializable id) throws DAOException;

	public void refresh(SysDirection sysDirection) throws DAOException;	
	
}
