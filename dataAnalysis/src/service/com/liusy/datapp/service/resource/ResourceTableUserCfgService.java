package com.liusy.datapp.service.resource;

import java.io.Serializable;
import java.util.Collection;

import com.liusy.dataapplatform.base.exception.ServiceException;
import com.liusy.dataapplatform.base.util.PagerObject;
import com.liusy.dataapplatform.base.util.PageQuery;

import com.liusy.datapp.dao.resource.ResourceTableUserCfgDao;
import com.liusy.datapp.model.resource.ResourceTableUserCfg;

public interface ResourceTableUserCfgService {

	public ResourceTableUserCfg findResourceTableUserCfg(Serializable id) throws ServiceException;

	public void createResourceTableUserCfg(ResourceTableUserCfg resourceTableUserCfg) throws ServiceException;

	public void updateResourceTableUserCfg(ResourceTableUserCfg resourceTableUserCfg) throws ServiceException;

	public void removeResourceTableUserCfg(Serializable id) throws ServiceException;

	public PageQuery queryResourceTableUserCfg(PageQuery pageQuery) throws ServiceException;

	public void removeResourceTableUserCfgs(Serializable[] ids) throws ServiceException;  
	
	public ResourceTableUserCfg findByTableIdAndUserId(Serializable tableId,Serializable userId)throws ServiceException;
}

