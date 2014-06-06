


//   Menu.java

package com.liusy.web.tag.menu;

import java.util.*;

// Referenced classes of package com.liusy.web.tag.menu:
//			MenuItem

public class Menu
{

	private List items;
	private String title;
	private String id;
	private Set rights;
	private String code;
	private String img;

	public Menu()
	{
	}

	public void addItem(MenuItem item)
	{
		if (items == null)
			items = new ArrayList();
		items.add(item);
	}

	public void generateMenu(StringBuffer sb)
	{
		sb.append((new StringBuilder("<div id=\"")).append(id).append("\" class=\"memuNavDivWrap\">\r\n<ul class=\"memuNav\">\r\n").toString());
		for (int i = 0; i < getItems().size(); i++)
		{
			MenuItem item = (MenuItem)items.get(i);
			if (rights.contains(item.getCode()))
			{
				if (item.getOnclick() != null)
					sb.append((new StringBuilder("<li><a href=\"javascript:")).append(item.getOnclick()).append("\"").toString());
				else
					sb.append("<li><a href=\"#\"");
				if (item.getTitle() != null)
					sb.append((new StringBuilder(" title='")).append(item.getTitle()).append("'").toString());
				if (item.getImg() != null)
					sb.append((new StringBuilder(" img='")).append(item.getImg()).append("'").toString());
				sb.append((new StringBuilder(" >")).append(item.getText()).append("</a></li>\r\n").toString());
			}
		}

		sb.append("</ul>\r\n</div>\r\n");
	}

	public String getId()
	{
		return id;
	}

	public void setId(String id)
	{
		this.id = id;
	}

	public List getItems()
	{
		if (items == null)
			items = new ArrayList();
		return items;
	}

	public void setItems(List items)
	{
		this.items = items;
	}

	public String getTitle()
	{
		return title;
	}

	public void setTitle(String title)
	{
		this.title = title;
	}

	public Set getRights()
	{
		if (rights == null)
			rights = new HashSet();
		return rights;
	}

	public void setRights(Set rights)
	{
		this.rights = rights;
	}

	public String getCode()
	{
		return code;
	}

	public void setCode(String code)
	{
		this.code = code;
	}

	public String getImg()
	{
		return img;
	}

	public void setImg(String img)
	{
		this.img = img;
	}
}
