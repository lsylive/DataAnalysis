package com.liusy.datapp.web.system.org.action;

import java.io.PrintWriter;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.liusy.core.util.Const;
import com.liusy.core.util.JsonUtil;
import com.liusy.core.util.Session;
import com.liusy.dataapplatform.base.exception.ServiceException;
import com.liusy.dataapplatform.base.exception.WebException;
import com.liusy.dataapplatform.base.util.ConvertUtil;
import com.liusy.dataapplatform.base.util.PageQuery;
import com.liusy.datapp.model.resource.ResourceColumn;
import com.liusy.datapp.model.resource.ResourceColumnUserCfg;
import com.liusy.datapp.model.resource.ResourceTable;
import com.liusy.datapp.model.resource.ResourceTableUserCfg;
import com.liusy.datapp.model.system.org.SysDept;
import com.liusy.datapp.model.system.org.SysOrg;
import com.liusy.datapp.model.system.org.SysRole;
import com.liusy.datapp.model.system.org.SysUser;
import com.liusy.datapp.service.resource.ResourceColumnUserCfgService;
import com.liusy.datapp.service.resource.ResourceTableUserCfgService;
import com.liusy.datapp.service.system.config.SysResourceService;
import com.liusy.datapp.service.system.org.SysDeptService;
import com.liusy.datapp.service.system.org.SysOrgService;
import com.liusy.datapp.service.system.org.SysResourceUserService;
import com.liusy.datapp.service.system.org.SysRoleService;
import com.liusy.datapp.service.system.org.SysUserService;
import com.liusy.datapp.web.BaseAction;
import com.liusy.datapp.web.system.org.form.SysRoleForm;
import com.liusy.datapp.web.system.org.form.SysUserForm;

public class SysUserAction extends BaseAction {

	private static final String	START			= "START";			// �����˻�

	private static final String	STOP			= "STOP";			// ��ͣ�˻�

	private static final String	PWD			= "PWD";			// �޸�����

	private static final String	SAVEPWD		= "SAVEPWD";		// ��������

	private static final String	RIGHTS		= "RIGHTS";		// ����Ȩ��

	private static final String	SAVERIGHTS	= "SAVERIGHTS";	// ����Ȩ��

	private static final String	VERIFY		= "VERIFY";		// ���

	private static final String	SAVEVERIFY	= "SAVEVERIFY";	// ������˽��

	private static final String	GETRIGHTS	= "GETRIGHTS";	// ȡ��Ȩ����

	private static final String	GETROLES		= "GETROLES";		// ȡ�ý�ɫ

	private static final String	CHECKUSER	= "CHECKUSER";
	
	private static final String STARTSPACE	= "STARTSPACE";
	
	private static final String STOPTSPACE	= "STOPSPACE";
	
	private static final String DELSPACE	= "DELSPACE";

	private static final String RESOURCE	= "RESOURCE";//������Դ
	
	private static final String SAVERESOURCE = "SAVERESOURCE";//������Դ
	
	private static final String GETRESOURCETREE		= "GETRESOURCETREE";//��ȡ��Դ��
	
	private static final String GETRESOURCEFORM	= "GETRESOURCEFORM";//��ȡ��Դ��
	
	@Override
	public ActionForward doExecute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		String action = request.getParameter("action");
		if (action == null) action = LIST;
		if (log.isDebugEnabled()) log.debug("action:" + action);
		ActionForward forward;
		SysUserForm sysUserForm = (SysUserForm) form;

		try {
			if (LIST.equalsIgnoreCase(action)) forward = searchUser(mapping, sysUserForm, request, response); // �򿪲�ѯ�б�ҳ��
			else if (ADD.equalsIgnoreCase(action)) forward = addUser(mapping, sysUserForm, request, response);// ������ҳ��
			else if (SAVE.equalsIgnoreCase(action)) forward = saveUser(mapping, sysUserForm, request, response);// ������������
			else if (EDIT.equalsIgnoreCase(action)) forward = editUser(mapping, sysUserForm, request, response);// ���޸�ҳ��
			else if (UPDATE.equalsIgnoreCase(action)) forward = updateUser(mapping, sysUserForm, request, response);// �����޸�����
			else if (DELETE.equalsIgnoreCase(action)) forward = deleteUser(mapping, sysUserForm, request, response);// ɾ��
			else if (START.equalsIgnoreCase(action)) forward = startUser(mapping, sysUserForm, request, response);// �����û�
			else if (STOP.equalsIgnoreCase(action)) forward = stopUser(mapping, sysUserForm, request, response);// ��ͣ�û�
			else if (PWD.equalsIgnoreCase(action)) forward = changePwd(mapping, sysUserForm, request, response);// �޸�����
			else if (SAVEPWD.equalsIgnoreCase(action)) forward = savePwd(mapping, sysUserForm, request, response);// ��������
			else if (VIEW.equalsIgnoreCase(action)) forward = viewUser(mapping, sysUserForm, request, response);// �鿴�û�
			else if (RIGHTS.equalsIgnoreCase(action)) forward = rightsUser(mapping, sysUserForm, request, response);// �����û�Ȩ��
			else if (GETRIGHTS.equalsIgnoreCase(action)) forward = getRights(mapping, sysUserForm, request, response);// ȡ��Ȩ����
			else if (SAVERIGHTS.equalsIgnoreCase(action)) forward = saveRights(mapping, sysUserForm, request, response);// ����Ȩ��
			else if (VERIFY.equalsIgnoreCase(action)) forward = verifyUser(mapping, sysUserForm, request, response);// ����û�
			else if (SAVEVERIFY.equalsIgnoreCase(action)) forward = saveVerify(mapping, sysUserForm, request, response);// ������˽��
			else if (GETROLES.equalsIgnoreCase(action)) forward = getRoles(mapping, sysUserForm, request, response);// ȡ�ý�ɫ
			else if (CHECKUSER.equalsIgnoreCase(action)) forward = checkUser(mapping, sysUserForm, request, response);
			else if (RESOURCE.equalsIgnoreCase(action)) forward = resourceUser(mapping, sysUserForm, request, response);
			else if (GETRESOURCETREE.equalsIgnoreCase(action)) forward = getResourceTree(mapping, sysUserForm, request, response);
			else if (GETRESOURCEFORM.equalsIgnoreCase(action)) forward = getResourceForm(mapping, sysUserForm, request, response);
			else if (SAVERESOURCE.equalsIgnoreCase(action)) forward = saveResourceUser(mapping, sysUserForm, request, response);
			else {
				request.setAttribute("err", new WebException("�Ҳ�����action������" + action));
				forward = mapping.findForward(ERROR);// �Ҳ������ʵ�action
			}
		}
		catch (Exception e) {// ����ϵͳ����
			e.printStackTrace();
			request.setAttribute("err", e);
			forward = mapping.findForward(ERROR);
		}
		return forward;
	}

	public ActionForward saveResourceUser(ActionMapping mapping, SysUserForm sysUserForm, HttpServletRequest request, HttpServletResponse response)throws Exception{
		try
		{
			if (log.isDebugEnabled()) log.debug("Entering 'saveResourceUser' method");
			String userId = sysUserForm.getRecord().get("userId");
			String tableId = sysUserForm.getRecord().get("tableId");
			String tableLevel = sysUserForm.getRecord().get("tableLevel");
			if (tableLevel==null||"".equals(tableLevel))tableLevel = "0";
			String[] cols = sysUserForm.getColumnId();
			String[] levels = sysUserForm.getColumnLevel();
			
			//����tableId��userId���ҿ��û���Դ����ֶ���Դ���Ƿ��ж�Ӧ�ļ�¼��������update��������add
			ResourceTableUserCfgService RTUCService = (ResourceTableUserCfgService)getBean("resourceTableUserCfgService");
			ResourceColumnUserCfgService RCUCService = (ResourceColumnUserCfgService)getBean("resourceColumnUserCfgService");
			ResourceTableUserCfg rtuc = RTUCService.findByTableIdAndUserId(Integer.valueOf(tableId), Integer.valueOf(userId));
			List<ResourceColumnUserCfg> colCFG = RCUCService.findByTableAndUserId(Integer.valueOf(tableId), Integer.valueOf(userId));
			
			//�ȴ����ȼ�
			if (rtuc!=null)
			{
				rtuc.setSecurityLevel(tableLevel);
				RTUCService.updateResourceTableUserCfg(rtuc);
			}else if(!"0".equals(tableLevel)){
				rtuc = new ResourceTableUserCfg();
				rtuc.setSecurityLevel(tableLevel);
				rtuc.setTableId(Integer.valueOf(tableId));
				rtuc.setUserId(Integer.valueOf(userId));
				RTUCService.createResourceTableUserCfg(rtuc);
			}
			
			//�ٴ����ֶεȼ�

			if (cols!=null&&cols.length>0)
			{
				for (int i = 0; i < cols.length; i++)
				{
					if (colCFG!=null&&!colCFG.isEmpty())
					{
						int length = colCFG.size();
						for (int j = 0; j < length; j++)
						{
							ResourceColumnUserCfg rcoltemp = colCFG.get(j);
							if (cols[i].equals(rcoltemp.getColumnId().toString()))
							{
								rcoltemp.setSecurityLevel(levels[i]);
								levels[i] = "0";
								RCUCService.updateResourceColumnUserCfg(rcoltemp);
							}
						}
					}
					
					if (!"0".equals(levels[i])) {
						ResourceColumnUserCfg rcoltemp = new ResourceColumnUserCfg();
						rcoltemp.setColumnId(Integer.valueOf(cols[i]));
						rcoltemp.setSecurityLevel(levels[i]);
						rcoltemp.setTableId(Integer.valueOf(tableId));
						rcoltemp.setUserId(Integer.valueOf(userId));
						RCUCService.createResourceColumnUserCfg(rcoltemp);
					}
				}
			}
			return null;
		}
		catch (Exception e)
		{
			e.printStackTrace();
			request.setAttribute("err", e);
			return mapping.findForward(ERROR);
		}
		
	}
	
	public ActionForward resourceUser(ActionMapping mapping, SysUserForm sysUserForm, HttpServletRequest request, HttpServletResponse response)throws Exception{
		if (log.isDebugEnabled()) log.debug("Entering 'resourceUser' method");
		String userId = request.getParameter("userId");
		sysUserForm.getRecord().put("userId", userId);
		return mapping.findForward(RESOURCE);
	}
	
	public ActionForward getResourceForm(ActionMapping mapping, SysUserForm sysUserForm, HttpServletRequest request, HttpServletResponse response)throws Exception{
		if (log.isDebugEnabled()) log.debug("Entering 'getResourceForm' method");
		try
		{
			String tableId = request.getParameter("tableId");
			String userId = request.getParameter("userId");
			sysUserForm.getRecord().put("userId", userId);
			sysUserForm.getRecord().put("tableId", tableId);
			SysUserService sysUserService = (SysUserService) getBean("sysUserService");	
			
			//�����û���Դ����ֶ���Դ�����Ƿ��е�ǰ��ĺ��û��ļ�¼���������øü�¼��ʾ
			ResourceTableUserCfgService RTUCService = (ResourceTableUserCfgService)getBean("resourceTableUserCfgService");
			ResourceColumnUserCfgService RCUCService = (ResourceColumnUserCfgService)getBean("resourceColumnUserCfgService");
			ResourceTableUserCfg rtuc = RTUCService.findByTableIdAndUserId(Integer.valueOf(tableId), Integer.valueOf(userId));
			List<ResourceColumnUserCfg> colCFG = RCUCService.findByTableAndUserId(Integer.valueOf(tableId), Integer.valueOf(userId));
			
			ResourceTable table = sysUserService.getTableById(Integer.valueOf(tableId));
			List<ResourceColumn> columns = sysUserService.getColumnsByTableId(Integer.valueOf(tableId));
			
			//����Ԥ����
			if (rtuc!=null)
			{
				sysUserForm.getRecord().put("tableLevel", rtuc.getSecurityLevel());
			}
			
			String colLevels = "";
			if (colCFG!=null&&!colCFG.isEmpty())
			{
				int length = columns.size();
				loop: for (int i = 0; i < length; i++)
				{
					ResourceColumn col = columns.get(i);
					int len = colCFG.size();
					for (int j = 0; j < len; j++)
					{
						ResourceColumnUserCfg cfg = colCFG.get(j);
						if (cfg.getColumnId().equals(col.getId()))
						{
							colLevels += cfg.getSecurityLevel()+",";
							continue loop;
						}
					}
					colLevels += "0,";					
				}
			}
			
			setCode(sysUserForm, "SECURITY_LEVEL");
			sysUserForm.getRecord().put("colLevels", colLevels);
			request.setAttribute("table", table);
			request.setAttribute("columns", columns);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			request.setAttribute("err", e);
			return mapping.findForward(ERROR);
		}
		return mapping.findForward(GETRESOURCEFORM);
	}
	
	public ActionForward getResourceTree(ActionMapping mapping, SysUserForm sysUserForm, HttpServletRequest request, HttpServletResponse response)throws Exception{
		if (log.isDebugEnabled()) log.debug("Entering 'getResourceTree' method");
		String id = request.getParameter("id");
		if (id==null)
		{
			id = "0";
		}
		String cityCode = getCityCode(request);
		
		SysUserService sysUserService = (SysUserService) getBean("sysUserService");		
		String xml = sysUserService.getResourceTreeStr(id, cityCode);
		
		response.setContentType("text/xml;charset=UTF-8");
		response.setHeader("Cache_Control", "no-cache");
		PrintWriter writer = response.getWriter();
		writer.write(xml);
		writer.close();
		return null;
	}
	
	public ActionForward checkUser(ActionMapping mapping, SysUserForm sysUserForm, HttpServletRequest request, HttpServletResponse response) throws Exception {
		if (log.isDebugEnabled()) log.debug("Entering 'checkUser' method");
		String id = request.getParameter("id");
		response.setContentType("text/xml;charset=UTF-8");
		response.setHeader("Cache_Control", "no-cache");

		try {
			SysUserService sysUserService = (SysUserService) getBean("sysUserService");
			SysUser user = sysUserService.findSysUser(new Integer(id));

			if (SysUserService.SYSUSER.equals(user.getAccountType())) response.getWriter().write("Y");
			else response.getWriter().write("N");
		}
		catch (Exception e) {
			e.printStackTrace();
			response.getWriter().write("ERROR");
		}
		response.getWriter().close();
		return null;
	}

	public ActionForward getRoles(ActionMapping mapping, SysUserForm sysUserForm, HttpServletRequest request, HttpServletResponse response) throws Exception {
		if (log.isDebugEnabled()) log.debug("Entering 'getRoles' method");
		String id = ";" + request.getParameter("roleIds");
		sysUserForm.getRecord().put("ids", id);
		response.setContentType("text/xml;charset=UTF-8");
		response.setHeader("Cache_Control", "no-cache");
		try {
			SysRoleService sysRoleService = (SysRoleService) getBean("sysRoleService");
			PageQuery pageQuery = sysUserForm.getQuery();
			pageQuery.setSelectParamId("GET_USER_ROLES");
			pageQuery.setPageSize("0");
			sysRoleService.querySysRole(pageQuery);
			List<Map<String, String>> list = pageQuery.getRecordSet();
			for (int i = 0; i < list.size(); i++) {
				Map<String, String> map = list.get(i);
				if (id.indexOf(";" + map.get("ID") + ";") > -1) map.put("CHECKED", "checked");
				else map.put("CHECKED", "");
			}
			return mapping.findForward(GETROLES);
		}
		catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("err", e);
			return mapping.findForward(ERROR);
		}
	}

	public ActionForward getRights(ActionMapping mapping, SysUserForm sysUserForm, HttpServletRequest request, HttpServletResponse response) throws Exception {
		if (log.isDebugEnabled()) log.debug("Entering 'getRights' method");
		response.setContentType("text/xml;charset=UTF-8");
		response.setHeader("Cache_Control", "no-cache");

		try {
			SysResourceService sysResourceService = (SysResourceService) getBean("sysResourceService");
			PageQuery pageQuery = new PageQuery();
			pageQuery.setSelectParamId("GET_RIGHTS");
			pageQuery.setPageSize("0");
			pageQuery.getParameters().put("queryString", "");
			sysResourceService.querySysResource(pageQuery);
			List rights = pageQuery.getRecordSet();
			response.getWriter().write(getNodes(rights));
			response.getWriter().close();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public ActionForward rightsUser(ActionMapping mapping, SysUserForm sysUserForm, HttpServletRequest request, HttpServletResponse response) throws Exception {
		if (log.isDebugEnabled()) log.debug("Entering 'rightsUser' method");
		String id = request.getParameter("id");
		sysUserForm.getRecord().put("id", id);

		try {
			SysResourceService sysResourceService = (SysResourceService) getBean("sysResourceService");
			PageQuery pageQuery = sysUserForm.getQuery();
			pageQuery.setSelectParamId("GET_RIGHTS");
			pageQuery.setPageSize("0");
			sysResourceService.querySysResource(pageQuery);

			SysResourceUserService sysResourceUserService = (SysResourceUserService) getBean("sysResourceUserService");
			List rights = sysResourceUserService.queryUserRights(new Integer(id));
			if (rights != null) sysUserForm.setRights(rights);

			return mapping.findForward(RIGHTS);
		}
		catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("err", e);
			return mapping.findForward(ERROR);
		}
	}

	public ActionForward saveRights(ActionMapping mapping, SysUserForm sysUserForm, HttpServletRequest request, HttpServletResponse response) throws Exception {
		if (log.isDebugEnabled()) log.debug("Entering 'saveRights' method");
		ActionForward forward = null;

		try {
			String id = sysUserForm.getRecord().get("id");
			String rights = sysUserForm.getRecord().get("rights");

			SysUserService sysUserService = (SysUserService) getBean("sysUserService");
			sysUserService.saveRights(new Integer(id), rights);
			forward = returnForward(mapping, request, RETURN_NORMAL);
		}
		catch (Exception e) {
			e.printStackTrace();
			addMessage(sysUserForm, "����Ȩ�޲���ʧ��!");
			forward = mapping.findForward(RIGHTS);
		}
		return forward;
	}

	public ActionForward verifyUser(ActionMapping mapping, SysUserForm sysUserForm, HttpServletRequest request, HttpServletResponse response) throws Exception {
		if (log.isDebugEnabled()) log.debug("Entering 'verifyUser' method");
		String id = request.getParameter("id");
		sysUserForm.getRecord().put("id", id);

		try {
			SysResourceService sysResourceService = (SysResourceService) getBean("sysResourceService");
			PageQuery pageQuery = sysUserForm.getQuery();
			pageQuery.setSelectParamId("GET_RIGHTS");
			pageQuery.setPageSize("0");
			sysResourceService.querySysResource(pageQuery);

			SysResourceUserService sysResourceUserService = (SysResourceUserService) getBean("sysResourceUserService");
			List rights = sysResourceUserService.queryUserRights(new Integer(id));
			if (rights != null) sysUserForm.setRights(rights);

			SysUserService sysUserService = (SysUserService) getBean("sysUserService");
			SysUser sysUser = (SysUser) sysUserService.findSysUser(new Integer(id));
			ConvertUtil.objectToMap(sysUserForm.getRecord(), sysUser);
			initForm(sysUserForm, VIEW);

			PageQuery pq = new PageQuery();
			pq.setSelectParamId("GET_USER_ROLES_R");
			pq.getParameters().put("queryString", "a.user_id=" + id);
			List<Map<String, String>> roles = sysUserService.findUserRoles(pq).getRecordSet();
			sysUserForm.setRoles(roles);

			return mapping.findForward(VERIFY);
		}
		catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("err", e);
			return mapping.findForward(ERROR);
		}
	}

	public ActionForward saveVerify(ActionMapping mapping, SysUserForm sysUserForm, HttpServletRequest request, HttpServletResponse response) throws Exception {
		if (log.isDebugEnabled()) log.debug("Entering 'saveVerify' method");
		ActionForward forward = null;

		try {
			String id = sysUserForm.getRecord().get("id");

			SysUserService sysUserService = (SysUserService) getBean("sysUserService");
			sysUserService.saveVerify(new Integer(id));
			forward = returnForward(mapping, request, RETURN_NORMAL);
		}
		catch (Exception e) {
			e.printStackTrace();
			addMessage(sysUserForm, "����Ȩ�޲���ʧ��!");
			forward = mapping.findForward(RIGHTS);
		}
		return forward;
	}

	public ActionForward changePwd(ActionMapping mapping, SysUserForm sysUserForm, HttpServletRequest request, HttpServletResponse response) throws Exception {
		if (log.isDebugEnabled()) log.debug("Entering 'changePwd' method");
		String id = request.getParameter("id");

		try {
			sysUserForm.getRecord().put("id", id);
			return mapping.findForward(PWD);
		}
		catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("err", e);
			return mapping.findForward(ERROR);
		}
	}

	public ActionForward savePwd(ActionMapping mapping, SysUserForm sysUserForm, HttpServletRequest request, HttpServletResponse response) throws Exception {
		if (log.isDebugEnabled()) log.debug("Entering 'savePwd' method");
		ActionForward forward = null;

		try {
			String id = sysUserForm.getRecord().get("id");
			String pwd = sysUserForm.getRecord().get("pwd");

			SysUserService sysUserService = (SysUserService) getBean("sysUserService");
			sysUserService.savePwd(new Integer(id), pwd);
			forward = returnForward(mapping, request, RETURN_NORMAL);
		}
		catch (Exception e) {
			e.printStackTrace();
			addMessage(sysUserForm, "�޸��������ʧ��!");
			sysUserForm.getRecord().put("pwd", "");
			sysUserForm.getRecord().put("confirmPwd", "");
			forward = mapping.findForward(PWD);
		}
		return forward;
	}

	public ActionForward addUser(ActionMapping mapping, SysUserForm sysUserForm, HttpServletRequest request, HttpServletResponse response) throws Exception {
		if (log.isDebugEnabled()) log.debug("Entering 'addUser' method");

		try {
			initForm(sysUserForm, ADD);
			return mapping.findForward(ADD);
		}
		catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("err", e);
			return mapping.findForward(ERROR);
		}
	}

	public ActionForward viewUser(ActionMapping mapping, SysUserForm sysUserForm, HttpServletRequest request, HttpServletResponse response) throws Exception {
		if (log.isDebugEnabled()) log.debug("Entering 'viewUser' method");
		String id = request.getParameter("id");

		try {
			SysUserService sysUserService = (SysUserService) getBean("sysUserService");
			SysUser sysUser = (SysUser) sysUserService.findSysUser(new Integer(id));
			ConvertUtil.objectToMap(sysUserForm.getRecord(), sysUser);

			PageQuery pageQuery = sysUserForm.getQuery();
			pageQuery.setSelectParamId("GET_USER_ROLES_R");
			pageQuery.getParameters().put("queryString", "a.user_id=" + id);
			List<Map<String, String>> roles = sysUserService.findUserRoles(pageQuery).getRecordSet();

			String roleIds = "";
			String roleNames = "";
			for (int i = 0; i < roles.size(); i++) {
				Map<String, String> sur = roles.get(i);
				roleIds += sur.get("ROLEID") + ";";
				roleNames += sur.get("ROLENAME") + ";";
			}
			sysUserForm.getRecord().put("roleIds", roleIds);
			sysUserForm.getRecord().put("roleNames", roleNames);

			initForm(sysUserForm, VIEW);
			return mapping.findForward(VIEW);
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

	public ActionForward editUser(ActionMapping mapping, SysUserForm sysUserForm, HttpServletRequest request, HttpServletResponse response) throws Exception {
		if (log.isDebugEnabled()) log.debug("Entering 'editUser' method");
		String id = request.getParameter("id");

		try {
			SysUserService sysUserService = (SysUserService) getBean("sysUserService");
			SysUser sysUser = (SysUser) sysUserService.findSysUser(new Integer(id));
			ConvertUtil.objectToMap(sysUserForm.getRecord(), sysUser);

			PageQuery pageQuery = sysUserForm.getQuery();
			pageQuery.setSelectParamId("GET_USER_ROLES_R");
			pageQuery.getParameters().put("queryString", "a.user_id=" + id);
			List<Map<String, String>> roles = sysUserService.findUserRoles(pageQuery).getRecordSet();

			String roleIds = "";
			String roleNames = "";
			for (int i = 0; i < roles.size(); i++) {
				Map<String, String> sur = roles.get(i);
				roleIds += sur.get("ROLEID") + ";";
				roleNames += sur.get("ROLENAME") + ";";
			}
			sysUserForm.getRecord().put("roleIds", roleIds);
			sysUserForm.getRecord().put("roleNames", roleNames);

			initForm(sysUserForm, EDIT);
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

	public ActionForward saveUser(ActionMapping mapping, SysUserForm sysUserForm, HttpServletRequest request, HttpServletResponse response) throws Exception {
		if (log.isDebugEnabled()) log.debug("Entering 'saveUser' method");
		ActionForward forward = null;

		try {
			SysUser sysUser = new SysUser();
			ConvertUtil.mapToObject(sysUser, sysUserForm.getRecord());
			sysUser.setUpdatedate(new Date());
			String roleIds = sysUserForm.getRecord().get("roleIds");
			SysUserService sysUserService = (SysUserService) getBean("sysUserService");
			sysUserService.createSysUser(sysUser, roleIds);
			forward = returnForward(mapping, request, RETURN_NORMAL);
		}
		catch (ServiceException e) {
			addMessage(sysUserForm, e.getMessage());
			initForm(sysUserForm, ADD);
			forward = mapping.findForward(ADD);
		}
		catch (Exception e) {
			e.printStackTrace();
			addMessage(sysUserForm, "�������ʧ��!");
			initForm(sysUserForm, ADD);
			forward = mapping.findForward(ADD);
		}
		return forward;
	}

	public ActionForward updateUser(ActionMapping mapping, SysUserForm sysUserForm, HttpServletRequest request, HttpServletResponse response) throws Exception {
		if (log.isDebugEnabled()) log.debug("Entering 'updateUser' method");

		ActionForward forward = null;

		try {
			SysUserService sysUserService = (SysUserService) getBean("sysUserService");
			SysUser sysUser = new SysUser();
			ConvertUtil.mapToObject(sysUser, sysUserForm.getRecord());
			sysUser.setUpdatedate(new Date());
			String roleIds = sysUserForm.getRecord().get("roleIds");
			sysUserService.updateSysUser(sysUser, roleIds);
			forward = returnForward(mapping, request, RETURN_NORMAL);
		}
		catch (ServiceException e) {
			addMessage(sysUserForm, e.getMessage());
			initForm(sysUserForm, EDIT);
			forward = mapping.findForward(EDIT);
		}
		catch (Exception e) {
			e.printStackTrace();
			addMessage(sysUserForm, "�������ʧ��!");
			initForm(sysUserForm, EDIT);
			forward = mapping.findForward(EDIT);
		}
		return forward;
	}

	public ActionForward deleteUser(ActionMapping mapping, SysUserForm sysUserForm, HttpServletRequest request, HttpServletResponse response) throws Exception {
		if (log.isDebugEnabled()) log.debug("Entering 'deleteUser' method");
		try {
			SysUserService sysUserInfoService = (SysUserService) getBean("sysUserService");
			String del_ids = request.getParameter("ids");
			if (del_ids != null && del_ids.trim().length() > 0) {
				sysUserInfoService.removeSysUsers(parseId(del_ids.split(";")));
			}
		}
		catch (Exception e) {
			e.printStackTrace();
			addMessage(sysUserForm, "ɾ������ʧ��!");
		}
		return searchUser(mapping, sysUserForm, request, response);
	}

	public ActionForward startUser(ActionMapping mapping, SysUserForm sysUserForm, HttpServletRequest request, HttpServletResponse response) throws Exception {
		if (log.isDebugEnabled()) log.debug("Entering 'deleteUser' method");
		try {
			SysUserService sysUserInfoService = (SysUserService) getBean("sysUserService");
			String del_ids = request.getParameter("ids");
			if (del_ids != null && del_ids.trim().length() > 0) {
				sysUserInfoService.startSysUsers(parseId(del_ids.split(";")));
			}
		}
		catch (Exception e) {
			e.printStackTrace();
			addMessage(sysUserForm, "���ò���ʧ��!");
		}
		return searchUser(mapping, sysUserForm, request, response);
	}

	public ActionForward stopUser(ActionMapping mapping, SysUserForm sysUserForm, HttpServletRequest request, HttpServletResponse response) throws Exception {
		if (log.isDebugEnabled()) log.debug("Entering 'deleteUser' method");
		try {
			SysUserService sysUserInfoService = (SysUserService) getBean("sysUserService");
			String del_ids = request.getParameter("ids");
			if (del_ids != null && del_ids.trim().length() > 0) {
				sysUserInfoService.stopSysUsers(parseId(del_ids.split(";")));
			}
		}
		catch (Exception e) {
			e.printStackTrace();
			addMessage(sysUserForm, "��ͣ����ʧ��!");
		}
		return searchUser(mapping, sysUserForm, request, response);
	}

	public ActionForward searchUser(ActionMapping mapping, SysUserForm sysUserForm, HttpServletRequest request, HttpServletResponse response) throws Exception {
		if (log.isDebugEnabled()) log.debug("Entering 'searchUser' method");
		try {
			SysUserService sysUserInfoService = (SysUserService) getBean("sysUserService");
			PageQuery pageQuery = sysUserForm.getQuery();
			pageQuery.setSelectParamId("GET_SYSUSER_PAGE");
			String queryString = getQueryString(pageQuery);
			pageQuery.getParameters().put("queryString", queryString);
			if (pageQuery.getOrder() == null || "".equals(pageQuery.getOrder().trim())) {
				pageQuery.setOrder("id");
				pageQuery.setOrderDirection("desc");
			}
			sysUserInfoService.querySysUser(pageQuery);
			initForm(sysUserForm, LIST);
			List<Map<String, String>> list = pageQuery.getRecordSet();
			for (int i = 0; i < list.size(); i++) {
				Map<String, String> map = list.get(i);
				String us = (String) map.get("USERSTATUS");
				if (us == null) map.put("USERSTATUS", "");
				else map.put("USERSTATUS", findCodeName(sysUserForm, "USER_STATUS", us.trim()));
				String dept = (String) map.get("DEPTID");
				if (dept == null) map.put("DEPTID", "");
				else map.put("DEPTID", findCodeName(sysUserForm, "depts", dept.trim()));
				String org = (String) map.get("ORGID");
				if (org == null) map.put("ORGID", "");
				else map.put("ORGID", findCodeName(sysUserForm, "orgs", org.trim()));

			}
			setPage(sysUserForm.getQuery());
		}
		catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("err", e);
			return mapping.findForward(ERROR);
		}
		return mapping.findForward(LIST);
	}
	

	@Deprecated
	public void searchUserInfoAjax(SysUserForm sysUserForm, HttpServletRequest request, HttpServletResponse response) throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'searchModu' method");
		}
		try {

			SysUserService sysUserService = (SysUserService) getBean("sysUserService");
			PageQuery pageQuery = sysUserForm.getQuery();
			sysUserService.querySysUser(pageQuery);
			JsonUtil.JsonGridOutput(pageQuery, response);
		}
		catch (Exception e) {

			e.printStackTrace();
			throw e;
		}
	}

	private java.io.Serializable[] parseId(String[] ids) throws Exception {
		if (ids == null || ids.length == 0) {
			throw new Exception("�Ƿ�����༭ҳ��");
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

	private void initForm(SysUserForm userForm, String action) {
		setCode(userForm, "USER_STATUS,ADMIN_LEVEL");
		userForm.getRecord().put("orgId", "1");
		SysDeptService sysDeptService = (SysDeptService) getBean("sysDeptService");
		List<SysDept> depts = sysDeptService.findAllDept();//findSysDepts(userForm.getRecord().get("orgId"));
		setCode(userForm, "depts", depts, SysDept.PROP_DEPT_NAME, SysDept.PROP_ID);
		SysOrgService sysOrgService = (SysOrgService) getBean("sysOrgService");
		List<SysOrg> orgs = sysOrgService.findSysOrgAll();
		setCode(userForm, "orgs", orgs, SysOrg.PROP_ORG_NAME, SysOrg.PROP_ID);

		SysUserService sysUserService = (SysUserService)getBean("sysUserService");
		if (ADD.equalsIgnoreCase(action)) {
			
			userForm.getRecord().put("userStatus", "1");
			int max_order = sysUserService.maxOrder()+1;
			userForm.getRecord().put("orderNo", String.valueOf(max_order));
		}
		else if (VIEW.equalsIgnoreCase(action)) {
			userForm.getRecord().put("orgName", findCodeName(userForm, "orgs", userForm.getRecord().get("orgId")));
			userForm.getRecord().put("deptName", findCodeName(userForm, "depts", userForm.getRecord().get("deptId")));
			userForm.getRecord().put("adminLevelName", findCodeName(userForm, "ADMIN_LEVEL", userForm.getRecord().get("adminLevel")));
			userForm.getRecord().put("userStatusName", findCodeName(userForm, "USER_STATUS", userForm.getRecord().get("userStatus")));
			String remark = userForm.getRecord().get("remark");
			if (remark != null) {
				// remark = remark.replace(" ", "&nbsp;");
				// System.out.println(remark);
				remark = remark.replace("\r", "<br>");
				userForm.getRecord().put("remark", remark);
			}
		}
	}

	private String getQueryString(PageQuery pageQuery) {
		StringBuffer buffer = new StringBuffer();
		Map fields = pageQuery.getParameters();
		String str = (String) fields.get("userAccount");
		if (str != null && !"".equals(str)) {
			if (str.contains("%")) buffer.append(" and " + SysUser.COL_USER_ACCOUNT + "  like '" + str + "'");
			else buffer.append(" and " + SysUser.COL_USER_ACCOUNT + "  like '%" + str + "%'");
		}
		str = (String) fields.get("userName");
		if (str != null && !"".equals(str)) {
			buffer.append(" and " + SysUser.COL_USER_NAME + "  like '%" + str + "%'");
		}
		str = (String) fields.get("userStatus");
		if (str != null && !"".equals(str)) {
			buffer.append(" and " + SysUser.COL_USER_STATUS + "='" + str + "'");
		}
		str = (String) fields.get("deptId");
		if (str != null && !"".equals(str)) {
			buffer.append(" and " + SysUser.COL_DEPT_ID + "=" + str + "");
		}
		return buffer.toString();
	}

	private String getNodes(List rights) {
		StringBuffer sb = new StringBuffer("");
		sb.append("<?xml version='1.0' encoding='UTF-8' ?>");
		sb.append("<tree id=\"0\">");
		if (rights != null) {
			for (int i = 0; i < rights.size(); i++) {
				Map so = (Map) rights.get(i);
				sb.append("<item id=\"res" + so.get("ID") + "\" text=\"" + so.get("NAME") + "\" child=\"1\">");
				sb.append("<userdata name=\"name\">" + so.get("NAME") + "</userdata>");
				sb.append("</item>");
			}
		}
		sb.append("</tree>");
		return sb.toString();
	}
	
	private String getCityCode(HttpServletRequest request) {

		String citycode = null;
		Session session = (Session) request.getSession().getAttribute(Const.SESSION);
		if (session == null) {
			citycode = "";
		} else {
			if (session.getCityCode() != null && !"".equals(session.getCityCode())) {
				citycode = session.getCityCode();
			} else {
				citycode = "";
			}
		}
		return citycode;
	}

}
