package com.liusy.analysismodel.log.service;

import java.util.List;

import com.liusy.analysismodel.log.model.log.LogonLog;



/**
 * ��־��ع�����
 * @author andy
 *
 */
public interface LogMonitorManager {
	
	
	/**
	 * ��ѯ���еĵ�¼��־
	 */
	public List queryAllLogon(String sql) throws Exception;
	
	
	/**
	 * ��ѯ���в�����־
	 */
	public List queryAllOperate(String sql) throws Exception;
	
	/**
	 * ����������ѯ��¼��־
	 */
	public List queryLogonByCondi(String whereSql) throws Exception;
	
	/**
	 * ����������ѯ������־
	 */
	public List queryOperateByCondi(String whereSql) throws Exception;
	
	
	/**
	 * ͨ��������ѯ��¼��־ 
	 */
	public LogonLog getLogonLog(java.io.Serializable id) throws Exception; 
	
	
    /**
     * ͨ��������ѯ������־
     */
//	public LogOperate getLogOperate(java.io.Serializable id) throws Exception;
	
	/**
	 * count
	 */
	public int countLogon(String sql) throws Exception;
	
	
	/**
	 * ��ҳ��ѯ��¼��־
	 */
	public List pageQueryLogon(String whereSql,int startRow,int pageSize) throws Exception;
	
	
}
