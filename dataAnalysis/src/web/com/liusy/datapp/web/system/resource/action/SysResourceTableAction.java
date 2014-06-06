/**
 * SysResourceAction.java Create on 2009-9-23 ����10:45:58 
 * @author ������
 * @date 2009-9-18
 * ����
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

import com.liusy.core.util.Const;
import com.liusy.core.util.JsonUtil;
import com.liusy.core.util.Session;
import com.liusy.dataapplatform.base.exception.ServiceException;
import com.liusy.dataapplatform.base.exception.WebException;
import com.liusy.dataapplatform.base.util.Condition;
import com.liusy.dataapplatform.base.util.ConvertUtil;
import com.liusy.dataapplatform.base.util.ICondition;
import com.liusy.dataapplatform.base.util.PageQuery;
import com.liusy.dataapplatform.code.Code;
import com.liusy.datapp.model.datastandard.StandardCategory;
import com.liusy.datapp.model.query.SubjectTableRelation;
import com.liusy.datapp.model.resource.ResourceTable;
import com.liusy.datapp.service.pool.TableConfigPool;
import com.liusy.datapp.service.query.SubjectTableRelationService;
import com.liusy.datapp.service.resource.ResourceTableService;
import com.liusy.datapp.web.BaseAction;
import com.liusy.datapp.web.system.resource.form.ResourceTableForm;

public class SysResourceTableAction extends BaseAction
{
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.liusy.core.web.BaseAction#doExecute(org.apache.struts.action.
	 * ActionMapping, org.apache.struts.action.ActionForm,
	 * javax.servlet.http.HttpServletRequest,
	 * javax.servlet.http.HttpServletResponse)
	 */

	private static final String CHECK = "CHECK";

	@Override
	public ActionForward doExecute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
		HttpServletResponse response) throws Exception
	{

		String action = request.getParameter("action");
		String id = request.getParameter("id");

		// if (id == null || "".equals(id)) {

		if (action == null)
			action = LIST;
		if (log.isDebugEnabled())
			log.debug("action:" + action);
		ActionForward forward;
		ResourceTableForm resourceTableForm = (ResourceTableForm) form;

		try
		{
			if (LIST.equalsIgnoreCase(action))
				forward = searchResourceTable(mapping, resourceTableForm, request, response); // �򿪲�ѯ�б�ҳ��
			else if (ADD.equalsIgnoreCase(action))
				forward = addResourceTable(mapping, resourceTableForm, request, response);// ������ҳ��
			else if (SAVE.equalsIgnoreCase(action))
				forward = saveResourceTable(mapping, resourceTableForm, request, response);// ������������
			else if (EDIT.equalsIgnoreCase(action))
				forward = editResourceTable(mapping, resourceTableForm, request, response);// ���޸�ҳ��
			else if (UPDATE.equalsIgnoreCase(action))
				forward = updateResourceTable(mapping, resourceTableForm, request, response);// �����޸�����
			else if (DELETE.equalsIgnoreCase(action))
				forward = deleteResourceTable(mapping, resourceTableForm, request, response);// ɾ��
			else if ("MAIN".equalsIgnoreCase(action))
			{
				forward = toMainJSP(mapping, resourceTableForm, request, response); // �������β˵���������
			}
			else if (VIEW.equalsIgnoreCase(action))
			{
				forward = toMainJSP(mapping, resourceTableForm, request, response); // �������β˵���������
			}
			else if ("TOEDIT".equalsIgnoreCase(action))
			{
				forward = toEditJSP(mapping, resourceTableForm, request, response); // �������β˵���������
			}
			else if ("TREE".equalsIgnoreCase(action))
			{
				returnTreeNode(response, request);
				return null;
			}
			else
			{
				request.setAttribute("err", new WebException("�Ҳ�����action������" + action));
				forward = mapping.findForward(ERROR);// �Ҳ������ʵ�action
			}
		}
		catch (Exception e)
		{// ����ϵͳ����

			e.printStackTrace();
			request.setAttribute("err", e);
			forward = mapping.findForward(ERROR);
		}
		return forward;
		// } else {
		// if ("0".equalsIgnoreCase(id)) {
		// // ���ó�ʼ�������ݵķ���(��ȡ���нڵ�)
		// returnCityNode(id, response, request);
		// } else {
		// returnCategoryNode(id, response, request);
		// }
		// return null;
		// }
	}

	public ActionForward addResourceTable(ActionMapping mapping, ResourceTableForm resourceTableForm,
		HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		if (log.isDebugEnabled())
			log.debug("Entering 'addResource' method");
		String nodeId = request.getParameter("nodeId");
		resourceTableForm.getRecord().put("nodeId", nodeId);
		initForm(resourceTableForm, ADD, getCityCode(request));
		return mapping.findForward(ADD);
	}

	public ActionForward toEditJSP(ActionMapping mapping, ResourceTableForm resourceTabelForm,
		HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		if (log.isDebugEnabled())
			log.debug("Entering 'editResource' method");
		String id = request.getParameter("tableid");
		if (id == null || "".equals(id))
		{
			Object oid = request.getAttribute("tableid");
			if (oid != null)
			{
				id = oid.toString();
			}
		}
		request.setAttribute("tableid", id);
		return mapping.findForward("EDITWINDOW");
	}

	public ActionForward editResourceTable(ActionMapping mapping, ResourceTableForm resourceTableForm,
		HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		if (log.isDebugEnabled())
			log.debug("Entering 'editResource' method");
		String id = request.getParameter("record(id)");
		ResourceTableService resourceTableService = (ResourceTableService) getBean("resourceTableService");
		ResourceTable resourceTable = (ResourceTable) resourceTableService.findResourceTable(new Integer(id));
		ConvertUtil.objectToMap(resourceTableForm.getRecord(), resourceTable);

		String nodeId = resourceTableService.getNodeIdByCategoryId(resourceTableForm.getRecord().get("categoryId"));
		if (nodeId != null && !"".equals(nodeId))
		{
			resourceTableForm.getRecord().remove("categoryId");
			resourceTableForm.getRecord().put("categoryId", nodeId);
		}

		String citycode = resourceTable.getCityCode() == null ? "" : resourceTable.getCityCode();
		if (citycode == null)
		{
			citycode = "";
		}
		initForm(resourceTableForm, EDIT, citycode);
		return mapping.findForward(EDIT);
	}

	public ActionForward saveResourceTable(ActionMapping mapping, ResourceTableForm ResourceTableForm,
		HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		if (log.isDebugEnabled())
			log.debug("Entering 'saveResource' method");
		ActionForward forward = null;

		try
		{
			String categoryTempStr = ResourceTableForm.getRecord().get("categoryId");
			if (categoryTempStr != null || !"".equals(categoryTempStr))
			{
				if (categoryTempStr.indexOf("_") != -1)
				{
					String[] strs = categoryTempStr.split("_");
					categoryTempStr = strs[strs.length - 1];
					ResourceTableForm.getRecord().remove("categoryId");
					ResourceTableForm.getRecord().put("categoryId", categoryTempStr);
				}
			}

			// �ڵ���maptoobject���������ʱ��һ��Ҫע�⣬jspҳ��form��Ԫ�ص�����һ��Ҫ�Ͷ�Ӧ��vo�����Ӧ������һ��
			ResourceTable resourceTable = new ResourceTable();
			ConvertUtil.mapToObject(resourceTable, ResourceTableForm.getRecord());
			// ��Ӵ������ڣ���������Ϣ
			 resourceTable.setCreateDate(new Date());
			 Session user =
			 (Session)request.getSession().getAttribute(Const.SESSION);
			 if (user==null) {
			 user = new Session();
			 }
			 resourceTable.setCreateBy(user.getAccountName());
			 resourceTable.setIsSpaceTable(Const.IS_RESOURCETABLE);
			ResourceTableService ResourceTableService = (ResourceTableService) getBean("resourceTableService");

			ResourceTableService.createResourceTable(resourceTable);
			// forward = returnForward(mapping, request, RETURN_NORMAL);
			// ���»���
			NotifyPool(resourceTable);
			request.setAttribute("tableid", resourceTable.getId());
			return toEditJSP(mapping, ResourceTableForm, request, response);

		}
		catch (ServiceException e) {
			addMessage(ResourceTableForm, e.getMessage());
			initForm(ResourceTableForm, ADD, getCityCode(request));
			forward = mapping.findForward(ADD);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			addMessage(ResourceTableForm, "�½�����ʧ��!");
			initForm(ResourceTableForm, ADD, getCityCode(request));
			forward = mapping.findForward(ADD);
		}
		return forward;
	}

	public ActionForward updateResourceTable(ActionMapping mapping, ResourceTableForm ResourceTableForm,
		HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		if (log.isDebugEnabled())
			log.debug("Entering 'updateResource' method");

		ActionForward forward = null;

		try
		{
			ResourceTableService resourceTableService = (ResourceTableService) getBean("resourceTableService");
			String categoryTempStr = ResourceTableForm.getRecord().get("categoryId");
			if (categoryTempStr != null || !"".equals(categoryTempStr))
			{
				if (categoryTempStr.indexOf("_") != -1)
				{
					String[] strs = categoryTempStr.split("_");
					categoryTempStr = strs[strs.length - 1];
					ResourceTableForm.getRecord().remove("categoryId");
					ResourceTableForm.getRecord().put("categoryId", categoryTempStr);
				}
			}
			String tableId = ResourceTableForm.getRecord().get("id");
			ResourceTable resourceTable = resourceTableService.findResourceTable(Integer.valueOf(tableId));
			ConvertUtil.mapToObject(resourceTable, ResourceTableForm.getRecord());
			// ����޸����ڣ��޸�����Ϣ
			 Session user =
			 (Session)request.getSession().getAttribute(Const.SESSION);
			 if (user==null) {
			 user = new Session();
			 }
			 resourceTable.setModifyDate(new Date(System.currentTimeMillis()));
			 resourceTable.setModifyBy(user.getAccountName());
			 resourceTable.setIsSpaceTable(Const.IS_RESOURCETABLE);
			
			resourceTableService.updateResourceTable(resourceTable);
			//���±�����������¼˳��˳��
			Integer orde = resourceTable.getOrde();
			if(orde!=null){
				SubjectTableRelationService subjectTableRelationService = (SubjectTableRelationService)getBean("subjectTableRelationService");
				List<SubjectTableRelation> list = subjectTableRelationService.findSubjectTableByTableId(tableId);
				if (list!=null&&!list.isEmpty()) {
					for (int i = 0; i < list.size(); i++) {
						SubjectTableRelation temp = list.get(i);
						temp.setDtOrde(orde);
						subjectTableRelationService.updateSubjectTableRelation(temp);
					}
				}
			}
			// forward = returnForward(mapping, request, RETURN_NORMAL);
			// ���»���
			NotifyPool(resourceTable);
			request.setAttribute("msg", "�޸ĳɹ���");
			forward = mapping.findForward(UPDATE);
		}
		catch (ServiceException e) {
			addMessage(ResourceTableForm, e.getMessage());
			initForm(ResourceTableForm, EDIT, getCityCode(request));
			forward = mapping.findForward(EDIT);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			addMessage(ResourceTableForm, "�༭����ʧ��");
			initForm(ResourceTableForm, EDIT, getCityCode(request));
			forward = mapping.findForward(EDIT);
		}
		return forward;
	}

	// ɾ�������ص��ֶ�

	public ActionForward deleteResourceTable(ActionMapping mapping, ResourceTableForm ResourceTableForm,
		HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		if (log.isDebugEnabled())
			log.debug("Entering 'deleteResource' method");
		try
		{
			ResourceTableService ResourceTableService = (ResourceTableService) getBean("resourceTableService");
			String del_ids = request.getParameter("ids");
			if (del_ids != null && del_ids.trim().length() > 0)
			{
				ResourceTableService.removeResourceTablesAndColumn(parseId(del_ids.split(";")));
			}
		}
		catch (Exception e)
		{
		}
		return searchResourceTable(mapping, ResourceTableForm, request, response);
	}

	public ActionForward startResourceTable(ActionMapping mapping, ResourceTableForm ResourceTableForm,
		HttpServletRequest request, HttpServletResponse response) throws Exception
	{

		return searchResourceTable(mapping, ResourceTableForm, request, response);
	}

	public ActionForward stopResourceTable(ActionMapping mapping, ResourceTableForm ResourceTableForm,
		HttpServletRequest request, HttpServletResponse response) throws Exception
	{

		return searchResourceTable(mapping, ResourceTableForm, request, response);
	}

	public ActionForward searchResourceTable(ActionMapping mapping, ResourceTableForm resourceTableForm,
		HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		if (log.isDebugEnabled())
			log.debug("Entering 'searchResource' method");
		try
		{
			Map<String, String> fields = resourceTableForm.getQuery().getParameters();
			String str = request.getParameter("treeid");
			if (str == null || "".equals(str))
			{
				str = (String) fields.get("treeid");
			}
			if (str != null && !"".equalsIgnoreCase(str))
			{

				fields.put("treeid", str);
				ResourceTableService ResourceTableService = (ResourceTableService) getBean("resourceTableService");
				PageQuery pageQuery = resourceTableForm.getQuery();
				pageQuery.setSelectParamId("GET_SYSRESOURCETABLE_PAGE");
				String queryString = getQueryString(pageQuery, str);
				pageQuery.setOrder("t."+ResourceTable.COL_ORDE);
				pageQuery.setOrderDirection(PageQuery.DESC);
				pageQuery.getParameters().put("queryString", queryString);
				
				// ִ�в�ѯ����������浽pagequery�У��������һ��list<map>������map��ÿ���ֶ�����key��ֵ��value��������
				ResourceTableService.queryResourceTable(pageQuery);
				setPage(pageQuery);
			}

		}
		catch (Exception e)
		{
			e.printStackTrace();
			throw e;
		}
		setCode(resourceTableForm, "SECURITY_LEVEL");
		return mapping.findForward(LIST);
	}

	public ActionForward toMainJSP(ActionMapping mapping, ResourceTableForm ResourceTableForm,
		HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		if (log.isDebugEnabled())
			log.debug("ת��mainjspҳ��");
		return mapping.findForward("MAIN");
	}

	@Deprecated
	public void searchResourceInfoAjax(ResourceTableForm ResourceTableForm, HttpServletRequest request,
		HttpServletResponse response) throws Exception
	{
		if (log.isDebugEnabled())
		{
			log.debug("Entering 'searchModu' method");
		}
		try
		{
			ResourceTableService ResourceTableService = (ResourceTableService) getBean("resourceTableService");
			PageQuery pageQuery = ResourceTableForm.getQuery();
			ResourceTableService.queryResourceTable(pageQuery);
			JsonUtil.JsonGridOutput(pageQuery, response);
		}
		catch (Exception e)
		{

			e.printStackTrace();
			throw e;
		}
	}

	private java.io.Serializable[] parseId(String[] ids) throws Exception
	{
		if (ids == null || ids.length == 0)
		{
			throw new Exception("�Ƿ�����༭ҳ��");
		}
		java.io.Serializable id[] = null;
		try
		{
			id = new Integer[ids.length];
			for (int i = 0; i < ids.length; i++)
			{
				id[i] = new Integer(ids[i]);
			}
		}
		catch (Exception e)
		{
			id = ids;
		}
		return id;
	}

	private void initForm(ResourceTableForm resourceForm, String action, String citycode)
	{
		setCode(resourceForm, "SECURITY_LEVEL");
		setCode(resourceForm, "DT_TYPE");

		ResourceTableService resourceTableService = (ResourceTableService) getBean("resourceTableService");

		List<Map<String, Object>> categorys = resourceTableService.getAllCategorysForSelection(citycode);
		if (categorys == null)
		{
			categorys = new ArrayList<Map<String, Object>>();
		}
		setCode(resourceForm, "Category", categorys, StandardCategory.PROP_NAME, StandardCategory.PROP_ID);
		List<Code> list0 = resourceForm.getCodeSets().get("Category");
		if (list0 != null)
		{
			list0.remove(0);
		}

		PageQuery query2 = new PageQuery();
		query2.setSelectParamId("GET_SYSCITY_PAGE");
		query2.getParameters().put("queryString", " and a.code='" + citycode + "'");
		query2.setPageSize("0");

		List<Map<String, String>> citys = resourceTableService.loadDataForTree(query2).getRecordSet();
		if (citys == null)
		{
			citys = new ArrayList<Map<String, String>>();
		}
		setCode(resourceForm, "City", citys, "NAME", "CODE");
		List<Code> list = resourceForm.getCodeSets().get("City");
		if (list != null)
		{
			list.remove(0);
		}
		
		if ("ADD".equalsIgnoreCase(action))
		{
			String nodeId = resourceForm.getRecord().get("nodeId");
			if (nodeId != null && !"".equals(nodeId))
			{
				if (nodeId.indexOf("_") != -1)
				{
					String[] ids = nodeId.split("_");
					if (ids.length >= 2)
					{
						resourceForm.getRecord().put("cityCode", ids[0]);
						resourceForm.getRecord().put("categoryId", nodeId.substring(nodeId.indexOf("_") + 1));
					}
				}
			}
		}
	}

	private String getQueryString(PageQuery pageQuery, String str)
	{
		StringBuffer buffer = new StringBuffer();
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

		if (idslength == 1)
		{
			buffer.append(" and " + "t.city_code = '" + ids[0] + "'");
		}
		else if (idslength >= 2)
		{
			Integer[] childIds = getCategoryChildsId(ids[ids.length - 1]);
			buffer.append(" and " + "t.city_code = '" + ids[0] + "'");
			buffer.append(" and (" + "t.category_id" + " = " + ids[ids.length - 1]);
			if (childIds != null)
			{
				for (int i = 0; i < childIds.length; i++)
				{
					buffer.append(" or " + "t.category_id" + " = " + childIds[i]);
				}
			}
			buffer.append(")");
		}
		
		return buffer.toString();
	}

	// private void returnCityNode(String id, HttpServletResponse response,
	// HttpServletRequest request) throws Exception {
	// ResourceTableService resourceTableService = (ResourceTableService)
	// getBean("resourceTableService");
	// // ������ϵ������������������û����ڵ��е����ݹ��˵�
	// PageQuery query2 = new PageQuery();
	// query2.setSelectParamId("GET_SYSCITY_PAGE");
	// query2.getParameters().put("queryString", " and a.code='" +
	// getCityCode(request) + "'");
	// query2.setPageSize("0");
	//
	// List<Map<String, String>> citys =
	// resourceTableService.loadDataForTree(query2).getRecordSet();
	// if (citys == null) {
	// citys = new ArrayList<Map<String, String>>();
	// }
	// // �������ĸ��ڵ�
	// Element root = DocumentHelper.createElement("tree").addAttribute("id",
	// "0");
	//
	// // Ϊ�����ʡ���ڵ�
	// Element province = root.addElement("item").addAttribute("text",
	// "�㶫ʡ��ԴĿ¼").addAttribute("id", "province").addAttribute("open",
	// "1").addAttribute("child", "1");
	// province.addElement("userdata").addAttribute("name",
	// "name").addText("�㶫ʡ��ԴĿ¼");
	//
	// // �����нڵ���ӵ�ʡ���ڵ���
	// for (Map<String, String> city : citys) {
	//
	// province.addElement("item").addAttribute("text",
	// String.valueOf(city.get("NAME"))).addAttribute("id",
	// city.get("CODE")).addAttribute("child",
	// "1").addElement("userdata").addAttribute("name",
	// "name").addText(city.get("NAME"));
	// }
	//
	// response.setContentType("text/xml;charset=UTF-8");
	// PrintWriter writer = response.getWriter();
	// System.out.println(root.asXML());
	// writer.write(root.asXML());
	// writer.close();
	// }
	//
	// private void returnCategoryNode(String id, HttpServletResponse response,
	// HttpServletRequest request) throws Exception {
	// ResourceTableService resourceTableService = (ResourceTableService)
	// getBean("resourceTableService");
	// // ����id������ѯ����,id��û��_���ʾ�ǳ��нڵ�id
	// StringBuffer queryString = new StringBuffer();
	// if (id.indexOf("_") == -1) {
	// queryString.append(" and a.city_code='" + getCityCode(request) + "'");
	// queryString.append(" and a.PARENT_ID is null or a.PARENT_ID=0");
	// } else {
	// String[] ids = id.split("_");
	// queryString.append(" and a.PARENT_ID=" + ids[ids.length - 1]);
	// }
	//
	// PageQuery query1 = new PageQuery();
	// query1.setSelectParamId("GET_STANDARDCATEGORY_PAGE");
	// query1.getParameters().put("queryString", queryString.toString());
	// query1.setPageSize("0");
	//
	// List<Map<String, String>> categorys =
	// resourceTableService.getCategoryForTree(query1).getRecordSet();
	// if (categorys == null) {
	// categorys = new ArrayList<Map<String, String>>();
	// }
	//
	// // �������ĸ��ڵ�
	// Element root = DocumentHelper.createElement("tree").addAttribute("id",
	// id);
	// for (Map<String, String> category : categorys) {
	// Element temp = root.addElement("item").addAttribute("text",
	// category.get("NAME"));
	// temp.addAttribute("id", id + "_" +
	// category.get("ID")).addAttribute("child",
	// "1").addElement("userdata").addAttribute("name",
	// "name").addText(category.get("NAME"));
	//
	// }
	//
	// response.setContentType("text/xml;charset=UTF-8");
	// PrintWriter writer = response.getWriter();
	// System.out.println(root.asXML());
	// writer.write(root.asXML());
	// writer.close();
	// }
	//	

	// һ������ȡ�������������ӽڵ�
	private void returnTreeNode(HttpServletResponse response, HttpServletRequest request)
		throws Exception
	{

		ResourceTableService resourceTableService = (ResourceTableService) getBean("resourceTableService");
		String XmlStr = resourceTableService.getAllTreeNodeAsXmlString(getCityCode(request));

		if (XmlStr == null || "".equals(XmlStr))
		{
			XmlStr = DocumentHelper.createElement("tree").addAttribute("id", "0").addElement("item").addAttribute("text", "�㶫ʡ��ԴĿ¼").addAttribute("id", "province").addAttribute("open", "1").addAttribute("child", "1").addElement("userdata").addAttribute("name", "name").addText("�㶫ʡ��ԴĿ¼").asXML();
		}
		response.setContentType("text/xml;charset=UTF-8");
		PrintWriter writer = response.getWriter();
		writer.write(XmlStr);
		writer.close();
	}

	private String getCityCode(HttpServletRequest request)
	{

		String citycode = null;
		Session session = (Session) request.getSession().getAttribute(Const.SESSION);
		if (session == null)
		{
			citycode = "";
		}
		else
		{
			if (session.getCityCode() != null && !"".equals(session.getCityCode()))
			{
				citycode = session.getCityCode();
			}
			else
			{
				citycode = "";
			}
		}
		return citycode;
	}

	private Integer[] getCategoryChildsId(String parentId)
	{
		ResourceTableService resourceTableService = (ResourceTableService) getBean("resourceTableService");
		List<Map<String, Object>> list = resourceTableService.getAllCategorysByParentId(parentId);
		if (list != null || !list.isEmpty())
		{
			Integer[] ids = new Integer[list.size()];
			for (int i = 0; i < ids.length; i++)
			{
				ids[i] = (Integer) list.get(i).get(StandardCategory.PROP_ID);
			}
			return ids;
		}

		return null;
	}

	private void NotifyPool(ResourceTable table)
	{
		TableConfigPool tableConfigPool = (TableConfigPool) getBean("tableConfigPool");
		tableConfigPool.clearTablePool(table.getId().toString(),table.getName());
	}
}
