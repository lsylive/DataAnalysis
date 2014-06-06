

package com.liusy.datapp.service.thread;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.supercsv.io.CsvListReader;
import org.supercsv.io.ICsvListReader;
import org.supercsv.prefs.CsvPreference;

import com.liusy.core.util.CollectionMapConvert;
import com.liusy.dataapplatform.base.util.CsvGenerator;
import com.liusy.datapp.dao.query.SynthesisTempSpaceDao;
import com.liusy.datapp.dao.resource.ResourceTableDao;
import com.liusy.datapp.model.resource.ResourceColumn;
import com.liusy.datapp.model.resource.ResourceTable;
import com.liusy.datapp.util.WebContextBeanFactory;
import com.liusy.datapp.util.poolobj.ColumnPoolObj;
/**
 * <p>Title:  数据管理平台</p>
 *
 * <p>Description:根据指定字段导入Csv数据类</p>
 *
 * <p>Copyright: Copyright (c) 2009</p>
 *
 * <p>Company: </p>
 *
 * @author luoming
 * @version 1.0
 */
public class ImportCsvFileThread implements Runnable{
	private Log log=LogFactory.getLog(getClass());
	private InputStream inputStream;
	private Map<String,String> tableMap;
	private List<ColumnPoolObj> columnList;
	
	public ImportCsvFileThread(Map<String,String> tableMap,List<ColumnPoolObj> columnList,InputStream stream){
		this.tableMap=tableMap;
		this.inputStream=stream;
		this.columnList=columnList;
	}
	public void run() {
		try{
			
			List<Map<String,String>> columnResultList=new ArrayList<Map<String,String>>();
			CollectionMapConvert<ColumnPoolObj> convert=new CollectionMapConvert<ColumnPoolObj>();
			Map<String,ColumnPoolObj> columnMap=convert.convertListToMap(columnList, ResourceColumn.PROP_NAME);
			int num=CsvGenerator.ReadFile(inputStream, columnMap, columnResultList);
			if(num==columnResultList.size() && num!=0){
				StringBuffer pbuffer=new StringBuffer();
				for(ColumnPoolObj col:columnList)
					pbuffer.append("?,");
				String insertSql="insert into "+tableMap.get("name")+" values ("+pbuffer.substring(0,pbuffer.length()-1)+")";
				SynthesisTempSpaceDao synthesisTempSpaceDao=(SynthesisTempSpaceDao)WebContextBeanFactory.getBean("synthesisTempSpaceDao");
				synthesisTempSpaceDao.batchUpdate(insertSql,columnResultList, columnList);
			}else{
				//CSV文件如果导入有问题,写到remark字段
				ResourceTableDao resourceTableDao=(ResourceTableDao)WebContextBeanFactory.getBean("resourceTableDao");
				ResourceTable table=resourceTableDao.get(Integer.valueOf(tableMap.get("id").toString()));
				table.setRemark("导入Csv在"+num+"行有误,没有导入,请检查后重新导入");
				resourceTableDao.update(table);
			}
		}catch (Exception e) {
			log.error(e);
			e.printStackTrace();
		}
		
	}

}
