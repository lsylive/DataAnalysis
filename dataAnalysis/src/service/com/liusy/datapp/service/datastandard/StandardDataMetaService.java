package com.liusy.datapp.service.datastandard;

import java.io.Serializable;

import com.liusy.dataapplatform.base.exception.ServiceException;
import com.liusy.dataapplatform.base.util.PageQuery;

import com.liusy.datapp.model.datastandard.StandardDataMeta;

public interface StandardDataMetaService {

	public StandardDataMeta findStandardDataMeta(Serializable id) throws ServiceException;

	public void createStandardDataMeta(StandardDataMeta standardDataMeta) throws ServiceException;

	public void updateStandardDataMeta(StandardDataMeta standardDataMeta) throws ServiceException;

	public void removeStandardDataMeta(Serializable id) throws ServiceException;

	public PageQuery queryStandardDataMeta(PageQuery pageQuery) throws ServiceException;

	public void removeStandardDataMetas(Serializable[] ids) throws ServiceException;

	public PageQuery getCategorysForTree(PageQuery pagequery) throws ServiceException;
	public PageQuery getCodesetsForTree(PageQuery pagequery) throws ServiceException;
	public PageQuery getIndicatorsForTree(PageQuery pagequery) throws ServiceException;
}

