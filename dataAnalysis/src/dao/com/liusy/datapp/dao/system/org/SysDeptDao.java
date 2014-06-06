package com.liusy.datapp.dao.system.org;

import java.io.Serializable;

import com.liusy.dataapplatform.base.dao.BaseDao;
import com.liusy.dataapplatform.base.exception.DAOException;

import com.liusy.datapp.model.system.org.SysDept;

public interface SysDeptDao extends BaseDao {

	public SysDept cast(Object object);
	
	public SysDept get(Serializable id) throws DAOException;

	public SysDept load(Serializable id) throws DAOException;	
	
	public Serializable save(SysDept sysDept) throws DAOException;

	public void saveOrUpdate(SysDept sysDept) throws DAOException;

	public void update(SysDept sysDept) throws DAOException;
	
	public void delete(Serializable id) throws DAOException;

	public void refresh(SysDept sysDept) throws DAOException;	
	
}
