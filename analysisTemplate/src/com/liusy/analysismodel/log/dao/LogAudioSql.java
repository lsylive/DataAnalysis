package com.liusy.analysismodel.log.dao;

import com.liusy.analysismodel.log.dao.formBean.LogOperateBean;
import com.liusy.analysismodel.util.PageBean;

public class LogAudioSql {

   public String getSearchSql(LogOperateBean logonBean, PageBean pageBean) {
      StringBuffer buf = new StringBuffer();
      buf.append("SELECT TABLE_ROW.*");
      buf.append("from");
      buf.append("(select ROWNUM DATAROWNUM,TABLE_DATA.*");
      buf.append("from ( ");
      //操作日志表
      buf.append(" select USER_ACCOUNT,USER_NAME,ORG_NAME,DEPT_NAME,null AS LOGON_TIME,IP_ADDRESS, ");
      buf.append("null AS LOGOUT_TIME,'' AS LOGON_RESULT ,");
      buf.append("OPT_TYPE,OPT_TIME,QUERY_CONDITION,'T_SYS_LOG_OPERATE' AS ORIGINAL,RES_NAME,OPT_TIME AS TIME2 from ");
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
         buf.append(" OPT_TIME <" + "To_date(" + "'" + logonBean.getEndDate().getText().trim() + "'" + ",'yyyy-mm-dd hh:mi:ss')");
      }
      buf.append(" union ");
      //登陆日志表
      buf.append("select USER_ACCOUNT,USER_NAME,ORG_NAME,DEPT_NAME, ");
      buf.append("LOGON_TIME,IP_ADDRESS,LOGOUT_TIME,LOGON_RESULT, ");
      buf.append("'' AS OPT_TYPE,null AS OPT_TIME,'' AS QUERY_CONDITION,'T_SYS_LOG_LOGON' AS ORIGINAL,'' AS RES_NAME,LOGON_TIME AS TIME2");
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
         buf.append(" LOGON_TIME <" + "To_date(" + "'" + logonBean.getEndDate().getText().trim() + "'" + ",'yyyy-mm-dd hh:mi:ss')");
      }
      buf.append(" union ");
      //临时表日志表
      buf.append("select USER_ACCOUNT,USER_NAME,ORG_NAME,DEPT_NAME, ");
      buf.append("LOGON_TIME,IP_ADDRESS,LOGOUT_TIME,LOGON_RESULT, ");
      buf.append("OPT_TYPE,OPT_TIME,QUERY_CONDITION,ORIGINAL,RES_NAME,DECODE(OPT_TIME,NULL,LOGON_TIME,OPT_TIME)AS TIME2");
      buf.append(" from TEMPLOGTABLE LEFT JOIN T_SYS_RESOURCE_INFO ");
      buf.append(" ON TEMPLOGTABLE.Res_Id = T_SYS_RESOURCE_INFO.Id ");
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
         buf.append(" (LOGON_TIME is null and OPT_TIME>=" + "To_date(" + "'" + logonBean.getStartDate().getText().trim() + "'" + ",'yyyy-mm-dd hh:mi:ss')");
         buf.append(" or OPT_TIME is null and LOGON_TIME>=" + "To_date(" + "'" + logonBean.getStartDate().getText().trim() + "'" + ",'yyyy-mm-dd'))");
      }
      if (logonBean.getEndDate().getText().trim() != null && !"".equals(logonBean.getEndDate().getText().trim())) {
         buf.append(" and");
         buf.append(" (LOGON_TIME is null and OPT_TIME<=" + "To_date(" + "'" + logonBean.getEndDate().getText().trim() + "'" + ",'yyyy-mm-dd hh:mi:ss')");
         buf.append(" or OPT_TIME is null and LOGON_TIME<=" + "To_date(" + "'" + logonBean.getEndDate().getText().trim() + "'" + ",'yyyy-mm-dd'))");
      }

      buf.append("order by TIME2");
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
      buf.append("select count(*) from (");
      buf.append("select distinct USER_ACCOUNT,USER_NAME,ORG_ID,DEPT_ID,ORG_NAME, ");
      buf.append("DEPT_NAME,LOGON_TIME,IP_ADDRESS,LOGOUT_TIME,LOGON_RESULT,null AS RES_ID, ");
      buf.append("'' AS OPT_TYPE,null AS OPT_TIME,'' AS QUERY_CONDITION,'T_SYS_LOG_LOGON' AS ORIGINAL");
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
         buf.append(" LOGON_TIME <" + "To_date(" + "'" + logonBean.getEndDate().getText().trim() + "'" + ",'yyyy-mm-dd hh:mi:ss')");
      }
      buf.append("union ");
      buf.append("select distinct USER_ACCOUNT,USER_NAME,ORG_ID,DEPT_ID,ORG_NAME, ");
      buf.append("DEPT_NAME,null AS LOGON_TIME,IP_ADDRESS,null AS LOGOUT_TIME,'' AS LOGON_RESULT,RES_ID,");
      buf.append("OPT_TYPE,OPT_TIME,QUERY_CONDITION,'T_SYS_LOG_OPERATE' AS ORIGINAL");
      buf.append(" from T_SYS_LOG_OPERATE ");
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
         buf.append(" OPT_TIME <" + "To_date(" + "'" + logonBean.getEndDate().getText().trim() + "'" + ",'yyyy-mm-dd hh:mi:ss')");
      }
      buf.append("union ");
      buf.append("select distinct USER_ACCOUNT,USER_NAME,ORG_ID,DEPT_ID,ORG_NAME, ");
      buf.append("DEPT_NAME,LOGON_TIME,IP_ADDRESS,LOGOUT_TIME,LOGON_RESULT,RES_ID,");
      buf.append("OPT_TYPE,OPT_TIME,QUERY_CONDITION,ORIGINAL");
      buf.append(" from TEMPLOGTABLE ");
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
         buf.append(" (LOGON_TIME is null and OPT_TIME>=" + "To_date(" + "'" + logonBean.getStartDate().getText().trim() + "'" + ",'yyyy-mm-dd hh:mi:ss')");
         buf.append(" or OPT_TIME is null and LOGON_TIME>=" + "To_date(" + "'" + logonBean.getStartDate().getText().trim() + "'" + ",'yyyy-mm-dd'))");
         //			buf.append(" OPT_TIME >="+"To_date("+"'"+logonBean.getStartDate().getText().trim()+"'"+",'yyyy-mm-dd hh:mi:ss')");
      }
      if (logonBean.getEndDate().getText().trim() != null && !"".equals(logonBean.getEndDate().getText().trim())) {
         buf.append(" and");
         buf.append(" (LOGON_TIME is null and OPT_TIME<=" + "To_date(" + "'" + logonBean.getEndDate().getText().trim() + "'" + ",'yyyy-mm-dd hh:mi:ss')");
         buf.append(" or OPT_TIME is null and LOGON_TIME<=" + "To_date(" + "'" + logonBean.getEndDate().getText().trim() + "'" + ",'yyyy-mm-dd'))");
         //			buf.append(" OPT_TIME <"+"To_date("+"'"+logonBean.getEndDate().getText().trim()+"'"+",'yyyy-mm-dd hh:mi:ss')");
      }
      buf.append(" )");
      return buf.toString();
   }

}
