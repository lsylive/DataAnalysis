package com.liusy.datapp.dao.compare;

import java.io.Serializable;

import com.liusy.dataapplatform.base.dao.BaseDao;
import com.liusy.dataapplatform.base.exception.DAOException;

import com.liusy.datapp.model.compare.CompareSlaveResult;

public interface CompareSlaveResultDao extends BaseDao {

	public CompareSlaveResult cast(Object object);
	
	public CompareSlaveResult get(Serializable id) throws DAOException;

	public CompareSlaveResult load(Serializable id) throws DAOException;	
	
	public Serializable save(CompareSlaveResult compareSlaveResult) throws DAOException;

	public void saveOrUpdate(CompareSlaveResult compareSlaveResult) throws DAOException;

	public void update(CompareSlaveResult compareSlaveResult) throws DAOException;
	
	public void delete(Serializable id) throws DAOException;

	public void refresh(CompareSlaveResult compareSlaveResult) throws DAOException;	
	
}
