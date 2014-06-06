


//    GreaterThanOrEqualOperator.java

package net.sourceforge.jeval.operator;


// Referenced classes of package net.sourceforge.jeval.operator:
//			AbstractOperator

public class GreaterThanOrEqualOperator extends AbstractOperator
{

	public GreaterThanOrEqualOperator()
	{
		super(">=", 4);
	}

	public double evaluate(double leftOperand, double rightOperand)
	{
		return leftOperand < rightOperand ? 0.0D : 1.0D;
	}

	public String evaluate(String leftOperand, String rightOperand)
	{
		if (leftOperand.compareTo(rightOperand) >= 0)
			return "1.0";
		else
			return "0.0";
	}
}
