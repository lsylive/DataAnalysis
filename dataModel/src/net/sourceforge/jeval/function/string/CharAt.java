


//    CharAt.java

package net.sourceforge.jeval.function.string;

import java.util.ArrayList;
import net.sourceforge.jeval.Evaluator;
import net.sourceforge.jeval.function.*;

public class CharAt
	implements Function
{

	public CharAt()
	{
	}

	public String getName()
	{
		return "charAt";
	}

	public String getDescription()
	{
		return " String CharAt(String SourceString,int index). \t\n?????????¦Ë?\365????";
	}

	public FunctionResult execute(Evaluator evaluator, String arguments)
		throws FunctionException
	{
		String result = null;
		String exceptionMessage = "One string and one integer argument are required.";
		ArrayList values = FunctionHelper.getOneStringAndOneInteger(arguments, ',');
		if (values.size() != 2)
			throw new FunctionException(exceptionMessage);
		try
		{
			String argumentOne = FunctionHelper.trimAndRemoveQuoteChars((String)values.get(0), evaluator.getQuoteCharacter());
			int index = ((Integer)values.get(1)).intValue();
			char character[] = new char[1];
			character[0] = argumentOne.charAt(index);
			result = new String(character);
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
