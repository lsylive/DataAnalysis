


//    Abs.java

package net.sourceforge.jeval.function.math;

import net.sourceforge.jeval.Evaluator;
import net.sourceforge.jeval.function.*;

public class Abs
	implements Function
{

	public Abs()
	{
	}

	public String getName()
	{
		return "abs";
	}

	public String getDescription()
	{
		return " String abs(String a). \t\n???? a ????????";
	}

	public FunctionResult execute(Evaluator evaluator, String arguments)
		throws FunctionException
	{
		Double result = null;
		Double number = null;
		try
		{
			number = new Double(arguments);
		}
		catch (Exception e)
		{
			throw new FunctionException("Invalid argument.", e);
		}
		result = new Double(Math.abs(number.doubleValue()));
		return new FunctionResult(result.toString(), 0);
	}
}
