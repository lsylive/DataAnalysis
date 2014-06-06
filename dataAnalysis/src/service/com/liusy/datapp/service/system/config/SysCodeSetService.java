package com.liusy.datapp.service.system.config;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import com.liusy.dataapplatform.base.exception.ServiceException;
import com.liusy.dataapplatform.base.util.PageQuery;
import com.liusy.dataapplatform.code.Code;
import com.liusy.datapp.model.system.config.SysCodeSet;

public interface SysCodeSetService {
	/**
	 * 用PK查找对象
	 */
	public SysCodeSet findSysCodeSet(Serializable id) throws ServiceException;

	/**
	 * 用Unique Key查找对象
	 */
	public SysCodeSet findSysCodeSetByUK(String ukField, Object ukValue) throws ServiceException;

	/**
	 * 新增对象
	 * 
	 * @param
	 */
	public void createSysCodeSet(SysCodeSet sysCodeSet) throws ServiceException;

	/**
	 * 更新对象
	 * 
	 * @param
	 */
	public void updateSysCodeSet(SysCodeSet sysCodeSet) throws ServiceException;

	/**
	 * 删除对象
	 * 
	 * @param clazz
	 *           model class to lookup
	 * @param id
	 *           the identifier (primary key) of the class
	 */
	public void removeSysCodeSet(Serializable id) throws ServiceException;

	/**
	 * 批删除对象
	 * 
	 * @param id
	 *           the identifier (primary key) of the class
	 */
	public void removeSysCodeSets(Serializable[] ids) throws ServiceException;

	/**
	 * @param fieldName
	 *           字段名
	 * @param fieldValue
	 *           字段值,这里必须传入字段相应的类型,如Long不能用String
	 */
	public void removeSysCodeSetByField(String fieldName, Object fieldValue) throws ServiceException;

	/**
	 * @return 返回全部表对象 Collection<SysCodeSet>
	 * @throws Exception
	 */
	public Collection findAllSysCodeSet() throws ServiceException;

	/**
	 * @param fieldName
	 *           字段名
	 * @param fieldValue
	 *           字段值,这里必须传入字段相应的类型,如Long不能用String
	 */
	public Collection findSysCodeSetByField(String fieldName, Object fieldValue) throws ServiceException;

	/**
	 * 计算记录数
	 * 
	 * @param fieldName
	 *           字段名
	 * @param fieldValue
	 *           字段值,这里必须传入字段相应的类型,如Long不能用String
	 * @return
	 */
	public int countSysCodeSetByField(String fieldName, Object fieldValue) throws ServiceException;
	/**
	 * 分页动态查询
	 * 
	 * @param queryObject
	 *           (queryObject.getResult()<Map(fieldName, fieldValue)>)
	 * @return
	 */
	// public PagerObject querySysCodeSet(QueryString queryString,PagerObject
	// queryObject) throws ServiceException ;

	/**
	 * @return 返回全部表对象
	 * @throws Exception
	 */
	public Map<String, List<Code>> findAllCode() throws ServiceException;
}
