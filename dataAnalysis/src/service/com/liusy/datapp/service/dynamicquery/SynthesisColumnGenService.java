package com.liusy.datapp.service.dynamicquery;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import com.liusy.core.util.Session;
import com.liusy.dataapplatform.base.form.QueryForm;
import com.liusy.datapp.model.resource.ResourceColumn;
import com.liusy.datapp.service.pool.BussCodeSetPool;
import com.liusy.datapp.util.poolobj.ColumnPoolObj;
import com.liusy.datapp.util.poolobj.SubjectColumnConfigPoolObj;

public interface SynthesisColumnGenService extends Serializable{
	public String getInputHtmlCode(SubjectColumnConfigPoolObj pool,BussCodeSetPool busspool,QueryForm theForm);
	public String getDisplayDataType(ColumnPoolObj obj);
	public String genenrateResultHtmlCode(List<ColumnPoolObj> colPoolObj,Map<String,String> record,HttpServletRequest request);
	public String getResultXml(List<Map<String,String>> list,List<String> colNameList,ResourceColumn obj);
	public String filterColumnType(ColumnPoolObj obj);
	public String getBatchInputHtmlCode(SubjectColumnConfigPoolObj pool,BussCodeSetPool busspool,QueryForm theForm,JSONObject jsonObj);
	public String getColumnHtmlCode(ColumnPoolObj pool,BussCodeSetPool busspool,QueryForm theForm);
	/**
	 * 安装配置安全等级过滤查询后的字段
	 * @param tableId
	 * @param resultList
	 * @param columnPoolList
	 * @param pkobj
	 * @param session
	 */
	public void filterSecurityLevel(String tableId,List<Map<String,String>> resultList,List<ColumnPoolObj> columnPoolList,ResourceColumn pkobj,Session session);
	public String getCodeSetHtml(ColumnPoolObj obj,BussCodeSetPool busspool,String ctrlName);
	public String getCodeSetHtml(SubjectColumnConfigPoolObj obj,BussCodeSetPool busspool,String ctrlName);
	public String getTableCountResultXml(List<Map<String,String>> list);
	public void setRowCount(int count);
}
