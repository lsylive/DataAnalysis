package com.liusy.datapp.dao.resource.impl;

import com.liusy.datapp.dao.resource.base.BaseResourceColumnUserCfgDaoImpl;
import com.liusy.datapp.dao.resource.ResourceColumnUserCfgDao;
import com.liusy.datapp.model.resource.ResourceColumnUserCfg;

public class ResourceColumnUserCfgDaoImpl extends BaseResourceColumnUserCfgDaoImpl implements ResourceColumnUserCfgDao {
   private static final long serialVersionUID = 1L;

   public ResourceColumnUserCfgDaoImpl () {}

   public String getTableName() {
	   return ResourceColumnUserCfg.REF_TABLE;
   }
   

}
