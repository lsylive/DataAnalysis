package com.liusy.datapp.service.system.config.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.liusy.dataapplatform.base.exception.ServiceException;
import com.liusy.dataapplatform.base.util.PageQuery;
import com.liusy.dataapplatform.code.Code;
import com.liusy.datapp.dao.system.config.SysCodeDao;
import com.liusy.datapp.dao.system.config.SysCodeSetDao;
import com.liusy.datapp.model.system.config.SysCode;
import com.liusy.datapp.model.system.config.SysCodeSet;
import com.liusy.datapp.service.system.config.SysCodeSetService;

public class SysCodeSetServiceImpl implements SysCodeSetService {
	private SysCodeSetDao	sysCodeSetDao;

	private SysCodeDao	sysCodeDao;

	/**
	 * 注入DAO
	 * 
	 * @see com.liusy.core.service.SysCodeSetService#setSysCodeSetDao(SysCodeSetDao
	 *      sysCodeSetDao)
	 */
	public void setSysCodeSetDao(SysCodeSetDao sysCodeSetDao) {
		this.sysCodeSetDao = sysCodeSetDao;
	}

	/**
	 * 用PK查找对象
	 * 
	 * @see com.liusy.core.service.SysCodeSetService#findSysCodeSet(Serializable
	 *      id)
	 */
	public SysCodeSet findSysCodeSet(Serializable id) throws ServiceException {
		try {
			return sysCodeSetDao.get(id);
		}
		catch (Exception e) {

			throw new ServiceException(e);
		}
	}

	/**
	 * 用Unique Key查找对象
	 */
	public SysCodeSet findSysCodeSetByUK(String ukField, Object ukValue) throws ServiceException {
		try {
			Collection c = findSysCodeSetByField(ukField, ukValue);
			if (c != null && !c.isEmpty()) { return (SysCodeSet) c.iterator().next(); }
			return null;
		}
		catch (Exception e) {

			throw new ServiceException(e);
		}
	}

	/**
	 * 新增对象
	 * 
	 * @see com.liusy.core.service.SysCodeSetService#createSysCodeSet(SysCodeSet
	 *      sysCodeSet)
	 * @param
	 */
	public void createSysCodeSet(SysCodeSet sysCodeSet) throws ServiceException {
		try {
			sysCodeSetDao.save(sysCodeSet);
		}
		catch (Exception e) {

			throw new ServiceException(e);
		}
	}

	/**
	 * 更新对象
	 * 
	 * @see com.liusy.core.service.SysCodeSetService#updateSysCodeSet(SysCodeSet
	 *      sysCodeSet)
	 * @param
	 */
	public void updateSysCodeSet(SysCodeSet sysCodeSet) throws ServiceException {
		try {
			sysCodeSetDao.update(sysCodeSet);
		}
		catch (Exception e) {

			throw new ServiceException(e);
		}
	}

	/**
	 * 删除对象
	 * 
	 * @param clazz
	 *           model class to lookup
	 * @param id
	 *           the identifier (primary key) of the class
	 * @see com.liusy.core.service.SysCodeSetService#removeSysCodeSet(Serializable
	 *      id)
	 */
	public void removeSysCodeSet(Serializable id) throws ServiceException {
		try {
			sysCodeSetDao.delete(id);
		}
		catch (Exception e) {

			throw new ServiceException(e);
		}
	}

	/**
	 * 批删除对象
	 * 
	 * @param id
	 *           the identifier (primary key) of the class
	 */
	public void removeSysCodeSets(Serializable[] ids) throws ServiceException {
		try {
			for (int i = 0; i < ids.length; i++) {
				removeSysCodeSet(ids[i]);
			}
		}
		catch (Exception e) {

			throw new ServiceException(e);
		}
	}

	/**
	 * 使用外健批删除从表记录
	 * 
	 * @param fieldName
	 *           字段名
	 * @param fieldValue
	 *           字段值,这里必须传入字段相应的类型,如Long不能用String
	 * @see com.liusy.core.service.SysCodeSetService#removeSysCodeSetByField(String
	 *      fieldName, Object fieldValue)
	 */
	public void removeSysCodeSetByField(String fieldName, Object fieldValue) throws ServiceException {
		try {
			sysCodeSetDao.deleteByField(fieldName, fieldValue);
		}
		catch (Exception e) {

			throw new ServiceException(e);
		}
	}

	/**
	 * 使用外健获取从表记录
	 * 
	 * @param fieldName
	 *           字段名
	 * @param fieldValue
	 *           字段值,这里必须传入字段相应的类型,如Long不能用String
	 * @see com.liusy.core.service.SysCodeSetService#findSysCodeSetByField(String
	 *      fieldName, Object fieldValue)
	 */
	public Collection findSysCodeSetByField(String fieldName, Object fieldValue) throws ServiceException {
		try {
			return sysCodeSetDao.findByField(fieldName, fieldValue);
		}
		catch (Exception e) {

			throw new ServiceException(e);
		}
	}

	/**
	 * 使用外健计算从表记录数
	 * 
	 * @param fieldName
	 *           字段名
	 * @param fieldValue
	 *           字段值,这里必须传入字段相应的类型,如Long不能用String
	 * @return
	 * @see com.liusy.core.service.SysCodeSetService#countSysCodeSetByField(String
	 *      fieldName, Object fieldValue)
	 */
	public int countSysCodeSetByField(String fieldName, Object fieldValue) throws ServiceException {
		try {
			return sysCodeSetDao.countByField(fieldName, fieldValue);
		}
		catch (Exception e) {

			throw new ServiceException(e);
		}
	}

	/**
	 * @return 全部
	 * @throws Exception
	 */
	public Collection findAllSysCodeSet() throws ServiceException {
		try {
			return sysCodeSetDao.findAll();
		}
		catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	public Map<String, List<Code>> findAllCode() throws ServiceException {
		if (sets != null) return sets;
		sets = new HashMap<String, List<Code>>();
		Iterator it = findAllSysCodeSet().iterator();
		try {
			Iterator it1 = sysCodeDao.findAll().iterator();
			while (it1.hasNext()) {
				SysCode sysCode = (SysCode) it1.next();
				if (sets.get(sysCode.getCodeSetId().toString()) == null) sets.put(sysCode.getCodeSetId().toString(), new ArrayList<Code>());
				sets.get(sysCode.getCodeSetId().toString()).add(new Code(sysCode.getName(), sysCode.getValue(), sysCode.getParentId(), sysCode.getParentValue()));
			}
			while (it.hasNext()) {
				SysCodeSet set = (SysCodeSet) it.next();
				List list = sets.get(set.getId().toString());
				if (list != null) {
					sets.remove(set.getId().toString());
					sets.put(set.getEname(), list);
				}
			}
		}
		catch (Exception e) {
			throw new ServiceException(e);
		}
		return sets;
	}

	private Map<String, List<Code>>	sets	= null;

	public SysCodeDao getSysCodeDao() {
		return sysCodeDao;
	}

	public void setSysCodeDao(SysCodeDao sysCodeDao) {
		this.sysCodeDao = sysCodeDao;
	}

	public PageQuery getCodeSetsForTree(PageQuery pagequery)
			throws ServiceException {
		return sysCodeSetDao.queryBySelectId(pagequery);
	}

	public PageQuery querySysCodeSet(PageQuery pageQuery) throws ServiceException {
		try {
			PageQuery  query=sysCodeSetDao.queryBySelectId(pageQuery);
			pageQuery.setRecordSet(query.getRecordSet());
			pageQuery.setRecordCount(query.getRecordCount());
			return pageQuery;
		}
		catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	// /**
	// * 多表分页动态查询
	// * @param queryObject
	// * @return
	// * @see com.liusy.service.BaseService#query(String selectId,
	// QueryObject queryObject)
	// */
	// public PagerObject query(QueryString queryString, PagerObject pagerObject)
	// throws ServiceException {
	// try {
	// return sysCodeSetDao.queryBySelectId(queryString, pagerObject);
	// } catch (Exception e) {
	//
	// throw new ServiceException(e);
	// }
	// }
}
