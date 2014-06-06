package com.liusy.datapp.web.system.config.action;

import java.util.Map;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.liusy.core.util.Const;
import com.liusy.core.util.Session;
import com.liusy.dataapplatform.base.exception.WebException;
import com.liusy.dataapplatform.base.exception.ServiceException;
import com.liusy.dataapplatform.base.util.PageQuery;
import com.liusy.datapp.model.system.config.SysCity;
import com.liusy.datapp.service.system.config.SysCityService;
import com.liusy.datapp.web.BaseAction;
import com.liusy.datapp.web.system.config.form.SysCityForm;

import com.liusy.dataapplatform.base.util.ConvertUtil;

public class SysCityAction extends BaseAction {

	@Override
	public ActionForward doExecute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// TODO Auto-generated method stub
		String action = request.getParameter("action");
		if (action == null) action = LIST;
		if (log.isDebugEnabled()) log.debug("action:" + action);
		ActionForward forward;
		SysCityForm sysCityForm = (SysCityForm) form;

		try {
			if (LIST.equalsIgnoreCase(action)) forward = searchCity(mapping, sysCityForm, request, response);
			else if (ADD.equalsIgnoreCase(action)) forward = addCity(mapping, sysCityForm, request, response);
			else if (SAVE.equalsIgnoreCase(action)) forward = saveCity(mapping, sysCityForm, request, response);
			else if (EDIT.equalsIgnoreCase(action)) forward = editCity(mapping, sysCityForm, request, response);
			else if (UPDATE.equalsIgnoreCase(action)) forward = updateCity(mapping, sysCityForm, request, response);
			else if (DELETE.equalsIgnoreCase(action)) forward = deleteCity(mapping, sysCityForm, request, response);
			else {
				request.setAttribute("err", new WebException("找不到该action方法：" + action));
				forward = mapping.findForward(ERROR);// 找不到合适的action
			}
		}
		catch (Exception e) {
			request.setAttribute("err", e);
			forward = mapping.findForward(ERROR);
		}
		return forward;
	}

	private ActionForward searchCity(ActionMapping mapping, SysCityForm sysCityForm,
			HttpServletRequest request, HttpServletResponse response) 
			throws Exception {
		// TODO Auto-generated method stub
		if (log.isDebugEnabled()) log.debug("Entering 'searchCity' method");

		try {
			SysCityService sysCityService = (SysCityService)getBean("sysCityService");
			PageQuery pageQuery = sysCityForm.getQuery();
			pageQuery.setSelectParamId("GET_SYSCITY_PAGE");
			String queryString = getQueryString(pageQuery);
			pageQuery.getParameters().put("queryString", queryString);
			if (pageQuery.getOrder() == null || "".equalsIgnoreCase(pageQuery.getOrder())) {
				pageQuery.setOrder("ID");
				pageQuery.setOrderDirection(PageQuery.ASC);
			}
			sysCityService.querySysCity(pageQuery);
			initForm(sysCityForm, LIST);
			setPage(sysCityForm.getQuery());
			return mapping.findForward(LIST);
		}
		catch (ServiceException e) {
			request.setAttribute("errMsg", e.getMessage());
			return mapping.findForward(ERROR);
		}
		catch (Exception e){
			e.printStackTrace();
			request.setAttribute("err", e);
			return mapping.findForward(ERROR);
		}
	}
	
	private ActionForward addCity(ActionMapping mapping, SysCityForm sysCityForm,
			HttpServletRequest request, HttpServletResponse response) 
			throws Exception {
//		 TODO Auto-generated method stub
		if (log.isDebugEnabled()) log.debug("Entering 'addCity' method");

		try {
			return mapping.findForward(ADD);
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("err", e);
			return mapping.findForward(ERROR);
		}
	}
	
	private ActionForward saveCity(ActionMapping mapping, SysCityForm sysCityForm,
			HttpServletRequest request, HttpServletResponse response) 
			throws Exception {
		if (log.isDebugEnabled()) log.debug("Entering 'saveCity' method");
		
		ActionForward forward = null;
		try {
			SysCity sysCity = new SysCity();
			ConvertUtil.mapToObject(sysCity, sysCityForm.getRecord());
			Session currentUser = (Session)request.getSession().getAttribute(Const.SESSION);
			if (currentUser != null)
				sysCity.setCreateBy(currentUser.getUserId());
			sysCity.setCreateDate(new Date());
			SysCityService sysCityService = (SysCityService)getBean("sysCityService");
			sysCityService.createSysCity(sysCity);
			forward = returnForward(mapping, request, RETURN_NORMAL);
		}catch (ServiceException e) {
			addMessage(sysCityForm, e.getMessage());
			forward = mapping.findForward(ADD);
		} 
		catch(Exception e){
			e.printStackTrace();
			addMessage(sysCityForm, "新增操作失败");
			forward = mapping.findForward(ADD);
		}
		return forward;
	}
	
	private ActionForward editCity(ActionMapping mapping, SysCityForm sysCityForm,
			HttpServletRequest request, HttpServletResponse response) 
			throws Exception {

		if (log.isDebugEnabled()) log.debug("Entering 'editCity' method");

		String id = request.getParameter("id");
		try {
			SysCityService sysCityService = (SysCityService)getBean("sysCityService");
			SysCity sysCity = (SysCity)sysCityService.findSysCity(new Integer(id));
			ConvertUtil.objectToMap(sysCityForm.getRecord(), sysCity);
			return mapping.findForward(EDIT);
		}
		catch(ServiceException e){
			request.setAttribute("errMsg", e.getMessage());
			return mapping.findForward(ERROR);
		}
		catch(Exception e){
			e.printStackTrace();
			request.setAttribute("err", e);
			return mapping.findForward(ERROR);
		}
	}
	
	private ActionForward updateCity(ActionMapping mapping, SysCityForm sysCityForm,
			HttpServletRequest request, HttpServletResponse response) 
			throws Exception {
//		 TODO Auto-generated method stub
		if (log.isDebugEnabled()) log.debug("Entering 'updateCity' method");

		try {
			SysCityService sysCityService = (SysCityService) getBean("sysCityService");
			SysCity sysCity = sysCityService.findSysCity(new Integer(sysCityForm.getRecord().get("id")));
			if (sysCity != null) {
				ConvertUtil.mapToObject(sysCity, sysCityForm.getRecord());
				Session currentUser = (Session)request.getSession().getAttribute(Const.SESSION);
				if (currentUser != null)
					sysCity.setModifyBy(currentUser.getUserId());
				sysCity.setModifyDate(new Date());
				sysCityService.updateSysCity(sysCity);
				return returnForward(mapping, request, RETURN_NORMAL);
			}
			else {
				request.setAttribute("errMsg", "记录不存在");
				return mapping.findForward(ERROR);
			}
		}
		catch (ServiceException e) {
			addMessage(sysCityForm, e.getMessage());
			return mapping.findForward(EDIT);
		}
		catch (Exception e) {
			e.printStackTrace();
			addMessage(sysCityForm, "编辑操作失败");
			return mapping.findForward(EDIT);
		}
	}
	
	private ActionForward deleteCity(ActionMapping mapping, SysCityForm sysCityForm,
			HttpServletRequest request, HttpServletResponse response) 
			throws Exception {
//		 TODO Auto-generated method stub
		if (log.isDebugEnabled()) log.debug("Entering 'deleteCity' method");

		try {
			String del_ids = request.getParameter("ids");
			if (del_ids != null && del_ids.trim().length() > 0) {
				SysCityService sysCityService = (SysCityService)getBean("sysCityService");
				sysCityService.removeSysCitys(parseId(del_ids.split(";")));
			}
			else
				request.setAttribute("msg", "未选择待删除记录");
		}
		catch(Exception e) {
			e.printStackTrace();
			request.setAttribute("msg", "删除操作失败");
		}
		return searchCity(mapping, sysCityForm, request, response);
	}
	
	private String getQueryString(PageQuery pageQuery) {
		StringBuffer buffer = new StringBuffer();
		
		return buffer.toString();
	}
	
	private void initForm(SysCityForm sysCityForm, String action) {

	}
	
	private java.io.Serializable[] parseId(String[] ids) throws Exception{
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
