package com.liusy.datapp.service.system.org;

import java.io.Serializable;
import java.util.List;

import com.liusy.dataapplatform.base.exception.ServiceException;
import com.liusy.datapp.model.system.org.SysDept;
import com.liusy.datapp.model.system.org.SysOrg;

public interface SysOrgService {
	public static String	SYSORG	= "1";

	public SysOrg findSysOrg(Serializable id) throws ServiceException;

	public void createSysOrg(SysOrg sysOrg) throws ServiceException;

	public void updateSysOrg(SysOrg sysOrg) throws ServiceException;

	public void removeSysOrg(Serializable id) throws ServiceException;

	public void removeSysOrgs(Serializable[] ids) throws ServiceException;

	public SysDept findSysDept(Serializable id) throws ServiceException;

	public void createSysDept(SysDept sysDept) throws ServiceException;

	public void updateSysDept(SysDept sysDept) throws ServiceException;

	public void removeSysDept(Serializable id) throws ServiceException;

	public List<SysDept> findSysDepts(Serializable orgId) throws ServiceException;

	// 查询所有的单位
	public List<SysOrg> findSysOrgAll() throws ServiceException;

	public List findChildOrgs(Serializable orgId) throws ServiceException;

	public List findChildDeptsByOrgId(Serializable orgId) throws ServiceException;

	public List findChildDeptsByDeptId(Serializable deptId) throws ServiceException;

}
