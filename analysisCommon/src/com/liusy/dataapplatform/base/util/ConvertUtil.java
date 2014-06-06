


//   ConvertUtil.java

package com.liusy.dataapplatform.base.util;

import com.liusy.dataapplatform.base.model.BaseVO;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.*;

public class ConvertUtil
{

	public ConvertUtil()
	{
	}

	public static void convertToWeb(Object target, Object src)
		throws Exception
	{
		if (target == null || src == null)
			return;
		Map map = getAllSetMethodNames(target);
		Method methods[] = src.getClass().getMethods();
		for (int i = 0; i < methods.length; i++)
		{
			String method = methods[i].getName();
			if (method.startsWith("get") && !"getClass".equals(method))
			{
				Method setMethod = (Method)map.get(method.replaceFirst("get", "set"));
				if (setMethod != null)
				{
					Object value = methods[i].invoke(src, null);
					if (value != null)
						setMethod.invoke(target, new Object[] {
							value.toString()
						});
					else
						setMethod.invoke(target, new Object[] {
							""
						});
				}
			}
		}

	}

	public static void objectToMap(Map target, Object src)
		throws Exception
	{
		if (src == null || target == null)
			return;
		Method methods[] = src.getClass().getMethods();
		for (int i = 0; i < methods.length; i++)
		{
			String method = methods[i].getName();
			if (method.startsWith("get") && !"getClass".equals(method))
			{
				String key = method.replaceFirst("get", "");
				key = (new StringBuilder(String.valueOf(key.substring(0, 1).toLowerCase()))).append(key.substring(1)).toString();
				Object value = methods[i].invoke(src, null);
				target.put(key, value != null ? ((Object) (value.toString().trim())) : "");
			}
		}

	}

	public static void mapToObject(BaseVO target, Map src)
		throws Exception
	{
		if (src == null || target == null)
			return;
		Iterator it = src.keySet().iterator();
		Method methods[] = target.getClass().getMethods();
		while (it.hasNext()) 
		{
			String key = (String)it.next();
			String value = (String)src.get(key);
			String methordName = (new StringBuilder("set")).append(key.substring(0, 1).toUpperCase()).append(key.substring(1)).toString();
			for (int i = 0; i < methods.length; i++)
				if (methods[i].getName().equalsIgnoreCase(methordName))
				{
					target.AddDirtyColumn(key);
					Class type = methods[i].getParameterTypes()[0];
					Object retValue = null;
					if (!type.getName().equalsIgnoreCase("java.lang.String") && value.equals(""))
						retValue = null;
					else
						retValue = parseParamenter(type, value);
					methods[i].invoke(target, new Object[] {
						retValue
					});
				}

		}
	}

	public static void mapToObject(Object target, Map src)
		throws Exception
	{
		if (src == null || target == null)
			return;
		Iterator it = src.keySet().iterator();
		Method methods[] = target.getClass().getMethods();
		while (it.hasNext()) 
		{
			String key = (String)it.next();
			String value = (String)src.get(key);
			String methordName = (new StringBuilder("set")).append(key.substring(0, 1).toUpperCase()).append(key.substring(1)).toString();
			for (int i = 0; i < methods.length; i++)
				if (methods[i].getName().equalsIgnoreCase(methordName))
				{
					Class type = methods[i].getParameterTypes()[0];
					Object retValue = null;
					if (!type.getName().equalsIgnoreCase("java.lang.String") && value.equals(""))
						retValue = null;
					else
						retValue = parseParamenter(type, value);
					methods[i].invoke(target, new Object[] {
						retValue
					});
				}

		}
	}

	public static void mapToObject(Object target, Map src, boolean idInclude)
		throws Exception
	{
		if (src == null || target == null)
			return;
		Iterator it = src.keySet().iterator();
		Method methods[] = target.getClass().getMethods();
		while (it.hasNext()) 
		{
			String key = (String)it.next();
			if (idInclude || !key.equals("id"))
			{
				String value = (String)src.get(key);
				String methordName = (new StringBuilder("set")).append(key.substring(0, 1).toUpperCase()).append(key.substring(1)).toString();
				for (int i = 0; i < methods.length; i++)
					if (methods[i].getName().equalsIgnoreCase(methordName))
					{
						Class type = methods[i].getParameterTypes()[0];
						Object retValue = null;
						if (!type.getName().equalsIgnoreCase("java.lang.String") && value.equals(""))
							retValue = null;
						else
							retValue = parseParamenter(type, value);
						methods[i].invoke(target, new Object[] {
							retValue
						});
					}

			}
		}
	}

	private static String wordCase(String value)
	{
		return (new StringBuilder(String.valueOf(value.substring(0, 1).toUpperCase()))).append(value.substring(1)).toString();
	}

	public static void convertToModel(Object target, Object src)
		throws Exception
	{
		if (target == null || src == null)
			return;
		if (!target.getClass().equals(src.getClass()))
			throw new Exception("类型不一致,本方法两个参数必须为同一类型");
		Map map = getAllSetMethodNames(target);
		Method methods[] = src.getClass().getMethods();
		for (int i = 0; i < methods.length; i++)
		{
			String method = methods[i].getName();
			if (method.startsWith("get") && !"getClass".equals(method))
			{
				Method setMethod = (Method)map.get(method.replaceFirst("get", "set"));
				if (setMethod != null)
				{
					Object value = methods[i].invoke(src, null);
					if (value != null)
						setMethod.invoke(target, new Object[] {
							value
						});
				}
			}
		}

	}

	public static void convertToModelForUpdateNew(BaseVO target, BaseVO src)
		throws Exception
	{
		if (target == null || src == null)
			return;
		if (!target.getClass().equals(src.getClass()))
			throw new Exception("类型不一致,本方法两个参数必须为同一类型");
		Map map = getAllSetMethodNames(target);
		List dirtyColumnList = src.getDirtyColumn();
		for (int i = 0; i < dirtyColumnList.size(); i++)
		{
			String method = (new StringBuilder(String.valueOf(((String)dirtyColumnList.get(i)).substring(0, 1).toUpperCase()))).append(((String)dirtyColumnList.get(i)).substring(1, ((String)dirtyColumnList.get(i)).length())).toString();
			Method setMethod = (Method)map.get((new StringBuilder("set")).append(method).toString());
			if (setMethod != null)
			{
				Method getMethod = src.getClass().getMethod((new StringBuilder("get")).append(method).toString(), null);
				Object value = getMethod.invoke(src, null);
				if (value != null)
					setMethod.invoke(target, new Object[] {
						value
					});
				else
					setMethod.invoke(target, new Object[1]);
			}
		}

	}

	public static void convertToModelForUpdate(Object target, Object src)
		throws Exception
	{
		if (target == null || src == null)
			return;
		if (!target.getClass().equals(src.getClass()))
			throw new Exception("类型不一致,本方法两个参数必须为同一类型");
		Map map = getAllSetMethodNames(target);
		Method methods[] = src.getClass().getMethods();
		for (int i = 0; i < methods.length; i++)
		{
			String method = methods[i].getName();
			if (method.startsWith("get") && !"getClass".equals(method) && !"getId".equalsIgnoreCase(method))
			{
				Method setMethod = (Method)map.get(method.replaceFirst("get", "set"));
				if (setMethod != null)
				{
					Object value = methods[i].invoke(src, null);
					if (value != null)
						setMethod.invoke(target, new Object[] {
							value
						});
				}
			}
		}

	}

	public static void convertToModel(Object target, Map src)
		throws Exception
	{
		if (target == null || src == null)
			return;
		Map map = getAllSetMethodNames(target);
		for (Iterator set = src.keySet().iterator(); set.hasNext();)
		{
			String method = (String)set.next();
			Method setMethod = (Method)map.get((new StringBuilder("set")).append(wordCase(method)).toString());
			if (setMethod != null)
			{
				String value = (String)src.get(method);
				if (value != null)
				{
					Class type = setMethod.getParameterTypes()[0];
					if (!type.getName().equalsIgnoreCase("java.lang.String") && value.equals(""))
					{
						setMethod.invoke(target, new Object[1]);
					} else
					{
						Object retValue = parseParamenter(type, value);
						setMethod.invoke(target, new Object[] {
							retValue
						});
					}
				}
			}
		}

	}

	public static void convertToPool(Object target, Object src)
		throws Exception
	{
		if (target == null || src == null)
			return;
		Map map = getAllSetMethodNames(target);
		Method methods[] = src.getClass().getMethods();
		for (int i = 0; i < methods.length; i++)
		{
			String method = methods[i].getName();
			if (method.startsWith("get") && !"getClass".equals(method))
			{
				Method setMethod = (Method)map.get(method.replaceFirst("get", "set"));
				if (setMethod != null)
				{
					Object value = methods[i].invoke(src, null);
					if (value != null)
					{
						Class type = setMethod.getParameterTypes()[0];
						Object retValue = parseParamenter(type, value);
						setMethod.invoke(target, new Object[] {
							retValue
						});
					}
				}
			}
		}

	}

	private static Map getAllSetMethodNames(Object source)
		throws Exception
	{
		Method methods[] = source.getClass().getMethods();
		Map map = new HashMap();
		for (int i = 0; i < methods.length; i++)
		{
			String method = methods[i].getName();
			if (method.startsWith("set"))
				map.put(method, methods[i]);
		}

		return map;
	}

	private static Object parseParamenter(Class type, Object strValue)
		throws Exception
	{
		String typeName = type.getName();
		Object ret = null;
		if (type.isPrimitive())
			if ("int".equals(typeName))
				type = Class.forName("java.lang.Integer");
			else
			if ("long".equals(typeName))
				type = Class.forName("java.lang.Long");
			else
			if ("float".equals(typeName))
				type = Class.forName("java.lang.Float");
			else
			if ("double".equals(typeName))
				type = Class.forName("java.lang.Double");
			else
			if ("boolean".equals(typeName))
				type = Class.forName("java.lang.Boolean");
			else
			if ("char".equals(typeName))
				type = Class.forName("java.lang.Character");
			else
			if ("byte".equals(typeName))
				type = Class.forName("java.lang.Byte");
		if (typeName.startsWith("java.math.") || "java.util.Date".equals(typeName))
		{
			String value = strValue.toString().trim();
			if (value.indexOf(":") == -1)
				value = (new StringBuilder(String.valueOf(value))).append(" 00:00:00").toString();
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
			ret = format.parse(value);
		} else
		if (typeName.equals("java.lang.String"))
		{
			ret = strValue.toString();
		} else
		{
			if (typeName.equals("java.sql.Timestamp") && strValue != null)
			{
				String value = strValue.toString().trim();
				int len = value.trim().length();
				if (len > 7 && len < 11)
					value = (new StringBuilder(String.valueOf(value))).append(" 00:00:00.0").toString();
				else
				if (len > 11 && value.indexOf(".") == -1)
					value = (new StringBuilder(String.valueOf(value))).append(".0").toString();
				strValue = value;
			} else
			if (typeName.equals("java.sql.Date") && strValue != null)
			{
				String value = strValue.toString().trim();
				if (value.length() > 10)
					value = value.substring(0, 10);
				strValue = value;
			}
			Method method = type.getMethod("valueOf", new Class[] {
				"java.lang.String".getClass()
			});
			ret = method.invoke(type, new Object[] {
				strValue.toString()
			});
		}
		return ret;
	}
}
