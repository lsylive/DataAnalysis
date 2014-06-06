


//    Eval.java

package net.sourceforge.jeval.function.string;

import net.sourceforge.jeval.EvaluationException;
import net.sourceforge.jeval.Evaluator;
import net.sourceforge.jeval.function.*;

public class Eval
	implements Function
{

	public Eval()
	{
	}

	public String getName()
	{
		return "eval";
	}

	public String getDescription()
	{
		return "Object eval(String expression).\t\nreturn The evaluated result fot the input expression. \t\nFor example eval(1 + 2)";
	}

	public FunctionResult execute(Evaluator evaluator, String arguments)
		throws FunctionException
	{
		String result = null;
		try
		{
			result = evaluator.evaluate(arguments, false, true);
		}
		catch (EvaluationException ee)
		{
			throw new FunctionException(ee.getMessage(), ee);
		}
		int resultType = 0;
		try
		{
			Double.parseDouble(result);
		}
		catch (NumberFormatException nfe)
		{
			resultType = 1;
		}
		return new FunctionResult(result, resultType);
	}
}
