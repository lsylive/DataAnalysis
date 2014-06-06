


//   Menu2.java

package com.liusy.web.tag.menu;

import java.util.*;

// Referenced classes of package com.liusy.web.tag.menu:
//			MenuItem

public class Menu2
{

	private List items;
	private String title;
	private String id;
	private Set rights;
	private String code;
	private String img;

	public Menu2()
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
		StringBuffer sb1 = new StringBuffer("");
		int idx = 0;
		for (int i = 0; i < getItems().size(); i++)
		{
			MenuItem item = (MenuItem)items.get(i);
			String img = item.getImg() != null ? item.getImg() : "";
			if (rights.contains(item.getCode()))
			{
				sb1.append((new StringBuilder("<item id=\"")).append(item.getCode()).append("\" text=\"").append(item.getText()).append("\" img=\"").append(img).append("\" >").toString());
				if (item.getOnclick() != null && item.getOnclick().length() > 0)
					sb1.append((new StringBuilder("<userdata name=\"onclick\"><![CDATA[")).append(item.getOnclick().replace("'", "\\'")).append("]]></userdata>").toString());
				sb1.append("</item>");
				idx++;
			}
		}

		if (sb1.length() > 0)
		{
			String imgstr = this.img != null ? (new StringBuilder(" img=\"")).append(this.img).append("\"").toString() : "";
			sb.append((new StringBuilder("menustr+='<item id=\"")).append(code).append("\" text=\"").append(title).append("\"").append(imgstr).append(" >").toString());
			sb.append(sb1);
			sb.append("</item>';");
		}
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
