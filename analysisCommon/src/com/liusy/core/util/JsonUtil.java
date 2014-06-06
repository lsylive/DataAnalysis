


//   JsonUtil.java

package com.liusy.core.util;

import com.liusy.dataapplatform.base.util.PageQuery;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.sf.json.*;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class JsonUtil
{

	private static Log log = LogFactory.getLog(JsonUtil.class);

	public JsonUtil()
	{
	}

	public static void JsonGridOutput(PageQuery pageQuery, HttpServletResponse response)
		throws Exception
	{
		try
		{
			response.setContentType("text/javascript;charset=UTF-8");
			PrintWriter writer = response.getWriter();
			List rowList = new ArrayList();
			Map jsonMap = new HashMap();
			List jsonList = new ArrayList();
			List list = pageQuery.getRecordSet();
			jsonMap.put("total", pageQuery.getPageCount());
			jsonMap.put("page", pageQuery.getPageNumber());
			jsonMap.put("records", pageQuery.getRecordCount());
			Map cellMap;
			for (Iterator iterator = list.iterator(); iterator.hasNext(); rowList.add(cellMap))
			{
				Map map = (Map)iterator.next();
				cellMap = new HashMap();
				String cols[] = (String[])map.keySet().toArray();
				Object obj[] = new Object[cols.length];
				for (int i = 0; i < cols.length; i++)
					obj[i] = map.get(cols[i]);

				cellMap.put("id", map.get("id"));
				cellMap.put("cell", ((Object) (obj)));
			}

			jsonMap.put("rows", rowList);
			JSONObject object1 = JSONObject.fromObject(jsonMap);
			writer.write(object1.toString());
			writer.close();
		}
		catch (Exception e)
		{
			log.error(e);
			throw e;
		}
	}

	public static void jsonOkResponse(String okMsg, HttpServletRequest request, HttpServletResponse response)
		throws Exception
	{
		jsonMsgResponse(okMsg, 0, request, response);
	}

	public static void jsonFailResponse(String failMsg, HttpServletRequest request, HttpServletResponse response)
		throws Exception
	{
		jsonMsgResponse(failMsg, 1, request, response);
	}

	public static void jsonMsgResponse(String msg, int msgType, HttpServletRequest request, HttpServletResponse response)
		throws IOException
	{
		response.setContentType("application/x-json;charset=UTF-8");
		PrintWriter out = response.getWriter();
		if (msgType == 0)
			out.print((new StringBuilder()).append("{success:true,'info':'").append(msg).append("'}").toString());
		else
			out.print((new StringBuilder()).append("{success:false,'errorInfo':'").append(msg).append("'}").toString());
	}

	public static void jsonResponse(String result, HttpServletRequest request, HttpServletResponse response)
		throws Exception
	{
		if (result == null)
			throw new IllegalArgumentException("result must be not null...");
		String cb = request.getParameter("callback");
		if (cb != null)
		{
			response.setContentType("text/javascript;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.write((new StringBuilder()).append(cb).append("(").toString());
			out.print(result);
			out.write(");");
		} else
		{
			response.setContentType("application/x-json;charset=UTF-8");
			response.getWriter().print(result);
		}
	}

	public static void jsonResponse(Collection selectList, HttpServletRequest request, HttpServletResponse response)
		throws Exception
	{
		if (selectList == null)
			throw new IllegalArgumentException("selectList must be not null...");
		JSONObject json = selectListToJSON(selectList);
		String cb = request.getParameter("callback");
		if (cb != null)
		{
			response.setContentType("text/javascript;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.write((new StringBuilder()).append(cb).append("(").toString());
			out.print(json);
			out.write(");");
		} else
		{
			response.setContentType("application/x-json;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.print(json);
		}
	}

	public static void jsonFormalResponse(Object obj, HttpServletRequest request, HttpServletResponse response)
		throws Exception
	{
		if (obj == null)
			throw new IllegalArgumentException("Object must be not null...");
		boolean scriptTag = false;
		String cb = request.getParameter("callback");
		if (cb != null)
		{
			scriptTag = true;
			response.setContentType("text/javascript;charset=UTF-8");
		} else
		{
			response.setContentType("application/x-json;charset=UTF-8");
		}
		PrintWriter out = response.getWriter();
		JSONObject json = new JSONObject();
		JSONArray jsonItems = new JSONArray();
		jsonItems.add(objectToJSON(obj));
		json.put("results", jsonItems);
		if (scriptTag)
			out.write((new StringBuilder()).append(cb).append("(").toString());
		out.print(json);
		if (scriptTag)
			out.write(");");
	}

	public static void jsonErrorsResponse(JSONObject errors, HttpServletRequest request, HttpServletResponse response)
		throws Exception
	{
		boolean scriptTag = false;
		String cb = request.getParameter("callback");
		if (cb != null)
		{
			scriptTag = true;
			response.setContentType("text/javascript;charset=UTF-8");
		} else
		{
			response.setContentType("application/x-json;charset=UTF-8");
		}
		PrintWriter out = response.getWriter();
		if (scriptTag)
			out.write((new StringBuilder()).append(cb).append("(").toString());
		out.print((new StringBuilder()).append("{errors:[").append(errors).append("], 'errorInfo':'���У����?'}").toString());
		if (scriptTag)
			out.write(");");
	}

	private static JSONObject selectListToJSON(Collection selectList)
		throws Exception
	{
		JSONObject json = new JSONObject();
		JSONArray jsonItems = new JSONArray();
		for (Iterator i = selectList.iterator(); i.hasNext(); jsonItems.add(objectToJSON(i.next())));
		json.put("totalCount", Integer.valueOf(selectList.size()));
		json.put("results", jsonItems);
		return json;
	}

	public static void jsonHtmlResponse(String html, HttpServletRequest request, HttpServletResponse response)
		throws Exception
	{
		boolean scriptTag = false;
		String cb = request.getParameter("callback");
		if (cb != null)
		{
			scriptTag = true;
			response.setContentType("text/javascript;charset=UTF-8");
		} else
		{
			response.setContentType("application/x-json;charset=UTF-8");
		}
		PrintWriter out = response.getWriter();
		if (scriptTag)
			out.write((new StringBuilder()).append(cb).append("(").toString());
		out.print(html);
		if (scriptTag)
			out.write(");");
	}

	public static JSONObject objectToJSON(Object obj)
		throws Exception
	{
		if (obj == null)
			throw new IllegalArgumentException("Object must be not null...");
		JSONObject json = new JSONObject();
		if (obj instanceof Map)
		{
			Map map = (Map)obj;
			json = JSONObject.fromObject(map);
		} else
		if (obj instanceof Collection)
		{
			Collection c = (Collection)obj;
			for (Iterator iter = c.iterator(); iter.hasNext(); objectToJson(iter.next(), json));
		} else
		{
			objectToJson(obj, json);
		}
		return json;
	}

	private static void objectToJson(Object obj, JSONObject json)
		throws IllegalAccessException, InvocationTargetException, JSONException
	{
		Method methods[] = obj.getClass().getMethods();
		for (int i = 0; i < methods.length; i++)
		{
			String methodName = methods[i].getName();
			if (methodName.startsWith("get") && !"getClass".equals(methodName))
			{
				Class type = methods[i].getReturnType();
				String typeName = type.getName();
				if (type.isPrimitive() || typeName.startsWith("java.lang") || typeName.startsWith("java.sql") || typeName.startsWith("java.math"))
				{
					Object value = methods[i].invoke(obj, null);
					if (value != null && typeName.equals("java.sql.Timestamp"))
					{
						String v = value.toString();
						int index = v.indexOf(".");
						if (index > -1)
							value = v.substring(0, index);
					}
					if (value == null)
						value = "";
					json.put(parseFieldName(methodName), value);
				}
			}
		}

	}

	private static String parseFieldName(String method)
	{
		String field = method.trim().substring(3);
		if (field.equals(field.toUpperCase()))
			return field;
		else
			return (new StringBuilder()).append(field.substring(0, 1).toLowerCase()).append(field.substring(1)).toString();
	}

}
