package com.liusy.datapp.service.compare;

import java.io.Serializable;
import java.util.Collection;

import com.liusy.dataapplatform.base.exception.ServiceException;
import com.liusy.dataapplatform.base.util.PagerObject;
import com.liusy.dataapplatform.base.util.PageQuery;

import com.liusy.datapp.dao.compare.CompareFilterDao;
import com.liusy.datapp.model.compare.CompareFilter;

public interface CompareFilterService {

	public CompareFilter findCompareFilter(Serializable id) throws ServiceException;

	public void createCompareFilter(CompareFilter compareFilter) throws ServiceException;

	public void updateCompareFilter(CompareFilter compareFilter) throws ServiceException;

	public void removeCompareFilter(Serializable id) throws ServiceException;
	
	public void removeCompareFiltersByCompId(Serializable id) throws ServiceException;

	public PageQuery queryCompareFilter(PageQuery pageQuery) throws ServiceException;

	public void removeCompareFilters(Serializable[] ids) throws ServiceException;  
	
	public Collection<CompareFilter>  findByField(String fieldName,Object fieldValue) throws ServiceException;
}

