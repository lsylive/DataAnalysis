package com.liusy.datapp.dao.system.config;

import java.io.Serializable;

import com.liusy.dataapplatform.base.dao.BaseDao;
import com.liusy.dataapplatform.base.exception.DAOException;

import com.liusy.datapp.model.system.config.SysCodeSet;

public interface SysCodeSetDao extends BaseDao {

	public SysCodeSet cast(Object object);
	
	public SysCodeSet get(Serializable id) throws DAOException;

	public SysCodeSet load(Serializable id) throws DAOException;	
	
	public Serializable save(SysCodeSet sysCodeSet) throws DAOException;

	public void saveOrUpdate(SysCodeSet sysCodeSet) throws DAOException;

	public void update(SysCodeSet sysCodeSet) throws DAOException;
	
	public void delete(Serializable id) throws DAOException;

	public void refresh(SysCodeSet sysCodeSet) throws DAOException;	
	
}
