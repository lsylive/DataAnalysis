package com.liusy.datapp.dao.compare;

import java.io.Serializable;

import com.liusy.dataapplatform.base.dao.BaseDao;
import com.liusy.dataapplatform.base.exception.DAOException;

import com.liusy.datapp.model.compare.CompareRunThread;

public interface CompareRunThreadDao extends BaseDao {

	public CompareRunThread cast(Object object);
	
	public CompareRunThread get(Serializable id) throws DAOException;

	public CompareRunThread load(Serializable id) throws DAOException;	
	
	public Serializable save(CompareRunThread CompareRunThread) throws DAOException;

	public void saveOrUpdate(CompareRunThread CompareRunThread) throws DAOException;

	public void update(CompareRunThread CompareRunThread) throws DAOException;
	
	public void delete(Serializable id) throws DAOException;

	public void refresh(CompareRunThread CompareRunThread) throws DAOException;	
	
}
