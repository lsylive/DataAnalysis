


//    EvaluationConstants.java

package net.sourceforge.jeval;


public class EvaluationConstants
{

	public static final char SINGLE_QUOTE = 39;
	public static final char DOUBLE_QUOTE = 34;
	public static final char OPEN_BRACE = 123;
	public static final char CLOSED_BRACE = 125;
	public static final char POUND_SIGN = 35;
	public static final String OPEN_VARIABLE = (new StringBuilder(String.valueOf(String.valueOf('#')))).append(String.valueOf('{')).toString();
	public static final String CLOSED_VARIABLE = String.valueOf('}');
	public static final String BOOLEAN_STRING_TRUE = "1.0";
	public static final String BOOLEAN_STRING_FALSE = "0.0";
	public static final char COMMA = 44;
	public static final char FUNCTION_ARGUMENT_SEPARATOR = 44;

	public EvaluationConstants()
	{
	}

}
