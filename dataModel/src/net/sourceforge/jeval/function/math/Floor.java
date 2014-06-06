


//    Floor.java

package net.sourceforge.jeval.function.math;

import net.sourceforge.jeval.Evaluator;
import net.sourceforge.jeval.function.*;

public class Floor
	implements Function
{

	public Floor()
	{
	}

	public String getName()
	{
		return "floor";
	}

	public String getDescription()
	{
		return " String floor(String a). \t\n ????????????????????double ??????��?????????????????????";
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
		result = new Double(Math.floor(number.doubleValue()));
		return new FunctionResult(result.toString(), 0);
	}
}
