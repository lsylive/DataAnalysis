


//    ToDegrees.java

package net.sourceforge.jeval.function.math;

import net.sourceforge.jeval.Evaluator;
import net.sourceforge.jeval.function.*;

public class ToDegrees
	implements Function
{

	public ToDegrees()
	{
	}

	public String getName()
	{
		return "toDegrees";
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
		result = new Double(Math.toDegrees(number.doubleValue()));
		return new FunctionResult(result.toString(), 0);
	}
}
