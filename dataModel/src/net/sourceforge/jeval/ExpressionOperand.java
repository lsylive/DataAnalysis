


//    ExpressionOperand.java

package net.sourceforge.jeval;

import net.sourceforge.jeval.operator.Operator;

public class ExpressionOperand
{

	private String value;
	private Operator unaryOperator;

	public ExpressionOperand(String value, Operator unaryOperator)
	{
		this.value = null;
		this.unaryOperator = null;
		this.value = value;
		this.unaryOperator = unaryOperator;
	}

	public String getValue()
	{
		return value;
	}

	public Operator getUnaryOperator()
	{
		return unaryOperator;
	}
}
