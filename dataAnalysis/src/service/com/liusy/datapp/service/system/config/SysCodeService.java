package com.liusy.datapp.service.system.config;

import java.io.Serializable;
import java.util.List;

import com.liusy.dataapplatform.base.exception.ServiceException;
import com.liusy.dataapplatform.base.util.PageQuery;
import com.liusy.datapp.model.system.config.SysCode;

public interface SysCodeService {

	public SysCode findSysCode(Serializable id) throws ServiceException;

	public void createSysCode(SysCode sysCode) throws ServiceException;

	public void updateSysCode(SysCode sysCode) throws ServiceException;

	public void removeSysCode(Serializable id) throws ServiceException;

	public PageQuery querySysCode(PageQuery pageQuery) throws ServiceException;

	public void removeSysCodes(Serializable[] ids) throws ServiceException;
	
	public PageQuery getSysCodeSetsForTree(PageQuery pagequery) throws ServiceException;
	public List findCodeBySetId(Integer codeSetId) throws ServiceException;
	public List findAll() throws ServiceException;
}

