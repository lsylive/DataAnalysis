package com.liusy.analysismodel.log.dao;

import com.liusy.analysismodel.log.dao.formBean.LogImportBean;
import com.liusy.analysis.template.model.util.StringUtil;

public class LogImportSql {
   public static String tableName = "LogTables";

   public String getCreateTableSql(String tableName) {
      StringBuilder buf = new StringBuilder();
      buf.append("create table ");
      buf.append(tableName);
      //		buf.append(String.valueOf(year));
      //		buf.append(String.valueOf(season));
      buf.append(" (ID NUMBER(*,0) NOT NULL ENABLE,");
      buf.append("USER_ACCOUNT VARCHAR2(64),");
      buf.append("USER_NAME VARCHAR2(16), ");
      buf.append("ORG_ID NUMBER(*,0), ");
      buf.append("DEPT_ID NUMBER(*,0), ");
      buf.append("ORG_NAME VARCHAR2(128),  ");
      buf.append("DEPT_NAME VARCHAR2(64), ");
      buf.append("LOGON_TIME DATE,  ");
      buf.append("IP_ADDRESS VARCHAR2(32), ");
      buf.append("LOGOUT_TIME DATE,  ");
      buf.append("LOGON_RESULT VARCHAR2(32),  ");
      buf.append("RES_ID NUMBER(*,0),  ");
      buf.append("OPT_TYPE CHAR(2), ");
      buf.append("OPT_TIME DATE, ");
      buf.append("QUERY_CONDITION VARCHAR2(4000))");
      //		buf.append("CONSTRAINT PK_T_SYS_LOG_LOGON PRIMARY KEY (ID) )");
      return buf.toString();
   }

   public String getExitLatestTableName() {
      StringBuilder buf = new StringBuilder();
      buf.append("select * ");
      buf.append("from ");
      buf.append("(SELECT TABLE_NAME ");
      buf.append("FROM  ");
      buf.append("DBA_TABLES  ");
      buf.append("WHERE  ");
      buf.append("TABLE_NAME like" + "'" + StringUtil.tableName + "%" + "'");
      buf.append("order by ");
      buf.append("TABLE_NAME desc)");
      buf.append("WHERE  ");
      buf.append("ROWNUM<=1 ");
      return buf.toString();

   }

   public String getLatestDateOfSqlLog(String tableName) {
      StringBuilder buf = new StringBuilder();
      buf.append("select *");
      buf.append(" from  ");
      buf.append("(select  LOGON_TIME ");
      buf.append("from ");
      buf.append(tableName);
      buf.append(" order by  ");
      buf.append("LOGON_TIME desc NULLS LAST) ");
      buf.append("where ");
      buf.append("ROWNUM<=1  ");
      return buf.toString();

   }

   public String getLatestDateOfSqlOperator(String tableName) {
      StringBuilder buf = new StringBuilder();
      buf.append("select *");
      buf.append(" from  ");
      buf.append("(select  OPT_TIME ");
      buf.append("from ");
      buf.append(tableName);
      buf.append(" order by  ");
      buf.append("OPT_TIME desc NULLS LAST ) ");
      buf.append("where ");
      buf.append("ROWNUM<=1  ");
      return buf.toString();

   }

   public String getInsertDataSqlLogon(LogImportBean logBean) {
      StringBuilder buf = new StringBuilder();
      buf.append("insert into ");
      buf.append(logBean.getTableName());
      buf.append("(ID,");
      buf.append(" USER_ACCOUNT,");
      buf.append("USER_NAME,");
      buf.append("ORG_ID,  ");
      buf.append("DEPT_ID,  ");
      buf.append("ORG_NAME,");
      buf.append("DEPT_NAME, ");
      buf.append("LOGON_TIME, ");
      buf.append("IP_ADDRESS, ");
      buf.append("LOGOUT_TIME, ");
      buf.append("LOGON_RESULT)");
      buf.append("select * ");
      buf.append("from ");
      buf.append("T_SYS_LOG_LOGON ");
      buf.append("where ");
      buf.append("1=1 ");
      if (!"".equals(logBean.getFromDate())) {
         buf.append("and ");
         buf.append("to_char(LOGON_TIME,'yyyy-mm-dd hh24:mi:ss') >" + "'" + logBean.getFromDate() + "'");
      }
      if (!"".equals(logBean.getToDate())) {
         buf.append("and ");
         buf.append("to_char(LOGON_TIME,'yyyy-mm-dd hh24:mi:ss') <=" + "'" + logBean.getToDate() + "'");
      }
      return buf.toString();
   }

   public String getInsertDataSqlOperator(LogImportBean logBean) {
      StringBuilder buf = new StringBuilder();
      buf.append("insert into ");
      buf.append(logBean.getTableName());
      buf.append("(ID,");
      buf.append(" USER_ACCOUNT,");
      buf.append("USER_NAME, ");
      buf.append("ORG_ID,  ");
      buf.append("DEPT_ID,  ");
      buf.append("ORG_NAME,");
      buf.append("DEPT_NAME, ");
      buf.append("RES_ID, ");
      buf.append("OPT_TYPE, ");
      buf.append("IP_ADDRESS, ");
      buf.append("OPT_TIME, ");
      buf.append("QUERY_CONDITION)");
      buf.append("select * ");
      buf.append("from ");
      buf.append("T_SYS_LOG_OPERATE ");
      buf.append("where ");
      buf.append("1=1 ");
      if (!"".equals(logBean.getFromDate())) {
         buf.append("and ");
         buf.append("to_char(OPT_TIME,'yyyy-mm-dd hh24:mi:ss') >" + "'" + logBean.getFromDate() + "'");
      }
      if (!"".equals(logBean.getToDate())) {
         buf.append("and ");
         buf.append("to_char(OPT_TIME,'yyyy-mm-dd hh24:mi:ss') <=" + "'" + logBean.getToDate() + "'");
      }
      return buf.toString();

   }

   public String getSysTiemSql() {
      StringBuilder buf = new StringBuilder();
      buf.append("select to_char(sysdate,'yyyy-mm-dd hh:mi:ss') from dual");
      return buf.toString();
   }
}
