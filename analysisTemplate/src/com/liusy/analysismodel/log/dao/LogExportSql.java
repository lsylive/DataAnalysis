package com.liusy.analysismodel.log.dao;

import java.util.List;

import com.liusy.analysismodel.log.dao.formBean.LogExportBean;
import com.liusy.analysis.template.model.util.StringUtil;
import com.liusy.analysismodel.util.PageBean;

public class LogExportSql {
   public static String tableName = "LogTables";

   public String getSearchSql(LogExportBean logonBean, PageBean pageBean, List<String> tableNameList) {
      StringBuffer buf = new StringBuffer();
      buf.append("SELECT TABLE_ROW.*");
      buf.append("from");
      buf.append("(select ROWNUM DATAROWNUM,TABLE_DATA.*");
      buf.append("from ");

      if (!tableNameList.isEmpty()) {
         if (tableNameList.size() == 1) {
            buf.append("(select * from  ");
            buf.append(tableNameList.get(0));
            buf.append(" where 1=1 ");
            if (logonBean.getFromDate() != null && !"".equals(logonBean.getFromDate())) {
               buf.append(" and");
               buf.append(" (LOGON_TIME is null and OPT_TIME>=" + "To_date(" + "'" + logonBean.getFromDate() + "'" + ",'yyyy-mm-dd hh:mi:ss')");
               buf.append(" or OPT_TIME is null and LOGON_TIME>=" + "To_date(" + "'" + logonBean.getFromDate() + "'" + ",'yyyy-mm-dd'))");
            }
            if (logonBean.getToDate() != null && !"".equals(logonBean.getToDate())) {
               buf.append(" and");
               buf.append(" (LOGON_TIME is null and OPT_TIME<=" + "To_date(" + "'" + logonBean.getToDate() + "'" + ",'yyyy-mm-dd hh:mi:ss')");
               buf.append(" or OPT_TIME is null and LOGON_TIME<=" + "To_date(" + "'" + logonBean.getToDate() + "'" + ",'yyyy-mm-dd'))");
            }
         }
         else {

            buf.append("(select * from  ");
            buf.append(tableNameList.get(0));
            buf.append(" where 1=1 ");
            if (logonBean.getFromDate() != null && !"".equals(logonBean.getFromDate())) {
               buf.append(" and");
               buf.append(" (LOGON_TIME is null and OPT_TIME>=" + "To_date(" + "'" + logonBean.getFromDate() + "'" + ",'yyyy-mm-dd hh:mi:ss')");
               buf.append(" or OPT_TIME is null and LOGON_TIME>=" + "To_date(" + "'" + logonBean.getFromDate() + "'" + ",'yyyy-mm-dd'))");
            }
            if (logonBean.getToDate() != null && !"".equals(logonBean.getToDate())) {
               buf.append(" and");
               buf.append(" (LOGON_TIME is null and OPT_TIME<=" + "To_date(" + "'" + logonBean.getToDate() + "'" + ",'yyyy-mm-dd hh:mi:ss')");
               buf.append(" or OPT_TIME is null and LOGON_TIME<=" + "To_date(" + "'" + logonBean.getToDate() + "'" + ",'yyyy-mm-dd'))");
            }

            for (int i = 1; i < tableNameList.size(); i++) {
               buf.append("union select * from  ");
               buf.append(tableNameList.get(i));
               buf.append(" where 1=1 ");
               if (logonBean.getFromDate() != null && !"".equals(logonBean.getFromDate())) {
                  buf.append(" and");
                  buf.append(" (LOGON_TIME is null and OPT_TIME>=" + "To_date(" + "'" + logonBean.getFromDate() + "'" + ",'yyyy-mm-dd hh:mi:ss')");
                  buf.append(" or OPT_TIME is null and LOGON_TIME>=" + "To_date(" + "'" + logonBean.getFromDate() + "'" + ",'yyyy-mm-dd'))");
               }
               if (logonBean.getToDate() != null && !"".equals(logonBean.getToDate())) {
                  buf.append(" and");
                  buf.append(" (LOGON_TIME is null and OPT_TIME<=" + "To_date(" + "'" + logonBean.getToDate() + "'" + ",'yyyy-mm-dd hh:mi:ss')");
                  buf.append(" or OPT_TIME is null and LOGON_TIME<=" + "To_date(" + "'" + logonBean.getToDate() + "'" + ",'yyyy-mm-dd'))");
               }
               if (i == tableNameList.size() - 1) {
                  buf.append(" )");
               }
            }
         }
      }
      buf.append("order by ID");
      buf.append(")TABLE_DATA");
      buf.append(")TABLE_ROW");
      buf.append(" where ");
      buf.append(" TABLE_ROW.DATAROWNUM >=" + pageBean.getStart());
      buf.append(" AND TABLE_ROW.DATAROWNUM <=" + pageBean.getEnd());
      return buf.toString();
   }

   public String getRecordCount(LogExportBean logonBean, PageBean pageBean, List<String> tableNameList) {
      StringBuffer buf = new StringBuffer();
      buf.append("select count(*) ");
      buf.append(" from ");

      if (!tableNameList.isEmpty()) {
         if (tableNameList.size() == 1) {
            //				buf.append("(select * from  ");
            buf.append(tableNameList.get(0));
            buf.append(" where 1=1 ");
            if (logonBean.getFromDate() != null && !"".equals(logonBean.getFromDate())) {
               buf.append(" and");
               buf.append(" (LOGON_TIME is null and OPT_TIME>=" + "To_date(" + "'" + logonBean.getFromDate() + "'" + ",'yyyy-mm-dd hh:mi:ss')");
               buf.append(" or OPT_TIME is null and LOGON_TIME>=" + "To_date(" + "'" + logonBean.getFromDate() + "'" + ",'yyyy-mm-dd'))");
            }
            if (logonBean.getToDate() != null && !"".equals(logonBean.getToDate())) {
               buf.append(" and");
               buf.append(" (LOGON_TIME is null and OPT_TIME<=" + "To_date(" + "'" + logonBean.getToDate() + "'" + ",'yyyy-mm-dd hh:mi:ss')");
               buf.append(" or OPT_TIME is null and LOGON_TIME<=" + "To_date(" + "'" + logonBean.getToDate() + "'" + ",'yyyy-mm-dd'))");
            }
         }
         else {

            buf.append("( ");
            buf.append("select * from  ");
            buf.append(tableNameList.get(0));
            buf.append(" where 1=1 ");
            if (logonBean.getFromDate() != null && !"".equals(logonBean.getFromDate())) {
               buf.append(" and");
               buf.append(" (LOGON_TIME is null and OPT_TIME>=" + "To_date(" + "'" + logonBean.getFromDate() + "'" + ",'yyyy-mm-dd hh:mi:ss')");
               buf.append(" or OPT_TIME is null and LOGON_TIME>=" + "To_date(" + "'" + logonBean.getFromDate() + "'" + ",'yyyy-mm-dd'))");
            }
            if (logonBean.getToDate() != null && !"".equals(logonBean.getToDate())) {
               buf.append(" and");
               buf.append(" (LOGON_TIME is null and OPT_TIME<=" + "To_date(" + "'" + logonBean.getToDate() + "'" + ",'yyyy-mm-dd hh:mi:ss')");
               buf.append(" or OPT_TIME is null and LOGON_TIME<=" + "To_date(" + "'" + logonBean.getToDate() + "'" + ",'yyyy-mm-dd'))");
            }

            for (int i = 1; i < tableNameList.size(); i++) {
               buf.append(" union select * from  ");
               buf.append(tableNameList.get(i));
               buf.append(" where 1=1 ");
               if (logonBean.getFromDate() != null && !"".equals(logonBean.getFromDate())) {
                  buf.append(" and");
                  buf.append(" (LOGON_TIME is null and OPT_TIME>=" + "To_date(" + "'" + logonBean.getFromDate() + "'" + ",'yyyy-mm-dd hh:mi:ss')");
                  buf.append(" or OPT_TIME is null and LOGON_TIME>=" + "To_date(" + "'" + logonBean.getFromDate() + "'" + ",'yyyy-mm-dd'))");
               }
               if (logonBean.getToDate() != null && !"".equals(logonBean.getToDate())) {
                  buf.append(" and");
                  buf.append(" (LOGON_TIME is null and OPT_TIME<=" + "To_date(" + "'" + logonBean.getToDate() + "'" + ",'yyyy-mm-dd hh:mi:ss')");
                  buf.append(" or OPT_TIME is null and LOGON_TIME<=" + "To_date(" + "'" + logonBean.getToDate() + "'" + ",'yyyy-mm-dd'))");
               }
               if (i == tableNameList.size() - 1) {
                  buf.append(" )");
               }
            }
         }
      }
      return buf.toString();
   }

   public String getAllSearchSql(LogExportBean logonBean, PageBean pageBean, List<String> tableNameList) {
      StringBuffer buf = new StringBuffer();
      buf.append("SELECT TABLE_ROW.*");
      buf.append(" from ");
      buf.append("(select ROWNUM DATAROWNUM,TABLE_DATA.*");
      buf.append(" from ");

      if (!tableNameList.isEmpty()) {
         if (tableNameList.size() == 1) {
            buf.append("(select * from  ");
            buf.append(tableNameList.get(0));
            buf.append(" where 1=1 ");
            if (logonBean.getFromDate() != null && !"".equals(logonBean.getFromDate())) {
               buf.append(" and");
               buf.append(" (LOGON_TIME is null and OPT_TIME>=" + "To_date(" + "'" + logonBean.getFromDate() + "'" + ",'yyyy-mm-dd hh:mi:ss')");
               buf.append(" or OPT_TIME is null and LOGON_TIME>=" + "To_date(" + "'" + logonBean.getFromDate() + "'" + ",'yyyy-mm-dd'))");
            }
            if (logonBean.getToDate() != null && !"".equals(logonBean.getToDate())) {
               buf.append(" and");
               buf.append(" (LOGON_TIME is null and OPT_TIME<=" + "To_date(" + "'" + logonBean.getToDate() + "'" + ",'yyyy-mm-dd hh:mi:ss')");
               buf.append(" or OPT_TIME is null and LOGON_TIME<=" + "To_date(" + "'" + logonBean.getToDate() + "'" + ",'yyyy-mm-dd'))");
            }
         }
         else {

            buf.append("(select * from  ");
            buf.append(tableNameList.get(0));
            buf.append(" where 1=1 ");
            if (logonBean.getFromDate() != null && !"".equals(logonBean.getFromDate())) {
               buf.append(" and");
               buf.append(" (LOGON_TIME is null and OPT_TIME>=" + "To_date(" + "'" + logonBean.getFromDate() + "'" + ",'yyyy-mm-dd hh:mi:ss')");
               buf.append(" or OPT_TIME is null and LOGON_TIME>=" + "To_date(" + "'" + logonBean.getFromDate() + "'" + ",'yyyy-mm-dd'))");
            }
            if (logonBean.getToDate() != null && !"".equals(logonBean.getToDate())) {
               buf.append(" and");
               buf.append(" (LOGON_TIME is null and OPT_TIME<=" + "To_date(" + "'" + logonBean.getToDate() + "'" + ",'yyyy-mm-dd hh:mi:ss')");
               buf.append(" or OPT_TIME is null and LOGON_TIME<=" + "To_date(" + "'" + logonBean.getToDate() + "'" + ",'yyyy-mm-dd'))");
            }

            for (int i = 1; i < tableNameList.size(); i++) {
               buf.append("union select * from  ");
               buf.append(tableNameList.get(i));
               buf.append(" where 1=1 ");
               if (logonBean.getFromDate() != null && !"".equals(logonBean.getFromDate())) {
                  buf.append(" and");
                  buf.append(" (LOGON_TIME is null and OPT_TIME>=" + "To_date(" + "'" + logonBean.getFromDate() + "'" + ",'yyyy-mm-dd hh:mi:ss')");
                  buf.append(" or OPT_TIME is null and LOGON_TIME>=" + "To_date(" + "'" + logonBean.getFromDate() + "'" + ",'yyyy-mm-dd'))");
               }
               if (logonBean.getToDate() != null && !"".equals(logonBean.getToDate())) {
                  buf.append(" and");
                  buf.append(" (LOGON_TIME is null and OPT_TIME<=" + "To_date(" + "'" + logonBean.getToDate() + "'" + ",'yyyy-mm-dd hh:mi:ss')");
                  buf.append(" or OPT_TIME is null and LOGON_TIME<=" + "To_date(" + "'" + logonBean.getToDate() + "'" + ",'yyyy-mm-dd'))");
               }
               if (i == tableNameList.size() - 1) {
                  buf.append(" )");
               }
            }
         }
      }
      buf.append("order by ID");
      buf.append(")TABLE_DATA");
      buf.append(")TABLE_ROW");
      buf.append(" where ");
      buf.append(" TABLE_ROW.DATAROWNUM >=1");
      buf.append(" AND TABLE_ROW.DATAROWNUM <=" + pageBean.getPageRecordCount());
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

   public String getExitEearlyTableName() {
      StringBuilder buf = new StringBuilder();
      buf.append("select * ");
      buf.append("from ");
      buf.append("(SELECT TABLE_NAME ");
      buf.append("FROM  ");
      buf.append("DBA_TABLES  ");
      buf.append("WHERE  ");
      buf.append("TABLE_NAME like" + "'" + StringUtil.tableName + "%" + "'");
      buf.append("order by ");
      buf.append("TABLE_NAME )");
      buf.append("WHERE  ");
      buf.append("ROWNUM<=1 ");
      return buf.toString();

   }
}
