


//    ParsedFunction.java

package net.sourceforge.jeval;

import net.sourceforge.jeval.function.Function;
import net.sourceforge.jeval.operator.Operator;

public class ParsedFunction
{

	private final Function function;
	private final String arguments;
	private final Operator unaryOperator;

	public ParsedFunction(Function function, String arguments, Operator unaryOperator)
	{
		this.function = function;
		this.arguments = arguments;
		this.unaryOperator = unaryOperator;
	}

	public Function getFunction()
	{
		return function;
	}

	public String getArguments()
	{
		return arguments;
	}

	public Operator getUnaryOperator()
	{
		return unaryOperator;
	}
}
