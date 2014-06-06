package com.liusy.datapp.service.datastandard;

import java.io.Serializable;
import java.util.List;

import com.liusy.dataapplatform.base.exception.ServiceException;
import com.liusy.dataapplatform.base.util.PageQuery;
import com.liusy.datapp.model.datastandard.StandardCode;

public interface StandardCodeService {

	public StandardCode findStandardCode(Serializable id) throws ServiceException;

	public void createStandardCode(StandardCode standardCode) throws ServiceException;

	public void updateStandardCode(StandardCode standardCode) throws ServiceException;

	public void removeStandardCode(Serializable id) throws ServiceException;

	public PageQuery queryStandardCode(PageQuery pageQuery) throws ServiceException;

	public void removeStandardCodes(Serializable[] ids) throws ServiceException;
	public List findAll() throws ServiceException;
	public List findCodeByCodeSetId(Integer codeSetId) throws ServiceException;
}

