package com.liusy.datapp.dao.query.impl;

import java.util.List;

import com.liusy.dataapplatform.base.exception.DAOException;
import com.liusy.datapp.dao.query.base.BaseQueryParamCfgDaoImpl;
import com.liusy.datapp.dao.query.QueryParamCfgDao;
import com.liusy.datapp.model.query.QueryParamCfg;

public class QueryParamCfgDaoImpl extends BaseQueryParamCfgDaoImpl implements QueryParamCfgDao {
   private static final long serialVersionUID = 1L;

   public QueryParamCfgDaoImpl () {}
   public List<QueryParamCfg> findParamByOrder(String tableId) throws DAOException{
	   try{
		   String hql=" from "+QueryParamCfg.REF_CLASS+" where "+QueryParamCfg.PROP_TABLE_ID+"="+tableId+" order by "+QueryParamCfg.PROP_SEQ_NO;
		   return (List<QueryParamCfg>)getHibernateTemplate().find(hql);
	   }catch (Exception e) {
		   throw new DAOException(e);
	}
   }

}
