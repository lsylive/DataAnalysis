


//   GridTag.java

package com.liusy.web.tag;

import com.liusy.web.tag.grid.Column;
import com.liusy.web.tag.grid.Grid;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.jsp.*;
import javax.servlet.jsp.tagext.BodyContent;
import org.apache.commons.digester.Digester;

// Referenced classes of package com.liusy.web.tag:
//			BaseTag, TagUtils

public class GridTag extends BaseTag
{

	private static final long serialVersionUID = 1L;
	private String schema;
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
	private String parameters;
	private String status;
	private String location;
	private String fixRows;
	private String rowDblClick;
	private Grid grid;

	public GridTag()
	{
		grid = null;
	}

	public int doStartTag()
		throws JspException
	{
		if (name == null || name.equals(""))
			throw new JspTagException("invalid null or empty 'name'.");
		if (property == null || property.equals(""))
			throw new JspTagException("invalid null or empty 'property'.");
		else
			return 2;
	}

	public int doEndTag()
		throws JspException
	{
		StringBuffer sb = new StringBuffer();
		StringBuffer sbs = new StringBuffer();
		sb.append("");
		sbs.append("");
		try
		{
			javax.servlet.ServletRequest req = pageContext.getRequest();
			Object rows = TagUtils.lookup(pageContext, name, property, "request");
			BodyContent bodyContent = getBodyContent();
			String gridXML = (new StringBuilder("<grid>")).append(bodyContent.getString()).append("</grid>").toString();
			grid = parse(gridXML);
			putProperties(grid);
			grid.setBorder(border);
			grid.setCellPadding(cellPadding);
			grid.setCellSpacing(cellSpacing);
			grid.setFrame(frame);
			grid.setRowEventHandle(rowEventHandle);
			grid.setRowSelectedStyleClass(rowSelectedStyleClass);
			grid.setRowStyle(rowStyle);
			grid.setRowStyleClass(rowStyleClass);
			grid.setRules(rules);
			grid.setSchema(schema);
			grid.setScroll(scroll);
			grid.setSecondRowSelectedStyleClass(secondRowSelectedStyleClass);
			grid.setSecondRowStyle(secondRowStyle);
			grid.setSecondRowStyleClass(secondRowStyleClass);
			grid.setParameterName(parameters);
			grid.setStatus(status);
			grid.setHeaderShow(headerShow);
			grid.setRooterShow(rooterShow);
			grid.setLocation(location);
			grid.setRowDblClick(rowDblClick);
			grid.setFixRows(fixRows);
			if (rows != null)
				grid.setRows(TagUtils.getIterator(rows));
			else
				grid.setRows((new ArrayList()).iterator());
			Object para = null;
			if (name != null && parameters != null)
				para = TagUtils.lookup(pageContext, name, parameters, "request");
			grid.setParameters(para);
			JspWriter out = pageContext.getOut();
			grid.generateGrid(sb);
			out.print(sb.toString());
		}
		catch (Exception ex)
		{
			ex.printStackTrace();
		}
		return 6;
	}

	private Grid parse(String str)
	{
		Grid grid = new Grid();
		Digester digester = new Digester();
		digester.setValidating(false);
		digester.addObjectCreate("grid", "com.liusy.web.tag.grid.Grid");
		digester.addSetProperties("grid");
		digester.addObjectCreate("grid/header", "com.liusy.web.tag.grid.Header");
		digester.addSetProperties("grid/header");
		digester.addSetNext("grid/header", "addHeader", "com.liusy.web.tag.grid.Header");
		digester.addObjectCreate("grid/column", "com.liusy.web.tag.grid.Column");
		digester.addSetProperties("grid/column");
		digester.addSetNext("grid/column", "addColumn", "com.liusy.web.tag.grid.Column");
		digester.addObjectCreate("grid/rooter", "com.liusy.web.tag.grid.Rooter");
		digester.addSetProperties("grid/rooter");
		digester.addSetNext("grid/rooter", "addRooter", "com.liusy.web.tag.grid.Rooter");
		StringReader sr = new StringReader(str);
		try
		{
			grid = (Grid)digester.parse(sr);
			Object obj = TagUtils.lookup(pageContext, Column.DYNAMICCOLUMNS, "request");
			if (obj != null)
				grid.setColumns((List)obj);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return grid;
	}

	public void setLocation(String x)
	{
		location = x;
	}

	public void setHeaderShow(String x)
	{
		headerShow = x;
	}

	public void setRooterShow(String x)
	{
		rooterShow = x;
	}

	public void setParameters(String x)
	{
		parameters = x;
	}

	public void setSchema(String x)
	{
		schema = x;
	}

	public void setStatus(String x)
	{
		status = x;
	}

	public void setScroll(String x)
	{
		scroll = x;
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

	public void setFixRows(String x)
	{
		fixRows = x;
	}

	public void release()
	{
		super.release();
		location = null;
		schema = null;
		status = null;
		scroll = null;
		headerShow = null;
		rooterShow = null;
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
		parameters = null;
		rowDblClick = null;
		fixRows = null;
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
