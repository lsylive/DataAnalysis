package com.liusy.datapp.service.query;

import java.io.Serializable;
import java.util.List;

import com.liusy.dataapplatform.base.exception.ServiceException;
import com.liusy.dataapplatform.base.util.PageQuery;
import com.liusy.datapp.model.query.QueryParamCfg;

public interface QueryParamCfgService {

	public QueryParamCfg findQueryParamCfg(Serializable id) throws ServiceException;

	public void createQueryParamCfg(QueryParamCfg queryParamCfg) throws ServiceException;

	public void updateQueryParamCfg(QueryParamCfg queryParamCfg) throws ServiceException;

	public void removeQueryParamCfg(Serializable id) throws ServiceException;

	public PageQuery queryQueryParamCfg(PageQuery pageQuery) throws ServiceException;

	public void removeQueryParamCfgs(Serializable[] ids) throws ServiceException;
	public QueryParamCfg findQueryParamByColumnId(String columnId) throws ServiceException;
	public List<QueryParamCfg> findParamByOrder(String tableId) throws ServiceException;
}

