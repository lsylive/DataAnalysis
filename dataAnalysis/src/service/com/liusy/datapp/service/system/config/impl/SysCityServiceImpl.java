package com.liusy.datapp.service.system.config.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.liusy.dataapplatform.base.exception.DAOException;
import com.liusy.dataapplatform.base.exception.ServiceException;
import com.liusy.dataapplatform.base.util.Condition;
import com.liusy.dataapplatform.base.util.ICondition;
import com.liusy.dataapplatform.base.util.PageQuery;

import com.liusy.datapp.dao.system.config.SysCityDao;
import com.liusy.datapp.model.system.config.SysCity;
import com.liusy.datapp.service.system.config.SysCityService;

public class SysCityServiceImpl implements SysCityService {
	private static final long serialVersionUID = 1L;

	public SysCity findSysCity(Serializable id) throws ServiceException {
		try {
			return sysCityDao.get(id);
		}
		catch (DAOException e) {
			throw new ServiceException(e);
		}
	}
	public SysCity findSysCityByCode(String cityCode) throws ServiceException{
		try{
			List list=sysCityDao.findByField(SysCity.PROP_CODE, cityCode);
			if(list!=null && !list.isEmpty())
				return (SysCity) list.get(0);
			else
				return null;
		}catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	public void createSysCity(SysCity sysCity) throws ServiceException {
		try {
			List<ICondition> conditions = new ArrayList<ICondition>();
			conditions.add(new Condition(SysCity.PROP_NAME, Condition.EQUALS, sysCity.getName()));
			List<SysCity> list = sysCityDao.commonQuery(conditions);
			if (list!=null&&list.size()>0)
			{
				throw new ServiceException("地市名:["+sysCity.getName()+"]已经存在，不能使用！");
			}
			
			conditions.clear();
			conditions.add(new Condition(SysCity.PROP_CODE, Condition.EQUALS, sysCity.getCode()));
			list = sysCityDao.commonQuery(conditions);
			if (list!=null&&list.size()>0)
			{
				throw new ServiceException("地市代码:["+sysCity.getCode()+"]已经存在，不能使用！");
			}
			sysCityDao.save(sysCity);
		}
		catch (DAOException e) {
			throw new ServiceException(e);
		}
	}

	public void updateSysCity(SysCity sysCity) throws ServiceException {
		try {
//			SysCity tmp = sysCityDao.get(sysCity.getId());
//			ConvertUtil.convertToModelForUpdate(tmp, sysCity);	
			List<ICondition> conditions = new ArrayList<ICondition>();
			conditions.add(new Condition(SysCity.PROP_ID, Condition.NOT_EQUALS, sysCity.getId()));
			conditions.add(new Condition(SysCity.PROP_NAME, Condition.EQUALS, sysCity.getName()));
			List<SysCity> list = sysCityDao.commonQuery(conditions);
			if (list!=null&&list.size()>0)
			{
				throw new ServiceException("地市名:["+sysCity.getName()+"]已经存在，不能使用！");
			}
			
			conditions.clear();
			conditions.add(new Condition(SysCity.PROP_ID, Condition.NOT_EQUALS, sysCity.getId()));
			conditions.add(new Condition(SysCity.PROP_CODE, Condition.EQUALS, sysCity.getCode()));
			list = sysCityDao.commonQuery(conditions);
			if (list!=null&&list.size()>0)
			{
				throw new ServiceException("地市代码:["+sysCity.getCode()+"]已经存在，不能使用！");
			}
			
			sysCityDao.update(sysCity);
		}
		catch (DAOException e) {
			throw new ServiceException(e);
		}
		catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	public void removeSysCity(Serializable id) throws ServiceException {
		try {
			sysCityDao.delete(id);
		}
		catch (DAOException e) {
			throw new ServiceException(e);
		}
	}

	public void removeSysCitys(Serializable[] ids) throws ServiceException {
		try {
			sysCityDao.deleteAll(ids);
		}
		catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	public PageQuery querySysCity(PageQuery pageQuery) throws ServiceException {
		try {
			PageQuery  query=sysCityDao.queryBySelectId(pageQuery);
			pageQuery.setRecordSet(query.getRecordSet());
			pageQuery.setRecordCount(query.getRecordCount());
			return pageQuery;
		}
		catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	private SysCityDao	sysCityDao;

	/**
	 * 注入DAO
	 * 
	 * @see com.liusy.core.service.SysCityService#setSysCityDao(SysCity
	 *      sysCityDao)
	 */
	public void setSysCityDao(SysCityDao sysCityDao) {
		this.sysCityDao = sysCityDao;
	}
}

