package com.liusy.datapp.service.datastandard.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.liusy.dataapplatform.base.exception.DAOException;
import com.liusy.dataapplatform.base.exception.ServiceException;
import com.liusy.dataapplatform.base.util.Condition;
import com.liusy.dataapplatform.base.util.ICondition;
import com.liusy.dataapplatform.base.util.PageQuery;
import com.liusy.datapp.dao.datastandard.StandardCategoryDao;
import com.liusy.datapp.dao.datastandard.StandardIndicatorDao;
import com.liusy.datapp.model.datastandard.StandardCategory;
import com.liusy.datapp.model.datastandard.StandardIndicator;
import com.liusy.datapp.service.datastandard.StandardIndicatorService;

public class StandardIndicatorServiceImpl implements StandardIndicatorService {
	private static final long serialVersionUID = 1L;
	private StandardCategoryDao standardCategoryDao;

	public StandardIndicator findStandardIndicator(Serializable id) throws ServiceException {
		try {
			return standardIndicatorDao.get(id);
		}
		catch (DAOException e) {
			throw new ServiceException(e);
		}
	}

	public void createStandardIndicator(StandardIndicator standardIndicator) throws ServiceException {
		try {
			List<ICondition> conditions = new ArrayList<ICondition>();
			conditions.add(new Condition(StandardIndicator.PROP_NAME, Condition.EQUALS, standardIndicator.getName()));
			List<StandardIndicator> list = standardIndicatorDao.commonQuery(conditions);
			if (list!=null&&list.size()>0)
			{
				throw new ServiceException("英文名:["+standardIndicator.getName()+"]已经存在，不能使用！");
			}
			
			conditions.clear();
			conditions.add(new Condition(StandardIndicator.PROP_CN_NAME, Condition.EQUALS, standardIndicator.getCnName()));
			list = standardIndicatorDao.commonQuery(conditions);
			if (list!=null&&list.size()>0)
			{
				throw new ServiceException("中文名:["+standardIndicator.getName()+"]已经存在，不能使用！");
			}
			
			conditions.clear();
			conditions.add(new Condition(StandardIndicator.PROP_INDICATOR_CODE, Condition.EQUALS, standardIndicator.getIndicatorCode()));
			list = standardIndicatorDao.commonQuery(conditions);
			if (list!=null&&list.size()>0)
			{
				throw new ServiceException("代码:["+standardIndicator.getIndicatorCode()+"]已经存在，不能使用！");
			}
			
			standardIndicatorDao.save(standardIndicator);
		}
		catch (DAOException e) {
			throw new ServiceException(e);
		}
	}

	public void updateStandardIndicator(StandardIndicator standardIndicator) throws ServiceException {
		try {
//			StandardIndicator tmp = standardIndicatorDao.get(standardIndicator.getId());
//			ConvertUtil.convertToModelForUpdate(tmp, standardIndicator);	
			List<ICondition> conditions = new ArrayList<ICondition>();
			conditions.add(new Condition(StandardIndicator.PROP_ID, Condition.NOT_EQUALS, standardIndicator.getId()));
			conditions.add(new Condition(StandardIndicator.PROP_NAME, Condition.EQUALS, standardIndicator.getName()));
			List<StandardIndicator> list = standardIndicatorDao.commonQuery(conditions);
			if (list!=null&&list.size()>0)
			{
				throw new ServiceException("英文名:["+standardIndicator.getName()+"]已经存在，不能使用！");
			}
			
			conditions.clear();
			conditions.add(new Condition(StandardIndicator.PROP_ID, Condition.NOT_EQUALS, standardIndicator.getId()));
			conditions.add(new Condition(StandardIndicator.PROP_CN_NAME, Condition.EQUALS, standardIndicator.getCnName()));
			list = standardIndicatorDao.commonQuery(conditions);
			if (list!=null&&list.size()>0)
			{
				throw new ServiceException("中文名:["+standardIndicator.getName()+"]已经存在，不能使用！");
			}
			
			conditions.clear();
			conditions.add(new Condition(StandardIndicator.PROP_ID, Condition.NOT_EQUALS, standardIndicator.getId()));
			conditions.add(new Condition(StandardIndicator.PROP_INDICATOR_CODE, Condition.EQUALS, standardIndicator.getIndicatorCode()));
			list = standardIndicatorDao.commonQuery(conditions);
			if (list!=null&&list.size()>0)
			{
				throw new ServiceException("代码:["+standardIndicator.getIndicatorCode()+"]已经存在，不能使用！");
			}
			standardIndicatorDao.update(standardIndicator);
		}
		catch (DAOException e) {
			throw new ServiceException(e);
		}
		catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	public void removeStandardIndicator(Serializable id) throws ServiceException {
		try {
			standardIndicatorDao.delete(id);
		}
		catch (DAOException e) {
			throw new ServiceException(e);
		}
	}

	public void removeStandardIndicators(Serializable[] ids) throws ServiceException {
		try {
			standardIndicatorDao.deleteAll(ids);
		}
		catch (Exception e) {
			throw new ServiceException(e);
		}
	}
	public Collection findAllStandardIndicators() throws ServiceException {
		try {
			return standardIndicatorDao.findAll();
		}
		catch (Exception e) {
			throw new ServiceException(e);
		}
	}
	public Collection findStandardIndicatorsByField(String fieldName,Object fieldValue) throws ServiceException {
		try {
			return standardIndicatorDao.findByField(fieldName, fieldValue);
		}
		catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	public PageQuery queryStandardIndicator(PageQuery pageQuery) throws ServiceException {
		try {
			PageQuery  query=standardIndicatorDao.queryBySelectId(pageQuery);
			pageQuery.setRecordSet(query.getRecordSet());
			pageQuery.setRecordCount(query.getRecordCount());
			return pageQuery;
		}
		catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	public PageQuery getCategorysForTree(PageQuery pagequery) throws ServiceException {
		return standardCategoryDao.queryBySelectId(pagequery);
	}

	public void setStandardCategoryDao(StandardCategoryDao standardCategoryDao) {
		this.standardCategoryDao = standardCategoryDao;
	}

	private StandardIndicatorDao	standardIndicatorDao;

	/**
	 * 注入DAO
	 * 
	 * @see com.liusy.core.service.StandardIndicatorService#setStandardIndicatorDao(StandardIndicator
	 *      standardIndicatorDao)
	 */
	public void setStandardIndicatorDao(StandardIndicatorDao standardIndicatorDao) {
		this.standardIndicatorDao = standardIndicatorDao;
	}
}

