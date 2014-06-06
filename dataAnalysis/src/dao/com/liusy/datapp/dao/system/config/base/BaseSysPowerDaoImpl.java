package com.liusy.datapp.dao.system.config.base;

import java.io.Serializable;
import org.hibernate.criterion.Order;
import com.liusy.dataapplatform.base.dao.HibernateDao;
import com.liusy.dataapplatform.base.exception.DAOException;

import com.liusy.datapp.model.system.config.SysPower;

public abstract class BaseSysPowerDaoImpl extends HibernateDao {
   private static final long serialVersionUID = 1L;


	public Class getReferenceClass() {
		return SysPower.class;
	}

	public Order getDefaultOrder() {
		return null;
	}

	public SysPower cast(Object object) {
		return (SysPower) object;
	}

	public SysPower get(Serializable id)  throws DAOException{
		return (SysPower) super.get(id);
	}

	public SysPower load(Serializable id)  throws DAOException{
		return (SysPower) super.load(id);
	}

	public Serializable save(SysPower sysPower)  throws DAOException{
		return super.save(sysPower);
	}

	public void saveOrUpdate(SysPower sysPower)  throws DAOException{
		super.saveOrUpdate(sysPower);
	}

	public void update(SysPower sysPower)  throws DAOException{
		super.update(sysPower);
	}

	public void delete(SysPower sysPower)  throws DAOException{
		super.delete(sysPower);
	}

	public void refresh(SysPower sysPower)  throws DAOException{
		super.refresh(sysPower);
	}

	public String getTableName() {
		return SysPower.REF_TABLE;
	}
	
}
