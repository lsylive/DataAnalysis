package com.liusy.analysismodel.log.service.provider;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import com.liusy.analysismodel.util.DbConnectionManager;
import com.liusy.analysismodel.log.model.log.ImportLog2;

public class LogImportProvider2 {
   private static Log log = LogFactory.getLog(LogImportProvider2.class);

   public List<ImportLog2> selectLogTableInfo(String sql) throws Exception {
      Connection con = null;
      Statement stat = null;
      ResultSet rs = null;

      List<ImportLog2> logonList = new ArrayList<ImportLog2>();
      try {
         con = DbConnectionManager.getConnection();
         stat = con.createStatement();
         rs = stat.executeQuery(sql);
         while (rs.next()) {
            ImportLog2 logonLog = new ImportLog2();
            logonLog.setUserAccount(rs.getString(2));
            logonLog.setUserName(rs.getString(3));
            logonLog.setOrgName(rs.getString(4));
            logonLog.setDeptName(rs.getString(5));
            logonLog.setLogonTime(rs.getDate(6));
            logonLog.setIpAddress(rs.getString(7));
            logonLog.setLogonOutTime(rs.getDate(8));
            logonLog.setResult(rs.getString(9));
            logonLog.setOptType(rs.getString(10));
            logonLog.setOptTime(rs.getTimestamp(11));
            logonLog.setQueryCondi(rs.getString(12));
            logonLog.setOriginal(rs.getString(13));
            logonLog.setResName(rs.getString(14));
            logonList.add(logonLog);
         }
         con.commit();
      }
      catch (Exception e) {
         DbConnectionManager.rollBack(con);
         log.error(e);
         e.printStackTrace();
      }
      finally {
         DbConnectionManager.closeResultSet(rs);
         DbConnectionManager.closeStatement(stat);
      }
      return logonList;
   }

   public int selectCount(String sql) throws Exception {
      Connection con = null;
      Statement stat = null;
      ResultSet rs = null;

      int count = 0;
      try {
         con = DbConnectionManager.getConnection();
         stat = con.createStatement();
         rs = stat.executeQuery(sql);
         while (rs.next()) {
            count = rs.getInt(1);
         }
         con.commit();
      }
      catch (Exception e) {
         DbConnectionManager.rollBack(con);
         log.error(e);
         e.printStackTrace();
      }
      finally {
         DbConnectionManager.closeResultSet(rs);
         DbConnectionManager.closeStatement(stat);
      }
      return count;
   }

   public int excuteMethod(String sql) throws Exception {
      Connection con = null;
      Statement stat = null;
      ResultSet rs = null;
      int result = 0;

      try {
         con = DbConnectionManager.getConnection();
         stat = con.createStatement();
         result = stat.executeUpdate(sql);
         con.commit();
      }
      catch (Exception e) {
         DbConnectionManager.rollBack(con);
         log.error(e);
         e.printStackTrace();
      }
      finally {
         DbConnectionManager.closeResultSet(rs);
         DbConnectionManager.closeStatement(stat);
      }
      return result;
   }
}
