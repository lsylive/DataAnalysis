


//   Grid.java

package com.liusy.web.tag.grid;

import com.liusy.web.tag.base.Base;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;
import javax.servlet.jsp.JspException;

// Referenced classes of package com.liusy.web.tag.grid:
//			Header, Rooter, Column

public class Grid extends Base
{

	private String schemaName;
	private String status;
	private String scroll;
	private String headerShow;
	private String rooterShow;
	private String cellPadding;
	private String cellSpacing;
	private String border;
	private String frame;
	private String rules;
	private String rowStyle;
	private String rowStyleClass;
	private String rowSelectedStyleClass;
	private String secondRowStyle;
	private String secondRowStyleClass;
	private String secondRowSelectedStyleClass;
	private String rowEventHandle;
	private Header header;
	private List columns;
	private Iterator rows;
	private Rooter rooter;
	private String parameterName;
	private Object parameters;
	private Map properties;
	private String location;
	private String fixRows;
	private String rowDblClick;
	private StringBuffer sbs;

	public void setSchema(String x)
	{
		schemaName = x;
	}

	public void setScroll(String x)
	{
		scroll = x;
	}

	public void setStatus(String x)
	{
		status = x;
	}

	public void setCellPadding(String x)
	{
		cellPadding = x;
	}

	public void setCellSpacing(String x)
	{
		cellSpacing = x;
	}

	public void setBorder(String x)
	{
		border = x;
	}

	public void setFrame(String x)
	{
		frame = x;
	}

	public void setRules(String x)
	{
		rules = x;
	}

	public void setRowEventHandle(String x)
	{
		rowEventHandle = x;
	}

	public void setRowStyle(String x)
	{
		rowStyle = x;
	}

	public void setRowStyleClass(String x)
	{
		rowStyleClass = x;
	}

	public void setRowSelectedStyleClass(String x)
	{
		rowSelectedStyleClass = x;
	}

	public void setSecondRowStyle(String x)
	{
		secondRowStyle = x;
	}

	public void setSecondRowStyleClass(String x)
	{
		secondRowStyleClass = x;
	}

	public void setSecondRowSelectedStyleClass(String x)
	{
		secondRowSelectedStyleClass = x;
	}

	public void setParameterName(String x)
	{
		parameterName = x;
	}

	public void setHeaderShow(String x)
	{
		headerShow = x;
	}

	public void setRooterShow(String x)
	{
		rooterShow = x;
	}

	public void setLocation(String x)
	{
		location = x;
	}

	public void setFixRows(String x)
	{
		fixRows = x;
	}

	public String getSchema()
	{
		return schemaName;
	}

	public String getScroll()
	{
		return scroll;
	}

	public String getStatus()
	{
		return status;
	}

	public String getCellPadding()
	{
		return cellPadding;
	}

	public String getCellSpacing()
	{
		return cellSpacing;
	}

	public String getBorder()
	{
		return border;
	}

	public String getFrame()
	{
		return frame;
	}

	public String getRules()
	{
		return rules;
	}

	public String getRowEventHandle()
	{
		return rowEventHandle;
	}

	public String getRowStyle()
	{
		return rowStyle;
	}

	public String getRowStyleClass()
	{
		return rowStyleClass;
	}

	public String getRowSelectedStyleClass()
	{
		return rowSelectedStyleClass;
	}

	public String getSecondRowStyle()
	{
		return secondRowStyle;
	}

	public String getSecondRowStyleClass()
	{
		return secondRowStyleClass;
	}

	public String getSecondRowSelectedStyleClass()
	{
		return secondRowSelectedStyleClass;
	}

	public String getParameterName()
	{
		return parameterName;
	}

	public String getHeaderShow()
	{
		return headerShow;
	}

	public String getRooterShow()
	{
		return rooterShow;
	}

	public String getLocation()
	{
		return location;
	}

	public String getFixRows()
	{
		return fixRows;
	}

	public void setParameters(Object o)
	{
		parameters = o;
	}

	public Rooter getRooter()
	{
		return rooter;
	}

	public void setRooter(Rooter r)
	{
		rooter = r;
	}

	public Header getHeader()
	{
		return header;
	}

	public void setHeader(Header h)
	{
		header = h;
	}

	public Iterator getRows()
	{
		return rows;
	}

	public void setRows(Iterator it)
	{
		rows = it;
	}

	public List getColumns()
	{
		if (columns == null)
			columns = new ArrayList();
		return columns;
	}

	public void setColumns(List thecolumns)
	{
		columns = thecolumns;
	}

	public void addHeader(Header header)
	{
		this.header = header;
	}

	public void addRooter(Rooter rooter)
	{
		this.rooter = rooter;
	}

	public void addColumn(Column column)
	{
		if (columns == null)
			columns = new ArrayList();
		columns.add(column);
	}

	public void generateGrid(StringBuffer sb)
		throws JspException
	{
		sbs.append("");
		loadProperties();
		loadSchema(properties, "grid");
		sb.append("<table ");
		generateAttr(sb, "height", height);
		generateAttr(sb, "width", width);
		status = getProperty(parameters, "status");
		sb.append(" border=\"0\" cellSpacing=\"0\" cellPadding=\"0\">\r\n");
		if (scroll != null && scroll.equalsIgnoreCase("true"))
		{
			if (headerShow == null || !headerShow.equalsIgnoreCase("false"))
			{
				sb.append("   <tr ");
				generateAttr(sb, "height", header.getHeight());
				sb.append(" >\r\n");
				sb.append("      <td width=\"*\" >\r\n");
				generateBeginGrid(sb);
				generateHeader(sb);
				generateEndGrid(sb);
				sb.append("      </td>\r\n");
				sb.append("      <td width=\"15\">&nbsp;");
				sb.append("      </td>\r\n");
				sb.append("   </tr>\r\n");
			}
			if (rooterShow != null && rooterShow.equalsIgnoreCase("false") && headerShow != null && headerShow.equalsIgnoreCase("false"))
				sb.append("   <tr height=\"100%\">\r\n");
			else
				sb.append("   <tr height=\"*\">\r\n");
			sb.append("      <td colspan=\"2\">\r\n");
			sb.append("<div style=\"width:100%;height:100%;overflow-y:scroll;overflow-x:hidden\">");
			generateBeginGrid(sb);
			generateRows(sb);
			generateEndGrid(sb);
			sb.append("</div>");
			sb.append("      </td>\r\n");
			sb.append("   </tr>\r\n");
			if (rooterShow == null || !rooterShow.equalsIgnoreCase("false"))
			{
				sb.append("   <tr ");
				generateAttr(sb, "height", rooter.getHeight());
				sb.append(" >\r\n");
				sb.append("      <td width=\"*\" >\r\n");
				generateRooter(sb, sbs);
				sb.append("      </td>\r\n");
				sb.append("      <td width=\"15\">&nbsp;");
				sb.append("      </td>\r\n");
				sb.append("   </tr>\r\n");
			}
		} else
		{
			if (rooterShow != null && rooterShow.equalsIgnoreCase("false"))
				sb.append("   <tr height=\"100%\">\r\n");
			else
				sb.append("   <tr height=\"*\">\r\n");
			sb.append("      <td >\r\n");
			generateBeginGrid(sb);
			generateHeader(sb);
			generateRows(sb);
			generateEndGrid(sb);
			sb.append("      </td>\r\n");
			sb.append("   </tr>\r\n");
			if (rooterShow == null || !rooterShow.equalsIgnoreCase("false"))
			{
				sb.append("   <tr ");
				generateAttr(sb, "height", rooter.getHeight());
				sb.append(" >\r\n");
				sb.append("      <td width=\"100%\" >\r\n");
				generateRooter(sb, sbs);
				sb.append("      </td>\r\n");
				sb.append("   </tr>\r\n");
			}
		}
		sb.append("</table>\r\n");
	}

	public void generateScript(StringBuffer sb, StringBuffer sbs)
	{
		sb.append("<script language=\"javascript\">\r\n");
		generateGenScript(sb);
		sb.append("</script>\r\n");
	}

	public void generateGenScript(StringBuffer sb)
	{
		sb.append("\r\n");
		sb.append((new StringBuilder("function ")).append(id).append("_rowMouseOver(obj) {\r\n").toString());
		sb.append("   obj.style.backgroundColor = rowMouseOverBgColor;\r\n");
		sb.append("}\r\n");
		sb.append((new StringBuilder("function ")).append(id).append("_rowMouseOut(obj) {\r\n").toString());
		sb.append("obj.style.backgroundColor = \"#d9edff\";\r\n");
		sb.append("}\r\n");
		sb.append((new StringBuilder("function ")).append(id).append("_secondRowMouseOver(obj) {\r\n").toString());
		sb.append("obj.style.backgroundColor = rowMouseOverBgColor;\r\n");
		sb.append("}\r\n");
		sb.append((new StringBuilder("function ")).append(id).append("_secondRowMouseOut(obj) {\r\n").toString());
		sb.append("obj.style.backgroundColor = \"#e9faff\";\r\n");
		sb.append("}\r\n");
		sb.append((new StringBuilder("function ")).append(id).append("_headerOver(obj) {\r\n").toString());
		sb.append("obj.style.cursor = \"hand\";\r\n");
		sb.append("obj.style.color  = \"#bb0000\";\r\n");
		sb.append("}\r\n");
		sb.append((new StringBuilder("function ")).append(id).append("_headerOut(obj) {\r\n").toString());
		sb.append("obj.style.cursor = \"default\";\r\n");
		sb.append("obj.style.color  = \"#000077\";\r\n");
		sb.append("}\r\n");
	}

	private void generateBeginGrid(StringBuffer sb)
	{
		sb.append("<table");
		generateAttr(sb, "id", id);
		generateAttr(sb, "style", style);
		generateAttr(sb, "class", styleClass);
		if (scroll == null || !scroll.equalsIgnoreCase("true"))
		{
			generateAttr(sb, "height", height);
			generateAttr(sb, "width", width);
		} else
		{
			generateAttr(sb, "width", "100%");
		}
		generateAttr(sb, "border", border);
		generateAttr(sb, "cellSpacing", cellSpacing);
		generateAttr(sb, "cellPadding", cellPadding);
		generateAttr(sb, "rules", rules);
		generateAttr(sb, "frame", frame);
		sb.append(">\r\n");
	}

	private void generateHeader(StringBuffer sb)
	{
		if (header == null)
		{
			return;
		} else
		{
			header.setColumns(columns);
			header.setStatus(status);
			header.setParameters(parameters);
			header.setProperties(properties);
			header.generate(sb);
			return;
		}
	}

	private void generateRows(StringBuffer sb)
		throws JspException
	{
		boolean isSecond = false;
		Iterator it = getRows();
		String ps = getProperty(parameters, "pageSize");
		int pagesize;
		if (ps == null)
			pagesize = 10;
		else
			pagesize = Integer.parseInt(ps);
		if (it == null)
			return;
		Column col = null;
		if (fixRows == null)
			fixRows = "false";
		for (int r = 0; r < pagesize; r++)
			if (it.hasNext())
			{
				Object obj = it.next();
				if (obj != null || fixRows.equalsIgnoreCase("true"))
				{
					sb.append("   <tr ");
					if (isSecond)
						isSecond = false;
					else
						isSecond = true;
					generateAttr(sb, "onDblClick", getParameters(rowDblClick, obj));
					if (rowEventHandle != null && rowEventHandle.equalsIgnoreCase("false"))
					{
						if (isSecond)
						{
							sb.append(" class=\"trBg\" ");
							generateAttr(sb, "onMouseOver", "secondRowMouseOver(this)");
							generateAttr(sb, "onMouseOut", "secondRowMouseOut(this)");
						} else
						{
							generateAttr(sb, "onMouseOver", "rowMouseOver(this)");
							generateAttr(sb, "onMouseOut", "rowMouseOut(this)");
						}
					} else
					{
						String bgcStart = "this.className = '";
						String bgcEnd = "';";
						if (isSecond)
						{
							sb.append(" class=\"trBg\" ");
							if (secondRowSelectedStyleClass == null)
							{
								if (rowSelectedStyleClass != null)
									generateAttr(sb, "onMouseOver", (new StringBuilder(String.valueOf(bgcStart))).append(rowSelectedStyleClass).append(bgcEnd).toString());
							} else
							if (secondRowSelectedStyleClass != null)
								generateAttr(sb, "onMouseOver", (new StringBuilder(String.valueOf(bgcStart))).append(secondRowSelectedStyleClass).append(bgcEnd).toString());
							if (secondRowStyleClass != null)
								generateAttr(sb, "onMouseOut", (new StringBuilder(String.valueOf(bgcStart))).append(secondRowStyleClass).append(bgcEnd).toString());
						} else
						{
							if (rowSelectedStyleClass != null)
								generateAttr(sb, "onMouseOver", (new StringBuilder(String.valueOf(bgcStart))).append(rowSelectedStyleClass).append(bgcEnd).toString());
							if (rowStyleClass != null)
								generateAttr(sb, "onMouseOut", (new StringBuilder(String.valueOf(bgcStart))).append(rowStyleClass).append(bgcEnd).toString());
						}
					}
					sb.append(">\r\n");
				}
				if (obj != null)
					if (columns == null || columns.size() == 0)
					{
						sb.append("      <td>&nbsp;</td>\r\n");
					} else
					{
						for (int i = 0; i < columns.size(); i++)
						{
							col = (Column)columns.get(i);
							col.setMatched(matchPK(obj));
							if (isSecond)
								col.setStyle(secondRowStyle);
							else
								col.setStyle(null);
							col.generate(sb, status, obj);
						}

					}
				if (it.hasNext() || fixRows.equalsIgnoreCase("true"))
					sb.append("   </tr>\r\n");
			}

	}

	private boolean matchPK(Object obj)
	{
		String pk = getProperty(parameters, "PK");
		if (pk == null)
			return false;
		String objpk = getProperty(obj, pk);
		if (objpk == null)
			return false;
		Object o = getProperty(parameters, "record");
		if (o == null)
			return false;
		String recpk = getProperty(o, pk);
		if (recpk == null)
			return false;
		return recpk.equalsIgnoreCase(objpk);
	}

	private void generateRooter(StringBuffer sb, StringBuffer sbs)
	{
		if (rooter == null)
		{
			return;
		} else
		{
			rooter.setParameterName(parameterName);
			rooter.setParameters(parameters);
			rooter.generate(sb, sbs);
			return;
		}
	}

	private void generateEndGrid(StringBuffer sb)
	{
		sb.append("</table>\r\n");
	}

	private void loadProperties()
	{
		Properties props = loadPropertiesFile("Grid");
		if (null==props || props.size() < 1)
			return;
		for (Iterator names = props.keySet().iterator(); names.hasNext();)
		{
			String key = (String)names.next();
			if (key.indexOf("default.") == 0)
			{
				String newKey = key.replaceFirst("default.", "");
				properties.put(newKey, props.getProperty(key));
			}
		}

		for (Iterator names = props.keySet().iterator(); names.hasNext();)
		{
			String key = (String)names.next();
			if (key.indexOf((new StringBuilder(String.valueOf(schemaName))).append(".").toString()) == 0)
			{
				String newKey = key.replaceFirst((new StringBuilder(String.valueOf(schemaName))).append(".").toString(), "");
				properties.remove(newKey);
				properties.put(newKey, props.getProperty(key));
			}
		}

	}

	private Properties loadPropertiesFile(String propertyName)
	{
		Properties props;
		InputStream is;
		props = new Properties();
		String name = (new StringBuilder("../")).append(propertyName).append(".properties").toString();
		is = null;
		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
		if (classLoader == null)
			classLoader = getClass().getClassLoader();
		is = classLoader.getResourceAsStream(name);
		if (is == null)
			return null;
		try
		{
			props.load(is);
		}
		catch (IOException ioexception)
		{
				try {
					is.close();
				} catch (IOException e) {
					return null;
				}
		}
		return props;
	}

	public Grid()
	{
		schemaName = null;
		scroll = null;
		status = null;
		headerShow = null;
		cellPadding = null;
		cellSpacing = null;
		border = null;
		frame = null;
		rules = null;
		rowStyle = null;
		rowStyleClass = null;
		rowSelectedStyleClass = null;
		secondRowStyle = null;
		secondRowStyleClass = null;
		secondRowSelectedStyleClass = null;
		rowEventHandle = null;
		rows = null;
		columns = null;
		header = null;
		rooter = null;
		sbs = new StringBuffer();
		parameters = null;
		parameterName = null;
		rowDblClick = null;
		properties = new HashMap();
	}

	public String getRowDblClick()
	{
		return rowDblClick;
	}

	public void setRowDblClick(String rowDblClick)
	{
		this.rowDblClick = rowDblClick;
	}
}
