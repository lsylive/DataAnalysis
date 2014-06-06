package com.liusy.datapp.service.resource.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import com.liusy.core.util.Const;
import com.liusy.dataapplatform.base.exception.DAOException;
import com.liusy.dataapplatform.base.exception.ServiceException;
import com.liusy.dataapplatform.base.util.Condition;
import com.liusy.dataapplatform.base.util.ConvertUtil;
import com.liusy.dataapplatform.base.util.ICondition;
import com.liusy.dataapplatform.base.util.PageQuery;
import com.liusy.datapp.dao.datastandard.StandardCategoryDao;
import com.liusy.datapp.dao.resource.ResourceColumnDao;
import com.liusy.datapp.dao.resource.ResourceTableDao;
import com.liusy.datapp.dao.system.config.SysCityDao;
import com.liusy.datapp.model.datastandard.StandardCategory;
import com.liusy.datapp.model.resource.ResourceColumn;
import com.liusy.datapp.model.resource.ResourceTable;
import com.liusy.datapp.model.system.config.SysCity;
import com.liusy.datapp.service.resource.ResourceTableService;

public class ResourceTableServiceImpl implements ResourceTableService
{
	private static final long serialVersionUID = 1L;
	private StandardCategoryDao standardCategoryDao;
	private SysCityDao sysCityDao;
	private ResourceColumnDao resourceColumnDao;

	public ResourceTable findResourceTable(Serializable id) throws ServiceException
	{
		try
		{
			return resourceTableDao.get(id);
		}
		catch (DAOException e)
		{
			throw new ServiceException(e);
		}
	}

	public void createResourceTable(ResourceTable resourceTable) throws ServiceException
	{
		try
		{
			List<ResourceTable> list = resourceTableDao.findByField(ResourceTable.PROP_CN_NAME, resourceTable.getCnName());
			if (list!=null&&list.size()>0)
			{
				throw new ServiceException("中文表名:["+resourceTable.getCnName()+"]已经存在，不能使用！");
			}
			list = resourceTableDao.findByField(ResourceTable.PROP_NAME, resourceTable.getName());
			if (list!=null&&list.size()>0)
			{
				throw new ServiceException("英文表名:["+resourceTable.getName()+"]已经存在，不能使用！");
			}
			
			resourceTableDao.save(resourceTable);
		}
		catch (DAOException e)
		{
			e.printStackTrace();
			throw new ServiceException(e);
		}
	}

	public void updateResourceTable(ResourceTable resourceTable) throws ServiceException
	{
		try
		{
			List<ICondition> conditions = new ArrayList<ICondition>();
			conditions.add(new Condition(ResourceTable.PROP_CN_NAME, Condition.EQUALS,resourceTable.getCnName()));
			conditions.add(new Condition(ResourceTable.PROP_ID, Condition.NOT_EQUALS,resourceTable.getId()));
			List<ResourceTable> list = resourceTableDao.commonQuery(conditions);
			if (list!=null&&list.size()>0)
			{
				throw new ServiceException("中文表名:["+resourceTable.getCnName()+"]已经存在，不能使用！");
			}
			conditions.clear();
			conditions.add(new Condition(ResourceTable.PROP_NAME, Condition.EQUALS,resourceTable.getName()));
			conditions.add(new Condition(ResourceTable.PROP_ID, Condition.NOT_EQUALS,resourceTable.getId()));
			list = resourceTableDao.commonQuery(conditions);
			if (list!=null&&list.size()>0)
			{
				throw new ServiceException("英文表名:["+resourceTable.getName()+"]已经存在，不能使用！");
			}
			
			ResourceTable tmp = resourceTableDao.get(resourceTable.getId());
			ConvertUtil.convertToModelForUpdate(tmp, resourceTable);
			resourceTableDao.update(tmp);
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

	public void removeResourceTable(Serializable id) throws ServiceException
	{
		try
		{
			resourceTableDao.delete(id);
		}
		catch (DAOException e)
		{
			throw new ServiceException(e);
		}
	}

	public void removeResourceTables(Serializable[] ids) throws ServiceException
	{
		try
		{
			resourceTableDao.deleteAll(ids);
		}
		catch (Exception e)
		{
			throw new ServiceException(e);
		}
	}

	/**
	 * 
	 */

	public void removeResourceTablesAndColumn(Serializable[] ids) throws ServiceException
	{
		try
		{
			if(ids.length > 0){
				resourceTableDao.deleteAll(ids);
				for (int i = 0; i < ids.length; i++)
				{
					resourceColumnDao.deleteByField(ResourceColumn.PROP_TABLE_ID, ids[i]);
				}				
			}
		}
		catch (Exception e)
		{
			throw new ServiceException(e);
		}
	}

	public PageQuery queryResourceTable(PageQuery pageQuery) throws ServiceException
	{
		try
		{
			PageQuery query = resourceTableDao.queryBySelectId(pageQuery);
			pageQuery.setRecordSet(query.getRecordSet());
			pageQuery.setRecordCount(query.getRecordCount());
			return pageQuery;
		}
		catch (Exception e)
		{
			throw new ServiceException(e);
		}
	}

	/**
	 * 按照行业id查询资源表
	 * 
	 * @param cataId
	 * 
	 */
	public List<ResourceTable> findTableByCatageoryId(String cataId) throws ServiceException
	{

		try
		{
			Integer cataid=null;
			if(cataId!=null && !"".equals(cataId))
				cataid=Integer.valueOf(cataId);
			String[] fieldName={ResourceTable.PROP_CATEGORY_ID,ResourceTable.PROP_ISSPACETABLE};
			Object[] fieldValue={cataid,Const.IS_RESOURCETABLE};
			return resourceTableDao.findByFields(fieldName, fieldValue, ResourceTable.PROP_ORDE, false);
		}
		catch (Exception e)
		{
			throw new ServiceException(e);
		}
	}

	/**
	 * 查询所有资源表
	 * 
	 * @return
	 * @throws ServiceException
	 */
	public List<ResourceTable> findAllTable() throws ServiceException
	{
		try
		{
			return (List<ResourceTable>) resourceTableDao.findByField(ResourceTable.PROP_ISSPACETABLE, "0");
		}
		catch (Exception e)
		{
			throw new ServiceException(e);
		}
	}

	/*
	 * 为数型菜单抽取所有地市数据
	 * 
	 * @see
	 * com.liusy.datapp.service.system.config.SysCityService#loadDataForTree
	 * ()
	 * 
	 * @return
	 */
	public PageQuery loadDataForTree(PageQuery pagequery) throws ServiceException
	{

		return sysCityDao.queryBySelectId(pagequery);

	}

	public PageQuery getCategoryForTree(PageQuery pagequery) throws ServiceException
	{

		return standardCategoryDao.queryBySelectId(pagequery);
	}

	public void setStandardCategoryDao(StandardCategoryDao standardCategoryDao)
	{
		this.standardCategoryDao = standardCategoryDao;
	}

	public void setSysCityDao(SysCityDao sysCityDao)
	{
		this.sysCityDao = sysCityDao;
	}

	public void setResourceColumnDao(ResourceColumnDao resourceColumnDao)
	{
		this.resourceColumnDao = resourceColumnDao;
	}

	/*
	 * 将某个分类的子节点的信息及其所有间接子节点信息拉取出来
	 * 
	 * @seecom.liusy.datapp.service.datastandard.StandardCategoryService#
	 * getAllCategorysParentId(java.lang.String)
	 */
	public List<Map<String, Object>> getAllCategorysByParentId(String parentId) throws ServiceException
	{
		try
		{
			int level = 0;
			List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
			getAllChildsByParentId(parentId, list, level, "tree");
			return list;
		}
		catch (Exception e)
		{
			throw new ServiceException();
		}

	}

	/**
	 * 
	 */
	public List<Map<String, Object>> getAllCategorysForSelection(String citycode)
		throws ServiceException
	{
		try
		{
			PageQuery query1 = new PageQuery();
			query1.setSelectParamId("GET_STANDARDCATEGORY_PAGE");
			// query1.getParameters().put("queryString",
			// " and (a.PARENT_ID is null or a.PARENT_ID=0)"+" and a.city_code='"+citycode+"'");
			query1.getParameters().put("queryString", " and (a.PARENT_ID is null or a.PARENT_ID=0)");
			query1.setPageSize("0");

			List<Map<String, String>> categorys = standardCategoryDao.queryBySelectId(query1).getRecordSet();
			if (categorys == null)
			{
				categorys = new ArrayList<Map<String, String>>();
			}
			List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();

			Iterator<Map<String, String>> it = categorys.iterator();
			while (it.hasNext())
			{
				Map<String, Object> root = new HashMap<String, Object>();
				Map<String, String> temp = it.next();
				root.put(StandardCategory.PROP_ID, temp.get("ID"));
				root.put(StandardCategory.PROP_NAME, temp.get("NAME"));
				list.add(root);
				getAllChildsByParentId(temp.get("ID"), list, 0, "selection");
			}

			return list;
		}
		catch (Exception e)
		{
			throw new ServiceException();
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @seecom.liusy.datapp.service.resource.ResourceTableService#
	 * getAllTreeNodeAsXmlString()
	 */
	public String getAllTreeNodeAsXmlString(String cityCode) throws ServiceException
	{

		try
		{

			PageQuery query2 = new PageQuery();
			query2.setSelectParamId("GET_SYSCITY_PAGE");
			if (cityCode != null && !"".equals(cityCode))
			{
				query2.getParameters().put("queryString", " and a.code='" + cityCode + "'");
			}
			else {
				query2.getParameters().put("queryString", "");
			}
			query2.setPageSize("0");

			List<Map<String, String>> citys = loadDataForTree(query2).getRecordSet();
			if (citys == null)
			{
				citys = new ArrayList<Map<String, String>>();
			}

			// 拉取顶级分类信息
			StringBuffer queryString = new StringBuffer();
			if (cityCode != null && !"".equals(cityCode))
			{
				queryString.append(" and a.city_code='" + cityCode + "'");
			}
			queryString.append(" and a.PARENT_ID is null or a.PARENT_ID=0");

			PageQuery query1 = new PageQuery();
			query1.setSelectParamId("GET_STANDARDCATEGORY_PAGE");
			query1.getParameters().put("queryString", queryString.toString());
			query1.setPageSize("0");

			List<Map<String, String>> categorys = getCategoryForTree(query1).getRecordSet();
			if (categorys == null)
			{
				categorys = new ArrayList<Map<String, String>>();
			}

			// 创建树的根节点
			Element root = DocumentHelper.createElement("tree").addAttribute("id", "0");

			// 为树添加省根节点
			Element province = root.addElement("item").addAttribute("text", "广东省资源目录").addAttribute("id", "province").addAttribute("open", "1").addAttribute("child", "1")
			.addAttribute("im0", "book_titel.gif").addAttribute("im1", "books_open.gif").addAttribute("im2", "books_close.gif");
			province.addElement("userdata").addAttribute("name", "name").addText("广东省资源目录");

			// 将地市节点添加到省根节点上
			for (Map<String, String> city : citys)
			{
				Element cityNode = province.addElement("item").addAttribute("text", String.valueOf(city.get("NAME")))
				.addAttribute("id", city.get("CODE")).addAttribute("child", "1")
				.addAttribute("im0", "book_titel.gif").addAttribute("im1", "books_open.gif").addAttribute("im2", "book.gif");
				cityNode.addElement("userdata").addAttribute("name", "name").addText(city.get("NAME"));
				// 将顶级分类节点加到地市节点上
				for (Map<String, String> category : categorys)
				{

					Element categoryNode = cityNode.addElement("item").addAttribute("text", category.get("NAME")).addAttribute("id", city.get("CODE")
						+ "_" + category.get("ID")).addAttribute("child", "1")
						.addAttribute("im0", "book_titel.gif").addAttribute("im1", "books_open.gif").addAttribute("im2", "book.gif");

					categoryNode.addElement("userdata").addAttribute("name", "name").addText(category.get("NAME"));
					// 将顶级分类所有子节点加到顶级分类下
					getAllTreeNodeAsXmlByParentId(categoryNode, city.get("CODE") + "_"
						+ category.get("ID"));
				}
			}
			return root.asXML();
		}
		catch (Exception e)
		{
			throw new ServiceException();
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @seecom.liusy.datapp.service.resource.ResourceTableService#
	 * getAllTreeNodeAsXmlString(org.dom4j.Element)
	 */
	private void getAllTreeNodeAsXmlByParentId(Element treeNode, String parentId)
		throws ServiceException
	{

		try
		{
			String[] splitId = parentId.split("_");
			String tempParentId;
			if (splitId != null && splitId.length > 1)
			{
				tempParentId = splitId[splitId.length - 1];
			}
			else
			{
				tempParentId = parentId;
			}

			List<StandardCategory> temp = standardCategoryDao.findByField(StandardCategory.PROP_PARENT_ID, Integer.valueOf(tempParentId));
			if (temp == null || temp.isEmpty())
			{
				return;
			}
			else
			{
				Iterator<StandardCategory> it = temp.iterator();
				while (it.hasNext())
				{
					StandardCategory bean = it.next();
					if (bean == null)
					{
						bean = new StandardCategory();
					}
					Element tempNode = treeNode.addElement("item").addAttribute("text", bean.getName()).addAttribute("id", parentId
						+ "_" + bean.getId().toString()).addAttribute("child", "1")
						.addAttribute("im0", "book_titel.gif").addAttribute("im1", "books_open.gif").addAttribute("im2", "book.gif");

					tempNode.addElement("userdata").addAttribute("name", "name").addText(bean.getName());
					getAllTreeNodeAsXmlByParentId(tempNode, parentId + "_" + bean.getId().toString());
				}
			}
			return;
		}
		catch (Exception e)
		{
			throw new ServiceException();
		}
	}

	// 递归方法，按树形结构将某分类下的所有子分类及其间接子分类查找出来
	private void getAllChildsByParentId(String parentId, List<Map<String, Object>> list, int level,
		String type) throws ServiceException
	{

		try
		{
			level++;
			String[] splitId = parentId.split("_");
			String tempParentId;
			if (splitId != null && splitId.length > 1)
			{
				tempParentId = splitId[splitId.length - 1];
			}
			else
			{
				tempParentId = parentId;
			}

			StringBuffer sb = new StringBuffer("");

			if ("tree".equalsIgnoreCase(type))
			{

			}
			else if ("selection".equalsIgnoreCase(type))
			{
				for (int i = 0; i < level; i++)
				{
					sb.append("___");
				}
			}

			List<StandardCategory> temp = standardCategoryDao.findByField(StandardCategory.PROP_PARENT_ID, Integer.valueOf(tempParentId));
			if (temp == null || temp.isEmpty())
			{
				return;
			}
			else
			{
				Iterator<StandardCategory> it = temp.iterator();
				while (it.hasNext())
				{
					StandardCategory bean = it.next();
					if (bean == null)
					{
						bean = new StandardCategory();
					}
					Map<String, Object> map = new HashMap<String, Object>();
					map.put(StandardCategory.PROP_NAME, sb.toString() + bean.getName());
					if ("selection".equals(type))
					{
						map.put(StandardCategory.PROP_ID, parentId + "_" + bean.getId());
					}
					else
					{
						map.put(StandardCategory.PROP_ID, bean.getId());
					}

					map.put(StandardCategory.PROP_PARENT_ID, bean.getParentId());
					list.add(map);
					getAllChildsByParentId(parentId + "_" + bean.getId(), list, level, type);
				}
			}
		}
		catch (Exception e)
		{
			throw new ServiceException();
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @seecom.liusy.datapp.service.resource.ResourceTableService#
	 * getNodeIdByCategoryId(java.lang.String)
	 */
	public String getNodeIdByCategoryId(String categoryId) throws ServiceException
	{

		try
		{
			String nodeId = "";
			StandardCategory category = (StandardCategory) standardCategoryDao.get(Integer.valueOf(categoryId));
			if (category == null)
			{
				return "";
			}
			if (category.getParentId() == null)
			{
				return categoryId;
			}
			String parentId = getNodeIdByCategoryId(category.getParentId().toString());
			if (parentId != null && !"".equals(parentId))
			{
				nodeId = parentId + "_" + categoryId;
			}
			else
			{
				nodeId = categoryId;
			}

			return nodeId;
		}
		catch (Exception e)
		{
			throw new ServiceException();
		}

	}

	public PageQuery queryForAdvancedQuery(PageQuery pageQuery) throws ServiceException
	{
		try
		{

			PageQuery query = resourceTableDao.queryBySelectId(pageQuery);
			pageQuery.setRecordSet(query.getRecordSet());
			pageQuery.setRecordCount(query.getRecordCount());
			List<Map<String, String>> re = query.getRecordSet();
			if (re != null && !re.isEmpty())
			{
				List<StandardCategory> parent = standardCategoryDao.findByField(StandardCategory.PROP_PARENT_ID, new Integer(0));
				for (Map<String, String> map : re)
				{
					String parentId = map.get("PARENTID");
					if (parentId == null || "0".equals(parentId))
					{
						String tradeName = map.get("CATEGORYNAME");
						map.put("TRADENAME", tradeName);
						map.put("CATEGORYNAME", "");
					}
					else
					{
						for (StandardCategory standardCategory : parent)
						{
							if (parentId.equalsIgnoreCase(standardCategory.getId().toString()))
							{
								map.put("TRADENAME", standardCategory.getName());
								break;
							}
						}
					}
				}
			}

			return pageQuery;
		}
		catch (Exception e)
		{
			throw new ServiceException(e);
		}
	}

	public SysCity getSysCityByCode(String code) throws ServiceException
	{
		try
		{
			return (SysCity) sysCityDao.findByField(SysCity.PROP_CODE, code).get(0);
		}
		catch (Exception e)
		{
			throw new ServiceException();
		}
	}

	public StandardCategory getStandardcCategoryById(Integer id) throws ServiceException
	{

		try
		{
			return standardCategoryDao.get(id);
		}
		catch (Exception e)
		{
			throw new ServiceException();
		}
	}

	public List<SysCity> findAllCitys() throws ServiceException
	{
		try
		{
			return sysCityDao.findAll();
		}
		catch (Exception e)
		{
			throw new ServiceException();
		}

	}

	public List<StandardCategory> findAllTrade() throws ServiceException
	{
		try
		{
			return standardCategoryDao.findByField(StandardCategory.PROP_PARENT_ID, new Integer(0));
		}
		catch (Exception e)
		{
			throw new ServiceException();
		}
	}

	public List<StandardCategory> findCategoryByTradeId(String tradeId) throws ServiceException
	{
		try
		{
			if (tradeId == null || "".equals(tradeId))
			{
				List<ICondition> conditions = new ArrayList<ICondition>();
				conditions.add(new Condition(StandardCategory.PROP_PARENT_ID, Condition.NOT_EQUALS, new Integer(0)));
				return standardCategoryDao.findByFilter(conditions);
			}
			else
			{
				List<ICondition> conditions = new ArrayList<ICondition>();
				conditions.add(new Condition(StandardCategory.PROP_PARENT_ID, Condition.EQUALS, new Integer(tradeId)));
				return standardCategoryDao.findByFilter(conditions);
			}
		}
		catch (Exception e)
		{
			throw new ServiceException();
		}

	}
	
	public ResourceTable getTableByCondition(List<ICondition> conditions) throws ServiceException
	{
		try
		{
			List<ResourceTable> temp = resourceTableDao.commonQuery(conditions);
			if (temp!=null&&temp.size()>0)
			{
				return temp.get(0);
			}
			else
			{
				return null;
			}
		}
		catch (Exception e)
		{
			throw new ServiceException();
		}
	}
	public List findByField(String fieldName,Object fieldValue) throws ServiceException{
		try{
			return resourceTableDao.findByField(fieldName, fieldValue,ResourceTable.PROP_ORDE,false);
//			return resourceTableDao.findByField(fieldName, fieldValue);
		}catch (Exception e) {
			throw new ServiceException(e);
		}
	}
	private ResourceTableDao resourceTableDao;

	/**
	 * 注入DAO
	 * 
	 * @see com.liusy.core.service.ResourceTableService#setResourceTableDao(ResourceTable
	 *      resourceTableDao)
	 */
	public void setResourceTableDao(ResourceTableDao resourceTableDao)
	{
		this.resourceTableDao = resourceTableDao;
	}

	public ResourceTable getResouceTableByEnName(String tableName)
			throws ServiceException {
		
		try
		{
			List<ResourceTable> tableList = findByField(ResourceTable.PROP_NAME, tableName);
			if (tableList==null || tableList.size()==0) {
				//初始页面
				
				return null;
			}
			else
			{
				return tableList.get(0);
			}
		}
		catch (DAOException e)
		{
			throw new ServiceException(e);
		}
	}
}
