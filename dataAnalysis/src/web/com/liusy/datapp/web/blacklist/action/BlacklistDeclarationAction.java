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
import com.liusy.datapp.model.blacklist.BlacklistDeclaration;
import com.liusy.datapp.model.compare.CompareInfo;
import com.liusy.datapp.model.datastandard.StandardCategory;
import com.liusy.datapp.model.datastandard.StandardIndicator;
import com.liusy.datapp.model.resource.ResourceTable;
import com.liusy.datapp.model.system.org.SysUser;
import com.liusy.datapp.service.blacklist.BlacklistDeclarationService;
import com.liusy.datapp.service.compare.CompareInfoService;
import com.liusy.datapp.service.datastandard.StandardCategoryService;
import com.liusy.datapp.service.datastandard.StandardIndicatorService;
import com.liusy.datapp.service.resource.ResourceColumnService;
import com.liusy.datapp.service.resource.ResourceTableService;
import com.liusy.datapp.service.system.org.SysUserService;
import com.liusy.datapp.service.thread.compare.CompareTaskExecutor;
import com.liusy.datapp.web.BaseAction;
import com.liusy.datapp.web.blacklist.form.BlacklistDeclarationForm;
import com.liusy.datapp.web.compare.form.CompareInfoForm;
import com.liusy.datapp.web.query.form.QuerySubjectForm;

public class BlacklistDeclarationAction extends BaseAction{

	@Override
	public ActionForward doExecute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		String action = request.getParameter("action");
		if (action == null) action = LIST;
		if (log.isDebugEnabled()) log.debug("action:" + action);
		ActionForward forward;
		BlacklistDeclarationForm theForm=(BlacklistDeclarationForm)form;
		try {
			if (LIST.equalsIgnoreCase(action)) forward = searchBlacklistDeclaration(mapping, theForm, request, response);
			else if(ADD.equalsIgnoreCase(action)) forward = addBlacklistDeclaration(mapping, theForm, request, response);
			else if(SAVE.equalsIgnoreCase(action)) forward = saveBlacklistDeclaration(mapping, theForm, request, response);
			else if(EDIT.equalsIgnoreCase(action)) forward = editBlacklistDeclaration(mapping, theForm, request, response);
			else if(UPDATE.equalsIgnoreCase(action)) forward = updateBlacklistDeclaration(mapping, theForm, request, response);
			else if (DELETE.equalsIgnoreCase(action)) forward = deleteBlacklistDeclaration(mapping, theForm, request, response);// 删除
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
	public ActionForward addBlacklistDeclaration(ActionMapping mapping, BlacklistDeclarationForm theForm,
			HttpServletRequest request, HttpServletResponse response) throws Exception
	{
			if (log.isDebugEnabled())
				log.debug("Entering 'addBlacklistDeclaration' method");
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
	
	public ActionForward editBlacklistDeclaration(ActionMapping mapping, BlacklistDeclarationForm theForm,
			HttpServletRequest request, HttpServletResponse response) throws Exception
		{
			if (log.isDebugEnabled())
				log.debug("Entering 'editBlacklistDeclaration' method");
			String id = theForm.getRecord().get("id");
			BlacklistDeclarationService blacklistDeclarationService = (BlacklistDeclarationService) getBean("blacklistDeclarationService");
			BlacklistDeclaration blacklistDeclaration = (BlacklistDeclaration) blacklistDeclarationService.findBlacklistDeclaration(new Integer(id));
			ConvertUtil.objectToMap(theForm.getRecord(), blacklistDeclaration);
			initForm(theForm,request,EDIT);
			theForm.setAction(UPDATE);
			return mapping.findForward(EDIT);
		}
	
	public ActionForward saveBlacklistDeclaration(ActionMapping mapping, BlacklistDeclarationForm theForm,
			HttpServletRequest request, HttpServletResponse response) throws Exception
		{
			if (log.isDebugEnabled())
				log.debug("Entering 'saveBlacklistDeclaration' method");
			ActionForward forward = null;

			try
			{
				initForm(theForm, request, SAVE);
				BlacklistDeclaration blacklistDeclaration = new BlacklistDeclaration();
				ConvertUtil.mapToObject(blacklistDeclaration, theForm.getRecord());
				BlacklistDeclarationService blacklistDeclarationService = (BlacklistDeclarationService) getBean("blacklistDeclarationService");
				blacklistDeclarationService.createBlacklistDeclaration(blacklistDeclaration);
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
	
		public ActionForward updateBlacklistDeclaration(ActionMapping mapping, BlacklistDeclarationForm theForm,
				HttpServletRequest request, HttpServletResponse response) throws Exception
			{
				if (log.isDebugEnabled())
					log.debug("Entering 'updateBlacklistDeclaration' method");

				ActionForward forward = null;
				initForm(theForm, request, SEARCH);
				try
				{
					BlacklistDeclarationService blacklistDeclarationService = (BlacklistDeclarationService) getBean("blacklistDeclarationService");
					BlacklistDeclaration blacklistDeclaration = blacklistDeclarationService.findBlacklistDeclaration(new Integer(theForm.getRecord().get("id")));
					ConvertUtil.mapToObject(blacklistDeclaration, theForm.getRecord());
					blacklistDeclarationService.updateBlacklistDeclaration(blacklistDeclaration);
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
	
		public ActionForward searchBlacklistDeclaration(ActionMapping mapping, BlacklistDeclarationForm theForm,
				HttpServletRequest request, HttpServletResponse response) throws Exception
			{
				if (log.isDebugEnabled())
					log.debug("Entering 'searchBlacklistDeclaration' method");
				ActionForward forward = null;
				try
				{
					BlacklistDeclarationService blacklistDeclarationService = (BlacklistDeclarationService) getBean("blacklistDeclarationService");
					PageQuery pageQuery = theForm.getQuery();
					pageQuery.setSelectParamId("GET_BLACKLIST_DECLARRATION_PAGE");
					Session session=(Session)request.getSession().getAttribute(Const.SESSION);
					
					//String queryString = getQueryString(pageQuery,session.getUserId());
					//pageQuery.getParameters().put("queryString", queryString);
					
					blacklistDeclarationService.queryBlacklistDeclaration(pageQuery);
					setPage(pageQuery);
					initForm(theForm, request, LIST);
//					List<Map<String, String>> list = pageQuery.getRecordSet();
//					for (int i = 0; i < list.size(); i++) {
//						Map<String, String> map = list.get(i);
//						String us = (String) map.get("STATUS");
//						if (us == null || us.length()==0) map.put("STATUS", "");
//						else map.put("STATUS", findCodeName(theForm, "COMPARE_STATUS", us.trim()));
//						String type = (String) map.get("COMPARETYPE");
//						if (type == null) map.put("COMPARETYPE", "");
//						else map.put("COMPARETYPE", findCodeName(theForm, "COMAPRE_TYPE", type.trim()));
//						String cyc = (String) map.get("RUNCYCLE");
//						if (cyc == null) map.put("RUNCYCLE", "");
//						else map.put("RUNCYCLE", findCodeName(theForm, "COMPARE_CYCLE", cyc.trim()));
//					}
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
		
		public ActionForward deleteBlacklistDeclaration(ActionMapping mapping, BlacklistDeclarationForm theForm,
				HttpServletRequest request, HttpServletResponse response) throws Exception
			{
				if (log.isDebugEnabled())
					log.debug("Entering 'deleteBlacklistDeclaration' method");
				try
				{
					BlacklistDeclarationService blacklistDeclarationService = (BlacklistDeclarationService) getBean("blacklistDeclarationService");
					String del_ids = request.getParameter("ids");
					if (del_ids != null && del_ids.trim().length() > 0)
					{
						blacklistDeclarationService.removeBlacklistDeclarations(parseId(del_ids.split(";")));
					}
				}
				catch (Exception e)
				{
					e.printStackTrace();
					addMessage(theForm, "删除操作失败");
				}
				return searchBlacklistDeclaration(mapping, theForm, request, response);
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

		private void initForm(BlacklistDeclarationForm theForm,HttpServletRequest request,String action)
		{
			SysUserService sysUserService = (SysUserService) getBean("sysUserService");
			ResourceColumnService resourceColumnService = (ResourceColumnService) getBean("resourceColumnService");
			List<SysUser> users = sysUserService.findAllSysUser();
			//StandardIndicatorService standardIndicatorService = (StandardIndicatorService) getBean("standardIndicatorService");
			//Collection<StandardIndicator>  inds = standardIndicatorService.findAllStandardIndicators();
			List<StandardIndicator> indicators = resourceColumnService.getIndicators(null);
			setCode(theForm, "userIds", users, SysUser.PROP_USER_NAME, SysUser.PROP_ID);
			setCode(theForm, "indicator", indicators, StandardIndicator.PROP_CN_NAME,StandardIndicator.PROP_INDICATOR_CODE);
			
			if (EDIT.equals(action))
			{
				String remark = theForm.getRecord().get("remark");
				if (remark != null) {
					remark = remark.replace(" ", "&nbsp;");
					//remark = remark.replace("\r", "<br>");
					theForm.getRecord().put("remark", remark);
				}
			}
			
		}

}
