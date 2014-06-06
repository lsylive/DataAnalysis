package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class main_005fnew_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static java.util.List _jspx_dependants;

  static {
    _jspx_dependants = new java.util.ArrayList(1);
    _jspx_dependants.add("/WEB-INF/config/tag/gw-tag.tld");
  }

  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005fgw_005fmenu2_0026_005ftitle_005fimg_005fid_005fcode;

  public Object getDependants() {
    return _jspx_dependants;
  }

  public void _jspInit() {
    _005fjspx_005ftagPool_005fgw_005fmenu2_0026_005ftitle_005fimg_005fid_005fcode = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
  }

  public void _jspDestroy() {
    _005fjspx_005ftagPool_005fgw_005fmenu2_0026_005ftitle_005fimg_005fid_005fcode.release();
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

	String path = request.getContextPath();
	String CONTEXT_PATH = request.getScheme() + "://"+ request.getServerName() + ":" + request.getServerPort()+ path + "/";

      out.write("\r\n");
      out.write("<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\">\r\n");
      out.write("<html xmlns=\"http://www.w3.org/1999/xhtml\">\r\n");
      out.write("<head>\r\n");
      out.write("<title>数据应用系统</title>\r\n");
      out.write("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\" />\r\n");
      out.write("<meta http-equiv=\"x-ua-compatible\" content=\"ie=7\" />\r\n");
      out.write("<link rel=\"stylesheet\" type=\"text/css\" href=\"");
      out.print(CONTEXT_PATH);
      out.write("dhtmlxSuite/dhtmlx/dhtmlx.css\" />\r\n");
      out.write("<link rel=\"stylesheet\" type=\"text/css\" href=\"");
      out.print(CONTEXT_PATH);
      out.write("dhtmlxSuite/dhtmlxMenu/codebase/skins/dhtmlxmenu_dhx_blue.css\"/>\r\n");
      out.write("<script src=\"");
      out.print(CONTEXT_PATH);
      out.write("dhtmlxSuite/dhtmlx/dhtmlx.js\"></script>\r\n");
      out.write("<link rel=\"stylesheet\" type=\"text/css\" href=\"");
      out.print(CONTEXT_PATH);
      out.write("css/reset.css\"/>\r\n");
      out.write("<link rel=\"stylesheet\" type=\"text/css\" href=\"");
      out.print(CONTEXT_PATH);
      out.write("css/menus.css\"/>\r\n");
      out.write("<script language=\"javascript\" src=\"");
      out.print( CONTEXT_PATH );
      out.write("js/ctrl_util.js\"></script>\r\n");
      out.write("<style type=\"text/css\">\r\n");
      out.write("html, body {\r\n");
      out.write("\twidth: 100%;\r\n");
      out.write("\theight: 100%;\r\n");
      out.write("\tborder:none;\r\n");
      out.write("\toverflow: hidden;\r\n");
      out.write("}\r\n");
      out.write("/* --------------以下为新增css---------------- */\r\n");
      out.write("\r\n");
      out.write("#sysName {\r\n");
      out.write("\tbackground:url(");
      out.print(CONTEXT_PATH);
      out.write("images/top1.jpg) no-repeat 0px 1px;\r\n");
      out.write("\tcolor:white;\r\n");
      out.write("\tfont-size:28px;\r\n");
      out.write("\theight:64px;\r\n");
      out.write("}\r\n");
      out.write("#topBanner {\r\n");
      out.write("\tbackground:url(");
      out.print(CONTEXT_PATH);
      out.write("images/headerBg.jpg) repeat-x center top;\r\n");
      out.write("\theight:64px;\r\n");
      out.write("\tposition:relative;\r\n");
      out.write("}\r\n");
      out.write("#header {\r\n");
      out.write("\twidth:100%;\r\n");
      out.write("\tzoom:1;\r\n");
      out.write("}\r\n");
      out.write("#hInput {\r\n");
      out.write("\tposition:absolute;\r\n");
      out.write("\tright:0;\r\n");
      out.write("\ttop:0;\r\n");
      out.write("\tbackground: url(");
      out.print(CONTEXT_PATH);
      out.write("images/headerRBg.jpg) no-repeat right top;\r\n");
      out.write("\theight:64px;\r\n");
      out.write("\twidth:366px;\r\n");
      out.write("\ttext-align:right;\r\n");
      out.write("\tline-height:64px;\r\n");
      out.write("}\r\n");
      out.write("#logSate {\r\n");
      out.write("\tposition:absolute;\r\n");
      out.write("\tleft:1px;\r\n");
      out.write("\tbottom:0px;\r\n");
      out.write("\t_top:44px;\r\n");
      out.write("\tpadding:2px 0 0 42px;\r\n");
      out.write("\tcolor:#ecfaff;\r\n");
      out.write("\tbackground:url(");
      out.print(CONTEXT_PATH);
      out.write("images/loginIFBg.jpg) no-repeat left top;\r\n");
      out.write("\twidth:100%;\r\n");
      out.write("}\r\n");
      out.write("#header .headerBtn {\r\n");
      out.write("\tcursor:pointer;\r\n");
      out.write("\tbackground:url(");
      out.print(CONTEXT_PATH);
      out.write("images/icon_list.gif) no-repeat right;\r\n");
      out.write("\twidth:50px;\r\n");
      out.write("\theight:18px;\r\n");
      out.write("\tline-height:18px;\r\n");
      out.write("\tborder:none;\r\n");
      out.write("\tmargin-right:3px;\r\n");
      out.write("\tmargin-top:44px;\r\n");
      out.write("\tfont-size:12px;\r\n");
      out.write("\tcolor:#fff;\r\n");
      out.write("\tpadding:2px 0 0 0px;\r\n");
      out.write("\ttext-align:right;\r\n");
      out.write("}\r\n");
      out.write("#header #topHome {\r\n");
      out.write("\tbackground-position: -90px -151px;\r\n");
      out.write("}\r\n");
      out.write("#header #topHelp {\r\n");
      out.write("\tbackground-position: -90px -100px;\r\n");
      out.write("}\r\n");
      out.write("#header #topExit {\r\n");
      out.write("\tbackground-position: -90px 2px;\r\n");
      out.write("}\r\n");
      out.write("#header .headerBtn:hover {\r\n");
      out.write("\tcolor:#e3f3f8\r\n");
      out.write("}\r\n");
      out.write(".memuNavDivWrap {\r\n");
      out.write("\tposition:relative;\r\n");
      out.write("\ttop:0;\r\n");
      out.write("\tleft:0;\r\n");
      out.write("\twidth:100%;\r\n");
      out.write("\theight:100%;\r\n");
      out.write("\tbackground:#f0f7ff url(");
      out.print(CONTEXT_PATH);
      out.write("images/nav_bottom_bg.jpg) repeat-y 1px center;\r\n");
      out.write("\t/* 滚动条代码S */\r\n");
      out.write("\toverflow:auto;/* 滚动条 */\r\n");
      out.write("\tscrollbar-face-color:#cae1fc;/* 滚动条颜色 */\r\n");
      out.write("\tscrollbar-track-color:#e9f2fe;/* 底色 */\r\n");
      out.write("\tscrollbar-arrow-color:#2b5589;/* 箭头颜色 */\r\n");
      out.write("\tscrollbar-highlight-color:#f6faff;/* 左次阴影 */\r\n");
      out.write("\tscrollbar-3dlight-color:#acc9e9;/* 左外阴影 */\r\n");
      out.write("\tscrollbar-shadow-color:#b0cbe8;/* 右次阴影 */\r\n");
      out.write("\tscrollbar-darkshadow-color:#e6f0fa;/* 右外阴影 *//* 滚动条代码E */\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write(".memuNav {\r\n");
      out.write("\tlist-style:none;\r\n");
      out.write("\tmargin:0;\r\n");
      out.write("\tpadding:0;\r\n");
      out.write("\tborder:1px solid #fff;\r\n");
      out.write("\tborder-bottom:1px solid #fff;\r\n");
      out.write("\tborder-top: none;\r\n");
      out.write("}\r\n");
      out.write(".memuNav li {\r\n");
      out.write("\ttext-align:center;\r\n");
      out.write("\theight:26px;\r\n");
      out.write("\tline-height:26px;\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write(".memuNav li a {\r\n");
      out.write("\tcolor:#093978;\r\n");
      out.write("\ttext-decoration:none;\r\n");
      out.write("\tdisplay:block;\r\n");
      out.write("\theight:26px;\r\n");
      out.write("\tline-height:26px;\r\n");
      out.write("\tbackground:#e4f0fe url(");
      out.print(CONTEXT_PATH);
      out.write("images/leftNavSBg.gif) repeat-x center top;\r\n");
      out.write("}\r\n");
      out.write(".memuNav li a:hover {\r\n");
      out.write("\tbackground:#fff0c8 url(");
      out.print(CONTEXT_PATH);
      out.write("images/leftNavSBg_hover.gif) repeat-x center bottom;\r\n");
      out.write("\tcolor:#9e5e2a;\r\n");
      out.write("\tletter-spacing:-1px;\r\n");
      out.write("\tfont-weight:bold;\r\n");
      out.write("\tborder-top:1px solid #fff;\r\n");
      out.write("\ttext-indent:2px;\r\n");
      out.write("\t_height:25px;\r\n");
      out.write("\t_line-height:25px;\r\n");
      out.write("}\r\n");
      out.write("#container {\r\n");
      out.write("\twidth:100%;\r\n");
      out.write("\theight:100%;\r\n");
      out.write("\tzoom:1;\r\n");
      out.write("\toverflow:hidden;\r\n");
      out.write("\tposition:absolute;\r\n");
      out.write("}\r\n");
      out.write("</style>\r\n");
      out.write("<script type=\"text/javascript\">\r\n");
      out.write("\t var contextpath = \"");
      out.print(CONTEXT_PATH);
      out.write("\";\t\r\n");
      out.write("\t var imgPath=\"");
      out.print(CONTEXT_PATH);
      out.write("dhtmlxSuite/dhtmlx/imgs/icon/\";\r\n");
      out.write("   function go(link,title,fName){\r\n");
      out.write("   \t  var f=\"leaf.gif\";\r\n");
      out.write("   \t  if(fName) f=fName;\r\n");
      out.write("      var img=\"&lt;img src='\"+imgPath+f+\"' /&gt;\";  \r\n");
      out.write("\r\n");
      out.write("      var t=\"&lt;font style='font-size:12px; font-weight:bold'&gt;\"+title+\"&lt;/font&gt;\";\r\n");
      out.write("      dhxToolbar.removeItem(\"img\");\r\n");
      out.write("      dhxToolbar.removeItem(\"title\");\r\n");
      out.write("   \t  dhxToolbar.addText(\"img\", 0,img);\r\n");
      out.write("   \t  dhxToolbar.addText(\"title\", 1,t);\r\n");
      out.write("   \t  //centerPanel.showToolbar();\r\n");
      out.write("      var cont=window.document.getElementById('container');\r\n");
      out.write("      cont.src=contextpath+link;\r\n");
      out.write("   }\r\n");
      out.write("   function changetitle(title){\r\n");
      out.write("   \t  var t=\"&lt;font style='font-size:12px; font-weight:bold'&gt;\"+title+\"&lt;/font&gt;\";\r\n");
      out.write("      dhxToolbar.removeItem(\"title\");\r\n");
      out.write("   \t  dhxToolbar.addText(\"title\", 0,t);\r\n");
      out.write("   }\r\n");
      out.write("   function showmain(){\r\n");
      out.write("   \t\twindow.location.href='");
      out.print(CONTEXT_PATH);
      out.write("main.jsp';\r\n");
      out.write("   }\r\n");
      out.write("   function logout(){\r\n");
      out.write("   \tif(confirm('是否真的要注销并重新登录?')==true)\r\n");
      out.write("   \t\twindow.location.href='");
      out.print(CONTEXT_PATH);
      out.write("user/logout.do';\r\n");
      out.write("   }\r\n");
      out.write("   function checklogin(){\r\n");
      out.write("   \t\tvar url='");
      out.print(CONTEXT_PATH);
      out.write("user/login.do?action=checklogin';\r\n");
      out.write("   \t\tvar loader=dhtmlxAjax.postSync(url,'');\r\n");
      out.write("   \t\tvar value = loader.xmlDoc.responseText;\r\n");
      out.write("   \t\tif(value!='OK'){\r\n");
      out.write("   \t\t\talert('您还未登录系统');\r\n");
      out.write("   \t\t\twindow.location.href='login.jsp';\r\n");
      out.write("   \t\t}\r\n");
      out.write("   }\r\n");
      out.write("\r\n");
      out.write("</script>\r\n");
      out.write("</head>\r\n");
      out.write("<body onload=\"checklogin()\">\r\n");
      out.write("<div id=\"topBanner\" style=\"display:none;\">\r\n");
      out.write("    <h1 id=\"sysName\">&nbsp;数据综合分析系统</h1>\r\n");
      out.write("    <div id=\"header\">\r\n");
      out.write("        <div id=\"logSate\">当前用户：<span>");
      out.print(request.getAttribute("userName"));
      out.write('(');
      out.print(request.getAttribute("userAccount"));
      out.write(")</span></div>\r\n");
      out.write("        <div id=\"hInput\">\r\n");
      out.write("            <button name=\"topPage\" id=\"topHome\"  type=\"button\" onClick=\"\" class=\"headerBtn\">首页</button>\r\n");
      out.write("            <button id=\"topHelp\" type=\"button\" onClick=\"\"  class=\"headerBtn\">帮助</button>\r\n");
      out.write("            <button id=\"topExit\" type=\"button\" onClick=\"logout()\"  class=\"headerBtn\">退出</button>\r\n");
      out.write("        </div>\r\n");
      out.write("    </div>\r\n");
      out.write("    <div id=\"sysMenu\" backgroundcolor=\"red\">测试菜单</div>\r\n");
      out.write("</div>\r\n");
      out.write("<script type=\"text/javascript\">\r\n");
      out.write("\t var dhxLayout = new dhtmlXLayoutObject(document.body, \"2E\");\r\n");
      out.write("\t dhxLayout.setSkin(\"dhx_skyblue\");\r\n");
      out.write("\t var topPanel=dhxLayout.cells(\"a\");\r\n");
      out.write("\t var centerPanel=dhxLayout.cells(\"b\");\r\n");
      out.write("\r\n");
      out.write("\t topPanel.setHeight(97);\r\n");
      out.write("\t topPanel.hideHeader();\r\n");
      out.write("\t topPanel.fixSize(true,true);\r\n");
      out.write("\t topPanel.attachObject(\"topBanner\");\r\n");
      out.write("\t \r\n");
      out.write("\t var menu = new dhtmlXMenuObject(\"sysMenu\",\"dhx_blue\");\r\n");
      out.write("   var menustr=\"<menu>\";\r\n");
      out.write("   \r\n");
      out.write("    ");
      if (_jspx_meth_gw_005fmenu2_005f0(_jspx_page_context))
        return;
      out.write("\r\n");
      out.write("    ");
      if (_jspx_meth_gw_005fmenu2_005f1(_jspx_page_context))
        return;
      out.write("    \r\n");
      out.write("\r\n");
      out.write("    ");
      if (_jspx_meth_gw_005fmenu2_005f2(_jspx_page_context))
        return;
      out.write("\r\n");
      out.write("    ");
      if (_jspx_meth_gw_005fmenu2_005f3(_jspx_page_context))
        return;
      out.write("\r\n");
      out.write("\r\n");
      out.write("    ");
      if (_jspx_meth_gw_005fmenu2_005f4(_jspx_page_context))
        return;
      out.write("\r\n");
      out.write("   menustr+=\"</menu>\";\r\n");
      out.write("   menu.loadXMLString(menustr);\r\n");
      out.write("   menu.attachEvent(\"onClick\", menuclick);\r\n");
      out.write("   \r\n");
      out.write("   function menuclick(id){\r\n");
      out.write("   \teval(menu.getUserData(id,\"onclick\"));\r\n");
      out.write("   } \r\n");
      out.write("\r\n");
      out.write("</script>\r\n");
      out.write("<div id=\"mainFrame\">\r\n");
      out.write("    <iframe id=\"container\" style=\"overflow-y:auto;overflow-x:hidden!important;width:100%;height:100%;\" frameborder=\"no\" src=\"../blank.jsp\" ></iframe>\r\n");
      out.write("</div>\r\n");
      out.write("<script type=\"text/javascript\">\r\n");
      out.write("\t centerPanel.hideHeader();\r\n");
      out.write("\t centerPanel.attachObject(\"mainFrame\");\r\n");
      out.write("   var dhxToolbar = centerPanel.attachToolbar();\r\n");
      out.write("   dhxToolbar.addText(\"title\", 0,\"\");\r\n");
      out.write("   centerPanel.hideToolbar();\r\n");
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

  private boolean _jspx_meth_gw_005fmenu2_005f0(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  gw:menu2
    com.liusy.web.tag.MenuTag2 _jspx_th_gw_005fmenu2_005f0 = (com.liusy.web.tag.MenuTag2) _005fjspx_005ftagPool_005fgw_005fmenu2_0026_005ftitle_005fimg_005fid_005fcode.get(com.liusy.web.tag.MenuTag2.class);
    _jspx_th_gw_005fmenu2_005f0.setPageContext(_jspx_page_context);
    _jspx_th_gw_005fmenu2_005f0.setParent(null);
    _jspx_th_gw_005fmenu2_005f0.setCode("A03");
    _jspx_th_gw_005fmenu2_005f0.setId("div2");
    _jspx_th_gw_005fmenu2_005f0.setTitle("综合查询");
    _jspx_th_gw_005fmenu2_005f0.setImg("../css/navImg/search.gif");
    int _jspx_eval_gw_005fmenu2_005f0 = _jspx_th_gw_005fmenu2_005f0.doStartTag();
    if (_jspx_eval_gw_005fmenu2_005f0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      if (_jspx_eval_gw_005fmenu2_005f0 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.pushBody();
        _jspx_th_gw_005fmenu2_005f0.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
        _jspx_th_gw_005fmenu2_005f0.doInitBody();
      }
      do {
        out.write("\r\n");
        out.write("       <item code=\"A0301\" onclick=\"go('query/commSubjectQuery.do?tree=comm','普通查询','code_child.gif')\" text=\"普通查询\" img=\"../css/navImg/1-1.gif\"/>\r\n");
        out.write("       <item code=\"A0302\" onclick=\"go('query/commSubjectQuery.do?tree=all','复合查询','code_all.gif')\" text=\"复合查询\" img=\"../css/navImg/1-2.gif\"/>\r\n");
        out.write("       <item code=\"A0304\" onclick=\"go('dynamicquery/searchEngine.do','全文检索查询','code_all.gif')\" text=\"全文检索查询\" img=\"../css/navImg/1-2.gif\"/>\r\n");
        out.write("    ");
        int evalDoAfterBody = _jspx_th_gw_005fmenu2_005f0.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
      if (_jspx_eval_gw_005fmenu2_005f0 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.popBody();
      }
    }
    if (_jspx_th_gw_005fmenu2_005f0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fgw_005fmenu2_0026_005ftitle_005fimg_005fid_005fcode.reuse(_jspx_th_gw_005fmenu2_005f0);
      return true;
    }
    _005fjspx_005ftagPool_005fgw_005fmenu2_0026_005ftitle_005fimg_005fid_005fcode.reuse(_jspx_th_gw_005fmenu2_005f0);
    return false;
  }

  private boolean _jspx_meth_gw_005fmenu2_005f1(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  gw:menu2
    com.liusy.web.tag.MenuTag2 _jspx_th_gw_005fmenu2_005f1 = (com.liusy.web.tag.MenuTag2) _005fjspx_005ftagPool_005fgw_005fmenu2_0026_005ftitle_005fimg_005fid_005fcode.get(com.liusy.web.tag.MenuTag2.class);
    _jspx_th_gw_005fmenu2_005f1.setPageContext(_jspx_page_context);
    _jspx_th_gw_005fmenu2_005f1.setParent(null);
    _jspx_th_gw_005fmenu2_005f1.setCode("A04");
    _jspx_th_gw_005fmenu2_005f1.setId("div3");
    _jspx_th_gw_005fmenu2_005f1.setTitle("数据分析");
    _jspx_th_gw_005fmenu2_005f1.setImg("../css/navImg/data.gif");
    int _jspx_eval_gw_005fmenu2_005f1 = _jspx_th_gw_005fmenu2_005f1.doStartTag();
    if (_jspx_eval_gw_005fmenu2_005f1 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      if (_jspx_eval_gw_005fmenu2_005f1 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.pushBody();
        _jspx_th_gw_005fmenu2_005f1.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
        _jspx_th_gw_005fmenu2_005f1.doInitBody();
      }
      do {
        out.write("\r\n");
        out.write("    <!--        <item code=\"A0401\" onclick=\"go('analysis/analysis.do?action=Link','常用查询')\" text=\"常用查询\" img=\"../css/navImg/3-1.gif\" />-->\r\n");
        out.write("            <item code=\"A0402\" onclick=\"go('analysis/analysis.do?action=Communication','通讯记录分析')\" text=\"通讯记录分析\" img=\"../css/navImg/3-2.gif\" />\r\n");
        out.write("            <item code=\"A0403\" onclick=\"go('analysis/analysis.do?action=Accommodation','住宿记录分析')\" text=\"住宿记录分析\" img=\"../css/navImg/3-3.gif\" />\r\n");
        out.write("            <item code=\"A0404\" onclick=\"go('analysis/analysis.do?action=Border','出入境记录分析')\" text=\"出入境记录分析\" img=\"../css/navImg/3-4.gif\" />\r\n");
        out.write("            <item code=\"A0405\" onclick=\"go('analysis/analysis.do?action=Flying','飞行记录分析')\" text=\"飞行记录分析\" img=\"../css/navImg/3-5.gif\" />\r\n");
        out.write("            <item code=\"A0406\" onclick=\"go('analysis/analysis.do?action=Composite','综合关联分析')\" text=\"综合关联分析\" img=\"../css/navImg/3-6.gif\" />\r\n");
        out.write("    ");
        int evalDoAfterBody = _jspx_th_gw_005fmenu2_005f1.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
      if (_jspx_eval_gw_005fmenu2_005f1 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.popBody();
      }
    }
    if (_jspx_th_gw_005fmenu2_005f1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fgw_005fmenu2_0026_005ftitle_005fimg_005fid_005fcode.reuse(_jspx_th_gw_005fmenu2_005f1);
      return true;
    }
    _005fjspx_005ftagPool_005fgw_005fmenu2_0026_005ftitle_005fimg_005fid_005fcode.reuse(_jspx_th_gw_005fmenu2_005f1);
    return false;
  }

  private boolean _jspx_meth_gw_005fmenu2_005f2(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  gw:menu2
    com.liusy.web.tag.MenuTag2 _jspx_th_gw_005fmenu2_005f2 = (com.liusy.web.tag.MenuTag2) _005fjspx_005ftagPool_005fgw_005fmenu2_0026_005ftitle_005fimg_005fid_005fcode.get(com.liusy.web.tag.MenuTag2.class);
    _jspx_th_gw_005fmenu2_005f2.setPageContext(_jspx_page_context);
    _jspx_th_gw_005fmenu2_005f2.setParent(null);
    _jspx_th_gw_005fmenu2_005f2.setCode("A07");
    _jspx_th_gw_005fmenu2_005f2.setId("div6");
    _jspx_th_gw_005fmenu2_005f2.setTitle("对比查询");
    _jspx_th_gw_005fmenu2_005f2.setImg("../css/navImg/8.gif");
    int _jspx_eval_gw_005fmenu2_005f2 = _jspx_th_gw_005fmenu2_005f2.doStartTag();
    if (_jspx_eval_gw_005fmenu2_005f2 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      if (_jspx_eval_gw_005fmenu2_005f2 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.pushBody();
        _jspx_th_gw_005fmenu2_005f2.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
        _jspx_th_gw_005fmenu2_005f2.doInitBody();
      }
      do {
        out.write("\r\n");
        out.write("\t\t  <item code=\"A0701\" onclick=\"go('compare/compareInfo.do','我的对比查询')\" text=\"我的对比查询\" img=\"../css/navImg/8-1.gif\"/>\r\n");
        out.write("\t\t  <item code=\"A0703\" onclick=\"go('compare/compareResult.do?action=ENTER','查看结果')\" text=\"查看结果\" img=\"../css/navImg/8-2.gif\"/>\r\n");
        out.write("\t\t  <item code=\"A0704\" onclick=\"go('blacklist/blacklistAlarmAction.do','黑名单报警结果')\" text=\"黑名单报警结果\" img=\"../css/navImg/8-3.gif\"/>\r\n");
        out.write("    ");
        int evalDoAfterBody = _jspx_th_gw_005fmenu2_005f2.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
      if (_jspx_eval_gw_005fmenu2_005f2 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.popBody();
      }
    }
    if (_jspx_th_gw_005fmenu2_005f2.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fgw_005fmenu2_0026_005ftitle_005fimg_005fid_005fcode.reuse(_jspx_th_gw_005fmenu2_005f2);
      return true;
    }
    _005fjspx_005ftagPool_005fgw_005fmenu2_0026_005ftitle_005fimg_005fid_005fcode.reuse(_jspx_th_gw_005fmenu2_005f2);
    return false;
  }

  private boolean _jspx_meth_gw_005fmenu2_005f3(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  gw:menu2
    com.liusy.web.tag.MenuTag2 _jspx_th_gw_005fmenu2_005f3 = (com.liusy.web.tag.MenuTag2) _005fjspx_005ftagPool_005fgw_005fmenu2_0026_005ftitle_005fimg_005fid_005fcode.get(com.liusy.web.tag.MenuTag2.class);
    _jspx_th_gw_005fmenu2_005f3.setPageContext(_jspx_page_context);
    _jspx_th_gw_005fmenu2_005f3.setParent(null);
    _jspx_th_gw_005fmenu2_005f3.setCode("A08");
    _jspx_th_gw_005fmenu2_005f3.setId("div7");
    _jspx_th_gw_005fmenu2_005f3.setTitle("元数据管理");
    _jspx_th_gw_005fmenu2_005f3.setImg("../css/navImg/metadata.gif");
    int _jspx_eval_gw_005fmenu2_005f3 = _jspx_th_gw_005fmenu2_005f3.doStartTag();
    if (_jspx_eval_gw_005fmenu2_005f3 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      if (_jspx_eval_gw_005fmenu2_005f3 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.pushBody();
        _jspx_th_gw_005fmenu2_005f3.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
        _jspx_th_gw_005fmenu2_005f3.doInitBody();
      }
      do {
        out.write("\r\n");
        out.write("            <!--<item code=\"A0801\" onclick=\"go('system/sysCity.do','地市管理')\" text=\"地 市 管 理\" img=\"../css/navImg/6-1.gif\" />-->\r\n");
        out.write("            <item code=\"A0802\" onclick=\"go('pages/datastandard/standardCategory_list.jsp','数据分类管理')\" text=\"数据分类管理\" img=\"../css/navImg/6-2.gif\" />\r\n");
        out.write("            <item code=\"A0803\" onclick=\"go('pages/datastandard/indicator.jsp','数据指标管理')\" text=\"数据指标管理\" img=\"../css/navImg/6-3.gif\" />\r\n");
        out.write("            <item code=\"A0804\" onclick=\"go('pages/datastandard/dataMeta.jsp','数据元管理')\" text=\"数据元管理\" img=\"../css/navImg/6-4.gif\" />\r\n");
        out.write("            <item code=\"A0805\" onclick=\"go('pages/datastandard/standardCodeset_list.jsp','代码集管理')\" text=\"代码集管理\" img=\"../css/navImg/6-5.gif\" />\r\n");
        out.write("    ");
        int evalDoAfterBody = _jspx_th_gw_005fmenu2_005f3.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
      if (_jspx_eval_gw_005fmenu2_005f3 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.popBody();
      }
    }
    if (_jspx_th_gw_005fmenu2_005f3.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fgw_005fmenu2_0026_005ftitle_005fimg_005fid_005fcode.reuse(_jspx_th_gw_005fmenu2_005f3);
      return true;
    }
    _005fjspx_005ftagPool_005fgw_005fmenu2_0026_005ftitle_005fimg_005fid_005fcode.reuse(_jspx_th_gw_005fmenu2_005f3);
    return false;
  }

  private boolean _jspx_meth_gw_005fmenu2_005f4(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  gw:menu2
    com.liusy.web.tag.MenuTag2 _jspx_th_gw_005fmenu2_005f4 = (com.liusy.web.tag.MenuTag2) _005fjspx_005ftagPool_005fgw_005fmenu2_0026_005ftitle_005fimg_005fid_005fcode.get(com.liusy.web.tag.MenuTag2.class);
    _jspx_th_gw_005fmenu2_005f4.setPageContext(_jspx_page_context);
    _jspx_th_gw_005fmenu2_005f4.setParent(null);
    _jspx_th_gw_005fmenu2_005f4.setCode("A99");
    _jspx_th_gw_005fmenu2_005f4.setId("div9");
    _jspx_th_gw_005fmenu2_005f4.setTitle("系统管理");
    _jspx_th_gw_005fmenu2_005f4.setImg("../css/navImg/set.gif");
    int _jspx_eval_gw_005fmenu2_005f4 = _jspx_th_gw_005fmenu2_005f4.doStartTag();
    if (_jspx_eval_gw_005fmenu2_005f4 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      if (_jspx_eval_gw_005fmenu2_005f4 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.pushBody();
        _jspx_th_gw_005fmenu2_005f4.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
        _jspx_th_gw_005fmenu2_005f4.doInitBody();
      }
      do {
        out.write("\r\n");
        out.write("            <item code=\"A9901\" onclick=\"go('system/sysOrg.do','组织机构管理','house_big.gif')\" text=\"组织机构管理\" img=\"../css/navImg/7-1.gif\" />\r\n");
        out.write("            <item code=\"A9902\" onclick=\"go('system/sysRole.do','角色权限管理','people.gif')\" text=\"角色权限管理\" img=\"../css/navImg/7-2.gif\" />\r\n");
        out.write("            <item code=\"A9903\" onclick=\"go('system/sysUser.do','用户管理','men.gif')\" text=\"用 户 管 理\" img=\"../css/navImg/7-3.gif\" />\r\n");
        out.write("            <item code=\"A9904\" onclick=\"go('system/ResourceTable.do?action=MAIN','资源目录管理')\" text=\"资源目录管理\" img=\"../css/navImg/7-4.gif\" />\r\n");
        out.write("            <item code=\"A9905\" onclick=\"go('querycfg/subjectCfg.do','主题管理','build_all.gif')\" text=\"主 题 管 理\" img=\"../css/navImg/7-5.gif\" />\r\n");
        out.write("            <item code=\"A9907\" onclick=\"go('config/sysCode.do','系统代码管理','code_mod.gif')\" text=\"系统代码管理\" img=\"../css/navImg/7-7.gif\" />\r\n");
        out.write("            <item code=\"A9908\" onclick=\"go('query/searchAudit.do','查询审计','code_mod.gif')\" text=\"查询审计\" img=\"../css/navImg/7-7.gif\" />\r\n");
        out.write("            <item code=\"A9909\" onclick=\"go('blacklist/blacklistDeclarationAction.do','黑名单管理','code_mod.gif')\" text=\"黑名单管理\" img=\"../css/navImg/7-7.gif\" />\r\n");
        out.write("            \r\n");
        out.write("    ");
        int evalDoAfterBody = _jspx_th_gw_005fmenu2_005f4.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
      if (_jspx_eval_gw_005fmenu2_005f4 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.popBody();
      }
    }
    if (_jspx_th_gw_005fmenu2_005f4.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fgw_005fmenu2_0026_005ftitle_005fimg_005fid_005fcode.reuse(_jspx_th_gw_005fmenu2_005f4);
      return true;
    }
    _005fjspx_005ftagPool_005fgw_005fmenu2_0026_005ftitle_005fimg_005fid_005fcode.reuse(_jspx_th_gw_005fmenu2_005f4);
    return false;
  }
}
