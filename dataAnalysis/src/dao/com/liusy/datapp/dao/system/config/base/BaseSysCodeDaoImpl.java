package com.liusy.datapp.dao.system.config.base;

import java.io.Serializable;
import org.hibernate.criterion.Order;
import com.liusy.dataapplatform.base.dao.HibernateDao;
import com.liusy.dataapplatform.base.exception.DAOException;

import com.liusy.datapp.model.system.config.SysCode;

public abstract class BaseSysCodeDaoImpl extends HibernateDao {
	private static final long	serialVersionUID	= 1L;

	public Class getReferenceClass() {
		return SysCode.class;
	}

	public Order getDefaultOrder() {
		return null;
	}

	public SysCode cast(Object object) {
		return (SysCode) object;
	}

	public SysCode get(Serializable id) throws DAOException {
		return (SysCode) super.get(id);
	}

	public SysCode load(Serializable id) throws DAOException {
		return (SysCode) super.load(id);
	}

	public Serializable save(SysCode sysCode) throws DAOException {
		return super.save(sysCode);
	}

	public void saveOrUpdate(SysCode sysCode) throws DAOException {
		super.saveOrUpdate(sysCode);
	}

	public void update(SysCode sysCode) throws DAOException {
		super.update(sysCode);
	}

	public void delete(SysCode sysCode) throws DAOException {
		super.delete(sysCode);
	}

	public void refresh(SysCode sysCode) throws DAOException {
		super.refresh(sysCode);
	}

	public String getTableName() {
		return SysCode.REF_TABLE;
	}

}
