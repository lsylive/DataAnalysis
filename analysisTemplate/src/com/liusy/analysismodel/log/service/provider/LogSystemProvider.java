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
import com.liusy.analysismodel.log.model.log.LogSystem;
import com.liusy.analysismodel.log.model.log.LogonLog;
import com.liusy.analysismodel.log.service.LogMonitorManager;

public class LogSystemProvider implements LogMonitorManager {
   private Connection con;
   private Statement  stat;
   private ResultSet  rs;
   private static Log log = LogFactory.getLog(LogSystemProvider.class);

   //	public LogOperate getLogOperate(Serializable id) throws Exception {}

   public LogonLog getLogonLog(Serializable id) throws Exception {

      LogonLog logonLog = new LogonLog();
      try {
         con = DbConnectionManager.getConnection();
         stat = con.createStatement();
         String sql = "select * from T_SYS_LOG_LOGON where ID=" + id;
         rs = stat.executeQuery(sql);
         while (rs.next()) {
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
      }
      catch (Exception e) {
         log.error(e);
         throw e;
      }
      finally {
         DbConnectionManager.closeResultSet(rs);
         DbConnectionManager.closeStatement(stat);
      }
      return logonLog;
   }

   public List queryAllLogon(String sql) throws Exception {
      List<LogonLog> logonList = new ArrayList<LogonLog>();
      try {
         con = DbConnectionManager.getConnection();
         stat = con.createStatement();
         rs = stat.executeQuery(sql);
         while (rs.next()) {
            LogonLog logonLog = new LogonLog();
            logonLog.setId(rs.getInt(2));
            logonLog.setUserAccount(rs.getString(3));
            logonLog.setUserName(rs.getString(4));
            logonLog.setOrgId(rs.getInt(5));
            logonLog.setDeptId(rs.getInt(6));
            logonLog.setOrgName(rs.getString(7));
            logonLog.setDeptName(rs.getString(8));
            logonLog.setLogonTime(rs.getDate(9));
            logonLog.setIpAddress(rs.getString(10));
            logonLog.setLogonOutTime(rs.getDate(11));
            logonLog.setResult(rs.getString(12));
            logonList.add(logonLog);
         }
      }
      catch (Exception e) {
         log.error(e);
         throw e;
      }
      finally {
         DbConnectionManager.closeResultSet(rs);
         DbConnectionManager.closeStatement(stat);
      }
      return logonList;
   }

   public List queryAllOperate(String sql) throws Exception {
      List<LogSystem> LogOperList = new ArrayList<LogSystem>();
      try {
         con = DbConnectionManager.getConnection();
         stat = con.createStatement();
         rs = stat.executeQuery(sql);
         while (rs.next()) {
            LogSystem logonLog = new LogSystem();
            //				logonLog.setId(rs.getInt(2));
            logonLog.setLogLevel(rs.getString(2));
            logonLog.setLogTime(rs.getDate(3));
            logonLog.setLogClass(rs.getString(4));
            logonLog.setLogMessage(rs.getString(5));
            LogOperList.add(logonLog);
         }
      }
      catch (Exception e) {
         log.error(e);
         throw e;
      }
      finally {
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

   public int countLogon(String sql) throws Exception {
      try {
         con = DbConnectionManager.getConnection();
         stat = con.createStatement();
         rs = stat.executeQuery(sql);
         if (rs.next()) {
            int count = rs.getInt(1);
            return count;
         }
         else {
            return 0;
         }

      }
      catch (Exception e) {
         log.error(e);
         throw e;
      }
      finally {
         DbConnectionManager.closeResultSet(rs);
         DbConnectionManager.closeStatement(stat);
      }
   }

   public List pageQueryLogon(String whereSql, int startRow, int pageSize) throws Exception {
      return null;
   }
}
