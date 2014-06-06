package com.liusy.datapp.web.analysis.action;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import zeal.util.GB2Big5;

import com.liusy.analysis.template.model.Diagram;
import com.liusy.core.util.Const;
import com.liusy.dataapplatform.base.util.BaseSqlGen;
import com.liusy.dataapplatform.base.util.QueryParam;
import com.liusy.datapp.dao.analysis.AnalysisDiagramDao;
import com.liusy.datapp.service.dynamicquery.SynthesisColumnGenService;
import com.liusy.datapp.service.pool.TableConfigPool;
import com.liusy.datapp.util.poolobj.ColumnPoolObj;
import com.liusy.datapp.util.poolobj.QueryParamPoolObj;
import com.liusy.datapp.web.BaseAction;
import com.liusy.datapp.web.analysis.form.AnalysisQueryForm;

public class MyAnalysisQueryAction extends BaseAction {
	private AnalysisDiagramDao dao;
	@Override
	public ActionForward doExecute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		String action = request.getParameter("action");

		ActionForward forward = null;
		AnalysisQueryForm theForm = (AnalysisQueryForm) form;
		try {
			if ("GETCONFIG".equalsIgnoreCase(action)) forward = getTemplateConfig(mapping, theForm, request, response); // 打开查询列表页面
			if ("GETDATA".equalsIgnoreCase(action)) forward = getData(mapping, theForm, request, response); // 打开查询列表页面
		}
		catch (Exception e) {
			request.setAttribute("err", e);
			forward = mapping.findForward("error");
		}
		return forward;
	}

	public ActionForward getData(ActionMapping mapping, AnalysisQueryForm theForm, HttpServletRequest request, HttpServletResponse response) throws Exception {
//		String diagramID = request.getParameter("templateId");
//		Map<String,String> map = theForm.getParameters();
//		TemplateDao dbSurport = new TemplateDao();
//		java.sql.Connection con= dbSurport.getDataSource().getConnection();
//		com.liusy.dataplatform.template.model.Diagram diagramModel = readRawDiagram(diagramID);
//		diagramModel.setParameterValues(map);
//		diagramModel.setDbConnection(con);
//		String xmlString = diagramModel.getResultXml();
//		
//		response.setContentType("text/xml;charset=UTF-8");
//		response.setHeader("Cache_Control", "no-cache");
//		response.getWriter().write(xmlString);
//		response.getWriter().close();
		return null;
	}

	public ActionForward getTemplateConfig(ActionMapping mapping, AnalysisQueryForm theForm, HttpServletRequest request, HttpServletResponse response) throws Exception {
		// String tempId=theForm.getTemplateId();
		String diagramID = request.getParameter("templateId");
		
		Diagram diagramModel = readRawDiagram(diagramID);
		String xmlString = diagramModel.getMetaDataXml();
		response.setContentType("text/xml;charset=UTF-8");
		response.setHeader("Cache_Control", "no-cache");
		System.out.println(xmlString);

		response.getWriter().write(xmlString);
		response.getWriter().close();
		return null;
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
	private Map<String, String> getQueryParam(List<Map<String, Object>> queryColumnlist, List<QueryParamPoolObj> colparamList, AnalysisQueryForm theForm, String tabId,
			SynthesisColumnGenService synthesisColumnGenService) throws Exception {
		TableConfigPool tableConfigPool = (TableConfigPool) getBean("tableConfigPool");
		//
		BaseSqlGen sqlGen = (BaseSqlGen) getBean("sybaseSqlGen");
		// 单个表查询语句
		StringBuffer buffer = new StringBuffer();
		// 字段缓存对象
		Map<String, String> tablepoolMap = tableConfigPool.getTableMap(tabId);

		List<QueryParam> paramList = new ArrayList<QueryParam>();
		for (QueryParamPoolObj colpool : colparamList) {
			ColumnPoolObj columnpool = tableConfigPool.getColumnPool(colpool.getColumnId());
			if (colpool.getFilterOperator().equals(Const.FILTER_OPER_BETWEEN)) {
				String val1 = "";
				if (theForm.getParameters().get(colpool.getColumnName() + "_s") != null) val1 = getDateString(columnpool, theForm.getParameters().get(colpool.getColumnName() + "_s").toString());
				String val2 = "";
				if (theForm.getParameters().get(colpool.getColumnName() + "_e") != null) val2 = getDateString(columnpool, theForm.getParameters().get(colpool.getColumnName() + "_e").toString());
				if ((val1 != null && !"".equals(val1)) || (val2 != null && !"".equals(val2))) {
					paramList.add(new QueryParam(colpool.getColumnName(), synthesisColumnGenService.filterColumnType(columnpool), colpool.getFilterOperator(), val1 + ";" + val2, "", QueryParam.OPER_AND));
				}
			}
			else {
				String val = "";
				if (theForm.getParameters().get(colpool.getColumnName()) != null) val = getDateString(columnpool, theForm.getParameters().get(colpool.getColumnName()).toString());

				if (val != null && !"".equals(val)) {
					// 支持简繁查
					if (colpool.getStQuery() != null && colpool.getStQuery().equals(Const.TAG_ENABLE)) {
						paramList.add(new QueryParam(colpool.getColumnName(), synthesisColumnGenService.filterColumnType(columnpool), colpool.getFilterOperator(), val, "", QueryParam.OPER_OR, "(", ""));
						String big5str = new String(GB2Big5.getInstance().gb2big5(val), "BIG5");
						paramList
								.add(new QueryParam(colpool.getColumnName(), synthesisColumnGenService.filterColumnType(columnpool), colpool.getFilterOperator(), big5str, "", QueryParam.OPER_AND, "", ")"));
					}
					else {
						paramList.add(new QueryParam(colpool.getColumnName(), synthesisColumnGenService.filterColumnType(columnpool), colpool.getFilterOperator(), val, "", QueryParam.OPER_AND));
					}
				}
			}
		}
		String gensql = sqlGen.getQueryStringPart(paramList);
		if (gensql != null && !"".equals(gensql.trim())) buffer.append(gensql + QueryParam.OPER_AND);
		Map<String, String> tableMap = new HashMap<String, String>();
		// 获取对应表的查询语句和表名
		tableMap = tablepoolMap;
		tableMap.put("id", tabId);
		String querysql = buffer.length() > 0 ? " and " + buffer.substring(0, buffer.length() - 5) : "";
		tableMap.put("sql", tablepoolMap.get("name") + " where 1=1" + querysql);
		return tableMap;
	}
	public String getTableName(String tempID) {
//		com.liusy.dataplatform.template.model.Diagram diagramModel = readRawDiagram(tempID);
//		List<INode> nodes = diagramModel.getNodes();
//		if (nodes != null && nodes.size() > 0) {
//			for (int i = 0 ;i < nodes.size() ;i++) {
//				INode node = nodes.get(i);
//				if (node instanceof DataNode) {
//					DataNode dataNode = (DataNode) node;
//					String tableName = dataNode.getProperties().getTableName();
//					return tableName;
//				}
//			}
//		}
		return null;
	}
	public Diagram readRawDiagram(String tempID) {
//		dao = (DiagramDao) getBean("diagramDao");
//		AnalysisDiagram diagram = dao.get(Long.valueOf(tempID));
//		if (diagram != null) {
//			Blob diagramData =  diagram.getBody();
//			try {
//				InputStream inputSteam = diagramData.getBinaryStream();
//				ObjectInputStream oisEditor = new ObjectInputStream(inputSteam);
//				Object objEditor = oisEditor.readObject();
//				com.liusy.dataplatform.template.model.Diagram diagramModel = 
//					(com.liusy.dataplatform.template.model.Diagram) objEditor;
//				return diagramModel;
//				
//			} catch (SQLException e) {
//				// TODO 自动生成 catch 块
//				e.printStackTrace();
//			} catch (IOException e1) {
//				// TODO 自动生成 catch 块
//				e1.printStackTrace();
//			} catch (ClassNotFoundException e) {
//				// TODO 自动生成 catch 块
//				e.printStackTrace();
//			}
//		}
		return null;
		
	}
	private String getDateString(ColumnPoolObj obj, String str) throws Exception {
		String retStr = "";
		if (obj.getDataType().equals(Const.META_TYPE_DATE) && str.length() > 0) {
			SimpleDateFormat format = new SimpleDateFormat("yyyy年MM月dd日");
			SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
			retStr = format1.format(format.parse(str));
		}
		else retStr = str;
		return retStr;
	}
}
