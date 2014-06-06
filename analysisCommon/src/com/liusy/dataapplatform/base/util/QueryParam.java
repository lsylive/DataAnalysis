


//   QueryParam.java

package com.liusy.dataapplatform.base.util;

import com.liusy.core.util.Const;

public class QueryParam
{

	private String columnName;
	private String columnType;
	private String queryMode;
	private String queryValue;
	private String aliasName;
	private String combineOper;
	private String prevoper;
	private String nextoper;
	public static final String COLUMN_TYPE_STRING = "1";
	public static final String COLUMN_TYPE_INT = "2";
	public static final String COLUMN_TYPE_DOUBLE = "3";
	public static final String COLUMN_TYPE_LONG = "4";
	public static final String COLUMN_TYPE_DATE = "5";
	public static final String COLUMN_TYPE_TIMESTAMP = "6";
	public static final String QUERYMODE_EQUAL;
	public static final String QUERYMODE_GT;
	public static final String QUERYMODE_LT;
	public static final String QUERYMODE_NOTEQUAL;
	public static final String QUERYMODE_GTANDEQUAL;
	public static final String QUERYMODE_LTANDEQUAL;
	public static final String QUERYMODE_LIKE;
	public static final String QUERYMODE_BETWEEN;
	public static final String QUERYMODE_IN;
	public static final String QUERYMODE_HAVING;
	public static final String OPER_AND = " and ";
	public static final String OPER_OR = " or ";
	public static final String TYPE_AND = "and";
	public static final String TYPE_OR = "or";

	public  QueryParam(String columnName, String columnType, String queryMode, String queryValue,String aliasName,String combineOper,String prevoper,String nextoper)
	{
		this.columnName = columnName;
		this.columnType = columnType;
		this.queryMode = queryMode;
		this.queryValue = queryValue;
				this.aliasName = aliasName;
				this.combineOper = combineOper;
				this.prevoper = prevoper;
				this.nextoper = nextoper;
	}
	public  QueryParam(String columnName, String columnType, String queryMode, String queryValue,String aliasName,String combineOper)
	{
		this.columnName = columnName;
		this.columnType = columnType;
		this.queryMode = queryMode;
		this.queryValue = queryValue;
				this.aliasName = aliasName;
				this.combineOper = combineOper;
	}
	public  QueryParam(String columnName, String columnType, String queryMode, String queryValue,String aliasName)
	{
		this.columnName = columnName;
		this.columnType = columnType;
		this.queryMode = queryMode;
		this.queryValue = queryValue;
				this.aliasName = aliasName;
	}
	public  QueryParam(String columnName, String columnType, String queryMode, String queryValue)
	{
		this.columnName = columnName;
		this.columnType = columnType;
		this.queryMode = queryMode;
		this.queryValue = queryValue;
	}
	public String getColumnName()
	{
		return columnName;
	}

	public void setColumnName(String columnName)
	{
		this.columnName = columnName;
	}

	public String getColumnType()
	{
		return columnType;
	}

	public void setColumnType(String columnType)
	{
		this.columnType = columnType;
	}

	public String getQueryMode()
	{
		return queryMode;
	}

	public void setQueryMode(String queryMode)
	{
		this.queryMode = queryMode;
	}

	public String getQueryValue()
	{
		return queryValue;
	}

	public void setQueryValue(String queryValue)
	{
		this.queryValue = queryValue;
	}

	public String getAliasName()
	{
		return aliasName;
	}

	public void setAliasName(String aliasName)
	{
		this.aliasName = aliasName;
	}

	public String getCombineOper()
	{
		return combineOper;
	}

	public void setCombineOper(String combineOper)
	{
		this.combineOper = combineOper;
	}

	public String getNextoper()
	{
		return nextoper;
	}

	public void setNextoper(String nextoper)
	{
		this.nextoper = nextoper;
	}

	public String getPrevoper()
	{
		return prevoper;
	}

	public void setPrevoper(String prevoper)
	{
		this.prevoper = prevoper;
	}

	static 
	{
		QUERYMODE_EQUAL = Const.FILTER_OPER_EQUAL;
		QUERYMODE_GT = Const.FILTER_OPER_GT;
		QUERYMODE_LT = Const.FILTER_OPER_LT;
		QUERYMODE_NOTEQUAL = Const.FILTER_OPER_NOTEQUAL;
		QUERYMODE_GTANDEQUAL = Const.FILTER_OPER_GTANDEQL;
		QUERYMODE_LTANDEQUAL = Const.FILTER_OPER_GTANDEQL;
		QUERYMODE_LIKE = Const.FILTER_OPER_LIKE;
		QUERYMODE_BETWEEN = Const.FILTER_OPER_BETWEEN;
		QUERYMODE_IN = Const.FILTER_OPER_IN;
		QUERYMODE_HAVING = Const.FILTER_OPER_HAVING;
	}
}
