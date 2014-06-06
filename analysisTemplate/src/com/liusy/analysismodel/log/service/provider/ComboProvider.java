package com.liusy.analysismodel.log.service.provider;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import com.liusy.analysismodel.util.DbConnectionManager;

public class ComboProvider {
	private static Connection con;
	private static Statement stat;
	private static ResultSet rs;
	private static Log log=LogFactory.getLog(LogMonitorProvider.class);

	public static String[] getOraNameArray(String sql) {
		List<String> results = new ArrayList<String>();
		try{
			con = DbConnectionManager.getConnection();
			stat =con.createStatement();
			rs =stat.executeQuery(sql);
			while (rs.next()) {
				results.add(rs.getString(1));
			}
			results.add(0, "");
			return (String[]) results.toArray(new String[results.size()]);
		}catch(Exception e) {
			log.error(e);
		}finally{
			DbConnectionManager.closeResultSet(rs);
			DbConnectionManager.closeStatement(stat);
		}
		return null;
	}
	
}
