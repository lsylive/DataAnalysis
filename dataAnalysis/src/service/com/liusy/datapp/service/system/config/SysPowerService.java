package com.liusy.datapp.service.system.config;

import java.io.Serializable;

import com.liusy.dataapplatform.base.exception.ServiceException;
import com.liusy.dataapplatform.base.util.PageQuery;
import com.liusy.datapp.model.system.config.SysPower;

public interface SysPowerService {

	public SysPower findSysPower(Serializable id) throws ServiceException;

	public void createSysPower(SysPower sysPower) throws ServiceException;

	public void updateSysPower(SysPower sysPower) throws ServiceException;

	public void removeSysPower(Serializable id) throws ServiceException;

	public PageQuery querySysPower(PageQuery pageQuery) throws ServiceException;

	public void removeSysPowers(Serializable[] ids) throws ServiceException;  
}

