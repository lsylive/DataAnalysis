


//    MultiplicationOperator.java

package net.sourceforge.jeval.operator;


// Referenced classes of package net.sourceforge.jeval.operator:
//			AbstractOperator

public class MultiplicationOperator extends AbstractOperator
{

	public MultiplicationOperator()
	{
		super("*", 6);
	}

	public double evaluate(double leftOperand, double rightOperand)
	{
		Double rtnValue = new Double(leftOperand * rightOperand);
		return rtnValue.doubleValue();
	}
}
