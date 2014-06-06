package com.liusy.datapp.service.system.org;

import java.io.Serializable;
import java.util.List;

import com.liusy.dataapplatform.base.exception.ServiceException;
import com.liusy.dataapplatform.base.util.PageQuery;
import com.liusy.datapp.model.system.org.SysResourceUser;

public interface SysResourceUserService {

	public SysResourceUser findSysResourceUser(Serializable id) throws ServiceException;

	public void createSysResourceUser(SysResourceUser sysResourceUser) throws ServiceException;

	public void updateSysResourceUser(SysResourceUser sysResourceUser) throws ServiceException;

	public void removeSysResourceUser(Serializable id) throws ServiceException;

	public PageQuery querySysResourceUser(PageQuery pageQuery) throws ServiceException;

	public void removeSysResourceUsers(Serializable[] ids) throws ServiceException;

	public List queryUserRights(Serializable userId) throws ServiceException;
}
