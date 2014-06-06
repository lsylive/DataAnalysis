


//    AddMinute.java

package net.sourceforge.jeval.function.date;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import net.sourceforge.jeval.Evaluator;
import net.sourceforge.jeval.function.*;

public class AddMinute
	implements Function
{

	public AddMinute()
	{
	}

	public String getName()
	{
		return "addMinute";
	}

	public String getDescription()
	{
		return " ";
	}

	public FunctionResult execute(Evaluator evaluator, String arguments)
		throws FunctionException
	{
		String exceptionMessage = "One string and one integer argument are required.";
		String dateExceptionMessage = "Argument is not a vaild Date.";
		ArrayList values = FunctionHelper.getOneStringAndOneInteger(arguments, ',');
		if (values.size() != 2)
			throw new FunctionException(exceptionMessage);
		String res;
		try
		{
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			java.util.Date date1 = dateFormat.parse((String)values.get(0));
			Calendar calendar1 = Calendar.getInstance();
			calendar1.setTime(date1);
			int diff = ((Integer)values.get(1)).intValue();
			calendar1.add(12, diff);
			res = dateFormat.format(calendar1.getTime());
		}
		catch (ParseException e)
		{
			throw new FunctionException(dateExceptionMessage);
		}
		return new FunctionResult(res, 1);
	}
}
