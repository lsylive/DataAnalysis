


//    SubtractionOperator.java

package net.sourceforge.jeval.operator;


// Referenced classes of package net.sourceforge.jeval.operator:
//			AbstractOperator

public class SubtractionOperator extends AbstractOperator
{

	public SubtractionOperator()
	{
		super("-", 5, true);
	}

	public double evaluate(double leftOperand, double rightOperand)
	{
		Double rtnValue = new Double(leftOperand - rightOperand);
		return rtnValue.doubleValue();
	}

	public double evaluate(double operand)
	{
		return -operand;
	}
}
