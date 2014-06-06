package com.liusy.analysismodel.log.dao;

import com.liusy.analysismodel.log.dao.formBean.SystemBean;
import com.liusy.analysismodel.util.PageBean;

public class LogSystemSql {

   public String getSearchSql(SystemBean logonBean, PageBean pageBean) {
      StringBuffer buf = new StringBuffer();
      buf.append("SELECT TABLE_ROW.*");
      buf.append("from");
      buf.append("(select ROWNUM DATAROWNUM,TABLE_DATA.*");
      buf.append("from ");
      buf.append("( select * from T_SYS_LOG_OPERATE");
      buf.append(" where 1=1 ");
      if (logonBean.getLogLevel().getSelectionIndex() != 0 && !"".equals(logonBean.getLogLevel().getSelectionIndex())) {
         buf.append(" and");
         buf.append(" LOG_LEVEL =" + "'" + logonBean.getLogLevel().getSelectionIndex() + "'");
      }
      if (logonBean.getStartDate().getText().trim() != null && !"".equals(logonBean.getStartDate().getText().trim())) {
         buf.append(" and");
         buf.append(" LOG_TIME >=" + "To_date(" + "'" + logonBean.getStartDate().getText().trim() + "'" + ",'yyyy-mm-dd hh:mi:ss')");
      }
      if (logonBean.getEndDate().getText().trim() != null && !"".equals(logonBean.getEndDate().getText().trim())) {
         buf.append(" and");
         buf.append(" LOG_TIME <=" + "To_date(" + "'" + logonBean.getEndDate().getText().trim() + "'" + ",'yyyy-mm-dd hh:mi:ss')");
      }
      buf.append("order by ID");
      buf.append(")TABLE_DATA");
      buf.append(")TABLE_ROW");
      buf.append(" where ");
      buf.append(" TABLE_ROW.DATAROWNUM >=" + pageBean.getStart());
      buf.append(" AND TABLE_ROW.DATAROWNUM <=" + pageBean.getEnd());
      return buf.toString();
   }

   public String getAllSearchSql(SystemBean logonBean, PageBean pageBean) {
      StringBuffer buf = new StringBuffer();
      buf.append("SELECT TABLE_ROW.*");
      buf.append("from");
      buf.append("(select ROWNUM DATAROWNUM,TABLE_DATA.*");
      buf.append("from ");
      buf.append("(select * from  T_SYS_LOG_RUN");
      buf.append(" where 1=1 ");
      if (logonBean.getLogLevel().getSelectionIndex() != 0 && !"".equals(logonBean.getLogLevel().getSelectionIndex())) {
         buf.append(" and");
         buf.append(" LOG_LEVEL =" + "'" + logonBean.getLogLevel().getSelectionIndex() + "'");
      }
      if (logonBean.getStartDate().getText().trim() != null && !"".equals(logonBean.getStartDate().getText().trim())) {
         buf.append(" and");
         buf.append(" LOG_TIME >=" + "To_date(" + "'" + logonBean.getStartDate().getText().trim() + "'" + ",'yyyy-mm-dd hh:mi:ss')");
      }
      if (logonBean.getEndDate().getText().trim() != null && !"".equals(logonBean.getEndDate().getText().trim())) {
         buf.append(" and");
         buf.append(" LOG_TIME <=" + "To_date(" + "'" + logonBean.getEndDate().getText().trim() + "'" + ",'yyyy-mm-dd hh:mi:ss')");
      }
      buf.append("order by ID");
      buf.append(")TABLE_DATA");
      buf.append(")TABLE_ROW");
      buf.append(" where ");
      buf.append(" TABLE_ROW.DATAROWNUM >=" + pageBean.getStart());
      buf.append(" AND TABLE_ROW.DATAROWNUM <=" + pageBean.getEnd());
      return buf.toString();

   }

   //查询记录条数
   public String getRecordCount(SystemBean logonBean, PageBean pageBean) {
      StringBuffer buf = new StringBuffer();
      buf.append("select count(*)");
      buf.append(" from");
      buf.append(" T_SYS_LOG_RUN");
      buf.append(" where 1=1 ");
      if (logonBean.getLogLevel().getSelectionIndex() != 0 && !"".equals(logonBean.getLogLevel().getSelectionIndex())) {
         buf.append(" and");
         buf.append(" LOG_LEVEL =" + "'" + logonBean.getLogLevel().getSelectionIndex() + "'");
      }
      if (logonBean.getStartDate().getText().trim() != null && !"".equals(logonBean.getStartDate().getText().trim())) {
         buf.append(" and");
         buf.append(" LOG_TIME >=" + "To_date(" + "'" + logonBean.getStartDate().getText().trim() + "'" + ",'yyyy-mm-dd hh:mi:ss')");
      }
      if (logonBean.getEndDate().getText().trim() != null && !"".equals(logonBean.getEndDate().getText().trim())) {
         buf.append(" and");
         buf.append(" LOG_TIME <=" + "To_date(" + "'" + logonBean.getEndDate().getText().trim() + "'" + ",'yyyy-mm-dd hh:mi:ss')");
      }
      return buf.toString();
   }

}
