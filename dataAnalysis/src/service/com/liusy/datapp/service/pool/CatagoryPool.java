package com.liusy.datapp.service.pool;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.liusy.core.util.CollectionMapConvert;
import com.liusy.datapp.model.datastandard.StandardCategory;
import com.liusy.datapp.model.resource.ResourceTable;
import com.liusy.datapp.service.datastandard.StandardCategoryService;
import com.liusy.datapp.service.resource.ResourceTableService;

public class CatagoryPool implements Serializable{
	private Map<String,List<String>> catagoryTableMap=new HashMap<String, List<String>>();
	private ResourceTableService resourceTableService;
	private StandardCategoryService standardCategoryService;
	private final Log logger = LogFactory.getLog(this.getClass());
	public void clearCatagoryPool(){
		catagoryTableMap.clear();
	}
	public List<String> getCatagoryTableMap(String catagoryId){
		if(catagoryTableMap.isEmpty()){
			setAllCatagory();
		}
		if(catagoryTableMap.get(catagoryId)!=null)
			return catagoryTableMap.get(catagoryId);
		else
			return null;
	}
	private void setAllCatagory(){
		try{
			List<ResourceTable> list=resourceTableService.findAllTable();
			CollectionMapConvert<ResourceTable> convert=new CollectionMapConvert<ResourceTable>();
			Map<String,List<ResourceTable>> listmap=convert.convertToMapByParentKey(list, ResourceTable.PROP_CATEGORY_ID);
			List<StandardCategory> catalist=standardCategoryService.findAll();
			CollectionMapConvert<StandardCategory> convert1=new CollectionMapConvert<StandardCategory>();
			Map<String,List<StandardCategory>> cataMap=convert1.convertToMapByParentKey(catalist, StandardCategory.PROP_PARENT_ID);
			List<StandardCategory>  frsitcataList=standardCategoryService.findCategoryByParentId("0");
			for(StandardCategory cata:frsitcataList){
				addChildCatagory(cata, null, cataMap, listmap);
			}
		}catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
		}
	}
	private void addChildCatagory(StandardCategory cata,StandardCategory parentcata,Map<String,List<StandardCategory>> cataMap,Map<String,List<ResourceTable>> resTabMap){
		String cataId=cata.getId().toString();
		List<StandardCategory> list=cataMap.get(cataId);
		List<ResourceTable> tabList=resTabMap.get(cataId);
		//添加属于自己分类的表
		if(tabList!=null && !tabList.isEmpty()){
			if(catagoryTableMap.get(cataId)==null || catagoryTableMap.get(cataId).isEmpty()){
				List<String> list1=new ArrayList<String>();
				catagoryTableMap.put(cataId, list1);
			}
			for(ResourceTable tab:tabList)
				catagoryTableMap.get(cataId).add(String.valueOf(tab.getId()));
			//添加到父分类中
			if(parentcata!=null){
				String parentcataId=parentcata.getId().toString();
				if(catagoryTableMap.get(parentcataId)==null || catagoryTableMap.get(parentcataId).isEmpty()){
					List<String> list1=new ArrayList<String>();
					catagoryTableMap.put(parentcataId, list1);
				}
				for(ResourceTable tab:tabList)
					catagoryTableMap.get(parentcataId).add(String.valueOf(tab.getId()));

			}
		}
		//添加子分类
		if(list!=null && !list.isEmpty()){
			for(StandardCategory cata1:list){
				addChildCatagory(cata1, cata, cataMap, resTabMap);
			}
		}
	}
	
	public void setResourceTableService(ResourceTableService resourceTableService) {
		this.resourceTableService = resourceTableService;
	}
	public void setStandardCategoryService(
			StandardCategoryService standardCategoryService) {
		this.standardCategoryService = standardCategoryService;
	}
}
