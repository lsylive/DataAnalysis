package com.liusy.datapp.web.dynamicquery.action;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

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
import com.liusy.datapp.model.resource.ResourceColumn;
import com.liusy.datapp.model.resource.ResourceTable;
import com.liusy.datapp.service.dynamicquery.SynthesisColumnGenService;
import com.liusy.datapp.service.pool.BussCodeSetPool;
import com.liusy.datapp.service.pool.QueryMetaIndPool;
import com.liusy.datapp.service.pool.TableConfigPool;
import com.liusy.datapp.service.query.SynthesisQueryService;
import com.liusy.datapp.service.resource.ResourceColumnService;
import com.liusy.datapp.service.resource.ResourceTableService;
import com.liusy.datapp.service.thread.QueryResultTableSaveThread;
import com.liusy.datapp.util.ThreadPool;
import com.liusy.datapp.util.meta.SqlScriptGenerator;
import com.liusy.datapp.util.poolobj.ColumnConfigPoolObj;
import com.liusy.datapp.util.poolobj.ColumnPoolObj;
import com.liusy.datapp.util.poolobj.QueryParamPoolObj;
import com.liusy.datapp.web.BaseAction;
import com.liusy.datapp.web.dynamicquery.form.UserDefinedQueryForm;
import com.liusy.web.tag.grid.Column;

public class UserDefinedQueryAction extends BaseAction {

	@Override
	public ActionForward doExecute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		String action = request.getParameter("action1");
		if (action == null) action = LIST;
		if (log.isDebugEnabled()) log.debug("action:" + action);
		ActionForward forward = null;
		UserDefinedQueryForm theForm = (UserDefinedQueryForm) form;
		try {
			if (LIST.equalsIgnoreCase(action)) forward = listAdvanceBacth(mapping, theForm, request, response); // 打开复杂查询列表页面
			else if ("LISTSIMPLE".equalsIgnoreCase(action)) forward = listSimpleBacth(mapping, theForm, request, response); // 打开简单查询列表页面
			else if ("SHOWQUERY".equalsIgnoreCase(action)) forward = queryAdvance(mapping, theForm, request, response);
			else if ("SHOWSIMPLE".equalsIgnoreCase(action)) forward = querySimple(mapping, theForm, request, response);
			else if ("VALID".equalsIgnoreCase(action)) forward = validate(mapping, theForm, request, response);
			else if ("PREVIEWSQL".equalsIgnoreCase(action)) forward = getSQLForPreview(mapping, theForm, request, response);
			else if ("VALIDSIMPLE".equalsIgnoreCase(action)) forward = validateSimple(mapping, theForm, request, response);
			else if ("ADVANCEOVERVIEW".equalsIgnoreCase(action)) forward = queryAdvanceOverview(mapping, theForm, request, response);
			else if ("SIMPLEOVERVIEW".equalsIgnoreCase(action)) forward = querySimpleOverview(mapping, theForm, request, response);
			else if ("SAVERESULT".equalsIgnoreCase(action)) forward = saveResult(mapping, theForm, request, response);
			else if ("SHOWEXPORTEXCEL".equalsIgnoreCase(action)) forward = showExportExcel(mapping, theForm, request, response);
			else if ("EXPORTEXCEL".equalsIgnoreCase(action)) forward = exportExcel(mapping, theForm, request, response);
			else {
				request.setAttribute("err", new WebException("找不到该action方法：" + action));
				forward = mapping.findForward(ERROR);// 找不到合适的action
			}

			// else if("SAVEPARAM".equalsIgnoreCase(action))
			// forward=saveQueryParam(mapping, theForm, request, response);
			// //保存查询条件到个人空间
		}
		catch (Exception e) {// 其他系统出错
			request.setAttribute("err", e);
			forward = mapping.findForward(ERROR);
		}
		return forward;
	}

	public ActionForward validate(ActionMapping mapping, UserDefinedQueryForm theForm, HttpServletRequest request, HttpServletResponse response) throws Exception {
		if (log.isDebugEnabled()) log.debug("Entering 'listCommBacth' method");

		response.setContentType("text/xml;charset=UTF-8");
		response.setHeader("Cache_Control", "no-cache");
		try {
			int len = theForm.getColid().length;
			ArrayList<String> conditionList = new ArrayList<String>();
			TableConfigPool tableConfigPool = (TableConfigPool) getBean("tableConfigPool");
			// Map<String,String>
			// tableMap=tableConfigPool.getTableMap(theForm.getTableId());
			List<ColumnPoolObj> collist = tableConfigPool.getTableColumnPool(theForm.getTableId());
			CollectionMapConvert<ColumnPoolObj> convert = new CollectionMapConvert<ColumnPoolObj>();
			Map<String, ColumnPoolObj> map = convert.convertToMapByMethold(collist, "name");

			for (int i = 0; i < len; i++) {
				String value = theForm.getParamvalue1()[i];

				if (null != value && !"".equals(value.trim())) {
					conditionList.add(i + "");
				}
				else {
					continue;
				}
			}
			if (conditionList.size() == 0) {
				addMessage(theForm, "查询条件不能为空");
				throw new Exception();
			}
			else {
				for (int j = 0; j < conditionList.size(); j++) {
					int i = Integer.parseInt(conditionList.get(j));
					String value = theForm.getParamvalue1()[i];
					String value1 = theForm.getParamvalue2()[i];
					ColumnPoolObj obj = map.get(theForm.getColid()[i]);

					if (theForm.getOper()[i].equals(Const.FILTER_OPER_BETWEEN)) {
						if (obj.getDataType().equals(Const.META_TYPE_STRING)) {
							addMessage(theForm, "条件第" + String.valueOf(i + 1) + "行字段" + obj.getCnName() + "为字符型，不允许做between操作");
							throw new Exception();
						}
						String[] values = { value, value1 };
						for (String str : values) {
							if (str != null && !"".equals(str)) validateInput(theForm, obj, str, i);
						}

					}
					else if (theForm.getOper()[i].equals(Const.FILTER_OPER_LIKE)) {
						if (!obj.getDataType().equals(Const.META_TYPE_STRING)) {
							addMessage(theForm, "条件第" + String.valueOf(i + 1) + "行字段" + obj.getCnName() + "非字符型，不允许做LIKE操作");
							throw new Exception();
						}
					}
				}
			}
			response.getWriter().write("<resp><result>OK</result><msg>OK</msg></resp>");
		}
		catch (Exception e) {
			e.printStackTrace();
			response.getWriter().write("<resp><result>ERR</result><msg>" + theForm.getErrorMessage() + "</msg></resp>");
		}
		return null;
	}

	public ActionForward listAdvanceBacth(ActionMapping mapping, UserDefinedQueryForm theForm, HttpServletRequest request, HttpServletResponse response) throws Exception {
		if (log.isDebugEnabled()) log.debug("Entering 'listCommBacth' method");
		ActionForward forward = null;
		try {
			TableConfigPool tableConfigPool = (TableConfigPool) getBean("tableConfigPool");
			List<QueryParamPoolObj> list = tableConfigPool.getParamConfigPool(theForm.getTableId());
			// request.setAttribute("paramList", list);
			initForm(theForm, "LIST", request);
			// 代码集
			List<ColumnPoolObj> columnPoolList = tableConfigPool.getTableColumnPool(theForm.getTableId());
			request.setAttribute("paramList", columnPoolList);
			SynthesisColumnGenService synthesisColumnGenService = (SynthesisColumnGenService) getBean("synthesisColumnGenService");
			List<Map<String, String>> codeSetList = new ArrayList<Map<String, String>>();
			StringBuffer codesetIdbuffer = new StringBuffer();
			StringBuffer timeIdbuffer = new StringBuffer();
			BussCodeSetPool bussCodeSetPool = (BussCodeSetPool) getBean("bussCodeSetPool");
			for (ColumnPoolObj obj : columnPoolList) {
				if (obj.getCodesetId() != null && !"".equals(obj.getCodesetId().trim())) {
					codesetIdbuffer.append(obj.getName() + ",");
					Map<String, String> map = new HashMap<String, String>();
					map.put("id", obj.getName());
					map.put("htmlcode", synthesisColumnGenService.getCodeSetHtml(obj, bussCodeSetPool, "paramvalue1"));
					codeSetList.add(map);
				}
				else if (obj.getDataType().equals(Const.META_TYPE_DATE)) timeIdbuffer.append(obj.getName() + ",");
			}
			if (!codeSetList.isEmpty()) request.setAttribute("codeSetList", codeSetList);
			String codeidArr = codesetIdbuffer.length() > 0 ? codesetIdbuffer.substring(0, codesetIdbuffer.length() - 1) : "";
			String dateArr = timeIdbuffer.length() > 0 ? timeIdbuffer.substring(0, timeIdbuffer.length() - 1) : "";
			request.setAttribute("codeColArr", codeidArr);
			request.setAttribute("dateColArr", dateArr);
			initForm(theForm, "showquery", request);
			forward = mapping.findForward(LIST);

		}
		catch (Exception e) {
			request.setAttribute("err", e);
			forward = mapping.findForward(ERROR);
		}
		return forward;
	}

	private ActionForward showExportExcel(ActionMapping mapping, UserDefinedQueryForm theForm, HttpServletRequest request, HttpServletResponse response) throws Exception {
		if (log.isDebugEnabled()) log.debug("Entering 'showExportExcel' method");
		return mapping.findForward("SHOWEXPORTEXCEL");
	}

	public ActionForward exportExcel(ActionMapping mapping, UserDefinedQueryForm theForm, HttpServletRequest request, HttpServletResponse response) throws Exception {
		if (log.isDebugEnabled()) log.debug("Entering 'saveResult' method");
		ActionForward forward = null;
		String tableId = theForm.getTableId();
		try {
			String ftype = request.getParameter("ftype");
			TableConfigPool tableConfigPool = (TableConfigPool) getBean("tableConfigPool");
			Map<String, String> tableMap = tableConfigPool.getTableMap(theForm.getTableId());
			List<ColumnPoolObj> columnList = tableConfigPool.getTableColumnPool(theForm.getTableId());
			CollectionMapConvert<ColumnPoolObj> convert = new CollectionMapConvert<ColumnPoolObj>();
			Map<String, ColumnPoolObj> map = convert.convertToMapByMethold(columnList, "name");
			Map<String, ColumnPoolObj> idmap = convert.convertToMapByMethold(columnList, "id");
			ResourceTableService resourceTableService = (ResourceTableService) getBean("resourceTableService");
			// 查询条件
			String type = request.getParameter("querytype").toUpperCase();
			SynthesisColumnGenService synthesisColumnGenService = (SynthesisColumnGenService) getBean("synthesisColumnGenService");
			String querySql = getQueryString(theForm, map, idmap, type, synthesisColumnGenService);
			tableMap.put("sql", " " + tableMap.get("name") + " where " + querySql);
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
			synthesisColumnGenService.filterSecurityLevel(tableMap.get("id"), resultList, columnList, pkobj, session);

			if ("0".equals(ftype)) {
				// 导出excel文件
				ExcelSheetColProp prop = new ExcelSheetColProp();
				List<String> columnNameList = new ArrayList<String>();
				List<String> columnEnameList = new ArrayList<String>();
				List<String> columnTypeList = new ArrayList<String>();

				for (ColumnPoolObj col : columnList) {
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

				if (columnList == null || columnList.isEmpty()) {
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
					header = new String[columnList.size()];
					for (int i = 0; i < columnList.size(); i++) {
						header[i] = columnList.get(i).getName();
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

	public ActionForward saveResult(ActionMapping mapping, UserDefinedQueryForm theForm, HttpServletRequest request, HttpServletResponse response) throws Exception {
		if (log.isDebugEnabled()) log.debug("Entering 'saveResult' method");
		ActionForward forward = null;
		String ename = getCurrentUser(request).getAccountName() + "_" + DateUtil.getDateTime("yyyyMMddHHmmss", new Date(System.currentTimeMillis()));
		String cname = request.getParameter("tabcname");
		String tableId = theForm.getTableId();
		String retStr = "OK";
		try {
			TableConfigPool tableConfigPool = (TableConfigPool) getBean("tableConfigPool");
			Map<String, String> tableMap = tableConfigPool.getTableMap(theForm.getTableId());
			List<ColumnPoolObj> collist = tableConfigPool.getTableColumnPool(theForm.getTableId());
			CollectionMapConvert<ColumnPoolObj> convert = new CollectionMapConvert<ColumnPoolObj>();
			Map<String, ColumnPoolObj> map = convert.convertToMapByMethold(collist, "name");
			Map<String, ColumnPoolObj> idmap = convert.convertToMapByMethold(collist, "id");
			ResourceTableService resourceTableService = (ResourceTableService) getBean("resourceTableService");
			// 查询条件
			String type = request.getParameter("querytype").toUpperCase();
			SynthesisColumnGenService synthesisColumnGenService = (SynthesisColumnGenService) getBean("synthesisColumnGenService");
			String querySql = getQueryString(theForm, map, idmap, type, synthesisColumnGenService);
			tableMap.put("sql", " " + tableMap.get("name") + " where " + querySql);
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
				if (ProcessGenTableAndRecord(collist, ename, pkobj, tableMap, session, table,theForm)) {
					// 建表跟插数据都成功
					retStr = "OK";
				}
				else {
					resourceTableService.removeResourceTable(table.getId());
					retStr = "REQUIRED";
				}
			}
			else {
				retStr = "REQUIRED";
			}
		}
		catch (Exception e) {
			e.printStackTrace();
			log.error(e);
			retStr = "ERR";
		}
		response.setContentType("text/plain;charset=UTF-8");
		response.setHeader("Cache_Control", "no-cache");
		response.getWriter().write(retStr);
		return forward;
	}

	public ActionForward listSimpleBacth(ActionMapping mapping, UserDefinedQueryForm theForm, HttpServletRequest request, HttpServletResponse response) throws Exception {
		if (log.isDebugEnabled()) log.debug("Entering 'listCommBacth' method");
		ActionForward forward = null;
		try {
			TableConfigPool tableConfigPool = (TableConfigPool) getBean("tableConfigPool");
			List<QueryParamPoolObj> list = tableConfigPool.getParamConfigPool(theForm.getTableId());
			request.setAttribute("paramList", list);
			initForm(theForm, "LIST", request);
			// 代码集
			List<ColumnPoolObj> columnPoolList = tableConfigPool.getTableColumnPool(theForm.getTableId());
			List<Map<String, String>> columnList = new ArrayList<Map<String, String>>();
			List<Map<String, String>> codeSetList = new ArrayList<Map<String, String>>();
			StringBuffer codesetIdbuffer = new StringBuffer();
			StringBuffer timeIdbuffer = new StringBuffer();
			BussCodeSetPool bussCodeSetPool = (BussCodeSetPool) getBean("bussCodeSetPool");
			SynthesisColumnGenService synthesisColumnGenService = (SynthesisColumnGenService) getBean("synthesisColumnGenService");
			for (int i = 0; i < columnPoolList.size(); i++) {
				ColumnPoolObj obj = columnPoolList.get(i);
				Map<String, String> map = new HashMap<String, String>();
				if (obj.getCodesetId() != null && !"".equals(obj.getCodesetId().trim())) {
					codesetIdbuffer.append(obj.getId() + ",");
					Map<String, String> codemap = new HashMap<String, String>();
					codemap.put("id", obj.getId());
					codemap.put("htmlcode", synthesisColumnGenService.getCodeSetHtml(obj, bussCodeSetPool, "paramvalue1"));
					// map.put("hascode", "1");
					codeSetList.add(codemap);
				}
				else if (obj.getDataType().equals(Const.META_TYPE_DATE)) timeIdbuffer.append(obj.getId() + ",");

				String valuefrom = "";
				String valueto = "";
				String oper = "";
				String linkoper = "";
				if (theForm.getParamvalue1() != null && theForm.getParamvalue1().length >= i) {
					valuefrom = theForm.getParamvalue1()[i];
					valueto = theForm.getParamvalue2()[i] == null ? "" : theForm.getParamvalue2()[i];
					oper = theForm.getOper()[i] == null ? "" : theForm.getOper()[i];
					if (i != 0) linkoper = theForm.getLinkoper()[i - 1] == null ? "" : theForm.getLinkoper()[i - 1];
				}

				map.put("paramvalue1", valuefrom);
				map.put("paramvalue2", valueto);
				map.put("oper", oper);
				map.put("linkoper", linkoper);
				map.put("colid", obj.getId());
				map.put("columnName", obj.getCnName());
				map.put("columnEName", obj.getName());
				columnList.add(map);
			}
			if (!columnList.isEmpty()) request.setAttribute("columnList", columnList);
			if (!codeSetList.isEmpty()) request.setAttribute("codeSetList", codeSetList);
			String codeidArr = codesetIdbuffer.length() > 0 ? codesetIdbuffer.substring(0, codesetIdbuffer.length() - 1) : "";
			String dateArr = timeIdbuffer.length() > 0 ? timeIdbuffer.substring(0, timeIdbuffer.length() - 1) : "";
			request.setAttribute("codeColArr", codeidArr);
			request.setAttribute("dateColArr", dateArr);
			forward = mapping.findForward("LISTSIMPLE");

		}
		catch (Exception e) {
			request.setAttribute("err", e);
			forward = mapping.findForward(ERROR);
		}
		return forward;
	}


	public ActionForward queryAdvance(ActionMapping mapping, UserDefinedQueryForm theForm, HttpServletRequest request, HttpServletResponse response) throws Exception {
		if (log.isDebugEnabled()) log.debug("Entering 'listCommBacth' method");
		ActionForward forward = null;
		try {
			TableConfigPool tableConfigPool = (TableConfigPool) getBean("tableConfigPool");
			Map<String, String> tableMap = tableConfigPool.getTableMap(theForm.getTableId());
			List<ColumnPoolObj> collist = tableConfigPool.getTableColumnPool(theForm.getTableId());
			CollectionMapConvert<ColumnPoolObj> convert = new CollectionMapConvert<ColumnPoolObj>();
			Map<String, ColumnPoolObj> map = convert.convertToMapByMethold(collist, "name");
			Map<String, ColumnPoolObj> idmap = convert.convertToMapByMethold(collist, "id");
			// 查询条件
			SynthesisColumnGenService synthesisColumnGenService = (SynthesisColumnGenService) getBean("synthesisColumnGenService");
			String querysql = getQueryString(theForm, map, idmap, "ADVANCE", synthesisColumnGenService);

			// 查询结果
			BussCodeSetPool bussCodeSetPool = (BussCodeSetPool) getBean("bussCodeSetPool");
			List<ColumnConfigPoolObj> columncfgpoolList = tableConfigPool.getColumnConfigPool(theForm.getTableId());
			List<String> colnameList = new ArrayList<String>();
			// Columns
			List<Column> columnList = new ArrayList<Column>();
			// checkbox
			Column checkcol = new Column();
			checkcol.setWidth("35px");
			checkcol.setAlign("center");
			checkcol.setItemType("checkbox");
			checkcol.setProperty("id");
			columnList.add(checkcol);
			// 主键字段
			// ColumnPoolObj pkobj=null;
			StringBuffer buffer = new StringBuffer();
			List<String> displayList = new ArrayList<String>();
			ResourceColumnService resourceColumnService = (ResourceColumnService) getBean("resourceColumnService");
			ResourceColumn pkobj = resourceColumnService.findResoucrePKColumn(Integer.valueOf(theForm.getTableId()));
			for (ColumnConfigPoolObj pool : columncfgpoolList) {
				ColumnPoolObj colpool = idmap.get(pool.getColumnId());
				if (pool.getIsSubject() != null && "1".equals(pool.getIsSubject())) {
					if (colpool.getId().equals(pkobj.getId().toString())) buffer.append(colpool.getName() + " as id,");
					else buffer.append(colpool.getName() + ",");
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

			// 显示字段是否包括主键,未包括就加入最前面

			if (pkobj != null && displayList.contains(pkobj.getName())) {
				// widthstr=widthbuffer.substring(0,widthbuffer.length()-1);
				colstr = buffer.toString().substring(0, buffer.length() - 1);
				// cnname=dnamebuffer.substring(0,dnamebuffer.length()-1);
				// alignstr=alignbuffer.substring(0,alignbuffer.length()-1);
				// typestr=typebuffer.substring(0,typebuffer.length()-1);
			}
			else {
				// widthstr="40,"+widthbuffer.substring(0,widthbuffer.length()-1);
				colstr = pkobj.getName() + " as id," + buffer.toString().substring(0, buffer.length() - 1);
				// cnname=pkobj.getCnName()+","+dnamebuffer.substring(0,dnamebuffer.length()-1);
				// alignstr="center,"+alignbuffer.substring(0,alignbuffer.length()-1);
				// typestr="int,"+typebuffer.substring(0,typebuffer.length()-1);
				colnameList.add(pkobj.getName());

			}
			String sql = "select " + colstr + " from " + tableMap.get("name") + " where " + querysql;//
			PageQuery pageQuery = theForm.getQuery();
			SynthesisQueryService synthesisQueryService = (SynthesisQueryService) getBean("synthesisQueryService");
			List<Map<String, String>> list = synthesisQueryService.queryBySql(sql, pageQuery).getRecordSet();
			// 取出该页id集合
			StringBuffer idArr = new StringBuffer();
			for (Map<String, String> tmpmap : list) {
				idArr.append(tmpmap.get("id") + ",");
			}
			request.setAttribute("idArr", idArr.length() > 0 ? idArr.substring(0, idArr.length() - 1) : "");
			// 过滤代码集
			bussCodeSetPool.filterBussCodeSet(list, collist);
			theForm.getQuery().setRecordSet(list);
			request.setAttribute(Column.DYNAMICCOLUMNS, columnList);
			initForm(theForm, "SHOWQUERY", request);
			forward = mapping.findForward("SHOWQUERY");
		}
		catch (Exception e) {
			request.setAttribute("err", e);
			e.printStackTrace();
			forward = mapping.findForward(LIST);
		}
		return forward;
	}

	public ActionForward querySimple(ActionMapping mapping, UserDefinedQueryForm theForm, HttpServletRequest request, HttpServletResponse response) throws Exception {
		if (log.isDebugEnabled()) log.debug("Entering 'querySimple' method");
		ActionForward forward = null;
		try {
			TableConfigPool tableConfigPool = (TableConfigPool) getBean("tableConfigPool");
			Map<String, String> tableMap = tableConfigPool.getTableMap(theForm.getTableId());
			List<ColumnPoolObj> collist = tableConfigPool.getTableColumnPool(theForm.getTableId());
			CollectionMapConvert<ColumnPoolObj> convert = new CollectionMapConvert<ColumnPoolObj>();
			Map<String, ColumnPoolObj> map = convert.convertToMapByMethold(collist, "name");
			Map<String, ColumnPoolObj> idmap = convert.convertToMapByMethold(collist, "id");
			int len = theForm.getColid().length;
			BaseSqlGen sqlGen = (BaseSqlGen) getBean("sybaseSqlGen");
			QueryMetaIndPool queryMetaIndPool = (QueryMetaIndPool) getBean("queryMetaIndPool");

			StringBuffer sqlbuffer = new StringBuffer();
			int pos = 0;
			SynthesisColumnGenService synthesisColumnGenService = (SynthesisColumnGenService) getBean("synthesisColumnGenService");
			for (int i = 0; i < len; i++) {
				ColumnPoolObj obj = idmap.get(theForm.getColid()[i]);
				String fromvalue = theForm.getParamvalue1()[i];
				String tovalue = theForm.getParamvalue2()[i];
				String linkoper = "";
				String indId = obj.getIndicatorId();
				if (indId == null) indId = "";
				if (i != 0) linkoper = theForm.getLinkoper()[i - 1];
				if (fromvalue != null && !"".equals(fromvalue)) {
					// BETWEEN操作
					QueryParam param = null;
					if (theForm.getOper()[i].equals(Const.FILTER_OPER_BETWEEN)) {

						if ((fromvalue != null && !"".equals(fromvalue)) || (tovalue != null && !"".equals(tovalue))) {
							param = new QueryParam(obj.getName(), synthesisColumnGenService.filterColumnType(obj), theForm.getOper()[i], fromvalue + ";" + tovalue, "", linkoper);
						}
					}
					else {
						param = new QueryParam(obj.getName(), synthesisColumnGenService.filterColumnType(obj), theForm.getOper()[i], fromvalue, "", linkoper);

						Map<String, String> indMap = queryMetaIndPool.getIndicatorPool(indId);
						List<QueryParam> list = new ArrayList<QueryParam>();
						if (indMap!=null&&Const.INDICATOR_NAME.equalsIgnoreCase(indMap.get("code"))) {
							String[] xms = fromvalue.split("[\\s]+");
//							if (xms.length == 2) {
//								param1 = new QueryParam(obj.getName(), synthesisColumnGenService.filterColumnType(obj), theForm.getOper()[i], xms[1] + " " + xms[0], "");
//							}
//							else if (xms.length == 3) {
//								param1 = new QueryParam(obj.getName(), synthesisColumnGenService.filterColumnType(obj), theForm.getOper()[i], xms[2] + " " + xms[1] + " " + xms[0], "");
//							}
							if (xms.length>1) {
								for (int j = 0; j < xms.length; j++) {
									list.add(new QueryParam(obj.getName(), synthesisColumnGenService.filterColumnType(obj), Const.FILTER_OPER_LIKE, "%"+xms[j] + "%" , ""));
								}
							}
						}
						if (pos != 0) {
							sqlbuffer.append(" " + linkoper + " (" + sqlGen.toSQLWithType(param));
						}
						else {
							sqlbuffer.append("("+sqlGen.toSQLWithType(param));
						}
						if (list.size()>0) {
							sqlbuffer.append(" or (" + sqlGen.getQueryStringPart(list,QueryParam.OPER_AND)+"))");
						}else {
							sqlbuffer.append(")");
						}
						pos++;
					}
				}
			}

			// 查询结果
			BussCodeSetPool bussCodeSetPool = (BussCodeSetPool) getBean("bussCodeSetPool");
			List<ColumnConfigPoolObj> columncfgpoolList = tableConfigPool.getColumnConfigPool(theForm.getTableId());
			List<String> colnameList = new ArrayList<String>();
			// Columns
			List<Column> columnList = new ArrayList<Column>();
			// checkbox
			Column checkcol = new Column();
			checkcol.setWidth("35px");
			checkcol.setAlign("center");
			checkcol.setItemType("checkbox");
			checkcol.setProperty("id");
			columnList.add(checkcol);
			// 主键字段
			// ColumnPoolObj pkobj=null;
			StringBuffer buffer = new StringBuffer();
			List<String> displayList = new ArrayList<String>();
			ResourceColumnService resourceColumnService = (ResourceColumnService) getBean("resourceColumnService");
			ResourceColumn pkobj = resourceColumnService.findResoucrePKColumn(Integer.valueOf(theForm.getTableId()));
			for (ColumnConfigPoolObj pool : columncfgpoolList) {
				ColumnPoolObj colpool = idmap.get(pool.getColumnId());
				if (pool.getIsSubject() != null && "1".equals(pool.getIsSubject())) {
					if (colpool.getId().equals(pkobj.getId().toString())) buffer.append(colpool.getName() + " as id,");
					else buffer.append(colpool.getName() + ",");
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
					// if(columnList.isEmpty()){
					// col.setItemType("hyperlink");
					// col.setHref("javascript:void(0)");
					// col.setOnClick("showdetail('{id}')");
					// }
					columnList.add(col);
				}

			}
			String colstr = "";

			// 显示字段是否包括主键,未包括就加入最前面

			if (pkobj != null && displayList.contains(pkobj.getName())) {
				// widthstr=widthbuffer.substring(0,widthbuffer.length()-1);
				colstr = buffer.toString().substring(0, buffer.length() - 1);
				// cnname=dnamebuffer.substring(0,dnamebuffer.length()-1);
				// alignstr=alignbuffer.substring(0,alignbuffer.length()-1);
				// typestr=typebuffer.substring(0,typebuffer.length()-1);
			}
			else {
				// widthstr="40,"+widthbuffer.substring(0,widthbuffer.length()-1);
				colstr = pkobj.getName() + " as id," + buffer.toString().substring(0, buffer.length() - 1);
				// cnname=pkobj.getCnName()+","+dnamebuffer.substring(0,dnamebuffer.length()-1);
				// alignstr="center,"+alignbuffer.substring(0,alignbuffer.length()-1);
				// typestr="int,"+typebuffer.substring(0,typebuffer.length()-1);
				colnameList.add(pkobj.getName());

			}
			String sql = "select " + colstr + " from " + tableMap.get("name") + " where " + sqlbuffer.toString();//
			PageQuery pageQuery = theForm.getQuery();
			SynthesisQueryService synthesisQueryService = (SynthesisQueryService) getBean("synthesisQueryService");
			List<Map<String, String>> list = synthesisQueryService.queryBySql(sql, pageQuery).getRecordSet();
			// 取出该页id集合
			StringBuffer idArr = new StringBuffer();
			for (Map<String, String> tmpmap : list) {
				idArr.append(tmpmap.get("id") + ",");
			}
			request.setAttribute("idArr", idArr.length() > 0 ? idArr.substring(0, idArr.length() - 1) : "");
			// 过滤代码集
			bussCodeSetPool.filterBussCodeSet(list, collist);
			theForm.getQuery().setRecordSet(list);
			request.setAttribute(Column.DYNAMICCOLUMNS, columnList);
			initForm(theForm, "showsimple", request);
			forward = mapping.findForward("SHOWSIMPLE");
		}
		catch (Exception e) {
			request.setAttribute("err", e);
			e.printStackTrace();
			forward = mapping.findForward("err");
		}
		return forward;
	}

	public ActionForward queryAdvanceOverview(ActionMapping mapping, UserDefinedQueryForm theForm, HttpServletRequest request, HttpServletResponse response) throws Exception {
		if (log.isDebugEnabled()) log.debug("Entering 'listCommBacth' method");
		ActionForward forward = null;
		try {
			initForm(theForm, "SHOWQUERY", request);
			forward = mapping.findForward("ADVANCEOVERVIEW");
		}
		catch (Exception e) {
			request.setAttribute("err", e);
			e.printStackTrace();
			forward = mapping.findForward("err");
		}
		return forward;
	}

	public ActionForward querySimpleOverview(ActionMapping mapping, UserDefinedQueryForm theForm, HttpServletRequest request, HttpServletResponse response) throws Exception {
		if (log.isDebugEnabled()) log.debug("Entering 'listCommBacth' method");
		ActionForward forward = null;
		try {
			initForm(theForm, "showsimple", request);
			forward = mapping.findForward("SIMPLEOVERVIEW");
		}
		catch (Exception e) {
			request.setAttribute("err", e);
			e.printStackTrace();
			forward = mapping.findForward("err");
		}
		return forward;
	}

	private void initForm(UserDefinedQueryForm theForm, String action, HttpServletRequest request) throws Exception {
		setCode(theForm, "FILTER_OPER");
		if (action.equalsIgnoreCase("showquery")) {
			List<Map<String, String>> list = new ArrayList<Map<String, String>>();
			if (theForm.getPrevoper() != null) {
				for (int i = 0; i < theForm.getPrevoper().length; i++) {

					Map<String, String> map = new HashMap<String, String>();
					map.put("prevoper", theForm.getPrevoper()[i]);
					map.put("colid", theForm.getColid()[i]);
					map.put("paramvalue1", theForm.getParamvalue1()[i]);
					map.put("paramvalue2", theForm.getParamvalue2()[i]);
					map.put("oper", theForm.getOper()[i]);
					map.put("nextoper", theForm.getNextoper()[i]);

					if (i != 0) {
						map.put("linkoper", theForm.getLinkoper()[i - 1]);
					}
					list.add(map);
				}
				request.setAttribute("mutilList", list);
			}
		}
		else if (action.equalsIgnoreCase("showsimple")) {
			List<Map<String, String>> list = new ArrayList<Map<String, String>>();
			if (theForm.getOper() != null) {
				for (int i = 0; i < theForm.getOper().length; i++) {
					Map<String, String> map = new HashMap<String, String>();
					if (i != 0) {
						map.put("linkoper", theForm.getLinkoper()[i - 1]);
					}
					map.put("colid", theForm.getColid()[i]);
					map.put("paramvalue1", theForm.getParamvalue1()[i]);
					map.put("paramvalue2", theForm.getParamvalue2()[i]);
					map.put("oper", theForm.getOper()[i]);
					list.add(map);
				}
				request.setAttribute("mutilList", list);
			}
		}
	}

	private void validateInput(UserDefinedQueryForm theForm, ColumnPoolObj obj, String value, int pos) throws Exception {
		String dataType = "";
		try {
			if (obj.getDataType().equals(Const.META_TYPE_NUMERIC)) {
				dataType = "数值(0-9)";
				double d = Double.parseDouble(value);
			}
			else if (obj.getDataType().equals(Const.META_TYPE_DATE)) {
				dataType = "日期(yyyy-MM-dd)";
				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
				dateFormat.parse(value);
			}
		}
		catch (Exception e) {
			addMessage(theForm, "条件第" + String.valueOf(pos + 1) + "行查询值应为" + dataType);
			throw new Exception();
		}
	}

	public ActionForward getSQLForPreview(ActionMapping mapping, UserDefinedQueryForm theForm, HttpServletRequest request, HttpServletResponse response)throws Exception{
		if (log.isDebugEnabled()) log.debug("Entering 'getSQLForPreview' method");
		try {
			TableConfigPool tableConfigPool = (TableConfigPool) getBean("tableConfigPool");
			Map<String, String> tableMap = tableConfigPool.getTableMap(theForm.getTableId());
			List<ColumnPoolObj> collist = tableConfigPool.getTableColumnPool(theForm.getTableId());
			CollectionMapConvert<ColumnPoolObj> convert = new CollectionMapConvert<ColumnPoolObj>();
			Map<String, ColumnPoolObj> map = convert.convertToMapByMethold(collist, "name");
			Map<String, ColumnPoolObj> idmap = convert.convertToMapByMethold(collist, "id");
			// 查询条件
			SynthesisColumnGenService synthesisColumnGenService = (SynthesisColumnGenService) getBean("synthesisColumnGenService");
			String querysql = getQueryString(theForm, map, idmap, "ADVANCE", synthesisColumnGenService);

			// 查询结果
			BussCodeSetPool bussCodeSetPool = (BussCodeSetPool) getBean("bussCodeSetPool");
			List<ColumnConfigPoolObj> columncfgpoolList = tableConfigPool.getColumnConfigPool(theForm.getTableId());
			List<String> colnameList = new ArrayList<String>();

			StringBuffer buffer = new StringBuffer();
			List<String> displayList = new ArrayList<String>();
			ResourceColumnService resourceColumnService = (ResourceColumnService) getBean("resourceColumnService");
			ResourceColumn pkobj = resourceColumnService.findResoucrePKColumn(Integer.valueOf(theForm.getTableId()));
			for (ColumnConfigPoolObj pool : columncfgpoolList) {
				ColumnPoolObj colpool = idmap.get(pool.getColumnId());
				if (pool.getIsSubject() != null && "1".equals(pool.getIsSubject())) {
					if (colpool.getId().equals(pkobj.getId().toString())) buffer.append(colpool.getName() + " as id,");
					else buffer.append(colpool.getName() + ",");
				}

			}
			String colstr = "";

			// 显示字段是否包括主键,未包括就加入最前面

			if (pkobj != null && displayList.contains(pkobj.getName())) {
				colstr = buffer.toString().substring(0, buffer.length() - 1);
			}
			else {
				colstr = pkobj.getName() + " as id," + buffer.toString().substring(0, buffer.length() - 1);
			}
			String sql = "select " + colstr + " from " + tableMap.get("name") + " where " + querysql;//
			
			response.setContentType("text/xml;charset=UTF-8");
			response.setHeader("Cache_Control", "no-cache");
			response.getWriter().write(sql);
		}
		catch (Exception e) {
			e.printStackTrace();
			response.getWriter().write("<resp><result>ERR</result><msg>" + theForm.getErrorMessage() + "</msg></resp>");
		}
		return null;
	}
	
	public ActionForward validateSimple(ActionMapping mapping, UserDefinedQueryForm theForm, HttpServletRequest request, HttpServletResponse response) throws Exception {
		if (log.isDebugEnabled()) log.debug("Entering 'listCommBacth' method");

		response.setContentType("text/xml;charset=UTF-8");
		response.setHeader("Cache_Control", "no-cache");
		try {
			int len = theForm.getColid().length;
			ArrayList<String> condictionList = new ArrayList<String>();
			TableConfigPool tableConfigPool = (TableConfigPool) getBean("tableConfigPool");
			// Map<String,String>
			// tableMap=tableConfigPool.getTableMap(theForm.getTableId());
			List<ColumnPoolObj> collist = tableConfigPool.getTableColumnPool(theForm.getTableId());
			CollectionMapConvert<ColumnPoolObj> convert = new CollectionMapConvert<ColumnPoolObj>();
			Map<String, ColumnPoolObj> map = convert.convertToMapByMethold(collist, "id");

			for (int i = 0; i < len; i++) {
				String value = theForm.getParamvalue1()[i];

				if (value != null && !"".equals(value.trim())) {
					condictionList.add(i + "");
				}
				else {
					continue;
				}
			}
			if (condictionList.size() == 0) {
				addMessage(theForm, "查询条件不能为空");
				throw new Exception();
			}
			else {
				for (int j = 0; j < condictionList.size(); j++) {
					int i = Integer.parseInt(condictionList.get(j));
					String value = theForm.getParamvalue1()[i];
					String value1 = theForm.getParamvalue2()[i];
					ColumnPoolObj obj = map.get(theForm.getColid()[i]);

					if (theForm.getOper()[i].equals(Const.FILTER_OPER_BETWEEN)) {
						if (obj.getDataType().equals(Const.META_TYPE_STRING)) {
							addMessage(theForm, "条件第" + String.valueOf(i + 1) + "行字段" + obj.getCnName() + "为字符型，不允许做between操作");
							throw new Exception();
						}
						String[] values = { value, value1 };
						for (String str : values) {
							if (str != null && !"".equals(str)) validateInput(theForm, obj, str, i);
						}

					}
					else if (theForm.getOper()[i].equals(Const.FILTER_OPER_LIKE)) {
						if (!obj.getDataType().equals(Const.META_TYPE_STRING)) {
							addMessage(theForm, "条件第" + String.valueOf(i + 1) + "行字段" + obj.getCnName() + "非字符型，不允许做LIKE操作");
							throw new Exception();
						}
					}
				}
			}
			response.getWriter().write("<resp><result>OK</result><msg>OK</msg></resp>");
		}
		catch (Exception e) {
			e.printStackTrace();
			response.getWriter().write("<resp><result>ERR</result><msg>" + theForm.getErrorMessage() + "</msg></resp>");
		}
		return null;
	}

	private String getQueryString(UserDefinedQueryForm theForm, Map<String, ColumnPoolObj> map, Map<String, ColumnPoolObj> idmap, String type, SynthesisColumnGenService synthesisColumnGenService) {
		int len = theForm.getColid().length;
		StringBuffer querybuffer = new StringBuffer();
		BaseSqlGen sqlGen = (BaseSqlGen) getBean("sybaseSqlGen");
		if (type.equals("ADVANCE")) {
			for (int i = 0; i < len; i++) {
				if (i != 0) querybuffer.append(" " + theForm.getLinkoper()[i - 1] + " ");
				ColumnPoolObj obj = map.get(theForm.getColid()[i]);
				String value = theForm.getParamvalue1()[i];
				String value1 = theForm.getParamvalue2()[i];

				querybuffer.append(theForm.getPrevoper()[i]);

				List<QueryParam> paramList = new ArrayList<QueryParam>();
				// BETWEEN操作
				if (theForm.getOper()[i].equals(Const.FILTER_OPER_BETWEEN)) {
					String fromvalue = theForm.getParamvalue1()[i];
					String tovalue = theForm.getParamvalue2()[i];

					if ((fromvalue != null && !"".equals(fromvalue)) || (tovalue != null && !"".equals(tovalue))) {
						paramList.add(new QueryParam(obj.getName(), synthesisColumnGenService.filterColumnType(obj), theForm.getOper()[i], fromvalue + ";" + tovalue, ""));
					}
				}
				else {
					// String val=theForm.getRecord().get("col"+indId);
					if (value != null && !"".equals(value)) {
						paramList.add(new QueryParam(obj.getName(), synthesisColumnGenService.filterColumnType(obj), theForm.getOper()[i], value, ""));
					}
				}
				String gensql = sqlGen.getQueryStringPart(paramList, QueryParam.OPER_AND);
				querybuffer.append(gensql);
				querybuffer.append(theForm.getNextoper()[i]);
			}
		}
		else if (type.equals("SIMPLE")) {
			int pos = 0;
			for (int i = 0; i < len; i++) {
				ColumnPoolObj obj = idmap.get(theForm.getColid()[i]);
				String fromvalue = theForm.getParamvalue1()[i];
				String tovalue = theForm.getParamvalue2()[i];
				String linkoper = "";
				if (i != 0) linkoper = theForm.getLinkoper()[i - 1];
				if (fromvalue != null && !"".equals(fromvalue)) {
					// BETWEEN操作
					QueryParam param = null;
					if (theForm.getOper()[i].equals(Const.FILTER_OPER_BETWEEN)) {

						if ((fromvalue != null && !"".equals(fromvalue)) || (tovalue != null && !"".equals(tovalue))) {
							param = new QueryParam(obj.getName(), synthesisColumnGenService.filterColumnType(obj), theForm.getOper()[i], fromvalue + ";" + tovalue, "", linkoper);
						}
					}
					else {
						param = new QueryParam(obj.getName(), synthesisColumnGenService.filterColumnType(obj), theForm.getOper()[i], fromvalue, "", linkoper);

					}
					if (pos != 0) querybuffer.append(" " + linkoper + " " + sqlGen.toSQLWithType(param));
					else querybuffer.append(sqlGen.toSQLWithType(param));
					pos++;
				}

			}
		}
		System.out.println("querysql=" + querybuffer.toString());
		return querybuffer.toString();
	}

	private synchronized boolean ProcessGenTableAndRecord(List<ColumnPoolObj> columnpoolList, String tableName, ResourceColumn pkobj, Map<String, String> tableMap, Session session, ResourceTable table,UserDefinedQueryForm theForm) {
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

			int count = synthesisTempSpaceDao.queryCountBySql("select count(id) from " + tableName);
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
