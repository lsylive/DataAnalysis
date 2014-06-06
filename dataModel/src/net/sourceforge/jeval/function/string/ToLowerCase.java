


//    ToLowerCase.java

package net.sourceforge.jeval.function.string;

import net.sourceforge.jeval.Evaluator;
import net.sourceforge.jeval.function.*;

public class ToLowerCase
	implements Function
{

	public ToLowerCase()
	{
	}

	public String getName()
	{
		return "toLowerCase";
	}

	public String getDescription()
	{
		return "String toLowerCase(String sourceString).\t\n???? sourceString ?§Ö????????????§³§Õ??";
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
			result = argumentOne.toLowerCase();
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
