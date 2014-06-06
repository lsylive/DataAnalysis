


//   JsonTreeUtil.java

package com.liusy.datapp.util;

import java.io.PrintWriter;
import java.util.*;
import javax.servlet.http.HttpServletResponse;

public class JsonTreeUtil
{

	public JsonTreeUtil()
	{
	}

	public static void getTreeListByList(List list, Map map, HttpServletResponse response, String iframetarget)
		throws Exception
	{
		StringBuffer buffer = new StringBuffer();
		buffer.append("[\n");
		String str;
		for (Iterator iterator = list.iterator(); iterator.hasNext(); buffer.append(str))
		{
			Map res = (Map)iterator.next();
			str = "";
			String targetstr = "";
			if (iframetarget != null && !"".equals(iframetarget))
				targetstr = (new StringBuilder(",target:\"")).append(iframetarget).append("\"").toString();
		}

		String retstr = (new StringBuilder(String.valueOf(buffer.substring(0, buffer.length() - 1)))).append("]").toString();
		response.setContentType("text/javascript;charset=UTF-8");
		PrintWriter writer = response.getWriter();
		writer.write(retstr);
		writer.close();
	}
}
