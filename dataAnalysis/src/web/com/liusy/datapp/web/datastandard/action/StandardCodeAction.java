package com.liusy.datapp.web.datastandard.action;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.liusy.dataapplatform.base.util.ConvertUtil;
import com.liusy.dataapplatform.base.util.PageQuery;
import com.liusy.dataapplatform.base.exception.WebException;
import com.liusy.dataapplatform.base.exception.ServiceException;

import com.liusy.datapp.model.datastandard.StandardCode;
import com.liusy.datapp.web.BaseAction;
import com.liusy.datapp.web.datastandard.form.StandardCodeForm;
import com.liusy.datapp.service.datastandard.StandardCodeService;
import com.liusy.datapp.service.pool.BussCodeSetPool;

public class StandardCodeAction extends BaseAction {

	@Override
	public ActionForward doExecute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String action = request.getParameter("action");
		if (action == null) action = LIST;
		if (log.isDebugEnabled()) log.debug("action:" + action);
		ActionForward forward = null;
		StandardCodeForm standardCodeForm = (StandardCodeForm)form;
		try {
			if (LIST.equalsIgnoreCase(action)) forward = searchStandardCode(mapping, standardCodeForm, request, response);
			else if (ADD.equalsIgnoreCase(action)) forward = addStandardCode(mapping, standardCodeForm, request, response);
			else if (SAVE.equalsIgnoreCase(action)) forward = saveStandardCode(mapping, standardCodeForm, request, response);
			else if (EDIT.equalsIgnoreCase(action)) forward = editStandardCode(mapping, standardCodeForm, request, response);
			else if (UPDATE.equalsIgnoreCase(action)) forward = updateCode(mapping, standardCodeForm, request, response);
			else if (DELETE.equalsIgnoreCase(action)) forward = deleteStandardCode(mapping, standardCodeForm, request, response);
			else {
				request.setAttribute("err", new WebException("找不到该action方法：" + action));
				forward = mapping.findForward(ERROR);// 找不到合适的action
			}
		}
		catch(Exception e) {
			e.printStackTrace();
			request.setAttribute("err", e);
			forward = mapping.findForward(ERROR);
		}
		return forward;
	}

	private ActionForward searchStandardCode(ActionMapping mapping, StandardCodeForm standardCodeForm,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		if (log.isDebugEnabled()) log.debug("Entering 'searchStandardCode' method");

		try {
			StandardCodeService standardCodeService = (StandardCodeService) getBean("standardCodeService");
			PageQuery pageQuery = standardCodeForm.getQuery();
			pageQuery.setSelectParamId("GET_STANDARDCODE_PAGE");
			String codesetId = (String)request.getParameter("codesetid");
			if (codesetId != null && !"".equalsIgnoreCase(codesetId)) {
				pageQuery.getParameters().put("codesetid", codesetId);
			}
			String queryString = getQueryString(pageQuery);
			pageQuery.getParameters().put("queryString", queryString);
			standardCodeService.queryStandardCode(pageQuery);

			setPage(standardCodeForm.getQuery());
			return mapping.findForward(LIST);
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

	public ActionForward addStandardCode(ActionMapping mapping, StandardCodeForm standardCodeForm,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		if (log.isDebugEnabled()) log.debug("Entering 'addStandardCode' method");

		initForm(standardCodeForm, ADD, request);
		return mapping.findForward(ADD);
	}

	public ActionForward saveStandardCode(ActionMapping mapping, StandardCodeForm standardCodeForm,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		if (log.isDebugEnabled()) log.debug("Entering 'saveStandardCode' method");

		try {
			StandardCode standardCode = new StandardCode();
			ConvertUtil.mapToObject(standardCode, standardCodeForm.getRecord());
			StandardCodeService standardCodeService = (StandardCodeService) getBean("standardCodeService");
			standardCodeService.createStandardCode(standardCode);
			NotifyPool(standardCode);
			return returnForward(mapping, request, RETURN_NORMAL);
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("operstatus", "0");
			standardCodeForm.setErrorMessage("新建操作失败");
			initForm(standardCodeForm, ADD, request);
			return mapping.findForward(ADD);
		}
	}

	private ActionForward editStandardCode(ActionMapping mapping, StandardCodeForm standardCodeForm,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		if (log.isDebugEnabled()) log.debug("Entering 'editStandardCode' method");

		String id = request.getParameter("id");
		StandardCodeService standardCodeService = (StandardCodeService) getBean("standardCodeService");
		StandardCode standardCode = (StandardCode) standardCodeService.findStandardCode(new Integer(id));
		ConvertUtil.objectToMap(standardCodeForm.getRecord(), standardCode);
		initForm(standardCodeForm, EDIT,request);
		return mapping.findForward(EDIT);
	}

	private ActionForward updateCode(ActionMapping mapping, StandardCodeForm standardCodeForm,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		if (log.isDebugEnabled()) log.debug("Entering 'updateCode' method");

		try {
			StandardCode standardCode = new StandardCode();
			ConvertUtil.mapToObject(standardCode, standardCodeForm.getRecord());
			StandardCodeService standardCodeService = (StandardCodeService) getBean("standardCodeService");
			standardCodeService.updateStandardCode(standardCode);
			NotifyPool(standardCode);
			request.setAttribute("msg", "修改成功！");
			return returnForward(mapping, request, RETURN_NORMAL);
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("operstatus", "0");
			standardCodeForm.setErrorMessage("编辑操作失败");
			return mapping.findForward(EDIT);
		}
	}

	public ActionForward deleteStandardCode(ActionMapping mapping, StandardCodeForm standardCodeForm,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		if (log.isDebugEnabled()) log.debug("Entering 'deleteStandardCode' method");

		try {
			StandardCodeService standardCodeService = (StandardCodeService) getBean("standardCodeService");
			String del_ids = request.getParameter("ids");
			if (del_ids != null && del_ids.trim().length() > 0) {
				standardCodeService.removeStandardCodes(parseId(del_ids.split(";")));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return searchStandardCode(mapping, standardCodeForm, request, response);
	}

	private void initForm(StandardCodeForm standardCodeForm, String action, HttpServletRequest request){
		if ("ADD".equalsIgnoreCase(action)) {
			String codesetId = request.getParameter("codesetid");
			standardCodeForm.getRecord().put("codesetId", codesetId);
		}
	}

	private String getQueryString(PageQuery pageQuery) {
		StringBuffer buffer = new StringBuffer();
		Map fields = pageQuery.getParameters();
		String str = (String) fields.get("codesetid");
		if (str != null && !"".equalsIgnoreCase(str)) {
			buffer.append(" and t.codeset_id" + " = " + str);
		}
		return buffer.toString();
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
		} catch (Exception e) {
			id = ids;
		}
		return id;
	}
	private void NotifyPool(StandardCode code){
		BussCodeSetPool bussCodeSetPool=(BussCodeSetPool)getBean("bussCodeSetPool");
		bussCodeSetPool.clearCodeById(code.getId().toString());
		bussCodeSetPool.clearCodeSet(code.getCodesetId().toString(),"");
	}
}
