package com.liusy.datapp.web.compare.action;


import java.io.Serializable;
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
import com.liusy.dataapplatform.base.util.StringUtil;
import com.liusy.datapp.model.compare.CompareInfo;
import com.liusy.datapp.model.datastandard.StandardCategory;
import com.liusy.datapp.model.resource.ResourceTable;
import com.liusy.datapp.service.compare.CompareInfoService;
import com.liusy.datapp.service.datastandard.StandardCategoryService;
import com.liusy.datapp.service.resource.ResourceTableService;
import com.liusy.datapp.service.thread.compare.CompareTaskExecutor;
import com.liusy.datapp.web.BaseAction;
import com.liusy.datapp.web.compare.form.CompareInfoForm;
import com.liusy.datapp.web.query.form.QuerySubjectForm;

public class CompareInfoAction extends BaseAction{

	@Override
	public ActionForward doExecute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		String action = request.getParameter("action");
		if (action == null) action = LIST;
		if (log.isDebugEnabled()) log.debug("action:" + action);
		ActionForward forward;
		CompareInfoForm theForm=(CompareInfoForm)form;
		try {
			if (LIST.equalsIgnoreCase(action)) forward = searchCompareInfo(mapping, theForm, request, response);
			else if(ADD.equalsIgnoreCase(action)) forward = addCompareInfo(mapping, theForm, request, response);
			else if(SAVE.equalsIgnoreCase(action)) forward = saveCompareInfo(mapping, theForm, request, response);
			else if(EDIT.equalsIgnoreCase(action)) forward = editCompareInfo(mapping, theForm, request, response);
			else if(UPDATE.equalsIgnoreCase(action)) forward = updateCompareInfo(mapping, theForm, request, response);
			else if (DELETE.equalsIgnoreCase(action)) forward = deleteCompareInfo(mapping, theForm, request, response);// 删除
//			else if ("ADDTABLE".equalsIgnoreCase(action)) forward = getSubjectTableTree(mapping, theForm, request, response); // 打开增加页面
			else if("CONFIG".equalsIgnoreCase(action)) forward=configCompareInfo(mapping, theForm, request, response);
			else if("STARTCOMPARE".equalsIgnoreCase(action)) forward=startCompareInfo(mapping, theForm, request, response);
			else if("STOPCOMPARE".equalsIgnoreCase(action)) forward=stopCompareInfo(mapping, theForm, request, response);
			else if("OVERVIEW".equalsIgnoreCase(action)) forward=overViewCompareInfo(mapping, theForm, request, response);
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
	public ActionForward addCompareInfo(ActionMapping mapping, CompareInfoForm theForm,
			HttpServletRequest request, HttpServletResponse response) throws Exception
	{
			if (log.isDebugEnabled())
				log.debug("Entering 'addResource' method");
			initForm(theForm,request,ADD);
			theForm.setAction(SAVE);
			return mapping.findForward(ADD);
	}
	public ActionForward overViewCompareInfo(ActionMapping mapping, CompareInfoForm theForm,
			HttpServletRequest request, HttpServletResponse response) throws Exception
	{
			if (log.isDebugEnabled())
				log.debug("Entering 'overViewCompareInfo' method");
			String id=theForm.getRecord().get("id");
			if(id!=null && id.length()>0)
				theForm.setAction(EDIT);
			else
				theForm.setAction(ADD);
			initForm(theForm,request,ADD);
			//theForm.setAction(SAVE);
			return mapping.findForward("CONFIG");
	}
	public ActionForward editCompareInfo(ActionMapping mapping, CompareInfoForm theForm,
			HttpServletRequest request, HttpServletResponse response) throws Exception
		{
			if (log.isDebugEnabled())
				log.debug("Entering 'editResource' method");
			String id = theForm.getRecord().get("id");
			CompareInfoService compareInfoService = (CompareInfoService) getBean("compareInfoService");
			CompareInfo compareInfo = (CompareInfo) compareInfoService.findCompareInfo(new Integer(id));
			ConvertUtil.objectToMap(theForm.getRecord(), compareInfo);
			request.setAttribute("tableid", id);
			initForm(theForm,request,EDIT);
			theForm.setAction(UPDATE);
			return mapping.findForward(EDIT);
		}
	public ActionForward saveCompareInfo(ActionMapping mapping, CompareInfoForm theForm,
			HttpServletRequest request, HttpServletResponse response) throws Exception
		{
			if (log.isDebugEnabled())
				log.debug("Entering 'saveCompareInfo' method");
			ActionForward forward = null;

			try
			{
				initForm(theForm, request, SAVE);
				CompareInfo compareInfo=new CompareInfo();
				ConvertUtil.mapToObject(compareInfo, theForm.getRecord());
				Session session=(Session)request.getSession().getAttribute(Const.SESSION);
				compareInfo.setUserId(Integer.valueOf(session.getUserId()));
				compareInfo.setComapreStatus(Const.TAG_DISABLE);
				CompareInfoService compareInfoService = (CompareInfoService) getBean("compareInfoService");
				compareInfo.setCreateDate(new Date());
				compareInfo.setCompareType(CompareInfo.AUTO);
//				if (compareInfo.getCompareType().equals(CompareInfo.MANUAL))
//				{
//					compareInfo.setRunCycle("");
//					compareInfo.setComapreStatus("");
//				}
				compareInfoService.createCompareInfo(compareInfo);
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
		public ActionForward updateCompareInfo(ActionMapping mapping, CompareInfoForm theForm,
				HttpServletRequest request, HttpServletResponse response) throws Exception
			{
				if (log.isDebugEnabled())
					log.debug("Entering 'updateResource' method");

				ActionForward forward = null;
				initForm(theForm, request, SEARCH);
				try
				{
					CompareInfo compareInfo=new CompareInfo();
					ConvertUtil.mapToObject(compareInfo, theForm.getRecord());
					compareInfo.setModifyDate(new Date());
					CompareInfoService compareInfoService = (CompareInfoService) getBean("compareInfoService");
//					if (compareInfo.getCompareType().equals(CompareInfo.MANUAL))
//					{
//						compareInfo.setRunCycle("");
//						compareInfo.setComapreStatus("");
//					}
					if (compareInfo.getRunCycle().equals(Const.RUN_CYCLE_ONCE))
					{
						compareInfo.setRunStatus("");
					}
					compareInfoService.updateCompareInfo(compareInfo);
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
		public ActionForward searchCompareInfo(ActionMapping mapping, CompareInfoForm theForm,
				HttpServletRequest request, HttpServletResponse response) throws Exception
			{
				if (log.isDebugEnabled())
					log.debug("Entering 'searchResource' method");
				ActionForward forward = null;
				try
				{
					CompareInfoService compareInfoService = (CompareInfoService) getBean("compareInfoService");
					PageQuery pageQuery = theForm.getQuery();
					pageQuery.setSelectParamId("GET_COMPAREINFO_PAGE");
					Session session=(Session)request.getSession().getAttribute(Const.SESSION);
					
					String queryString = getQueryString(pageQuery,session.getUserId());
					pageQuery.getParameters().put("queryString", queryString);
					// 执行查询并将结果保存到pagequery中，结果集是一个list<map>对象，用map将每个字段名作key，值做value保存起来
					compareInfoService.queryCompareInfo(pageQuery);
					setPage(pageQuery);
					initForm(theForm, request, LIST);
					List<Map<String, String>> list = pageQuery.getRecordSet();
					for (int i = 0; i < list.size(); i++) {
						Map<String, String> map = list.get(i);
						String us = (String) map.get("STATUS");
						if (us == null || us.length()==0) map.put("STATUS", "");
						else map.put("STATUS", findCodeName(theForm, "COMPARE_STATUS", us.trim()));
						String type = (String) map.get("COMPARETYPE");
						if (type == null) map.put("COMPARETYPE", "");
						else map.put("COMPARETYPE", findCodeName(theForm, "COMAPRE_TYPE", type.trim()));
						String cyc = (String) map.get("RUNCYCLE");
						if (cyc == null) map.put("RUNCYCLE", "");
						else map.put("RUNCYCLE", findCodeName(theForm, "COMPARE_CYCLE", cyc.trim()));
					}
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
		public ActionForward deleteCompareInfo(ActionMapping mapping, CompareInfoForm theForm,
				HttpServletRequest request, HttpServletResponse response) throws Exception
			{
				if (log.isDebugEnabled())
					log.debug("Entering 'deleteResource' method");
				try
				{
					CompareInfoService compareInfoService = (CompareInfoService) getBean("compareInfoService");
					String del_ids = request.getParameter("ids");
					if (del_ids != null && del_ids.trim().length() > 0)
					{
						compareInfoService.removeCompareInfos(parseId(del_ids.split(";")));
					}
				}
				catch (Exception e)
				{
					e.printStackTrace();
					addMessage(theForm, "编辑操作失败");
				}
				return searchCompareInfo(mapping, theForm, request, response);
		}
		public ActionForward configCompareInfo(ActionMapping mapping, CompareInfoForm theForm,
				HttpServletRequest request, HttpServletResponse response) throws Exception
		{
				if (log.isDebugEnabled())
					log.debug("Entering 'configCompareInfo' method");
				String id=theForm.getRecord().get("id");
				theForm.getRecord().put("compId", id);
				request.setAttribute("compId", id);
				theForm.setAction("BASE");
				return mapping.findForward("CONFIG");
		}
		public ActionForward startCompareInfo(ActionMapping mapping, CompareInfoForm theForm,
				HttpServletRequest request, HttpServletResponse response) throws Exception{
			if (log.isDebugEnabled())
				log.debug("Entering 'startCompareInfo' method");
			try
			{
				CompareInfoService compareInfoService = (CompareInfoService) getBean("compareInfoService");
				String ids = request.getParameter("ids");
				if (ids!=null && ids.trim().length() > 0)
				{
					String[] idArray = ids.split(";");
					for (String id : idArray){
						CompareInfo compare = compareInfoService.findCompareInfo(Integer.parseInt(id));
						if (compare.getCompareType().equals(CompareInfo.MANUAL))
						{
							log.info(new java.util.Date());
							log.info("Start to excecute:" + compare);
							CompareTaskExecutor compareTaskExecutor = (CompareTaskExecutor) getBean("compareTaskExecutor");
							compareTaskExecutor.execCompare(compare,null);
						}
					}
				}
				if (ids != null && ids.trim().length() > 0)
				{
					compareInfoService.startCompareInfo(parseId(ids.split(";")));
				}
			}
			catch (Exception e)
			{
				e.printStackTrace();
				addMessage(theForm, "启动对比操作失败");
			}
			return searchCompareInfo(mapping, theForm, request, response);
		}
		public ActionForward stopCompareInfo(ActionMapping mapping, CompareInfoForm theForm,
				HttpServletRequest request, HttpServletResponse response) throws Exception{
			if (log.isDebugEnabled())
				log.debug("Entering 'startCompare' method");
			try
			{
				CompareInfoService compareInfoService = (CompareInfoService) getBean("compareInfoService");
				String del_ids = request.getParameter("ids");
				if (del_ids != null && del_ids.trim().length() > 0)
				{
					compareInfoService.stopCompareInfo(parseId(del_ids.split(";")));
				}
			}
			catch (Exception e)
			{
				e.printStackTrace();
				addMessage(theForm, "取消对比操作失败");
			}
			return searchCompareInfo(mapping, theForm, request, response);
		}
		
		
		private String getQueryString(PageQuery pageQuery,String userId)
		{
			StringBuffer buffer = new StringBuffer();
			String order = pageQuery.getOrder();
			buffer.append(" and t."+CompareInfo.COL_USER_ID+"="+userId);
			if (order==null)
			{
				order = "";
			}
			String dire = pageQuery.getOrderDirection();
			if (dire==null)
			{
				dire = "";
			}
			if ("".equals(order)&&"".equals(dire))
			{
				pageQuery.setOrder(CompareInfo.COL_COMPARE_NAME);
				pageQuery.setOrderDirection(PageQuery.ASC);
			}
			
			String cnName = pageQuery.getParameters().get(CompareInfo.PROP_COMPARE_NAME);
			
			if (cnName!=null&&!"".equals(cnName))
			{
				if (cnName.indexOf("%")>-1)
				{
					buffer.append(" and t."+CompareInfo.COL_COMPARE_NAME+" like '"+cnName+"'");
				}else {
					buffer.append(" and t."+CompareInfo.COL_COMPARE_NAME+" like '%"+cnName+"%'");
				}	
			}
			return buffer.toString();
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

		private void initForm(CompareInfoForm theForm,HttpServletRequest request,String action)
		{
			setCode(theForm, "COMPARE_STATUS,COMAPRE_TYPE,COMPARE_CYCLE");
			
		}

}
