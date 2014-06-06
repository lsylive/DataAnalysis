package com.liusy.datapp.web.dynamicquery.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import com.liusy.core.util.CollectionMapConvert;
import com.liusy.core.util.Const;
import com.liusy.core.util.Session;
import com.liusy.dataapplatform.base.exception.WebException;
import com.liusy.dataapplatform.base.util.PageQuery;
import com.liusy.datapp.model.resource.ResourceColumn;
import com.liusy.datapp.model.resource.ResourceTable;
import com.liusy.datapp.service.dynamicquery.SynthesisColumnGenService;
import com.liusy.datapp.service.pool.BussCodeSetPool;
import com.liusy.datapp.service.pool.TableConfigPool;
import com.liusy.datapp.service.query.SynthesisQueryService;
import com.liusy.datapp.service.resource.ResourceColumnService;
import com.liusy.datapp.service.resource.ResourceTableService;
import com.liusy.datapp.util.poolobj.ColumnConfigPoolObj;
import com.liusy.datapp.util.poolobj.ColumnPoolObj;
import com.liusy.datapp.web.BaseAction;
import com.liusy.datapp.web.dynamicquery.form.QueryDetailForm;

public class QueryDetailAction extends BaseAction{
	@Override
	public ActionForward doExecute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		String action = request.getParameter("action1");
		if (action == null) action = LIST;
		if (log.isDebugEnabled()) log.debug("action:" + action);
		ActionForward forward=null;
		QueryDetailForm theForm=(QueryDetailForm)form;
		try {
			if (LIST.equalsIgnoreCase(action)) forward = queryDetail(mapping, theForm, request, response); // 打开查询列表页面
			else if("LISTBATCH".equalsIgnoreCase(action)) forward=batchDetail(mapping, theForm, request, response);//打开概览页面
			else
			{
				request.setAttribute("err", new WebException("找不到该action方法：" + action));
				forward = mapping.findForward(ERROR);// 找不到合适的action
			}
		}
		catch (Exception e) {// 其他系统出错
			request.setAttribute("err", e);
			request.setAttribute("errMsg", e.getMessage());
			forward = mapping.findForward(ERROR);
		}
		return forward;
	}
	public ActionForward queryDetail(ActionMapping mapping, QueryDetailForm theForm, HttpServletRequest request, HttpServletResponse response) throws Exception{
		if (log.isDebugEnabled()) log.debug("Entering 'searchSubject' method");
		ActionForward forward=null;
		
		try{
			SynthesisQueryService synthesisQueryService=(SynthesisQueryService)getBean("synthesisQueryService");
			TableConfigPool tableConfigPool=(TableConfigPool)getBean("tableConfigPool");
			ResourceTableService resourceTableService=(ResourceTableService)getBean("resourceTableService");
			BussCodeSetPool bussCodeSetPool=(BussCodeSetPool)getBean("bussCodeSetPool");
			List<ColumnPoolObj> columnpoolList=tableConfigPool.getTableColumnPool(theForm.getTabId());
			List<ColumnConfigPoolObj> columncfgpoolList=tableConfigPool.getColumnConfigPool(theForm.getTabId());
			StringBuffer buffer=new StringBuffer();
			CollectionMapConvert<ColumnPoolObj> convert=new CollectionMapConvert<ColumnPoolObj>();
			Map<String,ColumnPoolObj> map=convert.convertListToMap(columnpoolList, "id");
			StringBuffer dnamebuffer=new StringBuffer();
			String uid=request.getParameter("uid");
			
			List<ColumnPoolObj> displayColList=new ArrayList<ColumnPoolObj>();
			for(ColumnConfigPoolObj pool:columncfgpoolList){
				ColumnPoolObj colpool=map.get(pool.getColumnId());
				if(pool.getIsShown()!=null && "1".equals(pool.getIsShown()))
				{
					buffer.append(colpool.getName()+",");	
					dnamebuffer.append(colpool.getCnName()+",");
					displayColList.add(colpool);
				}
				
			}
			//生成sql语句
			ResourceTable table=resourceTableService.findResourceTable(Integer.valueOf(theForm.getTabId()));
			String colstr=buffer.toString().substring(0,buffer.length()-1);
			String cnname=dnamebuffer.substring(0,dnamebuffer.length()-1);
			ResourceColumnService resourceColumnService=(ResourceColumnService)getBean("resourceColumnService");
			ResourceColumn pkobj=resourceColumnService.findResoucrePKColumn(Integer.valueOf(theForm.getTabId()));
			String paramSql=pkobj.getName()+"=";
			if(pkobj.getDataType().equals(Const.META_TYPE_NUMERIC))
				paramSql+=uid;
			else
				paramSql+="'"+uid+"'";
			String sql="select "+colstr+" from "+table.getName()+" where "+paramSql;
			PageQuery pageQuery=theForm.getQuery();
			List<Map<String,String>> list=synthesisQueryService.queryBySql(sql, pageQuery).getRecordSet();
			//过滤代码集
			bussCodeSetPool.filterBussCodeSet(list, columnpoolList);
			//过滤安全等级字段
			Session currentUser = (Session)request.getSession().getAttribute(Const.SESSION);
			SynthesisColumnGenService synthesisColumnGenService=(SynthesisColumnGenService)getBean("synthesisColumnGenService");
			synthesisColumnGenService.filterSecurityLevel(theForm.getTabId(),list, columnpoolList,pkobj, currentUser);
			theForm.getQuery().setRecordSet(list);
			String CONTEXT_PATH = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";
			String url = CONTEXT_PATH+"pics/"+table.getName()+"/";
			request.setAttribute("htmlcode", synthesisColumnGenService.genenrateResultHtmlCode(displayColList, list.get(0),request));
			request.setAttribute("tableName", table.getName());
			forward=mapping.findForward("QUERYDETAIL");
			
		}catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("err", e);
			forward = mapping.findForward(ERROR);
		}
		return forward;
	}
	public ActionForward batchDetail(ActionMapping mapping, QueryDetailForm theForm, HttpServletRequest request, HttpServletResponse response) throws Exception{
		if (log.isDebugEnabled()) log.debug("Entering 'searchSubject' method");
		ActionForward forward=null;
		
		try{
			Element ele=DocumentHelper.createElement("QUERYRESULT");
			
			SynthesisQueryService synthesisQueryService=(SynthesisQueryService)getBean("synthesisQueryService");
			TableConfigPool tableConfigPool=(TableConfigPool)getBean("tableConfigPool");
			ResourceTableService resourceTableService=(ResourceTableService)getBean("resourceTableService");
			BussCodeSetPool bussCodeSetPool=(BussCodeSetPool)getBean("bussCodeSetPool");
			
			if(theForm.getIdArr()==null || "".equals(theForm.getIdArr().trim()))
				return mapping.findForward("LISTBATCH");
			String[] idArr=theForm.getIdArr().split(",");
			List<Map<String,String>> retList=new ArrayList<Map<String,String>>();
			for(int i=0;i<idArr.length;i++){
				String[] sptstr=idArr[i].split("-");
				String tabId=sptstr[0];
				String pkid=sptstr[1];
				List<ColumnPoolObj> columnpoolList=tableConfigPool.getTableColumnPool(tabId);
				List<ColumnConfigPoolObj> columncfgpoolList=tableConfigPool.getColumnConfigPool(tabId);
				StringBuffer buffer=new StringBuffer();
				CollectionMapConvert<ColumnPoolObj> convert=new CollectionMapConvert<ColumnPoolObj>();
				Map<String,ColumnPoolObj> map=convert.convertListToMap(columnpoolList, "id");
				StringBuffer dnamebuffer=new StringBuffer();
			
				List<ColumnPoolObj> displayColList=new ArrayList<ColumnPoolObj>();
				for(ColumnConfigPoolObj pool:columncfgpoolList){
					ColumnPoolObj colpool=map.get(pool.getColumnId());
					if(pool.getIsShown()!=null && "1".equals(pool.getIsShown()))
					{
						buffer.append(colpool.getName()+",");	
						dnamebuffer.append(colpool.getCnName()+",");
						displayColList.add(colpool);
					}
				
				}
				//生成sql语句
				Map<String,String> tableMap=tableConfigPool.getTableMap(tabId);
				String colstr=buffer.toString().substring(0,buffer.length()-1);
				String cnname=dnamebuffer.substring(0,dnamebuffer.length()-1);
				ResourceColumnService resourceColumnService=(ResourceColumnService)getBean("resourceColumnService");
				ResourceColumn pkobj=resourceColumnService.findResoucrePKColumn(Integer.valueOf(tabId));
				String paramSql=pkobj.getName()+"=";
				if(pkobj.getDataType().equals(Const.META_TYPE_NUMERIC))
					paramSql+=pkid;
				else
					paramSql+="'"+pkid+"'";
				String sql="select "+colstr+" from "+tableMap.get("name")+" where "+paramSql;
				PageQuery pageQuery=theForm.getQuery();
				List<Map<String,String>> list=synthesisQueryService.queryBySql(sql, pageQuery).getRecordSet();
				//过滤代码集
				bussCodeSetPool.filterBussCodeSet(list, columnpoolList);
				//过滤安全等级字段
				Session currentUser = (Session)request.getSession().getAttribute(Const.SESSION);
				SynthesisColumnGenService synthesisColumnGenService=(SynthesisColumnGenService)getBean("synthesisColumnGenService");
				synthesisColumnGenService.filterSecurityLevel(tabId,list, columnpoolList,pkobj, currentUser);
				//theForm.getQuery().setRecordSet(list);
				Map<String,String> showmap=new HashMap<String, String>();
				showmap.put("ids", idArr[i]);
				showmap.put("pid", pkid);
				showmap.put("tabName", tableMap.get("cName"));
				String CONTEXT_PATH = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";
				String url = CONTEXT_PATH+"pics/"+tableMap.get("name")+"/";
				showmap.put("htmlcode", synthesisColumnGenService.genenrateResultHtmlCode(displayColList, list.get(0),request));
				request.setAttribute("tableName", tableMap.get("name"));
				//request.setAttribute("htmlcode", SynthesisColumnGen.genenrateResultHtmlCode(displayColList, list.get(0)));
				retList.add(showmap);
			}	
			request.setAttribute("resultList", retList);
		}catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("err", e);
			forward = mapping.findForward(ERROR);
		}
		return mapping.findForward("LISTBATCH");
	}

}
