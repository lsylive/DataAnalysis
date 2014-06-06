
package com.liusy.datapp.web.analysis.action;

import java.io.InputStream;
import java.io.ObjectInputStream;
import java.sql.Blob;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.liusy.analysis.template.model.Diagram;
import com.liusy.dataapplatform.base.exception.WebException;
import com.liusy.datapp.model.analysis.AnalysisDiagram;
import com.liusy.datapp.service.analysis.AnalysisDiagramService;
import com.liusy.datapp.web.BaseAction;
import com.liusy.datapp.web.analysis.form.AnalysisForm;

public class AnalysisAction extends BaseAction {

	private static final String	COMMUNICATION	= "Communication";

	private static final String	ACCOMMODATION	= "Accommodation";

	private static final String	BORDER			= "Border";

	private static final String	FLYING			= "Flying";

	private static final String	COMPOSITE		= "Composite";

	private static final String	LINK				= "Link";

	private static final String	FOLDER			= "Folder";

	private static final String	QUERY				= "query";

	public static String[]			TEMP_LABEL		= { "Composite", "Communication", "Accommodation", "Border", "Flying", "Complex" };

	public static String getTempleNameById(int type) {
		return TEMP_LABEL[type];
	}

	@Override
	public ActionForward doExecute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		String action = request.getParameter("action");
		if (action == null) action = LINK;
		if (log.isDebugEnabled()) log.debug("action:" + action);
		ActionForward forward;
		AnalysisForm analysisForm = (AnalysisForm) form;

		try {
			if (LINK.equalsIgnoreCase(action)) forward = list(mapping, analysisForm, request, response, -1); // 打开主页面
			else if (COMPOSITE.equalsIgnoreCase(action)) forward = list(mapping, analysisForm, request, response, 5); // 打开主页面
			else if (COMMUNICATION.equalsIgnoreCase(action)) forward = list(mapping, analysisForm, request, response, 2); // 打开主页面
			else if (ACCOMMODATION.equalsIgnoreCase(action)) forward = list(mapping, analysisForm, request, response, 3); // 打开主页面
			else if (BORDER.equalsIgnoreCase(action)) forward = list(mapping, analysisForm, request, response, 4); // 打开主页面
			else if (FLYING.equalsIgnoreCase(action)) forward = list(mapping, analysisForm, request, response, 1); // 打开主页面
			else if (FOLDER.equalsIgnoreCase(action)) forward = folder(mapping, analysisForm, request, response); // 打开列表页面
			else if (QUERY.equalsIgnoreCase(action)) forward = query(mapping, analysisForm, request, response);
			else {
				request.setAttribute("err", new WebException("找不到该action方法：" + action));
				forward = mapping.findForward(ERROR);// 找不到合适的action
			}
		}
		catch (Exception e) {// 其他系统出错
			request.setAttribute("err", e);
			forward = mapping.findForward(ERROR);
		}
		return forward;
	}

	public ActionForward flying(ActionMapping mapping, AnalysisForm analysisForm, HttpServletRequest request, HttpServletResponse response) throws Exception {
		String type = request.getParameter("tempType");
		return mapping.findForward("flying");
	}

	public ActionForward query(ActionMapping mapping, AnalysisForm analysisForm, HttpServletRequest request, HttpServletResponse response) throws Exception {
		String diagramID = request.getParameter("tempId");
		AnalysisDiagramService service = (AnalysisDiagramService) getBean("analysisDiagramService");
		AnalysisDiagram analysisDiagram = service.findAnalysisDiagram(Integer.parseInt(diagramID));
		Diagram diagramModel = readRawDiagram(analysisDiagram.getBody());
		
		String interfaceName = diagramModel.getInterfaceType();
		if (interfaceName != null && interfaceName.length() > 0) request.setAttribute("fileName", interfaceName);
		else request.setAttribute("fileName", "QueryInterfaceNode");
		return mapping.findForward(QUERY);
	}

	public ActionForward folder(ActionMapping mapping, AnalysisForm analysisForm, HttpServletRequest request, HttpServletResponse response) throws Exception {
		String type = request.getParameter("type");

		response.setContentType("text/xml;charset=UTF-8");
		response.setHeader("Cache_Control", "no-cache");

		String str = getItems(type);
		response.getWriter().write(str);
		response.getWriter().close();
		return null;
	}

	public ActionForward list(ActionMapping mapping, AnalysisForm analysisForm, HttpServletRequest request, HttpServletResponse response, Integer type) throws Exception {
		if (log.isDebugEnabled()) log.debug("Entering 'list' method");
		try {
			request.setAttribute("type", type.toString());
		}
		catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("err", e);
			return mapping.findForward(ERROR);
		}
		return mapping.findForward(LIST);
	}

	private String getItems(String type) {
		StringBuffer sb = new StringBuffer("");
		sb.append("<?xml version='1.0' encoding='UTF-8' ?>");
		sb.append("<data>");
		AnalysisDiagramService service = (AnalysisDiagramService) getBean("analysisDiagramService");
      System.out.println("type:"+type);
		if (type != null) {
			List<AnalysisDiagram> list = service.queryDiagramByType(type);
	      System.out.println("size:"+list.size());
			for (int i = 0; i < list.size(); i++) {
				AnalysisDiagram diagram = list.get(i);
				if(diagram.getVisiable().equals("0")) continue;
				sb.append("<item id=\"").append(diagram.getId().toString()).append("\"");
				sb.append(" name=\"").append(diagram.getName()).append("\"");
				sb.append(" image=\"ZIP.png\"");
				sb.append(" tempType=\"").append(diagram.getType()).append("\"");
				sb.append(" tempId=\"").append(diagram.getId().toString()).append("\" />");
			}
		}
		sb.append("</data>");
		System.out.println(sb.toString());
		return sb.toString();
	}

	
	public Diagram readRawDiagram(Blob diagramData) {
		Diagram diagramModel = null;
		try {
			InputStream inputSteam = diagramData.getBinaryStream();
			ObjectInputStream oisEditor = new ObjectInputStream(inputSteam);
			Object objEditor = oisEditor.readObject();
			diagramModel = (Diagram) objEditor;
			return diagramModel;
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return diagramModel;
	}
}

