


//   MenuTag2.java

package com.liusy.web.tag;

import com.liusy.core.util.Const;
import com.liusy.web.tag.menu.Menu2;
import java.io.StringReader;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.BodyContent;
import org.apache.commons.digester.Digester;

// Referenced classes of package com.liusy.web.tag:
//			BaseTag, TagUtils

public class MenuTag2 extends BaseTag
{

	private static final long serialVersionUID = 1L;
	private String title;
	private String id;
	private String code;
	private String img;

	public MenuTag2()
	{
	}

	public String getCode()
	{
		return code;
	}

	public void setCode(String code)
	{
		this.code = code;
	}

	public String getId()
	{
		return id;
	}

	public void setId(String id)
	{
		this.id = id;
	}

	public String getTitle()
	{
		return title;
	}

	public void setTitle(String title)
	{
		this.title = title;
	}

	public int doStartTag()
		throws JspException
	{
		return 2;
	}

	public String getImg()
	{
		return img;
	}

	public void setImg(String img)
	{
		this.img = img;
	}

	public int doEndTag()
		throws JspException
	{
		StringBuffer sb = new StringBuffer("");
		try
		{
			BodyContent bodyContent = getBodyContent();
			String menuXML = (new StringBuilder("<menu>")).append(bodyContent.getString()).append("</menu>").toString();
			Menu2 menu = parse(menuXML);
			menu.setId(id);
			menu.setTitle(title);
			menu.setCode(code);
			menu.setImg(img);
			Map rights = (Map)TagUtils.lookup(pageContext, Const.SESSION, "privileges", "session");
			if (rights == null)
				rights = new HashMap();
			menu.setRights(rights.keySet());
			menu.generateMenu(sb);
			TagUtils.write(pageContext, sb.toString());
		}
		catch (Exception ex)
		{
			ex.printStackTrace();
		}
		return 6;
	}

	private Menu2 parse(String str)
	{
		Menu2 menu = new Menu2();
		Digester digester = new Digester();
		digester.setValidating(false);
		digester.addObjectCreate("menu", "com.liusy.web.tag.menu.Menu2");
		digester.addSetProperties("menu");
		digester.addObjectCreate("menu/item", "com.liusy.web.tag.menu.MenuItem");
		digester.addSetProperties("menu/item");
		digester.addSetNext("menu/item", "addItem", "com.liusy.web.tag.menu.MenuItem");
		StringReader sr = new StringReader(str);
		try
		{
			menu = (Menu2)digester.parse(sr);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return menu;
	}
}
