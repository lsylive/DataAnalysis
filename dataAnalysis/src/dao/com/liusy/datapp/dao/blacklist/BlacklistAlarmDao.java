package com.liusy.datapp.dao.blacklist;

import java.io.Serializable;

import com.liusy.dataapplatform.base.dao.BaseDao;
import com.liusy.dataapplatform.base.exception.DAOException;

import com.liusy.datapp.model.blacklist.BlacklistAlarm;

public interface BlacklistAlarmDao extends BaseDao {

	public BlacklistAlarm cast(Object object);
	
	public BlacklistAlarm get(Serializable id) throws DAOException;

	public BlacklistAlarm load(Serializable id) throws DAOException;	
	
	public Serializable save(BlacklistAlarm blacklistAlarm) throws DAOException;

	public void saveOrUpdate(BlacklistAlarm blacklistAlarm) throws DAOException;

	public void update(BlacklistAlarm blacklistAlarm) throws DAOException;
	
	public void delete(Serializable id) throws DAOException;

	public void refresh(BlacklistAlarm blacklistAlarm) throws DAOException;	
	
}
