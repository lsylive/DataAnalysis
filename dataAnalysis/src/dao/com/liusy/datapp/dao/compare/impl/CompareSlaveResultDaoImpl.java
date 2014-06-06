package com.liusy.datapp.dao.compare.impl;

import com.liusy.datapp.dao.compare.base.BaseCompareSlaveResultDaoImpl;
import com.liusy.datapp.dao.compare.CompareSlaveResultDao;
import com.liusy.datapp.model.compare.CompareSlaveResult;

public class CompareSlaveResultDaoImpl extends BaseCompareSlaveResultDaoImpl implements CompareSlaveResultDao {
   private static final long serialVersionUID = 1L;

   public CompareSlaveResultDaoImpl () {}

   public String getTableName() {
	   return CompareSlaveResult.REF_TABLE;
   }

}
