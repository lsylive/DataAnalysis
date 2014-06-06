


//   IDCard.java

package com.liusy.core.util;

import java.io.PrintStream;

public class IDCard
{

	static final int wi[] = {
		7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 
		7, 9, 10, 5, 8, 4, 2, 1
	};
	static final int vi[] = {
		1, 0, 88, 9, 8, 7, 6, 5, 4, 3, 
		2
	};

	public static void main(String args[])
	{
		System.out.println(verify(""));
	}

	private IDCard()
	{
	}

	public static boolean verify(String idcard)
	{
		if (idcard.length() == 15)
			idcard = upTo18(idcard);
		if (idcard.length() != 18)
		{
			return false;
		} else
		{
			String verify = idcard.substring(17, 18);
			return verify.equals(getVerify(idcard));
		}
	}

	public static String upTo18(String fifteencardid)
	{
		String eightcardid = fifteencardid.substring(0, 6);
		eightcardid = (new StringBuilder(String.valueOf(eightcardid))).append("19").toString();
		eightcardid = (new StringBuilder(String.valueOf(eightcardid))).append(fifteencardid.substring(6, 15)).toString();
		eightcardid = (new StringBuilder(String.valueOf(eightcardid))).append(getVerify(eightcardid)).toString();
		return eightcardid;
	}

	private static String getVerify(String eightcardid)
	{
		int remaining = 0;
		if (eightcardid.length() == 18)
			eightcardid = eightcardid.substring(0, 17);
		int ai[] = new int[18];
		if (eightcardid.length() == 17)
		{
			int sum = 0;
			for (int i = 0; i < 17; i++)
			{
				String k = eightcardid.substring(i, i + 1);
				ai[i] = Integer.parseInt(k);
				sum += wi[i] * ai[i];
			}

			remaining = sum % 11;
		}
		return remaining != 2 ? String.valueOf(vi[remaining]) : "X";
	}

}
