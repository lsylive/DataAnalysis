


//    Length.java

package net.sourceforge.jeval.function.string;

import net.sourceforge.jeval.Evaluator;
import net.sourceforge.jeval.function.*;

public class Length
	implements Function
{

	public Length()
	{
	}

	public String getName()
	{
		return "length";
	}

	public String getDescription()
	{
		return "int length(String sourceString).\t\n????????????";
	}

	public FunctionResult execute(Evaluator evaluator, String arguments)
		throws FunctionException
	{
		Integer result = null;
		String exceptionMessage = "One string argument is required.";
		try
		{
			String stringValue = arguments;
			String argumentOne = FunctionHelper.trimAndRemoveQuoteChars(stringValue, evaluator.getQuoteCharacter());
			result = new Integer(argumentOne.length());
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
