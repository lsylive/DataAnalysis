


//    CompareToIgnoreCase.java

package net.sourceforge.jeval.function.string;

import java.util.ArrayList;
import net.sourceforge.jeval.Evaluator;
import net.sourceforge.jeval.function.*;

public class CompareToIgnoreCase
	implements Function
{

	public CompareToIgnoreCase()
	{
	}

	public String getName()
	{
		return "compareToIgnoreCase";
	}

	public String getDescription()
	{
		return "int compareToIgnoreCase(String source,String destination).\t\n????????§³§Õ????????????§³??????????????????????????????????????";
	}

	public FunctionResult execute(Evaluator evaluator, String arguments)
		throws FunctionException
	{
		Integer result = null;
		String exceptionMessage = "Two string arguments are required.";
		ArrayList strings = FunctionHelper.getStrings(arguments, ',');
		if (strings.size() != 2)
			throw new FunctionException(exceptionMessage);
		try
		{
			String argumentOne = FunctionHelper.trimAndRemoveQuoteChars((String)strings.get(0), evaluator.getQuoteCharacter());
			String argumentTwo = FunctionHelper.trimAndRemoveQuoteChars((String)strings.get(1), evaluator.getQuoteCharacter());
			result = new Integer(argumentOne.compareToIgnoreCase(argumentTwo));
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
