


//    StartsWith.java

package net.sourceforge.jeval.function.string;

import java.util.ArrayList;
import net.sourceforge.jeval.Evaluator;
import net.sourceforge.jeval.function.*;

public class StartsWith
	implements Function
{

	public StartsWith()
	{
	}

	public String getName()
	{
		return "startsWith";
	}

	public String getDescription()
	{
		return "boolean startsWith(String sourceString,String prefix, int toffset).\t\n????sourceString??????????????????????????????????";
	}

	public FunctionResult execute(Evaluator evaluator, String arguments)
		throws FunctionException
	{
		String result = null;
		String exceptionMessage = "Two string arguments and one integer argument are required.";
		ArrayList values = FunctionHelper.getTwoStringsAndOneInteger(arguments, ',');
		if (values.size() != 3)
			throw new FunctionException(exceptionMessage);
		try
		{
			String argumentOne = FunctionHelper.trimAndRemoveQuoteChars((String)values.get(0), evaluator.getQuoteCharacter());
			String argumentTwo = FunctionHelper.trimAndRemoveQuoteChars((String)values.get(1), evaluator.getQuoteCharacter());
			int index = ((Integer)values.get(2)).intValue();
			if (argumentOne.startsWith(argumentTwo, index))
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
