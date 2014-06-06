package com.liusy.datapp.dao.query;

import java.io.Serializable;
import java.util.List;

import com.liusy.dataapplatform.base.dao.BaseDao;
import com.liusy.dataapplatform.base.exception.DAOException;

import com.liusy.datapp.model.query.QueryColumnCfg;

public interface QueryColumnCfgDao extends BaseDao {

	public QueryColumnCfg cast(Object object);
	
	public QueryColumnCfg get(Serializable id) throws DAOException;

	public QueryColumnCfg load(Serializable id) throws DAOException;	
	
	public Serializable save(QueryColumnCfg queryColumnCfg) throws DAOException;

	public void saveOrUpdate(QueryColumnCfg queryColumnCfg) throws DAOException;

	public void update(QueryColumnCfg queryColumnCfg) throws DAOException;
	
	public void delete(Serializable id) throws DAOException;

	public void refresh(QueryColumnCfg queryColumnCfg) throws DAOException;
	
	public List<QueryColumnCfg> findColumnConfigByOrder(String tableId) throws DAOException;
	
}
