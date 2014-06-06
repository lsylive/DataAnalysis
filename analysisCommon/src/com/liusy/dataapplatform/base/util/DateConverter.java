


//   DateConverter.java

package com.liusy.dataapplatform.base.util;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.apache.commons.beanutils.ConversionException;
import org.apache.commons.beanutils.Converter;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

// Referenced classes of package com.liusy.dataapplatform.base.util:
//			DateUtil

public class DateConverter
	implements Converter
{

	private static final Log log = LogFactory.getLog(DateConverter.class);
	public static final String TS_FORMAT = (new StringBuilder(String.valueOf(DateUtil.getDatePattern()))).append(" HH:mm:ss.S").toString();

	public DateConverter()
	{
	}

	public Object convert(Class type, Object value)
	{
		if (value == null)
			return null;
		if (type == Timestamp.class)
			return convertToDate(type, value, TS_FORMAT);
		if (type == Date.class)
			return convertToDate(type, value, DateUtil.getDatePattern());
		if (type == String.class)
			return convertToString(type, value);
		else
			throw new ConversionException((new StringBuilder("Could not convert ")).append(value.getClass().getName()).append(" to ").append(type.getName()).toString());
	}

	protected Object convertToDate(Class type, Object value, String pattern)
	{
		DateFormat df;
		try {
			
		df = new SimpleDateFormat(pattern);
		if (StringUtils.isEmpty(value.toString()))
			return null;
		Date date = df.parse((String)value);
		if (type.equals(Timestamp.class))
			return new Timestamp(date.getTime());
		return date;
		} catch (Exception e) {
			log.debug((new StringBuilder("convertToDate: value=")).append(value).toString());
			throw new ConversionException("Error converting String to Date");
		}
	}

	protected Object convertToString(Class type, Object value)
	{
try {
		DateFormat df;
		df = new SimpleDateFormat(DateUtil.getDatePattern());
		if (value instanceof Timestamp)
			df = new SimpleDateFormat(TS_FORMAT);
		return df.format(value);
		
	} catch (Exception e) {
		e.printStackTrace();
		}
		return value.toString();
	}

}
