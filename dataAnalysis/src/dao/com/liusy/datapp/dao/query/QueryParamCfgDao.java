package com.liusy.datapp.dao.query;

import java.io.Serializable;
import java.util.List;

import com.liusy.dataapplatform.base.dao.BaseDao;
import com.liusy.dataapplatform.base.exception.DAOException;

import com.liusy.datapp.model.query.QueryParamCfg;

public interface QueryParamCfgDao extends BaseDao {

	public QueryParamCfg cast(Object object);
	
	public QueryParamCfg get(Serializable id) throws DAOException;

	public QueryParamCfg load(Serializable id) throws DAOException;	
	
	public Serializable save(QueryParamCfg queryParamCfg) throws DAOException;

	public void saveOrUpdate(QueryParamCfg queryParamCfg) throws DAOException;

	public void update(QueryParamCfg queryParamCfg) throws DAOException;
	
	public void delete(Serializable id) throws DAOException;

	public void refresh(QueryParamCfg queryParamCfg) throws DAOException;
	
	public List<QueryParamCfg> findParamByOrder(String tableId) throws DAOException;
	
}
