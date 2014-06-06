package com.liusy.datapp.dao.query.base;

import java.io.Serializable;
import org.hibernate.criterion.Order;
import com.liusy.dataapplatform.base.dao.HibernateDao;
import com.liusy.dataapplatform.base.exception.DAOException;

import com.liusy.datapp.model.query.QueryParamCfg;

public abstract class BaseQueryParamCfgDaoImpl extends HibernateDao {
   private static final long serialVersionUID = 1L;


	public Class getReferenceClass() {
		return QueryParamCfg.class;
	}

	public Order getDefaultOrder() {
		return null;
	}

	public QueryParamCfg cast(Object object) {
		return (QueryParamCfg) object;
	}

	public QueryParamCfg get(Serializable id)  throws DAOException{
		return (QueryParamCfg) super.get(id);
	}

	public QueryParamCfg load(Serializable id)  throws DAOException{
		return (QueryParamCfg) super.load(id);
	}

	public Serializable save(QueryParamCfg queryParamCfg)  throws DAOException{
		return super.save(queryParamCfg);
	}

	public void saveOrUpdate(QueryParamCfg queryParamCfg)  throws DAOException{
		super.saveOrUpdate(queryParamCfg);
	}

	public void update(QueryParamCfg queryParamCfg)  throws DAOException{
		super.update(queryParamCfg);
	}

	public void delete(QueryParamCfg queryParamCfg)  throws DAOException{
		super.delete(queryParamCfg);
	}

	public void refresh(QueryParamCfg queryParamCfg)  throws DAOException{
		super.refresh(queryParamCfg);
	}
	public String getTableName() {
		return QueryParamCfg.REF_TABLE;
	}
}
