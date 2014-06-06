


//    LastIndexOf.java

package net.sourceforge.jeval.function.string;

import java.util.ArrayList;
import net.sourceforge.jeval.Evaluator;
import net.sourceforge.jeval.function.*;

public class LastIndexOf
	implements Function
{

	public LastIndexOf()
	{
	}

	public String getName()
	{
		return "lastIndexOf";
	}

	public String getDescription()
	{
		return "int lastIndexOf(String sourceString,String substring,int index).\t\n?????????????????????????sourceString??????????��???????substring????????";
	}

	public FunctionResult execute(Evaluator evaluator, String arguments)
		throws FunctionException
	{
		Integer result = null;
		String exceptionMessage = "Two string arguments and one integer argument are required.";
		ArrayList values = FunctionHelper.getTwoStringsAndOneInteger(arguments, ',');
		if (values.size() != 3)
			throw new FunctionException(exceptionMessage);
		try
		{
			String argumentOne = FunctionHelper.trimAndRemoveQuoteChars((String)values.get(0), evaluator.getQuoteCharacter());
			String argumentTwo = FunctionHelper.trimAndRemoveQuoteChars((String)values.get(1), evaluator.getQuoteCharacter());
			int index = ((Integer)values.get(2)).intValue();
			result = new Integer(argumentOne.lastIndexOf(argumentTwo, index));
		}
		catch (FunctionException fe)
		{
			throw new FunctionException(fe.getMessage(), fe);
		}
		catch (Exception e)
		{
			throw new FunctionException(exceptionMessage, e);
		}
		return new FunctionResult(result.toString(), 0);
	}
}
