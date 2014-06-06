package org.apache.jsp.pages.analysis;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class query_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List _jspx_dependants;

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

      out.write('\r');
      out.write('\n');

   String tempId = (String)request.getParameter("tempId");
   String fileName = (String)request.getAttribute("fileName");

	String path = request.getContextPath();
	String CONTEXT_PATH = request.getScheme() + "://"+ request.getServerName() + ":" + request.getServerPort()+ path + "/";

      out.write("\r\n");
      out.write("<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\">\r\n");
      out.write("<html xmlns=\"http://www.w3.org/1999/xhtml\">\r\n");
      out.write("<head>\r\n");
      out.write("<title>¹㶫ʡхϢ̹£¨˽¾ޓ¦ԃϵͳ£©</title>\r\n");
      out.write("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\" />\r\n");
      out.write("<meta http-equiv=\"x-ua-compatible\" content=\"ie=7\" />\r\n");
      out.write("\r\n");
      out.write("<style>\r\n");
      out.write("body { margin: 1px; overflow:hidden;text-align:center;background-color:#DCE9EF }\r\n");
      out.write("</style>\r\n");
      out.write("</head>\r\n");
      out.write("\r\n");
      out.write("<body scroll=\"no\">\r\n");
      out.write("  \t<object classid=\"clsid:D27CDB6E-AE6D-11cf-96B8-444553540000\"\r\n");
      out.write("\t\t\tid=\"query\" width=\"100%\" height=\"100%\"\r\n");
      out.write("\t\t\tcodebase=\"http://fpdownload.macromedia.com/get/flashplayer/current/swflash.cab\">\r\n");
      out.write("\t\t\t<param name=\"movie\" value=\"");
      out.print(CONTEXT_PATH);
      out.write("pages/analysis/flash/");
      out.print(fileName);
      out.write(".swf\" />\r\n");
      out.write("\t\t\t<param name=\"quality\" value=\"high\" />\r\n");
      out.write("\t\t\t<param name=\"wmode\" value=\"transparent\">\r\n");
      out.write("\t\t\t<param name=\"bgcolor\" value=\"#dce9ef\" />\r\n");
      out.write("\t\t\t<param name=\"FlashVars\" value=\"url=");
      out.print(CONTEXT_PATH);
      out.write("&templateId=");
      out.print(tempId);
      out.write("\" />\r\n");
      out.write("\t\t\t<param name=\"allowScriptAccess\" value=\"sameDomain\" />\r\n");
      out.write("\t\t\t<embed src=\"");
      out.print(CONTEXT_PATH);
      out.write("pages/analysis/flash/");
      out.print(fileName);
      out.write(".swf\" quality=\"high\" bgcolor=\"#dce9ef\"\r\n");
      out.write("\t\t\t\twidth=\"100%\" height=\"100%\" name=\"query\" align=\"middle\"\r\n");
      out.write("\t\t\t\tplay=\"true\"\r\n");
      out.write("\t\t\t\tloop=\"false\"\r\n");
      out.write("\t\t\t\tquality=\"high\"\r\n");
      out.write("\t\t\t\tallowScriptAccess=\"sameDomain\"\r\n");
      out.write("\t\t\t\ttype=\"application/x-shockwave-flash\"\r\n");
      out.write("\t\t\t\tpluginspage=\"http://www.adobe.com/go/getflashplayer\">\r\n");
      out.write("\t\t\t</embed>\r\n");
      out.write("\t</object>\r\n");
      out.write("</body>\r\n");
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
