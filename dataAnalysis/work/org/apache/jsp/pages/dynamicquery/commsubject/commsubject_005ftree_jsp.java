package org.apache.jsp.pages.dynamicquery.commsubject;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class commsubject_005ftree_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static java.util.List _jspx_dependants;

  public Object getDependants() {
    return _jspx_dependants;
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
      response.setContentType("text/html; charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;

      out.write("\r\n");
      out.write("<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\">\r\n");
      out.write("<html xmlns=\"http://www.w3.org/1999/xhtml\">\r\n");
String CONTEXT_PATH = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";
      out.write("\r\n");
      out.write("<head>\r\n");
      out.write("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\r\n");
      out.write("<title>资源目录查看</title>\r\n");
      out.write("<link rel=\"stylesheet\" type=\"text/css\" href=\"");
      out.print(CONTEXT_PATH);
      out.write("css/main.css\">\r\n");
      out.write("\t\t<link rel=\"stylesheet\" type=\"text/css\" href=\"");
      out.print(CONTEXT_PATH);
      out.write("dhtmlxSuite/dhtmlx/dhtmlx.css\">\r\n");
      out.write("\t\t\r\n");
      out.write("\t\t<script src=\"");
      out.print(CONTEXT_PATH);
      out.write("dhtmlxSuite/dhtmlx/dhtmlx.js\"></script>\r\n");
      out.write("\t\t<script language=\"javascript\" src=\"");
      out.print( CONTEXT_PATH );
      out.write("js/date_validate.js\"></script>\r\n");
      out.write("\t\t<script language=\"javascript\" src=\"");
      out.print( CONTEXT_PATH );
      out.write("js/ctrl_util.js\"></script>\r\n");
      out.write("\t\t\r\n");
      out.write("<script>\r\n");
      out.write("var win;\r\n");
      out.write("var contextpath = \"");
      out.print(CONTEXT_PATH);
      out.write("\";\t\r\n");
      out.write("var hyperlink = \"../query/commSubjectQuery.do\";\t\r\n");
      out.write("var fulllink = contextpath + \"/query/commSubjectQuery.do\";\r\n");
      out.write("var classifylink = contextpath + \"/query/classifySubjectQuery.do\";\r\n");
      out.write("var tablelink = contextpath + \"/dynamicquery/advancedquery.do\";\r\n");
      out.write("\t\t\t\r\n");
      out.write("function showquery(id){\t\r\n");
      out.write("\t\r\n");
      out.write("    var iscomm=id.indexOf('comm');\r\n");
      out.write("    var isclass=id.indexOf('buss');\r\n");
      out.write("    var isall = id.indexOf('all_');\r\n");
      out.write("    var isspacetable = id.indexOf(\"space_\");\r\n");
      out.write("    switch (id) {\r\n");
      out.write("\tcase \"spacetable\":case \"this_is_commons\":case \"this_is_categorys\":case \"this_is_alltables\":\r\n");
      out.write("\t\treturn;\r\n");
      out.write("\tdefault:\r\n");
      out.write("\t\tbreak;\r\n");
      out.write("\t}\r\n");
      out.write("\t\r\n");
      out.write("\tif(iscomm!=-1){\r\n");
      out.write("\t\tid=id.substr(4);\r\n");
      out.write("\t\tdhxLayout.cells(\"b\").attachURL(fulllink+\"?action1=showquery&id=\"+id);\r\n");
      out.write("\t}else if(isclass!=-1){\r\n");
      out.write("\t\tid=id.substr(4);\r\n");
      out.write("\t\tdhxLayout.cells(\"b\").attachURL(fulllink+\"?action1=showquery&cataId=\"+id);\r\n");
      out.write("\t}\r\n");
      out.write("\telse {\r\n");
      out.write("\t\tif(isspacetable!=-1){\r\n");
      out.write("\t\t\tid=id.substr(6);\r\n");
      out.write("\t\t}else if(isall!=-1){\r\n");
      out.write("\t\t\tid=id.substr(4);\r\n");
      out.write("\t\t}\r\n");
      out.write("\t\tvar urlink = tablelink + \"?action=TOADVANCEDPAGE&queryType=base&tableId=\" + id;\r\n");
      out.write("\t\t//alert(urlink);\r\n");
      out.write("\t\tdhxLayout.cells(\"b\").attachURL(urlink);\r\n");
      out.write("\t}\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("</script>\r\n");
      out.write("<style type=\"text/css\">\r\n");
      out.write("html, body {\r\n");
      out.write("        width: 100%;\r\n");
      out.write("        height: 100%;\r\n");
      out.write("        margin: 0px;\r\n");
      out.write("        overflow: hidden;\r\n");
      out.write("}\r\n");
      out.write("</style>\r\n");
      out.write("</head>\r\n");
      out.write("<body>\r\n");
      out.write("<div id=\"treepanel\" style=\"background-color:#e8f0f8;height:100%;\"></div>\r\n");
      out.write("<div id=\"openDiv\" style=\"position:relative;top:0;left:0;width:100%;height:100%;display: none;\">\r\n");
      out.write("  <iframe id=\"content\" frameborder=\"0\" name=\"content\" style=\"width: 100%;height: 100%;\"></iframe>\r\n");
      out.write("  </div>\r\n");
      out.write("<script>     \r\n");
      out.write("\t\t\r\n");
      out.write("        var dhxLayout = new dhtmlXLayoutObject(document.body, \"2U\");\r\n");
      out.write("       \tdhxLayout.setSkin(\"dhx_skyblue\");\r\n");
      out.write("       \tdhxLayout.setImagePath(\"");
      out.print(CONTEXT_PATH);
      out.write("/dhtmlxSuite/dhtmlxToolbar/codebase/imgs/dhxlayout_dhx_blue/\");\r\n");
      out.write("        dhxLayout.cells(\"a\").setText(\"分类\");\r\n");
      out.write("        dhxLayout.cells(\"a\").setWidth(200);\r\n");
      out.write("        dhxLayout.cells(\"a\").hideHeader();\r\n");
      out.write("        dhxLayout.cells(\"a\").attachObject(\"treepanel\");\r\n");
      out.write("        dhxLayout.cells(\"b\").hideHeader();\r\n");
      out.write("        \r\n");
      out.write("\r\n");
      out.write("       \r\n");
      out.write("        \r\n");
      out.write("        //树配置\r\n");
      out.write("        var tree=new dhtmlXTreeObject(\"treepanel\",\"100%\",\"100%\",0);\r\n");
      out.write("\t\ttree.setImagePath(\"");
      out.print(CONTEXT_PATH);
      out.write("/dhtmlxSuite/dhtmlx/imgs/\");\r\n");
      out.write("\t\ttree.setSkin('dhx_blue');\r\n");
      out.write("\t\ttree.enableCheckBoxes(0);\r\n");
      out.write("\t\ttree.setOnClickHandler(showquery);\r\n");
      out.write("\t\ttree.attachEvent(\"onMouseIn\", function(id){\r\n");
      out.write("\t    \t var str=tree.getUserData(id,\"name\");\r\n");
      out.write("\t    \t tree.setItemText(id,\"<font color='#FF4422'>\"+str+\"</font>\");\r\n");
      out.write("\t    });\r\n");
      out.write("\t    tree.attachEvent(\"onMouseOut\", function(id){\r\n");
      out.write("\t    \t var str=tree.getUserData(id,\"name\");\r\n");
      out.write("\t    \t tree.setItemText(id,str);\r\n");
      out.write("\t    });\r\n");
      out.write("\t \t\r\n");
      out.write("\t\ttree.setXMLAutoLoading(\"");
      out.print(CONTEXT_PATH);
      out.write("query/commSubjectQuery.do?action1=showtree\");\r\n");
      out.write("\t\ttree.loadXML(\"");
      out.print(CONTEXT_PATH);
      out.write("/query/commSubjectQuery.do?action1=showtree&tree=");
      out.print(request.getAttribute("tree"));
      out.write("&id=0\"); \r\n");
      out.write("\r\n");
      out.write("\t    //tabbar\r\n");
      out.write("\t   \r\n");
      out.write("\t    \r\n");
      out.write("\t\tvar wins = dhxLayout.dhxWins;\r\n");
      out.write("\t\tvar winName=\"win\";  //打开窗口名称\r\n");
      out.write("\t    var editMode;       //页面编辑状态：ADD,EDIT,DELETE\r\n");
      out.write("\t    wins.enableAutoViewport(true);\r\n");
      out.write("\t    wins.setImagePath(\"");
      out.print(CONTEXT_PATH);
      out.write("/dhtmlxSuite/dhtmlxWindows/codebase/imgs/\");\r\n");
      out.write("\t\t\r\n");
      out.write("\t    function openWindow(title,w,h){\r\n");
      out.write("\t       if(!win){\r\n");
      out.write("\t       win = wins.createWindow(winName, 0, 0, w, h);\r\n");
      out.write("\t       win.setText(title);\r\n");
      out.write("\t       win.keepInViewport(true);\r\n");
      out.write("\t       win.setModal(true);\r\n");
      out.write("\t       win.centerOnScreen();\r\n");
      out.write("\t       win.button(\"minmax1\").hide();\r\n");
      out.write("\t       win.button(\"minmax2\").hide();\r\n");
      out.write("\t       win.button(\"park\").hide();\r\n");
      out.write("\t        //win.button(\"close\").hide();\r\n");
      out.write("\t        win.button(\"close\").attachEvent(\"onClick\", closedialog);\r\n");
      out.write("\t       \r\n");
      out.write("\t       \r\n");
      out.write("\t       win.attachObject(\"openDiv\");\r\n");
      out.write("\t     \r\n");
      out.write("\t       }else{\r\n");
      out.write("\t       win.keepInViewport(true);\r\n");
      out.write("\t       win.setModal(true);\r\n");
      out.write("\t       win.centerOnScreen();\r\n");
      out.write("\t       \twins.window(winName).show();\r\n");
      out.write("\t       \t}\r\n");
      out.write("\t       return win;    \r\n");
      out.write("\t    }\r\n");
      out.write("\r\n");
      out.write("\t    \r\n");
      out.write("\t    function closedialog(){\r\n");
      out.write("\t    \tif(document.frames)\r\n");
      out.write("\t         \tdocument.frames[\"content\"].document.body.innerHTML='';\r\n");
      out.write("\t         else\r\n");
      out.write("\t          \tdocument.getElementById('content').contentDocument.body.innerHTML='';\r\n");
      out.write("\t         wins.window(winName).setModal(false);\r\n");
      out.write("\t\t     wins.window(winName).hide();\r\n");
      out.write("\t\t    \r\n");
      out.write("\t\t     \r\n");
      out.write("\t    }\r\n");
      out.write("\r\n");
      out.write("\t   \r\n");
      out.write("</script>\r\n");
      out.write("\r\n");
      out.write("</body>\r\n");
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
}
