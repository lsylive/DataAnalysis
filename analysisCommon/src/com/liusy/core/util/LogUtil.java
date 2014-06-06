


//   LogUtil.java

package com.liusy.core.util;

import java.util.HashMap;
import java.util.Map;

public class LogUtil
{

	public static final ThreadLocal session = new ThreadLocal();

	public LogUtil()
	{
	}

	public static Map currentMap()
		throws Exception
	{
		Map _m = (Map)session.get();
		if (_m == null)
		{
			_m = new HashMap();
			session.set(_m);
		}
		return _m;
	}

	public static void clearMap()
		throws Exception
	{
		Map _m = (Map)session.get();
		if (_m != null)
			_m.clear();
	}

}
