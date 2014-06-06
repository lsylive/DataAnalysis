package com.liusy.datapp.web.compare.action;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
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
import com.liusy.datapp.model.compare.CompareInfo;
import com.liusy.datapp.model.compare.CompareMainResult;
import com.liusy.datapp.model.compare.CompareRunInfo;
import com.liusy.datapp.model.resource.ResourceTable;
import com.liusy.datapp.service.compare.CompareInfoService;
import com.liusy.datapp.service.compare.CompareMainResultService;
import com.liusy.datapp.service.compare.CompareRunInfoService;
import com.liusy.datapp.service.compare.CompareSlaveResultService;
import com.liusy.datapp.service.resource.ResourceTableService;
import com.liusy.datapp.web.BaseAction;
import com.liusy.datapp.web.compare.form.CompareMainResultForm;

public class CompareResultAction extends BaseAction {
	
	private static final String GETTREE			= "getTree";		
	private static final String	ENTER			= "ENTER";
	private static final String DELETERESULT	= "DELETERESULT";
	private static final String DELETERUN	 	= "DELETERUN";

	@Override
	public ActionForward doExecute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String action = request.getParameter("action");
		if (action == null) action = LIST;
		if (log.isDebugEnabled()) log.debug("action:" + action);
		ActionForward forward;
		CompareMainResultForm compareMainResultForm = (CompareMainResultForm) form;

		try {
			
			if (ENTER.equalsIgnoreCase(action)) forward =  mapping.findForward(LIST);
			else if (GETTREE.equalsIgnoreCase(action))	forward = getTreeNode(mapping, request, response);
			else if (DELETERESULT.equalsIgnoreCase(action)) forward = delelteResult(mapping, request, response);
			else if (DELETERUN.equalsIgnoreCase(action)) forward = delelteRun(mapping, request, response);
//			else if (ADD.equalsIgnoreCase(action)) forward = addMemo(mapping, compareMainResultForm, request, response);
//			else if (SAVE.equalsIgnoreCase(action)) forward = saveMemo(mapping, compareMainResultForm, request, response);
//			else if (EDIT.equalsIgnoreCase(action)) forward = editMemo(mapping, compareMainResultForm, request, response);
//			else if (UPDATE.equalsIgnoreCase(action)) forward = updateMemo(mapping, compareMainResultForm, request, response);
//			else if (DELETE.equalsIgnoreCase(action)) forward = deleteMemo(mapping, compareMainResultForm, request, response);
//			else if (VIEW.equalsIgnoreCase(action)) forward = viewMemo(mapping, compareMainResultForm, request, response);
			else {
				request.setAttribute("err", new WebException("找不到该action方法：" + action));
				forward = mapping.findForward(ERROR);// 找不到合适的action
			}
		}
		catch (Exception e) {
			request.setAttribute("err", e);
			forward = mapping.findForward(ERROR);
		}
		return forward;
	}

	private ActionForward delelteRun(ActionMapping mapping,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		StringBuffer sb = new StringBuffer();
		try{
			String runId = request.getParameter("runId");
			CompareRunInfoService compareRunInfoService = (CompareRunInfoService)getBean("compareRunInfoService");
			compareRunInfoService.removeCompareRunInfo(Integer.parseInt(String.valueOf(runId)));
			sb.append(RET_NORAML);
			} catch (Exception e) {
				e.printStackTrace();
				sb.append(JsonErrorMessage(OPER_FAILED, "删除运行记录失败！"));
			}
			response.setContentType("ext/plain;charset=UTF-8");
			response.setHeader("Cache_Control", "no-cache");
			response.getWriter().write(sb.toString());
			response.getWriter().close();
			return null;
		
	}
	
	private ActionForward delelteResult(ActionMapping mapping,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		StringBuffer sb = new StringBuffer();
		try{
			String runId = request.getParameter("runId");
			String tableEnName = request.getParameter("tableEnName");
			CompareMainResultService compareMainResultService = (CompareMainResultService)getBean("compareMainResultService");
			compareMainResultService.deleleResultByRunIdAndSlaveTableName(Integer.parseInt(runId), tableEnName);
			sb.append(RET_NORAML);
			} catch (Exception e) {
				e.printStackTrace();
				sb.append(JsonErrorMessage(OPER_FAILED, "删除对比记录失败！"));
			}
			response.setContentType("ext/plain;charset=UTF-8");
			response.setHeader("Cache_Control", "no-cache");
			response.getWriter().write(sb.toString());
			response.getWriter().close();
			return null;
		
	}
	private ActionForward getTreeNode(ActionMapping mapping,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		if (log.isDebugEnabled())
			log.debug("Entering 'getTreeNode' method");

		try {
			CompareInfoService compareInfoService = (CompareInfoService) getBean("compareInfoService");
			CompareRunInfoService compareRunInfoService = (CompareRunInfoService)getBean("compareRunInfoService");
			CompareMainResultService compareMainResultService = (CompareMainResultService)getBean("compareMainResultService");
			CompareSlaveResultService compareSlaveResultService = (CompareSlaveResultService)getBean("compareSlaveResultService");
			ResourceTableService resourceTableService = (ResourceTableService) getBean("resourceTableService");
			
			Session session=(Session)request.getSession().getAttribute(Const.SESSION);
			PageQuery pageQuery = new PageQuery();
			pageQuery.setSelectParamId("GET_COMPAREINFO_PAGE");
			String queryString = "and t."+CompareInfo.COL_USER_ID+"="+session.getUserId();
			pageQuery.getParameters().put("queryString", queryString);
			compareInfoService.queryCompareInfo(pageQuery);
			setPage(pageQuery);

			Element root = DocumentHelper.createElement("tree").addAttribute(
					"id", "0");
			Element topNode = root.addElement("item")
				.addAttribute("text","我的对比查询")
				.addAttribute("id", "root")
				.addAttribute("open", "0")
				.addAttribute("child", "0");
			topNode.addElement("userdata")
					.addAttribute("name", "name")
					.addText("我的对比查询");
			
			for (Map<String, String> map : pageQuery.getRecordSet()) {
				String compId = map.get(CompareInfo.PROP_ID.toUpperCase());
				String mainTableId = map.get(CompareInfo.PROP_DT_ID.toUpperCase());
				List<CompareRunInfo> runInfoList = compareRunInfoService.getCompareRunInfoListByCompId(Integer.parseInt(compId));
				Element compareNode = topNode
						.addElement("item")
							.addAttribute("text",map.get(CompareInfo.PROP_COMPARE_NAME.toUpperCase()))
							.addAttribute("id",	compId)
							.addAttribute("im0", "folderClosed.gif")
							.addAttribute("open", "0")
						.addElement("userdata")
							.addAttribute("name", "name")
							.addText(map.get(CompareInfo.PROP_COMPARE_NAME.toUpperCase()));
				
				if (runInfoList!=null &&runInfoList.size() > 0)
				{
					for(CompareRunInfo runInfo : runInfoList)
					{
						int runId = runInfo.getId();
						List<CompareMainResult> mainResultList = compareMainResultService.getCompareMainResultListByRunId(runId);
						Element runInfoNode = compareNode.getParent().addElement("item")
								.addAttribute("text",runInfo.getStartTime().toString())
								.addAttribute("id",	"RUN" + String.valueOf(runInfo.getId()))
								.addAttribute("im0", "folderClosed.gif")
								.addAttribute("open", "0")
							.addElement("userdata")
								.addAttribute("name", "name")
								.addText(runInfo.getStartTime().toString());
						if(mainResultList!=null && mainResultList.size()>0)
						{
							for(CompareMainResult mainResult : mainResultList)
							{
								ResourceTable slaveTable = resourceTableService.getResouceTableByEnName(mainResult.getSlaveTableName());
								String count= compareSlaveResultService.
								getMainPkIdsByRunIdAndSlaveTableNameCount(runId,mainResult.getSlaveTableName());
													runInfoNode.getParent().addElement("item")
									.addAttribute("text",slaveTable.getCnName() + "(" + count+")")
									.addAttribute("id",	mainTableId+":"+String.valueOf(runInfo.getId())+ ":" + mainResult.getSlaveTableName())
									.addAttribute("im0", "folderClosed.gif")
										.addElement("userdata")
										.addAttribute("name", "name")
										.addText(slaveTable.getCnName() + "(" + count+")");
							}
						}
					}
				}
			}

			response.setContentType("text/xml;charset=UTF-8");
			String s = root.asXML();
			response.getWriter().write(root.asXML());
			response.getWriter().close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
//	private ActionForward searchMemo(ActionMapping mapping, CompareMainResultForm memoForm,
//			HttpServletRequest request, HttpServletResponse response)
//	throws Exception {
//		if (log.isDebugEnabled()) log.debug("Entering 'searchMemo' method");
//
//		try {
//			PersonalMemoService personalMemoService = (PersonalMemoService)getBean("personalMemoService");
//			PageQuery pageQuery = memoForm.getQuery();
//			
//			Map fields = pageQuery.getParameters();
//			String classId = (String) fields.get("classId");
//			request.setAttribute("classId", classId);
//			
//			String queryString = getQueryString(pageQuery,classId,request);
//			pageQuery.getParameters().put("queryString", queryString);
//			
//			if (pageQuery.getOrder() == null || "".equalsIgnoreCase(pageQuery.getOrder())) {
//				pageQuery.setOrder("ID");
//				pageQuery.setOrderDirection(PageQuery.ASC);
//			}
//			
//			personalMemoService.queryPersonalMemo(pageQuery);
//			memoForm.getRecord().put("classId", classId);
//			initForm(memoForm, LIST,request);
//			setPage(memoForm.getQuery());
//			return mapping.findForward(LIST);
//		}
//		catch (ServiceException e) {
//			request.setAttribute("errMsg", e.getMessage());
//			return mapping.findForward(ERROR);
//		}
//		catch (Exception e){
//			e.printStackTrace();
//			request.setAttribute("err", e);
//			return mapping.findForward(ERROR);
//		}
//		
//	}
//	
//	private String getQueryString(PageQuery pageQuery, String classId ,HttpServletRequest request) {
//		StringBuffer buffer = new StringBuffer();
//		
//		pageQuery.setSelectParamId("GET_MEMO_PAGE");
//		if (classId!=null && !classId.equals("") && !classId.equals("root")){			
//			buffer.append(" and t.class_id=" + classId);
//		}else {			
//			//取得该用户所有的分类下面的文章
//			Integer currentUserId = Integer.valueOf(getCurrentUser(request).getUserId());
//			buffer.append(" and s.user_id="+currentUserId);						
//		}
//		Map fields = pageQuery.getParameters();
//		String str = (String) fields.get("memoTitle");
//		
//		if (str != null && !"".equals(str)) {
//			if (str.contains("%")) buffer.append(" and t.MEMO_TITLE  like '" + str + "'");
//			else buffer.append(" and t.MEMO_TITLE  like '%" + str + "%'");
//		}
//		
//		String str1 = (String)fields.get("createDateFrom");
//		String str2 = (String)fields.get("createDateTo");
//		if (str1 != null && !"".equals(str1) 
//				&& str2 != null && !"".equals(str2)) {
//			buffer.append(" and t.CREATE_DATE between to_date('" + str1 + "','yyyy/mm/dd') and to_date('" + str2 + "','yyyy/mm/dd')");
//		}
//			
//		return buffer.toString();
//	}
//	
//	private ActionForward addMemo(ActionMapping mapping, MemoForm memoForm,
//			HttpServletRequest request, HttpServletResponse response)
//			throws Exception {
//		if (log.isDebugEnabled()) log.debug("Entering 'addMemo' method");
//
//
//		String classId = request.getParameter("classId");
//		memoForm.getRecord().put("classId", classId);
//		initForm(memoForm, ADD,request);
//		return mapping.findForward(ADD);
//	}
//
//	
//	
//	private ActionForward saveMemo(ActionMapping mapping, MemoForm memoForm,
//			HttpServletRequest request, HttpServletResponse response)
//			throws Exception {
//		if (log.isDebugEnabled()) log.debug("Entering 'saveMemo' method");
//
//		ActionForward forward = null;
//		try {
//			PersonalMemo personalMemo = new PersonalMemo();
//			if (memoForm.getRecord().get(personalMemo.PROP_CLASS_ID).equals("root"))
//			{//未指定类别
//				memoForm.getRecord().put(personalMemo.PROP_CLASS_ID, "");
//			}
//			ConvertUtil.mapToObject(personalMemo, memoForm.getRecord());
//			personalMemo.setCreateDate(new Date());
//			PersonalMemoService personalMemoService = (PersonalMemoService)getBean("personalMemoService");
//			personalMemoService.createPersonalMemo(personalMemo);
//			
//			
//			forward = returnForward(mapping, request, RETURN_NORMAL);
//		}
//		catch(Exception e) {
//			e.printStackTrace();
//			request.setAttribute("operstatus", "0");
//			addMessage(memoForm, "保存操作失败");
//			forward = mapping.findForward(ADD);
//		}
//		return forward;
//	}
//	
//	private ActionForward editMemo(ActionMapping mapping, MemoForm memoForm,
//			HttpServletRequest request, HttpServletResponse response)
//			throws Exception {
//		if (log.isDebugEnabled()) log.debug("Entering 'editMemo' method");
//
//		String id = request.getParameter("id");
//		PersonalMemoService personalMemoService = (PersonalMemoService)getBean("personalMemoService");
//		PersonalMemo personalMemo = personalMemoService.findPersonalMemo(new Integer(id));
//		ConvertUtil.objectToMap(memoForm.getRecord(), personalMemo);
//
//		initForm(memoForm, EDIT,request);
//		return mapping.findForward(EDIT);
//	}
//
//	private ActionForward updateMemo(ActionMapping mapping, MemoForm memoForm,
//			HttpServletRequest request, HttpServletResponse response)
//			throws Exception {
//		if (log.isDebugEnabled()) log.debug("Entering 'updateMemoClass' method");
//
//		try {
//			PersonalMemoService personalMemoService = (PersonalMemoService)getBean("personalMemoService");
//			PersonalMemo personalMemo = personalMemoService.findPersonalMemo(new Integer(memoForm.getRecord().get("id")));
//			if (personalMemo != null) {
//				
//				ConvertUtil.mapToObject(personalMemo, memoForm.getRecord());
//				personalMemo.setModifyDate(new Date());
//				personalMemoService.updatePersonalMemo(personalMemo);
//				request.setAttribute("msg", "修改成功！");
//				return returnForward(mapping, request, RETURN_NORMAL);
//			}
//			else {
//				request.setAttribute("msg", "备忘录分类记录不存在");
//				return mapping.findForward(ERROR);
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//			request.setAttribute("operstatus", "0");
//			request.setAttribute("msg", "更新操作失败");
//			return mapping.findForward(ERROR);
//		}
//	}
//
//	private ActionForward deleteMemo(ActionMapping mapping, MemoForm memoForm,
//			HttpServletRequest request, HttpServletResponse response)
//			throws Exception {
//		if (log.isDebugEnabled()) log.debug("Entering 'deleteMemo' method");
//		StringBuffer sb = new StringBuffer();
//		
//		try {
//			PersonalMemoService personalMemoService = (PersonalMemoService)getBean("personalMemoService");
//			String del_ids = request.getParameter("ids");
//			if (del_ids != null && del_ids.trim().length() > 0) {
//				personalMemoService.removePersonalMemos(parseId(del_ids.split(";")));
//			}
//			sb.append(RET_NORAML);
//			
//		} catch (Exception e) {
//			e.printStackTrace();
//			sb.append(JsonErrorMessage(OPER_FAILED, "备忘录删除失败！"));
//		}
//		return searchMemo(mapping, memoForm,request,response);
//	}
//	
//	public ActionForward viewMemo(ActionMapping mapping, MemoForm memoForm, HttpServletRequest request, HttpServletResponse response) throws Exception {
//		if (log.isDebugEnabled()) log.debug("Entering 'view memo' method");
//		String id = request.getParameter("id");
//
//		try {
//			PersonalMemoService personalMemoService = (PersonalMemoService)getBean("personalMemoService");
//			PersonalMemo personalMemo = (PersonalMemo)personalMemoService.findPersonalMemo(Integer.valueOf(id));
//			ConvertUtil.objectToMap(memoForm.getRecord(), personalMemo);
//			initForm(memoForm, VIEW,request);
//			return mapping.findForward(VIEW);
//		}
//		catch (ServiceException e) {
//			request.setAttribute("errMsg", e.getMessage());
//			return mapping.findForward(ERROR);
//		}
//		catch (Exception e) {
//			e.printStackTrace();
//			request.setAttribute("err", e);
//			return mapping.findForward(ERROR);
//		}
//	}
//		
//	private void initForm(CompareMainResultForm form, String action,HttpServletRequest request) {
//		if (VIEW.equalsIgnoreCase(action) ){
//		String content = form.getRecord().get("memoContent");
//		if (content != null) {
//			content = content.replace(" ", "&nbsp;");
//			content = content.replace("\r", "<br>");
//			form.getRecord().put("memoContent", content);
//		}
//		}
//		
//		PersonalMemoClassService personalMemoClassService = (PersonalMemoClassService) getBean("personalMemoClassService");
//		Integer currentUserId = Integer.valueOf(getCurrentUser(request).getUserId());
//		PageQuery query = new PageQuery();
//		query.setSelectParamId("GET_MEMOCLASS_PAGE");
//		query.setPageSize("0");
//		String condition = " and t." + PersonalMemoClass.COL_USER_ID
//				+ " = " + currentUserId;
//		query.getParameters().put("queryString", condition);
//		List<Map<String, String>> memoClasses = personalMemoClassService
//				.queryPersonalMemoClass(query).getRecordSet();
//		
//		setCode(form, "MEMO_CLASS", memoClasses, "CLASS_NAME", "ID");
//	}
//	
//	private java.io.Serializable[] parseId(String[] ids) throws Exception{
//		if (ids == null || ids.length == 0) { throw new Exception("非法进入编辑页！"); }
//		java.io.Serializable id[] = null;
//		try {
//			id = new Integer[ids.length];
//			for (int i = 0; i < ids.length; i++) {
//				id[i] = new Integer(ids[i]);
//			}
//		}
//		catch (Exception e) {
//			id = ids;
//		}
//		return id;
//		
//	}
}
