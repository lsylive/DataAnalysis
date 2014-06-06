package com.liusy.analysismodel.log.dao;

import com.liusy.analysismodel.log.model.log.ImportLog2;
import com.liusy.analysis.template.model.util.StringUtil;

public class LogExportSql2 {
   public static String tableName = "LogTables";

   public String getInsertDataSqlLogon(ImportLog2 logBean) {
      StringBuilder buf = new StringBuilder();
      buf.append("insert into ");
      buf.append("TEMPLOGTABLE");
      buf.append("(ID,");
      buf.append(" USER_ACCOUNT,");
      buf.append("USER_NAME,");
      buf.append("ORG_ID,");
      buf.append("DEPT_ID,");
      buf.append("ORG_NAME,");
      buf.append("DEPT_NAME,");
      buf.append("LOGON_TIME,");
      buf.append("IP_ADDRESS,");
      buf.append("LOGOUT_TIME,");
      buf.append("LOGON_RESULT,");

      buf.append("RES_ID,");
      buf.append("OPT_TYPE,");
      buf.append("OPT_TIME,");
      buf.append("QUERY_CONDITION,");
      buf.append("ORIGINAL)");
      buf.append(" VALUES ");
      buf.append("(");
      buf.append(logBean.getId() + ",");
      buf.append("'" + StringUtil.getStringNotNull(logBean.getUserAccount()) + "',");
      buf.append("'" + StringUtil.getStringNotNull(logBean.getUserName()) + "',");
      buf.append(logBean.getOrgId() + ",");
      buf.append(logBean.getDeptId() + ",");
      buf.append("'" + StringUtil.getStringNotNull(logBean.getOrgName()) + "',");
      buf.append("'" + StringUtil.getStringNotNull(logBean.getDeptName()) + "',");
      buf.append("'" + StringUtil.getStringNotNull(logBean.getLogonTime()) + "',");
      buf.append("'" + StringUtil.getStringNotNull(logBean.getIpAddress()) + "',");
      buf.append("'" + StringUtil.getStringNotNull(logBean.getLogonOutTime()) + "',");
      buf.append("'" + StringUtil.getStringNotNull(logBean.getResult()) + "',");
      buf.append(logBean.getResId() + ",");
      buf.append("'" + StringUtil.getStringNotNull(logBean.getOptType()) + "',");
      buf.append("to_date('" + StringUtil.getStringNotNull(StringUtil.timeStampToString(logBean.getOptTime())) + "','yyyy-mm-dd hh24:mi:ss'),");
      buf.append("'" + StringUtil.getFliterQueryCondition(logBean.getQueryCondi()) + "',");
      buf.append("'" + StringUtil.getStringNotNull(logBean.getOriginal()) + "')");
      return buf.toString();
   }
}
