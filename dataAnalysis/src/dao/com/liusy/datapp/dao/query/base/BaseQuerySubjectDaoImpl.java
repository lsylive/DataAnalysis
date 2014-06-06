package com.liusy.datapp.dao.query.base;

import java.io.Serializable;
import org.hibernate.criterion.Order;
import com.liusy.dataapplatform.base.dao.HibernateDao;
import com.liusy.dataapplatform.base.exception.DAOException;

import com.liusy.datapp.model.query.QuerySubject;

public abstract class BaseQuerySubjectDaoImpl extends HibernateDao {
   private static final long serialVersionUID = 1L;


	public Class getReferenceClass() {
		return QuerySubject.class;
	}

	public Order getDefaultOrder() {
		return null;
	}

	public QuerySubject cast(Object object) {
		return (QuerySubject) object;
	}

	public QuerySubject get(Serializable id)  throws DAOException{
		return (QuerySubject) super.get(id);
	}

	public QuerySubject load(Serializable id)  throws DAOException{
		return (QuerySubject) super.load(id);
	}

	public Serializable save(QuerySubject querySubject)  throws DAOException{
		return super.save(querySubject);
	}

	public void saveOrUpdate(QuerySubject querySubject)  throws DAOException{
		super.saveOrUpdate(querySubject);
	}

	public void update(QuerySubject querySubject)  throws DAOException{
		super.update(querySubject);
	}

	public void delete(QuerySubject querySubject)  throws DAOException{
		super.delete(querySubject);
	}

	public void refresh(QuerySubject querySubject)  throws DAOException{
		super.refresh(querySubject);
	}
	public String getTableName() {
		return QuerySubject.REF_TABLE;
	}
}
