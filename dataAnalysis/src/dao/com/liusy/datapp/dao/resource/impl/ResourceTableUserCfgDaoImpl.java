package com.liusy.datapp.dao.resource.impl;

import com.liusy.datapp.dao.resource.base.BaseResourceTableUserCfgDaoImpl;
import com.liusy.datapp.dao.resource.ResourceTableUserCfgDao;
import com.liusy.datapp.model.resource.ResourceTableUserCfg;

public class ResourceTableUserCfgDaoImpl extends BaseResourceTableUserCfgDaoImpl implements ResourceTableUserCfgDao {
   private static final long serialVersionUID = 1L;

   public ResourceTableUserCfgDaoImpl () {}

   public String getTableName() {
	
	   return ResourceTableUserCfg.REF_TABLE;
   }

}
