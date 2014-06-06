package com.liusy.datapp.dao.system.config.base;

import java.io.Serializable;
import org.hibernate.criterion.Order;
import com.liusy.dataapplatform.base.dao.HibernateDao;
import com.liusy.dataapplatform.base.exception.DAOException;

import com.liusy.datapp.model.system.config.SysCodeSet;

public abstract class BaseSysCodeSetDaoImpl extends HibernateDao {
   private static final long serialVersionUID = 1L;


	public Class getReferenceClass() {
		return SysCodeSet.class;
	}

	public Order getDefaultOrder() {
		return null;
	}

	public SysCodeSet cast(Object object) {
		return (SysCodeSet) object;
	}

	public SysCodeSet get(Serializable id)  throws DAOException{
		return (SysCodeSet) super.get(id);
	}

	public SysCodeSet load(Serializable id)  throws DAOException{
		return (SysCodeSet) super.load(id);
	}

	public Serializable save(SysCodeSet sysCodeSet)  throws DAOException{
		return super.save(sysCodeSet);
	}

	public void saveOrUpdate(SysCodeSet sysCodeSet)  throws DAOException{
		super.saveOrUpdate(sysCodeSet);
	}

	public void update(SysCodeSet sysCodeSet)  throws DAOException{
		super.update(sysCodeSet);
	}

	public void delete(SysCodeSet sysCodeSet)  throws DAOException{
		super.delete(sysCodeSet);
	}

	public void refresh(SysCodeSet sysCodeSet)  throws DAOException{
		super.refresh(sysCodeSet);
	}
	public String getTableName() {
		return SysCodeSet.REF_TABLE;
	}
}
