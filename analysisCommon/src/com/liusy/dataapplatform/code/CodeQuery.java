


//   CodeQuery.java

package com.liusy.dataapplatform.code;

import java.util.List;

public interface CodeQuery
{

	public abstract List getCodeset()
		throws Exception;

	public abstract List getCode(String s)
		throws Exception;
}
