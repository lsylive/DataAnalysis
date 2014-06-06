package com.liusy.datapp.dao.datastandard;

import java.io.Serializable;

import com.liusy.dataapplatform.base.dao.BaseDao;
import com.liusy.dataapplatform.base.exception.DAOException;

import com.liusy.datapp.model.datastandard.StandardDataMeta;

public interface StandardDataMetaDao extends BaseDao {

	public StandardDataMeta cast(Object object);
	
	public StandardDataMeta get(Serializable id) throws DAOException;

	public StandardDataMeta load(Serializable id) throws DAOException;	
	
	public Serializable save(StandardDataMeta standardDataMeta) throws DAOException;

	public void saveOrUpdate(StandardDataMeta standardDataMeta) throws DAOException;

	public void update(StandardDataMeta standardDataMeta) throws DAOException;
	
	public void delete(Serializable id) throws DAOException;

	public void refresh(StandardDataMeta standardDataMeta) throws DAOException;	
	
}
