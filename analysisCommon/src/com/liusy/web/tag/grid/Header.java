


//   Header.java

package com.liusy.web.tag.grid;

import com.liusy.web.tag.base.Base;
import java.util.List;
import java.util.Map;

// Referenced classes of package com.liusy.web.tag.grid:
//			Column

public class Header extends Base
{

	private Object parameters;
	private String status;
	private List columns;
	private Map properties;

	public Header()
	{
	}

	public void setParameters(Object o)
	{
		parameters = o;
	}

	public void setStatus(String x)
	{
		status = x;
	}

	public void setColumns(List c)
	{
		columns = c;
	}

	public void setProperties(Map h)
	{
		properties = h;
	}

	public void generate(StringBuffer sb)
	{
		loadSchema(properties, "grid.header");
		sb.append("   <tr");
		generateAttr(sb, "style", style);
		generateAttr(sb, "class", styleClass);
		generateAttr(sb, "height", height);
		sb.append(">\r\n");
		Column col = null;
		if (columns == null || columns.size() == 0)
		{
			sb.append("      <th>&nbsp;</th>\r\n");
		} else
		{
			for (int i = 0; i < columns.size(); i++)
			{
				col = (Column)columns.get(i);
				String order = getProperty(parameters, "order");
				String desc = getProperty(parameters, "desc");
				col.generateHeader(sb, status, order, desc);
			}

		}
		sb.append("   </tr>\r\n");
	}
}
