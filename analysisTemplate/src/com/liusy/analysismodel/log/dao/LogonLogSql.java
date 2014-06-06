package com.liusy.analysismodel.log.dao;

import com.liusy.analysismodel.log.dao.formBean.LogonBean;
import com.liusy.analysismodel.util.PageBean;

public class LogonLogSql {
   public String getSearchSql(LogonBean logonBean, PageBean pageBean) {
      StringBuffer buf = new StringBuffer();
      buf.append("SELECT TABLE_ROW.*");
      buf.append("from");
      buf.append("(select ROWNUM DATAROWNUM,TABLE_DATA.*");
      buf.append("from ");
      buf.append("(select distinct USER_ACCOUNT,USER_NAME,ORG_ID,DEPT_ID,ORG_NAME,  ");
      buf.append(" DEPT_NAME,LOGON_TIME,IP_ADDRESS,LOGOUT_TIME,LOGON_RESULT,ID");
      buf.append(" from T_SYS_LOG_LOGON");
      buf.append(" where 1=1 ");
      if (logonBean.getUserAcount().getText().trim() != null && !"".equals(logonBean.getUserAcount().getText().trim())) {
         buf.append(" and");
         buf.append(" USER_ACCOUNT =" + "'" + logonBean.getUserAcount().getText().trim() + "'");
      }
      if (logonBean.getUserName().getText().trim() != null && !"".equals(logonBean.getUserName().getText().trim())) {
         buf.append(" and");
         buf.append(" USER_NAME like" + "'%" + logonBean.getUserName().getText().trim() + "%'");
      }
      if (logonBean.getDepName().getText().trim() != null && !"".equals(logonBean.getDepName().getText().trim())) {
         buf.append(" and");
         buf.append(" DEPT_NAME like" + "'%" + logonBean.getDepName().getText().trim() + "%'");
      }
      if (logonBean.getOrgName().getText().trim() != null && !"".equals(logonBean.getOrgName().getText().trim())) {
         buf.append(" and");
         buf.append(" ORG_NAME like" + "'%" + logonBean.getOrgName().getText().trim() + "%'");
      }
      if (logonBean.getStartDate().getText().trim() != null && !"".equals(logonBean.getStartDate().getText().trim())) {
         buf.append(" and");
         buf.append(" LOGON_TIME >=" + "To_date(" + "'" + logonBean.getStartDate().getText().trim() + "'" + ",'yyyy-mm-dd hh:mi:ss')");
      }
      if (logonBean.getEndDate().getText().trim() != null && !"".equals(logonBean.getEndDate().getText().trim())) {
         buf.append(" and");
         buf.append(" trunc(LOGON_TIME,'dd') <=" + "To_date(" + "'" + logonBean.getEndDate().getText().trim() + "'" + ",'yyyy-mm-dd hh:mi:ss')");
      }
      buf.append("order by ID");
      buf.append(")TABLE_DATA");
      buf.append(")TABLE_ROW");
      buf.append(" where ");
      buf.append(" TABLE_ROW.DATAROWNUM >=" + pageBean.getStart());
      buf.append(" AND TABLE_ROW.DATAROWNUM <=" + pageBean.getEnd());
      return buf.toString();
   }

   public String getRecordCount(LogonBean logonBean, PageBean pageBean) {
      StringBuffer buf = new StringBuffer();
      buf.append("select count(*)");
      buf.append(" from");
      buf.append(" ( select distinct USER_ACCOUNT,USER_NAME,ORG_ID,DEPT_ID,ORG_NAME,  ");
      buf.append(" DEPT_NAME,LOGON_TIME,IP_ADDRESS,LOGOUT_TIME,LOGON_RESULT,ID");
      buf.append(" from");
      buf.append(" T_SYS_LOG_LOGON");
      buf.append(" where 1=1 ");
      if (logonBean.getUserAcount().getText().trim() != null && !"".equals(logonBean.getUserAcount().getText().trim())) {
         buf.append(" and");
         buf.append(" USER_ACCOUNT =" + "'" + logonBean.getUserAcount().getText().trim() + "'");
      }
      if (logonBean.getUserName().getText().trim() != null && !"".equals(logonBean.getUserName().getText().trim())) {
         buf.append(" and");
         buf.append(" USER_NAME like" + "'%" + logonBean.getUserName().getText().trim() + "%'");
      }
      if (logonBean.getDepName().getText().trim() != null && !"".equals(logonBean.getDepName().getText().trim())) {
         buf.append(" and");
         buf.append(" DEPT_NAME like" + "'%" + logonBean.getDepName().getText().trim() + "%'");
      }
      if (logonBean.getOrgName().getText().trim() != null && !"".equals(logonBean.getOrgName().getText().trim())) {
         buf.append(" and");
         buf.append(" ORG_NAME like" + "'%" + logonBean.getOrgName().getText().trim() + "%'");
      }
      if (logonBean.getStartDate().getText().trim() != null && !"".equals(logonBean.getStartDate().getText().trim())) {
         buf.append(" and");
         buf.append(" LOGON_TIME >=" + "To_date(" + "'" + logonBean.getStartDate().getText().trim() + "'" + ",'yyyy-mm-dd hh:mi:ss')");
      }
      if (logonBean.getEndDate().getText().trim() != null && !"".equals(logonBean.getEndDate().getText().trim())) {
         buf.append(" and");
         buf.append(" trunc(LOGON_TIME,'dd') <=" + "To_date(" + "'" + logonBean.getEndDate().getText().trim() + "'" + ",'yyyy-mm-dd hh:mi:ss')");
      }
      buf.append(" )");
      return buf.toString();
   }
}
