package com.liusy.datapp.dao.compare.impl;

import com.liusy.dataapplatform.base.exception.DAOException;
import com.liusy.datapp.dao.compare.base.BaseCompareMainResultDaoImpl;
import com.liusy.datapp.dao.compare.CompareMainResultDao;
import com.liusy.datapp.model.compare.CompareMainResult;

public class CompareMainResultDaoImpl extends BaseCompareMainResultDaoImpl implements CompareMainResultDao {
   private static final long serialVersionUID = 1L;

   public CompareMainResultDaoImpl () {}

   public String getTableName() {
	   return CompareMainResult.REF_TABLE;
   }
   

}
