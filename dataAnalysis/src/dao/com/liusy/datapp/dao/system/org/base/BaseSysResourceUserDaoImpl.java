package com.liusy.datapp.dao.system.org.base;

import java.io.Serializable;
import org.hibernate.criterion.Order;
import com.liusy.dataapplatform.base.dao.HibernateDao;
import com.liusy.dataapplatform.base.exception.DAOException;

import com.liusy.datapp.model.system.org.SysResourceUser;

public abstract class BaseSysResourceUserDaoImpl extends HibernateDao {
   private static final long serialVersionUID = 1L;


	public Class getReferenceClass() {
		return SysResourceUser.class;
	}

	public Order getDefaultOrder() {
		return null;
	}

	public SysResourceUser cast(Object object) {
		return (SysResourceUser) object;
	}

	public SysResourceUser get(Serializable id)  throws DAOException{
		return (SysResourceUser) super.get(id);
	}

	public SysResourceUser load(Serializable id)  throws DAOException{
		return (SysResourceUser) super.load(id);
	}

	public Serializable save(SysResourceUser sysResourceUser)  throws DAOException{
		return super.save(sysResourceUser);
	}

	public void saveOrUpdate(SysResourceUser sysResourceUser)  throws DAOException{
		super.saveOrUpdate(sysResourceUser);
	}

	public void update(SysResourceUser sysResourceUser)  throws DAOException{
		super.update(sysResourceUser);
	}

	public void delete(SysResourceUser sysResourceUser)  throws DAOException{
		super.delete(sysResourceUser);
	}

	public void refresh(SysResourceUser sysResourceUser)  throws DAOException{
		super.refresh(sysResourceUser);
	}

	public String getTableName() {
		return SysResourceUser.REF_TABLE;
	}
	
}
