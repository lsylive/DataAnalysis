


//   Column.java

package com.liusy.web.tag.grid;

import com.liusy.web.tag.base.Base;
import java.util.List;
import java.util.Map;
import javax.servlet.jsp.JspException;

// Referenced classes of package com.liusy.web.tag.grid:
//			Button, Options

public class Column extends Base
{

	public static String DYNAMICCOLUMNS = "columns";
	private String headerStyleClass;
	private String headerStyle;
	private String headerAlign;
	private String headerValign;
	private String headeronmouseover;
	private String headeronmouseout;
	private String headeronclick;
	private String itemType;
	private String itemEditType;
	private String itemPattern;
	private String itemStyleClass;
	private String itemStyle;
	private String href;
	private String itemHyperLinkTarget;
	private String order;
	private String id;
	private List buttons;
	private Options options;
	private String editProperty;
	private Map properties;
	private String selectAll;
	private boolean matched;

	public Column()
	{
		headerStyleClass = null;
		headerStyle = null;
		headerAlign = null;
		headerValign = null;
		headeronmouseover = null;
		headeronmouseout = null;
		headeronclick = null;
		itemType = null;
		itemEditType = null;
		itemPattern = null;
		itemStyleClass = null;
		itemStyle = null;
		href = null;
		itemHyperLinkTarget = null;
		order = null;
		id = null;
		buttons = null;
		options = null;
		editProperty = null;
	}

	public List getButtons()
	{
		return buttons;
	}

	public void setButtons(List x)
	{
		buttons = x;
	}

	public String getId()
	{
		return id;
	}

	public void setId(String x)
	{
		id = x;
	}

	public String getOrder()
	{
		return order;
	}

	public void setOrder(String x)
	{
		order = x;
	}

	public String getHeaderStyleClass()
	{
		return headerStyleClass;
	}

	public void setHeaderStyleClass(String x)
	{
		headerStyleClass = x;
	}

	public String getHeaderStyle()
	{
		return headerStyle;
	}

	public void setHeaderStyle(String x)
	{
		headerStyle = x;
	}

	public String getHeaderAlign()
	{
		return headerAlign;
	}

	public void setHeaderAlign(String x)
	{
		headerAlign = x;
	}

	public String getHeaderValign()
	{
		return headerValign;
	}

	public void setHeaderValign(String x)
	{
		headerValign = x;
	}

	public String getHeaderOnMouseOver()
	{
		return headeronmouseover;
	}

	public void setHeaderOnMouseOver(String x)
	{
		headeronmouseover = x;
	}

	public String getHeaderOnMouseOut()
	{
		return headeronmouseout;
	}

	public void setHeaderOnMouseOut(String x)
	{
		headeronmouseout = x;
	}

	public String getHeaderOnClick()
	{
		return headeronclick;
	}

	public void setHeaderOnClick(String x)
	{
		headeronclick = x;
	}

	public String getItemType()
	{
		return itemType;
	}

	public void setItemType(String x)
	{
		itemType = x;
	}

	public String getItemEditType()
	{
		return itemEditType;
	}

	public void setItemEditType(String x)
	{
		itemEditType = x;
	}

	public String getHref()
	{
		return href;
	}

	public void setHref(String x)
	{
		href = x;
	}

	public String getItemPattern()
	{
		return itemPattern;
	}

	public void setItemPattern(String x)
	{
		itemPattern = x;
	}

	public String getItemHyperLinkTarget()
	{
		return itemHyperLinkTarget;
	}

	public void setItemHyperLinkTarget(String x)
	{
		itemHyperLinkTarget = x;
	}

	public String getItemStyleClass()
	{
		return itemStyleClass;
	}

	public void setItemStyleClass(String x)
	{
		itemStyleClass = x;
	}

	public String getItemStyle()
	{
		return itemStyle;
	}

	public void setItemStyle(String x)
	{
		itemStyle = x;
	}

	public Options getOptions()
	{
		return options;
	}

	public void setOptions(Options o)
	{
		options = o;
	}

	public String getEditProperty()
	{
		return editProperty;
	}

	public void setEditProperty(String x)
	{
		editProperty = x;
	}

	public void setMatched(boolean b)
	{
		matched = b;
	}

	public void setProperties(Map h)
	{
		properties = h;
	}

	public void generateHeader(StringBuffer sb, String status, String order, String desc)
	{
		loadSchema(properties, "grid.column");
		if (itemType != null && itemType.equalsIgnoreCase("hidden"))
		{
			sb.append("      <th style=\"display:none;\">&nbsp;</th>\r\n");
			return;
		}
		sb.append("      <th");
		generateAttr(sb, "width", width);
		generateAttr(sb, "valign", headerValign);
		generateAttr(sb, "align", headerAlign);
		generateAttr(sb, "style", headerStyle);
		generateAttr(sb, "class", headerStyleClass);
		if (status == null || status.equals("") || status.equalsIgnoreCase("list"))
		{
			generateAttr(sb, "onClick", headeronclick);
			generateAttr(sb, "onMouseOver", headeronmouseover);
			generateAttr(sb, "onMouseOut", headeronmouseout);
		}
		sb.append(">\r\n");
		sb.append("         ");
		if (itemType != null && itemType.equalsIgnoreCase("checkbox") && selectAll != null && selectAll.equalsIgnoreCase("true"))
			sb.append((new StringBuilder("<input type=\"checkbox\" id=\"selectAll\" onClick=\"changeSelection('")).append(property).append("')\" >").toString());
		else
		if (name != null)
		{
			sb.append(name);
			if (order != null && order.equalsIgnoreCase(property))
			{
				if (desc != null && desc.equalsIgnoreCase("asc"))
					sb.append("<font class=\"icons\">5</font>");
				if (desc != null && desc.equalsIgnoreCase("desc"))
					sb.append("<font class=\"icons\">6</font>");
			}
		} else
		{
			sb.append("&nbsp;");
		}
		sb.append("\r\n");
		sb.append("      </th>\r\n");
	}

	public void generate(StringBuffer sb, String status, Object obj)
		throws JspException
	{
		loadSchema(properties, "grid.column");
		if (itemType != null && itemType.equalsIgnoreCase("hidden"))
		{
			sb.append("      <td style=\"display:none;\">");
			sb.append((new StringBuilder("         <input type=\"hidden\" id=\"")).append(property).append("\" name=\"").append(property).append("\" value=\"").toString());
			sb.append(getProp(obj, property));
			sb.append("\" />\r\n");
			sb.append("      </td>");
			return;
		}
		sb.append("      <td");
		generateAttr(sb, "style", style);
		generateAttr(sb, "class", styleClass);
		generateAttr(sb, "align", align);
		generateAttr(sb, "valign", valign);
		generateAttr(sb, "width", width);
		if (itemType == null || itemType.equalsIgnoreCase("hyperlink"))
			generateAttr(sb, "title", getProp(obj, property));
		sb.append(" >\r\n");
		if (itemType == null)
		{
			sb.append("         ");
			sb.append(getProp(obj, property));
			sb.append("\r\n");
		} else
		if (itemType.equalsIgnoreCase("radio"))
			generateOption(sb, status, property, getProperty(obj, property));
		else
		if (itemType.equalsIgnoreCase("checkbox"))
			generateCheckbox(sb, status, property, getProperty(obj, property));
		else
		if (itemType.equalsIgnoreCase("buttons"))
			generateButtons(sb, status, buttons, obj);
		else
		if (itemType.equalsIgnoreCase("hyperlink"))
		{
			generateLink(sb, status, obj);
		} else
		{
			sb.append("         ");
			sb.append(getProp(obj, property));
			sb.append("\r\n");
		}
		sb.append("      </td>\r\n");
	}

	public void generateBlank(StringBuffer sb, String status)
		throws JspException
	{
		loadSchema(properties, "grid.column");
		sb.append("      <td");
		generateAttr(sb, "style", style);
		generateAttr(sb, "class", styleClass);
		generateAttr(sb, "align", align);
		generateAttr(sb, "valign", valign);
		generateAttr(sb, "width", width);
		sb.append(" >\r\n");
		sb.append("         &nbsp;\r\n");
		sb.append("      </td>\r\n");
	}

	private String getProp(Object obj, String property)
	{
		String tmp = getProperty(obj, property);
		if (tmp == null)
			return "&nbsp;";
		if ("".equals(tmp))
			return "&nbsp;";
		else
			return tmp;
	}

	private void generateButtons(StringBuffer sb, String status, List al, Object obj)
		throws JspException
	{
		Button b = null;
		if (al == null)
			return;
		for (int i = 0; i < al.size(); i++)
		{
			b = (Button)al.get(i);
			b.setProperties(properties);
			b.generate(sb, obj, status, matched);
		}

	}

	private void generateLink(StringBuffer sb, String status, Object obj)
		throws JspException
	{
		sb.append("         ");
		if (status == null || status.equals("") || status.equalsIgnoreCase("list"))
		{
			if (href == null)
			{
				sb.append(getProp(obj, property));
			} else
			{
				sb.append("<a ");
				generateAttr(sb, "href", href);
				generateAttr(sb, "target", itemHyperLinkTarget);
				generateAttr(sb, "style", itemStyle);
				generateAttr(sb, "class", itemStyleClass);
				generateAttr(sb, "onClick", getParameters(onclick, obj));
				sb.append(" >");
				sb.append(getProp(obj, property));
				sb.append("</a>\r\n");
			}
		} else
		{
			sb.append(getProp(obj, property));
		}
	}

	private void generateOption(StringBuffer sb, String status, String property, String value)
	{
		sb.append("         ");
		sb.append("<input type=\"radio\"");
		generateAttr(sb, "name", property);
		generateAttr(sb, "style", itemStyle);
		generateAttr(sb, "class", itemStyleClass);
		if (value == null)
		{
			sb.append(" disabled ");
		} else
		{
			generateAttr(sb, "value", value);
			if (status == null || status.equals("") || status.equalsIgnoreCase("list"))
				sb.append("");
			else
				sb.append(" disabled ");
		}
		sb.append(">\r\n");
	}

	private void generateCheckbox(StringBuffer sb, String status, String property, String value)
	{
		sb.append("         ");
		sb.append("<input type=\"checkbox\"");
		generateAttr(sb, "name", property);
		generateAttr(sb, "style", itemStyle);
		generateAttr(sb, "class", itemStyleClass);
		if (value == null)
		{
			sb.append(" disabled=\"true\" ");
		} else
		{
			generateAttr(sb, "value", value);
			if (status == null || status.equals("") || status.equalsIgnoreCase("list"))
				sb.append("");
			else
				sb.append(" disabled=\"true\" ");
		}
		sb.append(">\r\n");
	}

	public String getSelectAll()
	{
		return selectAll;
	}

	public void setSelectAll(String selectAll)
	{
		this.selectAll = selectAll;
	}

}
