package com.liusy.datapp.web.system.config.action;

import java.util.ArrayList;
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

import com.liusy.core.util.Const;
import com.liusy.core.util.Session;
import com.liusy.dataapplatform.base.exception.ServiceException;
import com.liusy.dataapplatform.base.exception.WebException;
import com.liusy.dataapplatform.base.util.ConvertUtil;
import com.liusy.dataapplatform.base.util.PageQuery;
import com.liusy.datapp.model.system.config.SysCode;
import com.liusy.datapp.model.system.config.SysCodeSet;
import com.liusy.datapp.service.system.config.SysCodeService;
import com.liusy.datapp.service.system.config.SysCodeSetService;
import com.liusy.datapp.web.BaseAction;
import com.liusy.datapp.web.system.config.form.SysCodeForm;

public class SysCodeAction extends BaseAction {

	private static final String	GETTREE	= "GETTREE";
	private static final String	CODELIST	= "CODELIST";

	@Override
	public ActionForward doExecute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

		String action = request.getParameter("action");
		if (action == null) action = LIST;
		if (log.isDebugEnabled()) log.debug("action:" + action);
		ActionForward forward = null;
		SysCodeForm sysCodeForm = (SysCodeForm) form;
		String action1 = sysCodeForm.getQuery().getParameters().get("action1");
		if ("CODELIST".equals(action1))
		{
			action = CODELIST;
		}
		try {
			if (ADD.equalsIgnoreCase(action)) forward = addSysCode(mapping, sysCodeForm, request, response);
			else if (SAVE.equalsIgnoreCase(action)) forward = saveSysCode(mapping, sysCodeForm, request, response);
			else if (EDIT.equalsIgnoreCase(action)) forward = editSysCode(mapping, sysCodeForm, request, response);
			else if (UPDATE.equalsIgnoreCase(action)) forward = updateSysCode(mapping, sysCodeForm, request, response);
			else if (DELETE.equalsIgnoreCase(action)) forward = deleteSysCode(mapping, sysCodeForm, request, response);
			else if (GETTREE.equalsIgnoreCase(action)) forward = getTreeNode(mapping, sysCodeForm, request, response);
			else if (LIST.equalsIgnoreCase(action)) forward = list(mapping, sysCodeForm, request, response);
			else if (CODELIST.equalsIgnoreCase(action)) forward = searchSysCodesetGrid(mapping, sysCodeForm, request, response);
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
	public ActionForward list(ActionMapping mapping, SysCodeForm sysCodeForm, HttpServletRequest request, HttpServletResponse response) throws Exception {
	  return mapping.findForward(LIST);
	}
	
	public ActionForward addSysCode(ActionMapping mapping, SysCodeForm sysCodeForm, HttpServletRequest request, HttpServletResponse response) throws Exception {
		if (log.isDebugEnabled()) log.debug("Entering 'addSysCode' method");

		initForm(sysCodeForm, ADD);
		String codesetid = request.getParameter("codesetid");
		if (codesetid != null && !"".equals(codesetid)) {
			SysCodeSetService sysCodeSetService = (SysCodeSetService) getBean("sysCodeSetService");
			String name = sysCodeSetService.findSysCodeSet(Integer.parseInt(codesetid)).getCname();
			sysCodeForm.setCodeName(name);
			sysCodeForm.getRecord().put(SysCode.PROP_CS_ID, codesetid);
		}
		else {
			request.setAttribute("err", "系统参数错误。");
			return mapping.findForward(ERROR);
		}
		return mapping.findForward(ADD);
	}

	public ActionForward saveSysCode(ActionMapping mapping, SysCodeForm sysCodeForm, HttpServletRequest request, HttpServletResponse response) throws Exception {
		if (log.isDebugEnabled()) log.debug("Entering 'saveCodeset' method");

		ActionForward forward = null;
		try {
			SysCode sysCode = new SysCode();
			ConvertUtil.mapToObject(sysCode, sysCodeForm.getRecord());
			Session currentUser = (Session) request.getSession().getAttribute(Const.SESSION);
			if (currentUser != null) sysCode.setCreatedBy(currentUser.getAccountName());
			sysCode.setCreateDate(new Date());

			SysCodeService sysCodeService = (SysCodeService) getBean("sysCodeService");
			sysCodeService.createSysCode(sysCode);
			forward = returnForward(mapping, request, RETURN_NORMAL);
		}
		catch (ServiceException e) {
			addMessage(sysCodeForm, e.getMessage());
			initForm(sysCodeForm, ADD);
			forward = mapping.findForward(ADD);
		}
		catch (Exception e) {
			e.printStackTrace();
			addMessage(sysCodeForm, "保存操作失败!");
			initForm(sysCodeForm, ADD);
			forward = mapping.findForward(ADD);
		}
		return forward;
	}

	public ActionForward editSysCode(ActionMapping mapping, SysCodeForm sysCodeForm, HttpServletRequest request, HttpServletResponse response) throws Exception {
		if (log.isDebugEnabled()) log.debug("Entering 'editCodeset' method");

		initForm(sysCodeForm, EDIT);
		String id = request.getParameter("record(id)");
		SysCodeService sysCodeService = (SysCodeService) getBean("sysCodeService");
		SysCode sysCode = (SysCode) sysCodeService.findSysCode(new Integer(id));
		ConvertUtil.objectToMap(sysCodeForm.getRecord(), sysCode);
		SysCodeSetService sysCodeSetService = (SysCodeSetService) getBean("sysCodeSetService");
		String name = sysCodeSetService.findSysCodeSet(sysCode.getCodeSetId()).getCname();
		sysCodeForm.setCodeName(name);
		return mapping.findForward(EDIT);
	}

	public ActionForward updateSysCode(ActionMapping mapping, SysCodeForm sysCodeForm, HttpServletRequest request, HttpServletResponse response) throws Exception {
		if (log.isDebugEnabled()) log.debug("Entering 'updateCodeset' method");

		try {
			SysCodeService sysCodeService = (SysCodeService) getBean("sysCodeService");
			SysCode sysCode = sysCodeService.findSysCode(new Integer(sysCodeForm.getRecord().get("id")));
			if (sysCode != null) {
				ConvertUtil.mapToObject(sysCode, sysCodeForm.getRecord());
				Session currentUser = (Session) request.getSession().getAttribute(Const.SESSION);
				if (currentUser != null) sysCode.setModifiedby(currentUser.getAccountName());
				sysCode.setModifyDate(new Date());
				sysCodeService.updateSysCode(sysCode);
				request.setAttribute("msg", "修改成功！");
				return returnForward(mapping, request, RETURN_NORMAL);
			}
			else {
				request.setAttribute("operstatus", "0");
				addMessage(sysCodeForm, "数据集记录不存在");
				return mapping.findForward(ERROR);
			}
		}
		catch (ServiceException e) {
			addMessage(sysCodeForm, e.getMessage());
			initForm(sysCodeForm, EDIT);
			return mapping.findForward(EDIT);
		}
		catch (Exception e) {
			e.printStackTrace();
			addMessage(sysCodeForm, "编辑操作失败");
			initForm(sysCodeForm, EDIT);
			return mapping.findForward(EDIT);
		}
	}

	public ActionForward deleteSysCode(ActionMapping mapping, SysCodeForm sysCodeForm, HttpServletRequest request, HttpServletResponse response) throws Exception {
		if (log.isDebugEnabled()) log.debug("Entering 'deleteCodeset' method");

		try {
			SysCodeService sysCodeService = (SysCodeService) getBean("sysCodeService");
			String del_ids = request.getParameter("ids");
			if (del_ids != null && del_ids.trim().length() > 0) {
				sysCodeService.removeSysCodes(parseId(del_ids.split(";")));
			}
		}
		catch (Exception e) {
		}
		return searchSysCodesetGrid(mapping, sysCodeForm, request, response);
	}

	private ActionForward getTreeNode(ActionMapping mapping, SysCodeForm sysCodeForm, HttpServletRequest request, HttpServletResponse response) throws Exception {
		if (log.isDebugEnabled()) log.debug("Entering 'getTreeNode' mathod");

		String id = request.getParameter("id");

		try {
			response.setContentType("text/xml;charset=UTF-8");
			response.setHeader("Cache_Control", "no-cache");
			
			getCategoryTree(request, response, id);
			
			response.getWriter().close();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	private ActionForward searchSysCodesetGrid(ActionMapping mapping, SysCodeForm sysCodeForm, HttpServletRequest request, HttpServletResponse response) throws Exception {
		if (log.isDebugEnabled()) log.debug("Entering 'searchSysCodeGrid' method");

		try {
			SysCodeService sysCodeService = (SysCodeService) getBean("sysCodeService");
			PageQuery pageQuery = sysCodeForm.getQuery();
			pageQuery.setSelectParamId("GET_SYSCODE_PAGE");
			String queryString = getQueryString(pageQuery);
			pageQuery.getParameters().put("queryString", queryString);
			sysCodeService.querySysCode(pageQuery);
			setPage(sysCodeForm.getQuery());
			initForm(sysCodeForm, CODELIST);
			List<Map<String, String>> list = pageQuery.getRecordSet();
			for (int i = 0; i < list.size(); i++) {
				Map<String, String> map = list.get(i);
				String str = (String) map.get("STATUS");
				if (str == null) {
					map.put("STATUS", "");
				}
				else {
					setCode(sysCodeForm, "CODE_STATUS");
					map.put("STATUS", findCodeName(sysCodeForm, "CODE_STATUS", str.trim()));
				}
			}
		}
		catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return mapping.findForward(CODELIST);
	}

	private String getQueryString(PageQuery pageQuery) {
		StringBuffer buffer = new StringBuffer();
		Map fields = pageQuery.getParameters();
		String str = (String) fields.get("treeid");
		if ("root".equalsIgnoreCase(str)) buffer.append(" and " + SysCode.COL_CS_ID + " =0");
		else if (str != null && !"".equalsIgnoreCase(str)) buffer.append(" and " + SysCode.COL_CS_ID + " = " + str);
		else buffer.append(" and " + SysCode.COL_CS_ID + " =0");

		return buffer.toString();
	}
	

	private void getCategoryTree(HttpServletRequest request, HttpServletResponse response, String id) throws Exception {
		SysCodeService sysCodeService = (SysCodeService) getBean("sysCodeService");

		if (id == null || "".equalsIgnoreCase(id)) {
			Element root = DocumentHelper.createElement("tree").addAttribute("id", "0");
			Element topNode = root.addElement("item").addAttribute("text", "系统代码").addAttribute("id", "root").addAttribute("open", "1");
			topNode.addAttribute("im0", "icon/code_all.gif").addAttribute("im1", "icon/code_all.gif").addAttribute("im2", "icon/code_all.gif").addAttribute("child", "1");
			topNode.addElement("userdata").addAttribute("name", "name").addText("系统代码");

			PageQuery query = new PageQuery();
			StringBuffer str = new StringBuffer().append("");
			query.setSelectParamId("GET_SYSCODESET_PAGE");
			query.getParameters().put("queryString", str.toString());
			query.setOrder(SysCodeSet.COL_CN_NAME);
			query.setOrderDirection(PageQuery.ASC);
			query.setPageSize("0");
			List<Map<String, String>> categorys = sysCodeService.getSysCodeSetsForTree(query).getRecordSet();

			if (categorys == null) categorys = new ArrayList<Map<String, String>>();

			for (Map<String, String> category : categorys) {
				Element item = topNode.addElement("item");
				item.addAttribute("text", category.get(SysCodeSet.PROP_CN_NAME.toUpperCase())).addAttribute("id", category.get(SysCodeSet.PROP_ID.toUpperCase()));
				item.addAttribute("im0", "icon/code_mod.gif").addAttribute("im1", "icon/code_mod.gif").addAttribute("im2", "icon/code_mod.gif");
				item.addElement("userdata").addAttribute("name", "name").addText(category.get(SysCodeSet.PROP_CN_NAME.toUpperCase()));
			}

			response.getWriter().write("<?xml version='1.0' encoding='UTF-8' ?>");
			response.getWriter().write(root.asXML());
		}
	}

	private void initForm(SysCodeForm sysCodeForm, String action) {
		setCode(sysCodeForm, "CODE_STATUS");
		SysCodeService sysCodeService = (SysCodeService) getBean("sysCodeService");

		PageQuery query = new PageQuery();
		query.setSelectParamId("GET_SYSCODESET_PAGE");
		query.getParameters().put("queryString", "");
		query.setPageSize("0");

		if ("ADD".equalsIgnoreCase(action)) {
			// setCode(sysCodeForm, "CODE_STATUS");
			sysCodeForm.getRecord().put("status", "1");
		}

		List<Map<String, String>> codesets = sysCodeService.getSysCodeSetsForTree(query).getRecordSet();
		if (codesets == null) {
			codesets = new ArrayList<Map<String, String>>();
		}
		setCode(sysCodeForm, "codesets", codesets, SysCodeSet.PROP_CN_NAME.toUpperCase(), SysCodeSet.PROP_ID.toUpperCase());
	}

	private java.io.Serializable[] parseId(String[] ids) throws Exception {
		if (ids == null || ids.length == 0) {
			throw new Exception("非法进入编辑页！");
		}
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
