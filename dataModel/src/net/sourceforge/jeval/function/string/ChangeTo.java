


//    ChangeTo.java

package net.sourceforge.jeval.function.string;

import java.util.ArrayList;
import net.sourceforge.jeval.Evaluator;
import net.sourceforge.jeval.function.*;

public class ChangeTo
	implements Function
{

	public ChangeTo()
	{
	}

	public String getName()
	{
		return "changeto";
	}

	public String getDescription()
	{
		return "String changeto(String value,String sourceStrings, String targetStrings).\t\nsourceStrings?????value,????targetStrings?§Ø???????????????????????????";
	}

	public FunctionResult execute(Evaluator evaluator, String arguments)
		throws FunctionException
	{
		String result = "";
		String exceptionMessage = "Three string arguments are required.";
		ArrayList values = FunctionHelper.getThreeStrings(arguments, ',');
		if (values.size() != 3)
			throw new FunctionException(exceptionMessage);
		try
		{
			String argumentOne = FunctionHelper.trimAndRemoveQuoteChars((String)values.get(0), evaluator.getQuoteCharacter());
			String argumentTwo = FunctionHelper.trimAndRemoveQuoteChars((String)values.get(1), evaluator.getQuoteCharacter());
			String argumentThree = FunctionHelper.trimAndRemoveQuoteChars((String)values.get(2), evaluator.getQuoteCharacter());
			String aTwo[] = argumentTwo.split(";");
			String aThree[] = argumentThree.split(";");
			if (aTwo.length != aThree.length)
				throw new FunctionException("Source and target size are not equal.");
			for (int i = 0; i < aTwo.length; i++)
			{
				if (!argumentOne.trim().equalsIgnoreCase(aTwo[i]))
					continue;
				result = aThree[i];
				break;
			}

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
