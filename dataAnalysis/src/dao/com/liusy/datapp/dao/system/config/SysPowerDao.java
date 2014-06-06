package com.liusy.datapp.dao.system.config;

import java.io.Serializable;

import com.liusy.dataapplatform.base.dao.BaseDao;
import com.liusy.dataapplatform.base.exception.DAOException;

import com.liusy.datapp.model.system.config.SysPower;

public interface SysPowerDao extends BaseDao {

	public SysPower cast(Object object);
	
	public SysPower get(Serializable id) throws DAOException;

	public SysPower load(Serializable id) throws DAOException;	
	
	public Serializable save(SysPower sysPower) throws DAOException;

	public void saveOrUpdate(SysPower sysPower) throws DAOException;

	public void update(SysPower sysPower) throws DAOException;
	
	public void delete(Serializable id) throws DAOException;

	public void refresh(SysPower sysPower) throws DAOException;	
	
}
