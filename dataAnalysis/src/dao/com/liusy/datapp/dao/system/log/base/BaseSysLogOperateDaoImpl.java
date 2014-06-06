package com.liusy.datapp.dao.system.log.base;

import java.io.Serializable;
import org.hibernate.criterion.Order;
import com.liusy.dataapplatform.base.dao.HibernateDao;
import com.liusy.dataapplatform.base.exception.DAOException;

import com.liusy.datapp.model.system.log.SysLogOperate;

public abstract class BaseSysLogOperateDaoImpl extends HibernateDao {
   private static final long serialVersionUID = 1L;


	public Class getReferenceClass() {
		return SysLogOperate.class;
	}

	public Order getDefaultOrder() {
		return null;
	}

	public SysLogOperate cast(Object object) {
		return (SysLogOperate) object;
	}

	public SysLogOperate get(Serializable id)  throws DAOException{
		return (SysLogOperate) super.get(id);
	}

	public SysLogOperate load(Serializable id)  throws DAOException{
		return (SysLogOperate) super.load(id);
	}

	public Serializable save(SysLogOperate sysLogOperate)  throws DAOException{
		return super.save(sysLogOperate);
	}

	public void saveOrUpdate(SysLogOperate sysLogOperate)  throws DAOException{
		super.saveOrUpdate(sysLogOperate);
	}

	public void update(SysLogOperate sysLogOperate)  throws DAOException{
		super.update(sysLogOperate);
	}

	public void delete(SysLogOperate sysLogOperate)  throws DAOException{
		super.delete(sysLogOperate);
	}

	public void refresh(SysLogOperate sysLogOperate)  throws DAOException{
		super.refresh(sysLogOperate);
	}

	public String getTableName() {
		return SysLogOperate.REF_TABLE;
	}
	
}
