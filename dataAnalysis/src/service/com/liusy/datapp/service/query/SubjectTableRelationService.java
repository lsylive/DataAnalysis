package com.liusy.datapp.service.query;

import java.io.Serializable;
import java.util.List;

import com.liusy.dataapplatform.base.exception.ServiceException;
import com.liusy.dataapplatform.base.util.PageQuery;
import com.liusy.datapp.model.query.SubjectTableRelation;

public interface SubjectTableRelationService {

	public SubjectTableRelation findSubjectTableRelation(Serializable id) throws ServiceException;

	public void createSubjectTableRelation(SubjectTableRelation subjectTableRelation) throws ServiceException;

	public void updateSubjectTableRelation(SubjectTableRelation subjectTableRelation) throws ServiceException;

	public void removeSubjectTableRelation(Serializable id) throws ServiceException;

	public PageQuery querySubjectTableRelation(PageQuery pageQuery) throws ServiceException;

	public void removeSubjectTableRelations(Serializable[] ids) throws ServiceException;
	public List<SubjectTableRelation> findSubjectTableBySubjectId(String subjectId) throws ServiceException;
	public void removeSubjectTableRelationByTabId(String tabid) throws ServiceException;
	public void removeSubjectTableRelationBySubjectId(String subjectId) throws ServiceException;
	public List<SubjectTableRelation> findSubjectTableByTableId(String subjectId) throws ServiceException;
}

