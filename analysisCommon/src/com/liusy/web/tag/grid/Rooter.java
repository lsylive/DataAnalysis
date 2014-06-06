


//   Rooter.java

package com.liusy.web.tag.grid;

import com.liusy.web.tag.base.Base;
import java.util.Map;

public class Rooter extends Base
{

	private String url;
	private String showType;
	private String linkStyle;
	private String linkStyleClass;
	private String textStyle;
	private String textStyleClass;
	private String parameterName;
	private Object parameters;
	private Map properties;

	public Rooter()
	{
		url = null;
		showType = null;
		linkStyle = null;
		linkStyleClass = null;
		textStyle = null;
		textStyleClass = null;
		parameters = null;
		parameterName = null;
	}

	public void setProperties(Map h)
	{
		properties = h;
	}

	public void generate(StringBuffer sb, StringBuffer sbs)
	{
		loadSchema(properties, "grid.rooter");
		if (showType == null)
			sb.append("&nbsp;");
		else
		if (showType.equalsIgnoreCase("all"))
		{
			generateRootBegin(sb);
			generateRootData(sb);
			generateRootPage(sb);
			generateRootEnd(sb);
		} else
		if (showType.equalsIgnoreCase("page"))
		{
			generateRootBegin(sb);
			generateRootPage(sb);
			generateRootEnd(sb);
		} else
		if (showType.equalsIgnoreCase("data"))
		{
			generateRootBegin(sb);
			generateRootData(sb);
			generateRootEnd(sb);
		} else
		{
			sb.append("&nbsp;");
		}
	}

	private void generateRootBegin(StringBuffer sb)
	{
		sb.append("       <table");
		if (width != null)
			sb.append((new StringBuilder(" width=\"")).append(width).append("\"").toString());
		if (height != null)
			sb.append((new StringBuilder(" height=\"")).append(height).append("\"").toString());
		sb.append(" cellpadding=\"0\" cellspacing=\"0\" class=\"pageTurn\" >\r\n");
		sb.append("          <tr height=\"100%\">\r\n");
	}

	private void generateRootPage(StringBuffer sb)
	{
		String rc = getProperty(parameters, "recordCount");
		String pn = getProperty(parameters, "pageNumber");
		String pc = getProperty(parameters, "pageCount");
		sb.append("             <td align=\"right\" width=\"");
		if (showType.equalsIgnoreCase("all"))
			sb.append("50%\"");
		else
			sb.append("100%\"");
		generateAttr(sb, "style", style);
		generateAttr(sb, "class", styleClass);
		sb.append(" >\r\n");
		sb.append("<div class=\"pageTurnWrap\">");
		if (rc == null || rc.equals("") || rc.equals("0") || pn == null || pn.equals("") || pc == null || pc.equals(""))
		{
			sb.append("<a href=\"#\" class=\"greyleftPageMore\" title=\"��һҳ\" >��һҳ</a>");
			sb.append("<a href=\"#\" class=\"greyleftPage\" title=\"��һҳ\" >��һҳ</a>");
			sb.append("<a href=\"#\" class=\"greyrightPage\" title=\"��һҳ\" >��һҳ</a>");
			sb.append("<a href=\"#\" class=\"greyrightPageMore\" title=\"���һҳ\" >���һҳ</a>");
		} else
		{
			if (pn.equals("0") || pn.equals("1"))
			{
				sb.append("<a href=\"#\" class=\"greyleftPageMore\" title=\"��һҳ\" >��һҳ</a>");
				sb.append("<a href=\"#\" class=\"greyleftPage\" title=\"��һҳ\" >��һҳ</a>");
			} else
			{
				sb.append("<a href=\"javascript:goFirstPage();\" class=\"leftPageMore\" title=\"��һҳ\" >��һҳ</a>");
				sb.append("<a href=\"javascript:goPreviousPage();\" class=\"leftPage\" title=\"��һҳ\" >��һҳ</a>");
			}
			if (pn.equals(pc))
			{
				sb.append("<a href=\"#\" class=\"greyrightPage\" title=\"��һҳ\" >��һҳ</a>");
				sb.append("<a href=\"#\" class=\"greyrightPageMore\" title=\"���һҳ\" >���һҳ</a>");
			} else
			{
				sb.append("<a href=\"javascript:goNextPage();\" class=\"rightPage\" title=\"��һҳ\" >��һҳ</a>");
				sb.append("<a href=\"javascript:goLastPage();\" class=\"rightPageMore\" title=\"���һҳ\" >���һҳ</a>");
			}
		}
		sb.append(" </div>\r\n");
		sb.append("             </td>\r\n");
	}

	private void generateRootData(StringBuffer sb)
	{
		sb.append("             <td align=\"left\" width=\"");
		if (showType.equalsIgnoreCase("all"))
			sb.append("50%\"");
		else
			sb.append("100%\"");
		generateAttr(sb, "style", style);
		generateAttr(sb, "class", styleClass);
		sb.append(" >\r\n");
		sb.append("                ");
		String rc = getProperty(parameters, "recordCount");
		String cp = getProperty(parameters, "pageNumber");
		String pc = getProperty(parameters, "pageCount");
		String ps = getProperty(parameters, "pageSize");
		if (rc != null && !rc.equals(""))
		{
			sb.append("&nbsp;��&nbsp;");
			sb.append(rc);
			sb.append("&nbsp;��&nbsp;");
		}
		if (cp != null && !cp.equals("") && pc != null && !pc.equals(""))
		{
			sb.append("&nbsp;��&nbsp;");
			sb.append(cp);
			sb.append("&nbsp;ҳ");
		}
		if (pc != null && !pc.equals(""))
		{
			sb.append("/��&nbsp;");
			sb.append(pc);
			sb.append("&nbsp;ҳ&nbsp;");
		}
		if (pc != null && !pc.equals(""))
		{
			sb.append("&nbsp;");
			sb.append("<input type=\"textbox\" size=3 align=\"right\" class=\"pTextStyle\"");
			generateAttr(sb, "style", textStyle);
			generateAttr(sb, "class", textStyleClass);
			sb.append((new StringBuilder(" name=\"")).append(parameterName).append(".pageSize\"").toString());
			sb.append(" value=\"");
			if (ps != null)
				sb.append(ps);
			else
				sb.append("10");
			sb.append("\" onKeyPress=\"setpagesize()\">");
			sb.append("��/ҳ");
		}
		sb.append("\r\n");
		sb.append("             </td>\r\n");
	}

	private void generateRootEnd(StringBuffer sb)
	{
		sb.append("          </tr>\r\n");
		sb.append("       </table>\r\n");
	}

	public String getLinkStyleClass()
	{
		return linkStyleClass;
	}

	public void setLinkStyleClass(String x)
	{
		linkStyleClass = x;
	}

	public String getLinkStyle()
	{
		return linkStyle;
	}

	public void setLinkStyle(String x)
	{
		linkStyle = x;
	}

	public String getTextStyleClass()
	{
		return textStyleClass;
	}

	public void setTextStyleClass(String x)
	{
		textStyleClass = x;
	}

	public String getTextStyle()
	{
		return textStyle;
	}

	public void setTextStyle(String x)
	{
		textStyle = x;
	}

	public String getUrl()
	{
		return url;
	}

	public void setUrl(String x)
	{
		url = x;
	}

	public String getShowType()
	{
		return showType;
	}

	public void setShowType(String x)
	{
		showType = x;
	}

	public Object getParameters()
	{
		return parameters;
	}

	public void setParameters(Object x)
	{
		parameters = x;
	}

	public String getParameterName()
	{
		return parameterName;
	}

	public void setParameterName(String x)
	{
		parameterName = x;
	}
}
