package com.liusy.datapp.service.system.config.impl;

import java.io.Serializable;
import java.util.List;

import org.hibernate.criterion.Order;

import com.liusy.dataapplatform.base.exception.DAOException;
import com.liusy.dataapplatform.base.exception.ServiceException;
import com.liusy.dataapplatform.base.util.ConvertUtil;
import com.liusy.dataapplatform.base.util.PageQuery;
import com.liusy.datapp.dao.system.config.SysResourceDao;
import com.liusy.datapp.model.system.config.SysResource;
import com.liusy.datapp.service.system.config.SysResourceService;

public class SysResourceServiceImpl implements SysResourceService {
	private static final long	serialVersionUID	= 1L;

	public SysResource findSysResource(Serializable id) throws ServiceException {
		try {
			return sysResourceDao.get(id);
		}
		catch (DAOException e) {
			throw new ServiceException(e);
		}
	}

	public void createSysResource(SysResource sysResource) throws ServiceException {
		try {
			sysResourceDao.save(sysResource);
		}
		catch (DAOException e) {
			throw new ServiceException(e);
		}
	}

	public void updateSysResource(SysResource sysResource) throws ServiceException {
		try {
			SysResource tmp = sysResourceDao.get(sysResource.getId());
			ConvertUtil.convertToModelForUpdate(tmp, sysResource);
			sysResourceDao.update(tmp);
		}
		catch (DAOException e) {
			throw new ServiceException(e);
		}
		catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	public void removeSysResource(Serializable id) throws ServiceException {
		try {
			sysResourceDao.delete(id);
		}
		catch (DAOException e) {
			throw new ServiceException(e);
		}
	}

	public void removeSysResources(Serializable[] ids) throws ServiceException {
		try {
			sysResourceDao.deleteAll(ids);
		}
		catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	public PageQuery querySysResource(PageQuery pageQuery) throws ServiceException {
		try {
			PageQuery query = sysResourceDao.queryBySelectId(pageQuery);
			pageQuery.setRecordSet(query.getRecordSet());
			pageQuery.setRecordCount(query.getRecordCount());
			return pageQuery;
		}
		catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	public List findAll() throws ServiceException {
		try {
			Order order = Order.asc(SysResource.PROP_PARENT_ID);
			return sysResourceDao.findAll(order);
		}
		catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	public boolean needCheck(String url) throws ServiceException {
		if (url == null) return false;
		
		List list = sysResourceDao.findByField(SysResource.PROP_ACTION_URL, url);
		if (list != null && list.size() > 0) return true;
		else return false;
	}

	private SysResourceDao	sysResourceDao;

	/**
	 * ×¢ÈëDAO
	 * 
	 * @see com.liusy.core.service.SysResourceService#setSysResourceDao(SysResource
	 *      sysResourceDao)
	 */
	public void setSysResourceDao(SysResourceDao sysResourceDao) {
		this.sysResourceDao = sysResourceDao;
	}
}
