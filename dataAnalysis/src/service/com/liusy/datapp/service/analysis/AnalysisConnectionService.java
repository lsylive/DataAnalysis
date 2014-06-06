package com.liusy.datapp.service.analysis;

import java.sql.Connection;

public interface AnalysisConnectionService {

	public Connection getConnection();

   public void closeConnection(Connection conn);
}
