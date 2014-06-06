package com.liusy.datapp.service.system.org;

import java.io.Serializable;
import java.util.List;

import com.liusy.dataapplatform.base.exception.ServiceException;
import com.liusy.dataapplatform.base.util.PageQuery;
import com.liusy.datapp.model.system.org.SysResourceRole;

public interface SysResourceRoleService {

	public SysResourceRole findSysResourceRole(Serializable id) throws ServiceException;

	public void createSysResourceRole(SysResourceRole sysResourceRole) throws ServiceException;

	public void updateSysResourceRole(SysResourceRole sysResourceRole) throws ServiceException;

	public void removeSysResourceRole(Serializable id) throws ServiceException;

	public PageQuery querySysResourceRole(PageQuery pageQuery) throws ServiceException;

	public void removeSysResourceRoles(Serializable[] ids) throws ServiceException;  

	public List queryRoleRights(Serializable roleId) throws ServiceException;
}

