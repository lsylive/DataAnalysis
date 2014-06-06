
package com.liusy.datapp.web;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.log4j.MDC;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.liusy.core.util.Const;
import com.liusy.core.util.Session;
import com.liusy.dataapplatform.base.form.BaseForm;
import com.liusy.dataapplatform.base.util.PageQuery;
import com.liusy.dataapplatform.code.Code;
import com.liusy.datapp.service.system.config.SysCodeSetService;
import com.liusy.datapp.service.system.config.SysResourceService;
/**
 * <p>Title:  ���ݹ�������</p>
 *
 * <p>Description:Struts Action ���� </p>
 *
 * <p>Copyright: Copyright (c) 2009</p>
 *
 * <p>Company: </p>
 *
 * @author wangc
 * @version 1.0
 */
public abstract class BaseAction extends Action {
	protected final Log						log				= LogFactory.getLog(getClass());

	protected static final Long			defaultLong		= null;

	protected static final String			LIST				= "LIST";

	protected static final String			ADD				= "ADD";

	protected static final String			SAVE				= "SAVE";

	protected static final String			EDIT				= "EDIT";

	protected static final String			UPDATE			= "UPDATE";

	protected static final String			DELETE			= "DELETE";

	protected static final String			VIEW				= "VIEW";

	protected static final String			SEARCH			= "SEARCH";

	protected static final String			MESSAGE			= "message";

	protected static final String			ERROR				= "error";

	protected static final int				ERROR_MSG_SIZE	= 30;

	protected static final String			RETURN_NORMAL	= "0";

	private static ApplicationContext	ctx				= null;

	protected static final String			NOTLOGIN			= "notLogin";

	protected static final String			NOPRIVILEGE		= "noPrivilege";

	protected static final String			HAVEPRIVILEGE	= "havePrivilege";

	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		if (log.isDebugEnabled()) log.debug("enter execute...");

		Session currentUser = (Session) request.getSession().getAttribute(Const.SESSION);
		String islogon = checkLogin(request, currentUser);
		if (islogon.equals(NOTLOGIN)) return mapping.findForward("relogin");
		else if (islogon.equals(NOPRIVILEGE)) return mapping.findForward(NOPRIVILEGE);
		else {
			ActionForward forward;
			response.setHeader("Content-Type", "text/html; charset=UTF-8");
			response.setHeader("Pragma", "no-cache");
			response.setHeader("Cache-Control", "no-cache");
			response.setDateHeader("Expires", 0);

			MDC.put("userAccount", currentUser.getAccountName());
			MDC.put("userName", currentUser.getUserName());
			MDC.put("orgId", currentUser.getOrgId());
			MDC.put("deptId", currentUser.getDeptId());
			MDC.put("orgName", currentUser.getOrgName());
			MDC.put("deptName", currentUser.getDeptName());
			MDC.put("ipAddress", request.getRemoteAddr());
			// MDC.put("ipAddress", request.getRemoteAddr());

			forward = doExecute(mapping, form, request, response);
			
			MDC.remove("userAccount");
			MDC.remove("userName");
			MDC.remove("orgId");
			MDC.remove("deptId");
			MDC.remove("orgName");
			MDC.remove("deptName");
			MDC.remove("ipAddress");
			MDC.remove("queryCondi");
			return forward;
		}

	}

	public abstract ActionForward doExecute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception;

	/**
	 * Convenience method to get Spring-initialized beans
	 * 
	 * @param name
	 * @return Object bean from ApplicationContext
	 */
	public Object getBean(String name) {
		if (ctx == null) {
			ctx = WebApplicationContextUtils.getRequiredWebApplicationContext(servlet.getServletContext());
		}
		return ctx.getBean(name);
	}

	protected String getErrorMsg(Exception e, int size) {
		String errorMsg = e.getMessage();
		if (errorMsg != null && errorMsg.length() > size) {
			errorMsg = errorMsg.substring(0, size) + "...";
		}
		return errorMsg;
	}

	/**
	 * @param request
	 * @return
	 */
	private String checkLogin(HttpServletRequest request, Session currentUser) {
		if (currentUser == null) return NOTLOGIN;
		else {
			String uriStr = request.getRequestURI();
			String action = request.getParameter("action");
			String contextPath = request.getContextPath();
			String urlPath = uriStr.replaceAll(contextPath + "/", "");

			if (action != null) urlPath += "?action=" + action;
			else urlPath += "?action=list";

			SysResourceService sysResourceService = (SysResourceService) getBean("sysResourceService");
			if (!sysResourceService.needCheck(urlPath)) return HAVEPRIVILEGE;

			if (currentUser.getPrivileges().containsValue(urlPath)) return HAVEPRIVILEGE;
			else return NOPRIVILEGE;
			// return HAVEPRIVILEGE;
		}
	}

	/**
	 * ��ActionForm������һ����ʾ��Ϣ
	 * 
	 * @param objForm
	 *           ��Ҫ������Ϣ��ActionForm
	 * @param info
	 *           ��ʾ��Ϣ
	 */
	protected void addMessage(Object objForm, String info) {
		BaseForm bf = (BaseForm) objForm;
		String msg = bf.getErrorMessage();
		if (!msg.equals("")) msg = msg + "<br>";
		bf.setErrorMessage(msg + info);
	}

	/**
	 * ��ActionForm��
	 * 
	 * @param objForm
	 *           Ҫ���ҵ�ActionForm
	 * @param codeNo
	 *           ���뼯���
	 * @param value
	 *           ����ֵ
	 * @return value��Ӧ�Ĵ�������
	 */
	protected String findCodeName(Object objForm, String codeNo, String value) {
		BaseForm bf = (BaseForm) objForm;

		List<Code> list = bf.getCodeSets().get(codeNo);
		if (value == null) return "";
		if (list == null) return "";
		for (int i = 0; i < list.size(); i++) {
			Code code = list.get(i);
			if (value.equals(code.getValue())) return code.getCodeName();
		}
		return "";
	}

	/**
	 * ��ActionForm�еĴ��뼯����������һ����뼯
	 * 
	 * @param objForm
	 *           Ҫ���Ӵ��뼯��ActionForm
	 * @param codeSetNos
	 *           Ҫ���Ӵ��뼯�ı�ż��ϣ��Զ��Ż��߷ֺŷָ�
	 */
	protected void setCode(Object obj, String codeSetNos) {
		String[] codes;
		if (codeSetNos == null) return;
		if (codeSetNos.indexOf(";") > 0) codes = codeSetNos.split(";");
		else if (codeSetNos.indexOf(",") > 0) codes = codeSetNos.split(",");
		else {
			codes = new String[1];
			codes[0] = codeSetNos;
		}
		SysCodeSetService csi = (SysCodeSetService) ctx.getBean("sysCodeSetService");
		for (int i = 0; i < codes.length; i++) {
			List<Code> codeSet = csi.findAllCode().get(codes[i]);
			setCode(obj, codes[i], codeSet);
		}
	}

	/**
	 * ��ActionForm�еĴ��뼯����������һ�����뼯
	 * 
	 * @param objForm
	 *           Ҫ���Ӵ��뼯��ActionForm
	 * @param codeSetNo
	 *           Ҫ���Ӵ��뼯�ı��
	 * @param codes
	 *           Ҫ���Ӵ��뼯
	 */
	protected void setCode(Object obj, String codeSetNo, List<Code> codes) {
		if (codes == null) return;
		BaseForm bf = (BaseForm) obj;
		bf.getCodeSets().put(codeSetNo, codes);
	}

	/**
	 * ��ActionForm�еĴ��뼯����������һ�����뼯
	 * 
	 * @param objForm
	 *           Ҫ���Ӵ��뼯��ActionForm
	 * @param codeSetNo
	 *           Ҫ���Ӵ��뼯�ı��
	 * @param codes
	 *           ���뼯����Դ���󼯺�
	 * @param label
	 *           ��������ΪcodeName������
	 * @param value
	 *           ��������Ϊvalue������
	 */
	protected void setCode(Object obj, String codeSetNo, List codes, String label, String value) {
		if (codes == null) return;
		BaseForm bf = (BaseForm) obj;
		List<Code> al = new ArrayList<Code>();

		al.add(new Code("", ""));
		for (int i = 0; i < codes.size(); i++) {
			Code code = new Code();
			Object objtmp = codes.get(i);
			Object ol = null;
			Object ov = null;
			try {
				ol = PropertyUtils.getProperty(objtmp, label);
				ov = PropertyUtils.getProperty(objtmp, value);
			}
			catch (Exception e) {
				ol = null;
				ov = null;
			}
			if (ol != null && ov != null) {
				code.setCodeName(ol.toString());
				code.setValue(ov.toString());
				al.add(code);
			}
		}
		bf.getCodeSets().put(codeSetNo, al);
	}

	protected ActionForward returnForward(ActionMapping mapping, HttpServletRequest request, String returnStatus) {
		request.setAttribute("returnStatus", returnStatus);
		return mapping.findForward(MESSAGE);
	}

	/**
	 * ���ò�ѯ���ݿ���Ҫ�ķ�ҳ����
	 * 
	 * @param query
	 *           ��ҳ��ѯ����
	 * @param recordCount
	 *           ��ҳ����ʼ��¼��
	 */
	protected void setPage(PageQuery query) {
		int currentPage;
		int pageCount;
		int recordCount = Integer.parseInt(query.getRecordCount());
		if (recordCount == 0) {
			query.setPageNumber("0");
			query.setPageCount("0");
		}
		else {
			int pageSize = new Integer(query.getPageSize()).intValue();
			if (pageSize != 0) {
				pageCount = recordCount / pageSize;
				if (recordCount % pageSize > 0) pageCount++;
			}
			else pageCount = 1;
			query.setPageCount(new Integer(pageCount).toString());
			currentPage = new Integer(query.getPageNumber()).intValue();
			if (currentPage <= 0) currentPage = 1;
			if (currentPage > pageCount) currentPage = pageCount;
			query.setPageNumber(String.valueOf(currentPage));
		}
	}

	public Session getCurrentUser(HttpServletRequest request) {
		return (Session) request.getSession().getAttribute(Const.SESSION);
	}

	protected static final String	RET_NORAML	= "{\"exit\":\"0\"}";

	protected static final int		OPER_FAILED	= 1;

	protected String JsonErrorMessage(int errNo, String msg) {
		return "{\"exit\":\"" + errNo + "\",\"message\":\"" + msg + "\"}";
	}

}
