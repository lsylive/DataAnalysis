package com.liusy.datapp.dao.compare.impl;

import com.liusy.datapp.dao.compare.base.BaseCompareIndicatorDaoImpl;
import com.liusy.datapp.dao.compare.CompareIndicatorDao;
import com.liusy.datapp.model.compare.CompareIndicator;

public class CompareIndicatorDaoImpl extends BaseCompareIndicatorDaoImpl implements CompareIndicatorDao {
   private static final long serialVersionUID = 1L;

   public CompareIndicatorDaoImpl () {}

   public String getTableName() {
	   return CompareIndicator.REF_TABLE;
   }

}
