package com.liusy.datapp.web.query.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.liusy.dataapplatform.base.exception.ServiceException;
import com.liusy.dataapplatform.base.util.ConvertUtil;
import com.liusy.dataapplatform.base.util.PageQuery;
import com.liusy.datapp.model.datastandard.StandardCodeset;
import com.liusy.datapp.model.query.SubjectColumnCfg;
import com.liusy.datapp.service.datastandard.StandardCodesetService;
import com.liusy.datapp.service.datastandard.StandardIndicatorService;
import com.liusy.datapp.service.pool.SubjectConfigPool;
import com.liusy.datapp.service.query.QuerySubjectService;
import com.liusy.datapp.service.query.SubjectColumnCfgService;

import com.liusy.datapp.web.BaseAction;
import com.liusy.datapp.web.query.form.SubjectSynthesisCfgForm;


public class SubjectSynthesisCfgAction extends BaseAction{

	@Override
	public ActionForward doExecute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		String action = request.getParameter("action");
		if (action == null) action = LIST;
		if (log.isDebugEnabled()) log.debug("action:" + action);
		ActionForward forward=null;
		SubjectSynthesisCfgForm theForm=(SubjectSynthesisCfgForm) form;
		try {
			if (LIST.equalsIgnoreCase(action)) forward = searchCfg(mapping, theForm, request,response); // 打开查询列表页面
			else if (ADD.equalsIgnoreCase(action)) forward = addCfg(mapping, theForm, request, response); // 打开增加页面
			else if (SAVE.equalsIgnoreCase(action)) forward = saveCfg(mapping, theForm, request, response); // 保存增加数据
			else if (EDIT.equalsIgnoreCase(action)) forward = editCfg(mapping, theForm, request,response); // 打开修改页面
			else if (UPDATE.equalsIgnoreCase(action)) forward = updateCfg(mapping, theForm, request,response);// 保存修改数据
			else if (DELETE.equalsIgnoreCase(action)) forward = deleteCfg(mapping, theForm, request, response);
		}catch (Exception e) {
			request.setAttribute("err", e);
			forward = mapping.findForward(ERROR);
		}
		return forward;
	}
	private ActionForward searchCfg(ActionMapping mapping, SubjectSynthesisCfgForm theForm, HttpServletRequest request,HttpServletResponse response) throws Exception {
		ActionForward forward=null;
		if (log.isDebugEnabled()) log.debug("Entering 'searchCfg' method");
		try{
			QuerySubjectService querySubjectService=(QuerySubjectService)getBean("querySubjectService");
			PageQuery pageQuery=theForm.getQuery();
			Map<String,String> paramMap=new HashMap<String, String>();
			paramMap.put("subjectId", theForm.getSubjectId());
			pageQuery.setParameters(paramMap);
			//pageQuery.setPageSize("20");
			pageQuery.setSelectParamId("GET_SUBJECT_INDICATOR");
			querySubjectService.queryQuerySubject(pageQuery);
			initForm(theForm, LIST,request);
			setPage(theForm.getQuery());
			forward=mapping.findForward(LIST);
		}catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("err", e);
			forward = mapping.findForward(ERROR);
		}
		return forward;
	}
	private ActionForward addCfg(ActionMapping mapping, SubjectSynthesisCfgForm theForm, HttpServletRequest request,HttpServletResponse response) throws Exception {
		if (log.isDebugEnabled()) log.debug("Entering 'addCfg' method");
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
	private ActionForward saveCfg(ActionMapping mapping, SubjectSynthesisCfgForm theForm, HttpServletRequest request,HttpServletResponse response) throws Exception {
		if (log.isDebugEnabled()) log.debug("Entering 'saveCfg' method");
		ActionForward forward = null;
		try{
			initForm(theForm, SAVE, request);
			SubjectColumnCfg cfg=new SubjectColumnCfg();
			ConvertUtil.mapToObject(cfg, theForm.getRecord());
			SubjectColumnCfgService subjectColumnCfgService=(SubjectColumnCfgService)getBean("subjectColumnCfgService");
			subjectColumnCfgService.createSubjectColumnCfg(cfg);
			NotifyPool(cfg);
			forward = returnForward(mapping, request, RETURN_NORMAL);
			
		}catch (Exception e) {
			e.printStackTrace();
			addMessage(theForm, "保存操作失败!");
			initForm(theForm, ADD,request);
			forward = mapping.findForward(ADD);
		}
		return forward;
	}
	private ActionForward editCfg(ActionMapping mapping, SubjectSynthesisCfgForm theForm, HttpServletRequest request,HttpServletResponse response) throws Exception {
		if (log.isDebugEnabled()) log.debug("Entering 'editCfg' method");
		ActionForward forward = null;
		try{
			SubjectColumnCfgService subjectColumnCfgService=(SubjectColumnCfgService)getBean("subjectColumnCfgService");
			SubjectColumnCfg columncfg=subjectColumnCfgService.findSubjectColumnCfg(Integer.valueOf(theForm.getId()));
			ConvertUtil.objectToMap(theForm.getRecord(),columncfg);
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
	private ActionForward updateCfg(ActionMapping mapping, SubjectSynthesisCfgForm theForm, HttpServletRequest request,HttpServletResponse response) throws Exception {
		if (log.isDebugEnabled()) log.debug("Entering 'updateCfg' method");
		ActionForward forward = null;
		try{
			initForm(theForm, UPDATE, request);
			SubjectColumnCfgService subjectColumnCfgService=(SubjectColumnCfgService)getBean("subjectColumnCfgService");
			SubjectColumnCfg columncfg=subjectColumnCfgService.findSubjectColumnCfg(Integer.valueOf(theForm.getRecord().get("id")));
			ConvertUtil.mapToObject(columncfg, theForm.getRecord(),false);
			subjectColumnCfgService.updateSubjectColumnCfg(columncfg);forward = returnForward(mapping, request, RETURN_NORMAL);
			NotifyPool(columncfg);
			forward = returnForward(mapping, request, RETURN_NORMAL);
		}catch (Exception e) {
			e.printStackTrace();
			addMessage(theForm, "保存操作失败!");
			initForm(theForm, EDIT,request);
			forward = mapping.findForward(EDIT);
		}
		return forward;
	}
	private ActionForward deleteCfg(ActionMapping mapping, SubjectSynthesisCfgForm theForm, HttpServletRequest request,HttpServletResponse response) throws Exception {
		if (log.isDebugEnabled()) log.debug("Entering 'deleteCfg' method");
		try{
			SubjectColumnCfgService subjectColumnCfgService=(SubjectColumnCfgService)getBean("subjectColumnCfgService");
			String del_ids = request.getParameter("ids");
			if (del_ids != null && del_ids.trim().length() > 0) {
				subjectColumnCfgService.removeSubjectColumnCfgs(parseId(del_ids.split(";")));
			}
		}catch (Exception e) {
			e.printStackTrace();
			addMessage(theForm, "删除操作失败!");
		}
		return searchCfg(mapping, theForm, request, response);
		
	}
	private void initForm(SubjectSynthesisCfgForm theForm, String action,HttpServletRequest request) throws Exception {
		if(action.equals(ADD) || action.equals(EDIT)){
			setCode(theForm, "DATAMETA_TYPE,FILTER_OPER");
			StandardIndicatorService standardIndicatorService=(StandardIndicatorService)getBean("standardIndicatorService");
			PageQuery pageQuery=new PageQuery();
			pageQuery.setPageSize("0");
			pageQuery.setSelectParamId("GET_ALL_INDICATOR");
			List<Map<String,String>> list=standardIndicatorService.queryStandardIndicator(pageQuery).getRecordSet();
			request.setAttribute("indList", list);
			StandardCodesetService standardCodesetService=(StandardCodesetService)getBean("standardCodesetService");
			List<StandardCodeset> codesetlist=standardCodesetService.findAllCodeSet();
			request.setAttribute("codeSetList", codesetlist);
			if(action.equals(ADD))
				theForm.getRecord().put("subjectId", theForm.getSubjectId());
		}else if(action.equals(UPDATE) || action.equals(SAVE))
		{
			String[] keyArray={"isFilter","batchQuery","stQuery","homonymQuery","fuzzyQuery"};
			for(int i=0;i<keyArray.length;i++)
				if(theForm.getRecord().get(keyArray[i])==null)
					theForm.getRecord().put(keyArray[i], "0");
		}
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
	private void NotifyPool(SubjectColumnCfg columncfg){
		SubjectConfigPool subjectConfigPool=(SubjectConfigPool)getBean("subjectConfigPool");
		subjectConfigPool.clearSubjectConfigPool(columncfg.getSubjectId().toString());
	}

}
