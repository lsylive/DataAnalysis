package com.liusy.analysismodel.log.service.provider;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import com.liusy.analysismodel.util.DbConnectionManager;
import com.liusy.analysismodel.log.model.log.LogOperate;
import com.liusy.analysismodel.log.model.log.LogonLog;
import com.liusy.analysismodel.log.service.LogMonitorManager;

public class LogMonitorProvider implements LogMonitorManager {
	private Connection con;
	private Statement stat;
	private ResultSet rs;
	private static Log log=LogFactory.getLog(LogMonitorProvider.class);
//	public LogOperate getLogOperate(Serializable id) throws Exception {}

	
	public LogonLog getLogonLog(Serializable id) throws Exception {

		LogonLog logonLog = new LogonLog();
		try{
			con = DbConnectionManager.getConnection();
			stat =con.createStatement();
			String sql = "select * from T_SYS_LOG_LOGON where ID="+id;
			rs =stat.executeQuery(sql);
			while(rs.next()){
				logonLog.setId(rs.getInt("ID"));
				logonLog.setUserAccount(rs.getString("USER_ACCOUNT"));
				logonLog.setUserName(rs.getString("USER_NAME"));
				logonLog.setOrgId(rs.getInt("ORG_ID"));
				logonLog.setDeptId(rs.getInt("DEPT_ID"));
				logonLog.setOrgName(rs.getString("ORG_NAME"));
				logonLog.setDeptName(rs.getString("DEPT_NAME"));
				logonLog.setLogonTime(rs.getDate("LOGON_TIME"));
				logonLog.setIpAddress(rs.getString("IP_ADDRESS"));
				logonLog.setLogonOutTime(rs.getDate("LOGOUT_TIME"));
				logonLog.setResult(rs.getString("LOGON_RESULT"));
			}
		}catch(Exception e) {
			log.error(e);
			throw e;
		}finally{
			DbConnectionManager.closeResultSet(rs);
			DbConnectionManager.closeStatement(stat);
		}
		return logonLog;
	}

	
	public List<LogonLog> queryAllLogon(String sql) throws Exception {
		List<LogonLog> logonList = new ArrayList<LogonLog>();
		try{
			con = DbConnectionManager.getConnection();
			stat =con.createStatement();
			rs =stat.executeQuery(sql);
			while(rs.next()){
				LogonLog logonLog = new LogonLog();
				logonLog.setUserAccount(rs.getString(2));
				logonLog.setUserName(rs.getString(3));
				logonLog.setOrgName(rs.getString(6));
				logonLog.setDeptName(rs.getString(7));
				logonLog.setLogonTime(rs.getDate(8));
				logonLog.setIpAddress(rs.getString(9));
				logonLog.setLogonOutTime(rs.getDate(10));
				logonLog.setResult(rs.getString(11));
				logonList.add(logonLog);
			}
		}catch(Exception e) {
			log.error(e);
			throw e;
		}finally{
			DbConnectionManager.closeResultSet(rs);
			DbConnectionManager.closeStatement(stat);
		}
		return logonList;
	}

	
	public List<LogOperate> queryAllOperate(String sql) throws Exception {
		List<LogOperate> LogOperList = new ArrayList<LogOperate>();
		try{
			con = DbConnectionManager.getConnection();
			stat =con.createStatement();
			rs =stat.executeQuery(sql);
			while(rs.next()){
				LogOperate logonLog = new LogOperate();
				logonLog.setUserAccount(rs.getString(2));
				logonLog.setUserName(rs.getString(3));
				logonLog.setOrgName(rs.getString(4));
				logonLog.setDeptName(rs.getString(5));
				logonLog.setOptType(rs.getString(6));
				logonLog.setIpAddress(rs.getString(7));
				logonLog.setOptTime(rs.getTimestamp(8));
				logonLog.setQueryCondi(rs.getString(9));
				logonLog.setResName(rs.getString(10));
				LogOperList.add(logonLog);
			}
		}catch(Exception e) {
			log.error(e);
			throw e;
		}finally{
			DbConnectionManager.closeResultSet(rs);
			DbConnectionManager.closeStatement(stat);
		}
		return LogOperList;
	
	}

	
	public List queryLogonByCondi(String whereSql) throws Exception {
		
		return null;
	}

	
	public List queryOperateByCondi(String whereSql) throws Exception {
		
		return null;
	}
	
	public int countLogon(String sql) throws Exception{
		try{
			con = DbConnectionManager.getConnection();
			stat =con.createStatement();
			rs =stat.executeQuery(sql);
			if (rs.next()) {
				int count = rs.getInt(1);
				return count;
			}
			else {
				return 0;
			}
			
		}catch(Exception e) {
			log.error(e);
			throw e;
		}finally{
			DbConnectionManager.closeResultSet(rs);
			DbConnectionManager.closeStatement(stat);
		}
	}
	public String[] getOraNameArray(String sql) {
		List<String> results = new ArrayList<String>();
		try{
			con = DbConnectionManager.getConnection();
			stat =con.createStatement();
			rs =stat.executeQuery(sql);
			while (rs.next()) {
				results.add(rs.getString(1));
			}
			results.add(0, "");
			return (String[]) results.toArray(new String[results.size()]);
		}catch(Exception e) {
			log.error(e);
		}finally{
			DbConnectionManager.closeResultSet(rs);
			DbConnectionManager.closeStatement(stat);
		}
		return null;
	}
	
	public List pageQueryLogon(String whereSql,int startRow,int pageSize) throws Exception{
		return null;
	}
}
