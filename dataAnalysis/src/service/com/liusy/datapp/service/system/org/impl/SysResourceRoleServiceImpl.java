package com.liusy.datapp.service.system.org.impl;

import java.io.Serializable;
import java.util.List;

import com.liusy.dataapplatform.base.exception.DAOException;
import com.liusy.dataapplatform.base.exception.ServiceException;
import com.liusy.dataapplatform.base.util.ConvertUtil;
import com.liusy.dataapplatform.base.util.PageQuery;
import com.liusy.datapp.dao.system.org.SysResourceRoleDao;
import com.liusy.datapp.model.system.org.SysResourceRole;
import com.liusy.datapp.service.system.org.SysResourceRoleService;

public class SysResourceRoleServiceImpl implements SysResourceRoleService {
	private static final long serialVersionUID = 1L;

	public SysResourceRole findSysResourceRole(Serializable id) throws ServiceException {
		try {
			return sysResourceRoleDao.get(id);
		}
		catch (DAOException e) {
			throw new ServiceException(e);
		}
	}

	public void createSysResourceRole(SysResourceRole sysResourceRole) throws ServiceException {
		try {
			sysResourceRoleDao.save(sysResourceRole);
		}
		catch (DAOException e) {
			throw new ServiceException(e);
		}
	}

	public void updateSysResourceRole(SysResourceRole sysResourceRole) throws ServiceException {
		try {
			SysResourceRole tmp = sysResourceRoleDao.get(sysResourceRole.getId());
			ConvertUtil.convertToModelForUpdate(tmp, sysResourceRole);			
			sysResourceRoleDao.update(tmp);
		}
		catch (DAOException e) {
			throw new ServiceException(e);
		}
		catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	public void removeSysResourceRole(Serializable id) throws ServiceException {
		try {
			sysResourceRoleDao.delete(id);
		}
		catch (DAOException e) {
			throw new ServiceException(e);
		}
	}

	public void removeSysResourceRoles(Serializable[] ids) throws ServiceException {
		try {
			sysResourceRoleDao.deleteAll(ids);
		}
		catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	public PageQuery querySysResourceRole(PageQuery pageQuery) throws ServiceException {
		try {
			PageQuery  query=sysResourceRoleDao.queryBySelectId(pageQuery);
			pageQuery.setRecordSet(query.getRecordSet());
			pageQuery.setRecordCount(query.getRecordCount());
			return pageQuery;
		}
		catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	public List queryRoleRights(Serializable roleId) throws ServiceException {
		try {
			return sysResourceRoleDao.findByField(SysResourceRole.PROP_ROLE_ID, roleId);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException(e);
		}
	}
	
	private SysResourceRoleDao	sysResourceRoleDao;

	/**
	 * ×¢ÈëDAO
	 * 
	 * @see com.liusy.core.service.SysResourceRoleService#setSysResourceRoleDao(SysResourceRole
	 *      sysResourceRoleDao)
	 */
	public void setSysResourceRoleDao(SysResourceRoleDao sysResourceRoleDao) {
		this.sysResourceRoleDao = sysResourceRoleDao;
	}
}

