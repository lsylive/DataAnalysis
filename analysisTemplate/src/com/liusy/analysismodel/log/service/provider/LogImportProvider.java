package com.liusy.analysismodel.log.service.provider;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.liusy.analysis.template.model.util.StringUtil;
import com.liusy.analysismodel.util.DbConnectionManager;
import com.liusy.analysismodel.log.dao.LogImportSql;
import com.liusy.analysismodel.log.dao.formBean.LogImportBean;
import com.liusy.analysismodel.log.service.LogImportManager;
import com.liusy.analysismodel.util.Conversion;
import com.liusy.analysismodel.util.DateUtil;

public class LogImportProvider implements LogImportManager {
   private LogImportSql sqlBuilder;
   private static Log   log = LogFactory.getLog(LogImportProvider.class);

   public void insertLogTableInfo(Object[] array) throws Exception {
      Connection con = null;
      Statement stat = null;
      ResultSet rs = null;

      sqlBuilder = new LogImportSql();
      String dateSql = sqlBuilder.getExitLatestTableName();
      String latestTableName = "";
      try {
         con = DbConnectionManager.getConnection();
         stat = con.createStatement();
         rs = stat.executeQuery(dateSql);

         while (rs.next()) {
            // Get the latest existent table name.
            latestTableName = rs.getString(1);
         }
         if ("".equals(latestTableName)) {
            // first time to merge data.
            LogImportBean logBean = (LogImportBean) array[0];
            String toDate = logBean.getToDate();
            String tableName = logBean.getTableName();
            String createTableSql = sqlBuilder.getCreateTableSql(tableName);
            //				ResultSet result = 

            int result = stat.executeUpdate(createTableSql);
            if (result >= 0) {
               // start to merge data.
               String insertSql1 = sqlBuilder.getInsertDataSqlLogon(logBean);
               String insertSql2 = sqlBuilder.getInsertDataSqlOperator(logBean);
               stat.executeUpdate(insertSql1);
               stat.executeUpdate(insertSql2);
            }

         }
         else {
            // Not first time to import data into Log table.
            LogImportBean logBean = (LogImportBean) array[0];
            String insertSql1 = sqlBuilder.getInsertDataSqlLogon(logBean);
            String insertSql2 = sqlBuilder.getInsertDataSqlOperator(logBean);
            stat.execute(insertSql1);
            stat.execute(insertSql2);
            for (int j = 1; j < array.length; j++) {
               logBean = (LogImportBean) array[j];
               String tableName = logBean.getTableName();
               String createTableSql = sqlBuilder.getCreateTableSql(tableName);
               int result = stat.executeUpdate(createTableSql);
               if (result >= 0) {
                  // start to merge data.
                  insertSql1 = sqlBuilder.getInsertDataSqlLogon(logBean);
                  insertSql2 = sqlBuilder.getInsertDataSqlOperator(logBean);
                  stat.execute(insertSql1);
                  stat.execute(insertSql2);
               }
            }
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
   }

   public List<LogImportBean> queryLogTableInfo() {
      Connection con = null;
      Statement stat = null;
      ResultSet rs = null;

      sqlBuilder = new LogImportSql();
      String dateSql = sqlBuilder.getSysTiemSql();
      Date nowDate = null;
      String exitLatestTableName = "";
      try {
         con = DbConnectionManager.getConnection();
         stat = con.createStatement();
         rs = stat.executeQuery(dateSql);
         while (rs.next()) {
            nowDate = rs.getTimestamp(1);
         }
         String tableNameSql = sqlBuilder.getExitLatestTableName();
         rs = stat.executeQuery(tableNameSql);
         while (rs.next()) {
            exitLatestTableName = rs.getString(1);

         }
         //**********
         //			nowDate =DateUtil.getNow();
         //********
         List<LogImportBean> tableList = getTableNameList(exitLatestTableName, nowDate);
         if (tableList != null && !tableList.isEmpty()) { return tableList; }
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
      return null;
   }

   public List<LogImportBean> getTableNameList(String tableName, Date nowDate) throws Exception {
      List<LogImportBean> tableNameList = new ArrayList<LogImportBean>();
      String seasonInfo = "";
      if ("".equals(tableName)) {
         int year = getYear(nowDate);
         int season = getSeanson(nowDate);
         LogImportBean logBean = new LogImportBean();
         String start = "";
         String end = getEndOfSeason(year, season);
         if (Conversion.compareAfter(end, nowDate)) {
            logBean.setToDate(Conversion.getUtilDateToString(nowDate, StringUtil.dateTimeFormat));
         }
         else {
            logBean.setToDate(end);
         }
         logBean.setFromDate(start);

         tableName = StringUtil.tableName + String.valueOf(year) + String.valueOf(season);
         logBean.setTableName(tableName);
         tableNameList.add(logBean);
         return tableNameList;
      }
      else {
         int index = StringUtil.tableName.length();
         seasonInfo = tableName.substring(index, tableName.length());
         int oldYear = Integer.valueOf(seasonInfo.substring(0, 4));
         int oldSeason = Integer.valueOf(seasonInfo.substring(4));
         int hightDif = 0;
         int lowdDif = 0;
         int year = getYear(nowDate);
         int season = getSeanson(nowDate);
         hightDif = year - oldYear;
         lowdDif = season - oldSeason;
         int num = hightDif * 4 + lowdDif;
         if (num < 0) {
            return null;
         }
         else if (num == 0) {
            LogImportBean logBean = new LogImportBean();
            //The latest existed date between LogonLog and SystemOperator table.
            String start = getLatestDateOfLog(tableName);
            //The anding date of this season.
            String end = getEndOfSeason(oldYear, oldSeason);
            if (Conversion.compareAfter(end, nowDate)) {
               logBean.setToDate(Conversion.getUtilDateToString(nowDate, StringUtil.dateTimeFormat));
            }
            else {
               logBean.setToDate(end);
            }
            logBean.setFromDate(start);
            //				logBean.setToDate(end);
            logBean.setTableName(tableName);
            tableNameList.add(logBean);
            return tableNameList;
         }
         else {
            LogImportBean logBean = new LogImportBean();
            String start = getLatestDateOfLog(tableName);
            String end = getEndOfSeason(oldYear, oldSeason);
            if (Conversion.compareAfter(end, nowDate)) {
               logBean.setToDate(Conversion.getUtilDateToString(nowDate, StringUtil.dateTimeFormat));
            }
            else {
               logBean.setToDate(end);
            }
            logBean.setFromDate(start);
            //				logBean.setToDate(end);
            logBean.setTableName(tableName);
            tableNameList.add(logBean);
            for (int i = 0; i < num; i++) {
               LogImportBean logBean2 = new LogImportBean();
               if (oldSeason == 4) {
                  oldSeason = 1;
                  oldYear += 1;
               }
               else {
                  oldSeason += 1;
               }
               start = getStartOfSeason(oldYear, oldSeason);
               end = getEndOfSeason(oldYear, oldSeason);
               if (Conversion.compareAfter(end, nowDate)) {
                  logBean2.setToDate(Conversion.getUtilDateToString(nowDate, StringUtil.dateTimeFormat));
               }
               else {
                  logBean2.setToDate(end);
               }
               logBean2.setFromDate(start);
               //					logBean.setToDate(end);
               logBean2.setTableName(StringUtil.tableName + String.valueOf(oldYear) + String.valueOf(oldSeason));
               tableNameList.add(logBean2);
            }
            return tableNameList;
         }

      }

   }

   public String getLatestDateOfLog(String tableName) throws Exception {
      Connection con = null;
      Statement stat = null;
      ResultSet rs = null;
      Date date1 = null;
      Date date2 = null;

      try {
         sqlBuilder = new LogImportSql();
         String dateSql1 = sqlBuilder.getLatestDateOfSqlLog(tableName);
         String dateSql2 = sqlBuilder.getLatestDateOfSqlOperator(tableName);
         con = DbConnectionManager.getConnection();
         stat = con.createStatement();
         rs = stat.executeQuery(dateSql1);
         while (rs.next()) {
            date1 = rs.getTimestamp(1);
         }
         rs = stat.executeQuery(dateSql2);
         while (rs.next()) {
            date2 = rs.getTimestamp(1);
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

      if (date1 == null && date2 != null) {
         return Conversion.getUtilDateToString(date2, StringUtil.dateFormat);
      }
      else if (date1 != null && date2 == null) {
         return Conversion.getUtilDateToString(date1, StringUtil.dateFormat);
      }
      else {
         if (date1.after(date2)) {
            return Conversion.getUtilDateToString(date1, StringUtil.dateTimeFormat);
         }
         else {
            return Conversion.getUtilDateToString(date2, StringUtil.dateTimeFormat);
         }
      }
   }

   public String getStartOfSeason(int year, int season) {
      String month = "";
      switch (season) {
      case 1:
         month = "01";
         break;
      case 2:
         month = "04";
         break;
      case 3:
         month = "07";
         break;
      case 4:
         month = "10";
         break;
      default:
         month = "01";
         break;
      }
      String day = "01";
      return String.valueOf(year) + "-" + month + "-" + day + " 00:00:00";
   }

   public String getEndOfSeason(int year, int season) {
      String month = "";
      switch (season) {
      case 1:
         month = "03";
         break;
      case 2:
         month = "06";
         break;
      case 3:
         month = "09";
         break;
      case 4:
         month = "12";
         break;
      default:
         month = "03";
         break;
      }
      String day = "01";
      Date date1 = null;
      String date = String.valueOf(year) + "-" + month + "-" + day;
      date1 = Conversion.getObjectToUtilDate(date);
      return Conversion.getUtilDateToString(DateUtil.getThisMonthLastDay(date1), StringUtil.dateFormat) + StringUtil.timeEndTag;
   }

   public int getYear(Date date) {
      Calendar calendar = Calendar.getInstance();
      calendar.clear();
      calendar.setTime(date);
      return calendar.get(Calendar.YEAR);
   }

   public int getSeanson(Date date) {
      Calendar calendar = Calendar.getInstance();
      calendar.clear();
      calendar.setTime(date);
      int month = calendar.get(Calendar.MONTH) + 1;
      int season = 0;
      if (1 <= month && month <= 3) {
         season = 1;
      }
      if (4 <= month && month <= 6) {
         season = 2;
      }
      if (7 <= month && month <= 9) {
         season = 3;
      }
      if (10 <= month && month <= 12) {
         season = 4;
      }
      return season;

   }

   public String getTableName(Date date) {
      Calendar calendar = Calendar.getInstance();
      calendar.clear();
      calendar.setTime(date);
      int year = calendar.get(Calendar.YEAR);
      int month = calendar.get(Calendar.MONTH);
      int season = getSeanson(date);
      return StringUtil.tableName + String.valueOf(year) + String.valueOf(season);
   }

}
