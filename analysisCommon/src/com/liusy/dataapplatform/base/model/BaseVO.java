


//   BaseVO.java

package com.liusy.dataapplatform.base.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public abstract class BaseVO
	implements Serializable
{

	private List dirtyColumnList;

	public BaseVO()
	{
		dirtyColumnList = new ArrayList();
	}

	public void AddDirtyColumn(String key)
	{
		dirtyColumnList.add(key);
	}

	public List getDirtyColumn()
	{
		return dirtyColumnList;
	}

	public abstract String toString();
}
