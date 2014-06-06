package com.liusy.datapp.web.query.action;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.liusy.core.util.CollectionMapConvert;
import com.liusy.core.util.Const;
import com.liusy.core.util.Session;
import com.liusy.dataapplatform.base.exception.ServiceException;
import com.liusy.dataapplatform.base.exception.WebException;
import com.liusy.dataapplatform.base.util.ConvertUtil;
import com.liusy.datapp.model.datastandard.StandardCategory;
import com.liusy.datapp.model.query.QuerySubject;
import com.liusy.datapp.service.datastandard.StandardCategoryService;
import com.liusy.datapp.service.pool.SubjectConfigPool;
import com.liusy.datapp.service.query.QuerySubjectService;
import com.liusy.datapp.util.DhtmlTreeParam;
import com.liusy.datapp.util.DhtmlTreeUtil;
import com.liusy.datapp.web.BaseAction;
import com.liusy.datapp.web.query.form.SubjectClassfiyForm;

public class SubjectClassifyAction extends BaseAction{
	protected static final String	GETTREE		= "GETTREE";
	@Override
	public ActionForward doExecute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		String action = request.getParameter("action");
		if (action == null) action = LIST;
		if (log.isDebugEnabled()) log.debug("action:" + action);
		ActionForward forward;
		SubjectClassfiyForm theForm=(SubjectClassfiyForm) form;
		try {
			if (LIST.equalsIgnoreCase(action)) forward = search(mapping, theForm, request, response); // 打开查询列表页面
			else if (GETTREE.equalsIgnoreCase(action)) forward = getTreeNode(mapping, theForm, request, response); // 打开增加页面
			else if ("MODIFY".equalsIgnoreCase(action)) forward = modifyClassify(mapping, theForm, request, response); // 打开增加页面
//			else if (SAVE.equalsIgnoreCase(action)) forward = saveOrg(mapping, theForm, request, response); // 保存增加数据
//			else if (EDIT.equalsIgnoreCase(action)) forward = editOrg(mapping, theForm, request, response); // 打开修改页面
			else if (UPDATE.equalsIgnoreCase(action)) forward = updateClassify(mapping, theForm, request, response);// 保存修改数据
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
	private ActionForward modifyClassify(ActionMapping mapping, SubjectClassfiyForm theForm, HttpServletRequest request, HttpServletResponse response) throws Exception {
		if (log.isDebugEnabled()) log.debug("Entering 'modifyClassify' method");
		ActionForward forward=null;
		try {
			QuerySubjectService querySubjectService=(QuerySubjectService)getBean("querySubjectService");
			QuerySubject subject=querySubjectService.findSubjectByCatagoryId(Integer.valueOf(theForm.getCatagoryId()));
			if(subject!=null ){
				ConvertUtil.objectToMap(theForm.getRecord(), subject);
				initForm(theForm, EDIT,request);
			}else
			{
				subject=createNewSubject(theForm.getCatagoryId());
				ConvertUtil.objectToMap(theForm.getRecord(), subject);
				initForm(theForm, EDIT,request);
			}
			return mapping.findForward("MODIFY");
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
	
	private ActionForward updateClassify(ActionMapping mapping, SubjectClassfiyForm theForm, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		try {
			QuerySubjectService querySubjectService=(QuerySubjectService)getBean("querySubjectService");
			if(theForm.getRecord().get("id")!=null){
				QuerySubject subject=querySubjectService.findQuerySubject(new Integer(theForm.getRecord().get("id")));
				ConvertUtil.mapToObject(subject, theForm.getRecord(),false);
				Session session=(Session) request.getSession().getAttribute(Const.SESSION);
				subject.setModifyby(session.getAccountName());
				subject.setModifydate(new Date(System.currentTimeMillis()));
				querySubjectService.updateQuerySubject(subject);
				NotifyPool(subject);
			}else{
				QuerySubject subject=querySubjectService.findQuerySubject(new Integer(theForm.getRecord().get("id")));
				ConvertUtil.mapToObject(subject, theForm.getRecord(),false);
				querySubjectService.createQuerySubject(subject);
				NotifyPool(subject);
			}
			request.setAttribute("msg", "保存分类主题基本信息成功");
			//request.setAttribute("returnUrl", "classifySubject.do?action=modify&record(id)="+theForm.getRecord().get("id")+"&catagoryId="+theForm.getCatagoryId());
			forward = returnForward(mapping, request, RETURN_NORMAL);
		}catch (Exception e) {
			e.printStackTrace();
			addMessage(theForm, "保存操作失败!");
			initForm(theForm,"MODIFY",request);
			forward = mapping.findForward("MODIFY");
		}
		return forward;
	}
	private void initForm(SubjectClassfiyForm theForm, String action,HttpServletRequest request) {
		
		
	}
	private void NotifyPool(QuerySubject subject){
		SubjectConfigPool subjectConfigPool=(SubjectConfigPool)getBean("subjectConfigPool");
		subjectConfigPool.clearSubjectConfigPool(subject.getId().toString());
	}
	private ActionForward search(ActionMapping mapping, SubjectClassfiyForm sysOrgForm, HttpServletRequest request, HttpServletResponse response) throws Exception {
		return mapping.findForward(LIST);
	}
	private ActionForward getTreeNode(ActionMapping mapping, SubjectClassfiyForm theForm, HttpServletRequest request, HttpServletResponse response) throws Exception {
		if (log.isDebugEnabled()) log.debug("Entering 'getTree' method");
		String id=theForm.getId();

		response.setContentType("text/xml;charset=UTF-8");
		response.setHeader("Cache_Control", "no-cache");
		try {
			if (id.indexOf("buss") == 0) {
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
	private QuerySubject createNewSubject(String catagoryId) throws Exception{
		StandardCategoryService standardCategoryService=(StandardCategoryService)getBean("standardCategoryService");
		StandardCategory catagory=standardCategoryService.findStandardCategory(Integer.valueOf(catagoryId));
		QuerySubject subject=new QuerySubject();
		subject.setScId(Integer.valueOf(catagoryId));
		subject.setSubType(QuerySubject.SUBTYPE_SEPERATE);
		subject.setSubName(catagory.getName());
		subject.setCreatedate(new Date(System.currentTimeMillis()));
		subject.setModifydate(new Date(System.currentTimeMillis()));
		QuerySubjectService querySubjectService=(QuerySubjectService)getBean("querySubjectService");
		querySubjectService.createQuerySubject(subject);
		return subject;
	}
	private String getTopNodes() {
		StringBuffer sb = new StringBuffer("");
		sb.append("<?xml version='1.0' encoding='UTF-8' ?>");
		sb.append("<tree id=\"0\">");
		
		//sb.append("<item id=\"comm" + "\" text=\"公共分类\" child=\"1\"></item>");
		sb.append("<item id=\"buss0" + "\" text=\"行业分类\" im0=\"/icon/code_all.gif\" im1=\"/icon/code_all.gif\" im2=\"/icon/code_all.gif\"  child=\"1\"><userdata name=\"name\">行业分类</userdata></item>");
			
		sb.append("</tree>");
		return sb.toString();
	}
	
	private String getBussinessRange(String parentId) throws WebException{
		String retStr="";
		try{
			if(parentId==null)
				parentId="0";
			StandardCategoryService standardCategoryService=(StandardCategoryService)getBean("standardCategoryService");
			List<StandardCategory> list=standardCategoryService.findCategoryByParentId(parentId);
			if(list!=null && !list.isEmpty()){
				//有下级分类
				CollectionMapConvert<StandardCategory> convert=new CollectionMapConvert<StandardCategory>();
				List<Map<String,String>> listmap=convert.getListMapFromListVO(list);
				String[] nameArr={"name"};
				DhtmlTreeParam param=new DhtmlTreeParam("id","name","buss",nameArr,nameArr,"/icon/code_child.gif");
				if(parentId==null)
					parentId="";
				
				retStr=DhtmlTreeUtil.genXmlTreeByList(listmap, param, "buss"+parentId,true);	
			}else
				retStr=DhtmlTreeUtil.genBlankXmlTree("buss"+parentId);
//			else{
//				//没有下级分类，显示所有表
//				ResourceTableService resourceTableService=(ResourceTableService)getBean("resourceTableService");
//				List<ResourceTable> tabList=resourceTableService.findTableByCatageoryId(parentId);
//				CollectionMapConvert<ResourceTable> convert1=new CollectionMapConvert<ResourceTable>();
//				List<Map<String,String>> tabMapList=convert1.getListMapFromListVO(tabList);
//				DhtmlTreeParam param1=new DhtmlTreeParam("id","cnName","tab",null);
//				retStr=DhtmlTreeUtil.genXmlTreeByList(tabMapList, param1, "buss"+parentId,false);
//			}
		}catch (Exception e) {
			throw new WebException(e);
		}
		return retStr;
	}
	
	

}
