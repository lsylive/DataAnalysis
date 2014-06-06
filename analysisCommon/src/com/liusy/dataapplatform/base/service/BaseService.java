


//   BaseService.java

package com.liusy.dataapplatform.base.service;

import com.liusy.dataapplatform.base.util.PageQuery;

public interface BaseService
{

	public abstract PageQuery query(PageQuery pagequery)
		throws Exception;
}
