


//   SplitPageResultSetExtractor.java

package com.liusy.dataapplatform.base.util;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Date;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

public class SplitPageResultSetExtractor
	implements ResultSetExtractor
{

	private final int start;
	private final int len;

	public SplitPageResultSetExtractor(int start, int len)
	{
		this.start = start;
		this.len = len;
	}

	public Object extractData(ResultSet rs)
		throws SQLException, DataAccessException
	{
		return wrapMapper(rs, start, len);
	}

	public List wrapMapper(ResultSet rs, int start, int len)
		throws SQLException, DataAccessException
	{
		int end = start + len;
		boolean allcode = false;
		if (end == 0)
			allcode = true;
		int rowNum = 0;
		List list = new ArrayList();
		ResultSetMetaData rsmd = rs.getMetaData();
		int count = rsmd.getColumnCount();
		String columnName[] = new String[count];
		int typeName[] = new int[count];
		for (int k = 0; k < count; k++)
		{
			columnName[k] = rsmd.getColumnName(k + 1);
			typeName[k] = rsmd.getColumnType(k + 1);
		}

		while (rs.next()) 
		{
			rowNum++;
			if (!allcode)
			{
				if (rowNum <= start)
					continue;
				if (rowNum > end)
					break;
			}
			Map map = new HashMap();
			for (int i = 0; i < count; i++)
			{
				Object obj = rs.getObject(i + 1);
				if (rs.wasNull())
					map.put(columnName[i], "");
				else
				if (typeName[i] == 91)
				{
					SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
					Date date = rs.getDate(i + 1);
					String datestr = format.format(date);
					map.put(columnName[i], datestr);
				} else
				if (typeName[i] == 93)
				{
					SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					Timestamp stamp = rs.getTimestamp(i + 1);
					String datestr = format.format(new Date(stamp.getTime()));
					map.put(columnName[i], datestr);
				} else
				{
					map.put(columnName[i], rs.getObject(i + 1).toString().trim());
				}
			}

			list.add(map);
		}
		return list;
	}
}
