package com.liusy.datapp.dao.query;

import java.io.Serializable;
import java.util.List;

import com.liusy.dataapplatform.base.dao.BaseDao;
import com.liusy.dataapplatform.base.exception.DAOException;

import com.liusy.datapp.model.query.SubjectColumnCfg;

public interface SubjectColumnCfgDao extends BaseDao {

	public SubjectColumnCfg cast(Object object);
	
	public SubjectColumnCfg get(Serializable id) throws DAOException;

	public SubjectColumnCfg load(Serializable id) throws DAOException;	
	
	public Serializable save(SubjectColumnCfg subjectColumnCfg) throws DAOException;

	public void saveOrUpdate(SubjectColumnCfg subjectColumnCfg) throws DAOException;

	public void update(SubjectColumnCfg subjectColumnCfg) throws DAOException;
	
	public void delete(Serializable id) throws DAOException;

	public void refresh(SubjectColumnCfg subjectColumnCfg) throws DAOException;
	
	public List<SubjectColumnCfg> findAllConfigByOrder(String subjectId,boolean isBatch) throws DAOException;
	
}
