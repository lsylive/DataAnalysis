package com.liusy.datapp.dao.query;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import com.liusy.dataapplatform.base.dao.BaseDao;
import com.liusy.dataapplatform.base.exception.DAOException;
import com.liusy.dataapplatform.base.util.PageQuery;
import com.liusy.datapp.util.poolobj.ColumnPoolObj;

public interface SynthesisTempSpaceDao {
	
	public PageQuery queryBySql(String sqlstr, PageQuery pageQuery) throws DAOException;
	public int queryCountBySql(String querySQL) throws DAOException;
	public int queryByInt(String querySQL) throws DAOException;
	public void batchUpdate(String sql,List<Map<String,String>> resultList,List<ColumnPoolObj> columnpoolList) throws DAOException;
	public void executeUpdate(String sql) throws DAOException;
	public boolean isTableExist(String tableName) throws DAOException;
	public boolean isTableHasRecord(String tableName) throws DAOException;
	public void createTable(String tableName,List<ColumnPoolObj> columnpoolList,boolean droporign);
	public void dropTable(String tableName) throws DAOException;
	public Collection<String> getTableFieldNames(String tableName) throws DAOException;

}
