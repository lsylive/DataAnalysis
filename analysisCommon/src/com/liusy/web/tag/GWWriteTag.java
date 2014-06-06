


//   GWWriteTag.java

package com.liusy.web.tag;

import javax.servlet.jsp.JspException;
import org.apache.struts.taglib.bean.WriteTag;

// Referenced classes of package com.liusy.web.tag:
//			TagUtils

public class GWWriteTag extends WriteTag
{

	private static final long serialVersionUID = 1L;

	public GWWriteTag()
	{
	}

	public int doStartTag()
		throws JspException
	{
		if (ignore && TagUtils.lookup(pageContext, name, scope) == null)
			return 0;
		Object value = TagUtils.lookup(pageContext, name, property, scope);
		String output;
		if (value == null)
			output = "";
		else
			output = formatValue(value);
		String out;
		if (filter)
			out = TagUtils.filter(output);
		else
			out = output;
		if ("".equals(out))
			out = "&nbsp;";
		TagUtils.write(pageContext, out);
		return 0;
	}
}
