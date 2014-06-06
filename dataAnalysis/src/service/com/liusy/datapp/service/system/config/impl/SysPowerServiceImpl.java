package com.liusy.datapp.service.system.config.impl;

import java.io.Serializable;

import com.liusy.dataapplatform.base.exception.DAOException;
import com.liusy.dataapplatform.base.exception.ServiceException;
import com.liusy.dataapplatform.base.util.ConvertUtil;
import com.liusy.dataapplatform.base.util.PageQuery;
import com.liusy.datapp.dao.system.config.SysPowerDao;
import com.liusy.datapp.model.system.config.SysPower;
import com.liusy.datapp.service.system.config.SysPowerService;

public class SysPowerServiceImpl implements SysPowerService {
	private static final long serialVersionUID = 1L;

	public SysPower findSysPower(Serializable id) throws ServiceException {
		try {
			return sysPowerDao.get(id);
		}
		catch (DAOException e) {
			throw new ServiceException(e);
		}
	}

	public void createSysPower(SysPower sysPower) throws ServiceException {
		try {
			sysPowerDao.save(sysPower);
		}
		catch (DAOException e) {
			throw new ServiceException(e);
		}
	}

	public void updateSysPower(SysPower sysPower) throws ServiceException {
		try {
			SysPower tmp = sysPowerDao.get(sysPower.getId());
			ConvertUtil.convertToModelForUpdate(tmp, sysPower);			
			sysPowerDao.update(tmp);
		}
		catch (DAOException e) {
			throw new ServiceException(e);
		}
		catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	public void removeSysPower(Serializable id) throws ServiceException {
		try {
			sysPowerDao.delete(id);
		}
		catch (DAOException e) {
			throw new ServiceException(e);
		}
	}

	public void removeSysPowers(Serializable[] ids) throws ServiceException {
		try {
			sysPowerDao.deleteAll(ids);
		}
		catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	public PageQuery querySysPower(PageQuery pageQuery) throws ServiceException {
		try {
			PageQuery  query=sysPowerDao.queryBySelectId(pageQuery);
			pageQuery.setRecordSet(query.getRecordSet());
			pageQuery.setRecordCount(query.getRecordCount());
			return pageQuery;
		}
		catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	private SysPowerDao	sysPowerDao;

	/**
	 * ×¢ÈëDAO
	 * 
	 * @see com.liusy.core.service.SysPowerService#setSysPowerDao(SysPower
	 *      sysPowerDao)
	 */
	public void setSysPowerDao(SysPowerDao sysPowerDao) {
		this.sysPowerDao = sysPowerDao;
	}
}

