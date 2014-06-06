package com.liusy.analysismodel.log.dao;

public class ComboSql {

   public static String getOrgInfoSql() {
      StringBuffer buf = new StringBuffer();
      buf.append("select ORG_NAME ");
      buf.append("from ");
      buf.append("T_SYS_ORG_INFO  ");
      return buf.toString();
   }

   public static String getDeptInfoSql(String orgName) {
      StringBuffer buf = new StringBuffer();
      buf.append("select T_SYS_DEPT_INFO.DEPT_NAME ");
      buf.append("from ");
      buf.append("T_SYS_DEPT_INFO,T_SYS_ORG_INFO  ");
      buf.append("where ");
      buf.append("T_SYS_DEPT_INFO.ORG_ID = T_SYS_ORG_INFO.ID ");
      buf.append("and ");
      buf.append("T_SYS_ORG_INFO.ORG_NAME = ");
      buf.append("'" + orgName + "'");
      return buf.toString();
   }
}
