package com.liusy.datapp.dao.query.base;

import java.io.Serializable;
import org.hibernate.criterion.Order;
import com.liusy.dataapplatform.base.dao.HibernateDao;
import com.liusy.dataapplatform.base.exception.DAOException;

import com.liusy.datapp.model.query.SubjectTableColRelation;

public abstract class BaseSubjectTableColRelationDaoImpl extends HibernateDao {
   private static final long serialVersionUID = 1L;


	public Class getReferenceClass() {
		return SubjectTableColRelation.class;
	}

	public Order getDefaultOrder() {
		return null;
	}

	public SubjectTableColRelation cast(Object object) {
		return (SubjectTableColRelation) object;
	}

	public SubjectTableColRelation get(Serializable id)  throws DAOException{
		return (SubjectTableColRelation) super.get(id);
	}

	public SubjectTableColRelation load(Serializable id)  throws DAOException{
		return (SubjectTableColRelation) super.load(id);
	}

	public Serializable save(SubjectTableColRelation subjectTableColRelation)  throws DAOException{
		return super.save(subjectTableColRelation);
	}

	public void saveOrUpdate(SubjectTableColRelation subjectTableColRelation)  throws DAOException{
		super.saveOrUpdate(subjectTableColRelation);
	}

	public void update(SubjectTableColRelation subjectTableColRelation)  throws DAOException{
		super.update(subjectTableColRelation);
	}

	public void delete(SubjectTableColRelation subjectTableColRelation)  throws DAOException{
		super.delete(subjectTableColRelation);
	}

	public void refresh(SubjectTableColRelation subjectTableColRelation)  throws DAOException{
		super.refresh(subjectTableColRelation);
	}
	public String getTableName() {
		return SubjectTableColRelation.REF_TABLE;
	}
}
