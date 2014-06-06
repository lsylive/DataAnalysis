


//   Global.java

package com.liusy.core.util;

import java.util.HashMap;
import java.util.Map;

public class Global
{

	public static Map tableMap;

	public Global()
	{
	}

	public static String getTableName(String key)
	{
		if (tableMap.containsKey(key))
			return (String)tableMap.get(key);
		else
			return null;
	}

	static 
	{
		tableMap = new HashMap();
		tableMap.put("StandardCategoryDaoImpl", "T_STANDARD_CATEGORY");
	}
}
