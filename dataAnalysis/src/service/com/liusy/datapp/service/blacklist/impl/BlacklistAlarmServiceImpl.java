package com.liusy.datapp.service.blacklist.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.hibernate.criterion.Order;

import com.liusy.dataapplatform.base.exception.DAOException;
import com.liusy.dataapplatform.base.exception.ServiceException;
import com.liusy.dataapplatform.base.util.Condition;
import com.liusy.dataapplatform.base.util.ConvertUtil;
import com.liusy.dataapplatform.base.util.ICondition;
import com.liusy.dataapplatform.base.util.PageQuery;

import com.liusy.datapp.dao.blacklist.BlacklistAlarmDao;
import com.liusy.datapp.model.blacklist.BlacklistAlarm;
import com.liusy.datapp.service.blacklist.BlacklistAlarmService;

public class BlacklistAlarmServiceImpl implements BlacklistAlarmService {
	private static final long serialVersionUID = 1L;

	public BlacklistAlarm findBlacklistAlarm(Serializable id) throws ServiceException {
		try {
			return blacklistAlarmDao.get(id);
		}
		catch (DAOException e) {
			throw new ServiceException(e);
		}
	}

	public void createBlacklistAlarm(BlacklistAlarm blacklistAlarm) throws ServiceException {
		try {
			blacklistAlarmDao.save(blacklistAlarm);
		}
		catch (DAOException e) {
			throw new ServiceException(e);
		}
	}

	public void updateBlacklistAlarm(BlacklistAlarm blacklistAlarm) throws ServiceException {
		try {
			BlacklistAlarm tmp = blacklistAlarmDao.get(blacklistAlarm.getId());
			ConvertUtil.convertToModelForUpdate(tmp, blacklistAlarm);			
			blacklistAlarmDao.update(tmp);
		}
		catch (DAOException e) {
			throw new ServiceException(e);
		}
		catch (Exception e) {
			// TODO: handle exception
			throw new ServiceException(e);
		}
	}

	public void removeBlacklistAlarm(Serializable id) throws ServiceException {
		try {
			blacklistAlarmDao.delete(id);
		}
		catch (DAOException e) {
			throw new ServiceException(e);
		}
	}

	public void removeBlacklistAlarms(Serializable[] ids) throws ServiceException {
		try {
			blacklistAlarmDao.deleteAll(ids);
		}
		catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	public PageQuery queryBlacklistAlarm(PageQuery pageQuery) throws ServiceException {
		try {
			PageQuery  query=blacklistAlarmDao.queryBySelectId(pageQuery);
			pageQuery.setRecordSet(query.getRecordSet());
			pageQuery.setRecordCount(query.getRecordCount());
			return pageQuery;
		}
		catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	private BlacklistAlarmDao	blacklistAlarmDao;

	/**
	 * ×¢ÈëDAO
	 * 
	 * @see com.liusy.core.service.BlacklistAlarmService#setBlacklistAlarmDao(BlacklistAlarm
	 *      blacklistAlarmDao)
	 */
	public void setBlacklistAlarmDao(BlacklistAlarmDao blacklistAlarmDao) {
		this.blacklistAlarmDao = blacklistAlarmDao;
	}
}

