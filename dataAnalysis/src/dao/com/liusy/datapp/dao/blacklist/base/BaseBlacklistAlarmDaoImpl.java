package com.liusy.datapp.dao.blacklist.base;

import java.io.Serializable;
import org.hibernate.criterion.Order;
import com.liusy.dataapplatform.base.dao.HibernateDao;
import com.liusy.dataapplatform.base.exception.DAOException;

import com.liusy.datapp.model.blacklist.BlacklistAlarm;

public abstract class BaseBlacklistAlarmDaoImpl extends HibernateDao {
   private static final long serialVersionUID = 1L;


	public Class getReferenceClass() {
		return BlacklistAlarm.class;
	}

	public Order getDefaultOrder() {
		return null;
	}

	public BlacklistAlarm cast(Object object) {
		return (BlacklistAlarm) object;
	}

	public BlacklistAlarm get(Serializable id)  throws DAOException{
		return (BlacklistAlarm) super.get(id);
	}

	public BlacklistAlarm load(Serializable id)  throws DAOException{
		return (BlacklistAlarm) super.load(id);
	}

	public Serializable save(BlacklistAlarm blacklistAlarm)  throws DAOException{
		return super.save(blacklistAlarm);
	}

	public void saveOrUpdate(BlacklistAlarm blacklistAlarm)  throws DAOException{
		super.saveOrUpdate(blacklistAlarm);
	}

	public void update(BlacklistAlarm blacklistAlarm)  throws DAOException{
		super.update(blacklistAlarm);
	}

	public void delete(BlacklistAlarm blacklistAlarm)  throws DAOException{
		super.delete(blacklistAlarm);
	}

	public void refresh(BlacklistAlarm blacklistAlarm)  throws DAOException{
		super.refresh(blacklistAlarm);
	}
}
