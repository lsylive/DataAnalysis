package com.liusy.datapp.service.system.org;

import java.io.Serializable;

import com.liusy.dataapplatform.base.exception.ServiceException;
import com.liusy.dataapplatform.base.util.PageQuery;
import com.liusy.datapp.model.system.org.SysUserRole;

public interface SysUserRoleService {

	public SysUserRole findSysUserRole(Serializable id) throws ServiceException;

	public void createSysUserRole(SysUserRole sysUserRole) throws ServiceException;

	public void updateSysUserRole(SysUserRole sysUserRole) throws ServiceException;

	public void removeSysUserRole(Serializable id) throws ServiceException;

	public PageQuery querySysUserRole(PageQuery pageQuery) throws ServiceException;

	public void removeSysUserRoles(Serializable[] ids) throws ServiceException;  
}

