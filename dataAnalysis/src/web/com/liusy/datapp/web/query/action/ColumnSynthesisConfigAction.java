package com.liusy.datapp.web.query.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.liusy.dataapplatform.base.exception.ServiceException;
import com.liusy.dataapplatform.base.util.ConvertUtil;
import com.liusy.dataapplatform.base.util.PageQuery;
import com.liusy.datapp.model.query.QueryColumnCfg;
import com.liusy.datapp.model.query.QueryParamCfg;
import com.liusy.datapp.model.resource.ResourceColumn;
import com.liusy.datapp.service.datastandard.StandardIndicatorService;
import com.liusy.datapp.service.pool.TableConfigPool;
import com.liusy.datapp.service.query.QueryColumnCfgService;
import com.liusy.datapp.service.query.QueryParamCfgService;
import com.liusy.datapp.service.resource.ResourceColumnService;
import com.liusy.datapp.util.ColumnCfgPageVO;
import com.liusy.datapp.web.BaseAction;
import com.liusy.datapp.web.query.form.ColumnSynthesisConfigForm;

public class ColumnSynthesisConfigAction extends BaseAction{

	@Override
	public ActionForward doExecute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		String action = request.getParameter("action");
		if (action == null) action = LIST;
		if (log.isDebugEnabled()) log.debug("action:" + action);
		ActionForward forward=null;
		ColumnSynthesisConfigForm theForm = (ColumnSynthesisConfigForm) form;
		try {
			if ("MODIFY".equalsIgnoreCase(action)) forward = modifyColumnQueryCfg(mapping, theForm, request,response); // 打开编辑页面
			else if (UPDATE.equalsIgnoreCase(action)) forward = updateColumnQueryCfg(mapping, theForm, request,response);// 保存修改数据
			else if("INITCFG".equalsIgnoreCase(action)) forward= initQueryCfg(mapping, theForm, request, response);
			else if("SAVEINITCFG".equalsIgnoreCase(action)) forward=saveInitQueryCfg(mapping, theForm, request, response);
		}catch (Exception e) {
			request.setAttribute("err", e);
			forward = mapping.findForward(ERROR);
		}
		return forward;
	}
	private ActionForward modifyColumnQueryCfg(ActionMapping mapping, ColumnSynthesisConfigForm theForm, HttpServletRequest request,HttpServletResponse response) throws Exception {
		if (log.isDebugEnabled()) log.debug("Entering 'modifyColumnQueryCfg' method");
		ActionForward forward = null;
		try{
			ResourceColumnService resourceColumnService=(ResourceColumnService)getBean("resourceColumnService");
			QueryColumnCfgService queryColumnCfgService=(QueryColumnCfgService)getBean("queryColumnCfgService");
			QueryParamCfgService queryParamCfgService=(QueryParamCfgService)getBean("queryParamCfgService");
			ResourceColumn column=resourceColumnService.findResourceColumn(Integer.valueOf(theForm.getColumnId()));
			request.setAttribute("columnName", column.getCnName());
			QueryColumnCfg columncfg=queryColumnCfgService.findColumnCfgByColumnId(theForm.getColumnId());
			QueryParamCfg paramcfg=queryParamCfgService.findQueryParamByColumnId(theForm.getColumnId());
			if(columncfg!=null){
				//如果没有排序号则设置为默认排序号			
				if (columncfg.getSeqNo()==null)
				{
					Integer maxOrder = resourceColumnService.getMaxOrderFromCFG(theForm.getTableId());
					columncfg.setSeqNo(maxOrder+1); 
				}
				ConvertUtil.objectToMap(theForm.getRecord(),columncfg);
				theForm.getRecord().put("colcfgId", columncfg.getId().toString());
			}
			if(paramcfg!=null){
				ConvertUtil.objectToMap(theForm.getRecord(),paramcfg);
				theForm.getRecord().put("paramcfgId", paramcfg.getId().toString());
			}
			initForm(theForm, "MODIFY",request);
			forward=mapping.findForward("MODIFY");
		}catch (ServiceException e) {
			request.setAttribute("errMsg", e.getMessage());
			return mapping.findForward(ERROR);
		}
		catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("err", e);
			return mapping.findForward(ERROR);
		}
		return forward;
	}
	private ActionForward initQueryCfg(ActionMapping mapping, ColumnSynthesisConfigForm theForm, HttpServletRequest request,HttpServletResponse response) throws Exception {
		if (log.isDebugEnabled()) log.debug("Entering 'modifyColumnQueryCfg' method");
		String tableId=theForm.getTableId();
		theForm.getRecord().put("tableId", tableId);
		QueryColumnCfgService queryColumnCfgService=(QueryColumnCfgService)getBean("queryColumnCfgService");
		QueryParamCfgService queryParamCfgService=(QueryParamCfgService)getBean("queryParamCfgService");
		ResourceColumnService resourceColumnService=(ResourceColumnService)getBean("resourceColumnService");
		List<ResourceColumn> list=resourceColumnService.findColumnByTableIdSort(tableId);
		List<QueryColumnCfg> listQcc = queryColumnCfgService.findColumnConfigByOrder(tableId);
		List<QueryParamCfg> listQpc = queryParamCfgService.findParamByOrder(tableId);
		Map<Integer, QueryColumnCfg> mapQc = null;
		Map<Integer, QueryParamCfg> mapQp = null;
		if(null != listQcc && listQcc.size() != 0){
			mapQc = new HashMap<Integer, QueryColumnCfg>();
			for(QueryColumnCfg qc:listQcc){
				mapQc.put(qc.getColumnId(), qc);
			}
		}
		if(null != listQpc && listQpc.size() != 0){
			mapQp = new HashMap<Integer, QueryParamCfg>();
			for(QueryParamCfg qp:listQpc){				
				mapQp.put(qp.getColumnId(), qp);
			}
		}
		List<ColumnCfgPageVO> paramters=theForm.getParameters();
		paramters.clear();
		int i=1;
		for(ResourceColumn column:list){
			ColumnCfgPageVO vo=new ColumnCfgPageVO();
			vo.setColumnId(String.valueOf(column.getId()));
			vo.setName(column.getName());
			vo.setCname(column.getCnName());
			if(null != column.getSquenceNo()){
				vo.setSeqNo(column.getSquenceNo().toString());
			}else{
				vo.setSeqNo(i + "");
			}
			if(null != mapQc){
				if(null != mapQc.get(column.getId())){
					QueryColumnCfg qcc = mapQc.get(column.getId());
					vo.setIsShown(qcc.getIsShown());
					vo.setIsSubject(qcc.getIsSubject());
					vo.setIsSortable(qcc.getIsSortable());
				}
			}
			if(null != mapQp){
				if(null != mapQp.get(column.getId())){
					QueryParamCfg qpc = mapQp.get(column.getId());
					vo.setIsFilter(qpc.getIsFilter());
					vo.setFilterOperator(qpc.getFilterOperator());
					vo.setFuzzyQuery(qpc.getFuzzyQuery());
					vo.setHomonymQuery(qpc.getHomonymQuery());
					vo.setBatchQuery(qpc.getBatchQuery());
					vo.setStQuery(qpc.getStQuery());
				}
			}
			paramters.add(vo);
			i++;
		}
		return mapping.findForward("INITCFG");
	}
	private ActionForward saveInitQueryCfg(ActionMapping mapping, ColumnSynthesisConfigForm theForm, HttpServletRequest request,HttpServletResponse response) throws Exception {
		if (log.isDebugEnabled()) log.debug("Entering 'modifyColumnQueryCfg' method");
		List<ColumnCfgPageVO> paramters=theForm.getParameters();
		ActionForward forward = null;
		try{
			QueryColumnCfgService queryColumnCfgService=(QueryColumnCfgService)getBean("queryColumnCfgService");
			QueryParamCfgService queryParamCfgService=(QueryParamCfgService)getBean("queryParamCfgService");
			List<QueryColumnCfg> columncfglist=new ArrayList<QueryColumnCfg>();
			List<QueryParamCfg> paramcfglist=new ArrayList<QueryParamCfg>();
			List<String> columnIdList=new ArrayList<String>();
			Integer tableId=Integer.valueOf(theForm.getTableId());
			for(ColumnCfgPageVO vo:paramters){	
					QueryColumnCfg columncfg=new QueryColumnCfg();
					columnIdList.add(vo.getColumnId());
					BeanUtils.copyProperties(columncfg, vo);
					columncfg.setTableId(tableId);
					columncfglist.add(columncfg);
					//ConvertUtil.convertToModel(columncfg, vo);
					QueryParamCfg paramcfg=new QueryParamCfg();
					BeanUtils.copyProperties(paramcfg, vo);
					paramcfg.setTableId(tableId);
					paramcfglist.add(paramcfg);
					//ConvertUtil.convertToModel(paramcfg, vo);
			}
			queryColumnCfgService.initQueryConfig(columncfglist, paramcfglist, tableId);
			NotifyPool(theForm.getTableId());
			
			forward = returnForward(mapping, request, RETURN_NORMAL);
		}catch (Exception e) {
			e.printStackTrace();
			addMessage(theForm, "保存操作失败!");
			initForm(theForm, EDIT,request);
			forward = mapping.findForward(EDIT);
		}
		return forward;
	}
	private ActionForward updateColumnQueryCfg(ActionMapping mapping, ColumnSynthesisConfigForm theForm, HttpServletRequest request,HttpServletResponse response) throws Exception {
		if (log.isDebugEnabled()) log.debug("Entering 'modifyColumnQueryCfg' method");
		ActionForward forward = null;
		initForm(theForm, UPDATE, request);
		try{
			QueryColumnCfgService queryColumnCfgService=(QueryColumnCfgService)getBean("queryColumnCfgService");
			QueryParamCfgService queryParamCfgService=(QueryParamCfgService)getBean("queryParamCfgService");
			QueryColumnCfg columncfg=null;
			QueryParamCfg paramcfg=null;
			if(theForm.getRecord().get("colcfgId")!=null && !"".equals(theForm.getRecord().get("colcfgId").toString())){
				//修改
				columncfg=queryColumnCfgService.findQueryColumnCfg(Integer.valueOf(theForm.getRecord().get("colcfgId").toString()));
				ConvertUtil.mapToObject(columncfg, theForm.getRecord(),false);
				columncfg.setId(Integer.valueOf(theForm.getRecord().get("colcfgId").toString()));
			}else{
				columncfg=new QueryColumnCfg();
				ConvertUtil.mapToObject(columncfg, theForm.getRecord());
			}
			if(theForm.getRecord().get("paramcfgId")!=null && !"".equals(theForm.getRecord().get("paramcfgId").toString())){
				paramcfg=queryParamCfgService.findQueryParamCfg(Integer.valueOf(theForm.getRecord().get("paramcfgId").toString()));
				ConvertUtil.mapToObject(paramcfg, theForm.getRecord(),false);
				paramcfg.setId(Integer.valueOf(theForm.getRecord().get("paramcfgId").toString()));
			}else{
				paramcfg=new QueryParamCfg();
				ConvertUtil.mapToObject(paramcfg, theForm.getRecord());
			}
			queryColumnCfgService.updateQueryConfig(columncfg, paramcfg);
			NotifyPool(theForm.getTableId());
			forward = returnForward(mapping, request, RETURN_NORMAL);
		}catch (Exception e) {
			e.printStackTrace();
			addMessage(theForm, "保存操作失败!");
			initForm(theForm, EDIT,request);
			forward = mapping.findForward(EDIT);
		}
		return forward;
	}
	private void initForm(ColumnSynthesisConfigForm theForm, String action,HttpServletRequest request) throws Exception {
		String[] keyArray={"isShown","isSubject","isSortable","isFilter","batchQuery","stQuery","homonymQuery","fuzzyQuery"};
		if(action.equalsIgnoreCase(UPDATE)){
			for(int i=0;i<keyArray.length;i++)
			if(theForm.getRecord().get(keyArray[i])==null)
				theForm.getRecord().put(keyArray[i], "0");
		}else if(action.equalsIgnoreCase("MODIFY")){
			setCode(theForm, "FILTER_OPER");
			StandardIndicatorService standardIndicatorService=(StandardIndicatorService)getBean("standardIndicatorService");
			PageQuery pageQuery=new PageQuery();
			pageQuery.setPageSize("0");
			pageQuery.setSelectParamId("GET_ALL_INDICATOR");
			List<Map<String,String>> list=standardIndicatorService.queryStandardIndicator(pageQuery).getRecordSet();
			request.setAttribute("indList", list);
			theForm.getRecord().put("columnId", theForm.getColumnId());
			theForm.getRecord().put("tableId", theForm.getTableId());
			if (theForm.getRecord().get("seqNo")==null)
			{
				ResourceColumnService resourceColumnService=(ResourceColumnService)getBean("resourceColumnService");
				Integer maxOrder = resourceColumnService.getMaxOrderFromCFG(theForm.getTableId());
				theForm.getRecord().put("seqNo", maxOrder+1);
			}			
		}
	}
	private void NotifyPool(String tableId){
		TableConfigPool tableConfigPool=(TableConfigPool)getBean("tableConfigPool");
		tableConfigPool.clearColumnConfigPool(tableId);
		tableConfigPool.clearParamConfigPool(tableId);
	}

}
