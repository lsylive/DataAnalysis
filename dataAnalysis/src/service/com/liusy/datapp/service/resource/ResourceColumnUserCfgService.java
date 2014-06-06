package com.liusy.datapp.service.resource;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import com.liusy.dataapplatform.base.exception.ServiceException;
import com.liusy.dataapplatform.base.util.PagerObject;
import com.liusy.dataapplatform.base.util.PageQuery;

import com.liusy.datapp.dao.resource.ResourceColumnUserCfgDao;
import com.liusy.datapp.model.resource.ResourceColumnUserCfg;
import com.liusy.datapp.service.util.SynthesisUserColumnCfgParam;
import com.liusy.datapp.util.poolobj.ColumnPoolObj;

public interface ResourceColumnUserCfgService {

	public ResourceColumnUserCfg findResourceColumnUserCfg(Serializable id) throws ServiceException;

	public void createResourceColumnUserCfg(ResourceColumnUserCfg resourceColumnUserCfg) throws ServiceException;

	public void updateResourceColumnUserCfg(ResourceColumnUserCfg resourceColumnUserCfg) throws ServiceException;

	public void removeResourceColumnUserCfg(Serializable id) throws ServiceException;

	public PageQuery queryResourceColumnUserCfg(PageQuery pageQuery) throws ServiceException;

	public void removeResourceColumnUserCfgs(Serializable[] ids) throws ServiceException;
	
	public List<SynthesisUserColumnCfgParam> getTableColumnSercurityCfg(String tableId,List<ColumnPoolObj> orglist) throws ServiceException;
	
	public List<ResourceColumnUserCfg> findByTableAndUserId(Serializable tableId,Serializable userId)throws ServiceException;
}

