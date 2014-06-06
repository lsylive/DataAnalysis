


//   Base.java

package com.liusy.web.tag.base;

import java.util.*;
import javax.servlet.jsp.JspException;
import org.apache.commons.beanutils.PropertyUtils;

public class Base
{

	protected String name;
	protected String title;
	protected String property;
	protected String width;
	protected String style;
	protected String styleClass;
	protected String styleid;
	protected String height;
	protected String align;
	protected String valign;
	protected String accesskey;
	protected String tabindex;
	protected String alt;
	protected String altKey;
	protected String bundle;
	protected String id;
	protected String nowrap;
	protected String indexed;
	protected String disabled;
	protected String readonly;
	protected String onmouseover;
	protected String onmouseout;
	protected String onclick;
	protected String ondblclick;
	protected String onmousemove;
	protected String onmousedown;
	protected String onmouseup;
	protected String onkeydown;
	protected String onkeyup;
	protected String onkeypress;
	protected String onselect;
	protected String onchange;
	protected String onblur;
	protected String onfocus;

	public String getWidth()
	{
		return width;
	}

	public void setWidth(String x)
	{
		width = x;
	}

	public String getTitle()
	{
		return title;
	}

	public void setTitle(String x)
	{
		title = x;
	}

	public String getProperty()
	{
		return property;
	}

	public void setProperty(String x)
	{
		property = x;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String x)
	{
		name = x;
	}

	public String getHeight()
	{
		return height;
	}

	public void setHeight(String x)
	{
		height = x;
	}

	public String getStyleClass()
	{
		return styleClass;
	}

	public void setStyleClass(String x)
	{
		styleClass = x;
	}

	public String getStyle()
	{
		return style;
	}

	public void setStyle(String x)
	{
		style = x;
	}

	public String getStyleId()
	{
		return styleid;
	}

	public void setStyleId(String x)
	{
		styleid = x;
	}

	public String getAlign()
	{
		return align;
	}

	public void setAlign(String x)
	{
		align = x;
	}

	public String getValign()
	{
		return valign;
	}

	public void setValign(String x)
	{
		valign = x;
	}

	public String getAlt()
	{
		return alt;
	}

	public void setAlt(String x)
	{
		alt = x;
	}

	public String getAltKey()
	{
		return altKey;
	}

	public void setAltKey(String x)
	{
		altKey = x;
	}

	public String getAccessKey()
	{
		return accesskey;
	}

	public void setAccessKey(String x)
	{
		accesskey = x;
	}

	public String getTabIndex()
	{
		return tabindex;
	}

	public void setTabIndex(String x)
	{
		tabindex = x;
	}

	public String getBundle()
	{
		return bundle;
	}

	public void setBundle(String x)
	{
		bundle = x;
	}

	public String getId()
	{
		return id;
	}

	public void setId(String x)
	{
		id = x;
	}

	public String getIndexed()
	{
		return indexed;
	}

	public void setIndexed(String b)
	{
		indexed = b;
	}

	public String getDisabled()
	{
		return disabled;
	}

	public void setDisabled(String b)
	{
		disabled = b;
	}

	public String getReadonly()
	{
		return readonly;
	}

	public void setReadonly(String b)
	{
		readonly = b;
	}

	public String getOnMouseOver()
	{
		return onmouseover;
	}

	public void setOnMouseOver(String x)
	{
		onmouseover = x;
	}

	public String getOnMouseOut()
	{
		return onmouseout;
	}

	public void setOnMouseOut(String x)
	{
		onmouseout = x;
	}

	public String getOnClick()
	{
		return onclick;
	}

	public void setOnClick(String x)
	{
		onclick = x;
	}

	public String getOnDblClick()
	{
		return ondblclick;
	}

	public void setOnDblClick(String x)
	{
		ondblclick = x;
	}

	public String getOnMouseMove()
	{
		return onmousemove;
	}

	public void setOnMouseMove(String x)
	{
		onmousemove = x;
	}

	public String getOnMouseUp()
	{
		return onmouseup;
	}

	public void setOnMouseUp(String x)
	{
		onmouseup = x;
	}

	public String getOnMouseDown()
	{
		return onmousedown;
	}

	public void setOnMouseDown(String x)
	{
		onmousedown = x;
	}

	public String getOnKeyUp()
	{
		return onkeyup;
	}

	public void setOnKeyUp(String x)
	{
		onkeyup = x;
	}

	public String getOnKeyDown()
	{
		return onkeydown;
	}

	public void setOnKeyDown(String x)
	{
		onkeydown = x;
	}

	public String getOnKeyPress()
	{
		return onkeypress;
	}

	public void setOnKeyPress(String x)
	{
		onkeypress = x;
	}

	public String getOnSelect()
	{
		return onselect;
	}

	public void setOnSelect(String x)
	{
		onselect = x;
	}

	public String getOnChange()
	{
		return onchange;
	}

	public void setOnChange(String x)
	{
		onchange = x;
	}

	public String getOnBlur()
	{
		return onblur;
	}

	public void setOnBlur(String x)
	{
		onblur = x;
	}

	public String getOnFocus()
	{
		return onfocus;
	}

	public void setOnFocus(String x)
	{
		onfocus = x;
	}

	public void loadSchema(Map props, String objectName)
	{
		Map attrs = filterProperties(props, objectName);
		if (attrs == null)
			return;
		for (Iterator names = attrs.keySet().iterator(); names.hasNext();)
		{
			String key = (String)names.next();
			try
			{
				String val = (String)PropertyUtils.getProperty(this, key);
				if (val == null)
					PropertyUtils.setProperty(this, key, (String)attrs.get(key));
			}
			catch (Exception exception) { }
		}

	}

	private Map filterProperties(Map props, String filter)
	{
		if (props == null)
			return null;
		if (filter == null)
			return props;
		Map newmap = new HashMap();
		for (Iterator it = props.keySet().iterator(); it.hasNext();)
		{
			String key = (String)it.next();
			if (key.indexOf((new StringBuilder(String.valueOf(filter))).append(".").toString()) == 0)
			{
				String attr = key.replaceFirst((new StringBuilder(String.valueOf(filter))).append(".").toString(), "");
				if (attr.indexOf(".") == -1)
					newmap.put(attr, (String)props.get(key));
			}
		}

		return newmap;
	}

	public String toHtml()
	{
		StringBuffer sb = new StringBuffer();
		generateAttr(sb, "name", name);
		generateAttr(sb, "title", title);
		generateAttr(sb, "width", width);
		generateAttr(sb, "style", style);
		generateAttr(sb, "class", styleClass);
		generateAttr(sb, "styleid", styleid);
		generateAttr(sb, "height", height);
		generateAttr(sb, "align", align);
		generateAttr(sb, "valign", valign);
		generateAttr(sb, "accesskey", accesskey);
		generateAttr(sb, "tabindex", tabindex);
		generateAttr(sb, "alt", alt);
		generateAttr(sb, "altKey", altKey);
		generateAttr(sb, "bundle", bundle);
		generateAttr(sb, "id", id);
		if (disabled != null && disabled.equalsIgnoreCase("true"))
			sb.append(" disabled ");
		if (readonly != null && readonly.equalsIgnoreCase("true"))
			sb.append(" readonly ");
		if (nowrap != null && nowrap.equalsIgnoreCase("true"))
			sb.append(" nowrap ");
		generateAttr(sb, "onmouseover", onmouseover);
		generateAttr(sb, "onmouseout", onmouseout);
		generateAttr(sb, "onclick", onclick);
		generateAttr(sb, "ondblclick", ondblclick);
		generateAttr(sb, "onmousemove", onmousemove);
		generateAttr(sb, "onmousedown", onmousedown);
		generateAttr(sb, "onmouseup", onmouseup);
		generateAttr(sb, "onkeydown", onkeydown);
		generateAttr(sb, "onkeyup", onkeyup);
		generateAttr(sb, "onkeypress", onkeypress);
		generateAttr(sb, "onselect", onselect);
		generateAttr(sb, "onchange", onchange);
		generateAttr(sb, "onblur", onblur);
		generateAttr(sb, "onfocus", onfocus);
		return sb.toString();
	}

	protected String getParameters(String value, Object obj)
		throws JspException
	{
		if (obj == null || value == null)
			return null;
		String res = value.replaceAll("\\{\\}", "");
		String para = "";
		String paravalue = "";
		int start = res.indexOf("{");
		int end = res.indexOf("}");
		if (start > 0 && end > 0 && end > start)
		{
			para = res.substring(start + 1, end);
			Object o = getProperty(obj, para.toLowerCase());
			if (o == null ||o.equals(""))
				o = getProperty(obj, para.toUpperCase());
			if (o == null)
				paravalue = "";
			else
				paravalue = o.toString();
			res = res.replaceFirst((new StringBuilder("\\{")).append(para).append("\\}").toString(), paravalue);
			res = getParameters(res, obj);
		}
		return res;
	}

	protected String getProperty(Object obj, String property)
	{
		String result = null;
		Object object = null;
		try
		{
			result = PropertyUtils.getProperty(obj, property).toString();
		}
		catch (Exception ex)
		{
			result = null;
		}
		if (result == null)
			try
			{
				object = PropertyUtils.getProperty(obj, "parameters");
				if (object instanceof HashMap)
				{
					HashMap map = (HashMap)object;
					result = (String)map.get(property);
				}
			}
			catch (Exception ex)
			{
				result = null;
			}
		return result;
	}

	protected void generateAttr(StringBuffer sb, String name, String value, String secondvalue)
	{
		if (value == null)
			generateAttr(sb, name, secondvalue);
		else
			generateAttr(sb, name, value);
	}

	protected void generateAttr(StringBuffer sb, String name, String value)
	{
		if (name == null || value == null)
		{
			return;
		} else
		{
			sb.append((new StringBuilder(" ")).append(name).append("=\"").append(value).append("\"").toString());
			return;
		}
	}

	public Base()
	{
		name = null;
		title = null;
		property = null;
		width = null;
		style = null;
		styleClass = null;
		styleid = null;
		height = null;
		align = null;
		valign = null;
		accesskey = null;
		tabindex = null;
		alt = null;
		altKey = null;
		bundle = null;
		id = null;
		nowrap = null;
		indexed = null;
		disabled = null;
		readonly = null;
		onmouseover = null;
		onmouseout = null;
		onclick = null;
		ondblclick = null;
		onmousemove = null;
		onmousedown = null;
		onmouseup = null;
		onkeydown = null;
		onkeyup = null;
		onkeypress = null;
		onselect = null;
		onchange = null;
		onblur = null;
		onfocus = null;
		name = null;
		property = null;
		width = null;
		height = null;
		style = null;
		styleClass = null;
		align = null;
		valign = null;
		accesskey = null;
		tabindex = null;
		alt = null;
		altKey = null;
		bundle = null;
		id = null;
		indexed = null;
		disabled = null;
		readonly = null;
		onmouseover = null;
		onmouseout = null;
		onclick = null;
		ondblclick = null;
		onmousemove = null;
		onmousedown = null;
		onmouseup = null;
		onkeydown = null;
		onkeyup = null;
		onkeypress = null;
		onselect = null;
		onchange = null;
		onblur = null;
		onfocus = null;
	}

	public String getNowrap()
	{
		return nowrap;
	}

	public void setNowrap(String nowrap)
	{
		this.nowrap = nowrap;
	}
}
