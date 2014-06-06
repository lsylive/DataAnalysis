package com.liusy.datapp.service.system.config.impl;

import java.io.Serializable;

import com.liusy.dataapplatform.base.exception.DAOException;
import com.liusy.dataapplatform.base.exception.ServiceException;
import com.liusy.dataapplatform.base.util.ConvertUtil;
import com.liusy.dataapplatform.base.util.PageQuery;
import com.liusy.datapp.dao.system.config.SysDirectionDao;
import com.liusy.datapp.model.system.config.SysDirection;
import com.liusy.datapp.service.system.config.SysDirectionService;

public class SysDirectionServiceImpl implements SysDirectionService {
	private static final long serialVersionUID = 1L;

	public SysDirection findSysDirection(Serializable id) throws ServiceException {
		try {
			return sysDirectionDao.get(id);
		}
		catch (DAOException e) {
			throw new ServiceException(e);
		}
	}

	public void createSysDirection(SysDirection sysDirection) throws ServiceException {
		try {
			sysDirectionDao.save(sysDirection);
		}
		catch (DAOException e) {
			throw new ServiceException(e);
		}
	}

	public void updateSysDirection(SysDirection sysDirection) throws ServiceException {
		try {
			SysDirection tmp = sysDirectionDao.get(sysDirection.getId());
			ConvertUtil.convertToModelForUpdate(tmp, sysDirection);			
			sysDirectionDao.update(tmp);
		}
		catch (DAOException e) {
			throw new ServiceException(e);
		}
		catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	public void removeSysDirection(Serializable id) throws ServiceException {
		try {
			sysDirectionDao.delete(id);
		}
		catch (DAOException e) {
			throw new ServiceException(e);
		}
	}

	public void removeSysDirections(Serializable[] ids) throws ServiceException {
		try {
			sysDirectionDao.deleteAll(ids);
		}
		catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	public PageQuery querySysDirection(PageQuery pageQuery) throws ServiceException {
		try {
			PageQuery  query=sysDirectionDao.queryBySelectId(pageQuery);
			pageQuery.setRecordSet(query.getRecordSet());
			pageQuery.setRecordCount(query.getRecordCount());
			return pageQuery;
		}
		catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	private SysDirectionDao	sysDirectionDao;

	/**
	 * ×¢ÈëDAO
	 * 
	 * @see com.liusy.core.service.SysDirectionService#setSysDirectionDao(SysDirection
	 *      sysDirectionDao)
	 */
	public void setSysDirectionDao(SysDirectionDao sysDirectionDao) {
		this.sysDirectionDao = sysDirectionDao;
	}
}

