


//    Trim.java

package net.sourceforge.jeval.function.string;

import net.sourceforge.jeval.Evaluator;
import net.sourceforge.jeval.function.*;

public class Trim
	implements Function
{

	public Trim()
	{
	}

	public String getName()
	{
		return "trim";
	}

	public String getDescription()
	{
		return "String trim(String sourceString).\t\n????????????????????????¦Â??????";
	}

	public FunctionResult execute(Evaluator evaluator, String arguments)
		throws FunctionException
	{
		String result = null;
		String exceptionMessage = "One string argument is required.";
		try
		{
			String stringValue = arguments;
			String argumentOne = FunctionHelper.trimAndRemoveQuoteChars(stringValue, evaluator.getQuoteCharacter());
			result = argumentOne.trim();
		}
		catch (FunctionException fe)
		{
			throw new FunctionException(fe.getMessage(), fe);
		}
		catch (Exception e)
		{
			throw new FunctionException(exceptionMessage, e);
		}
		return new FunctionResult(result, 1);
	}
}
