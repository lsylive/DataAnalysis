package com.liusy.datapp.service.resource.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.criterion.Order;

import com.liusy.core.util.CollectionMapConvert;
import com.liusy.dataapplatform.base.exception.DAOException;
import com.liusy.dataapplatform.base.exception.ServiceException;
import com.liusy.dataapplatform.base.util.Condition;
import com.liusy.dataapplatform.base.util.ConvertUtil;
import com.liusy.dataapplatform.base.util.ICondition;
import com.liusy.dataapplatform.base.util.PageQuery;

import com.liusy.datapp.dao.resource.ResourceColumnUserCfgDao;
import com.liusy.datapp.model.resource.ResourceColumn;
import com.liusy.datapp.model.resource.ResourceColumnUserCfg;
import com.liusy.datapp.service.resource.ResourceColumnService;
import com.liusy.datapp.service.resource.ResourceColumnUserCfgService;
import com.liusy.datapp.service.util.SynthesisUserColumnCfgParam;
import com.liusy.datapp.util.poolobj.ColumnPoolObj;

public class ResourceColumnUserCfgServiceImpl implements ResourceColumnUserCfgService {
	private static final long serialVersionUID = 1L;

	public ResourceColumnUserCfg findResourceColumnUserCfg(Serializable id) throws ServiceException {
		try {
			return resourceColumnUserCfgDao.get(id);
		}
		catch (DAOException e) {
			throw new ServiceException(e);
		}
	}

	public void createResourceColumnUserCfg(ResourceColumnUserCfg resourceColumnUserCfg) throws ServiceException {
		try {
			resourceColumnUserCfgDao.save(resourceColumnUserCfg);
		}
		catch (DAOException e) {
			throw new ServiceException(e);
		}
	}

	public void updateResourceColumnUserCfg(ResourceColumnUserCfg resourceColumnUserCfg) throws ServiceException {
		try {
			ResourceColumnUserCfg tmp = resourceColumnUserCfgDao.get(resourceColumnUserCfg.getId());
			ConvertUtil.convertToModelForUpdate(tmp, resourceColumnUserCfg);			
			resourceColumnUserCfgDao.update(tmp);
		}
		catch (DAOException e) {
			throw new ServiceException(e);
		}
		catch (Exception e) {
			// TODO: handle exception
			throw new ServiceException(e);
		}
	}

	public void removeResourceColumnUserCfg(Serializable id) throws ServiceException {
		try {
			resourceColumnUserCfgDao.delete(id);
		}
		catch (DAOException e) {
			throw new ServiceException(e);
		}
	}

	public void removeResourceColumnUserCfgs(Serializable[] ids) throws ServiceException {
		try {
			resourceColumnUserCfgDao.deleteAll(ids);
		}
		catch (Exception e) {
			throw new ServiceException(e);
		}
	}
	public Collection findColumnCfgByField(String fieldName,Object fieldValue) throws ServiceException{
		try{
			return resourceColumnUserCfgDao.findByField(fieldName, fieldValue);
		}catch (Exception e) {
			throw new ServiceException(e);
		}
	}
	public List<SynthesisUserColumnCfgParam> getTableColumnSercurityCfg(String tableId,List<ColumnPoolObj> orglist) throws ServiceException{
		try{
			List<ResourceColumnUserCfg> list= resourceColumnUserCfgDao.findByField(ResourceColumnUserCfg.PROP_TABLE_ID,Integer.valueOf(tableId));
//			List<ResourceColumn> orglist=resourceColumnService.findColumnByTableIdSort(tableId);
			CollectionMapConvert<ResourceColumnUserCfg> convert=new CollectionMapConvert<ResourceColumnUserCfg>();
			Map<String,ResourceColumnUserCfg> map=new HashMap<String, ResourceColumnUserCfg>();
			if(list!=null && !list.isEmpty())
				map = convert.convertListToMap(list, ResourceColumnUserCfg.PROP_COLUMN_ID);
			List<SynthesisUserColumnCfgParam> retList=new ArrayList<SynthesisUserColumnCfgParam>();
			for(ColumnPoolObj column:orglist){
				SynthesisUserColumnCfgParam param=new SynthesisUserColumnCfgParam();
				if(map.get(column.getId().toString())!=null){
					param.setSercurityLevel(map.get(column.getId().toString()).getSecurityLevel());
				}else
					param.setSercurityLevel(column.getSecurityLevel());
				param.setColumnId(column.getId().toString());
				param.setName(column.getName());
				param.setCname(column.getCnName());
				param.setId(column.getId());
				retList.add(param);
			}
			return retList;
		}catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	public PageQuery queryResourceColumnUserCfg(PageQuery pageQuery) throws ServiceException {
		try {
			PageQuery  query=resourceColumnUserCfgDao.queryBySelectId(pageQuery);
			pageQuery.setRecordSet(query.getRecordSet());
			pageQuery.setRecordCount(query.getRecordCount());
			return pageQuery;
		}
		catch (Exception e) {
			throw new ServiceException(e);
		}
	}
	

	private ResourceColumnUserCfgDao	resourceColumnUserCfgDao;

	/**
	 * ×¢ÈëDAO
	 * 
	 * @see com.liusy.core.service.ResourceColumnUserCfgService#setResourceColumnUserCfgDao(ResourceColumnUserCfg
	 *      resourceColumnUserCfgDao)
	 */
	public void setResourceColumnUserCfgDao(ResourceColumnUserCfgDao resourceColumnUserCfgDao) {
		this.resourceColumnUserCfgDao = resourceColumnUserCfgDao;
	}
	
	public List<ResourceColumnUserCfg> findByTableAndUserId(Serializable tableId,Serializable userId) throws ServiceException
	{
		try
		{
			List<ICondition> conditions = new ArrayList<ICondition>();
			conditions.add(new Condition(ResourceColumnUserCfg.PROP_TABLE_ID, Condition.EQUALS,tableId));
			conditions.add(new Condition(ResourceColumnUserCfg.PROP_USER_ID, Condition.EQUALS,userId));
			return resourceColumnUserCfgDao.commonQuery(conditions);
		}
		catch (Exception e)
		{
			throw new ServiceException();
		}
	}
}

