package com.liusy.analysismodel.log.dao;

import com.liusy.analysismodel.log.dao.formBean.LogOperateBean;
import com.liusy.analysismodel.util.PageBean;

public class LogOperateSql {

   public String getSearchSql(LogOperateBean logonBean, PageBean pageBean) {
      StringBuffer buf = new StringBuffer();
      buf.append("SELECT TABLE_ROW.*");
      buf.append("from");
      buf.append("(select ROWNUM DATAROWNUM,TABLE_DATA.*");
      buf.append("from ");
      buf.append("( select DISTINCT USER_ACCOUNT,USER_NAME,ORG_NAME,DEPT_NAME,OPT_TYPE, ");
      buf.append("IP_ADDRESS, OPT_TIME,QUERY_CONDITION,RES_NAME,T_SYS_LOG_OPERATE.ID from ");
      buf.append(" T_SYS_LOG_OPERATE LEFT JOIN T_SYS_RESOURCE_INFO ");
      buf.append(" ON T_SYS_LOG_OPERATE.Res_Id = T_SYS_RESOURCE_INFO.Id ");
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
         buf.append(" OPT_TIME >=" + "To_date(" + "'" + logonBean.getStartDate().getText().trim() + "'" + ",'yyyy-mm-dd hh:mi:ss')");
      }
      if (logonBean.getEndDate().getText().trim() != null && !"".equals(logonBean.getEndDate().getText().trim())) {
         buf.append(" and");
         buf.append(" trunc(OPT_TIME,'dd') <=" + "To_date(" + "'" + logonBean.getEndDate().getText().trim() + "'" + ",'yyyy-mm-dd hh:mi:ss')");
      }
      buf.append("order by T_SYS_LOG_OPERATE.ID desc");
      buf.append(")TABLE_DATA");
      buf.append(")TABLE_ROW");
      buf.append(" where ");
      buf.append(" TABLE_ROW.DATAROWNUM >=" + pageBean.getStart());
      buf.append(" AND TABLE_ROW.DATAROWNUM <=" + pageBean.getEnd());
      return buf.toString();
   }

   //查询记录条数
   public String getRecordCount(LogOperateBean logonBean, PageBean pageBean) {
      StringBuffer buf = new StringBuffer();
      buf.append("select count(*)");
      buf.append(" from");
      buf.append(" ( select DISTINCT USER_ACCOUNT,USER_NAME,ORG_NAME,DEPT_NAME,OPT_TYPE, ");
      buf.append(" IP_ADDRESS, OPT_TIME,QUERY_CONDITION,ID");
      buf.append(" from");
      buf.append(" T_SYS_LOG_OPERATE");
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
         buf.append(" OPT_TIME >=" + "To_date(" + "'" + logonBean.getStartDate().getText().trim() + "'" + ",'yyyy-mm-dd hh:mi:ss')");
      }
      if (logonBean.getEndDate().getText().trim() != null && !"".equals(logonBean.getEndDate().getText().trim())) {
         buf.append(" and");
         buf.append(" trunc(OPT_TIME,'dd') <=" + "To_date(" + "'" + logonBean.getEndDate().getText().trim() + "'" + ",'yyyy-mm-dd hh:mi:ss')");
      }
      //      buf.append("order by ID");
      buf.append(" )");
      return buf.toString();
   }

   public String getOrgInfoSql() {
      StringBuffer buf = new StringBuffer();
      buf.append("select ORG_NAME ");
      buf.append("from ");
      buf.append("T_SYS_ORG_INFO  ");
      return buf.toString();
   }

   public String getDeptInfoSql(String orgName) {
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
