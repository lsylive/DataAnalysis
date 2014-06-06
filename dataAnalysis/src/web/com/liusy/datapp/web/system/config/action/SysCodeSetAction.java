package com.liusy.datapp.web.system.config.action;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import com.liusy.core.util.Const;
import com.liusy.core.util.Session;
import com.liusy.dataapplatform.base.exception.WebException;
import com.liusy.dataapplatform.base.util.PageQuery;

import com.liusy.dataapplatform.base.util.ConvertUtil;
import com.liusy.datapp.model.datastandard.StandardCategory;
import com.liusy.datapp.model.system.config.SysCodeSet;
import com.liusy.datapp.service.datastandard.StandardCodesetService;
import com.liusy.datapp.service.system.config.SysCodeSetService;
import com.liusy.datapp.web.BaseAction;
import com.liusy.datapp.web.system.config.form.SysCodeSetForm;

public class SysCodeSetAction extends BaseAction {

	private static final String	GETTREE	= "GETTREE";

	@Override
	public ActionForward doExecute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		String action = request.getParameter("action");
		if (action == null) action = LIST;
		if (log.isDebugEnabled()) log.debug("action:" + action);
		ActionForward forward = null;
		SysCodeSetForm codeSetForm = (SysCodeSetForm) form;
		try {
			// if (ADD.equalsIgnoreCase(action)) forward = addSysCode(mapping,
			// codeSetForm, request, response);
			// if (SAVE.equalsIgnoreCase(action)) forward = saveSysCode(mapping,
			// codeSetForm, request, response);
			// if (EDIT.equalsIgnoreCase(action)) forward = editSysCode(mapping,
			// codeSetForm, request, response);
			// if (UPDATE.equalsIgnoreCase(action)) forward =
			// updateSysCode(mapping, codeSetForm, request, response);
			// if (DELETE.equalsIgnoreCase(action)) forward =
			// deleteSysCode(mapping, codeSetForm, request, response);
			if (GETTREE.equalsIgnoreCase(action)) forward = getTreeNode(mapping, codeSetForm, request, response);
			else if (LIST.equalsIgnoreCase(action)) forward = searchSysCodesetGrid(mapping, codeSetForm, request, response);
			else {
				request.setAttribute("err", new WebException("找不到该action方法：" + action));
				forward = mapping.findForward(ERROR);// 找不到合适的action
			}
		}
		catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("err", e);
			forward = mapping.findForward(ERROR);
		}
		return forward;
	}

	private ActionForward getTreeNode(ActionMapping mapping, SysCodeSetForm codeSetForm, HttpServletRequest request, HttpServletResponse response) throws Exception {
		if (log.isDebugEnabled()) log.debug("Entering 'getTreeNode' mathod");

		String id = request.getParameter("id");
		try {
			getCategoryTree(request, response, id);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	private ActionForward searchSysCodesetGrid(ActionMapping mapping, SysCodeSetForm codeSetForm, HttpServletRequest request, HttpServletResponse response) throws Exception {
		if (log.isDebugEnabled()) log.debug("Entering 'searchStandardCodesetGrid' method");

		try {
			SysCodeSetService sysCodeSetService = (SysCodeSetService) getBean("sysCodeSetService");
			PageQuery pageQuery = codeSetForm.getQuery();
			pageQuery.setSelectParamId("GET_SYSCODESET_PAGE");
			String queryString = getQueryString(pageQuery);
			pageQuery.getParameters().put("queryString", queryString);
			// sysCodeSetService.querySysCodeSet(pageQuery);
			setPage(codeSetForm.getQuery());
		}
		catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return mapping.findForward(LIST);
	}

	private String getQueryString(PageQuery pageQuery) {
		StringBuffer buffer = new StringBuffer();
		Map fields = pageQuery.getParameters();
		String str = (String) fields.get("treeid");
		if ("root".equalsIgnoreCase(str)) buffer.append("");
		else if (str != null && !"".equalsIgnoreCase(str)) buffer.append(" and " + SysCodeSet.COL_ID + " = " + str);
		else buffer.append(" and " + SysCodeSet.COL_ID + " !=0");
		return buffer.toString();
	}

	private void getCategoryTree(HttpServletRequest request, HttpServletResponse response, String id) throws Exception {
		SysCodeSetService sysCodeSetService = (SysCodeSetService) getBean("sysCodeSetService");

		if (id == null || "".equalsIgnoreCase(id)) {
			PageQuery query = new PageQuery();
			StringBuffer str = new StringBuffer().append("");
			str.append(" and " + SysCodeSet.COL_ID + " != 0");
			Element root = DocumentHelper.createElement("tree").addAttribute("id", "0");
			Element topNode = root.addElement("item").addAttribute("text", "系统代码分类").addAttribute("id", "root")
			                      .addAttribute("im0", "icon/code_all.gif").addAttribute("im1", "icon/code_all.gif").addAttribute("im2", "icon/code_all.gif")
			                      .addAttribute("open", "1").addAttribute("child", "1");
			topNode.addElement("userdata").addAttribute("name", "name").addText("系统代码分类");
			query.setSelectParamId("GET_SYSCODESET_PAGE");
			query.getParameters().put("queryString", str.toString());
			query.setOrder(SysCodeSet.COL_ID);
			query.setOrderDirection(PageQuery.ASC);
			query.setPageSize("0");
			/*
			 * //List<Map<String, String>> categorys =
			 * sysCodeSetService.getCodeSetsForTree(query).getRecordSet(); if
			 * (categorys==null) { categorys = new ArrayList<Map<String,
			 * String>>(); } for (Map<String, String> category : categorys) {
			 * topNode.addElement("item") .addAttribute("text",
			 * category.get(SysCodeSet.PROP_CN_NAME.toUpperCase()))
			 * .addAttribute("id", category.get(SysCodeSet.PROP_ID.toUpperCase()))
			 * .addAttribute("im0", "folderClosed.gif") .addElement("userdata")
			 * .addAttribute("name", "name")
			 * .addText(category.get(SysCodeSet.PROP_CN_NAME.toUpperCase())); }
			 */
			response.getWriter().write(root.asXML());
			response.getWriter().close();
		}
	}

}
