package com.liusy.analysismodel.log.service;

import java.util.List;

import com.liusy.analysismodel.log.model.log.LogonLog;



/**
 * 日志监控管理者
 * @author andy
 *
 */
public interface LogMonitorManager {
	
	
	/**
	 * 查询所有的登录日志
	 */
	public List queryAllLogon(String sql) throws Exception;
	
	
	/**
	 * 查询所有操作日志
	 */
	public List queryAllOperate(String sql) throws Exception;
	
	/**
	 * 根据条件查询登录日志
	 */
	public List queryLogonByCondi(String whereSql) throws Exception;
	
	/**
	 * 根据条件查询操作日志
	 */
	public List queryOperateByCondi(String whereSql) throws Exception;
	
	
	/**
	 * 通过主键查询登录日志 
	 */
	public LogonLog getLogonLog(java.io.Serializable id) throws Exception; 
	
	
    /**
     * 通过主键查询操作日志
     */
//	public LogOperate getLogOperate(java.io.Serializable id) throws Exception;
	
	/**
	 * count
	 */
	public int countLogon(String sql) throws Exception;
	
	
	/**
	 * 分页查询登录日志
	 */
	public List pageQueryLogon(String whereSql,int startRow,int pageSize) throws Exception;
	
	
}
