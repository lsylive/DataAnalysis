


//    Atan2.java

package net.sourceforge.jeval.function.math;

import java.util.ArrayList;
import net.sourceforge.jeval.Evaluator;
import net.sourceforge.jeval.function.*;

public class Atan2
	implements Function
{

	public Atan2()
	{
	}

	public String getName()
	{
		return "atan2";
	}

	public String getDescription()
	{
		return " ";
	}

	public FunctionResult execute(Evaluator evaluator, String arguments)
		throws FunctionException
	{
		Double result = null;
		ArrayList numbers = FunctionHelper.getDoubles(arguments, ',');
		if (numbers.size() != 2)
			throw new FunctionException("Two numeric arguments are required.");
		try
		{
			double argumentOne = ((Double)numbers.get(0)).doubleValue();
			double argumentTwo = ((Double)numbers.get(1)).doubleValue();
			result = new Double(Math.atan2(argumentOne, argumentTwo));
		}
		catch (Exception e)
		{
			throw new FunctionException("Two numeric arguments are required.", e);
		}
		return new FunctionResult(result.toString(), 0);
	}
}
