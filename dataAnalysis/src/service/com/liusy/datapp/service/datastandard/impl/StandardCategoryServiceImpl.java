package com.liusy.datapp.service.datastandard.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.liusy.dataapplatform.base.exception.DAOException;
import com.liusy.dataapplatform.base.exception.ServiceException;
import com.liusy.dataapplatform.base.util.Condition;
import com.liusy.dataapplatform.base.util.ICondition;
import com.liusy.dataapplatform.base.util.PageQuery;
import com.liusy.datapp.dao.datastandard.StandardCategoryDao;
import com.liusy.datapp.model.datastandard.StandardCategory;
import com.liusy.datapp.service.datastandard.StandardCategoryService;

public class StandardCategoryServiceImpl implements StandardCategoryService {
	private static final long serialVersionUID = 1L;

	public StandardCategory findStandardCategory(Serializable id) throws ServiceException {
		try {
			return standardCategoryDao.get(id);
		}
		catch (DAOException e) {
			throw new ServiceException(e);
		}
	}

	public void createStandardCategory(StandardCategory standardCategory) throws ServiceException {
		try {
			List<ICondition> conditions = new ArrayList<ICondition>();
			conditions.add(new Condition(StandardCategory.PROP_NAME, Condition.EQUALS, standardCategory.getName()));
			List<StandardCategory> list = standardCategoryDao.commonQuery(conditions);
			if (list!=null&&list.size()>0)
			{
				throw new ServiceException("名称:["+standardCategory.getName()+"]已经存在，不能使用！");
			}
			
			conditions.clear();
			conditions.add(new Condition(StandardCategory.PROP_CODE, Condition.EQUALS, standardCategory.getCode()));
			list = standardCategoryDao.commonQuery(conditions);
			if (list!=null&&list.size()>0)
			{
				throw new ServiceException("代码:["+standardCategory.getName()+"]已经存在，不能使用！");
			}
			
			standardCategoryDao.save(standardCategory);
		}
		catch (DAOException e) {
			throw new ServiceException(e);
		}
	}

	public void updateStandardCategory(StandardCategory standardCategory) throws ServiceException {
		try {
//			StandardCategory tmp = standardCategoryDao.get(standardCategory.getId());
//			ConvertUtil.convertToModelForUpdate(tmp, standardCategory);
			List<ICondition> conditions = new ArrayList<ICondition>();
			conditions.add(new Condition(StandardCategory.PROP_ID, Condition.NOT_EQUALS, standardCategory.getId()));
			conditions.add(new Condition(StandardCategory.PROP_NAME, Condition.EQUALS, standardCategory.getName()));
			List<StandardCategory> list = standardCategoryDao.commonQuery(conditions);
			if (list!=null&&list.size()>0)
			{
				throw new ServiceException("名称:["+standardCategory.getName()+"]已经存在，不能使用！");
			}
			
			conditions.clear();
			conditions.add(new Condition(StandardCategory.PROP_ID, Condition.NOT_EQUALS, standardCategory.getId()));
			conditions.add(new Condition(StandardCategory.PROP_CODE, Condition.EQUALS, standardCategory.getCode()));
			list = standardCategoryDao.commonQuery(conditions);
			if (list!=null&&list.size()>0)
			{
				throw new ServiceException("代码:["+standardCategory.getName()+"]已经存在，不能使用！");
			}
			standardCategoryDao.update(standardCategory);
		}
		catch (DAOException e) {
			throw new ServiceException(e);
		}
		catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	public void removeStandardCategory(Serializable id) throws ServiceException {
		try {
			standardCategoryDao.delete(id);
		}
		catch (DAOException e) {
			throw new ServiceException(e);
		}
	}

	public void removeStandardCategorys(Serializable[] ids) throws ServiceException {
		try {
			standardCategoryDao.deleteAll(ids);
		}
		catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	public PageQuery queryStandardCategory(PageQuery pageQuery) throws ServiceException {
		try {
			PageQuery  query=standardCategoryDao.queryBySelectId(pageQuery);
			pageQuery.setRecordSet(query.getRecordSet());
			pageQuery.setRecordCount(query.getRecordCount());
			return pageQuery;
		}
		catch (Exception e) {
			throw new ServiceException(e);
		}
	}
	public List<StandardCategory> findAll() throws ServiceException{
		try{
			return standardCategoryDao.findAll();
		}catch (Exception e) {
			throw new ServiceException(e);
		}
	}
	
	/**
	 * 通过Parentid查找分类
	 * @param parentId
	 * @return
	 * @throws ServiceException
	 */
	public List<StandardCategory> findCategoryByParentId(String parentId) throws ServiceException{
		try{
			if(parentId!=null) 
				return standardCategoryDao.findByField(StandardCategory.PROP_PARENT_ID, Integer.valueOf(parentId));
			else
				return standardCategoryDao.findByField(StandardCategory.PROP_PARENT_ID, null);
		}catch (Exception e) {
			throw new ServiceException(e);
		}
	}
	public List<StandardCategory> findCategoryByParentIdCityCode(String parentId, String cityCode) throws ServiceException{
		try{
			if (parentId != null){
				return standardCategoryDao.findByField(StandardCategory.PROP_PARENT_ID, Integer.valueOf(parentId));
			}
			else {
				List<ICondition> conditions = new ArrayList<ICondition>();
				conditions.add(new Condition(StandardCategory.PROP_PARENT_ID, Condition.IS_NULL));
				if (cityCode != null)
					conditions.add(new Condition(StandardCategory.PROP_CITY_CODE, Condition.EQUALS, cityCode));
				else
					conditions.add(new Condition(StandardCategory.PROP_CITY_CODE, Condition.IS_NULL));
				return standardCategoryDao.commonQuery(conditions);
			}
		}catch (Exception e){
			throw new ServiceException(e);
		}
	}
	public String getCataClassfiyName(String cataId,String separate) throws ServiceException{
		try{
			StringBuffer buffer=new StringBuffer();
			List<String> cataList=new ArrayList<String>();
			StandardCategory endcata=standardCategoryDao.get(Integer.valueOf(cataId));
			cataList.add(endcata.getName());
			while(endcata.getParentId()!=null && !"".equals(endcata.getParentId())){
				endcata=standardCategoryDao.get(endcata.getParentId());
				cataList.add(endcata.getName());
			}
			for(int i=cataList.size();i>0;i--){
				String name=cataList.get(i);
				buffer.append(name+separate);
			}
			if(buffer.length()>0)
				return buffer.substring(0,buffer.length()-separate.length());
			else
				return "";		
		}catch (Exception e) {
			throw new ServiceException(e);
		}
	}
	public List<StandardCategory> findCatagoryByCode() throws ServiceException{
		try{
			return standardCategoryDao.findCatagoryByCode();
		}catch (Exception e) {
			throw new ServiceException(e);
		}
	}
	private StandardCategoryDao	standardCategoryDao;

	
	
	
	
	/**
	 * 注入DAO
	 * 
	 * @see com.liusy.core.service.StandardCategoryService#setStandardCategoryDao(StandardCategory
	 *      standardCategoryDao)
	 */
	public void setStandardCategoryDao(StandardCategoryDao standardCategoryDao) {
		this.standardCategoryDao = standardCategoryDao;
	}
	
}

