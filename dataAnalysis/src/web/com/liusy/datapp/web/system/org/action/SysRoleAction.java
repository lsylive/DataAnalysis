package com.liusy.datapp.web.system.org.action;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.liusy.dataapplatform.base.exception.ServiceException;
import com.liusy.dataapplatform.base.exception.WebException;
import com.liusy.dataapplatform.base.util.ConvertUtil;
import com.liusy.dataapplatform.base.util.PageQuery;
import com.liusy.datapp.model.system.org.SysRole;
import com.liusy.datapp.service.system.config.SysResourceService;
import com.liusy.datapp.service.system.org.SysResourceRoleService;
import com.liusy.datapp.service.system.org.SysRoleService;
import com.liusy.datapp.service.system.org.SysUserService;
import com.liusy.datapp.web.BaseAction;
import com.liusy.datapp.web.system.org.form.SysRoleForm;

public class SysRoleAction extends BaseAction {

	private static final String	START			= "START";

	private static final String	STOP			= "STOP";

	private static final String	CHECKROLE	= "CHECKROLE";

	private static final String	RIGHTS		= "RIGHTS";		// 分配权限

	private static final String	SAVERIGHTS	= "SAVERIGHTS";	// 保存权限

	private static final String	VERIFY		= "VERIFY";		// 审核

	private static final String	SAVEVERIFY	= "SAVEVERIFY";	// 保存审核结果

	private static final String	GETRIGHTS	= "GETRIGHTS";	// 取得权限树

	@Override
	public ActionForward doExecute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		String action = request.getParameter("action");
		if (action == null) action = LIST;
		if (log.isDebugEnabled()) log.debug("action:" + action);
		ActionForward forward;
		SysRoleForm sysRoleForm = (SysRoleForm) form;

		try {
			if (LIST.equalsIgnoreCase(action)) forward = searchRole(mapping, sysRoleForm, request, response); // 打开查询列表页面
			else if (ADD.equalsIgnoreCase(action)) forward = addRole(mapping, sysRoleForm, request, response); // 打开增加页面
			else if (SAVE.equalsIgnoreCase(action)) forward = saveRole(mapping, sysRoleForm, request, response); // 保存增加数据
			else if (EDIT.equalsIgnoreCase(action)) forward = editRole(mapping, sysRoleForm, request, response); // 打开修改页面
			else if (UPDATE.equalsIgnoreCase(action)) forward = updateRole(mapping, sysRoleForm, request, response);// 保存修改数据
			else if (VIEW.equalsIgnoreCase(action)) forward = viewRole(mapping, sysRoleForm, request, response);
			else if (DELETE.equalsIgnoreCase(action)) forward = deleteRole(mapping, sysRoleForm, request, response);// 删除
			else if (START.equalsIgnoreCase(action)) forward = startRole(mapping, sysRoleForm, request, response); // 启动角色
			else if (STOP.equalsIgnoreCase(action)) forward = stopRole(mapping, sysRoleForm, request, response); // 暂停角色
			else if (RIGHTS.equalsIgnoreCase(action)) forward = rights(mapping, sysRoleForm, request, response); // 打开分配权限页面
			else if (SAVERIGHTS.equalsIgnoreCase(action)) forward = saveRights(mapping, sysRoleForm, request, response);// 保存打开分配权限
			else if (VERIFY.equalsIgnoreCase(action)) forward = verify(mapping, sysRoleForm, request, response);// 打开审核
			else if (SAVEVERIFY.equalsIgnoreCase(action)) forward = saveVerify(mapping, sysRoleForm, request, response); // 保存审核
			else if (CHECKROLE.equalsIgnoreCase(action)) forward = checkRole(mapping, sysRoleForm, request, response);
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

	public ActionForward checkRole(ActionMapping mapping, SysRoleForm sysRoleForm, HttpServletRequest request, HttpServletResponse response) throws Exception {
		if (log.isDebugEnabled()) log.debug("Entering 'checkRole' method");
		String id = request.getParameter("id");
		response.setContentType("text/xml;charset=UTF-8");
		response.setHeader("Cache_Control", "no-cache");

		try {
			SysRoleService sysRoleService = (SysRoleService) getBean("sysRoleService");
			SysRole role = sysRoleService.findSysRole(new Integer(id));

			if (SysRoleService.SYSROLE.equals(role.getRoleType())) response.getWriter().write("Y");
			else response.getWriter().write("N");
		}
		catch (Exception e) {
			e.printStackTrace();
			response.getWriter().write("ERROR");
		}
		response.getWriter().close();
		return null;
	}

	public ActionForward viewRole(ActionMapping mapping, SysRoleForm sysRoleForm, HttpServletRequest request, HttpServletResponse response) throws Exception {
		if (log.isDebugEnabled()) log.debug("Entering 'viewRole' method");
		String id = request.getParameter("id");
		sysRoleForm.getRecord().put("id", id);

		try {
			SysResourceService sysResourceService = (SysResourceService) getBean("sysResourceService");
			PageQuery pageQuery = sysRoleForm.getQuery();
			pageQuery.setSelectParamId("GET_RIGHTS");
			pageQuery.setPageSize("0");
			sysResourceService.querySysResource(pageQuery);
			
			SysRoleService sysRoleService = (SysRoleService) getBean("sysRoleService");
			SysRole role = sysRoleService.findSysRole(new Integer(id));
			ConvertUtil.objectToMap(sysRoleForm.getRecord(), role);

			SysResourceRoleService sysResourceRoleService = (SysResourceRoleService) getBean("sysResourceRoleService");
			List rights = sysResourceRoleService.queryRoleRights(new Integer(id));
			if (rights != null) sysRoleForm.setRights(rights);
			
			SysUserService sysUserService = (SysUserService) getBean("sysUserService");
			PageQuery pq = new PageQuery();
			pq.setSelectParamId("GET_ROLE_USERS_R");
			pq.getParameters().put("queryString", "a.role_id=" + id);
			List<Map<String, String>> users = sysUserService.findUserRoles(pq).getRecordSet();
			sysRoleForm.setUsers(users);
			
			initForm(sysRoleForm, VIEW);
			return mapping.findForward(VIEW);
		}
		catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("err", e);
			return mapping.findForward(ERROR);
		}
	}

	public ActionForward rights(ActionMapping mapping, SysRoleForm sysRoleForm, HttpServletRequest request, HttpServletResponse response) throws Exception {
		if (log.isDebugEnabled()) log.debug("Entering 'rightsRole' method");
		String id = request.getParameter("id");
		sysRoleForm.getRecord().put("id", id);

		try {
			SysRoleService sysRoleService = (SysRoleService) getBean("sysRoleService");
			SysRole role = sysRoleService.findSysRole(new Integer(id));
			if (role == null) {
				addMessage(sysRoleForm, "审核操作失败!");
				mapping.findForward(VERIFY);
			}

			SysResourceService sysResourceService = (SysResourceService) getBean("sysResourceService");
			PageQuery pageQuery = sysRoleForm.getQuery();
			pageQuery.setSelectParamId("GET_RIGHTS");
			pageQuery.setPageSize("0");
			sysResourceService.querySysResource(pageQuery);

			SysResourceRoleService sysResourceRoleService = (SysResourceRoleService) getBean("sysResourceRoleService");
			List rights = sysResourceRoleService.queryRoleRights(new Integer(id));
			if (rights != null) sysRoleForm.setRights(rights);

			return mapping.findForward(RIGHTS);
		}
		catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("err", e);
			return mapping.findForward(ERROR);
		}
	}

	public ActionForward saveRights(ActionMapping mapping, SysRoleForm sysRoleForm, HttpServletRequest request, HttpServletResponse response) throws Exception {
		if (log.isDebugEnabled()) log.debug("Entering 'saveRights' method");
		ActionForward forward = null;

		try {
			String id = sysRoleForm.getRecord().get("id");
			String rights = sysRoleForm.getRecord().get("rights");

			SysRoleService sysRoleService = (SysRoleService) getBean("sysRoleService");
			sysRoleService.saveRights(new Integer(id), rights);
			forward = returnForward(mapping, request, RETURN_NORMAL);
		}
		catch (Exception e) {
			e.printStackTrace();
			addMessage(sysRoleForm, "保存权限操作失败!");
			forward = mapping.findForward(RIGHTS);
		}
		return forward;
	}

	public ActionForward verify(ActionMapping mapping, SysRoleForm sysRoleForm, HttpServletRequest request, HttpServletResponse response) throws Exception {
		if (log.isDebugEnabled()) log.debug("Entering 'verifyRole' method");
		String id = request.getParameter("id");
		sysRoleForm.getRecord().put("id", id);

		try {
			SysResourceService sysResourceService = (SysResourceService) getBean("sysResourceService");
			PageQuery pageQuery = sysRoleForm.getQuery();
			pageQuery.setSelectParamId("GET_RIGHTS");
			pageQuery.setPageSize("0");
			sysResourceService.querySysResource(pageQuery);

			SysResourceRoleService sysResourceRoleService = (SysResourceRoleService) getBean("sysResourceRoleService");
			List rights = sysResourceRoleService.queryRoleRights(new Integer(id));
			if (rights != null) sysRoleForm.setRights(rights);

			SysRoleService sysRoleService = (SysRoleService) getBean("sysRoleService");
			SysRole sysRole = (SysRole) sysRoleService.findSysRole(new Integer(id));
			ConvertUtil.objectToMap(sysRoleForm.getRecord(), sysRole);
			initForm(sysRoleForm, VIEW);

			return mapping.findForward(VERIFY);
		}
		catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("err", e);
			return mapping.findForward(ERROR);
		}
	}

	public ActionForward saveVerify(ActionMapping mapping, SysRoleForm sysRoleForm, HttpServletRequest request, HttpServletResponse response) throws Exception {
		if (log.isDebugEnabled()) log.debug("Entering 'saveVerify' method");
		ActionForward forward = null;

		try {
			String id = sysRoleForm.getRecord().get("id");

			SysRoleService sysRoleService = (SysRoleService) getBean("sysRoleService");
			sysRoleService.saveVerify(new Integer(id));
			forward = returnForward(mapping, request, RETURN_NORMAL);
		}
		catch (Exception e) {
			e.printStackTrace();
			addMessage(sysRoleForm, "审核操作失败!");
			forward = mapping.findForward(VERIFY);
		}
		return forward;
	}

	public ActionForward searchRole(ActionMapping mapping, SysRoleForm sysRoleForm, HttpServletRequest request, HttpServletResponse response) throws Exception {
		if (log.isDebugEnabled()) log.debug("Entering 'searchRole' method");
		try {
			SysRoleService sysRoleService = (SysRoleService) getBean("sysRoleService");
			PageQuery pageQuery = sysRoleForm.getQuery();
			pageQuery.setSelectParamId("GET_SYSROLE_PAGE");
			String queryString = getQueryString(pageQuery);
			pageQuery.getParameters().put("queryString", queryString);
			sysRoleService.querySysRole(pageQuery);
			initForm(sysRoleForm, LIST);
			List<Map<String, String>> list = pageQuery.getRecordSet();
			for (int i = 0; i < list.size(); i++) {
				Map<String, String> map = list.get(i);
				String rs = (String) map.get("ROLESTATUS");
				if (rs == null) map.put("ROLESTATUS", "");
				else {
					map.put("ROLESTATUS", findCodeName(sysRoleForm, SysRole.COL_ROLE_STATUS, rs.trim()));
				}
			}
			setPage(sysRoleForm.getQuery());
		}
		catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return mapping.findForward(LIST);
	}

	public ActionForward addRole(ActionMapping mapping, SysRoleForm sysRoleForm, HttpServletRequest request, HttpServletResponse response) throws Exception {
		if (log.isDebugEnabled()) log.debug("Entering 'addRole' method");

		try {
			initForm(sysRoleForm, ADD);
			return mapping.findForward(ADD);
		}
		catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("err", e);
			return mapping.findForward(ERROR);
		}
	}

	public ActionForward saveRole(ActionMapping mapping, SysRoleForm sysRoleForm, HttpServletRequest request, HttpServletResponse response) throws Exception {
		if (log.isDebugEnabled()) log.debug("Entering 'saveRole' method");
		ActionForward forward = null;

		try {
			SysRole sysRole = new SysRole();
			ConvertUtil.mapToObject(sysRole, sysRoleForm.getRecord());
			SysRoleService sysRoleService = (SysRoleService) getBean("sysRoleService");
			sysRoleService.createSysRole(sysRole);
			forward = returnForward(mapping, request, RETURN_NORMAL);
		}
		catch (ServiceException e) {
			addMessage(sysRoleForm, e.getMessage());
			initForm(sysRoleForm, ADD);
			forward = mapping.findForward(ADD);
		}
		catch (Exception e) {
			e.printStackTrace();
			addMessage(sysRoleForm, "保存操作失败!");
			initForm(sysRoleForm, ADD);
			forward = mapping.findForward(ADD);
		}
		return forward;
	}

	public ActionForward editRole(ActionMapping mapping, SysRoleForm sysRoleForm, HttpServletRequest request, HttpServletResponse response) throws Exception {
		if (log.isDebugEnabled()) log.debug("Entering 'editRole' method");
		String id = request.getParameter("id");

		try {
			SysRoleService sysRoleService = (SysRoleService) getBean("sysRoleService");
			SysRole sysRole = (SysRole) sysRoleService.findSysRole(new Integer(id));
			ConvertUtil.objectToMap(sysRoleForm.getRecord(), sysRole);
			initForm(sysRoleForm, EDIT);
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

	public ActionForward updateRole(ActionMapping mapping, SysRoleForm sysRoleForm, HttpServletRequest request, HttpServletResponse response) throws Exception {
		if (log.isDebugEnabled()) log.debug("Entering 'updateRole' method");

		ActionForward forward = null;

		try {
			SysRole sysRole = new SysRole();
			ConvertUtil.mapToObject(sysRole, sysRoleForm.getRecord());
			SysRoleService sysRoleService = (SysRoleService) getBean("sysRoleService");
			sysRoleService.updateSysRole(sysRole);
			forward = returnForward(mapping, request, RETURN_NORMAL);
		}
		catch (ServiceException e) {
			addMessage(sysRoleForm, e.getMessage());
			initForm(sysRoleForm, EDIT);
			forward = mapping.findForward(EDIT);
		}
		catch (Exception e) {
			e.printStackTrace();
			addMessage(sysRoleForm, "保存操作失败!");
			initForm(sysRoleForm, EDIT);
			forward = mapping.findForward(EDIT);
		}
		return forward;
	}

	public ActionForward deleteRole(ActionMapping mapping, SysRoleForm sysRoleForm, HttpServletRequest request, HttpServletResponse response) throws Exception {
		if (log.isDebugEnabled()) log.debug("Entering 'deleteRole' method");
		try {
			SysRoleService sysRoleService = (SysRoleService) getBean("sysRoleService");
			String del_ids = request.getParameter("ids");
			if (del_ids != null && del_ids.trim().length() > 0) {
				sysRoleService.removeSysRoles(parseId(del_ids.split(";")));
			}
		}
		catch (ServiceException e) {
			addMessage(sysRoleForm, e.getMessage());
		}
		catch (Exception e) {
			addMessage(sysRoleForm, "删除操作失败!");
		}
		return searchRole(mapping, sysRoleForm, request, response);
	}

	public ActionForward startRole(ActionMapping mapping, SysRoleForm sysRoleForm, HttpServletRequest request, HttpServletResponse response) throws Exception {
		if (log.isDebugEnabled()) log.debug("Entering 'startRole' method");
		try {
			SysRoleService sysRoleService = (SysRoleService) getBean("sysRoleService");
			String del_ids = request.getParameter("ids");
			if (del_ids != null && del_ids.trim().length() > 0) {
				sysRoleService.startSysRoles(parseId(del_ids.split(";")));
			}
		}
		catch (ServiceException e) {
			addMessage(sysRoleForm, e.getMessage());
		}
		catch (Exception e) {
			addMessage(sysRoleForm, "启用操作失败!");
		}
		return searchRole(mapping, sysRoleForm, request, response);
	}

	public ActionForward stopRole(ActionMapping mapping, SysRoleForm sysRoleForm, HttpServletRequest request, HttpServletResponse response) throws Exception {
		if (log.isDebugEnabled()) log.debug("Entering 'stopRole' method");
		try {
			SysRoleService sysRoleService = (SysRoleService) getBean("sysRoleService");
			String del_ids = request.getParameter("ids");
			if (del_ids != null && del_ids.trim().length() > 0) {
				sysRoleService.stopSysRoles(parseId(del_ids.split(";")));
			}
		}
		catch (ServiceException e) {
			addMessage(sysRoleForm, e.getMessage());
		}
		catch (Exception e) {
			addMessage(sysRoleForm, "暂停操作失败!");
		}
		return searchRole(mapping, sysRoleForm, request, response);
	}

	private String getQueryString(PageQuery pageQuery) {
		StringBuffer buffer = new StringBuffer();
		Map fields = pageQuery.getParameters();
		String str = (String) fields.get(SysRole.PROP_ROLE_CODE);
		if (str != null && !"".equals(str)) {
			if (str.contains("%")) buffer.append(" and " + SysRole.COL_ROLE_CODE + "  like '" + str + "'");
			else buffer.append(" and " + SysRole.COL_ROLE_CODE + "  like '%" + str + "%'");
		}
		str = (String) fields.get(SysRole.PROP_ROLE_NAME);
		if (str != null && !"".equals(str)) {
			buffer.append(" and " + SysRole.COL_ROLE_NAME + "  like '%" + str + "%'");
		}
		str = (String) fields.get(SysRole.PROP_ROLE_STATUS);
		if (str != null && !"".equals(str)) {
			buffer.append(" and " + SysRole.COL_ROLE_STATUS + " =" + str);
		}
		return buffer.toString();
	}

	private void initForm(SysRoleForm roleForm, String action) {
		setCode(roleForm, SysRole.COL_ROLE_STATUS);
		roleForm.getRecord().put("roleStatusName", findCodeName(roleForm, SysRole.COL_ROLE_STATUS, roleForm.getRecord().get("roleStatus")));
		if (ADD.equalsIgnoreCase(action)) {
			roleForm.getRecord().put(SysRole.PROP_ROLE_STATUS, "1");
		}
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
