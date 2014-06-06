package com.liusy.datapp.dao.system.org;

import java.io.Serializable;

import com.liusy.dataapplatform.base.dao.BaseDao;
import com.liusy.dataapplatform.base.exception.DAOException;
import com.liusy.datapp.model.system.org.SysUser;

/**
 * <p>
 * Title: ÐÅÏ¢Ëù
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Copyright: Copyright (c) 2009
 * </p>
 * <p>
 * Company: liusy
 * </p>
 * 
 * @author:
 * @version 1.0
 */
public interface SysUserDao extends BaseDao {

	public SysUser cast(Object object);

	public SysUser get(Serializable id) throws DAOException;

	public SysUser load(Serializable id) throws DAOException;

	public Serializable save(SysUser sysUser) throws DAOException;

	public void saveOrUpdate(SysUser sysUser) throws DAOException;

	public void update(SysUser sysUser) throws DAOException;

	public void delete(SysUser sysUser) throws DAOException;

	public void refresh(SysUser sysUser) throws DAOException;

}
