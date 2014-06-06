package com.liusy.datapp.dao.query.impl;


import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.liusy.dataapplatform.base.dao.JdbcDao;
import com.liusy.dataapplatform.base.exception.DAOException;
import com.liusy.dataapplatform.base.util.QueryParam;
import com.liusy.datapp.dao.query.SynthesisTempSpaceDao;
import com.liusy.datapp.util.meta.SqlScriptGenerator;
import com.liusy.datapp.util.poolobj.ColumnPoolObj;

public class SynthesisTempSpaceDaoImpl extends JdbcDao implements SynthesisTempSpaceDao {
	public boolean isTableExist(String tableName) throws DAOException{
		try{
		String sql=sqlScriptGenerator.isDbExistSql(tableName);
		int count=queryByInt(sql);
		if(count>0)
			return true;
		else
			return false;
		}catch (Exception e) {
			e.printStackTrace();
			throw new DAOException(e);
		}
	}
	public boolean isTableHasRecord(String tableName) throws DAOException{
		try{
			String sql="select count(id) from "+tableName.toUpperCase();
			int count=queryByInt(sql);
			if(count>0)
				return true;
			else
				return false;
		}catch (Exception e) {
			e.printStackTrace();
			throw new DAOException(e);
		}
	}
	
	public void createTable(String tableName,List<ColumnPoolObj> columnpoolList,boolean droporign){
		try{
			List<Map<String, String>> columnList=new ArrayList<Map<String,String>>();
			if(droporign)
				dropTable(tableName);
			for(ColumnPoolObj obj:columnpoolList){
				Map<String,String> map=new HashMap<String, String>();
				map.put("columnName", obj.getName());
				map.put("columnType", obj.getDataType());
				map.put("isNullable", "Y"); //默认字段都可以为空
				map.put("precise", obj.getDataPercise());
				map.put("columnLength", obj.getDataLength());
				columnList.add(map);
			}
			String createSql=sqlScriptGenerator.generateCreateTableScript(columnList, tableName);
			executeUpdate(createSql);
		}catch (Exception e) {
			e.printStackTrace();
			throw new DAOException(e);
		}
	}
	public void dropTable(String tableName) throws DAOException{
		try{
			String dropsql=sqlScriptGenerator.generateDropTableScript(tableName);
			executeUpdate(dropsql);
		}catch (Exception e) {
			e.printStackTrace();
			throw new DAOException(e);
		}
	}
	private SqlScriptGenerator sqlScriptGenerator;
	public void setSqlScriptGenerator(SqlScriptGenerator sqlScriptGenerator) {
		this.sqlScriptGenerator = sqlScriptGenerator;
	}
	public String getQueryParamSql(List<QueryParam> paramList)
			throws DAOException {
		// TODO Auto-generated method stub
		return null;
	}
	public Collection<String> getTableFieldNames(String tableName)
			throws DAOException {
		String columnsSql = sqlScriptGenerator.gererateGetTableFieldNamesSql(tableName);
		List<Map> result = queryBySql(columnsSql);
		Collection<String> fieldNames = new ArrayList<String>();
		for(Map record:result)
		{
			fieldNames.add(record.get("COLUMN_NAME").toString());
		}
		return fieldNames;
	}

	
	

}
