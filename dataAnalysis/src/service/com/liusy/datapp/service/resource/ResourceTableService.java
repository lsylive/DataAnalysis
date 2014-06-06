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
	 * @author ������
	 * @date 2009-10-14
	 * @Title: getAllCategorysByParentId
	 * @Description: �ݹ����ĳ�������µ������ӷ���ͼ���ӷ���
	 * @param parentId
	 * @return
	 * @throws ServiceException
	 */
	public List<Map<String, Object>> getAllCategorysByParentId(String parentId) throws ServiceException;
	/**
	 * 
	 * @author ������
	 * @date 2009-10-14
	 * @Title: getAllCategorysForSelection
	 * @Description: �����з��༰�������ӷ���ͼ���ӷ��ఴ�㼶��ϵ���ҳ��������ڳ�ʼ������������
	 * @return
	 * @throws ServiceException
	 */
	public List<Map<String, Object>> getAllCategorysForSelection(String citycode) throws ServiceException;
	/**
	 * 
	 * @author ������
	 * @date 2009-10-16
	 * @Title: getStandardcCategoryById
	 * @Description: ����id����ĳ������
	 * @param id
	 * @return
	 * @throws ServiceException
	 */
	public StandardCategory getStandardcCategoryById(Integer id)throws ServiceException;
	/**
	 * 
	 * @author ������
	 * @date 2009-10-16
	 * @Title: getSysCityByCode
	 * @Description: ���ݴ������ĳ������
	 * @param code
	 * @return
	 * @throws ServiceException
	 */
	public SysCity getSysCityByCode(String code) throws ServiceException;
	/**
	 * 
	 * @author ������
	 * @date 2009-10-19
	 * @Title: getAllTreeNodeAsXmlString
	 * @Description: һ������ȡ���з���Ľڵ���xml��ʽ���ַ�������
	 * @param cityCode
	 * @return
	 * @throws ServiceException
	 */
	public String getAllTreeNodeAsXmlString(String cityCode)throws ServiceException;
	/**
	 * 
	 * @author ������
	 * @date 2009-10-19
	 * @Title: getNodeIdByCategoryId
	 * @Description: ����ĳ�������id���������и���id����˳����_��������
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

