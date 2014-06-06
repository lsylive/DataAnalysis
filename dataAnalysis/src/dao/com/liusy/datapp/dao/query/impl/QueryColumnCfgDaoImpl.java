package com.liusy.datapp.dao.query.impl;

import java.util.List;

import com.liusy.dataapplatform.base.exception.DAOException;
import com.liusy.datapp.dao.query.base.BaseQueryColumnCfgDaoImpl;
import com.liusy.datapp.dao.query.QueryColumnCfgDao;
import com.liusy.datapp.model.query.QueryColumnCfg;

public class QueryColumnCfgDaoImpl extends BaseQueryColumnCfgDaoImpl implements QueryColumnCfgDao {
   private static final long serialVersionUID = 1L;

   public QueryColumnCfgDaoImpl () {}
   public List<QueryColumnCfg> findColumnConfigByOrder(String tableId) throws DAOException{
	   try{
		   String hql=" from "+QueryColumnCfg.REF_CLASS+" where "+QueryColumnCfg.PROP_TABLE_ID+"="+tableId+" order by "+QueryColumnCfg.PROP_SEQ_NO;
		   return (List<QueryColumnCfg>)getHibernateTemplate().find(hql);
	   }catch (Exception e) {
		   throw new DAOException(e);
	}
   }

}
