


//   ButtonTag.java

package com.liusy.web.tag;

import com.liusy.core.util.Const;
import com.liusy.core.util.Session;
import java.util.Map;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.BodyContent;

// Referenced classes of package com.liusy.web.tag:
//			BaseTag, TagUtils

public class ButtonTag extends BaseTag
{

	private static final long serialVersionUID = 1L;
	private String value;
	private String code;
	private String icon;

	public ButtonTag()
	{
	}

	public int doStartTag()
		throws JspException
	{
		return 2;
	}

	public int doEndTag()
		throws JspException
	{
		Session session = (Session)pageContext.getSession().getAttribute(Const.SESSION);
		if (code != null && !code.equals("") && !session.getPrivileges().containsKey(code))
			return 6;
		StringBuffer out = new StringBuffer("");
		out.append("<a ");
		if (styleid != null)
			out.append((new StringBuilder(" id=\"")).append(styleid).append("\"").toString());
		if (name != null)
			out.append((new StringBuilder(" name=\"")).append(name).append("\"").toString());
		if (styleClass != null)
			out.append((new StringBuilder(" class=\"")).append(styleClass).append("\"").toString());
		else
			out.append(" class=\"btnStyle\"");
		if (onclick != null)
			out.append((new StringBuilder(" href=\"javascript:")).append(onclick).append(";\"").toString());
		if (title != null)
			out.append((new StringBuilder(" title=\"")).append(title).append("\"").toString());
		out.append(" ><strong>");
		if (icon != null)
			out.append((new StringBuilder("<span class=\"")).append(icon).append("\">&nbsp;</span>").toString());
		out.append(getBodyContent().getString());
		out.append("</strong></a>");
		TagUtils.write(pageContext, out.toString());
		return 6;
	}

	public void release()
	{
		super.release();
		value = null;
	}

	public void setValue(String x)
	{
		value = x;
	}

	public String getCode()
	{
		return code;
	}

	public void setCode(String code)
	{
		this.code = code;
	}

	public String getIcon()
	{
		return icon;
	}

	public void setIcon(String icon)
	{
		this.icon = icon;
	}
}
