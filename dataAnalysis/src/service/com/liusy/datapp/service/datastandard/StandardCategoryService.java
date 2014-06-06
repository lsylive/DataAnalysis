package com.liusy.datapp.service.datastandard;

import java.io.Serializable;
import java.util.List;

import com.liusy.dataapplatform.base.exception.ServiceException;
import com.liusy.dataapplatform.base.util.PageQuery;

import com.liusy.datapp.model.datastandard.StandardCategory;

public interface StandardCategoryService {

	public StandardCategory findStandardCategory(Serializable id) throws ServiceException;

	public void createStandardCategory(StandardCategory standardCategory) throws ServiceException;

	public void updateStandardCategory(StandardCategory standardCategory) throws ServiceException;

	public void removeStandardCategory(Serializable id) throws ServiceException;

	public PageQuery queryStandardCategory(PageQuery pageQuery) throws ServiceException;

	public void removeStandardCategorys(Serializable[] ids) throws ServiceException;  
	/**
	 * 通过Parentid查找分类
	 * @param parentId
	 * @return
	 * @throws ServiceException
	 */
	public List<StandardCategory> findCategoryByParentId(String parentId) throws ServiceException;
	public List<StandardCategory> findCategoryByParentIdCityCode(String parentId, String cityCode) throws ServiceException;
	public String getCataClassfiyName(String cataId,String separate) throws ServiceException;
	public List<StandardCategory> findCatagoryByCode() throws ServiceException;
	public List<StandardCategory> findAll() throws ServiceException;
}

