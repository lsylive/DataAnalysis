


//   ConditionBuilder.java

package com.liusy.dataapplatform.base.util;

import java.lang.reflect.Method;
import java.util.*;

// Referenced classes of package com.liusy.dataapplatform.base.util:
//			ConditionList, Condition

public class ConditionBuilder
{

	public ConditionBuilder()
	{
	}

	public static ConditionList build(Object obj1, int empty, Map methods)
		throws Exception
	{
		if (obj1 == null)
			throw new IllegalArgumentException("parameter is null!");
		Method method[] = obj1.getClass().getMethods();
		ConditionList list = new ConditionList();
		for (int i = 0; i < method.length; i++)
		{
			String methodName = method[i].getName();
			String methodFix = methodName.substring(3, methodName.length());
			if (methodName.startsWith("get") && !methodName.equals("getClass") && (methods == null || methods.isEmpty() || methods.containsKey(methodFix)) && method[i].getParameterTypes().length <= 0)
			{
				Object args1[] = new Object[0];
				Object args2[] = new Object[1];
				args2[0] = method[i].invoke(obj1, args1);
				if ((empty != 1 || args2[0] != null) && (empty != 2 || args2[0] != null && !args2[0].equals("") && !args2[0].equals(new Integer(0)) && !args2[0].equals(new Long(0L)) && !args2[0].equals(new Double(0.0D)) && !args2[0].equals(new Float(0.0F))) && !args2[0].equals(new HashMap()) && !args2[0].equals(new ArrayList()))
				{
					Condition condition = null;
					if (methods != null && !methods.isEmpty() && methods.get(methodFix) != null && !methods.get(methodFix).equals(""))
						condition = new Condition(methodFix, (String)methods.get(methodFix), args2[0]);
					else
						condition = new Condition(methodFix, "EQUALS", args2[0]);
					list.add(condition);
				}
			}
		}

		return list;
	}
}
