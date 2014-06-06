package com.liusy.datapp.dao.compare;

import java.io.Serializable;

import com.liusy.dataapplatform.base.dao.BaseDao;
import com.liusy.dataapplatform.base.exception.DAOException;

import com.liusy.datapp.model.compare.CompareMainResult;

public interface CompareMainResultDao extends BaseDao {

	public CompareMainResult cast(Object object);
	
	public CompareMainResult get(Serializable id) throws DAOException;

	public CompareMainResult load(Serializable id) throws DAOException;	
	
	public Serializable save(CompareMainResult compareMainResult) throws DAOException;

	public void saveOrUpdate(CompareMainResult compareMainResult) throws DAOException;

	public void update(CompareMainResult compareMainResult) throws DAOException;
	
	public void delete(Serializable id) throws DAOException;

	public void refresh(CompareMainResult compareMainResult) throws DAOException;	
	
}
