


//    ExpressionOperator.java

package net.sourceforge.jeval;

import net.sourceforge.jeval.operator.Operator;

public class ExpressionOperator
{

	private Operator operator;
	private Operator unaryOperator;

	public ExpressionOperator(Operator operator, Operator unaryOperator)
	{
		this.operator = null;
		this.unaryOperator = null;
		this.operator = operator;
		this.unaryOperator = unaryOperator;
	}

	public Operator getOperator()
	{
		return operator;
	}

	public Operator getUnaryOperator()
	{
		return unaryOperator;
	}
}
