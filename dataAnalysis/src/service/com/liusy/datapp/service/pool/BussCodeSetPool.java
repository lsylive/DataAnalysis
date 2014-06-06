package com.liusy.datapp.service.pool;

import java.io.Serializable;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.liusy.core.util.CollectionMapConvert;
import com.liusy.core.util.Const;
import com.liusy.datapp.model.datastandard.StandardCode;
import com.liusy.datapp.model.datastandard.StandardCodeset;
import com.liusy.datapp.service.datastandard.StandardCodeService;
import com.liusy.datapp.service.datastandard.StandardCodesetService;

import com.liusy.datapp.util.poolobj.ColumnPoolObj;

public class BussCodeSetPool implements Serializable{
	private Map<String,List<StandardCode>> codeSetCodeMap=new HashMap<String, List<StandardCode>>();
	private Map<String,StandardCode> codeMap=new HashMap<String, StandardCode>();
	private Map<String,Map<String,String>> codeVMap=new HashMap<String, Map<String,String>>();
	private Map<String,StandardCodeset> codeSetMap=new HashMap<String, StandardCodeset>();
	private Map<String,StandardCodeset> codeSetIdMap=new HashMap<String, StandardCodeset>();
	private StandardCodesetService standardCodesetService;
	private StandardCodeService standardCodeService;
	private final Log logger = LogFactory.getLog(this.getClass());
	public BussCodeSetPool(){
		//findAllCode();
	}
	private void findAllCode(){
		codeSetCodeMap=standardCodesetService.findAllBussCode();
		List<StandardCode> codeList=standardCodeService.findAll();
		try{
			CollectionMapConvert<StandardCode> convert=new CollectionMapConvert<StandardCode>();
			codeMap=convert.convertListToMap(codeList, "id");
			
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	public List<StandardCode> getCodeListByCodeSetId(String codeSetId){
		List<StandardCode> list=null;
		try{
			if(codeSetCodeMap.get(codeSetId)!=null && !codeSetCodeMap.get(codeSetId).isEmpty())
				list=codeSetCodeMap.get(codeSetId);
			else{
				list=standardCodeService.findCodeByCodeSetId(Integer.valueOf(codeSetId));
				codeSetCodeMap.put(codeSetId, list);
			}
		}catch (Exception e) {
			e.printStackTrace();
			
		}
		return list;
	}
	/**
	 * 翻译结果中的代码集
	 * @param list
	 * @param columnPoolList
	 */
	public void filterBussCodeSet(List<Map<String,String>> list,List<ColumnPoolObj> columnPoolList){
		if(columnPoolList!=null && !columnPoolList.isEmpty() && list!=null && !list.isEmpty()){
			for(Map<String,String> map:list){
				for(int i=0;i<columnPoolList.size();i++){
					String column=columnPoolList.get(i).getName();
					String codeSetId=columnPoolList.get(i).getCodesetId();
					if(codeSetId!=null && !"".equals(codeSetId.trim()) && map.get(column)!=null && !"".equals(map.get(column).trim())){
						String val=map.get(column);
						Map<String,String> codeMaps=getCodeSetValueMap(codeSetId);
						//查看是否能查到对应的代码,找不到保留原值.但以该字段查询就无法查到了
						if(codeMaps.get(val)!=null && !"".equals(codeMaps.get(val)))
							map.put(column, codeMaps.get(val));
					}
					//过滤日期
					try{
					if(columnPoolList.get(i).getDataType().equals(Const.META_TYPE_DATE))
					{
						SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd");
						if(map.get(column)!=null && !"".equals(map.get(column)))
							map.put(column, format.format(format.parse(map.get(column).toString())));
					}
					}catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		}
	}
	
	public void filterBussCodeSetForDetail(List<Map<String,String>> list,List<ColumnPoolObj> columnPoolList){
		if(columnPoolList!=null && !columnPoolList.isEmpty() && list!=null && !list.isEmpty()){
			for(Map<String,String> map:list){
				for(int i=0;i<columnPoolList.size();i++){
					String column=columnPoolList.get(i).getName();
					String codeSetId=columnPoolList.get(i).getCodesetId();
					if(codeSetId!=null && !"".equals(codeSetId.trim()) && map.get(column)!=null && !"".equals(map.get(column).trim())){
						String val=map.get(column);
						Map<String,String> codeMaps=getCodeSetValueMap(codeSetId);
						//查看是否能查到对应的代码,找不到保留原值.但以该字段查询就无法查到了
						if(codeMaps.get(val)!=null && !"".equals(codeMaps.get(val)))
							map.put(column, codeMaps.get(val));
					}
				}
			}
		}
	}
	public Map<String,String> getCodeSetValueMap(String codeSetId){
		Map<String,String> retMap=new HashMap<String, String>();
		if(codeVMap.get(codeSetId)!=null){
			retMap=codeVMap.get(codeSetId);
		}else{
			retMap=getCodeByCodeSet(codeSetId);
			codeVMap.put(codeSetId, retMap);
		}
		return retMap;
	}
	public StandardCodeset getCodeSetByCodeNo(String codeSetNo){
		StandardCodeset sCodeset=null;
		try{
			if(codeSetMap.get(codeSetNo)!=null)
				sCodeset=codeSetMap.get(codeSetNo);
			else{
				sCodeset=standardCodesetService.findCodeSetByCodeNo(codeSetNo);
				codeSetMap.put(codeSetNo, sCodeset);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return sCodeset;	
	}
	public StandardCodeset getCodeSetById(String codeSetId){
		StandardCodeset sCodeset=null;
		try{
			if(codeSetMap.get(codeSetId)!=null)
				sCodeset=codeSetMap.get(codeSetId);
			else{
				sCodeset=standardCodesetService.findStandardCodeset(Integer.valueOf(codeSetId));
				codeSetMap.put(codeSetId, sCodeset);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return sCodeset;	
	}
	public void clearCodeSetById(String codeSetId){
		if(codeSetMap.get(codeSetId)!=null)
			codeSetMap.put(codeSetId, null);
	}
	public StandardCode getCodeById(String codeId){
		if(codeMap.isEmpty())
			findAllCode();
		if(codeMap.get(codeId)!=null)
			return codeMap.get(codeId);
		else
		{
			StandardCode code=standardCodeService.findStandardCode(Integer.valueOf(codeId));
			codeMap.put(codeId, code);
			return code;
		}
	}
	private Map<String,String> getCodeByCodeSet(String codeSetId){
		Map<String,String> retMap=new HashMap<String, String>();
		try{
			List<StandardCode> codeList=standardCodeService.findCodeByCodeSetId(Integer.valueOf(codeSetId));
			if(codeList!=null && !codeList.isEmpty()){
				for(StandardCode code:codeList){
					retMap.put(code.getValue(), code.getName());
				}
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return retMap;
	}
	public String getCodeArrByCodeSet(String codeSetId,String valuesep,String rowsep){
		Map<String,String> codeMap=getCodeSetValueMap(codeSetId);
		StringBuffer buffer=new StringBuffer();
		Iterator<String> iter=codeMap.keySet().iterator();
		while(iter.hasNext()){
			String value=iter.next();
			String name=codeMap.get(value);
			buffer.append(name+valuesep+value+rowsep);
		}
		String retStr="";
		if(buffer.length()>0)
			retStr=buffer.substring(0,buffer.length()-1);
		return retStr;
	}
	public void clearCodeSet(String codeId,String codeSetName){
		codeSetCodeMap.remove(codeId);
		codeSetIdMap.remove(codeId);
		//codeMap.remove(codeId);
		codeSetMap.remove(codeSetName);
		codeVMap.remove(codeId);
	}
	public void clearCodeById(String codeId){
		if(codeMap.get(codeId)!=null)
			codeMap.put(codeId, null);
	}
	public void setStandardCodeService(StandardCodeService standardCodeService) {
		this.standardCodeService = standardCodeService;
	}
	public void setStandardCodesetService(
			StandardCodesetService standardCodesetService) {
		this.standardCodesetService = standardCodesetService;
	}

}
