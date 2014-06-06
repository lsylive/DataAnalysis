package com.liusy.datapp.dao.datastandard;

import java.io.Serializable;

import com.liusy.dataapplatform.base.dao.BaseDao;
import com.liusy.dataapplatform.base.exception.DAOException;

import com.liusy.datapp.model.datastandard.StandardIndicator;

public interface StandardIndicatorDao extends BaseDao {

	public StandardIndicator cast(Object object);
	
	public StandardIndicator get(Serializable id) throws DAOException;

	public StandardIndicator load(Serializable id) throws DAOException;	
	
	public Serializable save(StandardIndicator standardIndicator) throws DAOException;

	public void saveOrUpdate(StandardIndicator standardIndicator) throws DAOException;

	public void update(StandardIndicator standardIndicator) throws DAOException;
	
	public void delete(Serializable id) throws DAOException;

	public void refresh(StandardIndicator standardIndicator) throws DAOException;	
	
}
