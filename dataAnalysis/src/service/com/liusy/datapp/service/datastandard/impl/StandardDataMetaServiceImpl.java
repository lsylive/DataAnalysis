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
import com.liusy.datapp.dao.datastandard.StandardCodesetDao;
import com.liusy.datapp.dao.datastandard.StandardDataMetaDao;
import com.liusy.datapp.dao.datastandard.StandardIndicatorDao;
import com.liusy.datapp.model.datastandard.StandardDataMeta;
import com.liusy.datapp.service.datastandard.StandardDataMetaService;

public class StandardDataMetaServiceImpl implements StandardDataMetaService {
	private static final long serialVersionUID = 1L;
	private StandardCategoryDao standardCategoryDao;
	private StandardCodesetDao standardCodesetDao;
	private StandardIndicatorDao standardIndicatorDao;

	public StandardDataMeta findStandardDataMeta(Serializable id) throws ServiceException {
		try {
			return standardDataMetaDao.get(id);
		}
		catch (DAOException e) {
			throw new ServiceException(e);
		}
	}

	public void createStandardDataMeta(StandardDataMeta standardDataMeta) throws ServiceException {
		try {
			List<ICondition> cd = new ArrayList<ICondition>();
			cd.add(new Condition(StandardDataMeta.PROP_CN_NAME, Condition.EQUALS, standardDataMeta.getCnName()));
			List<StandardDataMeta> list = standardDataMetaDao.commonQuery(cd);
			if (list!=null&&list.size()>0)
			{
				throw new ServiceException("中文名:["+standardDataMeta.getCnName()+"]已经存在，不能使用！");
			}
			
			cd.clear();
			cd.add(new Condition(StandardDataMeta.PROP_NAME, Condition.EQUALS, standardDataMeta.getName()));
			list = standardDataMetaDao.commonQuery(cd);
			if (list!=null&&list.size()>0)
			{
				throw new ServiceException("英文名:["+standardDataMeta.getName()+"]已经存在，不能使用！");
			}
			
			cd.clear();
			cd.add(new Condition(StandardDataMeta.PROP_CODE, Condition.EQUALS, standardDataMeta.getCode()));
			list = standardDataMetaDao.commonQuery(cd);
			if (list!=null&&list.size()>0)
			{
				throw new ServiceException("代码:["+standardDataMeta.getCode()+"]已经存在，不能使用！");
			}
			
			
			standardDataMetaDao.save(standardDataMeta);
		}
		catch (DAOException e) {
			throw new ServiceException(e);
		}
	}

	public void updateStandardDataMeta(StandardDataMeta standardDataMeta) throws ServiceException {
		try {
//			StandardDataMeta tmp = standardDataMetaDao.get(standardDataMeta.getId());
//			ConvertUtil.convertToModelForUpdate(tmp, standardDataMeta);		
			
			List<ICondition> cd = new ArrayList<ICondition>();
			cd.add(new Condition(StandardDataMeta.PROP_ID, Condition.NOT_EQUALS, standardDataMeta.getId()));
			cd.add(new Condition(StandardDataMeta.PROP_CN_NAME, Condition.EQUALS, standardDataMeta.getCnName()));
			List<StandardDataMeta> list = standardDataMetaDao.commonQuery(cd);
			if (list!=null&&list.size()>0)
			{
				throw new ServiceException("中文名:["+standardDataMeta.getCnName()+"]已经存在，不能使用！");
			}
			
			cd.clear();
			cd.add(new Condition(StandardDataMeta.PROP_ID, Condition.NOT_EQUALS, standardDataMeta.getId()));
			cd.add(new Condition(StandardDataMeta.PROP_NAME, Condition.EQUALS, standardDataMeta.getName()));
			list = standardDataMetaDao.commonQuery(cd);
			if (list!=null&&list.size()>0)
			{
				throw new ServiceException("英文名:["+standardDataMeta.getName()+"]已经存在，不能使用！");
			}
			
			cd.clear();
			cd.add(new Condition(StandardDataMeta.PROP_ID, Condition.NOT_EQUALS, standardDataMeta.getId()));
			cd.add(new Condition(StandardDataMeta.PROP_CODE, Condition.EQUALS, standardDataMeta.getCode()));
			list = standardDataMetaDao.commonQuery(cd);
			if (list!=null&&list.size()>0)
			{
				throw new ServiceException("代码:["+standardDataMeta.getCode()+"]已经存在，不能使用！");
			}
			standardDataMetaDao.update(standardDataMeta);
		}
		catch (DAOException e) {
			throw new ServiceException(e);
		}
		catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	public void removeStandardDataMeta(Serializable id) throws ServiceException {
		try {
			standardDataMetaDao.delete(id);
		}
		catch (DAOException e) {
			throw new ServiceException(e);
		}
	}

	public void removeStandardDataMetas(Serializable[] ids) throws ServiceException {
		try {
			standardDataMetaDao.deleteAll(ids);
		}
		catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	public PageQuery queryStandardDataMeta(PageQuery pageQuery) throws ServiceException {
		try {
			PageQuery  query=standardDataMetaDao.queryBySelectId(pageQuery);
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

	public PageQuery getCodesetsForTree(PageQuery pagequery) throws ServiceException {
		return standardCodesetDao.queryBySelectId(pagequery);
	}

	public void setStandardCodesetDao(StandardCodesetDao standardCodesetDao) {
		this.standardCodesetDao = standardCodesetDao;
	}

	public PageQuery getIndicatorsForTree(PageQuery pagequery) throws ServiceException {
		return standardIndicatorDao.queryBySelectId(pagequery);
	}

	public void setStandardIndicatorDao(StandardIndicatorDao standardIndicatorDao) {
		this.standardIndicatorDao = standardIndicatorDao;
	}

	private StandardDataMetaDao	standardDataMetaDao;

	/**
	 * 注入DAO
	 * 
	 * @see com.liusy.core.service.StandardDataMetaService#setStandardDataMetaDao(StandardDataMeta
	 *      standardDataMetaDao)
	 */
	public void setStandardDataMetaDao(StandardDataMetaDao standardDataMetaDao) {
		this.standardDataMetaDao = standardDataMetaDao;
	}
}

