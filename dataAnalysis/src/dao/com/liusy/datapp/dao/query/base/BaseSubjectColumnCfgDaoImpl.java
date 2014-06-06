package com.liusy.datapp.dao.query.base;

import java.io.Serializable;
import org.hibernate.criterion.Order;
import com.liusy.dataapplatform.base.dao.HibernateDao;
import com.liusy.dataapplatform.base.exception.DAOException;

import com.liusy.datapp.model.query.SubjectColumnCfg;

public abstract class BaseSubjectColumnCfgDaoImpl extends HibernateDao {
   private static final long serialVersionUID = 1L;


	public Class getReferenceClass() {
		return SubjectColumnCfg.class;
	}

	public Order getDefaultOrder() {
		return null;
	}

	public SubjectColumnCfg cast(Object object) {
		return (SubjectColumnCfg) object;
	}

	public SubjectColumnCfg get(Serializable id)  throws DAOException{
		return (SubjectColumnCfg) super.get(id);
	}

	public SubjectColumnCfg load(Serializable id)  throws DAOException{
		return (SubjectColumnCfg) super.load(id);
	}

	public Serializable save(SubjectColumnCfg subjectColumnCfg)  throws DAOException{
		return super.save(subjectColumnCfg);
	}

	public void saveOrUpdate(SubjectColumnCfg subjectColumnCfg)  throws DAOException{
		super.saveOrUpdate(subjectColumnCfg);
	}

	public void update(SubjectColumnCfg subjectColumnCfg)  throws DAOException{
		super.update(subjectColumnCfg);
	}

	public void delete(SubjectColumnCfg subjectColumnCfg)  throws DAOException{
		super.delete(subjectColumnCfg);
	}

	public void refresh(SubjectColumnCfg subjectColumnCfg)  throws DAOException{
		super.refresh(subjectColumnCfg);
	}
	public String getTableName() {
		return SubjectColumnCfg.REF_TABLE;
	}
}
