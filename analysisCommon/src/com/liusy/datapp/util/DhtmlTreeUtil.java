


//   DhtmlTreeUtil.java

package com.liusy.datapp.util;

import java.util.List;
import java.util.Map;

// Referenced classes of package com.liusy.datapp.util:
//			DhtmlTreeParam

public class DhtmlTreeUtil
{

	public DhtmlTreeUtil()
	{
	}

	public static String genXmlTreeByList(List list, DhtmlTreeParam param, String parentid, boolean haschild)
		throws Exception
	{
		StringBuffer sb = new StringBuffer("");
		String childstr = haschild ? "1" : "0";
		sb.append("<?xml version='1.0' encoding='UTF-8' ?>");
		sb.append((new StringBuilder("<tree id=\"")).append(parentid).append("\">").toString());
		try
		{
			if (list != null)
			{
				for (int i = 0; i < list.size(); i++)
				{
					Map map = (Map)list.get(i);
					sb.append((new StringBuilder("<item id=\"")).append(param.getTagName()).toString()).append(String.valueOf(map.get(param.getIdField()))).append((new StringBuilder("\" text=\"")).append((String)map.get(param.getNameField())).append("\"").toString());
					if (param.getImgPath() != null && !"".equals(param.getImgPath().trim()))
						sb.append((new StringBuilder(" im0=\"")).append(param.getImgPath()).append("\" im1=\"").append(param.getImgPath()).append("\" im2=\"").append(param.getImgPath()).append("\"").toString());
					sb.append((new StringBuilder(" child=\"")).append(childstr).append("\">").toString());
					if (param.getUserDataFields() != null && param.getUserDataFields().length > 0)
					{
						for (int h = 0; h < param.getUserDataFields().length; h++)
							sb.append((new StringBuilder("<userdata name=\"")).append(param.getUserDataNames()[h]).append("\">").append((String)map.get(param.getUserDataFields()[h])).append("</userdata>").toString());

					}
					sb.append("</item>");
				}

			}
			sb.append("</tree>");
		}
		catch (Exception e)
		{
			e.printStackTrace();
			throw e;
		}
		return sb.toString();
	}

	public static String genBlankXmlTree(String parentid)
	{
		StringBuffer sb = new StringBuffer("");
		sb.append("<?xml version='1.0' encoding='UTF-8' ?>");
		sb.append((new StringBuilder("<tree id=\"")).append(parentid).append("\" child=\"0\">").toString());
		sb.append("</tree>");
		return sb.toString();
	}

	public static String genXmlTreeByMutilList(List mutilList[], DhtmlTreeParam mutilParam[], String parentId)
	{
		StringBuffer sb = new StringBuffer("");
		sb.append("<?xml version='1.0' encoding='UTF-8' ?>");
		sb.append((new StringBuilder("<tree id=\"")).append(parentId).append("\">").toString());
		for (int k = 0; k < mutilList.length; k++)
		{
			List list = mutilList[k];
			DhtmlTreeParam param = mutilParam[k];
			if (list != null)
			{
				for (int i = 0; i < list.size(); i++)
				{
					Map map = (Map)list.get(i);
					sb.append((new StringBuilder("<item id=\"")).append(param.getTagName()).append((String)map.get(param.getIdField())).append("\" text=\"").append((String)map.get(param.getNameField())).append("\" child=\"1\">").toString());
					if (param.getUserDataFields() != null && param.getUserDataFields().length > 0)
					{
						for (int h = 0; h < param.getUserDataFields().length; h++)
							sb.append((new StringBuilder("<userdata name=\"")).append(param.getUserDataFields()[h]).append("\">").append((String)map.get(param.getUserDataFields()[h])).append("</userdata>").toString());

					}
					sb.append("</item>");
				}

			}
		}

		sb.append("</tree>");
		return sb.toString();
	}
}
