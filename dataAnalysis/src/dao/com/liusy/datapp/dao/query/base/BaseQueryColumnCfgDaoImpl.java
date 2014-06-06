package com.liusy.datapp.dao.query.base;

import java.io.Serializable;
import org.hibernate.criterion.Order;
import com.liusy.dataapplatform.base.dao.HibernateDao;
import com.liusy.dataapplatform.base.exception.DAOException;

import com.liusy.datapp.model.query.QueryColumnCfg;

public abstract class BaseQueryColumnCfgDaoImpl extends HibernateDao {
   private static final long serialVersionUID = 1L;


	public Class getReferenceClass() {
		return QueryColumnCfg.class;
	}

	public Order getDefaultOrder() {
		return null;
	}

	public QueryColumnCfg cast(Object object) {
		return (QueryColumnCfg) object;
	}

	public QueryColumnCfg get(Serializable id)  throws DAOException{
		return (QueryColumnCfg) super.get(id);
	}

	public QueryColumnCfg load(Serializable id)  throws DAOException{
		return (QueryColumnCfg) super.load(id);
	}

	public Serializable save(QueryColumnCfg queryColumnCfg)  throws DAOException{
		return super.save(queryColumnCfg);
	}

	public void saveOrUpdate(QueryColumnCfg queryColumnCfg)  throws DAOException{
		super.saveOrUpdate(queryColumnCfg);
	}

	public void update(QueryColumnCfg queryColumnCfg)  throws DAOException{
		super.update(queryColumnCfg);
	}

	public void delete(QueryColumnCfg queryColumnCfg)  throws DAOException{
		super.delete(queryColumnCfg);
	}

	public void refresh(QueryColumnCfg queryColumnCfg)  throws DAOException{
		super.refresh(queryColumnCfg);
	}
	public String getTableName() {
		return QueryColumnCfg.REF_TABLE;
	}
}
