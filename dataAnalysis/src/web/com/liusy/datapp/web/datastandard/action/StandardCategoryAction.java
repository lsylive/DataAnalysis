package com.liusy.datapp.web.datastandard.action;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import com.liusy.dataapplatform.base.util.PageQuery;
import com.liusy.core.util.Const;
import com.liusy.core.util.Session;
import com.liusy.dataapplatform.base.exception.ServiceException;
import com.liusy.dataapplatform.base.exception.WebException;
import com.liusy.dataapplatform.base.util.ConvertUtil;
import com.liusy.datapp.web.BaseAction;
import com.liusy.datapp.web.datastandard.form.StandardCategoryForm;
import com.liusy.datapp.model.datastandard.StandardCategory;
import com.liusy.datapp.service.datastandard.StandardCategoryService;

public class StandardCategoryAction extends BaseAction{

	private static final String	GETTREE		= "GETTREE";

	@Override
	public ActionForward doExecute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String action = request.getParameter("action");
		if (action == null) action = LIST;
		if (log.isDebugEnabled()) log.debug("action:" + action);
		ActionForward forward = null;
		StandardCategoryForm standardCategoryForm = (StandardCategoryForm)form;

		try {
			if (LIST.equalsIgnoreCase(action)) forward = searchStandardCategory(mapping, standardCategoryForm, request, response);
			else if (ADD.equalsIgnoreCase(action)) forward = addStandardCategory(mapping, standardCategoryForm, request, response);
			else if (SAVE.equalsIgnoreCase(action)) forward = saveStandardCategory(mapping, standardCategoryForm, request, response);
			else if (EDIT.equalsIgnoreCase(action)) forward = editStandardCategory(mapping, standardCategoryForm, request, response);
			else if (UPDATE.equalsIgnoreCase(action)) forward = updateStandardCategory(mapping, standardCategoryForm, request, response);
			else if (DELETE.equalsIgnoreCase(action)) forward = deleteStandardCategory(mapping, standardCategoryForm, request, response);
			else if (GETTREE.equalsIgnoreCase(action)) forward = getTreeNode(mapping, standardCategoryForm, request, response);
			else {
				request.setAttribute("err", new WebException("找不到该action方法：" + action));
				forward = mapping.findForward(ERROR);// 找不到合适的action
			}
		}
		catch(Exception e) {
			e.printStackTrace();
			request.setAttribute("err", e);
			forward = mapping.findForward(ERROR);
		}
		return forward;
	}

	private ActionForward searchStandardCategory(ActionMapping mapping, StandardCategoryForm standardCategoryForm,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		if (log.isDebugEnabled()) log.debug("Entering 'searchStandardCategory' method");

		return mapping.findForward(LIST);
	}

	private ActionForward addStandardCategory(ActionMapping mapping, StandardCategoryForm standardCategoryForm,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		if (log.isDebugEnabled()) log.debug("Entering 'addStandardCategory' method");

		try {
			String pid = request.getParameter("pid");
			if (pid != null){
				if ("root".equalsIgnoreCase(pid)) {
					standardCategoryForm.setParentName("广东省数据分类");
					standardCategoryForm.getRecord().put("parentId", "0");
				}
				else {
					StandardCategoryService standardCategoryService = (StandardCategoryService)getBean("standardCategoryService");
					String pName;
					pName = standardCategoryService.findStandardCategory(new Integer(pid)).getName();
					standardCategoryForm.setParentName(pName);
					standardCategoryForm.getRecord().put(StandardCategory.PROP_PARENT_ID, pid);
				}
				initForm(standardCategoryForm, ADD);
				return mapping.findForward(ADD);
			}
			else
				return mapping.findForward(ERROR);
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("err", e);
			return mapping.findForward(ERROR);
		}
	}

	private ActionForward saveStandardCategory(ActionMapping mapping, StandardCategoryForm standardCategoryForm,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		if (log.isDebugEnabled()) log.debug("Entering 'saveStandardCategory' method");
		
		ActionForward forward = null;
		try {
			standardCategoryForm.getRecord().put(StandardCategory.PROP_CITY_CODE, getCityCode(request));
			StandardCategory standardCategory = new StandardCategory();
			ConvertUtil.mapToObject(standardCategory, standardCategoryForm.getRecord());
			Session currentUser = (Session)request.getSession().getAttribute(Const.SESSION);
			if (currentUser != null)
				standardCategory.setCreateBy(currentUser.getUserId());
			standardCategory.setCreateDate(new Date());
			StandardCategoryService standardCategoryService = (StandardCategoryService)getBean("standardCategoryService");
			standardCategoryService.createStandardCategory(standardCategory);
			forward = returnForward(mapping, request, RETURN_NORMAL);
		}catch (ServiceException e) {
			addMessage(standardCategoryForm, e.getMessage());
			initForm(standardCategoryForm, ADD);
			forward = mapping.findForward(ADD);
		}
		catch(Exception e){
			e.printStackTrace();
			addMessage(standardCategoryForm, "保存操作失败");
			initForm(standardCategoryForm, ADD);
			forward = mapping.findForward(ADD);
		}
		return forward;
	}

	private ActionForward editStandardCategory(ActionMapping mapping, StandardCategoryForm standardCategoryForm,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		if (log.isDebugEnabled()) log.debug("Entering 'editStandardCategory' method");

		ActionForward forward = null;
		String id = request.getParameter("id");
		try {
			StandardCategoryService standardCategoryService = (StandardCategoryService)getBean("standardCategoryService");
			StandardCategory standardCategory = (StandardCategory)standardCategoryService.findStandardCategory(new Integer(id));
			String cityCode = getCityCode(request);
			if (cityCode.equalsIgnoreCase(standardCategory.getCityCode())) {
				ConvertUtil.objectToMap(standardCategoryForm.getRecord(), standardCategory);
				initForm(standardCategoryForm, EDIT);
				forward = mapping.findForward(EDIT);
			}
			else {
				request.setAttribute("errMsg", "非本市分类，不能修改！");
				forward = mapping.findForward(ERROR);
			}
		}
		catch(ServiceException e){
			request.setAttribute("errMsg", e.getMessage());
			forward = mapping.findForward(ERROR);
		}
		catch(Exception e){
			e.printStackTrace();
			request.setAttribute("err", e);
			forward = mapping.findForward(ERROR);
		}
		return forward;
	}

	private ActionForward updateStandardCategory(ActionMapping mapping, StandardCategoryForm standardCategoryForm,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		if (log.isDebugEnabled()) log.debug("Entering 'updateStandardCategory' method");

		ActionForward forward = null;
		try {
			StandardCategoryService standardCategoryService = (StandardCategoryService) getBean("standardCategoryService");
			StandardCategory standardCategory = standardCategoryService.findStandardCategory(new Integer(standardCategoryForm.getRecord().get("id")));
			if (standardCategory != null) {
				ConvertUtil.mapToObject(standardCategory, standardCategoryForm.getRecord());
				Session currentUser = (Session)request.getSession().getAttribute(Const.SESSION);
				if (currentUser != null)
					standardCategory.setModifyBy(currentUser.getUserId());
				standardCategory.setModifyDate(new Date());
				standardCategoryService.updateStandardCategory(standardCategory);
				forward = returnForward(mapping, request, RETURN_NORMAL);
			}
			else {
				request.setAttribute("errMsg", "分类记录不存在");
				forward = mapping.findForward(ERROR);
			}
		}
		catch (ServiceException e) {
			addMessage(standardCategoryForm, e.getMessage());
			initForm(standardCategoryForm, EDIT);
			forward = mapping.findForward(EDIT);
		}
		catch(Exception e) {
			e.printStackTrace();
			addMessage(standardCategoryForm, "修改操作失败!");
			initForm(standardCategoryForm, EDIT);
			forward = mapping.findForward(EDIT);
		}
		return forward;
	}

	private ActionForward deleteStandardCategory(ActionMapping mapping, StandardCategoryForm standardCategoryForm,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// TODO Auto-generated method stub
		if (log.isDebugEnabled()) log.debug("Entering 'deleteStandardCategory' method");

		StringBuffer sb = new StringBuffer();
		try {
			StandardCategoryService standardCategoryService = (StandardCategoryService)getBean("standardCategoryService");
			String id = request.getParameter("id");
			if (id != null){
				StandardCategory standardCategory = standardCategoryService.findStandardCategory(new Integer(id));
				String cityCode = getCityCode(request);
				if (cityCode.equalsIgnoreCase(standardCategory.getCityCode())) {
					standardCategoryService.removeStandardCategory(new Integer(id));
					sb.append(RET_NORAML);
				}
				else
					sb.append(JsonErrorMessage(OPER_FAILED, "非本市分类，不能删除！"));
			}
			else
				sb.append(JsonErrorMessage(OPER_FAILED, "分类记录不存在！"));
		}
		catch(Exception e) {
			e.printStackTrace();
			sb.append(JsonErrorMessage(OPER_FAILED, "分类删除失败！"));
		}
		response.setContentType("ext/plain;charset=UTF-8");
		response.setHeader("Cache_Control", "no-cache");
		response.getWriter().write(sb.toString());
		response.getWriter().close();
		return null;
	}

	private ActionForward getTreeNode(ActionMapping mapping, StandardCategoryForm standardCategoryForm, 
			HttpServletRequest request, HttpServletResponse response) {
		if (log.isDebugEnabled()) log.debug("Entering 'getTreeNode' mathod");

		String id = request.getParameter("id");
		try {
			StandardCategoryService standardCategoryService = (StandardCategoryService) getBean("standardCategoryService");
			PageQuery pageQuery = standardCategoryForm.getQuery();
			pageQuery.setSelectParamId("GET_STANDARDCATEGORY_PAGE");
			pageQuery.setOrder(StandardCategory.COL_SQUENCE_NO);
			pageQuery.setOrderDirection(PageQuery.ASC);
			String queryString = getQueryString(pageQuery, id);
			pageQuery.getParameters().put("queryString", queryString);
			pageQuery.setPageSize("0");
			standardCategoryService.queryStandardCategory(pageQuery);
			response.setContentType("text/xml;charset=UTF-8");
			response.setHeader("Cache_Control", "no-cache");
			response.getWriter().write(getNodes(pageQuery.getRecordSet(), id));
			response.getWriter().close();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	private void initForm(StandardCategoryForm StandardCategoryForm, String action) {

	}

	private String getQueryString(PageQuery pageQuery, String pid) {
		if ("root".equalsIgnoreCase(pid)) pid = "";
		StringBuffer buffer = new StringBuffer();
		if ((pid == null) || "".equalsIgnoreCase(pid))
			buffer.append(" and " + StandardCategory.COL_PARENT_ID + " = 0");
		else
			buffer.append(" and " + StandardCategory.COL_PARENT_ID + " = " + pid);
		return buffer.toString();
/*		Session currentUser = (Session)request.getSession().getAttribute(Const.SESSION);
		String cityCode = null;
		if (currentUser != null)
			cityCode = currentUser.getCityCode();
		StringBuffer buffer = new StringBuffer();

		if ((cityCode == null) || ("".equalsIgnoreCase(cityCode)))
			buffer.append(" and " + StandardCategory.COL_CITY_CODE + " is null");
		else
			buffer.append(" and " + StandardCategory.COL_CITY_CODE + " = '" + cityCode + "'");
		String pid = request.getParameter("id");
		if ((pid == null) || "".equalsIgnoreCase(pid))
			buffer.append(" and " + StandardCategory.COL_PARENT_ID + " =0");
		else
			buffer.append(" and " + StandardCategory.COL_PARENT_ID + " = " + pid);
		return buffer.toString();*/
	}

	private String getNodes(List<Map<String, String>> list, String id) {
		Element root = null;
		Element topNode = null;
		if (id == null || "".equalsIgnoreCase(id)) {
			root = DocumentHelper.createElement("tree")
					.addAttribute("id", "0");

			topNode = root.addElement("item")
					.addAttribute("text", "广东省数据分类")
					.addAttribute("id", "root")
					.addAttribute("open", "1")
					.addAttribute("child", "1");
			topNode.addElement("userdata")
					.addAttribute("name", "name")
					.addText("广东省数据分类");
		}
		else {
			root = DocumentHelper.createElement("tree")
					.addAttribute("id", id);
			topNode = root;
		}
		for (Map<String, String> category : list) {
			topNode.addElement("item")
					.addAttribute("text", category.get(StandardCategory.PROP_NAME.toUpperCase()))
					.addAttribute("id", category.get(StandardCategory.PROP_ID.toUpperCase()))
					.addAttribute("child", "1")
				.addElement("userdata")
					.addAttribute("name", "name")
				.addText(category.get(StandardCategory.PROP_NAME.toUpperCase()));
		}
		return root.asXML();
/*
		StringBuffer sb = new StringBuffer("");
		sb.append("<?xml version='1.0' encoding='UTF-8' ?>");
		if (id == null || "".equalsIgnoreCase(id)) {
			sb.append("<tree id=\"0\">");
		}
		else
			sb.append("<tree id=\"" + id + "\">");
		if (list != null) {
			for (int i = 0; i < list.size(); i++) {
				Map rs = (Map)list.get(i);
			sb.append("<item id=\"" + rs.get(StandardCategory.COL_ID) + "\" "
					+ "text=\"" + rs.get(StandardCategory.COL_NAME) + "\" "
					+ "child=\"1\">");
				sb.append("<userdata name=\"code\">" + (rs.get(StandardCategory.COL_CODE) == null ? "" : rs.get(StandardCategory.COL_CODE)) + "</userdata>");
				sb.append("<userdata name=\"name\">" + rs.get(StandardCategory.COL_NAME) + "</userdata>");
				sb.append("<userdata name=\"parentId\">" + (rs.get(StandardCategory.COL_PARENT_ID) == null ? "" : rs.get(StandardCategory.COL_PARENT_ID)) + "</userdata>");
				sb.append("</item>");
			}
		}
		sb.append("</tree>");
		System.out.println(sb.toString());
		return sb.toString();*/
	}

	private String getCityCode(HttpServletRequest request) {
		String cityCode = null;
		Session currentUser = (Session)request.getSession().getAttribute(Const.SESSION);
		if (currentUser == null)
			cityCode = "";
		else {
			if ((currentUser.getCityCode() != null) && !("".equalsIgnoreCase(currentUser.getCityCode())))
				cityCode = currentUser.getCityCode();
			else
				cityCode = "";
		}
		return cityCode;
	}
}
