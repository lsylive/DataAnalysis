


//    MinuteDifference.java

package net.sourceforge.jeval.function.date;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import net.sourceforge.jeval.Evaluator;
import net.sourceforge.jeval.function.*;

public class MinuteDifference
	implements Function
{

	public MinuteDifference()
	{
	}

	public String getName()
	{
		return "minuteDifference";
	}

	public String getDescription()
	{
		return " ";
	}

	public FunctionResult execute(Evaluator evaluator, String arguments)
		throws FunctionException
	{
		String exceptionMessage = "Two string arguments are required.";
		String dateExceptionMessage = "Argument is not a vaild Date.";
		if (arguments == null)
			throw new FunctionException(exceptionMessage);
		ArrayList strings = FunctionHelper.getStrings(arguments, ',');
		if (strings.size() != 2)
			throw new FunctionException(exceptionMessage);
		String res;
		try
		{
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			java.util.Date date1 = dateFormat.parse((String)strings.get(0));
			java.util.Date date2 = dateFormat.parse((String)strings.get(1));
			Calendar calendar1 = Calendar.getInstance();
			Calendar calendar2 = Calendar.getInstance();
			calendar1.setTime(date1);
			calendar2.setTime(date2);
			long milliseconds1 = calendar1.getTimeInMillis();
			long milliseconds2 = calendar2.getTimeInMillis();
			long diff = milliseconds2 - milliseconds1;
			long resi = diff / 60000L;
			res = String.valueOf(resi);
		}
		catch (ParseException e)
		{
			throw new FunctionException(dateExceptionMessage);
		}
		return new FunctionResult(res, 0);
	}
}
