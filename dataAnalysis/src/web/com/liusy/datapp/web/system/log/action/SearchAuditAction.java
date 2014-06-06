package com.liusy.datapp.web.system.log.action;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.liusy.core.util.Const;
import com.liusy.dataapplatform.base.exception.WebException;
import com.liusy.dataapplatform.base.util.PageQuery;
import com.liusy.datapp.model.system.org.SysDept;
import com.liusy.datapp.service.system.log.SysLogOperateService;
import com.liusy.datapp.service.system.org.SysDeptService;
import com.liusy.datapp.service.system.org.SysUserService;
import com.liusy.datapp.web.BaseAction;
import com.liusy.datapp.web.system.log.form.SearchAuditForm;

public class SearchAuditAction extends BaseAction{

	@Override
	public ActionForward doExecute(ActionMapping mapping, ActionForm tform, HttpServletRequest request, HttpServletResponse response) throws Exception {
		String action = request.getParameter("action");
		if (action == null) action = LIST;
		if (log.isDebugEnabled()) log.debug("action:" + action);
		ActionForward forward;
		
		SearchAuditForm form = (SearchAuditForm)tform;
		try {
			if (LIST.equalsIgnoreCase(action)) forward = search(mapping, form, request, response); // 打开查询列表页面
			else {
				request.setAttribute("err", new WebException("找不到该action方法：" + action));
				forward = mapping.findForward(ERROR);// 找不到合适的action
			}
		}
		catch (Exception e) {// 其他系统出错
			e.printStackTrace();
			request.setAttribute("err", e);
			forward = mapping.findForward(ERROR);
		}
		return forward;
	}

	private ActionForward search(ActionMapping mapping,SearchAuditForm form,
			HttpServletRequest request,HttpServletResponse response)
	throws Exception
	{
		if (log.isDebugEnabled()) log.debug("Entering 'search' method");
		try {
			
			SysLogOperateService sysLogOperateService = (SysLogOperateService) getBean("sysLogOperateService");
			PageQuery pageQuery = form.getQuery();
			pageQuery.setSelectParamId("GET_QUERY_SEARCHAUDIT_PAGE");
			String queryString = getQueryString(pageQuery);
			pageQuery.getParameters().put("queryString", queryString);
			if (pageQuery.getOrder() == null || "".equals(pageQuery.getOrder().trim())) {
				pageQuery.setOrder("OPTTIME");
				pageQuery.setOrderDirection("desc");
			}
			sysLogOperateService.querySysLogOperate(pageQuery);
			initForm(form);
			setPage(form.getQuery());
		}
		catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("err", e);
			return mapping.findForward(ERROR);
		}
		return mapping.findForward(LIST);
	}
	
	private String getQueryString(PageQuery query){
		StringBuffer buffer = new StringBuffer("");
		String userName = query.getParameters().get("username");
		if(userName==null)userName = "";
		String depId = query.getParameters().get("depid");
		if(depId==null)depId = "";
		String startTime = query.getParameters().get("starttime");
		if(startTime==null)startTime = "";
		String endTime = query.getParameters().get("endtime");
		if(endTime==null)endTime = "";
		
		if (!"".equals(userName)) {
			buffer.append(" and t.user_name like '%"+userName+"%' ");
		}
		if (!"".equals(depId)) {
			buffer.append(" and t.DEPT_ID="+depId);
		}
		if (!"".equals(startTime)&&!"".equals(endTime)) {
			buffer.append(" and (t.OPT_TIME between to_date('"+startTime+"','yyyy.mm.dd') and to_date('"+endTime+"','yyyy.mm.dd'))");
		}else if (!"".equals(startTime)) {
			buffer.append(" and t.opt_time"+Const.FILTER_OPER_GTANDEQL+" to_date('"+startTime+"','yyyy.mm.dd')");
		}else if (!"".equals(endTime)) {
			buffer.append(" and t.opt_time"+Const.FILTER_OPER_LTANDEQL+" to_date('"+endTime+"','yyyy.mm.dd')");
		}
		
		return buffer.toString();
	}
	
	private void initForm(SearchAuditForm form){
		SysDeptService sysDeptService = (SysDeptService)getBean("sysDeptService");
		List<SysDept> depts = sysDeptService.findAllDept();
		setCode(form, "depts", depts, SysDept.PROP_DEPT_NAME, SysDept.PROP_ID);
	}
}
