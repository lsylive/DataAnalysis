package com.liusy.datapp.dao.compare;

import java.io.Serializable;

import com.liusy.dataapplatform.base.dao.BaseDao;
import com.liusy.dataapplatform.base.exception.DAOException;

import com.liusy.datapp.model.compare.CompareIndicator;

public interface CompareIndicatorDao extends BaseDao {

	public CompareIndicator cast(Object object);
	
	public CompareIndicator get(Serializable id) throws DAOException;

	public CompareIndicator load(Serializable id) throws DAOException;	
	
	public Serializable save(CompareIndicator compareIndicator) throws DAOException;

	public void saveOrUpdate(CompareIndicator compareIndicator) throws DAOException;

	public void update(CompareIndicator compareIndicator) throws DAOException;
	
	public void delete(Serializable id) throws DAOException;

	public void refresh(CompareIndicator compareIndicator) throws DAOException;	
	
}
