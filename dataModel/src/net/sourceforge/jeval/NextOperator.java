


//    NextOperator.java

package net.sourceforge.jeval;

import net.sourceforge.jeval.operator.Operator;

class NextOperator
{

	private Operator operator;
	private int index;

	public NextOperator(Operator operator, int index)
	{
		this.operator = null;
		this.index = -1;
		this.operator = operator;
		this.index = index;
	}

	public Operator getOperator()
	{
		return operator;
	}

	public int getIndex()
	{
		return index;
	}
}
