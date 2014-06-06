package com.liusy.datapp.web.system.org.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.liusy.dataapplatform.base.exception.ServiceException;
import com.liusy.dataapplatform.base.exception.WebException;
import com.liusy.dataapplatform.base.util.ConvertUtil;
import com.liusy.datapp.model.system.org.SysDept;
import com.liusy.datapp.model.system.org.SysOrg;
import com.liusy.datapp.service.system.org.SysOrgService;
import com.liusy.datapp.web.BaseAction;
import com.liusy.datapp.web.system.org.form.SysOrgForm;

public class SysOrgAction extends BaseAction {

	protected static final String	GETTREE		= "GETTREE";

	@Override
	public ActionForward doExecute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		String action = request.getParameter("action");
		if (action == null) action = LIST;
		if (log.isDebugEnabled()) log.debug("action:" + action);
		ActionForward forward;
		SysOrgForm sysOrgForm = (SysOrgForm) form;

		try {
			if (LIST.equalsIgnoreCase(action)) forward = search(mapping, sysOrgForm, request, response); // 打开查询列表页面
			else if (GETTREE.equalsIgnoreCase(action)) forward = getTreeNode(mapping, sysOrgForm, request, response); // 打开树
			else if (ADD.equalsIgnoreCase(action)) forward = addOrg(mapping, sysOrgForm, request, response); // 打开增加页面
			else if (SAVE.equalsIgnoreCase(action)) forward = saveOrg(mapping, sysOrgForm, request, response); // 保存增加数据
			else if (EDIT.equalsIgnoreCase(action)) forward = editOrg(mapping, sysOrgForm, request, response); // 打开修改页面
			else if (UPDATE.equalsIgnoreCase(action)) forward = updateOrg(mapping, sysOrgForm, request, response);// 保存修改数据
			else if (DELETE.equalsIgnoreCase(action)) forward = deleteOrg(mapping, sysOrgForm, request, response);// 删除
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

	public ActionForward addOrg(ActionMapping mapping, SysOrgForm sysOrgForm, HttpServletRequest request, HttpServletResponse response) throws Exception {
		if (log.isDebugEnabled()) log.debug("Entering 'addOrg' method");

		try {
			String pid = request.getParameter("pid");
			sysOrgForm.getRecord().put("upOrgId", pid);
			initForm(sysOrgForm, ADD);
			return mapping.findForward(ADD);
		}
		catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("err", e);
			return mapping.findForward(ERROR);
		}
	}

	public ActionForward saveOrg(ActionMapping mapping, SysOrgForm sysOrgForm, HttpServletRequest request, HttpServletResponse response) throws Exception {
		if (log.isDebugEnabled()) log.debug("Entering 'saveOrg' method");
		ActionForward forward = null;

		try {
			SysOrg sysOrg = new SysOrg();
			ConvertUtil.mapToObject(sysOrg, sysOrgForm.getRecord());
			SysOrgService sysOrgService = (SysOrgService) getBean("sysOrgService");
			sysOrgService.createSysOrg(sysOrg);
			forward = returnForward(mapping, request, RETURN_NORMAL);
		}
		catch (ServiceException e) {
			addMessage(sysOrgForm, e.getMessage());
			initForm(sysOrgForm, ADD);
			forward = mapping.findForward(ADD);
		}
		catch (Exception e) {
			e.printStackTrace();
			addMessage(sysOrgForm, "保存操作失败!");
			initForm(sysOrgForm, ADD);
			forward = mapping.findForward(ADD);
		}
		return forward;
	}

	public ActionForward editOrg(ActionMapping mapping, SysOrgForm sysOrgForm, HttpServletRequest request, HttpServletResponse response) throws Exception {
		if (log.isDebugEnabled()) log.debug("Entering 'editOrg' method");
		String id = request.getParameter("id");

		try {
			SysOrgService sysOrgService = (SysOrgService) getBean("sysOrgService");
			SysOrg sysOrg = (SysOrg) sysOrgService.findSysOrg(new Integer(id));
			ConvertUtil.objectToMap(sysOrgForm.getRecord(), sysOrg);
			initForm(sysOrgForm, EDIT);
			return mapping.findForward(EDIT);
		}
		catch (ServiceException e) {
			request.setAttribute("errMsg", e.getMessage());
			return mapping.findForward(ERROR);
		}
		catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("err", e);
			return mapping.findForward(ERROR);
		}
	}

	public ActionForward updateOrg(ActionMapping mapping, SysOrgForm sysOrgForm, HttpServletRequest request, HttpServletResponse response) throws Exception {
		if (log.isDebugEnabled()) log.debug("Entering 'updateOrg' method");

		ActionForward forward = null;

		try {
			SysOrgService sysOrgService = (SysOrgService) getBean("sysOrgService");
			SysOrg sysOrg = sysOrgService.findSysOrg(new Integer(sysOrgForm.getRecord().get("id")));
			ConvertUtil.mapToObject(sysOrg, sysOrgForm.getRecord(), false);
			sysOrgService.updateSysOrg(sysOrg);
			forward = returnForward(mapping, request, RETURN_NORMAL);
		}
		catch (Exception e) {
			addMessage(sysOrgForm, "保存操作失败!");
			initForm(sysOrgForm, EDIT);
			forward = mapping.findForward(EDIT);
		}
		return forward;
	}

	public ActionForward deleteOrg(ActionMapping mapping, SysOrgForm sysOrgForm, HttpServletRequest request, HttpServletResponse response) throws Exception {
		if (log.isDebugEnabled()) log.debug("Entering 'deleteOrg' method");
		response.setContentType("text/plain;charset=UTF-8");
		response.setHeader("Cache_Control", "no-cache");
		StringBuffer sb = new StringBuffer();
		try {
			SysOrgService sysOrgService = (SysOrgService) getBean("sysOrgService");
			String id = request.getParameter("id");
			if (id != null) {
				sysOrgService.removeSysOrg(new Integer(id));
			}
			sb.append(RET_NORAML);
		}
		catch (Exception e) {
			sb.append(JsonErrorMessage(OPER_FAILED, "单位删除失败！"));
		}
		response.getWriter().write(sb.toString());
		response.getWriter().close();
		return null;
	}

	public ActionForward search(ActionMapping mapping, SysOrgForm sysOrgForm, HttpServletRequest request, HttpServletResponse response) throws Exception {
		return mapping.findForward(LIST);
	}

	public ActionForward getTreeNode(ActionMapping mapping, SysOrgForm sysOrgForm, HttpServletRequest request, HttpServletResponse response) throws Exception {
		if (log.isDebugEnabled()) log.debug("Entering 'getTree' method");
		String id = sysOrgForm.getId();

		response.setContentType("text/xml;charset=UTF-8");
		response.setHeader("Cache_Control", "no-cache");
		try {
			SysOrgService sysOrgService = (SysOrgService) getBean("sysOrgService");
			List orgs = null, depts = null;
			if (id.indexOf("org") == 0) {
				orgs = sysOrgService.findChildOrgs(id.substring(3));
				depts = sysOrgService.findChildDeptsByOrgId(id.substring(3));
				response.getWriter().write(getNodes(orgs, depts, id));
			}
			else if (id.indexOf("dept") == 0) {
				depts = sysOrgService.findChildDeptsByDeptId(id.substring(4));
				response.getWriter().write(getNodes(null, depts, id));
			}
			else {
				orgs = sysOrgService.findChildOrgs(null);
				response.getWriter().write(getTopNodes(orgs));
			}
			response.getWriter().close();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	private void initForm(SysOrgForm sysOrgForm, String action) {
	}

	private String getTopNodes(List orgs) {
		StringBuffer sb = new StringBuffer("");
		sb.append("<?xml version='1.0' encoding='UTF-8' ?>");
		sb.append("<tree id=\"0\">");
		if (orgs != null) {
			for (int i = 0; i < orgs.size(); i++) {
				SysOrg so = (SysOrg) orgs.get(i);
				sb.append("<item id=\"org" + so.getId() + "\" text=\"" + so.getName() + "\" im0=\"/icon/house_big.gif\" im1=\"/icon/house_big.gif\" im2=\"/icon/house_big.gif\" child=\"1\">");
				sb.append("<userdata name=\"name\">" + so.getName() + "</userdata>");
				sb.append("<userdata name=\"parentId\">" + (so.getUpOrgId() == null ? "" : so.getUpOrgId()) + "</userdata>");
				sb.append("<userdata name=\"orgCode\">" + (so.getOrgCode() == null ? "" : so.getOrgCode()) + "</userdata>");
				sb.append("</item>");
			}
		}
		sb.append("</tree>");
		return sb.toString();
	}

	private String getNodes(List orgs, List depts, String id) {
		StringBuffer sb = new StringBuffer("");
		sb.append("<?xml version='1.0' encoding='UTF-8' ?>");
		sb.append("<tree id=\"" + id + "\">");
		if (orgs != null) {
			for (int i = 0; i < orgs.size(); i++) {
				SysOrg so = (SysOrg) orgs.get(i);
				sb.append("<item id=\"org" + so.getId() + "\" text=\"" + so.getName() + "\" im0=\"icon/house_big.gif\" im1=\"icon/house_big.gif\" im2=\"icon/house_big.gif\" child=\"1\">");
				sb.append("<userdata name=\"name\">" + so.getName() + "</userdata>");
				sb.append("<userdata name=\"parentId\">" + (so.getUpOrgId() == null ? "" : so.getUpOrgId()) + "</userdata>");
				sb.append("</item>");
			}
		}
		if (depts != null) {
			for (int i = 0; i < depts.size(); i++) {
				SysDept so = (SysDept) depts.get(i);
				sb.append("<item id=\"dept" + so.getId() + "\" text=\"" + so.getDeptName() + "\" im0=\"icon/people.gif\" im1=\"icon/people.gif\" im2=\"icon/people.gif\" child=\"1\">");
				sb.append("<userdata name=\"name\">" + so.getDeptName() + "</userdata>");
				sb.append("<userdata name=\"orgId\">" + (so.getOrgId() == null ? "" : "org" + so.getOrgId()) + "</userdata>");
				sb.append("<userdata name=\"parentId\">" + (so.getUpDeptId() == null ? "" : "dept" + so.getUpDeptId()) + "</userdata>");
				sb.append("<userdata name=\"deptCode\">" + (so.getDeptCode() == null ? "" : so.getDeptCode()) + "</userdata>");
				sb.append("</item>");
			}
		}
		sb.append("</tree>");
		return sb.toString();
	}
}
