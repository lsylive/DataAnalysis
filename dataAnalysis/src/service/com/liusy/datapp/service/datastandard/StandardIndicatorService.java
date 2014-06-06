package com.liusy.datapp.service.datastandard;

import java.io.Serializable;
import java.util.Collection;

import com.liusy.dataapplatform.base.exception.ServiceException;
import com.liusy.dataapplatform.base.util.PageQuery;

import com.liusy.datapp.model.datastandard.StandardIndicator;

public interface StandardIndicatorService {

	public StandardIndicator findStandardIndicator(Serializable id) throws ServiceException;

	public void createStandardIndicator(StandardIndicator standardIndicator) throws ServiceException;

	public void updateStandardIndicator(StandardIndicator standardIndicator) throws ServiceException;

	public void removeStandardIndicator(Serializable id) throws ServiceException;

	public PageQuery queryStandardIndicator(PageQuery pageQuery) throws ServiceException;

	public void removeStandardIndicators(Serializable[] ids) throws ServiceException;

	public PageQuery getCategorysForTree(PageQuery pagequery) throws ServiceException;
	public Collection findAllStandardIndicators() throws ServiceException;
	public Collection findStandardIndicatorsByField(String fieldName,Object fieldValue) throws ServiceException;
}