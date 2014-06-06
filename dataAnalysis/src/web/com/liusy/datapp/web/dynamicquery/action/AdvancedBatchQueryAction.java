package com.liusy.datapp.web.dynamicquery.action;

import java.net.URLDecoder;
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

import com.liusy.core.util.Const;
import com.liusy.core.util.Session;
import com.liusy.dataapplatform.base.exception.ServiceException;
import com.liusy.dataapplatform.base.exception.WebException;
import com.liusy.dataapplatform.base.util.BaseSqlGen;
import com.liusy.dataapplatform.base.util.DateUtil;
import com.liusy.dataapplatform.base.util.ExcelGenerator;
import com.liusy.dataapplatform.base.util.ExcelSheetColProp;
import com.liusy.dataapplatform.base.util.PageQuery;
import com.liusy.dataapplatform.base.util.QueryParam;
import com.liusy.dataapplatform.code.Code;
import com.liusy.datapp.model.datastandard.StandardCode;
import com.liusy.datapp.model.resource.ResourceColumn;
import com.liusy.datapp.service.dynamicquery.SynthesisColumnGenService;
import com.liusy.datapp.service.pool.BussCodeSetPool;
import com.liusy.datapp.service.pool.QueryMetaIndPool;
import com.liusy.datapp.service.pool.TableConfigPool;
import com.liusy.datapp.service.query.QueryParamCfgService;
import com.liusy.datapp.service.query.SynthesisQueryService;
import com.liusy.datapp.service.resource.ResourceColumnService;
import com.liusy.datapp.util.poolobj.ColumnConfigPoolObj;
import com.liusy.datapp.util.poolobj.ColumnPoolObj;
import com.liusy.datapp.util.poolobj.QueryParamPoolObj;
import com.liusy.datapp.web.BaseAction;
import com.liusy.datapp.web.dynamicquery.form.AdvancedQueryForm;
import com.liusy.web.tag.grid.Column;

public class AdvancedBatchQueryAction extends BaseAction {

	private static final String	SEARCHCONDITION	= "SEARCHCONDITION"; // ��������ѯ������Ѱ
	private static final String	TOBASEPAGE			= "TOBASEPAGE";

	@Override
	public ActionForward doExecute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		String action = request.getParameter("action");
		if (action == null || "".equals(action)) {
			action = LIST;
		}
		if (log.isDebugEnabled()) log.debug("action:" + action);

		ActionForward forward;
		AdvancedQueryForm advancedQueryForm = (AdvancedQueryForm) form;

		String actionType = advancedQueryForm.getQuery().getParameters().get("type");
		try {
			if (TOBASEPAGE.equalsIgnoreCase(action)) {
				forward = toBasePage(mapping, advancedQueryForm, request, response);
			}
			else if (LIST.equalsIgnoreCase(action)) {
				if ("SEARCHCONDITION".equals(actionType)) {
					forward = searchCondition(mapping, advancedQueryForm, request, response);
				}
				else {
					forward = search(mapping, advancedQueryForm, request, response);
				}

			}
			else if (SEARCHCONDITION.equalsIgnoreCase(action)) {
				forward = searchCondition(mapping, advancedQueryForm, request, response);
			}
			else if ("EXPORTALLEXCELL".equalsIgnoreCase(action)) {
				forward = exportAllExcell(mapping, advancedQueryForm, request, response);
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
			return mapping.findForward(ERROR);
		}
		return forward;
	}

	private ActionForward toBasePage(ActionMapping mapping, AdvancedQueryForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			String tableId = request.getParameter("tableId");
			if (tableId == null || "".equals(tableId)) {
				tableId = form.getQuery().getParameters().get("tableId");
			}

			String allparams = form.getQuery().getParameters().get("allParams");
			String condId = request.getParameter("condId");
			if (condId == null) condId = "";
			List<Map<String, String>> paramsValue = new ArrayList<Map<String, String>>();
			if (!"".equals(condId)) {
				//PersonalQueryConditionService personalQueryConditionService = (PersonalQueryConditionService) getBean("personalQueryConditionService");
				//PersonalQueryCondition cond = personalQueryConditionService.findPersonalQueryCondition(Integer.valueOf(condId));
				//form.setQueryName(cond.getQueryName());
				//paramsValue = conditionToValues(condId, request);
			}
			else {
				initParamsValues(paramsValue, allparams);
			}
			if (paramsValue.size() == 0) {
				paramsValue.add(new HashMap<String, String>());
			}
			// ��ʼ����������
			List<Map<String, String>> res = initFormForQuery(form, tableId, "base");

			// �����������������ַ���
			String formStr = getFormTableStr(form, res, paramsValue);
			//
			// formStr += "<tr class=\"btnTr\"><td colspan=\"" + (res.size() + 1) +
			// "\" "
			// +
			// "class=\"textL\"><a  code=\"\" class=\"btnStyle\" href=\"#middle\" "
			// +
			// "onclick=\"insertRow()\"><strong>���</strong></a></td></tr><tr class=\"btnTr\">"
			// + "<td class=\"textC\" colspan=\"" + (res.size() + 1) + "\">";

			// ���浽request
			List<String> script = getScriptRow(form, res);
			if (script == null) {
				script = new ArrayList<String>();
			}
			request.setAttribute("script", script);
			request.setAttribute("formStr", formStr);
			form.getQuery().getParameters().put("tableId", tableId);
			// request.setAttribute("tableId", tableId);

			List<Column> columns = new ArrayList<Column>();
			Column column = new Column();
			column.setName("���������ѯ������");
			columns.add(column);
			// ����ͷ���浽queset��
			request.setAttribute(Column.DYNAMICCOLUMNS, columns);

			return mapping.findForward("TOBATCHPAGE");
		}
		catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("err", e);
			return mapping.findForward(ERROR);
		}
	}

	private ActionForward searchCondition(ActionMapping mapping, AdvancedQueryForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			String tableId = request.getParameter("tableId");
			String params = request.getParameter("pramaStr");
			if (tableId == null || "".equals(tableId)) {
				tableId = form.getQuery().getParameters().get("tableId");
			}
			if (params == null || "".equals(params)) {
				params = form.getQuery().getParameters().get("pramaStr");
			}

			// ������Ҫ����ת��
			if (params != null) {
				String type = form.getQuery().getParameters().get("type");
				if (!SEARCHCONDITION.equals(type)) {
					params = URLDecoder.decode(params, request.getCharacterEncoding());
					// params = new String(params.getBytes("ISO-8859-1"),
					// request.getCharacterEncoding());
				}
			}

			form.getQuery().getParameters().put("pramaStr", params);
			if (tableId == null || "".equals(tableId)) {
				tableId = request.getParameter("tableId");
			}
			SynthesisQueryService sqs = (SynthesisQueryService) getBean("synthesisQueryService");
			TableConfigPool tcp = (TableConfigPool) getBean("tableConfigPool");
			BussCodeSetPool bussCodeSetPool = (BussCodeSetPool) getBean("bussCodeSetPool");
			ResourceColumnService resourceColumnService = (ResourceColumnService) getBean("resourceColumnService");
			ResourceColumn pkobj = resourceColumnService.findResoucrePKColumn(Integer.valueOf(tableId));
			// �õ���ѯ��������
			List<QueryParamPoolObj> queryParam = tcp.getParamConfigPool(tableId);
			List<Map<String, String>> res = initFormForQuery(form, tableId, "base");
			// ������ѯ��������������ҳ�洫�����Ĳ�ѯ�����Ͳ������õ�where�������
			String sqlWhere = " where 1=1 ";
			Map<String, String> values = getWhereMapByParamsStr(params);
			if (values != null && queryParam != null) {
				sqlWhere = getWhereStrForBase(res, values);
			}

			// �õ���ʾ�ֶ�
			List<ColumnConfigPoolObj> allColumn = tcp.getColumnConfigPool(tableId);
			List<ColumnPoolObj> columnpro = tcp.getTableColumnPool(tableId);

			// ������ʾ�ֶΣ��õ���ʾ�ֶ��ַ������ͱ�ͷ��Ϣ
			String showColumn = pkobj.getName() + " as ID,";
			List<Column> columns = new ArrayList<Column>();
			Column checkcol = new Column();
			checkcol.setWidth("35px");
			checkcol.setAlign("center");
			checkcol.setItemType("checkbox");
			checkcol.setProperty("ID");
			columns.add(checkcol);
			int limit = 1;
			for (ColumnConfigPoolObj obj : allColumn) {
				if ("1".equals(obj.getIsSubject())) {
					for (ColumnPoolObj column : columnpro) {
						if (column.getId() != null) {
							if (obj.getColumnId().trim().equals(column.getId().trim())) {
								showColumn += column.getName() + ",";
								Column colObj = new Column();

								colObj.setName(column.getCnName());
								colObj.setProperty(column.getName());
								colObj.setHeaderOnMouseOut("headerOut(this)");
								colObj.setHeaderOnMouseOver("headerOver(this)");
								// colObj.setOnDblClick("view('{ID}')");
								// colObj.setId(column.getId());
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
			// �õ�����
			Map<String, String> table = tcp.getTableMap(tableId);
			String tableName = table.get("name");

			// �õ�ҳ�������ֶ�
			String order = (String) form.getQuery().getOrder();
			String dir = (String) form.getQuery().getOrderDirection();
			String orderSql = " ";
			if (order != null && !"".equals(order) && dir != null && !"".equals(dir)) {
				orderSql += " order by " + order + " " + dir;
			}

			String sqlAll = "select " + showColumn + " from " + tableName + sqlWhere + orderSql;

			sqs.queryBySql(sqlAll, form.getQuery());
			List<Map<String, String>> list = form.getQuery().getRecordSet();
			// ���˴��뼯
			bussCodeSetPool.filterBussCodeSet(list, columnpro);
			// ���˰�ȫ�ȼ��ֶ�
			Session currentUser = (Session) request.getSession().getAttribute(Const.SESSION);
			SynthesisColumnGenService synthesisColumnGenService = (SynthesisColumnGenService) getBean("synthesisColumnGenService");
			synthesisColumnGenService.filterSecurityLevel(tableId, list, columnpro, pkobj, currentUser);
			setPage(form.getQuery());

			// ȡ����ҳid����
			StringBuffer idArr = new StringBuffer();
			for (Map<String, String> idmap : list) {
				idArr.append(idmap.get("ID") + ",");
			}
			request.setAttribute("idArr", idArr.length() > 0 ? idArr.substring(0, idArr.length() - 1) : "");

			return mapping.findForward(SEARCHCONDITION);
		}
		catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("err", e);
			return mapping.findForward(ERROR);
		}
	}

	private ActionForward exportAllExcell(ActionMapping mapping, AdvancedQueryForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			SynthesisQueryService sqs = (SynthesisQueryService) getBean("synthesisQueryService");
			QueryParamCfgService qcs = (QueryParamCfgService) getBean("queryParamCfgService");
			TableConfigPool tcp = (TableConfigPool) getBean("tableConfigPool");

			Map<String, String> params = form.getQuery().getParameters();
			StringBuffer formTableStr = new StringBuffer();
			String tableId = params.get("tableId");
			String paramsStr = params.get("allParams");

			Map<String, String> table = tcp.getTableMap(tableId);
			List<Map<String, String>> resTable = new ArrayList<Map<String, String>>();
			String tableName = table.get("name");

			List<ColumnPoolObj> columnpoolList = tcp.getTableColumnPool(tableId);
			List<ColumnConfigPoolObj> columnpConfigPoolObjs = tcp.getColumnConfigPool(tableId);

			StringBuffer sb = new StringBuffer();
			List<String> columnCNNames = new ArrayList<String>();
			List<String> columnENNames = new ArrayList<String>();

			for (ColumnPoolObj columnPoolObj : columnpoolList) {
				for (ColumnConfigPoolObj columnConfigPoolObj : columnpConfigPoolObjs) {
					if (columnPoolObj.getId().equals(columnConfigPoolObj.getColumnId()) && Const.SYS_CODE_YES.equals(columnConfigPoolObj.getIsShown())) {
						sb.append("," + columnPoolObj.getName());
						columnCNNames.add(columnPoolObj.getCnName());
						columnENNames.add(columnPoolObj.getName());
					}
				}
			}

			String showColumns = sb.length() == 0 ? "*" : sb.toString().substring(1);

			List<Map<String, String>> queryFields = initFormForQuery(form, tableId, "batch");
			List<Map<String, String>> paramsSet = new ArrayList<Map<String, String>>();
			initParamsValues(paramsSet, paramsStr);
			int i = 1;
			// ��ȡ��Ӧ��ĸ������������
			SynthesisQueryService synthesisQueryService = (SynthesisQueryService) getBean("synthesisQueryService");
			PageQuery pageQuery = form.getQuery();
			pageQuery.setPageSize("0");

			HSSFWorkbook wb = ExcelGenerator.GenerateExcelFile();
			for (Map<String, String> map : paramsSet) {

				String sheetName = map.get("sheetName");
				if ("".equals(sheetName)) {
					sheetName = "��" + (i++) + "������";
				}
				String whereStr = getWhereStrForBase(queryFields, map);
				String sql = "select " + showColumns + " from " + tableName + " " + whereStr;

				List<Map<String, String>> result = synthesisQueryService.queryBySql(sql, pageQuery).getRecordSet();
				if (result != null && !result.isEmpty()) {
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
			return null;
		}
		catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("err", e);
			return mapping.findForward(ERROR);
		}
	}

	private ActionForward search(ActionMapping mapping, AdvancedQueryForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			SynthesisQueryService sqs = (SynthesisQueryService) getBean("synthesisQueryService");
			QueryParamCfgService qcs = (QueryParamCfgService) getBean("queryParamCfgService");
			TableConfigPool tcp = (TableConfigPool) getBean("tableConfigPool");

			Map<String, String> params = form.getQuery().getParameters();
			StringBuffer formTableStr = new StringBuffer();
			String tableId = params.get("tableId");
			String paramsStr = params.get("allParams");

			Map<String, String> table = tcp.getTableMap(tableId);
			List<Map<String, String>> resTable = new ArrayList<Map<String, String>>();
			String tableName = table.get("name");
			List<Map<String, String>> queryFields = initFormForQuery(form, tableId, "batch");
			List<Column> columns = new ArrayList<Column>();
			// ����sql���ͱ��ַ���
			List<String> sqls = null;
			int totalCount = 0;
			if (tableName != null && !"".equals(tableName)) {
				sqls = generaSQLForBatch(request, resTable, queryFields, tableName, formTableStr);

				if (sqls != null && !sqls.isEmpty()) {
					for (int i = 0; i < sqls.size(); i++) {
						String count = "0";
						sqs.queryBySql(sqls.get(i), form.getQuery());
						List<Map<String, String>> reSet = form.getQuery().getRecordSet();
						if (reSet != null && !reSet.isEmpty()) {
							count = reSet.get(0).get("RECORDCOUNT");
							if (count == null) {
								count = "0";
							}
							totalCount += Integer.parseInt(count);
						}
						resTable.get(i).put("CONDITION_QUERY_COUNT", count);
					}
				}
				sortByCount(resTable);
				form.getQuery().getParameters().put("allParams", resTable.toString());
				form.getQuery().setRecordSet(resTable);
				form.getQuery().setRecordCount(String.valueOf(resTable.size()));
				setPage(form.getQuery());
				request.setAttribute("sqls", sqls);
				request.setAttribute("tabs", resTable);
				request.setAttribute("tableName", table.get("cName"));
				request.setAttribute("totalCount", totalCount);
			}
			else {
				throw new Exception("��Ӣ����Ϊ�գ�");
			}

			
			// ��ʼ����������
			int limit = 1;
			for (Map<String, String> column : queryFields) {
				Column colObj = new Column();

				colObj.setName(column.get("CNNAME"));
				colObj.setProperty(column.get("ENNAME"));
				colObj.setHeaderOnMouseOut("headerOut(this)");
				colObj.setHeaderOnMouseOver("headerOver(this)");
				if (limit == 1) {
					colObj.setItemType("hyperlink");
					colObj.setOnClick("queryCondition('{CONDITION_QUERY_PARAMS}')");
					colObj.setHref("#middle");
					limit++;
				}

				columns.add(colObj);
			}
			Column countCol = new Column();

			countCol.setName("����");
			countCol.setProperty("CONDITION_QUERY_COUNT");
			countCol.setHeaderOnMouseOut("headerOut(this)");
			countCol.setHeaderOnMouseOver("headerOver(this)");
			columns.add(countCol);

			request.setAttribute(Column.DYNAMICCOLUMNS, columns);

			return mapping.findForward("TOBATCHCOUNT");
		}
		catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("err", e);
			return mapping.findForward(ERROR);
		}
	}

	private List<String> generaSQLForBatch(HttpServletRequest request, List<Map<String, String>> resTable, List<Map<String, String>> queryFields, String tableName, StringBuffer formTableStr)
			throws ServiceException {
		String countStr = request.getParameter("query.parameters(paramCount)");
		int count = Integer.parseInt(countStr);
		List<String> sqls = new ArrayList<String>();

		BaseSqlGen sqlGen = (BaseSqlGen) getBean("sybaseSqlGen");
		QueryMetaIndPool queryMetaIndPool = (QueryMetaIndPool) getBean("queryMetaIndPool");
		for (int i = 0; i < count; i++) {
			String sql = " where 1=1 ";
			String showFieldStr = "";
			Map<String, String> value = new HashMap<String, String>();
			List<QueryParam> param = new ArrayList<QueryParam>();
			List<QueryParam> param2 = new ArrayList<QueryParam>();
			String paramStr = "";
			for (Map<String, String> map : queryFields) {
				String name = map.get("ENNAME");
				String cnname = map.get("CNNAME");
				String isDate = map.get("DATATYPE"); // �Ƿ�����
				String matchSymbol = map.get("FILTEROPERATOR");// ƥ�����
				String codeSetId = map.get("CODESETID");// ���뼯id
				String isHomonym = map.get("HOMONYMQUERY");// �Ƿ�ͬ��
				String isST = map.get("STQUERY");// �Ƿ��

				if (name != null && !"".equals(name)) {
					name = name.trim();
					showFieldStr += name + ",";
					if (Const.FILTER_OPER_BETWEEN.equalsIgnoreCase(matchSymbol)) {
						String[] tems1 = request.getParameterValues(name + "START");
						String[] tems2 = request.getParameterValues(name + "END");
						if (tems1 != null && tems2 != null) {
							String temp1 = tems1[i];

							if (temp1 == null) {
								temp1 = "";
							}
							String temp2 = tems2[i];
							if (temp2 == null) {
								temp2 = "";
							}

							if (!"".equals(temp1) && !"".equals(temp2)) {
								// sql += " and " + name + " between '" + temp1 +
								// "' and '" + temp2 + "'";
								value.put(name + "START", temp1);
								value.put(name + "END", temp2);
								value.put(name, temp1 + " - " + temp2);
								paramStr += " " + temp1 + "����" + cnname + "С��" + temp2;
								param.add(new QueryParam(name, filterColumnType(isDate), matchSymbol, temp1 + ";" + temp2, ""));
							}
							else if ("".equals(temp1) && !"".equals(temp2)) {
								// sql += " and " + name + " <= '" + temp2 + "' ";
								value.put(name, temp2);
								value.put(name + "END", temp2);
								paramStr += " " + cnname + "С��" + temp2;
								param.add(new QueryParam(name, filterColumnType(isDate), matchSymbol, temp1 + ";" + temp2, ""));
							}
							else if (!"".equals(temp1) && "".equals(temp2)) {
								// sql += " and " + name + " >= '" + temp1 + "' ";
								value.put(name, temp1);
								value.put(name + "START", temp1);
								paramStr += " " + cnname + "����" + temp1;
								param.add(new QueryParam(name, filterColumnType(isDate), matchSymbol, temp1 + ";" + temp2, ""));
							}
						}

					}
					// else if (Const.FILTER_OPER_LIKE.equalsIgnoreCase(matchSymbol))
					// {
					// String[] tems = request.getParameterValues(name);
					// if (tems!=null&&tems.length>0) {
					//							
					// String temp = tems[i];
					// if (temp != null && !"".equals(temp))
					// {
					// // if (temp.indexOf("%")>-1)
					// // {
					// // sql += " and " + name + " like '" + temp + "' ";
					// // }else {
					// // sql += " and " + name + " like '%" + temp + "%' ";
					// // }
					// value.put(name, temp);
					// paramStr += " " + cnname +"="+temp;
					// param.add(new QueryParam(name,
					// filterColumnType(isDate),Const.FILTER_OPER_EQUAL, temp, ""));
					// }
					// }
					// }
					else {
						String indId = map.get("INDICATORID");
						if (indId == null) indId = "";

						String[] tems = request.getParameterValues(name);
						if (tems != null && tems.length > 0) {
							String temp = tems[i];
							if (temp != null && !"".equals(temp)) {
								// sql += " and " + name + " " + matchSymbol + " '" +
								// temp + "' ";
								value.put(name, temp);
								paramStr += " " + cnname + matchSymbol + temp;
								param.add(new QueryParam(name, filterColumnType(isDate), matchSymbol, temp, ""));
								// ����ֶθ�����ָ��ҹ��������������
								if (!"".equals(indId)) {
									Map<String, String> indMap = queryMetaIndPool.getIndicatorPool(indId);
									if (Const.INDICATOR_NAME.equalsIgnoreCase(indMap.get("code"))) {
										String[] xms = temp.split("[\\s]+");
										// if (xms.length==2) {
										// param.add(new QueryParam(name,
										// filterColumnType(isDate), matchSymbol,
										// xms[1]+" "+xms[0], ""));
										// }else if (xms.length==3) {
										// param.add(new QueryParam(name,
										// filterColumnType(isDate), matchSymbol,
										// xms[2]+" "+xms[1]+" "+xms[0], ""));
										// }
										if (xms.length > 1) {
											for (int j = 0; j < xms.length; j++) {
												param2.add(new QueryParam(name, filterColumnType(isDate), Const.FILTER_OPER_LIKE, "%" + xms[j] + "%", ""));
											}
										}
									}
								}
							}
						}
					}
				}
			}

			if (param.size() > 0 && !"".equals(showFieldStr)) {
				String commonSQL = sqlGen.getQueryStringPart(param, QueryParam.OPER_AND);
				if (commonSQL == null || "".equals(commonSQL)) {
					commonSQL = "";
				}
				else {
					commonSQL = " and " + commonSQL;
				}

				if (!param2.isEmpty()) {
					String spSQL = "";
					spSQL = sqlGen.getQueryStringPart(param2, QueryParam.OPER_AND);
					if (spSQL != null && !"".equals(spSQL)) {
						spSQL = " or (" + spSQL + ") ";
					}
					else {
						spSQL = "";
					}

					commonSQL += spSQL;
				}

				sql = "select count(id) as RECORDCOUNT from " + tableName + " " + sql + commonSQL;
				sqls.add(sql);
				value.put("CONDITION_QUERY_PARAMS", value.toString().replaceAll("\\{", "@").replaceAll("\\}", "@"));
				value.put("query_param_id", String.valueOf(i + 1));
				value.put("query_tab_name", paramStr);
				resTable.add(value);
			}
		}

		return sqls;
	}

	private List<Map<String, String>> initFormForQuery(AdvancedQueryForm advancedQueryForm, String tableId, String type) {
		advancedQueryForm.getQuery().getParameters().put("tableId", tableId);
		BussCodeSetPool bussCodeSetPool = (BussCodeSetPool) getBean("bussCodeSetPool");
		QueryParamCfgService qcs = (QueryParamCfgService) getBean("queryParamCfgService");

		PageQuery pq = new PageQuery();

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

	private String getFormTableStr(AdvancedQueryForm form, List<Map<String, String>> res, List<Map<String, String>> params) {
		StringBuffer headStr = new StringBuffer();
		StringBuffer bodyStr = new StringBuffer();
		StringBuffer scriptStr = new StringBuffer();

		if (res == null) {
			res = new ArrayList<Map<String, String>>();
		}

		if (params == null) {
			params = new ArrayList<Map<String, String>>();
		}

		int resLength = res.size();

		// ���ɱ�ͷ
		headStr.append("<tr style=\"\" height=\"27\"><th width=\"25px\"></th>");
		for (int i = 0; i < resLength; i++) {
			Map<String, String> map = res.get(i);
			String matchSymbol = map.get("FILTEROPERATOR");// ƥ�����
			String codeSetId = map.get("CODESETID");// ���뼯id
			if (Const.FILTER_OPER_BETWEEN.equalsIgnoreCase(matchSymbol)) {
				headStr.append("<th width=\"25%\">").append(map.get("CNNAME")).append("</th>");
			}
			else {
				if (codeSetId != null && !"".equals(codeSetId)) {
					headStr.append("<th width=\"10%\">").append(map.get("CNNAME")).append("</th>");
				}
				else {
					headStr.append("<th>").append(map.get("CNNAME")).append("</th>");
				}
			}
		}
		headStr.append("</tr>");

		// ���ɱ�����
		int limit = 1;
		for (Map<String, String> values : params) {
			bodyStr.append("<tr><td><input type=\"checkbox\" name=\"deletebox\" class=\"checkbox\"/></td>");
			for (int i = 0; i < resLength; i++) {
				Map<String, String> map = res.get(i);
				String isDate = map.get("DATATYPE"); // �Ƿ�����
				String matchSymbol = map.get("FILTEROPERATOR");// ƥ�����
				String codeSetId = map.get("CODESETID");// ���뼯id
				String isHomonym = map.get("HOMONYMQUERY");// �Ƿ�ͬ��
				String isST = map.get("STQUERY");// �Ƿ��
				if (Const.FILTER_OPER_BETWEEN.equalsIgnoreCase(matchSymbol)) {
					bodyStr.append("<td>");
					String value1 = values.get(map.get("ENNAME") + "START");
					if (value1 == null || "".equals(value1)) {
//						value1 = DateUtil.getDateTime("yyyy", new Date(System.currentTimeMillis())) + "-01-01";
						value1 = "";
					}
					String value2 = values.get(map.get("ENNAME") + "END");
					if (value2 == null) {
						value2 = "";
//						value2 = DateUtil.getDateTime("yyyy-MM-dd", new Date(System.currentTimeMillis()));
					}

					if (isDate != null && !"".equals(isDate) && Const.META_TYPE_DATE.equalsIgnoreCase(isDate.trim())) {
						bodyStr.append("<table cellpadding=\"0\" cellspacing=\"0\" width=\"100%\"><tr><td width=\"50%\"><input name=\"" + map.get("ENNAME") + "START\" type=\"text\" value=\"" + value1
								+ "\" width=\"98%\" class=\"Wdate\" onFocus=\"WdatePicker({isShowWeek:true})\" readonly/></td>");
						bodyStr.append("<td width=\"50%\"><input name=\"" + map.get("ENNAME") + "END\" type=\"text\" value=\"" + value2
								+ "\" width=\"98%\" class=\"Wdate\" onFocus=\"WdatePicker({isShowWeek:true})\" readonly/></td></tr></table>");

					}
					else if (codeSetId != null && !"".equals(codeSetId)) {
						bodyStr.append("<table cellpadding=\"0\" cellspacing=\"0\"  width=\"100%\"><tr><td width=\"50%\" class=\"sel\" >").append("<select name=\"" + map.get("ENNAME") + "START" + "\" >");
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
							bodyStr.append("<option value=\"" + code.getValue() + "\" " + selected + ">").append(code.getCodeName()).append("</option>");
						}
						bodyStr.append("</select></td>");

						bodyStr.append("<td width=\"50%\" class=\"sel\">").append("<select name=\"" + map.get("ENNAME") + "END" + "\" >");
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
							bodyStr.append("<option value=\"" + code.getValue() + "\" " + selected + ">").append(code.getCodeName()).append("</option>");
						}
						bodyStr.append("</select></td></tr></table>");
					}
					// ��������
					else {
						bodyStr.append("<table cellpadding=\"0\" cellspacing=\"0\" width=\"100%\"><tr><td width=\"50%\"><input name=\"" + map.get("ENNAME") + "START\" type=\"text\" value=\"" + value1
								+ "\" width=\"98%\" /></td>");
						bodyStr.append("<td width=\"50%\"><input name=\"" + map.get("ENNAME") + "END\" type=\"text\" value=\"" + value2 + "\" width=\"98%\" /></td></tr></table>");

						// if (isHomonym != null && !"".equals(isHomonym)
						// && "1".equalsIgnoreCase(isHomonym.trim()))
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
						// bodyStr.append("<input  type=checkbox name=\"homonym" +
						// map.get("ENNAME")
						// + "\" " + isHom + " value=\"" + isHomonym + "\" />ͬ��");
						// }
						// if (isST != null && !"".equals(isST) && "1".equals(isST))
						// {
						// String st = values.get("ST" + map.get("ENNAME"));
						// if ("1".equals(st))
						// {
						// st = "checked";
						// }
						// else
						// {
						// st = "";
						// }
						// bodyStr.append("<input  type=checkbox name=\"ST" +
						// map.get("ENNAME") + "\" "
						// + st + " value=\"" + isST + "\" />��");
						// }
					}
				}
				else {
					String value = values.get(map.get("ENNAME"));
					if (value == null) {
						value = "";
					}

					if (isDate != null && !"".equals(isDate) && Const.META_TYPE_DATE.equalsIgnoreCase(isDate.trim())) {
						bodyStr.append("<td>");
						bodyStr.append("<input name=\"" + map.get("ENNAME") + " type=\"text\" value=\"" + value + "\" width=\"98%\" class=\"Wdate\" onFocus=\"WdatePicker({isShowWeek:true})\" readonly/>");
					}
					else if (codeSetId != null && !"".equals(codeSetId)) {
						bodyStr.append("<td class=\"sel\">");
						bodyStr.append("<select name=\"" + map.get("ENNAME") + "\" >");
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
							bodyStr.append("<option value=\"" + code.getValue() + "\" " + selected + ">").append(code.getCodeName()).append("</option>");
						}

						bodyStr.append("</select>");
					}
					// ��������
					else {
						bodyStr.append("<td>");
						bodyStr.append("<input name=\"" + map.get("ENNAME") + "\" type=\"text\" value=\"" + value + "\" />");

						// if (isHomonym != null && !"".equals(isHomonym)
						// && "1".equalsIgnoreCase(isHomonym.trim()))
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
						// bodyStr.append("<input  type=checkbox name=\"homonym" +
						// map.get("ENNAME")
						// + "\" " + isHom + " value=\"" + isHomonym + "\" />ͬ��");
						// }
						// if (isST != null && !"".equals(isST) && "1".equals(isST))
						// {
						// String st = values.get("ST" + map.get("ENNAME"));
						// if ("1".equals(st))
						// {
						// st = "checked";
						// }
						// else
						// {
						// st = "";
						// }
						// bodyStr.append("<input  type=checkbox name=\"ST" +
						// map.get("ENNAME") + "\" "
						// + st + " value=\"" + isST + "\" />��");
						// }
					}
				}
				bodyStr.append("</td>");
			}
			bodyStr.append("</tr>");
		}

		scriptStr.append("</tr>");

		headStr.append(bodyStr);
		return headStr.toString();
	}

	private List<String> getScriptRow(AdvancedQueryForm form, List<Map<String, String>> res) {
		List<String> scripts = new ArrayList<String>();
		String firstTd = "<input type=\"checkbox\" name=\"deletebox\"/>";
		scripts.add(firstTd);
		if (res == null) {
			res = new ArrayList<Map<String, String>>();
		}
		int resLength = res.size();
		// ���ɱ�����
		for (int i = 0; i < resLength; i++) {
			StringBuffer bodyStr = new StringBuffer();
			Map<String, String> map = res.get(i);
			String isDate = map.get("DATATYPE"); // �Ƿ�����
			String matchSymbol = map.get("FILTEROPERATOR");// ƥ�����
			String codeSetId = map.get("CODESETID");// ���뼯id
			String isHomonym = map.get("HOMONYMQUERY");// �Ƿ�ͬ��
			String isST = map.get("STQUERY");// �Ƿ��
			if (Const.FILTER_OPER_BETWEEN.equalsIgnoreCase(matchSymbol)) {
				if (isDate != null && !"".equals(isDate) && Const.META_TYPE_DATE.equalsIgnoreCase(isDate.trim())) {
					bodyStr.append("<table cellpadding=\"0\" cellspacing=\"0\" width=\"100%\"><tr><td width=\"50%\"><input name=\"" + map.get("ENNAME")
							+ "START\" type=\"text\" width=\"98%\" class=\"Wdate\" onFocus=\"WdatePicker({isShowWeek:true})\" readonly/></td>");
					bodyStr.append("<td width=\"50%\"><input name=\"" + map.get("ENNAME")
							+ "END\" type=\"text\" width=\"98%\" class=\"Wdate\" onFocus=\"WdatePicker({isShowWeek:true})\" readonly/></td></tr></table>");

				}
				else if (codeSetId != null && !"".equals(codeSetId)) {
					bodyStr.append("<table cellpadding=\"0\" cellspacing=\"0\" width=\"100%\"><tr><td width=\"50%\" class=\"sel\">").append("<select name=\"" + map.get("ENNAME") + "START" + "\" >");
					List<Code> codeSet = form.getCodeSets().get(codeSetId);
					if (codeSet == null) {
						codeSet = new ArrayList<Code>();
					}
					for (Code code : codeSet) {
						bodyStr.append("<option value=\"" + code.getValue() + "\" >").append(code.getCodeName()).append("</option>");
					}
					bodyStr.append("</select></td>");

					bodyStr.append("<td width=\"50%\" class=\"sel\">").append("<select name=\"" + map.get("ENNAME") + "END" + "\" >");
					if (codeSet == null) {
						codeSet = new ArrayList<Code>();
					}
					for (Code code : codeSet) {
						bodyStr.append("<option value=\"" + code.getValue() + "\" >").append(code.getCodeName()).append("</option>");
					}
					bodyStr.append("</select></td></tr></table>");
				}
				// ��������
				else {
					bodyStr.append("<table cellpadding=\"0\" cellspacing=\"0\" width=\"100%\"><tr><td width=\"50%\"><input name=\"" + map.get("ENNAME") + "START\" type=\"text\" width=\"98%\" /></td>");
					bodyStr.append("<td width=\"50%\"><input name=\"" + map.get("ENNAME") + "END\" type=\"text\" width=\"98%\" /></td></tr></table>");

					if (isHomonym != null && !"".equals(isHomonym) && "1".equalsIgnoreCase(isHomonym.trim())) {
						bodyStr.append("<input  type=checkbox name=\"homonym" + map.get("ENNAME") + "\" value=\"" + isHomonym + "\" />ͬ��");
					}
					if (isST != null && !"".equals(isST) && "1".equals(isST)) {
						bodyStr.append("<input  type=checkbox name=\"ST" + map.get("ENNAME") + "\" value=\"" + isST + "\" />��");
					}
				}
			}
			else {
				if (isDate != null && !"".equals(isDate) && Const.META_TYPE_DATE.equalsIgnoreCase(isDate.trim())) {
					bodyStr.append("<input name=\"" + map.get("ENNAME") + " type=\"text\" width=\"98%\" class=\"Wdate\" onFocus=\"WdatePicker({isShowWeek:true})\" readonly/>");
				}
				else if (codeSetId != null && !"".equals(codeSetId)) {
					bodyStr.append("<select name=\"" + map.get("ENNAME") + "\" >");
					List<Code> codeSet = form.getCodeSets().get(codeSetId);

					if (codeSet == null) {
						codeSet = new ArrayList<Code>();
					}
					for (Code code : codeSet) {
						bodyStr.append("<option value=\"" + code.getValue() + "\" >").append(code.getCodeName()).append("</option>");
					}

					bodyStr.append("</select>");
				}
				// ��������
				else {
					bodyStr.append("<input name=\"" + map.get("ENNAME") + "\" type=\"text\" />");

					// if (isHomonym != null && !"".equals(isHomonym)
					// && "1".equalsIgnoreCase(isHomonym.trim()))
					// {
					// bodyStr.append("<input  type=checkbox name=\"homonym" +
					// map.get("ENNAME")
					// + "\" value=\"" + isHomonym + "\" />ͬ��");
					// }
					// if (isST != null && !"".equals(isST) && "1".equals(isST))
					// {
					// bodyStr.append("<input  type=checkbox name=\"ST" +
					// map.get("ENNAME")
					// + "\" value=\"" + isST + "\" />��");
					// }
				}
			}
			scripts.add(bodyStr.toString());
		}
		// String lastTd =
		// "<a href=\"javascript:void(0);\" class=\"sbuBtnStyle\" onclick=\"deletRow(this);return false;\"><strong><span class=\"delIcon\">&nbsp;</span>ɾ��</strong></a>";
		return scripts;
	}

	private Map<String, String> getWhereMapByParamsStr(String params) {

		if (params != null) {
			params = params.replaceAll("@", "");
		}
		String[] paramArray = params.split(",");
		Map<String, String> values = new HashMap<String, String>();
		for (int i = 0; i < paramArray.length; i++) {
			String[] param = paramArray[i].split("=");
			if (param.length == 2) {
				String name = param[0];
				String value = param[1];
				if (name != null && !"".equals(name)) {
					name = name.trim();
					values.put(name, value);
				}
			}
		}
		return values;
	}

	private String getWhereStrForBase(List<Map<String, String>> queryParames, Map<String, String> queryValue) {
		String str = " where 1=1 ";
		List<QueryParam> param = new ArrayList<QueryParam>();
		List<QueryParam> param2 = new ArrayList<QueryParam>();
		BaseSqlGen sqlGen = (BaseSqlGen) getBean("sybaseSqlGen");
		QueryMetaIndPool queryMetaIndPool = (QueryMetaIndPool) getBean("queryMetaIndPool");
		for (Map<String, String> bean : queryParames) {
			String dataType = bean.get("DATATYPE"); // �Ƿ�����
			String opType = bean.get("FILTEROPERATOR");
			String name = bean.get("ENNAME");

			if (name != null && !"".equals(name)) {
				String indId = bean.get("INDICATORID");
				if (indId == null) indId = "";

				if (opType != null && "BT".equalsIgnoreCase(opType)) {

					String getNameSt = name + "START";
					String getNameEn = name + "END";
					String stValue = queryValue.get(getNameSt);
					if (stValue == null) {
						stValue = "";
					}
					String enValue = queryValue.get(getNameEn);
					if (enValue == null) {
						enValue = "";
					}

					if (!"".equals(stValue) || !"".equals(enValue)) {
						param.add(new QueryParam(name, filterColumnType(dataType), opType, stValue + ";" + enValue, ""));
					}
				}
				else {
					String value = queryValue.get(name.trim());
					// ���ڳ�between�����������=��ѯ
					if (value != null && !"".equals(value.trim())) {
						param.add(new QueryParam(name, filterColumnType(dataType), "=", value, ""));// pool.getFilterOperator()
						// ����ֶθ�����ָ��ҹ��������������
						if (!"".equals(indId)) {
							Map<String, String> indMap = queryMetaIndPool.getIndicatorPool(indId);
							if (Const.INDICATOR_NAME.equalsIgnoreCase(indMap.get("code"))) {
								String[] xms = value.split("[\\s]+");
								// if (xms.length==2) {
								// param.add(new QueryParam(name,
								// filterColumnType(dataType), opType,
								// xms[1]+" "+xms[0], ""));
								// }else if (xms.length==3) {
								// param.add(new QueryParam(name,
								// filterColumnType(dataType), opType,
								// xms[2]+" "+xms[1]+" "+xms[0], ""));
								// }
								if (xms.length > 1) {
									for (int i = 0; i < xms.length; i++) {
										param2.add(new QueryParam(name, filterColumnType(dataType), Const.FILTER_OPER_LIKE, "%" + xms[i] + "%", ""));
									}
								}
							}
						}
					}
				}
			}
		}
		if (param.size() > 0) {
			String commonSQL = sqlGen.getQueryStringPart(param, QueryParam.OPER_AND);
			if (commonSQL == null || "".equals(commonSQL)) {
				commonSQL = "";
			}
			else {
				commonSQL = " and " + commonSQL;
			}

			str += commonSQL;
		}

		if (!param2.isEmpty()) {
			String spSQL = "";
			spSQL = sqlGen.getQueryStringPart(param2, QueryParam.OPER_AND);
			if (spSQL != null && !"".equals(spSQL)) {
				spSQL = " or (" + spSQL + ") ";
			}
			else {
				spSQL = "";
			}

			str += spSQL;
		}
		return str;
	}

	private void initParamsValues(List<Map<String, String>> paramsValue, String allparams) {
		if (allparams == null || "".equals(allparams)) {
			paramsValue.add(new HashMap<String, String>());
			return;
		}

		if (allparams.length() > 3) {
			allparams = allparams.substring(1, allparams.length() - 1);
			String[] oneParams = allparams.split("\\}\\, \\{");
			StringBuffer sb = new StringBuffer();
			// ������ת����map���󲢱��浽list��
			for (int i = 0; i < oneParams.length; i++) {
				String temp = oneParams[i];
				if (temp != null) {
					Map<String, String> keyValue = new HashMap<String, String>();
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

							keyValue.put(key, value);
							sb.append(","+value);
						}
					}
					keyValue.put("sheetName", sb.substring(1).toString());
					paramsValue.add(keyValue);
				}
			}
		}
		else {
			paramsValue.add(new HashMap<String, String>());
			return;
		}
	}

	private String filterColumnType(String opType) {
		if (Const.META_TYPE_NUMERIC.equals(opType)) return QueryParam.COLUMN_TYPE_INT;
		else if (Const.META_TYPE_DATE.equals(opType)) return QueryParam.COLUMN_TYPE_DATE;
		else if (Const.META_TYPE_STRING.equals(opType)) return QueryParam.COLUMN_TYPE_STRING;
		else return QueryParam.COLUMN_TYPE_STRING;
	}
	
	private void sortByCount(List<Map<String, String>> src){
		Map<String, String> temp = new HashMap<String, String>();
		for (int i = 0; i < src.size(); i++) {
			for (int j = i+1; j < src.size(); j++) {
				int isrc = Integer.parseInt((String)src.get(i).get("CONDITION_QUERY_COUNT"));
				int jsrc = Integer.parseInt((String)src.get(j).get("CONDITION_QUERY_COUNT"));
				
				if (jsrc>isrc) {
					temp = src.get(i);
					src.set(i, src.get(j));
					src.set(j, temp);
				}
			}
		}
	}
}
