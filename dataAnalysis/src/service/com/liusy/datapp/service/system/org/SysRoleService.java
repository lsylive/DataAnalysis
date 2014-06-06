package com.liusy.datapp.service.system.org;

import java.io.Serializable;

import com.liusy.dataapplatform.base.exception.ServiceException;
import com.liusy.dataapplatform.base.util.PageQuery;
import com.liusy.datapp.model.system.org.SysRole;

public interface SysRoleService {
	
	public static String	SYSROLE	= "1";

	public SysRole findSysRole(Serializable id) throws ServiceException;

	public void createSysRole(SysRole sysRole) throws ServiceException;

	public void updateSysRole(SysRole sysRole) throws ServiceException;

	public void removeSysRole(Serializable id) throws ServiceException;

	public PageQuery querySysRole(PageQuery pageQuery) throws ServiceException;

	public void removeSysRoles(Serializable[] ids) throws ServiceException;

	public void startSysRoles(Serializable[] ids) throws ServiceException;

	public void stopSysRoles(Serializable[] ids) throws ServiceException;

	// 保存权限
	public void saveRights(Integer id, String rights) throws ServiceException;

	// 保存审核结果
	public void saveVerify(Integer id) throws ServiceException;
}
