package com.liusy.datapp.service.system.config;

import java.io.Serializable;

import com.liusy.dataapplatform.base.exception.ServiceException;
import com.liusy.dataapplatform.base.util.PageQuery;
import com.liusy.datapp.model.system.config.SysCity;

public interface SysCityService {

	public SysCity findSysCity(Serializable id) throws ServiceException;

	public void createSysCity(SysCity sysCity) throws ServiceException;

	public void updateSysCity(SysCity sysCity) throws ServiceException;

	public void removeSysCity(Serializable id) throws ServiceException;

	public PageQuery querySysCity(PageQuery pageQuery) throws ServiceException;

	public void removeSysCitys(Serializable[] ids) throws ServiceException;
	public SysCity findSysCityByCode(String cityCode) throws ServiceException;

}

