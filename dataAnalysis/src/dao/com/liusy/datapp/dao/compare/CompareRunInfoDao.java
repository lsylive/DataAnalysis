package com.liusy.datapp.dao.compare;

import java.io.Serializable;

import com.liusy.dataapplatform.base.dao.BaseDao;
import com.liusy.dataapplatform.base.exception.DAOException;

import com.liusy.datapp.model.compare.CompareRunInfo;

public interface CompareRunInfoDao extends BaseDao {

	public CompareRunInfo cast(Object object);
	
	public CompareRunInfo get(Serializable id) throws DAOException;

	public CompareRunInfo load(Serializable id) throws DAOException;	
	
	public Serializable save(CompareRunInfo compareRunInfo) throws DAOException;

	public void saveOrUpdate(CompareRunInfo compareRunInfo) throws DAOException;

	public void update(CompareRunInfo compareRunInfo) throws DAOException;
	
	public void delete(Serializable id) throws DAOException;

	public void refresh(CompareRunInfo compareRunInfo) throws DAOException;	
	
}
