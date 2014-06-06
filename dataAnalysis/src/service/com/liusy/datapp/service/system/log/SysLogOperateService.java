package com.liusy.datapp.service.system.log;

import java.io.Serializable;
import java.util.Collection;

import com.liusy.dataapplatform.base.exception.ServiceException;
import com.liusy.dataapplatform.base.util.PagerObject;
import com.liusy.dataapplatform.base.util.PageQuery;

import com.liusy.datapp.dao.system.log.SysLogOperateDao;
import com.liusy.datapp.model.system.log.SysLogOperate;

public interface SysLogOperateService {

	public SysLogOperate findSysLogOperate(Serializable id) throws ServiceException;

	public void createSysLogOperate(SysLogOperate sysLogOperate) throws ServiceException;

	public void updateSysLogOperate(SysLogOperate sysLogOperate) throws ServiceException;

	public void removeSysLogOperate(Serializable id) throws ServiceException;

	public PageQuery querySysLogOperate(PageQuery pageQuery) throws ServiceException;

	public void removeSysLogOperates(Serializable[] ids) throws ServiceException;  
}

