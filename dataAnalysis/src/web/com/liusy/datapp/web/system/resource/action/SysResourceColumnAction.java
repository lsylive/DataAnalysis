/**
 * SysResourceColumnAction.java Create on 2009-10-9 下午03:16:21 
 * @author 黄少淘
 * @date 2009-9-18
 * 作用
 *       
 */
package com.liusy.datapp.web.system.resource.action;

import java.io.PrintWriter;
import java.util.ArrayList;
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
import com.liusy.dataapplatform.code.Code;
import com.liusy.datapp.model.datastandard.StandardCode;
import com.liusy.datapp.model.datastandard.StandardCodeset;
import com.liusy.datapp.model.datastandard.StandardDataMeta;
import com.liusy.datapp.model.datastandard.StandardIndicator;
import com.liusy.datapp.model.resource.ResourceColumn;
import com.liusy.datapp.service.pool.TableConfigPool;
import com.liusy.datapp.service.resource.ResourceColumnService;
import com.liusy.datapp.web.BaseAction;
import com.liusy.datapp.web.system.resource.form.SysResourceColumnForm;

public class SysResourceColumnAction extends BaseAction {

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.liusy.core.web.BaseAction#doExecute(org.apache.struts.action.
	 * ActionMapping, org.apache.struts.action.ActionForm,
	 * javax.servlet.http.HttpServletRequest,
	 * javax.servlet.http.HttpServletResponse)
	 */
	public ActionForward doExecute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

		String action = request.getParameter("action");

		if (action == null)
			action = LIST;
		if (log.isDebugEnabled())
			log.debug("action:" + action);
		ActionForward forward;
		SysResourceColumnForm resourceColumnForm = (SysResourceColumnForm) form;

		try {
			if (LIST.equalsIgnoreCase(action))
				forward = searchResourceColumn(mapping, resourceColumnForm, request, response); // 打开查询列表页面
			else if (ADD.equalsIgnoreCase(action))
				forward = addResourceColumn(mapping, resourceColumnForm, request, response);// 打开增加页面
			else if (SAVE.equalsIgnoreCase(action))
				forward = saveResourceColumn(mapping, resourceColumnForm, request, response);// 保存增加数据
			else if (EDIT.equalsIgnoreCase(action))
				forward = editResourceColumn(mapping, resourceColumnForm, request, response);// 打开修改页面
			else if (UPDATE.equalsIgnoreCase(action))
				forward = updateResourceColumn(mapping, resourceColumnForm, request, response);// 保存修改数据
			else if (DELETE.equalsIgnoreCase(action))
				forward = deleteResourceColumn(mapping, resourceColumnForm, request, response);// 删除
			else if ("TOEDIT".equals(action))
				forward = toEditJsp(mapping, resourceColumnForm, request, response);
			else if ("SETFORM".equalsIgnoreCase(action)) {
				getDataMeta(mapping, resourceColumnForm, request, response);
				forward = null;
			}else if ("TREE".equalsIgnoreCase(action)) {
				getDataMetaTree(mapping, resourceColumnForm, request, response);
				forward = null;
			}
			else if ("SELECTDATAMETA".equalsIgnoreCase(action)) {
				forward = mapping.findForward("SELECTDATAMETA");
			}else if ("moveUp".equalsIgnoreCase(action)) {
				forward = moveUp(mapping, resourceColumnForm, request, response);
			}else if ("moveDown".equalsIgnoreCase(action)) {
				forward = moveDown(mapping, resourceColumnForm, request, response);
			}
			else {
				request.setAttribute("err", new WebException("找不到该action方法：" + action));
				forward = mapping.findForward(ERROR);// 找不到合适的action
			}

		} catch (Exception e) {// 其他系统出错

			e.printStackTrace();
			request.setAttribute("err", e);
			forward = mapping.findForward(ERROR);
		}
		return forward;
	}

	
	
	public ActionForward addResourceColumn(ActionMapping mapping, SysResourceColumnForm resourceColumnForm, HttpServletRequest request, HttpServletResponse response) throws Exception {
		if (log.isDebugEnabled())
			log.debug("Entering 'addResource' method");
		initForm(resourceColumnForm, ADD,request);
		return mapping.findForward(ADD);
	}

	public ActionForward editResourceColumn(ActionMapping mapping, SysResourceColumnForm resourceColumnForm, HttpServletRequest request, HttpServletResponse response) throws Exception {
		if (log.isDebugEnabled())
			log.debug("Entering 'editResource' method");
		String id = request.getParameter("record(id)");
		
		ResourceColumnService resourceColumnService = (ResourceColumnService) getBean("resourceColumnService");
		ResourceColumn ResourceColumn = (ResourceColumn) resourceColumnService.findResourceColumn(new Integer(id));
		ConvertUtil.objectToMap(resourceColumnForm.getRecord(), ResourceColumn);
		initForm(resourceColumnForm, EDIT,request);
		List<Code> codeset = resourceColumnForm.getCodeSets().get("datameta");
		if (codeset!=null)
		{
			for (Code cs : codeset)
			{
				if (ResourceColumn.getDatametaId()!=null&&cs.getValue().equals(ResourceColumn.getDatametaId().toString()))
				{
					resourceColumnForm.getRecord().put("datametaName", cs.getCodeName());
				}
			}
		}
		return mapping.findForward(EDIT);
	}

	public ActionForward saveResourceColumn(ActionMapping mapping, SysResourceColumnForm resourceColumnForm, HttpServletRequest request, HttpServletResponse response) throws Exception {
		if (log.isDebugEnabled())
			log.debug("Entering 'saveResource' method");
		ActionForward forward = null;

		try {
			ResourceColumn resourceColumn = new ResourceColumn();
			ConvertUtil.mapToObject(resourceColumn, resourceColumnForm.getRecord());
			Session user = (Session)request.getSession().getAttribute(Const.SESSION);
			if (user==null) {
				user = new Session();
			}
			resourceColumn.setCreateDate(new Date());
			resourceColumn.setCreateBy(user.getAccountName());
			
			if (resourceColumn.getIsForeign()==null||"".equals(resourceColumn.getIsForeign())) {
				resourceColumn.setIsForeign("0");
			}
			if (resourceColumn.getIsNull()==null||"".equals(resourceColumn.getIsNull())) {
				resourceColumn.setIsNull("0");
			}
			if (resourceColumn.getIsPrimary()==null||"".equals(resourceColumn.getIsPrimary())) {
				resourceColumn.setIsPrimary("0");
			}
			ResourceColumnService resourceColumnService = (ResourceColumnService) getBean("resourceColumnService");

			//添加对应数据元的指标id
//			if (resourceColumn.getIndicatorId()==null)
//			{
//				Integer dataMeteId = resourceColumn.getDatametaId();
//				if (dataMeteId!=null) {
//					Integer indicatorId = resourceColumnService.getIndicatorIdByDataMete(dataMeteId);
//					if (indicatorId!=null) {
//						resourceColumn.setIndicatorId(indicatorId);
//					}				
//				}
//			}
			
			resourceColumnService.createResourceColumn(resourceColumn);
			NotifyPool(resourceColumn);
			forward = returnForward(mapping, request, RETURN_NORMAL);
		}catch (ServiceException e) {
			addMessage(resourceColumnForm, e.getMessage());
			initForm(resourceColumnForm, ADD,request);
			forward = mapping.findForward(ADD);
		} 
		catch (Exception e) {
			e.printStackTrace();
			addMessage(resourceColumnForm, "新建操作失败");
			initForm(resourceColumnForm, ADD,request);
			forward = mapping.findForward(ADD);
		}
		return forward;
	}
	
	public ActionForward toEditJsp(ActionMapping mapping, SysResourceColumnForm resourceColumnForm, HttpServletRequest request, HttpServletResponse response){
		if (log.isDebugEnabled()) log.debug("Entering 'toEditJsp' method");
		String id = request.getParameter("record(id)");
		String tableid = request.getParameter("record(tableid)");
		
		if(id == null || "".equals(id)){
			Object obj = request.getAttribute("record(id)");
			if(obj != null){
				id = obj.toString();
			}
		}
		if(tableid == null || "".equals(tableid)){
			Object obj = request.getAttribute("record(tableid)");
			if(obj != null){
				tableid = obj.toString();
			}
		}
		request.setAttribute("record(tableid)", tableid);
		request.setAttribute("record(id)", id);
		return mapping.findForward("EDITWINDOW");
	}

	public ActionForward updateResourceColumn(ActionMapping mapping, SysResourceColumnForm resourceColumnForm, HttpServletRequest request, HttpServletResponse response) throws Exception {
		if (log.isDebugEnabled())
			log.debug("Entering 'updateResource' method");

		ActionForward forward = null;

		try {
			ResourceColumn resourceColumn = new ResourceColumn();
			ConvertUtil.mapToObject(resourceColumn, resourceColumnForm.getRecord());
			Session user = (Session)request.getSession().getAttribute(Const.SESSION);
			if (user==null) {
				user = new Session();
			}
			resourceColumn.setModifyDate(new Date());
			resourceColumn.setModifyBy(user.getAccountName());
			
			if (resourceColumn.getIsForeign()==null||"".equals(resourceColumn.getIsForeign())) {
				resourceColumn.setIsForeign("0");
			}
			if (resourceColumn.getIsNull()==null||"".equals(resourceColumn.getIsNull())) {
				resourceColumn.setIsNull("0");
			}
			if (resourceColumn.getIsPrimary()==null||"".equals(resourceColumn.getIsPrimary())) {
				resourceColumn.setIsPrimary("0");
			}
			ResourceColumnService resourceColumnService = (ResourceColumnService) getBean("resourceColumnService");
			
			//添加对应数据元的指标id,这里默认是如果用户在页面没有选择指标则	
//			if (resourceColumn.getIndicatorId()==null)
//			{
//				Integer dataMeteId = resourceColumn.getDatametaId();
//				if (dataMeteId!=null) {
//					Integer indicatorId = resourceColumnService.getIndicatorIdByDataMete(dataMeteId);
//					if (indicatorId!=null) {
//						resourceColumn.setIndicatorId(indicatorId);
//					}				
//				}
//			}
			
			
			resourceColumnService.updateResourceColumn(resourceColumn);
			NotifyPool(resourceColumn);
			request.setAttribute("msg", "修改成功！");
			forward = returnForward(mapping, request, RETURN_NORMAL);
		}catch (ServiceException e) {
			addMessage(resourceColumnForm, e.getMessage());
			initForm(resourceColumnForm, EDIT,request);
			forward = mapping.findForward(EDIT);
		} 
		catch (Exception e) {
			e.printStackTrace();
			addMessage(resourceColumnForm, "编辑失败");
			initForm(resourceColumnForm, EDIT,request);
			forward = mapping.findForward(EDIT);
		}
		return forward;
	}

	public ActionForward deleteResourceColumn(ActionMapping mapping, SysResourceColumnForm resourceColumnForm, HttpServletRequest request, HttpServletResponse response) throws Exception {
		if (log.isDebugEnabled())
			log.debug("Entering 'deleteResource' method");
		try {
			ResourceColumnService resourceColumnService = (ResourceColumnService) getBean("resourceColumnService");
			String del_ids = request.getParameter("ids");
			if (del_ids != null && del_ids.trim().length() > 0) {
				resourceColumnService.removeResourceColumns(parseId(del_ids.split(";")));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return searchResourceColumn(mapping, resourceColumnForm, request, response);
	}
	
	private ActionForward moveUp(ActionMapping mapping, SysResourceColumnForm form, HttpServletRequest request, HttpServletResponse response)throws Exception{
		if (log.isDebugEnabled())
			log.debug("Entering 'deleteResource' method");
		try {
			ResourceColumnService resourceColumnService = (ResourceColumnService) getBean("resourceColumnService");
			String id = form.getRecord().get("id");
			if (id != null && id.trim().length() > 0) {
				resourceColumnService.moveUp(Integer.valueOf(id));
			}
			request.setAttribute("moveID", id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return searchResourceColumn(mapping, form, request, response);
	}
	
	private ActionForward moveDown(ActionMapping mapping, SysResourceColumnForm form, HttpServletRequest request, HttpServletResponse response)throws Exception{
		if (log.isDebugEnabled())
			log.debug("Entering 'deleteResource' method");
		try {
			ResourceColumnService resourceColumnService = (ResourceColumnService) getBean("resourceColumnService");
			String id = form.getRecord().get("id");
			if (id != null && id.trim().length() > 0) {
				resourceColumnService.moveDown(Integer.valueOf(id));
			}
			request.setAttribute("moveID", id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return searchResourceColumn(mapping, form, request, response);
	}

	public ActionForward startResourceColumn(ActionMapping mapping, SysResourceColumnForm resourceColumnForm, HttpServletRequest request, HttpServletResponse response) throws Exception {

		return searchResourceColumn(mapping, resourceColumnForm, request, response);
	}

	public ActionForward stopResourceColumn(ActionMapping mapping, SysResourceColumnForm resourceColumnForm, HttpServletRequest request, HttpServletResponse response) throws Exception {

		return searchResourceColumn(mapping, resourceColumnForm, request, response);
	}

	public ActionForward searchResourceColumn(ActionMapping mapping, SysResourceColumnForm resourceColumnForm, HttpServletRequest request, HttpServletResponse response) throws Exception {
		if (log.isDebugEnabled())
			log.debug("Entering 'searchResource' method");
		try {
			ResourceColumnService resourceColumnService = (ResourceColumnService) getBean("resourceColumnService");
			PageQuery pageQuery = resourceColumnForm.getQuery();
			pageQuery.setSelectParamId("GET_SYSRESOURCECOLUMN_PAGE");
			//为pageQuery对象添加查询条件
			String tableid = (String)request.getParameter("tableid");
			if (tableid!=null&&!"".equalsIgnoreCase(tableid)) {
				pageQuery.getParameters().put("tableid", tableid);
			}
			String queryString = getQueryString(pageQuery);
			pageQuery.getParameters().put("queryString", queryString);
			// 执行查询并将结果保存到pagequery中，结果集是一个list<map>对象，用map将每个字段名作key，值做value保存起来
			resourceColumnService.queryResourceColumn(pageQuery);
			//处理，将数据类型转换为中文显示
			initForm(resourceColumnForm, SEARCH, request);
			List<Map<String, String>> res = resourceColumnForm.getQuery().getRecordSet();
			for (Map<String, String> map : res) {
				String dataType = (String)map.get("DATA_TYPE");
				if (dataType==null) {
					map.put("DATA_TYPE", "");
				}else {
					map.put("DATA_TYPE", findCodeName(resourceColumnForm, "DATAMETA_TYPE", dataType.trim()));
				}
			}

			setPage(resourceColumnForm.getQuery());
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return mapping.findForward(LIST);
	}

	
	public ActionForward getDataMeta(ActionMapping mapping, SysResourceColumnForm sysResourceColumnForm, 
			HttpServletRequest request, HttpServletResponse response)throws Exception{
		ResourceColumnService resourceColumnService = (ResourceColumnService) getBean("resourceColumnService");
		String dataMetaId = request.getParameter("dataMetaId");
		Element datametax = DocumentHelper.createElement("datameta");
		if (dataMetaId==null||"".equals(dataMetaId)) {
			datametax.addElement("indicator").addText("");
			datametax.addElement("dataType").addText("");
			datametax.addElement("dataCode").addText("");
		}else {
			StandardDataMeta datameta = resourceColumnService.getDataMetaById(Integer.valueOf(dataMetaId));
			if (datameta==null) {
				datameta = new StandardDataMeta();
			}
			datametax.addElement("indicator").addText(datameta.getIndicatorId()==null?"":datameta.getIndicatorId().toString());
			datametax.addElement("dataType").addText(datameta.getDataType()==null?"":datameta.getDataType());
			
			datametax.addElement("dataCode").addText(datameta.getCodesetNo()==null?"":datameta.getCodesetNo());
			response.setContentType("text/xml;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.write(datametax.asXML());
			out.close();
		}
		return null;
	}
	
	private ActionForward getDataMetaTree(ActionMapping mapping, SysResourceColumnForm sysResourceColumnForm, 
		HttpServletRequest request, HttpServletResponse response)throws Exception{
		ResourceColumnService resourceColumnService = (ResourceColumnService) getBean("resourceColumnService");
		String str = resourceColumnService.getAllDataMateForTree();
		if (str==null||"".equals(str))
		{
			str = DocumentHelper.createElement("tree").addAttribute("id", "0").asXML();
		}
		response.setContentType("text/xml;charset=utf-8");
		PrintWriter out = response.getWriter();
		out.write(str);
		out.close();
		return null;
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

	private void initForm(SysResourceColumnForm resourceForm, String action,HttpServletRequest request) {
		setCode(resourceForm, "SECURITY_LEVEL;DATAMETA_TYPE");

		ResourceColumnService resourceColumnService = (ResourceColumnService) getBean("resourceColumnService");
		
		//取得对应指标代码集
		List<StandardIndicator> indicators = resourceColumnService.getIndicators(null);
		if (indicators==null) {
			indicators = new ArrayList<StandardIndicator>();
		}
		//取得对应数据源代码集
		List<StandardDataMeta> datameta = resourceColumnService.getDataMetaCode(null);
		//取得对应代码代码集
		List<StandardCodeset> codeset = resourceColumnService.getCodesetCode(null);
		
		//设置代码集
		setCode(resourceForm, "indicator",indicators,StandardIndicator.PROP_CN_NAME,StandardIndicator.PROP_ID);
		setCode(resourceForm, "datameta", datameta, StandardDataMeta.PROP_CN_NAME, StandardDataMeta.PROP_ID);
		setCode(resourceForm, "codeset", codeset, StandardCode.PROP_NAME, StandardCode.PROP_ID);
		if ("ADD".equalsIgnoreCase(action)) {
			String tableid = request.getParameter("tableid");
			int max_order;
			if (tableid!=null&&!"".equals(tableid))
			{
				resourceForm.getRecord().put("tableId", tableid);
				max_order = resourceColumnService.getMaxOrderFromColumn(tableid)+1;
			}
			else {
				max_order = 0;
			}
			//找到当前排序值的最大值，并保存到页面
			 
			resourceForm.getRecord().put("squenceNo", String.valueOf(max_order));
			
		}
	}

	private String getQueryString(PageQuery pageQuery) {
		
		String order = pageQuery.getOrder();
		if(order==null)order = "";
		String direction = pageQuery.getOrderDirection();
		if(direction==null)direction = "";
		
		if ("".equals(order)||"".equals(direction))
		{
			pageQuery.setOrder("t.squence_no");
			pageQuery.setOrderDirection("asc");
		}

		StringBuffer buffer = new StringBuffer();
		Map fields = pageQuery.getParameters();
		String str = (String) fields.get("tableid");
		if (str != null && !"".equalsIgnoreCase(str)) {
			buffer.append(" and t.table_id" + " = " + str);
		}
		return buffer.toString();
	}
	/**
	 * 刷新缓存对象
	 * @param column
	 */
	private void NotifyPool(ResourceColumn column){
		TableConfigPool tableConfigPool=(TableConfigPool)getBean("tableConfigPool");
		tableConfigPool.clearColumnConfigPool(column.getTableId().toString());
		tableConfigPool.clearColumnPool(column.getId().toString());
	}
}
