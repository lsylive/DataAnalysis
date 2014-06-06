package com.liusy.datapp.service.pool;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.liusy.datapp.model.datastandard.StandardCodeset;
import com.liusy.datapp.model.datastandard.StandardIndicator;
import com.liusy.datapp.service.datastandard.StandardCodesetService;
import com.liusy.datapp.service.datastandard.StandardIndicatorService;

public class QueryMetaIndPool implements Serializable{
	private Map<String,Map<String, String>> indMap=new HashMap<String, Map<String,String>>();
		
	public Map<String,String> getIndicatorPool(String indId){
		Map<String,String> map=null;
		try{
			if(indMap.get(indId)!=null && !indMap.get(indId).isEmpty())
				map=indMap.get(indId);
			else{
			StandardIndicator ind=standardIndicatorService.findStandardIndicator(Integer.valueOf(indId));
			if(ind!=null){
				map=new HashMap<String, String>();
				map.put("id", indId);
				map.put("name", ind.getName());
				map.put("code", ind.getIndicatorCode());
				map.put("cnName", ind.getCnName());
				map.put("catagoryId", ind.getCategoryId().toString());
				indMap.put(indId, map);
			}
			}
		}catch (Exception e) {
			logger.error(e);
		}
		return map;
	}
	
	public void clearIndicatorPool(String indId){
		if(indMap.get(indId)!=null)
			indMap.put(indId, null);
	}
	
	private final Log logger = LogFactory.getLog(this.getClass());
	private StandardIndicatorService standardIndicatorService;
	private StandardCodesetService standardCodesetService;
	public void setStandardIndicatorService(
			StandardIndicatorService standardIndicatorService) {
		this.standardIndicatorService = standardIndicatorService;
	}
	public void setStandardCodesetService(
			StandardCodesetService standardCodesetService) {
		this.standardCodesetService = standardCodesetService;
	}

	
}
