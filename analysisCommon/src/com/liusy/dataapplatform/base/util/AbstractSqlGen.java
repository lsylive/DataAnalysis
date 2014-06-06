


//   AbstractSqlGen.java

package com.liusy.dataapplatform.base.util;

import java.util.Iterator;
import java.util.List;

// Referenced classes of package com.liusy.dataapplatform.base.util:
//			BaseSqlGen, QueryParam

public abstract class AbstractSqlGen
	implements BaseSqlGen
{

	public AbstractSqlGen()
	{
	}

	public static String replace(String str)
	{
		if (str != null)
			return str.replaceAll("'", "''");
		else
			return null;
	}

	public String getQueryStringPart(List paramList, String linkOper)
	{
		StringBuffer buffer = new StringBuffer();
		for (Iterator iterator = paramList.iterator(); iterator.hasNext();)
		{
			QueryParam param = (QueryParam)iterator.next();
			String prevoper = param.getPrevoper();
			String nextoper = param.getNextoper();
			if (prevoper == null || prevoper.length() == 0)
				prevoper = "";
			if (nextoper == null || nextoper.length() == 0)
				nextoper = "";
			if (param.getQueryValue() == null || "".equals(param.getQueryValue()))
				break;
			if (param.getColumnType().equals("2"))
				buffer.append((new StringBuilder(String.valueOf(prevoper))).append(toSQLForInt(param)).append(nextoper).append(linkOper).toString());
			else
			if (param.getColumnType().equals("3"))
				buffer.append((new StringBuilder(String.valueOf(prevoper))).append(toSQLForDecimal(param)).append(nextoper).append(linkOper).toString());
			else
			if (param.getColumnType().equals("1"))
			{
				String temp = toSQLForString(param);
				if (!"".equals(temp))
					buffer.append((new StringBuilder(String.valueOf(prevoper))).append(temp).append(nextoper).append(linkOper).toString());
			} else
			if (param.getColumnType().equals("5"))
				buffer.append((new StringBuilder(String.valueOf(prevoper))).append(toSQLForDate(param)).append(nextoper).append(linkOper).toString());
		}

		String retstr = "";
		if (buffer.length() > 0)
			retstr = buffer.substring(0, buffer.length() - linkOper.length());
		return retstr;
	}

	public String getQueryStringPart(List paramList)
	{
		StringBuffer buffer = new StringBuffer();
		String lastoper = "";
		for (Iterator iterator = paramList.iterator(); iterator.hasNext();)
		{
			QueryParam param = (QueryParam)iterator.next();
			String prevoper = param.getPrevoper();
			String nextoper = param.getNextoper();
			if (prevoper == null || prevoper.length() == 0)
				prevoper = "";
			if (nextoper == null || nextoper.length() == 0)
				nextoper = "";
			lastoper = param.getCombineOper();
			if (param.getQueryValue() == null || "".equals(param.getQueryValue()))
				break;
			if (param.getColumnType().equals("2"))
				buffer.append((new StringBuilder(String.valueOf(prevoper))).append(toSQLForInt(param)).append(nextoper).append(lastoper).toString());
			else
			if (param.getColumnType().equals("3"))
				buffer.append((new StringBuilder(String.valueOf(prevoper))).append(toSQLForDecimal(param)).append(nextoper).append(lastoper).toString());
			else
			if (param.getColumnType().equals("1"))
				buffer.append((new StringBuilder(String.valueOf(prevoper))).append(toSQLForString(param)).append(nextoper).append(lastoper).toString());
			else
			if (param.getColumnType().equals("5"))
				buffer.append((new StringBuilder(String.valueOf(prevoper))).append(toSQLForDate(param)).append(nextoper).append(lastoper).toString());
		}

		String retstr = "";
		if (buffer.length() > 0)
			retstr = buffer.substring(0, buffer.length() - lastoper.length());
		return retstr;
	}

	public String getQueryStringByDiffOper(List paramList)
	{
		StringBuffer buffer = new StringBuffer();
		for (int i = 0; i < paramList.size(); i++)
		{
			QueryParam param = (QueryParam)paramList.get(i);
			String linkOper = param.getCombineOper() != null ? param.getCombineOper() : "";
			if (i == paramList.size() - 1)
				linkOper = "";
			if (param.getQueryValue() == null || "".equals(param.getQueryValue()))
				break;
			if (param.getColumnType().equals("2"))
				buffer.append((new StringBuilder(String.valueOf(toSQLForInt(param)))).append(linkOper).append(" ").toString());
			else
			if (param.getColumnType().equals("3"))
				buffer.append((new StringBuilder(String.valueOf(toSQLForDecimal(param)))).append(linkOper).append(" ").toString());
			else
			if (param.getColumnType().equals("1"))
				buffer.append((new StringBuilder(String.valueOf(toSQLForString(param)))).append(linkOper).append(" ").toString());
			else
			if (param.getColumnType().equals("5"))
				buffer.append((new StringBuilder(String.valueOf(toSQLForDate(param)))).append(linkOper).append(" ").toString());
		}

		String retstr = "";
		if (buffer.length() > 0)
			retstr = buffer.toString();
		return retstr;
	}

	public String getQueryString(List paramList, String linkOper)
	{
		StringBuffer buffer = new StringBuffer();
		buffer.append(" 1=1 and ");
		for (Iterator iterator = paramList.iterator(); iterator.hasNext();)
		{
			QueryParam param = (QueryParam)iterator.next();
			if (param.getQueryValue() == null || "".equals(param.getQueryValue()))
				break;
			if (param.getColumnType().equals("2"))
				buffer.append((new StringBuilder(String.valueOf(toSQLForInt(param)))).append(linkOper).toString());
			else
			if (param.getColumnType().equals("3"))
				buffer.append((new StringBuilder(String.valueOf(toSQLForDecimal(param)))).append(linkOper).toString());
			else
			if (param.getColumnType().equals("1"))
				buffer.append((new StringBuilder(String.valueOf(toSQLForString(param)))).append(linkOper).toString());
			else
			if (param.getColumnType().equals("5"))
				buffer.append((new StringBuilder(String.valueOf(toSQLForDate(param)))).append(linkOper).toString());
		}

		return buffer.substring(0, buffer.length() - 5);
	}

	public String toSQLWithType(QueryParam param)
	{
		String sqlstr = "";
		if (param.getQueryValue() == null || "".equals(param.getQueryValue()))
			return sqlstr;
		if (param.getColumnType().equals("2"))
			sqlstr = toSQLForInt(param);
		else
		if (param.getColumnType().equals("3"))
			sqlstr = toSQLForDecimal(param);
		else
		if (param.getColumnType().equals("1"))
			sqlstr = toSQLForString(param);
		else
		if (param.getColumnType().equals("5"))
			sqlstr = toSQLForDate(param);
		return sqlstr;
	}

	protected abstract String toSQLForDecimal(QueryParam queryparam);

	protected abstract String toSQLForInt(QueryParam queryparam);

	protected abstract String toSQLForString(QueryParam queryparam);

	protected abstract String toSQLForDate(QueryParam queryparam);
}
