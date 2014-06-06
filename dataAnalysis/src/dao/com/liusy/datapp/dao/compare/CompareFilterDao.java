package com.liusy.datapp.dao.compare;

import java.io.Serializable;

import com.liusy.dataapplatform.base.dao.BaseDao;
import com.liusy.dataapplatform.base.exception.DAOException;

import com.liusy.datapp.model.compare.CompareFilter;

public interface CompareFilterDao extends BaseDao {

	public CompareFilter cast(Object object);
	
	public CompareFilter get(Serializable id) throws DAOException;

	public CompareFilter load(Serializable id) throws DAOException;	
	
	public Serializable save(CompareFilter compareFilter) throws DAOException;

	public void saveOrUpdate(CompareFilter compareFilter) throws DAOException;

	public void update(CompareFilter compareFilter) throws DAOException;
	
	public void delete(Serializable id) throws DAOException;

	public void refresh(CompareFilter compareFilter) throws DAOException;	
	
}
