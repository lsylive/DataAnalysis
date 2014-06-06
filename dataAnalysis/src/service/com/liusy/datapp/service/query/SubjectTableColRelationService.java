package com.liusy.datapp.service.query;

import java.io.Serializable;

import com.liusy.dataapplatform.base.exception.ServiceException;
import com.liusy.dataapplatform.base.util.PageQuery;
import com.liusy.datapp.model.query.SubjectTableColRelation;

public interface SubjectTableColRelationService {

	public SubjectTableColRelation findSubjectTableColRelation(Serializable id) throws ServiceException;

	public void createSubjectTableColRelation(SubjectTableColRelation subjectTableColRelation) throws ServiceException;

	public void updateSubjectTableColRelation(SubjectTableColRelation subjectTableColRelation) throws ServiceException;

	public void removeSubjectTableColRelation(Serializable id) throws ServiceException;

	public PageQuery querySubjectTableColRelation(PageQuery pageQuery) throws ServiceException;

	public void removeSubjectTableColRelations(Serializable[] ids) throws ServiceException;  
}

