


//   ICondition.java

package com.liusy.dataapplatform.base.util;

import org.hibernate.criterion.Criterion;

public interface ICondition
{

	public abstract Criterion generateExpression();

	public abstract Object getValue();

	public abstract Object[] getValues();

	public abstract String getState();

	public abstract String getName();
}
