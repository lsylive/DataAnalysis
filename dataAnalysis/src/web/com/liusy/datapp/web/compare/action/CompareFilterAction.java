package com.liusy.datapp.web.compare.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.liusy.core.util.Const;
import com.liusy.core.util.Session;
import com.liusy.dataapplatform.base.exception.ServiceException;
import com.liusy.dataapplatform.base.exception.WebException;
import com.liusy.dataapplatform.base.util.ConvertUtil;
import com.liusy.dataapplatform.base.util.PageQuery;
import com.liusy.datapp.model.compare.CompareFilter;
import com.liusy.datapp.model.compare.CompareIndicator;
import com.liusy.datapp.model.compare.CompareInfo;
import com.liusy.datapp.model.resource.ResourceColumn;
import com.liusy.datapp.model.resource.ResourceTable;
import com.liusy.datapp.service.compare.CompareFilterService;
import com.liusy.datapp.service.compare.CompareIndicatorService;
import com.liusy.datapp.service.compare.CompareInfoService;
import com.liusy.datapp.service.pool.QueryMetaIndPool;
import com.liusy.datapp.service.resource.ResourceColumnService;
import com.liusy.datapp.service.resource.ResourceTableService;
import com.liusy.datapp.web.BaseAction;
import com.liusy.datapp.web.compare.form.CompareFilterForm;
import com.liusy.datapp.web.compare.form.CompareInfoForm;

public class CompareFilterAction extends BaseAction{

	public ActionForward doExecute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		String action = request.getParameter("action");
		if (action == null) action = LIST;
		if (log.isDebugEnabled()) log.debug("action:" + action);
		ActionForward forward;
		CompareFilterForm theForm=(CompareFilterForm)form;
		try {
			if (LIST.equalsIgnoreCase(action)) forward = searchCompareFilter(mapping, theForm, request, response);
			else if(ADD.equalsIgnoreCase(action)) forward = addCompareFilter(mapping, theForm, request, response);
			else if(SAVE.equalsIgnoreCase(action)) forward = saveCompareFilter(mapping, theForm, request, response);
			else if(EDIT.equalsIgnoreCase(action)) forward = editCompareFilter(mapping, theForm, request, response);
			else if(UPDATE.equalsIgnoreCase(action)) forward = updateCompareFilter(mapping, theForm, request, response);
			else if (DELETE.equalsIgnoreCase(action)) forward = deleteCompareFilter(mapping, theForm, request, response);// 删除
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
	public ActionForward addCompareFilter(ActionMapping mapping, CompareFilterForm theForm,
			HttpServletRequest request, HttpServletResponse response) throws Exception
	{
			if (log.isDebugEnabled())
				log.debug("Entering 'addCompareFilter' method");
			
			
			initForm(theForm,request,ADD);
			theForm.setAction(SAVE);
			return mapping.findForward(ADD);
	}
	public ActionForward editCompareFilter(ActionMapping mapping, CompareFilterForm theForm,
			HttpServletRequest request, HttpServletResponse response) throws Exception
		{
			if (log.isDebugEnabled())
				log.debug("Entering 'editResource' method");
			String id = request.getParameter("id");
			CompareFilterService compareFilterService = (CompareFilterService) getBean("compareFilterService");
			CompareFilter compareFilter = (CompareFilter) compareFilterService.findCompareFilter(new Integer(id));
			ConvertUtil.objectToMap(theForm.getRecord(), compareFilter);
			//theForm.getRecord().put("compId", id);
			//request.setAttribute("compId", id);
			initForm(theForm,request,EDIT);
			theForm.setAction(UPDATE);
			return mapping.findForward(EDIT);
		}
	public ActionForward saveCompareFilter(ActionMapping mapping, CompareFilterForm theForm,
			HttpServletRequest request, HttpServletResponse response) throws Exception
		{
			if (log.isDebugEnabled())
				log.debug("Entering 'saveCompareFilter' method");
			ActionForward forward = null;
			try
			{
				initForm(theForm, request, SAVE);
				CompareFilter compareFilter=new CompareFilter();
				ConvertUtil.mapToObject(compareFilter, theForm.getRecord());
				String param1=theForm.getRecord().get("paramvalue1");
				String param2=theForm.getRecord().get("paramvalue2");
				String param=param1;
				if(theForm.getRecord().get("filterOperator").equalsIgnoreCase(Const.FILTER_OPER_BETWEEN))
					param+=";"+param2;
				compareFilter.setFilterValue(param);
				CompareFilterService compareFilterService = (CompareFilterService) getBean("compareFilterService");
				compareFilterService.createCompareFilter(compareFilter);
				forward = returnForward(mapping, request, RETURN_NORMAL);		
			}
			catch (ServiceException e) {
				addMessage(theForm, e.getMessage());
				//initForm(theForm, ADD, getCityCode(request));
				forward = mapping.findForward(ADD);
			}
			catch (Exception e)
			{
				e.printStackTrace();
				addMessage(theForm, "新建操作失败!");
				//initForm(theForm, ADD, getCityCode(request));
				forward = mapping.findForward(ADD);
			}
			return forward;
		}
		public ActionForward updateCompareFilter(ActionMapping mapping, CompareFilterForm theForm,
				HttpServletRequest request, HttpServletResponse response) throws Exception
			{
				if (log.isDebugEnabled())
					log.debug("Entering 'updateResource' method");

				ActionForward forward = null;
				try
				{
					CompareFilter compareFilter=new CompareFilter();
					ConvertUtil.mapToObject(compareFilter, theForm.getRecord());
					String param1=theForm.getRecord().get("paramvalue1");
					String param2=theForm.getRecord().get("paramvalue2");
					String param=param1;
					if(theForm.getRecord().get("filterOperator").equalsIgnoreCase(Const.FILTER_OPER_BETWEEN) && param2!=null && param2.length()>0)
						param+=";"+param2;
					compareFilter.setFilterValue(param);
					CompareFilterService compareFilterService = (CompareFilterService) getBean("compareFilterService");
					
					compareFilterService.updateCompareFilter(compareFilter);
					request.setAttribute("msg", "修改成功！");
					forward=returnForward(mapping, request, RETURN_NORMAL);
				}
				catch (ServiceException e) {
					addMessage(theForm, e.getMessage());
					forward = mapping.findForward(EDIT);
				}
				catch (Exception e)
				{
					e.printStackTrace();
					addMessage(theForm, "编辑操作失败");
					forward = mapping.findForward(EDIT);
				}
				return forward;
		}
		public ActionForward searchCompareFilter(ActionMapping mapping, CompareFilterForm theForm,
				HttpServletRequest request, HttpServletResponse response) throws Exception
			{
				if (log.isDebugEnabled())
					log.debug("Entering 'searchResource' method");
				ActionForward forward = null;
				
				try
				{
					CompareFilterService compareFilterService = (CompareFilterService) getBean("compareFilterService");
					PageQuery pageQuery = theForm.getQuery();
					pageQuery.setPageCount("0");
					pageQuery.setSelectParamId("GET_COMPAREFILTER");
					Session session=(Session)request.getSession().getAttribute(Const.SESSION);					
//					String queryString = getQueryString(pageQuery,session.getUserId());
//					pageQuery.getParameters().put("queryString", queryString);
					pageQuery.getParameters().put("compId", theForm.getRecord().get("compId"));
					// 执行查询并将结果保存到pagequery中，结果集是一个list<map>对象，用map将每个字段名作key，值做value保存起来
					compareFilterService.queryCompareFilter(pageQuery);
					setPage(pageQuery);
					initForm(theForm, request, LIST);
					forward = mapping.findForward(LIST);
				}
				catch (Exception e)
				{
					e.printStackTrace();
					request.setAttribute("err", e);
					forward=mapping.findForward("error");
				}
				return forward;
			}
		public ActionForward deleteCompareFilter(ActionMapping mapping, CompareFilterForm theForm,
				HttpServletRequest request, HttpServletResponse response) throws Exception
			{
				if (log.isDebugEnabled())
					log.debug("Entering 'deleteResource' method");
				try
				{
					CompareFilterService compareFilterService = (CompareFilterService) getBean("compareFilterService");
					String del_ids = request.getParameter("ids");
					if (del_ids != null && del_ids.trim().length() > 0)
					{
						compareFilterService.removeCompareFilters(parseId(del_ids.split(";")));
					}
				}
				catch (Exception e)
				{
					e.printStackTrace();
					addMessage(theForm, "编辑操作失败");
				}
				return searchCompareFilter(mapping, theForm, request, response);
		}
		
		private java.io.Serializable[] parseId(String[] ids) throws Exception
		{
			if (ids == null || ids.length == 0)
			{
				throw new Exception("非法进入编辑页！");
			}
			java.io.Serializable id[] = null;
			try
			{
				id = new Integer[ids.length];
				for (int i = 0; i < ids.length; i++)
				{
					id[i] = new Integer(ids[i]);
				}
			}
			catch (Exception e)
			{
				id = ids;
			}
			return id;
		}

		private void initForm(CompareFilterForm theForm,HttpServletRequest request,String action)
		{
			setCode(theForm, "FILTER_OPER,YES_OR_NO");
			String compId = theForm.getRecord().get("compId");
			String mainTabId = request.getParameter("mainTabId");
			
			CompareInfoService compareInfoService=(CompareInfoService)getBean("compareInfoService");
			ResourceTableService resourceTableService = (ResourceTableService)getBean("resourceTableService");
			ResourceColumnService resourceColumnService=(ResourceColumnService)getBean("resourceColumnService");
			CompareInfo compareInfo = compareInfoService.findCompareInfo(Integer.parseInt(compId));
			CompareFilterService compareFilterService = (CompareFilterService)getBean("compareFilterService");
			
			if (mainTabId!=null && mainTabId.length() > 0){
				theForm.getRecord().put("mainTabId",mainTabId);
			}
			else if(compareInfo.getDtId()!=null) 
			{
				mainTabId = String.valueOf(compareInfo.getDtId());
				theForm.getRecord().put("mainTabId",mainTabId);
			}

			
			/*
			if (mainTabId!=null && mainTabId.length() > 0 && Integer.parseInt(mainTabId)!= compareInfo.getDtId())
			{//切换后的tableId发生了变化,先删除原有的筛选条件设置
				compareFilterService.removeCompareFiltersByCompId(compId);
			}
			*/
			
			if(ADD.equalsIgnoreCase(action) || EDIT.equalsIgnoreCase(action)){
				List<ResourceColumn> columnList = resourceColumnService.findColumnByTableIdSort(mainTabId);
				request.setAttribute("columnList", columnList);
			}
			if(EDIT.equalsIgnoreCase(action)){
				String value=theForm.getRecord().get("filterValue");
				String oper=theForm.getRecord().get("filterOperator");
				if(Const.FILTER_OPER_BETWEEN.equalsIgnoreCase(oper))
				{
					String[] paramvalue=value.split(";");
					if(paramvalue.length==2)
					{
						theForm.getRecord().put("paramvalue1", paramvalue[0]);
						theForm.getRecord().put("paramvalue2", paramvalue[1]);
					}else
						theForm.getRecord().put("paramvalue1", paramvalue[0]);
				}else
					theForm.getRecord().put("paramvalue1", value);
			}
		}

}
