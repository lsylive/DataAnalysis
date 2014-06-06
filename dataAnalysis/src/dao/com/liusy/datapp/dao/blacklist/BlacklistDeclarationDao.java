package com.liusy.datapp.dao.blacklist;

import java.io.Serializable;

import com.liusy.dataapplatform.base.dao.BaseDao;
import com.liusy.dataapplatform.base.exception.DAOException;

import com.liusy.datapp.model.blacklist.BlacklistDeclaration;

public interface BlacklistDeclarationDao extends BaseDao {

	public BlacklistDeclaration cast(Object object);
	
	public BlacklistDeclaration get(Serializable id) throws DAOException;

	public BlacklistDeclaration load(Serializable id) throws DAOException;	
	
	public Serializable save(BlacklistDeclaration blacklistDeclaration) throws DAOException;

	public void saveOrUpdate(BlacklistDeclaration blacklistDeclaration) throws DAOException;

	public void update(BlacklistDeclaration blacklistDeclaration) throws DAOException;
	
	public void delete(Serializable id) throws DAOException;

	public void refresh(BlacklistDeclaration blacklistDeclaration) throws DAOException;	
	
}
