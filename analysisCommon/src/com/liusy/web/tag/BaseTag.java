


//   BaseTag.java

package com.liusy.web.tag;

import com.liusy.web.tag.base.Base;
import javax.servlet.jsp.tagext.BodyTagSupport;

// Referenced classes of package com.liusy.web.tag:
//			TagUtils

public class BaseTag extends BodyTagSupport
{

	private static final long serialVersionUID = 1L;
	protected String height;
	protected String width;
	protected String styleClass;
	protected String style;
	protected String styleid;
	protected String align;
	protected String valign;
	protected String name;
	protected String title;
	protected String property;
	protected String accesskey;
	protected String tabindex;
	protected String alt;
	protected String altKey;
	protected String bundle;
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

	public BaseTag()
	{
		height = null;
		width = null;
		styleClass = null;
		style = null;
		styleid = null;
		align = null;
		valign = null;
		name = null;
		title = null;
		property = null;
		accesskey = null;
		tabindex = null;
		alt = null;
		altKey = null;
		bundle = null;
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

	public void setHeight(String x)
	{
		height = x;
	}

	public void setStyleClass(String x)
	{
		styleClass = x;
	}

	public void setStyle(String x)
	{
		style = x;
	}

	public void setStyleId(String x)
	{
		styleid = x;
	}

	public void setAlign(String x)
	{
		align = x;
	}

	public void setValign(String x)
	{
		valign = x;
	}

	public void setName(String x)
	{
		name = x;
	}

	public void setTitle(String x)
	{
		title = x;
	}

	public void setProperty(String x)
	{
		property = x;
	}

	public void setWidth(String x)
	{
		width = x;
	}

	public void setAccesskey(String x)
	{
		accesskey = x;
	}

	public void setTabindex(String x)
	{
		tabindex = x;
	}

	public void setAlt(String x)
	{
		alt = x;
	}

	public void setAltKey(String x)
	{
		altKey = x;
	}

	public void setBundle(String x)
	{
		bundle = x;
	}

	public void setDisabled(String x)
	{
		disabled = x;
	}

	public void setReadonly(String x)
	{
		readonly = x;
	}

	public void setOnMouseOver(String x)
	{
		onmouseover = x;
	}

	public void setOnMouseOut(String x)
	{
		onmouseout = x;
	}

	public void setOnClick(String x)
	{
		onclick = x;
	}

	public void setOnDblClick(String x)
	{
		ondblclick = x;
	}

	public void setOnMouseMove(String x)
	{
		onmousemove = x;
	}

	public void setOnMouseDown(String x)
	{
		onmousedown = x;
	}

	public void setOnMouseUp(String x)
	{
		onmouseup = x;
	}

	public void setOnKeyDown(String x)
	{
		onkeydown = x;
	}

	public void setOnKeyUp(String x)
	{
		onkeyup = x;
	}

	public void setOnKeyPress(String x)
	{
		onkeypress = x;
	}

	public void setOnSelect(String x)
	{
		onselect = x;
	}

	public void setOnChange(String x)
	{
		onchange = x;
	}

	public void setOnBlur(String x)
	{
		onblur = x;
	}

	public void setOnFocus(String x)
	{
		onfocus = x;
	}

	public String getName()
	{
		return name;
	}

	public void putProperties(Base base)
	{
		base.setAccessKey(accesskey);
		base.setAlign(align);
		base.setAlt(alt);
		base.setAltKey(altKey);
		base.setBundle(bundle);
		base.setDisabled(disabled);
		base.setHeight(height);
		base.setName(name);
		base.setProperty(property);
		base.setReadonly(readonly);
		base.setStyle(style);
		base.setStyleClass(styleClass);
		base.setStyleId(styleid);
		base.setTitle(title);
		base.setTabIndex(tabindex);
		base.setValign(valign);
		base.setWidth(width);
		base.setOnDblClick(ondblclick);
		base.setOnBlur(onblur);
		base.setOnChange(onchange);
		base.setOnClick(onclick);
		base.setOnFocus(onfocus);
		base.setOnKeyDown(onkeydown);
		base.setOnKeyPress(onkeypress);
		base.setOnKeyUp(onkeyup);
		base.setOnMouseDown(onmousedown);
		base.setOnMouseMove(onmousemove);
		base.setOnMouseOut(onmouseout);
		base.setOnMouseOver(onmouseover);
		base.setOnMouseUp(onmouseup);
		base.setOnSelect(onselect);
	}

	protected void saveException(Throwable exception)
	{
		TagUtils.saveException(pageContext, exception);
	}

	public void release()
	{
		super.release();
		name = null;
		title = null;
		property = null;
		width = null;
		height = null;
		style = null;
		styleClass = null;
		styleid = null;
		align = null;
		valign = null;
		accesskey = null;
		tabindex = null;
		alt = null;
		altKey = null;
		bundle = null;
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
}
