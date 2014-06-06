


//    Operator.java

package net.sourceforge.jeval.operator;

import net.sourceforge.jeval.EvaluationException;

public interface Operator
{

	public abstract double evaluate(double d, double d1);

	public abstract String evaluate(String s, String s1)
		throws EvaluationException;

	public abstract double evaluate(double d);

	public abstract String getSymbol();

	public abstract int getPrecedence();

	public abstract int getLength();

	public abstract boolean isUnary();
}
