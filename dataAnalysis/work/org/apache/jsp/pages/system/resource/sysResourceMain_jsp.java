package org.apache.jsp.pages.system.resource;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class sysResourceMain_jsp extends org.apache.jasper.runtime.HttpJspBase
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
String fullurl = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath();
      out.write("\r\n");
      out.write("<head>\r\n");
      out.write("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\r\n");
      out.write("<title>资源目录管理</title>\r\n");
      out.write("<link rel=\"stylesheet\" type=\"text/css\" href=\"");
      out.print(fullurl);
      out.write("/dhtmlxSuite/dhtmlx/dhtmlx.css\">\r\n");
      out.write("<script src=\"");
      out.print(fullurl);
      out.write("/dhtmlxSuite/dhtmlx/dhtmlx.js\"></script>\r\n");
      out.write("<link rel=\"stylesheet\" type=\"text/css\" href=\"");
      out.print(fullurl);
      out.write("/css/main.css\" />\r\n");
      out.write("<script>\r\n");
      out.write("\r\n");
      out.write("var contextpath = \"");
      out.print(fullurl);
      out.write("\";\t\r\n");
      out.write("var hyperlink = \"../system/ResourceTable.do\";\t\r\n");
      out.write("var fulllink = contextpath + \"/system/ResourceTable.do\";\r\n");
      out.write("function tonclick(id){\r\n");
      out.write("\t\tvar value = id;\r\n");
      out.write("\t\tif(value!=\"province\"){\t\t\t\t\t\r\n");
      out.write("\t\tdhxLayout.cells(\"b\").attachURL(fulllink+\"?treeid=\"+id+\"&action=\"+\"LIST\");\r\n");
      out.write("\t\t}else{\r\n");
      out.write("\t\t\tdhxLayout.cells(\"b\").attachURL(\"");
      out.print(fullurl);
      out.write("/dhtmlxSuite/dhtmlx/imgs/blank.html\");\r\n");
      out.write("\t\t}\r\n");
      out.write("\t\tshowMenuItems(id);\r\n");
      out.write("};\r\n");
      out.write("function tonopen(id,mode){\r\n");
      out.write("\treturn confirm(\"Do you want to \"+(mode>0?\"close\":\"open\")+\" node \"+tree.getItemText(id)+\"?\");\r\n");
      out.write("};\r\n");
      out.write("function goAdd()  {\r\n");
      out.write("\tvar nodeId = tree.getSelectedItemId();\r\n");
      out.write("\topenWindow(\"添加资源库\",fulllink+'?action=ADD&nodeId='+nodeId,800,500);\r\n");
      out.write("}\r\n");
      out.write("function goModify(){\r\n");
      out.write("var id = dhxLayout.cells(\"b\")._frame.contentWindow.getModifyId();\r\n");
      out.write("if (id==\"\") {\r\n");
      out.write("return;\r\n");
      out.write("}\r\n");
      out.write("var urlink=fulllink+'?action=TOEDIT&tableid='+id;\r\n");
      out.write("openWindow(\"修改资源库\",urlink,800,540);\r\n");
      out.write("};\r\n");
      out.write("\r\n");
      out.write("function goDelete(){\r\n");
      out.write("var ids = dhxLayout.cells(\"b\")._frame.contentWindow.getDelIds();\r\n");
      out.write("if (ids==\"\") {\r\n");
      out.write("return;\r\n");
      out.write("}\t\r\n");
      out.write("var res = confirm(\"是否真的要删除?\");\r\n");
      out.write("if(res == true) {\r\n");
      out.write("var innerdoc;\r\n");
      out.write("if (_isIE) {\r\n");
      out.write("\tinnerdoc = dhxLayout.cells(\"b\")._frame.contentWindow.document;\r\n");
      out.write("} else {\r\n");
      out.write("\tinnerdoc = dhxLayout.cells(\"b\")._frame.contentDocument;\r\n");
      out.write("}\r\n");
      out.write("innerdoc.forms[0].action = hyperlink + \"?action=delete&ids=\" + ids;\r\n");
      out.write("innerdoc.forms[0].target = \"_self\";\r\n");
      out.write("innerdoc.forms[0].submit();\r\n");
      out.write("}\r\n");
      out.write("};\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("function showMenuItems(id){\r\n");
      out.write("\tif(id==\"province\"){\r\n");
      out.write("\t\ttoolbar.disableItem(\"new_table\");\r\n");
      out.write("\t    toolbar.disableItem(\"modify_table\");\r\n");
      out.write("\t    toolbar.disableItem(\"del_tabel\");\r\n");
      out.write("\t}\r\n");
      out.write("\telse if(id.indexOf(\"_\")>-1){\r\n");
      out.write("\t\ttoolbar.enableItem(\"new_table\");\r\n");
      out.write("\t\ttoolbar.enableItem(\"modify_table\");\r\n");
      out.write("\t\ttoolbar.enableItem(\"del_tabel\");\r\n");
      out.write("\t}\r\n");
      out.write("\telse{\r\n");
      out.write("\t \ttoolbar.disableItem(\"new_table\");\r\n");
      out.write("\t \ttoolbar.enableItem(\"modify_table\");\r\n");
      out.write("\t \ttoolbar.enableItem(\"del_tabel\");\r\n");
      out.write("\t}\r\n");
      out.write("}\r\n");
      out.write("</script>\r\n");
      out.write("<style type=\"text/css\">\r\n");
      out.write("<!--\r\n");
      out.write("html, body {\r\n");
      out.write("        width: 100%;\r\n");
      out.write("        height: 100%;\r\n");
      out.write("        margin: 0px;\r\n");
      out.write("        overflow: hidden;\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write("-->\r\n");
      out.write("</style>\r\n");
      out.write("</head>\r\n");
      out.write("<body>\r\n");
      out.write("<div id=\"treepanel\" style=\"background-color:#e8f0f8;height:100%;\"></div>\r\n");
      out.write("<script>     \r\n");
      out.write("        var dhxLayout = new dhtmlXLayoutObject(document.body, \"2U\");\r\n");
      out.write("       \tdhxLayout.setSkin(\"dhx_skyblue\");\r\n");
      out.write("       \tdhxLayout.setImagePath(\"");
      out.print(fullurl);
      out.write("/dhtmlxSuite/dhtmlxToolbar/codebase/imgs/dhxlayout_dhx_blue/\");\r\n");
      out.write("        dhxLayout.cells(\"a\").setText(\"资源目录\");\r\n");
      out.write("        dhxLayout.cells(\"a\").setWidth(150);\r\n");
      out.write("        dhxLayout.cells(\"a\").hideHeader();\r\n");
      out.write("        dhxLayout.cells(\"a\").attachObject(\"treepanel\");\r\n");
      out.write("        dhxLayout.cells(\"b\").hideHeader();\r\n");
      out.write("        dhxLayout.cells(\"b\").attachURL(\"");
      out.print(fullurl);
      out.write("/dhtmlxSuite/dhtmlx/imgs/blank.html\");//.attachURL(\"");
      out.print(fullurl);
      out.write("/system/ResourceTable.do\");\r\n");
      out.write("\r\n");
      out.write("        var toolbar = dhxLayout.attachToolbar();\r\n");
      out.write("        toolbar.setIconsPath(\"");
      out.print(fullurl);
      out.write("/images/icon/\");\r\n");
      out.write("        toolbar.setSkin(\"dhx_skyblue\");\r\n");
      out.write("        toolbar.addButton(\"new_table\", 0, \"添加表\", \"database--plus.gif\", \"databases--plus.gif\");\r\n");
      out.write("        toolbar.setItemToolTip(\"new_table\", \"在分类下面添加资源库表\");\r\n");
      out.write("        toolbar.addSeparator(\"sep1\", 1);\t \r\n");
      out.write("        toolbar.addButton(\"modify_table\", 2, \"修改表\", \"database--pencil.gif\", \"database--pencil.gif\");\r\n");
      out.write("        toolbar.setItemToolTip(\"modify_table\", \"修改资源库表信息和该表下对应的字段信息\");\r\n");
      out.write("        toolbar.addSeparator(\"sep3\", 3);\r\n");
      out.write("        toolbar.addButton(\"del_tabel\", 4, \"删除表\", \"databases--minus.gif\", \"databases--minus.gif\");\r\n");
      out.write("        toolbar.setItemToolTip(\"del_tabel\", \"删除资源库表\");\r\n");
      out.write("        //toolbar.addButton(\"viwe_theme\", 5, \"查看公共目录\", \"door-open.png\", \"door-open.png\");\r\n");
      out.write("        //toolbar.setItemToolTip(\"viwe_theme\", \"查看公共目录\");\r\n");
      out.write("        toolbar.disableItem(\"new_table\");\r\n");
      out.write("        toolbar.disableItem(\"modify_table\");\r\n");
      out.write("        toolbar.disableItem(\"del_tabel\");\r\n");
      out.write("        toolbar.attachEvent(\"onClick\", function(id){\r\n");
      out.write("        \tif(id==\"new_table\"){\r\n");
      out.write("            \tgoAdd();\r\n");
      out.write("            }\r\n");
      out.write("        \telse if(id==\"modify_table\") {\r\n");
      out.write("            \tgoModify();\r\n");
      out.write("            }\r\n");
      out.write("        \telse if(id==\"del_tabel\") {\r\n");
      out.write("            \tgoDelete();\r\n");
      out.write("        \t}\r\n");
      out.write("        \telse if(id==\"viwe_theme\") {\r\n");
      out.write("        \t\topenWindow(\"查看公共目录\",\"\",800,450);\r\n");
      out.write("        \t}\r\n");
      out.write("        });\r\n");
      out.write("        \r\n");
      out.write("        \r\n");
      out.write("        var tree=new dhtmlXTreeObject(\"treepanel\",\"100%\",\"100%\",0);\r\n");
      out.write("\t\ttree.setImagePath(\"");
      out.print(fullurl);
      out.write("/dhtmlxSuite/dhtmlx/imgs/\");\r\n");
      out.write("\t\ttree.setSkin(\"dhx_blue\");\r\n");
      out.write("\t\ttree.enableCheckBoxes(0);\r\n");
      out.write("\t\ttree.setOnClickHandler(tonclick);\r\n");
      out.write("\t\ttree.attachEvent(\"onMouseIn\", function(id){\r\n");
      out.write("\t    \t var str=tree.getUserData(id,\"name\");\r\n");
      out.write("\t    \t tree.setItemText(id,\"<font color='#FF4422'>\"+str+\"</font>\");\r\n");
      out.write("\t    });\r\n");
      out.write("\t    tree.attachEvent(\"onMouseOut\", function(id){\r\n");
      out.write("\t    \t var str=tree.getUserData(id,\"name\");\r\n");
      out.write("\t    \t tree.setItemText(id,str);\r\n");
      out.write("\t    });\r\n");
      out.write("\t\t//tree.setXMLAutoLoading(\"");
      out.print(fullurl);
      out.write("/system/ResourceTable.do\");\r\n");
      out.write("\t\ttree.loadXML(\"");
      out.print(fullurl);
      out.write("/system/ResourceTable.do?action=TREE\"); \r\n");
      out.write("\r\n");
      out.write("\t\r\n");
      out.write("\t\t//var wins = dhxLayout.dhxWins;\r\n");
      out.write("\t\tvar wins = new dhtmlXWindows();\r\n");
      out.write("\t\tvar winName=\"win\";  \r\n");
      out.write("\t    var editMode;      \r\n");
      out.write("\t    wins.enableAutoViewport(true);\r\n");
      out.write("\t    wins.setImagePath(\"");
      out.print(fullurl);
      out.write("dhtmlxSuite/dhtmlxWindows/codebase/imgs/\");\r\n");
      out.write("\r\n");
      out.write("\t    function openWindow(title,urlink,width,height){\r\n");
      out.write("\t       var w = wins.createWindow(winName, 0, 0, width,height);\r\n");
      out.write("\t       w.attachURL(urlink);\r\n");
      out.write("\t       w.setText(title);\r\n");
      out.write("\t       w.keepInViewport(true);\r\n");
      out.write("\t      // w.denyResize();\r\n");
      out.write("\t       w.setModal(true);\r\n");
      out.write("\t       w.centerOnScreen();\r\n");
      out.write("\t       w.button(\"minmax1\").hide();\r\n");
      out.write("\t       w.button(\"minmax2\").hide();\r\n");
      out.write("\t       w.button(\"park\").hide();\r\n");
      out.write("\t       w.button(\"close\").attachEvent(\"onClick\", function(){closedialog();});\r\n");
      out.write("\t    }\r\n");
      out.write("\t    \r\n");
      out.write("\t    function closedialog(){\r\n");
      out.write("\t\t     wins.window(winName).close();\r\n");
      out.write("\t\t     var treeid = tree.getSelectedItemId();\r\n");
      out.write("\t\t     reSearch(treeid);\r\n");
      out.write("\t\t     return treeid;\r\n");
      out.write("\t    }\r\n");
      out.write("\r\n");
      out.write("\t    function closedialognofresh(){\r\n");
      out.write("\t    \twins.window(winName).close();\r\n");
      out.write("\t\t}\r\n");
      out.write("\t    function reSearch(treeid){\r\n");
      out.write("\t    \tdhxLayout.cells(\"b\")._frame.contentWindow.gosearch();\r\n");
      out.write("\t\t\t//dhxLayout.cells(\"b\")._frame.contentWindow.renew(treeid);\r\n");
      out.write("\t\t}\r\n");
      out.write("\r\n");
      out.write("\t\tfunction setSelectTreeNode(nodeid){\t\t\t\r\n");
      out.write("\t\t\ttree.selectItem(nodeid);\r\n");
      out.write("\t\t}\r\n");
      out.write("\r\n");
      out.write("\t\tfunction setWindowsTitle(title){\r\n");
      out.write("\t\t\twins.window(winName).setText(title);\r\n");
      out.write("\t\t}\r\n");
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
