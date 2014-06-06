


//    ArgumentTokenizer.java

package net.sourceforge.jeval;

import java.util.Enumeration;

public class ArgumentTokenizer
	implements Enumeration
{

	public final char defaultDelimiter = ',';
	private String arguments;
	private char delimiter;

	public ArgumentTokenizer(String arguments, char delimiter)
	{
		this.arguments = null;
		this.delimiter = ',';
		this.arguments = arguments;
		this.delimiter = delimiter;
	}

	public boolean hasMoreElements()
	{
		return hasMoreTokens();
	}

	public boolean hasMoreTokens()
	{
		return arguments.length() > 0;
	}

	public Object nextElement()
	{
		return nextToken();
	}

	public String nextToken()
	{
		int charCtr = 0;
		int size = arguments.length();
		int parenthesesCtr = 0;
		String returnArgument = null;
		for (; charCtr < size; charCtr++)
		{
			if (arguments.charAt(charCtr) == '(')
			{
				parenthesesCtr++;
				continue;
			}
			if (arguments.charAt(charCtr) == ')')
			{
				parenthesesCtr--;
				continue;
			}
			if (arguments.charAt(charCtr) != delimiter || parenthesesCtr != 0)
				continue;
			returnArgument = arguments.substring(0, charCtr);
			arguments = arguments.substring(charCtr + 1);
			break;
		}

		if (returnArgument == null)
		{
			returnArgument = arguments;
			arguments = "";
		}
		return returnArgument;
	}
}
