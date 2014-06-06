package com.liusy.datapp.service.datastandard.impl;

import java.io.Serializable;
import java.util.List;

import com.liusy.dataapplatform.base.exception.DAOException;
import com.liusy.dataapplatform.base.exception.ServiceException;
import com.liusy.dataapplatform.base.util.ConvertUtil;
import com.liusy.dataapplatform.base.util.PageQuery;
import com.liusy.datapp.dao.datastandard.StandardCodeDao;
import com.liusy.datapp.model.datastandard.StandardCode;
import com.liusy.datapp.service.datastandard.StandardCodeService;

public class StandardCodeServiceImpl implements StandardCodeService {
	private static final long serialVersionUID = 1L;

	public StandardCode findStandardCode(Serializable id) throws ServiceException {
		try {
			return standardCodeDao.get(id);
		}
		catch (DAOException e) {
			throw new ServiceException(e);
		}
	}

	public void createStandardCode(StandardCode standardCode) throws ServiceException {
		try {
			standardCodeDao.save(standardCode);
		}
		catch (DAOException e) {
			throw new ServiceException(e);
		}
	}

	public void updateStandardCode(StandardCode standardCode) throws ServiceException {
		try {
			StandardCode tmp = standardCodeDao.get(standardCode.getId());
			ConvertUtil.convertToModelForUpdate(tmp, standardCode);			
			standardCodeDao.update(tmp);
		}
		catch (DAOException e) {
			throw new ServiceException(e);
		}
		catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	public void removeStandardCode(Serializable id) throws ServiceException {
		try {
			standardCodeDao.delete(id);
		}
		catch (DAOException e) {
			throw new ServiceException(e);
		}
	}

	public void removeStandardCodes(Serializable[] ids) throws ServiceException {
		try {
			standardCodeDao.deleteAll(ids);
		}
		catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	public PageQuery queryStandardCode(PageQuery pageQuery) throws ServiceException {
		try {
			PageQuery  query=standardCodeDao.queryBySelectId(pageQuery);
			pageQuery.setRecordSet(query.getRecordSet());
			pageQuery.setRecordCount(query.getRecordCount());
			return pageQuery;
		}
		catch (Exception e) {
			throw new ServiceException(e);
		}
	}
	public List findCodeByCodeSetId(Integer codeSetId) throws ServiceException{
		try{
			return standardCodeDao.findByField(StandardCode.PROP_CODESET_ID, codeSetId);
		}catch (Exception e) {
			throw new ServiceException(e);
		}
	}
	public List findAll() throws ServiceException{
		try{
			return standardCodeDao.findAll();
		}catch (Exception e) {
			throw new ServiceException(e);
		}
	}
	private StandardCodeDao	standardCodeDao;

	/**
	 * ×¢ÈëDAO
	 * 
	 * @see com.liusy.core.service.StandardCodeService#setStandardCodeDao(StandardCode
	 *      standardCodeDao)
	 */
	public void setStandardCodeDao(StandardCodeDao standardCodeDao) {
		this.standardCodeDao = standardCodeDao;
	}
}

