


//   OracleSqlGen.java

package com.liusy.dataapplatform.base.util;

import java.util.List;

// Referenced classes of package com.liusy.dataapplatform.base.util:
//			AbstractSqlGen, BaseSqlGen, SqlParam, PageQuery, 
//			QueryParam, PagerObject

public class OracleSqlGen extends AbstractSqlGen
	implements BaseSqlGen
{

	public OracleSqlGen()
	{
	}

	public String generateCountSql(String strSQL)
	{
		String str = strSQL.trim().toLowerCase();
		int nFromPos = str.indexOf(" from ");
		int nOrderPos = str.lastIndexOf(" order by ");
		if (nOrderPos == -1)
			nOrderPos = str.length();
		StringBuffer strBuf = new StringBuffer();
		strBuf.append("select count(*) as total ").append(strSQL.substring(nFromPos, nOrderPos));
		return strBuf.toString();
	}

	public String generatePageSql(String strSQL, SqlParam param)
	{
		int nBegin = param.getStartIndex();
		boolean hasOffset = nBegin > 0;
		strSQL = strSQL.trim();
		boolean isForUpdate = false;
		if (strSQL.toLowerCase().endsWith(" for update"))
		{
			strSQL = strSQL.substring(0, strSQL.length() - 11);
			isForUpdate = true;
		}
		StringBuffer pagingSelect = new StringBuffer(strSQL.length() + 100);
		if (hasOffset)
			pagingSelect.append("select * from ( select row_.*, rownum rownum_ from ( ");
		else
			pagingSelect.append("select * from ( ");
		pagingSelect.append(strSQL);
		int count = nBegin + param.getPageSize();
		if (count > param.getTotalCount())
			count = param.getTotalCount();
		if (hasOffset)
			pagingSelect.append(" ) row_ ) where rownum_ <= ").append(count).append(" and rownum_ > ").append(nBegin);
		else
			pagingSelect.append(" ) where rownum <= ").append(param.getPageSize());
		if (isForUpdate)
			pagingSelect.append(" for update");
		return pagingSelect.toString();
	}

	public String generatePageSql(String strSQL, PageQuery pageQuery)
	{
		int nBegin = (Integer.parseInt(pageQuery.getPageNumber()) - 1) * Integer.parseInt(pageQuery.getPageSize());
		boolean hasOffset = nBegin > 0;
		strSQL = strSQL.trim();
		boolean isForUpdate = false;
		if (strSQL.toLowerCase().endsWith(" for update"))
		{
			strSQL = strSQL.substring(0, strSQL.length() - 11);
			isForUpdate = true;
		}
		StringBuffer pagingSelect = new StringBuffer(strSQL.length() + 100);
		if (hasOffset)
			pagingSelect.append("select * from ( select row_.*, rownum rownum_ from ( ");
		else
			pagingSelect.append("select * from ( ");
		pagingSelect.append(strSQL);
		int tonums = nBegin + Integer.parseInt(pageQuery.getPageSize());
		if (Integer.parseInt(pageQuery.getRecordCount()) < tonums)
			tonums = Integer.parseInt(pageQuery.getRecordCount());
		if (hasOffset)
			pagingSelect.append(" ) row_ ) where rownum_ <= ").append(tonums).append(" and rownum_ > ").append(nBegin);
		else
			pagingSelect.append(" ) where rownum <= ").append(pageQuery.getPageSize());
		if (isForUpdate)
			pagingSelect.append(" for update");
		return pagingSelect.toString();
	}

	public String generatePageSqlById(String strSQL, PageQuery pageQuery, int startNum)
	{
		if (pageQuery.getPageSize().equals("0"))
			return strSQL;
		int nBegin = (Integer.parseInt(pageQuery.getPageNumber()) - 1) * Integer.parseInt(pageQuery.getPageSize());
		boolean hasOffset = nBegin > 0;
		strSQL = strSQL.trim();
		boolean isForUpdate = false;
		if (strSQL.toLowerCase().endsWith(" for update"))
		{
			strSQL = strSQL.substring(0, strSQL.length() - 11);
			isForUpdate = true;
		}
		StringBuffer pagingSelect = new StringBuffer(strSQL.length() + 100);
		pagingSelect.append(strSQL);
		int tonums = nBegin + Integer.parseInt(pageQuery.getPageSize());
		if (Integer.parseInt(pageQuery.getRecordCount()) < tonums)
			tonums = Integer.parseInt(pageQuery.getRecordCount());
		if (hasOffset)
		{
			if (pagingSelect.indexOf("where") == -1)
				pagingSelect.append(" where to_number(ID) > ").append(startNum + nBegin).append(" and to_number(ID) <= ").append(startNum + tonums);
			else
				pagingSelect.append(" and to_number(ID) > ").append(startNum + nBegin).append(" and to_number(ID) <= ").append(startNum + tonums);
		} else
		if (pagingSelect.indexOf("where") == -1)
			pagingSelect.append(" where to_number(ID) >= ").append(startNum).append(" and to_number(ID) <= ").append((startNum - 1) + Integer.parseInt(pageQuery.getPageSize()));
		else
			pagingSelect.append(" and to_number(ID) >= ").append(startNum).append(" and to_number(ID) <= ").append((startNum - 1) + Integer.parseInt(pageQuery.getPageSize()));
		if (isForUpdate)
			pagingSelect.append(" for update");
		return pagingSelect.toString();
	}

	public String generateSql(SqlParam queryString)
	{
		StringBuffer buffer = new StringBuffer();
		buffer.append("select ");
		buffer.append(queryString.getFields());
		buffer.append((new StringBuilder(" from ")).append(queryString.getFromString()).toString());
		buffer.append(" where ");
		buffer.append(getQueryString(queryString.getParamList(), " and "));
		if (queryString.getGroupByString() != null && !"".equals(queryString.getGroupByString()))
		{
			buffer.append((new StringBuilder(" group by ")).append(queryString.getGroupByString()).toString());
			if (queryString.getHavingString() != null && !"".equals(queryString.getHavingString()))
				buffer.append((new StringBuilder(" having ")).append(queryString.getHavingString()).toString());
		}
		if (queryString.getOrderString() != null && !"".equals(queryString.getOrderString().trim()))
			buffer.append((new StringBuilder(" order by ")).append(queryString.getOrderString()).toString());
		else
		if (queryString.getSortCol() != null && !"".equals(queryString.getSortCol()))
			buffer.append((new StringBuilder(" order by ")).append(queryString.getSortCol()).append(" ").append(queryString.getSortType()).toString());
		return buffer.toString();
	}

	public String generateSqlBySelectId(String sqlscript, PageQuery queryString)
	{
		StringBuffer buffer = new StringBuffer();
		buffer.append(sqlscript);
		if (queryString.getGroupByString() != null && !"".equals(queryString.getGroupByString()))
		{
			buffer.append((new StringBuilder(" group by ")).append(queryString.getGroupByString()).toString());
			if (queryString.getHavingString() != null && !"".equals(queryString.getHavingString()))
				buffer.append((new StringBuilder(" having ")).append(queryString.getHavingString()).toString());
		}
		if (sqlscript.toLowerCase().indexOf(" order by ") == -1)
			if (queryString.getOrderString() != null && !"".equals(queryString.getOrderString().trim()))
				buffer.append((new StringBuilder(" order by ")).append(queryString.getOrderString()).toString());
			else
			if (queryString.getOrder() != null && !"".equals(queryString.getOrder()))
				buffer.append((new StringBuilder(" order by ")).append(queryString.getOrder()).append(" ").append(queryString.getOrderDirection()).toString());
		return buffer.toString();
	}

	public String generateComplexCountSql(SqlParam queryString, List queryList)
	{
		return null;
	}

	public String generateComplexPageSql(SqlParam queryString, List classifyList, PagerObject pagerObject)
	{
		return null;
	}

	private String getClassSql(List queryList)
	{
		return null;
	}

	protected String toSQLForInt(QueryParam param)
	{
		StringBuffer sql = new StringBuffer("");
		String retstr = "";
		String nQueryModel = param.getQueryMode();
		if (param.getQueryValue() == null || "".equals(param.getQueryValue().trim()))
			return "";
		String value = param.getQueryValue();
		String key = param.getColumnName();
		if (param.getAliasName() != null && !"".equals(param.getAliasName()))
			key = (new StringBuilder(String.valueOf(param.getAliasName()))).append(".").append(key).toString();
		if (value != null && !"".equals(value.trim()))
			if (nQueryModel.equals(QueryParam.QUERYMODE_EQUAL))
				sql.append((new StringBuilder(String.valueOf(key))).append(" = ").append(value).toString());
			else
			if (nQueryModel.equals(QueryParam.QUERYMODE_GT))
				sql.append((new StringBuilder(String.valueOf(key))).append(" > ").append(value).toString());
			else
			if (nQueryModel.equals(QueryParam.QUERYMODE_LT))
				sql.append((new StringBuilder(String.valueOf(key))).append(" < ").append(value).toString());
			else
			if (nQueryModel.equals(QueryParam.QUERYMODE_NOTEQUAL))
				sql.append((new StringBuilder(String.valueOf(key))).append(" != ").append(value).toString());
			else
			if (nQueryModel.equals(QueryParam.QUERYMODE_GTANDEQUAL))
				sql.append((new StringBuilder(String.valueOf(key))).append(" >= ").append(value).toString());
			else
			if (nQueryModel.equals(QueryParam.QUERYMODE_LTANDEQUAL))
				sql.append((new StringBuilder(String.valueOf(key))).append(" <= ").append(value).toString());
			else
			if (nQueryModel.equals(QueryParam.QUERYMODE_IN))
				sql.append((new StringBuilder(String.valueOf(key))).append(" IN (").append(value).append(")").toString());
			else
			if (nQueryModel.equals(QueryParam.QUERYMODE_HAVING))
				sql.append((new StringBuilder(" having ")).append(key).append(param.getQueryMode()).append(param.getQueryValue()).toString());
			else
			if (nQueryModel.equals(QueryParam.QUERYMODE_BETWEEN) && !";".equals(value))
			{
				String beginvalue = value.substring(0, value.indexOf(";"));
				String endvalue = value.substring(value.indexOf(";") + 1, value.length());
				if (!"".equals(beginvalue))
				{
					if (!"".equals(endvalue))
						sql.append((new StringBuilder("(")).append(key).append(" between ").append(beginvalue).append(" and ").append(endvalue).append(")").toString());
					else
						sql.append((new StringBuilder("(")).append(key).append(">=").append(beginvalue).append(")").toString());
				} else
				if (!"".equals(endvalue))
					sql.append((new StringBuilder("(")).append(key).append("<=").append(endvalue).append(")").toString());
			}
		return sql.toString();
	}

	protected String toSQLForDecimal(QueryParam param)
	{
		StringBuffer sql = new StringBuffer("");
		String nQueryModel = param.getQueryMode();
		if (param.getQueryValue() == null || "".equals(param.getQueryValue().trim()))
			return "";
		String value = param.getQueryValue();
		String key = param.getColumnName();
		if (param.getAliasName() != null && !"".equals(param.getAliasName()))
			key = (new StringBuilder(String.valueOf(param.getAliasName()))).append(".").append(key).toString();
		if (value != null && !"".equals(value.trim()))
		{
			if (nQueryModel.equals(QueryParam.QUERYMODE_EQUAL))
				sql.append((new StringBuilder(String.valueOf(key))).append(" = ").append(value).append(" and ").toString());
			if (nQueryModel.equals(QueryParam.QUERYMODE_GT))
				sql.append((new StringBuilder(String.valueOf(key))).append(" > ").append(value).append(" and ").toString());
			else
			if (nQueryModel.equals(QueryParam.QUERYMODE_LT))
				sql.append((new StringBuilder(String.valueOf(key))).append(" < ").append(value).append(" and ").toString());
			else
			if (nQueryModel.equals(QueryParam.QUERYMODE_NOTEQUAL))
				sql.append((new StringBuilder(String.valueOf(key))).append(" != ").append(value).append(" and ").toString());
			else
			if (nQueryModel.equals(QueryParam.QUERYMODE_GTANDEQUAL))
				sql.append((new StringBuilder(String.valueOf(key))).append(" >= ").append(value).append(" and ").toString());
			else
			if (nQueryModel.equals(QueryParam.QUERYMODE_LTANDEQUAL))
				sql.append((new StringBuilder(String.valueOf(key))).append(" <= ").append(value).append(" and ").toString());
			else
			if (nQueryModel.equals(QueryParam.QUERYMODE_HAVING))
				sql.append((new StringBuilder(" having ")).append(key).append(param.getQueryMode()).append(param.getQueryValue()).toString());
			else
			if (nQueryModel.equals(QueryParam.QUERYMODE_BETWEEN))
			{
				String beginvalue = value.substring(0, value.indexOf(";"));
				String endvalue = value.substring(value.indexOf(";") + 1, value.length());
				sql.append((new StringBuilder("(")).append(key).append(" between ").append(beginvalue).append(" and ").append(endvalue).append(")").toString());
			}
		}
		return sql.toString();
	}

	protected String toSQLForString(QueryParam param)
	{
		StringBuffer sql = new StringBuffer("");
		String nQueryModel = param.getQueryMode();
		if (param.getQueryValue() == null || "".equals(param.getQueryValue().trim()))
			return "";
		String key = param.getColumnName();
		if (param.getAliasName() != null && !"".equals(param.getAliasName()))
			key = (new StringBuilder(String.valueOf(param.getAliasName()))).append(".").append(key).toString();
		String value = replace(param.getQueryValue());
		if (value != null && !"".equals(value))
			if (nQueryModel.equals(QueryParam.QUERYMODE_EQUAL))
				sql.append((new StringBuilder(String.valueOf(key))).append("='").append(value).append("'").toString());
			else
			if (nQueryModel.equals(QueryParam.QUERYMODE_NOTEQUAL))
				sql.append((new StringBuilder(String.valueOf(key))).append("!='").append(value).append("'").toString());
			else
			if (nQueryModel.equals(QueryParam.QUERYMODE_LIKE))
				if (value.contains("%"))
					sql.append((new StringBuilder(String.valueOf(key))).append(" like '").append(value).append("'").toString());
				else
					sql.append((new StringBuilder(String.valueOf(key))).append(" like '%").append(value).append("%'").toString());
		return sql.toString();
	}

	protected String toSQLForDate(QueryParam param)
	{
		StringBuffer sql = new StringBuffer("");
		String nQueryModel = param.getQueryMode();
		if (param.getQueryValue() == null || "".equals(param.getQueryValue().trim()))
			return "";
		String key = param.getColumnName();
		if (param.getAliasName() != null && !"".equals(param.getAliasName()))
			key = (new StringBuilder(String.valueOf(param.getAliasName()))).append(".").append(key).toString();
		String value = param.getQueryValue();
		if (nQueryModel.equals(QueryParam.QUERYMODE_GTANDEQUAL))
			sql.append((new StringBuilder(String.valueOf(key))).append(">=").append("to_date('").append(value).append("','YYYY-MM-DD')").toString());
		else
		if (nQueryModel.equals(QueryParam.QUERYMODE_LTANDEQUAL))
			sql.append((new StringBuilder(String.valueOf(key))).append("<=").append("to_date('").append(value).append("','YYYY-MM-DD')").toString());
		else
		if (nQueryModel.equals(QueryParam.QUERYMODE_BETWEEN) && !"".equals(value) && !";".equals(value))
		{
			String str[] = value.split(";");
			String begindate = value.substring(0, value.indexOf(";"));
			String enddate = value.substring(value.indexOf(";") + 1, value.length());
			if (!"".equals(begindate))
			{
				if (!"".equals(enddate))
					sql.append((new StringBuilder("(")).append(key).append(" between to_date('").append(begindate).append("','YYYY-MM-DD') and to_date('").append(enddate).append("','YYYY-MM-DD'))").toString());
				else
					sql.append((new StringBuilder("(")).append(key).append(">=to_date('").append(begindate).append("','YYYY-MM-DD'))").toString());
			} else
			if (!"".equals(enddate))
				sql.append((new StringBuilder("(")).append(key).append("<=to_date('").append(enddate).append("','YYYY-MM-DD'))").toString());
		}
		return sql.toString();
	}

	public String generateMinIdSql(String strSQL)
	{
		String str = strSQL.trim().toLowerCase();
		StringBuffer strBuf = new StringBuffer();
		strBuf.append("select min(to_number(ID)) from ( ").append(strSQL).append(")");
		return strBuf.toString();
	}
}
