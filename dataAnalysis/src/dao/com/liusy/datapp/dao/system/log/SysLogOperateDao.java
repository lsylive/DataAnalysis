package com.liusy.datapp.dao.system.log;

import java.io.Serializable;

import com.liusy.dataapplatform.base.dao.BaseDao;
import com.liusy.dataapplatform.base.exception.DAOException;

import com.liusy.datapp.model.system.log.SysLogOperate;

public interface SysLogOperateDao extends BaseDao {

	public SysLogOperate cast(Object object);
	
	public SysLogOperate get(Serializable id) throws DAOException;

	public SysLogOperate load(Serializable id) throws DAOException;	
	
	public Serializable save(SysLogOperate sysLogOperate) throws DAOException;

	public void saveOrUpdate(SysLogOperate sysLogOperate) throws DAOException;

	public void update(SysLogOperate sysLogOperate) throws DAOException;
	
	public void delete(Serializable id) throws DAOException;

	public void refresh(SysLogOperate sysLogOperate) throws DAOException;	
	
}
