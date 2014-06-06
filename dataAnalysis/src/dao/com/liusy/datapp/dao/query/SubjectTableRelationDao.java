package com.liusy.datapp.dao.query;

import java.io.Serializable;

import com.liusy.dataapplatform.base.dao.BaseDao;
import com.liusy.dataapplatform.base.exception.DAOException;

import com.liusy.datapp.model.query.SubjectTableRelation;

public interface SubjectTableRelationDao extends BaseDao {

	public SubjectTableRelation cast(Object object);
	
	public SubjectTableRelation get(Serializable id) throws DAOException;

	public SubjectTableRelation load(Serializable id) throws DAOException;	
	
	public Serializable save(SubjectTableRelation subjectTableRelation) throws DAOException;

	public void saveOrUpdate(SubjectTableRelation subjectTableRelation) throws DAOException;

	public void update(SubjectTableRelation subjectTableRelation) throws DAOException;
	
	public void delete(Serializable id) throws DAOException;

	public void refresh(SubjectTableRelation subjectTableRelation) throws DAOException;	
	
}
