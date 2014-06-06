/**
 * SysResourceViewCol.java Create on 2009-10-16 ����11:00:26 
 * @author ������
 * @date 2009-9-18
 * ����
 *       
 */
package com.liusy.datapp.web.system.resource.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.liusy.core.util.Const;
import com.liusy.core.util.Session;
import com.liusy.dataapplatform.base.exception.WebException;
import com.liusy.dataapplatform.base.util.ConvertUtil;
import com.liusy.dataapplatform.base.util.PageQuery;
import com.liusy.datapp.model.datastandard.StandardCode;
import com.liusy.datapp.model.datastandard.StandardCodeset;
import com.liusy.datapp.model.datastandard.StandardDataMeta;
import com.liusy.datapp.model.datastandard.StandardIndicator;
import com.liusy.datapp.model.resource.ResourceColumn;
import com.liusy.datapp.service.resource.ResourceColumnService;
import com.liusy.datapp.web.BaseAction;
import com.liusy.datapp.web.system.resource.form.SysResourceColumnForm;

public class SysResourceViewColAaction extends BaseAction{

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
				forward = searchResourceColumn(mapping, resourceColumnForm, request, response); // �򿪲�ѯ�б�ҳ��
			else if (ADD.equalsIgnoreCase(action))
				forward = addResourceColumn(mapping, resourceColumnForm, request, response);// ������ҳ��
			else if (SAVE.equalsIgnoreCase(action))
				forward = saveResourceColumn(mapping, resourceColumnForm, request, response);// ������������
			else if (EDIT.equalsIgnoreCase(action))
				forward = editResourceColumn(mapping, resourceColumnForm, request, response);// ���޸�ҳ��
			else if (UPDATE.equalsIgnoreCase(action))
				forward = updateResourceColumn(mapping, resourceColumnForm, request, response);// �����޸�����
			else if (DELETE.equalsIgnoreCase(action))
				forward = deleteResourceColumn(mapping, resourceColumnForm, request, response);// ɾ��
			else {
				request.setAttribute("err", new WebException("�Ҳ�����action������" + action));
				forward = mapping.findForward(ERROR);// �Ҳ������ʵ�action
			}

		} catch (Exception e) {// ����ϵͳ����

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
		return mapping.findForward(EDIT);
	}

	public ActionForward saveResourceColumn(ActionMapping mapping, SysResourceColumnForm resourceColumnForm, HttpServletRequest request, HttpServletResponse response) throws Exception {
		if (log.isDebugEnabled())
			log.debug("Entering 'saveResource' method");
		ActionForward forward = null;

		try {
			ResourceColumn resourceColumn = new ResourceColumn();
			ConvertUtil.mapToObject(resourceColumn, resourceColumnForm.getRecord());
			ResourceColumnService resourceColumnService = (ResourceColumnService) getBean("resourceColumnService");

			resourceColumnService.createResourceColumn(resourceColumn);
			forward = returnForward(mapping, request, RETURN_NORMAL);
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("operstatus", "0");
			resourceColumnForm.setErrorMessage("�½�����ʧ��");
			forward = mapping.findForward(ADD);
		}
		return forward;
	}

	public ActionForward updateResourceColumn(ActionMapping mapping, SysResourceColumnForm resourceColumnForm, HttpServletRequest request, HttpServletResponse response) throws Exception {
		if (log.isDebugEnabled())
			log.debug("Entering 'updateResource' method");

		ActionForward forward = null;

		try {
			ResourceColumn resourceColumn = new ResourceColumn();
			ConvertUtil.mapToObject(resourceColumn, resourceColumnForm.getRecord());
			ResourceColumnService resourceColumnService = (ResourceColumnService) getBean("resourceColumnService");
			resourceColumnService.updateResourceColumn(resourceColumn);
			// forward = returnForward(mapping, request, RETURN_NORMAL);
			request.setAttribute("msg", "�޸ĳɹ���");
			forward = returnForward(mapping, request, RETURN_NORMAL);
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("operstatus", "0");
			request.setAttribute("msg", "�༭����ʧ��");
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
			String tableid = (String)request.getParameter("tableid");
			if (tableid==null||"".equals(tableid)) {
				tableid = resourceColumnForm.getQuery().getParameters().get("tableid");
			}
			if (tableid==null||"".equals(tableid))
			{
				tableid = "-1";
			}
			//���ݱ�id���û������ĵ��е�code�жϸ��û��Ƿ���Ȩ�鿴
			if (resourceColumnService.haveRight(getCityCode(request), Integer.valueOf(tableid))) {
				PageQuery pageQuery = resourceColumnForm.getQuery();
				pageQuery.setSelectParamId("GET_SYSRESOURCECOLUMN_PAGE");
				//ΪpageQuery������Ӳ�ѯ����			
				if (tableid!=null&&!"".equalsIgnoreCase(tableid)) {
					pageQuery.getParameters().put("tableid", tableid);
				}
				String queryString = getQueryString(pageQuery);
				pageQuery.getParameters().put("queryString", queryString);
				// ִ�в�ѯ����������浽pagequery�У��������һ��list<map>������map��ÿ���ֶ�����key��ֵ��value��������
				resourceColumnService.queryResourceColumn(pageQuery);

				//��������������ת��Ϊ������ʾ
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
			}
			
		} catch (Exception e) {

			e.printStackTrace();
			throw e;
		}
		return mapping.findForward(LIST);
	}

	private java.io.Serializable[] parseId(String[] ids) throws Exception {
		if (ids == null || ids.length == 0) {
			throw new Exception("�Ƿ�����༭ҳ��");
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
		
		//ȡ�ö�Ӧָ����뼯
		List<StandardIndicator> indicators = resourceColumnService.getIndicators(null);
		if (indicators==null) {
			indicators = new ArrayList<StandardIndicator>();
		}
		
		//ȡ�ö�Ӧ����Դ���뼯
		List<StandardDataMeta> datameta = resourceColumnService.getDataMetaCode(null);		
		//ȡ�ö�Ӧ������뼯\
		List<StandardCodeset> codeset = resourceColumnService.getCodesetCode(null);
		
		//���ô��뼯
		setCode(resourceForm, "indicator",indicators,StandardIndicator.PROP_CN_NAME,StandardIndicator.PROP_ID);
		setCode(resourceForm, "datameta", datameta, StandardDataMeta.PROP_CN_NAME, StandardDataMeta.PROP_ID);
		setCode(resourceForm, "codeset", codeset, StandardCode.PROP_NAME, StandardCode.PROP_ID);
		if ("ADD".equalsIgnoreCase(action)) {
			String tableid = request.getParameter("tableid");
			resourceForm.getRecord().put("tableId", tableid);
		}
	}

	private String getQueryString(PageQuery pageQuery) {
		StringBuffer buffer = new StringBuffer();
		Map fields = pageQuery.getParameters();
		String str = (String) fields.get("tableid");
		if (str != null && !"".equalsIgnoreCase(str)) {
			buffer.append(" and t.table_id" + " = " + str);
		}
		return buffer.toString();
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
}
