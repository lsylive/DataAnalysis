


//   Const.java

package com.liusy.core.util;


public class Const
{

	public static String DEFAULT_PAGE_SIZE = "10";
	public static String MAX_PAGE_SIZE = "500";
	public static String MIN_PAGE_SIZE = "2";
	public static String SESSION = "SESS_LOGINUSER";
	public static String LOGIN_DEACTIVE = "0";
	public static String LOGIN_ACTIVE = "1";
	public static String LOGIN_VERFIYCODE = "verify_code";
	public static String ADMIN_LEVEL_KEJI = "4";
	public static String ADMIN_LEVEL_CHUJI = "3";
	public static String RESOURCE_TYPE_MENU = "1";
	public static String RESOURCE_TYPE_BUTTON = "2";
	public static int FIELD_TYPE_STRING = 0;
	public static int FIELD_TYPE_INT = 1;
	public static int FIELD_TYPE_INTEGER = 2;
	public static int FIELD_TYPE_DOUBLE = 3;
	public static int FIELD_TYPE_DATE = 4;
	public static int FIELD_TYPE_BOOLEAN = 5;
	public static String SYS_VERIFIED = "0";
	public static String SYS_NOTVERIFY = "1";
	public static String SYS_OPER_NONE = "";
	public static String SYS_OPER_ADD = "1";
	public static String SYS_OPER_MODIFY = "2";
	public static String SYS_OPER_DELETE = "3";
	public static String META_TYPE_STRING = "01";
	public static String META_TYPE_DATE = "02";
	public static String META_TYPE_NUMERIC = "03";
	public static String META_TYPE_PICTRUE = "04";
	public static String FILTER_OPER_BETWEEN = "BT";
	public static String FILTER_OPER_LIKE = "LK";
	public static String FILTER_OPER_EQUAL = "=";
	public static String FILTER_OPER_NOTEQUAL = "!=";
	public static String FILTER_OPER_GT = ">";
	public static String FILTER_OPER_GTANDEQL = ">=";
	public static String FILTER_OPER_LT = "<";
	public static String FILTER_OPER_LTANDEQL = "<=";
	public static String FILTER_OPER_IN = "in";
	public static String FILTER_OPER_HAVING = "having";
	public static String TAG_ENABLE = "1";
	public static String TAG_DISABLE = "0";
	public static String SYS_CODE_YES = "1";
	public static String SYS_CODE_NO = "0";
	public static String QUERY_TYPE_SUBJECTCOMM = "1";
	public static String QUERY_TYPE_BATCH = "2";
	public static String QUERY_TYPE_TABCOMM = "3";
	public static String QUERY_TYPE_TABBATCH = "4";
	public static String QUERY_TYPE_TABADVANCE = "5";
	public static String QUERY_TYPE_TABCOMPLEX = "6";
	public static Integer DEFAULT_AVIALABLE_SPACE = Integer.valueOf(100);
	public static String IS_RESOURCETABLE = "0";
	public static String IS_SPACCETABLE = "1";
	public static String RESTAB_TYPE_DB = "1";
	public static String RESTAB_TYPE_VIEW = "2";
	public static String PROCESS_STATUS_DRAFT = "0";
	public static String PROCESS_STATUS_WAIT = "1";
	public static String PROCESS_STATUS_WAIT_C = "2";
	public static String PROCESS_STATUS_END = "3";
	public static String PROCESS_STATUS_PIGEONHOLE = "4";
	public static String PROCESS_STATUS_PIGEONHOLED = "5";
	public static String PROCESS_TACHE_STATUS_WAIT = "6";
	public static String PROCESS_TACHE_STATUS_DEALED = "7";
	public static int SYS_ADMINER_ID = 1;
	public static String PROCESS_EMERGENCY_NORMAL = "1";
	public static String PROCESS_EMERGENCY_HIGH = "2";
	public static String RUN_CYCLE_MONTH = "0";
	public static String RUN_CYCLE_WEEK = "1";
	public static String RUN_CYCLE_DAY = "2";
	public static String RUN_CYCLE_ONCE = "3";
	public static String STATUS_RUNNING = "1";
	public static String STATUS_PENDDING = "2";
	public static String STAUTS_FINISH = "3";
	public static String STATUS_STOPPED = "4";
	public static String STATUS_FAILED = "5";
	public static String STATUS_COLUMN_RUNNING = "1";
	public static String STATUS_COLUMN_PENNDING = "2";
	public static String STATUS_COLUMN_FINISH = "3";
	public static String STATUS_COLUMN_STOPPED = "4";
	public static String STATUS_COLUMN_FAILED = "5";
	public static String INDICATOR_NAME = "XM";

	public Const()
	{
	}

	public static String getDataType(String type)
	{
		if (type.equals(META_TYPE_STRING))
			return "string";
		if (type.equals(META_TYPE_NUMERIC))
			return "number";
		if (type.equals(META_TYPE_DATE))
			return "date";
		else
			return "unknown";
	}

}
