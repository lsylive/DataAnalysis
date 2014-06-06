package com.liusy.datapp.web.query.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.liusy.datapp.web.BaseAction;
import com.liusy.datapp.web.query.form.SubjectSynthesisCfgForm;

public class SubjectCfgAction extends BaseAction{

	@Override
	public ActionForward doExecute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		String action = request.getParameter("action");
		if (action == null) action = LIST;
		if (log.isDebugEnabled()) log.debug("action:" + action);
		ActionForward forward=null;
		SubjectSynthesisCfgForm theForm=(SubjectSynthesisCfgForm) form;
		try {
			if (LIST.equalsIgnoreCase(action)) forward = mapping.findForward(LIST); // 打开查询列表页面
			else{
				request.setAttribute("err", new Exception("非法调用"));
				forward = mapping.findForward(ERROR);
			}
		}catch (Exception e) {
			request.setAttribute("err", e);
			forward = mapping.findForward(ERROR);
		}
		return forward;
	}
	

}
