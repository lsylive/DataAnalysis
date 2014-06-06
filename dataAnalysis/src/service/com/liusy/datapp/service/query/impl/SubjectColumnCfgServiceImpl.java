package com.liusy.datapp.service.query.impl;

import java.io.Serializable;
import java.util.List;

import com.liusy.dataapplatform.base.exception.DAOException;
import com.liusy.dataapplatform.base.exception.ServiceException;
import com.liusy.dataapplatform.base.util.PageQuery;
import com.liusy.datapp.dao.query.SubjectColumnCfgDao;
import com.liusy.datapp.model.query.SubjectColumnCfg;
import com.liusy.datapp.service.query.SubjectColumnCfgService;

public class SubjectColumnCfgServiceImpl implements SubjectColumnCfgService {
	private static final long serialVersionUID = 1L;

	public SubjectColumnCfg findSubjectColumnCfg(Serializable id) throws ServiceException {
		try {
			return subjectColumnCfgDao.get(id);
		}
		catch (DAOException e) {
			throw new ServiceException(e);
		}
	}

	public void createSubjectColumnCfg(SubjectColumnCfg subjectColumnCfg) throws ServiceException {
		try {
			subjectColumnCfgDao.save(subjectColumnCfg);
		}
		catch (DAOException e) {
			throw new ServiceException(e);
		}
	}

	public void updateSubjectColumnCfg(SubjectColumnCfg subjectColumnCfg) throws ServiceException {
		try {
			subjectColumnCfgDao.update(subjectColumnCfg);
		}
		catch (DAOException e) {
			throw new ServiceException(e);
		}
		catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	public void removeSubjectColumnCfg(Serializable id) throws ServiceException {
		try {
			subjectColumnCfgDao.delete(id);
		}
		catch (DAOException e) {
			throw new ServiceException(e);
		}
	}

	public void removeSubjectColumnCfgs(Serializable[] ids) throws ServiceException {
		try {
			subjectColumnCfgDao.deleteAll(ids);
		}
		catch (Exception e) {
			throw new ServiceException(e);
		}
	}
	public void removeSubjectColumnCfgBySubjectId(String subjectId) throws ServiceException{
		try{
			subjectColumnCfgDao.deleteByField(SubjectColumnCfg.PROP_SUBJECT_ID, Integer.valueOf(subjectId));
		}catch (Exception e) {
			throw new ServiceException(e);
		}
		
	}

	public PageQuery querySubjectColumnCfg(PageQuery pageQuery) throws ServiceException {
		try {
			PageQuery  query=subjectColumnCfgDao.queryBySelectId(pageQuery);
			pageQuery.setRecordSet(query.getRecordSet());
			pageQuery.setRecordCount(query.getRecordCount());
			return pageQuery;
		}
		catch (Exception e) {
			throw new ServiceException(e);
		}
	}
	public List<SubjectColumnCfg> findAllConfigByOrder(String subjectId) throws ServiceException{
		try{
			return subjectColumnCfgDao.findAllConfigByOrder(subjectId,false);		
		}catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	public List<SubjectColumnCfg> findAllConfigByOrderForBatch(String subjectId) throws ServiceException {
		try{
			return subjectColumnCfgDao.findAllConfigByOrder(subjectId,true);		
		}catch (Exception e) {
			throw new ServiceException(e);
		}
	}
	
	private SubjectColumnCfgDao	subjectColumnCfgDao;

	/**
	 * ×¢ÈëDAO
	 * 
	 * @see com.liusy.core.service.SubjectColumnCfgService#setSubjectColumnCfgDao(SubjectColumnCfg
	 *      subjectColumnCfgDao)
	 */
	public void setSubjectColumnCfgDao(SubjectColumnCfgDao subjectColumnCfgDao) {
		this.subjectColumnCfgDao = subjectColumnCfgDao;
	}
}

