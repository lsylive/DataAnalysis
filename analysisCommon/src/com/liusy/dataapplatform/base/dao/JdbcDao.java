


//   JdbcDao.java

package com.liusy.dataapplatform.base.dao;

import com.liusy.core.util.Const;
import com.liusy.dataapplatform.base.exception.DAOException;
import com.liusy.dataapplatform.base.util.BaseSqlGen;
import com.liusy.dataapplatform.base.util.PageQuery;
import com.liusy.dataapplatform.base.util.SplitPageResultSetExtractor;
import com.liusy.datapp.util.poolobj.ColumnPoolObj;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.sql.DataSource;
import org.apache.commons.logging.Log;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

// Referenced classes of package com.liusy.dataapplatform.base.dao:
//			ISqlDao

public abstract class JdbcDao extends JdbcDaoSupport
	implements ISqlDao
{

	private DataSource dataSource;
	private BaseSqlGen sqlGen;

	public JdbcDao()
	{
	}

	public PageQuery queryBySql(String sqlstr, PageQuery pageQuery)
		throws DAOException
	{
		List list = null;
		String querySQL = sqlstr;
		if (logger.isInfoEnabled())
			logger.info((new StringBuilder()).append("querySQL: ").append(querySQL).toString());
		String sumSQL = sqlGen.generateCountSql(querySQL);
		if (Integer.parseInt(pageQuery.getPageSize()) > 0)
		{
			String pageSQL = querySQL;
			if (pageQuery.getOrder() != null && !"".equals(pageQuery.getOrder()))
				pageSQL = (new StringBuilder(String.valueOf(pageSQL))).append(" order by ").append(pageQuery.getOrder()).append(" ").append(pageQuery.getOrderDirection()).toString();
			if (logger.isDebugEnabled())
			{
				logger.debug((new StringBuilder()).append("sumSQL: ").append(sumSQL).toString());
				logger.debug((new StringBuilder()).append("pageSQL: ").append(pageSQL).toString());
			}
			int total = ((Integer)getJdbcTemplate().query(sumSQL, new ResultSetExtractor() {


				public Object extractData(ResultSet rs)
					throws SQLException, DataAccessException
				{
					rs.next();
					return new Integer(rs.getInt(1));
				}
			})).intValue();
			pageQuery.setRecordCount(String.valueOf(total));
			if (total > 0)
			{
				int pages = total / Integer.parseInt(pageQuery.getPageSize());
				if (total % Integer.parseInt(pageQuery.getPageSize()) != 0)
					pages++;
				int pageNumber = Integer.parseInt(pageQuery.getPageNumber());
				if (pageNumber > pages)
					pageQuery.setPageNumber(String.valueOf(pages));
				pageQuery.setPageCount(String.valueOf(pages));
				list = queryItemList(pageQuery, pageSQL);
			} else
			{
				list = new ArrayList();
				pageQuery.setPageCount("0");
			}
		} else
		{
			list = queryItemList(pageQuery, querySQL);
			int len1 = list.size();
			pageQuery.setRecordCount(String.valueOf(list.size()));
			pageQuery.setPageCount("1");
		}
		pageQuery.setRecordSet(list);
		return pageQuery;
	}

	public List queryBySql(String sqlstr)
		throws DAOException
	{
		List list = null;
		String querySQL = sqlstr;
		if (logger.isInfoEnabled())
			logger.info((new StringBuilder()).append("querySQL: ").append(querySQL).toString());
		PageQuery pageQuery = new PageQuery();
		pageQuery.setPageSize(Const.MAX_PAGE_SIZE);
		String sumSQL = sqlGen.generateCountSql(querySQL);
		int total = ((Integer)getJdbcTemplate().query(sumSQL, new ResultSetExtractor() {

			public Object extractData(ResultSet rs)
				throws SQLException, DataAccessException
			{
				rs.next();
				return new Integer(rs.getInt(1));
			}
		})).intValue();
		pageQuery.setRecordCount(String.valueOf(total));
		String pageSQL = sqlGen.generatePageSql(querySQL, pageQuery);
		list = queryItemList(pageQuery, pageSQL);
		return list;
	}

	public int queryCountBySql(String querySQL)
		throws DAOException
	{
		String sumSQL = sqlGen.generateCountSql(querySQL);
		return ((Integer)getJdbcTemplate().query(sumSQL, new ResultSetExtractor() {

			public Object extractData(ResultSet rs)
				throws SQLException, DataAccessException
			{
				rs.next();
				return new Integer(rs.getInt(1));
			}
		})).intValue();
	}

	public int queryByInt(String querySQL)
		throws DAOException
	{
		return ((Integer)getJdbcTemplate().query(querySQL, new ResultSetExtractor() {

			public Object extractData(ResultSet rs)
				throws SQLException, DataAccessException
			{
				rs.next();
				return new Integer(rs.getInt(1));
			}
		})).intValue();
	}

	public PageQuery queryBySqlAndPage(String sqlstr, PageQuery pageQuery, int minId)
		throws DAOException
	{
		List list = null;
		String querySQL = sqlGen.generatePageSqlById(sqlstr, pageQuery, minId);
		if (logger.isInfoEnabled())
			logger.info((new StringBuilder()).append("querySQL: ").append(querySQL).toString());
		String sumSQL = sqlGen.generateCountSql(querySQL);
		String pageSQL = querySQL;
		if (pageQuery.getOrder() != null && !"".equals(pageQuery.getOrder()))
			pageSQL = (new StringBuilder(String.valueOf(pageSQL))).append(" order by ").append(pageQuery.getOrder()).append(" ").append(pageQuery.getOrderDirection()).toString();
		if (logger.isDebugEnabled())
		{
			logger.debug((new StringBuilder()).append("sumSQL: ").append(sumSQL).toString());
			logger.debug((new StringBuilder()).append("pageSQL: ").append(pageSQL).toString());
		}
		list = queryItemList1(pageQuery, pageSQL);
		pageQuery.setRecordSet(list);
		return pageQuery;
	}

	private List queryItemList(PageQuery qs, String pageSQL)
	{
		int pageNum = Integer.parseInt(qs.getPageNumber());
		int pageSize = Integer.parseInt(qs.getPageSize());
		int start = 0;
		int end = 0;
		if (pageSize != 0)
		{
			start = (pageNum - 1) * pageSize;
			end = pageNum * pageSize;
		}
		Object ret = getJdbcTemplate().query(pageSQL, new SplitPageResultSetExtractor(start, end));
		return (List)ret;
	}

	public void batchUpdate(String sql, List resultList, List columnpoolList)
		throws DAOException
	{
		final List list = resultList;
		final List colList = columnpoolList;
		BatchPreparedStatementSetter setter = new BatchPreparedStatementSetter() {
			public int getBatchSize()
			{
				return list.size();
			}

			public void setValues(PreparedStatement ps, int i)
				throws SQLException
			{
				Map resultMap = (Map)list.get(i);
				try
				{
					for (int pos = 0; pos < colList.size(); pos++)
					{
						ColumnPoolObj poolobj = (ColumnPoolObj)colList.get(pos);
						String value = (String)resultMap.get(poolobj.getName().toUpperCase());
						if (value == null)
							value = (String)resultMap.get(poolobj.getName().toLowerCase());
						if (poolobj.getDataType().equals(Const.META_TYPE_STRING))
						{
							if (value != null)
								ps.setString(pos + 1, value);
							else
								ps.setNull(pos + 1, 12);
						} else
						if (poolobj.getDataType().equals(Const.META_TYPE_NUMERIC))
						{
							if (value == null || value.equals(""))
								ps.setNull(pos + 1, 8);
							else
								ps.setDouble(pos + 1, Double.valueOf(value).doubleValue());
						} else
						if (poolobj.getDataType().equals(Const.META_TYPE_DATE))
						{
							SimpleDateFormat oformat = new SimpleDateFormat("yyyy-MM-dd");
							SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
							if (value == null || value.equals(""))
							{
								ps.setNull(pos + 1, 91);
							} else
							{
								Date date = null;
								if (value.contains(":"))
									date = new Date(format.parse(value).getTime());
								else
									date = new Date(oformat.parse(value).getTime());
								ps.setDate(pos + 1, date);
							}
						} else
						if (value != null)
							ps.setString(pos + 1, value);
						else
							ps.setNull(pos + 1, 12);
					}

				}
				catch (SQLException e)
				{
					e.printStackTrace();
					throw e;
				}
				catch (Exception e)
				{
					e.printStackTrace();
					throw new SQLException("data type mismatch");
				}
			}
		};
		try
		{
			getJdbcTemplate().batchUpdate(sql, setter);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			throw new DAOException(e);
		}
	}

	public void executeUpdate(String sql)
		throws DAOException
	{
		try
		{
			getJdbcTemplate().execute(sql);
		}
		catch (Exception e)
		{
			throw new DAOException(e);
		}
	}

	public void setSqlGen(BaseSqlGen sqlGen)
	{
		this.sqlGen = sqlGen;
	}

	public int getMinId(String strSQL)
		throws DAOException
	{
		String minIdSql = sqlGen.generateMinIdSql(strSQL);
		return queryByInt(minIdSql);
	}

	private List queryItemList1(PageQuery qs, String pageSQL)
	{
		int pageNum = Integer.parseInt(qs.getPageNumber());
		int pageSize = Integer.parseInt(qs.getPageSize());
		int start = 0;
		int end = 0;
		if (pageSize != 0)
		{
			start = (pageNum - 1) * pageSize;
			end = pageNum * pageSize;
		}
		final List list = new ArrayList();
		return (List)getJdbcTemplate().query(pageSQL, new ResultSetExtractor() {

			public Object extractData(ResultSet rs)
				throws SQLException, DataAccessException
			{
				ResultSetMetaData rsmd = rs.getMetaData();
				int count = rsmd.getColumnCount();
				String columnName[] = new String[count];
				String typeName[] = new String[count];
				for (int k = 0; k < count; k++)
				{
					columnName[k] = rsmd.getColumnName(k + 1);
					typeName[k] = rsmd.getColumnTypeName(k + 1);
				}

				Map map;
				for (; rs.next(); list.add(map))
				{
					map = new HashMap();
					for (int i = 0; i < count; i++)
					{
						Object obj = rs.getObject(i + 1);
						if (rs.wasNull())
							map.put(columnName[i], "");
						else
						if (typeName[i].equalsIgnoreCase("DATE"))
						{
							SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
							Date date = rs.getDate(i + 1);
							String datestr = format.format(date);
							map.put(columnName[i], datestr);
						} else
						if (typeName[i].equalsIgnoreCase("TIMESTAMP"))
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
				}
				return list;
			}
		});
	}
}
