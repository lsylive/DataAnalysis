package com.liusy.datapp.dao.compare.impl;

import com.liusy.datapp.dao.compare.base.BaseCompareRunInfoDaoImpl;
import com.liusy.datapp.dao.compare.CompareRunInfoDao;
import com.liusy.datapp.model.compare.CompareRunInfo;

public class CompareRunInfoDaoImpl extends BaseCompareRunInfoDaoImpl implements CompareRunInfoDao {
   private static final long serialVersionUID = 1L;

   public CompareRunInfoDaoImpl () {}

   public String getTableName() {
	   return CompareRunInfo.REF_TABLE;
   }

}
