package com.liusy.datapp.web.dynamicquery.action;

import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.poi.hssf.model.Workbook;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;
import org.apache.xmlbeans.XmlOptions;

import org.supercsv.io.CsvListReader;
import org.supercsv.io.ICsvListReader;
import org.supercsv.prefs.CsvPreference;

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
import com.liusy.datapp.model.datastandard.StandardCodeset;
import com.liusy.datapp.model.query.QuerySubject;
import com.liusy.datapp.model.resource.ResourceColumn;
import com.liusy.datapp.model.resource.ResourceTable;
import com.liusy.datapp.service.dynamicquery.SynthesisColumnGenService;
import com.liusy.datapp.service.pool.BussCodeSetPool;
import com.liusy.datapp.service.pool.QueryMetaIndPool;
import com.liusy.datapp.service.pool.SubjectConfigPool;
import com.liusy.datapp.service.pool.TableConfigPool;
import com.liusy.datapp.service.query.SynthesisQueryService;
import com.liusy.datapp.service.resource.ResourceColumnService;
import com.liusy.datapp.service.resource.ResourceTableService;
import com.liusy.datapp.service.thread.QueryResultTableSaveThread;

import com.liusy.datapp.util.ThreadPool;
import com.liusy.datapp.util.WebContextBeanFactory;
import com.liusy.datapp.util.meta.SqlScriptGenerator;
import com.liusy.datapp.util.poolobj.ColumnConfigPoolObj;
import com.liusy.datapp.util.poolobj.ColumnPoolObj;
import com.liusy.datapp.util.poolobj.SubjectColumnConfigPoolObj;
import com.liusy.datapp.util.poolobj.SubjectConfigPoolObj;
import com.liusy.datapp.web.BaseAction;
import com.liusy.datapp.web.dynamicquery.form.CommSubjectQueryForm;
import com.liusy.datapp.web.dynamicquery.form.SubjectBatchQueryForm;
import com.liusy.datapp.xbeans.COLSType;
import com.liusy.datapp.xbeans.COLType;
import com.liusy.datapp.xbeans.COMMBATCHQUERYType;
import com.liusy.datapp.xbeans.PARAMSType;
import com.liusy.datapp.xbeans.PARAMType;
import com.liusy.datapp.xbeans.QUERYPARAMDocument;
import com.liusy.datapp.xbeans.QUERYPARAMType;
import com.liusy.web.tag.grid.Column;

public class SubjectBatchQueryAction extends BaseAction {

	@Override
	public ActionForward doExecute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		String action = request.getParameter("action1");
		if (action == null) action = LIST;
		if (log.isDebugEnabled()) log.debug("action:" + action);
		ActionForward forward = null;
		SubjectBatchQueryForm theForm = (SubjectBatchQueryForm) form;
		try {
			if ("LISTBATCH".equalsIgnoreCase(action)) forward = listBacth(mapping, theForm, request, response); // 打开查询列表页面
			// else if("LISTBATCHGRID".equalsIgnoreCase(action))
			// forward=listBacthGrid(mapping, theForm, request, response);
			else if ("SAVEPARAM".equalsIgnoreCase(action)) forward = saveQueryParam(mapping, theForm, request, response);
			else if ("uploadfile".equalsIgnoreCase(action)) forward = uploadfile(mapping, theForm, request, response);
			else if ("importfile".equalsIgnoreCase(action)) forward = importfile(mapping, theForm, request, response);
			// if ("LISTCLASSIFY".equalsIgnoreCase(action)) forward =
			// listClassifyBacth(mapping, theForm, request, response); // 打开查询列表页面
			else if ("QUERYOVERVIEW".equalsIgnoreCase(action)) forward = queryOverview(mapping, theForm, request, response); // 概览页面
			else if ("TOTALCOUNT".equalsIgnoreCase(action)) forward = querySubjectTotalCount(mapping, theForm, request, response);
			else if ("QUERYCOUNT".equalsIgnoreCase(action)) forward = querySubjectTableCount(mapping, theForm, request, response);
			else if ("SHOWRESULT".equalsIgnoreCase(action)) forward = queryRecordList(mapping, theForm, request, response);
			else if ("SAVERESULT".equalsIgnoreCase(action)) forward = saveResult(mapping, theForm, request, response);
			else if ("EXPORTEXCEL".equalsIgnoreCase(action)) forward = exportExcel(mapping, theForm, request, response);
			else if ("EXPORTALLEXCEL".equalsIgnoreCase(action)) forward = exportAllExcel(mapping, theForm, request, response);
			else {
				request.setAttribute("err", new WebException("找不到该action方法：" + action));
				forward = mapping.findForward(ERROR);// 找不到合适的action
			}
			Session session = (Session) request.getSession().getAttribute(Const.SESSION);
			// 判断是否配置个人空间
			if (session.getSpaceId() == null || "".equals(session.getSpaceId().trim()) || Const.TAG_DISABLE.equals(session.getSpaceId())) {
				request.setAttribute("hasspace", "0");
			}
			else request.setAttribute("hasspace", "1");
		}
		catch (Exception e) {// 其他系统出错
			request.setAttribute("err", e);
			forward = mapping.findForward(ERROR);
		}
		return forward;
	}

	public ActionForward exportExcel(ActionMapping mapping, SubjectBatchQueryForm theForm, HttpServletRequest request, HttpServletResponse response)throws Exception{
		if (log.isDebugEnabled()) log.debug("Entering 'saveResult' method");
		ActionForward forward=null;
		try{
			String ftype=request.getParameter("ftype");
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
			List<Map<String, String>> columnameList = new ArrayList<Map<String, String>>();
			List<String> colvalList = new ArrayList<String>();
			SynthesisColumnGenService synthesisColumnGenService = (SynthesisColumnGenService) getBean("synthesisColumnGenService");
			List<Map<String, String>> tableSqlList = getQueryParam(theForm, theForm.getTableId(), columnameList, colvalList, synthesisColumnGenService);
			Map<String, String> tableMap = tableSqlList.get(Integer.valueOf(theForm.getPos()));
			
			Session session=(Session)request.getSession().getAttribute(Const.SESSION);
			//将数据从后台查询出来
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
		}catch (Exception e) {
			e.printStackTrace();
			log.error(e);
		}
		return forward;
	}
	
	public ActionForward uploadfile(ActionMapping mapping, SubjectBatchQueryForm theForm, HttpServletRequest request, HttpServletResponse response) throws Exception {
		String seltab = request.getParameter("seltab");
		if (seltab != null && !"".equals(seltab.trim())) {
			theForm.setSelTable(seltab.split(","));
			initForm(theForm, "SHOWOVERVIEW", request);
		}
		return mapping.findForward("UPLOADFILE");
	}

	public ActionForward importfile(ActionMapping mapping, SubjectBatchQueryForm theForm, HttpServletRequest request, HttpServletResponse response) throws Exception {
		if (log.isDebugEnabled()) log.debug("Entering 'searchSubject' method");
		ActionForward forward = null;
		try {
			FormFile file = theForm.getFilename();
			ICsvListReader reader = new CsvListReader(new InputStreamReader(file.getInputStream()), CsvPreference.STANDARD_PREFERENCE);

			JSONArray array = new JSONArray();
			List<String> resultlist = new ArrayList<String>();
			SubjectConfigPool subjectConfigPool = (SubjectConfigPool) getBean("subjectConfigPool");
			QueryMetaIndPool queryMetaIndPool = (QueryMetaIndPool) getBean("queryMetaIndPool");
			List<SubjectColumnConfigPoolObj> paramPoolObj = subjectConfigPool.getSubjectColumnConfigPool(theForm.getId(),true);
			int readTimes = 0;
			while ((resultlist = reader.read()) != null) {
				//限制导入查询条件为100条
				if (readTimes >99) {
					break;
				}
				int cols = 0;
				JSONObject object = new JSONObject();
				for (SubjectColumnConfigPoolObj pool : paramPoolObj) {
					if (cols >= resultlist.size()) break;
					Map<String, String> indMap = queryMetaIndPool.getIndicatorPool(pool.getIndId());
					String indId = pool.getIndId();
					if (indMap != null && pool.getBatchQuery() != null && !"".equals(pool.getBatchQuery()) && "1".equals(pool.getBatchQuery())) {
						if (pool.getFilterOperator().equals(Const.FILTER_OPER_BETWEEN)) {
							String val1 = resultlist.get(cols);
							if(val1==null)val1 = "";
							val1 = val1.replaceAll("'", " ").replaceAll("‘", " ");
							object.put("col" + indId + "from", val1);
							cols++;
							if (cols >= resultlist.size()) break;
							String val2 = resultlist.get(cols);
							if(val2==null)val2 = "";
							val2 = val2.replaceAll("'", " ").replaceAll("‘", " ");
							object.put("col" + indId + "to", val2);
						}
						else {
							String val = resultlist.get(cols);
							val = val.replaceAll("'", " ").replaceAll("‘", " ");
							object.put("col" + indId, val);
						}
						cols++;
					}
				}
				array.add(object);
				
				readTimes++;
			}
			theForm.getRecord().put("paramjson", array.toString());
			forward = listBacth(mapping, theForm, request, response);
		}
		catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("err", e);
			forward = mapping.findForward(ERROR);
		}
		return forward;
	}


	public ActionForward saveQueryParam(ActionMapping mapping, SubjectBatchQueryForm theForm, HttpServletRequest request, HttpServletResponse response) throws Exception {
		if (log.isDebugEnabled()) log.debug("Entering 'searchSubject' method");
		ActionForward forward = null;
		try {
			SubjectConfigPool subjectConfigPool = (SubjectConfigPool) getBean("subjectConfigPool");
			SubjectConfigPoolObj subjectPoolObj = subjectConfigPool.getSubjectConfigPool(theForm.getId());
			QueryMetaIndPool queryMetaIndPool = (QueryMetaIndPool) getBean("queryMetaIndPool");
			List<SubjectColumnConfigPoolObj> paramPoolObj = subjectConfigPool.getSubjectColumnConfigPool(theForm.getId(),true);

			String jsonStr = theForm.getRecord().get("paramjson");
			QUERYPARAMDocument doc = QUERYPARAMDocument.Factory.newInstance();
			QUERYPARAMType params = doc.addNewQUERYPARAM();
			COMMBATCHQUERYType subject = params.addNewCOMMBATCHQUERY();
			PARAMSType sparams = subject.addNewPARAMS();

			subject.setSUBJECTID(theForm.getId());

			JSONArray array = null;
			if (jsonStr != null) array = JSONArray.fromObject(jsonStr);
			Iterator<JSONObject> iter = array.iterator();

			while (iter.hasNext()) {
				JSONObject jsonObj = iter.next();
				PARAMType param = sparams.addNewPARAM();
				COLSType cols = param.addNewCOLS();
				for (SubjectColumnConfigPoolObj pool : paramPoolObj) {
					Map<String, String> indMap = queryMetaIndPool.getIndicatorPool(pool.getIndId());
					String indId = pool.getIndId();
					if (indMap != null && pool.getBatchQuery() != null && !"".equals(pool.getBatchQuery()) && "1".equals(pool.getBatchQuery())) {

						if (pool.getFilterOperator().equals(Const.FILTER_OPER_BETWEEN)) {
							String col1 = "col" + indId + "from";
							String col2 = "col" + indId + "to";
							String val1 = jsonObj.getString(col1) == null ? "" : jsonObj.getString(col1);
							String val2 = jsonObj.getString(col2) == null ? "" : jsonObj.getString(col2);
							COLType column1 = cols.addNewCOL();
							column1.setId(col1);
							column1.setStringValue(val1);
							COLType column2 = cols.addNewCOL();
							column2.setId(col2);
							column2.setStringValue(val2);
						}
						else {
							String val = jsonObj.getString("col" + indId) == null ? "" : jsonObj.getString("col" + indId);
							COLType column = cols.addNewCOL();
							column.setId("col" + indId);
							column.setStringValue(val);
						}
					}
				}

			}
			SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
			String dateformat = format.format(new Date());
			response.setContentType("application/xml");
			response.setHeader("Content-Disposition", "attachment;filename=subject" + theForm.getId() + "_" + dateformat + ".xml");
			ServletOutputStream os = response.getOutputStream();
			XmlOptions xmlOptions = new XmlOptions();
			xmlOptions.setSavePrettyPrint();
			xmlOptions.setSavePrettyPrintIndent(4);
			// xmlOptions.setUseDefaultNamespace();
			// params.save(os,xmlOptions);
			os.write(doc.xmlText(xmlOptions).getBytes("UTF-8"));
			os.flush();
			forward = null;
		}
		catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("err", e);
			forward = mapping.findForward(ERROR);
		}
		return forward;
	}

	public ActionForward saveResult(ActionMapping mapping, SubjectBatchQueryForm theForm, HttpServletRequest request, HttpServletResponse response) throws Exception {
		if (log.isDebugEnabled()) log.debug("Entering 'searchSubject' method");
		ActionForward forward = null;
		boolean processOk = true;
		String retStr = "OK";
		String ename = getCurrentUser(request).getAccountName()+"_"+DateUtil.getDateTime("yyyyMMddHHmmss", new Date(System.currentTimeMillis()));
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
			List<Map<String, String>> columnameList = new ArrayList<Map<String, String>>();
			List<String> colvalList = new ArrayList<String>();
			SynthesisColumnGenService synthesisColumnGenService = (SynthesisColumnGenService) getBean("synthesisColumnGenService");
			List<Map<String, String>> tableSqlList = getQueryParam(theForm, theForm.getTableId(), columnameList, colvalList, synthesisColumnGenService);
			Map<String, String> tableMap = tableSqlList.get(Integer.valueOf(theForm.getPos()));

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
				if (ProcessGenTableAndRecord(columnpoolList, ename, pkobj, tableMap, session,theForm)) {
					processOk = true;
					retStr = "OK";
				}else {
					resourceTableService.removeResourceTable(table.getId());
					processOk = false;
					retStr = "REQUIRED";
				}
//				QueryResultTableSaveThread thread = new QueryResultTableSaveThread(tableMap, ename, columnpoolList, pkobj, session);
//				ThreadPool.exec(thread);
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
		return forward;
	}

	public ActionForward queryOverview(ActionMapping mapping, SubjectBatchQueryForm theForm, HttpServletRequest request, HttpServletResponse response) throws Exception {
		if (log.isDebugEnabled()) log.debug("Entering 'searchSubject' method");
		ActionForward forward = null;
		try {

			String[] tableArr = theForm.getSelTable();
			String fristtabId = tableArr[0];
			request.setAttribute("fristtabId", fristtabId);
			theForm.setTableId(fristtabId);
			List<Map<String, String>> tableSqlList = new ArrayList<Map<String, String>>();
			TableConfigPool tableConfigPool = (TableConfigPool) getBean("tableConfigPool");
			for (int i = 0; i < tableArr.length; i++) {
				Map<String, String> tablepoolMap = tableConfigPool.getTableMap(tableArr[i]);
				tableSqlList.add(tablepoolMap);
			}
			// 获取对应表的总记录数
			initForm(theForm, "SHOWOVERVIEW", request);
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

	public ActionForward listBacth(ActionMapping mapping, SubjectBatchQueryForm theForm, HttpServletRequest request, HttpServletResponse response) throws Exception {
		if (log.isDebugEnabled()) log.debug("Entering 'listCommBacth' method");
		ActionForward forward = null;
		try {
			List<Map<String, String>> queryColumnlist = new ArrayList<Map<String, String>>();
			QueryMetaIndPool queryMetaIndPool = (QueryMetaIndPool) getBean("queryMetaIndPool");
			SubjectConfigPool subjectConfigPool = (SubjectConfigPool) getBean("subjectConfigPool");
			SubjectConfigPoolObj subjectPoolObj = subjectConfigPool.getSubjectConfigPool(theForm.getId());
			List<SubjectColumnConfigPoolObj> paramPoolObj = subjectConfigPool.getSubjectColumnConfigPool(theForm.getId(),true);
			List<SubjectColumnConfigPoolObj> displayList = new ArrayList<SubjectColumnConfigPoolObj>();

			StringBuffer colnamebuffer = new StringBuffer();
			List<Map<String, String>> codeSetList = new ArrayList<Map<String, String>>();
			SynthesisColumnGenService synthesisColumnGenService = (SynthesisColumnGenService) getBean("synthesisColumnGenService");
			for (SubjectColumnConfigPoolObj pool : paramPoolObj) {
				Map<String, String> indMap = queryMetaIndPool.getIndicatorPool(pool.getIndId());
				String indId = pool.getIndId();
				if (indMap != null && pool.getBatchQuery() != null && !"".equals(pool.getBatchQuery()) && "1".equals(pool.getBatchQuery())) {
					Map<String, String> map = new HashMap<String, String>();
					map.put("columName", pool.getName());
					map.put("oper", pool.getFilterOperator());
					map.put("displayName", pool.getCnName());
					map.put("dataType", pool.getDataType());
					if (pool.getFilterOperator().equals(Const.FILTER_OPER_BETWEEN)) {
						colnamebuffer.append("col" + indId + "from,");
						colnamebuffer.append("col" + indId + "to,");
						map.put("width", "25");
					}
					else {
						colnamebuffer.append("col" + indId + ",");
						map.put("width", "12");
					}
					queryColumnlist.add(map);
					displayList.add(pool);
					// 代码集列表
					BussCodeSetPool bussCodeSetPool = (BussCodeSetPool) getBean("bussCodeSetPool");
					if (pool.getCodeSetNo() != null && !"".equals(pool.getCodeSetNo().trim())) {
						StandardCodeset codeset = bussCodeSetPool.getCodeSetByCodeNo(pool.getCodeSetNo());
						map.put("id", codeset.getId().toString());
						map.put("htmlcode", synthesisColumnGenService.getCodeSetHtml(pool, bussCodeSetPool, "dictsel"));
						codeSetList.add(map);
					}
				}
			}

			List<Map<String, String>> columnHtmlList = new ArrayList<Map<String, String>>();
			request.setAttribute("queryParamList", queryColumnlist);
			// request.setAttribute("tableList", subjectPoolObj.getTableMapList());
			if (subjectPoolObj.getSubType().equals(QuerySubject.SUBTYPE_COMM)) request.setAttribute("seltablestr", genenrateSelTableHtmlCode(theForm, subjectPoolObj.getTableMapList()));
			else {
				request.setAttribute("queryParamList", queryColumnlist);
				ResourceTableService resourceTableService = (ResourceTableService) getBean("resourceTableService");
				List<ResourceTable> list = resourceTableService.findTableByCatageoryId(subjectPoolObj.getScId());
				request.setAttribute("seltablestr", genenrateClassifySelTableHtmlCode(theForm, list));
			}
			if (!codeSetList.isEmpty()) request.setAttribute("codeSetList", codeSetList);
			request.setAttribute("columnhtml", genenrateHtmlCode(subjectPoolObj, displayList, theForm, columnHtmlList, synthesisColumnGenService));
			request.setAttribute("cellList", columnHtmlList);
			
			request.setAttribute("colArr", colnamebuffer.length()>0?colnamebuffer.substring(0, colnamebuffer.length() - 1):"");
			forward = mapping.findForward("COMMBATCH");

		}
		catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("err", e);
			forward = mapping.findForward(ERROR);
		}
		return forward;
	}

	public ActionForward querySubjectTotalCount(ActionMapping mapping, SubjectBatchQueryForm theForm, HttpServletRequest request, HttpServletResponse response) throws Exception {
		if (log.isDebugEnabled()) log.debug("Entering 'listBacth' method");
		ActionForward forward = null;
		try {
			List<Map> tablist = new ArrayList<Map>();

			String[] tabArr = theForm.getSelTable();
			SynthesisQueryService synthesisQueryService = (SynthesisQueryService) getBean("synthesisQueryService");
			TableConfigPool tableConfigPool = (TableConfigPool) getBean("tableConfigPool");
			SynthesisColumnGenService synthesisColumnGenService = (SynthesisColumnGenService) getBean("synthesisColumnGenService");
			for (int k = 0; k < tabArr.length; k++) {
				int totalcount = 0;
				Map<String, String> tablepoolMap = tableConfigPool.getTableMap(tabArr[k]);
				List<Map<String, String>> columnameList = new ArrayList<Map<String, String>>();
				List<String> colvalList = new ArrayList<String>();
				List<Map<String, String>> batchSqlList = getQueryParam(theForm, tabArr[k], columnameList, colvalList, synthesisColumnGenService);
				Map resultMap = new HashMap();
				int[] countArr = new int[batchSqlList.size()];
				for (int i = 0; i < batchSqlList.size(); i++) {
					String where = batchSqlList.get(i).get("sql");
					int count = 0;
					if (where!=null) {
						String sql = "select count(id) from " + where;
						count = synthesisQueryService.queryByInt(sql);
						countArr[i] = count;
						totalcount += count;
					}					
				}
				resultMap.put("columnList", columnameList);
				List<Map<String, Object>> colvalretList = new ArrayList<Map<String, Object>>();
				for (int j = 0; j < colvalList.size(); j++) {
					String str = colvalList.get(j);
					if ("".equals(str)) {
						
//						String[] strArr = new String[columnameList.size()];
//						List<Map<String, String>> tmplist = new ArrayList<Map<String, String>>();
//						for (int i = 0; i < strArr.length; i++) {
//							Map<String, String> tmpmap = new HashMap<String, String>();
//							tmpmap.put("value", "&nbsp;");
//							tmplist.add(tmpmap);
//						}
//						Map<String, Object> tmpmap2 = new HashMap<String, Object>();
//						tmpmap2.put("valArr", tmplist);
//						tmpmap2.put("count", String.valueOf(countArr[j]));
//						colvalretList.add(tmpmap2);
					}
					else {
						String[] strArr = str.split(";");
						List<Map<String, String>> tmplist = new ArrayList<Map<String, String>>();
						for (int i = 0; i < strArr.length; i++) {
							Map<String, String> tmpmap = new HashMap<String, String>();
							if (i == 0) tmpmap.put("showurl", "1");
							tmpmap.put("value", strArr[i]);
							tmplist.add(tmpmap);
						}
						if(strArr.length<columnameList.size()){
							for (int i = 0; i < columnameList.size()-strArr.length; i++) {
								Map<String, String> tmpmap = new HashMap<String, String>();
								tmpmap.put("value", " ");
								tmplist.add(tmpmap);
							}
						}
						Map<String, Object> tmpmap2 = new HashMap<String, Object>();
						//总数
						tmpmap2.put("valArr", tmplist);
						tmpmap2.put("count", String.valueOf(countArr[j]));
						colvalretList.add(tmpmap2);

					}
				}
				sortByCount(colvalretList);
				if (!colvalretList.isEmpty()) resultMap.put("valueList", colvalretList);
				resultMap.put("count", String.valueOf(totalcount));
				resultMap.put("cName", tablepoolMap.get("cName"));
				resultMap.put("cityName", tablepoolMap.get("cityName"));
				resultMap.put("catagoryName", tablepoolMap.get("catagoryName"));
				resultMap.put("id", tablepoolMap.get("id"));
				resultMap.put("totalcount", String.valueOf(totalcount));
				// resultMap.put("paramList", batchSqlList);
				tablist.add(resultMap);
			}
			request.setAttribute("resultList", tablist);
			// theForm.getQuery().setRecordSet(tablist);
			forward = mapping.findForward("TOTALCOUNT");

		}
		catch (Exception e) {
			request.setAttribute("err", e);
			forward = mapping.findForward(ERROR);
		}
		return forward;
	}

	public ActionForward querySubjectTableCount(ActionMapping mapping, SubjectBatchQueryForm theForm, HttpServletRequest request, HttpServletResponse response) throws Exception {
		if (log.isDebugEnabled()) log.debug("Entering 'listBacth' method");
		ActionForward forward = null;
		try {

			String tabId = theForm.getTableId();
			List<Map<String, String>> columnameList = new ArrayList<Map<String, String>>();
			List<String> colvalList = new ArrayList<String>();
			SynthesisColumnGenService synthesisColumnGenService = (SynthesisColumnGenService) getBean("synthesisColumnGenService");
			List<Map<String, String>> batchSqlList = getQueryParam(theForm, tabId, columnameList, colvalList, synthesisColumnGenService);

			// 获取对应表的总记录数
			SynthesisQueryService synthesisQueryService = (SynthesisQueryService) getBean("synthesisQueryService");

			for (int i = 0; i < batchSqlList.size(); i++) {
				if (batchSqlList.get(i).get("sql")==null) {
					batchSqlList.get(i).put("count", "未查询");
				}else {
					String sql = "select count(*) from " + batchSqlList.get(i).get("sql");
					int count = synthesisQueryService.queryByInt(sql);
					batchSqlList.get(i).put("count", String.valueOf(count));
				}
				
			}
			// 显示条件
			List<Column> columnList = new ArrayList<Column>();
			SubjectConfigPool subjectConfigPool = (SubjectConfigPool) getBean("subjectConfigPool");
			QueryMetaIndPool queryMetaIndPool = (QueryMetaIndPool) getBean("queryMetaIndPool");
			TableConfigPool tableConfigPool = (TableConfigPool) getBean("tableConfigPool");
			List<SubjectColumnConfigPoolObj> paramPoolObj = subjectConfigPool.getSubjectColumnConfigPool(theForm.getId(),true);
			List<ColumnPoolObj> columnpoolList = tableConfigPool.getTableColumnPool(tabId);

			CollectionMapConvert<ColumnPoolObj> convert = new CollectionMapConvert<ColumnPoolObj>();
			// 表中指标对应的字段
			Map<String, List<ColumnPoolObj>> colmap = convert.convertToMapByParentKey(columnpoolList, "indicatorId");
			for (SubjectColumnConfigPoolObj pool : paramPoolObj) {
				Map<String, String> indMap = queryMetaIndPool.getIndicatorPool(pool.getIndId());
				String indId = pool.getIndId();
				// 指标存在且表中包括该指标
				if (indMap != null && colmap.get(indId) != null) {
					Column col = new Column();
					col.setWidth("15%");
					col.setAlign("center");
					col.setName(pool.getCnName());
					col.setProperty(pool.getName());
					// if(columnList.isEmpty()){
					// col.setItemType("hyperlink");
					// col.setHref("#");
					// col.setOnClick("parent.showresult('{id}')");
					// }
					columnList.add(col);
				}
			}
			Column col = new Column();
			// col.setWidth("15%");
			col.setAlign("left");
			col.setName("数量");
			col.setProperty("count");
			columnList.add(col);

			request.setAttribute(Column.DYNAMICCOLUMNS, columnList);
			theForm.getQuery().setRecordSet(batchSqlList);
			// request.setAttribute("querycount", "1");

			forward = mapping.findForward("QUERYCOUNT");
		}
		catch (Exception e) {
			request.setAttribute("err", e);
			forward = mapping.findForward(ERROR);
		}
		return forward;
	}

	public ActionForward exportAllExcel(ActionMapping mapping, SubjectBatchQueryForm theForm, HttpServletRequest request, HttpServletResponse response)throws Exception{

		if (log.isDebugEnabled()) log.debug("Entering 'exportAllExcel' method");

		try {

			String tabId = theForm.getTableId();
			List<Map<String, String>> columnameList = new ArrayList<Map<String, String>>();
			List<String> colvalList = new ArrayList<String>();
			SynthesisColumnGenService synthesisColumnGenService = (SynthesisColumnGenService) getBean("synthesisColumnGenService");
			List<Map<String, String>> batchSqlList = getQueryParam(theForm, tabId, columnameList, colvalList, synthesisColumnGenService);

		// 显示条件
			List<Column> columnList = new ArrayList<Column>();
			SubjectConfigPool subjectConfigPool = (SubjectConfigPool) getBean("subjectConfigPool");
			QueryMetaIndPool queryMetaIndPool = (QueryMetaIndPool) getBean("queryMetaIndPool");
			TableConfigPool tableConfigPool = (TableConfigPool) getBean("tableConfigPool");
			List<SubjectColumnConfigPoolObj> paramPoolObj = subjectConfigPool.getSubjectColumnConfigPool(theForm.getId(),true);
			List<ColumnPoolObj> columnpoolList = tableConfigPool.getTableColumnPool(tabId);
			List<ColumnConfigPoolObj> columnpConfigPoolObjs = tableConfigPool.getColumnConfigPool(tabId);
			
			StringBuffer sb = new StringBuffer();
			List<String> columnCNNames = new ArrayList<String>();
			List<String> columnENNames = new ArrayList<String>();
			
			for (ColumnPoolObj columnPoolObj : columnpoolList) {
				for (ColumnConfigPoolObj columnConfigPoolObj : columnpConfigPoolObjs) {
					if (columnPoolObj.getId().equals(columnConfigPoolObj.getColumnId())&&Const.SYS_CODE_YES.equals(columnConfigPoolObj.getIsShown())) {
						sb.append(","+columnPoolObj.getName());
						columnCNNames.add(columnPoolObj.getCnName());
						columnENNames.add(columnPoolObj.getName());
					}
				}				
			}
			
			String showColumns = sb.length()==0?"*":sb.toString().substring(1);
			
			HSSFWorkbook wb = ExcelGenerator.GenerateExcelFile();
			
			
			// 获取对应表的各个条件结果集
			SynthesisQueryService synthesisQueryService = (SynthesisQueryService) getBean("synthesisQueryService");
			PageQuery pageQuery = theForm.getQuery();
			pageQuery.setPageSize("0");
			
			for (int i = 0; i < batchSqlList.size(); i++) {
				String sql = "select "+showColumns+" from " + batchSqlList.get(i).get("sql");
				String sheetName = batchSqlList.get(i).get("sheetName");
				if("".equals(sheetName)){sheetName="第"+i+"个条件";}
				
				List<Map<String,String>> result = synthesisQueryService.queryBySql(sql, pageQuery).getRecordSet();
				if (result!=null&&!result.isEmpty()) {
					ExcelSheetColProp prop = new ExcelSheetColProp();
					
					String[] headerName = new String[columnCNNames.size()];
					System.arraycopy(columnCNNames.toArray(), 0, headerName, 0, columnCNNames.toArray().length);
					prop.setHeaderName(headerName);
					
					String[] columnName = new String[columnENNames.size()];
					System.arraycopy(columnENNames.toArray(), 0, columnName, 0, columnENNames.toArray().length);
					prop.setColumnName(columnName);
					
					prop.setColumnList(result);
					prop.setSheetName(sheetName);
					ExcelGenerator.addSheet(wb, prop);
				}
			}
			
			response.setContentType("application/vnd.ms-excel;charset=utf-8");
			response.setHeader("Content-Disposition", "attachment;filename=datafile.xls");
			ServletOutputStream os = response.getOutputStream();
			wb.write(os);
			os.flush();
			os.close();
		}
		catch (Exception e) {
			request.setAttribute("err", e);
			return mapping.findForward(ERROR);
		}
		return null;
	
	}
	
	public ActionForward queryBatchCount(ActionMapping mapping, SubjectBatchQueryForm theForm, HttpServletRequest request, HttpServletResponse response) throws Exception {
		if (log.isDebugEnabled()) log.debug("Entering 'listBacth' method");
		ActionForward forward = null;
		try {
			String tabId = theForm.getTableId();
			String pos = theForm.getPos();
			List<Map<String, String>> columnameList = new ArrayList<Map<String, String>>();
			List<String> colvalList = new ArrayList<String>();
			SynthesisColumnGenService synthesisColumnGenService = (SynthesisColumnGenService) getBean("synthesisColumnGenService");
			List<Map<String, String>> tableSqlList = getQueryParam(theForm, tabId, columnameList, colvalList, synthesisColumnGenService);
			SynthesisQueryService synthesisQueryService = (SynthesisQueryService) getBean("synthesisQueryService");
			TableConfigPool tableConfigPool = (TableConfigPool) getBean("tableConfigPool");
			BussCodeSetPool bussCodeSetPool = (BussCodeSetPool) getBean("bussCodeSetPool");
			List<ColumnPoolObj> columnpoolList = tableConfigPool.getTableColumnPool(theForm.getTableId());
			List<ColumnConfigPoolObj> columncfgpoolList = tableConfigPool.getColumnConfigPool(theForm.getTableId());
			StringBuffer buffer = new StringBuffer();
			CollectionMapConvert<ColumnPoolObj> convert = new CollectionMapConvert<ColumnPoolObj>();
			Map<String, ColumnPoolObj> map = convert.convertListToMap(columnpoolList, "id");
			StringBuffer widthbuffer = new StringBuffer();
			StringBuffer dnamebuffer = new StringBuffer();
			StringBuffer alignbuffer = new StringBuffer();
			StringBuffer typebuffer = new StringBuffer();
			List<String> colnameList = new ArrayList<String>();
			// 主键字段
			// ColumnPoolObj pkobj=null;
			List<String> displayList = new ArrayList<String>();
			ResourceColumnService resourceColumnService = (ResourceColumnService) getBean("resourceColumnService");
			ResourceColumn pkobj = resourceColumnService.findResoucrePKColumn(Integer.valueOf(theForm.getTableId()));

			for (ColumnConfigPoolObj pool : columncfgpoolList) {
				ColumnPoolObj colpool = map.get(pool.getColumnId());
				if (pool.getIsSubject() != null && "1".equals(pool.getIsSubject())) {
					buffer.append(colpool.getName() + ",");
					colnameList.add(colpool.getName());
					dnamebuffer.append(colpool.getCnName() + ",");
					alignbuffer.append("center,");
					displayList.add(colpool.getName());
					typebuffer.append(synthesisColumnGenService.getDisplayDataType(colpool) + ",");
					if (pool.getDisplayWidth() != null && !"".equals(pool.getDisplayWidth())) widthbuffer.append(pool.getDisplayWidth() + ",");
					else widthbuffer.append("100,");
				}

			}
			String colstr = "";
			String cnname = "";

			// 显示字段是否包括主键,未包括就加入最前面
			if (pkobj != null && displayList.contains(pkobj.getName())) {
				colstr = buffer.toString().substring(0, buffer.length() - 1);
				cnname = dnamebuffer.substring(0, dnamebuffer.length() - 1);
			}
			else {
				colstr = pkobj.getName() + "," + buffer.toString().substring(0, buffer.length() - 1);
				cnname = pkobj.getCnName() + "," + dnamebuffer.substring(0, dnamebuffer.length() - 1);
				colnameList.add(pkobj.getName());
			}

			String sql = "select " + colstr + " from " + tableSqlList.get(Integer.parseInt(pos) - 1).get("sql");//
			PageQuery pageQuery = theForm.getQuery();
			List<Map<String, String>> list = synthesisQueryService.queryBySql(sql, pageQuery).getRecordSet();
			// 过滤代码集
			bussCodeSetPool.filterBussCodeSet(list, columnpoolList);

			forward = mapping.findForward("QUERYLIST");

		}
		catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("err", e);
			forward = mapping.findForward(ERROR);
		}
		return forward;

	}

	public ActionForward queryRecordList(ActionMapping mapping, SubjectBatchQueryForm theForm, HttpServletRequest request, HttpServletResponse response) throws Exception {
		if (log.isDebugEnabled()) log.debug("Entering 'searchSubject' method");
		ActionForward forward = null;

		try {
			SynthesisQueryService synthesisQueryService = (SynthesisQueryService) getBean("synthesisQueryService");
			TableConfigPool tableConfigPool = (TableConfigPool) getBean("tableConfigPool");
			BussCodeSetPool bussCodeSetPool = (BussCodeSetPool) getBean("bussCodeSetPool");
			List<ColumnPoolObj> columnpoolList = tableConfigPool.getTableColumnPool(theForm.getTableId());
			List<ColumnConfigPoolObj> columncfgpoolList = tableConfigPool.getColumnConfigPool(theForm.getTableId());
			StringBuffer buffer = new StringBuffer("");
			CollectionMapConvert<ColumnPoolObj> convert = new CollectionMapConvert<ColumnPoolObj>();
			Map<String, ColumnPoolObj> map = convert.convertListToMap(columnpoolList, "id");
			StringBuffer widthbuffer = new StringBuffer();
			StringBuffer dnamebuffer = new StringBuffer();
			StringBuffer alignbuffer = new StringBuffer();
			StringBuffer typebuffer = new StringBuffer();
			List<String> colnameList = new ArrayList<String>();
			String pos = theForm.getPos();
			
			//判断用户是否有权限访问该表
			String tableLevel = tableConfigPool.getTableMap(theForm.getTableId()).get("securityLevel");
			if (!synthesisQueryService.checkUserTableSecurityLevel(tableLevel, getCurrentUser(request))) {		
				request.setAttribute("msg", "该表的安全等级为:【"+tableLevel+"】，您的权限不够！");
				return mapping.findForward("BLANK");
			}
			
			// 主键字段
			// ColumnPoolObj pkobj=null;
			ResourceColumnService resourceColumnService = (ResourceColumnService) getBean("resourceColumnService");
			ResourceColumn pkobj = resourceColumnService.findResoucrePKColumn(Integer.valueOf(theForm.getTableId()));
			
			
			List<Column> columnList = new ArrayList<Column>();
			// checkbox
			Column checkcol = new Column();
			checkcol.setWidth("35px");
			checkcol.setAlign("center");
			checkcol.setItemType("checkbox");
			checkcol.setProperty("id");
			columnList.add(checkcol);
			List<String> displayList = new ArrayList<String>();
			SynthesisColumnGenService synthesisColumnGenService = (SynthesisColumnGenService) getBean("synthesisColumnGenService");

			//
			for (ColumnConfigPoolObj pool : columncfgpoolList) {
				ColumnPoolObj colpool = map.get(pool.getColumnId());
				if (pool.getIsSubject() != null && "1".equals(pool.getIsSubject())) {
					if (pkobj != null && colpool.getId().equals(pkobj.getId().toString())) buffer.append(colpool.getName() + " as id,");
					else buffer.append(colpool.getName() + ",");
					colnameList.add(colpool.getName());
					dnamebuffer.append(colpool.getCnName() + ",");
					alignbuffer.append("center,");
					displayList.add(colpool.getName());
					typebuffer.append(synthesisColumnGenService.getDisplayDataType(colpool) + ",");
					if (pool.getDisplayWidth() != null && !"".equals(pool.getDisplayWidth())) widthbuffer.append(pool.getDisplayWidth() + ",");
					else widthbuffer.append("100,");
					// grid2配置
					Column col = new Column();

					// col.setWidth("15%");

					col.setAlign("center");
					col.setName(colpool.getCnName());
					col.setProperty(colpool.getName());
					if (pool.getIsSortable() != null && pool.getIsSortable().equals("1")) {
						col.setHeaderOnMouseOut("headerOut(this)");
						col.setHeaderOnClick("query('" + colpool.getName() + "')");
						col.setHeaderOnMouseOver("headerOver(this)");
					}
					if (columnList.isEmpty()) {
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
			if (pkobj != null && displayList.contains(pkobj.getName())) {
				colstr = buffer.toString().substring(0, buffer.length() - 1);
				cnname = dnamebuffer.substring(0, dnamebuffer.length() - 1);
			}
			else {
				colstr = pkobj.getName() + " as id," + (buffer.length() > 0 ? buffer.toString().substring(0, buffer.length() - 1) : "");
				cnname = pkobj.getCnName() + "," + (dnamebuffer.length() > 0 ? dnamebuffer.substring(0, dnamebuffer.length() - 1) : "");
				colnameList.add(pkobj.getName());
			}
			List<Map<String, String>> columnameList = new ArrayList<Map<String, String>>();
			List<String> colvalList = new ArrayList<String>();
			List<Map<String, String>> tableSqlList = getQueryParam(theForm, theForm.getTableId(), columnameList, colvalList, synthesisColumnGenService);
			String whereSql = tableSqlList.get(Integer.valueOf(pos)).get("sql");
			if (whereSql==null) {
				request.setAttribute("msg", "未能找到关联的数据指标，该表未查询。");
				return mapping.findForward("BLANK");
			}
			
			if (colstr.endsWith(",")) {
				colstr = colstr.substring(0,colstr.length()-1);
			}
			String sql = "select " + colstr + " from " + whereSql;
			PageQuery pageQuery = theForm.getQuery();
			List<Map<String, String>> list = synthesisQueryService.queryBySql(sql, pageQuery).getRecordSet();

			setPage(theForm.getQuery());
			// 取出该页id集合
			StringBuffer idArr = new StringBuffer();
			for (Map<String, String> idmap : list) {
				idArr.append(idmap.get("id") + ",");
			}
			request.setAttribute("idArr", idArr.length() > 0 ? idArr.substring(0, idArr.length() - 1) : "");
			// 过滤代码集
			bussCodeSetPool.filterBussCodeSet(list, columnpoolList);
			// 过滤安全等级字段
			Session currentUser = (Session) request.getSession().getAttribute(Const.SESSION);
			synthesisColumnGenService.filterSecurityLevel(theForm.getTableId(), list, columnpoolList, pkobj, currentUser);
			theForm.getQuery().setRecordSet(list);
			request.setAttribute(Column.DYNAMICCOLUMNS, columnList);
			forward = mapping.findForward("QUERYLIST");

		}
		catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("err", e);
			forward = mapping.findForward(ERROR);
		}
		return forward;
	}

	private String genenrateHtmlCode(SubjectConfigPoolObj subjectPoolObj, List<SubjectColumnConfigPoolObj> configList, SubjectBatchQueryForm theForm, List<Map<String, String>> columnHtmlList,
			SynthesisColumnGenService synthesisColumnGenService) {
		StringBuffer buffer = new StringBuffer();
		String onecoltd = "<td>";
		String selcoltd = "<td class=\"sel\" >";

		String tdend = "</td>";

		BussCodeSetPool bussCodeSetPool = (BussCodeSetPool) getBean("bussCodeSetPool");
		String jsonStr = theForm.getRecord().get("paramjson");

		// String
		// linktd="<a href=\"javascript:void(0);\" class=\"sbuBtnStyle\" onclick=\"delRow(this);return false;\"><strong><span class=\"delIcon\">&nbsp;</span>删除</strong></a>";
		String linktd = "<input type=\"checkbox\" name=\"deletebox\"/>";
		boolean hasrecord = true;
		if (jsonStr != null && !"".equals(jsonStr)) {
			JSONArray array = null;
			array = JSONArray.fromObject(jsonStr);
			Iterator<JSONObject> iter = array.iterator();
			// 有查询数据

			while (iter.hasNext()) {
				JSONObject jsonObj = iter.next();
				buffer.append("<tr>");
				buffer.append("<td width=\"3%\" class=\"checkbox\">" + linktd + tdend);
				for (SubjectColumnConfigPoolObj pool : configList) {
					String htmlcode = synthesisColumnGenService.getBatchInputHtmlCode(pool, bussCodeSetPool, theForm, jsonObj);
					// Map<String, String> map=new HashMap<String, String>();
					// map.put("code", htmlcode);
					// columnHtmlList.add(map);
					if (pool.getCodeSetNo() != null && !"".equals(pool.getCodeSetNo())) buffer.append(selcoltd + htmlcode + tdend);
					else buffer.append(onecoltd + htmlcode + tdend);
				}

			}
		}
		else {
			buffer.append("<tr>");
			hasrecord = false;
		}
		if (!hasrecord) buffer.append("<td width=\"3%\" class=\"checkbox\">" + linktd + tdend);
		Map<String, String> map = new HashMap<String, String>();
		map.put("code", linktd);
		columnHtmlList.add(map);
		for (SubjectColumnConfigPoolObj pool : configList) {
			String htmlcode = synthesisColumnGenService.getBatchInputHtmlCode(pool, bussCodeSetPool, theForm, null);
			Map<String, String> tmpmap = new HashMap<String, String>();
			tmpmap.put("code", htmlcode);
			columnHtmlList.add(tmpmap);
			if (!hasrecord) {
				if (pool.getCodeSetNo() != null && !"".equals(pool.getCodeSetNo())) buffer.append(selcoltd + htmlcode + tdend);
				else buffer.append(onecoltd + htmlcode + tdend);
			}
		}

		return buffer.toString();
	}

	private List<Map<String, String>> getQueryParam(SubjectBatchQueryForm theForm, String tabId, List<Map<String, String>> colnameList, List<String> colvalList,
			SynthesisColumnGenService synthesisColumnGenService) throws Exception {

		List<Map<String, String>> tableSqlList = new ArrayList<Map<String, String>>();

		//字段缓存对象
		QueryMetaIndPool queryMetaIndPool = (QueryMetaIndPool) getBean("queryMetaIndPool");//指标
		SubjectConfigPool subjectConfigPool = (SubjectConfigPool) getBean("subjectConfigPool");//主题
		TableConfigPool tableConfigPool = (TableConfigPool) getBean("tableConfigPool");//表配置
		
		BaseSqlGen sqlGen = (BaseSqlGen) getBean("sybaseSqlGen");
		
		List<SubjectColumnConfigPoolObj> paramPoolObj = subjectConfigPool.getSubjectColumnConfigPool(theForm.getId(),true);
		List<ColumnPoolObj> columnpoolList = tableConfigPool.getTableColumnPool(tabId);//表字段
		Map<String, String> tablepoolMap = tableConfigPool.getTableMap(tabId);//表信息
		CollectionMapConvert<ColumnPoolObj> convert = new CollectionMapConvert<ColumnPoolObj>();
		// 表中指标对应的字段
		Map<String, List<ColumnPoolObj>> map = convert.convertToMapByParentKey(columnpoolList, "indicatorId");
		String jsonStr = theForm.getRecord().get("paramjson");

		JSONArray array = null;
		if (jsonStr != null) array = JSONArray.fromObject(jsonStr);
		Iterator<JSONObject> iter = array.iterator();
		int pos = 0;
		// List<Map<String,String>> colnameList=new
		// ArrayList<Map<String,String>>();
		// List<String> colvalList=new ArrayList<String>();

		for (SubjectColumnConfigPoolObj pool : paramPoolObj) {
			Map<String, String> indMap = queryMetaIndPool.getIndicatorPool(pool.getIndId());
			String indId = pool.getIndId();
			if (indMap != null && map.get(indId) != null) {
				Map<String, String> queryParamMap = new HashMap<String, String>();
				queryParamMap.put("columnName", pool.getCnName());
				colnameList.add(queryParamMap);
			}else {
				
			}
		}
		while (iter.hasNext()) {
			StringBuffer colvalpartbuffer = new StringBuffer();
			// StringBuffer displayquerybuf=new StringBuffer();
			JSONObject jsonObj = iter.next();
			StringBuffer partBuffer = new StringBuffer();
			StringBuffer sheetName = new StringBuffer();
			Map<String, String> tableMap = new HashMap<String, String>();
			tableMap.put("id", String.valueOf(pos));
			// tableMap.put("cName", "条件"+pos);
			for (SubjectColumnConfigPoolObj pool : paramPoolObj) {
				Map<String, String> indMap = queryMetaIndPool.getIndicatorPool(pool.getIndId());
				String indId = pool.getIndId();
				// 指标存在且表中包括该指标
				if (indMap != null && map.get(indId) != null) {
					List<ColumnPoolObj> list = map.get(indId);
					List<QueryParam> paramList = new ArrayList<QueryParam>();
					List<QueryParam> param2 = new ArrayList<QueryParam>();
					StringBuffer sb = new StringBuffer("");
					// 前台显示查询条件串
					String colid = "col" + indId;

					Map<String, String> queryParamValueMap = new HashMap<String, String>();

					if (Const.FILTER_OPER_BETWEEN.equals(pool.getFilterOperator())) {
						String val1 = jsonObj.getString(colid + "from");
						String val2 = jsonObj.getString(colid + "to");
						tableMap.put(pool.getName(), val1 + "-" + val2);
						sheetName.append(","+val1 + "-" + val2);
						queryParamValueMap.put("values", val1 + "-" + val2);
						colvalpartbuffer.append(val1 + "-" + val2 + ";");
						// displayquerybuf.append(pool.getCnName()+":{"+val1+","+val2+"}&nbsp;&nbsp;");
					}
					else {
						String val = jsonObj.getString("col" + indId);
						tableMap.put(pool.getName(), val);
						sheetName.append(","+val);
						queryParamValueMap.put("values", val);
						colvalpartbuffer.append(val + ";");
						// displayquerybuf.append(pool.getCnName()+"="+val+"&nbsp;&nbsp;");
					}
					for (ColumnPoolObj colpool : list) {
						if (pool.getFilterOperator().equals(Const.FILTER_OPER_BETWEEN)) {
							String val1 = jsonObj.getString("col" + indId + "from");
							String val2 = jsonObj.getString("col" + indId + "to");
							if ((val1 != null && !"".equals(val1)) || (val2 != null && !"".equals(val2))) {
								paramList.add(new QueryParam(colpool.getName(), synthesisColumnGenService.filterColumnType(colpool), pool.getFilterOperator(), val1 + ";" + val2, ""));
							}
						}
						else {
							String val = jsonObj.getString("col" + indId);
							// 对于除时between外的条件均用=查询
							if (val != null && !"".equals(val)){
								paramList.add(new QueryParam(colpool.getName(), synthesisColumnGenService.filterColumnType(colpool), "=", val, "")); // pool.getFilterOperator()
								if (Const.INDICATOR_NAME.equalsIgnoreCase(indMap.get("code"))) {								
									String[] xms = val.split("[\\s]+");
//									if (xms.length==2) {
//										paramList.add(new QueryParam(colpool.getName(), synthesisColumnGenService.filterColumnType(colpool), pool.getFilterOperator(), xms[1]+" "+xms[0], ""));
//									}else if (xms.length==3) {
//										paramList.add(new QueryParam(colpool.getName(), synthesisColumnGenService.filterColumnType(colpool), pool.getFilterOperator(), xms[2]+" "+xms[1]+" "+xms[0], ""));
//									}
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
						}
					}
					String gensql = sqlGen.getQueryStringPart(paramList, QueryParam.OPER_OR);
	
					if (gensql != null && !"".equals(gensql.trim())) {
//						String spSQL = "";
//						if (!param2.isEmpty()) {
//							spSQL = sqlGen.getQueryStringPart(param2,QueryParam.OPER_AND);
//							if (spSQL!=null&&!"".equals(spSQL)) {
//								spSQL = " or (" +spSQL + ") ";
//							}else {
//								spSQL = "";
//							}
//						}
						
//						gensql += spSQL;
						gensql += sb.toString();
						
						partBuffer.append("(" + gensql + ")" + QueryParam.OPER_AND);
					}
				}

			}
			String colval = colvalpartbuffer.length() > 0 ? colvalpartbuffer.substring(0, colvalpartbuffer.length() - 1) : "";
			colvalList.add(colval);

			String querysql = partBuffer.length() > 0 ? " and " + partBuffer.substring(0, partBuffer.length() - 5) : "";
			if (querysql.length() > 0)tableMap.put("sql", tablepoolMap.get("name") + " where 1=1" + querysql);

			tableMap.put("sheetName", sheetName.length()>1?sheetName.substring(1).toString():"");
			tableMap.put("pos", String.valueOf(pos));
			tableSqlList.add(tableMap);
			pos++;
		}
		return tableSqlList;

	}

	private void initForm(SubjectBatchQueryForm theForm, String action, HttpServletRequest request) throws Exception {
		if (action.equalsIgnoreCase("SHOWOVERVIEW")) {

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

	private String genenrateSelTableHtmlCode(SubjectBatchQueryForm theForm, List<Map<String, String>> tablSqlList) {
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

	private String genenrateClassifySelTableHtmlCode(SubjectBatchQueryForm theForm, List<ResourceTable> tablSqlList) {
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

	/*
	 * 判断要创建的表是否已经存在，若存在告知用户 若不存在则建表，然后执行导数据线程 等待一段时间，看导入数据量与现有结果集数量是否一致
	 * 一致则说明导入成功，不一致则说明导入失败，将表drop掉
	 */
	private synchronized boolean ProcessGenTableAndRecord(List<ColumnPoolObj> columnpoolList, String tableName, ResourceColumn pkobj, Map<String, String> tableMap, Session session,SubjectBatchQueryForm theForm) {
		boolean isSuccess = false;
		SqlScriptGenerator generator = (SqlScriptGenerator) WebContextBeanFactory.getBean("oracleScriptGen");
		SynthesisTempSpaceDao synthesisTempSpaceDao = (SynthesisTempSpaceDao) WebContextBeanFactory.getBean("synthesisTempSpaceDao");
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
			SynthesisQueryService synthesisQueryService = (SynthesisQueryService) WebContextBeanFactory.getBean("synthesisQueryService");
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
			SynthesisColumnGenService synthesisColumnGenService = (SynthesisColumnGenService) WebContextBeanFactory.getBean("synthesisColumnGenService");
			synthesisColumnGenService.filterSecurityLevel(tableMap.get("id"), list, columnpoolList, pkobj, session);
			// 批量插入数据
			String insertSql = "insert into " + tableName + " values (" + pbuffer.substring(0, pbuffer.length() - 1) + ")";
			synthesisTempSpaceDao.batchUpdate(insertSql, list, columnpoolList);

			int count = synthesisTempSpaceDao.queryCountBySql("select count(*) from " + tableName);
			if (count == list.size()) {
				isSuccess = true;
			}
			else {
				synthesisTempSpaceDao.dropTable(tableName);
				isSuccess = false;
			}

		}
		catch (Exception e) {
			if (synthesisTempSpaceDao.isTableExist(tableName)) {
				synthesisTempSpaceDao.dropTable(tableName);
			}
			e.printStackTrace();
			log.error(e);
		}

		return isSuccess;
	}
	
	private void sortByCount(List<Map<String, Object>> src){
		//排序之前为每个对象加一个顺序号，用以记录排序之前的顺序
		for (int i = 0; i < src.size(); i++) {
			src.get(i).put("colind", Integer.valueOf(i));
		}
		
		Map<String, Object> temp = new HashMap<String, Object>();
		for (int i = 0; i < src.size(); i++) {
			for (int j = i+1; j < src.size(); j++) {
				int isrc = Integer.parseInt((String)src.get(i).get("count"));
				int jsrc = Integer.parseInt((String)src.get(j).get("count"));
				
				if (jsrc>isrc) {
					temp = src.get(i);
					src.set(i, src.get(j));
					src.set(j, temp);
				}
			}
		}
	}
}
