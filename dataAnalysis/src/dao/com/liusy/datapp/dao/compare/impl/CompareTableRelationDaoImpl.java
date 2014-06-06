package com.liusy.datapp.dao.compare.impl;

import com.liusy.datapp.dao.compare.base.BaseCompareTableRelationDaoImpl;
import com.liusy.datapp.dao.compare.CompareTableRelationDao;
import com.liusy.datapp.model.compare.CompareTableRelation;

public class CompareTableRelationDaoImpl extends BaseCompareTableRelationDaoImpl implements CompareTableRelationDao {
   private static final long serialVersionUID = 1L;

   public CompareTableRelationDaoImpl () {}

   public String getTableName() {
	   return CompareTableRelation.REF_TABLE;
   }

}
