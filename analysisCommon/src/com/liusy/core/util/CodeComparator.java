


//   CodeComparator.java

package com.liusy.core.util;

import java.util.Comparator;
import java.util.Map;

public class CodeComparator
	implements Comparator
{

	private String codeKeyName;

	public CodeComparator(String codeKeyName)
	{
		this.codeKeyName = codeKeyName;
	}

	public int compare(Object o1, Object o2)
	{
		String code_1 = (String)((Map)o1).get(codeKeyName);
		String code_2 = (String)((Map)o2).get(codeKeyName);
		if (code_1.length() > code_2.length())
			code_1 = code_1.substring(0, code_2.length());
		else
		if (code_1.length() < code_2.length())
			code_2 = code_2.substring(0, code_1.length());
		double checkCode1 = Double.parseDouble(code_1);
		double checkCode2 = Double.parseDouble(code_2);
		if (checkCode1 > checkCode2)
			return -1;
		return checkCode1 != checkCode2 ? 1 : 0;
	}
}
