


//   ISqlDao.java

package com.liusy.dataapplatform.base.dao;

import com.liusy.dataapplatform.base.exception.DAOException;
import com.liusy.dataapplatform.base.exception.ServiceException;
import com.liusy.dataapplatform.base.util.PageQuery;
import java.util.List;

public interface ISqlDao
{

	public abstract PageQuery queryBySql(String s, PageQuery pagequery)
		throws ServiceException;

	public abstract int queryCountBySql(String s)
		throws DAOException;

	public abstract int queryByInt(String s)
		throws DAOException;

	public abstract List queryBySql(String s)
		throws DAOException;

	public abstract PageQuery queryBySqlAndPage(String s, PageQuery pagequery, int i)
		throws DAOException;

	public abstract int getMinId(String s)
		throws DAOException;
}
