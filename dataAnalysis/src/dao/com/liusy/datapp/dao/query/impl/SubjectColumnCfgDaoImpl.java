package com.liusy.datapp.dao.query.impl;

import java.util.List;

import com.liusy.core.util.Const;
import com.liusy.dataapplatform.base.exception.DAOException;
import com.liusy.datapp.dao.query.base.BaseSubjectColumnCfgDaoImpl;
import com.liusy.datapp.dao.query.SubjectColumnCfgDao;
import com.liusy.datapp.model.query.SubjectColumnCfg;

public class SubjectColumnCfgDaoImpl extends BaseSubjectColumnCfgDaoImpl implements SubjectColumnCfgDao {
   private static final long serialVersionUID = 1L;

   public SubjectColumnCfgDaoImpl () {}
   public List<SubjectColumnCfg> findAllConfigByOrder(String subjectId,boolean isBatch) throws DAOException{
	   try{
	   	String hql= null;
	   	if (isBatch) {
	   		 hql=" from "+SubjectColumnCfg.REF_CLASS+" a where a."+SubjectColumnCfg.PROP_SUBJECT_ID+"=:subjectId and "+SubjectColumnCfg.PROP_IS_FILTER+"="+Const.SYS_CODE_YES+" and "+SubjectColumnCfg.PROP_BATCH_QUERY+"="+Const.SYS_CODE_YES+" order by "+SubjectColumnCfg.PROP_ORDER_NO;
			}else {
				 hql=" from "+SubjectColumnCfg.REF_CLASS+" a where a."+SubjectColumnCfg.PROP_SUBJECT_ID+"=:subjectId and "+SubjectColumnCfg.PROP_IS_FILTER+"="+Const.SYS_CODE_YES+" order by "+SubjectColumnCfg.PROP_ORDER_NO;
			}		  
		   return (List<SubjectColumnCfg>)getHibernateTemplate().findByNamedParam(hql, "subjectId", Integer.valueOf(subjectId));
	   }catch (Exception e) {
		   e.printStackTrace();
		   throw new DAOException(e);
	}
   }

}
