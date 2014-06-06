


//    DayOfWeek.java

package net.sourceforge.jeval.function.date;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import net.sourceforge.jeval.Evaluator;
import net.sourceforge.jeval.function.*;

public class DayOfWeek
	implements Function
{

	public DayOfWeek()
	{
	}

	public String getName()
	{
		return "dayOfWeek";
	}

	public String getDescription()
	{
		return " ";
	}

	public FunctionResult execute(Evaluator evaluator, String arguments)
		throws FunctionException
	{
		String exceptionMessage = "One string argument are required.";
		String dateExceptionMessage = "Argument is not a vaild Date.";
		if (arguments == null || arguments.length() == 0)
			throw new FunctionException(exceptionMessage);
		int res;
		try
		{
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			java.util.Date date = dateFormat.parse(arguments.trim());
			Calendar c = Calendar.getInstance();
			c.setTime(date);
			res = c.get(7);
		}
		catch (ParseException e)
		{
			throw new FunctionException(dateExceptionMessage);
		}
		return new FunctionResult(String.valueOf(res), 0);
	}
}
