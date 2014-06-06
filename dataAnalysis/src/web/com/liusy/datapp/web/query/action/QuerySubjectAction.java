package com.liusy.datapp.web.query.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.liusy.core.util.CollectionMapConvert;
import com.liusy.dataapplatform.base.exception.ServiceException;
import com.liusy.dataapplatform.base.exception.WebException;
import com.liusy.dataapplatform.base.util.ConvertUtil;
import com.liusy.dataapplatform.base.util.PageQuery;
import com.liusy.dataapplatform.base.util.StringUtil;
import com.liusy.datapp.model.datastandard.StandardCategory;
import com.liusy.datapp.model.query.QuerySubject;
import com.liusy.datapp.model.resource.ResourceTable;

import com.liusy.datapp.service.datastandard.StandardCategoryService;
import com.liusy.datapp.service.pool.SubjectConfigPool;
import com.liusy.datapp.service.query.QuerySubjectService;
import com.liusy.datapp.service.query.SubjectTableRelationService;
import com.liusy.datapp.service.resource.ResourceTableService;
import com.liusy.datapp.util.DhtmlTreeParam;
import com.liusy.datapp.util.DhtmlTreeUtil;
import com.liusy.datapp.web.BaseAction;
import com.liusy.datapp.web.query.form.QuerySubjectForm;


public class QuerySubjectAction extends BaseAction{
	private static String GETTABLE="GETTAB";
	private static String ADDTABLE="ADDTAB";
	private static String SAVETABLE="SAVETAB";
	@Override
	public ActionForward doExecute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		String action = request.getParameter("action");
		if (action == null) action = LIST;
		if (log.isDebugEnabled()) log.debug("action:" + action);
		ActionForward forward;
		QuerySubjectForm theForm=(QuerySubjectForm) form;
		try {
			if (LIST.equalsIgnoreCase(action)) forward = searchSubject(mapping, theForm, request, response); // 打开查询列表页面
			else if (ADDTABLE.equalsIgnoreCase(action)) forward = getSubjectTableTree(mapping, theForm, request, response); // 打开增加页面
//			else if (GETTABLE.equalsIgnoreCase(action)) forward = getSubjectTableTree(mapping, theForm, request, response);  // 打开分类表页面
			else if (SAVETABLE.equalsIgnoreCase(action)) forward = saveSubjectTable(mapping, theForm, request, response); // 打开分类表页面
			else if (ADD.equalsIgnoreCase(action)) forward = addSubject(mapping, theForm, request, response); // 打开增加页面
			else if (SAVE.equalsIgnoreCase(action)) forward = saveSubject(mapping, theForm, request, response); // 保存增加数据
			else if (EDIT.equalsIgnoreCase(action)) forward = editSubject(mapping, theForm, request, response); // 打开修改页面
			else if (UPDATE.equalsIgnoreCase(action)) forward = updateSubject(mapping, theForm, request, response);// 保存修改数据
			else if (DELETE.equalsIgnoreCase(action)) forward = deleteSubject(mapping, theForm, request, response);// 删除
			else {
				request.setAttribute("err", new WebException("找不到该action方法：" + action));
				forward = mapping.findForward(ERROR);// 找不到合适的action
			}
		}
		catch (Exception e) {// 其他系统出错
			request.setAttribute("err", e);
			forward = mapping.findForward(ERROR);
		}
		return forward;
	}
	public ActionForward searchSubject(ActionMapping mapping, QuerySubjectForm theForm, HttpServletRequest request, HttpServletResponse response) throws Exception {
		if (log.isDebugEnabled()) log.debug("Entering 'searchSubject' method");
		ActionForward forward=null;
		try{
			QuerySubjectService querySubjectService=(QuerySubjectService)getBean("querySubjectService");
			PageQuery pageQuery=theForm.getQuery();
			pageQuery.setSelectParamId("GET_COMMSUBJECT_PAGE");
			String queryString = getQueryString(pageQuery);
			pageQuery.getParameters().put("queryString", queryString);
			querySubjectService.queryQuerySubject(pageQuery);
			initForm(theForm, LIST,request);
			setPage(theForm.getQuery());
			forward=mapping.findForward(LIST);
		}catch (Exception e) {
			request.setAttribute("err", e);
			forward = mapping.findForward(ERROR);
		}
		return forward;
	}
	public ActionForward addSubject(ActionMapping mapping, QuerySubjectForm theForm, HttpServletRequest request, HttpServletResponse response) throws Exception {
		if (log.isDebugEnabled()) log.debug("Entering 'addSubject' method");
		ActionForward forward = null;
		try{
			initForm(theForm, ADD,request);
			forward = mapping.findForward(ADD);
			
		}catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("err", e);
			return mapping.findForward(ERROR);
		}
		return forward;
	}
	public ActionForward saveSubject(ActionMapping mapping, QuerySubjectForm theForm, HttpServletRequest request, HttpServletResponse response) throws Exception {
		if (log.isDebugEnabled()) log.debug("Entering 'saveSubject' method");
		ActionForward forward = null;

		try {
			QuerySubject subject=new QuerySubject();
			ConvertUtil.mapToObject(subject, theForm.getRecord());
			QuerySubjectService querySubjectService=(QuerySubjectService)getBean("querySubjectService");	
			String[] tabIds={};
			if(theForm.getSelectedTabId()!=null && !"".equals(theForm.getSelectedTabId()))
				tabIds=theForm.getSelectedTabId().split(",");
			querySubjectService.createSubjectWithSelTable(subject, tabIds);
			NotifyPool(subject);
			forward = returnForward(mapping, request, RETURN_NORMAL);
		}
		catch (ServiceException e) {
			addMessage(theForm, e.getMessage());
			initForm(theForm, ADD, request);
			forward = mapping.findForward(ADD);
		}
		catch (Exception e) {
			e.printStackTrace();
			addMessage(theForm, "保存操作失败!");
			initForm(theForm, ADD,request);
			forward = mapping.findForward(ADD);
		}
		return forward;
	}
	public ActionForward editSubject(ActionMapping mapping, QuerySubjectForm theForm, HttpServletRequest request, HttpServletResponse response) throws Exception {
		if (log.isDebugEnabled()) log.debug("Entering 'editSubject' method");
		ActionForward forward = null;
		try{
			QuerySubjectService querySubjectService=(QuerySubjectService)getBean("querySubjectService");
			QuerySubject subject=querySubjectService.findQuerySubject(new Integer(theForm.getId()));
			ConvertUtil.objectToMap(theForm.getRecord(),subject);
			theForm.setId(subject.getId().toString());
			initForm(theForm, EDIT,request);
			forward=mapping.findForward(EDIT);
		}catch (ServiceException e) {
			request.setAttribute("errMsg", e.getMessage());
			return mapping.findForward(ERROR);
		}
		catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("err", e);
			return mapping.findForward(ERROR);
		}
		return forward;
	}
	
	
	public ActionForward updateSubject(ActionMapping mapping, QuerySubjectForm theForm, HttpServletRequest request, HttpServletResponse response) throws Exception {
		if (log.isDebugEnabled()) log.debug("Entering 'updateSubject' method");
		ActionForward forward = null;

		try {
			QuerySubjectService querySubjectService=(QuerySubjectService)getBean("querySubjectService");
			QuerySubject subject=querySubjectService.findQuerySubject(new Integer(theForm.getRecord().get("id")));
			ConvertUtil.mapToObject(subject, theForm.getRecord(),false);
			String[] tabIds={};
			if(theForm.getSelectedTabId()!=null && !"".equals(theForm.getSelectedTabId()))
				tabIds=theForm.getSelectedTabId().split(",");
			querySubjectService.updateSubjectWithSelTable(subject, tabIds);
			NotifyPool(subject);
			forward = returnForward(mapping, request, RETURN_NORMAL);
		}catch (ServiceException e) {
			e.printStackTrace();
			addMessage(theForm, e.getMessage());
			initForm(theForm, ADD, request);
			forward = mapping.findForward(ADD);
		}
		catch (Exception e) {
			e.printStackTrace();
			addMessage(theForm, "保存操作失败!");
			initForm(theForm, EDIT,request);
			forward = mapping.findForward(EDIT);
		}
		return forward;
	}
	public ActionForward deleteSubject(ActionMapping mapping, QuerySubjectForm theForm, HttpServletRequest request, HttpServletResponse response) throws Exception {
		if (log.isDebugEnabled()) log.debug("Entering 'deleteSubject' method");
		try{
			QuerySubjectService querySubjectService=(QuerySubjectService)getBean("querySubjectService");
			String del_ids = request.getParameter("ids");
			if (del_ids != null && del_ids.trim().length() > 0) {
				querySubjectService.removeQuerySubjects(parseId(del_ids.split(";")));
			}
		}catch (Exception e) {
			e.printStackTrace();
			addMessage(theForm, "删除操作失败!");
		}
		return searchSubject(mapping, theForm, request, response);
	}
	public ActionForward getSubjectTableTree(ActionMapping mapping, QuerySubjectForm theForm, HttpServletRequest request, HttpServletResponse response) throws Exception {
		if (log.isDebugEnabled()) log.debug("Entering 'getSubjectTableTree' method");
		String id=theForm.getId();

		StandardCategoryService standardCategoryService=(StandardCategoryService)getBean("standardCategoryService");
		
		ResourceTableService resourceTableService=(ResourceTableService)getBean("resourceTableService");
		
		try {
			List<StandardCategory> cataList=standardCategoryService.findCatagoryByCode();
			List<ResourceTable> tableList=resourceTableService.findAllTable();
			List<String> selTabIdList=null;
			if(id!=null){
			PageQuery pageQuery=new PageQuery();
			Map<String,String> param=new HashMap<String, String>();
			param.put("subjectId", id);
			pageQuery.setParameters(param);
			pageQuery.setPageSize("0");
			pageQuery.setSelectParamId("GET_SUBJECT_TABLE");
			List<Map<String,String>> tabMapList=resourceTableService.queryResourceTable(pageQuery).getRecordSet();
			selTabIdList=StringUtil.getSingleColumnListByMap(tabMapList, "ID");
			}
			List<Map<String,String>> paramList=getTreeConstructList(cataList, tableList, selTabIdList);
			request.setAttribute("treeList", paramList);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return mapping.findForward("ADDTABLE");
	}
	public ActionForward saveSubjectTable(ActionMapping mapping, QuerySubjectForm theForm, HttpServletRequest request, HttpServletResponse response) throws Exception {
		if (log.isDebugEnabled()) log.debug("Entering 'saveSubjectTable' method");
		ActionForward forward=null;
		String selectedId=theForm.getSelectedItemId();
		String[] ids=selectedId.split(",");
		StringBuffer tabidbuffer=new StringBuffer();
		StringBuffer tabnamebuffer=new StringBuffer();
		try{
			for(int i=0;i<ids.length;i++){
				if(ids[i].startsWith("tab")){
				ResourceTableService resourceTableService=(ResourceTableService)getBean("resourceTableService");
				ResourceTable table=resourceTableService.findResourceTable(Integer.valueOf(ids[i].substring(3,ids[i].length())));
				tabidbuffer.append(ids[i]+",");
				tabnamebuffer.append(table.getCnName()+",");
				}
			}
			request.setAttribute("tabidArr", tabidbuffer.substring(0,tabidbuffer.length()-1));
			request.setAttribute("tabnameArr", tabnamebuffer.substring(0,tabnamebuffer.length()-1));
			forward=mapping.findForward("SAVETABLE");
		
		}catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("err", e);
			forward = mapping.findForward(ERROR);
		}
		return forward;
	}
	public ActionForward deleteSubjectTable(ActionMapping mapping, QuerySubjectForm theForm, HttpServletRequest request, HttpServletResponse response) throws Exception {
		if (log.isDebugEnabled()) log.debug("Entering 'deleteSubjectTable' method");
		String tabId=request.getParameter("tableId");
		try{
//			SubjectTableRelationService subjectTableRelationService=(SubjectTableRelationService)getBean("subjectTableRelationService");
//			subjectTableRelationService.removeSubjectTableRelationByTabId(tabId);
			
		}catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return null;
	}

	private String getQueryString(PageQuery pageQuery) {
		StringBuffer buffer = new StringBuffer();
		Map fields = pageQuery.getParameters();
		String str=(String) fields.get(QuerySubject.PROP_SUB_NAME);
		if(str!=null &&!"".equals(str))
		{
			if(str.contains("%")) buffer.append(" and "+QuerySubject.COL_SUB_NAME +" like '"+str+"'");
			else
				buffer.append(" and "+QuerySubject.COL_SUB_NAME+" like '%"+str+"%'");
		}
		str=(String)fields.get(QuerySubject.PROP_SUB_CODE);
		if(str!=null && !"".equals(str))
		{
			if(str.contains("%")) buffer.append(" and "+QuerySubject.COL_SUB_CODE +" like '"+str+"'");
			else
				buffer.append(" and "+QuerySubject.COL_SUB_CODE+" like '%"+str+"%'");
		}
		return buffer.toString();
	}
	private void initForm(QuerySubjectForm theForm, String action,HttpServletRequest request) throws Exception {
		try{
		if(action.equalsIgnoreCase(EDIT)){
			SubjectTableRelationService subjectTableRelationService=(SubjectTableRelationService)getBean("subjectTableRelationService");
			PageQuery pageQuery=new PageQuery();
			pageQuery.setPageSize("0");
			Map<String, String> params=new HashMap<String, String>();
			params.put("subjectId", theForm.getId());
			pageQuery.setParameters(params);
			pageQuery.setSelectParamId("GET_SUBJECT_TABLE");
			List<Map<String, String>> list=subjectTableRelationService.querySubjectTableRelation(pageQuery).getRecordSet();
			StringBuffer idbuffer=new StringBuffer();
			StringBuffer namebuffer=new StringBuffer();
			for(Map<String,String> map:list){
				idbuffer.append(map.get("ID")+",");
				namebuffer.append(map.get("NAME")+",");
			}
			if(idbuffer.length()>0)
			{
				theForm.setSelectedTabId(idbuffer.substring(0,idbuffer.length()-1));
				request.setAttribute("selTab", namebuffer.substring(0,namebuffer.length()-1));
			}else
				request.setAttribute("selTab", "");
			//request.setAttribute("tableList", list);
		} 
		}
		catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}
	/**
	 * 清理缓存对象
	 * @param subject
	 */
	private void NotifyPool(QuerySubject subject){
		SubjectConfigPool subjectConfigPool=(SubjectConfigPool)getBean("subjectConfigPool");
		subjectConfigPool.clearSubjectConfigPool(subject.getId().toString());
	}
	private List<Map<String,String>> getTreeConstructList(List<StandardCategory> list1,List<ResourceTable> tableList,List<String> selTabIdList) {
		List<Map<String,String>> retList=new ArrayList<Map<String,String>>();
		if (list1 != null) {
			boolean haschild=false;
			for (int i = 0; i < list1.size(); i++) {
				StandardCategory so =  list1.get(i);
				Map<String,String> map=new HashMap<String, String>();
//				if(parentCataMap.get(String.valueOf(so.getId()))!=null || parentTableMap.get(String.valueOf(so.getId()))!=null)
//					haschild=true;
				String parentid="0";
				if(so.getParentId()!=null && !"0".equals(so.getParentId().toString().trim()))
					parentid="cata"+so.getParentId().toString();
				map.put("parentId", parentid);
				map.put("id", "cata"+String.valueOf(so.getId()));
				map.put("label", so.getName());
				map.put("action", "0");
				if(haschild){
					map.put("imageOpen", "0");
					map.put("imageClose", "0");
					map.put("imageLeaf", "0");
				}else{
					map.put("imageOpen", "0");
					map.put("imageClose", "0");
					map.put("imageLeaf", "0");
				}
				map.put("hasChild", "1");
				map.put("isCheck", "0");
				retList.add(map);
		}
		if (tableList != null) {
			
			for (int j = 0; j < tableList.size(); j++) {
				ResourceTable so = tableList.get(j);
				Map<String,String> map=new HashMap<String, String>();
				map.put("parentId", "cata"+so.getCategoryId().toString());
				map.put("id", "tab"+String.valueOf(so.getId()));
				map.put("label", so.getCnName());
				map.put("action", "0");
				
				map.put("imageOpen", "0");
				map.put("imageClose", "0");
				map.put("imageLeaf", "1");
				map.put("hasChild", "0");
				if(selTabIdList!=null && selTabIdList.contains(so.getId().toString()))
					map.put("isCheck", "1");
				else
					map.put("isCheck", "0");
				retList.add(map);
			}
		}
		}
		return retList;
	}
	private java.io.Serializable[] parseId(String[] ids) throws Exception {
		if (ids == null || ids.length == 0) { throw new Exception("非法进入编辑页！"); }
		java.io.Serializable id[] = null;
		try {
			id = new Integer[ids.length];
			for (int i = 0; i < ids.length; i++) {
				id[i] = new Integer(ids[i]);
			}
		}
		catch (Exception e) {
			id = ids;
		}
		return id;
	}
}
