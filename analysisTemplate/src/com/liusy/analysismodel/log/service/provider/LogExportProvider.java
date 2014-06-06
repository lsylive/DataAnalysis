package com.liusy.analysismodel.log.service.provider;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import com.liusy.analysismodel.log.dao.LogExportSql;
import com.liusy.analysismodel.log.model.log.ExportLog;
import com.liusy.analysismodel.util.DbConnectionManager;

public class LogExportProvider {
   static private Connection con;
   private Statement         stat;
   private ResultSet         rs;
   private LogExportSql      sqlBuilder;
   private static Log        log = LogFactory.getLog(LogExportProvider.class);

   public void insertLogTableInfo(Object[] array) throws Exception {
   }

   public List queryLogTableInfo(String sql) throws Exception {

      List<ExportLog> logonList = new ArrayList<ExportLog>();
      try {
         con = DbConnectionManager.getConnection();
         stat = con.createStatement();
         rs = stat.executeQuery(sql);
         while (rs.next()) {
            ExportLog logonLog = new ExportLog();
            logonLog.setId(rs.getInt(1));
            logonLog.setUserAccount(rs.getString(2));
            logonLog.setUserName(rs.getString(3));
            logonLog.setOrgId(rs.getInt(4));
            logonLog.setDeptId(rs.getInt(5));
            logonLog.setOrgName(rs.getString(6));
            logonLog.setDeptName(rs.getString(7));
            logonLog.setLogonTime(rs.getDate(8));
            logonLog.setIpAddress(rs.getString(9));
            logonLog.setLogonOutTime(rs.getDate(10));
            logonLog.setResult(rs.getString(11));
            logonLog.setResId(rs.getInt(12));
            logonLog.setOptType(rs.getString(13));
            logonLog.setOptTime(rs.getTimestamp(14));
            logonLog.setQueryCondi(rs.getString(15));
            logonLog.setOriginal(rs.getString(16));
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

   public int countLogon(String sql) throws Exception {
      try {
         con = DbConnectionManager.getConnection();
         stat = con.createStatement();
         rs = stat.executeQuery(sql);
         int count = 0;
         if (rs.next()) count = rs.getInt(1);
         return count;

      }
      catch (Exception e) {
         DbConnectionManager.rollBack(con);
         log.error(e);
         throw e;
      }
      finally {
         DbConnectionManager.closeResultSet(rs);
         DbConnectionManager.closeStatement(stat);
      }
   }

   public int excuteMethod(String sql) throws Exception {
      try {
         con = DbConnectionManager.getConnection();
         stat = con.createStatement();
         int result = stat.executeUpdate(sql);
         con.commit();
         return result;
      }
      catch (Exception e) {
         DbConnectionManager.rollBack(con);
         log.error(e);
         e.printStackTrace();
      }
      finally {
         DbConnectionManager.closeStatement(stat);
      }
      return 0;
   }

   public int excuteDelete(String sql1, String sql2) throws Exception {
      try {
         con = DbConnectionManager.getConnection();
         stat = con.createStatement();
         int result1 = stat.executeUpdate(sql1);
         int result2 = stat.executeUpdate(sql2);

         con.commit();
         return result1 + result2;
      }
      catch (Exception e) {
         DbConnectionManager.rollBack(con);
         log.error(e);
         e.printStackTrace();
      }
      finally {
         DbConnectionManager.closeStatement(stat);
      }
      return 0;
   }

}
