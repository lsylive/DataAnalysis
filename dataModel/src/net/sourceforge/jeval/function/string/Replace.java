


//    Replace.java

package net.sourceforge.jeval.function.string;

import java.util.ArrayList;
import net.sourceforge.jeval.Evaluator;
import net.sourceforge.jeval.function.*;

public class Replace
	implements Function
{

	public Replace()
	{
	}

	public String getName()
	{
		return "replace";
	}

	public String getDescription()
	{
		return "String replace(String sourceString,char oldChar, char newChar).\t\n????????\265????????????? newChar ?ùIsourceString????ß‘???????? oldChar ???????";
	}

	public FunctionResult execute(Evaluator evaluator, String arguments)
		throws FunctionException
	{
		String result = null;
		String exceptionMessage = "One string argument and two character arguments are required.";
		ArrayList values = FunctionHelper.getStrings(arguments, ',');
		if (values.size() != 3)
			throw new FunctionException(exceptionMessage);
		try
		{
			String argumentOne = FunctionHelper.trimAndRemoveQuoteChars((String)values.get(0), evaluator.getQuoteCharacter());
			String argumentTwo = FunctionHelper.trimAndRemoveQuoteChars((String)values.get(1), evaluator.getQuoteCharacter());
			String argumentThree = FunctionHelper.trimAndRemoveQuoteChars((String)values.get(2), evaluator.getQuoteCharacter());
			char oldCharacter = ' ';
			if (argumentTwo.length() == 1)
				oldCharacter = argumentTwo.charAt(0);
			else
				throw new FunctionException(exceptionMessage);
			char newCharacter = ' ';
			if (argumentThree.length() == 1)
				newCharacter = argumentThree.charAt(0);
			else
				throw new FunctionException(exceptionMessage);
			result = argumentOne.replace(oldCharacter, newCharacter);
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
