package com.liusy.datapp.service.compare;

import java.io.Serializable;
import java.util.Collection;

import com.liusy.dataapplatform.base.exception.ServiceException;
import com.liusy.dataapplatform.base.util.PagerObject;
import com.liusy.dataapplatform.base.util.PageQuery;

import com.liusy.datapp.dao.compare.CompareIndicatorDao;
import com.liusy.datapp.model.compare.CompareIndicator;

public interface CompareIndicatorService {

	public CompareIndicator findCompareIndicator(Serializable id) throws ServiceException;

	public void createCompareIndicator(CompareIndicator compareIndicator) throws ServiceException;

	public void updateCompareIndicator(CompareIndicator compareIndicator) throws ServiceException;

	public void removeCompareIndicator(Serializable id) throws ServiceException;

	public PageQuery queryCompareIndicator(PageQuery pageQuery) throws ServiceException;

	public void removeCompareIndicators(Serializable[] ids) throws ServiceException;
	public Collection findByFields(String fieldName,Object fieldValue) throws ServiceException;
}

