package org.apache.jsp.pages.system.subject;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class subject_005fcfg_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List _jspx_dependants;

  static {
    _jspx_dependants = new java.util.ArrayList(4);
    _jspx_dependants.add("/WEB-INF/config/tag/struts-logic.tld");
    _jspx_dependants.add("/WEB-INF/config/tag/struts-template.tld");
    _jspx_dependants.add("/WEB-INF/config/tag/struts-bean.tld");
    _jspx_dependants.add("/WEB-INF/config/tag/struts-html.tld");
  }

  private javax.el.ExpressionFactory _el_expressionfactory;
  private org.apache.AnnotationProcessor _jsp_annotationprocessor;

  public Object getDependants() {
    return _jspx_dependants;
  }

  public void _jspInit() {
    _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
    _jsp_annotationprocessor = (org.apache.AnnotationProcessor) getServletConfig().getServletContext().getAttribute(org.apache.AnnotationProcessor.class.getName());
  }

  public void _jspDestroy() {
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

	String path = request.getContextPath();
	String CONTEXT_PATH = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";

      out.write("\r\n");
      out.write("\r\n");
      out.write("<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\">\r\n");
      out.write("<html xmlns=\"http://www.w3.org/1999/xhtml\">\r\n");
      out.write("<head>\r\n");
      out.write("\t<title></title>\r\n");
      out.write("  \r\n");
      out.write("\t\r\n");
      out.write("\t<link rel=\"stylesheet\" type=\"text/css\" href=\"");
      out.print(CONTEXT_PATH);
      out.write("css/main.css\" />\r\n");
      out.write("  \t <link rel=\"stylesheet\" type=\"text/css\" href=\"");
      out.print(CONTEXT_PATH);
      out.write("dhtmlxSuite/dhtmlx/dhtmlx.css\">\r\n");
      out.write("<script src=\"");
      out.print(CONTEXT_PATH);
      out.write("dhtmlxSuite/dhtmlx/dhtmlx.js\"></script>\r\n");
      out.write("   <style type=\"text/css\">\r\n");
      out.write("   html, body {width:100%; height:100%;}\r\n");
      out.write("  .special-padding{padding: 2px;}\r\n");
      out.write("  .special-form{margin: 0;}\r\n");
      out.write("</style>\r\n");
      out.write("  <body>\r\n");
      out.write("   \r\n");
      out.write("\t<script type=\"text/javascript\">\r\n");
      out.write("\tvar dhxLayout = new dhtmlXLayoutObject(document.body, \"1C\");\r\n");
      out.write("\t\r\n");
      out.write("\tvar tabbar = dhxLayout.cells(\"a\").attachTabbar(); //attachObject(\"a_tabbar\");\r\n");
      out.write("\t//var tabbar = new dhtmlXTabBar(\"a_tabbar\", \"top\");\r\n");
      out.write("\t//tabbar.enableScroll(false);\r\n");
      out.write("\ttabbar.enableAutoReSize();\r\n");
      out.write("tabbar.setSkin('dhx_skyblue');\r\n");
      out.write("tabbar.setHrefMode(\"iframes-on-demand\");\r\n");
      out.write("tabbar.setImagePath(\"");
      out.print(CONTEXT_PATH);
      out.write("dhtmlxSuite/dhtmlxTabbar/codebase/imgs/\");\r\n");
      out.write("tabbar.addTab(\"a1\", \"公共主题配置\", \"100px\");\r\n");
      out.write("tabbar.addTab(\"a2\", \"分类主题配置\", \"100px\");\r\n");
      out.write("tabbar.setContentHref(\"a1\", \"");
      out.print(CONTEXT_PATH);
      out.write("querycfg/commSubject.do\");\r\n");
      out.write("tabbar.setContentHref(\"a2\", \"");
      out.print(CONTEXT_PATH);
      out.write("querycfg/classifySubject.do\");\r\n");
      out.write("\r\n");
      out.write("tabbar.setTabActive(\"a1\");\r\n");
      out.write("\t</script>\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("  </body>\r\n");
      out.write("</html>\r\n");
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
}
