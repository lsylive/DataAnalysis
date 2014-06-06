


//    NewDay.java

package net.sourceforge.jeval.function.date;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import net.sourceforge.jeval.Evaluator;
import net.sourceforge.jeval.function.*;

public class NewDay
	implements Function
{

	public NewDay()
	{
	}

	public String getName()
	{
		return "NewDay";
	}

	public String getDescription()
	{
		return " ";
	}

	public FunctionResult execute(Evaluator evaluator, String arguments)
		throws FunctionException
	{
		String exceptionMessage = "One string or many string argument are required.";
		String dateExceptionMessage = "Argument is not a vaild Date.";
		ArrayList values = FunctionHelper.getStrings(arguments, ',');
		ArrayList tempValues = new ArrayList();
		String pram;
		for (Iterator iterator = values.iterator(); iterator.hasNext(); tempValues.add(pram))
		{
			pram = (String)iterator.next();
			pram = pram.substring(1, pram.length() - 1);
		}

		if (values.size() < 2)
			throw new FunctionException(exceptionMessage);
		String res;
		try
		{
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String year = String.valueOf(tempValues.get(0));
			for (int i = tempValues.size(); i < 6; i++)
				if (i < 3)
					tempValues.add("01");
				else
					tempValues.add("00");

			String dateStr = year.concat("-").concat(((String)tempValues.get(1)).toString()).concat("-").concat(((String)tempValues.get(2)).toString()).concat(" ").concat(((String)tempValues.get(3)).toString()).concat(":").concat(((String)tempValues.get(4)).toString()).concat(":").concat(((String)tempValues.get(5)).toString());
			java.util.Date date1 = dateFormat.parse(dateStr);
			res = dateStr;
		}
		catch (ParseException e)
		{
			throw new FunctionException(dateExceptionMessage);
		}
		return new FunctionResult(res, 1);
	}
}
