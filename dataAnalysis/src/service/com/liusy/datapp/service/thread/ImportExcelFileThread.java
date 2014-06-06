
package com.liusy.datapp.service.thread;

import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.liusy.core.util.CollectionMapConvert;
import com.liusy.dataapplatform.base.util.ExcelGenerator;
import com.liusy.dataapplatform.base.util.ExcelSheetColProp;
import com.liusy.datapp.dao.query.SynthesisTempSpaceDao;
import com.liusy.datapp.dao.resource.ResourceTableDao;
import com.liusy.datapp.model.resource.ResourceColumn;
import com.liusy.datapp.model.resource.ResourceTable;
import com.liusy.datapp.util.WebContextBeanFactory;
import com.liusy.datapp.util.poolobj.ColumnPoolObj;

/**
 * <p>Title:  ���ݹ���ƽ̨</p>
 *
 * <p>Description:����ָ���ֶε���Excel������</p>
 *
 * <p>Copyright: Copyright (c) 2009</p>
 *
 * <p>Company: </p>
 *
 * @author luoming
 * @version 1.0
 */
public class ImportExcelFileThread implements Runnable{
	private Log log=LogFactory.getLog(getClass());
	private InputStream inputStream;
	private Map<String,String> tableMap;
	private List<ColumnPoolObj> columnList;
	private String startRow;
	private String startCol;
	private String endRow;
	private String endCol;	
	
	public ImportExcelFileThread(Map<String,String> tableMap,List<ColumnPoolObj> columnList,InputStream stream,
			String startRow,String startCol,String endRow,String endCol){
		this.tableMap=tableMap;
		this.inputStream=stream;
		this.columnList=columnList;
		this.startRow=startRow;
		this.startCol=startCol;
		this.endRow=endRow;
		this.endCol=endCol;		
	}
	
	public void run() {
		ExcelSheetColProp prop=new ExcelSheetColProp();
		List<String> columnNameList=new ArrayList<String>();
		List<String> columnEnameList=new ArrayList<String>();
		List<String> columnTypeList=new ArrayList<String>();
		StringBuffer pbuffer=new StringBuffer();
		List<Map<String, String>> columnMapList=new ArrayList<Map<String,String>>();
		try{
			for(ColumnPoolObj col:columnList){
//				columnEnameList.add(col.getName());
//				columnNameList.add(col.getCnName());
//				columnTypeList.add(col.getDataType());
//				Map<String,String> map=new HashMap<String, String>();
//				map.put("columnName", col.getName());
//				map.put("columnType", col.getDataType());
//				map.put("isNullable", "Y"); //Ĭ���ֶζ�����Ϊ��
//				map.put("precise", col.getDataPercise());
//				map.put("columnLength", col.getDataLength());
//				columnMapList.add(map);
				pbuffer.append("?,");
			}
//			String[] headerStr=new String[columnNameList.size()];
//			String[] columnNamestr=new String[columnEnameList.size()];
//			String[] columntypetr=new String[columnNameList.size()];
//			prop.setHeaderName(columnNameList.toArray(headerStr));
//			prop.setColumnName(columnEnameList.toArray(columnNamestr));
//			prop.setColumnType(columnTypeList.toArray(columntypetr));
//			//prop.setTableId(tableId);
//			//TODO:Ĭ�ϴ�2��һ�п�ʼ��ȡ
//			prop.setStartCol(1);
//			prop.setStartRow(2);
			//ExcelGenerator.ReadExcelFile(inputStream, prop);
			CollectionMapConvert<ColumnPoolObj> convert=new CollectionMapConvert<ColumnPoolObj>();
			Map<String,ColumnPoolObj> columnMap=convert.convertListToMap(columnList, ResourceColumn.PROP_NAME);
			int rowCount=ExcelGenerator.ReadExcelFile(inputStream, columnMap, startRow, startCol, endRow, endCol, prop);
			if(rowCount > 0){
				String insertSql="insert into "+tableMap.get("name")+" values ("+pbuffer.substring(0,pbuffer.length()-1)+")";
				SynthesisTempSpaceDao synthesisTempSpaceDao=(SynthesisTempSpaceDao)WebContextBeanFactory.getBean("synthesisTempSpaceDao");
				synthesisTempSpaceDao.batchUpdate(insertSql,prop.getColumnList(), columnList);
			}	
			ResourceTableDao resourceTableDao=(ResourceTableDao)WebContextBeanFactory.getBean("resourceTableDao");
			ResourceTable table=resourceTableDao.get(Integer.valueOf(tableMap.get("id").toString()));
			//���뷴����Ϣд��remark�ֶ�
			SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			if(rowCount > 0 && null != table.getRecordeCount() && !"".equals(table.getRecordeCount()) 
					&& table.getRecordeCount().length() > 0){
				table.setRecordeCount((Integer.parseInt(table.getRecordeCount())+prop.getColumnList().size())+"");
				table.setRemark("["+format.format(new Date())+"]����Excel�ļ���"+prop.getColumnList().size()+"�м�¼");
			}else if(rowCount > 0){
				table.setRecordeCount(prop.getColumnList().size()+"");
				table.setRemark("["+format.format(new Date())+"]����Excel�ļ���"+prop.getColumnList().size()+"�м�¼");
			}
//			table.setRecordeCount((Integer.parseInt(table.getRecordeCount())+prop.getColumnList().size())+"");
//			if(pos!=prop.getColumnList().size()+1)
//				table.setRemark("["+format.format(new Date())+"]����Excel��"+pos+"������,û�е���,��������µ���");
//			else
//				table.setRemark("["+format.format(new Date())+"]����ɹ�");
			resourceTableDao.update(table);
			
		}catch (Exception e) {
			log.error(e);
			e.printStackTrace();
		}
	}
	

}
