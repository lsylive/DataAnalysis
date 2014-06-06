


//    BooleanNotOperator.java

package net.sourceforge.jeval.operator;


// Referenced classes of package net.sourceforge.jeval.operator:
//			AbstractOperator

public class BooleanNotOperator extends AbstractOperator
{

	public BooleanNotOperator()
	{
		super("!", 0, true);
	}

	public double evaluate(double operand)
	{
		return operand != 1.0D ? 1.0D : 0.0D;
	}
}
