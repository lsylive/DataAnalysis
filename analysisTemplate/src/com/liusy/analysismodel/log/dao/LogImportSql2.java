package com.liusy.analysismodel.log.dao;

import com.liusy.analysismodel.log.dao.formBean.LogImportBean2;

public class LogImportSql2 {
   public static String tableName = "LogTables";

   public String getSelectLogTableSql(LogImportBean2 logonBean) {
      StringBuilder buf = new StringBuilder();
      buf.append("select distinct ID,USER_ACCOUNT,USER_NAME,ORG_ID,DEPT_ID,ORG_NAME, ");
      buf.append("DEPT_NAME,LOGON_TIME,IP_ADDRESS,LOGOUT_TIME,LOGON_RESULT,null AS RES_ID, ");
      buf.append("'' AS OPT_TYPE,null AS OPT_TIME,'' AS QUERY_CONDITION,'T_SYS_LOG_LOGON' AS ORIGINAL");
      buf.append(" from T_SYS_LOG_LOGON ");
      buf.append(" where 1=1 ");
      if (logonBean.getFromDate().getText().trim() != null && !"".equals(logonBean.getFromDate().getText().trim())) {
         buf.append(" and");
         buf.append(" LOGON_TIME >=" + "To_date(" + "'" + logonBean.getFromDate().getText().trim() + "'" + ",'yyyy-mm-dd hh:mi:ss')");
      }
      if (logonBean.getToDate().getText().trim() != null && !"".equals(logonBean.getToDate().getText().trim())) {
         buf.append(" and");
         buf.append(" LOGON_TIME <=" + "To_date(" + "'" + logonBean.getToDate().getText().trim() + "'" + ",'yyyy-mm-dd hh:mi:ss')");
      }
      buf.append("union ");
      buf.append("select distinct ID,USER_ACCOUNT,USER_NAME,ORG_ID,DEPT_ID,ORG_NAME, ");
      buf.append("DEPT_NAME,null AS LOGON_TIME,IP_ADDRESS,null AS LOGOUT_TIME,'' AS LOGON_RESULT,RES_ID,");
      buf.append("OPT_TYPE,OPT_TIME,QUERY_CONDITION,'T_SYS_LOG_OPERATE' AS ORIGINAL");
      buf.append(" from T_SYS_LOG_OPERATE ");
      buf.append(" where 1=1 ");
      if (logonBean.getFromDate().getText().trim() != null && !"".equals(logonBean.getFromDate().getText().trim())) {
         buf.append(" and");
         buf.append(" OPT_TIME >=" + "To_date(" + "'" + logonBean.getFromDate().getText().trim() + "'" + ",'yyyy-mm-dd hh:mi:ss')");
      }
      if (logonBean.getToDate().getText().trim() != null && !"".equals(logonBean.getToDate().getText().trim())) {
         buf.append(" and");
         buf.append(" OPT_TIME <=" + "To_date(" + "'" + logonBean.getToDate().getText().trim() + "'" + ",'yyyy-mm-dd hh:mi:ss')");
      }
      return buf.toString();
   }

   public String getLogTableCountSql(LogImportBean2 logonBean) {

      StringBuilder buf = new StringBuilder();
      buf.append("select count(*) from (");

      buf.append("select distinct ID,USER_ACCOUNT,USER_NAME,ORG_ID,DEPT_ID,ORG_NAME, ");
      buf.append("DEPT_NAME,LOGON_TIME,IP_ADDRESS,LOGOUT_TIME,LOGON_RESULT,null AS RES_ID, ");
      buf.append("'' AS OPT_TYPE,null AS OPT_TIME,'' AS QUERY_CONDITION,'T_SYS_LOG_LOGON' AS ORIGINAL");
      buf.append(" from T_SYS_LOG_LOGON ");
      buf.append(" where 1=1 ");
      if (logonBean.getFromDate().getText().trim() != null && !"".equals(logonBean.getFromDate().getText().trim())) {
         buf.append(" and");
         buf.append(" LOGON_TIME >=" + "To_date(" + "'" + logonBean.getFromDate().getText().trim() + "'" + ",'yyyy-mm-dd hh:mi:ss')");
      }
      if (logonBean.getToDate().getText().trim() != null && !"".equals(logonBean.getToDate().getText().trim())) {
         buf.append(" and");
         buf.append(" LOGON_TIME <=" + "To_date(" + "'" + logonBean.getToDate().getText().trim() + "'" + ",'yyyy-mm-dd hh:mi:ss')");
      }
      buf.append("union ");
      buf.append("select distinct  ID,USER_ACCOUNT,USER_NAME,ORG_ID,DEPT_ID,ORG_NAME, ");
      buf.append("DEPT_NAME,null AS LOGON_TIME,IP_ADDRESS,null AS LOGOUT_TIME,'' AS LOGON_RESULT,RES_ID,");
      buf.append("OPT_TYPE,OPT_TIME,QUERY_CONDITION,'T_SYS_LOG_OPERATE' AS ORIGINAL");
      buf.append(" from T_SYS_LOG_OPERATE ");
      buf.append(" where 1=1 ");
      if (logonBean.getFromDate().getText().trim() != null && !"".equals(logonBean.getFromDate().getText().trim())) {
         buf.append(" and");
         buf.append(" OPT_TIME >=" + "To_date(" + "'" + logonBean.getFromDate().getText().trim() + "'" + ",'yyyy-mm-dd hh:mi:ss')");
      }
      if (logonBean.getToDate().getText().trim() != null && !"".equals(logonBean.getToDate().getText().trim())) {
         buf.append(" and");
         buf.append(" OPT_TIME <=" + "To_date(" + "'" + logonBean.getToDate().getText().trim() + "'" + ",'yyyy-mm-dd hh:mi:ss')");
      }
      buf.append(" )");
      return buf.toString();

   }

   public String getDeleteLogonTableSql(LogImportBean2 logonBean) {
      StringBuilder buf = new StringBuilder();
      buf.append("delete T_SYS_LOG_LOGON ");
      buf.append(" where 1=1 ");
      if (logonBean.getFromDate().getText().trim() != null && !"".equals(logonBean.getFromDate().getText().trim())) {
         buf.append(" and");
         buf.append(" LOGON_TIME >=" + "To_date(" + "'" + logonBean.getFromDate().getText().trim() + "'" + ",'yyyy-mm-dd hh:mi:ss')");
      }
      if (logonBean.getToDate().getText().trim() != null && !"".equals(logonBean.getToDate().getText().trim())) {
         buf.append(" and");
         buf.append(" LOGON_TIME <=" + "To_date(" + "'" + logonBean.getToDate().getText().trim() + "'" + ",'yyyy-mm-dd hh:mi:ss')");
      }
      return buf.toString();
   }

   public String getDeleteOperatorTableSql(LogImportBean2 logonBean) {
      StringBuilder buf = new StringBuilder();
      buf.append("delete T_SYS_LOG_OPERATE ");
      buf.append(" where 1=1 ");
      if (logonBean.getFromDate().getText().trim() != null && !"".equals(logonBean.getFromDate().getText().trim())) {
         buf.append(" and");
         buf.append(" OPT_TIME >=" + "To_date(" + "'" + logonBean.getFromDate().getText().trim() + "'" + ",'yyyy-mm-dd hh:mi:ss')");
      }
      if (logonBean.getToDate().getText().trim() != null && !"".equals(logonBean.getToDate().getText().trim())) {
         buf.append(" and");
         buf.append(" OPT_TIME <=" + "To_date(" + "'" + logonBean.getToDate().getText().trim() + "'" + ",'yyyy-mm-dd hh:mi:ss')");
      }
      return buf.toString();
   }

}
