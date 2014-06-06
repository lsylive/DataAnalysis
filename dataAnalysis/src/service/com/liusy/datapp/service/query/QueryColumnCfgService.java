package com.liusy.datapp.service.query;

import java.io.Serializable;
import java.util.List;

import com.liusy.dataapplatform.base.exception.ServiceException;
import com.liusy.dataapplatform.base.util.PageQuery;
import com.liusy.datapp.model.query.QueryColumnCfg;
import com.liusy.datapp.model.query.QueryParamCfg;

public interface QueryColumnCfgService {

	public QueryColumnCfg findQueryColumnCfg(Serializable id) throws ServiceException;

	public void createQueryColumnCfg(QueryColumnCfg queryColumnCfg) throws ServiceException;

	public void updateQueryColumnCfg(QueryColumnCfg queryColumnCfg) throws ServiceException;

	public void removeQueryColumnCfg(Serializable id) throws ServiceException;

	public PageQuery queryQueryColumnCfg(PageQuery pageQuery) throws ServiceException;

	public void removeQueryColumnCfgs(Serializable[] ids) throws ServiceException;
	public QueryColumnCfg findColumnCfgByColumnId(String columnId) throws ServiceException;
	public void updateQueryConfig(QueryColumnCfg columncfg,QueryParamCfg paramcfg) throws ServiceException;
	public List<QueryColumnCfg> findColumnConfigByOrder(String tableId) throws ServiceException;
	public void initQueryConfig(List<QueryColumnCfg> columncfglist,List<QueryParamCfg> paramcfglist,Integer tableId) throws ServiceException;
	
}

