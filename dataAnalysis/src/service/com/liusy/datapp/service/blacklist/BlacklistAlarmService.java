package com.liusy.datapp.service.blacklist;

import java.io.Serializable;
import java.util.Collection;

import com.liusy.dataapplatform.base.exception.ServiceException;
import com.liusy.dataapplatform.base.util.PagerObject;
import com.liusy.dataapplatform.base.util.PageQuery;

import com.liusy.datapp.dao.blacklist.BlacklistAlarmDao;
import com.liusy.datapp.model.blacklist.BlacklistAlarm;

public interface BlacklistAlarmService {

	public BlacklistAlarm findBlacklistAlarm(Serializable id) throws ServiceException;

	public void createBlacklistAlarm(BlacklistAlarm blacklistAlarm) throws ServiceException;

	public void updateBlacklistAlarm(BlacklistAlarm blacklistAlarm) throws ServiceException;

	public void removeBlacklistAlarm(Serializable id) throws ServiceException;

	public PageQuery queryBlacklistAlarm(PageQuery pageQuery) throws ServiceException;

	public void removeBlacklistAlarms(Serializable[] ids) throws ServiceException;  
}

