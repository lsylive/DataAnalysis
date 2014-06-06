package com.liusy.datapp.service.resource.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import com.liusy.dataapplatform.base.exception.DAOException;
import com.liusy.dataapplatform.base.exception.ServiceException;
import com.liusy.dataapplatform.base.util.Condition;
import com.liusy.dataapplatform.base.util.ConvertUtil;
import com.liusy.dataapplatform.base.util.ICondition;
import com.liusy.dataapplatform.base.util.PageQuery;
import com.liusy.datapp.dao.datastandard.StandardCategoryDao;
import com.liusy.datapp.dao.datastandard.StandardCodesetDao;
import com.liusy.datapp.dao.datastandard.StandardDataMetaDao;
import com.liusy.datapp.dao.datastandard.StandardIndicatorDao;
import com.liusy.datapp.dao.query.QueryColumnCfgDao;
import com.liusy.datapp.dao.query.QueryParamCfgDao;
import com.liusy.datapp.dao.resource.ResourceColumnDao;
import com.liusy.datapp.dao.resource.ResourceColumnUserCfgDao;
import com.liusy.datapp.dao.resource.ResourceTableDao;
import com.liusy.datapp.model.datastandard.StandardCategory;
import com.liusy.datapp.model.datastandard.StandardCodeset;
import com.liusy.datapp.model.datastandard.StandardDataMeta;
import com.liusy.datapp.model.datastandard.StandardIndicator;
import com.liusy.datapp.model.query.QueryColumnCfg;
import com.liusy.datapp.model.query.QueryParamCfg;
import com.liusy.datapp.model.resource.ResourceColumn;
import com.liusy.datapp.model.resource.ResourceColumnUserCfg;
import com.liusy.datapp.model.resource.ResourceTable;
import com.liusy.datapp.service.resource.ResourceColumnService;

public class ResourceColumnServiceImpl implements ResourceColumnService
{
	private static final long serialVersionUID = 1L;

	
	
	public ResourceColumn findResourceColumn(Serializable id) throws ServiceException
	{
		try
		{
			return resourceColumnDao.get(id);
		}
		catch (DAOException e)
		{
			throw new ServiceException(e);
		}
	}

	public ResourceColumn findResoucrePKColumn(Integer tableId) throws ServiceException
	{
		try
		{
			return resourceColumnDao.findPKColumnByTable(tableId);
		}
		catch (Exception e)
		{
			throw new ServiceException(e);
		}
	}

	public void createResourceColumn(ResourceColumn resourceColumn) throws ServiceException
	{
		try
		{
			List<ICondition> conditions = new ArrayList<ICondition>();
			conditions.add(new Condition(ResourceColumn.PROP_CN_NAME, Condition.EQUALS, resourceColumn.getCnName()));
			conditions.add(new Condition(ResourceColumn.PROP_TABLE_ID, Condition.EQUALS, resourceColumn.getTableId()));
			List<ResourceColumn> list = resourceColumnDao.commonQuery(conditions);
			if (list != null && list.size() > 0)
			{
				throw new ServiceException("中文名:[" + resourceColumn.getCnName() + "]已经存在，不能使用！");
			}

			conditions.clear();
			conditions.add(new Condition(ResourceColumn.PROP_NAME, Condition.EQUALS, resourceColumn.getName()));
			conditions.add(new Condition(ResourceColumn.PROP_TABLE_ID, Condition.EQUALS, resourceColumn.getTableId()));
			list = resourceColumnDao.commonQuery(conditions);

			if (list != null && list.size() > 0)
			{
				throw new ServiceException("英文名:[" + resourceColumn.getName() + "]已经存在，不能使用！");
			}

			resourceColumnDao.save(resourceColumn);
		}
		catch (DAOException e)
		{
			throw new ServiceException(e);
		}
	}

	public void updateResourceColumn(ResourceColumn resourceColumn) throws ServiceException
	{
		try
		{
			List<ICondition> conditions = new ArrayList<ICondition>();
			conditions.add(new Condition(ResourceColumn.PROP_ID, Condition.NOT_EQUALS, resourceColumn.getId()));
			conditions.add(new Condition(ResourceColumn.PROP_CN_NAME, Condition.EQUALS, resourceColumn.getCnName()));
			conditions.add(new Condition(ResourceColumn.PROP_TABLE_ID, Condition.EQUALS, resourceColumn.getTableId()));
			List<ResourceColumn> list = resourceColumnDao.commonQuery(conditions);
			if (list != null && list.size() > 0)
			{
				throw new ServiceException("中文名:[" + resourceColumn.getCnName() + "]已经存在，不能使用！");
			}

			conditions.clear();
			conditions.add(new Condition(ResourceColumn.PROP_ID, Condition.NOT_EQUALS, resourceColumn.getId()));
			conditions.add(new Condition(ResourceColumn.PROP_NAME, Condition.EQUALS, resourceColumn.getName()));
			conditions.add(new Condition(ResourceColumn.PROP_TABLE_ID, Condition.EQUALS, resourceColumn.getTableId()));
			list = resourceColumnDao.commonQuery(conditions);

			if (list != null && list.size() > 0)
			{
				throw new ServiceException("英文名:[" + resourceColumn.getName() + "]已经存在，不能使用！");
			}
			ResourceColumn tmp = resourceColumnDao.get(resourceColumn.getId());
			ConvertUtil.convertToModelForUpdateNew(tmp, resourceColumn);
			resourceColumnDao.update(tmp);
		}
		catch (DAOException e)
		{
			throw new ServiceException(e);
		}
		catch (Exception e)
		{
			throw new ServiceException(e);
		}
	}

	public void removeResourceColumn(Serializable id) throws ServiceException
	{
		try
		{
			resourceColumnDao.delete(id);
			// 移除对应的综合查询配置
			queryColumnCfgDao.deleteByField(QueryColumnCfg.PROP_COLUMN_ID, id);
			queryParamCfgDao.deleteByField(QueryParamCfg.PROP_COLUMN_ID, id);
		}
		catch (DAOException e)
		{
			throw new ServiceException(e);
		}
	}

	public void removeResourceColumns(Serializable[] ids) throws ServiceException
	{
		try
		{
			for(Serializable id : ids)
			{
				resourceColumnUserCfgDao.deleteByField(ResourceColumnUserCfg.PROP_COLUMN_ID, id); //删除资源表字段授权用户表的引用
				queryColumnCfgDao.deleteByField(QueryColumnCfg.PROP_COLUMN_ID, id);        //删除资源表字段授权表的引用
				queryParamCfgDao.deleteByField(QueryParamCfg.PROP_COLUMN_ID, id);         //删除资源表字段关联查询条件表的引用
			}
			
			resourceColumnDao.deleteAll(ids);        //删除资源表字段主表记录
			
		}
		catch (Exception e)
		{
			throw new ServiceException(e);
		}
	}

	public PageQuery queryResourceColumn(PageQuery pageQuery) throws ServiceException
	{
		try
		{
			PageQuery query = resourceColumnDao.queryBySelectId(pageQuery);
			pageQuery.setRecordSet(query.getRecordSet());
			pageQuery.setRecordCount(query.getRecordCount());
			return pageQuery;
		}
		catch (Exception e)
		{
			throw new ServiceException(e);
		}
	}

	public Integer getIndicatorIdByDataMete(Integer dataMeteId) throws ServiceException
	{

		try
		{
			StandardDataMeta dataMeta = standardDataMetaDao.get(dataMeteId);
			if (dataMeta == null)
			{
				return null;
			}
			return dataMeta.getIndicatorId();
		}
		catch (Exception e)
		{
			throw new ServiceException();
		}
	}

	private ResourceColumnDao resourceColumnDao;
	private ResourceColumnUserCfgDao resourceColumnUserCfgDao;	
	private StandardCodesetDao standardCodesetDao;
	private ResourceTableDao resourceTableDao;
	private StandardDataMetaDao standardDataMetaDao;
	private QueryColumnCfgDao queryColumnCfgDao;
	private QueryParamCfgDao queryParamCfgDao;
	private StandardIndicatorDao standardIndicatorDao;
	private StandardCategoryDao standardCategoryDao;

	public void moveUp(Serializable id) throws ServiceException
	{
		if (id!=null)
		{
			Integer currentId = (Integer)id;
			try
			{
				ResourceColumn rc1 = resourceColumnDao.get(currentId);
				if (rc1!=null)
				{
					Integer squenceNo1 = rc1.getSquenceNo();
					if (squenceNo1!=null&&squenceNo1>1)
					{
						ResourceColumn rc2 = (ResourceColumn)resourceColumnDao.findByField(ResourceColumn.PROP_SQUENCE_NO, squenceNo1-1).get(0);
						if (rc2!=null)
						{
							rc2.setSquenceNo(squenceNo1);
							rc1.setSquenceNo(squenceNo1-1);
							resourceColumnDao.update(rc1);
							resourceColumnDao.update(rc2);
						}
					}
				}
			}
			catch (Exception e)
			{
				throw new ServiceException(e);
			}
		}
		
	}
	
	public void moveDown(Serializable id) throws ServiceException
	{
		if (id!=null)
		{
			Integer currentId = (Integer)id;
			try
			{
				ResourceColumn rc1 = resourceColumnDao.get(currentId);
				if (rc1!=null)
				{
					Integer squenceNo1 = rc1.getSquenceNo();
					if (squenceNo1!=null)
					{
						ResourceColumn rc2 = (ResourceColumn)resourceColumnDao.findByField(ResourceColumn.PROP_SQUENCE_NO, squenceNo1+1).get(0);
						if (rc2!=null)
						{
							rc2.setSquenceNo(squenceNo1);
							rc1.setSquenceNo(squenceNo1+1);
							resourceColumnDao.update(rc1);
							resourceColumnDao.update(rc2);
						}
					}
				}
			}
			catch (Exception e)
			{
				throw new ServiceException(e);
			}
		}
		
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.liusy.datapp.service.resource.ResourceColumnService#getCodesetCode
	 * (java.util.Collection)
	 */
	public List<StandardCodeset> getCodesetCode(Collection<ICondition> conditions)
		throws ServiceException
	{
		try
		{
			return standardCodesetDao.commonQuery(conditions);
		}
		catch (Exception e)
		{
			throw new ServiceException(e);
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.liusy.datapp.service.resource.ResourceColumnService#getDataMetaCode
	 * (java.util.Collection)
	 */
	public List<StandardDataMeta> getDataMetaCode(Collection<ICondition> conditions)
		throws ServiceException
	{
		try
		{
			return standardDataMetaDao.commonQuery(conditions);
		}
		catch (Exception e)
		{
			throw new ServiceException();
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.liusy.datapp.service.resource.ResourceColumnService#haveRight
	 * (java.lang.String, java.lang.Integer)
	 */
	public boolean haveRight(String cityCode, Integer tableId) throws ServiceException
	{

		try
		{
			ResourceTable table = resourceTableDao.get(tableId);

			if (table == null)
			{
				table = new ResourceTable();
				table.setCityCode("");
			}
			if (table.getCityCode().equals(cityCode))
			{
				return true;
			}
			return false;
		}
		catch (Exception e)
		{
			throw new ServiceException();
		}

	}

	public List<ResourceColumn> findColumnByTableIdSort(String tableId) throws ServiceException
	{
		try
		{
			return resourceColumnDao.findColumnByTableIdSort(tableId);
		}
		catch (DAOException e)
		{
			throw new ServiceException(e);
		}
	}

	public List<StandardIndicator> getIndicators(Collection<ICondition> conditions)
		throws ServiceException
	{
		try
		{
			if (conditions == null)
			{
				return standardIndicatorDao.findAll();
			}
			return standardIndicatorDao.findByFilter(conditions);
		}
		catch (Exception e)
		{
			throw new ServiceException(e);
		}
	}

	public StandardDataMeta getDataMetaById(Integer dataMetaId) throws ServiceException
	{
		try
		{
			return standardDataMetaDao.get(dataMetaId);
		}
		catch (Exception e)
		{
			throw new ServiceException(e);
		}
	}

	/**
	 * 注入DAO
	 * 
	 * @see com.liusy.core.service.ResourceColumnService#setResourceColumnDao(ResourceColumn
	 *      resourceColumnDao)
	 */
	public void setResourceColumnDao(ResourceColumnDao resourceColumnDao)
	{
		this.resourceColumnDao = resourceColumnDao;
	}

	public void setStandardCodesetDao(StandardCodesetDao standardCodesetDao)
	{
		this.standardCodesetDao = standardCodesetDao;
	}

	public void setStandardDataMetaDao(StandardDataMetaDao standardDataMetaDao)
	{
		this.standardDataMetaDao = standardDataMetaDao;
	}

	public void setResourceTableDao(ResourceTableDao resourceTableDao)
	{
		this.resourceTableDao = resourceTableDao;
	}

	public void setQueryColumnCfgDao(QueryColumnCfgDao queryColumnCfgDao)
	{
		this.queryColumnCfgDao = queryColumnCfgDao;
	}

	public void setQueryParamCfgDao(QueryParamCfgDao queryParamCfgDao)
	{
		this.queryParamCfgDao = queryParamCfgDao;
	}

	public void setStandardIndicatorDao(StandardIndicatorDao standardIndicatorDao)
	{
		this.standardIndicatorDao = standardIndicatorDao;
	}

	public void setStandardCategoryDao(StandardCategoryDao standardCategoryDao)
	{
		this.standardCategoryDao = standardCategoryDao;
	}
	
	public void setResourceColumnUserCfgDao(ResourceColumnUserCfgDao resourceColumnUserCfgDao)
	{
		this.resourceColumnUserCfgDao = resourceColumnUserCfgDao;
	}

	public String getAllDataMateForTree() throws ServiceException
	{
		Element root = DocumentHelper.createElement("tree").addAttribute("id", "0");
		// 查找所有分类节点和其子节点
		List<ICondition> conditions = new ArrayList<ICondition>();
		conditions.add(new Condition(StandardCategory.PROP_PARENT_ID, Condition.EQUALS, Integer.valueOf(0)));
		List<StandardCategory> topList = standardCategoryDao.commonQuery(conditions);
		if (topList == null)
		{
			topList = new ArrayList<StandardCategory>();
		}
		for (StandardCategory sc : topList)
		{
			Element topNode = root.addElement("item").addAttribute("text", sc.getName()).addAttribute("id", "category_"+sc.getId().toString());
			Integer id = sc.getId();
			conditions.clear();
			conditions.add(new Condition(StandardCategory.PROP_PARENT_ID, Condition.EQUALS, Integer.valueOf(id)));
			List<StandardCategory> secList = standardCategoryDao.commonQuery(conditions);
			if (secList == null)
			{
				secList = new ArrayList<StandardCategory>();
			}
			for (StandardCategory sl : secList)
			{
				Element secNode = topNode.addElement("item").addAttribute("text", sl.getName()).addAttribute("id", "category_"+sl.getId().toString());
				Integer secid = sl.getId();

				conditions.clear();
				conditions.add(new Condition(StandardDataMeta.PROP_CATEGORY_ID, Condition.EQUALS, Integer.valueOf(secid)));
				List<StandardDataMeta> dataMetaList2 = standardDataMetaDao.commonQuery(conditions);
				if (dataMetaList2 == null)
				{
					dataMetaList2 = new ArrayList<StandardDataMeta>();
				}
				for (StandardDataMeta dml : dataMetaList2)
				{
					secNode.addElement("item").addAttribute("text", dml.getCnName()).addAttribute("id", dml.getId().toString());
				}
			}
			conditions.clear();
			conditions.add(new Condition(StandardDataMeta.PROP_CATEGORY_ID, Condition.EQUALS, Integer.valueOf(id)));
			List<StandardDataMeta> dataMetaList = standardDataMetaDao.commonQuery(conditions);
			if (dataMetaList == null)
			{
				dataMetaList = new ArrayList<StandardDataMeta>();
			}
			for (StandardDataMeta sdm : dataMetaList)
			{
				topNode.addElement("item").addAttribute("text", sdm.getCnName()).addAttribute("id", sdm.getId().toString());
			}			
		}

		return root.asXML();
	}
	
	public int getMaxOrderFromColumn(Serializable tableId) throws ServiceException
	{
		Integer max_order;	
		List list = resourceColumnDao.findByHql("select max(column.squenceNo) from ResourceColumn as column where column.tableId="+tableId);
		if (list!=null&&!list.isEmpty())
		{			
			max_order = ((Integer)list.get(0));
			if (max_order==null)
			{
				max_order = 0;
			}
		}
		else {
			max_order = 0;
		}
		return max_order;
	}
	
	public Integer getMaxOrderFromCFG(Serializable tableId) throws ServiceException
	{
		Integer max_order;	
		List list = queryColumnCfgDao.findByHql("select max(cfg.seqNo) from QueryColumnCfg as cfg where cfg.tableId="+tableId);
		if (list!=null&&!list.isEmpty())
		{			
			max_order = ((Integer)list.get(0));
			if (max_order==null)
			{
				max_order = 0;
			}
		}
		else {
			max_order = 0;
		}
		return max_order;
	}
}
