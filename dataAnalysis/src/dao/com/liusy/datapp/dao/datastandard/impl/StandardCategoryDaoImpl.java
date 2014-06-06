package com.liusy.datapp.dao.datastandard.impl;

import java.util.List;

import com.liusy.dataapplatform.base.exception.DAOException;
import com.liusy.datapp.dao.datastandard.base.BaseStandardCategoryDaoImpl;
import com.liusy.datapp.dao.datastandard.StandardCategoryDao;
import com.liusy.datapp.model.datastandard.StandardCategory;

public class StandardCategoryDaoImpl extends BaseStandardCategoryDaoImpl implements StandardCategoryDao {
   private static final long serialVersionUID = 1L;

   public StandardCategoryDaoImpl () {}
   public List<StandardCategory> findCatagoryByCode() throws DAOException{
	   String hql=" from "+StandardCategory.REF_CLASS+" order by "+StandardCategory.PROP_PARENT_ID+","+StandardCategory.PROP_SQUENCE_NO;
	   try{
	   return (List<StandardCategory>)getHibernateTemplate().find(hql);
	   }catch (Exception e) {
		   e.printStackTrace();
		   throw new DAOException(e);
	}
   }
}
