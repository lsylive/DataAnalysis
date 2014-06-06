package com.liusy.datapp.web.compare.action;

import java.io.PrintWriter;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.dom4j.DocumentHelper;
import zeal.util.GB2Big5;

import com.liusy.core.util.Const;
import com.liusy.core.util.Session;
import com.liusy.dataapplatform.base.exception.WebException;
import com.liusy.dataapplatform.base.util.BaseSqlGen;
import com.liusy.dataapplatform.base.util.PageQuery;
import com.liusy.dataapplatform.base.util.QueryParam;
import com.liusy.dataapplatform.code.Code;
import com.liusy.datapp.dao.query.SynthesisTempSpaceDao;
import com.liusy.datapp.model.compare.CompareInfo;
import com.liusy.datapp.model.compare.CompareTableRelation;
import com.liusy.datapp.model.datastandard.StandardCategory;
import com.liusy.datapp.model.datastandard.StandardCode;
import com.liusy.datapp.model.resource.ResourceColumn;
import com.liusy.datapp.model.resource.ResourceTable;
import com.liusy.datapp.model.system.config.SysCity;
import com.liusy.datapp.service.compare.CompareInfoService;
import com.liusy.datapp.service.compare.CompareSlaveResultService;
import com.liusy.datapp.service.compare.CompareTableRelationService;
import com.liusy.datapp.service.dynamicquery.SynthesisColumnGenService;
import com.liusy.datapp.service.pool.BussCodeSetPool;
import com.liusy.datapp.service.pool.TableConfigPool;
import com.liusy.datapp.service.query.QueryParamCfgService;
import com.liusy.datapp.service.query.SynthesisQueryService;
import com.liusy.datapp.service.resource.ResourceColumnService;
import com.liusy.datapp.service.resource.ResourceTableService;
import com.liusy.datapp.util.poolobj.ColumnConfigPoolObj;
import com.liusy.datapp.util.poolobj.ColumnPoolObj;
import com.liusy.datapp.util.poolobj.QueryParamPoolObj;
import com.liusy.datapp.web.BaseAction;
import com.liusy.datapp.web.compare.form.CompareResultRecordForm;
import com.liusy.datapp.web.dynamicquery.form.AdvancedQueryForm;

import com.liusy.web.tag.grid.Column;

public class CompareResultRecordAction extends BaseAction
{

	@Override
	public ActionForward doExecute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
		HttpServletResponse response) throws Exception
	{
		String action = request.getParameter("action");
		if (action == null || "".equals(action))
		{
			action = LIST;
		}
		if (log.isDebugEnabled())
			log.debug("action:" + action);

		ActionForward forward;
		CompareResultRecordForm compareResultRecordForm = (CompareResultRecordForm) form;

		try
		{
			if (LIST.equalsIgnoreCase(action))
			{
				forward = searchResourceTable(mapping, compareResultRecordForm, request, response); // 打开查询资源表列表页面
			}
			else if ("TREE".equalsIgnoreCase(action))
			{
				returnTreeNode(response, request);
				return null;
			}
			else if ("MAIN".equalsIgnoreCase(action))
			{
				return mapping.findForward("MAIN");
			}
			// else if ("SELECT".equalsIgnoreCase(action))
			// {
			// getCategoryForSelection(request, response);
			// return null;
			// }
			else if ("TOADVANCEDPAGE".equalsIgnoreCase(action))
			{
				forward = toAdvancePage(mapping, compareResultRecordForm, request, response);
			}
			else if ("BASEQUERY".equalsIgnoreCase(action))
			{
				forward = baseQuery(mapping, compareResultRecordForm, request, response);
			}
			// else if (VIEW.equalsIgnoreCase(action))
			// {
			// forward = viewQuery(mapping, advancedQueryForm, request,
			// response);
			// }
			else
			{
				request.setAttribute("err", new WebException("找不到该action方法：" + action));
				forward = mapping.findForward(ERROR);// 找不到合适的action
			}
			
			Session session=(Session) request.getSession().getAttribute(Const.SESSION);
			//判断是否配置个人空间
			Integer spaceId=0;
			if(session.getSpaceId()==null || "".equals(session.getSpaceId().trim()) || Const.TAG_DISABLE.equals(session.getSpaceId()))
			{
				request.setAttribute("hasspace", "0");
			}
			else{
				request.setAttribute("hasspace", "1");
			}
		}
		catch (Exception e)
		{// 其他系统出错

			e.printStackTrace();
			request.setAttribute("err", e);
			forward = mapping.findForward(ERROR);
		}
		return forward;
	}

	private ActionForward searchResourceTable(ActionMapping mapping,
			CompareResultRecordForm advancedQueryForm, HttpServletRequest request, HttpServletResponse response)
		throws Exception
	{
		if (log.isDebugEnabled())
			log.debug("Entering 'searchResource' method");
		try
		{
			Map<String, String> params = advancedQueryForm.getQuery().getParameters();
			String treeId = request.getParameter("treeId");
			if (treeId != null && !"".equals(treeId))
			{
				if (treeId.indexOf("_") > -1)
				{
					String[] ids = treeId.split("_");
					if (ids.length == 2)
					{
						params.put("cityCode", ids[0]);
						params.put("tradeId", ids[1]);
					}
					else if (ids.length == 3)
					{
						params.put("cityCode", ids[0]);
						params.put("tradeId", ids[1]);
						params.put("categoryId", ids[2]);
					}
					else if (ids.length > 3)
					{
						params.put("cityCode", ids[0]);
						params.put("tradeId", ids[ids.length - 2]);
						params.put("categoryId", ids[ids.length - 1]);
					}
				}
				else
				{
					params.put("cityCode", treeId);
				}
			}

			ResourceTableService ResourceTableService = (ResourceTableService) getBean("resourceTableService");
			PageQuery pageQuery = advancedQueryForm.getQuery();
			pageQuery.setSelectParamId("GET_RESOURCETABLE_FOR_ADVANCEDQUERY");
			String queryString = getQueryString(pageQuery);
			pageQuery.getParameters().put("queryString", queryString);
			// 执行查询并将结果保存到pagequery中，结果集是一个list<map>对象，用map将每个字段名作key，值做value保存起来
			ResourceTableService.queryForAdvancedQuery(pageQuery);
			setPage(pageQuery);
			initForm(advancedQueryForm);
			return mapping.findForward(LIST);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			request.setAttribute("err", e);
			return mapping.findForward(ERROR);
		}
	}

	public ActionForward toAdvancePage(ActionMapping mapping, CompareResultRecordForm form,
		HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		try
		{
			String tableId = request.getParameter("tableId");
			if (tableId == null || "".equals(tableId))
			{
				tableId = form.getQuery().getParameters().get("tableId");
			}

			String condId = request.getParameter("condId");
			if(condId==null)condId = "";
			
			String paramStr = form.getQuery().getParameters().get("pramaStr");
			Map<String, String> values = new HashMap<String, String>();
			List<Map<String, String>> res = initFormForQuery(form, tableId, "base");
			if (paramStr!=null&&!"".equals(paramStr))
			{
				initParamsValues(values, paramStr);
			}else if (!"".equals(condId))
			{
				//PersonalQueryConditionService personalQueryConditionService=(PersonalQueryConditionService)getBean("personalQueryConditionService");
				//PersonalQueryCondition cond=personalQueryConditionService.findPersonalQueryCondition(Integer.valueOf(condId));
				//form.setQueryName(cond.getQueryName());
				//values = conditionToValues(condId, request);
			}
			// 生成所有输入项表格字符串
			String formStr = getFormTableStr(values, form, res);

			// 保存到request
			request.setAttribute("formStr", formStr);
			form.getQuery().getParameters().put("tableId", tableId);

			return mapping.findForward("BASEQUERY");
		}
		catch (Exception e)
		{
			e.printStackTrace();
			request.setAttribute("err", e);
			return mapping.findForward(ERROR);
		}

	}

	// 基本查询
	public ActionForward baseQuery(ActionMapping mapping, CompareResultRecordForm form,
		HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		try
		{
			String viewType = request.getParameter("viewType");
			String tableId = form.getQuery().getParameters().get("tableId");
			if (tableId == null || "".equals(tableId))
			{
				tableId = request.getParameter("tableId");
			}
			
			String runId = request.getParameter("runId");
			String tableEnName = request.getParameter("tableEnName");
			
			request.setAttribute("idArr","1");
			if (runId==null || tableEnName == null)
			{
				
				return mapping.findForward("BASEQUERYCOUNT");
			}
			
			ResourceTableService resourceTableService = (ResourceTableService) getBean("resourceTableService");
			CompareSlaveResultService compareSlaveResultService = (CompareSlaveResultService)getBean("compareSlaveResultService");
			ResourceColumnService resourceColumnService = (ResourceColumnService)getBean("resourceColumnService");
			SynthesisQueryService sqs = (SynthesisQueryService) getBean("synthesisQueryService");
			SynthesisTempSpaceDao synthesisTempSpaceDao = (SynthesisTempSpaceDao) getBean("synthesisTempSpaceDao");
			TableConfigPool tcp = (TableConfigPool) getBean("tableConfigPool");
			BussCodeSetPool bussCodeSetPool=(BussCodeSetPool)getBean("bussCodeSetPool");
			CompareInfoService compareInfoService = (CompareInfoService)getBean("compareInfoService");
			CompareTableRelationService compareTableRelationService = (CompareTableRelationService)getBean("compareTableRelationService");
			
			ResourceTable resourceTable = null;
			if (tableId == null){
				resourceTable = resourceTableService.getResouceTableByEnName(tableEnName);
				if (resourceTable==null) {
					//初始页面
					return mapping.findForward("BASEQUERYCOUNT");
			}
				tableId = String.valueOf(resourceTable.getId());
				form.getQuery().getParameters().put("tableId", tableId);
			}
			else
			{
				resourceTable = resourceTableService.findResourceTable(Integer.parseInt(tableId));
			}
			boolean isPersonalTable = false;
			if (resourceTable.getIsSpaceTable()!=null && resourceTable.getIsSpaceTable().equals(Const.IS_SPACCETABLE))
			{
				isPersonalTable = true;
			}
			form.getQuery().getParameters().put("isPersonalTable", isPersonalTable?"1":"0");
			form.getQuery().getParameters().put("tableCnName", resourceTable.getCnName());
			request.setAttribute("tableCnName", resourceTable.getCnName());
			
			ResourceColumn pkobj=resourceColumnService.findResoucrePKColumn(Integer.parseInt(tableId));
			CompareInfo compareInfo = compareInfoService.getCompareInfoByRunId(Integer.parseInt(runId));
			
			String sqlWhere="";
			String[] sqlWheres = null;
			String[] colAttr = null;
			if (viewType.equals("0"))//主表浏览
			{
				Collection<Serializable> mainPkIdList = compareSlaveResultService.getMainPkIdsByRunIdAndSlaveTableName(Integer.parseInt(runId), tableEnName);
				Serializable[] mainPkIdListArray = new Serializable[mainPkIdList.size()];
				Collection<Collection<Serializable>> mainPkIdListArr = new LinkedList<Collection<Serializable>>();
				if (mainPkIdList.size() < 1000) //maximum number of expressions in a list is 1000
				{
					mainPkIdListArr.add(mainPkIdList);
				}
				else
				{
					mainPkIdListArray = mainPkIdList.toArray(mainPkIdListArray);
					for(int i = 0; i < mainPkIdListArray.length; i += 1000)
					{
						Collection<Serializable> newPkIdList = new LinkedHashSet<Serializable>();
						for(int j = 0 ; j < 1000; j ++)
						{
							if (i + j == mainPkIdListArray.length) break;
							newPkIdList.add(mainPkIdListArray[i + j]);
						}
						mainPkIdListArr.add(newPkIdList);
					}
				}
				sqlWheres = new String[mainPkIdListArr.size()];
				int index = 0;
				for(Collection<Serializable> newPkIdList:mainPkIdListArr){
					sqlWheres[index++] = buildWhereIdInClause(newPkIdList,pkobj.getDataType());
				}
				
				if (sqlWheres.length == 0 || sqlWheres[0] == null || sqlWheres[0].equals(""))
				{
					return mapping.findForward("BASEQUERYCOUNT");
				}
				if (compareInfo!=null)
				{
					String cols = compareInfo.getColList();
					if (cols!=null) colAttr = cols.split(",");
						
				}
			}
			else//从表浏览
			{	
				String mainPkId = request.getParameter("mainPkId");
				String slavePkIdsArr[] = compareSlaveResultService.getSlavePkIdsArrByRunIdAndSlaveTableNameAndPkId(Integer.parseInt(runId),tableEnName,Integer.parseInt(mainPkId));
				int index = 0;
				sqlWheres = new String[slavePkIdsArr.length];
				for(String slavePkIds:slavePkIdsArr){
					Collection<Serializable> slavePkIdArr = new LinkedList<Serializable>();
					String[] tempArray = slavePkIds.split(",");
					for(String slaveId : tempArray)
					{
						slavePkIdArr.add(slaveId);
					}
				
				sqlWheres[index++] = buildWhereIdInClause(slavePkIdArr,pkobj.getDataType());
				}
				
				if(compareInfo!=null)
				{
					CompareTableRelation relation = compareTableRelationService.getCompareTableRelationByCompIdAndDtId(compareInfo.getId(), Integer.parseInt(tableId));
					String cols = relation.getColList();
					if (cols!=null) colAttr = cols.split(",");
				}
			}
			
//			// 根据参数得到动态表单的输入项名称和值，以map保存，name为key，value为值
//			Map<String, String> values = getParamMap(form);
//			//将查询参数保存到form中
//			String paramStr = form.getQuery().getParameters().get("pramaStr");
//			if (paramStr!=null&&!"".equals(paramStr))
//			{
//				initParamsValues(values, paramStr);
//			}
//			else {
//				form.getQuery().getParameters().put("pramaStr", values.toString());
//			}
//			// 初始化表单项字符串并保存
//			List<Map<String, String>> res = initFormForQuery(form, tableId, "base");
//			String formStr = getFormTableStr(values, form, res);
//			request.setAttribute("formStr", formStr);
//
//			// 得到查询条件参数
//			List<QueryParamPoolObj> queryParam = tcp.getParamConfigPool(tableId);
//			// 遍历查询条件参数，利用页面传过来的查询参数和操作符得到where条件语句
//			String sqlWhere = "";
//			if (values != null && queryParam != null)
//			{
//				sqlWhere = getWhereStrForBase(res, values);
//			}

			// 得到显示字段
			List<ColumnConfigPoolObj> allColumn = tcp.getColumnConfigPool(tableId);
			List<ColumnPoolObj> columnpro = tcp.getTableColumnPool(tableId);
			
			//为个人资源表加入字段显示配置信息
			if (isPersonalTable && allColumn.size() == 0)
			{
				for(ColumnPoolObj cpo: columnpro)
				{
					ColumnConfigPoolObj ccpo = new ColumnConfigPoolObj();
					ccpo.setColumnId(cpo.getId());
					ccpo.setIsShown("1");
					ccpo.setIsSubject("1");
					ccpo.setIsSortable("1");
					allColumn.add(ccpo);
				}
				
			}
			// 表头属性
			// Map<String, Map<String, String>> tableHead = new HashMap<String,
			// Map<String,String>>();

			// 遍历显示字段，得到显示字段字符串，和表头信息
			String showColumn = pkobj.getName() + " as ID,";
			List<Column> columns = new ArrayList<Column>();
			
			Column idCol=new Column();
			idCol.setWidth("1px");
			idCol.setStyle("display:none");
			idCol.setProperty("ID");
			columns.add(idCol);
			
			int columnIndex = 0;
			for (ColumnConfigPoolObj obj : allColumn)
			{
				columnIndex ++;
				if ("1".equals(obj.getIsSubject()))
				{
					for (ColumnPoolObj column : columnpro)
					{
						if (column.getId() != null)
						{
							if (obj.getColumnId().trim().equals(column.getId().trim()))
							{
								showColumn += column.getName() + ",";
								Column colObj = new Column();

								colObj.setName(column.getCnName());
								colObj.setProperty(column.getName().toUpperCase());
								colObj.setHeaderOnMouseOut("headerOut(this)");
								colObj.setHeaderOnMouseOver("headerOver(this)");
								colObj.setId(column.getId());
								if ("0".equals(viewType) && columnIndex == 1)
								{
									colObj.setItemType("hyperlink");
									colObj.setOnClick("viewSlave('{ID}')");
									colObj.setHref("#");
									colObj.setStyleClass("HLight");
								}
								if (colAttr!=null)
								{
									if (Arrays.asList(colAttr).contains(column.getId()))//凸显对比列标题
									{
										colObj.setHeaderStyle("color:red");
									}
									
								}
								
								// 显示宽度
								if (obj.getDisplayWidth() != null)
								{
									colObj.setWidth(obj.getDisplayWidth());
								}
								// 是否可排序
								if ("1".equals(obj.getIsSortable()))
								{
									colObj.setHeaderOnClick("queryBase('" + column.getName() + "')");
								}

								// 判断用户权限，若用户没有该权限则屏蔽掉该列
								// column.getSecurityLevel();
								columns.add(colObj);
							}
						}
					}
				}
			}
			// 将表头保存到queset中
			if (columns.isEmpty())
			{
				Column column = new Column();
				column.setName("您要查询的数据库不存在！");
				columns.add(column);
			}
			request.setAttribute(Column.DYNAMICCOLUMNS, columns);

			// 组装sql语句进行查询
			if (showColumn != null && !"".equals(showColumn))
			{
				showColumn = showColumn.substring(0, showColumn.length() - 1);
			}
			else
			{
				throw new Exception("没有可显示列表。。。。。。。。");
			}
			// 得到表名
			Map<String, String> table = tcp.getTableMap(tableId);
			String tableName = table.get("name");

			// 得到页面排序字段
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
			String sqlAll = null;
			if (sqlWheres.length == 1)
			{
				sqlAll = "select " + showColumn + " from " + tableName + " where "+sqlWheres[0];
			}
			else
			{
				StringBuffer sbSql = new StringBuffer("select " + showColumn + " from " + tableName + " where ");
				
				for(String where : sqlWheres)
				{
					sbSql.append(where).append(" or ");
					
				}
				sbSql.delete(sbSql.length()-3, sbSql.length());
				sqlAll = sbSql.toString();
			}
			
			String type = request.getParameter("queryType");
			
			if (type==null||"".equals(type))
			{
				if (isPersonalTable)
				{
					synthesisTempSpaceDao.queryBySql(sqlAll, form.getQuery());
				}
				else
				{
					sqs.queryBySql(sqlAll, form.getQuery());
				}
				//过滤代码集
				bussCodeSetPool.filterBussCodeSet(form.getQuery().getRecordSet(), columnpro);
				//过滤安全等级字段
				Session currentUser = (Session)request.getSession().getAttribute(Const.SESSION);
				SynthesisColumnGenService synthesisColumnGenService=(SynthesisColumnGenService)getBean("synthesisColumnGenService");
				synthesisColumnGenService.filterSecurityLevel(tableId,form.getQuery().getRecordSet(), columnpro, pkobj, currentUser);
				setPage(form.getQuery());
			}
			
			//取出该页id集合
			List<Map<String, String>> list = form.getQuery().getRecordSet();
			if (list==null)
			{
				list = new ArrayList<Map<String,String>>();
			}
			StringBuffer idArr=new StringBuffer();
			for(Map<String,String> idmap:list){
				idArr.append(idmap.get("ID")+",");
			}
			request.setAttribute("idArr",idArr.length()>0?idArr.substring(0,idArr.length()-1):"");
			return mapping.findForward("BASEQUERYCOUNT");
		}
		catch (Exception e)
		{
			e.printStackTrace();
			request.setAttribute("err", e);
			return mapping.findForward(ERROR);
		}

	}


	
	private String getQueryString(PageQuery pageQuery)
	{
		String order = pageQuery.getOrder();
		if (order==null)
		{
			order = "";
		}
		String dire = pageQuery.getOrderDirection();
		if (dire==null)
		{
			dire = "";
		}
		if ("".equals(order)&&"".equals(dire))
		{
			pageQuery.setOrder("CN_NAME");
			pageQuery.setOrderDirection(PageQuery.ASC);
		}
		
		StringBuffer buffer = new StringBuffer();

		Map<String, String> params = pageQuery.getParameters();
		if (params == null)
		{
			params = new HashMap<String, String>();
		}
		String cityCode = params.get("cityCode");
		String tradeId = params.get("tradeId");
		String categoryId = params.get("categoryId");
		// String type = params.get("type");

		if (cityCode != null && !"".equals(cityCode) && !"-".equals(cityCode))
		{
			buffer.append(" and r.city_code='").append(cityCode).append("'");
		}
		if (tradeId != null && !"".equals(tradeId) && !"-".equals(tradeId))
		{
			buffer.append(" and (ca.parent_id=").append(tradeId).append(" or r.category_id=").append(tradeId
				+ ")");
		}
		if (categoryId != null && !"".equals(categoryId) && !"-".equals(categoryId))
		{
			buffer.append(" and r.category_id=").append(categoryId);
		}
		// if (type != null && !"".equals(type) && !"-".equals(type))
		// {
		// buffer.append(" and r.type='").append(type).append("'");
		// }
		return buffer.toString();
	}

	private Integer[] getCategoryChildsId(String parentId)
	{
		ResourceTableService resourceTableService = (ResourceTableService) getBean("resourceTableService");
		List<Map<String, Object>> list = resourceTableService.getAllCategorysByParentId(parentId);
		if (list != null || !list.isEmpty())
		{
			Integer[] ids = new Integer[list.size()];
			for (int i = 0; i < ids.length; i++)
			{
				ids[i] = (Integer) list.get(i).get(StandardCategory.PROP_ID);
			}
			return ids;
		}

		return null;
	}

	private List<Map<String, String>> initFormForQuery(CompareResultRecordForm advancedQueryForm,
		String tableId, String type)
	{
		advancedQueryForm.getQuery().getParameters().put("tableId", tableId);
		BussCodeSetPool bussCodeSetPool = (BussCodeSetPool) getBean("bussCodeSetPool");
		QueryParamCfgService qcs = (QueryParamCfgService) getBean("queryParamCfgService");

		PageQuery pq = new PageQuery();
		// 基本查询

		// 取得查询参数，包括字段中英文名、字段指标id，字段类型，字段代码集id，及参数配置表中其他的项目
		pq.setSelectParamId("GET_FORMPARAM_FOR_ADVANCEDQUERY");
		pq.getParameters().put("queryString", " and t.table_id=" + tableId);
		pq.setPageSize("0");
		pq.setOrder("t.seq_no");
		pq.setOrderDirection("asc");
		qcs.queryQueryParamCfg(pq);

		List<Map<String, String>> res = pq.getRecordSet();
		if (res == null)
		{
			res = new ArrayList<Map<String, String>>();
		}

		for (Map<String, String> map : res)
		{

			String codeSetId = map.get("CODESETID");
			if (codeSetId != null && !"".equals(codeSetId))
			{
				List<StandardCode> codeSet = bussCodeSetPool.getCodeListByCodeSetId(codeSetId);
				setCode(advancedQueryForm, codeSetId, codeSet, StandardCode.PROP_NAME, StandardCode.PROP_VALUE);
			}
		}
		return res;
	}

	private void initForm(CompareResultRecordForm advancedQueryForm)
	{
		setCode(advancedQueryForm, "DT_TYPE");
		ResourceTableService rs = (ResourceTableService) getBean("resourceTableService");
		List<SysCity> citys = rs.findAllCitys();

		if (citys == null)
		{
			citys = new ArrayList<SysCity>();
		}
		setCode(advancedQueryForm, "Citys", citys, SysCity.PROP_NAME, SysCity.PROP_CODE);
		// advancedQueryForm.getCodeSets().get("Citys").get(0).setCodeName("全部");
		advancedQueryForm.getCodeSets().get("Citys").remove(0);
		// 设置行业代码集
		List<StandardCategory> trade = rs.findAllTrade();
		if (trade == null)
		{
			trade = new ArrayList<StandardCategory>();
		}
		setCode(advancedQueryForm, "Trade", trade, StandardCategory.PROP_NAME, StandardCategory.PROP_ID);
		// advancedQueryForm.getCodeSets().get("Trade").get(0).setCodeName("全部");
		advancedQueryForm.getCodeSets().get("Trade").remove(0);

		
		// 设置分类代码集
		List<StandardCategory> categorys;
		String tradeId = advancedQueryForm.getQuery().getParameters().get("tradeId");
		if (tradeId == null || "".equals(tradeId))
		{
			// 设置分类代码集为全部
			categorys = rs.findCategoryByTradeId(null);
		}
		else
		{
			// 设置分类代码集为该行业tradeId下面的所有分类
			categorys = rs.findCategoryByTradeId(tradeId);
		}
		setCode(advancedQueryForm, "Categorys", categorys, StandardCategory.PROP_NAME, StandardCategory.PROP_ID);
		// advancedQueryForm.getCodeSets().get("Categorys").get(0).setCodeName("全部");
		advancedQueryForm.getCodeSets().get("Categorys").remove(0);
	}

	// 一次性拉取整棵树的所有子节点
	private void returnTreeNode(HttpServletResponse response, HttpServletRequest request)
		throws Exception
	{

		ResourceTableService resourceTableService = (ResourceTableService) getBean("resourceTableService");
		String XmlStr = resourceTableService.getAllTreeNodeAsXmlString(null);

		if (XmlStr == null || "".equals(XmlStr))
		{
			XmlStr = DocumentHelper.createElement("tree").addAttribute("id", "0").addElement("item").addAttribute("text", "广东省资源目录").addAttribute("id", "province").addAttribute("open", "1").addAttribute("child", "1").addElement("userdata").addAttribute("name", "name").addText("广东省资源目录").asXML();
		}
		response.setContentType("text/xml;charset=UTF-8");
		PrintWriter writer = response.getWriter();
		writer.write(XmlStr);
		writer.close();
	}

	private String getFormTableStr(Map<String, String> values, CompareResultRecordForm form,
		List<Map<String, String>> res)
	{
		if (values == null)
		{
			values = new HashMap<String, String>();
		}

		StringBuffer str = new StringBuffer();

		if (res == null)
		{
			res = new ArrayList<Map<String, String>>();
		}

		int resLength = res.size();

		for (int i = 0; i < resLength; i += 2)
		{
		//第一个
		Map<String, String> map = res.get(i);
		String isDate = map.get("DATATYPE"); // 是否日期
		String matchSymbol = map.get("FILTEROPERATOR");// 匹配符号
		String codeSetId = map.get("CODESETID");// 代码集id
		String isHomonym = map.get("HOMONYMQUERY");// 是否同音
		String isST = map.get("STQUERY");// 是否简繁

		str.append("<tr>").append("<td>").append(map.get("CNNAME")).append(":</td>");
		if (Const.FILTER_OPER_BETWEEN.equals(matchSymbol))
		{
			String value1 = values.get(map.get("ENNAME") + "START");
			if (value1 == null)
			{
				value1 = "";
			}
			String value2 = values.get(map.get("ENNAME") + "END");
			if (value2 == null)
			{
				value2 = "";
			}
			//是否日期型
			if (isDate != null && !"".equals(isDate)
				&& Const.META_TYPE_DATE.equalsIgnoreCase(isDate.trim()))
			{
				str.append("<td><table cellpadding=\"0\" cellspacing=\"0\"><tr><td width=\"50%\"><input name=\"query.parameters("
					+ map.get("ENNAME")
					+ "START)\" type=\"text\" value=\""
					+ value1
					+ "\" width=\"98%\" class=\"Wdate\" onFocus=\"WdatePicker({isShowWeek:true})\" readonly/></td>");
				str.append("<td width=\"50%\"><input name=\"query.parameters("
					+ map.get("ENNAME")
					+ "END)\" type=\"text\" value=\""
					+ value2
					+ "\" width=\"98%\" class=\"Wdate\" onFocus=\"WdatePicker({isShowWeek:true})\" readonly/></td></tr></table></td>");
			}
			//是否代码项
			else if (codeSetId != null && !"".equals(codeSetId)) {
				str.append("<td><table cellpadding=\"0\" cellspacing=\"0\"><tr><td width=\"50%\" class=\"sel\">");
				str.append("<select name=\"query.parameters(" + map.get("ENNAME") + "START)\" >");
				List<Code> codeSet = form.getCodeSets().get(codeSetId);

				if (codeSet == null)
				{
					codeSet = new ArrayList<Code>();
				}
				for (Code code : codeSet)
				{
					String selected;
					if (value1.equalsIgnoreCase(code.getValue()))
					{
						selected = "selected";
					}
					else
					{
						selected = "";
					}
					str.append("<option value=\"" + code.getValue() + "\" " + selected + ">").append(code.getCodeName()).append("</option>");
				}
				
				str.append("</select></td><td width=\"50%\" class=\"sel\"><select name=\"query.parameters(" + map.get("ENNAME") + "END)\" >");
				if (codeSet == null)
				{
					codeSet = new ArrayList<Code>();
				}
				for (Code code : codeSet)
				{
					String selected;
					if (value2.equalsIgnoreCase(code.getValue()))
					{
						selected = "selected";
					}
					else
					{
						selected = "";
					}
					str.append("<option value=\"" + code.getValue() + "\" " + selected + ">").append(code.getCodeName()).append("</option>");
				}
				str.append("</select></td></tr></table></td>");
			}
			//其他类型
			else {
				str.append("<td><table cellpadding=\"0\" cellspacing=\"0\"><tr><td width=\"50%\"><input name=\"query.parameters("
					+ map.get("ENNAME")
					+ "START)\" type=\"text\" value=\""
					+ value1
					+ "\" width=\"98%\" /></td>");
				str.append("<td width=\"50%\"><input name=\"query.parameters("
					+ map.get("ENNAME")
					+ "END)\" type=\"text\" value=\""
					+ value2
					+ "\" width=\"98%\" /></td></tr></table></td>");
			}	
		}
		else
		{
			String value = values.get(map.get("ENNAME"));
			if (value == null)
			{
				value = "";
			}
			
			//是否日期型
			if (isDate != null && !"".equals(isDate)
				&& Const.META_TYPE_DATE.equalsIgnoreCase(isDate.trim()))
			{
				str.append("<td><input name=\"query.parameters(" + map.get("ENNAME")
					+ ")\" type=\"text\" value=\"" + value
					+ "\" class=\"Wdate\" onFocus=\"WdatePicker({isShowWeek:true})\" readonly/></td>");
			}
			else if (codeSetId != null && !"".equals(codeSetId))
			{
				str.append("<td class=\"sel\"><select name=\"query.parameters(" + map.get("ENNAME") + ")\" >");
				List<Code> codeSet = form.getCodeSets().get(codeSetId);

				if (codeSet == null)
				{
					codeSet = new ArrayList<Code>();
				}
				for (Code code : codeSet)
				{
					String selected;
					if (value.equalsIgnoreCase(code.getValue()))
					{
						selected = "selected";
					}
					else
					{
						selected = "";
					}
					str.append("<option value=\"" + code.getValue() + "\" " + selected + ">").append(code.getCodeName()).append("</option>");
				}
				str.append("</select></td>");
			}
			else
			{
				str.append("<td><table cellpadding=\"0\" cellspacing=\"0\"><tr>");

				str.append("<td><input name=\"query.parameters(" + map.get("ENNAME")
					+ ")\" type=\"text\" value=\"" + value + "\" /></td>");

//				if (isHomonym != null && !"".equals(isHomonym) && "1".equalsIgnoreCase(isHomonym.trim()))
//				{
//					String isHom = values.get("homonym" + map.get("ENNAME"));
//					if ("1".equals(isHom))
//					{
//						isHom = "checked";
//					}
//					else
//					{
//						isHom = "";
//					}
//					str.append("<td class=\"textL\"><input  type=checkbox class=\"checkbox\" name=\"query.parameters(homonym"
//						+ map.get("ENNAME") + ")\" " + isHom + " value=\"" + isHomonym + "\" />同音</td>");
//				}
//				if (Const.SYS_CODE_YES.equals(isST))
//				{
//					String st = values.get("ST" + map.get("ENNAME"));
//					if (Const.SYS_CODE_YES.equals(st))
//					{
//						st = "checked";
//					}
//					else
//					{
//						st = "";
//					}
//					str.append("<td class=\"textL\"><input  type=checkbox class=\"checkbox\" name=\"query.parameters(ST" + map.get("ENNAME")
//						+ ")\" " + st + " value=\"" + Const.SYS_CODE_YES + "\" />简繁</td>");
//				}

				str.append("</tr></table></td>");
			}
		}

			// 第二个表单项

			if (i + 1 < resLength)
			{
				Map<String, String> map2 = res.get(i + 1);
				String isDate2 = map2.get("DATATYPE"); // 是否日期
				String matchSymbol2 = map2.get("FILTEROPERATOR");// 匹配符号
				String codeSetId2 = map2.get("CODESETID");// 代码集id
				String isHomonym2 = map2.get("HOMONYMQUERY");// 是否同音
				String isST2 = map2.get("STQUERY");// 是否简繁

				str.append("<td>").append(map2.get("CNNAME")).append(":</td>");
				if (Const.FILTER_OPER_BETWEEN.equals(matchSymbol2))
				{
					String value1 = values.get(map2.get("ENNAME") + "START");
					if (value1 == null)
					{
						value1 = "";
					}
					String value2 = values.get(map2.get("ENNAME") + "END");
					if (value2 == null)
					{
						value2 = "";
					}
					//是否日期型
					if (isDate2 != null && !"".equals(isDate2)
						&& Const.META_TYPE_DATE.equalsIgnoreCase(isDate2.trim()))
					{
						str.append("<td><table cellpadding=\"0\" cellspacing=\"0\"><tr><td width=\"50%\"><input name=\"query.parameters("
							+ map2.get("ENNAME")
							+ "START)\" type=\"text\" value=\""
							+ value1
							+ "\" width=\"98%\" class=\"Wdate\" onFocus=\"WdatePicker({isShowWeek:true})\" readonly/></td>");
						str.append("<td width=\"50%\"><input name=\"query.parameters("
							+ map2.get("ENNAME")
							+ "END)\" type=\"text\" value=\""
							+ value2
							+ "\" width=\"98%\" class=\"Wdate\" onFocus=\"WdatePicker({isShowWeek:true})\" readonly/></td></tr></table></td>");
					}
					//是否代码项
					else if (codeSetId2 != null && !"".equals(codeSetId2)) {
						str.append("<td><table cellpadding=\"0\" cellspacing=\"0\"><tr><td width=\"50%\" class=\"sel\">");
						str.append("<select name=\"query.parameters(" + map2.get("ENNAME") + "START)\" >");
						List<Code> codeSet = form.getCodeSets().get(codeSetId2);

						if (codeSet == null)
						{
							codeSet = new ArrayList<Code>();
						}
						for (Code code : codeSet)
						{
							String selected;
							if (value1.equalsIgnoreCase(code.getValue()))
							{
								selected = "selected";
							}
							else
							{
								selected = "";
							}
							str.append("<option value=\"" + code.getValue() + "\" " + selected + ">").append(code.getCodeName()).append("</option>");
						}
						
						str.append("</select></td><td width=\"50%\" class=\"sel\"><select name=\"query.parameters(" + map2.get("ENNAME") + "END)\" >");
						if (codeSet == null)
						{
							codeSet = new ArrayList<Code>();
						}
						for (Code code : codeSet)
						{
							String selected;
							if (value2.equalsIgnoreCase(code.getValue()))
							{
								selected = "selected";
							}
							else
							{
								selected = "";
							}
							str.append("<option value=\"" + code.getValue() + "\" " + selected + ">").append(code.getCodeName()).append("</option>");
						}
						str.append("</select></td></tr></table></td>");
					}
					//其他类型
					else {
						str.append("<td><table cellpadding=\"0\" cellspacing=\"0\"><tr><td width=\"50%\"><input name=\"query.parameters("
							+ map2.get("ENNAME")
							+ "START)\" type=\"text\" value=\""
							+ value1
							+ "\" width=\"98%\" /></td>");
						str.append("<td width=\"50%\"><input name=\"query.parameters("
							+ map2.get("ENNAME")
							+ "END)\" type=\"text\" value=\""
							+ value2
							+ "\" width=\"98%\" /></td></tr></table></td>");
					}	
				}
				else
				{
					String value = values.get(map2.get("ENNAME"));
					if (value == null)
					{
						value = "";
					}
					
					//是否日期型
					if (isDate2 != null && !"".equals(isDate2)
						&& Const.META_TYPE_DATE.equalsIgnoreCase(isDate2.trim()))
					{
						str.append("<td><input name=\"query.parameters(" + map2.get("ENNAME")
							+ ")\" type=\"text\" value=\"" + value
							+ "\" class=\"Wdate\" onFocus=\"WdatePicker({isShowWeek:true})\" readonly/></td>");
					}
					else if (codeSetId2 != null && !"".equals(codeSetId2))
					{
						str.append("<td class=\"sel\"><select name=\"query.parameters(" + map2.get("ENNAME") + ")\" >");
						List<Code> codeSet = form.getCodeSets().get(codeSetId2);

						if (codeSet == null)
						{
							codeSet = new ArrayList<Code>();
						}
						for (Code code : codeSet)
						{
							String selected;
							if (value.equalsIgnoreCase(code.getValue()))
							{
								selected = "selected";
							}
							else
							{
								selected = "";
							}
							str.append("<option value=\"" + code.getValue() + "\" " + selected + ">").append(code.getCodeName()).append("</option>");
						}
						str.append("</select></td>");
					}
					else
					{
						str.append("<td><table cellpadding=\"0\" cellspacing=\"0\"><tr>");

						str.append("<td><input name=\"query.parameters(" + map2.get("ENNAME")
							+ ")\" type=\"text\" value=\"" + value + "\" /></td>");

//						if (isHomonym != null && !"".equals(isHomonym) && "1".equalsIgnoreCase(isHomonym.trim()))
//						{
//							String isHom = values.get("homonym" + map.get("ENNAME"));
//							if ("1".equals(isHom))
//							{
//								isHom = "checked";
//							}
//							else
//							{
//								isHom = "";
//							}
//							str.append("<td class=\"textL\"><input  type=checkbox class=\"checkbox\" name=\"query.parameters(homonym"
//								+ map.get("ENNAME") + ")\" " + isHom + " value=\"" + isHomonym + "\" />同音</td>");
//						}
//						if (Const.SYS_CODE_YES.equals(isST))
//						{
//							String st = values.get("ST" + map.get("ENNAME"));
//							if (Const.SYS_CODE_YES.equals(st))
//							{
//								st = "checked";
//							}
//							else
//							{
//								st = "";
//							}
//							str.append("<td class=\"textL\"><input  type=checkbox class=\"checkbox\" name=\"query.parameters(ST" + map.get("ENNAME")
//								+ ")\" " + st + " value=\"" + Const.SYS_CODE_YES + "\" />简繁</td>");
//						}

						str.append("</tr></table></td>");
					}
				}
				str.append("</tr>");
			}
			else
			{
				str.append("<td colspan=\"2\">&nbsp;</td></tr>");
			}
		}

		return str.toString();
	}

	private Map<String, String> getParamMap(CompareResultRecordForm form)
	{
		Map<String, String> paramMap = new HashMap<String, String>();
		Set<String> parameterNames = form.getQuery().getParameters().keySet();
		for (String str : parameterNames)
		{
			if (str != null && !"".equals(str))
			{
				String name = str.trim();
				String value = form.getQuery().getParameters().get(name);
				if (value != null && !"".equals(value))
				{
					paramMap.put(name, value);
				}
			}
		}
		return paramMap;
	}

	private String getWhereStrForBase(List<Map<String, String>> queryParames,
		Map<String, String> queryValue)throws Exception
	{
		String str = " where 1=1 ";
		BaseSqlGen sqlGen=(BaseSqlGen)getBean("sybaseSqlGen");
		for (Map<String, String> bean : queryParames)
		{
			StringBuffer sb = new StringBuffer();
			List<QueryParam> param = new ArrayList<QueryParam>();
			String dataType = bean.get("DATATYPE"); // 是否日期
			String opType = bean.get("FILTEROPERATOR");
			String name = bean.get("ENNAME");
			String isHomonym = bean.get("HOMONYMQUERY");// 是否同音
			String isST = bean.get("STQUERY");// 是否简繁
			String checkST = queryValue.get("ST"+name);
			if (name != null && !"".equals(name))
			{
				if (opType != null && "BT".equalsIgnoreCase(opType))
				{

					String stValue = queryValue.get(name.trim() + "START");
					if (stValue == null)
					{
						stValue = "";
					}
					String enValue = queryValue.get(name.trim() + "END");
					if (enValue == null)
					{
						enValue = "";
					}

					if (!stValue.equals("") || !enValue.equals(""))
					{
						param.add(new QueryParam(name, filterColumnType(dataType), opType,  stValue+";"+enValue, ""));
					}
				}
				else
				{
					String value = queryValue.get(name.trim());
					if (value != null && !"".equals(value))
					{
						param.add(new QueryParam(name, filterColumnType(dataType), opType, value, ""));
						if (Const.SYS_CODE_YES.equals(isST))
						{
							String big5str=new String(GB2Big5.getInstance().gb2big5(value),"BIG5");
							param.add(new QueryParam(name, filterColumnType(dataType), opType, big5str, ""));
						}						
					}
				}
				
				String commonSQL = sqlGen.getQueryStringPart(param,QueryParam.OPER_OR);
				if (commonSQL==null||"".equals(commonSQL))
				{
					commonSQL = "";
				}else {
					commonSQL = " and ("+ commonSQL+") ";
				}
				
				sb.append(commonSQL);
				
			}
			
			if (sb.length()>0)
			{
				str += sb.toString();
			}
		}
		return str;
	}
	
	private void initParamsValues(Map<String, String> paramsValue, String allparams)
	{
		if (allparams == null || "".equals(allparams))
		{
			paramsValue = new HashMap<String, String>();
			return;
		}

		if (allparams.length() > 3)
		{
			allparams = allparams.substring(1, allparams.length() - 1);
			String[] oneParams = allparams.split("\\}\\, \\{");
			// 将数组转换成map对象并保存到list中
			for (int i = 0; i < oneParams.length; i++)
			{
				String temp = oneParams[i];
				if (temp != null)
				{
					temp = temp.replaceAll("\\{", "").replaceAll("\\}", "");
					String[] keyValueStr = temp.replaceAll("@.*@", "").split("\\,");

					for (int j = 0; j < keyValueStr.length; j++)
					{
						String[] singleMapStr = keyValueStr[j].split("\\=");
						if (singleMapStr.length == 2)
						{
							String key = singleMapStr[0];
							if (key != null)
							{
								key = key.trim();
							}
							String value = singleMapStr[1];

							paramsValue.put(key, value);
						}
					}
				}
			}
		}
		else
		{
			return;
		}
	}
	private String filterColumnType(String opType){
		if(Const.META_TYPE_NUMERIC.equals(opType))
			return QueryParam.COLUMN_TYPE_INT;
		else if(Const.META_TYPE_DATE.equals(opType))
			return QueryParam.COLUMN_TYPE_DATE;
		else if(Const.META_TYPE_STRING.equals(opType))
			return QueryParam.COLUMN_TYPE_STRING;
		else
			return QueryParam.COLUMN_TYPE_STRING;
	}
	
	private String buildWhereIdInClause(Collection<Serializable> pkIdArray,String idMetaType)
	{
		if (pkIdArray == null || pkIdArray.size() == 0)
		{
			return "";
		}
		StringBuffer sbWhere = new StringBuffer(" id in(");
		
		if (idMetaType==null || idMetaType.equals(Const.META_TYPE_STRING))
		{
			for(Serializable id : pkIdArray)
			{
				sbWhere.append("'").append(id).append("',");
			}
		}
		else
		{
			for(Serializable id : pkIdArray)
			{
				sbWhere.append(id).append(",");
			}
		}
		return sbWhere.deleteCharAt(sbWhere.length()-1).append(")").toString();
		
	}
}
