package com.liusy.datapp.web.compare.action;

import java.util.ArrayList;
import java.util.Collection;
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
import com.liusy.datapp.model.compare.CompareRunInfo;
import com.liusy.datapp.model.compare.CompareRunThread;
import com.liusy.datapp.model.resource.ResourceColumn;
import com.liusy.datapp.model.resource.ResourceTable;
import com.liusy.datapp.service.compare.CompareFilterService;
import com.liusy.datapp.service.compare.CompareIndicatorService;
import com.liusy.datapp.service.compare.CompareInfoService;
import com.liusy.datapp.service.compare.CompareRunInfoService;
import com.liusy.datapp.service.compare.CompareRunThreadService;
import com.liusy.datapp.service.pool.QueryMetaIndPool;
import com.liusy.datapp.service.resource.ResourceColumnService;
import com.liusy.datapp.service.resource.ResourceTableService;
import com.liusy.datapp.web.BaseAction;
import com.liusy.datapp.web.compare.form.CompareFilterForm;
import com.liusy.datapp.web.compare.form.CompareInfoForm;
import com.liusy.datapp.web.compare.form.CompareRunInfoForm;

public class CompareRunInfoAction extends BaseAction{

	public ActionForward doExecute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		String action = request.getParameter("action");
		if (action == null) action = LIST;
		if (log.isDebugEnabled()) log.debug("action:" + action);
		ActionForward forward;
		CompareRunInfoForm theForm=(CompareRunInfoForm)form;
		try {
			if (LIST.equalsIgnoreCase(action)) forward = searchCompareRunInfo(mapping, theForm, request, response);
			else if(EDIT.equalsIgnoreCase(action)) forward = editCompareFilter(mapping, theForm, request, response);
			else if(UPDATE.equalsIgnoreCase(action)) forward = updateCompareFilter(mapping, theForm, request, response);
			else if (DELETE.equalsIgnoreCase(action)) forward = deleteCompareRunInfo(mapping, theForm, request, response);// 删除
			else if ("STOP".equalsIgnoreCase(action)) forward = stopCompareRunInfo(mapping, theForm, request, response);// 暂停
			else if ("RESUME".equalsIgnoreCase(action)) forward = resumeCompareRunInfo(mapping, theForm, request, response);// 暂停
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

	public ActionForward editCompareFilter(ActionMapping mapping, CompareRunInfoForm theForm,
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

		public ActionForward updateCompareFilter(ActionMapping mapping, CompareRunInfoForm theForm,
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
		public ActionForward searchCompareRunInfo(ActionMapping mapping, CompareRunInfoForm theForm,
				HttpServletRequest request, HttpServletResponse response) throws Exception
			{
				if (log.isDebugEnabled())
					log.debug("Entering 'searchCompareRunInfo' method");
				ActionForward forward = null;
				
				try
				{
					String ids = request.getParameter("compId");
					String compId ="";
					if (ids != null && ids.trim().length() > 0)
					{
						compId =String.valueOf(parseId(ids.split(";"))[0]);
						theForm.getRecord().put("compId", compId);
					}
					else
					{
						compId = theForm.getRecord().get("compId");
					}
					
					CompareRunInfoService compareRunInfoService = (CompareRunInfoService) getBean("compareRunInfoService");
					CompareRunThreadService compareRunThreadService = (CompareRunThreadService) getBean("compareRunThreadService");
					
					PageQuery pageQuery = theForm.getQuery();
					pageQuery.setPageCount("0");
					pageQuery.setSelectParamId("GET_COMPARERUNINFO");
					Session session=(Session)request.getSession().getAttribute(Const.SESSION);					
					pageQuery.getParameters().put("compId", compId);
					compareRunInfoService.queryCompareRunInfo(pageQuery);
					setPage(pageQuery);
					theForm.getRecord().put("compId", compId);
					
					setCode(theForm, "COMPARE_RUN_STATUS");
					List<Map<String, String>> list = pageQuery.getRecordSet();
					for (int i = 0; i < list.size(); i++) {
						Map<String, String> map = list.get(i);
						String us = (String) map.get("STATUS");
						if (us == null || us.length()==0 || us.trim().equals(Const.STATUS_RUNNING)) 
						{
							Integer runId = Integer.parseInt(map.get("ID"));
							Collection<CompareRunThread> stoppedThreads =  compareRunThreadService.getStoppedCompareRunThreadsByRunId(runId);
							if (stoppedThreads==null || stoppedThreads.size()==0)
							{
								us =  Const.STAUTS_FINISH;
							}
							else
							{
								us = Const.STATUS_RUNNING;
							}
						}
						map.put("STATUSNAME", findCodeName(theForm, "COMPARE_RUN_STATUS", us.trim()));
						
						String runId = (String)map.get("ID");
						map.put("PMATCHCOUNT", String.valueOf(compareRunInfoService.computePMatchCountByRunId(Integer.parseInt(runId))));

					}
					
					
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
		public ActionForward deleteCompareRunInfo(ActionMapping mapping, CompareRunInfoForm theForm,
				HttpServletRequest request, HttpServletResponse response) throws Exception
			{
				if (log.isDebugEnabled())
					log.debug("Entering 'deleteCompareRunInfo' method");
				try
				{
					CompareRunInfoService compareRunInfoService = (CompareRunInfoService) getBean("compareRunInfoService");
					String del_ids = request.getParameter("ids");
					if (del_ids != null && del_ids.trim().length() > 0)
					{
						compareRunInfoService.removeCompareRunInfos(parseId(del_ids.split(";")));
					}
				}
				catch (Exception e)
				{
					e.printStackTrace();
					addMessage(theForm, "删除操作失败");
				}
				return searchCompareRunInfo(mapping, theForm, request, response);
		}
		
		public ActionForward stopCompareRunInfo(ActionMapping mapping, CompareRunInfoForm theForm,
				HttpServletRequest request, HttpServletResponse response) throws Exception
			{
				if (log.isDebugEnabled())
					log.debug("Entering 'stopCompareRunInfo' method");
				try
				{
					CompareRunInfoService compareRunInfoService = (CompareRunInfoService) getBean("compareRunInfoService");
					String del_ids = request.getParameter("ids");
					if (del_ids != null && del_ids.trim().length() > 0)
					{
						compareRunInfoService.stopCompareRunInfos(parseId(del_ids.split(";")));
					}
				}
				catch (Exception e)
				{
					e.printStackTrace();
					addMessage(theForm, "停止操作失败");
				}
				return searchCompareRunInfo(mapping, theForm, request, response);
		}
		
		public ActionForward resumeCompareRunInfo(ActionMapping mapping, CompareRunInfoForm theForm,
				HttpServletRequest request, HttpServletResponse response) throws Exception
			{
				if (log.isDebugEnabled())
					log.debug("Entering 'resumeCompareRunInfo' method");
				try
				{
					CompareRunInfoService compareRunInfoService = (CompareRunInfoService) getBean("compareRunInfoService");
					String del_ids = request.getParameter("ids");
					if (del_ids != null && del_ids.trim().length() > 0)
					{
						compareRunInfoService.resumeCompareRunInfos(parseId(del_ids.split(";")));
					}
				}
				catch (Exception e)
				{
					e.printStackTrace();
					addMessage(theForm, "停止操作失败");
				}
				return searchCompareRunInfo(mapping, theForm, request, response);
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

		private void initForm(CompareRunInfoForm theForm,HttpServletRequest request,String action)
		{
//			setCode(theForm, "FILTER_OPER,YES_OR_NO");
//			String compId = theForm.getRecord().get("compId");
//			String mainTabId = request.getParameter("mainTabId");
//			
//			CompareInfoService compareInfoService=(CompareInfoService)getBean("compareInfoService");
//			ResourceTableService resourceTableService = (ResourceTableService)getBean("resourceTableService");
//			ResourceColumnService resourceColumnService=(ResourceColumnService)getBean("resourceColumnService");
//			CompareInfo compareInfo = compareInfoService.findCompareInfo(Integer.parseInt(compId));
//			CompareFilterService compareFilterService = (CompareFilterService)getBean("compareFilterService");
//			
//			if (mainTabId!=null && mainTabId.length() > 0){
//				theForm.getRecord().put("mainTabId",mainTabId);
//			}
//			else if(compareInfo.getDtId()!=null) 
//			{
//				mainTabId = String.valueOf(compareInfo.getDtId());
//				theForm.getRecord().put("mainTabId",mainTabId);
//			}
//
//			
//			/*
//			if (mainTabId!=null && mainTabId.length() > 0 && Integer.parseInt(mainTabId)!= compareInfo.getDtId())
//			{//切换后的tableId发生了变化,先删除原有的筛选条件设置
//				compareFilterService.removeCompareFiltersByCompId(compId);
//			}
//			*/
//			
//			if(ADD.equalsIgnoreCase(action) || EDIT.equalsIgnoreCase(action)){
//				List<ResourceColumn> columnList = resourceColumnService.findColumnByTableIdSort(mainTabId);
//				request.setAttribute("columnList", columnList);
//			}
//			if(EDIT.equalsIgnoreCase(action)){
//				String value=theForm.getRecord().get("filterValue");
//				String oper=theForm.getRecord().get("filterOperator");
//				if(Const.FILTER_OPER_BETWEEN.equalsIgnoreCase(oper))
//				{
//					String[] paramvalue=value.split(";");
//					if(paramvalue.length==2)
//					{
//						theForm.getRecord().put("paramvalue1", paramvalue[0]);
//						theForm.getRecord().put("paramvalue2", paramvalue[1]);
//					}else
//						theForm.getRecord().put("paramvalue1", paramvalue[0]);
//				}else
//					theForm.getRecord().put("paramvalue1", value);
//			}
		}

}
