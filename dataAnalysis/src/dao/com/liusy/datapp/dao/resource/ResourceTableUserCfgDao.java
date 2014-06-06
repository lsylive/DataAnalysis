package com.liusy.datapp.dao.resource;

import java.io.Serializable;

import com.liusy.dataapplatform.base.dao.BaseDao;
import com.liusy.dataapplatform.base.exception.DAOException;

import com.liusy.datapp.model.resource.ResourceTableUserCfg;

public interface ResourceTableUserCfgDao extends BaseDao {

	public ResourceTableUserCfg cast(Object object);
	
	public ResourceTableUserCfg get(Serializable id) throws DAOException;

	public ResourceTableUserCfg load(Serializable id) throws DAOException;	
	
	public Serializable save(ResourceTableUserCfg resourceTableUserCfg) throws DAOException;

	public void saveOrUpdate(ResourceTableUserCfg resourceTableUserCfg) throws DAOException;

	public void update(ResourceTableUserCfg resourceTableUserCfg) throws DAOException;
	
	public void delete(Serializable id) throws DAOException;

	public void refresh(ResourceTableUserCfg resourceTableUserCfg) throws DAOException;	
	
}
