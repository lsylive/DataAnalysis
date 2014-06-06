package com.liusy.datapp.web.datastandard.action;

import java.util.Date;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;

import org.dom4j.Element;
import org.dom4j.DocumentHelper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.liusy.dataapplatform.base.util.ConvertUtil;
import com.liusy.dataapplatform.base.util.PageQuery;

import com.liusy.core.util.Const;
import com.liusy.core.util.Session;
import com.liusy.dataapplatform.base.exception.WebException;
import com.liusy.datapp.web.BaseAction;
import com.liusy.datapp.web.datastandard.form.StandardCodesetForm;
import com.liusy.datapp.service.datastandard.StandardCategoryService;
import com.liusy.datapp.service.datastandard.StandardCodesetService;
import com.liusy.datapp.service.pool.BussCodeSetPool;
import com.liusy.datapp.model.datastandard.StandardCategory;
import com.liusy.datapp.model.datastandard.StandardCode;
import com.liusy.datapp.model.datastandard.StandardCodeset;

public class StandardCodesetAction extends BaseAction {

	private static final String	GETTREE		= "GETTREE";

	@Override
	public ActionForward doExecute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// TODO Auto-generated method stub
		String action = request.getParameter("action");
		if (action == null) action = LIST;
		if (log.isDebugEnabled()) log.debug("action:" + action);
		ActionForward forward = null;
		StandardCodesetForm standardCodesetForm = (StandardCodesetForm)form;
		try {
			if (ADD.equalsIgnoreCase(action)) forward = addCodeset(mapping, standardCodesetForm, request, response);
			else if (SAVE.equalsIgnoreCase(action)) forward = saveCodeset(mapping, standardCodesetForm, request, response);
			else if (EDIT.equalsIgnoreCase(action)) forward = editCodeset(mapping, standardCodesetForm, request, response);
			else if (UPDATE.equalsIgnoreCase(action)) forward = updateCodeset(mapping, standardCodesetForm, request, response);
			else if (DELETE.equalsIgnoreCase(action)) forward = deleteCodeset(mapping, standardCodesetForm, request, response);
			else if (GETTREE.equalsIgnoreCase(action)) forward = getTreeNode(mapping, standardCodesetForm, request, response);
			else if (LIST.equalsIgnoreCase(action)) forward = searchStandardCodesetGrid(mapping, standardCodesetForm, request, response);
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

	private ActionForward addCodeset(ActionMapping mapping, StandardCodesetForm standardCodesetForm,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		if (log.isDebugEnabled()) log.debug("Entering 'addCodeset' method");

		initForm(standardCodesetForm, ADD);
		String categoryId = request.getParameter("categoryid");
		standardCodesetForm.getRecord().put(StandardCodeset.PROP_CATEGORY_ID, categoryId);
		return mapping.findForward(ADD);
	}

	private ActionForward saveCodeset(ActionMapping mapping, StandardCodesetForm standardCodesetForm,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		if (log.isDebugEnabled()) log.debug("Entering 'saveCodeset' method");

		ActionForward forward = null;
		try {
			StandardCodeset standardCodeset = new StandardCodeset();
			ConvertUtil.mapToObject(standardCodeset, standardCodesetForm.getRecord());
			Session currentUser = (Session)request.getSession().getAttribute(Const.SESSION);
			if (currentUser != null)
				standardCodeset.setCreateBy(currentUser.getUserId());
			standardCodeset.setCreateDate(new Date());
			StandardCodesetService standardCodesetService = (StandardCodesetService) getBean("standardCodesetService");
			standardCodesetService.createStandardCodeset(standardCodeset);
			NotifyPool(standardCodeset);
			forward = returnForward(mapping, request, RETURN_NORMAL);
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("operstatus", "0");
			addMessage(standardCodesetForm, "保存操作失败");
			initForm(standardCodesetForm, ADD);
			forward = mapping.findForward(ADD);
		}
		return forward;
	}

	private ActionForward editCodeset(ActionMapping mapping, StandardCodesetForm standardCodesetForm,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		if (log.isDebugEnabled()) log.debug("Entering 'editCodeset' method");

		/*String id = request.getParameter("record(id)");

		System.out.println(id+"================"+id);
		
		StandardCodesetService standardCodesetService = (StandardCodesetService) getBean("standardCodesetService");
		StandardCodeset standardCodeset = (StandardCodeset) standardCodesetService.findStandardCodeset(new Integer(id));
		ConvertUtil.objectToMap(standardCodesetForm.getRecord(), standardCodeset);
		
		StandardCategoryService standardCategoryService = (StandardCategoryService) getBean("standardCategoryService");
		String categoryName = (String)standardCategoryService.findStandardCategory(standardCodeset.getCategoryId()).getName();
		System.out.println("categoryName1==="+categoryName);
		if(categoryName==null){
			categoryName=request.getParameter("categoryName");
			System.out.println("categoryName2==="+categoryName);
		}
		standardCodesetForm.setCategoryName(categoryName);*/
		initForm(standardCodesetForm, EDIT);
		return mapping.findForward(EDIT);
	}

	private ActionForward updateCodeset(ActionMapping mapping, StandardCodesetForm standardCodesetForm,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		if (log.isDebugEnabled()) log.debug("Entering 'updateCodeset' method");

		try {
			StandardCodesetService standardCodesetService = (StandardCodesetService) getBean("standardCodesetService");
			StandardCodeset standardCodeset = standardCodesetService.findStandardCodeset(new Integer(standardCodesetForm.getRecord().get("id")));
			if (standardCodeset != null) {
				ConvertUtil.mapToObject(standardCodeset, standardCodesetForm.getRecord());
				Session currentUser = (Session)request.getSession().getAttribute(Const.SESSION);
				if (currentUser != null)
					standardCodeset.setModifyBy(currentUser.getUserId());
				standardCodeset.setModifyDate(new Date());
				standardCodesetService.updateStandardCodeset(standardCodeset);
				NotifyPool(standardCodeset);
				request.setAttribute("msg", "修改成功！");
				return mapping.findForward(UPDATE);
			}
			else {
				request.setAttribute("msg", "数据集记录不存在");
				return mapping.findForward(ERROR);
			}
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("operstatus", "0");
			addMessage(standardCodesetForm, "编辑操作失败");
			initForm(standardCodesetForm, EDIT);
			
			return mapping.findForward(EDIT);
		}
	}

	private ActionForward deleteCodeset(ActionMapping mapping, StandardCodesetForm standardCodesetForm,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		if (log.isDebugEnabled()) log.debug("Entering 'deleteCodeset' method");

		try {
			StandardCodesetService standardCodesetService = (StandardCodesetService) getBean("standardCodesetService");
			String del_ids = request.getParameter("ids");
			if (del_ids != null && del_ids.trim().length() > 0) {
				standardCodesetService.removeStandardCodesetsAndCodes(parseId(del_ids.split(";")));
			}
		} catch (Exception e) {
		}
		return searchStandardCodesetGrid(mapping, standardCodesetForm, request, response);
	}

	private ActionForward getTreeNode(ActionMapping mapping, StandardCodesetForm standardCodesetForm,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		if (log.isDebugEnabled()) log.debug("Entering 'getTreeNode' mathod");

		String id = request.getParameter("id");
		try {
			getCategoryTree(request, response, id);
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}

	private ActionForward searchStandardCodesetGrid(ActionMapping mapping, StandardCodesetForm standardCodesetForm,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		if (log.isDebugEnabled()) log.debug("Entering 'searchStandardCodesetGrid' method");

		try {
			StandardCodesetService standardCodesetService = (StandardCodesetService) getBean("standardCodesetService");
			PageQuery pageQuery = standardCodesetForm.getQuery();
			pageQuery.setSelectParamId("GET_STANDARDCODESET_PAGE");
			String queryString = getQueryString(pageQuery);
			pageQuery.getParameters().put("queryString", queryString);
			standardCodesetService.queryStandardCodeset(pageQuery);
			setPage(standardCodesetForm.getQuery());
		}
		catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return mapping.findForward(LIST);
	}

	private String getQueryString(PageQuery pageQuery) {
		StringBuffer buffer = new StringBuffer();
		Map fields = pageQuery.getParameters();
		String str = (String) fields.get("treeid");
		if ("root".equalsIgnoreCase(str))
			buffer.append(" and t." + StandardCodeset.COL_CATEGORY_ID + " is null");
		else if (str != null && !"".equalsIgnoreCase(str))
			buffer.append(" and t." + StandardCodeset.COL_CATEGORY_ID + " = " + str);
		else
			buffer.append(" and t." + StandardCodeset.COL_CATEGORY_ID + " is null");
		return buffer.toString();
	}

	private void getCategoryTree(HttpServletRequest request, HttpServletResponse response, String id)
			throws Exception{
		if (id == null || "".equalsIgnoreCase(id)) {
			StandardCodesetService standardCodesetService = (StandardCodesetService)getBean("standardCodesetService");
			
			PageQuery query = new PageQuery();
			StringBuffer str = new StringBuffer();
			str.append(" and a." + StandardCategory.COL_PARENT_ID + " = 0");

			Element root = DocumentHelper.createElement("tree")
							.addAttribute("id", "0");
			Element topNode = root.addElement("item")
							.addAttribute("text", "广东省代码集分类")
							.addAttribute("id", "root")
							.addAttribute("open", "1")
							.addAttribute("child", "1");
			topNode.addElement("userdata")
						.addAttribute("name", "name")
						.addText("广东省代码集分类");
			query.setSelectParamId("GET_STANDARDCATEGORY_PAGE");
			query.getParameters().put("queryString", str.toString());
			query.setOrder(StandardCategory.COL_SQUENCE_NO);
			query.setOrderDirection(PageQuery.ASC);
			query.setPageSize("0");
			List<Map<String, String>> categorys = standardCodesetService.getCategorysForTree(query).getRecordSet();
			if (categorys==null) {
				categorys = new ArrayList<Map<String, String>>();
			}

			for (Map<String, String> category : categorys) {
				topNode.addElement("item")
						.addAttribute("text", category.get(StandardCategory.PROP_NAME.toUpperCase()))
						.addAttribute("id", category.get(StandardCategory.PROP_ID.toUpperCase()))
//						.addAttribute("child", "1")
						.addAttribute("im0", "folderClosed.gif")
					.addElement("userdata")
						.addAttribute("name", "name")
						.addText(category.get(StandardCategory.PROP_NAME.toUpperCase()));
			}
			root.addAttribute("hasChild", Integer.toString(categorys.size()));

			response.setContentType("text/xml;charset=UTF-8");
			System.out.println(root.asXML());
			response.getWriter().write(root.asXML());
			response.getWriter().close();
		}
	}

	private void initForm(StandardCodesetForm standardCodesetForm, String action) {
		StandardCodesetService standardCodesetService = (StandardCodesetService) getBean("standardCodesetService");
		
		if("EDIT".equalsIgnoreCase(action)){
			String id = standardCodesetForm.getRecord().get("id");
			StandardCodeset standardCodeset = (StandardCodeset) standardCodesetService.findStandardCodeset(new Integer(id));
			try {
				ConvertUtil.objectToMap(standardCodesetForm.getRecord(), standardCodeset);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			StandardCategoryService standardCategoryService = (StandardCategoryService) getBean("standardCategoryService");
			String categoryName = (String)standardCategoryService.findStandardCategory(standardCodeset.getCategoryId()).getName();
			standardCodesetForm.setCategoryName(categoryName);
		}
		
		PageQuery query = new PageQuery();
		query.setSelectParamId("GET_STANDARDCATEGORY_PAGE");
		query.getParameters().put("queryString", " and a."+StandardCategory.COL_PARENT_ID+" = 0");
		query.setPageSize("0");

		List<Map<String, String>> categorys = standardCodesetService.getCategorysForTree(query).getRecordSet();
		if (categorys==null) {
			categorys = new ArrayList<Map<String, String>>();
		}
		setCode(standardCodesetForm, "categorys", categorys, StandardCategory.PROP_NAME.toUpperCase(), StandardCategory.PROP_ID.toUpperCase());
	}

	private java.io.Serializable[] parseId(String[] ids) throws Exception{
		if (ids == null || ids.length == 0) { throw new Exception("非法进入编辑页！"); }
		java.io.Serializable id[] = null;
		try {
			id = new Integer[ids.length];
			for (int i = 0; i < ids.length; i++) {
				id[i] = new Integer(ids[i]);
			}
		}
		catch (Exception e) {
			id = ids;
		}
		return id;
	}
	private void NotifyPool(StandardCodeset codeset){
		BussCodeSetPool bussCodeSetPool=(BussCodeSetPool)getBean("bussCodeSetPool");
		bussCodeSetPool.clearCodeSet(codeset.getId().toString(),codeset.getCodesetNo());
	}
}