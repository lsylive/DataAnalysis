


//    AbstractOperator.java

package net.sourceforge.jeval.operator;

import net.sourceforge.jeval.EvaluationException;

// Referenced classes of package net.sourceforge.jeval.operator:
//			Operator

public abstract class AbstractOperator
	implements Operator
{

	private String symbol;
	private int precedence;
	private boolean unary;

	public AbstractOperator(String symbol, int precedence)
	{
		this.symbol = null;
		this.precedence = 0;
		unary = false;
		this.symbol = symbol;
		this.precedence = precedence;
	}

	public AbstractOperator(String symbol, int precedence, boolean unary)
	{
		this.symbol = null;
		this.precedence = 0;
		this.unary = false;
		this.symbol = symbol;
		this.precedence = precedence;
		this.unary = unary;
	}

	public double evaluate(double leftOperand, double rightOperand)
	{
		return 0.0D;
	}

	public String evaluate(String leftOperand, String rightOperand)
		throws EvaluationException
	{
		throw new EvaluationException("Invalid operation for a string.");
	}

	public double evaluate(double operand)
	{
		return 0.0D;
	}

	public String getSymbol()
	{
		return symbol;
	}

	public int getPrecedence()
	{
		return precedence;
	}

	public int getLength()
	{
		return symbol.length();
	}

	public boolean isUnary()
	{
		return unary;
	}

	public boolean equals(Object object)
	{
		if (object == null)
			return false;
		if (!(object instanceof AbstractOperator))
			throw new IllegalStateException("Invalid operator object.");
		AbstractOperator operator = (AbstractOperator)object;
		return symbol.equals(operator.getSymbol());
	}

	public String toString()
	{
		return getSymbol();
	}
}
