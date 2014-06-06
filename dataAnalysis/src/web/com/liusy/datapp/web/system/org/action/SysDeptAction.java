package com.liusy.datapp.web.system.org.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.liusy.dataapplatform.base.exception.ServiceException;
import com.liusy.dataapplatform.base.exception.WebException;
import com.liusy.dataapplatform.base.util.ConvertUtil;
import com.liusy.datapp.model.system.org.SysDept;
import com.liusy.datapp.service.system.org.SysDeptService;
import com.liusy.datapp.service.system.org.SysOrgService;
import com.liusy.datapp.web.BaseAction;
import com.liusy.datapp.web.system.org.form.SysDeptForm;

public class SysDeptAction extends BaseAction {
   @Override
   public ActionForward doExecute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
      String action = request.getParameter("action");
      if (action == null) action = VIEW;
      if (log.isDebugEnabled()) log.debug("action:" + action);
      ActionForward forward;
      SysDeptForm sysDeptForm = (SysDeptForm) form;

      try {
         if (ADD.equalsIgnoreCase(action)) forward = addDept(mapping, sysDeptForm, request, response); // 打开增加页面
         else if (SAVE.equalsIgnoreCase(action)) forward = saveDept(mapping, sysDeptForm, request, response); // 保存增加数据
         else if (EDIT.equalsIgnoreCase(action)) forward = editDept(mapping, sysDeptForm, request, response); // 打开修改页面
         else if (UPDATE.equalsIgnoreCase(action)) forward = updateDept(mapping, sysDeptForm, request, response);// 保存修改数据
         else if (DELETE.equalsIgnoreCase(action)) forward = deleteDept(mapping, sysDeptForm, request, response);// 删除
         else if (VIEW.equalsIgnoreCase(action)) forward = deleteDept(mapping, sysDeptForm, request, response);// 查看
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

   public ActionForward addDept(ActionMapping mapping, SysDeptForm sysDeptForm, HttpServletRequest request, HttpServletResponse response) throws Exception {
      if (log.isDebugEnabled()) log.debug("Entering 'addDept' method");

      try {
         String orgId = request.getParameter("orgId");
         String pid = request.getParameter("pid");
         sysDeptForm.getRecord().put("orgId", orgId);
         sysDeptForm.getRecord().put("upDeptId", (pid == null) ? "" : pid);
         initForm(sysDeptForm, ADD);
         return mapping.findForward(ADD);
      }
      catch (Exception e) {
         e.printStackTrace();
         request.setAttribute("err", e);
         return mapping.findForward(ERROR);
      }
   }

   public ActionForward saveDept(ActionMapping mapping, SysDeptForm sysDeptForm, HttpServletRequest request, HttpServletResponse response) throws Exception {
      if (log.isDebugEnabled()) log.debug("Entering 'saveDept' method");
      ActionForward forward = null;

      try {
         SysDept sysDept = new SysDept();
         ConvertUtil.mapToObject(sysDept, sysDeptForm.getRecord());
         SysOrgService sysOrgService = (SysOrgService) getBean("sysOrgService");
         sysOrgService.createSysDept(sysDept);
         forward = returnForward(mapping, request, RETURN_NORMAL);
      }
      catch (ServiceException e) {
         addMessage(sysDeptForm, e.getMessage());
         initForm(sysDeptForm, ADD);
         return mapping.findForward(ADD);
      }
      catch (Exception e) {
         e.printStackTrace();
         addMessage(sysDeptForm, "保存操作失败!");
         initForm(sysDeptForm, ADD);
         forward = mapping.findForward(ADD);
      }
      return forward;
   }

   public ActionForward editDept(ActionMapping mapping, SysDeptForm sysDeptForm, HttpServletRequest request, HttpServletResponse response) throws Exception {
      if (log.isDebugEnabled()) log.debug("Entering 'editDept' method");
      String id = request.getParameter("id");

      try {
         SysDeptService sysDeptService = (SysDeptService) getBean("sysDeptService");
         SysDept sysDept = (SysDept) sysDeptService.findSysDept(new Integer(id));
         ConvertUtil.objectToMap(sysDeptForm.getRecord(), sysDept);
         initForm(sysDeptForm, EDIT);
         return mapping.findForward(EDIT);
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

   public ActionForward updateDept(ActionMapping mapping, SysDeptForm sysDeptForm, HttpServletRequest request, HttpServletResponse response) throws Exception {
      if (log.isDebugEnabled()) log.debug("Entering 'updateDept' method");

      ActionForward forward = null;

      try {
         SysOrgService sysOrgService = (SysOrgService) getBean("sysOrgService");
         SysDept sysDept = sysOrgService.findSysDept(new Integer(sysDeptForm.getRecord().get("id")));
         ConvertUtil.mapToObject(sysDept, sysDeptForm.getRecord(), false);
         sysOrgService.updateSysDept(sysDept);
         forward = returnForward(mapping, request, RETURN_NORMAL);
      }
      catch (ServiceException e) {
         addMessage(sysDeptForm, e.getMessage());
         initForm(sysDeptForm, EDIT);
         return mapping.findForward(EDIT);
      }
      catch (Exception e) {
         addMessage(sysDeptForm, "保存操作失败!");
         initForm(sysDeptForm, EDIT);
         forward = mapping.findForward(EDIT);
      }
      return forward;
   }

   public ActionForward deleteDept(ActionMapping mapping, SysDeptForm sysDeptForm, HttpServletRequest request, HttpServletResponse response) throws Exception {
      if (log.isDebugEnabled()) log.debug("Entering 'deleteDept' method");
      response.setContentType("text/plain;charset=UTF-8");
      response.setHeader("Cache_Control", "no-cache");
      StringBuffer sb = new StringBuffer();
      try {
         SysOrgService sysOrgService = (SysOrgService) getBean("sysOrgService");
         String id = request.getParameter("id");
         if (id != null) {
            sysOrgService.removeSysDept(new Integer(id));
         }
         sb.append(RET_NORAML);
      }
      catch (Exception e) {
         sb.append(JsonErrorMessage(OPER_FAILED, "部门删除失败！"));
      }
      response.getWriter().write(sb.toString());
      response.getWriter().close();
      return null;
   }

   private void initForm(SysDeptForm sysDeptForm, String action) {
   }
}
