package com.liusy.datapp.service.system.org.impl;

import java.io.Serializable;
import java.util.List;

import com.liusy.dataapplatform.base.exception.DAOException;
import com.liusy.dataapplatform.base.exception.ServiceException;
import com.liusy.dataapplatform.base.util.ConvertUtil;
import com.liusy.dataapplatform.base.util.PageQuery;
import com.liusy.datapp.dao.system.org.SysResourceUserDao;
import com.liusy.datapp.model.system.org.SysResourceUser;
import com.liusy.datapp.service.system.org.SysResourceUserService;

public class SysResourceUserServiceImpl implements SysResourceUserService {
	private static final long	serialVersionUID	= 1L;

	public SysResourceUser findSysResourceUser(Serializable id) throws ServiceException {
		try {
			return sysResourceUserDao.get(id);
		}
		catch (DAOException e) {
			throw new ServiceException(e);
		}
	}

	public void createSysResourceUser(SysResourceUser sysResourceUser) throws ServiceException {
		try {
			sysResourceUserDao.save(sysResourceUser);
		}
		catch (DAOException e) {
			throw new ServiceException(e);
		}
	}

	public void updateSysResourceUser(SysResourceUser sysResourceUser) throws ServiceException {
		try {
			SysResourceUser tmp = sysResourceUserDao.get(sysResourceUser.getId());
			ConvertUtil.convertToModelForUpdate(tmp, sysResourceUser);
			sysResourceUserDao.update(tmp);
		}
		catch (DAOException e) {
			throw new ServiceException(e);
		}
		catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	public void removeSysResourceUser(Serializable id) throws ServiceException {
		try {
			sysResourceUserDao.delete(id);
		}
		catch (DAOException e) {
			throw new ServiceException(e);
		}
	}

	public void removeSysResourceUsers(Serializable[] ids) throws ServiceException {
		try {
			sysResourceUserDao.deleteAll(ids);
		}
		catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	public PageQuery querySysResourceUser(PageQuery pageQuery) throws ServiceException {
		try {
			PageQuery query = sysResourceUserDao.queryBySelectId(pageQuery);
			pageQuery.setRecordSet(query.getRecordSet());
			pageQuery.setRecordCount(query.getRecordCount());
			return pageQuery;
		}
		catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	public List queryUserRights(Serializable userId) throws ServiceException {
		try {
			return sysResourceUserDao.findByField(SysResourceUser.PROP_USER_ID, userId);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException(e);
		}
	}

	private SysResourceUserDao	sysResourceUserDao;

	/**
	 * ×¢ÈëDAO
	 * 
	 * @see com.liusy.core.service.SysResourceUserService#setSysResourceUserDao(SysResourceUser
	 *      sysResourceUserDao)
	 */
	public void setSysResourceUserDao(SysResourceUserDao sysResourceUserDao) {
		this.sysResourceUserDao = sysResourceUserDao;
	}
}
