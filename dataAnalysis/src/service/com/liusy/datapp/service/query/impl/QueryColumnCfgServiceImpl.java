package com.liusy.datapp.service.query.impl;

import java.io.Serializable;
import java.util.List;

import com.liusy.dataapplatform.base.exception.DAOException;
import com.liusy.dataapplatform.base.exception.ServiceException;
import com.liusy.dataapplatform.base.util.PageQuery;
import com.liusy.datapp.dao.query.QueryColumnCfgDao;
import com.liusy.datapp.dao.query.QueryParamCfgDao;
import com.liusy.datapp.model.query.QueryColumnCfg;
import com.liusy.datapp.model.query.QueryParamCfg;
import com.liusy.datapp.service.pool.TableConfigPool;
import com.liusy.datapp.service.query.QueryColumnCfgService;


public class QueryColumnCfgServiceImpl implements QueryColumnCfgService {
	private static final long serialVersionUID = 1L;
	public QueryColumnCfgServiceImpl(){
		int i=0;
	}
	public QueryColumnCfg findQueryColumnCfg(Serializable id) throws ServiceException {
		try {
			return queryColumnCfgDao.get(id);
		}
		catch (DAOException e) {
			throw new ServiceException(e);
		}
	}

	public void createQueryColumnCfg(QueryColumnCfg queryColumnCfg) throws ServiceException {
		try {
			queryColumnCfgDao.save(queryColumnCfg);
			columnConfigPool.clearColumnConfigPool(queryColumnCfg.getTableId().toString());
		}
		catch (DAOException e) {
			throw new ServiceException(e);
		}
	}

	public void updateQueryColumnCfg(QueryColumnCfg queryColumnCfg) throws ServiceException {
		try {		
			queryColumnCfgDao.update(queryColumnCfg);
			columnConfigPool.clearColumnConfigPool(queryColumnCfg.getTableId().toString());
		}
		catch (DAOException e) {
			throw new ServiceException(e);
		}
		catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	public void removeQueryColumnCfg(Serializable id) throws ServiceException {
		try {
			QueryColumnCfg cfg=findQueryColumnCfg(id);
			columnConfigPool.clearColumnConfigPool(cfg.getTableId().toString());
			queryColumnCfgDao.delete(id);
			
		}
		catch (DAOException e) {
			throw new ServiceException(e);
		}
	}

	public void removeQueryColumnCfgs(Serializable[] ids) throws ServiceException {
		try {
			for(Serializable id:ids){
				QueryColumnCfg cfg=findQueryColumnCfg(id);
				columnConfigPool.clearColumnConfigPool(cfg.getTableId().toString());
			}
			queryColumnCfgDao.deleteAll(ids);
		}
		catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	public PageQuery queryQueryColumnCfg(PageQuery pageQuery) throws ServiceException {
		try {
			PageQuery  query=queryColumnCfgDao.queryBySelectId(pageQuery);
			pageQuery.setRecordSet(query.getRecordSet());
			pageQuery.setRecordCount(query.getRecordCount());
			return pageQuery;
		}
		catch (Exception e) {
			throw new ServiceException(e);
		}
	}
	public QueryColumnCfg findColumnCfgByColumnId(String columnId) throws ServiceException{
		try{
			List list=queryColumnCfgDao.findByField(QueryColumnCfg.PROP_COLUMN_ID, Integer.valueOf(columnId));
			if(list!=null && !list.isEmpty())
				return (QueryColumnCfg)list.get(0);
			else
				return null;
		}catch (Exception e) {
			throw new ServiceException(e);
		}
	}
	public void updateQueryConfig(QueryColumnCfg columncfg,QueryParamCfg paramcfg) throws ServiceException{
		try{
			if(columncfg.getId()==null)
				queryColumnCfgDao.save(columncfg);
			else
				queryColumnCfgDao.update(columncfg);
			if(paramcfg.getId()==null)
				queryParamCfgDao.save(paramcfg);
			else
				queryParamCfgDao.update(paramcfg);
			
		}catch (Exception e) {
			throw new ServiceException(e);
		}
	}
	public void initQueryConfig(List<QueryColumnCfg> columncfglist,List<QueryParamCfg> paramcfglist,Integer tableId) throws ServiceException{
		try{
			//…æ≥˝∂‘”¶◊÷∂Œ≈‰÷√
//			for(int k=0;k<columnIdlist.size();k++){
//				queryColumnCfgDao.deleteByField(QueryColumnCfg.PROP_COLUMN_ID, Integer.valueOf(columnIdlist.get(k)));
//				queryParamCfgDao.deleteByField(QueryParamCfg.PROP_COLUMN_ID, Integer.valueOf(columnIdlist.get(k)));
//			}
			queryColumnCfgDao.deleteByField(QueryColumnCfg.PROP_TABLE_ID, tableId);
			queryParamCfgDao.deleteByField(QueryParamCfg.PROP_TABLE_ID, tableId);
			for(int i=0;i<columncfglist.size();i++){
				queryColumnCfgDao.save(columncfglist.get(i));
				queryParamCfgDao.save(paramcfglist.get(i));
			}
		}catch (Exception e) {
			throw new ServiceException(e);
		}
	}
	public void deleteColumnConfigByColumnId(String columnId) throws ServiceException{
		try{
			queryColumnCfgDao.deleteByField(QueryColumnCfg.PROP_COLUMN_ID, Integer.valueOf(columnId));
			queryParamCfgDao.deleteByField(QueryParamCfg.PROP_COLUMN_ID, Integer.valueOf(columnId));
		}catch (Exception e) {
			throw new ServiceException(e);
		}
	}
	public List<QueryColumnCfg> findColumnConfigByOrder(String tableId) throws ServiceException{
		try{
			return queryColumnCfgDao.findColumnConfigByOrder(tableId);
		}catch (DAOException e) {
			throw new ServiceException(e);
		}
	}

	private QueryColumnCfgDao	queryColumnCfgDao;
	private QueryParamCfgDao queryParamCfgDao;
	private TableConfigPool columnConfigPool;

	/**
	 * ◊¢»ÎDAO
	 * 
	 * @see com.liusy.core.service.QueryColumnCfgService#setQueryColumnCfgDao(QueryColumnCfg
	 *      queryColumnCfgDao)
	 */
	public void setQueryColumnCfgDao(QueryColumnCfgDao queryColumnCfgDao) {
		this.queryColumnCfgDao = queryColumnCfgDao;
	}

	public void setQueryParamCfgDao(QueryParamCfgDao queryParamCfgDao) {
		this.queryParamCfgDao = queryParamCfgDao;
	}

	public void setColumnConfigPool(TableConfigPool columnConfigPool) {
		this.columnConfigPool = columnConfigPool;
	}
}

