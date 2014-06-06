package com.liusy.analysismodel.log.service.provider;

import java.sql.Connection;
import java.sql.Statement;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import com.liusy.analysismodel.util.DbConnectionManager;
import com.liusy.analysismodel.log.dao.LogExportSql2;
import com.liusy.analysismodel.log.model.log.ImportLog2;

public class LogExportProvider2 {
   private static Log log = LogFactory.getLog(LogExportProvider2.class);

   public boolean insertLogTableInfo(List<ImportLog2> logBeanList, LogExportSql2 sqlBuilder) {
      Connection con = null;
      Statement stat = null;

      try {
         con = DbConnectionManager.getConnection();
         stat = con.createStatement();
         for (int i = 0; i < logBeanList.size(); i++) {
            ImportLog2 logBean = logBeanList.get(i);
            String sql = sqlBuilder.getInsertDataSqlLogon(logBean);
            stat.addBatch(sql);
         }
         stat.executeBatch();

         con.commit();
         return true;
      }
      catch (Exception e) {
         DbConnectionManager.rollBack(con);
         log.error(e);
         e.printStackTrace();
         return false;
      }
      finally {
         DbConnectionManager.closeStatement(stat);
      }
   }

}
