package com.liusy.datapp.dao.datastandard;

import java.io.Serializable;
import java.util.List;

import com.liusy.dataapplatform.base.dao.BaseDao;
import com.liusy.dataapplatform.base.exception.DAOException;

import com.liusy.datapp.model.datastandard.StandardCategory;

public interface StandardCategoryDao extends BaseDao {

	public StandardCategory cast(Object object);
	
	public StandardCategory get(Serializable id) throws DAOException;

	public StandardCategory load(Serializable id) throws DAOException;	
	
	public Serializable save(StandardCategory standardCategory) throws DAOException;

	public void saveOrUpdate(StandardCategory standardCategory) throws DAOException;

	public void update(StandardCategory standardCategory) throws DAOException;
	
	public void delete(Serializable id) throws DAOException;

	public void refresh(StandardCategory standardCategory) throws DAOException;
	
	public List<StandardCategory> findCatagoryByCode() throws DAOException;
	
}
