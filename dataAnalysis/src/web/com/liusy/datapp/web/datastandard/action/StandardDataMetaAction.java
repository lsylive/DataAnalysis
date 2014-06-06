package com.liusy.datapp.web.datastandard.action;

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
import com.liusy.dataapplatform.base.exception.WebException;
import com.liusy.dataapplatform.base.exception.ServiceException;
import com.liusy.dataapplatform.base.util.ConvertUtil;
import com.liusy.dataapplatform.base.util.PageQuery;
import com.liusy.datapp.model.datastandard.StandardCategory;
import com.liusy.datapp.model.datastandard.StandardIndicator;
import com.liusy.datapp.model.datastandard.StandardCodeset;
import com.liusy.datapp.model.datastandard.StandardDataMeta;
import com.liusy.datapp.service.datastandard.StandardCategoryService;
import com.liusy.datapp.service.datastandard.StandardDataMetaService;
import com.liusy.datapp.web.BaseAction;
import com.liusy.datapp.web.datastandard.form.StandardDataMetaForm;

public class StandardDataMetaAction extends BaseAction {

	private static final String	GETTREE		= "GETTREE";

	@Override
	public ActionForward doExecute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// TODO Auto-generated method stub
		String action = request.getParameter("action");
		if (action==null || "".equalsIgnoreCase(action))
			action = LIST;
		if (log.isDebugEnabled()) log.debug("action:" + action);
		ActionForward forward = null;
		StandardDataMetaForm dataMetaForm = (StandardDataMetaForm)form;
		try {
			if (LIST.equalsIgnoreCase(action)) forward = searchDataMeta(mapping, dataMetaForm, request, response);
			else if (ADD.equalsIgnoreCase(action)) forward = addDataMeta(mapping, dataMetaForm, request, response);
			else if (SAVE.equalsIgnoreCase(action)) forward = saveDataMeta(mapping, dataMetaForm, request, response);
			else if (EDIT.equalsIgnoreCase(action)) forward = editDataMeta(mapping, dataMetaForm, request, response);
			else if (UPDATE.equalsIgnoreCase(action)) forward = updateDataMeta(mapping, dataMetaForm, request, response);
			else if (DELETE.equalsIgnoreCase(action)) forward = deleteDataMeta(mapping, dataMetaForm, request, response);
			else if (GETTREE.equalsIgnoreCase(action)) forward = getTreeNode(mapping, dataMetaForm, request, response);
			else if (VIEW.equalsIgnoreCase(action)) forward = viewDataMeta(mapping, dataMetaForm, request);
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

	private ActionForward searchDataMeta(ActionMapping mapping, StandardDataMetaForm dataMetaForm,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		if (log.isDebugEnabled()) log.debug("Entering 'searchDataMeta' method");

		try {
			StandardDataMetaService dataMetaService = (StandardDataMetaService) getBean("standardDataMetaService");
			PageQuery pageQuery = dataMetaForm.getQuery();
			String categoryid = request.getParameter("categoryid");
			if (categoryid!=null&&!"".equals(categoryid))
			{
				pageQuery.getParameters().put("categoryid", categoryid);
			}
			pageQuery.setSelectParamId("GET_STANDARDDATAMETA_PAGE");
			String queryString = getQueryString(pageQuery);
			pageQuery.getParameters().put("queryString", queryString);
			dataMetaService.queryStandardDataMeta(pageQuery);

			initForm(dataMetaForm, LIST);
			List<Map<String,String>> list = pageQuery.getRecordSet();
			for (int i = 0; i < list.size(); i++) {
				Map<String,String> record = list.get(i);
				String dataType = (String)record.get(StandardDataMeta.PROP_DATA_TYPE.toUpperCase());
				if (dataType == null) record.put(StandardDataMeta.PROP_DATA_TYPE.toUpperCase(), "");
				else record.put(StandardDataMeta.PROP_DATA_TYPE.toUpperCase(), findCodeName(dataMetaForm, "DATAMETA_TYPE", record.get(StandardDataMeta.PROP_DATA_TYPE.toUpperCase())));
				String categoryId = (String)record.get(StandardDataMeta.PROP_CATEGORY_ID.toUpperCase());
				if (categoryId == null) record.put(StandardDataMeta.PROP_CATEGORY_ID.toUpperCase(), "");
				else record.put(StandardDataMeta.PROP_CATEGORY_ID.toUpperCase(), findCodeName(dataMetaForm, "categorys", record.get(StandardDataMeta.PROP_CATEGORY_ID.toUpperCase())));
			}

			setPage(dataMetaForm.getQuery());
			return mapping.findForward(LIST);
		}
		catch(ServiceException e) {
			request.setAttribute("errMsg", e.getMessage());
			return mapping.findForward(ERROR);
		}
		catch(Exception e) {
			e.printStackTrace();
			request.setAttribute("err", e);
			return mapping.findForward(ERROR);
		}
	}

	private ActionForward addDataMeta(ActionMapping mapping, StandardDataMetaForm dataMetaForm,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		if (log.isDebugEnabled()) log.debug("Entering 'addDataMeta' method");

		initForm(dataMetaForm, ADD);
		String categoryId = request.getParameter("categoryid");
		dataMetaForm.getRecord().put(StandardDataMeta.PROP_CATEGORY_ID, categoryId);
		return mapping.findForward(ADD);
	}

	private ActionForward saveDataMeta(ActionMapping mapping, StandardDataMetaForm dataMetaForm,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		if (log.isDebugEnabled()) log.debug("Entering 'saveDataMeta' method");

		ActionForward forward = null;
		try {
			StandardDataMeta dataMeta = new StandardDataMeta();
			ConvertUtil.mapToObject(dataMeta, dataMetaForm.getRecord());
			Session currentUser = (Session)request.getSession().getAttribute(Const.SESSION);
			if (currentUser != null)
				dataMeta.setCreateBy(currentUser.getUserId());
			dataMeta.setCreateDate(new Date());
			StandardDataMetaService dataMetaService = (StandardDataMetaService) getBean("standardDataMetaService");
			dataMetaService.createStandardDataMeta(dataMeta);
			
			forward = returnForward(mapping, request, RETURN_NORMAL);
		}catch (ServiceException e) {
			initForm(dataMetaForm, ADD);
			addMessage(dataMetaForm, e.getMessage());
			forward = mapping.findForward(ADD);
		}
		catch(Exception e) {
			e.printStackTrace();
			initForm(dataMetaForm, ADD);
			addMessage(dataMetaForm, "保存操作失败");
			forward = mapping.findForward(ADD);
		}
		return forward;
	}

	private ActionForward editDataMeta(ActionMapping mapping, StandardDataMetaForm dataMetaForm,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		if (log.isDebugEnabled()) log.debug("Entering 'editDataMeta' method");
		String id = request.getParameter("id");
		initForm(dataMetaForm, EDIT,id);
		initForm(dataMetaForm, EDIT);
		return mapping.findForward(EDIT);
	}

	private ActionForward updateDataMeta(ActionMapping mapping, StandardDataMetaForm dataMetaForm,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		if (log.isDebugEnabled()) log.debug("Entering 'updateDataMeta' method");

		try {
			StandardDataMetaService dataMetaService = (StandardDataMetaService) getBean("standardDataMetaService");
			StandardDataMeta dataMeta = dataMetaService.findStandardDataMeta(new Integer(dataMetaForm.getRecord().get("id")));
			if (dataMeta != null) {
				ConvertUtil.mapToObject(dataMeta, dataMetaForm.getRecord());
				Session currentUser = (Session)request.getSession().getAttribute(Const.SESSION);
				if (currentUser != null)
					dataMeta.setModifyBy(currentUser.getUserId());
				dataMeta.setModifyDate(new Date());
				dataMetaService.updateStandardDataMeta(dataMeta);
				addMessage(dataMetaForm, "修改成功");
				//request.setAttribute("msg", "修改成功！");
				return returnForward(mapping, request, RETURN_NORMAL);
			}
			else {
				//request.setAttribute("msg", "数据元记录不存在");
				addMessage(dataMetaForm, "数据元记录不存在！");
				return mapping.findForward(ERROR);
			}
		}catch (ServiceException e) {
			addMessage(dataMetaForm, e.getMessage());
			String id=dataMetaForm.getRecord().get("id");
			initForm(dataMetaForm, EDIT,id);
			initForm(dataMetaForm, EDIT);
			return mapping.findForward(EDIT);
		} 
		catch (Exception e) {
			e.printStackTrace();
			addMessage(dataMetaForm, "编辑操作失败");
			String id=dataMetaForm.getRecord().get("id");
			initForm(dataMetaForm, EDIT,id);
			initForm(dataMetaForm, EDIT);
			return mapping.findForward(EDIT);
		}
	}

	private ActionForward deleteDataMeta(ActionMapping mapping, StandardDataMetaForm dataMetaForm,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		if (log.isDebugEnabled()) log.debug("Entering 'deleteDataMeta' method");

		try {
			StandardDataMetaService dataMetaService = (StandardDataMetaService) getBean("standardDataMetaService");
			String del_ids = request.getParameter("ids");
			if (del_ids != null && del_ids.trim().length() > 0) {
				dataMetaService.removeStandardDataMetas(parseId(del_ids.split(";")));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return searchDataMeta(mapping, dataMetaForm, request, response);		
	}

	private ActionForward getTreeNode(ActionMapping mapping, StandardDataMetaForm dataMetaForm,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		if (log.isDebugEnabled()) log.debug("Entering 'getTreeNode' mathod");

		StandardDataMetaService standardDataMetaService = (StandardDataMetaService)getBean("standardDataMetaService");
		String id = request.getParameter("id");
		PageQuery query = new PageQuery();
		StringBuffer str = new StringBuffer();
		Element root = DocumentHelper.createElement("tree");
		Element topNode = null;
		if (id == null || "".equalsIgnoreCase(id)) {
			str.append(" and a." + StandardCategory.COL_PARENT_ID + " = 0");
			
			root.addAttribute("id", "0");
			topNode = root.addElement("item")
							.addAttribute("text", "广东省数据元分类")
							.addAttribute("id", "root")
							.addAttribute("open", "1")
							.addAttribute("child", "1");
			topNode.addElement("userdata")
						.addAttribute("name", "name")
						.addText("广东省数据元分类");
		}
		else {
			str.append(" and a." + StandardCategory.COL_PARENT_ID + " = " + id);
			root.addAttribute("id", id);
			topNode = root;
		}
			query.setSelectParamId("GET_STANDARDCATEGORY_PAGE");
			query.getParameters().put("queryString", str.toString());
			query.setOrder(StandardCategory.COL_SQUENCE_NO);
			query.setOrderDirection(PageQuery.ASC);
			query.setPageSize("0");
			List<Map<String, String>> categorys = standardDataMetaService.getCategorysForTree(query).getRecordSet();
			if (categorys==null) {
				categorys = new ArrayList<Map<String, String>>();
			}

			for (Map<String, String> category : categorys) {
				topNode.addElement("item")
						.addAttribute("text", category.get(StandardCategory.PROP_NAME.toUpperCase()))
						.addAttribute("id", category.get(StandardCategory.PROP_ID.toUpperCase()))
						.addAttribute("child", "1")
//						.addAttribute("im0", "folderClosed.gif")
					.addElement("userdata")
						.addAttribute("name", "name")
						.addText(category.get(StandardCategory.PROP_NAME.toUpperCase()));
			}
			root.addAttribute("hasChild", Integer.toString(categorys.size()));

			response.setContentType("text/xml;charset=UTF-8");
			System.out.println(root.asXML());
			response.getWriter().write(root.asXML());
			response.getWriter().close();
/*		String id = request.getParameter("id");
		if (id == null || "".equalsIgnoreCase(id)) {
			try {
				StandardDataMetaService dataMetaService = (StandardDataMetaService)getBean("standardDataMetaService");

				PageQuery query = new PageQuery();
				StringBuffer buffer = new StringBuffer();
				buffer.append(" and a." + StandardCategory.COL_PARENT_ID + " = 0");
				query.setSelectParamId("GET_STANDARDCATEGORY_PAGE");
				query.getParameters().put("queryString", buffer.toString());
				query.setPageSize("0");
				List<Map<String, String>> categorys = dataMetaService.getCategorysForTree(query).getRecordSet();
				if (categorys==null) {
					categorys = new ArrayList<Map<String, String>>();
				}

				Element root = DocumentHelper.createElement("tree")
							.addAttribute("id", "0");
				Element topNode = root.addElement("item")
							.addAttribute("text", "广东省数据元分类")
							.addAttribute("id", "root")
							.addAttribute("open", "1")
							.addAttribute("child", "1");
				topNode.addElement("userdata")
							.addAttribute("name", "name")
							.addText("广东省数据元分类");
				for (Map<String, String> category : categorys) {
					topNode.addElement("item")
							.addAttribute("text", category.get(StandardCategory.PROP_NAME.toUpperCase()))
							.addAttribute("id", category.get(StandardCategory.PROP_ID.toUpperCase()))
							.addAttribute("im0", "folderClosed.gif")
						.addElement("userdata")
							.addAttribute("name", "name")
							.addText(category.get(StandardCategory.PROP_NAME.toUpperCase()));
				}

				response.setContentType("text/xml;charset=UTF-8");
				response.getWriter().write(root.asXML());
				response.getWriter().close();
			}
			catch(Exception e){
				e.printStackTrace();
			}
		}*/
		return null;
	}

	private ActionForward viewDataMeta(ActionMapping mapping, StandardDataMetaForm dataMetaForm,
			HttpServletRequest request) throws Exception{
		if (log.isDebugEnabled()) log.debug("Entering 'viewDataMeta' method");

		String id = request.getParameter("id");
		StandardDataMetaService dataMetaService = (StandardDataMetaService) getBean("standardDataMetaService");
		StandardDataMeta dataMeta = (StandardDataMeta) dataMetaService.findStandardDataMeta(new Integer(id));
		ConvertUtil.objectToMap(dataMetaForm.getRecord(), dataMeta);

		initForm(dataMetaForm, VIEW);

		return mapping.findForward(VIEW);
	}
	
	private void initForm(StandardDataMetaForm dataMetaForm, String action,String id){
		StandardDataMetaService dataMetaService = (StandardDataMetaService) getBean("standardDataMetaService");
		StandardDataMeta dataMeta = (StandardDataMeta) dataMetaService.findStandardDataMeta(new Integer(id));
		try {
			ConvertUtil.objectToMap(dataMetaForm.getRecord(), dataMeta);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		StandardCategoryService standardCategoryService = (StandardCategoryService) getBean("standardCategoryService");
		String categoryName = (String)standardCategoryService.findStandardCategory(dataMeta.getCategoryId()).getName();
		dataMetaForm.setCategoryName(categoryName);
	}

	private void initForm(StandardDataMetaForm dataMetaForm, String action) {
		StandardDataMetaService dataMetaService = (StandardDataMetaService) getBean("standardDataMetaService");
		//下面为系统代码
		setCode(dataMetaForm, "DATAMETA_TYPE");

	//下面为业务代码
		//StandardDataMetaService dataMetaService = (StandardDataMetaService) getBean("standardDataMetaService");
		PageQuery query = new PageQuery();
		query.setSelectParamId("GET_STANDARDCATEGORY_PAGE");
		query.getParameters().put("queryString", "");//" and a."+StandardCategory.COL_PARENT_ID+" > 0");
		query.setPageSize("0");
		List<Map<String, String>> categorys = dataMetaService.getCategorysForTree(query).getRecordSet();
		if (categorys==null) {
			categorys = new ArrayList<Map<String, String>>();
		}
		setCode(dataMetaForm, "categorys", categorys, StandardCategory.PROP_NAME.toUpperCase(), StandardCategory.PROP_ID.toUpperCase());
		PageQuery query2 = new PageQuery();
		query2.setSelectParamId("GET_STANDARDINDICATOR_PAGE");
		query2.getParameters().put("queryString", "");
		query2.setPageSize("0");
		List<Map<String, String>> indicators = dataMetaService.getIndicatorsForTree(query2).getRecordSet();
		if (indicators==null) {
			indicators = new ArrayList<Map<String, String>>();
		}
		setCode(dataMetaForm, "indicators", indicators, StandardIndicator.PROP_CN_NAME.toUpperCase(), StandardIndicator.PROP_ID.toUpperCase());
		PageQuery query3 = new PageQuery();
		query3.setSelectParamId("GET_STANDARDCODESET_PAGE");
		query3.getParameters().put("queryString", "");
		query3.setPageSize("0");
		List<Map<String, String>> codesets = dataMetaService.getCodesetsForTree(query3).getRecordSet();
		if (codesets==null) {
			codesets = new ArrayList<Map<String, String>>();
		}
		setCode(dataMetaForm, "codesets", codesets, StandardCodeset.PROP_NAME.toUpperCase(), StandardCodeset.PROP_CODESET_NO.toUpperCase());

	//填充初始化页面
		if (ADD.equalsIgnoreCase(action)) {
			dataMetaForm.getRecord().put(StandardDataMeta.PROP_DATA_TYPE, "STRING");
		}
		else if (VIEW.equalsIgnoreCase(action)) {
			dataMetaForm.getRecord().put("categoryName", findCodeName(dataMetaForm, "categorys", dataMetaForm.getRecord().get(StandardDataMeta.PROP_CATEGORY_ID)));
			dataMetaForm.getRecord().put("indicatorName", findCodeName(dataMetaForm, "indicators", dataMetaForm.getRecord().get(StandardDataMeta.PROP_INDICATOR_ID)));
			dataMetaForm.getRecord().put("dataTypeName", findCodeName(dataMetaForm, "DATAMETA_TYPE", dataMetaForm.getRecord().get(StandardDataMeta.PROP_DATA_TYPE)));
		}
	}

	private String getQueryString(PageQuery pageQuery){
		StringBuffer buffer = new StringBuffer();
		Map fields = pageQuery.getParameters();
		String str = (String) fields.get("categoryid");
		if ("root".equalsIgnoreCase(str))
			buffer.append("");
		else if (str != null && !"".equalsIgnoreCase(str))
			buffer.append(" and t." + StandardDataMeta.COL_CATEGORY_ID + " = " + str);
		else
			buffer.append(" and t." + StandardDataMeta.COL_CATEGORY_ID + " is null");
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
}