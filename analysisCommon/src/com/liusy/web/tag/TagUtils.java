


//   TagUtils.java

package com.liusy.web.tag;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.text.MessageFormat;
import java.util.*;
import javax.servlet.jsp.*;
import org.apache.commons.beanutils.PropertyUtils;

// Referenced classes of package com.liusy.web.tag:
//			Consts

public class TagUtils
{

	protected static Locale defaultLocale = Locale.getDefault();
	private static final Map scopes;
	private static int model;

	public TagUtils()
	{
	}

	public static Object lookup(PageContext pageContext, String name, String property, String scope)
		throws JspException
	{
		Object bean;
		String msg = "";
		bean = lookup(pageContext, name, scope);
		if (bean == null)
		{
			JspException e = null;
			if (scope == null)
			{
				msg = "Cannot find bean {0} in any scope.";
				e = new JspException(getMessage(msg, name));
			} else
			{
				msg = "Cannot find bean {0} in scope {1}.";
				e = new JspException(getMessage(msg, name, scope));
			}
			saveException(pageContext, e);
			throw e;
		}
		if (property == null)
			return bean;
		try {
			return PropertyUtils.getProperty(bean, property);
		} catch (IllegalAccessException e) {
			saveException(pageContext, e);
			msg = "Invalid access looking up property {0} of bean {1}.";
			throw new JspException(getMessage(msg, property, name));
		} catch (InvocationTargetException e) {
			saveException(pageContext, e);
			msg = "Invalid access looking up property {0} of bean {1}.";
			throw new JspException(getMessage(msg, property, name));
		} catch (NoSuchMethodException e) {
			saveException(pageContext, e);
			msg = "Invalid access looking up property {0} of bean {1}.";
			throw new JspException(getMessage(msg, property, name));
			}
	}

	public static void write(PageContext pageContext, String text)
		throws JspException
	{
		JspWriter writer = pageContext.getOut();
		try
		{
			writer.print(text);
		}
		catch (IOException e)
		{
			saveException(pageContext, e);
			throw new JspException("can not get pageContext.");
		}
	}

	public static Iterator getIterator(Object collection)
		throws JspException
	{
		if (collection.getClass().isArray())
			collection = Arrays.asList((Object[])collection);
		if (collection instanceof Collection)
			return ((Collection)collection).iterator();
		if (collection instanceof Iterator)
			return (Iterator)collection;
		if (collection instanceof Map)
			return ((Map)collection).entrySet().iterator();
		else
			throw new JspException((new StringBuilder("Cannot create iterator for ")).append(collection.toString()).append(".").toString());
	}

	public static Object lookup(PageContext pageContext, String name, String scopeName)
		throws JspException
	{
		if (scopeName == null)
			return pageContext.findAttribute(name);
		try {
		return pageContext.getAttribute(name, getScope(scopeName));

		} catch (Exception e) {
			saveException(pageContext, e);
			throw new JspException(e);
			
		}

	}

	public static String filter(String value)
	{
		if (value == null || value.length() == 0)
			return value;
		StringBuffer result = null;
		String filtered = null;
		for (int i = 0; i < value.length(); i++)
		{
			filtered = null;
			switch (value.charAt(i))
			{
			case 60: // '<'
				filtered = "&lt;";
				break;

			case 62: // '>'
				filtered = "&gt;";
				break;

			case 38: // '&'
				filtered = "&amp;";
				break;

			case 34: // '"'
				filtered = "&quot;";
				break;

			case 39: // '\''
				filtered = "&#39;";
				break;
			}
			if (result == null)
			{
				if (filtered != null)
				{
					result = new StringBuffer(value.length() + 50);
					if (i > 0)
						result.append(value.substring(0, i));
					result.append(filtered);
				}
			} else
			if (filtered == null)
				result.append(value.charAt(i));
			else
				result.append(filtered);
		}

		return result != null ? result.toString() : value;
	}

	public static String getMessage(String key)
	{
		return getMessage(key, ((Object []) (null)));
	}

	public static String getMessage(String key, Object arg0)
	{
		return getMessage(key, new Object[] {
			arg0
		});
	}

	public static String getMessage(String key, Object arg0, Object arg1)
	{
		return getMessage(key, new Object[] {
			arg0, arg1
		});
	}

	public static String getMessage(String key, Object arg0, Object arg1, Object arg2)
	{
		return getMessage(key, new Object[] {
			arg0, arg1, arg2
		});
	}

	public static String getMessage(String key, Object arg0, Object arg1, Object arg2, Object arg3)
	{
		return getMessage(key, new Object[] {
			arg0, arg1, arg2, arg3
		});
	}

	public static String getMessage(String key, Object args[])
	{
		Locale locale = defaultLocale;
		MessageFormat format = new MessageFormat(escape(key));
		format.setLocale(locale);
		return format.format(((Object) (args)));
	}

	protected static String escape(String string)
	{
		if (string == null || string.indexOf('\'') < 0)
			return string;
		int n = string.length();
		StringBuffer sb = new StringBuffer(n);
		for (int i = 0; i < n; i++)
		{
			char ch = string.charAt(i);
			if (ch == '\'')
				sb.append('\'');
			sb.append(ch);
		}

		return sb.toString();
	}

	public static void saveException(PageContext pageContext, Throwable exception)
	{
		pageContext.setAttribute("com.greatwall.taglib.EXCEPTION", exception, 2);
	}

	public static int getScope(String scopeName)
		throws JspException
	{
		Integer scope = (Integer)scopes.get(scopeName.toLowerCase());
		if (scope == null)
			throw new JspException((new StringBuilder("Invalid bean scope ")).append(scopeName).append(".").toString());
		else
			return scope.intValue();
	}

	public static String confusion(String str)
	{
		if (str == null)
			return null;
		if (model == Consts.MODEL_DEBUG)
			return str;
		if (model == Consts.MODEL_RUNNING)
			return str.replace("\r\n", "").replace("   ", "");
		else
			return str;
	}

	public static boolean isType(String strType)
	{
		if (strType == null)
			return false;
		boolean res = false;
		if (strType.equalsIgnoreCase(Consts.TYPE_CHECKBOX) || strType.equalsIgnoreCase(Consts.TYPE_CALENDAR) || strType.equalsIgnoreCase(Consts.TYPE_FILE) || strType.equalsIgnoreCase(Consts.TYPE_FORM) || strType.equalsIgnoreCase(Consts.TYPE_HIDDEN) || strType.equalsIgnoreCase(Consts.TYPE_IMAGE) || strType.equalsIgnoreCase(Consts.TYPE_LABEL) || strType.equalsIgnoreCase(Consts.TYPE_LINK) || strType.equalsIgnoreCase(Consts.TYPE_MULTISELECT) || strType.equalsIgnoreCase(Consts.TYPE_RADIO) || strType.equalsIgnoreCase(Consts.TYPE_SELECT) || strType.equalsIgnoreCase(Consts.TYPE_TEXT) || strType.equalsIgnoreCase(Consts.TYPE_TEXTAREA))
			res = true;
		return res;
	}

	static 
	{
		scopes = new HashMap();
		scopes.put("page", new Integer(1));
		scopes.put("request", new Integer(2));
		scopes.put("session", new Integer(3));
		scopes.put("application", new Integer(4));
		model = Consts.MODEL_DEBUG;
	}
}
