


//   DateUtil.java

package com.liusy.dataapplatform.base.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class DateUtil
{

	private static Log log = LogFactory.getLog(DateUtil.class);
	private static String defaultDatePattern = "yyyy-MM-dd";
	private static String timePattern = "HH:mm";

	public DateUtil()
	{
	}

	public static synchronized String getDatePattern()
	{
		return defaultDatePattern;
	}

	public static final String getDate(Date date)
	{
		SimpleDateFormat df = null;
		String returnValue = "";
		if (date != null)
		{
			df = new SimpleDateFormat(getDatePattern());
			returnValue = df.format(date);
		}
		return returnValue;
	}

	public static final Date convertStringToDate(String mask, String strdate)
		throws ParseException
	{
		SimpleDateFormat df = null;
		Date date = null;
		df = new SimpleDateFormat(mask);
		if (log.isDebugEnabled())
			log.debug((new StringBuilder("converting '")).append(strdate).append("' to date with mask '").append(mask).append("'").toString());
		try
		{
			date = df.parse(strdate);
		}
		catch (ParseException pe)
		{
			throw new ParseException(pe.getMessage(), pe.getErrorOffset());
		}
		return date;
	}

	public static String getTimeNow(Date time)
	{
		return getDateTime(timePattern, time);
	}

	public static Calendar getToday()
		throws ParseException
	{
		Date today = new Date();
		SimpleDateFormat df = new SimpleDateFormat(getDatePattern());
		String todayAsString = df.format(today);
		Calendar cal = new GregorianCalendar();
		cal.setTime(convertStringToDate(todayAsString));
		return cal;
	}

	public static final String getDateTime(String mask, Date date)
	{
		if (date == null)
		{
			return null;
		} else
		{
			SimpleDateFormat df = new SimpleDateFormat(mask);
			return df.format(date);
		}
	}

	public static final String convertDateToString(Date date)
	{
		return getDateTime(getDatePattern(), date);
	}

	public static Date convertStringToDate(String strdate)
		throws ParseException
	{
		Date aDate = null;
		try
		{
			if (log.isDebugEnabled())
				log.debug((new StringBuilder("converting date with pattern: ")).append(getDatePattern()).toString());
			aDate = convertStringToDate(getDatePattern(), strdate);
		}
		catch (ParseException pe)
		{
			log.error((new StringBuilder("Could not convert '")).append(strdate).append("' to a date, throwing exception").toString());
			pe.printStackTrace();
			throw new ParseException(pe.getMessage(), pe.getErrorOffset());
		}
		return aDate;
	}

}
