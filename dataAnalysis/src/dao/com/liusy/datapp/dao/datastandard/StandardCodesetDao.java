package com.liusy.datapp.dao.datastandard;

import java.io.Serializable;

import com.liusy.dataapplatform.base.dao.BaseDao;
import com.liusy.dataapplatform.base.exception.DAOException;

import com.liusy.datapp.model.datastandard.StandardCodeset;

public interface StandardCodesetDao extends BaseDao {

	public StandardCodeset cast(Object object);
	
	public StandardCodeset get(Serializable id) throws DAOException;

	public StandardCodeset load(Serializable id) throws DAOException;	
	
	public Serializable save(StandardCodeset standardCodeset) throws DAOException;

	public void saveOrUpdate(StandardCodeset standardCodeset) throws DAOException;

	public void update(StandardCodeset standardCodeset) throws DAOException;
	
	public void delete(Serializable id) throws DAOException;

	public void refresh(StandardCodeset standardCodeset) throws DAOException;	
	
}
