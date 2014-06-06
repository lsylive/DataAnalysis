package com.liusy.datapp.dao.query.base;

import java.io.Serializable;
import org.hibernate.criterion.Order;
import com.liusy.dataapplatform.base.dao.HibernateDao;
import com.liusy.dataapplatform.base.exception.DAOException;

import com.liusy.datapp.model.query.SubjectTableRelation;

public abstract class BaseSubjectTableRelationDaoImpl extends HibernateDao {
   private static final long serialVersionUID = 1L;


	public Class getReferenceClass() {
		return SubjectTableRelation.class;
	}

	public Order getDefaultOrder() {
		return null;
	}

	public SubjectTableRelation cast(Object object) {
		return (SubjectTableRelation) object;
	}

	public SubjectTableRelation get(Serializable id)  throws DAOException{
		return (SubjectTableRelation) super.get(id);
	}

	public SubjectTableRelation load(Serializable id)  throws DAOException{
		return (SubjectTableRelation) super.load(id);
	}

	public Serializable save(SubjectTableRelation subjectTableRelation)  throws DAOException{
		return super.save(subjectTableRelation);
	}

	public void saveOrUpdate(SubjectTableRelation subjectTableRelation)  throws DAOException{
		super.saveOrUpdate(subjectTableRelation);
	}

	public void update(SubjectTableRelation subjectTableRelation)  throws DAOException{
		super.update(subjectTableRelation);
	}

	public void delete(SubjectTableRelation subjectTableRelation)  throws DAOException{
		super.delete(subjectTableRelation);
	}

	public void refresh(SubjectTableRelation subjectTableRelation)  throws DAOException{
		super.refresh(subjectTableRelation);
	}
	public String getTableName() {
		return SubjectTableRelation.REF_TABLE;
	}
}
