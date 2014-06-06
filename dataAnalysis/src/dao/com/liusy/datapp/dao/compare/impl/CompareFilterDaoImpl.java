package com.liusy.datapp.dao.compare.impl;

import com.liusy.datapp.dao.compare.base.BaseCompareFilterDaoImpl;
import com.liusy.datapp.dao.compare.CompareFilterDao;
import com.liusy.datapp.model.compare.CompareFilter;

public class CompareFilterDaoImpl extends BaseCompareFilterDaoImpl implements CompareFilterDao {
   private static final long serialVersionUID = 1L;

   public CompareFilterDaoImpl () {}

   public String getTableName() {
	   return CompareFilter.REF_TABLE;
   }

}
