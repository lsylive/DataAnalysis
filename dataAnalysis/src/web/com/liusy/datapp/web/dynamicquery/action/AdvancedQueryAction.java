package com.liusy.datapp.web.dynamicquery.action;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.dom4j.DocumentHelper;
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
import com.liusy.datapp.model.datastandard.StandardCode;
import com.liusy.datapp.model.resource.ResourceColumn;
import com.liusy.datapp.model.resource.ResourceTable;
import com.liusy.datapp.model.system.config.SysCity;
import com.liusy.datapp.service.dynamicquery.SynthesisColumnGenService;
import com.liusy.datapp.service.pool.BussCodeSetPool;
import com.liusy.datapp.service.pool.QueryMetaIndPool;
import com.liusy.datapp.service.pool.SubjectConfigPool;
import com.liusy.datapp.service.pool.TableConfigPool;
import com.liusy.datapp.service.query.QueryParamCfgService;
import com.liusy.datapp.service.query.SynthesisQueryService;
import com.liusy.datapp.service.resource.ResourceColumnService;
import com.liusy.datapp.service.resource.ResourceTableService;
import com.liusy.datapp.service.system.config.SysCodeService;
import com.liusy.datapp.service.thread.QueryResultTableSaveThread;
import com.liusy.datapp.util.ThreadPool;
import com.liusy.datapp.util.meta.SqlScriptGenerator;
import com.liusy.datapp.util.poolobj.ColumnConfigPoolObj;
import com.liusy.datapp.util.poolobj.ColumnPoolObj;
import com.liusy.datapp.util.poolobj.QueryParamPoolObj;
import com.liusy.datapp.util.poolobj.SubjectColumnConfigPoolObj;
import com.liusy.datapp.web.BaseAction;
import com.liusy.datapp.web.dynamicquery.form.AdvancedQueryForm;
import com.liusy.datapp.web.dynamicquery.form.CommSubjectQueryForm;
import com.liusy.web.tag.grid.Column;

public class AdvancedQueryAction extends BaseAction {

	@Override
	public ActionForward doExecute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		String action = request.getParameter("action");
		if (action == null || "".equals(action)) {
			action = LIST;
		}
		if (log.isDebugEnabled()) log.debug("action:" + action);

		ActionForward forward;
		AdvancedQueryForm advancedQueryForm = (AdvancedQueryForm) form;

		try {
			if (LIST.equalsIgnoreCase(action)) {
				forward = searchResourceTable(mapping, advancedQueryForm, request, response); // �򿪲�ѯ��Դ���б�ҳ��
			}
			else if ("TREE".equalsIgnoreCase(action)) {
				returnTreeNode(response, request);
				return null;
			}
			else if ("MAIN".equalsIgnoreCase(action)) {
				return mapping.findForward("MAIN");
			}
			// else if ("SELECT".equalsIgnoreCase(action))
			// {
			// getCategoryForSelection(request, response);
			// return null;
			// }
			else if ("TOADVANCEDPAGE".equalsIgnoreCase(action)) {
				forward = toAdvancePage(mapping, advancedQueryForm, request, response);
			}
			else if ("BASEQUERY".equalsIgnoreCase(action)) {
				forward = baseQuery(mapping, advancedQueryForm, request, response);
			}
			else if ("SAVERESULT".equalsIgnoreCase(action)) {
				forward = saveResult(mapping, advancedQueryForm, request, response);
			}
			else if ("EXPORTEXCEL".equalsIgnoreCase(action)) {
				forward = exportExcel(mapping, advancedQueryForm, request, response);
			}
			else if ("SHOWEXPORTEXCEL".equalsIgnoreCase(action)) {
				forward = showExportExcel(mapping, advancedQueryForm, request, response);
			}
			else if ("CHECKCNNAME".equalsIgnoreCase(action)) {
				forward = checkCnName(mapping, advancedQueryForm, request, response);
			}
			else {
				request.setAttribute("err", new WebException("�Ҳ�����action������" + action));
				forward = mapping.findForward(ERROR);// �Ҳ������ʵ�action
			}

			Session session = (Session) request.getSession().getAttribute(Const.SESSION);
			// �ж��Ƿ����ø��˿ռ�
			Integer spaceId = 0;
			if (session.getSpaceId() == null || "".equals(session.getSpaceId().trim()) || Const.TAG_DISABLE.equals(session.getSpaceId())) {
				request.setAttribute("hasspace", "0");
			}
			else {
				request.setAttribute("hasspace", "1");
			}
		}
		catch (Exception e) {// ����ϵͳ����

			e.printStackTrace();
			request.setAttribute("err", e);
			forward = mapping.findForward(ERROR);
		}
		return forward;
	}

	private ActionForward checkCnName(ActionMapping mapping, AdvancedQueryForm theForm, HttpServletRequest request, HttpServletResponse response) throws Exception {
		if (log.isDebugEnabled()) log.debug("Entering 'checkCnName' method");
		String retStr = "";
		String cnName = request.getParameter("cnName");
		if (cnName == null) {
			cnName = "";
		}

		
		ResourceTableService resourceTableService = (ResourceTableService) getBean("resourceTableService");

		List<ResourceTable> list = resourceTableService.findByField(ResourceTable.PROP_CN_NAME, cnName);
		if (list != null && !list.isEmpty()) {
			retStr = "false";
		}
		response.setContentType("text/plain;charset=UTF-8");
		response.setHeader("Cache_Control", "no-cache");
		response.getWriter().write(retStr);
		return null;
	}

	public ActionForward saveResult(ActionMapping mapping, AdvancedQueryForm theForm, HttpServletRequest request, HttpServletResponse response) throws Exception {
		if (log.isDebugEnabled()) log.debug("Entering 'saveResult' method");
		ActionForward forward = null;
		boolean processOk = true;
		String retStr = "OK";
		String ename = getCurrentUser(request).getAccountName() + "_" + DateUtil.getDateTime("yyyyMMddHHmmss", new Date(System.currentTimeMillis()));
		String cname = request.getParameter("tabcname");
		try {
			List<Map<String, Object>> queryColumnlist = new ArrayList<Map<String, Object>>();
			String tableId = theForm.getQuery().getParameters().get("tableId");
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
			String params = theForm.getQuery().getParameters().get("pramaStr");
			Map<String, String> queryValue = new HashMap<String, String>();
			initParamsValues(queryValue, params);
			List<Map<String, String>> queryParames = initFormForQuery(theForm, tableId, "base",null);
			String sqlWhere = getWhereStrForBase(queryParames, queryValue, false,false);
			Map<String, String> tableMap = new HashMap<String, String>();
			tableMap.put("id", tableId);
			tableMap.put("sql", tableConfigPool.getTableMap(tableId).get("name") + " " + sqlWhere);

			// Map<String, String> tableMap = getQueryParam(queryColumnlist,
			// theForm, tableId, synthesisColumnGenService);

			Session session = (Session) request.getSession().getAttribute(Const.SESSION);
			if (session.getSpaceId() != null && !"".equals(session.getSpaceId())) {
				// ��ʱ�������˿ռ�
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
				// ��ʼ��̨д���ѯ���
				ResourceColumnService resourceColumnService = (ResourceColumnService) getBean("resourceColumnService");
				ResourceColumn pkobj = resourceColumnService.findResoucrePKColumn(Integer.valueOf(tableId));

				if (ProcessGenTableAndRecord(tableConfigPool.getTableColumnPool(tableId), ename, pkobj, tableMap, session,table,theForm)) {
					// ����������ݶ��ɹ�
					processOk = true;
					retStr = "OK";
				}
				else {
					resourceTableService.removeResourceTable(table.getId());
					processOk = false;
					retStr = "REQUIRED";
				}

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

	private ActionForward showExportExcel(ActionMapping mapping, AdvancedQueryForm theForm, HttpServletRequest request, HttpServletResponse response) throws Exception {
		if (log.isDebugEnabled()) log.debug("Entering 'showExportExcel' method");
		return mapping.findForward("SHOWEXPORTEXCEL");
	}

	private ActionForward exportExcel(ActionMapping mapping, AdvancedQueryForm theForm, HttpServletRequest request, HttpServletResponse response) throws Exception {
		if (log.isDebugEnabled()) log.debug("Entering 'saveResult' method");

		String ftype = request.getParameter("ftype");
		try {
			String tableId = theForm.getQuery().getParameters().get("tableId");
			TableConfigPool tableConfigPool = (TableConfigPool) getBean("tableConfigPool");

			List<ColumnPoolObj> columnList = tableConfigPool.getTableColumnPool(tableId);

			SynthesisColumnGenService synthesisColumnGenService = (SynthesisColumnGenService) getBean("synthesisColumnGenService");
			String params = theForm.getQuery().getParameters().get("pramaStr");
			Map<String, String> queryValue = new HashMap<String, String>();
			initParamsValues(queryValue, params);
			List<Map<String, String>> queryParames = initFormForQuery(theForm, tableId, "base",null);
			String sqlWhere = getWhereStrForBase(queryParames, queryValue, false,false);
			Map<String, String> tableMap = new HashMap<String, String>();
			tableMap.put("id", tableId);
			tableMap.put("name", tableConfigPool.getTableMap(tableId).get("name"));
			tableMap.put("sql", tableConfigPool.getTableMap(tableId).get("name") + " " + sqlWhere);

			SynthesisQueryService synthesisQueryService = (SynthesisQueryService) getBean("synthesisQueryService");
			String sqlstr = "select * from " + tableMap.get("sql");
			PageQuery pageQuery = new PageQuery();
			pageQuery.setPageSize("0");
			List<Map<String, String>> resultList = synthesisQueryService.queryBySql(sqlstr, pageQuery).getRecordSet();
			// ���ݰ�ȫ�ȼ����ˣ���֤��ȫ
			Session session = (Session) request.getSession().getAttribute(Const.SESSION);
			ResourceColumnService resourceColumnService = (ResourceColumnService) getBean("resourceColumnService");
			ResourceColumn pkobj = resourceColumnService.findResoucrePKColumn(Integer.valueOf(tableId));
			synthesisColumnGenService.filterSecurityLevel(tableMap.get("id"), resultList, columnList, pkobj, session);

			if ("0".equals(ftype)) {
				// ����excel�ļ�
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
				// TODO:Ĭ�ϴ�2��һ�п�ʼ��ȡ
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
				// ����csv�ļ�
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
		return null;
	}

	private ActionForward searchResourceTable(ActionMapping mapping, AdvancedQueryForm advancedQueryForm, HttpServletRequest request, HttpServletResponse response) throws Exception {
		if (log.isDebugEnabled()) log.debug("Entering 'searchResource' method");
		try {
			Map<String, String> params = advancedQueryForm.getQuery().getParameters();
			String treeId = request.getParameter("treeId");
			if (treeId != null && !"".equals(treeId)) {
				if (treeId.indexOf("_") > -1) {
					String[] ids = treeId.split("_");
					if (ids.length == 2) {
						params.put("cityCode", ids[0]);
						params.put("tradeId", ids[1]);
					}
					else if (ids.length == 3) {
						params.put("cityCode", ids[0]);
						params.put("tradeId", ids[1]);
						params.put("categoryId", ids[2]);
					}
					else if (ids.length > 3) {
						params.put("cityCode", ids[0]);
						params.put("tradeId", ids[ids.length - 2]);
						params.put("categoryId", ids[ids.length - 1]);
					}
				}
				else {
					params.put("cityCode", treeId);
				}
			}

			ResourceTableService ResourceTableService = (ResourceTableService) getBean("resourceTableService");
			PageQuery pageQuery = advancedQueryForm.getQuery();
			pageQuery.setSelectParamId("GET_RESOURCETABLE_FOR_ADVANCEDQUERY");
			String queryString = getQueryString(pageQuery);
			pageQuery.getParameters().put("queryString", queryString);
			// ִ�в�ѯ����������浽pagequery�У��������һ��list<map>������map��ÿ���ֶ�����key��ֵ��value��������
			ResourceTableService.queryForAdvancedQuery(pageQuery);
			setPage(pageQuery);
			initForm(advancedQueryForm);
			return mapping.findForward(LIST);
		}
		catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("err", e);
			return mapping.findForward(ERROR);
		}
	}

	public ActionForward toAdvancePage(ActionMapping mapping, AdvancedQueryForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			String tableId = request.getParameter("tableId");
			if (tableId == null || "".equals(tableId)) {
				tableId = form.getQuery().getParameters().get("tableId");
			}
			String tempTableId = tableId;
			
			//�ж��û��Ƿ���Ȩ�޷��ʸñ�
			SynthesisQueryService synthesisQueryService = (SynthesisQueryService) getBean("synthesisQueryService");
			TableConfigPool tableConfigPool = (TableConfigPool) getBean("tableConfigPool");
			String tableLevel = tableConfigPool.getTableMap(tableId).get("securityLevel");
			if (!synthesisQueryService.checkUserTableSecurityLevel(tableLevel, getCurrentUser(request))) {		
				request.setAttribute("msg", "�ñ�İ�ȫ�ȼ�Ϊ:��"+tableLevel+"��������Ȩ�޲�����");
				return mapping.findForward("BLANK");
			}
			
			// �ж��Ƿ���˿ռ�����Ǹ�ҳ�����һ�����
			ResourceTableService resourceTableService = (ResourceTableService) getBean("resourceTableService");
			ResourceTable table = resourceTableService.findResourceTable(new Integer(tableId));
			if (Const.IS_SPACCETABLE.equals(table.getIsSpaceTable())) {
				request.setAttribute("is_spacetable", "is_spacetable");
				Integer refTableId = table.getRefTableId();
				if (refTableId != null) {
					tableId = refTableId.toString();
				}
			}

			String condId = request.getParameter("condId");
			if (condId == null) condId = "";

			String paramStr = form.getQuery().getParameters().get("pramaStr");
			Map<String, String> values = new HashMap<String, String>();
			List<Map<String, String>> res=null;
			if (Const.IS_SPACCETABLE.equals(table.getIsSpaceTable())) {
				res = initFormForQuery(form, tableId, "base",tempTableId);
			}else {
				res = initFormForQuery(form, tableId, "base",null);
			}
			if (paramStr != null && !"".equals(paramStr)) {
				initParamsValues(values, paramStr);
			}
			else if (!"".equals(condId)) {
				//PersonalQueryConditionService personalQueryConditionService = (PersonalQueryConditionService) getBean("personalQueryConditionService");
				//PersonalQueryCondition cond = personalQueryConditionService.findPersonalQueryCondition(Integer.valueOf(condId));
				//form.setQueryName(cond.getQueryName());
				//values = conditionToValues(condId, request);
			}
			// �����������������ַ���
			String formStr = getFormTableStr(values, form, res);

			// ���浽request
			request.setAttribute("formStr", formStr);
			form.getQuery().getParameters().put("tableId", tempTableId);

			return mapping.findForward("BASEQUERY");
		}
		catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("err", e);
			return mapping.findForward(ERROR);
		}

	}

	// ������ѯ
	public ActionForward baseQuery(ActionMapping mapping, AdvancedQueryForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			String tableId = form.getQuery().getParameters().get("tableId");
			if (tableId == null || "".equals(tableId)) {
				tableId = request.getParameter("tableId");
			}

			String tempTableId = tableId;
			// �ж��Ƿ���˿ռ�����Ǹ�ҳ�����һ�����
			ResourceTableService resourceTableService = (ResourceTableService) getBean("resourceTableService");
			ResourceTable rtable = resourceTableService.findResourceTable(new Integer(tableId));
			if (Const.IS_SPACCETABLE.equals(rtable.getIsSpaceTable())) {
				request.setAttribute("is_spacetable", "is_spacetable");
				Integer refTableId = rtable.getRefTableId();
				if (refTableId != null) {
					tableId = refTableId.toString();
				}
			}

			SynthesisQueryService sqs = (SynthesisQueryService) getBean("synthesisQueryService");
			TableConfigPool tcp = (TableConfigPool) getBean("tableConfigPool");
			BussCodeSetPool bussCodeSetPool = (BussCodeSetPool) getBean("bussCodeSetPool");

			// �õ�����
			Map<String, String> table = tcp.getTableMap(tableId);
			String tableName = table.get("name");

			// ���ݲ����õ���̬�������������ƺ�ֵ����map���棬nameΪkey��valueΪֵ
			Map<String, String> values = getParamMap(form);
			// ����ѯ�������浽form��
			String paramStr = form.getQuery().getParameters().get("pramaStr");
			if (paramStr != null && !"".equals(paramStr)) {
				initParamsValues(values, paramStr);
			}
			else {
				form.getQuery().getParameters().put("pramaStr", values.toString());
			}
			// ��ʼ�������ַ���������
			
			List<Map<String, String>> res = null;
			if (Const.IS_SPACCETABLE.equals(rtable.getIsSpaceTable())) {
				res = initFormForQuery(form, tableId, "base",tempTableId);
			}else {
				res = initFormForQuery(form, tableId, "base",null);
			}
			String formStr = getFormTableStr(values, form, res);
			request.setAttribute("formStr", formStr);

			// �õ���ѯ��������
			List<QueryParamPoolObj> queryParam = tcp.getParamConfigPool(tableId);
			// ������ѯ��������������ҳ�洫�����Ĳ�ѯ�����Ͳ������õ�where�������
			String sqlWhere = "";
			if (values != null && queryParam != null) {
				boolean iss = false;
				boolean isSpaceTable = Const.IS_SPACCETABLE.equals(rtable.getIsSpaceTable());
				if (sqs.getSpecialTables().get(tableName) != null) iss = true;
				sqlWhere = getWhereStrForBase(res, values, iss, isSpaceTable);
			}

			// �õ���ʾ�ֶ�
			List<ColumnConfigPoolObj> allColumn = tcp.getColumnConfigPool(tableId);
			List<ColumnPoolObj> columnpro = tcp.getTableColumnPool(tableId);
			ResourceColumnService resourceColumnService = (ResourceColumnService) getBean("resourceColumnService");
			ResourceColumn pkobj = resourceColumnService.findResoucrePKColumn(Integer.valueOf(tableId));
			// ��ͷ����
			// Map<String, Map<String, String>> tableHead = new HashMap<String,
			// Map<String,String>>();

			// ������ʾ�ֶΣ��õ���ʾ�ֶ��ַ������ͱ�ͷ��Ϣ
			String showColumn = pkobj.getName() + " as ID,";
			List<Column> columns = new ArrayList<Column>();

			Column checkcol = new Column();
			checkcol.setWidth("35px");
			checkcol.setAlign("center");
			checkcol.setItemType("checkbox");
			checkcol.setProperty("ID");
			checkcol.setItemStyleClass("checkbox");
			if (!Const.IS_SPACCETABLE.equals(rtable.getIsSpaceTable())) {
				columns.add(checkcol);
			}
			

			// int limit = 1;
			for (ColumnConfigPoolObj obj : allColumn) {
				if ("1".equals(obj.getIsSubject())) {
					for (ColumnPoolObj column : columnpro) {
						if (column.getId() != null) {
							if (obj.getColumnId().trim().equals(column.getId().trim())) {
								showColumn += column.getName() + ",";
								Column colObj = new Column();

								colObj.setName(column.getCnName());
								String columnName = "";
								boolean isSpaceTable = Const.IS_SPACCETABLE.equals(rtable.getIsSpaceTable());
								if (isSpaceTable) {
									columnName = column.getName().toUpperCase()==null?"":column.getName().toUpperCase();
								}else {
									columnName = column.getName();
								}
								
								colObj.setProperty(columnName);
								colObj.setHeaderOnMouseOut("headerOut(this)");
								colObj.setHeaderOnMouseOver("headerOver(this)");
								// colObj.setOnDblClick("view('{ID}')");
								colObj.setId(column.getId());
								// if (limit == 1)
								// {
								// colObj.setItemType("hyperlink");
								// colObj.setOnClick("view('{ID}')");
								// colObj.setHref("#middle");
								// limit++;
								// }

								// ��ʾ���
								if (obj.getDisplayWidth() != null) {
									colObj.setWidth(obj.getDisplayWidth());
								}
								// �Ƿ������
								if ("1".equals(obj.getIsSortable())) {
									colObj.setHeaderOnClick("queryBase('" + column.getName() + "')");
								}

								// �ж��û�Ȩ�ޣ����û�û�и�Ȩ�������ε�����
								// column.getSecurityLevel();

								columns.add(colObj);
							}
						}
					}
				}
			}
			// ����ͷ���浽queset��
			if (columns.isEmpty()) {
				Column column = new Column();
				column.setName("��Ҫ��ѯ�����ݿⲻ���ڣ�");
				columns.add(column);
			}
			request.setAttribute(Column.DYNAMICCOLUMNS, columns);

			// ��װsql�����в�ѯ
			if (showColumn != null && !"".equals(showColumn)) {
				showColumn = showColumn.substring(0, showColumn.length() - 1);
			}
			else {
				throw new Exception("û�п���ʾ�б���������������");
			}

			// �õ�ҳ�������ֶ�
			// String order = (String) form.getQuery().getOrder();
			// String dir = (String) form.getQuery().getOrderDirection();
			// String orderSql = " ";
			// if (order != null && !"".equals(order) && dir != null &&
			// !"".equals(dir))
			// {
			// orderSql += " order by " + order + " " + dir;
			// }

			// String sqlAll = "select " + showColumn + " from " + tableName +
			// sqlWhere + orderSql;

			// �ж��Ƿ���˿ռ���������˿ռ���������sybase��
			if (Const.IS_SPACCETABLE.equals(rtable.getIsSpaceTable())) {
				request.setAttribute("is_spacetable", "is_spacetable");
				String sqlAll = "select " + showColumn + " from " + rtable.getName() + sqlWhere;
				String type = request.getParameter("queryType");
				SynthesisTempSpaceDao synthesisTempSpaceDao = (SynthesisTempSpaceDao) getBean("synthesisTempSpaceDao");

				if (type == null || "".equals(type)) {
					synthesisTempSpaceDao.queryBySql(sqlAll, form.getQuery());
					setPage(form.getQuery());
				}
			}
			else {
				String sqlAll = "select " + showColumn + " from " + tableName + sqlWhere;
				String type = request.getParameter("queryType");

				if (type == null || "".equals(type)) {
					sqs.queryBySql(sqlAll, form.getQuery());
					
					// ���˴��뼯
					bussCodeSetPool.filterBussCodeSet(form.getQuery().getRecordSet(), columnpro);
					// ���˰�ȫ�ȼ��ֶ�
					Session currentUser = (Session) request.getSession().getAttribute(Const.SESSION);
					SynthesisColumnGenService synthesisColumnGenService = (SynthesisColumnGenService) getBean("synthesisColumnGenService");
					synthesisColumnGenService.filterSecurityLevel(tableId, form.getQuery().getRecordSet(), columnpro, pkobj, currentUser);
					
					
					setPage(form.getQuery());
				}
			}

			// ȡ����ҳid����
			List<Map<String, String>> list = form.getQuery().getRecordSet();
			if (list == null) {
				list = new ArrayList<Map<String, String>>();
			}
			StringBuffer idArr = new StringBuffer();
			for (Map<String, String> idmap : list) {
				idArr.append(idmap.get("ID") + ",");
			}
			request.setAttribute("idArr", idArr.length() > 0 ? idArr.substring(0, idArr.length() - 1) : "");
			return mapping.findForward("BASEQUERYCOUNT");
		}
		catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("err", e);
			return mapping.findForward(ERROR);
		}

	}

	

	private String getQueryString(PageQuery pageQuery) {
		String order = pageQuery.getOrder();
		if (order == null) {
			order = "";
		}
		String dire = pageQuery.getOrderDirection();
		if (dire == null) {
			dire = "";
		}
		if ("".equals(order) && "".equals(dire)) {
			pageQuery.setOrder("CN_NAME");
			pageQuery.setOrderDirection(PageQuery.ASC);
		}

		StringBuffer buffer = new StringBuffer();

		Map<String, String> params = pageQuery.getParameters();
		if (params == null) {
			params = new HashMap<String, String>();
		}
		String cityCode = params.get("cityCode");
		String tradeId = params.get("tradeId");
		String categoryId = params.get("categoryId");
		// String type = params.get("type");

		if (cityCode != null && !"".equals(cityCode) && !"-".equals(cityCode)) {
			buffer.append(" and r.city_code='").append(cityCode).append("'");
		}
		if (tradeId != null && !"".equals(tradeId) && !"-".equals(tradeId)) {
			buffer.append(" and (ca.parent_id=").append(tradeId).append(" or r.category_id=").append(tradeId + ")");
		}
		if (categoryId != null && !"".equals(categoryId) && !"-".equals(categoryId)) {
			buffer.append(" and r.category_id=").append(categoryId);
		}
		// if (type != null && !"".equals(type) && !"-".equals(type))
		// {
		// buffer.append(" and r.type='").append(type).append("'");
		// }
		return buffer.toString();
	}

	private Integer[] getCategoryChildsId(String parentId) {
		ResourceTableService resourceTableService = (ResourceTableService) getBean("resourceTableService");
		List<Map<String, Object>> list = resourceTableService.getAllCategorysByParentId(parentId);
		if (list != null || !list.isEmpty()) {
			Integer[] ids = new Integer[list.size()];
			for (int i = 0; i < ids.length; i++) {
				ids[i] = (Integer) list.get(i).get(StandardCategory.PROP_ID);
			}
			return ids;
		}

		return null;
	}

	private List<Map<String, String>> initFormForQuery(AdvancedQueryForm advancedQueryForm, String tableId, String type,String tempTableId) {
		if (tempTableId!=null) {
			advancedQueryForm.getQuery().getParameters().put("tableId", tempTableId);
		}else {
			advancedQueryForm.getQuery().getParameters().put("tableId", tableId);
		}
		
		BussCodeSetPool bussCodeSetPool = (BussCodeSetPool) getBean("bussCodeSetPool");
		QueryParamCfgService qcs = (QueryParamCfgService) getBean("queryParamCfgService");

		PageQuery pq = new PageQuery();
		// ������ѯ

		// ȡ�ò�ѯ�����������ֶ���Ӣ�������ֶ�ָ��id���ֶ����ͣ��ֶδ��뼯id�����������ñ�����������Ŀ
		pq.setSelectParamId("GET_FORMPARAM_FOR_ADVANCEDQUERY");
		pq.getParameters().put("queryString", " and t.table_id=" + tableId);
		pq.setPageSize("0");
		pq.setOrder("t.seq_no");
		pq.setOrderDirection("asc");
		qcs.queryQueryParamCfg(pq);

		List<Map<String, String>> res = pq.getRecordSet();
		if (res == null) {
			res = new ArrayList<Map<String, String>>();
		}

		for (Map<String, String> map : res) {

			String codeSetId = map.get("CODESETID");
			if (codeSetId != null && !"".equals(codeSetId)) {
				List<StandardCode> codeSet = bussCodeSetPool.getCodeListByCodeSetId(codeSetId);
				setCode(advancedQueryForm, codeSetId, codeSet, StandardCode.PROP_NAME, StandardCode.PROP_VALUE);
			}
		}
		return res;
	}

	private void initForm(AdvancedQueryForm advancedQueryForm) {
		setCode(advancedQueryForm, "DT_TYPE");
		ResourceTableService rs = (ResourceTableService) getBean("resourceTableService");
		List<SysCity> citys = rs.findAllCitys();

		if (citys == null) {
			citys = new ArrayList<SysCity>();
		}
		setCode(advancedQueryForm, "Citys", citys, SysCity.PROP_NAME, SysCity.PROP_CODE);
		// advancedQueryForm.getCodeSets().get("Citys").get(0).setCodeName("ȫ��");
		advancedQueryForm.getCodeSets().get("Citys").remove(0);
		// ������ҵ���뼯
		List<StandardCategory> trade = rs.findAllTrade();
		if (trade == null) {
			trade = new ArrayList<StandardCategory>();
		}
		setCode(advancedQueryForm, "Trade", trade, StandardCategory.PROP_NAME, StandardCategory.PROP_ID);
		// advancedQueryForm.getCodeSets().get("Trade").get(0).setCodeName("ȫ��");
		advancedQueryForm.getCodeSets().get("Trade").remove(0);

		// ���÷�����뼯
		List<StandardCategory> categorys;
		String tradeId = advancedQueryForm.getQuery().getParameters().get("tradeId");
		if (tradeId == null || "".equals(tradeId)) {
			// ���÷�����뼯Ϊȫ��
			categorys = rs.findCategoryByTradeId(null);
		}
		else {
			// ���÷�����뼯Ϊ����ҵtradeId��������з���
			categorys = rs.findCategoryByTradeId(tradeId);
		}
		setCode(advancedQueryForm, "Categorys", categorys, StandardCategory.PROP_NAME, StandardCategory.PROP_ID);
		// advancedQueryForm.getCodeSets().get("Categorys").get(0).setCodeName("ȫ��");
		advancedQueryForm.getCodeSets().get("Categorys").remove(0);
	}

	// һ������ȡ�������������ӽڵ�
	private void returnTreeNode(HttpServletResponse response, HttpServletRequest request) throws Exception {

		ResourceTableService resourceTableService = (ResourceTableService) getBean("resourceTableService");
		String XmlStr = resourceTableService.getAllTreeNodeAsXmlString(null);

		if (XmlStr == null || "".equals(XmlStr)) {
			XmlStr = DocumentHelper.createElement("tree").addAttribute("id", "0").addElement("item").addAttribute("text", "�㶫ʡ��ԴĿ¼").addAttribute("id", "province").addAttribute("open", "1")
					.addAttribute("child", "1").addElement("userdata").addAttribute("name", "name").addText("�㶫ʡ��ԴĿ¼").asXML();
		}
		response.setContentType("text/xml;charset=UTF-8");
		PrintWriter writer = response.getWriter();
		writer.write(XmlStr);
		writer.close();
	}

	private String getFormTableStr(Map<String, String> values, AdvancedQueryForm form, List<Map<String, String>> res) {
		if (values == null) {
			values = new HashMap<String, String>();
		}

		StringBuffer str = new StringBuffer();

		if (res == null) {
			res = new ArrayList<Map<String, String>>();
		}

		int resLength = res.size();

		for (int i = 0; i < resLength; i += 2) {
			// ��һ��
			Map<String, String> map = res.get(i);
			String isDate = map.get("DATATYPE"); // �Ƿ�����
			String matchSymbol = map.get("FILTEROPERATOR");// ƥ�����
			String codeSetId = map.get("CODESETID");// ���뼯id
			String isHomonym = map.get("HOMONYMQUERY");// �Ƿ�ͬ��
			String isST = map.get("STQUERY");// �Ƿ��

			str.append("<tr>").append("<td>").append(map.get("CNNAME")).append(":</td>");
			if (Const.FILTER_OPER_BETWEEN.equals(matchSymbol)) {
				String value1 = values.get(map.get("ENNAME") + "START");
				if (value1 == null || "".equals(value1)) {
					value1 = "";
//					value1 = DateUtil.getDateTime("yyyy", new Date(System.currentTimeMillis())) + "-01-01";
				}
				String value2 = values.get(map.get("ENNAME") + "END");
				if (value2 == null || "".equals(value2)) {
					value2 = "";
//					value2 = DateUtil.getDateTime("yyyy-MM-dd", new Date(System.currentTimeMillis()));
				}
				// �Ƿ�������
				if (isDate != null && !"".equals(isDate) && Const.META_TYPE_DATE.equalsIgnoreCase(isDate.trim())) {
					str.append("<td><table cellpadding=\"0\" cellspacing=\"0\" class=\"formTable noBorder\"><tr><td width=\"50%\"><input name=\"query.parameters(" + map.get("ENNAME")
							+ "START)\" type=\"text\" value=\"" + value1 + "\" width=\"98%\" class=\"Wdate\" onFocus=\"WdatePicker({isShowWeek:true})\" readonly/></td>");
					str.append("<td width=\"50%\"><input name=\"query.parameters(" + map.get("ENNAME") + "END)\" type=\"text\" value=\"" + value2
							+ "\" width=\"98%\" class=\"Wdate\" onFocus=\"WdatePicker({isShowWeek:true})\" readonly/></td></tr></table></td>");
				}
				// �Ƿ������
				else if (codeSetId != null && !"".equals(codeSetId)) {
					str.append("<td><table cellpadding=\"0\" cellspacing=\"0\" class=\"formTable noBorder\"><tr><td width=\"50%\" class=\"sel\">");
					str.append("<select name=\"query.parameters(" + map.get("ENNAME") + "START)\" >");
					List<Code> codeSet = form.getCodeSets().get(codeSetId);

					if (codeSet == null) {
						codeSet = new ArrayList<Code>();
					}
					for (Code code : codeSet) {
						String selected;
						if (value1.equalsIgnoreCase(code.getValue())) {
							selected = "selected";
						}
						else {
							selected = "";
						}
						str.append("<option value=\"" + code.getValue() + "\" " + selected + ">").append(code.getCodeName()).append("</option>");
					}

					str.append("</select></td><td width=\"50%\" class=\"sel\"><select name=\"query.parameters(" + map.get("ENNAME") + "END)\" >");
					if (codeSet == null) {
						codeSet = new ArrayList<Code>();
					}
					for (Code code : codeSet) {
						String selected;
						if (value2.equalsIgnoreCase(code.getValue())) {
							selected = "selected";
						}
						else {
							selected = "";
						}
						str.append("<option value=\"" + code.getValue() + "\" " + selected + ">").append(code.getCodeName()).append("</option>");
					}
					str.append("</select></td></tr></table></td>");
				}
				// ��������
				else {
					str.append("<td><table cellpadding=\"0\" cellspacing=\"0\" class=\"formTable noBorder\"><tr><td width=\"50%\"><input name=\"query.parameters(" + map.get("ENNAME")
							+ "START)\" type=\"text\" value=\"" + value1 + "\" width=\"98%\" /></td>");
					str.append("<td width=\"50%\"><input name=\"query.parameters(" + map.get("ENNAME") + "END)\" type=\"text\" value=\"" + value2 + "\" width=\"98%\" /></td></tr></table></td>");
				}
			}
			else {
				String value = values.get(map.get("ENNAME"));
				if (value == null) {
					value = "";
				}

				// �Ƿ�������
				if (isDate != null && !"".equals(isDate) && Const.META_TYPE_DATE.equalsIgnoreCase(isDate.trim())) {
					str.append("<td><input name=\"query.parameters(" + map.get("ENNAME") + ")\" type=\"text\" value=\"" + value
							+ "\" class=\"Wdate\" onFocus=\"WdatePicker({isShowWeek:true})\" readonly/></td>");
				}
				else if (codeSetId != null && !"".equals(codeSetId)) {
					str.append("<td class=\"sel\"><select name=\"query.parameters(" + map.get("ENNAME") + ")\" >");
					List<Code> codeSet = form.getCodeSets().get(codeSetId);

					if (codeSet == null) {
						codeSet = new ArrayList<Code>();
					}
					for (Code code : codeSet) {
						String selected;
						if (value.equalsIgnoreCase(code.getValue())) {
							selected = "selected";
						}
						else {
							selected = "";
						}
						str.append("<option value=\"" + code.getValue() + "\" " + selected + ">").append(code.getCodeName()).append("</option>");
					}
					str.append("</select></td>");
				}
				else {
					str.append("<td><table cellpadding=\"0\" cellspacing=\"0\" class=\"formTable noBorder\"><tr>");

					str.append("<td><input name=\"query.parameters(" + map.get("ENNAME") + ")\" type=\"text\" value=\"" + value + "\" /></td>");

					// if (isHomonym != null && !"".equals(isHomonym) &&
					// "1".equalsIgnoreCase(isHomonym.trim()))
					// {
					// String isHom = values.get("homonym" + map.get("ENNAME"));
					// if ("1".equals(isHom))
					// {
					// isHom = "checked";
					// }
					// else
					// {
					// isHom = "";
					// }
					// str.append("<td class=\"textL\"><input type=checkbox
					// class=\"checkbox\" name=\"query.parameters(homonym"
					// + map.get("ENNAME") + ")\" " + isHom + " value=\"" + isHomonym
					// + "\" />ͬ��</td>");
					// }
					// if (Const.SYS_CODE_YES.equals(isST))
					// {
					// String st = values.get("ST" + map.get("ENNAME"));
					// if (Const.SYS_CODE_YES.equals(st))
					// {
					// st = "checked";
					// }
					// else
					// {
					// st = "";
					// }
					// str.append("<td class=\"textL\"><input type=checkbox
					// class=\"checkbox\" name=\"query.parameters(ST" +
					// map.get("ENNAME")
					// + ")\" " + st + " value=\"" + Const.SYS_CODE_YES + "\"
					// />��</td>");
					// }

					str.append("</tr></table></td>");
				}
			}

			// �ڶ�������

			if (i + 1 < resLength) {
				Map<String, String> map2 = res.get(i + 1);
				String isDate2 = map2.get("DATATYPE"); // �Ƿ�����
				String matchSymbol2 = map2.get("FILTEROPERATOR");// ƥ�����
				String codeSetId2 = map2.get("CODESETID");// ���뼯id
				String isHomonym2 = map2.get("HOMONYMQUERY");// �Ƿ�ͬ��
				String isST2 = map2.get("STQUERY");// �Ƿ��

				str.append("<td>").append(map2.get("CNNAME")).append(":</td>");
				if (Const.FILTER_OPER_BETWEEN.equals(matchSymbol2)) {
					String value1 = values.get(map2.get("ENNAME") + "START");
					if (value1 == null || "".equals(value1)) {
						value1 = "";
//						value1 = DateUtil.getDateTime("yyyy", new Date(System.currentTimeMillis())) + "-01-01";
					}
					String value2 = values.get(map2.get("ENNAME") + "END");
					if (value2 == null) {
						value2 = "";
//						value2 = DateUtil.getDateTime("yyyy-MM-dd", new Date(System.currentTimeMillis()));
					}
					// �Ƿ�������
					if (isDate2 != null && !"".equals(isDate2) && Const.META_TYPE_DATE.equalsIgnoreCase(isDate2.trim())) {
						str.append("<td><table cellpadding=\"0\" cellspacing=\"0\" class=\"formTable noBorder\"><tr><td width=\"50%\"><input name=\"query.parameters(" + map2.get("ENNAME")
								+ "START)\" type=\"text\" value=\"" + value1 + "\" width=\"98%\" class=\"Wdate\" onFocus=\"WdatePicker({isShowWeek:true})\" readonly/></td>");
						str.append("<td width=\"50%\"><input name=\"query.parameters(" + map2.get("ENNAME") + "END)\" type=\"text\" value=\"" + value2
								+ "\" width=\"98%\" class=\"Wdate\" onFocus=\"WdatePicker({isShowWeek:true})\" readonly/></td></tr></table></td>");
					}
					// �Ƿ������
					else if (codeSetId2 != null && !"".equals(codeSetId2)) {
						str.append("<td><table cellpadding=\"0\" cellspacing=\"0\" class=\"formTable noBorder\"><tr><td width=\"50%\" class=\"sel\">");
						str.append("<select name=\"query.parameters(" + map2.get("ENNAME") + "START)\" >");
						List<Code> codeSet = form.getCodeSets().get(codeSetId2);

						if (codeSet == null) {
							codeSet = new ArrayList<Code>();
						}
						for (Code code : codeSet) {
							String selected;
							if (value1.equalsIgnoreCase(code.getValue())) {
								selected = "selected";
							}
							else {
								selected = "";
							}
							str.append("<option value=\"" + code.getValue() + "\" " + selected + ">").append(code.getCodeName()).append("</option>");
						}

						str.append("</select></td><td width=\"50%\" class=\"sel\"><select name=\"query.parameters(" + map2.get("ENNAME") + "END)\" >");
						if (codeSet == null) {
							codeSet = new ArrayList<Code>();
						}
						for (Code code : codeSet) {
							String selected;
							if (value2.equalsIgnoreCase(code.getValue())) {
								selected = "selected";
							}
							else {
								selected = "";
							}
							str.append("<option value=\"" + code.getValue() + "\" " + selected + ">").append(code.getCodeName()).append("</option>");
						}
						str.append("</select></td></tr></table></td>");
					}
					// ��������
					else {
						str.append("<td><table cellpadding=\"0\" cellspacing=\"0\" class=\"formTable noBorder\"><tr><td width=\"50%\"><input name=\"query.parameters(" + map2.get("ENNAME")
								+ "START)\" type=\"text\" value=\"" + value1 + "\" width=\"98%\" /></td>");
						str.append("<td width=\"50%\"><input name=\"query.parameters(" + map2.get("ENNAME") + "END)\" type=\"text\" value=\"" + value2 + "\" width=\"98%\" /></td></tr></table></td>");
					}
				}
				else {
					String value = values.get(map2.get("ENNAME"));
					if (value == null) {
						value = "";
					}

					// �Ƿ�������
					if (isDate2 != null && !"".equals(isDate2) && Const.META_TYPE_DATE.equalsIgnoreCase(isDate2.trim())) {
						str.append("<td><input name=\"query.parameters(" + map2.get("ENNAME") + ")\" type=\"text\" value=\"" + value
								+ "\" class=\"Wdate\" onFocus=\"WdatePicker({isShowWeek:true})\" readonly/></td>");
					}
					else if (codeSetId2 != null && !"".equals(codeSetId2)) {
						str.append("<td class=\"sel\"><select name=\"query.parameters(" + map2.get("ENNAME") + ")\" >");
						List<Code> codeSet = form.getCodeSets().get(codeSetId2);

						if (codeSet == null) {
							codeSet = new ArrayList<Code>();
						}
						for (Code code : codeSet) {
							String selected;
							if (value.equalsIgnoreCase(code.getValue())) {
								selected = "selected";
							}
							else {
								selected = "";
							}
							str.append("<option value=\"" + code.getValue() + "\" " + selected + ">").append(code.getCodeName()).append("</option>");
						}
						str.append("</select></td>");
					}
					else {
						str.append("<td><table cellpadding=\"0\" cellspacing=\"0\" class=\"formTable noBorder\"><tr>");

						str.append("<td><input name=\"query.parameters(" + map2.get("ENNAME") + ")\" type=\"text\" value=\"" + value + "\" /></td>");

						// if (isHomonym != null && !"".equals(isHomonym) &&
						// "1".equalsIgnoreCase(isHomonym.trim()))
						// {
						// String isHom = values.get("homonym" + map.get("ENNAME"));
						// if ("1".equals(isHom))
						// {
						// isHom = "checked";
						// }
						// else
						// {
						// isHom = "";
						// }
						// str.append("<td class=\"textL\"><input type=checkbox
						// class=\"checkbox\" name=\"query.parameters(homonym"
						// + map.get("ENNAME") + ")\" " + isHom + " value=\"" +
						// isHomonym + "\" />ͬ��</td>");
						// }
						// if (Const.SYS_CODE_YES.equals(isST))
						// {
						// String st = values.get("ST" + map.get("ENNAME"));
						// if (Const.SYS_CODE_YES.equals(st))
						// {
						// st = "checked";
						// }
						// else
						// {
						// st = "";
						// }
						// str.append("<td class=\"textL\"><input type=checkbox
						// class=\"checkbox\" name=\"query.parameters(ST" +
						// map.get("ENNAME")
						// + ")\" " + st + " value=\"" + Const.SYS_CODE_YES + "\"
						// />��</td>");
						// }

						str.append("</tr></table></td>");
					}
				}
				str.append("</tr>");
			}
			else {
				str.append("<td colspan=\"2\">&nbsp;</td></tr>");
			}
		}

		return str.toString();
	}

	private Map<String, String> getParamMap(AdvancedQueryForm form) {
		Map<String, String> paramMap = new HashMap<String, String>();
		Set<String> parameterNames = form.getQuery().getParameters().keySet();
		for (String str : parameterNames) {
			if (str != null && !"".equals(str)) {
				String name = str.trim();
				String value = form.getQuery().getParameters().get(name);
				if (value != null && !"".equals(value)) {
					paramMap.put(name, value);
				}
			}
		}
		return paramMap;
	}

	private String getWhereStrForBase(List<Map<String, String>> queryParames, Map<String, String> queryValue, boolean isSpecialTable, boolean isSpaceTable) throws Exception {
		String str = " where 1=1 ";
		QueryMetaIndPool queryMetaIndPool = (QueryMetaIndPool) getBean("queryMetaIndPool");
		BaseSqlGen sqlGen = null;
		if (isSpaceTable) {
			sqlGen = (BaseSqlGen) getBean("sqlGen");
		}
		else {
			sqlGen = (BaseSqlGen) getBean("sybaseSqlGen");
		}

		for (Map<String, String> bean : queryParames) {
			StringBuffer sb = new StringBuffer();
			List<QueryParam> param = new ArrayList<QueryParam>();
			List<QueryParam> param2 = new ArrayList<QueryParam>();
			String dataType = bean.get("DATATYPE"); // �Ƿ�����
			String opType = bean.get("FILTEROPERATOR");
			String name = bean.get("ENNAME");
			String isHomonym = bean.get("HOMONYMQUERY");// �Ƿ�ͬ��
			String isST = bean.get("STQUERY");// �Ƿ��
			String checkST = queryValue.get("ST" + name);
			if (name != null && !"".equals(name)) {
				if (opType != null && Const.FILTER_OPER_BETWEEN.equalsIgnoreCase(opType)) {

					String stValue = queryValue.get(name.trim() + "START");
					if (stValue == null) stValue = "";

					String enValue = queryValue.get(name.trim() + "END");
					if (enValue == null) enValue = "";

					if (!stValue.equals("") || !enValue.equals("")) {
						param.add(new QueryParam(name, filterColumnType(dataType), opType, stValue + ";" + enValue, ""));
					}
				}
				else {
					
					String indId = bean.get("INDICATORID");
					if(indId==null)indId="";
					
					String value = queryValue.get(name.trim());
					if (value != null && !"".equals(value)) {
						// ��;�绰�����⴦�����
						if (isSpecialTable && (name.equalsIgnoreCase("ZJHM") || name.equalsIgnoreCase("BJHM"))) {

						}
						else {
							param.add(new QueryParam(name, filterColumnType(dataType), opType, value, ""));
							if (Const.SYS_CODE_YES.equals(isST)) {
								String big5str = new String(GB2Big5.getInstance().gb2big5(value), "BIG5");
								param.add(new QueryParam(name, filterColumnType(dataType), opType, big5str, ""));
							}
							
							//����ֶθ�����ָ��ҹ��������������
							
							if (!"".equals(indId)) {
								Map<String, String> indMap = queryMetaIndPool.getIndicatorPool(indId);
								if (Const.INDICATOR_NAME.equalsIgnoreCase(indMap.get("code"))) {
									String[] xms = value.split("[\\s]+");
//									if (xms.length==2) {
//										param.add(new QueryParam(name, filterColumnType(dataType), opType, xms[1]+" "+xms[0], ""));
//									}else if (xms.length==3) {
//										param.add(new QueryParam(name, filterColumnType(dataType), opType, xms[2]+" "+xms[1]+" "+xms[0], ""));
//									}
									if (xms.length>1) {
										for (int i = 0; i < xms.length; i++) {
											param2.add(new QueryParam(name, filterColumnType(dataType), Const.FILTER_OPER_LIKE, "%"+xms[i]+"%", ""));
										}
									}
								}
							}
						}
					}
				}

				String spSQL = "";
				if (!param2.isEmpty()) {
					spSQL = sqlGen.getQueryStringPart(param2,QueryParam.OPER_AND);
					if (spSQL!=null&&!"".equals(spSQL)) {
						spSQL = " or (" +spSQL + ") ";
					}else {
						spSQL = "";
					}
				}
				String commonSQL = sqlGen.getQueryStringPart(param, QueryParam.OPER_OR);
				if (commonSQL == null || "".equals(commonSQL)) {
					commonSQL = "";
				}
				else {
					commonSQL = " and (" + commonSQL + ") ";
				}

				sb.append(commonSQL).append(spSQL);

			}

			if (sb.length() > 0) {
				str += sb.toString();
			}
		}
		return str;
	}

	private void initParamsValues(Map<String, String> paramsValue, String allparams) {
		if (allparams == null || "".equals(allparams)) {
			paramsValue = new HashMap<String, String>();
			return;
		}

		if (allparams.length() > 3) {
			allparams = allparams.substring(1, allparams.length() - 1);
			String[] oneParams = allparams.split("\\}\\, \\{");
			// ������ת����map���󲢱��浽list��
			for (int i = 0; i < oneParams.length; i++) {
				String temp = oneParams[i];
				if (temp != null) {
					temp = temp.replaceAll("\\{", "").replaceAll("\\}", "");
					String[] keyValueStr = temp.replaceAll("@.*@", "").split("\\,");

					for (int j = 0; j < keyValueStr.length; j++) {
						String[] singleMapStr = keyValueStr[j].split("\\=");
						if (singleMapStr.length == 2) {
							String key = singleMapStr[0];
							if (key != null) {
								key = key.trim();
							}
							String value = singleMapStr[1];

							paramsValue.put(key, value);
						}
					}
				}
			}
		}
		else {
			return;
		}
	}

	private String filterColumnType(String opType) {
		if (Const.META_TYPE_NUMERIC.equals(opType)) return QueryParam.COLUMN_TYPE_INT;
		else if (Const.META_TYPE_DATE.equals(opType)) return QueryParam.COLUMN_TYPE_DATE;
		else if (Const.META_TYPE_STRING.equals(opType)) return QueryParam.COLUMN_TYPE_STRING;
		else return QueryParam.COLUMN_TYPE_STRING;
	}

	private synchronized boolean ProcessGenTableAndRecord(List<ColumnPoolObj> columnpoolList, String tableName, ResourceColumn pkobj, Map<String, String> tableMap, Session session,ResourceTable table, AdvancedQueryForm theForm) {
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
				map.put("isNullable", "Y"); // Ĭ���ֶζ�����Ϊ��
				map.put("precise", obj.getDataPercise());
				map.put("columnLength", obj.getDataLength());
				columnList.add(map);
				pbuffer.append("?,");
			}
			String createSql = generator.generateCreateTableScript(columnList, tableName);

			// ִ�н������
			synthesisTempSpaceDao.executeUpdate(createSql);

			// ��ȡ��ѯ�Ľ��
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
			// ���ݰ�ȫ�ȼ����ˣ���֤��ȫ
			SynthesisColumnGenService synthesisColumnGenService = (SynthesisColumnGenService) getBean("synthesisColumnGenService");
			synthesisColumnGenService.filterSecurityLevel(tableMap.get("id"), list, columnpoolList, pkobj, session);
			// ������������
			String insertSql = "insert into " + tableName + " values (" + pbuffer.substring(0, pbuffer.length() - 1) + ")";
			synthesisTempSpaceDao.batchUpdate(insertSql, list, columnpoolList);

			int count = synthesisTempSpaceDao.queryCountBySql("select count(id) from " + tableName);
			if (count == list.size()) {
				isSuccess = true;
				table.setRecordeCount(String.valueOf(count));
				ResourceTableService resourceTableService = (ResourceTableService)getBean("resourceTableService");
				resourceTableService.updateResourceTable(table);
			}
			else {
				synthesisTempSpaceDao.dropTable(tableName);
				isSuccess = false;
			}

		}
		catch (Exception e) {
			synthesisTempSpaceDao.dropTable(tableName);
			isSuccess = false;
			e.printStackTrace();
			log.error(e);
		}

		return isSuccess;
	}
}
