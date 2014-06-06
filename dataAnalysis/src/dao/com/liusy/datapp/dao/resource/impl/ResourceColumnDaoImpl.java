package com.liusy.datapp.dao.resource.impl;

import java.io.Serializable;
import java.util.List;

import com.liusy.dataapplatform.base.exception.DAOException;
import com.liusy.datapp.dao.resource.base.BaseResourceColumnDaoImpl;
import com.liusy.datapp.dao.resource.ResourceColumnDao;
import com.liusy.datapp.model.resource.ResourceColumn;

public class ResourceColumnDaoImpl extends BaseResourceColumnDaoImpl implements ResourceColumnDao {
	private static final long	serialVersionUID	= 1L;

	public ResourceColumnDaoImpl() {
	}

	public List<ResourceColumn> findColumnByTableIdSort(String tableId) throws DAOException {
		String hql = " from " + ResourceColumn.REF_CLASS + " where " + ResourceColumn.PROP_TABLE_ID + "=" + tableId + " order by " + ResourceColumn.PROP_SQUENCE_NO;
		try {
			return (List<ResourceColumn>) getHibernateTemplate().find(hql);
		}
		catch (Exception e) {
			throw new DAOException(e);
		}
	}

	public ResourceColumn findPKColumnByTable(Serializable tableId) throws DAOException {
		try {
			String hql = " from " + ResourceColumn.REF_CLASS + " where " + ResourceColumn.PROP_IS_PRIMARY + "='1' and " + ResourceColumn.PROP_TABLE_ID + "=:tableId";
			List list = getHibernateTemplate().findByNamedParam(hql, "tableId", tableId);
			if (list == null || list.size() == 0) return null;
			else return (ResourceColumn) getHibernateTemplate().findByNamedParam(hql, "tableId", tableId).get(0);
		}
		catch (Exception e) {
			throw new DAOException(e);
		}
	}

	public String getTableName() {
		return ResourceColumn.REF_TABLE;
	}

}
