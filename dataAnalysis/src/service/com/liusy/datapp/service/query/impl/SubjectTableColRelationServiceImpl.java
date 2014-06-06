package com.liusy.datapp.service.query.impl;

import java.io.Serializable;

import com.liusy.dataapplatform.base.exception.DAOException;
import com.liusy.dataapplatform.base.exception.ServiceException;
import com.liusy.dataapplatform.base.util.PageQuery;
import com.liusy.datapp.dao.query.SubjectTableColRelationDao;
import com.liusy.datapp.model.query.SubjectTableColRelation;
import com.liusy.datapp.service.query.SubjectTableColRelationService;

public class SubjectTableColRelationServiceImpl implements SubjectTableColRelationService {
	private static final long serialVersionUID = 1L;

	public SubjectTableColRelation findSubjectTableColRelation(Serializable id) throws ServiceException {
		try {
			return subjectTableColRelationDao.get(id);
		}
		catch (DAOException e) {
			throw new ServiceException(e);
		}
	}

	public void createSubjectTableColRelation(SubjectTableColRelation subjectTableColRelation) throws ServiceException {
		try {
			subjectTableColRelationDao.save(subjectTableColRelation);
		}
		catch (DAOException e) {
			throw new ServiceException(e);
		}
	}

	public void updateSubjectTableColRelation(SubjectTableColRelation subjectTableColRelation) throws ServiceException {
		try {
			
			subjectTableColRelationDao.update(subjectTableColRelation);
		}
		catch (DAOException e) {
			throw new ServiceException(e);
		}
		catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	public void removeSubjectTableColRelation(Serializable id) throws ServiceException {
		try {
			subjectTableColRelationDao.delete(id);
		}
		catch (DAOException e) {
			throw new ServiceException(e);
		}
	}

	public void removeSubjectTableColRelations(Serializable[] ids) throws ServiceException {
		try {
			subjectTableColRelationDao.deleteAll(ids);
		}
		catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	public PageQuery querySubjectTableColRelation(PageQuery pageQuery) throws ServiceException {
		try {
			PageQuery  query=subjectTableColRelationDao.queryBySelectId(pageQuery);
			pageQuery.setRecordSet(query.getRecordSet());
			pageQuery.setRecordCount(query.getRecordCount());
			return pageQuery;
		}
		catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	private SubjectTableColRelationDao	subjectTableColRelationDao;

	/**
	 * ×¢ÈëDAO
	 * 
	 * @see com.liusy.core.service.SubjectTableColRelationService#setSubjectTableColRelationDao(SubjectTableColRelation
	 *      subjectTableColRelationDao)
	 */
	public void setSubjectTableColRelationDao(SubjectTableColRelationDao subjectTableColRelationDao) {
		this.subjectTableColRelationDao = subjectTableColRelationDao;
	}
}

