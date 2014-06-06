package com.liusy.datapp.web.dynamicquery.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import zeal.util.GB2Big5;

import com.liusy.core.util.CollectionMapConvert;
import com.liusy.core.util.Const;
import com.liusy.core.util.Session;
import com.liusy.dataapplatform.base.exception.WebException;
import com.liusy.dataapplatform.base.util.BaseSqlGen;
import com.liusy.dataapplatform.base.util.CsvGenerator;
import com.liusy.dataapplatform.base.util.DateUtil;
import com.liusy.dataapplatform.base.util.ExcelGenerator;
import com.liusy.dataapplatform.base.util.ExcelSheetColProp;
import com.liusy.dataapplatform.base.util.PageQuery;
import com.liusy.dataapplatform.base.util.QueryParam;
import com.liusy.dataapplatform.base.util.StringUtil;
import com.liusy.dataapplatform.code.Code;
import com.liusy.datapp.dao.query.SynthesisTempSpaceDao;
import com.liusy.datapp.model.datastandard.StandardCategory;
import com.liusy.datapp.model.query.QuerySubject;
import com.liusy.datapp.model.resource.ResourceColumn;
import com.liusy.datapp.model.resource.ResourceTable;
import com.liusy.datapp.service.datastandard.StandardCategoryService;
import com.liusy.datapp.service.dynamicquery.SynthesisColumnGenService;
import com.liusy.datapp.service.pool.BussCodeSetPool;
import com.liusy.datapp.service.pool.QueryMetaIndPool;
import com.liusy.datapp.service.pool.SubjectConfigPool;
import com.liusy.datapp.service.pool.TableConfigPool;
import com.liusy.datapp.service.query.QuerySubjectService;
import com.liusy.datapp.service.query.SynthesisQueryService;
import com.liusy.datapp.service.resource.ResourceColumnService;
import com.liusy.datapp.service.resource.ResourceTableService;
import com.liusy.datapp.service.thread.QueryResultTableSaveThread;
import com.liusy.datapp.util.DhtmlTreeParam;
import com.liusy.datapp.util.DhtmlTreeUtil;
import com.liusy.datapp.util.ThreadPool;
import com.liusy.datapp.util.meta.SqlScriptGenerator;
import com.liusy.datapp.util.poolobj.ColumnConfigPoolObj;
import com.liusy.datapp.util.poolobj.ColumnPoolObj;
import com.liusy.datapp.util.poolobj.SubjectColumnConfigPoolObj;
import com.liusy.datapp.util.poolobj.SubjectConfigPoolObj;
import com.liusy.datapp.web.BaseAction;
import com.liusy.datapp.web.dynamicquery.form.CommSubjectQueryForm;
import com.liusy.datapp.web.dynamicquery.form.UserDefinedQueryForm;
import com.liusy.web.tag.grid.Column;

public class CommSubjectQueryAction extends BaseAction {

	@Override
	public ActionForward doExecute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		String action = request.getParameter("action1");
		if (action == null) action = LIST;
		if (log.isDebugEnabled()) log.debug("action:" + action);
		ActionForward forward = null;
		CommSubjectQueryForm theForm = (CommSubjectQueryForm) form;// theForm.setTableId("1");
		try {
			if (LIST.equalsIgnoreCase(action)) forward = searchSubject(mapping, theForm, request, response); // 打开查询列表页面
			else if ("OVERVIEW".equalsIgnoreCase(action)) forward = queryOverView(mapping, theForm, request, response);// 打开概览页面
			else if ("SHOWTREE".equalsIgnoreCase(action)) forward = getTreeNode(mapping, theForm, request, response);// Ajax树列表
			else if ("SHOWQUERY".equalsIgnoreCase(action)) forward = showParamSubject(mapping, theForm, request, response);// 打开查询条件页面
			else if ("QUERYCOUNT".equalsIgnoreCase(action)) forward = queryTableCount(mapping, theForm, request, response);// 打开记录总数页面
			else if ("SHOWLIST".equalsIgnoreCase(action)) forward = queryRecordList(mapping, theForm, request, response);// 打开查询列表页面
			else if ("SHOWDETAIL".equalsIgnoreCase(action)) forward = queryDetail(mapping, theForm, request, response);// 打开查询详细结果页面
		//	else if ("EXPORTPARAM".equalsIgnoreCase(action)) forward = loadQueryParam(mapping, theForm, request, response);
			else if ("SAVERESULT".equalsIgnoreCase(action)) forward = saveResult(mapping, theForm, request, response);
			else if ("EXPORTEXCEL".equalsIgnoreCase(action)) forward = exportExcel(mapping, theForm, request, response);
			// else if("SAVEPARAM".equalsIgnoreCase(action))
			// forward=saveQueryParam(mapping, theForm, request, response);
			// //保存查询条件到个人空间
			else {
				request.setAttribute("err", new WebException("找不到该action方法：" + action));
				forward = mapping.findForward(ERROR);// 找不到合适的action
			}
			Session session = (Session) request.getSession().getAttribute(Const.SESSION);
			// 判断是否配置个人空间
			Integer spaceId = 0;
			if (session.getSpaceId() == null || "".equals(session.getSpaceId().trim()) || Const.TAG_DISABLE.equals(session.getSpaceId())) {
				request.setAttribute("hasspace", "0");
			}
			else request.setAttribute("hasspace", "1");

		}
		catch (Exception e) {// 其他系统出错
			request.setAttribute("err", e);
			request.setAttribute("errMsg", e.getMessage());
			forward = mapping.findForward(ERROR);
		}
		return forward;
	}

	public ActionForward exportExcel(ActionMapping mapping, CommSubjectQueryForm theForm, HttpServletRequest request, HttpServletResponse response) throws Exception {
		if (log.isDebugEnabled()) log.debug("Entering 'saveResult' method");
		ActionForward forward = null;
		try {
			String ftype = request.getParameter("ftype");
			List<Map<String, Object>> queryColumnlist = new ArrayList<Map<String, Object>>();
			String tableId = theForm.getTableId();
			TableConfigPool tableConfigPool = (TableConfigPool) getBean("tableConfigPool");
			List<ColumnPoolObj> columnpoolList = tableConfigPool.getTableColumnPool(theForm.getTableId());
			List<ColumnConfigPoolObj> columncfgpoolList = tableConfigPool.getColumnConfigPool(theForm.getTableId());

			StringBuffer buffer = new StringBuffer();
			CollectionMapConvert<ColumnPoolObj> convert = new CollectionMapConvert<ColumnPoolObj>();
			Map<String, ColumnPoolObj> map = convert.convertListToMap(columnpoolList, "id");
			for (ColumnConfigPoolObj pool : columncfgpoolList) {
				ColumnPoolObj colpool = map.get(pool.getColumnId());
				if (pool.getIsShown() != null && Const.TAG_ENABLE.equals(pool.getIsShown())) {
					buffer.append(colpool.getName() + ",");
				}
			}
			SynthesisColumnGenService synthesisColumnGenService = (SynthesisColumnGenService) getBean("synthesisColumnGenService");
			Map<String, String> tableMap = getQueryParam(queryColumnlist, theForm, tableId, synthesisColumnGenService);
			Session session = (Session) request.getSession().getAttribute(Const.SESSION);
			// 将数据从后台查询出来
			SynthesisQueryService synthesisQueryService = (SynthesisQueryService) getBean("synthesisQueryService");
			String sqlstr = "select * from " + tableMap.get("sql");
			PageQuery pageQuery = new PageQuery();
			pageQuery.setPageSize("0");
			List<Map<String, String>> resultList = synthesisQueryService.queryBySql(sqlstr, pageQuery).getRecordSet();

			// 数据安全等级过滤，保证安全
			ResourceColumnService resourceColumnService = (ResourceColumnService) getBean("resourceColumnService");
			ResourceColumn pkobj = resourceColumnService.findResoucrePKColumn(Integer.valueOf(tableId));
			synthesisColumnGenService.filterSecurityLevel(tableMap.get("id"), resultList, columnpoolList, pkobj, session);

			if ("0".equals(ftype)) {
				// 导出excel文件
				ExcelSheetColProp prop = new ExcelSheetColProp();
				List<String> columnNameList = new ArrayList<String>();
				List<String> columnEnameList = new ArrayList<String>();
				List<String> columnTypeList = new ArrayList<String>();

				for (ColumnPoolObj col : columnpoolList) {
					columnEnameList.add(col.getName());
					columnNameList.add(col.getCnName());
					columnTypeList.add(col.getDataType());
				}

				String[] headerStr = new String[columnNameList.size()];
				String[] columnNamestr = new String[columnEnameList.size()];
				String[] columntypetr = new String[columnNameList.size()];
				prop.setHeaderName(columnNameList.toArray(headerStr));
				prop.setColumnName(columnEnameList.toArray(columnNamestr));
				prop.setColumnType(columnTypeList.toArray(columntypetr));
				prop.setColumnList(resultList);
				// prop.setTableId(tableId);
				// TODO:默认从2行一列开始读取
				prop.setStartCol(1);
				prop.setStartRow(2);
				prop.setSheetName(tableMap.get("name"));
				response.setContentType("application/vnd.ms-excel;charset=utf-8");
				response.setHeader("Content-Disposition", "attachment;filename=datafile.xls");
				HSSFWorkbook wb;
				wb = ExcelGenerator.GenerateExcelFile(prop, null);
				ServletOutputStream os = response.getOutputStream();
				wb.write(os);
				os.flush();
				os.close();
			}
			else {
				// 导出csv文件
				String[] header = new String[0];
				List<String[]> resultArrList = new ArrayList<String[]>();

				if (columnpoolList == null || columnpoolList.isEmpty()) {
					if (resultList != null && resultList.size() > 0) {
						Map<String, String> result = resultList.get(0);
						if (result != null && !result.isEmpty()) {
							Object[] objs = result.keySet().toArray();
							header = new String[objs.length];
							System.arraycopy(objs, 0, header, 0, objs.length);
						}
					}
				}
				else {
					header = new String[columnpoolList.size()];
					for (int i = 0; i < columnpoolList.size(); i++) {
						header[i] = columnpoolList.get(i).getName();
					}
				}

				for (int j = 0; j < resultList.size(); j++) {
					String[] resultArr = new String[header.length];
					Map<String, String> resultMap = resultList.get(j);
					for (int k = 0; k < header.length; k++) {
						if (resultMap.get(header[k].toUpperCase()) != null && !"".equals(resultMap.get(header[k].toUpperCase()))) {
							resultArr[k] = resultMap.get(header[k].toUpperCase());
						}
						else resultArr[k] = "";
					}
					resultArrList.add(resultArr);
				}
				response.setContentType("application/vnd.ms-excel;charset=gbk");
				response.setHeader("Content-Disposition", "attachment;filename=datafile.csv");
				CsvGenerator.WriteFile(response.getWriter(), header, resultArrList);
				response.getWriter().flush();
			}
		}
		catch (Exception e) {
			e.printStackTrace();
			log.error(e);
		}
		return forward;
	}

	public ActionForward showTree(ActionMapping mapping, CommSubjectQueryForm theForm, HttpServletRequest request, HttpServletResponse response) throws Exception {
		if (log.isDebugEnabled()) log.debug("Entering 'showTree' method");
		try {
			// String id=theForm.getId();
			// QuerySubjectService
			// querySubjectService=(QuerySubjectService)getBean("querySubjectService");
			// List<QuerySubject> commList=querySubjectService.findCommSubject();
			response.setContentType("text/xml;charset=UTF-8");
			response.setHeader("Cache_Control", "no-cache");
			String tree = request.getParameter("tree");
			String retStr;
			if (tree.equalsIgnoreCase("ALL")) retStr = getTopNodes(true);
			else retStr = getTopNodes(false);
			response.getWriter().write(retStr);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public ActionForward searchSubject(ActionMapping mapping, CommSubjectQueryForm theForm, HttpServletRequest request, HttpServletResponse response) throws Exception {
		if (log.isDebugEnabled()) log.debug("Entering 'searchSubject' method");
		ActionForward forward = null;
		try {
			QuerySubjectService querySubjectService = (QuerySubjectService) getBean("querySubjectService");
			PageQuery pageQuery = theForm.getQuery();
			pageQuery.setSelectParamId("GET_COMMSUBJECT_PAGE");
			String queryString = getQueryString(pageQuery);
			pageQuery.getParameters().put("queryString", queryString);
			querySubjectService.queryQuerySubject(pageQuery);
			initForm(theForm, LIST, request);
			setPage(theForm.getQuery());
			String tree = request.getParameter("tree");
			request.setAttribute("tree", tree);
			forward = mapping.findForward(LIST);
		}
		catch (Exception e) {
			request.setAttribute("err", e);
			forward = mapping.findForward(ERROR);
		}
		return forward;
	}

	public ActionForward queryTableCount(ActionMapping mapping, CommSubjectQueryForm theForm, HttpServletRequest request, HttpServletResponse response) throws Exception {
		if (log.isDebugEnabled()) log.debug("Entering 'queryTableCount' method");
		ActionForward forward = null;
		showParamSubject(mapping, theForm, request, response);
		try {
			List<Map<String, Object>> queryColumnlist = new ArrayList<Map<String, Object>>();
			String[] tableArr = theForm.getSelTable();

			List<Map<String, String>> tableSqlList = new ArrayList<Map<String, String>>();
			// 获取查询条件
			SynthesisColumnGenService synthesisColumnGenService = (SynthesisColumnGenService) getBean("synthesisColumnGenService");
			for (int i = 0; i < tableArr.length; i++) {
				Map<String, String> tableMap = getQueryParam(queryColumnlist, theForm, tableArr[i], synthesisColumnGenService);
				tableSqlList.add(tableMap);
			}
			SynthesisQueryService synthesisQueryService = (SynthesisQueryService) getBean("synthesisQueryService");
			// 获取对应表的总记录数
			for (int i = 0; i < tableSqlList.size(); i++) {
				if (tableSqlList.get(i).get("sql") != null) {
					String sql = "select count(id) from " + tableSqlList.get(i).get("sql");// sqlscriptList.get(i);
					int count = synthesisQueryService.queryByInt(sql);
					tableSqlList.get(i).put("count", String.valueOf(count));
				}
				else tableSqlList.get(i).put("count", "未查询");

			}
			theForm.getQuery().setPageSize("100");
			theForm.getQuery().setRecordSet(tableSqlList);
			// initForm(theForm, "SHOWCOUNT",request);
			// request.setAttribute("xmlstr",
			// SynthesisColumnGen.getTableCountResultXml(tableSqlList));
			forward = mapping.findForward("QUERYCOUNT");

		}
		catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("err", e);
			forward = mapping.findForward(ERROR);
		}
		return forward;
	}

	public ActionForward queryOverView(ActionMapping mapping, CommSubjectQueryForm theForm, HttpServletRequest request, HttpServletResponse response) throws Exception {
		if (log.isDebugEnabled()) log.debug("Entering 'queryOverView' method");
		ActionForward forward = null;
		try {
			List<Map<String, Object>> queryColumnlist = new ArrayList<Map<String, Object>>();
			String[] tableArr = theForm.getSelTable();

			List<Map<String, String>> tableSqlList = new ArrayList<Map<String, String>>();
			SynthesisColumnGenService synthesisColumnGenService = (SynthesisColumnGenService) getBean("synthesisColumnGenService");
			for (int i = 0; i < tableArr.length; i++) {
				Map<String, String> tableMap = getQueryParam(queryColumnlist, theForm, tableArr[i], synthesisColumnGenService);
				tableSqlList.add(tableMap);
			}
			// 获取对应表的总记录数
			initForm(theForm, "SHOWOVERVIEW", request);
			String isspecial = theForm.getIsspecial();
			if ("true".equals(isspecial)) {
				request.setAttribute("isspesial", "true");
			}
			request.setAttribute("tableList", tableSqlList);
			forward = mapping.findForward("QUERYOVERVIEW");

		}
		catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("err", e);
			forward = mapping.findForward(ERROR);
		}
		return forward;
	}

	// private ActionForward querySpaceTable(ActionMapping mapping,
	// CommSubjectQueryForm theForm, HttpServletRequest request,
	// HttpServletResponse response)throws Exception{
	// if (log.isDebugEnabled()) log.debug("Entering 'querySpaceTable' method");
	// ActionForward forward = null;
	// String tableId = request.getParameter("tableId");
	// if(tableId==null){tableId="";}
	// if (!"".equals(tableId)) {
	// ResourceTableService resourceTableService =
	// (ResourceTableService)getBean("resourceTableService");
	// ResourceTable table = resourceTableService.findResourceTable(new
	// Integer(tableId));
	// if(table!=null){
	// SynthesisQueryService synthesisQueryService =
	// (SynthesisQueryService)getBean("synthesisQueryService");
	// String sql = "select * from "+table.getName();
	// PageQuery pageQuery = theForm.getQuery();
	// //pageQuery.setPageSize("0");
	// List<Map<String, String>> list = synthesisQueryService.queryBySql(sql,
	// pageQuery).getRecordSet();
	//				
	// setPage(pageQuery);
	// }else{
	//				
	// }
	// }else {
	//			
	// }
	// }

	/**
	 * 保存查询结果到个人空间
	 * 
	 * @param mapping
	 * @param theForm
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward saveResult(ActionMapping mapping, CommSubjectQueryForm theForm, HttpServletRequest request, HttpServletResponse response) throws Exception {
		if (log.isDebugEnabled()) log.debug("Entering 'saveResult' method");
		ActionForward forward = null;
		boolean processOk = true;
		String retStr = "OK";
		String ename = getCurrentUser(request).getAccountName() + "_" + DateUtil.getDateTime("yyyyMMddHHmmss", new Date(System.currentTimeMillis()));
		String cname = request.getParameter("tabcname");
		try {
			List<Map<String, Object>> queryColumnlist = new ArrayList<Map<String, Object>>();
			String tableId = theForm.getTableId();
			TableConfigPool tableConfigPool = (TableConfigPool) getBean("tableConfigPool");
			ResourceTableService resourceTableService = (ResourceTableService) getBean("resourceTableService");
			BussCodeSetPool bussCodeSetPool = (BussCodeSetPool) getBean("bussCodeSetPool");
			List<ColumnPoolObj> columnpoolList = tableConfigPool.getTableColumnPool(theForm.getTableId());
			List<ColumnConfigPoolObj> columncfgpoolList = tableConfigPool.getColumnConfigPool(theForm.getTableId());

			StringBuffer buffer = new StringBuffer();
			CollectionMapConvert<ColumnPoolObj> convert = new CollectionMapConvert<ColumnPoolObj>();
			Map<String, ColumnPoolObj> map = convert.convertListToMap(columnpoolList, "id");
			for (ColumnConfigPoolObj pool : columncfgpoolList) {
				ColumnPoolObj colpool = map.get(pool.getColumnId());
				if (pool.getIsShown() != null && Const.TAG_ENABLE.equals(pool.getIsShown())) {
					buffer.append(colpool.getName() + ",");
				}
			}
			SynthesisColumnGenService synthesisColumnGenService = (SynthesisColumnGenService) getBean("synthesisColumnGenService");
			Map<String, String> tableMap = getQueryParam(queryColumnlist, theForm, tableId, synthesisColumnGenService);

			Session session = (Session) request.getSession().getAttribute(Const.SESSION);
			if (session.getSpaceId() != null && !"".equals(session.getSpaceId())) {
				// 临时表插入个人空间
				ResourceTable table = new ResourceTable();
				String desc = request.getParameter("desc");
				if (desc == null || "".equals(desc)) table.setRemark(desc);
				table.setName(ename);
				table.setCreateBy(session.getAccountName());
				table.setIsSpaceTable(Const.IS_SPACCETABLE);
				table.setRefTableId(Integer.valueOf(tableId));
				table.setType(Const.RESTAB_TYPE_DB);
				table.setCnName(cname);
				table.setSpaceId(Integer.valueOf(session.getSpaceId()));
				table.setCreateDate(new Date(System.currentTimeMillis()));
				resourceTableService.createResourceTable(table);
				// 开始后台写入查询结果
				ResourceColumnService resourceColumnService = (ResourceColumnService) getBean("resourceColumnService");
				ResourceColumn pkobj = resourceColumnService.findResoucrePKColumn(Integer.valueOf(tableId));
				if (ProcessGenTableAndRecord(columnpoolList, ename, pkobj, tableMap, session, table,theForm)) {
					// 建表跟插数据都成功
					processOk = true;
					retStr = "OK";
				}
				else {
					resourceTableService.removeResourceTable(table.getId());
					processOk = false;
					retStr = "REQUIRED";
				}
				//				
				// QueryResultTableSaveThread thread = new
				// QueryResultTableSaveThread(tableMap, ename, columnpoolList,
				// pkobj, session);
				// ThreadPool.exec(thread);
			}
			else {
				processOk = false;
				retStr = "REQUIRED";
			}

		}
		catch (Exception e) {
			e.printStackTrace();
			log.error(e);
			retStr = "ERR";
			processOk = false;
		}
		response.setContentType("text/plain;charset=UTF-8");
		response.setHeader("Cache_Control", "no-cache");
		response.getWriter().write(retStr);
		// request.setAttribute("msg", retStr);
		// forward=mapping.findForward("commmsg");
		return forward;
	}

	public ActionForward queryTotalCountAjax(ActionMapping mapping, CommSubjectQueryForm theForm, HttpServletRequest request, HttpServletResponse response) throws Exception {
		if (log.isDebugEnabled()) log.debug("Entering 'queryTotalCountAjax' method");
		try {
			String queryTableId = theForm.getTableId();
			List<Map<String, Object>> queryColumnlist = new ArrayList<Map<String, Object>>();
			SynthesisColumnGenService synthesisColumnGenService = (SynthesisColumnGenService) getBean("synthesisColumnGenService");
			Map<String, String> tableMap = getQueryParam(queryColumnlist, theForm, queryTableId, synthesisColumnGenService);
			String sql = "select count(*) from " + tableMap.get("sql");// sqlscriptList.get(i);
			SynthesisQueryService synthesisQueryService = (SynthesisQueryService) getBean("synthesisQueryService");
			int count = synthesisQueryService.queryByInt(sql);
			response.setContentType("text/plain;charset=UTF-8");
			response.setHeader("Cache_Control", "no-cache");
			response.getWriter().write(String.valueOf(count));
			response.getWriter().close();

		}
		catch (Exception e) {
			e.printStackTrace();
			log.error(e);
		}
		return null;
	}

	public ActionForward showParamSubject(ActionMapping mapping, CommSubjectQueryForm theForm, HttpServletRequest request, HttpServletResponse response) throws Exception {
		if (log.isDebugEnabled()) log.debug("Entering 'searchSubject' method");
		ActionForward forward = null;
		try {
			SynthesisQueryService sqs = (SynthesisQueryService) getBean("synthesisQueryService");
			Map specialTables = sqs.getSpecialTables();

			String cataId = theForm.getCataId();
			if (cataId != null) {// 分类主题
				QuerySubjectService querySubjectService = (QuerySubjectService) getBean("querySubjectService");
				QuerySubject subject = querySubjectService.findSubjectByCatagoryId(Integer.valueOf(cataId));
				if (subject == null) {
					request.setAttribute("errMsg", "该分类主题还未建立，请联系管理员");
					return mapping.findForward("error");
				}
				theForm.setId(subject.getId().toString());
			}
			List<Map<String, Object>> queryColumnlist = new ArrayList<Map<String, Object>>();
			QueryMetaIndPool queryMetaIndPool = (QueryMetaIndPool) getBean("queryMetaIndPool");
			SubjectConfigPool subjectConfigPool = (SubjectConfigPool) getBean("subjectConfigPool");

			SubjectConfigPoolObj subjectPoolObj = subjectConfigPool.getSubjectConfigPool(theForm.getId());
			List<SubjectColumnConfigPoolObj> paramPoolObj = subjectConfigPool.getSubjectColumnConfigPool(theForm.getId(),false);
			// 查询字段列表
			// StringBuffer codeArrBuffer=new StringBuffer();
			SynthesisColumnGenService synthesisColumnGenService = (SynthesisColumnGenService) getBean("synthesisColumnGenService");
			for (SubjectColumnConfigPoolObj pool : paramPoolObj) {
				Map<String, String> indMap = queryMetaIndPool.getIndicatorPool(pool.getIndId());
				String indId = pool.getIndId();
				if (indMap != null) {
					Map<String, Object> map = new HashMap<String, Object>();
					map.put("columnName", pool.getName());
					map.put("indId", indId);
					map.put("oper", pool.getFilterOperator());
					map.put("displayName", pool.getCnName());
					map.put("dataType", pool.getDataType());
					queryColumnlist.add(map);
				}
			}
			request.setAttribute("queryParamList", queryColumnlist);
			request.setAttribute("tableList", subjectPoolObj.getTableMapList());
			request.setAttribute("htmlcode", genenrateHtmlCode(subjectPoolObj, paramPoolObj, theForm, synthesisColumnGenService));
			if (subjectPoolObj.getSubType().equals(QuerySubject.SUBTYPE_COMM)) {
				request.setAttribute("seltablestr", genenrateSelTableHtmlCode(theForm, subjectPoolObj.getTableMapList()));
				request.setAttribute("commName", subjectPoolObj.getSubCode().equals("GZCH") ? true : false);
				//为电信主题日期设置初始值
				if (subjectPoolObj.getSubCode().equals("GZCH")) {
					String telNoType = theForm.getRecord().get("telNoType");
					if (telNoType!=null) {
						theForm.getRecord().put("telNoType", telNoType);
					}else {
						theForm.getRecord().put("telNoType", "1");
					}
					theForm.getRecord().put("telSDate", DateUtil.getDateTime("yyyy", new Date(System.currentTimeMillis())) + "-01-01");
					theForm.getRecord().put("telEDate", DateUtil.getDateTime("yyyy-MM-dd", new Date(System.currentTimeMillis())));
				}
			}
			else {// 分类主题，只查找属于该分类的一级表
				request.setAttribute("queryParamList", queryColumnlist);
				ResourceTableService resourceTableService = (ResourceTableService) getBean("resourceTableService");
				List<ResourceTable> list1 = resourceTableService.findTableByCatageoryId(subjectPoolObj.getScId());
				List<ResourceTable> list = new ArrayList<ResourceTable>();
				for (ResourceTable rt : list1) {
					// 特殊的电信记录不显示
					if (specialTables.get(rt.getName()) == null) list.add(rt);
				}
				request.setAttribute("seltablestr", genenrateClassifySelTableHtmlCode(theForm, list));
			}
			initForm(theForm, "SHOW", request);
			forward = mapping.findForward("SHOWQUERY");
		}
		catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("err", e);
			request.setAttribute("errMsg", e.getMessage());
			forward = mapping.findForward(ERROR);
		}
		return forward;
	}

	public ActionForward queryRecordList(ActionMapping mapping, CommSubjectQueryForm theForm, HttpServletRequest request, HttpServletResponse response) throws Exception {
		if (log.isDebugEnabled()) log.debug("Entering 'searchSubject' method");
		ActionForward forward = null;

		try {
			SynthesisQueryService synthesisQueryService = (SynthesisQueryService) getBean("synthesisQueryService");
			TableConfigPool tableConfigPool = (TableConfigPool) getBean("tableConfigPool");
			BussCodeSetPool bussCodeSetPool = (BussCodeSetPool) getBean("bussCodeSetPool");
			List<ColumnPoolObj> columnpoolList = tableConfigPool.getTableColumnPool(theForm.getTableId());
			List<ColumnConfigPoolObj> columncfgpoolList = tableConfigPool.getColumnConfigPool(theForm.getTableId());
			StringBuffer buffer = new StringBuffer();
			CollectionMapConvert<ColumnPoolObj> convert = new CollectionMapConvert<ColumnPoolObj>();
			Map<String, ColumnPoolObj> map = convert.convertListToMap(columnpoolList, "id");
			StringBuffer dnamebuffer = new StringBuffer();
			StringBuffer alignbuffer = new StringBuffer();
			StringBuffer typebuffer = new StringBuffer();
			List<String> colnameList = new ArrayList<String>();

			// 判断用户是否有权限访问该表
			String tableLevel = tableConfigPool.getTableMap(theForm.getTableId()).get("securityLevel");
			if (!synthesisQueryService.checkUserTableSecurityLevel(tableLevel, getCurrentUser(request))) {
				request.setAttribute("msg", "该表的安全等级为:【" + tableLevel + "】，您的权限不够！");
				return mapping.findForward("BLANK");
			}

			// grid2 Columns配置
			List<Column> columnList = new ArrayList<Column>();
			// checkbox
			Column checkcol = new Column();
			checkcol.setWidth("35px");
			checkcol.setAlign("center");
			checkcol.setItemType("checkbox");
			checkcol.setProperty("id");
			columnList.add(checkcol);
			// 主键字段
			List<String> displayList = new ArrayList<String>();
			ResourceColumnService resourceColumnService = (ResourceColumnService) getBean("resourceColumnService");
			ResourceColumn pkobj = resourceColumnService.findResoucrePKColumn(Integer.valueOf(theForm.getTableId()));
			SynthesisColumnGenService synthesisColumnGenService = (SynthesisColumnGenService) getBean("synthesisColumnGenService");
			for (ColumnConfigPoolObj pool : columncfgpoolList) {
				ColumnPoolObj colpool = map.get(pool.getColumnId());
				if (pool.getIsSubject() != null && "1".equals(pool.getIsSubject())) {
					if (colpool.getId().equals(pkobj.getId().toString())) buffer.append(colpool.getName() + " as id,");
					else buffer.append(colpool.getName() + ",");
					colnameList.add(colpool.getName());
					dnamebuffer.append(colpool.getCnName() + ",");
					alignbuffer.append("center,");
					displayList.add(colpool.getName());
					typebuffer.append(synthesisColumnGenService.getDisplayDataType(colpool) + ",");

					// grid2配置
					Column col = new Column();
					// col.setWidth("15%");
					col.setAlign("center");
					col.setName(colpool.getCnName());
					col.setProperty(colpool.getName());
					if (pool.getIsSortable() != null && pool.getIsSortable().equals("1")) {
						col.setHeaderOnMouseOut("headerOut(this)");
						col.setHeaderOnClick("queryByColumn('" + colpool.getName() + "')");
						col.setHeaderOnMouseOver("headerOver(this)");
					}
					if (columnList.size() == 1) {
						col.setItemType("hyperlink");
						col.setHref("javascript:void(0)");
						col.setOnClick("showdetail('{id}')");
					}
					columnList.add(col);
				}

			}
			String colstr = "";
			String cnname = "";
			// 显示字段是否包括主键,未包括就加入最前面
			if (pkobj == null) {
				request.setAttribute("msg", "未能找到该表的主键字段，该表未查询。");
				return mapping.findForward("BLANK");
			}
			else if (displayList.contains(pkobj.getName())) {
				if (buffer.length() > 0) colstr = buffer.toString().substring(0, buffer.length() - 1);
				if (dnamebuffer.length() > 0) cnname = dnamebuffer.substring(0, dnamebuffer.length() - 1);
			}
			else {
				if (buffer.length() > 0) colstr = pkobj.getName() + " as id," + buffer.toString().substring(0, buffer.length() - 1);
				else colstr = pkobj.getName() + " as id";
				if (buffer.length() > 0) cnname = pkobj.getCnName() + "," + dnamebuffer.substring(0, dnamebuffer.length() - 1);
				else cnname = pkobj.getCnName();
				colnameList.add(pkobj.getName());
			}
			List<Map<String, Object>> queryColumnlist = new ArrayList<Map<String, Object>>();
			String queryStr = getQueryParam(queryColumnlist, theForm, theForm.getTableId(), synthesisColumnGenService).get("sql");
			if (queryStr == null) {
				request.setAttribute("msg", "未能找到关联的数据指标，该表未查询。");
				return mapping.findForward("BLANK");
			}
			String tableName = queryStr.substring(0, queryStr.indexOf("where")).trim();

			SynthesisQueryService sqs = (SynthesisQueryService) getBean("synthesisQueryService");
			Map specialTables = sqs.getSpecialTables();

			if (specialTables.get(tableName) != null) {
				request.setAttribute("isSpTable", "true");
				colstr = changeFields(colstr);
			}

			String sql = "select " + colstr + " from " + queryStr;//
			PageQuery pageQuery = theForm.getQuery();
			List<Map<String, String>> list = synthesisQueryService.queryBySql(sql, pageQuery).getRecordSet();
			setPage(theForm.getQuery());
			// 取出该页id集合
			StringBuffer idArr = new StringBuffer();
			for (Map<String, String> idmap : list) {
				idArr.append(idmap.get("id") + ",");
			}
			request.setAttribute("idArr", idArr.length() > 0 ? idArr.substring(0, idArr.length() - 1) : "");
			// theForm.setIdArr(idArr.substring(0,idArr.length()-1));

			// 过滤代码集
			bussCodeSetPool.filterBussCodeSet(list, columnpoolList);
			// 过滤安全等级字段
			Session currentUser = (Session) request.getSession().getAttribute(Const.SESSION);
			synthesisColumnGenService.filterSecurityLevel(theForm.getTableId(), list, columnpoolList, pkobj, currentUser);
			if (specialTables.get(tableName) != null) {
				changeList(list);
			}

			theForm.getQuery().setRecordSet(list);
			// 设置grid2的column配置
			request.setAttribute(Column.DYNAMICCOLUMNS, columnList);
			initForm(theForm, "SHOWLIST", request);

			forward = mapping.findForward("QUERYLIST");

		}
		catch (Exception e) {
			e.printStackTrace();
			this.addMessage(theForm, e.getMessage());
			forward = mapping.findForward(ERROR);
		}
		return forward;
	}

	private void changeList(List<Map<String, String>> list) {
		for (Map<String, String> map : list) {
			int len = Integer.parseInt(map.get("initiator_len"));
			String i2 = "";
			String i1;
			StringBuffer sb;
			if (len > 9) {
				try {
					sb = new StringBuffer(map.get("initiator2").substring(0, len - 9));
					i2 = sb.reverse().toString();
				}
				catch (Exception e) {
					i2 = "";
				}
				sb = new StringBuffer(map.get("initiator1"));
				i1 = sb.reverse().toString();
			}
			else {
				i1 = reverseAndFill(map.get("initiator1"), len);
			}
			map.remove("ZJHM");
			map.put("ZJHM", i2 + i1);

			len = Integer.parseInt(map.get("passive_len"));
			if (len > 9) {
				try {
					sb = new StringBuffer(map.get("passive2").substring(0, len - 9));
					i2 = sb.reverse().toString();
				}
				catch (Exception e) {
					i2 = "";
				}
				sb = new StringBuffer(map.get("passive1"));
				i1 = sb.reverse().toString();
			}
			else {
				i1 = reverseAndFill(map.get("passive1"), len);
			}
			map.remove("BJHM");
			map.put("BJHM", i2 + i1);
		}
	}

	private String reverseAndFill(String str, int len) {
		StringBuffer sb = new StringBuffer(str);
		String s = sb.reverse().toString();
		int fi = len > 9 ? 9 : len;
		s += "0000000000";
		return s.substring(0, fi);
	}

	private String changeFields(String str) {
		String s = str;
		if (str.indexOf("ZJHM") >= 0) {
			s = s.replaceAll("ZJHM", "initiator1,initiator2,initiator_len");
		}
		if (str.indexOf("BJHM") >= 0) {
			s = s.replaceAll("BJHM", "passive1,passive2,passive_len");
		}
		return s;
	}

	public ActionForward queryDetail(ActionMapping mapping, CommSubjectQueryForm theForm, HttpServletRequest request, HttpServletResponse response) throws Exception {
		if (log.isDebugEnabled()) log.debug("Entering 'searchSubject' method");
		ActionForward forward = null;

		try {
			SynthesisQueryService synthesisQueryService = (SynthesisQueryService) getBean("synthesisQueryService");
			TableConfigPool tableConfigPool = (TableConfigPool) getBean("tableConfigPool");
			ResourceTableService resourceTableService = (ResourceTableService) getBean("resourceTableService");
			BussCodeSetPool bussCodeSetPool = (BussCodeSetPool) getBean("bussCodeSetPool");
			List<ColumnPoolObj> columnpoolList = tableConfigPool.getTableColumnPool(theForm.getTableId());
			List<ColumnConfigPoolObj> columncfgpoolList = tableConfigPool.getColumnConfigPool(theForm.getTableId());
			StringBuffer buffer = new StringBuffer();
			CollectionMapConvert<ColumnPoolObj> convert = new CollectionMapConvert<ColumnPoolObj>();
			Map<String, ColumnPoolObj> map = convert.convertListToMap(columnpoolList, "id");
			StringBuffer dnamebuffer = new StringBuffer();
			String uid = theForm.getUid();
			// 上一页下一页
			String idArr = theForm.getIdArr();
			String dir = request.getParameter("dir");
			int pos = 0;
			String hasprev = "false";
			String hasnext = "false";
			if (!"".equals(idArr)) {
				String[] idArray = idArr.split(",");
				pos = getUidPos(idArray, uid);
				if (dir != null && !"".equals(dir)) {
					if ("+".equals(dir)) {
						if (pos < idArray.length - 1) {
							pos++;
							uid = idArray[pos];
						}
					}
					else if ("-".equals(dir)) {
						if (pos != 0) {
							pos--;
							uid = idArray[pos];
						}
					}
					theForm.setUid(uid);
				}
				if (pos == 0) {
					hasnext = "true";
					hasprev = "false";
				}
				else if (pos == idArray.length - 1) {
					hasprev = "true";
					hasnext = "false";
				}
				else {
					hasprev = "true";
					hasnext = "true";
				}
			}

			request.setAttribute("hasnext", hasnext);
			request.setAttribute("hasprev", hasprev);

			List<ColumnPoolObj> displayColList = new ArrayList<ColumnPoolObj>();
			for (ColumnConfigPoolObj pool : columncfgpoolList) {
				ColumnPoolObj colpool = map.get(pool.getColumnId());
				if (pool.getIsShown() != null && "1".equals(pool.getIsShown())) {
					if (colpool != null) {
						buffer.append(colpool.getName() + ",");
						dnamebuffer.append(colpool.getCnName() + ",");
						colpool.setName(colpool.getName().toUpperCase());
						displayColList.add(colpool);
					}
				}

			}
			// 生成sql语句
			ResourceTable table = resourceTableService.findResourceTable(Integer.valueOf(theForm.getTableId()));
			String tableName = table.getName();
			SynthesisQueryService sqs = (SynthesisQueryService) getBean("synthesisQueryService");
			Map specialTables = sqs.getSpecialTables();
			String colstr = buffer.toString().substring(0, buffer.length() - 1);
			if (specialTables.get(tableName) != null) {
				colstr = changeFields(colstr);
			}
			String cnname = dnamebuffer.substring(0, dnamebuffer.length() - 1);
			ResourceColumnService resourceColumnService = (ResourceColumnService) getBean("resourceColumnService");
			ResourceColumn pkobj = resourceColumnService.findResoucrePKColumn(Integer.valueOf(theForm.getTableId()));
			String paramSql = pkobj.getName() + "=";
			if (pkobj.getDataType().equals(Const.META_TYPE_NUMERIC)) paramSql += uid;
			else paramSql += "'" + uid + "'";
			String sql = "select " + colstr + " from " + table.getName() + " where " + paramSql;
			PageQuery pageQuery = theForm.getQuery();
			List<Map<String, String>> list = synthesisQueryService.queryBySql(sql, pageQuery).getRecordSet();
			// 过滤代码集
			bussCodeSetPool.filterBussCodeSetForDetail(list, columnpoolList);
			// 过滤安全等级字段
			Session currentUser = (Session) request.getSession().getAttribute(Const.SESSION);
			SynthesisColumnGenService synthesisColumnGenService = (SynthesisColumnGenService) getBean("synthesisColumnGenService");
			synthesisColumnGenService.filterSecurityLevel(table.getId().toString(), list, columnpoolList, pkobj, currentUser);
			theForm.getQuery().setRecordSet(list);
			// String CONTEXT_PATH =
			// request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";
			// String url = CONTEXT_PATH+"pics/"+table.getName()+"/";
			if (specialTables.get(tableName) != null) {
				changeList(list);
			}
			request.setAttribute("htmlcode", synthesisColumnGenService.genenrateResultHtmlCode(displayColList, list.get(0), request));
			request.setAttribute("tableName", table.getName());

			forward = mapping.findForward("QUERYDETAIL");

		}
		catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("err", e);
			forward = mapping.findForward(ERROR);
		}
		return forward;
	}

	// 装载保存的查询条件
	/*
	public ActionForward loadQueryParam(ActionMapping mapping, CommSubjectQueryForm theForm, HttpServletRequest request, HttpServletResponse response) throws Exception {
		if (log.isDebugEnabled()) log.debug("Entering 'loadQueryParam' method");
		ActionForward forward = null;
		try {
			String condId = theForm.getCondId();
			//PersonalQueryConditionService personalQueryConditionService = (PersonalQueryConditionService) getBean("personalQueryConditionService");
			//PersonalQueryCondition cond = personalQueryConditionService.findPersonalQueryCondition(Integer.valueOf(condId));
			theForm.setQueryName(cond.getQueryName());
			String xmlstr = cond.getCondtionsXml();
			QUERYPARAMDocument doc = QUERYPARAMDocument.Factory.parse(xmlstr);
			QUERYPARAMType qparams = doc.getQUERYPARAM();
			COMMBATCHQUERYType batchtype = qparams.getCOMMBATCHQUERY();
			String seltabs = batchtype.getSELTABS();
			if (seltabs != null && !"".equals(seltabs)) {
				String[] selTabArr = seltabs.split(",");
				theForm.setSelTable(selTabArr);
			}
			PARAMSType params = batchtype.getPARAMS();
			PARAMType param = params.getPARAM();
			COLSType[] colsArr = param.getCOLSArray();
			if (colsArr.length > 0) {
				COLSType cols = colsArr[0];
				COLType[] colArr = cols.getCOLArray();
				for (COLType type : colArr) {
					String colName = type.getId();
					String value = type.getStringValue();
					theForm.getRecord().put(colName, value);
				}
			}
			forward = showParamSubject(mapping, theForm, request, response);
		}
		catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("msg", "保存查询条件有误");
			forward = mapping.findForward("SHOWQUERY");
		}
		return forward;
	}
*/
	private ActionForward getTreeNode(ActionMapping mapping, CommSubjectQueryForm theForm, HttpServletRequest request, HttpServletResponse response) throws Exception {
		if (log.isDebugEnabled()) log.debug("Entering 'getTree' method");
		String id = theForm.getId();

		response.setContentType("text/xml;charset=UTF-8");
		response.setHeader("Cache_Control", "no-cache");
		String tree = request.getParameter("tree");

		String retstr = "";
		try {
			if (id != null && !"".equals(id.trim())) {

				if (id.equals("0")) {
					if ("ALL".equalsIgnoreCase(tree)) retstr = getTopNodes(true);
					else retstr = getTopNodes(false);
				}
				else if (id.startsWith("buss")) {
					id = id.substring(4, id.length());
					retstr = getBussinessRange(id);
				}
				else if (id.equals("this_is_alltables")) {
					retstr = getAllTableNode(request);
				}
				else if (id.equals("spacetable")) {
					retstr = getSpaceTableNode(request);
				}
				else retstr = DhtmlTreeUtil.genBlankXmlTree(id);
				response.getWriter().write(retstr);
			}
			else {
				if ("ALL".equalsIgnoreCase(tree)) retstr = getTopNodes(true);
				else retstr = getTopNodes(false);
				response.getWriter().write(retstr);
			}
			response.getWriter().close();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 生成查询主题根列表
	 * 
	 * @return
	 */
	private String getTopNodes(boolean showCategory) {
		StringBuffer sb = new StringBuffer("");
		try {

			QuerySubjectService querySubjectService = (QuerySubjectService) getBean("querySubjectService");
			// ResourceTableService resourceTableService =
			// (ResourceTableService)getBean("resourceTableService");
			List<QuerySubject> commList = querySubjectService.findCommSubject();
			// String spaceId = getCurrentUser(request).getSpaceId();
			// if(spaceId==null)spaceId="";

			sb.append("<?xml version='1.0' encoding='UTF-8' ?>");
			sb.append("<tree id=\"0\">");
			sb.append("<item id=\"this_is_commons\" text=\"主题\" im0=\"/icon/classify.gif\" im1=\"/icon/classify.gif\" im2=\"/icon/classify.gif\"").append(" child=\"1\">");
			sb.append("<userdata name=\"name\">" + "主题" + "</userdata>");
			if (commList != null) {
				for (int i = 0; i < commList.size(); i++) {
					String idstr = "";
					String id = "";
					String haschild = "1";
					String imgPath = "/icon/classify.gif";
					QuerySubject subject = commList.get(i);
					String subjectName = subject.getSubName();
					id = subject.getId().toString();
					if (subject.getScId() != null) idstr = "buss" + subject.getScId().toString();
					else {
						idstr = "comm" + subject.getId().toString();
						haschild = "0";
						imgPath = "/icon/subject.gif";
					}
					// sb.append("<item id=\""+idstr+"\" text=\""+subjectName+"\"
					// im0=\"/icon/code_all.gif\" im1=\"/icon/code_all.gif\"
					// im2=\"/icon/code_all.gif\" child=\""+haschild+"\">");
					sb.append("<item id=\"").append(idstr).append("\" text=\"" + subjectName).append("\" im0=\"" + imgPath + "\" im1=\"" + imgPath + "\" im2=\"" + imgPath + "\"").append(
							" child=\"" + haschild + "\">");
					sb.append("<userdata name=\"name\">" + subjectName + "</userdata>");
					sb.append("</item>");
				}
			}
			sb.append("</item>");
			
			if (showCategory) {
				// 一层分类
				sb.append("<item id=\"this_is_categorys\" text=\"分类\" im0=\"/icon/classify.gif\" im1=\"/icon/classify.gif\" im2=\"/icon/classify.gif\"").append(" child=\"1\">");
				sb.append("<userdata name=\"name\">" + "分类" + "</userdata>");
				
				StandardCategoryService standardCategoryService = (StandardCategoryService) getBean("standardCategoryService");
				List<StandardCategory> list = standardCategoryService.findCategoryByParentId("0");
				if (list != null && !list.isEmpty()) {
					for (int i = 0; i < list.size(); i++) {
						StandardCategory cata = list.get(i);
						String idstr = "buss" + cata.getId().toString();
						String subjectName = cata.getName();
						sb.append("<item id=\"").append(idstr).append("\" text=\"" + subjectName).append("\" im0=\"/icon/classify.gif\" im1=\"/icon/classify.gif\" im2=\"/icon/classify.gif\"").append(
								" child=\"1\">");
						sb.append("<userdata name=\"name\">" + subjectName + "</userdata>");
						sb.append("</item>");
					}
				}
				
				sb.append("</item>");
				
				sb.append("<item id=\"this_is_alltables\" text=\"所有表\" im0=\"/icon/classify.gif\" im1=\"/icon/classify.gif\" im2=\"/icon/classify.gif\"").append(" child=\"1\">");
				sb.append("<userdata name=\"name\">" + "所有表" + "</userdata>");
				sb.append("</item>");
				
				//sb.append("<item id=\"").append("spacetable").append("\" text=\"" + "个人空间表").append("\" im0=\"/icon/classify.gif\" im1=\"/icon/classify.gif\" im2=\"/icon/classify.gif\"").append(
				//		" child=\"1\">");
				//sb.append("<userdata name=\"name\">" + "个人空间表" + "</userdata>");
				//sb.append("</item>");
				// sb.append("<item id=\"personal\" text=\"个人空间\"
				// im0=\"/icon/code_all.gif\" im1=\"/icon/code_all.gif\"
				// im2=\"/icon/code_all.gif\" child=\"1\">");
				// sb.append("<userdata name=\"name\">个人空间</userdata></item>");
			}
			sb.append("</tree>");
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return sb.toString();
	}

	private String getSpaceTableNode(HttpServletRequest request) {
		try {
			StringBuffer sb = new StringBuffer();
			sb.append("<?xml version='1.0' encoding='UTF-8' ?>");
			sb.append("<tree id=\"spacetable\">");
			ResourceTableService resourceTableService = (ResourceTableService) getBean("resourceTableService");
			String spaceId = getCurrentUser(request).getSpaceId();
			if (spaceId == null) spaceId = "";
			if (!"".equals(spaceId)) {
				List<ResourceTable> tabList = resourceTableService.findByField(ResourceTable.PROP_SPACEID, new Integer(spaceId));
				if (tabList == null) {
					tabList = new ArrayList<ResourceTable>();
				}
				for (int i = 0; i < tabList.size(); i++) {
					ResourceTable table = tabList.get(i);
					sb.append("<item id=\"").append("space_" + table.getId().toString()).append("\" text=\"" + table.getCnName()).append(
							"\" im0=\"/icon/table.gif\" im1=\"/icon/table.gif\" im2=\"/icon/table.gif\"").append(" child=\"0\">");
					sb.append("<userdata name=\"name\">" + table.getCnName() + "</userdata>");
					sb.append("</item>");
				}
			}
			sb.append("</tree>");
			return sb.toString();
		}
		catch (Exception e) {
			throw new WebException();
		}
	}
	
	private String getAllTableNode(HttpServletRequest request) {
		try {
			StringBuffer sb = new StringBuffer();
			sb.append("<?xml version='1.0' encoding='UTF-8' ?>");
			sb.append("<tree id=\"this_is_alltables\">");
			ResourceTableService resourceTableService = (ResourceTableService) getBean("resourceTableService");
			
			String cityCode = getCurrentUser(request).getCityCode();
			if (cityCode == null) cityCode = "";
			if (!"".equals(cityCode)) {
				List<ResourceTable> tabList = resourceTableService.findByField(ResourceTable.PROP_CITY_CODE, cityCode);
				if (tabList == null) {
					tabList = new ArrayList<ResourceTable>();
				}
				for (int i = 0; i < tabList.size(); i++) {
					ResourceTable table = tabList.get(i);
					sb.append("<item id=\"").append("all_"+table.getId().toString()).append("\" text=\"" + table.getCnName()).append(
							"\" im0=\"/icon/table.gif\" im1=\"/icon/table.gif\" im2=\"/icon/table.gif\"").append(" child=\"0\">");
					sb.append("<userdata name=\"name\">" + table.getCnName() + "</userdata>");
					sb.append("</item>");
				}
			}
			sb.append("</tree>");
			return sb.toString();
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new WebException();
		}
	}

	public String getCommNodeElements() {
		String retStr = "";
		try {
			QuerySubjectService querySubjectService = (QuerySubjectService) getBean("querySubjectService");
			List<QuerySubject> commList = querySubjectService.findCommSubject();
			CollectionMapConvert<QuerySubject> convert = new CollectionMapConvert<QuerySubject>();
			List<Map<String, String>> listmap = convert.getListMapFromListVO(commList);
			String[] nameArr = new String[] { "subName" };
			DhtmlTreeParam param = new DhtmlTreeParam("id", "subName", "", nameArr, nameArr, "/icon/code_child.gif");
			retStr = DhtmlTreeUtil.genXmlTreeByList(listmap, param, "comm", true);
		}
		catch (Exception e) {
			e.printStackTrace();
			log.error(e);
		}
		return retStr;
	}

	private String getBussinessRange(String parentId) throws WebException {
		String retStr = "";
		StringBuffer sb = new StringBuffer();
		try {
			if (parentId == null) parentId = "0";

			SynthesisQueryService sqs = (SynthesisQueryService) getBean("synthesisQueryService");
			Map specialTables = sqs.getSpecialTables();

			StandardCategoryService standardCategoryService = (StandardCategoryService) getBean("standardCategoryService");
			List<StandardCategory> list = standardCategoryService.findCategoryByParentId(parentId);
			if (list != null && !list.isEmpty()) {
				// 有下级分类
				// 加上属于自己的表
				ResourceTableService resourceTableService = (ResourceTableService) getBean("resourceTableService");
				List<ResourceTable> tabList = resourceTableService.findTableByCatageoryId(parentId);
				sb.append("<?xml version='1.0' encoding='UTF-8' ?>");
				sb.append("<tree id=\"buss" + parentId + "\">");
				for (int i = 0; i < list.size(); i++) {
					String idstr = "";
					String id = "";
					StandardCategory cata = list.get(i);
					String subjectName = cata.getName();
					id = cata.getId().toString();
					idstr = "buss" + cata.getId().toString();
					// sb.append("<item id=\""+idstr+"\" text=\""+subjectName+"\"
					// im0=\"/icon/code_all.gif\" im1=\"/icon/code_all.gif\"
					// im2=\"/icon/code_all.gif\" child=\"1\">");
					sb.append("<item id=\"").append(idstr).append("\" text=\"" + subjectName).append("\" im0=\"/icon/classify.gif\" im1=\"/icon/classify.gif\" im2=\"/icon/classify.gif\"").append(
							" child=\"1\">");
					sb.append("<userdata name=\"name\">" + subjectName + "</userdata>");
					sb.append("</item>");
				}
				if (tabList != null && tabList.size() > 0) {
					for (int i = 0; i < tabList.size(); i++) {
						ResourceTable table = tabList.get(i);
						// if (specialTables.get(table.getName()) != null)
						// continue;//特殊的电信记录不显示
						// sb.append("<item id=\""+table.getId().toString()+"\"
						// text=\""+table.getCnName()+"\" im0=\"/icon/code_all.gif\"
						// im1=\"/icon/code_all.gif\" im2=\"/icon/code_all.gif\"
						// child=\"0\">");

						sb.append("<item id=\"").append(table.getId().toString()).append("\" text=\"" + table.getCnName()).append(
								"\" im0=\"/icon/table.gif\" im1=\"/icon/table.gif\" im2=\"/icon/table.gif\"").append(" child=\"0\">");
						sb.append("<userdata name=\"name\">" + table.getCnName() + "</userdata>");
						sb.append("</item>");
					}
				}
				sb.append("</tree>");
				retStr = sb.toString();
			}
			else {
				// 没有下级分类，显示所有表
				ResourceTableService resourceTableService = (ResourceTableService) getBean("resourceTableService");

				List<ResourceTable> tabList1 = resourceTableService.findTableByCatageoryId(parentId);
				List<ResourceTable> tabList = new ArrayList<ResourceTable>();
				for (ResourceTable rt : tabList1) {
					// 特殊的电信记录不显示
					if (specialTables.get(rt.getName()) == null) tabList.add(rt);
				}
				CollectionMapConvert<ResourceTable> convert1 = new CollectionMapConvert<ResourceTable>();
				List<Map<String, String>> tabMapList = convert1.getListMapFromListVO(tabList);
				String[] nameArr = new String[] { "name" };
				String[] nameArr1 = new String[] { "cnName" };
				DhtmlTreeParam param1 = new DhtmlTreeParam("id", "cnName", "", nameArr1, nameArr, "/icon/table.gif");
				retStr = DhtmlTreeUtil.genXmlTreeByList(tabMapList, param1, "buss" + parentId, false);
			}
		}
		catch (Exception e) {
			throw new WebException(e);
		}
		return retStr;
	}

	private String getQueryString(PageQuery pageQuery) {
		StringBuffer buffer = new StringBuffer();
		Map fields = pageQuery.getParameters();
		String str = (String) fields.get(QuerySubject.PROP_SUB_NAME);
		if (str != null && !"".equals(str)) {
			if (str.contains("%")) buffer.append(" and " + QuerySubject.COL_SUB_NAME + " like '" + str + "'");
			else buffer.append(" and " + QuerySubject.COL_SUB_NAME + " like '%" + str + "%'");
		}

		return buffer.toString();
	}

	private void initForm(CommSubjectQueryForm theForm, String action, HttpServletRequest request) throws Exception {
		if (action.equalsIgnoreCase("SHOWOVERVIEW") || action.equalsIgnoreCase("SHOWLIST")) {
			// 查询条件转为hidden
			Map<String, String> map = theForm.getRecord();
			Iterator<String> iter = map.keySet().iterator();
			List<Map<String, String>> list = new ArrayList<Map<String, String>>();
			while (iter.hasNext()) {
				String key = iter.next();
				String value = map.get(key);
				Map<String, String> listmap = new HashMap<String, String>();
				listmap.put("key", "record(" + key + ")");
				listmap.put("value", value);
				list.add(listmap);
			}
			request.setAttribute("paramList", list);
			// 选中table的hidden
			String[] tabArr = theForm.getSelTable();
			List<Map<String, String>> tablist = new ArrayList<Map<String, String>>();
			for (int i = 0; i < tabArr.length; i++) {
				Map<String, String> listmap = new HashMap<String, String>();
				listmap.put("id", tabArr[i]);
				tablist.add(listmap);
			}
			request.setAttribute("selTabList", tablist);
		}
	}

	/**
	 * 按照主题和对应表生成查询条件
	 * 
	 * @param queryColumnlist
	 * @param theForm
	 * @param tabId
	 * @return
	 * @throws Exception
	 */
	private Map<String, String> getQueryParam(List<Map<String, Object>> queryColumnlist, CommSubjectQueryForm theForm, String tabId, SynthesisColumnGenService synthesisColumnGenService)
			throws Exception {
		QueryMetaIndPool queryMetaIndPool = (QueryMetaIndPool) getBean("queryMetaIndPool");
		SubjectConfigPool subjectConfigPool = (SubjectConfigPool) getBean("subjectConfigPool");
		TableConfigPool tableConfigPool = (TableConfigPool) getBean("tableConfigPool");
		//
		BaseSqlGen sqlGen = (BaseSqlGen) getBean("sybaseSqlGen");
		List<SubjectColumnConfigPoolObj> paramPoolObj = subjectConfigPool.getSubjectColumnConfigPool(theForm.getId(),false);

		SynthesisQueryService sqs = (SynthesisQueryService) getBean("synthesisQueryService");
		Map specialTables = sqs.getSpecialTables();

		// 单个表查询语句
		StringBuffer buffer = new StringBuffer();
		// 字段缓存对象
		List<ColumnPoolObj> columnpoolList = tableConfigPool.getTableColumnPool(tabId);
		Map<String, String> tablepoolMap = tableConfigPool.getTableMap(tabId);
		CollectionMapConvert<ColumnPoolObj> convert = new CollectionMapConvert<ColumnPoolObj>();
		// 表中指标对应的字段
		Map<String, List<ColumnPoolObj>> map = convert.convertToMapByParentKey(columnpoolList, "indicatorId");

		for (SubjectColumnConfigPoolObj pool : paramPoolObj) {
			Map<String, String> indMap = queryMetaIndPool.getIndicatorPool(pool.getIndId());
			String indId = pool.getIndId();
			
			// 指标存在且表中包括该指标
			if (indMap != null && map.get(indId) != null) {
				List<ColumnPoolObj> list = map.get(indId);
				List<QueryParam> paramList = new ArrayList<QueryParam>();
				List<QueryParam> param2 = new ArrayList<QueryParam>();
				StringBuffer sb = new StringBuffer("");
				for (ColumnPoolObj colpool : list) {
					if (pool.getFilterOperator().equals(Const.FILTER_OPER_BETWEEN)) {
						String val1 = theForm.getRecord().get("col" + indId + "from");
						String val2 = theForm.getRecord().get("col" + indId + "to");
						if ((val1 != null && !"".equals(val1)) || (val2 != null && !"".equals(val2))) {
							paramList.add(new QueryParam(colpool.getName(), synthesisColumnGenService.filterColumnType(colpool), pool.getFilterOperator(), val1 + ";" + val2, ""));
						}
					}
					else {
						String val = theForm.getRecord().get("col" + indId).trim();
						if (val != null && !"".equals(val)){
							paramList.add(new QueryParam(colpool.getName(), synthesisColumnGenService.filterColumnType(colpool), pool.getFilterOperator(), val, ""));
							if (Const.INDICATOR_NAME.equalsIgnoreCase(indMap.get("code"))) {								
								String[] xms = val.split("[\\s]+");
//								if (xms.length==2) {
//									paramList.add(new QueryParam(colpool.getName(), synthesisColumnGenService.filterColumnType(colpool), pool.getFilterOperator(), xms[1]+" "+xms[0], ""));
//								}else if (xms.length==3) {
//									paramList.add(new QueryParam(colpool.getName(), synthesisColumnGenService.filterColumnType(colpool), pool.getFilterOperator(), xms[2]+" "+xms[1]+" "+xms[0], ""));
//								}
								if (xms.length>1) {
									for (int i = 0; i < xms.length; i++) {
										param2.add(new QueryParam(colpool.getName(), synthesisColumnGenService.filterColumnType(colpool), Const.FILTER_OPER_LIKE, "%"+xms[i]+"%", ""));
									}
									if (!param2.isEmpty()) {
										sb.append(" or ("+sqlGen.getQueryStringPart(param2,QueryParam.OPER_AND)+")");
									}
									param2.clear();
								}
							}
						}						
						// 支持简繁查
						if (pool.getStQuery() != null && pool.getStQuery().equals(Const.TAG_ENABLE)) {
							String big5str = new String(GB2Big5.getInstance().gb2big5(val), "BIG5");
							paramList.add(new QueryParam(colpool.getName(), synthesisColumnGenService.filterColumnType(colpool), pool.getFilterOperator(), big5str, ""));
						}
					}
				}
				
//				String spSQL = "";
//				if (specialTables.get(tablepoolMap.get("name")) == null) {
//					if (!param2.isEmpty()) {
//						spSQL = sqlGen.getQueryStringPart(param2,QueryParam.OPER_AND);
//						if (spSQL!=null&&!"".equals(spSQL)) {
//							spSQL = " or (" +spSQL + ") ";
//						}else {
//							spSQL = "";
//						}
//					}
//				}

				String gensql;
				if (specialTables.get(tablepoolMap.get("name")) == null) {
					gensql = sqlGen.getQueryStringPart(paramList, QueryParam.OPER_OR);
				}
				else {
					theForm.setIsspecial("true");
					String telNoType = theForm.getRecord().get("telNoType");
					if (telNoType==null) {
						telNoType = "3";
					}
					gensql = changeParamList(paramList, sqlGen, telNoType);
					// gensql = sqlGen.getQueryStringPart(changeParamList(paramList),
					// QueryParam.OPER_OR);
				}
				if (gensql != null && !"".equals(gensql.trim())){
					
					//gensql += spSQL;
					gensql += sb.toString();
					buffer.append("(" + gensql + ")" + QueryParam.OPER_AND);
				}
			}
		}
		Map<String, String> tableMap = new HashMap<String, String>();
		// 获取对应表的查询语句和表名
		tableMap = tablepoolMap;
		tableMap.put("id", tabId);
		String querysql = buffer.length() > 0 ? " and " + buffer.substring(0, buffer.length() - 5) : "";
		if (querysql.length() > 0) {
			tableMap.put("sql", tablepoolMap.get("name") + " where 1=1" + querysql);
		}
		return tableMap;
	}

	private String changeParamList(List<QueryParam> pList, BaseSqlGen sqlGen ,String telNoType) {
		List<QueryParam> list = new ArrayList<QueryParam>();
		if (pList == null) return "";
		if (pList.size() == 0) return "";
		String pv = "";
		String strBig9 = "";
		for (QueryParam qp : pList) {
			if (qp.getColumnName().equalsIgnoreCase("ZJHM")&&("1".equals(telNoType)||"3".equals(telNoType))) {
				StringBuffer sb = new StringBuffer(qp.getQueryValue());
				String pValue = sb.reverse().toString();
				pv = pValue;
				QueryParam tmp;
				if (pValue.length() < 9) {
					String v1 = pValue + "0000000000";
					v1 = String.valueOf(Integer.parseInt(v1.substring(0, 9)));
					String v2 = pValue + "9999999999";
					v2 = String.valueOf(Integer.parseInt(v2.substring(0, 9)));

					tmp = new QueryParam("initiator1", QueryParam.COLUMN_TYPE_INT, QueryParam.QUERYMODE_BETWEEN, v1 + ";" + v2);
					list.add(tmp);
				}
				else if (pValue.length() == 9) {
					tmp = new QueryParam("initiator1", QueryParam.COLUMN_TYPE_INT, QueryParam.QUERYMODE_EQUAL, pValue);
					list.add(tmp);
				}else if (pValue.length() > 9&&pValue.length()<18) {
					String lv = pValue.substring(0, 9);
					strBig9 = "initiator1=" + lv;

					String v1 = pValue.substring(9) + "0000000000";
					v1 = String.valueOf(Integer.parseInt(v1.substring(0, 9)));
					String v2 = pValue.substring(9) + "9999999999";
					v2 = String.valueOf(Integer.parseInt(v2.substring(0, 9)));
					strBig9 += " and (initiator2 between " + v1 + " and " + v2 + ")";
				}
				else if (pValue.length() == 18){
					String lv = pValue.substring(0, 9);
					strBig9 = "initiator1=" + lv;

					String v1 = pValue.substring(9) + "0000000000";
					v1 = String.valueOf(Integer.parseInt(v1.substring(0, 9)));
					strBig9 += " and (initiator2 = " + v1 + ")";
				}
				else {
					String lv = pValue.substring(0, 9);
					strBig9 = "initiator1=" + lv;

					String v2 = pValue.substring(9, 18);
					strBig9 += " and (initiator2 = " + v2 + ")";
					
					String v3 = pValue.substring(18) + "0000000000";
					v3 = String.valueOf(Integer.parseInt(v3.substring(0, 9)));
					String v4 = pValue.substring(18) + "9999999999";
					v4 = String.valueOf(Integer.parseInt(v4.substring(0, 9)));
					strBig9 += " and (initiator3 between " + v3 + " and " + v4 + ")";
				}
			}
			else if (qp.getColumnName().equalsIgnoreCase("BJHM")&&("2".equals(telNoType)||"3".equals(telNoType))) {
				StringBuffer sb = new StringBuffer(qp.getQueryValue());
				String pValue = sb.reverse().toString();
				pv = pValue;
				QueryParam tmp;
				if (pValue.length() < 9) {
					String v1 = pValue + "0000000000";
					v1 = String.valueOf(Integer.parseInt(v1.substring(0, 9)));
					String v2 = pValue + "9999999999";
					v2 = String.valueOf(Integer.parseInt(v2.substring(0, 9)));
					tmp = new QueryParam("passive1", QueryParam.COLUMN_TYPE_INT, QueryParam.QUERYMODE_BETWEEN, v1 + ";" + v2);
					list.add(tmp);
				}
				else if (pValue.length() == 9) {
					tmp = new QueryParam("passive1", QueryParam.COLUMN_TYPE_INT, QueryParam.QUERYMODE_EQUAL, pValue);
					list.add(tmp);
				}
				else if(pValue.length() > 9&&pValue.length()<18){
					String lv = pValue.substring(0, 9);
					strBig9 += "passive1=" + lv;

					String v1 = pValue.substring(9) + "0000000000";
					v1 = String.valueOf(Integer.parseInt(v1.substring(0, 9)));
					String v2 = pValue.substring(9) + "9999999999";
					v2 = String.valueOf(Integer.parseInt(v2.substring(0, 9)));
					strBig9 += " and (passive2 between " + v1 + " and " + v2 + ")";
				}else if (pValue.length() == 18){
					String lv = pValue.substring(0, 9);
					strBig9 = "passive1=" + lv;

					String v1 = pValue.substring(9) + "0000000000";
					v1 = String.valueOf(Integer.parseInt(v1.substring(0, 9)));
					strBig9 += " and (passive2 = " + v1 + ")";
				}
				else {
					String lv = pValue.substring(0, 9);
					strBig9 = "passive1 =" + lv;

					String v2 = pValue.substring(9, 18);
					strBig9 += " and (passive2 = " + v2 + ")";
					
					String v3 = pValue.substring(18) + "0000000000";
					v3 = String.valueOf(Integer.parseInt(v3.substring(0, 9)));
					String v4 = pValue.substring(18) + "9999999999";
					v4 = String.valueOf(Integer.parseInt(v4.substring(0, 9)));
					strBig9 += " and (passive3 between " + v3 + " and " + v4 + ")";
				}
			}
			else if(!qp.getColumnName().equalsIgnoreCase("ZJHM")&&!qp.getColumnName().equalsIgnoreCase("BJHM")){
				list.add(qp);
			}
		}
		if (pv.length() < 10) return sqlGen.getQueryStringPart(list, QueryParam.OPER_OR);
		else {
			return strBig9;
		}
	}

	private String genenrateHtmlCode(SubjectConfigPoolObj subjectPoolObj, List<SubjectColumnConfigPoolObj> configList, CommSubjectQueryForm theForm, SynthesisColumnGenService synthesisColumnGenService) {
		StringBuffer buffer = new StringBuffer();
		String retStr = "";
		QueryMetaIndPool queryMetaIndPool = (QueryMetaIndPool) getBean("queryMetaIndPool");
		int rowcount = 2; // 每行显示查询个数，暂定2
		String trstart = "</tr><tr>";
		String twonamecoltd = "<td width=\"20%\">";
		String tdend = "</td>";

		buffer.append("<tr>");
		int count = 0;
		BussCodeSetPool bussCodeSetPool = (BussCodeSetPool) getBean("bussCodeSetPool");
		for (SubjectColumnConfigPoolObj pool : configList) {
			Map<String, String> indMap = queryMetaIndPool.getIndicatorPool(pool.getIndId());
			// String indId=pool.getIndId();
			if (indMap != null) {
				count++;
				if (count % rowcount == 0) {
					buffer.append(twonamecoltd + pool.getCnName() + "：" + tdend);
					buffer.append(synthesisColumnGenService.getInputHtmlCode(pool, bussCodeSetPool, theForm) + tdend);
					buffer.append(trstart);
				}
				else {
					buffer.append(twonamecoltd + pool.getCnName() + "：" + tdend);
					buffer.append(synthesisColumnGenService.getInputHtmlCode(pool, bussCodeSetPool, theForm) + tdend);
				}
			}
		}
		if (count % rowcount == 0) retStr = buffer.substring(0, buffer.length() - 4); // 去掉最后的<tr>
		else {// 补上空缺的td
			int repnum = (rowcount - count % rowcount) * 2;
			buffer.append("<td colspan=\"" + repnum + "\">&nbsp;</td>");
			retStr = buffer.append("</tr>").toString();
		}
		return retStr;
	}

	private String genenrateSelTableHtmlCode(CommSubjectQueryForm theForm, List<Map<String, String>> tablSqlList) throws WebException {
		StringBuffer buffer = new StringBuffer();
		String retStr = "";
		String[] tabId = theForm.getSelTable();
		List<String> selTabList = new ArrayList<String>();
		if (tabId != null) {
			for (int k = 0; k < tabId.length; k++)
				selTabList.add(tabId[k]);
		}
		int rowcount = 5; // 每行显示查询个数，暂定5
		String trstart = "</tr><tr>";
		String twonamecoltd = "<td width=\"3%\" class=\"textL\"><input type=\"checkbox\" title=\"数据表\" class=\"checkbox\" name=\"selTable\" value=\"";
		String twofieldcoltd = "<td width=\"17%\" class=\"textL\">";
		String tdend = "</td>";
		buffer.append("<tr>");
		int count = 0;
		if (tablSqlList == null) throw new WebException("没有配置表");
		for (Map<String, String> map : tablSqlList) {
			count++;
			String checked = "";
			if (selTabList.contains(map.get("id"))) checked = "checked";
			if (count % rowcount == 0) {
				buffer.append(twonamecoltd + map.get("id") + "\"" + checked + "/>" + tdend);
				buffer.append(twofieldcoltd + map.get("cnName") + tdend);
				buffer.append(trstart);
			}
			else {
				buffer.append(twonamecoltd + map.get("id") + "\"" + checked + "/>" + tdend);
				buffer.append(twofieldcoltd + map.get("cnName") + tdend);
			}

		}
		if (count % rowcount == 0) retStr = buffer.substring(0, buffer.length() - 4); // 去掉最后的<tr>
		else {
			int repnum = (rowcount - count % rowcount) * 2;
			buffer.append("<td colspan=\"" + repnum + "\"></td>");
			retStr = buffer.append("</tr>").toString();
		}
		return retStr;
	}

	private String genenrateClassifySelTableHtmlCode(CommSubjectQueryForm theForm, List<ResourceTable> tablSqlList) {
		StringBuffer buffer = new StringBuffer();
		String retStr = "";
		String[] tabId = theForm.getSelTable();
		List<String> selTabList = new ArrayList<String>();
		if (tabId != null) {
			for (int k = 0; k < tabId.length; k++)
				selTabList.add(tabId[k]);
		}
		int rowcount = 5; // 每行显示查询个数，暂定5
		String trstart = "</tr><tr>";
		String twonamecoltd = "<td width=\"3%\" class=\"textL\"><input type=\"checkbox\" title=\"数据表\" class=\"checkbox\" name=\"selTable\" value=\"";
		String twofieldcoltd = "<td width=\"17%\" class=\"textL\">";

		String tdend = "</td>";
		buffer.append("<tr>");
		int count = 0;
		for (ResourceTable tab : tablSqlList) {
			count++;
			String checked = "";
			if (selTabList.contains(tab.getId().toString())) checked = "checked";
			if (count % rowcount == 0) {
				buffer.append(twonamecoltd + tab.getId().toString() + "\"" + checked + "/>" + tdend);
				buffer.append(twofieldcoltd + tab.getCnName() + tdend);
				buffer.append(trstart);
			}
			else {
				buffer.append(twonamecoltd + tab.getId().toString() + "\"" + checked + "/>" + tdend);
				buffer.append(twofieldcoltd + tab.getCnName() + tdend);
			}

		}
		if (count % rowcount == 0) retStr = buffer.substring(0, buffer.length() - 4); // 去掉最后的<tr>
		else {
			int repnum = (rowcount - count % rowcount) * 2;
			buffer.append("<td colspan=\"" + repnum + "\"></td>");
			retStr = buffer.append("</tr>").toString();
		}
		return retStr;
	}

	public int getUidPos(String[] idArray, String uid) {
		int pos = -1;
		for (int i = 0; i < idArray.length; i++) {

			if (idArray[i].equals(uid)) {
				pos = i;
				break;
			}
		}
		return pos;
	}

	private synchronized boolean ProcessGenTableAndRecord(List<ColumnPoolObj> columnpoolList, String tableName, ResourceColumn pkobj, Map<String, String> tableMap, Session session, ResourceTable table,CommSubjectQueryForm theForm) {
		boolean isSuccess = false;
		SqlScriptGenerator generator = (SqlScriptGenerator) getBean("oracleScriptGen");
		SynthesisTempSpaceDao synthesisTempSpaceDao = (SynthesisTempSpaceDao) getBean("synthesisTempSpaceDao");
		if (synthesisTempSpaceDao.isTableExist(tableName)) {
			return isSuccess;
		}

		List<Map<String, String>> columnList = new ArrayList<Map<String, String>>();
		try {
			StringBuffer pbuffer = new StringBuffer("");
			for (ColumnPoolObj obj : columnpoolList) {
				Map<String, String> map = new HashMap<String, String>();
				map.put("columnName", obj.getName());
				map.put("columnType", obj.getDataType());
				map.put("isNullable", "Y"); // 默认字段都可以为空
				map.put("precise", obj.getDataPercise());
				map.put("columnLength", obj.getDataLength());
				columnList.add(map);
				pbuffer.append("?,");
			}
			String createSql = generator.generateCreateTableScript(columnList, tableName);

			// 执行建表语句
			synthesisTempSpaceDao.executeUpdate(createSql);

			// 获取查询的结果
			SynthesisQueryService synthesisQueryService = (SynthesisQueryService) getBean("synthesisQueryService");
			String sqlstr = "select * from " + tableMap.get("sql");
			
			PageQuery pageQuery = new PageQuery();
			
			String exportCount = "10000";
			setCode(theForm, "PERSONAL_EXPORT_COUNT");
			List<Code> codes = theForm.getCodeSets().get("PERSONAL_EXPORT_COUNT");
			if (codes!=null&&!codes.isEmpty()) {
				Code code = codes.get(0);
				if (code.getValue()!=null&&!"".equals(code.getValue())) {
					if (StringUtil.isNumberic(code.getValue())) {
						exportCount = code.getValue();
					}
				}
			}
			pageQuery.setPageSize(exportCount);
			
			List<Map<String, String>> list = synthesisQueryService.queryBySql(sqlstr, pageQuery).getRecordSet();
			// 数据安全等级过滤，保证安全
			SynthesisColumnGenService synthesisColumnGenService = (SynthesisColumnGenService) getBean("synthesisColumnGenService");
			synthesisColumnGenService.filterSecurityLevel(tableMap.get("id"), list, columnpoolList, pkobj, session);
			// 批量插入数据
			String insertSql = "insert into " + tableName + " values (" + pbuffer.substring(0, pbuffer.length() - 1) + ")";
			synthesisTempSpaceDao.batchUpdate(insertSql, list, columnpoolList);

			int count = synthesisTempSpaceDao.queryCountBySql("select count(*) from " + tableName);
			if (count == list.size()) {
				isSuccess = true;
				table.setRecordeCount(String.valueOf(count));
				ResourceTableService resourceTableService = (ResourceTableService) getBean("resourceTableService");
				resourceTableService.updateResourceTable(table);
			}
			else {
				synthesisTempSpaceDao.dropTable(tableName);
				isSuccess = false;
			}

		}
		catch (Exception e) {
			e.printStackTrace();
			log.error(e);
		}

		return isSuccess;
	}
}
