package com.liusy.datapp.service.datastandard.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.liusy.dataapplatform.base.exception.DAOException;
import com.liusy.dataapplatform.base.exception.ServiceException;
import com.liusy.dataapplatform.base.util.PageQuery;
import com.liusy.dataapplatform.code.Code;
import com.liusy.datapp.dao.datastandard.StandardCategoryDao;
import com.liusy.datapp.dao.datastandard.StandardCodeDao;
import com.liusy.datapp.dao.datastandard.StandardCodesetDao;
import com.liusy.datapp.model.datastandard.StandardCode;
import com.liusy.datapp.model.datastandard.StandardCodeset;
import com.liusy.datapp.model.system.config.SysCode;
import com.liusy.datapp.model.system.config.SysCodeSet;
import com.liusy.datapp.service.datastandard.StandardCodesetService;

public class StandardCodesetServiceImpl implements StandardCodesetService {
	private static final long serialVersionUID = 1L;

	private StandardCategoryDao standardCategoryDao;
	private StandardCodeDao standardCodeDao;
	private Map<String, List<StandardCode>>	sets	= null;

	public StandardCodeset findStandardCodeset(Serializable id) throws ServiceException {
		try {
			return standardCodesetDao.get(id);
		}
		catch (DAOException e) {
			throw new ServiceException(e);
		}
	}
	public StandardCodeset findCodeSetByCodeNo(String codeNo) throws ServiceException{
		try{
			Collection col=standardCodesetDao.findByField(StandardCodeset.PROP_CODESET_NO, codeNo);
			if(!col.isEmpty()){
				return (StandardCodeset) col.iterator().next();
			}else
				return null;
		}catch (DAOException e) {
			throw new ServiceException(e);
		}
	}
	public void createStandardCodeset(StandardCodeset standardCodeset) throws ServiceException {
		try {
			standardCodesetDao.save(standardCodeset);
		}
		catch (DAOException e) {
			throw new ServiceException(e);
		}
	}

	public void updateStandardCodeset(StandardCodeset standardCodeset) throws ServiceException {
		try {
//			StandardCodeset tmp = standardCodesetDao.get(standardCodeset.getId());
//			ConvertUtil.convertToModelForUpdate(tmp, standardCodeset);			
			standardCodesetDao.update(standardCodeset);
		}
		catch (DAOException e) {
			throw new ServiceException(e);
		}
		catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	public void removeStandardCodeset(Serializable id) throws ServiceException {
		try {
			standardCodesetDao.delete(id);
		}
		catch (DAOException e) {
			throw new ServiceException(e);
		}
	}

	public void removeStandardCodesets(Serializable[] ids) throws ServiceException {
		try {
			standardCodesetDao.deleteAll(ids);
		}
		catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	public PageQuery queryStandardCodeset(PageQuery pageQuery) throws ServiceException {
		try {
			PageQuery  query=standardCodesetDao.queryBySelectId(pageQuery);
			pageQuery.setRecordSet(query.getRecordSet());
			pageQuery.setRecordCount(query.getRecordCount());
			return pageQuery;
		}
		catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	public void removeStandardCodesetsAndCodes(Serializable[] ids) throws ServiceException {
		try {
			standardCodesetDao.deleteAll(ids);
			for (int i = 0; i < ids.length; i++) {
				standardCodeDao.deleteByField(StandardCode.PROP_CODESET_ID, ids[i]);
			}
		}
		catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	public void setStandardCodeDao(StandardCodeDao standardCodeDao){
		this.standardCodeDao = standardCodeDao;
	}
	

/*
	public List<StandardCategory> getCategorysForTree(Collection<ICondition> conditions) throws ServiceException {
		return standardCategoryDao.commonQuery(conditions);
	}
*/
	public PageQuery getCategorysForTree(PageQuery pagequery) throws ServiceException {
		return standardCategoryDao.queryBySelectId(pagequery);
	}
	

	public void setStandardCategoryDao(StandardCategoryDao standardCategoryDao) {
		this.standardCategoryDao = standardCategoryDao;
	}
	public List<StandardCodeset> findAllCodeSet() throws ServiceException{
		try{
			return (List<StandardCodeset>)standardCodesetDao.findAll();
		}catch (Exception e) {
			throw new ServiceException(e);
		}
	}
	public Map<String, List<StandardCode>> findAllBussCode() throws ServiceException {
		if (sets != null) return sets;
		sets = new HashMap<String, List<StandardCode>>();
		Iterator it = findAllCodeSet().iterator();
		try {
			Iterator it1 = standardCodeDao.findAll().iterator();
			while (it1.hasNext()) {
				StandardCode bussCode = (StandardCode) it1.next();
				if (sets.get(bussCode.getCodesetId().toString()) == null) sets.put(bussCode.getCodesetId().toString(), new ArrayList<StandardCode>());
				StandardCode code=new StandardCode();
				code.setName(bussCode.getName());
				code.setValue(bussCode.getValue());
				sets.get(bussCode.getCodesetId().toString()).add(code);
			}
			while (it.hasNext()) {
				StandardCodeset set = (StandardCodeset) it.next();
				List list = sets.get(set.getId().toString());
				if (list != null) {
					sets.remove(set.getId().toString());
					sets.put(set.getCodesetNo(), list);
				}
			}
		}
		catch (Exception e) {
			throw new ServiceException(e);
		}
		return sets;
	}

	private StandardCodesetDao	standardCodesetDao;

	/**
	 * ×¢ÈëDAO
	 * 
	 * @see com.liusy.core.service.StandardCodesetService#setStandardCodesetDao(StandardCodeset
	 *      standardCodesetDao)
	 */
	public void setStandardCodesetDao(StandardCodesetDao standardCodesetDao) {
		this.standardCodesetDao = standardCodesetDao;
	}
}

