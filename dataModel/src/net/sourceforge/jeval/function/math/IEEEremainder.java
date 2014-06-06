


//    IEEEremainder.java

package net.sourceforge.jeval.function.math;

import java.util.ArrayList;
import net.sourceforge.jeval.Evaluator;
import net.sourceforge.jeval.function.*;

public class IEEEremainder
	implements Function
{

	public IEEEremainder()
	{
	}

	public String getName()
	{
		return "IEEEremainder";
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
			result = new Double(Math.IEEEremainder(argumentOne, argumentTwo));
		}
		catch (Exception e)
		{
			throw new FunctionException("Two numeric arguments are required.", e);
		}
		return new FunctionResult(result.toString(), 0);
	}
}