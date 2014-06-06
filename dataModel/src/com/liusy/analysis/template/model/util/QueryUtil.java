
package com.liusy.analysis.template.model.util;

import com.liusy.analysis.template.model.Diagram;
import com.liusy.analysis.template.model.base.Metadata;
import java.sql.*;
import java.util.*;

public class QueryUtil
{

	public QueryUtil()
	{
	}

	public static boolean MetaEquals(List meta1, List meta2)
	{
		if (meta1 == null || meta2 == null)
			return false;
		if (meta1.size() != meta2.size())
			return false;
		for (Iterator iterator = meta1.iterator(); iterator.hasNext();)
		{
			Metadata mt = (Metadata)iterator.next();
			if (!isExists(mt, meta2))
				return false;
		}

		return true;
	}

	private static boolean isExists(Metadata meta, List metas)
	{
		if (meta == null || metas == null)
			return false;
		for (Iterator iterator = metas.iterator(); iterator.hasNext();)
		{
			Metadata mt = (Metadata)iterator.next();
			if (meta.getName().equals(mt.getName()) && meta.getCnName().equals(mt.getCnName()) && meta.getDataType().equals(mt.getDataType()))
				return true;
		}

		return false;
	}

	public static List query(Diagram diagram, String sql)
	{
		Connection dbConnection;
		ResultSet rs;
		Statement stat;
		dbConnection = null;
		rs = null;
		stat = null;
		List list = null;
		try {
		dbConnection = diagram.getDbConnection();
		stat = dbConnection.createStatement();
		rs = stat.executeQuery(sql);
		dbConnection.commit();
		List returnDataSet = loadData(rs);
		list = returnDataSet;
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (rs != null)
			try
			{
				rs.close();
				rs = null;
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
		if (stat != null)
			try
			{
				stat.close();
				stat = null;
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}

		try
		{
			dbConnection.rollback();
		}
		catch (Exception ex)
		{
			ex.printStackTrace();
		}
		if (rs != null)
			try
			{		
				rs.close();
				rs = null;
				return list;
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
		if (stat != null)
			try
			{
				stat.close();
				stat = null;
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
		
		if (rs != null)
			try
			{
				rs.close();
				rs = null;
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
		if (stat != null)
			try
			{
				stat.close();
				stat = null;
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
		return list;
	}

	private static List loadData(ResultSet resultSet)
		throws SQLException
	{
		ResultSetMetaData metaData = resultSet.getMetaData();
		List columns = new ArrayList();
		List columnTypes = new ArrayList();
		for (int i = 0; i < metaData.getColumnCount(); i++)
		{
			columns.add(metaData.getColumnName(i + 1));
			columnTypes.add(Integer.valueOf(metaData.getColumnType(i + 1)));
		}

		List list = new ArrayList();
		Map recordMap;
		for (; resultSet.next(); list.add(recordMap))
		{
			recordMap = new HashMap();
			for (int i = 0; i < columns.size(); i++)
				if (((Integer)columnTypes.get(i)).intValue() == 1)
				{
					String s = (String)resultSet.getObject(i + 1);
					recordMap.put((String)columns.get(i), s.trim());
				} else
				{
					recordMap.put((String)columns.get(i), resultSet.getObject(i + 1));
				}

		}

		return list;
	}
}
