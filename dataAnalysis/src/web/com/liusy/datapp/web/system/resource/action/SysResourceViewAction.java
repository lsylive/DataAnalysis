/**
 * SysResourceViewAction.java Create on 2009-10-16 上午09:35:00 
 * @author 黄少淘
 * @date 2009-9-18
 * 作用
 *       
 */
package com.liusy.datapp.web.system.resource.action;

import java.io.PrintWriter;
import java.util.ArrayList;
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
import com.liusy.core.util.JsonUtil;
import com.liusy.core.util.Session;
import com.liusy.dataapplatform.base.exception.WebException;
import com.liusy.dataapplatform.base.util.ConvertUtil;
import com.liusy.dataapplatform.base.util.PageQuery;
import com.liusy.datapp.model.datastandard.StandardCategory;
import com.liusy.datapp.model.resource.ResourceTable;
import com.liusy.datapp.model.system.config.SysCity;
import com.liusy.datapp.service.resource.ResourceTableService;
import com.liusy.datapp.web.BaseAction;
import com.liusy.datapp.web.system.resource.form.ResourceTableForm;

public class SysResourceViewAction extends BaseAction {

	public ActionForward doExecute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

		String action = request.getParameter("action");
		String id = request.getParameter("id");

		if (id == null || "".equals(id)) {

			if (action == null)
				action = LIST;
			if (log.isDebugEnabled())
				log.debug("action:" + action);
			ActionForward forward;
			ResourceTableForm resourceTableForm = (ResourceTableForm) form;

			try {
				if (LIST.equalsIgnoreCase(action))
					forward = searchResourceTable(mapping, resourceTableForm, request, response); // 打开查询列表页面
				else if (ADD.equalsIgnoreCase(action))
					forward = addResourceTable(mapping, resourceTableForm, request, response);// 打开增加页面
				else if (SAVE.equalsIgnoreCase(action))
					forward = saveResourceTable(mapping, resourceTableForm, request, response);// 保存增加数据
				else if (EDIT.equalsIgnoreCase(action))
					forward = editResourceTable(mapping, resourceTableForm, request, response);// 打开修改页面
				else if (UPDATE.equalsIgnoreCase(action))
					forward = updateResourceTable(mapping, resourceTableForm, request, response);// 保存修改数据
				else if (DELETE.equalsIgnoreCase(action))
					forward = deleteResourceTable(mapping, resourceTableForm, request, response);// 删除
				else if ("MAIN".equalsIgnoreCase(action)) {
					forward = toMainJSP(mapping, resourceTableForm, request, response); // 到有树形菜单的主界面
				} else if (VIEW.equalsIgnoreCase(action)) {
					forward = viewResourceTableProp(mapping, resourceTableForm, request, response); // 属性查看页面
				} else {
					request.setAttribute("err", new WebException("找不到该action方法：" + action));
					forward = mapping.findForward(ERROR);// 找不到合适的action
				}

			} catch (Exception e) {// 其他系统出错

				e.printStackTrace();
				request.setAttribute("err", e);
				forward = mapping.findForward(ERROR);
			}
			return forward;
		} else {
			if ("0".equalsIgnoreCase(id)) {
				// 调用初始化树数据的方法(抽取地市节点)
				returnCityNode(id, response, request);
			} else {
				returnCategoryNode(id, response, request);
			}
			return null;
		}
	}

	public ActionForward addResourceTable(ActionMapping mapping, ResourceTableForm resourceTableForm, HttpServletRequest request, HttpServletResponse response) throws Exception {
		if (log.isDebugEnabled())
			log.debug("Entering 'addResource' method");
		String nodeId = request.getParameter("nodeId");
		resourceTableForm.getRecord().put("nodeId", nodeId);
		initForm(resourceTableForm, ADD);
		return mapping.findForward(ADD);
	}

	public ActionForward editResourceTable(ActionMapping mapping, ResourceTableForm resourceTableForm, HttpServletRequest request, HttpServletResponse response) throws Exception {
		if (log.isDebugEnabled())
			log.debug("Entering 'editResource' method");
		String id = request.getParameter("record(id)");
		ResourceTableService resourceTableService = (ResourceTableService) getBean("resourceTableService");
		ResourceTable resourceTable = (ResourceTable) resourceTableService.findResourceTable(new Integer(id));
		ConvertUtil.objectToMap(resourceTableForm.getRecord(), resourceTable);

		String citycode = resourceTable.getCityCode() == null ? "" : resourceTable.getCityCode();
		if (citycode == null) {
			citycode = "";
		}
		initForm(resourceTableForm, EDIT);
		return mapping.findForward(EDIT);
	}

	public ActionForward saveResourceTable(ActionMapping mapping, ResourceTableForm ResourceTableForm, HttpServletRequest request, HttpServletResponse response) throws Exception {
		if (log.isDebugEnabled())
			log.debug("Entering 'saveResource' method");
		ActionForward forward = null;

		try {
			ResourceTable ResourceTable = new ResourceTable();
			// 在调用maptoobject这个方法的时候一定要注意，jsp页面form表单元素的名字一定要和对应的vo对象对应的属性一致
			ConvertUtil.mapToObject(ResourceTable, ResourceTableForm.getRecord());
			ResourceTableService ResourceTableService = (ResourceTableService) getBean("resourceTableService");

			ResourceTableService.createResourceTable(ResourceTable);
			forward = returnForward(mapping, request, RETURN_NORMAL);
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("operstatus", "0");
			ResourceTableForm.setErrorMessage("新建操作失败");
			forward = mapping.findForward(ADD);
		}
		return forward;
	}

	public ActionForward updateResourceTable(ActionMapping mapping, ResourceTableForm ResourceTableForm, HttpServletRequest request, HttpServletResponse response) throws Exception {
		if (log.isDebugEnabled())
			log.debug("Entering 'updateResource' method");

		ActionForward forward = null;

		try {
			ResourceTable ResourceTable = new ResourceTable();
			ConvertUtil.mapToObject(ResourceTable, ResourceTableForm.getRecord());
			ResourceTableService ResourceTableService = (ResourceTableService) getBean("resourceTableService");
			ResourceTableService.updateResourceTable(ResourceTable);
			// forward = returnForward(mapping, request, RETURN_NORMAL);
			request.setAttribute("msg", "修改成功！");
			forward = mapping.findForward(UPDATE);
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("operstatus", "0");
			request.setAttribute("msg", "编辑操作失败");
		}
		return forward;
	}

	// 删除表和相关的字段

	public ActionForward deleteResourceTable(ActionMapping mapping, ResourceTableForm ResourceTableForm, HttpServletRequest request, HttpServletResponse response) throws Exception {
		if (log.isDebugEnabled())
			log.debug("Entering 'deleteResource' method");
		try {
			ResourceTableService ResourceTableService = (ResourceTableService) getBean("resourceTableService");
			String del_ids = request.getParameter("ids");
			if (del_ids != null && del_ids.trim().length() > 0) {
				ResourceTableService.removeResourceTablesAndColumn(parseId(del_ids.split(";")));
			}
		} catch (Exception e) {
		}
		return searchResourceTable(mapping, ResourceTableForm, request, response);
	}

	public ActionForward startResourceTable(ActionMapping mapping, ResourceTableForm ResourceTableForm, HttpServletRequest request, HttpServletResponse response) throws Exception {

		return searchResourceTable(mapping, ResourceTableForm, request, response);
	}

	public ActionForward stopResourceTable(ActionMapping mapping, ResourceTableForm ResourceTableForm, HttpServletRequest request, HttpServletResponse response) throws Exception {

		return searchResourceTable(mapping, ResourceTableForm, request, response);
	}

	public ActionForward searchResourceTable(ActionMapping mapping, ResourceTableForm resourceTableForm, HttpServletRequest request, HttpServletResponse response) throws Exception {

		try {
			if (log.isDebugEnabled())
				log.debug("Entering 'searchResource' method");

			Map<String,String> fields = resourceTableForm.getQuery().getParameters();
			String str = (String)request.getParameter("treeid");
			if (str==null||"".equals(str)) {
				str = (String) fields.get("treeid");
			}
			
			if (str != null && !"".equalsIgnoreCase(str)) {
				fields.put("treeid", str);
				ResourceTableService ResourceTableService = (ResourceTableService) getBean("resourceTableService");
				PageQuery pageQuery = resourceTableForm.getQuery();
				pageQuery.setSelectParamId("GET_SYSRESOURCETABLE_PAGE");
				String queryString = getQueryString(pageQuery, str);
				pageQuery.getParameters().put("queryString", queryString);
				// 执行查询并将结果保存到pagequery中，结果集是一个list<map>对象，用map将每个字段名作key，值做value保存起来
				ResourceTableService.queryResourceTable(pageQuery);
				setPage(resourceTableForm.getQuery());
			}

		} catch (Exception e) {

			e.printStackTrace();
			throw e;
		}
		setCode(resourceTableForm, "SECURITY_LEVEL");
		return mapping.findForward(LIST);
	}

	public ActionForward toMainJSP(ActionMapping mapping, ResourceTableForm ResourceTableForm, HttpServletRequest request, HttpServletResponse response) throws Exception {
		if (log.isDebugEnabled())
			log.debug("转到mainjsp页面");
		return mapping.findForward("MAIN");
	}

	public ActionForward viewResourceTableProp(ActionMapping mapping, ResourceTableForm resourceTableForm, HttpServletRequest request, HttpServletResponse response) throws Exception {
		if (log.isDebugEnabled())
			log.debug("转到viewjsp页面");
		String id = request.getParameter("record(id)");
		ResourceTableService resourceTableService = (ResourceTableService) getBean("resourceTableService");
		ResourceTable resourceTable = (ResourceTable) resourceTableService.findResourceTable(new Integer(id));
		ConvertUtil.objectToMap(resourceTableForm.getRecord(), resourceTable);

		String citycode = resourceTable.getCityCode() == null ? "" : resourceTable.getCityCode();
		if (citycode == null) {
			citycode = "";
		}
		initForm(resourceTableForm, EDIT);
		return mapping.findForward("VIEW");
	}
	@Deprecated
	public void searchResourceInfoAjax(ResourceTableForm ResourceTableForm, HttpServletRequest request, HttpServletResponse response) throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'searchModu' method");
		}
		try {
			ResourceTableService ResourceTableService = (ResourceTableService) getBean("resourceTableService");
			PageQuery pageQuery = ResourceTableForm.getQuery();
			ResourceTableService.queryResourceTable(pageQuery);
			JsonUtil.JsonGridOutput(pageQuery, response);
		} catch (Exception e) {

			e.printStackTrace();
			throw e;
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
		} catch (Exception e) {
			id = ids;
		}
		return id;
	}

	private void initForm(ResourceTableForm ResourceForm, String action) {
		String cityCode = ResourceForm.getRecord().get(ResourceTable.PROP_CITY_CODE);
		Integer categoryId = Integer.valueOf(ResourceForm.getRecord().get(ResourceTable.PROP_CATEGORY_ID));
		ResourceTableService resourceTableService = (ResourceTableService) getBean("resourceTableService");

		SysCity city = resourceTableService.getSysCityByCode(cityCode);
		if (city == null) {
			city = new SysCity();
			city.setName("");
		}
		StandardCategory category = resourceTableService.getStandardcCategoryById(categoryId);
		if (category == null) {
			category = new StandardCategory();
			category.setName("");
		}

		ResourceForm.getRecord().put("cityName", city.getName());
		ResourceForm.getRecord().put("categoryName", category.getName());
	}

	private String getQueryString(PageQuery pageQuery, String str) {
		
		String order = pageQuery.getOrder();
		if (order==null)
		{
			order = "";
		}
		String dire = pageQuery.getOrderDirection();
		if (dire==null)
		{
			dire = "";
		}
		if ("".equals(order)&&"".equals(dire))
		{
			pageQuery.setOrder("CN_NAME");
			pageQuery.setOrderDirection(PageQuery.ASC);
		}
		StringBuffer buffer = new StringBuffer();
		
		String cnName = pageQuery.getParameters().get("cnName");
		String level = pageQuery.getParameters().get("securityLevel");
		
		if (cnName!=null&&!"".equals(cnName))
		{
			if (cnName.indexOf("%")>-1)
			{
				buffer.append(" and t.cn_name like '"+cnName+"'");
			}else {
				buffer.append(" and t.cn_name like '%"+cnName+"%'");
			}	
		}
		
		if (level!=null&&!"".equals(level))
		{
			buffer.append(" and t.security_level = '"+level+"'");
		}
		
		String[] ids = str.split("_");
		int idslength = ids.length;

		if (idslength == 1) {
			buffer.append(" and " + "t.city_code = '" + str + "'");
		} else if (idslength >= 2) {
			Integer[] childIds = getCategoryChildsId(ids[ids.length - 1]);
			buffer.append(" and " + "t.city_code = '" + ids[0] + "'");
			buffer.append(" and (" + "t.category_id" + " = " + ids[ids.length - 1]);
			if (childIds != null) {
				for (int i = 0; i < childIds.length; i++) {
					buffer.append(" or " + "t.category_id" + " = " + childIds[i]);
				}
			}
			buffer.append(")");
		}

		return buffer.toString();
	}

	private void returnCityNode(String id, HttpServletResponse response, HttpServletRequest request) throws Exception {
		ResourceTableService resourceTableService = (ResourceTableService) getBean("resourceTableService");
		PageQuery query2 = new PageQuery();
		query2.setSelectParamId("GET_SYSCITY_PAGE");
		query2.getParameters().put("queryString", "");
		query2.setPageSize("0");

		List<Map<String, String>> citys = resourceTableService.loadDataForTree(query2).getRecordSet();
		if (citys == null) {
			citys = new ArrayList<Map<String, String>>();
		}
		// 创建树的根节点
		Element root = DocumentHelper.createElement("tree").addAttribute("id", "0");

		// 为树添加省根节点
		Element province = root.addElement("item").addAttribute("text", "广东省资源目录").addAttribute("id", "province").addAttribute("open", "1").addAttribute("child", "1")
		.addAttribute("im0", "book_titel.gif").addAttribute("im1", "books_open.gif").addAttribute("im2", "book.gif");
		province.addElement("userdata").addAttribute("name", "name").addText("广东省资源目录");

		// 将地市节点添加到省根节点上
		for (Map<String, String> city : citys) {

			province.addElement("item").addAttribute("text", String.valueOf(city.get("NAME"))).addAttribute("id", city.get("CODE"))
			.addAttribute("child", "1").addAttribute("im0", "book_titel.gif").addAttribute("im1", "books_open.gif").addAttribute("im2", "book.gif")
			.addElement("userdata").addAttribute("name", "name").addText(city.get("NAME"));
		}

		response.setContentType("text/xml;charset=UTF-8");
		PrintWriter writer = response.getWriter();
		System.out.println(root.asXML());
		writer.write(root.asXML());
		writer.close();
	}

	private void returnCategoryNode(String id, HttpServletResponse response, HttpServletRequest request) throws Exception {
		ResourceTableService resourceTableService = (ResourceTableService) getBean("resourceTableService");
		// 根据id创建查询条件,id中没有_则表示是城市节点id
		StringBuffer queryString = new StringBuffer();
		if (id.indexOf("_") == -1) {
			queryString.append(" and (a.PARENT_ID is null or a.PARENT_ID=0)");
		} else {
			String[] ids = id.split("_");
			queryString.append(" and a.PARENT_ID=" + ids[ids.length - 1]);
		}

		PageQuery query1 = new PageQuery();
		query1.setSelectParamId("GET_STANDARDCATEGORY_PAGE");
		query1.getParameters().put("queryString", queryString.toString());
		query1.setPageSize("0");

		List<Map<String, String>> categorys = resourceTableService.getCategoryForTree(query1).getRecordSet();
		if (categorys == null) {
			categorys = new ArrayList<Map<String, String>>();
		}

		// 创建树的根节点
		Element root = DocumentHelper.createElement("tree").addAttribute("id", id);
		for (Map<String, String> category : categorys) {
			Element temp = root.addElement("item").addAttribute("text", category.get("NAME"))
			.addAttribute("im0", "book_titel.gif").addAttribute("im1", "books_open.gif").addAttribute("im2", "book.gif");
			temp.addAttribute("id", id + "_" + category.get("ID")).addAttribute("child", "1").addElement("userdata").addAttribute("name", "name").addText(category.get("NAME"));

		}

		response.setContentType("text/xml;charset=UTF-8");
		response.setHeader("Cache_Control", "no-cache");
		PrintWriter writer = response.getWriter();
		writer.write(root.asXML());
		writer.close();
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

	private Integer[] getCategoryChildsId(String parentId) {
		ResourceTableService resourceTableService = (ResourceTableService) getBean("resourceTableService");
		List<Map<String, Object>> list = resourceTableService.getAllCategorysByParentId(parentId);
		if (list != null || !list.isEmpty()) {
			Integer[] ids = new Integer[list.size()];
			for (int i = 0; i < ids.length; i++) {
				ids[i] = (Integer) list.get(i).get(StandardCategory.PROP_ID);
			}
			return ids;
		}

		return null;
	}
}
