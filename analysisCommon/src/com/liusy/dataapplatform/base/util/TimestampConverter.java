


//   TimestampConverter.java

package com.liusy.dataapplatform.base.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import org.apache.commons.lang.StringUtils;

// Referenced classes of package com.liusy.dataapplatform.base.util:
//			DateConverter, DateUtil

public class TimestampConverter extends DateConverter
{

	public static final String TS_FORMAT = (new StringBuilder(String.valueOf(DateUtil.getDatePattern()))).append(" HH:mm:ss.S").toString();

	public TimestampConverter()
	{
	}

	protected Object convertToDate(Class type, Object value)
	{
		DateFormat df;
		df = new SimpleDateFormat(TS_FORMAT);
		if (StringUtils.isEmpty(value.toString()))
			return null;
		try {
			return df.parse((String)value);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	protected Object convertToString(Class type, Object value)
	{
		DateFormat df;
		df = new SimpleDateFormat(TS_FORMAT);
		return df.format(value);
	}

}
