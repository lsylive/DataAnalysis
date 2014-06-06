package com.liusy.datapp.service.query.impl;

import java.io.Serializable;
import java.util.List;

import com.liusy.dataapplatform.base.exception.DAOException;
import com.liusy.dataapplatform.base.exception.ServiceException;
import com.liusy.dataapplatform.base.util.PageQuery;
import com.liusy.datapp.dao.query.QueryParamCfgDao;
import com.liusy.datapp.model.query.QueryParamCfg;
import com.liusy.datapp.service.pool.TableConfigPool;
import com.liusy.datapp.service.query.QueryParamCfgService;

public class QueryParamCfgServiceImpl implements QueryParamCfgService {
	private static final long serialVersionUID = 1L;

	public QueryParamCfg findQueryParamCfg(Serializable id) throws ServiceException {
		try {
			return queryParamCfgDao.get(id);
		}
		catch (DAOException e) {
			throw new ServiceException(e);
		}
	}

	public void createQueryParamCfg(QueryParamCfg queryParamCfg) throws ServiceException {
		try {
			queryParamCfgDao.save(queryParamCfg);
			columnConfigPool.clearParamConfigPool(queryParamCfg.getTableId().toString());
		}
		catch (DAOException e) {
			throw new ServiceException(e);
		}
	}

	public void updateQueryParamCfg(QueryParamCfg queryParamCfg) throws ServiceException {
		try {
			queryParamCfgDao.update(queryParamCfg);
			columnConfigPool.clearParamConfigPool(queryParamCfg.getTableId().toString());
		}
		catch (DAOException e) {
			throw new ServiceException(e);
		}
		catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	public void removeQueryParamCfg(Serializable id) throws ServiceException {
		try {
			QueryParamCfg queryParamCfg=findQueryParamCfg(id);
			columnConfigPool.clearParamConfigPool(queryParamCfg.getTableId().toString());
			queryParamCfgDao.delete(id);
		}
		catch (DAOException e) {
			throw new ServiceException(e);
		}
	}

	public void removeQueryParamCfgs(Serializable[] ids) throws ServiceException {
		try {
			for(Serializable id:ids){
				QueryParamCfg queryParamCfg=findQueryParamCfg(id);
				columnConfigPool.clearParamConfigPool(queryParamCfg.getTableId().toString());
			}
			queryParamCfgDao.deleteAll(ids);
		}
		catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	public PageQuery queryQueryParamCfg(PageQuery pageQuery) throws ServiceException {
		try {
			PageQuery  query=queryParamCfgDao.queryBySelectId(pageQuery);
			pageQuery.setRecordSet(query.getRecordSet());
			pageQuery.setRecordCount(query.getRecordCount());
			return pageQuery;
		}
		catch (Exception e) {
			throw new ServiceException(e);
		}
	}
	public QueryParamCfg findQueryParamByColumnId(String columnId) throws ServiceException{
		try{
			List list=queryParamCfgDao.findByField(QueryParamCfg.PROP_COLUMN_ID, Integer.valueOf(columnId));
			if(list!=null && !list.isEmpty())
				return (QueryParamCfg)list.get(0);
			else
				return null;
		}catch (Exception e) {
			throw new ServiceException(e);
		}
	}
	public List<QueryParamCfg> findParamByOrder(String tableId) throws ServiceException{
		try{
			return queryParamCfgDao.findParamByOrder(tableId);
		}catch (DAOException e) {
			throw new ServiceException(e);
		}
	}

	private QueryParamCfgDao	queryParamCfgDao;
	private TableConfigPool   columnConfigPool;

	/**
	 * ×¢ÈëDAO
	 * 
	 * @see com.liusy.core.service.QueryParamCfgService#setQueryParamCfgDao(QueryParamCfg
	 *      queryParamCfgDao)
	 */
	public void setQueryParamCfgDao(QueryParamCfgDao queryParamCfgDao) {
		this.queryParamCfgDao = queryParamCfgDao;
	}

	public void setColumnConfigPool(TableConfigPool columnConfigPool) {
		this.columnConfigPool = columnConfigPool;
	}
}

