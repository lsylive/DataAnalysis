package com.liusy.datapp.service.system.config;

import java.io.Serializable;

import com.liusy.dataapplatform.base.exception.ServiceException;
import com.liusy.dataapplatform.base.util.PageQuery;
import com.liusy.datapp.model.system.config.SysDirection;

public interface SysDirectionService {

	public SysDirection findSysDirection(Serializable id) throws ServiceException;

	public void createSysDirection(SysDirection sysDirection) throws ServiceException;

	public void updateSysDirection(SysDirection sysDirection) throws ServiceException;

	public void removeSysDirection(Serializable id) throws ServiceException;

	public PageQuery querySysDirection(PageQuery pageQuery) throws ServiceException;

	public void removeSysDirections(Serializable[] ids) throws ServiceException;  
}

