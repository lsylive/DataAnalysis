package com.liusy.datapp.web.blacklist.action;


import java.io.Serializable;
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
import com.liusy.dataapplatform.base.util.StringUtil;
import com.liusy.datapp.model.blacklist.BlacklistAlarm;
import com.liusy.datapp.model.blacklist.BlacklistDeclaration;
import com.liusy.datapp.model.compare.CompareInfo;
import com.liusy.datapp.model.datastandard.StandardCategory;
import com.liusy.datapp.model.datastandard.StandardIndicator;
import com.liusy.datapp.model.resource.ResourceTable;
import com.liusy.datapp.model.system.org.SysUser;
import com.liusy.datapp.service.blacklist.BlacklistAlarmService;
import com.liusy.datapp.service.compare.CompareInfoService;
import com.liusy.datapp.service.datastandard.StandardCategoryService;
import com.liusy.datapp.service.datastandard.StandardIndicatorService;
import com.liusy.datapp.service.resource.ResourceColumnService;
import com.liusy.datapp.service.resource.ResourceTableService;
import com.liusy.datapp.service.system.org.SysUserService;
import com.liusy.datapp.service.thread.compare.CompareTaskExecutor;
import com.liusy.datapp.web.BaseAction;
import com.liusy.datapp.web.blacklist.form.BlacklistAlarmForm;
import com.liusy.datapp.web.compare.form.CompareInfoForm;
import com.liusy.datapp.web.query.form.QuerySubjectForm;

public class BlacklistAlarmAction extends BaseAction{

	@Override
	public ActionForward doExecute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		String action = request.getParameter("action");
		if (action == null) action = LIST;
		if (log.isDebugEnabled()) log.debug("action:" + action);
		ActionForward forward;
		BlacklistAlarmForm theForm=(BlacklistAlarmForm)form;
		try {
			if (LIST.equalsIgnoreCase(action)) forward = searchBlacklistAlarm(mapping, theForm, request, response);
			else if(ADD.equalsIgnoreCase(action)) forward = addBlacklistAlarm(mapping, theForm, request, response);
			else if(SAVE.equalsIgnoreCase(action)) forward = saveBlacklistAlarm(mapping, theForm, request, response);
			else if(EDIT.equalsIgnoreCase(action)) forward = editBlacklistAlarm(mapping, theForm, request, response);
			else if(UPDATE.equalsIgnoreCase(action)) forward = updateBlacklistAlarm(mapping, theForm, request, response);
			else if (DELETE.equalsIgnoreCase(action)) forward = deleteBlacklistAlarm(mapping, theForm, request, response);// 删除
//		else if ("ADDTABLE".equalsIgnoreCase(action)) forward = getSubjectTableTree(mapping, theForm, request, response); // 打开增加页面
//			else if("CONFIG".equalsIgnoreCase(action)) forward=configCompareInfo(mapping, theForm, request, response);
//			else if("STARTCOMPARE".equalsIgnoreCase(action)) forward=startCompareInfo(mapping, theForm, request, response);
//			else if("STOPCOMPARE".equalsIgnoreCase(action)) forward=stopCompareInfo(mapping, theForm, request, response);
//			else if("OVERVIEW".equalsIgnoreCase(action)) forward=overViewCompareInfo(mapping, theForm, request, response);
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
	public ActionForward addBlacklistAlarm(ActionMapping mapping, BlacklistAlarmForm theForm,
			HttpServletRequest request, HttpServletResponse response) throws Exception
	{
			if (log.isDebugEnabled())
				log.debug("Entering 'addBlacklistAlarm' method");
			initForm(theForm,request,ADD);
			theForm.setAction(SAVE);
			return mapping.findForward(ADD);
	}
	
//	public ActionForward overViewCompareInfo(ActionMapping mapping, CompareInfoForm theForm,
//			HttpServletRequest request, HttpServletResponse response) throws Exception
//	{
//			if (log.isDebugEnabled())
//				log.debug("Entering 'overViewCompareInfo' method");
//			String id=theForm.getRecord().get("id");
//			if(id!=null && id.length()>0)
//				theForm.setAction(EDIT);
//			else
//				theForm.setAction(ADD);
//			initForm(theForm,request,ADD);
//			//theForm.setAction(SAVE);
//			return mapping.findForward("CONFIG");
//	}
	
	public ActionForward editBlacklistAlarm(ActionMapping mapping, BlacklistAlarmForm theForm,
			HttpServletRequest request, HttpServletResponse response) throws Exception
		{
			if (log.isDebugEnabled())
				log.debug("Entering 'editBlacklistAlarm' method");
			String id = theForm.getRecord().get("id");
			BlacklistAlarmService blacklistAlarmService = (BlacklistAlarmService) getBean("blacklistAlarmService");
			BlacklistAlarm blacklistAlarm = (BlacklistAlarm) blacklistAlarmService.findBlacklistAlarm(new Integer(id));
			ConvertUtil.objectToMap(theForm.getRecord(), blacklistAlarm);
			initForm(theForm,request,EDIT);
			theForm.setAction(UPDATE);
			return mapping.findForward(EDIT);
		}
	
	public ActionForward saveBlacklistAlarm(ActionMapping mapping, BlacklistAlarmForm theForm,
			HttpServletRequest request, HttpServletResponse response) throws Exception
		{
			if (log.isDebugEnabled())
				log.debug("Entering 'saveBlacklistAlarm' method");
			ActionForward forward = null;

			try
			{
				initForm(theForm, request, SAVE);
				BlacklistAlarm blacklistAlarm = new BlacklistAlarm();
				ConvertUtil.mapToObject(blacklistAlarm, theForm.getRecord());
				BlacklistAlarmService blacklistAlarmService = (BlacklistAlarmService) getBean("blacklistAlarmService");
				blacklistAlarmService.createBlacklistAlarm(blacklistAlarm);
				forward = returnForward(mapping, request, RETURN_NORMAL);
				
			}
			catch (ServiceException e) {
				addMessage(theForm, e.getMessage());
				forward = mapping.findForward(ADD);
			}
			catch (Exception e)
			{
				e.printStackTrace();
				addMessage(theForm, "新建操作失败!");
				forward = mapping.findForward(ADD);
			}
			return forward;
		}
	
		public ActionForward updateBlacklistAlarm(ActionMapping mapping, BlacklistAlarmForm theForm,
				HttpServletRequest request, HttpServletResponse response) throws Exception
			{
				if (log.isDebugEnabled())
					log.debug("Entering 'updateBlacklistAlarm' method");

				ActionForward forward = null;
				initForm(theForm, request, SEARCH);
				try
				{
					BlacklistAlarmService blacklistAlarmService = (BlacklistAlarmService) getBean("blacklistAlarmService");
					BlacklistAlarm blacklistAlarm = blacklistAlarmService.findBlacklistAlarm(new Integer(theForm.getRecord().get("id")));
					ConvertUtil.mapToObject(blacklistAlarm, theForm.getRecord());
					blacklistAlarmService.updateBlacklistAlarm(blacklistAlarm);
					request.setAttribute("msg", "修改成功！");
					forward=returnForward(mapping, request, RETURN_NORMAL);
				}
				catch (ServiceException e) {
					e.printStackTrace();
					addMessage(theForm, e.getMessage());
					forward = mapping.findForward(EDIT);
				}
				catch (Exception e)
				{
					e.printStackTrace();
					addMessage(theForm, "修改操作失败");
					forward = mapping.findForward(EDIT);
				}
				return forward;
		}
	
		public ActionForward searchBlacklistAlarm(ActionMapping mapping, BlacklistAlarmForm theForm,
				HttpServletRequest request, HttpServletResponse response) throws Exception
			{
				if (log.isDebugEnabled())
					log.debug("Entering 'searchBlacklistAlarm' method");
				ActionForward forward = null;
				try
				{
					BlacklistAlarmService blacklistAlarmService = (BlacklistAlarmService) getBean("blacklistAlarmService");
					PageQuery pageQuery = theForm.getQuery();
					pageQuery.setSelectParamId("GET_BLACKLIST_ALARM_PAGE");
					Session session=(Session)request.getSession().getAttribute(Const.SESSION);
					
					String queryString = getQueryString(pageQuery,session.getUserId());
					pageQuery.getParameters().put("queryString", queryString);
					
					blacklistAlarmService.queryBlacklistAlarm(pageQuery);
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
		
		public ActionForward deleteBlacklistAlarm(ActionMapping mapping, BlacklistAlarmForm theForm,
				HttpServletRequest request, HttpServletResponse response) throws Exception
			{
				if (log.isDebugEnabled())
					log.debug("Entering 'deleteBlacklistAlarm' method");
				try
				{
					BlacklistAlarmService blacklistAlarmService = (BlacklistAlarmService) getBean("blacklistAlarmService");
					String del_ids = request.getParameter("ids");
					if (del_ids != null && del_ids.trim().length() > 0)
					{
						blacklistAlarmService.removeBlacklistAlarms(parseId(del_ids.split(";")));
					}
				}
				catch (Exception e)
				{
					e.printStackTrace();
					addMessage(theForm, "删除操作失败");
				}
				return searchBlacklistAlarm(mapping, theForm, request, response);
		}
		
//		public ActionForward configCompareInfo(ActionMapping mapping, CompareInfoForm theForm,
//				HttpServletRequest request, HttpServletResponse response) throws Exception
//		{
//				if (log.isDebugEnabled())
//					log.debug("Entering 'configCompareInfo' method");
//				String id=theForm.getRecord().get("id");
//				theForm.getRecord().put("compId", id);
//				request.setAttribute("compId", id);
//				theForm.setAction("BASE");
//				return mapping.findForward("CONFIG");
//		}
		
//		public ActionForward startCompareInfo(ActionMapping mapping, CompareInfoForm theForm,
//				HttpServletRequest request, HttpServletResponse response) throws Exception{
//			if (log.isDebugEnabled())
//				log.debug("Entering 'startCompareInfo' method");
//			try
//			{
//				CompareInfoService compareInfoService = (CompareInfoService) getBean("compareInfoService");
//				String ids = request.getParameter("ids");
//				if (ids!=null && ids.trim().length() > 0)
//				{
//					String[] idArray = ids.split(";");
//					for (String id : idArray){
//						CompareInfo compare = compareInfoService.findCompareInfo(Integer.parseInt(id));
//						if (compare.getCompareType().equals(CompareInfo.MANUAL))
//						{
//							log.info(new java.util.Date());
//							log.info("Start to excecute:" + compare);
//							CompareTaskExecutor compareTaskExecutor = (CompareTaskExecutor) getBean("compareTaskExecutor");
//							compareTaskExecutor.execCompare(compare,null);
//						}
//					}
//				}
//				if (ids != null && ids.trim().length() > 0)
//				{
//					compareInfoService.startCompareInfo(parseId(ids.split(";")));
//				}
//			}
//			catch (Exception e)
//			{
//				e.printStackTrace();
//				addMessage(theForm, "启动对比操作失败");
//			}
//			return searchCompareInfo(mapping, theForm, request, response);
//		}
		
//		public ActionForward stopCompareInfo(ActionMapping mapping, CompareInfoForm theForm,
//				HttpServletRequest request, HttpServletResponse response) throws Exception{
//			if (log.isDebugEnabled())
//				log.debug("Entering 'startCompare' method");
//			try
//			{
//				CompareInfoService compareInfoService = (CompareInfoService) getBean("compareInfoService");
//				String del_ids = request.getParameter("ids");
//				if (del_ids != null && del_ids.trim().length() > 0)
//				{
//					compareInfoService.stopCompareInfo(parseId(del_ids.split(";")));
//				}
//			}
//			catch (Exception e)
//			{
//				e.printStackTrace();
//				addMessage(theForm, "取消对比操作失败");
//			}
//			return searchCompareInfo(mapping, theForm, request, response);
//		}
		
		
		private String getQueryString(PageQuery pageQuery,String userId)
		{
			StringBuffer buffer = new StringBuffer();
			String order = pageQuery.getOrder();
			buffer.append(" and t2."+BlacklistDeclaration.COL_USER_ID+"="+userId);
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
				pageQuery.setOrder(BlacklistDeclaration.COL_DEC_TIME);
				pageQuery.setOrderDirection(PageQuery.DESC);
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

		private void initForm(BlacklistAlarmForm theForm,HttpServletRequest request,String action)
		{

			
		}

}
