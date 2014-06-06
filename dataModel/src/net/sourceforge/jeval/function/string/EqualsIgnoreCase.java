


//    EqualsIgnoreCase.java

package net.sourceforge.jeval.function.string;

import java.util.ArrayList;
import net.sourceforge.jeval.Evaluator;
import net.sourceforge.jeval.function.*;

public class EqualsIgnoreCase
	implements Function
{

	public EqualsIgnoreCase()
	{
	}

	public String getName()
	{
		return "equalsIgnoreCase";
	}

	public String getDescription()
	{
		return "String equalsIgnoreCase(String source,String destination).\t\n????????§³§Õ?????Returns \"1.0\" (true) if the string ends with the suffix, otherwise it returns \"0.0\" (false)";
	}

	public FunctionResult execute(Evaluator evaluator, String arguments)
		throws FunctionException
	{
		String result = null;
		String exceptionMessage = "Two string arguments are required.";
		ArrayList strings = FunctionHelper.getStrings(arguments, ',');
		if (strings.size() != 2)
			throw new FunctionException(exceptionMessage);
		try
		{
			String argumentOne = FunctionHelper.trimAndRemoveQuoteChars((String)strings.get(0), evaluator.getQuoteCharacter());
			String argumentTwo = FunctionHelper.trimAndRemoveQuoteChars((String)strings.get(1), evaluator.getQuoteCharacter());
			if (argumentOne.equalsIgnoreCase(argumentTwo))
				result = "1.0";
			else
				result = "0.0";
		}
		catch (FunctionException fe)
		{
			throw new FunctionException(fe.getMessage(), fe);
		}
		catch (Exception e)
		{
			throw new FunctionException(exceptionMessage, e);
		}
		return new FunctionResult(result, 0);
	}
}
