package org.apache.jsp.pages.compare;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class compareInfo_005flist_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

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
  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005fhtml_005ftext_0026_005fproperty_005fnobody;
  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005fgw_005fbutton_0026_005fonClick_005fname;
  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005fgw_005fbutton_0026_005fstyleClass_005fonClick_005ficon;
  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005fhtml_005fhidden_0026_005fproperty_005fnobody;
  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005fgw_005fgrid2_0026_005fwidth_005fstyleClass_005frowEventHandle_005fproperty_005fparameters_005fname_005ffixRows_005fcellSpacing_005fcellPadding;

  public Object getDependants() {
    return _jspx_dependants;
  }

  public void _jspInit() {
    _005fjspx_005ftagPool_005fhtml_005fform_0026_005fmethod_005faction = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _005fjspx_005ftagPool_005fhtml_005ftext_0026_005fproperty_005fnobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _005fjspx_005ftagPool_005fgw_005fbutton_0026_005fonClick_005fname = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _005fjspx_005ftagPool_005fgw_005fbutton_0026_005fstyleClass_005fonClick_005ficon = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _005fjspx_005ftagPool_005fhtml_005fhidden_0026_005fproperty_005fnobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _005fjspx_005ftagPool_005fgw_005fgrid2_0026_005fwidth_005fstyleClass_005frowEventHandle_005fproperty_005fparameters_005fname_005ffixRows_005fcellSpacing_005fcellPadding = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
  }

  public void _jspDestroy() {
    _005fjspx_005ftagPool_005fhtml_005fform_0026_005fmethod_005faction.release();
    _005fjspx_005ftagPool_005fhtml_005ftext_0026_005fproperty_005fnobody.release();
    _005fjspx_005ftagPool_005fgw_005fbutton_0026_005fonClick_005fname.release();
    _005fjspx_005ftagPool_005fgw_005fbutton_0026_005fstyleClass_005fonClick_005ficon.release();
    _005fjspx_005ftagPool_005fhtml_005fhidden_0026_005fproperty_005fnobody.release();
    _005fjspx_005ftagPool_005fgw_005fgrid2_0026_005fwidth_005fstyleClass_005frowEventHandle_005fproperty_005fparameters_005fname_005ffixRows_005fcellSpacing_005fcellPadding.release();
  }

  public void _jspService(HttpServletRequest request, HttpServletResponse response)
        throws java.io.IOException, ServletException {

    JspFactory _jspxFactory = null;
    PageContext pageContext = null;
    HttpSession session = null;
    ServletContext application = null;
    ServletConfig config = null;
    JspWriter out = null;
    Object page = this;
    JspWriter _jspx_out = null;
    PageContext _jspx_page_context = null;


    try {
      _jspxFactory = JspFactory.getDefaultFactory();
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
	String CONTEXT_PATH = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	String msg = (String) request.getAttribute("msg");
	if (msg == null)
		msg = "";

      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\">\r\n");
      out.write("<html xmlns=\"http://www.w3.org/1999/xhtml\">\r\n");
      out.write("\t<head>\r\n");
      out.write("\t\t<link rel=\"stylesheet\" type=\"text/css\" href=\"");
      out.print(CONTEXT_PATH);
      out.write("dhtmlxSuite/dhtmlx/dhtmlx.css\" />\r\n");
      out.write("\t\t<script src=\"");
      out.print(CONTEXT_PATH);
      out.write("dhtmlxSuite/dhtmlx/dhtmlx.js\"></script>\r\n");
      out.write("\t\t<link rel=\"stylesheet\" type=\"text/css\" href=\"");
      out.print(CONTEXT_PATH);
      out.write("css/main.css\" />\r\n");
      out.write("\t\t<script src=\"");
      out.print(CONTEXT_PATH);
      out.write("js/ctrl_util.js\"></script>\r\n");
      out.write("\t\t<script src=\"");
      out.print(CONTEXT_PATH);
      out.write("js/date_validate.js\"></script>\r\n");
      out.write("\t\t<script language=\"javascript\" src=\"");
      out.print( CONTEXT_PATH );
      out.write("js/datapicker/WdatePicker.js\"></script>\r\n");
      out.write("\t\t\r\n");
      out.write("\t\t<script language=\"javascript\"> \r\n");
      out.write("      var contextpath = \"");
      out.print(CONTEXT_PATH);
      out.write("\";\r\n");
      out.write("      var hyperlink = contextpath + \"compare/compareInfo.do\";\r\n");
      out.write("      var hyperlink1 = contextpath + \"compare/compareRunInfo.do\";\r\n");
      out.write("      var fulllink = contextpath + \"compare/compareInfo.do\";\r\n");
      out.write("      \r\n");
      out.write("\t\t function goAdd()  {\r\n");
      out.write("\t\t\tvar tableid = getElement(\"query.parameters(tableid)\");\r\n");
      out.write("\t\t\topenWindow(\"添加对比\",fulllink+'?action=OVERVIEW',700,400);\r\n");
      out.write("\t\t}\r\n");
      out.write("\t\tfunction goModify() {\r\n");
      out.write("\t\t\tvar id = findSelected(\"ID\",\"修改\");\r\n");
      out.write("\t\t\tif(id == \"\") return;\r\n");
      out.write("\t\t    var url_link=fulllink+'?action=OVERVIEW&record(id)='+id;\r\n");
      out.write("\t\t\topenWindow(\"修改对比\",url_link,700,400);\r\n");
      out.write("\t\t}\r\n");
      out.write("\t\tfunction goConfig() {\r\n");
      out.write("\t\t\tvar id = findSelected(\"ID\",\"修改\");\r\n");
      out.write("\t\t\tif(id == \"\") return;\r\n");
      out.write("\t\t    var url_link=fulllink+'?action=config&record(id)='+id;\r\n");
      out.write("\t\t\topenWindow(\"修改对比\",url_link,700,400);\r\n");
      out.write("\t\t}\r\n");
      out.write("\t\t\r\n");
      out.write("\t\tfunction goDel()  {\r\n");
      out.write("\t  \t\tvar id = findMultiSelected(\"ID\",\"删除\");\r\n");
      out.write("\t  \t\tif(id == \"\") return;\r\n");
      out.write("\t  \t\tvar res = confirm(\"是否真的要删除?\");\r\n");
      out.write("\t  \t\tif(res == true) {\r\n");
      out.write("       \t\t\tdocument.forms[0].action = fulllink + \"?action=delete&ids=\" + id;\r\n");
      out.write("       \t\t\tdocument.forms[0].target = \"_self\";\r\n");
      out.write("       \t\t\tdocument.forms[0].submit();\r\n");
      out.write("  \t\t \t}\r\n");
      out.write("    \t}\r\n");
      out.write("    \tfunction goStart(){\r\n");
      out.write("    \t\tvar id = findMultiSelected(\"ID\",\"开始对比\");\r\n");
      out.write("\t  \t\tif(id == \"\") return;\r\n");
      out.write("\t  \t\t\r\n");
      out.write("\t  \t\tvar cType = getElement(\"query.pageNumber\");    \r\n");
      out.write("\t  \t\tvar res = confirm(\"是否真的要启用对比?\");\r\n");
      out.write("\t  \t\tif(res == true) {\r\n");
      out.write("       \t\t\t//goViewInfo();\r\n");
      out.write("       \t\t\tdocument.forms[0].action = fulllink + \"?action=startcompare&ids=\" + id;\r\n");
      out.write("       \t\t\tdocument.forms[0].target = \"_self\";\r\n");
      out.write("       \t\t\tdocument.forms[0].submit();\r\n");
      out.write("       \t\t\t\r\n");
      out.write("  \t\t \t}\r\n");
      out.write("    \t}\r\n");
      out.write("    \tfunction goCancel(){\r\n");
      out.write("    \t\tvar id = findMultiSelected(\"ID\",\"取消对比\");\r\n");
      out.write("\t  \t\tif(id == \"\") return;\r\n");
      out.write("\t  \t\t//var res = confirm(\"是否真的要取消开始对比?\");\r\n");
      out.write("\t  \t\t//if(res == true) {\r\n");
      out.write("       \t\t\tdocument.forms[0].action = fulllink + \"?action=stopcompare&ids=\" + id;\r\n");
      out.write("       \t\t\tdocument.forms[0].target = \"_self\";\r\n");
      out.write("       \t\t\tdocument.forms[0].submit();\r\n");
      out.write("  \t\t \t//}\r\n");
      out.write("    \t}\r\n");
      out.write("    \t\r\n");
      out.write("\t\tfunction renew() {\r\n");
      out.write("\t\t\tvar order = getElement(\"query.order\");                  order.value=\"\";\r\n");
      out.write("\t\t\tvar desc =  getElement(\"query.orderDirection\");         desc.value=\"\";\r\n");
      out.write("\t\t\tvar pn =    getElement(\"query.pageNumber\");             pn.value=\"1\";\r\n");
      out.write("\t\t\tvar ps =    getElement(\"query.pageSize\");               ps.value=\"10\";\r\n");
      out.write("\t\t\tvar v0 =    getElement(\"query.parameters(memoTitle)\");    v0.value=\"\";\r\n");
      out.write("\t\t\tvar v1 =    getElement(\"query.parameters(createDateFrom)\");    v1.value=\"\";\r\n");
      out.write("\t\t\tvar v2 =    getElement(\"query.parameters(createDateTo)\");    v2.value=\"\";\r\n");
      out.write("    \t\tgosearch();\r\n");
      out.write("\t\t}\r\n");
      out.write("\r\n");
      out.write("\t\tfunction goQuery() {\r\n");
      out.write("\t\t\tvar pn = getElement(\"query.pageNumber\");             \r\n");
      out.write("\t\t\tpn.value=\"1\";\r\n");
      out.write("\t\t\tgosearch();\r\n");
      out.write("\t\t}\r\n");
      out.write("\r\n");
      out.write("\t\tfunction closedialog(ret) {\r\n");
      out.write("\t\t\tdhxWins.window(winName).close();\r\n");
      out.write("      \t\teditMode=\"\";\r\n");
      out.write("\t\t\tif(ret=='true') {\r\n");
      out.write("\t\t\t\tgosearch();\r\n");
      out.write("\t\t\t}\t\r\n");
      out.write("\t\t}\r\n");
      out.write("\t\t\r\n");
      out.write("\r\n");
      out.write("\t\tfunction goDel()  {\r\n");
      out.write("\t  \t\tvar id = findMultiSelected(\"ID\",\"删除\");\r\n");
      out.write("\t  \t\tif(id == \"\") return;\r\n");
      out.write("\t  \t\tvar res = confirm(\"是否真的要删除?\");\r\n");
      out.write("\t  \t\tif(res == true) {\r\n");
      out.write("       \t\t\tdocument.forms[0].action = hyperlink + \"?action=delete&ids=\" + id;\r\n");
      out.write("       \t\t\tdocument.forms[0].target = \"_self\";\r\n");
      out.write("       \t\t\tdocument.forms[0].submit();\r\n");
      out.write("    }\r\n");
      out.write("    }\r\n");
      out.write("    \r\n");
      out.write("    function goViewInfo()  {\r\n");
      out.write("\t  \t\tvar id = findMultiSelected(\"ID\",\"运行状态\");\r\n");
      out.write("\t  \t\tif(id == \"\") return;\r\n");
      out.write("    \t\tvar url_link=hyperlink1 + \"?action=list&compId=\" + id;\r\n");
      out.write("\t\t\topenWindow(\"查看运行状态\",url_link,700,400);\r\n");
      out.write("    \r\n");
      out.write("}\r\n");
      out.write("\t\t function alertMsg(msgtxt,heigth){\r\n");
      out.write("      \tvar alerttab='<table cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" class=\"formTable\"><tbody><tr><td width=\"60px\" class=\"textR\"><span><img src=\"'+contextpath+'common/images/info.gif\"/></span></td><td class=\"textL\"><span><b>'+msgtxt+'</b></span></td></tr><tr class=\"btnTr\"><td class=\"textC\" colspan=\"2\"><a id=\"closebtn\" href=\"javascript:onClick=closedialog()\" class=\"btnStyle\" name=\"btnRefresh\" ><strong>关闭</strong></a></td></tr></tbody></table>';       \r\n");
      out.write("\t\t\topenSingleWindow('个人空间',alerttab,300,120);\r\n");
      out.write("\t\t\t$$(\"closebtn\").focus();\r\n");
      out.write("      }\t\t\r\n");
      out.write("\r\n");
      out.write("\t\tvar msg = \"");
      out.print(msg);
      out.write("\";\r\n");
      out.write("\t\tif (msg != \"\")\r\n");
      out.write("\t\t\talert(msg);\r\n");
      out.write("\t\t\t\r\n");
      out.write("\r\n");
      out.write("\t</script>\r\n");
      out.write("\r\n");
      out.write("\t</head>\r\n");
      out.write("\r\n");
      out.write("\t<body>\r\n");
      out.write("\t<div class=\"special-padding\">   \r\n");
      out.write("\t<!--action=\"/personal/personalTable.do\"-->\r\n");
      out.write("\t\t");
      if (_jspx_meth_html_005fform_005f0(_jspx_page_context))
        return;
      out.write("\r\n");
      out.write("\t\t</div>\r\n");
      out.write("\t\t");
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
      out.write("\t</body>\r\n");
      out.write("</html>");
    } catch (Throwable t) {
      if (!(t instanceof SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          out.clearBuffer();
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
      }
    } finally {
      if (_jspxFactory != null) _jspxFactory.releasePageContext(_jspx_page_context);
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
    _jspx_th_html_005fform_005f0.setAction("/compare/compareTable.do");
    _jspx_th_html_005fform_005f0.setMethod("post");
    int _jspx_eval_html_005fform_005f0 = _jspx_th_html_005fform_005f0.doStartTag();
    if (_jspx_eval_html_005fform_005f0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      do {
        out.write("\r\n");
        out.write("\t\t\t<!-- \r\n");
        out.write("\t\t\t<table cellpadding=\"0\" cellspacing=\"0\" class=\"formTable\">\r\n");
        out.write("\t\t\t\t<tbody>\r\n");
        out.write("\t\t\t\t<tr>\r\n");
        out.write("\t\t\t\t\t<td width=\"30%\">\r\n");
        out.write("\t\t\t\t\t  对比查询名称：\r\n");
        out.write("\t\t\t\t\t</td>\r\n");
        out.write("\t\t\t\t\t<td>\r\n");
        out.write("\t\t\t\t\t\t");
        if (_jspx_meth_html_005ftext_005f0(_jspx_th_html_005fform_005f0, _jspx_page_context))
          return true;
        out.write("\r\n");
        out.write("\t\t\t\t\t</td>\r\n");
        out.write("\t\t\t\t\r\n");
        out.write("\t\t\t\t</tr>\r\n");
        out.write("\t\t\t\t\r\n");
        out.write("\t\t\t\t\t<tr class=\"btnTr\">\r\n");
        out.write("\t\t\t\t\t\t<td class=\"textC\" colspan=\"2\">\r\n");
        out.write("\t\t\t\t\t\t\t");
        if (_jspx_meth_gw_005fbutton_005f0(_jspx_th_html_005fform_005f0, _jspx_page_context))
          return true;
        out.write("\r\n");
        out.write("\t\t\t\t      &nbsp;\r\n");
        out.write("\t\t\t      \t\t\t");
        if (_jspx_meth_gw_005fbutton_005f1(_jspx_th_html_005fform_005f0, _jspx_page_context))
          return true;
        out.write("\r\n");
        out.write("\t\t\t\t\t\t</td>\r\n");
        out.write("\t\t\t\t\t</tr>\r\n");
        out.write("\t\t\t\t\r\n");
        out.write("\t\t\t\t</tbody>\r\n");
        out.write("\t\t\t</table>\r\n");
        out.write("\t\t\t \r\n");
        out.write("  \t\t\t<div class=\"gap8\">&nbsp;</div>\r\n");
        out.write("  \t\t\t-->  \r\n");
        out.write("\t\t\t<table border=\"0\" width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" align=\"center\">\r\n");
        out.write("\t\t\t\t<tr>\r\n");
        out.write("            \t\t<td width=\"100%\" valign=\"top\">\r\n");
        out.write("\t\t\t\t\t\t<table cellspacing=\"1\" cellpadding=\"1\" class=\"controlTable\">\r\n");
        out.write("\t\t\t\t\t\t\t<tr>\r\n");
        out.write("\t\t\t\t\t\t\t\t<td valign=\"top\" align=\"left\">\r\n");
        out.write("\t\t\t\t\t\t\t\t\t");
        if (_jspx_meth_gw_005fbutton_005f2(_jspx_th_html_005fform_005f0, _jspx_page_context))
          return true;
        out.write("&nbsp;\r\n");
        out.write("\t\t\t\t\t\t\t\t\t");
        if (_jspx_meth_gw_005fbutton_005f3(_jspx_th_html_005fform_005f0, _jspx_page_context))
          return true;
        out.write("&nbsp;\r\n");
        out.write("\t\t\t\t\t\t\t\t\t<!--");
        if (_jspx_meth_gw_005fbutton_005f4(_jspx_th_html_005fform_005f0, _jspx_page_context))
          return true;
        out.write("&nbsp;-->\r\n");
        out.write("\t\t\t\t\t\t\t\t\t");
        if (_jspx_meth_gw_005fbutton_005f5(_jspx_th_html_005fform_005f0, _jspx_page_context))
          return true;
        out.write("&nbsp;\r\n");
        out.write("\t\t\t\t\t\t\t\t\t");
        if (_jspx_meth_gw_005fbutton_005f6(_jspx_th_html_005fform_005f0, _jspx_page_context))
          return true;
        out.write("&nbsp;\r\n");
        out.write("\t\t\t\t\t\t\t\t\t");
        if (_jspx_meth_gw_005fbutton_005f7(_jspx_th_html_005fform_005f0, _jspx_page_context))
          return true;
        out.write("&nbsp;\r\n");
        out.write("\t\t\t\t\t\t\t\t\t");
        if (_jspx_meth_gw_005fbutton_005f8(_jspx_th_html_005fform_005f0, _jspx_page_context))
          return true;
        out.write("&nbsp;\r\n");
        out.write("\t\t\t\t\t\t\t\t</td>\r\n");
        out.write("\t\t\t\t\t\t\t</tr>\r\n");
        out.write("\t\t\t\t\t\t</table>\r\n");
        out.write("\t\t\t\t\t\t");
        if (_jspx_meth_html_005fhidden_005f0(_jspx_th_html_005fform_005f0, _jspx_page_context))
          return true;
        out.write("\r\n");
        out.write("\t\t\t\t\t\t");
        if (_jspx_meth_html_005fhidden_005f1(_jspx_th_html_005fform_005f0, _jspx_page_context))
          return true;
        out.write("\r\n");
        out.write("\t\t\t\t\t\t");
        if (_jspx_meth_html_005fhidden_005f2(_jspx_th_html_005fform_005f0, _jspx_page_context))
          return true;
        out.write("\r\n");
        out.write("\t\t\t\t\t\t");
        if (_jspx_meth_html_005fhidden_005f3(_jspx_th_html_005fform_005f0, _jspx_page_context))
          return true;
        out.write("\r\n");
        out.write("\t\t\t\t\t\t");
        if (_jspx_meth_html_005fhidden_005f4(_jspx_th_html_005fform_005f0, _jspx_page_context))
          return true;
        out.write("\r\n");
        out.write("\t\t\t\t\t\t");
        if (_jspx_meth_html_005fhidden_005f5(_jspx_th_html_005fform_005f0, _jspx_page_context))
          return true;
        out.write("\r\n");
        out.write("\t\t\t\t\t\t\r\n");
        out.write("\t\t\t\t\t\t");
        if (_jspx_meth_gw_005fgrid2_005f0(_jspx_th_html_005fform_005f0, _jspx_page_context))
          return true;
        out.write("\r\n");
        out.write("\t\t\t\t\t\t\t\t\t\t\t\r\n");
        out.write("\t\t\t\t\t\t\t\t\t\t\t\t\r\n");
        out.write("\t\t\t\t\t</td>\r\n");
        out.write("\t\t\t\t</tr>\r\n");
        out.write("\t\t\t</table>\r\n");
        out.write("\t\t");
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

  private boolean _jspx_meth_html_005ftext_005f0(javax.servlet.jsp.tagext.JspTag _jspx_th_html_005fform_005f0, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  html:text
    org.apache.struts.taglib.html.TextTag _jspx_th_html_005ftext_005f0 = (org.apache.struts.taglib.html.TextTag) _005fjspx_005ftagPool_005fhtml_005ftext_0026_005fproperty_005fnobody.get(org.apache.struts.taglib.html.TextTag.class);
    _jspx_th_html_005ftext_005f0.setPageContext(_jspx_page_context);
    _jspx_th_html_005ftext_005f0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_html_005fform_005f0);
    _jspx_th_html_005ftext_005f0.setProperty("query.parameters(cnName)");
    int _jspx_eval_html_005ftext_005f0 = _jspx_th_html_005ftext_005f0.doStartTag();
    if (_jspx_th_html_005ftext_005f0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fhtml_005ftext_0026_005fproperty_005fnobody.reuse(_jspx_th_html_005ftext_005f0);
      return true;
    }
    _005fjspx_005ftagPool_005fhtml_005ftext_0026_005fproperty_005fnobody.reuse(_jspx_th_html_005ftext_005f0);
    return false;
  }

  private boolean _jspx_meth_gw_005fbutton_005f0(javax.servlet.jsp.tagext.JspTag _jspx_th_html_005fform_005f0, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  gw:button
    com.liusy.web.tag.ButtonTag _jspx_th_gw_005fbutton_005f0 = (com.liusy.web.tag.ButtonTag) _005fjspx_005ftagPool_005fgw_005fbutton_0026_005fonClick_005fname.get(com.liusy.web.tag.ButtonTag.class);
    _jspx_th_gw_005fbutton_005f0.setPageContext(_jspx_page_context);
    _jspx_th_gw_005fbutton_005f0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_html_005fform_005f0);
    _jspx_th_gw_005fbutton_005f0.setName("btnQuery");
    _jspx_th_gw_005fbutton_005f0.setOnClick("goQuery()");
    int _jspx_eval_gw_005fbutton_005f0 = _jspx_th_gw_005fbutton_005f0.doStartTag();
    if (_jspx_eval_gw_005fbutton_005f0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      if (_jspx_eval_gw_005fbutton_005f0 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.pushBody();
        _jspx_th_gw_005fbutton_005f0.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
        _jspx_th_gw_005fbutton_005f0.doInitBody();
      }
      do {
        out.write('查');
        out.write('询');
        int evalDoAfterBody = _jspx_th_gw_005fbutton_005f0.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
      if (_jspx_eval_gw_005fbutton_005f0 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.popBody();
      }
    }
    if (_jspx_th_gw_005fbutton_005f0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fgw_005fbutton_0026_005fonClick_005fname.reuse(_jspx_th_gw_005fbutton_005f0);
      return true;
    }
    _005fjspx_005ftagPool_005fgw_005fbutton_0026_005fonClick_005fname.reuse(_jspx_th_gw_005fbutton_005f0);
    return false;
  }

  private boolean _jspx_meth_gw_005fbutton_005f1(javax.servlet.jsp.tagext.JspTag _jspx_th_html_005fform_005f0, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  gw:button
    com.liusy.web.tag.ButtonTag _jspx_th_gw_005fbutton_005f1 = (com.liusy.web.tag.ButtonTag) _005fjspx_005ftagPool_005fgw_005fbutton_0026_005fonClick_005fname.get(com.liusy.web.tag.ButtonTag.class);
    _jspx_th_gw_005fbutton_005f1.setPageContext(_jspx_page_context);
    _jspx_th_gw_005fbutton_005f1.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_html_005fform_005f0);
    _jspx_th_gw_005fbutton_005f1.setName("btnRefresh");
    _jspx_th_gw_005fbutton_005f1.setOnClick("renew()");
    int _jspx_eval_gw_005fbutton_005f1 = _jspx_th_gw_005fbutton_005f1.doStartTag();
    if (_jspx_eval_gw_005fbutton_005f1 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      if (_jspx_eval_gw_005fbutton_005f1 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.pushBody();
        _jspx_th_gw_005fbutton_005f1.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
        _jspx_th_gw_005fbutton_005f1.doInitBody();
      }
      do {
        out.write('重');
        out.write('置');
        int evalDoAfterBody = _jspx_th_gw_005fbutton_005f1.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
      if (_jspx_eval_gw_005fbutton_005f1 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.popBody();
      }
    }
    if (_jspx_th_gw_005fbutton_005f1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fgw_005fbutton_0026_005fonClick_005fname.reuse(_jspx_th_gw_005fbutton_005f1);
      return true;
    }
    _005fjspx_005ftagPool_005fgw_005fbutton_0026_005fonClick_005fname.reuse(_jspx_th_gw_005fbutton_005f1);
    return false;
  }

  private boolean _jspx_meth_gw_005fbutton_005f2(javax.servlet.jsp.tagext.JspTag _jspx_th_html_005fform_005f0, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  gw:button
    com.liusy.web.tag.ButtonTag _jspx_th_gw_005fbutton_005f2 = (com.liusy.web.tag.ButtonTag) _005fjspx_005ftagPool_005fgw_005fbutton_0026_005fstyleClass_005fonClick_005ficon.get(com.liusy.web.tag.ButtonTag.class);
    _jspx_th_gw_005fbutton_005f2.setPageContext(_jspx_page_context);
    _jspx_th_gw_005fbutton_005f2.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_html_005fform_005f0);
    _jspx_th_gw_005fbutton_005f2.setStyleClass("sbuBtnStyle");
    _jspx_th_gw_005fbutton_005f2.setIcon("addIcon");
    _jspx_th_gw_005fbutton_005f2.setOnClick("goAdd()");
    int _jspx_eval_gw_005fbutton_005f2 = _jspx_th_gw_005fbutton_005f2.doStartTag();
    if (_jspx_eval_gw_005fbutton_005f2 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      if (_jspx_eval_gw_005fbutton_005f2 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.pushBody();
        _jspx_th_gw_005fbutton_005f2.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
        _jspx_th_gw_005fbutton_005f2.doInitBody();
      }
      do {
        out.write("添加对比");
        int evalDoAfterBody = _jspx_th_gw_005fbutton_005f2.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
      if (_jspx_eval_gw_005fbutton_005f2 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.popBody();
      }
    }
    if (_jspx_th_gw_005fbutton_005f2.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fgw_005fbutton_0026_005fstyleClass_005fonClick_005ficon.reuse(_jspx_th_gw_005fbutton_005f2);
      return true;
    }
    _005fjspx_005ftagPool_005fgw_005fbutton_0026_005fstyleClass_005fonClick_005ficon.reuse(_jspx_th_gw_005fbutton_005f2);
    return false;
  }

  private boolean _jspx_meth_gw_005fbutton_005f3(javax.servlet.jsp.tagext.JspTag _jspx_th_html_005fform_005f0, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  gw:button
    com.liusy.web.tag.ButtonTag _jspx_th_gw_005fbutton_005f3 = (com.liusy.web.tag.ButtonTag) _005fjspx_005ftagPool_005fgw_005fbutton_0026_005fstyleClass_005fonClick_005ficon.get(com.liusy.web.tag.ButtonTag.class);
    _jspx_th_gw_005fbutton_005f3.setPageContext(_jspx_page_context);
    _jspx_th_gw_005fbutton_005f3.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_html_005fform_005f0);
    _jspx_th_gw_005fbutton_005f3.setStyleClass("sbuBtnStyle");
    _jspx_th_gw_005fbutton_005f3.setIcon("subIcon");
    _jspx_th_gw_005fbutton_005f3.setOnClick("goModify()");
    int _jspx_eval_gw_005fbutton_005f3 = _jspx_th_gw_005fbutton_005f3.doStartTag();
    if (_jspx_eval_gw_005fbutton_005f3 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      if (_jspx_eval_gw_005fbutton_005f3 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.pushBody();
        _jspx_th_gw_005fbutton_005f3.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
        _jspx_th_gw_005fbutton_005f3.doInitBody();
      }
      do {
        out.write("对比配置");
        int evalDoAfterBody = _jspx_th_gw_005fbutton_005f3.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
      if (_jspx_eval_gw_005fbutton_005f3 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.popBody();
      }
    }
    if (_jspx_th_gw_005fbutton_005f3.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fgw_005fbutton_0026_005fstyleClass_005fonClick_005ficon.reuse(_jspx_th_gw_005fbutton_005f3);
      return true;
    }
    _005fjspx_005ftagPool_005fgw_005fbutton_0026_005fstyleClass_005fonClick_005ficon.reuse(_jspx_th_gw_005fbutton_005f3);
    return false;
  }

  private boolean _jspx_meth_gw_005fbutton_005f4(javax.servlet.jsp.tagext.JspTag _jspx_th_html_005fform_005f0, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  gw:button
    com.liusy.web.tag.ButtonTag _jspx_th_gw_005fbutton_005f4 = (com.liusy.web.tag.ButtonTag) _005fjspx_005ftagPool_005fgw_005fbutton_0026_005fstyleClass_005fonClick_005ficon.get(com.liusy.web.tag.ButtonTag.class);
    _jspx_th_gw_005fbutton_005f4.setPageContext(_jspx_page_context);
    _jspx_th_gw_005fbutton_005f4.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_html_005fform_005f0);
    _jspx_th_gw_005fbutton_005f4.setStyleClass("sbuBtnStyle");
    _jspx_th_gw_005fbutton_005f4.setIcon("subIcon");
    _jspx_th_gw_005fbutton_005f4.setOnClick("goConfig()");
    int _jspx_eval_gw_005fbutton_005f4 = _jspx_th_gw_005fbutton_005f4.doStartTag();
    if (_jspx_eval_gw_005fbutton_005f4 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      if (_jspx_eval_gw_005fbutton_005f4 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.pushBody();
        _jspx_th_gw_005fbutton_005f4.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
        _jspx_th_gw_005fbutton_005f4.doInitBody();
      }
      do {
        out.write("对比配置");
        int evalDoAfterBody = _jspx_th_gw_005fbutton_005f4.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
      if (_jspx_eval_gw_005fbutton_005f4 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.popBody();
      }
    }
    if (_jspx_th_gw_005fbutton_005f4.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fgw_005fbutton_0026_005fstyleClass_005fonClick_005ficon.reuse(_jspx_th_gw_005fbutton_005f4);
      return true;
    }
    _005fjspx_005ftagPool_005fgw_005fbutton_0026_005fstyleClass_005fonClick_005ficon.reuse(_jspx_th_gw_005fbutton_005f4);
    return false;
  }

  private boolean _jspx_meth_gw_005fbutton_005f5(javax.servlet.jsp.tagext.JspTag _jspx_th_html_005fform_005f0, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  gw:button
    com.liusy.web.tag.ButtonTag _jspx_th_gw_005fbutton_005f5 = (com.liusy.web.tag.ButtonTag) _005fjspx_005ftagPool_005fgw_005fbutton_0026_005fstyleClass_005fonClick_005ficon.get(com.liusy.web.tag.ButtonTag.class);
    _jspx_th_gw_005fbutton_005f5.setPageContext(_jspx_page_context);
    _jspx_th_gw_005fbutton_005f5.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_html_005fform_005f0);
    _jspx_th_gw_005fbutton_005f5.setStyleClass("sbuBtnStyle");
    _jspx_th_gw_005fbutton_005f5.setIcon("subIcon");
    _jspx_th_gw_005fbutton_005f5.setOnClick("goStart()");
    int _jspx_eval_gw_005fbutton_005f5 = _jspx_th_gw_005fbutton_005f5.doStartTag();
    if (_jspx_eval_gw_005fbutton_005f5 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      if (_jspx_eval_gw_005fbutton_005f5 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.pushBody();
        _jspx_th_gw_005fbutton_005f5.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
        _jspx_th_gw_005fbutton_005f5.doInitBody();
      }
      do {
        out.write("启用对比");
        int evalDoAfterBody = _jspx_th_gw_005fbutton_005f5.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
      if (_jspx_eval_gw_005fbutton_005f5 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.popBody();
      }
    }
    if (_jspx_th_gw_005fbutton_005f5.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fgw_005fbutton_0026_005fstyleClass_005fonClick_005ficon.reuse(_jspx_th_gw_005fbutton_005f5);
      return true;
    }
    _005fjspx_005ftagPool_005fgw_005fbutton_0026_005fstyleClass_005fonClick_005ficon.reuse(_jspx_th_gw_005fbutton_005f5);
    return false;
  }

  private boolean _jspx_meth_gw_005fbutton_005f6(javax.servlet.jsp.tagext.JspTag _jspx_th_html_005fform_005f0, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  gw:button
    com.liusy.web.tag.ButtonTag _jspx_th_gw_005fbutton_005f6 = (com.liusy.web.tag.ButtonTag) _005fjspx_005ftagPool_005fgw_005fbutton_0026_005fstyleClass_005fonClick_005ficon.get(com.liusy.web.tag.ButtonTag.class);
    _jspx_th_gw_005fbutton_005f6.setPageContext(_jspx_page_context);
    _jspx_th_gw_005fbutton_005f6.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_html_005fform_005f0);
    _jspx_th_gw_005fbutton_005f6.setStyleClass("sbuBtnStyle");
    _jspx_th_gw_005fbutton_005f6.setIcon("subIcon");
    _jspx_th_gw_005fbutton_005f6.setOnClick("goCancel()");
    int _jspx_eval_gw_005fbutton_005f6 = _jspx_th_gw_005fbutton_005f6.doStartTag();
    if (_jspx_eval_gw_005fbutton_005f6 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      if (_jspx_eval_gw_005fbutton_005f6 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.pushBody();
        _jspx_th_gw_005fbutton_005f6.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
        _jspx_th_gw_005fbutton_005f6.doInitBody();
      }
      do {
        out.write("禁用对比");
        int evalDoAfterBody = _jspx_th_gw_005fbutton_005f6.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
      if (_jspx_eval_gw_005fbutton_005f6 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.popBody();
      }
    }
    if (_jspx_th_gw_005fbutton_005f6.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fgw_005fbutton_0026_005fstyleClass_005fonClick_005ficon.reuse(_jspx_th_gw_005fbutton_005f6);
      return true;
    }
    _005fjspx_005ftagPool_005fgw_005fbutton_0026_005fstyleClass_005fonClick_005ficon.reuse(_jspx_th_gw_005fbutton_005f6);
    return false;
  }

  private boolean _jspx_meth_gw_005fbutton_005f7(javax.servlet.jsp.tagext.JspTag _jspx_th_html_005fform_005f0, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  gw:button
    com.liusy.web.tag.ButtonTag _jspx_th_gw_005fbutton_005f7 = (com.liusy.web.tag.ButtonTag) _005fjspx_005ftagPool_005fgw_005fbutton_0026_005fstyleClass_005fonClick_005ficon.get(com.liusy.web.tag.ButtonTag.class);
    _jspx_th_gw_005fbutton_005f7.setPageContext(_jspx_page_context);
    _jspx_th_gw_005fbutton_005f7.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_html_005fform_005f0);
    _jspx_th_gw_005fbutton_005f7.setStyleClass("sbuBtnStyle");
    _jspx_th_gw_005fbutton_005f7.setIcon("delIcon");
    _jspx_th_gw_005fbutton_005f7.setOnClick("goDel()");
    int _jspx_eval_gw_005fbutton_005f7 = _jspx_th_gw_005fbutton_005f7.doStartTag();
    if (_jspx_eval_gw_005fbutton_005f7 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      if (_jspx_eval_gw_005fbutton_005f7 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.pushBody();
        _jspx_th_gw_005fbutton_005f7.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
        _jspx_th_gw_005fbutton_005f7.doInitBody();
      }
      do {
        out.write("删除对比");
        int evalDoAfterBody = _jspx_th_gw_005fbutton_005f7.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
      if (_jspx_eval_gw_005fbutton_005f7 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.popBody();
      }
    }
    if (_jspx_th_gw_005fbutton_005f7.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fgw_005fbutton_0026_005fstyleClass_005fonClick_005ficon.reuse(_jspx_th_gw_005fbutton_005f7);
      return true;
    }
    _005fjspx_005ftagPool_005fgw_005fbutton_0026_005fstyleClass_005fonClick_005ficon.reuse(_jspx_th_gw_005fbutton_005f7);
    return false;
  }

  private boolean _jspx_meth_gw_005fbutton_005f8(javax.servlet.jsp.tagext.JspTag _jspx_th_html_005fform_005f0, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  gw:button
    com.liusy.web.tag.ButtonTag _jspx_th_gw_005fbutton_005f8 = (com.liusy.web.tag.ButtonTag) _005fjspx_005ftagPool_005fgw_005fbutton_0026_005fstyleClass_005fonClick_005ficon.get(com.liusy.web.tag.ButtonTag.class);
    _jspx_th_gw_005fbutton_005f8.setPageContext(_jspx_page_context);
    _jspx_th_gw_005fbutton_005f8.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_html_005fform_005f0);
    _jspx_th_gw_005fbutton_005f8.setStyleClass("sbuBtnStyle");
    _jspx_th_gw_005fbutton_005f8.setIcon("saveIcon");
    _jspx_th_gw_005fbutton_005f8.setOnClick("goViewInfo()");
    int _jspx_eval_gw_005fbutton_005f8 = _jspx_th_gw_005fbutton_005f8.doStartTag();
    if (_jspx_eval_gw_005fbutton_005f8 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      if (_jspx_eval_gw_005fbutton_005f8 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.pushBody();
        _jspx_th_gw_005fbutton_005f8.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
        _jspx_th_gw_005fbutton_005f8.doInitBody();
      }
      do {
        out.write("运行状态");
        int evalDoAfterBody = _jspx_th_gw_005fbutton_005f8.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
      if (_jspx_eval_gw_005fbutton_005f8 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.popBody();
      }
    }
    if (_jspx_th_gw_005fbutton_005f8.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fgw_005fbutton_0026_005fstyleClass_005fonClick_005ficon.reuse(_jspx_th_gw_005fbutton_005f8);
      return true;
    }
    _005fjspx_005ftagPool_005fgw_005fbutton_0026_005fstyleClass_005fonClick_005ficon.reuse(_jspx_th_gw_005fbutton_005f8);
    return false;
  }

  private boolean _jspx_meth_html_005fhidden_005f0(javax.servlet.jsp.tagext.JspTag _jspx_th_html_005fform_005f0, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  html:hidden
    org.apache.struts.taglib.html.HiddenTag _jspx_th_html_005fhidden_005f0 = (org.apache.struts.taglib.html.HiddenTag) _005fjspx_005ftagPool_005fhtml_005fhidden_0026_005fproperty_005fnobody.get(org.apache.struts.taglib.html.HiddenTag.class);
    _jspx_th_html_005fhidden_005f0.setPageContext(_jspx_page_context);
    _jspx_th_html_005fhidden_005f0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_html_005fform_005f0);
    _jspx_th_html_005fhidden_005f0.setProperty("query.order");
    int _jspx_eval_html_005fhidden_005f0 = _jspx_th_html_005fhidden_005f0.doStartTag();
    if (_jspx_th_html_005fhidden_005f0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fhtml_005fhidden_0026_005fproperty_005fnobody.reuse(_jspx_th_html_005fhidden_005f0);
      return true;
    }
    _005fjspx_005ftagPool_005fhtml_005fhidden_0026_005fproperty_005fnobody.reuse(_jspx_th_html_005fhidden_005f0);
    return false;
  }

  private boolean _jspx_meth_html_005fhidden_005f1(javax.servlet.jsp.tagext.JspTag _jspx_th_html_005fform_005f0, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  html:hidden
    org.apache.struts.taglib.html.HiddenTag _jspx_th_html_005fhidden_005f1 = (org.apache.struts.taglib.html.HiddenTag) _005fjspx_005ftagPool_005fhtml_005fhidden_0026_005fproperty_005fnobody.get(org.apache.struts.taglib.html.HiddenTag.class);
    _jspx_th_html_005fhidden_005f1.setPageContext(_jspx_page_context);
    _jspx_th_html_005fhidden_005f1.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_html_005fform_005f0);
    _jspx_th_html_005fhidden_005f1.setProperty("query.orderDirection");
    int _jspx_eval_html_005fhidden_005f1 = _jspx_th_html_005fhidden_005f1.doStartTag();
    if (_jspx_th_html_005fhidden_005f1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fhtml_005fhidden_0026_005fproperty_005fnobody.reuse(_jspx_th_html_005fhidden_005f1);
      return true;
    }
    _005fjspx_005ftagPool_005fhtml_005fhidden_0026_005fproperty_005fnobody.reuse(_jspx_th_html_005fhidden_005f1);
    return false;
  }

  private boolean _jspx_meth_html_005fhidden_005f2(javax.servlet.jsp.tagext.JspTag _jspx_th_html_005fform_005f0, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  html:hidden
    org.apache.struts.taglib.html.HiddenTag _jspx_th_html_005fhidden_005f2 = (org.apache.struts.taglib.html.HiddenTag) _005fjspx_005ftagPool_005fhtml_005fhidden_0026_005fproperty_005fnobody.get(org.apache.struts.taglib.html.HiddenTag.class);
    _jspx_th_html_005fhidden_005f2.setPageContext(_jspx_page_context);
    _jspx_th_html_005fhidden_005f2.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_html_005fform_005f0);
    _jspx_th_html_005fhidden_005f2.setProperty("query.pageNumber");
    int _jspx_eval_html_005fhidden_005f2 = _jspx_th_html_005fhidden_005f2.doStartTag();
    if (_jspx_th_html_005fhidden_005f2.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fhtml_005fhidden_0026_005fproperty_005fnobody.reuse(_jspx_th_html_005fhidden_005f2);
      return true;
    }
    _005fjspx_005ftagPool_005fhtml_005fhidden_0026_005fproperty_005fnobody.reuse(_jspx_th_html_005fhidden_005f2);
    return false;
  }

  private boolean _jspx_meth_html_005fhidden_005f3(javax.servlet.jsp.tagext.JspTag _jspx_th_html_005fform_005f0, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  html:hidden
    org.apache.struts.taglib.html.HiddenTag _jspx_th_html_005fhidden_005f3 = (org.apache.struts.taglib.html.HiddenTag) _005fjspx_005ftagPool_005fhtml_005fhidden_0026_005fproperty_005fnobody.get(org.apache.struts.taglib.html.HiddenTag.class);
    _jspx_th_html_005fhidden_005f3.setPageContext(_jspx_page_context);
    _jspx_th_html_005fhidden_005f3.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_html_005fform_005f0);
    _jspx_th_html_005fhidden_005f3.setProperty("query.recordCount");
    int _jspx_eval_html_005fhidden_005f3 = _jspx_th_html_005fhidden_005f3.doStartTag();
    if (_jspx_th_html_005fhidden_005f3.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fhtml_005fhidden_0026_005fproperty_005fnobody.reuse(_jspx_th_html_005fhidden_005f3);
      return true;
    }
    _005fjspx_005ftagPool_005fhtml_005fhidden_0026_005fproperty_005fnobody.reuse(_jspx_th_html_005fhidden_005f3);
    return false;
  }

  private boolean _jspx_meth_html_005fhidden_005f4(javax.servlet.jsp.tagext.JspTag _jspx_th_html_005fform_005f0, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  html:hidden
    org.apache.struts.taglib.html.HiddenTag _jspx_th_html_005fhidden_005f4 = (org.apache.struts.taglib.html.HiddenTag) _005fjspx_005ftagPool_005fhtml_005fhidden_0026_005fproperty_005fnobody.get(org.apache.struts.taglib.html.HiddenTag.class);
    _jspx_th_html_005fhidden_005f4.setPageContext(_jspx_page_context);
    _jspx_th_html_005fhidden_005f4.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_html_005fform_005f0);
    _jspx_th_html_005fhidden_005f4.setProperty("query.pageCount");
    int _jspx_eval_html_005fhidden_005f4 = _jspx_th_html_005fhidden_005f4.doStartTag();
    if (_jspx_th_html_005fhidden_005f4.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fhtml_005fhidden_0026_005fproperty_005fnobody.reuse(_jspx_th_html_005fhidden_005f4);
      return true;
    }
    _005fjspx_005ftagPool_005fhtml_005fhidden_0026_005fproperty_005fnobody.reuse(_jspx_th_html_005fhidden_005f4);
    return false;
  }

  private boolean _jspx_meth_html_005fhidden_005f5(javax.servlet.jsp.tagext.JspTag _jspx_th_html_005fform_005f0, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  html:hidden
    org.apache.struts.taglib.html.HiddenTag _jspx_th_html_005fhidden_005f5 = (org.apache.struts.taglib.html.HiddenTag) _005fjspx_005ftagPool_005fhtml_005fhidden_0026_005fproperty_005fnobody.get(org.apache.struts.taglib.html.HiddenTag.class);
    _jspx_th_html_005fhidden_005f5.setPageContext(_jspx_page_context);
    _jspx_th_html_005fhidden_005f5.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_html_005fform_005f0);
    _jspx_th_html_005fhidden_005f5.setProperty("query.parameters(classId)");
    int _jspx_eval_html_005fhidden_005f5 = _jspx_th_html_005fhidden_005f5.doStartTag();
    if (_jspx_th_html_005fhidden_005f5.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fhtml_005fhidden_0026_005fproperty_005fnobody.reuse(_jspx_th_html_005fhidden_005f5);
      return true;
    }
    _005fjspx_005ftagPool_005fhtml_005fhidden_0026_005fproperty_005fnobody.reuse(_jspx_th_html_005fhidden_005f5);
    return false;
  }

  private boolean _jspx_meth_gw_005fgrid2_005f0(javax.servlet.jsp.tagext.JspTag _jspx_th_html_005fform_005f0, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  gw:grid2
    com.liusy.web.tag.GridTag _jspx_th_gw_005fgrid2_005f0 = (com.liusy.web.tag.GridTag) _005fjspx_005ftagPool_005fgw_005fgrid2_0026_005fwidth_005fstyleClass_005frowEventHandle_005fproperty_005fparameters_005fname_005ffixRows_005fcellSpacing_005fcellPadding.get(com.liusy.web.tag.GridTag.class);
    _jspx_th_gw_005fgrid2_005f0.setPageContext(_jspx_page_context);
    _jspx_th_gw_005fgrid2_005f0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_html_005fform_005f0);
    _jspx_th_gw_005fgrid2_005f0.setCellPadding("0");
    _jspx_th_gw_005fgrid2_005f0.setCellSpacing("0");
    _jspx_th_gw_005fgrid2_005f0.setWidth("100%");
    _jspx_th_gw_005fgrid2_005f0.setStyleClass("listTable");
    _jspx_th_gw_005fgrid2_005f0.setName("compareInfoForm");
    _jspx_th_gw_005fgrid2_005f0.setProperty("query.recordSet");
    _jspx_th_gw_005fgrid2_005f0.setParameters("query");
    _jspx_th_gw_005fgrid2_005f0.setRowEventHandle("false");
    _jspx_th_gw_005fgrid2_005f0.setFixRows("false");
    int _jspx_eval_gw_005fgrid2_005f0 = _jspx_th_gw_005fgrid2_005f0.doStartTag();
    if (_jspx_eval_gw_005fgrid2_005f0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      if (_jspx_eval_gw_005fgrid2_005f0 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.pushBody();
        _jspx_th_gw_005fgrid2_005f0.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
        _jspx_th_gw_005fgrid2_005f0.doInitBody();
      }
      do {
        out.write("\r\n");
        out.write("\t      \t\t\t\t\t\t <header style=\"\" height=\"27\"  />\r\n");
        out.write("    \t\t\t\t\t\t\t\t\t\t<column width=\"5%\" itemType=\"checkbox\" property=\"ID\" align=\"center\" selectAll=\"true\"  headerAlign=\"center\"/>\r\n");
        out.write("  \t\t\t\t\t\t\t\t\t\t\t  <column width=\"20%\" name=\"对比名称\" property=\"COMPARENAME\" align=\"center\" \r\n");
        out.write("    \t    \t\t\t\t\t\t\t      headerOnMouseOut=\"headerOut(this)\"\r\n");
        out.write("\t\t\t\t\t\t\t\t\t\t\t  headerOnClick=\"query('COMPARENAME')\"\r\n");
        out.write("\t\t\t\t\t\t\t\t\t\t\t  headerOnMouseOver=\"headerOver(this)\"/>\r\n");
        out.write("\t\t\t\t\t\t\t\t\t\t\t <column width=\"10%\" name=\"运行时间\" property=\"RUNTIME\" align=\"center\" \r\n");
        out.write("    \t    \t\t\t\t\t\t\t      headerOnMouseOut=\"headerOut(this)\"\r\n");
        out.write("\t\t\t\t\t\t\t\t\t\t\t  headerOnClick=\"query('RUNTIME')\"\r\n");
        out.write("\t\t\t\t\t\t\t\t\t\t\t  headerOnMouseOver=\"headerOver(this)\"/>\r\n");
        out.write("\t\t\t\t\t\t\t\t\t\t\t<column width=\"5%\" name=\"周期\" property=\"RUNCYCLE\" align=\"center\" \r\n");
        out.write("    \t    \t\t\t\t\t\t\t      headerOnMouseOut=\"headerOut(this)\"\r\n");
        out.write("\t\t\t\t\t\t\t\t\t\t\t  headerOnMouseOver=\"headerOver(this)\"/>\r\n");
        out.write("    \t\t\t\t\t\t\t\t\t\t<column width=\"5%\" name=\"状态\" property=\"STATUS\" align=\"center\" \r\n");
        out.write("    \t    \t\t\t\t\t\t\t      headerOnMouseOut=\"headerOut(this)\"\r\n");
        out.write("\t\t\t\t\t\t\t\t\t\t\t  headerOnMouseOver=\"headerOver(this)\"/>\r\n");
        out.write("\t\t\t\t\t\t\t\t\t\t\t<column width=\"10%\" name=\"创建时间\" property=\"CREATEDATE\" styleClass=\"td_LeftTop\"  headerStyleClass=\"td_LeftTop\"\r\n");
        out.write("    \t\t\t\t\t\t\t\t\t\t\t\t\theaderOnClick=\"query('createDate')\" \r\n");
        out.write("    \t\t\t\t\t\t\t\t\t\t\t\t\theaderOnMouseOver=\"headerOver(this)\" headerStyle=\"color:#000077\"\r\n");
        out.write("    \t\t\t\t\t\t\t\t\t\t\t\t\theaderOnMouseOut=\"headerOut(this)\"/>\r\n");
        out.write("\t\t\t\t\t\t\t\t\t\t\t<column width=\"50%\" name=\"说明\" property=\"REMARK\" align=\"center\" \r\n");
        out.write("    \t \t\t\t\t\t\t\t\t\t    headerOnClick=\"query('remark')\"   \r\n");
        out.write("\t\t\t\t\t\t\t\t\t\t\t\theaderOnMouseOut=\"headerOut(this)\"\r\n");
        out.write("\t\t\t\t\t\t\t\t\t\t\t\theaderOnMouseOver=\"headerOver(this)\"/>\r\n");
        out.write("    \t\t\t\t\t\t\t\t\t\t<rooter height=\"30\" width=\"100%\" showType=\"all\" />      \r\n");
        out.write("\t\t\t\t\t\t\t\t\t\t\t\t\r\n");
        out.write("\t\t\t\t\t\t\t\t\t\t\t\t");
        int evalDoAfterBody = _jspx_th_gw_005fgrid2_005f0.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
      if (_jspx_eval_gw_005fgrid2_005f0 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.popBody();
      }
    }
    if (_jspx_th_gw_005fgrid2_005f0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fgw_005fgrid2_0026_005fwidth_005fstyleClass_005frowEventHandle_005fproperty_005fparameters_005fname_005ffixRows_005fcellSpacing_005fcellPadding.reuse(_jspx_th_gw_005fgrid2_005f0);
      return true;
    }
    _005fjspx_005ftagPool_005fgw_005fgrid2_0026_005fwidth_005fstyleClass_005frowEventHandle_005fproperty_005fparameters_005fname_005ffixRows_005fcellSpacing_005fcellPadding.reuse(_jspx_th_gw_005fgrid2_005f0);
    return false;
  }
}
