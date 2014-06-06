package com.liusy.datapp.dao.resource;

import java.io.Serializable;

import com.liusy.dataapplatform.base.dao.BaseDao;
import com.liusy.dataapplatform.base.exception.DAOException;

import com.liusy.datapp.model.resource.ResourceColumnUserCfg;

public interface ResourceColumnUserCfgDao extends BaseDao {

	public ResourceColumnUserCfg cast(Object object);
	
	public ResourceColumnUserCfg get(Serializable id) throws DAOException;

	public ResourceColumnUserCfg load(Serializable id) throws DAOException;	
	
	public Serializable save(ResourceColumnUserCfg resourceColumnUserCfg) throws DAOException;

	public void saveOrUpdate(ResourceColumnUserCfg resourceColumnUserCfg) throws DAOException;

	public void update(ResourceColumnUserCfg resourceColumnUserCfg) throws DAOException;
	
	public void delete(Serializable id) throws DAOException;

	public void refresh(ResourceColumnUserCfg resourceColumnUserCfg) throws DAOException;	
	
}
