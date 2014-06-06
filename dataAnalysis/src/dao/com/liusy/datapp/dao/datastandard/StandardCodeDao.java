package com.liusy.datapp.dao.datastandard;

import java.io.Serializable;

import com.liusy.dataapplatform.base.dao.BaseDao;
import com.liusy.dataapplatform.base.exception.DAOException;

import com.liusy.datapp.model.datastandard.StandardCode;

public interface StandardCodeDao extends BaseDao {

	public StandardCode cast(Object object);
	
	public StandardCode get(Serializable id) throws DAOException;

	public StandardCode load(Serializable id) throws DAOException;	
	
	public Serializable save(StandardCode standardCode) throws DAOException;

	public void saveOrUpdate(StandardCode standardCode) throws DAOException;

	public void update(StandardCode standardCode) throws DAOException;
	
	public void delete(Serializable id) throws DAOException;

	public void refresh(StandardCode standardCode) throws DAOException;	
	
}
