package com.liusy.datapp.web.login;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import nl.captcha.Captcha;

import org.apache.log4j.Logger;
import org.apache.log4j.MDC;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.liusy.core.util.Const;
import com.liusy.core.util.JsonUtil;
import com.liusy.core.util.Session;
import com.liusy.datapp.service.login.LoginService;

public class LoginAction extends Action {
	private Logger	logger	= Logger.getLogger(this.getClass());

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		String action = request.getParameter("action");
		try {
			if (action == null || "".equals(action)) {
				// validateLogin(request, response);
				forward = mapping.findForward("LIST");
			}
			else if ("login".equalsIgnoreCase(action)) {
				//验证码验证
				//verfiyCode(request, response);
				validateLogin(request, response);
				forward = mapping.findForward("INDEX");
			}else if("checklogin".equalsIgnoreCase(action))
				return checkLogin(mapping, form, request, response);

			MDC.remove("userAccount");
			MDC.remove("userName");
			MDC.remove("orgId");
			MDC.remove("deptId");
			MDC.remove("orgName");
			MDC.remove("deptName");
			MDC.remove("loginDate");
			MDC.remove("ipAddress");
			MDC.remove("logonResult");
			MDC.remove("logoutDate");
		}
		catch (Exception e) {

			e.printStackTrace();
			request.setAttribute("errMsg", e.getMessage());
			forward = mapping.findForward("LIST");
			// JsonUtil.jsonFailResponse(e.getMessage(), request, response);
		}
		return forward;
	}

	public void validateLogin(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String userName = request.getParameter("userName");
		String password = request.getParameter("password");
		String SSLLogin = request.getParameter("SSLLogin");

		ApplicationContext ctx = WebApplicationContextUtils.getRequiredWebApplicationContext(servlet.getServletContext());
		LoginService loginService = (LoginService) ctx.getBean("loginService");

		Session session = loginService.login(userName, password, SSLLogin);
		if (session != null) {
			MDC.put("userAccount", session.getAccountName());
			MDC.put("userName", session.getUserName());
			MDC.put("orgId", session.getOrgId());
			MDC.put("deptId", session.getDeptId());
			MDC.put("orgName", session.getOrgName());
			MDC.put("deptName", session.getDeptName());
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			MDC.put("loginDate", format.format(new Date(System.currentTimeMillis())));
			MDC.put("ipAddress", request.getRemoteAddr());

			session.setCityCode("001");
			request.getSession().setAttribute(Const.SESSION, session);
			request.setAttribute("userName", session.getUserName());
			request.setAttribute("userAccount", session.getAccountName());

			// MDC.put("logoutDate",null);
			MDC.put("logonResult", "成功登入");

			logger.info("");
			//JsonUtil.jsonOkResponse("登录成功", request, response);
		}
		else {
			MDC.put("logonResult", "登入失败");
			logger.info("登入失败");
			throw new Exception("用户名或密码错误");
			//JsonUtil.jsonFailResponse("用户名密码错误",request, response);
		}
	}
	public ActionForward checkLogin(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		response.setContentType("text/xml;charset=UTF-8");
		response.setHeader("Cache_Control", "no-cache");
		String retstr="";
		Session session=(Session)request.getSession().getAttribute(Const.SESSION);
		if(session!=null)
			retstr="OK";
		else
			retstr="NO";
		response.getWriter().write(retstr);
		response.getWriter().close();
		return null;
	}
	public void verfiyCode(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//String verfiycode = request.getSession().getAttribute(Const.LOGIN_VERFIYCODE).toString();
		String verfiycode = request.getSession().getAttribute(Captcha.NAME).toString();
		String inputcode = request.getParameter("verfiycode");
		if (!verfiycode.equalsIgnoreCase(inputcode))
			throw new Exception("验证码有误");
	}

}
