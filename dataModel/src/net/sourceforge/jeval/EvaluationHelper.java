


//    EvaluationHelper.java

package net.sourceforge.jeval;


public class EvaluationHelper
{

	public EvaluationHelper()
	{
	}

	public static String replaceAll(String expression, String oldString, String newString)
	{
		String replacedExpression = expression;
		if (replacedExpression != null)
		{
			int charCtr = 0;
			for (int oldStringIndex = replacedExpression.indexOf(oldString, charCtr); oldStringIndex > -1;)
			{
				StringBuffer buffer = new StringBuffer((new StringBuilder(String.valueOf(replacedExpression.substring(0, oldStringIndex)))).append(replacedExpression.substring(oldStringIndex + oldString.length())).toString());
				buffer.insert(oldStringIndex, newString);
				replacedExpression = buffer.toString();
				charCtr = oldStringIndex + newString.length();
				if (charCtr < replacedExpression.length())
					oldStringIndex = replacedExpression.indexOf(oldString, charCtr);
				else
					oldStringIndex = -1;
			}

		}
		return replacedExpression;
	}

	public static boolean isSpace(char character)
	{
		return character == ' ' || character == '\t' || character == '\n' || character == '\r' || character == '\f';
	}
}
