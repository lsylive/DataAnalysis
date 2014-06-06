


//    Asin.java

package net.sourceforge.jeval.function.math;

import net.sourceforge.jeval.Evaluator;
import net.sourceforge.jeval.function.*;

public class Asin
	implements Function
{

	public Asin()
	{
	}

	public String getName()
	{
		return "asin";
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
		result = new Double(Math.asin(number.doubleValue()));
		return new FunctionResult(result.toString(), 0);
	}
}
