package com.liusy.datapp.dao.query;

import java.io.Serializable;

import com.liusy.dataapplatform.base.dao.BaseDao;
import com.liusy.dataapplatform.base.exception.DAOException;

import com.liusy.datapp.model.query.QuerySubject;

public interface QuerySubjectDao extends BaseDao {

	public QuerySubject cast(Object object);
	
	public QuerySubject get(Serializable id) throws DAOException;

	public QuerySubject load(Serializable id) throws DAOException;	
	
	public Serializable save(QuerySubject querySubject) throws DAOException;

	public void saveOrUpdate(QuerySubject querySubject) throws DAOException;

	public void update(QuerySubject querySubject) throws DAOException;
	
	public void delete(Serializable id) throws DAOException;

	public void refresh(QuerySubject querySubject) throws DAOException;	
	
}
