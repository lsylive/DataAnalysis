package com.liusy.datapp.dao.system.org.base;

import java.io.Serializable;

import org.hibernate.criterion.Order;

import com.liusy.dataapplatform.base.dao.HibernateDao;
import com.liusy.dataapplatform.base.exception.DAOException;
import com.liusy.datapp.model.system.org.SysOrg;

public abstract class BaseSysOrgDaoImpl extends HibernateDao {
   private static final long serialVersionUID = 1L;


	public Class getReferenceClass() {
		return SysOrg.class;
	}

	public Order getDefaultOrder() {
		return null;
	}

	public SysOrg cast(Object object) {
		return (SysOrg) object;
	}

	public SysOrg get(Serializable id)  throws DAOException{
		return (SysOrg) super.get(id);
	}

	public SysOrg load(Serializable id)  throws DAOException{
		return (SysOrg) super.load(id);
	}

	public Serializable save(SysOrg sysOrg)  throws DAOException{
		return super.save(sysOrg);
	}

	public void saveOrUpdate(SysOrg sysOrg)  throws DAOException{
		super.saveOrUpdate(sysOrg);
	}

	public void update(SysOrg sysOrg)  throws DAOException{
		super.update(sysOrg);
	}

	public void delete(SysOrg sysOrg)  throws DAOException{
		super.delete(sysOrg);
	}

	public void refresh(SysOrg sysOrg)  throws DAOException{
		super.refresh(sysOrg);
	}

	public String getTableName() {
		return SysOrg.REF_TABLE;
	}
}
