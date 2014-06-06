package com.liusy.datapp.dao.system.config.base;

import java.io.Serializable;
import org.hibernate.criterion.Order;
import com.liusy.dataapplatform.base.dao.HibernateDao;
import com.liusy.dataapplatform.base.exception.DAOException;

import com.liusy.datapp.model.system.config.SysCity;

public abstract class BaseSysCityDaoImpl extends HibernateDao {
   private static final long serialVersionUID = 1L;


	public Class getReferenceClass() {
		return SysCity.class;
	}

	public Order getDefaultOrder() {
		return null;
	}

	public SysCity cast(Object object) {
		return (SysCity) object;
	}

	public SysCity get(Serializable id)  throws DAOException{
		return (SysCity) super.get(id);
	}

	public SysCity load(Serializable id)  throws DAOException{
		return (SysCity) super.load(id);
	}

	public Serializable save(SysCity sysCity)  throws DAOException{
		return super.save(sysCity);
	}

	public void saveOrUpdate(SysCity sysCity)  throws DAOException{
		super.saveOrUpdate(sysCity);
	}

	public void update(SysCity sysCity)  throws DAOException{
		super.update(sysCity);
	}

	public void delete(SysCity sysCity)  throws DAOException{
		super.delete(sysCity);
	}

	public void refresh(SysCity sysCity)  throws DAOException{
		super.refresh(sysCity);
	}
	public String getTableName() {
		return SysCity.REF_TABLE;
	}
}
