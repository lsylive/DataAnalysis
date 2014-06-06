package com.liusy.datapp.service.blacklist.impl;

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

import com.liusy.datapp.dao.blacklist.BlacklistDeclarationDao;
import com.liusy.datapp.model.blacklist.BlacklistDeclaration;
import com.liusy.datapp.service.blacklist.BlacklistDeclarationService;

public class BlacklistDeclarationServiceImpl implements BlacklistDeclarationService {
	private static final long serialVersionUID = 1L;

	public BlacklistDeclaration findBlacklistDeclaration(Serializable id) throws ServiceException {
		try {
			return blacklistDeclarationDao.get(id);
		}
		catch (DAOException e) {
			throw new ServiceException(e);
		}
	}

	public void createBlacklistDeclaration(BlacklistDeclaration blacklistDeclaration) throws ServiceException {
		try {
			blacklistDeclarationDao.save(blacklistDeclaration);
		}
		catch (DAOException e) {
			throw new ServiceException(e);
		}
	}

	public void updateBlacklistDeclaration(BlacklistDeclaration blacklistDeclaration) throws ServiceException {
		try {
			BlacklistDeclaration tmp = blacklistDeclarationDao.get(blacklistDeclaration.getId());
			ConvertUtil.convertToModelForUpdate(tmp, blacklistDeclaration);			
			blacklistDeclarationDao.update(tmp);
		}
		catch (DAOException e) {
			throw new ServiceException(e);
		}
		catch (Exception e) {
			// TODO: handle exception
			throw new ServiceException(e);
		}
	}

	public void removeBlacklistDeclaration(Serializable id) throws ServiceException {
		try {
			blacklistDeclarationDao.delete(id);
		}
		catch (DAOException e) {
			throw new ServiceException(e);
		}
	}

	public void removeBlacklistDeclarations(Serializable[] ids) throws ServiceException {
		try {
			blacklistDeclarationDao.deleteAll(ids);
		}
		catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	public PageQuery queryBlacklistDeclaration(PageQuery pageQuery) throws ServiceException {
		try {
			PageQuery  query=blacklistDeclarationDao.queryBySelectId(pageQuery);
			pageQuery.setRecordSet(query.getRecordSet());
			pageQuery.setRecordCount(query.getRecordCount());
			return pageQuery;
		}
		catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	private BlacklistDeclarationDao	blacklistDeclarationDao;

	/**
	 * ×¢ÈëDAO
	 * 
	 * @see com.liusy.core.service.BlacklistDeclarationService#setBlacklistDeclarationDao(BlacklistDeclaration
	 *      blacklistDeclarationDao)
	 */
	public void setBlacklistDeclarationDao(BlacklistDeclarationDao blacklistDeclarationDao) {
		this.blacklistDeclarationDao = blacklistDeclarationDao;
	}
}

