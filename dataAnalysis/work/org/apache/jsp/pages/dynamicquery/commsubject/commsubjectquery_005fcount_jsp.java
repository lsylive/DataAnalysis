package org.apache.jsp.pages.dynamicquery.commsubject;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class commsubjectquery_005fcount_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List _jspx_dependants;

  static {
    _jspx_dependants = new java.util.ArrayList(6);
    _jspx_dependants.add("/common/dialog1.jsp");
    _jspx_dependants.add("/WEB-INF/config/tag/struts-logic.tld");
    _jspx_dependants.add("/WEB-INF/config/tag/struts-template.tld");
    _jspx_dependants.add("/WEB-INF/config/tag/struts-bean.tld");
    _jspx_dependants.add("/WEB-INF/config/tag/struts-html.tld");
    _jspx_dependants.add("/WEB-INF/config/tag/gw-tag.tld");
  }

  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005fhtml_005fform_0026_005fmethod_005faction;
  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005fhtml_005fhidden_0026_005fproperty_005fname_005fnobody;
  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005fgw_005fgrid2_0026_005fwidth_005fstyleClass_005fsecondRowStyle_005fschema_005frowEventHandle_005fproperty_005fparameters_005fname_005ffixRows_005fcellSpacing_005fcellPadding_005fborder;

  private javax.el.ExpressionFactory _el_expressionfactory;
  private org.apache.AnnotationProcessor _jsp_annotationprocessor;

  public Object getDependants() {
    return _jspx_dependants;
  }

  public void _jspInit() {
    _005fjspx_005ftagPool_005fhtml_005fform_0026_005fmethod_005faction = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _005fjspx_005ftagPool_005fhtml_005fhidden_0026_005fproperty_005fname_005fnobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _005fjspx_005ftagPool_005fgw_005fgrid2_0026_005fwidth_005fstyleClass_005fsecondRowStyle_005fschema_005frowEventHandle_005fproperty_005fparameters_005fname_005ffixRows_005fcellSpacing_005fcellPadding_005fborder = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
    _jsp_annotationprocessor = (org.apache.AnnotationProcessor) getServletConfig().getServletContext().getAttribute(org.apache.AnnotationProcessor.class.getName());
  }

  public void _jspDestroy() {
    _005fjspx_005ftagPool_005fhtml_005fform_0026_005fmethod_005faction.release();
    _005fjspx_005ftagPool_005fhtml_005fhidden_0026_005fproperty_005fname_005fnobody.release();
    _005fjspx_005ftagPool_005fgw_005fgrid2_0026_005fwidth_005fstyleClass_005fsecondRowStyle_005fschema_005frowEventHandle_005fproperty_005fparameters_005fname_005ffixRows_005fcellSpacing_005fcellPadding_005fborder.release();
  }

  public void _jspService(HttpServletRequest request, HttpServletResponse response)
        throws java.io.IOException, ServletException {

    PageContext pageContext = null;
    HttpSession session = null;
    ServletContext application = null;
    ServletConfig config = null;
    JspWriter out = null;
    Object page = this;
    JspWriter _jspx_out = null;
    PageContext _jspx_page_context = null;


    try {
      response.setContentType("text/html;charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;

      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");

String path = request.getContextPath();
String CONTEXT_PATH = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";

      out.write("\r\n");
      out.write("<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\">\r\n");
      out.write("<html xmlns=\"http://www.w3.org/1999/xhtml\">\r\n");
      out.write("<head>\r\n");
      out.write("\t<link rel=\"stylesheet\" type=\"text/css\" href=\"");
      out.print(CONTEXT_PATH);
      out.write("css/main.css\" />\r\n");
      out.write("\t <link rel=\"stylesheet\" type=\"text/css\" href=\"");
      out.print(CONTEXT_PATH);
      out.write("dhtmlxSuite/dhtmlx/dhtmlx.css\">\r\n");
      out.write("<script src=\"");
      out.print(CONTEXT_PATH);
      out.write("dhtmlxSuite/dhtmlx/dhtmlx.js\"></script>\r\n");
      out.write("<script language=\"javascript\" src=\"");
      out.print( CONTEXT_PATH );
      out.write("js/date_validate.js\"></script>\r\n");
      out.write("\t\t<script language=\"javascript\" src=\"");
      out.print( CONTEXT_PATH );
      out.write("js/ctrl_util.js\"></script>\r\n");
      out.write("\r\n");
      out.write("\t<script language=\"javascript\">  \r\n");
      out.write("      var contextpath = \"");
      out.print(CONTEXT_PATH);
      out.write("\";\t\r\n");
      out.write("      var hyperlink = \"../query/commSubjectQuery.do\";\t\r\n");
      out.write("      var fulllink = contextpath + \"/query/commSubjectQuery.do\";\t\t\r\n");
      out.write("      \r\n");
      out.write("\t  function showdetail(id){\r\n");
      out.write("\t  \t var urllink='");
      out.print(CONTEXT_PATH);
      out.write("/query/commSubjectQuery.do?action1=SHOWQUERY&id='+id;\r\n");
      out.write("\t  \t openWindow('查询结果',urllink,800,450);\r\n");
      out.write("\t  }\r\n");
      out.write("\t  function goQuery(){\r\n");
      out.write("\t  \r\n");
      out.write("\t  }\r\n");
      out.write("\r\n");
      out.write("\t  function showPage(){\r\n");
      out.write("\t\t  parent.showPage();\r\n");
      out.write("\t}\r\n");
      out.write("   \r\n");
      out.write("\t</script>\t\r\n");
      out.write("   \r\n");
      out.write("<style type=\"text/css\">\r\n");
      out.write("html, body {\r\n");
      out.write("        width: 100%;\r\n");
      out.write("        height: 100%;\r\n");
      out.write("        margin: 0px;\r\n");
      out.write("        }\r\n");
      out.write("</style>\r\n");
      out.write("</head>\r\n");
      out.write("\r\n");
      out.write("<body onload=\"showPage()\">\r\n");
      out.write("<div class=\"special-padding\" style=\"overflow-x:hidden;\r\n");
      out.write("overflow-y:auto;\">\r\n");
      out.write("\r\n");
      out.write("\r\n");
      if (_jspx_meth_html_005fform_005f0(_jspx_page_context))
        return;
      out.write("\r\n");
      out.write("</div>\r\n");
      out.write("\r\n");
      out.write("<script type=\"text/javascript\">\r\n");
      out.write("\t  var dhxWins = new dhtmlXWindows();\r\n");
      out.write("    dhxWins.enableAutoViewport(true);\r\n");
      out.write("    dhxWins.setImagePath(\"");
      out.print(CONTEXT_PATH);
      out.write("dhtmlxSuite/dhtmlxWindows/codebase/imgs/\");\r\n");
      out.write("    \r\n");
      out.write("    var winName=\"win\";  \r\n");
      out.write("    var editMode;       \r\n");
      out.write("    \r\n");
      out.write("    function openWindow(title,urlink,width,height){\r\n");
      out.write("       var w = dhxWins.createWindow(winName, 0, 0, width, height);\r\n");
      out.write("       w.setText(title);\r\n");
      out.write("       w.keepInViewport(true);\r\n");
      out.write("       w.setModal(true);\r\n");
      out.write("       w.centerOnScreen();\r\n");
      out.write("       //w.button(\"minmax1\").hide();\r\n");
      out.write("       //w.button(\"minmax2\").hide();\r\n");
      out.write("       w.button(\"park\").hide();\r\n");
      out.write("       w.attachURL(urlink);\r\n");
      out.write("       return w;    \r\n");
      out.write("    }\r\n");
      out.write("    function openSingleWindow(title,divhtml,width,height){\r\n");
      out.write("       if(height==null || height=='')\r\n");
      out.write("       \t\theight=100;\r\n");
      out.write("       \tif(width==null || width=='')\r\n");
      out.write("       \t\twidth=300;\r\n");
      out.write("   \t   var win2 = dhxWins.createWindow(winName, 0, 0, width, height);\r\n");
      out.write("       win2.setText(title);\r\n");
      out.write("       win2.keepInViewport(true);\r\n");
      out.write("       win2.setModal(true);\r\n");
      out.write("       win2.centerOnScreen();\r\n");
      out.write("       //win2.button(\"minmax1\").hide();\r\n");
      out.write("      // win2.button(\"minmax2\").hide();\r\n");
      out.write("       win2.button(\"park\").hide();\r\n");
      out.write("       win2.attachHTMLString(divhtml);\r\n");
      out.write("       return win2;    \r\n");
      out.write("    }\r\n");
      out.write("\r\n");
      out.write("    function openWindowById(title,objId,width,height){\r\n");
      out.write("\t       if(height==null || height=='')\r\n");
      out.write("\t       \t\theight=100;\r\n");
      out.write("\t       \tif(width==null || width=='')\r\n");
      out.write("\t       \t\twidth=300;\r\n");
      out.write("\t   \t   var win2 = dhxWins.createWindow(winName, 0, 0, width, height);\r\n");
      out.write("\t   \t   win2.button(\"close\").attachEvent(\"onClick\", function(){\r\n");
      out.write("\t   \t\tdhxWins.window(winName).close();\r\n");
      out.write("\t\t   \t   });\r\n");
      out.write("\t   \t   win2.maximize();\r\n");
      out.write("\t       win2.setText(title);\r\n");
      out.write("\t       win2.keepInViewport(true);\r\n");
      out.write("\t       win2.setModal(true);\r\n");
      out.write("\t       win2.centerOnScreen();\r\n");
      out.write("\t       win2.button(\"minmax1\").hide();\r\n");
      out.write("\t       win2.button(\"minmax2\").hide();\r\n");
      out.write("\t       win2.button(\"park\").hide();\r\n");
      out.write("\t       win2.attachObject(objId);;\r\n");
      out.write("\t       return win2;    \r\n");
      out.write("\t    }\r\n");
      out.write("   \r\n");
      out.write("</script> \r\n");
      out.write("\r\n");
      out.write("</body>\r\n");
      out.write("</html>");
    } catch (Throwable t) {
      if (!(t instanceof SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          try { out.clearBuffer(); } catch (java.io.IOException e) {}
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }

  private boolean _jspx_meth_html_005fform_005f0(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  html:form
    org.apache.struts.taglib.html.FormTag _jspx_th_html_005fform_005f0 = (org.apache.struts.taglib.html.FormTag) _005fjspx_005ftagPool_005fhtml_005fform_0026_005fmethod_005faction.get(org.apache.struts.taglib.html.FormTag.class);
    _jspx_th_html_005fform_005f0.setPageContext(_jspx_page_context);
    _jspx_th_html_005fform_005f0.setParent(null);
    // /pages/dynamicquery/commsubject/commsubjectquery_count.jsp(54,0) name = action type = null reqTime = true required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_html_005fform_005f0.setAction("/query/commSubjectQuery.do");
    // /pages/dynamicquery/commsubject/commsubjectquery_count.jsp(54,0) name = method type = null reqTime = true required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_html_005fform_005f0.setMethod("post");
    int _jspx_eval_html_005fform_005f0 = _jspx_th_html_005fform_005f0.doStartTag();
    if (_jspx_eval_html_005fform_005f0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      do {
        out.write('\r');
        out.write('\n');
        if (_jspx_meth_html_005fhidden_005f0(_jspx_th_html_005fform_005f0, _jspx_page_context))
          return true;
        out.write("\r\n");
        out.write("\r\n");
        out.write("\r\n");
        out.write("\r\n");
        if (_jspx_meth_gw_005fgrid2_005f0(_jspx_th_html_005fform_005f0, _jspx_page_context))
          return true;
        out.write("\t\t\r\n");
        out.write("\r\n");
        out.write("\r\n");
        out.write("\r\n");
        int evalDoAfterBody = _jspx_th_html_005fform_005f0.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
    }
    if (_jspx_th_html_005fform_005f0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fhtml_005fform_0026_005fmethod_005faction.reuse(_jspx_th_html_005fform_005f0);
      return true;
    }
    _005fjspx_005ftagPool_005fhtml_005fform_0026_005fmethod_005faction.reuse(_jspx_th_html_005fform_005f0);
    return false;
  }

  private boolean _jspx_meth_html_005fhidden_005f0(javax.servlet.jsp.tagext.JspTag _jspx_th_html_005fform_005f0, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  html:hidden
    org.apache.struts.taglib.html.HiddenTag _jspx_th_html_005fhidden_005f0 = (org.apache.struts.taglib.html.HiddenTag) _005fjspx_005ftagPool_005fhtml_005fhidden_0026_005fproperty_005fname_005fnobody.get(org.apache.struts.taglib.html.HiddenTag.class);
    _jspx_th_html_005fhidden_005f0.setPageContext(_jspx_page_context);
    _jspx_th_html_005fhidden_005f0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_html_005fform_005f0);
    // /pages/dynamicquery/commsubject/commsubjectquery_count.jsp(55,0) name = name type = null reqTime = true required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_html_005fhidden_005f0.setName("commSubjectQueryForm");
    // /pages/dynamicquery/commsubject/commsubjectquery_count.jsp(55,0) name = property type = null reqTime = true required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_html_005fhidden_005f0.setProperty("record");
    int _jspx_eval_html_005fhidden_005f0 = _jspx_th_html_005fhidden_005f0.doStartTag();
    if (_jspx_th_html_005fhidden_005f0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fhtml_005fhidden_0026_005fproperty_005fname_005fnobody.reuse(_jspx_th_html_005fhidden_005f0);
      return true;
    }
    _005fjspx_005ftagPool_005fhtml_005fhidden_0026_005fproperty_005fname_005fnobody.reuse(_jspx_th_html_005fhidden_005f0);
    return false;
  }

  private boolean _jspx_meth_gw_005fgrid2_005f0(javax.servlet.jsp.tagext.JspTag _jspx_th_html_005fform_005f0, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  gw:grid2
    com.liusy.web.tag.GridTag _jspx_th_gw_005fgrid2_005f0 = (com.liusy.web.tag.GridTag) _005fjspx_005ftagPool_005fgw_005fgrid2_0026_005fwidth_005fstyleClass_005fsecondRowStyle_005fschema_005frowEventHandle_005fproperty_005fparameters_005fname_005ffixRows_005fcellSpacing_005fcellPadding_005fborder.get(com.liusy.web.tag.GridTag.class);
    _jspx_th_gw_005fgrid2_005f0.setPageContext(_jspx_page_context);
    _jspx_th_gw_005fgrid2_005f0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_html_005fform_005f0);
    // /pages/dynamicquery/commsubject/commsubjectquery_count.jsp(59,0) name = border type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_gw_005fgrid2_005f0.setBorder("0");
    // /pages/dynamicquery/commsubject/commsubjectquery_count.jsp(59,0) name = cellPadding type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_gw_005fgrid2_005f0.setCellPadding("0");
    // /pages/dynamicquery/commsubject/commsubjectquery_count.jsp(59,0) name = cellSpacing type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_gw_005fgrid2_005f0.setCellSpacing("0");
    // /pages/dynamicquery/commsubject/commsubjectquery_count.jsp(59,0) name = width type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_gw_005fgrid2_005f0.setWidth("100%");
    // /pages/dynamicquery/commsubject/commsubjectquery_count.jsp(59,0) name = styleClass type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_gw_005fgrid2_005f0.setStyleClass("listTable");
    // /pages/dynamicquery/commsubject/commsubjectquery_count.jsp(59,0) name = secondRowStyle type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_gw_005fgrid2_005f0.setSecondRowStyle("background:#e3effe");
    // /pages/dynamicquery/commsubject/commsubjectquery_count.jsp(59,0) name = name type = null reqTime = true required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_gw_005fgrid2_005f0.setName("commSubjectQueryForm");
    // /pages/dynamicquery/commsubject/commsubjectquery_count.jsp(59,0) name = property type = null reqTime = true required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_gw_005fgrid2_005f0.setProperty("query.recordSet");
    // /pages/dynamicquery/commsubject/commsubjectquery_count.jsp(59,0) name = parameters type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_gw_005fgrid2_005f0.setParameters("query");
    // /pages/dynamicquery/commsubject/commsubjectquery_count.jsp(59,0) name = rowEventHandle type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_gw_005fgrid2_005f0.setRowEventHandle("false");
    // /pages/dynamicquery/commsubject/commsubjectquery_count.jsp(59,0) name = schema type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_gw_005fgrid2_005f0.setSchema("common");
    // /pages/dynamicquery/commsubject/commsubjectquery_count.jsp(59,0) name = fixRows type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_gw_005fgrid2_005f0.setFixRows("true");
    int _jspx_eval_gw_005fgrid2_005f0 = _jspx_th_gw_005fgrid2_005f0.doStartTag();
    if (_jspx_eval_gw_005fgrid2_005f0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      if (_jspx_eval_gw_005fgrid2_005f0 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.pushBody();
        _jspx_th_gw_005fgrid2_005f0.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
        _jspx_th_gw_005fgrid2_005f0.doInitBody();
      }
      do {
        out.write("\r\n");
        out.write("    \t\t\t\t<header height=\"27\"  />\r\n");
        out.write("    \r\n");
        out.write("    \t\t\t\t\t\t<column width=\"30%\" name=\"表名称\" itemType=\"hyperlink\" href=\"javascript:void(0)\"  onClick=\"parent.showlist('{id}')\" property=\"cName\" align=\"center\" />\r\n");
        out.write("\r\n");
        out.write("\t\t\t\t\t\t\t<column width=\"20%\" name=\"来源\" property=\"cityName\" align=\"center\" />\r\n");
        out.write("\t\t\t\t\t\t\t<column width=\"20%\" name=\"分类\" property=\"catagoryName\" align=\"center\" />\r\n");
        out.write("\t\t\t\t\t\t\t<column width=\"20%\" name=\"安全等级\" property=\"securityLevel\" align=\"center\" />\r\n");
        out.write("\t\r\n");
        out.write("    \t\t\t\t\t\t <column width=\"10%\" name=\"结果数量\" property=\"count\" />\r\n");
        out.write("    \t         \r\n");
        out.write("   \t\t\t\t\t\t\t<rooter height=\"0\" width=\"100%\" showType=\"none\" />      \r\n");
        out.write("\t\t\t\t\t\t\t");
        int evalDoAfterBody = _jspx_th_gw_005fgrid2_005f0.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
      if (_jspx_eval_gw_005fgrid2_005f0 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.popBody();
      }
    }
    if (_jspx_th_gw_005fgrid2_005f0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fgw_005fgrid2_0026_005fwidth_005fstyleClass_005fsecondRowStyle_005fschema_005frowEventHandle_005fproperty_005fparameters_005fname_005ffixRows_005fcellSpacing_005fcellPadding_005fborder.reuse(_jspx_th_gw_005fgrid2_005f0);
      return true;
    }
    _005fjspx_005ftagPool_005fgw_005fgrid2_0026_005fwidth_005fstyleClass_005fsecondRowStyle_005fschema_005frowEventHandle_005fproperty_005fparameters_005fname_005ffixRows_005fcellSpacing_005fcellPadding_005fborder.reuse(_jspx_th_gw_005fgrid2_005f0);
    return false;
  }
}
