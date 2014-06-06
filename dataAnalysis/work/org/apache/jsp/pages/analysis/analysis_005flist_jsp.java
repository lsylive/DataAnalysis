package org.apache.jsp.pages.analysis;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class analysis_005flist_jsp extends org.apache.jasper.runtime.HttpJspBase
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
      response.setContentType("text/html;charset=GBK");
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

  String ITEM_TYPE = (String)request.getAttribute("type");
	String path = request.getContextPath();
	String CONTEXT_PATH = request.getScheme() + "://"+ request.getServerName() + ":" + request.getServerPort()+ path + "/";

      out.write("\r\n");
      out.write("<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\">\r\n");
      out.write("<html xmlns=\"http://www.w3.org/1999/xhtml\">\r\n");
      out.write("<head>\r\n");
      out.write("<title>骞夸笢鐪佷俊鎭墍锛堟暟鎹簲鐢ㄧ郴缁燂級</title>\r\n");
      out.write("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\" />\r\n");
      out.write("<meta http-equiv=\"x-ua-compatible\" content=\"ie=7\" />\r\n");
      out.write("<link rel=\"stylesheet\" type=\"text/css\" href=\"");
      out.print(CONTEXT_PATH);
      out.write("dhtmlxSuite/dhtmlx/dhtmlx.css\">\r\n");
      out.write("<script src=\"");
      out.print(CONTEXT_PATH);
      out.write("dhtmlxSuite/dhtmlx/dhtmlx.js\"></script>\r\n");
      out.write("<script language=\"javascript\" src=\"");
      out.print( CONTEXT_PATH );
      out.write("/js/ctrl_util.js\"></script>\t\r\n");
      out.write("<style type=\"text/css\">\r\n");
      out.write("  html, body {\r\n");
      out.write("   \t  width:100%; \r\n");
      out.write("   \t  height:100%; \r\n");
      out.write("   \t  margin:0px; \r\n");
      out.write("   \t  padding:0px; \r\n");
      out.write("   \t  overflow:hidden;\r\n");
      out.write("  }\r\n");
      out.write("\r\n");
      out.write(" .ficon_item,.ficon_item_selected{\r\n");
      out.write(" \t   border:1px solid lightgrey;\r\n");
      out.write(" \t   width:100px;\r\n");
      out.write(" \t   float:left;\r\n");
      out.write(" \t   margin:5px; \r\n");
      out.write(" \t   cursor:default;\r\n");
      out.write(" \t   vertical-align:middle;\r\n");
      out.write(" }\r\n");
      out.write(" .ficon_item_text{\r\n");
      out.write(" \t   font-size:12px; \r\n");
      out.write(" \t   font-family: tahoma; \r\n");
      out.write(" \t   width:85px!important; \r\n");
      out.write(" \t   height:43px!important; \r\n");
      out.write(" \t   text-align:center!important;\r\n");
      out.write(" }\r\n");
      out.write(" .ficon_item_selected div{\r\n");
      out.write(" \t   background-color:#3366ff; \r\n");
      out.write(" \t   color:white;\r\n");
      out.write(" }\r\n");
      out.write("</style>\r\n");
      out.write("</head>\r\n");
      out.write("<body>\r\n");
      out.write("  <div id=\"winVP\" style=\"position:relative;top:0;left:0;width:100%;height:100%;z-index:100;\"></div>\r\n");
      out.write("<script type=\"text/javascript\">\r\n");
      out.write("\r\n");
      out.write("//椤甸潰鍒濆鍖栦唬鐮�\n");
      out.write("\t  var dhxLayout = new dhtmlXLayoutObject(document.body, \"1C\");\r\n");
      out.write("\t  dhxLayout.setSkin(\"dhx_skyblue\");\r\n");
      out.write("\r\n");
      out.write("\t  var leftPanel=dhxLayout.cells(\"a\");\r\n");
      out.write("\t  leftPanel.hideHeader();\r\n");
      out.write(" \r\n");
      out.write("//    var toolbar = dhxLayout.attachToolbar();\r\n");
      out.write("//    toolbar.setIconsPath(\"");
      out.print(CONTEXT_PATH);
      out.write("images/icon/\");\r\n");
      out.write("//    toolbar.addButton(\"add\", 0, \"鍒涘缓蹇嵎鏂瑰紡\", \"home--plus.gif\", \"home--plus.gif\");\r\n");
      out.write("//    toolbar.addButton(\"del\", 1, \"鍒犻櫎蹇嵎鏂瑰紡\", \"home--minus.gif\", \"home--minus.gif\");\r\n");
      out.write("//    toolbar.attachEvent(\"onClick\", function(id){\r\n");
      out.write("//    \tif(id==\"add\") addLink(id);\r\n");
      out.write("//    \telse if(id==\"del\") delLink(id);\r\n");
      out.write("//    });\r\n");
      out.write("\r\n");
      out.write("    var dhxFolders = leftPanel.attachFolders();\r\n");
      out.write("    dhxFolders.setItemType(\"ftiles\");\r\n");
      out.write("    dhxFolders.enableSelection(2);\r\n");
      out.write("\r\n");
      out.write("    dhxFolders.setUserData(\"icons_src_dir\", \"");
      out.print(CONTEXT_PATH);
      out.write("images/analysis\");\r\n");
      out.write("    dhxFolders.loadXML(\"");
      out.print(CONTEXT_PATH);
      out.write("analysis/analysis.do?action=Folder&type=");
      out.print(ITEM_TYPE);
      out.write("\", \"");
      out.print(CONTEXT_PATH);
      out.write("pages/analysis/analysis.xsl\");\r\n");
      out.write("    dhxFolders.attachEvent(\"ondblclick\", doOnDblClick);\r\n");
      out.write("\r\n");
      out.write("    var dhxWins = new dhtmlXWindows();\r\n");
      out.write("    dhxWins.enableAutoViewport(true);\r\n");
      out.write("    dhxWins.setImagePath(\"");
      out.print(CONTEXT_PATH);
      out.write("dhtmlxSuite/dhtmlxWindows/codebase/imgs/\");\r\n");
      out.write("    \r\n");
      out.write("    var winName=\"win\";  //鎵撳紑绐楀彛鍚嶇О\r\n");
      out.write("    var editMode;       //椤甸潰缂栬緫鐘舵�锛欰DD,EDIT,DELETE\r\n");
      out.write("\r\n");
      out.write("    function openWindow(title,urlink,w,h){\r\n");
      out.write("    \t var w = dhxWins.createWindow(winName, 0, 0, w, h);\r\n");
      out.write("       w.setText(title);\r\n");
      out.write("       w.keepInViewport(true);\r\n");
      out.write("       w.setModal(true);\r\n");
      out.write("       w.centerOnScreen();\r\n");
      out.write("       w.button(\"minmax1\").hide();\r\n");
      out.write("       w.button(\"minmax2\").hide();\r\n");
      out.write("       w.button(\"park\").hide();\r\n");
      out.write("       w.attachURL(urlink);\r\n");
      out.write("\r\n");
      out.write("       return w;    \r\n");
      out.write("    }\r\n");
      out.write("\r\n");
      out.write("    function doOnDblClick(itemId) {\r\n");
      out.write("      var xmlNode = dhxFolders.getItem(itemId).data.dataObj;\r\n");
      out.write("      var tempId=xmlNode.getAttribute(\"tempId\");\r\n");
      out.write("      var type=xmlNode.getAttribute(\"type\");\r\n");
      out.write("      var tempType=xmlNode.getAttribute(\"tempType\");\r\n");
      out.write("      \r\n");
      out.write("      window.location=\"");
      out.print(CONTEXT_PATH);
      out.write("analysis/analysis.do?action=query&tempId=\"+tempId+\"&type=\"+type+\"&tempType=\"+tempType;\r\n");
      out.write("    }\r\n");
      out.write("\r\n");
      out.write("</script>\r\n");
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
