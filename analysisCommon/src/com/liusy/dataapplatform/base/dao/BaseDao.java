


//   BaseDao.java

package com.liusy.dataapplatform.base.dao;

import com.liusy.dataapplatform.base.exception.DAOException;
import com.liusy.dataapplatform.base.util.PageQuery;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import org.hibernate.criterion.Order;

public interface BaseDao
{

	public abstract List commonQuery(Collection collection, Collection collection1, int i, int j)
		throws DAOException;

	public abstract List commonQuery(Collection collection)
		throws DAOException;

	public abstract List commonQuery(Collection collection, Collection collection1)
		throws DAOException;

	public abstract long count()
		throws DAOException;

	public abstract long countByFilter(Collection collection)
		throws DAOException;

	public abstract int countByField(String s, Object obj)
		throws DAOException;

	public abstract void delete(Serializable serializable)
		throws DAOException;

	public abstract void deleteAll()
		throws DAOException;

	public abstract void deleteAll(Serializable aserializable[])
		throws DAOException;

	public abstract void deleteAll(Collection collection)
		throws DAOException;

	public abstract void deleteByFilter(Collection collection)
		throws DAOException;

	public abstract void deleteByField(String s, Object obj)
		throws DAOException;

	public abstract List findAll()
		throws DAOException;

	public abstract List findAll(Order order)
		throws DAOException;

	public abstract List findByField(String s, Object obj)
		throws DAOException;

	public abstract List findByFilter(Collection collection)
		throws DAOException;

	public abstract PageQuery queryBySelectId(PageQuery pagequery)
		throws DAOException;

	public abstract void saveOrUpdateAll(Collection collection)
		throws DAOException;

	public abstract String getTableName();

	public abstract List findByHql(String s)
		throws DAOException;

	public abstract List queryBySql(String s)
		throws DAOException;

	public abstract List findByField(String s, Object obj, String s1, boolean flag)
		throws DAOException;

	public abstract List findByFields(String as[], Object aobj[])
		throws DAOException;

	public abstract List findByFields(String as[], Object aobj[], String s, boolean flag)
		throws DAOException;

	public abstract List findByFields(String as[], Object aobj[], String as1[], boolean aflag[])
		throws DAOException;

	public abstract void executeSqlUpdate(String s)
		throws DAOException;

	public abstract void batchUpdate(String s, List list, List list1)
		throws DAOException;
}
