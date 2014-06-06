package com.liusy.datapp.dao.compare;

import java.io.Serializable;

import com.liusy.dataapplatform.base.dao.BaseDao;
import com.liusy.dataapplatform.base.exception.DAOException;

import com.liusy.datapp.model.compare.CompareTableRelation;

public interface CompareTableRelationDao extends BaseDao {

	public CompareTableRelation cast(Object object);
	
	public CompareTableRelation get(Serializable id) throws DAOException;

	public CompareTableRelation load(Serializable id) throws DAOException;	
	
	public Serializable save(CompareTableRelation compareTableRelation) throws DAOException;

	public void saveOrUpdate(CompareTableRelation compareTableRelation) throws DAOException;

	public void update(CompareTableRelation compareTableRelation) throws DAOException;
	
	public void delete(Serializable id) throws DAOException;

	public void refresh(CompareTableRelation compareTableRelation) throws DAOException;	
	
}
