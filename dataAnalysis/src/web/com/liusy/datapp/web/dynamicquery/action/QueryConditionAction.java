package com.liusy.datapp.web.dynamicquery.action;


import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.xmlbeans.XmlOptions;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import com.liusy.core.util.Const;
import com.liusy.core.util.Session;

import com.liusy.dataapplatform.base.exception.WebException;

import com.liusy.datapp.service.pool.QueryMetaIndPool;
import com.liusy.datapp.service.pool.SubjectConfigPool;
import com.liusy.datapp.service.pool.TableConfigPool;
import com.liusy.datapp.util.poolobj.QueryParamPoolObj;
import com.liusy.datapp.util.poolobj.SubjectColumnConfigPoolObj;
import com.liusy.datapp.web.BaseAction;
import com.liusy.datapp.web.dynamicquery.form.QueryConditionForm;

public class QueryConditionAction extends BaseAction{

	@Override
	public ActionForward doExecute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		String action = request.getParameter("action1");
		if (action == null) action = LIST;
		if (log.isDebugEnabled()) log.debug("action:" + action);
		ActionForward forward=null;
		QueryConditionForm theForm=(QueryConditionForm) form;
		try {

			if("CHECKSPACE".equalsIgnoreCase(action)) forward=checkSpace(mapping, theForm, request, response);
			else
			{
				request.setAttribute("err", new WebException("找不到该action方法：" + action));
				forward = mapping.findForward(ERROR);// 找不到合适的action
			}
		}
		catch (Exception e) {// 其他系统出错
			request.setAttribute("err", e);
			request.setAttribute("errMsg", e.getMessage());
			forward = mapping.findForward(ERROR);
		}
		return forward;
	}
	/**
	 * 检查个人空间是否存在
	 * @param mapping
	 * @param theForm
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward checkSpace(ActionMapping mapping, QueryConditionForm theForm, HttpServletRequest request, HttpServletResponse response) throws Exception{
		if (log.isDebugEnabled()) log.debug("Entering 'checkSpace' method");
	
		response.setContentType("text/plain;charset=UTF-8");
		response.setHeader("Cache_Control", "no-cache");
		
		Session session=(Session) request.getSession().getAttribute(Const.SESSION);
		//判断是否配置个人空间
		if(session.getSpaceId()==null || "".equals(session.getSpaceId().trim()))
		{
			response.getWriter().write("REQUIRED");
			return null;
		}else
			response.getWriter().write("OK");
		return null;
	}
}
