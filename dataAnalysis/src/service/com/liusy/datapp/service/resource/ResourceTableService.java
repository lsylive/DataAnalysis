package com.liusy.datapp.service.resource;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.liusy.dataapplatform.base.exception.ServiceException;
import com.liusy.dataapplatform.base.util.ICondition;
import com.liusy.dataapplatform.base.util.PageQuery;
import com.liusy.datapp.model.datastandard.StandardCategory;
import com.liusy.datapp.model.resource.ResourceTable;
import com.liusy.datapp.model.system.config.SysCity;

public interface ResourceTableService {

	public ResourceTable findResourceTable(Serializable id) throws ServiceException;

	public void createResourceTable(ResourceTable resourceTable) throws ServiceException;

	public void updateResourceTable(ResourceTable resourceTable) throws ServiceException;

	public void removeResourceTable(Serializable id) throws ServiceException;

	public PageQuery queryResourceTable(PageQuery pageQuery) throws ServiceException;


	public void removeResourceTables(Serializable[] ids) throws ServiceException; 
	public void removeResourceTablesAndColumn(Serializable[] ids) throws ServiceException;
	
	public PageQuery loadDataForTree(PageQuery pagequery) throws ServiceException;
	
	public PageQuery getCategoryForTree(PageQuery pagequery) throws ServiceException;

	public List<ResourceTable> findTableByCatageoryId(String cataId) throws ServiceException;
	public List<ResourceTable> findAllTable() throws ServiceException;
	
	public ResourceTable getResouceTableByEnName(String tableName) throws ServiceException;

	/**
	 * 
	 * @author 黄少淘
	 * @date 2009-10-14
	 * @Title: getAllCategorysByParentId
	 * @Description: 递归查找某个分类下的所有子分类和间接子分类
	 * @param parentId
	 * @return
	 * @throws ServiceException
	 */
	public List<Map<String, Object>> getAllCategorysByParentId(String parentId) throws ServiceException;
	/**
	 * 
	 * @author 黄少淘
	 * @date 2009-10-14
	 * @Title: getAllCategorysForSelection
	 * @Description: 将所有分类及其所有子分类和间接子分类按层级关系查找出来，用于初始化分类下拉框
	 * @return
	 * @throws ServiceException
	 */
	public List<Map<String, Object>> getAllCategorysForSelection(String citycode) throws ServiceException;
	/**
	 * 
	 * @author 黄少淘
	 * @date 2009-10-16
	 * @Title: getStandardcCategoryById
	 * @Description: 根据id查找某个分类
	 * @param id
	 * @return
	 * @throws ServiceException
	 */
	public StandardCategory getStandardcCategoryById(Integer id)throws ServiceException;
	/**
	 * 
	 * @author 黄少淘
	 * @date 2009-10-16
	 * @Title: getSysCityByCode
	 * @Description: 根据代码查找某个地市
	 * @param code
	 * @return
	 * @throws ServiceException
	 */
	public SysCity getSysCityByCode(String code) throws ServiceException;
	/**
	 * 
	 * @author 黄少淘
	 * @date 2009-10-19
	 * @Title: getAllTreeNodeAsXmlString
	 * @Description: 一次性拉取所有分类的节点以xml格式的字符串返回
	 * @param cityCode
	 * @return
	 * @throws ServiceException
	 */
	public String getAllTreeNodeAsXmlString(String cityCode)throws ServiceException;
	/**
	 * 
	 * @author 黄少淘
	 * @date 2009-10-19
	 * @Title: getNodeIdByCategoryId
	 * @Description: 根据某个分类的id查找其所有父类id并按顺序用_连接起来
	 * @param categoryId
	 * @return
	 * @throws ServiceException
	 */
	public String getNodeIdByCategoryId(String categoryId)throws ServiceException;
	
	public PageQuery queryForAdvancedQuery(PageQuery pageQuery)throws ServiceException;
	
	public List<SysCity> findAllCitys()throws ServiceException;
	
	public List<StandardCategory> findAllTrade()throws ServiceException;
	
	public List<StandardCategory> findCategoryByTradeId(String tradeId)throws ServiceException;
	
	public ResourceTable getTableByCondition(List<ICondition> conditions)throws ServiceException;
	
	public List findByField(String fieldName,Object fieldValue) throws ServiceException;
}

