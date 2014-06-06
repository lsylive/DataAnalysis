


//   HibernateDao.java

package com.liusy.dataapplatform.base.dao;

import com.liusy.core.util.Const;
import com.liusy.dataapplatform.base.exception.DAOException;
import com.liusy.dataapplatform.base.util.BaseSqlGen;
import com.liusy.dataapplatform.base.util.Condition;
import com.liusy.dataapplatform.base.util.ICondition;
import com.liusy.dataapplatform.base.util.PageQuery;
import com.liusy.dataapplatform.base.util.QueryFactory;
import com.liusy.dataapplatform.base.util.SqlParam;
import com.liusy.datapp.util.poolobj.ColumnPoolObj;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.support.lob.DefaultLobHandler;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

// Referenced classes of package com.liusy.dataapplatform.base.dao:
//			BaseDao

public abstract class HibernateDao extends HibernateDaoSupport
	implements BaseDao
{

	private final Log logger = LogFactory.getLog(getClass());
	private JdbcTemplate jdbcTemplate;
	private BaseSqlGen sqlGen;
	private DefaultLobHandler lobHandler;
	private QueryFactory queryFactory;

	public HibernateDao()
	{
	}	
	public List commonQuery(final Collection conditions, final Collection orders, final int currpage, final int pagesize)
		throws DAOException
	{
		HibernateCallback callback = new HibernateCallback() {
			public Object doInHibernate(Session session)
				throws HibernateException
			{
				Criteria criteria = session.createCriteria(getReferenceClass());
				if (conditions != null)
					generateQueryExpression(conditions, criteria);
				if (orders != null)
					generateOrderExpression(orders, criteria);
				if (pagesize > 0 && currpage > 0)
				{
					criteria.setFirstResult(pagesize * (currpage - 1));
					criteria.setMaxResults(pagesize);
				}
				return criteria.list();
			}

		};
		List list = null;
		try
		{
			list = (List)getHibernateTemplate().execute(callback);
		}
		catch (Exception e)
		{
			throw new DAOException(e);
		}
		return list;
	}

	public List commonQuery(Collection conditions)
		throws DAOException
	{
		return commonQuery(conditions, null, 0, 0);
	}

	public List commonQuery(Collection conditions, Collection orders)
		throws DAOException
	{
		return commonQuery(conditions, orders, 0, 0);
	}

	public List findByHql(String hql)
		throws DAOException
	{
		try {
			return getHibernateTemplate().find(hql);
		} catch (Exception e) {
			throw new DAOException(e);
		}
	}

	public List queryBySql(String sql)
		throws DAOException
	{
		try {
			return jdbcTemplate.queryForList(sql);
		} catch (Exception e) {
			throw new DAOException(e);
		}
	}

	public PageQuery queryBySelectId(PageQuery pageQuery)
		throws DAOException
	{
		try {
		String queryString1;
		String selectId = pageQuery.getSelectParamId();
		if (selectId == null || selectId.trim().length() == 0)
			throw new IllegalArgumentException("Selectid");
		if (pageQuery == null)
			throw new IllegalArgumentException("missing pagerQueryObject");
		queryString1 = queryFactory.getQuery(selectId);
		if (queryString1 == null)
			throw new DAOException("Error in generate sql");
		return queryByParamter(queryString1, pageQuery);
		} catch (Exception e) {
			throw new DAOException(e);
		}
	}

	private PageQuery queryByParamter(String qs, PageQuery pageQuery)
		throws DAOException
	{
		List list = null;
		String querySQL = sqlGen.generateSqlBySelectId(qs, pageQuery);
		if (logger.isInfoEnabled())
			logger.info((new StringBuilder()).append("querySQL: ").append(querySQL).toString());
		Map params = pageQuery.getParameters();
		Object keyarray[] = params.keySet().toArray();
		if (keyarray.length > 0 && isNumeric(keyarray[0].toString()))
			pageQuery = queryByPreparedParamter(qs, pageQuery);
		else
			pageQuery = queryByReplaceParamter(qs, pageQuery);
		return pageQuery;
	}

	private PageQuery queryByReplaceParamter(String qs, PageQuery pageQuery)
		throws DAOException
	{
		List list = null;
		String querySQL = sqlGen.generateSqlBySelectId(qs, pageQuery);
		if (logger.isInfoEnabled())
			logger.info((new StringBuilder()).append("querySQL: ").append(querySQL).toString());
		Map params = pageQuery.getParameters();
		for (Iterator keyiter = params.keySet().iterator(); keyiter.hasNext();)
		{
			String key = (String)keyiter.next();
			String replacestr = (new StringBuilder("\\$\\{")).append(key).append("\\}").toString();
			String value = (String)params.get(key);
			querySQL = querySQL.replaceAll(replacestr, value);
		}

		String sumSQL = sqlGen.generateCountSql(querySQL);
		int pageSize = 0;
		try
		{
			pageSize = Integer.parseInt(pageQuery.getPageSize());
		}
		catch (Exception e)
		{
			pageSize = Integer.parseInt(Const.DEFAULT_PAGE_SIZE);
		}
		if (pageSize != 0)
		{
			if (pageSize < Integer.parseInt(Const.MIN_PAGE_SIZE))
				pageSize = Integer.parseInt(Const.DEFAULT_PAGE_SIZE);
			else
			if (pageSize > Integer.parseInt(Const.MAX_PAGE_SIZE))
				pageSize = Integer.parseInt(Const.MAX_PAGE_SIZE);
			pageQuery.setPageSize(String.valueOf(pageSize));
			int total = ((Integer)jdbcTemplate.query(sumSQL, new ResultSetExtractor() {
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
				pageQuery.setPageCount(String.valueOf(pages));
				if (Integer.parseInt(pageQuery.getPageNumber()) > pages)
					pageQuery.setPageNumber(String.valueOf(pages));
				String pageSQL = sqlGen.generatePageSql(querySQL, pageQuery);
				if (logger.isDebugEnabled())
				{
					logger.debug((new StringBuilder()).append("sumSQL: ").append(sumSQL).toString());
					logger.debug((new StringBuilder()).append("pageSQL: ").append(pageSQL).toString());
				}
				list = getResultItems(pageQuery, pageSQL);
			} else
			{
				list = new ArrayList();
				pageQuery.setPageCount("0");
			}
		} else
		{
			list = getResultItems(pageQuery, querySQL);
			int len1 = list.size();
			pageQuery.setRecordCount(String.valueOf(list.size()));
			pageQuery.setPageCount("1");
		}
		pageQuery.setRecordSet(list);
		return pageQuery;
	}

	private PageQuery queryByPreparedParamter(String qs, PageQuery pageQuery)
		throws DAOException
	{
		List list = null;
		try
		{
			String querySQL = sqlGen.generateSqlBySelectId(qs, pageQuery);
			if (logger.isInfoEnabled())
				logger.info((new StringBuilder()).append("querySQL: ").append(querySQL).toString());
			if (Integer.parseInt(pageQuery.getPageSize()) > 0)
			{
				String sumSQL = sqlGen.generateCountSql(querySQL);
				String pageSQL = sqlGen.generatePageSql(querySQL, pageQuery);
				if (logger.isDebugEnabled())
				{
					logger.debug((new StringBuilder()).append("sumSQL: ").append(sumSQL).toString());
					logger.debug((new StringBuilder()).append("pageSQL: ").append(pageSQL).toString());
				}
				Map params = pageQuery.getParameters();
				int len = params.values().size();
				Object paramobj[] = new Object[len];
				for (int i = 0; i < len; i++)
				{
					String columnType = (String)pageQuery.getColumnTypes().get(String.valueOf(i));
					String value = (String)pageQuery.getParameters().get(String.valueOf(i));
					if (columnType.equals("2"))
						paramobj[i] = new Integer(value);
					else
					if (columnType.equals("3"))
						paramobj[i] = new Double(value);
					else
					if (columnType.equals("4"))
						paramobj[i] = new Long(value);
					else
					if (columnType.equals("5"))
					{
						SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
						Date date = new Date(format.parse(value).getTime());
						paramobj[i] = date;
					} else
					if (columnType.equals("6"))
					{
						SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:ss:mm");
						Date date = new Date(format.parse(value).getTime());
						paramobj[i] = date;
					} else
					if (columnType.equals("1"))
						paramobj[i] = new String(value);
				}

				Integer total = Integer.valueOf(jdbcTemplate.queryForInt(sumSQL, paramobj));
				pageQuery.setRecordCount(String.valueOf(total));
				if (total.intValue() > 0)
				{
					int pages = total.intValue() / Integer.parseInt(pageQuery.getPageSize());
					if (total.intValue() % Integer.parseInt(pageQuery.getPageSize()) != 0)
						pages++;
					pageQuery.setPageCount(String.valueOf(pages));
					list = getResultItemsByPrepared(pageQuery, pageSQL);
				} else
				{
					list = new ArrayList();
					pageQuery.setPageCount("0");
				}
			} else
			{
				list = getResultItemsByPrepared(pageQuery, querySQL);
				int len1 = list.size();
				pageQuery.setRecordCount(String.valueOf(list.size()));
				int pages = len1 / Integer.parseInt(pageQuery.getPageSize());
				if (len1 % Integer.parseInt(pageQuery.getPageSize()) != 0)
					pages++;
				pageQuery.setPageCount(String.valueOf(pages));
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
			logger.error(e);
			throw new DAOException(e.getMessage());
		}
		pageQuery.setRecordSet(list);
		return pageQuery;
	}

	private List getResultItems(PageQuery qs, String pageSQL)
	{
		Object ret = jdbcTemplate.query(pageSQL, new ResultSetExtractor() {
			public Object extractData(ResultSet rs)
				throws SQLException, DataAccessException
			{
				List list = new ArrayList();
				if (rs.next())
				{
					ResultSetMetaData rsmd = rs.getMetaData();
					int count = rsmd.getColumnCount();
					do
					{
						Map map = new HashMap();
						for (int i = 0; i < count; i++)
						{
							String columnName = rsmd.getColumnName(i + 1);
							String typeName = rsmd.getColumnTypeName(i + 1);
							Object obj = rs.getObject(i + 1);
							if (rs.wasNull())
								map.put(columnName, "");
							else
							if (typeName.equalsIgnoreCase("DATE"))
							{
								SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
								Date date = rs.getDate(i + 1);
								String datestr = format.format(date);
								map.put(columnName, datestr);
							} else
							if (typeName.equalsIgnoreCase("TIMESTAMP"))
							{
								SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
								Timestamp stamp = rs.getTimestamp(i + 1);
								String datestr = format.format(new Date(stamp.getTime()));
								map.put(columnName, datestr);
							} else
							{
								map.put(columnName, rs.getObject(i + 1).toString().trim());
							}
						}

						list.add(map);
					} while (rs.next());
				}
				return list;
			}

		});
		return (List)ret;
	}

	private List getResultItems(final SqlParam qs, String pageSQL)
	{
		Object ret = jdbcTemplate.query(pageSQL, new ResultSetExtractor() {


			public Object extractData(ResultSet rs)
				throws SQLException, DataAccessException
			{
				List list = new ArrayList();
				if (rs.next())
				{
					String queryField = qs.getFields().trim();
					if (queryField.toLowerCase().startsWith("distinct"))
						queryField = queryField.substring(9);
					StringTokenizer st = new StringTokenizer(queryField, ",");
					int fields_nums = st.countTokens();
					String fields[] = new String[fields_nums];
					int sqlTypes[] = new int[fields_nums];
					do
					{
						Map map = new HashMap();
						for (int i = 0; i < fields_nums; i++)
							map.put(fields[i], rs.getObject(i));

						list.add(map);
					} while (rs.next());
				}
				return list;
			}

		});
		return (List)ret;
	}

	private List getResultItemsByPrepared(final PageQuery pageQuery, final String pageSQL)
	{
		Object ret = jdbcTemplate.query(new PreparedStatementCreator() {
			public PreparedStatement createPreparedStatement(Connection conn)
				throws SQLException
			{
				PreparedStatement ps = conn.prepareStatement(pageSQL);
				int len = pageQuery.getParameters().size();
				try
				{
					for (int i = 0; i < len; i++)
					{
						String columnType = (String)pageQuery.getColumnTypes().get(String.valueOf(i));
						String value = (String)pageQuery.getParameters().get(String.valueOf(i));
						if (columnType.equals("2"))
							ps.setInt(i, (new Integer(value)).intValue());
						else
						if (columnType.equals("3"))
							ps.setDouble(i, (new Double(value)).doubleValue());
						else
						if (columnType.equals("4"))
							ps.setLong(i, (new Long(value)).longValue());
						else
						if (columnType.equals("5"))
						{
							SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
							Date date = new Date(format.parse(value).getTime());
							ps.setDate(i, date);
						} else
						if (columnType.equals("6"))
						{
							SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:ss:mm");
							Date date = new Date(format.parse(value).getTime());
							ps.setDate(i, date);
						} else
						if (columnType.equals("1"))
							ps.setString(i, new String(value));
					}

				}
				catch (Exception e)
				{
					throw new SQLException(e.getMessage());
				}
				return ps;
			}
		}, new ResultSetExtractor() {
			public Object extractData(ResultSet rs)
				throws SQLException, DataAccessException
			{
				return rs;
			}

		});
		return (List)ret;
	}

	private String addResultData(ResultSet rs, String field, int sqlType, int i)
		throws SQLException
	{
		String value = null;
		switch (sqlType)
		{
		case 93: // ']'
			Timestamp t = rs.getTimestamp(field);
			if (t == null)
				break;
			value = t.toString();
			int index = value.indexOf(".");
			if (index > -1)
				value = value.substring(0, index);
			break;

		case 91: // '['
			Date d = rs.getDate(field);
			value = d == null ? "" : d.toString();
			break;

		case 2005: 
			value = lobHandler.getClobAsString(rs, i);
			break;

		case 2004: 
			value = new String(lobHandler.getBlobAsBytes(rs, i));
			break;

		default:
			value = rs.getString(field);
			break;
		}
		return value == null ? "" : value;
	}

	public String getClassName(Class refClass)
	{
		return refClass.getSimpleName();
	}

	public long count()
		throws DAOException
	{
		return countByFilter(null);
	}
	

	protected Object get(Serializable key)
		throws DAOException
	{
		try {
			return getHibernateTemplate().get(getReferenceClass(), key);
		} catch (Exception e) {
			throw new DAOException(e);
		}
	}

	protected Object load(Serializable key)
	{
		try {
			return getHibernateTemplate().load(getReferenceClass(), key);
		} catch (Exception e) {
			throw new DAOException(e);
		}
	}

	public List findAll()
	{
		return getHibernateTemplate().find((new StringBuilder("from ")).append(getClassName(getReferenceClass())).toString());
	}

	public List findByFilter(Collection conditions)
		throws DAOException
	{
		return commonQuery(conditions);
	}

	public List findByField(String fieldName, Object fieldValue)
		throws DAOException
	{
		if (fieldValue == null)
		{
			String hql = (new StringBuilder("from ")).append(getClassName(getReferenceClass())).append(" a where a.").append(fieldName).append(" is null").toString();
			return getHibernateTemplate().find(hql);
		} else
		{
			String hql = (new StringBuilder("from ")).append(getClassName(getReferenceClass())).append(" a where a.").append(fieldName).append("=:fieldValue").toString();
			return getHibernateTemplate().findByNamedParam(hql, "fieldValue", fieldValue);
		}
	}

	public List findByField(String fieldName, Object fieldValue, String orderName, boolean ascending)
		throws DAOException
	{
		String orderstr = "";
		if (orderName != null && !"".equals(orderName))
			orderstr = (new StringBuilder(" order by a.")).append(orderName).append(ascending ? " Asc" : " Desc").toString();
		if (fieldValue == null)
		{
			String hql = (new StringBuilder("from ")).append(getClassName(getReferenceClass())).append(" a where a.").append(fieldName).append(" is null").append(orderstr).toString();
			return getHibernateTemplate().find(hql);
		} else
		{
			String hql = (new StringBuilder("from ")).append(getClassName(getReferenceClass())).append(" a where a.").append(fieldName).append("=:fieldValue").append(orderstr).toString();
			return getHibernateTemplate().findByNamedParam(hql, "fieldValue", fieldValue);
		}
	}

	public List findByFields(String fieldName[], Object fieldValue[], String orderName[], boolean ascending[])
		throws DAOException
	{
		String hql = "";
		StringBuffer buffer = new StringBuffer();
		buffer.append((new StringBuilder("from ")).append(getClassName(getReferenceClass())).append(" a where 1=1").toString());
		if (fieldName != null && fieldName.length > 0)
		{
			if (fieldName.length != fieldValue.length)
				throw new DAOException("Parameter count error.");
			for (int i = 0; i < fieldName.length; i++)
			{
				if (fieldName[i] == null)
					throw new DAOException("Parameter name error.");
				if (fieldValue[i] == null)
					buffer.append((new StringBuilder(" and a.")).append(fieldName[i]).append(" is null").toString());
				else
					buffer.append((new StringBuilder(" and a.")).append(fieldName[i]).append("=:").append(fieldName[i]).toString());
			}

		}
		if (orderName != null && orderName.length > 0)
		{
			if (orderName.length != ascending.length)
				throw new DAOException("OrderName count error.");
			buffer.append(" order by");
			for (int i = 0; i < orderName.length; i++)
			{
				if (orderName[i] == null)
					throw new DAOException("OrderName name error.");
				buffer.append((new StringBuilder(" a.")).append(orderName[i]).append(ascending[i] ? " Asc" : " Desc").toString());
				if (i != orderName.length - 1)
					buffer.append(",");
			}

		}
		hql = buffer.toString();
		return getHibernateTemplate().findByNamedParam(hql, fieldName, fieldValue);
	}

	public List findByFields(String fieldName[], Object fieldValue[], String orderName, boolean ascending)
		throws DAOException
	{
		String hql = "";
		StringBuffer buffer = new StringBuffer();
		buffer.append((new StringBuilder("from ")).append(getClassName(getReferenceClass())).append(" a where 1=1").toString());
		if (fieldName != null && fieldName.length > 0)
		{
			if (fieldName.length != fieldValue.length)
				throw new DAOException("Parameter count error.");
			for (int i = 0; i < fieldName.length; i++)
			{
				if (fieldName[i] == null)
					throw new DAOException("Parameter name error.");
				if (fieldValue[i] == null)
					buffer.append((new StringBuilder(" and a.")).append(fieldName[i]).append(" is null").toString());
				else
					buffer.append((new StringBuilder(" and a.")).append(fieldName[i]).append("=:").append(fieldName[i]).toString());
			}

			if (orderName != null)
				buffer.append((new StringBuilder(" order by a.")).append(orderName).append(ascending ? " Asc" : " Desc").toString());
		}
		hql = buffer.toString();
		return getHibernateTemplate().findByNamedParam(hql, fieldName, fieldValue);
	}

	public List findByFields(String fieldName[], Object fieldValue[])
		throws DAOException
	{
		String hql = "";
		StringBuffer buffer = new StringBuffer();
		buffer.append((new StringBuilder("from ")).append(getClassName(getReferenceClass())).append(" a where 1=1").toString());
		if (fieldName != null && fieldName.length > 0)
		{
			if (fieldName.length != fieldValue.length)
				throw new DAOException("Parameter count error.");
			for (int i = 0; i < fieldName.length; i++)
			{
				if (fieldName[i] == null)
					throw new DAOException("Parameter name error.");
				if (fieldValue[i] == null)
					buffer.append((new StringBuilder(" and a.")).append(fieldName[i]).append(" is null").toString());
				else
					buffer.append((new StringBuilder(" and a.")).append(fieldName[i]).append("=:").append(fieldName[i]).toString());
			}

		}
		hql = buffer.toString();
		return getHibernateTemplate().findByNamedParam(hql, fieldName, fieldValue);
	}

	public List findAll(Order defaultOrder)
		throws DAOException
	{
		List list = new ArrayList();
		list.add(defaultOrder);
		return commonQuery(null, list);
	}

	public int countByField(String fieldName, Object fieldValue)
		throws DAOException
	{
		String hql;
		if (fieldValue == null)
			hql = (new StringBuilder("select count(*) from ")).append(getClassName(getReferenceClass())).append(" a where a.").append(fieldName).append(" is null").toString();
		else
			hql = (new StringBuilder("select count(*) from ")).append(getClassName(getReferenceClass())).append(" a where a.").append(fieldName).append("=:fieldValue").toString();
		return ((Long)getHibernateTemplate().findByNamedParam(hql, "fieldValue", fieldValue).iterator().next()).intValue();
	}

	protected Serializable save(Object obj)
		throws DAOException
	{
		return getHibernateTemplate().save(obj);
	}

	protected void saveOrUpdate(Object obj)
		throws DAOException
	{
		try
		{
			getHibernateTemplate().saveOrUpdate(obj);
		}
		catch (HibernateException e)
		{
			throw new DAOException(e);
		}
	}

	public void saveOrUpdateAll(Collection collection)
		throws DAOException
	{
		try
		{
			getHibernateTemplate().saveOrUpdateAll(collection);
		}
		catch (HibernateException e)
		{
			throw new DAOException(e);
		}
	}

	protected void update(Object obj)
		throws DAOException
	{
		try
		{
			getHibernateTemplate().update(obj);
		}
		catch (HibernateException e)
		{
			throw new DAOException(e);
		}
	}

	public void deleteAll(Collection collection)
		throws DAOException
	{
		try
		{
			getHibernateTemplate().deleteAll(collection);
		}
		catch (HibernateException e)
		{
			throw new DAOException(e);
		}
	}

	public void deleteAll()
		throws DAOException
	{
		executeUpdate((new StringBuilder("delete from ")).append(getClassName(getReferenceClass())).toString());
	}

	public void deleteAll(Serializable ids[])
		throws DAOException
	{
		Serializable aserializable[];
		int j = (aserializable = ids).length;
		for (int i = 0; i < j; i++)
		{
			Serializable id = aserializable[i];
			delete(id);
		}

	}

	public void deleteByField(String fieldName, Object fieldValue)
	{
		List removeList = findByField(fieldName, fieldValue);
		if (removeList != null && !removeList.isEmpty())
			getHibernateTemplate().deleteAll(removeList);
	}

	protected void delete(Object obj)
		throws DAOException
	{
		try
		{
			getHibernateTemplate().delete(obj);
		}
		catch (HibernateException e)
		{
			throw new DAOException(e);
		}
	}

	public void delete(Serializable id)
		throws DAOException
	{
		try
		{
			getHibernateTemplate().delete(get(id));
		}
		catch (HibernateException e)
		{
			throw new DAOException(e);
		}
	}

	protected void refresh(Object obj)
		throws DAOException
	{
		try
		{
			getHibernateTemplate().refresh(obj);
		}
		catch (HibernateException e)
		{
			throw new DAOException(e);
		}
	}

	protected Criteria generateQueryExpression(Collection conditions, Criteria criteria)
		throws HibernateException
	{
		ICondition condition;
		for (Iterator iterator = conditions.iterator(); iterator.hasNext(); criteria.add(condition.generateExpression()))
			condition = (ICondition)iterator.next();

		return criteria;
	}

	protected Criteria generateOrderExpression(Collection orders, Criteria criteria)
		throws HibernateException
	{
		Order order;
		for (Iterator iterator = orders.iterator(); iterator.hasNext(); criteria.addOrder(order))
			order = (Order)iterator.next();

		return criteria;
	}

	protected boolean executeUpdate(final String hql)
		throws DAOException
	{
		try {
			
		HibernateCallback callback;
		Boolean ret;
		callback = new HibernateCallback() {

			public Object doInHibernate(Session session)
				throws HibernateException
			{
				try {

					Query query = session.createQuery(hql);
					query.executeUpdate();
					return new Boolean(true);	
				} catch (Exception e) {
					e.printStackTrace();
				}
				return new Boolean(false);
			}
		};
		ret = Boolean.valueOf(false);
		ret = Boolean.valueOf(((Boolean)getHibernateTemplate().execute(callback)).booleanValue());
		return ret.booleanValue();
} catch (Exception e) {
	throw new DAOException(e);
}
	}

	public abstract Class getReferenceClass();

	public long countByFilter(final Collection conditions)
		throws DAOException
	{
		try {
			
		HibernateCallback callback = new HibernateCallback() {

			public Object doInHibernate(Session session)
				throws HibernateException
			{
				int i =0;
				StringBuffer sql = new StringBuffer();
				sql.append("select count(*) from ");
				sql.append((new StringBuilder(String.valueOf(getClassName(getReferenceClass())))).append(" tablename_0").toString());
				if (conditions != null && !conditions.isEmpty())
				{
					sql.append(" where ");
					boolean isFirst = true;
					for (Iterator iter = conditions.iterator(); iter.hasNext();)
					{
						Condition obj = (Condition)iter.next();
						if (isFirst)
						{
							isFirst = false;
							sql.append(obj.toSQLString("tablename_0"));
						} else
						{
							sql.append(" and ");
							sql.append(obj.toSQLString("tablename_0"));
						}
					}

				}
				Query query = session.createQuery(sql.toString());
				if (conditions != null && !conditions.isEmpty())
				{
					i = 0;
					Condition condition;
					for (Iterator iter = conditions.iterator(); iter.hasNext(); setQueryValues(query, condition))
						condition = (Condition)iter.next();

				}
				return query.list();
			}

			private void setQueryValues(Query query, Condition condition)
			{
				int i = 0;
				if (condition.getState() == "EQUALS" || condition.getState() == "LIKE" || condition.getState() == "GREATNESS" || condition.getState() == "GREATNESS_AND_EQUALS" || condition.getState() == "SMALLNESS" || condition.getState() == "SMALLNESS_AND_EQUALS")
				{
					setQuery(query, i, condition.getValue());
					i++;
				} else
				if (condition.getState() == "BETWEEN")
				{
					setQuery(query, i, condition.getValues()[0]);
					i++;
					setQuery(query, i, condition.getValues()[1]);
					i++;
				} else
				if (condition.getState() == "IN")
				{
					Object objs[] = condition.getValues();
					for (int j = 0; j < objs.length; j++)
					{
						setQuery(query, i, objs[j]);
						i++;
					}

				} else
				if (condition.getState() == "OR")
				{
					Object objs[] = condition.getValues();
					setQueryValues(query, (Condition)objs[0]);
					setQueryValues(query, (Condition)objs[1]);
				} else
				if (condition.getState() == "NOT")
					setQueryValues(query, (Condition)condition.getValue());
			}

			private void setQuery(Query query, int i, Object obj)
			{
				if (obj instanceof String)
					query.setString(i, (String)obj);
				else
				if (obj instanceof Integer)
					query.setInteger(i, ((Integer)obj).intValue());
				else
				if (obj instanceof Long)
					query.setLong(i, ((Long)obj).longValue());
				else
				if (obj instanceof Date)
					query.setDate(i, (Date)obj);
			}
		};
		List listCount = (List)getHibernateTemplate().execute(callback);
		if (listCount != null && !listCount.isEmpty())
			return ((Long)listCount.get(0)).longValue();
		else
			return 0L;
	
		} catch (Exception e) {

			throw new DAOException(e);}
	}

	public void deleteByFilter(final Collection conditions)
		throws DAOException
	{
		HibernateCallback callback = new HibernateCallback() {

			public Object doInHibernate(Session session)
				throws HibernateException
			{
				StringBuffer sql = new StringBuffer();
				sql.append("delete from ");
				sql.append((new StringBuilder(String.valueOf(getClassName(getReferenceClass())))).append(" tablename_0").toString());
				if (conditions != null && !conditions.isEmpty())
				{
					sql.append(" where ");
					boolean isFirst = true;
					for (Iterator iter = conditions.iterator(); iter.hasNext();)
					{
						Condition obj = (Condition)iter.next();
						if (isFirst)
						{
							isFirst = false;
							sql.append(obj.toSQLString("tablename_0"));
						} else
						{
							sql.append(" and ");
							sql.append(obj.toSQLString("tablename_0"));
						}
					}

				}
				Query query = session.createQuery(sql.toString());
				if (conditions != null && !conditions.isEmpty())
				{
					Condition condition;
					for (Iterator iter = conditions.iterator(); iter.hasNext(); setQueryValues(query, condition))
						condition = (Condition)iter.next();

				}
				return Integer.valueOf(query.executeUpdate());
			}

			private void setQueryValues(Query query, Condition condition)
			{
				
				int i = 0;
				if (condition.getState() == "EQUALS" || condition.getState() == "LIKE" || condition.getState() == "GREATNESS" || condition.getState() == "GREATNESS_AND_EQUALS" || condition.getState() == "SMALLNESS" || condition.getState() == "SMALLNESS_AND_EQUALS")
				{
					setQuery(query, i, condition.getValue());
					i++;
				} else
				if (condition.getState() == "BETWEEN")
				{
					setQuery(query, i, condition.getValues()[0]);
					i++;
					setQuery(query, i, condition.getValues()[1]);
					i++;
				} else
				if (condition.getState() == "IN")
				{
					Object objs[] = condition.getValues();
					for (int j = 0; j < objs.length; j++)
					{
						setQuery(query, i, objs[j]);
						i++;
					}

				} else
				if (condition.getState() == "OR")
				{
					Object objs[] = condition.getValues();
					setQueryValues(query, (Condition)objs[0]);
					setQueryValues(query, (Condition)objs[1]);
				} else
				if (condition.getState() == "NOT")
					setQueryValues(query, (Condition)condition.getValue());
			}

			private void setQuery(Query query, int i, Object obj)
			{
				if (obj instanceof String)
					query.setString(i, (String)obj);
				else
				if (obj instanceof Integer)
					query.setInteger(i, ((Integer)obj).intValue());
				else
				if (obj instanceof Long)
					query.setLong(i, ((Long)obj).longValue());
				else
				if (obj instanceof Date)
					query.setDate(i, (Date)obj);
			}
		};
		try
		{
			getHibernateTemplate().execute(callback);
		}
		catch (HibernateException e)
		{
			throw new DAOException(e);
		}
	}

	public void executeSqlUpdate(String sql)
		throws DAOException
	{
		try
		{
			jdbcTemplate.execute(sql);
		}
		catch (Exception e)
		{
			throw new DAOException(e);
		}
	}

	public void batchUpdate(String sql, List resultList, List columnpoolList)
		throws DAOException
	{
		final List list = resultList;
		final List colList = columnpoolList;
		BatchPreparedStatementSetter setter = new BatchPreparedStatementSetter() {

			public int getBatchSize()
			{
				return 100;
			}

			public void setValues(PreparedStatement ps, int pos)
				throws SQLException
			{
				Map resultMap = (Map)list.get(pos);
				try
				{
					for (Iterator iterator = colList.iterator(); iterator.hasNext();)
					{
						ColumnPoolObj poolobj = (ColumnPoolObj)iterator.next();
						String value = (String)resultMap.get(poolobj.getName());
						if (poolobj.getDataType().equals(Const.META_TYPE_STRING))
							ps.setString(pos, value);
						else
						if (poolobj.getDataType().equals(Const.META_TYPE_NUMERIC))
							ps.setDouble(pos, Double.valueOf(value).doubleValue());
						else
						if (poolobj.getDataType().equals(Const.META_TYPE_DATE))
						{
							SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh24:mi:ss");
							ps.setDate(pos, new Date(format.parse(value).getTime()));
						} else
						{
							ps.setString(pos, value);
						}
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
			jdbcTemplate.batchUpdate(sql, setter);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			throw new DAOException(e);
		}
	}

	private boolean isNumeric(String str)
	{
		for (int i = 0; i < str.length(); i++)
			if (!Character.isDigit(str.charAt(i)))
				return false;

		return true;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate)
	{
		this.jdbcTemplate = jdbcTemplate;
	}

	public void setSqlGen(BaseSqlGen sqlGen)
	{
		this.sqlGen = sqlGen;
	}

	public void setLobHandler(DefaultLobHandler lobHandler)
	{
		this.lobHandler = lobHandler;
	}

	public void setQueryFactory(QueryFactory queryFactory)
	{
		this.queryFactory = queryFactory;
	}
}
