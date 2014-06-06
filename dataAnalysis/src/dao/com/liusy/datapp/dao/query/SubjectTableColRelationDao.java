package com.liusy.datapp.dao.query;

import java.io.Serializable;

import com.liusy.dataapplatform.base.dao.BaseDao;
import com.liusy.dataapplatform.base.exception.DAOException;

import com.liusy.datapp.model.query.SubjectTableColRelation;

public interface SubjectTableColRelationDao extends BaseDao {

	public SubjectTableColRelation cast(Object object);
	
	public SubjectTableColRelation get(Serializable id) throws DAOException;

	public SubjectTableColRelation load(Serializable id) throws DAOException;	
	
	public Serializable save(SubjectTableColRelation subjectTableColRelation) throws DAOException;

	public void saveOrUpdate(SubjectTableColRelation subjectTableColRelation) throws DAOException;

	public void update(SubjectTableColRelation subjectTableColRelation) throws DAOException;
	
	public void delete(Serializable id) throws DAOException;

	public void refresh(SubjectTableColRelation subjectTableColRelation) throws DAOException;	
	
}
