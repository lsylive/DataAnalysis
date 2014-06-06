


//    Concat.java

package net.sourceforge.jeval.function.string;

import java.util.ArrayList;
import net.sourceforge.jeval.Evaluator;
import net.sourceforge.jeval.function.*;

public class Concat
	implements Function
{

	public Concat()
	{
	}

	public String getName()
	{
		return "concat";
	}

	public String getDescription()
	{
		return "String concat(String source,String follow).\t\n??follow????????source????";
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
			result = argumentOne.concat(argumentTwo);
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
