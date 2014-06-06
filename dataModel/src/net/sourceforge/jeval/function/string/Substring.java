


//    Substring.java

package net.sourceforge.jeval.function.string;

import java.util.ArrayList;
import net.sourceforge.jeval.Evaluator;
import net.sourceforge.jeval.function.*;

public class Substring
	implements Function
{

	public Substring()
	{
	}

	public String getName()
	{
		return "substring";
	}

	public String getDescription()
	{
		return "String substring(String sourceString,int beginIndex, int endIndex).\t\n?????????????????????????????";
	}

	public FunctionResult execute(Evaluator evaluator, String arguments)
		throws FunctionException
	{
		String result = null;
		String exceptionMessage = "One string argument and two integer arguments are required.";
		ArrayList values = FunctionHelper.getOneStringAndTwoIntegers(arguments, ',');
		if (values.size() != 3)
			throw new FunctionException(exceptionMessage);
		try
		{
			String argumentOne = FunctionHelper.trimAndRemoveQuoteChars((String)values.get(0), evaluator.getQuoteCharacter());
			int beginningIndex = ((Integer)values.get(1)).intValue();
			int endingIndex = ((Integer)values.get(2)).intValue();
			result = argumentOne.substring(beginningIndex, endingIndex);
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
