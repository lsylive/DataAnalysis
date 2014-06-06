package com.liusy.datapp.web.dynamicquery.action;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.liusy.core.util.CollectionMapConvert;
import com.liusy.core.util.Const;
import com.liusy.dataapplatform.base.exception.WebException;
import com.liusy.dataapplatform.base.util.BaseSqlGen;
import com.liusy.dataapplatform.base.util.PageQuery;
import com.liusy.dataapplatform.base.util.QueryParam;
import com.liusy.datapp.model.datastandard.StandardCategory;
import com.liusy.datapp.model.query.QuerySubject;
import com.liusy.datapp.model.resource.ResourceColumn;
import com.liusy.datapp.model.resource.ResourceTable;
import com.liusy.datapp.service.datastandard.StandardCategoryService;
import com.liusy.datapp.service.dynamicquery.SynthesisColumnGenService;
import com.liusy.datapp.service.pool.BussCodeSetPool;
import com.liusy.datapp.service.pool.CatagoryPool;
import com.liusy.datapp.service.pool.QueryMetaIndPool;
import com.liusy.datapp.service.pool.SubjectConfigPool;
import com.liusy.datapp.service.pool.TableConfigPool;
import com.liusy.datapp.service.query.QuerySubjectService;
import com.liusy.datapp.service.query.SynthesisQueryService;
import com.liusy.datapp.service.resource.ResourceColumnService;
import com.liusy.datapp.service.resource.ResourceTableService;
import com.liusy.datapp.util.DhtmlTreeParam;
import com.liusy.datapp.util.DhtmlTreeUtil;
import com.liusy.datapp.util.poolobj.ColumnConfigPoolObj;
import com.liusy.datapp.util.poolobj.ColumnPoolObj;
import com.liusy.datapp.util.poolobj.SubjectColumnConfigPoolObj;
import com.liusy.datapp.util.poolobj.SubjectConfigPoolObj;
import com.liusy.datapp.web.BaseAction;
import com.liusy.datapp.web.dynamicquery.form.ClassifySubjectQueryForm;
import com.liusy.web.tag.grid.Column;

public class ClassifySubjectQueryAction extends BaseAction{

	@Override
	public ActionForward doExecute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		String action = request.getParameter("action");
		if (action == null) action = LIST;
		if (log.isDebugEnabled()) log.debug("action:" + action);
		ActionForward forward=null;
		ClassifySubjectQueryForm theForm=(ClassifySubjectQueryForm)form;
		try {
			if (LIST.equalsIgnoreCase(action)) forward = searchSubject(mapping, theForm, request, response); // 打开查询列表页面
			else if("OVERVIEW".equalsIgnoreCase(action)) forward=queryOverView(mapping, theForm, request, response);
			else if("SHOWQUERY".equalsIgnoreCase(action)) forward=showParamSubject(mapping, theForm, request, response);
			else if("QUERYCOUNT".equalsIgnoreCase(action)) forward=queryTableCount(mapping, theForm, request, response);
			else if("SHOWLIST".equalsIgnoreCase(action)) forward=queryRecordList(mapping, theForm, request, response);
			else if("SHOWDETAIL".equalsIgnoreCase(action)) forward=queryDetail(mapping, theForm, request, response);
			else if("SHOWTREE".equalsIgnoreCase(action)) forward=getTreeNode(mapping, theForm, request, response);
			//else if("SHOWLIST".equalsIgnoreCase(action)) forward=queryRecordList(mapping, theForm, request, response);
			//else if("SHOWDETAIL".equalsIgnoreCase(action)) forward=queryDetail(mapping, theForm, request, response);
		}
		catch (Exception e) {// 其他系统出错
			request.setAttribute("err", e);
			forward = mapping.findForward(ERROR);
		}
		return forward;
	}
	public ActionForward searchSubject(ActionMapping mapping, ClassifySubjectQueryForm theForm, HttpServletRequest request, HttpServletResponse response) throws Exception{
		if (log.isDebugEnabled()) log.debug("Entering 'searchSubject' method");
		ActionForward forward=null;
		try{
			QuerySubjectService querySubjectService=(QuerySubjectService)getBean("querySubjectService");
			PageQuery pageQuery=theForm.getQuery();
			pageQuery.setSelectParamId("GET_CLASSIFYSUBJECT_PAGE");
			String queryString = getQueryString(pageQuery);
			pageQuery.getParameters().put("queryString", queryString);
			querySubjectService.queryQuerySubject(pageQuery);
			initForm(theForm);
			setPage(theForm.getQuery());
			forward=mapping.findForward(LIST);
		}catch (Exception e) {
			request.setAttribute("err", e);
			forward = mapping.findForward(ERROR);
		}
		return forward;
	}
	public ActionForward showParamSubject(ActionMapping mapping, ClassifySubjectQueryForm theForm, HttpServletRequest request, HttpServletResponse response) throws Exception{
		if (log.isDebugEnabled()) log.debug("Entering 'searchSubject' method");
		ActionForward forward=null;
		try{
			String cataId=theForm.getCataId();
			
			QuerySubjectService querySubjectService=(QuerySubjectService)getBean("querySubjectService");
			QuerySubject subject=querySubjectService.findSubjectByCatagoryId(Integer.valueOf(cataId));
			if(subject==null){
				request.setAttribute("errMsg", "该分类主题还未建立，请联系管理员");
				return mapping.findForward("error");
			}
			theForm.setId(subject.getId().toString());
			List<Map<String, Object>> queryColumnlist=new ArrayList<Map<String,Object>>();
			QueryMetaIndPool queryMetaIndPool=(QueryMetaIndPool)getBean("queryMetaIndPool");
			SubjectConfigPool subjectConfigPool=(SubjectConfigPool)getBean("subjectConfigPool");
			
			SubjectConfigPoolObj subjectPoolObj=subjectConfigPool.getSubjectConfigPool(subject.getId().toString());
			List<String> cataList=null;
			List<Map<String,String>> tabMapList=new ArrayList<Map<String,String>>();
			TableConfigPool tableConfigPool=(TableConfigPool)getBean("tableConfigPool");
			//查找分类和子分类的所有表
			if(subjectPoolObj.getScId()!=null)
			{
				CatagoryPool catagoryPool=(CatagoryPool)getBean("catagoryPool");
				cataList=catagoryPool.getCatagoryTableMap(subjectPoolObj.getScId());
				if(cataList!=null && !cataList.isEmpty()){
					for(String str:cataList){
						Map<String,String> map=tableConfigPool.getTableMap(str);
						tabMapList.add(map);
					}
				}
			}
			
			List<SubjectColumnConfigPoolObj> paramPoolObj=subjectConfigPool.getSubjectColumnConfigPool(subject.getId().toString(),false);
			//代码集列表
			StringBuffer codeArrBuffer=new StringBuffer();
			for(SubjectColumnConfigPoolObj pool:paramPoolObj){
				Map<String,String> indMap=queryMetaIndPool.getIndicatorPool(pool.getIndId());
				String indId=pool.getIndId();
				if(indMap!=null){
					Map<String,Object> map=new HashMap<String, Object>();
					map.put("columnName", pool.getName());
					map.put("indId", indId);
					map.put("oper", pool.getFilterOperator());
					map.put("displayName", pool.getCnName());
					map.put("dataType", pool.getDataType());
					queryColumnlist.add(map);
				}
			}
			request.setAttribute("queryParamList", queryColumnlist);
			ResourceTableService resourceTableService=(ResourceTableService)getBean("resourceTableService");
			List<ResourceTable> list=resourceTableService.findTableByCatageoryId(subjectPoolObj.getScId());
			//request.setAttribute("tableList", tabMapList);
			request.setAttribute("seltablestr", genenrateSelTableHtmlCode(theForm,list));
			SynthesisColumnGenService synthesisColumnGenService=(SynthesisColumnGenService)getBean("synthesisColumnGenService");
			request.setAttribute("htmlcode", genenrateHtmlCode(subjectPoolObj, paramPoolObj, theForm,synthesisColumnGenService));
			
			forward=mapping.findForward("SHOWQUERY");
		}catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("err", e);
			forward = mapping.findForward(ERROR);
		}
		return forward;
	}
	public ActionForward queryOverView(ActionMapping mapping, ClassifySubjectQueryForm theForm, HttpServletRequest request, HttpServletResponse response) throws Exception{
		if (log.isDebugEnabled()) log.debug("Entering 'searchSubject' method");
		ActionForward forward=null;
		try{
			List<Map<String, Object>> queryColumnlist=new ArrayList<Map<String,Object>>();
			String[] tableArr=theForm.getSelTable();
			SynthesisColumnGenService synthesisColumnGenService=(SynthesisColumnGenService)getBean("synthesisColumnGenService");
			List<Map<String,String>> tableSqlList=new ArrayList<Map<String,String>>();
			for(int i=0;i<tableArr.length;i++){
				Map<String,String> tableMap=getQueryParam(queryColumnlist, theForm, tableArr[i],synthesisColumnGenService);
				tableSqlList.add(tableMap);
			}
			//获取对应表的总记录数
			initForm(theForm, "SHOWOVERVIEW",request);
			request.setAttribute("tableList", tableSqlList);
			request.setAttribute("querycount", "1");
			forward=mapping.findForward("QUERYOVERVIEW");
			
		}catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("err", e);
			forward = mapping.findForward(ERROR);
		}
		return forward;
	}
	public ActionForward queryTableCount(ActionMapping mapping, ClassifySubjectQueryForm theForm, HttpServletRequest request, HttpServletResponse response) throws Exception{
		if (log.isDebugEnabled()) log.debug("Entering 'searchSubject' method");
		ActionForward forward=null;
		showParamSubject(mapping, theForm, request, response);
		try{
			List<Map<String, Object>> queryColumnlist=new ArrayList<Map<String,Object>>();
			QueryMetaIndPool queryMetaIndPool=(QueryMetaIndPool)getBean("queryMetaIndPool");
			SubjectConfigPool subjectConfigPool=(SubjectConfigPool)getBean("subjectConfigPool");
			SubjectConfigPoolObj subjectPoolObj=subjectConfigPool.getSubjectConfigPool(theForm.getId());
			TableConfigPool tableConfigPool=(TableConfigPool)getBean("tableConfigPool");
			//
			BaseSqlGen sqlGen=(BaseSqlGen)getBean("sybaseSqlGen"); 
			List<SubjectColumnConfigPoolObj> paramPoolObj=subjectConfigPool.getSubjectColumnConfigPool(theForm.getId(),false);
			String[] tableArr=theForm.getSelTable();
			List<Map<String,String>> tableSqlList=new ArrayList<Map<String,String>>();
			List<String> sqlscriptList=new ArrayList<String>();
			SynthesisColumnGenService synthesisColumnGenService=(SynthesisColumnGenService)getBean("synthesisColumnGenService");
			for(int i=0;i<tableArr.length;i++){
				//单个表查询语句
				StringBuffer buffer=new StringBuffer();
				//字段缓存对象
				List<ColumnPoolObj> columnpoolList=tableConfigPool.getTableColumnPool(tableArr[i]);
				Map<String,String> tablepoolMap=tableConfigPool.getTableMap(tableArr[i]);
				CollectionMapConvert<ColumnPoolObj> convert=new CollectionMapConvert<ColumnPoolObj>();
				//表中指标对应的字段
				Map<String,List<ColumnPoolObj>> map=convert.convertToMapByParentKey(columnpoolList, "indicatorId");
				
				for(SubjectColumnConfigPoolObj pool:paramPoolObj){
					Map<String,String> indMap=queryMetaIndPool.getIndicatorPool(pool.getIndId());
					String indId=pool.getIndId();
					//指标存在且表中包括该指标
					if(indMap!=null && map.get(indId)!=null){
						List<ColumnPoolObj> list=map.get(indId);
						List<QueryParam> paramList=new ArrayList<QueryParam>();
						for(ColumnPoolObj colpool:list){
							//between操作
							if(pool.getFilterOperator().equals(Const.FILTER_OPER_BETWEEN)){
								String val1=theForm.getRecord().get("col"+indId+"from");
								String val2=theForm.getRecord().get("col"+indId+"to");
								if((val1!=null && !"".equals(val1)) || (val2!=null && !"".equals(val2))){
									paramList.add(new QueryParam(colpool.getName(),synthesisColumnGenService.filterColumnType(colpool),pool.getFilterOperator(),val1+";"+val2,""));
								}
							}else{
								String val=theForm.getRecord().get("col"+indId);
								if(val!=null && !"".equals(val))
									paramList.add(new QueryParam(colpool.getName(),synthesisColumnGenService.filterColumnType(colpool),pool.getFilterOperator(),val,""));
							}
						}
						String gensql=sqlGen.getQueryStringPart(paramList, QueryParam.OPER_OR);
						if(gensql!=null && !"".equals(gensql.trim()))
							buffer.append("("+gensql+")"+QueryParam.OPER_AND);
					}
					
				}
				
				Map<String,String> tableMap=new HashMap<String, String>();
				//获取对应表的查询语句和表名
				tableMap=tablepoolMap;
				tableMap.put("id", tableArr[i]);
				String querysql=buffer.length()>0?" and "+buffer.substring(0,buffer.length()-5):"";
				sqlscriptList.add(tablepoolMap.get("name")+" where 1=1"+querysql);
				tableMap.put("sql",URLEncoder.encode(tablepoolMap.get("name")+" where 1=1"+querysql,"UTF-8")); //URLEncoder.encode(tablepoolMap.get("name")+" where 1=1"+querysql,"UTF-8")
				tableSqlList.add(tableMap);
			}
			//获取对应表的总记录数
			SynthesisQueryService synthesisQueryService=(SynthesisQueryService)getBean("synthesisQueryService");
			
			for(int i=0;i<tableSqlList.size();i++){
				String sql="select count(id) from "+sqlscriptList.get(i);
				int count=synthesisQueryService.queryByInt(sql);
				tableSqlList.get(i).put("count", String.valueOf(count));
			}
			theForm.getQuery().setRecordSet(tableSqlList);
			request.setAttribute("querycount", "1");
			request.setAttribute("htmlcode", genenrateHtmlCode(subjectPoolObj, paramPoolObj, theForm,synthesisColumnGenService));
			forward=mapping.findForward("QUERYCOUNT");
			
		}catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("err", e);
			forward = mapping.findForward(ERROR);
		}
		return forward;
	}
	public ActionForward queryRecordList(ActionMapping mapping, ClassifySubjectQueryForm theForm, HttpServletRequest request, HttpServletResponse response) throws Exception{
		if (log.isDebugEnabled()) log.debug("Entering 'searchSubject' method");
		ActionForward forward=null;
		
		try{
			SynthesisQueryService synthesisQueryService=(SynthesisQueryService)getBean("synthesisQueryService");
			TableConfigPool tableConfigPool=(TableConfigPool)getBean("tableConfigPool");
			BussCodeSetPool bussCodeSetPool=(BussCodeSetPool)getBean("bussCodeSetPool");
			Map<String,String> tablepoolMap=tableConfigPool.getTableMap(theForm.getTabId());
			List<ColumnPoolObj> columnpoolList=tableConfigPool.getTableColumnPool(theForm.getTabId());
			List<ColumnConfigPoolObj> columncfgpoolList=tableConfigPool.getColumnConfigPool(theForm.getTabId());
			StringBuffer buffer=new StringBuffer();
			CollectionMapConvert<ColumnPoolObj> convert=new CollectionMapConvert<ColumnPoolObj>();
			Map<String,ColumnPoolObj> map=convert.convertListToMap(columnpoolList, "id");
			StringBuffer widthbuffer=new StringBuffer();
			StringBuffer dnamebuffer=new StringBuffer();
			StringBuffer alignbuffer=new StringBuffer();
			StringBuffer typebuffer=new StringBuffer();
			List<String> colnameList=new ArrayList<String>();
			//grid2 Columns字段动态list
			List<Column> columnList=new ArrayList<Column>();
			//主键字段
			List<String> displayList=new ArrayList<String>();
			ResourceColumnService resourceColumnService=(ResourceColumnService)getBean("resourceColumnService");
			ResourceColumn pkobj=resourceColumnService.findResoucrePKColumn(Integer.valueOf(theForm.getTabId()));
			SynthesisColumnGenService synthesisColumnGenService=(SynthesisColumnGenService)getBean("synthesisColumnGenService");
			for(ColumnConfigPoolObj pool:columncfgpoolList){
				ColumnPoolObj colpool=map.get(pool.getColumnId());
				if(pool.getIsSubject()!=null && "1".equals(pool.getIsSubject()))
				{
					buffer.append(colpool.getName()+",");
					colnameList.add(colpool.getName());
					dnamebuffer.append(colpool.getCnName()+",");
					alignbuffer.append("center,");
					displayList.add(colpool.getName());
					typebuffer.append(synthesisColumnGenService.getDisplayDataType(colpool)+",");
					if(pool.getDisplayWidth()!=null && !"".equals(pool.getDisplayWidth()))
						widthbuffer.append(pool.getDisplayWidth()+",");
					else
						widthbuffer.append("100,");
					//grid2配置
					Column col=new Column();
//					col.setWidth("15%");
					col.setAlign("center");
					col.setName(colpool.getCnName());
					col.setProperty(colpool.getName());
					if(columnList.isEmpty()){
						col.setItemType("hyperlink");
						col.setHref("#");
						col.setOnClick("showdetail('{"+pkobj.getName()+"}')");
					}
					columnList.add(col);
				}
					
			}
			
			String widthstr="";
			String colstr="";
			String cnname="";
			String alignstr="";
			String typestr="";
			//显示字段是否包括主键,未包括就加入最前面
			if(pkobj!=null && displayList.contains(pkobj.getName()))
			{
				widthstr=widthbuffer.substring(0,widthbuffer.length()-1);
				colstr=buffer.toString().substring(0,buffer.length()-1);
				cnname=dnamebuffer.substring(0,dnamebuffer.length()-1);
				alignstr=alignbuffer.substring(0,alignbuffer.length()-1);
				typestr=typebuffer.substring(0,typebuffer.length()-1);
			}
			else{
				widthstr="40,"+widthbuffer.substring(0,widthbuffer.length()-1);
				colstr=pkobj.getName()+","+buffer.toString().substring(0,buffer.length()-1);
				cnname=pkobj.getCnName()+","+dnamebuffer.substring(0,dnamebuffer.length()-1);
				alignstr="center,"+alignbuffer.substring(0,alignbuffer.length()-1);
				typestr="int,"+typebuffer.substring(0,typebuffer.length()-1);
				colnameList.add(pkobj.getName());
				
			}
			List<Map<String, Object>> queryColumnlist=new ArrayList<Map<String,Object>>();
			String sql="select "+colstr+" from "+ getQueryParam(queryColumnlist, theForm, theForm.getTabId(),synthesisColumnGenService).get("sql");//
			PageQuery pageQuery=theForm.getQuery();
			List<Map<String,String>> list=synthesisQueryService.queryBySql(sql, pageQuery).getRecordSet();
			//过滤代码集
			bussCodeSetPool.filterBussCodeSet(list, columnpoolList);
			request.setAttribute("columnIds", colstr);
			request.setAttribute("columnWidths", widthstr);
			request.setAttribute("columnNames", cnname);
			request.setAttribute("alignModes", alignstr);
			request.setAttribute("colTypes", typestr);
			request.setAttribute("xmlstr", synthesisColumnGenService.getResultXml(list, colnameList,pkobj));
			request.setAttribute(Column.DYNAMICCOLUMNS, columnList);
			forward=mapping.findForward("QUERYLIST");
			
		}catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("err", e);
			forward = mapping.findForward(ERROR);
		}
		return forward;
	}
	public ActionForward queryDetail(ActionMapping mapping, ClassifySubjectQueryForm theForm, HttpServletRequest request, HttpServletResponse response) throws Exception{
		if (log.isDebugEnabled()) log.debug("Entering 'searchSubject' method");
		ActionForward forward=null;
		
		try{
			SynthesisQueryService synthesisQueryService=(SynthesisQueryService)getBean("synthesisQueryService");
			TableConfigPool tableConfigPool=(TableConfigPool)getBean("tableConfigPool");
			ResourceTableService resourceTableService=(ResourceTableService)getBean("resourceTableService");
			BussCodeSetPool bussCodeSetPool=(BussCodeSetPool)getBean("bussCodeSetPool");
			Map<String,String> tablepoolMap=tableConfigPool.getTableMap(theForm.getTabId());
			List<ColumnPoolObj> columnpoolList=tableConfigPool.getTableColumnPool(theForm.getTabId());
			List<ColumnConfigPoolObj> columncfgpoolList=tableConfigPool.getColumnConfigPool(theForm.getTabId());
			StringBuffer buffer=new StringBuffer();
			CollectionMapConvert<ColumnPoolObj> convert=new CollectionMapConvert<ColumnPoolObj>();
			Map<String,ColumnPoolObj> map=convert.convertListToMap(columnpoolList, "id");
			StringBuffer dnamebuffer=new StringBuffer();
			String uid=request.getParameter("uid");
			SynthesisColumnGenService synthesisColumnGenService=(SynthesisColumnGenService)getBean("synthesisColumnGenService");
			//主键字段
			
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
	private Map<String,String> getQueryParam(List<Map<String, Object>> queryColumnlist,ClassifySubjectQueryForm theForm,String tabId,SynthesisColumnGenService synthesisColumnGenService) throws Exception{
		QueryMetaIndPool queryMetaIndPool=(QueryMetaIndPool)getBean("queryMetaIndPool");
		SubjectConfigPool subjectConfigPool=(SubjectConfigPool)getBean("subjectConfigPool");
		SubjectConfigPoolObj subjectPoolObj=subjectConfigPool.getSubjectConfigPool(theForm.getId());
		TableConfigPool tableConfigPool=(TableConfigPool)getBean("tableConfigPool");
		//
		BaseSqlGen sqlGen=(BaseSqlGen)getBean("sybaseSqlGen"); 
		List<SubjectColumnConfigPoolObj> paramPoolObj=subjectConfigPool.getSubjectColumnConfigPool(theForm.getId(),false);
		String[] tableArr=theForm.getSelTable();
		
		List<Map<String,String>> tableSqlList=new ArrayList<Map<String,String>>();
		
		
			//单个表查询语句
			StringBuffer buffer=new StringBuffer();
			//字段缓存对象
			List<ColumnPoolObj> columnpoolList=tableConfigPool.getTableColumnPool(tabId);
			Map<String,String> tablepoolMap=tableConfigPool.getTableMap(tabId);
			CollectionMapConvert<ColumnPoolObj> convert=new CollectionMapConvert<ColumnPoolObj>();
			//表中指标对应的字段
			Map<String,List<ColumnPoolObj>> map=convert.convertToMapByParentKey(columnpoolList, "indicatorId");
			
			for(SubjectColumnConfigPoolObj pool:paramPoolObj){
				Map<String,String> indMap=queryMetaIndPool.getIndicatorPool(pool.getIndId());
				String indId=pool.getIndId();
				//指标存在且表中包括该指标
				if(indMap!=null && map.get(indId)!=null){
					List<ColumnPoolObj> list=map.get(indId);
					List<QueryParam> paramList=new ArrayList<QueryParam>();
					for(ColumnPoolObj colpool:list){
						if(pool.getFilterOperator().equals(Const.FILTER_OPER_BETWEEN)){
							String val1=theForm.getRecord().get("col"+indId+"from");
							String val2=theForm.getRecord().get("col"+indId+"to");
							if((val1!=null && !"".equals(val1)) || (val2!=null && !"".equals(val2))){
								paramList.add(new QueryParam(colpool.getName(),synthesisColumnGenService.filterColumnType(colpool),pool.getFilterOperator(),val1+";"+val2,""));
							}
						}else{
							String val=theForm.getRecord().get("col"+indId);
							if(val!=null && !"".equals(val))
								paramList.add(new QueryParam(colpool.getName(),synthesisColumnGenService.filterColumnType(colpool),pool.getFilterOperator(),val,""));
						}
					}
					String gensql=sqlGen.getQueryStringPart(paramList, QueryParam.OPER_OR);
					if(gensql!=null && !"".equals(gensql.trim()))
						buffer.append("("+gensql+")"+QueryParam.OPER_AND);
				}
				
			}
			Map<String,String> tableMap=new HashMap<String, String>();
			//获取对应表的查询语句和表名
			tableMap=tablepoolMap;
			tableMap.put("id", tabId);
			String querysql=buffer.length()>0?" and "+buffer.substring(0,buffer.length()-5):"";
			tableMap.put("sql",tablepoolMap.get("name")+" where 1=1"+querysql);
			return tableMap;
	}
		
	private String getQueryString(PageQuery pageQuery) {
		StringBuffer buffer = new StringBuffer();
		Map fields = pageQuery.getParameters();
		String str=(String) fields.get("tradeId");
		if(str!=null &&!"".equals(str))
			buffer.append(" and b.parent_id="+str);
		str=(String) fields.get("categoryId");
		if(str!=null &&!"".equals(str))
			buffer.append(" and a.catagory_id="+str);
		
		return buffer.toString();
	}
	private void initForm(ClassifySubjectQueryForm theForm){
		
		ResourceTableService rs = (ResourceTableService)getBean("resourceTableService");
		
		//设置行业代码集
		List<StandardCategory> trade = rs.findAllTrade();
		if (trade==null) {
			trade = new ArrayList<StandardCategory>();
		}
		setCode(theForm, "Trade", trade, StandardCategory.PROP_NAME, StandardCategory.PROP_ID);
		//advancedQueryForm.getCodeSets().get("Trade").get(0).setCodeName("全部");
		theForm.getCodeSets().get("Trade").remove(0);
		
		//设置分类代码集
		List<StandardCategory> categorys;
		String tradeId = theForm.getQuery().getParameters().get("tradeId");
		if (tradeId==null||"".equals(tradeId)) {
			//设置分类代码集为全部
			categorys = rs.findCategoryByTradeId(null);
		}else {
			//设置分类代码集为该行业tradeId下面的所有分类
			categorys = rs.findCategoryByTradeId(tradeId);
		}
		setCode(theForm, "Categorys", categorys, StandardCategory.PROP_NAME, StandardCategory.PROP_ID);
		
		theForm.getCodeSets().get("Categorys").remove(0);
	}
	private String genenrateHtmlCode(SubjectConfigPoolObj subjectPoolObj,List<SubjectColumnConfigPoolObj> configList,ClassifySubjectQueryForm theForm,SynthesisColumnGenService synthesisColumnGenService){
		
		StringBuffer buffer=new StringBuffer();
		String retStr="";
		QueryMetaIndPool queryMetaIndPool=(QueryMetaIndPool)getBean("queryMetaIndPool");
		int rowcount=2;   //每行显示查询个数，暂定2
		String trstart="</tr><tr>";
		String twonamecoltd="<td width=\"20%\">";
		String twofieldcoltd="<td width=\"30%\">";
		String threenamecoltd="<td width=\"13%\">";
		String threefieldcoltd="<td width=\"20%\">";
		String tdbegin="<td>";
		String tdend="</td>";
		String trend="</tr>";
		buffer.append("<tr>");
		int count=0;
		BussCodeSetPool bussCodeSetPool=(BussCodeSetPool)getBean("bussCodeSetPool");
		for(SubjectColumnConfigPoolObj pool:configList){
			Map<String,String> indMap=queryMetaIndPool.getIndicatorPool(pool.getIndId());
			String indId=pool.getIndId();	
			if(indMap!=null){
				count++;
				if(count % rowcount==0){
					buffer.append(twonamecoltd+pool.getCnName()+"："+tdend);
					buffer.append(twofieldcoltd+synthesisColumnGenService.getInputHtmlCode(pool,bussCodeSetPool,theForm)+tdend);
					buffer.append(trstart);
				}
				else{
					buffer.append(twonamecoltd+pool.getCnName()+"："+tdend);
					buffer.append(twofieldcoltd+synthesisColumnGenService.getInputHtmlCode(pool,bussCodeSetPool,theForm)+tdend);
				}
			}
		}
		if(count % rowcount==0)
			retStr=buffer.substring(0,buffer.length()-4); //去掉最后的<tr>
		else{
			int repnum=rowcount-count % rowcount;
			for(int i=0;i<repnum;i++)
				buffer.append("<td>&nbsp;</td><td>&nbsp;</td>");
			retStr=buffer.append("</tr>").toString();
		}
		return retStr;
	}
	private void initForm(ClassifySubjectQueryForm theForm, String action,HttpServletRequest request) throws Exception {
		if(action.equalsIgnoreCase("SHOWOVERVIEW")){
			Map<String,String> map=theForm.getRecord();
			Iterator<String> iter=map.keySet().iterator();
			List<Map<String,String>> list=new ArrayList<Map<String,String>>();
			while(iter.hasNext()){
				String key=iter.next();
				String value=map.get(key);	
				Map<String,String> listmap=new HashMap<String, String>();
				listmap.put("key", "record("+key+")");
				listmap.put("value", value);
				list.add(listmap);
			}
			request.setAttribute("paramList", list);
			String[] tabArr=theForm.getSelTable();
			List<Map<String,String>> tablist=new ArrayList<Map<String,String>>();
			for(int i=0;i<tabArr.length;i++){
				Map<String,String> listmap=new HashMap<String, String>();
				listmap.put("id", tabArr[i]);
				tablist.add(listmap);
			}
			request.setAttribute("selTabList", tablist);
		}
	}
	private ActionForward getTreeNode(ActionMapping mapping, ClassifySubjectQueryForm theForm, HttpServletRequest request, HttpServletResponse response) throws Exception {
		if (log.isDebugEnabled()) log.debug("Entering 'getTree' method");
		String id=theForm.getId();

		response.setContentType("text/xml;charset=UTF-8");
		response.setHeader("Cache_Control", "no-cache");
		String retstr="";
		try {
			if (id!=null && !"".equals(id.trim())) {
				
				if(id.equals("buss"))
					retstr=getBussinessRange("0");
				else
					retstr=getBussinessRange(id);
				response.getWriter().write(retstr);
			}
			else {
//				retstr=getBussinessRange("0");
				response.getWriter().write(retstr);
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
		
		//sb.append("<item id=\"comm" + "\" text=\"公共分类\" child=\"1\"></item>");
		sb.append("<item id=\"buss" + "\" text=\"行业分类\"  im0=\"/icon/code_all.gif\" im1=\"/icon/code_all.gif\" im2=\"/icon/code_all.gif\" child=\"1\"></item>");
			
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
				String[] nameArr=new String[]{"name"};
				
				DhtmlTreeParam param=new DhtmlTreeParam("id","name","",nameArr,nameArr,"/icon/code_child.gif");
				if(parentId==null)
					parentId="";
				retStr=DhtmlTreeUtil.genXmlTreeByList(listmap, param, parentId,true);	
			}else
				retStr=DhtmlTreeUtil.genBlankXmlTree(parentId);
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
	private String genenrateSelTableHtmlCode(ClassifySubjectQueryForm theForm,List<ResourceTable> tablSqlList){
		StringBuffer buffer=new StringBuffer();
		String retStr="";
		String[] tabId=theForm.getSelTable();
		List<String> selTabList=new ArrayList<String>();
		if(tabId!=null){
			for(int k=0;k<tabId.length;k++)
				selTabList.add(tabId[k]);
		}
		int rowcount=5;   //每行显示查询个数，暂定5
		String trstart="</tr><tr>";
		String twonamecoltd="<td width=\"3%\" class=\"textL\"><input type=\"checkbox\" class=\"checkbox\" name=\"selTable\" value=\"";
		String twofieldcoltd="<td width=\"17%\" class=\"textL\">";
		
		String tdbegin="<td>";
		String tdend="</td>";
		String trend="</tr>";
		buffer.append("<tr>");
		int count=0;
		for(ResourceTable tab:tablSqlList){
			count++;
			String checked="";
			if(selTabList.contains(tab.getId().toString()))
				checked="checked";
			if(count % rowcount==0){
				buffer.append(twonamecoltd+tab.getId().toString()+"\""+checked+"/>"+tdend);
				buffer.append(twofieldcoltd+tab.getCnName()+tdend);
				buffer.append(trstart);
			}
			else{
				buffer.append(twonamecoltd+tab.getId().toString()+"\""+checked+"/>"+tdend);
				buffer.append(twofieldcoltd+tab.getCnName()+tdend);
			}
			
		}
		if(count % rowcount==0)
			retStr=buffer.substring(0,buffer.length()-4); //去掉最后的<tr>
		else{
			int repnum=(rowcount-count % rowcount)*2;
			buffer.append("<td colspan=\""+repnum+"\"></td>");
			retStr=buffer.append("</tr>").toString();
		}
		return retStr;
	}
	
}
