package com.liusy.datapp.web.analysis.action;

import java.io.InputStream;
import java.io.ObjectInputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.liusy.analysis.template.model.Diagram;
import com.liusy.analysis.template.model.DiagramParameter;
import com.liusy.datapp.model.analysis.AnalysisDiagram;
import com.liusy.datapp.service.analysis.AnalysisConnectionService;
import com.liusy.datapp.service.analysis.AnalysisDiagramService;
import com.liusy.datapp.web.BaseAction;
import com.liusy.datapp.web.analysis.form.AnalysisQueryForm;

public class AnalysisQueryAction extends BaseAction {
   @Override
   public ActionForward doExecute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
      String action = request.getParameter("action");

      ActionForward forward = null;
      AnalysisQueryForm theForm = (AnalysisQueryForm) form;
      try {
         if ("GETCONFIG".equalsIgnoreCase(action)) forward = getTemplateConfig(mapping, theForm, request, response);
         if ("GETDATA".equalsIgnoreCase(action)) forward = getData(mapping, theForm, request, response);
         if ("GETDETAILDATA".equalsIgnoreCase(action)) forward = getDetailData(mapping, theForm, request, response);
         if ("GETCONFIGANDDATA".equalsIgnoreCase(action)) forward = getTemplateConfigAndData(mapping, theForm, request, response);
      }
      catch (Exception e) {
         request.setAttribute("err", e);
         e.printStackTrace();
         forward = mapping.findForward("error");
      }
      return forward;
   }

   public ActionForward getData(ActionMapping mapping, AnalysisQueryForm theForm, HttpServletRequest request, HttpServletResponse response) throws Exception {

      String diagramID = request.getParameter("templateId");
      AnalysisDiagramService service = (AnalysisDiagramService) getBean("analysisDiagramService");
      AnalysisDiagram analysisDiagram = service.findAnalysisDiagram(Integer.parseInt(diagramID));
      Diagram diagram = readRawDiagram(analysisDiagram.getBody());

      AnalysisConnectionService connectionService = (AnalysisConnectionService) getBean("analysisConnectionService");
      Connection conn = connectionService.getConnection();

      diagram.setDbConnection(conn);
      Map paras = theForm.getParameters();
      Map<String, String> p = new HashMap<String, String>();
      Iterator it = paras.keySet().iterator();
      while (it.hasNext()) {
         String key = (String) it.next();
         String value = (String) paras.get(key);
         if (value.length() > 0) p.put(key, value);
      }
      //p.put("name", "YO");
      String xmlString = "<?xml version=\"1.0\" encoding=\"utf-8\"?>\r\n";
      if (p.isEmpty()) {
    	  xmlString += xmlString = genBlank();
      }
      else {
         diagram.setParameterValues(p);
         xmlString += diagram.getResultXml();
      }
      connectionService.closeConnection(conn);
      System.out.println(xmlString);

      response.setContentType("text/xml;charset=UTF-8");
      response.setHeader("Cache_Control", "no-cache");
      response.getWriter().write(xmlString);
      response.getWriter().close();
      return null;
   }

   public ActionForward getDetailData(ActionMapping mapping, AnalysisQueryForm theForm, HttpServletRequest request, HttpServletResponse response)
         throws Exception {
      String diagramID = request.getParameter("templateId");
      AnalysisDiagramService service = (AnalysisDiagramService) getBean("analysisDiagramService");
      AnalysisDiagram analysisDiagram = service.findAnalysisDiagram(Integer.parseInt(diagramID));
      Diagram diagram = readRawDiagram(analysisDiagram.getBody());

      AnalysisConnectionService connectionService = (AnalysisConnectionService) getBean("analysisConnectionService");
      Connection conn = connectionService.getConnection();

      diagram.setDbConnection(conn);
      Map paras = theForm.getParameters();
      Map<String, String> p = new HashMap<String, String>();
      Iterator it = paras.keySet().iterator();
      while (it.hasNext()) {
         String key = (String) it.next();
         String value = (String) paras.get(key);
         if (value.length() > 0) p.put(key, value);
      }

      String xmlString;
      if (p.isEmpty()) {
         xmlString = genBlank();
      }
      else {
         diagram.setParameterValues(p);
         String id = p.get("#nodeId");
         String pkid = p.get("#pkId");
         xmlString = diagram.runNodeXml(id, pkid);
      }
      connectionService.closeConnection(conn);

      System.out.println(xmlString);

      response.setContentType("text/xml;charset=UTF-8");
      response.setHeader("Cache_Control", "no-cache");
      response.getWriter().write(xmlString);
      response.getWriter().close();
      return null;
   }

   private String genBlank() {
      StringBuffer sb = new StringBuffer("");
      sb.append("<?xml version=\"1.0\" encoding=\"utf-8\"?>\r\n");
      sb.append("<records>\r\n");
      sb.append("</records>\r\n");
      return sb.toString();
   }

   public ActionForward getTemplateConfig(ActionMapping mapping, AnalysisQueryForm theForm, HttpServletRequest request, HttpServletResponse response)
         throws Exception {
      String diagramID = request.getParameter("templateId");
      AnalysisDiagramService service = (AnalysisDiagramService) getBean("analysisDiagramService");
      AnalysisDiagram analysisDiagram = service.findAnalysisDiagram(Integer.parseInt(diagramID));
      Diagram diagramModel = readRawDiagram(analysisDiagram.getBody());
      String xmlString = diagramModel.getMetaDataXml();
      System.out.println(xmlString);

      response.setContentType("text/xml;charset=UTF-8");
      response.setHeader("Cache_Control", "no-cache");
      response.getWriter().write(xmlString);
      response.getWriter().close();
      return null;
   }

   public ActionForward getTemplateConfigAndData(ActionMapping mapping, AnalysisQueryForm theForm, HttpServletRequest request, HttpServletResponse response)
         throws Exception {
      String diagramID = request.getParameter("templateId");
      AnalysisDiagramService service = (AnalysisDiagramService) getBean("analysisDiagramService");
      AnalysisDiagram analysisDiagram = service.findAnalysisDiagram(Integer.parseInt(diagramID));
      Diagram diagram = readRawDiagram(analysisDiagram.getBody());

      Map paras = theForm.getParameters();
      List<DiagramParameter> parameters = diagram.getParameterList();

      for (DiagramParameter dp : parameters) {
         String value = (String) paras.get(dp.getName());
         if (value != null && value.length() > 0) {
            dp.setValue(value);
         }
      }

      String xmlString = diagram.getMetaDataXml();

      AnalysisConnectionService connectionService = (AnalysisConnectionService) getBean("analysisConnectionService");
      Connection conn = connectionService.getConnection();

      diagram.setDbConnection(conn);
      Map<String, String> p = new HashMap<String, String>();
      Iterator it = paras.keySet().iterator();
      while (it.hasNext()) {
         String key = (String) it.next();
         String value = (String) paras.get(key);
         if (value.length() > 0) p.put(key, value);
      }

      if (p.isEmpty()) {
         xmlString = xmlString + "\r\n<records>\r\n</records>\r\n";
      }
      else {
         diagram.setParameterValues(p);
         xmlString = xmlString + "\r\n" + diagram.getResultXml();
      }
      connectionService.closeConnection(conn);
      System.out.println(xmlString);

      response.setContentType("text/xml;charset=UTF-8");
      response.setHeader("Cache_Control", "no-cache");
      response.getWriter().write(xmlString);
      response.getWriter().close();
      return null;
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
