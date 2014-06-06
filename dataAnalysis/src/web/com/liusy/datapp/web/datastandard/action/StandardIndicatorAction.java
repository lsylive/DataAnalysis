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
import com.liusy.datapp.service.datastandard.StandardCategoryService;
import com.liusy.datapp.service.datastandard.StandardIndicatorService;
import com.liusy.datapp.service.pool.QueryMetaIndPool;
import com.liusy.datapp.web.BaseAction;
import com.liusy.datapp.web.datastandard.form.StandardIndicatorForm;

public class StandardIndicatorAction extends BaseAction {

	private static final String GETTREE 	= "GETTREE";

	@Override
	public ActionForward doExecute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// TODO Auto-generated method stub
		String action = request.getParameter("action");
		if (action == null || "".equalsIgnoreCase(action))
			action = LIST;
		if (log.isDebugEnabled()) log.debug("action:" + action);
		ActionForward forward = null;
		StandardIndicatorForm standardIndicatorForm = (StandardIndicatorForm)form;
		try {
			if (LIST.equalsIgnoreCase(action)) forward = searchStandardIndicator(mapping, standardIndicatorForm, request, response);
			else if (ADD.equalsIgnoreCase(action)) forward = addStandardIndicator(mapping, standardIndicatorForm, request, response);
			else if (SAVE.equalsIgnoreCase(action)) forward = saveStandardIndicator(mapping, standardIndicatorForm, request, response);
			else if (EDIT.equalsIgnoreCase(action)) forward = editStandardIndicator(mapping, standardIndicatorForm, request, response);
			else if (UPDATE.equalsIgnoreCase(action)) forward = updateStandardIndicator(mapping, standardIndicatorForm, request, response);
			else if (DELETE.equalsIgnoreCase(action)) forward = deleteStandardIndicator(mapping, standardIndicatorForm, request, response);
			else if (GETTREE.equalsIgnoreCase(action)) forward = getTreeNode(mapping, standardIndicatorForm, request, response);
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

	private ActionForward searchStandardIndicator(ActionMapping mapping, StandardIndicatorForm standardIndicatorForm,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		if (log.isDebugEnabled()) log.debug("Entering 'searchStandardIndicator' method");

		try {
			StandardIndicatorService standardIndicatorService = (StandardIndicatorService) getBean("standardIndicatorService");
			PageQuery pageQuery = standardIndicatorForm.getQuery();
			
			String indicatorId = request.getParameter("indicatorId");
			if (indicatorId!=null&&!"".equals(indicatorId))
			{
				pageQuery.getParameters().put("categoryid", indicatorId);
			}
			pageQuery.setSelectParamId("GET_STANDARDINDICATOR_PAGE");
			String queryString = getQueryString(pageQuery);
			pageQuery.getParameters().put("queryString", queryString);
			standardIndicatorService.queryStandardIndicator(pageQuery);

			initForm(standardIndicatorForm, LIST);
			List<Map<String,String>> list = pageQuery.getRecordSet();
			for (int i = 0; i < list.size(); i++) {
				Map<String,String> record = list.get(i);
				String categoryId = (String)record.get(StandardIndicator.PROP_CATEGORY_ID.toUpperCase());
				if (categoryId == null) record.put(StandardIndicator.PROP_CATEGORY_ID.toUpperCase(), "");
				else record.put(StandardIndicator.PROP_CATEGORY_ID.toUpperCase(), findCodeName(standardIndicatorForm, "categorys", record.get(StandardIndicator.PROP_CATEGORY_ID.toUpperCase())));
			}

			setPage(standardIndicatorForm.getQuery());
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

	private ActionForward addStandardIndicator(ActionMapping mapping, StandardIndicatorForm standardIndicatorForm,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		if (log.isDebugEnabled()) log.debug("Entering 'addStandardIndicator' method");

		initForm(standardIndicatorForm, ADD);
		String categoryId = request.getParameter("categoryid");
		standardIndicatorForm.getRecord().put(StandardIndicator.PROP_CATEGORY_ID, categoryId);
		return mapping.findForward(ADD);
	}

	private ActionForward saveStandardIndicator(ActionMapping mapping, StandardIndicatorForm standardIndicatorForm,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		if (log.isDebugEnabled()) log.debug("Entering 'saveStandardIndicator' method");

		ActionForward forward = null;
		try {
			StandardIndicator standardIndicator = new StandardIndicator();
			ConvertUtil.mapToObject(standardIndicator, standardIndicatorForm.getRecord());
			Session currentUser = (Session)request.getSession().getAttribute(Const.SESSION);
			if (currentUser != null)
				standardIndicator.setCreateBy(currentUser.getUserId());
			standardIndicator.setCreateDate(new Date());
			StandardIndicatorService standardIndicatorService = (StandardIndicatorService) getBean("standardIndicatorService");
			standardIndicatorService.createStandardIndicator(standardIndicator);
			NotifyPool(standardIndicator);
			forward = returnForward(mapping, request, RETURN_NORMAL);
		}catch (ServiceException e) {
			addMessage(standardIndicatorForm, e.getMessage());
			initForm(standardIndicatorForm, ADD);
			forward = mapping.findForward(ADD);
		}
		catch(Exception e) {
			e.printStackTrace();
			addMessage(standardIndicatorForm, "保存操作失败");
			initForm(standardIndicatorForm, ADD);
			forward = mapping.findForward(ADD);
		}
		return forward;
	}

	private ActionForward editStandardIndicator(ActionMapping mapping, StandardIndicatorForm standardIndicatorForm,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		if (log.isDebugEnabled()) log.debug("Entering 'editStandardIndicator' method");

		String id = request.getParameter("id");
		/*StandardIndicatorService standardIndicatorService = (StandardIndicatorService) getBean("standardIndicatorService");
		StandardIndicator standardIndicator = (StandardIndicator) standardIndicatorService.findStandardIndicator(new Integer(id));
		ConvertUtil.objectToMap(standardIndicatorForm.getRecord(), standardIndicator);
		StandardCategoryService standardCategoryService = (StandardCategoryService) getBean("standardCategoryService");
		String categoryName = (String)standardCategoryService.findStandardCategory(standardIndicator.getCategoryId()).getName();
		standardIndicatorForm.setCategoryName(categoryName);*/
		initForm(standardIndicatorForm, EDIT,id);
		initForm(standardIndicatorForm, EDIT);
		return mapping.findForward(EDIT);
	}

	private ActionForward updateStandardIndicator(ActionMapping mapping, StandardIndicatorForm standardIndicatorForm,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		if (log.isDebugEnabled()) log.debug("Entering 'updateStandardIndicator' method");

		try {
			StandardIndicatorService standardIndicatorService = (StandardIndicatorService) getBean("standardIndicatorService");
			StandardIndicator standardIndicator = standardIndicatorService.findStandardIndicator(new Integer(standardIndicatorForm.getRecord().get("id")));
			if (standardIndicator != null) {
				ConvertUtil.mapToObject(standardIndicator, standardIndicatorForm.getRecord());
				Session currentUser = (Session)request.getSession().getAttribute(Const.SESSION);
				if (currentUser != null)
					standardIndicator.setModifyBy(currentUser.getUserId());
				standardIndicator.setModifyDate(new Date());
				standardIndicatorService.updateStandardIndicator(standardIndicator);
				NotifyPool(standardIndicator);
				request.setAttribute("msg", "修改成功！");
				return returnForward(mapping, request, RETURN_NORMAL);
			}
			else {
				request.setAttribute("msg", "数据指标记录不存在");
				return mapping.findForward(ERROR);
			}
		} catch (ServiceException e) {
			addMessage(standardIndicatorForm, e.getMessage());
			String id=standardIndicatorForm.getRecord().get("id");
			initForm(standardIndicatorForm, EDIT,id);
			initForm(standardIndicatorForm, EDIT);
			return mapping.findForward(EDIT);
		}catch (Exception e) {
			e.printStackTrace();
			addMessage(standardIndicatorForm, "编辑操作失败");
			String id=standardIndicatorForm.getRecord().get("id");
			initForm(standardIndicatorForm, EDIT,id);
			initForm(standardIndicatorForm, EDIT);
			return mapping.findForward(EDIT);
		}
	}

	private ActionForward deleteStandardIndicator(ActionMapping mapping, StandardIndicatorForm standardIndicatorForm,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		if (log.isDebugEnabled()) log.debug("Entering 'deleteStandardIndicator' method");

		try {
			StandardIndicatorService standardIndicatorService = (StandardIndicatorService) getBean("standardIndicatorService");
			String del_ids = request.getParameter("ids");
			if (del_ids != null && del_ids.trim().length() > 0) {
				standardIndicatorService.removeStandardIndicators(parseId(del_ids.split(";")));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return searchStandardIndicator(mapping, standardIndicatorForm, request, response);
	}

	private ActionForward getTreeNode(ActionMapping mapping, StandardIndicatorForm standardIndicatorForm,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		if (log.isDebugEnabled()) log.debug("Entering 'getTreeNode' mathod");

		String id = request.getParameter("id");
		if (id == null || "".equalsIgnoreCase(id)) {
			try {
				StandardIndicatorService standardIndicatorService = (StandardIndicatorService)getBean("standardIndicatorService");
	
				PageQuery query = new PageQuery();
				StringBuffer buffer = new StringBuffer();
				buffer.append(" and a." + StandardCategory.COL_PARENT_ID + " = 0");
				query.setSelectParamId("GET_STANDARDCATEGORY_PAGE");
				query.getParameters().put("queryString", buffer.toString());
				query.setOrder(StandardCategory.COL_SQUENCE_NO);
				query.setOrderDirection(PageQuery.ASC);
				query.setPageSize("0");
				List<Map<String, String>> categorys = standardIndicatorService.getCategorysForTree(query).getRecordSet();
				if (categorys==null) {
					categorys = new ArrayList<Map<String, String>>();
				}
	
				Element root = DocumentHelper.createElement("tree")
								.addAttribute("id", "0");
				Element topNode = root.addElement("item")
								.addAttribute("text", "广东省数据指标分类")
								.addAttribute("id", "root")
								.addAttribute("open", "1")
								.addAttribute("child", "1");
				topNode.addElement("userdata")
							.addAttribute("name", "name")
							.addText("广东省数据指标分类");
				for (Map<String, String> category : categorys) {
					topNode.addElement("item")
							.addAttribute("text", category.get(StandardCategory.COL_NAME.toUpperCase()))
							.addAttribute("id", category.get(StandardCategory.COL_ID.toUpperCase()))
							.addAttribute("im0", "folderClosed.gif")
						.addElement("userdata")
							.addAttribute("name", "name")
							.addText(category.get(StandardCategory.COL_NAME.toUpperCase()));
				}
	
				response.setContentType("text/xml;charset=UTF-8");
				response.getWriter().write(root.asXML());
				response.getWriter().close();
			}
			catch(Exception e){
				e.printStackTrace();
			}
		}
		return null;
	}

	private String getQueryString(PageQuery pageQuery) {
		StringBuffer buffer = new StringBuffer();
		Map fields = pageQuery.getParameters();
		String str = (String) fields.get("categoryid");
		if ("root".equalsIgnoreCase(str))
			buffer.append("");
		else if (str != null && !"".equalsIgnoreCase(str))
			buffer.append(" and ((t." + StandardIndicator.COL_CATEGORY_ID + " = " + str + ") or Exists(Select * from t_standard_category where PARENT_ID="+str+" and ID=t." + StandardIndicator.COL_CATEGORY_ID+"))");
		else
			buffer.append(" and t." + StandardIndicator.COL_CATEGORY_ID + " is null");
		return buffer.toString();
	}
	
	private void initForm(StandardIndicatorForm standardIndicatorForm, String action,String id){
		StandardIndicatorService standardIndicatorService = (StandardIndicatorService) getBean("standardIndicatorService");
		StandardIndicator standardIndicator = (StandardIndicator) standardIndicatorService.findStandardIndicator(new Integer(id));
		try {
			ConvertUtil.objectToMap(standardIndicatorForm.getRecord(), standardIndicator);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		StandardCategoryService standardCategoryService = (StandardCategoryService) getBean("standardCategoryService");
		String categoryName = (String)standardCategoryService.findStandardCategory(standardIndicator.getCategoryId()).getName();
		standardIndicatorForm.setCategoryName(categoryName);
	}

	private void initForm(StandardIndicatorForm standardIndicatorForm, String action) {
		StandardIndicatorService standardIndicatorService = (StandardIndicatorService) getBean("standardIndicatorService");

		PageQuery query = new PageQuery();
		query.setSelectParamId("GET_STANDARDCATEGORY_PAGE");
		query.getParameters().put("queryString", " and "+StandardCategory.COL_PARENT_ID+" = 0");
		query.setPageSize("0");
		List<Map<String, String>> categorys = standardIndicatorService.getCategorysForTree(query).getRecordSet();
		if (categorys==null) {
			categorys = new ArrayList<Map<String, String>>();
		}
		setCode(standardIndicatorForm, "categorys", categorys, StandardCategory.PROP_NAME.toUpperCase(), StandardCategory.PROP_ID.toUpperCase());
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
	private void NotifyPool(StandardIndicator standardIndicator){
		QueryMetaIndPool queryMetaIndPool=(QueryMetaIndPool)getBean("queryMetaIndPool");
		queryMetaIndPool.clearIndicatorPool(standardIndicator.getId().toString());
	}
}
