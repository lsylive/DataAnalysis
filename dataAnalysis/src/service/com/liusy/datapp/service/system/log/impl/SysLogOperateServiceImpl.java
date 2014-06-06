package com.liusy.datapp.service.system.log.impl;

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

import com.liusy.datapp.dao.system.log.SysLogOperateDao;
import com.liusy.datapp.model.system.log.SysLogOperate;
import com.liusy.datapp.service.system.log.SysLogOperateService;

public class SysLogOperateServiceImpl implements SysLogOperateService {
	private static final long serialVersionUID = 1L;

	public SysLogOperate findSysLogOperate(Serializable id) throws ServiceException {
		try {
			return sysLogOperateDao.get(id);
		}
		catch (DAOException e) {
			throw new ServiceException(e);
		}
	}

	public void createSysLogOperate(SysLogOperate sysLogOperate) throws ServiceException {
		try {
			sysLogOperateDao.save(sysLogOperate);
		}
		catch (DAOException e) {
			throw new ServiceException(e);
		}
	}

	public void updateSysLogOperate(SysLogOperate sysLogOperate) throws ServiceException {
		try {
			SysLogOperate tmp = sysLogOperateDao.get(sysLogOperate.getId());
			ConvertUtil.convertToModelForUpdate(tmp, sysLogOperate);			
			sysLogOperateDao.update(tmp);
		}
		catch (DAOException e) {
			throw new ServiceException(e);
		}
		catch (Exception e) {
			// TODO: handle exception
			throw new ServiceException(e);
		}
	}

	public void removeSysLogOperate(Serializable id) throws ServiceException {
		try {
			sysLogOperateDao.delete(id);
		}
		catch (DAOException e) {
			throw new ServiceException(e);
		}
	}

	public void removeSysLogOperates(Serializable[] ids) throws ServiceException {
		try {
			sysLogOperateDao.deleteAll(ids);
		}
		catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	public PageQuery querySysLogOperate(PageQuery pageQuery) throws ServiceException {
		try {
			PageQuery  query=sysLogOperateDao.queryBySelectId(pageQuery);
			pageQuery.setRecordSet(query.getRecordSet());
			pageQuery.setRecordCount(query.getRecordCount());
			return pageQuery;
		}
		catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	private SysLogOperateDao	sysLogOperateDao;

	/**
	 * ×¢ÈëDAO
	 * 
	 * @see com.liusy.core.service.SysLogOperateService#setSysLogOperateDao(SysLogOperate
	 *      sysLogOperateDao)
	 */
	public void setSysLogOperateDao(SysLogOperateDao sysLogOperateDao) {
		this.sysLogOperateDao = sysLogOperateDao;
	}
}

