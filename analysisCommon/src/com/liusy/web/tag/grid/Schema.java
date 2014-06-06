


//   Schema.java

package com.liusy.web.tag.grid;


public class Schema
{

	public String schema;
	public String cellPadding;
	public String cellSpacing;
	public String frame;
	public String rules;
	public String style;
	public String styleClass;
	public String rowsStyle;
	public String rowsStyleClass;
	public String rowsHiliteStyleClass;
	public String alternateRowsStyle;
	public String alternateRowsStyleClass;
	public String alternateRowsHiliteStyleClass;
	public int defaultOrderIndex;
	public String imgOrderAscending;
	public String imgOrderDescending;

	public Schema(String schemaname)
	{
		cellPadding = null;
		cellSpacing = null;
		frame = null;
		rules = null;
		style = null;
		styleClass = null;
		rowsStyle = null;
		rowsStyleClass = null;
		rowsHiliteStyleClass = null;
		alternateRowsStyle = null;
		alternateRowsStyleClass = null;
		alternateRowsHiliteStyleClass = null;
		defaultOrderIndex = 0;
		imgOrderAscending = null;
		imgOrderDescending = null;
		schema = schemaname;
	}
}
