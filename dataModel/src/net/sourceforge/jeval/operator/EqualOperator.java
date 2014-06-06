


//    EqualOperator.java

package net.sourceforge.jeval.operator;


// Referenced classes of package net.sourceforge.jeval.operator:
//			AbstractOperator

public class EqualOperator extends AbstractOperator
{

	public EqualOperator()
	{
		super("==", 3);
	}

	public double evaluate(double leftOperand, double rightOperand)
	{
		return leftOperand != rightOperand ? 0.0D : 1.0D;
	}

	public String evaluate(String leftOperand, String rightOperand)
	{
		if (leftOperand.compareTo(rightOperand) == 0)
			return "1.0";
		else
			return "0.0";
	}
}