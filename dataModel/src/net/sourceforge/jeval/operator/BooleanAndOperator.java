


//    BooleanAndOperator.java

package net.sourceforge.jeval.operator;


// Referenced classes of package net.sourceforge.jeval.operator:
//			AbstractOperator

public class BooleanAndOperator extends AbstractOperator
{

	public BooleanAndOperator()
	{
		super("&&", 2);
	}

	public double evaluate(double leftOperand, double rightOperand)
	{
		return leftOperand != 1.0D || rightOperand != 1.0D ? 0.0D : 1.0D;
	}
}
