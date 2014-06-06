package com.liusy.datapp.dao.compare;

import java.io.Serializable;

import com.liusy.dataapplatform.base.dao.BaseDao;
import com.liusy.dataapplatform.base.exception.DAOException;

import com.liusy.datapp.model.compare.CompareInfo;

public interface CompareInfoDao extends BaseDao {

	public CompareInfo cast(Object object);
	
	public CompareInfo get(Serializable id) throws DAOException;

	public CompareInfo load(Serializable id) throws DAOException;	
	
	public Serializable save(CompareInfo compareInfo) throws DAOException;

	public void saveOrUpdate(CompareInfo compareInfo) throws DAOException;

	public void update(CompareInfo compareInfo) throws DAOException;
	
	public void delete(Serializable id) throws DAOException;

	public void refresh(CompareInfo compareInfo) throws DAOException;	
	public void changeCompareInfoStatus(final Serializable[] ids,final String status) throws DAOException;
	public void changeCompareInfoRunStatus(final Object ids,final String status) throws DAOException;
}
