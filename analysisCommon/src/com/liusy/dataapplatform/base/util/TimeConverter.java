


//   TimeConverter.java

package com.liusy.dataapplatform.base.util;

import java.sql.Time;
import org.apache.commons.beanutils.ConversionException;
import org.apache.commons.beanutils.Converter;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class TimeConverter
	implements Converter
{

	private static Log log = LogFactory.getLog(TimeConverter.class);

	public TimeConverter()
	{
	}

	public Object convert(Class type, Object value)
	{
		log.debug((new StringBuilder("convert type:")).append(type).append(" value:").append(value).toString());
		if (value == null)
			return null;
		if (type == Time.class)
			return Time.valueOf((String)value);
		if (type == String.class)
			return value.toString();
		else
			throw new ConversionException((new StringBuilder("Could not convert ")).append(value.getClass().getName()).append(" to ").append(type.getName()).toString());
	}

}
