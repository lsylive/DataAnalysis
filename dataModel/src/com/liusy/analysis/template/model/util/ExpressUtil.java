
package com.liusy.analysis.template.model.util;

import com.liusy.analysis.template.model.vo.DataField;

public class ExpressUtil
{

	public ExpressUtil()
	{
	}

	public static StringBuffer parseExpress(StringBuffer order, DataField df)
	{
		return parseExpress(order, df, "");
	}

	public static StringBuffer parseExpress(StringBuffer order, DataField df, String type)
	{
		String operorArr[] = {
			"(", ")", "+", "-", "*", "/", "%", "=", "!", ">", 
			"<", ",", "&", "|"
		};
		String dfName = df.getName().trim();
		String str = "";
		int index = -1;
		for (int i = 0; i < dfName.length();)
		{
			for (int j = 0; j < operorArr.length; j++)
				if (dfName.indexOf(operorArr[j], i) >= 0 && (index < 0 || index > dfName.indexOf(operorArr[j], i)))
					index = dfName.indexOf(operorArr[j], i);

			if (index > i)
				str = dfName.substring(i, index).trim();
			if (index > i && str.length() > 0 && !(new StringBuilder(String.valueOf(str.charAt(0)))).toString().equals("{") && !(new StringBuilder(String.valueOf(str.charAt(0)))).toString().equals("'") && !(new StringBuilder(String.valueOf(dfName.charAt(index)))).toString().equals("(") && !"0123456789".contains((new StringBuilder(String.valueOf(str.charAt(0)))).toString()) && !(new StringBuilder(String.valueOf(str.charAt(0)))).toString().equals("#"))
			{
				if (!type.equals("MultiTable"))
					order.append("t.").append(str);
				else
					order.append(str);
				i = index;
				index = -1;
			} else
			if (index > i)
			{
				int start = dfName.indexOf("'", i);
				int end = -1;
				if (start >= i)
					end = dfName.indexOf("'", start + 1);
				else
					end = dfName.indexOf("'", i);
				if (end > index && index > start)
				{
					index = end + 1;
					order.append(dfName.substring(i, index).trim());
				} else
				if (index > end && end > start)
					order.append(str);
				else
				if (end <= start)
				{
					start = str.indexOf("#", 0);
					if (start == 0)
						order.append(str.substring(1));
					else
						order.append(str);
				} else
				if (start > index || end < i)
				{
					start = str.indexOf("{", 0);
					end = str.indexOf("}", 0);
					if (start == 0 && end < 0)
					{
						order.append("'").append(str).append("'");
					} else
					{
						start = str.indexOf("#", 0);
						if (start == 0)
							order.append(str.substring(1));
						else
							order.append(str);
					}
				}
				i = index;
				index = -1;
			} else
			if (index == i)
			{
				order.append((new StringBuilder(String.valueOf(dfName.charAt(i)))).toString());
				i++;
				index = -1;
			} else
			{
				str = dfName.substring(i).trim();
				String strNoOperFirst = (new StringBuilder(String.valueOf(str.charAt(0)))).toString();
				if (!strNoOperFirst.equals("{") && !strNoOperFirst.equals("'") && !"0123456789".contains(strNoOperFirst))
				{
					if (!type.equals("MultiTable"))
						order.append("t.").append(str);
					else
						order.append(str);
				} else
				if (strNoOperFirst.equals("{"))
				{
					int start = dfName.indexOf("{", i);
					int end = dfName.indexOf("}", i);
					if (start < end)
						order.append(str);
					else
					if (start >= i && end < start)
						order.append("'").append(str).append("'");
				} else
				{
					order.append(str);
				}
				i = dfName.length();
				index = -1;
			}
		}

		return order;
	}
}
