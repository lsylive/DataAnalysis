package com.liusy.datapp.service.system.config;

import java.io.Serializable;
import java.util.List;

import com.liusy.dataapplatform.base.exception.ServiceException;
import com.liusy.dataapplatform.base.util.PageQuery;
import com.liusy.datapp.model.system.config.SysResource;

public interface SysResourceService {

	public SysResource findSysResource(Serializable id) throws ServiceException;

	public void createSysResource(SysResource sysResource) throws ServiceException;

	public void updateSysResource(SysResource sysResource) throws ServiceException;

	public void removeSysResource(Serializable id) throws ServiceException;

	public PageQuery querySysResource(PageQuery pageQuery) throws ServiceException;

	public void removeSysResources(Serializable[] ids) throws ServiceException;  

	public List findAll() throws ServiceException;
	
	public boolean needCheck(String url) throws ServiceException;

}

