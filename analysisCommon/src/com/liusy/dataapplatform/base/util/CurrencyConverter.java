


//   CurrencyConverter.java

package com.liusy.dataapplatform.base.util;

import java.text.DecimalFormat;
import java.text.ParseException;
import org.apache.commons.beanutils.ConversionException;
import org.apache.commons.beanutils.Converter;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class CurrencyConverter
	implements Converter
{

	protected final Log log = LogFactory.getLog(CurrencyConverter.class);
	protected final DecimalFormat formatter = new DecimalFormat("###,###.00");

	public CurrencyConverter()
	{
	}

	public final Object convert(Class type, Object value)
	{
		try {
		if (value == null)
			return null;
		if (!(value instanceof String))
		if (log.isDebugEnabled())
			log.debug((new StringBuilder("value (")).append(value).append(") instance of String").toString());
		if (StringUtils.isBlank(String.valueOf(value)))
			return null;
		Number num;
		if (log.isDebugEnabled())
			log.debug((new StringBuilder("converting '")).append(value).append("' to a decimal").toString());
		num = formatter.parse(String.valueOf(value));
		return new Double(num.doubleValue());
} catch (ParseException pe) {
	pe.printStackTrace();
}
		
		if (value instanceof Double)
		{
			if (log.isDebugEnabled())
			{
				log.debug((new StringBuilder("value (")).append(value).append(") instance of Double").toString());
				log.debug((new StringBuilder("returning double: ")).append(formatter.format(value)).toString());
			}
			return formatter.format(value);
		}
		throw new ConversionException((new StringBuilder("Could not convert ")).append(value).append(" to ").append(type.getName()).append("!").toString());
	}
}
