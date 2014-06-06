package com.liusy.datapp.web.query.action;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.liusy.core.util.CollectionMapConvert;
import com.liusy.dataapplatform.base.exception.WebException;
import com.liusy.datapp.model.datastandard.StandardCategory;
import com.liusy.datapp.model.query.QuerySubject;
import com.liusy.datapp.model.resource.ResourceTable;
import com.liusy.datapp.service.datastandard.StandardCategoryService;
import com.liusy.datapp.service.query.QuerySubjectService;
import com.liusy.datapp.service.resource.ResourceTableService;
import com.liusy.datapp.util.DhtmlTreeParam;
import com.liusy.datapp.util.DhtmlTreeUtil;
import com.liusy.datapp.web.BaseAction;
import com.liusy.datapp.web.query.form.SynthesisQueryForm;

public class SynthesisQueryAction extends BaseAction{

	protected static final String	GETTREE		= "GETTREE";
	@Override
	public ActionForward doExecute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		String action = request.getParameter("action");
		if (action == null) action = LIST;
		if (log.isDebugEnabled()) log.debug("action:" + action);
		ActionForward forward;
		SynthesisQueryForm theForm=(SynthesisQueryForm) form;
		try {
			if (LIST.equalsIgnoreCase(action)) forward = search(mapping, theForm, request, response); // 打开查询列表页面
			else if (GETTREE.equalsIgnoreCase(action)) forward = getTreeNode(mapping, theForm, request, response); // 打开增加页面
//			else if (ADD.equalsIgnoreCase(action)) forward = addOrg(mapping, theForm, request, response); // 打开增加页面
//			else if (SAVE.equalsIgnoreCase(action)) forward = saveOrg(mapping, theForm, request, response); // 保存增加数据
//			else if (EDIT.equalsIgnoreCase(action)) forward = editOrg(mapping, theForm, request, response); // 打开修改页面
//			else if (UPDATE.equalsIgnoreCase(action)) forward = updateOrg(mapping, theForm, request, response);// 保存修改数据
//			else if (DELETE.equalsIgnoreCase(action)) forward = deleteOrg(mapping, theForm, request, response);// 删除
			else {
				request.setAttribute("err", new WebException("找不到该action方法：" + action));
				forward = mapping.findForward(ERROR);// 找不到合适的action
			}
		}
		catch (Exception e) {// 其他系统出错
			request.setAttribute("err", e);
			forward = mapping.findForward(ERROR);
		}
		return forward;
	}
	private ActionForward search(ActionMapping mapping, SynthesisQueryForm sysOrgForm, HttpServletRequest request, HttpServletResponse response) throws Exception {
		return mapping.findForward(LIST);
	}
	private ActionForward getTreeNode(ActionMapping mapping, SynthesisQueryForm theForm, HttpServletRequest request, HttpServletResponse response) throws Exception {
		if (log.isDebugEnabled()) log.debug("Entering 'getTree' method");
		String id=theForm.getId();

		response.setContentType("text/xml;charset=UTF-8");
		response.setHeader("Cache_Control", "no-cache");
		try {
			if (id.indexOf("comm") == 0) {	
				response.getWriter().write(getCommonRange());
			}
			else if (id.indexOf("buss") == 0) {
				String retstr="";
				if(id.equals("buss"))
					retstr=getBussinessRange(null);
				else
					retstr=getBussinessRange(id.substring(4,id.length()));
				response.getWriter().write(retstr);
			}
			else {
				response.getWriter().write(getTopNodes());
			}
			response.getWriter().close();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	private String getTopNodes() {
		StringBuffer sb = new StringBuffer("");
		sb.append("<?xml version='1.0' encoding='UTF-8' ?>");
		sb.append("<tree id=\"0\">");
		
		sb.append("<item id=\"comm" + "\" text=\"公共分类\" child=\"1\"></item>");
		sb.append("<item id=\"buss0" + "\" text=\"行业分类\" child=\"1\"></item>");
			
		sb.append("</tree>");
		return sb.toString();
	}
	private String getCommonRange() throws WebException{
		String retStr="";
		try{
			QuerySubjectService querySubjectService=(QuerySubjectService) getBean("querySubjectService");
			List<QuerySubject> commList=querySubjectService.findCommSubject();
			CollectionMapConvert<QuerySubject> convert=new CollectionMapConvert<QuerySubject>();
			List<Map<String,String>> listmap=convert.getListMapFromListVO(commList);
			DhtmlTreeParam param=new DhtmlTreeParam("id","subName","comm","/icon/code_child.gif");
			retStr=DhtmlTreeUtil.genXmlTreeByList(listmap, param, "comm",true);
		
		}catch (Exception e) {
			e.printStackTrace();
			throw new WebException(e);
		}
		return retStr;
	}
	private String getBussinessRange(String parentId) throws WebException{
		String retStr="";
		try{
			StandardCategoryService standardCategoryService=(StandardCategoryService)getBean("standardCategoryService");
			List<StandardCategory> list=standardCategoryService.findCategoryByParentId(parentId);
			if(list!=null && !list.isEmpty()){
				//有下级分类
				CollectionMapConvert<StandardCategory> convert=new CollectionMapConvert<StandardCategory>();
				List<Map<String,String>> listmap=convert.getListMapFromListVO(list);
				DhtmlTreeParam param=new DhtmlTreeParam("id","name","buss","/icon/code_child.gif");
				if(parentId==null)
					parentId="";
				retStr=DhtmlTreeUtil.genXmlTreeByList(listmap, param, "buss"+parentId,true);	
			}else{
				//没有下级分类，显示所有表
				ResourceTableService resourceTableService=(ResourceTableService)getBean("resourceTableService");
				List<ResourceTable> tabList=resourceTableService.findTableByCatageoryId(parentId);
				CollectionMapConvert<ResourceTable> convert1=new CollectionMapConvert<ResourceTable>();
				List<Map<String,String>> tabMapList=convert1.getListMapFromListVO(tabList);
				DhtmlTreeParam param1=new DhtmlTreeParam("id","cnName","tab","/icon/code_child.gif");
				retStr=DhtmlTreeUtil.genXmlTreeByList(tabMapList, param1, "buss"+parentId,false);
			}
		}catch (Exception e) {
			e.printStackTrace();
			throw new WebException(e);
		}
		return retStr;
	}
	

}
