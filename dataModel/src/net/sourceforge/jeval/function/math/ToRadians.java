


//    ToRadians.java

package net.sourceforge.jeval.function.math;

import net.sourceforge.jeval.Evaluator;
import net.sourceforge.jeval.function.*;

public class ToRadians
	implements Function
{

	public ToRadians()
	{
	}

	public String getName()
	{
		return "toRadians";
	}

	public String getDescription()
	{
		return " ";
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
		result = new Double(Math.toRadians(number.doubleValue()));
		return new FunctionResult(result.toString(), 0);
	}
}
