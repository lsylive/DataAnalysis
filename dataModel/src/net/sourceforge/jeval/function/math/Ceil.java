


//    Ceil.java

package net.sourceforge.jeval.function.math;

import net.sourceforge.jeval.Evaluator;
import net.sourceforge.jeval.function.*;

public class Ceil
	implements Function
{

	public Ceil()
	{
	}

	public String getName()
	{
		return "ceil";
	}

	public String getDescription()
	{
		return " String ceil(String a). \t\n??????§³????????????double ?????????????????????????????";
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
		result = new Double(Math.ceil(number.doubleValue()));
		return new FunctionResult(result.toString(), 0);
	}
}
