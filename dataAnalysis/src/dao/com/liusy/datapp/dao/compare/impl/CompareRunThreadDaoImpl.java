package com.liusy.datapp.dao.compare.impl;

import java.io.Serializable;

import com.liusy.dataapplatform.base.exception.DAOException;
import com.liusy.datapp.dao.compare.base.BaseCompareRunThreadDaoImpl;
import com.liusy.datapp.dao.compare.CompareRunInfoDao;
import com.liusy.datapp.dao.compare.CompareRunThreadDao;
import com.liusy.datapp.model.compare.CompareRunInfo;
import com.liusy.datapp.model.compare.CompareRunThread;

public class CompareRunThreadDaoImpl  extends BaseCompareRunThreadDaoImpl implements CompareRunThreadDao {
   private static final long serialVersionUID = 1L;

   public CompareRunThreadDaoImpl () {}

   public String getTableName() {
	   return CompareRunThread.REF_TABLE;
   }


}
