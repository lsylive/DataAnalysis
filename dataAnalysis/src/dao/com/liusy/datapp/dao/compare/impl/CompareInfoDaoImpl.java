package com.liusy.datapp.dao.compare.impl;

import java.io.Serializable;
import java.sql.SQLException;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;

import com.liusy.dataapplatform.base.exception.DAOException;
import com.liusy.datapp.dao.compare.base.BaseCompareInfoDaoImpl;
import com.liusy.datapp.dao.compare.CompareInfoDao;
import com.liusy.datapp.model.compare.CompareInfo;

public class CompareInfoDaoImpl extends BaseCompareInfoDaoImpl implements CompareInfoDao {
   private static final long serialVersionUID = 1L;

   public CompareInfoDaoImpl () {}

   public String getTableName() {
	   return CompareInfo.REF_TABLE;
   }
   public void changeCompareInfoStatus(final Serializable[] ids,final String status) throws DAOException{
	   try{
		   getHibernateTemplate().execute(new HibernateCallback(){
			   public Object doInHibernate(Session session)
				throws HibernateException, SQLException {
				   String hql="update "+CompareInfo.REF_CLASS+" set comapreStatus='"+status+"' where id in (:ids)";
				   Query query=getHibernateTemplate().getSessionFactory().getCurrentSession().createQuery(hql);
				   query.setParameterList("ids", ids);
				   query.executeUpdate();
				   return null;
			   }
		   }
		   );
	   }catch (Exception e) {
		   throw new DAOException(e);
	   }
   }
   public void changeCompareInfoRunStatus(final Object ids,final String status) throws DAOException{
	   try{
		   getHibernateTemplate().execute(new HibernateCallback(){
			   public Object doInHibernate(Session session)
				throws HibernateException, SQLException {
				   String hql="update "+CompareInfo.REF_CLASS+" set comapreStatus='"+status+"' where id =:ids";
				   Query query=session.createQuery(hql);
				   query.setParameter(0, ids);
				   query.executeUpdate();
				   return null;
			   }
		   }
		   );
	   }catch (Exception e) {
		   throw new DAOException(e);
	   }
   }

}
