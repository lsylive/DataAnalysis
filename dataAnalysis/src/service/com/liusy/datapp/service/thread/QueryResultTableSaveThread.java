
package com.liusy.datapp.service.thread;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.liusy.core.util.Session;

import com.liusy.dataapplatform.base.util.PageQuery;

import com.liusy.datapp.dao.query.SynthesisTempSpaceDao;
import com.liusy.datapp.model.resource.ResourceColumn;
import com.liusy.datapp.service.dynamicquery.SynthesisColumnGenService;
import com.liusy.datapp.service.dynamicquery.impl.SynthesisColumnGenServiceImpl;
import com.liusy.datapp.service.query.SynthesisQueryService;
import com.liusy.datapp.util.WebContextBeanFactory;
import com.liusy.datapp.util.meta.SqlScriptGenerator;
import com.liusy.datapp.util.poolobj.ColumnPoolObj;
/**
 * <p>Title:  ���ݹ�������</p>
 *
 * <p>Description:���˿ռ��ѯ���ݱ��������˿ռ����ݱ�</p>
 *
 * <p>Copyright: Copyright (c) 2009</p>
 *
 * <p>Company: </p>
 *
 * @author luoming
 * @version 1.0
 */
public class QueryResultTableSaveThread implements Runnable{
	private Log log=LogFactory.getLog(getClass());
	private String tableName;
	private Map<String,String> tableMap;
	private List<ColumnPoolObj> columnpoolList;
	private Session session;
	private ResourceColumn pkobj;
	
	public QueryResultTableSaveThread(Map<String,String>tableMap,String tableName,List<ColumnPoolObj> columnpoolList,ResourceColumn pkobj,Session session){
		this.tableName=tableName;
		this.columnpoolList=columnpoolList;
		this.tableMap=tableMap;
		this.session=session;
		this.pkobj=pkobj;
	}
	public void run() {
		
		try{
			ProcessGenTableAndRecord();
		}catch (Exception e) {
			e.printStackTrace();
			log.error(e);
		}
	}
	private synchronized void ProcessGenTableAndRecord(){
		SqlScriptGenerator generator=(SqlScriptGenerator)WebContextBeanFactory.getBean("oracleScriptGen");
		List<Map<String, String>> columnList=new ArrayList<Map<String,String>>();
		try{
			StringBuffer pbuffer=new StringBuffer("");
			for(ColumnPoolObj obj:columnpoolList){
				Map<String,String> map=new HashMap<String, String>();
				map.put("columnName", obj.getName());
				map.put("columnType", obj.getDataType());
				map.put("isNullable", "Y"); //Ĭ���ֶζ�����Ϊ��
				map.put("precise", obj.getDataPercise());
				map.put("columnLength", obj.getDataLength());
				columnList.add(map);
				pbuffer.append("?,");
			}
			String createSql=generator.generateCreateTableScript(columnList, tableName);
			SynthesisTempSpaceDao synthesisTempSpaceDao=(SynthesisTempSpaceDao)WebContextBeanFactory.getBean("synthesisTempSpaceDao");
			//ִ�н������
			synthesisTempSpaceDao.executeUpdate(createSql);
			//��ȡ��ѯ�Ľ��
			SynthesisQueryService synthesisQueryService=(SynthesisQueryService)WebContextBeanFactory.getBean("synthesisQueryService");
			String sqlstr="select * from "+tableMap.get("sql");
			PageQuery pageQuery=new PageQuery();
			pageQuery.setPageSize("0");
			List<Map<String,String>> list=synthesisQueryService.queryBySql(sqlstr, pageQuery).getRecordSet();
			//���ݰ�ȫ�ȼ����ˣ���֤��ȫ
			SynthesisColumnGenService synthesisColumnGenService=(SynthesisColumnGenService)WebContextBeanFactory.getBean("synthesisColumnGenService");
			synthesisColumnGenService.filterSecurityLevel(tableMap.get("id"),list, columnpoolList, pkobj, session);
			//������������
			String insertSql="insert into "+tableName+" values ("+pbuffer.substring(0,pbuffer.length()-1)+")";
			synthesisTempSpaceDao.batchUpdate(insertSql, list, columnpoolList);
			
		}catch (Exception e) {
			e.printStackTrace();
			log.error(e);
		}
	}
	

}
