


//   ConditionList.java

package com.liusy.dataapplatform.base.util;

import java.util.ArrayList;
import java.util.Iterator;

// Referenced classes of package com.liusy.dataapplatform.base.util:
//			Condition

public class ConditionList extends ArrayList
{

	private static final long serialVersionUID = 1L;
	private final String GREATNESS = " > ";
	private final String SMALLNESS = " < ";
	private final String AND = " AND ";
	private final String EQUALS = " = ";
	private final String LIKE = " LIKE ";
	private final String IS_NOT_NULL = " IS NOT NULL ";
	private final String IS_NULL = " IS NULL ";

	public ConditionList()
	{
	}

	public String toString()
	{
		StringBuffer sql = new StringBuffer();
		Iterator iterator = iterator();
		while (iterator.hasNext()) 
		{
			Condition condition = (Condition)iterator.next();
			if (condition.getState() == "BETWEEN")
			{
				if (condition.getValues().length < 2)
					continue;
				if (condition.getValues()[0] != null || condition.getValues()[1] != null)
					sql.append(" AND ");
				if (condition.getValues()[0] != null)
				{
					sql.append(condition.getName());
					sql.append(" > ");
					sql.append(valueToString(condition.getValues()[0]));
				}
				if (condition.getValues()[0] != null && condition.getValues()[1] != null)
					sql.append(" AND ");
				if (condition.getValues()[1] != null)
				{
					sql.append(condition.getName());
					sql.append(" < ");
					sql.append(valueToString(condition.getValues()[1]));
				}
			}
			if (condition.getState() == "EQUALS")
			{
				if (condition.getValue() == null)
					continue;
				sql.append(" AND ");
				sql.append(condition.getName());
				sql.append(" = ");
				sql.append(valueToString(condition.getValue()));
			}
			if (condition.getState() == "LIKE")
			{
				if (condition.getValue() == null)
					continue;
				sql.append(" AND ");
				sql.append(condition.getName());
				sql.append(" LIKE ");
				sql.append("'");
				sql.append(condition.getValue());
				sql.append("'");
			}
			if (condition.getState() == "NOT_NULL")
			{
				if (condition.getValue() == null)
					continue;
				sql.append(" AND ");
				sql.append(condition.getName());
				sql.append(" IS NOT NULL ");
			}
			if (condition.getState() == "IS_NULL" && condition.getValue() != null)
			{
				sql.append(" AND ");
				sql.append(condition.getName());
				sql.append(" IS NULL ");
			}
		}
		return sql.toString();
	}

	public String toString(String tablename)
	{
		StringBuffer sql = new StringBuffer();
		Condition condition;
		for (Iterator iterator = iterator(); iterator.hasNext(); sql.append(condition.toSQLString(tablename)))
		{
			condition = (Condition)iterator.next();
			sql.append(" AND ");
		}

		return sql.toString();
	}

	private String valueToString(Object object)
	{
		if (object instanceof String)
			return (new StringBuilder("'")).append(object.toString()).append("'").toString();
		else
			return object.toString();
	}
}
