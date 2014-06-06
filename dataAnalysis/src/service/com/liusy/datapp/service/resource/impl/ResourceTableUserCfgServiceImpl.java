package com.liusy.datapp.service.resource.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.hibernate.criterion.Order;

import com.liusy.dataapplatform.base.exception.DAOException;
import com.liusy.dataapplatform.base.exception.ServiceException;
import com.liusy.dataapplatform.base.util.Condition;
import com.liusy.dataapplatform.base.util.ConvertUtil;
import com.liusy.dataapplatform.base.util.ICondition;
import com.liusy.dataapplatform.base.util.PageQuery;

import com.liusy.datapp.dao.resource.ResourceTableUserCfgDao;
import com.liusy.datapp.model.resource.ResourceTableUserCfg;
import com.liusy.datapp.service.resource.ResourceTableUserCfgService;

public class ResourceTableUserCfgServiceImpl implements ResourceTableUserCfgService {
	private static final long serialVersionUID = 1L;

	public ResourceTableUserCfg findResourceTableUserCfg(Serializable id) throws ServiceException {
		try {
			return resourceTableUserCfgDao.get(id);
		}
		catch (DAOException e) {
			throw new ServiceException(e);
		}
	}

	public void createResourceTableUserCfg(ResourceTableUserCfg resourceTableUserCfg) throws ServiceException {
		try {
			resourceTableUserCfgDao.save(resourceTableUserCfg);
		}
		catch (DAOException e) {
			throw new ServiceException(e);
		}
	}

	public void updateResourceTableUserCfg(ResourceTableUserCfg resourceTableUserCfg) throws ServiceException {
		try {
			ResourceTableUserCfg tmp = resourceTableUserCfgDao.get(resourceTableUserCfg.getId());
			ConvertUtil.convertToModelForUpdate(tmp, resourceTableUserCfg);			
			resourceTableUserCfgDao.update(tmp);
		}
		catch (DAOException e) {
			throw new ServiceException(e);
		}
		catch (Exception e) {
			// TODO: handle exception
			throw new ServiceException(e);
		}
	}

	public void removeResourceTableUserCfg(Serializable id) throws ServiceException {
		try {
			resourceTableUserCfgDao.delete(id);
		}
		catch (DAOException e) {
			throw new ServiceException(e);
		}
	}

	public void removeResourceTableUserCfgs(Serializable[] ids) throws ServiceException {
		try {
			resourceTableUserCfgDao.deleteAll(ids);
		}
		catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	public PageQuery queryResourceTableUserCfg(PageQuery pageQuery) throws ServiceException {
		try {
			PageQuery  query=resourceTableUserCfgDao.queryBySelectId(pageQuery);
			pageQuery.setRecordSet(query.getRecordSet());
			pageQuery.setRecordCount(query.getRecordCount());
			return pageQuery;
		}
		catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	private ResourceTableUserCfgDao	resourceTableUserCfgDao;

	/**
	 * ×¢ÈëDAO
	 * 
	 * @see com.liusy.core.service.ResourceTableUserCfgService#setResourceTableUserCfgDao(ResourceTableUserCfg
	 *      resourceTableUserCfgDao)
	 */
	public void setResourceTableUserCfgDao(ResourceTableUserCfgDao resourceTableUserCfgDao) {
		this.resourceTableUserCfgDao = resourceTableUserCfgDao;
	}
	
	public ResourceTableUserCfg findByTableIdAndUserId(Serializable tableId,Serializable userId) throws ServiceException
	{
		try
		{
			List<ICondition> conditions = new ArrayList<ICondition>();
			conditions.add(new Condition(ResourceTableUserCfg.PROP_TABLE_ID,Condition.EQUALS,tableId));
			conditions.add(new Condition(ResourceTableUserCfg.PROP_USER_ID,Condition.EQUALS,userId));
			List<ResourceTableUserCfg> temp = resourceTableUserCfgDao.commonQuery(conditions);
			if (temp!=null&&!temp.isEmpty())
			{
				return temp.get(0);
			}
		}
		catch (Exception e)
		{
			throw new ServiceException();
		}
		return null;
	}
}

