package com.liusy.datapp.dao.system.org.base;

import java.io.Serializable;
import org.hibernate.criterion.Order;
import com.liusy.dataapplatform.base.dao.HibernateDao;
import com.liusy.dataapplatform.base.exception.DAOException;

import com.liusy.datapp.model.system.org.SysDept;

public abstract class BaseSysDeptDaoImpl extends HibernateDao {
   private static final long serialVersionUID = 1L;


	public Class getReferenceClass() {
		return SysDept.class;
	}

	public Order getDefaultOrder() {
		return null;
	}

	public SysDept cast(Object object) {
		return (SysDept) object;
	}

	public SysDept get(Serializable id)  throws DAOException{
		return (SysDept) super.get(id);
	}

	public SysDept load(Serializable id)  throws DAOException{
		return (SysDept) super.load(id);
	}

	public Serializable save(SysDept sysDept)  throws DAOException{
		return super.save(sysDept);
	}

	public void saveOrUpdate(SysDept sysDept)  throws DAOException{
		super.saveOrUpdate(sysDept);
	}

	public void update(SysDept sysDept)  throws DAOException{
		super.update(sysDept);
	}

	public void delete(SysDept sysDept)  throws DAOException{
		super.delete(sysDept);
	}

	public void refresh(SysDept sysDept)  throws DAOException{
		super.refresh(sysDept);
	}

	public String getTableName() {
		return SysDept.REF_TABLE;
	}
}
