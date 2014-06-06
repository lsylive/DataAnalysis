


//    BooleanOrOperator.java

package net.sourceforge.jeval.operator;


// Referenced classes of package net.sourceforge.jeval.operator:
//			AbstractOperator

public class BooleanOrOperator extends AbstractOperator
{

	public BooleanOrOperator()
	{
		super("||", 1);
	}

	public double evaluate(double leftOperand, double rightOperand)
	{
		return leftOperand != 1.0D && rightOperand != 1.0D ? 0.0D : 1.0D;
	}
}
