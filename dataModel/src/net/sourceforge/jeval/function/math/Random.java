


//    Random.java

package net.sourceforge.jeval.function.math;

import net.sourceforge.jeval.Evaluator;
import net.sourceforge.jeval.function.*;

public class Random
	implements Function
{

	public Random()
	{
	}

	public String getName()
	{
		return "random";
	}

	public String getDescription()
	{
		return " ";
	}

	public FunctionResult execute(Evaluator evaluator, String arguments)
		throws FunctionException
	{
		Double result = new Double(Math.random());
		return new FunctionResult(result.toString(), 0);
	}
}
