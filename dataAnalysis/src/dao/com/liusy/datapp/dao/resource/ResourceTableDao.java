package com.liusy.datapp.dao.resource;

import java.io.Serializable;

import com.liusy.dataapplatform.base.dao.BaseDao;
import com.liusy.dataapplatform.base.exception.DAOException;

import com.liusy.datapp.model.resource.ResourceTable;

public interface ResourceTableDao extends BaseDao {

	public ResourceTable cast(Object object);
	
	public ResourceTable get(Serializable id) throws DAOException;

	public ResourceTable load(Serializable id) throws DAOException;	
	
	public Serializable save(ResourceTable resourceTable) throws DAOException;

	public void saveOrUpdate(ResourceTable resourceTable) throws DAOException;

	public void update(ResourceTable resourceTable) throws DAOException;
	
	public void delete(Serializable id) throws DAOException;

	public void refresh(ResourceTable resourceTable) throws DAOException;	
	
}
