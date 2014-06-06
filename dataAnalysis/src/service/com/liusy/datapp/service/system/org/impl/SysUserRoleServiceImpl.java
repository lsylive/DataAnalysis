package com.liusy.datapp.service.system.org.impl;

import java.io.Serializable;

import com.liusy.dataapplatform.base.exception.DAOException;
import com.liusy.dataapplatform.base.exception.ServiceException;
import com.liusy.dataapplatform.base.util.ConvertUtil;
import com.liusy.dataapplatform.base.util.PageQuery;
import com.liusy.datapp.dao.system.org.SysUserRoleDao;
import com.liusy.datapp.model.system.org.SysUserRole;
import com.liusy.datapp.service.system.org.SysUserRoleService;

public class SysUserRoleServiceImpl implements SysUserRoleService {
	private static final long serialVersionUID = 1L;

	public SysUserRole findSysUserRole(Serializable id) throws ServiceException {
		try {
			return sysUserRoleDao.get(id);
		}
		catch (DAOException e) {
			throw new ServiceException(e);
		}
	}

	public void createSysUserRole(SysUserRole sysUserRole) throws ServiceException {
		try {
			sysUserRoleDao.save(sysUserRole);
		}
		catch (DAOException e) {
			throw new ServiceException(e);
		}
	}

	public void updateSysUserRole(SysUserRole sysUserRole) throws ServiceException {
		try {
			SysUserRole tmp = sysUserRoleDao.get(sysUserRole.getId());
			ConvertUtil.convertToModelForUpdate(tmp, sysUserRole);			
			sysUserRoleDao.update(tmp);
		}
		catch (DAOException e) {
			throw new ServiceException(e);
		}
		catch (Exception e) {
			// TODO: handle exception
			throw new ServiceException(e);
		}
	}

	public void removeSysUserRole(Serializable id) throws ServiceException {
		try {
			sysUserRoleDao.delete(id);
		}
		catch (DAOException e) {
			throw new ServiceException(e);
		}
	}

	public void removeSysUserRoles(Serializable[] ids) throws ServiceException {
		try {
			sysUserRoleDao.deleteAll(ids);
		}
		catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	public PageQuery querySysUserRole(PageQuery pageQuery) throws ServiceException {
		try {
			PageQuery  query=sysUserRoleDao.queryBySelectId(pageQuery);
			pageQuery.setRecordSet(query.getRecordSet());
			pageQuery.setRecordCount(query.getRecordCount());
			return pageQuery;
		}
		catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	private SysUserRoleDao	sysUserRoleDao;

	/**
	 * ×¢ÈëDAO
	 * 
	 * @see com.liusy.core.service.SysUserRoleService#setSysUserRoleDao(SysUserRole
	 *      sysUserRoleDao)
	 */
	public void setSysUserRoleDao(SysUserRoleDao sysUserRoleDao) {
		this.sysUserRoleDao = sysUserRoleDao;
	}
}

