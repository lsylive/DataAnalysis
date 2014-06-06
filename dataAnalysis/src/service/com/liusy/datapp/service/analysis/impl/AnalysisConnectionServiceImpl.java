package com.liusy.datapp.service.analysis.impl;

import java.sql.Connection;
import java.sql.SQLException;
import javax.sql.DataSource;

import com.liusy.datapp.service.analysis.AnalysisConnectionService;

public class AnalysisConnectionServiceImpl implements AnalysisConnectionService {

   private DataSource dataSourceIq;

   public Connection getConnection() {
      try {
         return dataSourceIq.getConnection();
      }
      catch (SQLException e) {
         e.printStackTrace();
         return null;
      }
   }

   public void closeConnection(Connection conn) {
      try {
         conn.close();
      }
      catch (SQLException e) {
         e.printStackTrace();
      }
   }

   public DataSource getDataSourceIq() {
      return dataSourceIq;
   }

   public void setDataSourceIq(DataSource dataSourceIq) {
      this.dataSourceIq = dataSourceIq;
   }

}
