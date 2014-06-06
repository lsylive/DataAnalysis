package com.liusy.datapp.service.query.impl;

import java.io.Serializable;
import java.util.List;

import com.liusy.dataapplatform.base.exception.DAOException;
import com.liusy.dataapplatform.base.exception.ServiceException;
import com.liusy.dataapplatform.base.util.ConvertUtil;
import com.liusy.dataapplatform.base.util.PageQuery;
import com.liusy.datapp.dao.query.SubjectTableRelationDao;
import com.liusy.datapp.model.query.SubjectTableRelation;
import com.liusy.datapp.service.query.SubjectTableRelationService;

public class SubjectTableRelationServiceImpl implements SubjectTableRelationService {
	private static final long serialVersionUID = 1L;

	public SubjectTableRelation findSubjectTableRelation(Serializable id) throws ServiceException {
		try {
			return subjectTableRelationDao.get(id);
		}
		catch (DAOException e) {
			throw new ServiceException(e);
		}
	}

	public void createSubjectTableRelation(SubjectTableRelation subjectTableRelation) throws ServiceException {
		try {
			subjectTableRelationDao.save(subjectTableRelation);
		}
		catch (DAOException e) {
			throw new ServiceException(e);
		}
	}

	public void updateSubjectTableRelation(SubjectTableRelation subjectTableRelation) throws ServiceException {
		try {
			SubjectTableRelation tmp = subjectTableRelationDao.get(subjectTableRelation.getId());
			ConvertUtil.convertToModelForUpdate(tmp, subjectTableRelation);			
			subjectTableRelationDao.update(tmp);
		}
		catch (DAOException e) {
			throw new ServiceException(e);
		}
		catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	public void removeSubjectTableRelation(Serializable id) throws ServiceException {
		try {
			subjectTableRelationDao.delete(id);
		}
		catch (DAOException e) {
			throw new ServiceException(e);
		}
	}
	public void removeSubjectTableRelationByTabId(String tabid) throws ServiceException {
		try{
			subjectTableRelationDao.deleteByField(SubjectTableRelation.PROP_DT_ID, Integer.valueOf(tabid));
		}catch (DAOException e) {
			throw new ServiceException(e);
		}
	}
	public void removeSubjectTableRelationBySubjectId(String subjectId) throws ServiceException {
		try{
			subjectTableRelationDao.deleteByField(SubjectTableRelation.PROP_SUBJECT_ID, Integer.valueOf(subjectId));
		}catch (DAOException e) {
			throw new ServiceException(e);
		}
	}

	public void removeSubjectTableRelations(Serializable[] ids) throws ServiceException {
		try {
			subjectTableRelationDao.deleteAll(ids);
		}
		catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	public PageQuery querySubjectTableRelation(PageQuery pageQuery) throws ServiceException {
		try {
			PageQuery  query=subjectTableRelationDao.queryBySelectId(pageQuery);
			pageQuery.setRecordSet(query.getRecordSet());
			pageQuery.setRecordCount(query.getRecordCount());
			return pageQuery;
		}
		catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	private SubjectTableRelationDao	subjectTableRelationDao;

	/**
	 * ×¢ÈëDAO
	 * 
	 * @see com.liusy.core.service.SubjectTableRelationService#setSubjectTableRelationDao(SubjectTableRelation
	 *      subjectTableRelationDao)
	 */
	public void setSubjectTableRelationDao(SubjectTableRelationDao subjectTableRelationDao) {
		this.subjectTableRelationDao = subjectTableRelationDao;
	}
	public List<SubjectTableRelation> findSubjectTableBySubjectId(String subjectId) throws ServiceException{
		try{
		//return subjectTableRelationDao.findByField(SubjectTableRelation.PROP_SUBJECT_ID, Integer.valueOf(subjectId));
			return subjectTableRelationDao.findByField(SubjectTableRelation.PROP_SUBJECT_ID, Integer.valueOf(subjectId),SubjectTableRelation.PROP_DT_ORDE,false);
		}catch (Exception e) {
			throw new ServiceException(e);
		}
	}
	
	public List<SubjectTableRelation> findSubjectTableByTableId(String tableId) throws ServiceException{
		try{
			return subjectTableRelationDao.findByField(SubjectTableRelation.PROP_DT_ID, Integer.valueOf(tableId));
		}catch (Exception e) {
			throw new ServiceException(e);
		}
	}
}

