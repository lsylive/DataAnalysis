


//    FunctionHelper.java

package net.sourceforge.jeval.function;

import java.util.ArrayList;
import net.sourceforge.jeval.ArgumentTokenizer;

// Referenced classes of package net.sourceforge.jeval.function:
//			FunctionException

public class FunctionHelper
{

	public FunctionHelper()
	{
	}

	public static String trimAndRemoveQuoteChars(String arguments, char quoteCharacter)
		throws FunctionException
	{
		String trimmedArgument = arguments;
		trimmedArgument = trimmedArgument.trim();
		if (trimmedArgument.charAt(0) == quoteCharacter)
			trimmedArgument = trimmedArgument.substring(1, trimmedArgument.length());
		else
			throw new FunctionException("Value does not start with a quote.");
		if (trimmedArgument.charAt(trimmedArgument.length() - 1) == quoteCharacter)
			trimmedArgument = trimmedArgument.substring(0, trimmedArgument.length() - 1);
		else
			throw new FunctionException("Value does not end with a quote.");
		return trimmedArgument;
	}

	public static ArrayList getDoubles(String arguments, char delimiter)
		throws FunctionException
	{
		ArrayList returnValues = new ArrayList();
		try
		{
			String token;
			for (ArgumentTokenizer tokenizer = new ArgumentTokenizer(arguments, delimiter); tokenizer.hasMoreTokens(); returnValues.add(new Double(token)))
				token = tokenizer.nextToken().trim();

		}
		catch (Exception e)
		{
			throw new FunctionException("Invalid values in string.", e);
		}
		return returnValues;
	}

	public static ArrayList getStrings(String arguments, char delimiter)
		throws FunctionException
	{
		ArrayList returnValues = new ArrayList();
		try
		{
			String token;
			for (ArgumentTokenizer tokenizer = new ArgumentTokenizer(arguments, delimiter); tokenizer.hasMoreTokens(); returnValues.add(token))
				token = tokenizer.nextToken();

		}
		catch (Exception e)
		{
			throw new FunctionException("Invalid values in string.", e);
		}
		return returnValues;
	}

	public static ArrayList getOneStringAndOneInteger(String arguments, char delimiter)
		throws FunctionException
	{
		ArrayList returnValues = new ArrayList();
		try
		{
			ArgumentTokenizer tokenizer = new ArgumentTokenizer(arguments, delimiter);
			for (int tokenCtr = 0; tokenizer.hasMoreTokens(); tokenCtr++)
				if (tokenCtr == 0)
				{
					String token = tokenizer.nextToken();
					returnValues.add(token);
				} else
				if (tokenCtr == 1)
				{
					String token = tokenizer.nextToken().trim();
					returnValues.add(new Integer((new Double(token)).intValue()));
				} else
				{
					throw new FunctionException("Invalid values in string.");
				}

		}
		catch (Exception e)
		{
			throw new FunctionException("Invalid values in string.", e);
		}
		return returnValues;
	}

	public static ArrayList getTwoStringsAndOneInteger(String arguments, char delimiter)
		throws FunctionException
	{
		ArrayList returnValues = new ArrayList();
		try
		{
			ArgumentTokenizer tokenizer = new ArgumentTokenizer(arguments, delimiter);
			for (int tokenCtr = 0; tokenizer.hasMoreTokens(); tokenCtr++)
				if (tokenCtr == 0 || tokenCtr == 1)
				{
					String token = tokenizer.nextToken();
					returnValues.add(token);
				} else
				if (tokenCtr == 2)
				{
					String token = tokenizer.nextToken().trim();
					returnValues.add(new Integer((new Double(token)).intValue()));
				} else
				{
					throw new FunctionException("Invalid values in string.");
				}

		}
		catch (Exception e)
		{
			throw new FunctionException("Invalid values in string.", e);
		}
		return returnValues;
	}

	public static ArrayList getOneStringAndTwoIntegers(String arguments, char delimiter)
		throws FunctionException
	{
		ArrayList returnValues = new ArrayList();
		try
		{
			ArgumentTokenizer tokenizer = new ArgumentTokenizer(arguments, delimiter);
			for (int tokenCtr = 0; tokenizer.hasMoreTokens(); tokenCtr++)
				if (tokenCtr == 0)
				{
					String token = tokenizer.nextToken().trim();
					returnValues.add(token);
				} else
				if (tokenCtr == 1 || tokenCtr == 2)
				{
					String token = tokenizer.nextToken().trim();
					returnValues.add(new Integer((new Double(token)).intValue()));
				} else
				{
					throw new FunctionException("Invalid values in string.");
				}

		}
		catch (Exception e)
		{
			throw new FunctionException("Invalid values in string.", e);
		}
		return returnValues;
	}

	public static ArrayList getThreeStrings(String arguments, char delimiter)
		throws FunctionException
	{
		ArrayList returnValues = getStrings(arguments, delimiter);
		if (returnValues.size() != 3)
			throw new FunctionException("Invalid values in string.");
		else
			return returnValues;
	}
}
