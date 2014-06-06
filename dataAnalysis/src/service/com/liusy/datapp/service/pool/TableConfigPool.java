package com.liusy.datapp.service.pool;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.liusy.dataapplatform.base.util.ConvertUtil;
import com.liusy.datapp.model.datastandard.StandardCategory;
import com.liusy.datapp.model.query.QueryColumnCfg;
import com.liusy.datapp.model.query.QueryParamCfg;
import com.liusy.datapp.model.resource.ResourceColumn;
import com.liusy.datapp.model.resource.ResourceTable;
import com.liusy.datapp.model.system.config.SysCity;
import com.liusy.datapp.service.datastandard.StandardCategoryService;
import com.liusy.datapp.service.query.QueryColumnCfgService;
import com.liusy.datapp.service.query.QueryParamCfgService;
import com.liusy.datapp.service.resource.ResourceColumnService;
import com.liusy.datapp.service.resource.ResourceTableService;
import com.liusy.datapp.service.system.config.SysCityService;
import com.liusy.datapp.util.poolobj.ColumnConfigPoolObj;
import com.liusy.datapp.util.poolobj.ColumnPoolObj;
import com.liusy.datapp.util.poolobj.QueryParamPoolObj;

public class TableConfigPool implements Serializable{
	private Map<String, List<ColumnConfigPoolObj>> columnConfigPoolMap=new HashMap<String, List<ColumnConfigPoolObj>>();
	private Map<String,List<QueryParamPoolObj>> queryParamPoolMap=new HashMap<String, List<QueryParamPoolObj>>();
	private Map<String,List<ColumnPoolObj>> tableColumnPoolMap=new HashMap<String, List<ColumnPoolObj>>();
	private Map<String,Map<String,String>> tableIdPoolMap=new HashMap<String, Map<String,String>>();
	private Map<String,Map<String,String>> tableNamePoolMap=new HashMap<String, Map<String,String>>();
	private Map<String,String> cityPoolMap=new HashMap<String, String>();
	private Map<String,String> catagoryPoolMap=new HashMap<String, String>();
	private Map<String,ColumnPoolObj> columnPoolMap=new HashMap<String, ColumnPoolObj>();	
	private QueryColumnCfgService queryColumnCfgService;
	private QueryParamCfgService queryParamCfgService;
	private ResourceColumnService resourceColumnService;
	private ResourceTableService resourceTableService;
	private StandardCategoryService standardCategoryService;
	private SysCityService sysCityService;
	
	private final Log logger = LogFactory.getLog(this.getClass());
	
	public List<ColumnPoolObj> getTableColumnPool(String tableId){
		List<ColumnPoolObj> retList=new ArrayList<ColumnPoolObj>();
		try{
			if(tableId==null || tableId.length()==0)
				return retList;
			if(tableColumnPoolMap.get(tableId)!=null && !tableColumnPoolMap.get(tableId).isEmpty())
				retList=tableColumnPoolMap.get(tableId);
			else{
//			retList=new ArrayList<ColumnPoolObj>();
			List<ResourceColumn> list=resourceColumnService.findColumnByTableIdSort(tableId);
			for(ResourceColumn col:list){
				ColumnPoolObj poolobj=new ColumnPoolObj();
				ConvertUtil.convertToPool(poolobj, col);
				retList.add(poolobj);
			}
			tableColumnPoolMap.put(tableId, retList);
			}
		}catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
		}
		return retList;
	}
	public List<ColumnConfigPoolObj> getColumnConfigPool(String tableId){
		List<ColumnConfigPoolObj> retList=new ArrayList<ColumnConfigPoolObj>();
		try{
			if(tableId==null || tableId.length()==0)
				return retList;
			if(columnConfigPoolMap.get(tableId)!=null && !columnConfigPoolMap.get(tableId).isEmpty())
				retList=columnConfigPoolMap.get(tableId);
			else
			{
				retList=new ArrayList<ColumnConfigPoolObj>();
				List<QueryColumnCfg> list=queryColumnCfgService.findColumnConfigByOrder(tableId);
				for(QueryColumnCfg cfg:list){
					ColumnConfigPoolObj poolobj=new ColumnConfigPoolObj();
					ConvertUtil.convertToPool(poolobj, cfg);
					retList.add(poolobj);
				}
				columnConfigPoolMap.put(tableId, retList);
			}
		}catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
		}
		return retList;
	}
	public List<QueryParamPoolObj> getParamConfigPool(String tableId){
		List<QueryParamPoolObj> retList=new ArrayList<QueryParamPoolObj>();
		try{
			if(tableId==null || tableId.length()==0)
				return retList;
			if(queryParamPoolMap.get(tableId)!=null && !queryParamPoolMap.get(tableId).isEmpty())
				retList=queryParamPoolMap.get(tableId);
			else{
				retList=new ArrayList<QueryParamPoolObj>();
				List<QueryParamCfg> list=queryParamCfgService.findParamByOrder(tableId);
				for(QueryParamCfg cfg:list){
					QueryParamPoolObj poolobj=new QueryParamPoolObj();
					ConvertUtil.convertToPool(poolobj, cfg);
					ColumnPoolObj colpool=getColumnPool(cfg.getColumnId().toString());
					poolobj.setColumnName(colpool.getName());
					poolobj.setDisplayName(colpool.getCnName());
					retList.add(poolobj);
				}
				queryParamPoolMap.put(tableId, retList);
			}
		}catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
		}
		return retList;
	}
	public Map<String,String> getTableMap(String tableId){
		Map<String,String> tablemap=null;
		try{
			if(tableId==null || tableId.length()==0)
				return null;
			if(tableIdPoolMap.get(tableId)!=null )
				tablemap=tableIdPoolMap.get(tableId);
			else
			{
				ResourceTable rsTable=resourceTableService.findResourceTable(Integer.valueOf(tableId));
				tablemap=new HashMap<String, String>();
				tablemap.put("id", tableId);
				tablemap.put("name", rsTable.getName());
				tablemap.put("cName", rsTable.getCnName());
				if(rsTable.getCityCode()!=null &&  !"".equals(rsTable.getCityCode())){
					tablemap.put("cityName", getCityNameByCode(rsTable.getCityCode()));
				}else
					tablemap.put("cityName", "");
				if(rsTable.getCategoryId()!=null)
				{
					tablemap.put("catagoryName", getCatagoryName(rsTable.getCategoryId().toString()));
				}else
					tablemap.put("catagoryName","");
				tablemap.put("securityLevel", rsTable.getSecurityLevel());
				
				tableIdPoolMap.put(tableId, tablemap);
			}
		}catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
		}
		return tablemap;
	}
	public Map<String,String> getTableNameMap(String tableName){
		Map<String,String> tablemap=null;
		try{
			if(tableName==null || tableName.length()==0)
				return null;
			if(tableNamePoolMap.get(tableName)!=null )
				tablemap=tableNamePoolMap.get(tableName);
			else
			{
				List tableList=resourceTableService.findByField(ResourceTable.PROP_NAME, tableName);
				if(tableList!=null && !tableList.isEmpty()){
				ResourceTable rsTable=(ResourceTable) tableList.get(0);
				tablemap=new HashMap<String, String>();
				tablemap.put("id", String.valueOf(rsTable.getId()));
				tablemap.put("name", rsTable.getName());
				tablemap.put("cName", rsTable.getCnName());
				if(rsTable.getCityCode()!=null &&  !"".equals(rsTable.getCityCode())){
					tablemap.put("cityName", getCityNameByCode(rsTable.getCityCode()));
				}else
					tablemap.put("cityName", "");
				if(rsTable.getCategoryId()!=null)
				{
					tablemap.put("catagoryName", getCatagoryName(rsTable.getCategoryId().toString()));
				}else
					tablemap.put("catagoryName","");
				tablemap.put("securityLevel", rsTable.getSecurityLevel());
				
				tableIdPoolMap.put(tableName, tablemap);
				}
			}
		}catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
		}
		return tablemap;
	}
	public String getCityNameByCode(String cityCode){
		String cityName="";
		if(cityPoolMap.get(cityCode)!=null && !"".equals(cityPoolMap.get(cityCode).trim()))
			cityName=cityPoolMap.get(cityCode);
		else{
			SysCity city=sysCityService.findSysCityByCode(cityCode);
			if(city!=null){
				cityName=city.getName();
				cityPoolMap.put(cityCode, cityName);
			}
		}
		return cityName;
	}
	public String getCatagoryName(String catagoryId){
		String catagoryName="";
		if(catagoryPoolMap.get(catagoryId)!=null && !"".equals(catagoryPoolMap.get(catagoryId).trim()))
			catagoryName=catagoryPoolMap.get(catagoryId);
		else{
			StandardCategory category=standardCategoryService.findStandardCategory(Integer.valueOf(catagoryId));
			if(category!=null){
				catagoryName=category.getName();
				catagoryPoolMap.put(catagoryId, catagoryName);
			}
		}
		return catagoryName;
	}
	public ColumnPoolObj getColumnPool(String columnId){
		ColumnPoolObj obj=null;
		try{
			if(columnId==null || columnId.length()==0)
				return null;
			if(columnPoolMap.get(columnId)==null){
				ResourceColumn column=resourceColumnService.findResourceColumn(Integer.valueOf(columnId));
				obj=new ColumnPoolObj();
				ConvertUtil.convertToPool(obj, column);
				columnPoolMap.put(columnId, obj);
			}else
				obj=columnPoolMap.get(columnId);
		}catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
		}
		return obj;
	}
	public void clearColumnPool(String columnId){
		if(columnPoolMap.get(columnId)!=null)
			columnPoolMap.put(columnId, null);
	}
	public void clearColumnConfigPool(String tableId){
		if(columnConfigPoolMap.get(tableId)!=null)
			columnConfigPoolMap.put(tableId, null);
	}
	public void clearParamConfigPool(String tableId){
		if(queryParamPoolMap.get(tableId)!=null)
			queryParamPoolMap.put(tableId, null);
	}
	public void clearTableColumnPool(String tableId){
		if(tableColumnPoolMap.get(tableId)!=null)
			tableColumnPoolMap.put(tableId, null);
	}
	public void clearTablePool(String tableId,String tableName){
		tableIdPoolMap.put(tableId, null);
		tableColumnPoolMap.put(tableId, null);
		tableNamePoolMap.put(tableName, null);
	}
	public void setQueryColumnCfgService(QueryColumnCfgService queryColumnCfgService) {
		this.queryColumnCfgService = queryColumnCfgService;
	}
	public void setQueryParamCfgService(QueryParamCfgService queryParamCfgService) {
		this.queryParamCfgService = queryParamCfgService;
	}
	public void setResourceColumnService(ResourceColumnService resourceColumnService) {
		this.resourceColumnService = resourceColumnService;
	}
	public void setResourceTableService(ResourceTableService resourceTableService) {
		this.resourceTableService = resourceTableService;
	}
	public void setStandardCategoryService(
			StandardCategoryService standardCategoryService) {
		this.standardCategoryService = standardCategoryService;
	}
	public void setSysCityService(SysCityService sysCityService) {
		this.sysCityService = sysCityService;
	}

}
