package com.liusy.datapp.service.blacklist;

import java.io.Serializable;
import java.util.Collection;

import com.liusy.dataapplatform.base.exception.ServiceException;
import com.liusy.dataapplatform.base.util.PagerObject;
import com.liusy.dataapplatform.base.util.PageQuery;

import com.liusy.datapp.dao.blacklist.BlacklistDeclarationDao;
import com.liusy.datapp.model.blacklist.BlacklistDeclaration;

public interface BlacklistDeclarationService {

	public BlacklistDeclaration findBlacklistDeclaration(Serializable id) throws ServiceException;

	public void createBlacklistDeclaration(BlacklistDeclaration blacklistDeclaration) throws ServiceException;

	public void updateBlacklistDeclaration(BlacklistDeclaration blacklistDeclaration) throws ServiceException;

	public void removeBlacklistDeclaration(Serializable id) throws ServiceException;

	public PageQuery queryBlacklistDeclaration(PageQuery pageQuery) throws ServiceException;

	public void removeBlacklistDeclarations(Serializable[] ids) throws ServiceException;  
}

