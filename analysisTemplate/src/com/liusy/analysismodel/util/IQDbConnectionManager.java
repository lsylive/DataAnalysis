package com.liusy.analysismodel.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.log4j.Logger;

import com.liusy.analysismodel.DataAdminPlatform;

/**
 * IQ���ݿ������
 * 
 * @author andy
 */
public class IQDbConnectionManager {
	private static Logger log = Logger.getLogger(DbConnectionManager.class);

	private static Connection conn;

	public static Connection getConnection() {
		if (conn != null){
			try {
				boolean autoCommitFlg = conn.getAutoCommit();
				if (!autoCommitFlg) {
					return conn;
				} else {
					conn.setAutoCommit(false);
					return conn;
				}
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			try {
				Class.forName(DataAdminPlatform.IQ_DATABASE_DRIVE);
				conn = DriverManager.getConnection(DataAdminPlatform.IQ_DATABASE_URL,
						DataAdminPlatform.IQ_DATABASE_USER,
						DataAdminPlatform.IQ_DATABASE_PASSWORD);
				conn.setAutoCommit(false);
			} catch (Exception e) {
				conn = null;
				e.printStackTrace();
				log.error(e);
			}
		}
		return conn;
	}

	public static void closeConnection() {
		if (null != conn) {
			try {
				conn.close();
				conn = null;
			} catch (Exception e) {
				e.printStackTrace();
				log.error(e);
			}
		}
	}

	//
	//   public static void closeConnection(Connection conn) {
	//      if (null != conn) {
	//         try {
	//            conn.close();
	//            conn = null;
	//         }
	//         catch (Exception e) {
	//            e.printStackTrace();
	//            log.error(e);
	//         }
	//      }
	//   }

	/**
	 * �رն���ķ���
	 */
	public static void closeResultSet(ResultSet rs) {
		if (null != rs) {
			try {
				rs.close();
				rs = null;
			} catch (Exception e) {
				e.printStackTrace();
				log.error(e);
			}
		}
	}

	public static void closeStatement(Statement statm) {
		if (null != statm) {
			try {
				statm.close();
				statm = null;
			} catch (Exception e) {
				e.printStackTrace();
				log.error(e);
			}
		}
	}

	public static void closePreparedStatement(PreparedStatement pstm) {
		if (null != pstm) {
			try {
				pstm.close();
				pstm = null;
			} catch (Exception e) {
				e.printStackTrace();
				log.error(e);
			}
		}
	}

	public static void rollBack(Connection con) {
		if (null != con) {
			try {
				con.rollback();
			} catch (Exception e) {
				e.printStackTrace();
				log.error(e);
			}
		}
	}

	//   /**
	//    * ��ô������connection
	//    */
	//   public static Connection getTransactionConnection() throws Exception {
	//      Connection con = getConnection();
	//
	//      con.setAutoCommit(false);
	//
	//      return con;
	//   }
	//
	//   public static void closeTransactionConnection(Connection con) {
	//      if (null != con) {
	//         try {
	//            con.rollback();
	//            con.close();
	//            con = null;
	//         }
	//         catch (Exception e) {
	//            e.printStackTrace();
	//            log.error(e);
	//         }
	//      }
	//   }

}
