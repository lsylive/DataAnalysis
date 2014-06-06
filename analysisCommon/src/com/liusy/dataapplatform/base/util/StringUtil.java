


//   StringUtil.java

package com.liusy.dataapplatform.base.util;

import java.io.IOException;
import java.io.PrintStream;
import java.security.MessageDigest;
import java.util.*;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

public class StringUtil
{

	private static final Log log = LogFactory.getLog(StringUtil.class);

	public StringUtil()
	{
	}

	public static String encodePassword(String password, String algorithm)
	{
		byte unencodedPassword[] = password.getBytes();
		MessageDigest md = null;
		try
		{
			md = MessageDigest.getInstance(algorithm);
		}
		catch (Exception e)
		{
			log.error((new StringBuilder("Exception: ")).append(e).toString());
			return password;
		}
		md.reset();
		md.update(unencodedPassword);
		byte encodedPassword[] = md.digest();
		StringBuffer buf = new StringBuffer();
		for (int i = 0; i < encodedPassword.length; i++)
		{
			if ((encodedPassword[i] & 0xff) < 16)
				buf.append("0");
			buf.append(Long.toString(encodedPassword[i] & 0xff, 16));
		}

		return buf.toString();
	}

	public static String encodeString(String str)
	{
		BASE64Encoder encoder = new BASE64Encoder();
		return encoder.encodeBuffer(str.getBytes()).trim();
	}

	public static String decodeString(String str)
	{
		try {
		BASE64Decoder dec = new BASE64Decoder();
		return new String(dec.decodeBuffer(str));
		
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage(), e.getCause());
		}
	}

	public static String upgradeIDCard(String id15)
	{
		if (id15 == null)
			throw new IllegalArgumentException("���֤����Ƿ�!");
		int length = id15.length();
		if (length != 15 && length != 18)
			throw new IllegalArgumentException((new StringBuilder("���֤����[")).append(id15).append("]�Ƿ�!").toString());
		String id18 = "";
		if (length == 18)
		{
			id18 = id15.toUpperCase();
		} else
		{
			int w[] = {
				7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 
				7, 9, 10, 5, 8, 4, 2, 1
			};
			char A[] = {
				'1', '0', 'X', '9', '8', '7', '6', '5', '4', '3', 
				'2'
			};
			String id17 = (new StringBuilder(String.valueOf(id15.substring(0, 6)))).append("19").append(id15.substring(6, 15)).toString();
			int id17Array[] = new int[17];
			for (int i = 0; i < 17; i++)
				id17Array[i] = Integer.parseInt(id17.substring(i, i + 1));

			int s = 0;
			for (int i = 0; i < 17; i++)
				s += id17Array[i] * w[i];

			s %= 11;
			id18 = (new StringBuilder(String.valueOf(id17))).append(A[s]).toString();
		}
		return id18;
	}

	public static List getSingleColumnListByMap(List list, String identifyCol)
	{
		List retList = new ArrayList();
		Map map;
		for (Iterator iterator = list.iterator(); iterator.hasNext(); retList.add((String)map.get(identifyCol)))
			map = (Map)iterator.next();

		return retList;
	}

	public static boolean isNumberic(String str)
	{
		if (str == null || "".equals(str))
			return false;
		return str.matches("\\d++\\.\\d++|\\d++");
	}

	public static void main(String args[])
	{
		System.out.println(isNumberic("0343534443"));
	}

}
