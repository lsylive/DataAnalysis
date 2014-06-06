package com.liusy.datapp.dao.resource;

import java.io.Serializable;
import java.util.List;

import com.liusy.dataapplatform.base.dao.BaseDao;
import com.liusy.dataapplatform.base.exception.DAOException;

import com.liusy.datapp.model.resource.ResourceColumn;

public interface ResourceColumnDao extends BaseDao {

	public ResourceColumn cast(Object object);
	
	public ResourceColumn get(Serializable id) throws DAOException;

	public ResourceColumn load(Serializable id) throws DAOException;	
	
	public Serializable save(ResourceColumn resourceColumn) throws DAOException;

	public void saveOrUpdate(ResourceColumn resourceColumn) throws DAOException;

	public void update(ResourceColumn resourceColumn) throws DAOException;
	
	public void delete(Serializable id) throws DAOException;

	public void refresh(ResourceColumn resourceColumn) throws DAOException;
	
	 public List<ResourceColumn> findColumnByTableIdSort(String tableId) throws DAOException;
	 public ResourceColumn findPKColumnByTable(Serializable tableId) throws DAOException;
	
}
