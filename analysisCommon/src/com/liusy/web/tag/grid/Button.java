


//   Button.java

package com.liusy.web.tag.grid;

import com.liusy.web.tag.base.Base;
import java.util.Map;
import javax.servlet.jsp.JspException;

public class Button extends Base
{

	private String value;
	private String editType;
	private Map properties;

	public String getValue()
	{
		return value;
	}

	public void setValue(String x)
	{
		value = x;
	}

	public String getEditType()
	{
		return editType;
	}

	public void setEditType(String x)
	{
		editType = x;
	}

	public void setProperties(Map h)
	{
		properties = h;
	}

	public void generate(StringBuffer sb, Object obj, String status, boolean matched)
		throws JspException
	{
		loadSchema(properties, "grid.column.button");
		boolean enabled = false;
		if (status == null || status.equals("") || status.equalsIgnoreCase("list"))
		{
			if (editType == null || editType.equals("") || editType.equalsIgnoreCase("list"))
				enabled = true;
			else
				return;
		} else
		if (editType == null)
		{
			if (matched)
				return;
			enabled = false;
		} else
		{
			String edittypes[] = editType.split(",");
			boolean b = false;
			for (int i = 0; i < edittypes.length; i++)
			{
				if (!edittypes[i].equalsIgnoreCase(status))
					continue;
				b = true;
				break;
			}

			if (b)
			{
				enabled = true;
				if (!matched)
					return;
			} else
			{
				return;
			}
		}
		sb.append("         ");
		sb.append("<input type=\"button\"");
		generateAttr(sb, "name", property);
		generateAttr(sb, "value", value);
		generateAttr(sb, "style", style);
		generateAttr(sb, "class", styleClass);
		generateAttr(sb, "align", align);
		generateAttr(sb, "valign", valign);
		generateAttr(sb, "width", width);
		generateAttr(sb, "height", height);
		generateAttr(sb, "onClick", getParameters(onclick, obj));
		generateAttr(sb, "onMouseOver", getParameters(onmouseover, obj));
		generateAttr(sb, "onMouseOut", getParameters(onmouseout, obj));
		if (!enabled)
			sb.append(" disabled ");
		sb.append(" >\r\n");
	}

	public Button()
	{
		value = "";
		editType = "";
	}
}
