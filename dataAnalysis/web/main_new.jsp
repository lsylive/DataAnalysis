<%@ page language="java" contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="/WEB-INF/config/tag/gw-tag.tld" prefix="gw" %>
<%
	String path = request.getContextPath();
	String CONTEXT_PATH = request.getScheme() + "://"+ request.getServerName() + ":" + request.getServerPort()+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>数据应用系统</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="x-ua-compatible" content="ie=7" />
<link rel="stylesheet" type="text/css" href="<%=CONTEXT_PATH%>dhtmlxSuite/dhtmlx/dhtmlx.css" />
<link rel="stylesheet" type="text/css" href="<%=CONTEXT_PATH%>dhtmlxSuite/dhtmlxMenu/codebase/skins/dhtmlxmenu_dhx_blue.css"/>
<script src="<%=CONTEXT_PATH%>dhtmlxSuite/dhtmlx/dhtmlx.js"></script>
<link rel="stylesheet" type="text/css" href="<%=CONTEXT_PATH%>css/reset.css"/>
<link rel="stylesheet" type="text/css" href="<%=CONTEXT_PATH%>css/menus.css"/>
<script language="javascript" src="<%= CONTEXT_PATH %>js/ctrl_util.js"></script>
<style type="text/css">
html, body {
	width: 100%;
	height: 100%;
	border:none;
	overflow: hidden;
}
/* --------------以下为新增css---------------- */

#sysName {
	background:url(<%=CONTEXT_PATH%>images/top1.jpg) no-repeat 0px 1px;
	color:white;
	font-size:28px;
	height:64px;
}
#topBanner {
	background:url(<%=CONTEXT_PATH%>images/headerBg.jpg) repeat-x center top;
	height:64px;
	position:relative;
}
#header {
	width:100%;
	zoom:1;
}
#hInput {
	position:absolute;
	right:0;
	top:0;
	background: url(<%=CONTEXT_PATH%>images/headerRBg.jpg) no-repeat right top;
	height:64px;
	width:366px;
	text-align:right;
	line-height:64px;
}
#logSate {
	position:absolute;
	left:1px;
	bottom:0px;
	_top:44px;
	padding:2px 0 0 42px;
	color:#ecfaff;
	background:url(<%=CONTEXT_PATH%>images/loginIFBg.jpg) no-repeat left top;
	width:100%;
}
#header .headerBtn {
	cursor:pointer;
	background:url(<%=CONTEXT_PATH%>images/icon_list.gif) no-repeat right;
	width:50px;
	height:18px;
	line-height:18px;
	border:none;
	margin-right:3px;
	margin-top:44px;
	font-size:12px;
	color:#fff;
	padding:2px 0 0 0px;
	text-align:right;
}
#header #topHome {
	background-position: -90px -151px;
}
#header #topHelp {
	background-position: -90px -100px;
}
#header #topExit {
	background-position: -90px 2px;
}
#header .headerBtn:hover {
	color:#e3f3f8
}
.memuNavDivWrap {
	position:relative;
	top:0;
	left:0;
	width:100%;
	height:100%;
	background:#f0f7ff url(<%=CONTEXT_PATH%>images/nav_bottom_bg.jpg) repeat-y 1px center;
	/* 滚动条代码S */
	overflow:auto;/* 滚动条 */
	scrollbar-face-color:#cae1fc;/* 滚动条颜色 */
	scrollbar-track-color:#e9f2fe;/* 底色 */
	scrollbar-arrow-color:#2b5589;/* 箭头颜色 */
	scrollbar-highlight-color:#f6faff;/* 左次阴影 */
	scrollbar-3dlight-color:#acc9e9;/* 左外阴影 */
	scrollbar-shadow-color:#b0cbe8;/* 右次阴影 */
	scrollbar-darkshadow-color:#e6f0fa;/* 右外阴影 *//* 滚动条代码E */
}

.memuNav {
	list-style:none;
	margin:0;
	padding:0;
	border:1px solid #fff;
	border-bottom:1px solid #fff;
	border-top: none;
}
.memuNav li {
	text-align:center;
	height:26px;
	line-height:26px;
}

.memuNav li a {
	color:#093978;
	text-decoration:none;
	display:block;
	height:26px;
	line-height:26px;
	background:#e4f0fe url(<%=CONTEXT_PATH%>images/leftNavSBg.gif) repeat-x center top;
}
.memuNav li a:hover {
	background:#fff0c8 url(<%=CONTEXT_PATH%>images/leftNavSBg_hover.gif) repeat-x center bottom;
	color:#9e5e2a;
	letter-spacing:-1px;
	font-weight:bold;
	border-top:1px solid #fff;
	text-indent:2px;
	_height:25px;
	_line-height:25px;
}
#container {
	width:100%;
	height:100%;
	zoom:1;
	overflow:hidden;
	position:absolute;
}
</style>
<script type="text/javascript">
	 var contextpath = "<%=CONTEXT_PATH%>";	
	 var imgPath="<%=CONTEXT_PATH%>dhtmlxSuite/dhtmlx/imgs/icon/";
   function go(link,title,fName){
   	  var f="leaf.gif";
   	  if(fName) f=fName;
      var img="&lt;img src='"+imgPath+f+"' /&gt;";  

      var t="&lt;font style='font-size:12px; font-weight:bold'&gt;"+title+"&lt;/font&gt;";
      dhxToolbar.removeItem("img");
      dhxToolbar.removeItem("title");
   	  dhxToolbar.addText("img", 0,img);
   	  dhxToolbar.addText("title", 1,t);
   	  //centerPanel.showToolbar();
      var cont=window.document.getElementById('container');
      cont.src=contextpath+link;
   }
   function changetitle(title){
   	  var t="&lt;font style='font-size:12px; font-weight:bold'&gt;"+title+"&lt;/font&gt;";
      dhxToolbar.removeItem("title");
   	  dhxToolbar.addText("title", 0,t);
   }
   function showmain(){
   		window.location.href='<%=CONTEXT_PATH%>main.jsp';
   }
   function logout(){
   	if(confirm('是否真的要注销并重新登录?')==true)
   		window.location.href='<%=CONTEXT_PATH%>user/logout.do';
   }
   function checklogin(){
   		var url='<%=CONTEXT_PATH%>user/login.do?action=checklogin';
   		var loader=dhtmlxAjax.postSync(url,'');
   		var value = loader.xmlDoc.responseText;
   		if(value!='OK'){
   			alert('您还未登录系统');
   			window.location.href='login.jsp';
   		}
   }

</script>
</head>
<body onload="checklogin()">
<div id="topBanner" style="display:none;">
    <h1 id="sysName">&nbsp;数据综合分析系统</h1>
    <div id="header">
        <div id="logSate">当前用户：<span><%=request.getAttribute("userName")%>(<%=request.getAttribute("userAccount")%>)</span></div>
        <div id="hInput">
            <button name="topPage" id="topHome"  type="button" onClick="" class="headerBtn">首页</button>
            <button id="topHelp" type="button" onClick=""  class="headerBtn">帮助</button>
            <button id="topExit" type="button" onClick="logout()"  class="headerBtn">退出</button>
        </div>
    </div>
    <div id="sysMenu" backgroundcolor="red">测试菜单</div>
</div>
<script type="text/javascript">
	 var dhxLayout = new dhtmlXLayoutObject(document.body, "2E");
	 dhxLayout.setSkin("dhx_skyblue");
	 var topPanel=dhxLayout.cells("a");
	 var centerPanel=dhxLayout.cells("b");

	 topPanel.setHeight(97);
	 topPanel.hideHeader();
	 topPanel.fixSize(true,true);
	 topPanel.attachObject("topBanner");
	 
	 var menu = new dhtmlXMenuObject("sysMenu","dhx_blue");
   var menustr="<menu>";
   
    <gw:menu2 code="A03" id="div2" title="综合查询" img="../css/navImg/search.gif">
       <item code="A0301" onclick="go('query/commSubjectQuery.do?tree=comm','普通查询','code_child.gif')" text="普通查询" img="../css/navImg/1-1.gif"/>
       <item code="A0302" onclick="go('query/commSubjectQuery.do?tree=all','复合查询','code_all.gif')" text="复合查询" img="../css/navImg/1-2.gif"/>
       <item code="A0304" onclick="go('dynamicquery/searchEngine.do','全文检索查询','code_all.gif')" text="全文检索查询" img="../css/navImg/1-2.gif"/>
    </gw:menu2>
    <gw:menu2  code="A04" id="div3" title="数据分析" img="../css/navImg/data.gif">
    <!--        <item code="A0401" onclick="go('analysis/analysis.do?action=Link','常用查询')" text="常用查询" img="../css/navImg/3-1.gif" />-->
            <item code="A0402" onclick="go('analysis/analysis.do?action=Communication','通讯记录分析')" text="通讯记录分析" img="../css/navImg/3-2.gif" />
            <item code="A0403" onclick="go('analysis/analysis.do?action=Accommodation','住宿记录分析')" text="住宿记录分析" img="../css/navImg/3-3.gif" />
            <item code="A0404" onclick="go('analysis/analysis.do?action=Border','出入境记录分析')" text="出入境记录分析" img="../css/navImg/3-4.gif" />
            <item code="A0405" onclick="go('analysis/analysis.do?action=Flying','飞行记录分析')" text="飞行记录分析" img="../css/navImg/3-5.gif" />
            <item code="A0406" onclick="go('analysis/analysis.do?action=Composite','综合关联分析')" text="综合关联分析" img="../css/navImg/3-6.gif" />
    </gw:menu2>    

    <gw:menu2 code="A07" id="div6" title="对比查询" img="../css/navImg/8.gif">
		  <item code="A0701" onclick="go('compare/compareInfo.do','我的对比查询')" text="我的对比查询" img="../css/navImg/8-1.gif"/>
		  <item code="A0703" onclick="go('compare/compareResult.do?action=ENTER','查看结果')" text="查看结果" img="../css/navImg/8-2.gif"/>
		  <item code="A0704" onclick="go('blacklist/blacklistAlarmAction.do','黑名单报警结果')" text="黑名单报警结果" img="../css/navImg/8-3.gif"/>
    </gw:menu2>
    <gw:menu2 code="A08" id="div7" title="元数据管理" img="../css/navImg/metadata.gif">
            <!--<item code="A0801" onclick="go('system/sysCity.do','地市管理')" text="地 市 管 理" img="../css/navImg/6-1.gif" />-->
            <item code="A0802" onclick="go('pages/datastandard/standardCategory_list.jsp','数据分类管理')" text="数据分类管理" img="../css/navImg/6-2.gif" />
            <item code="A0803" onclick="go('pages/datastandard/indicator.jsp','数据指标管理')" text="数据指标管理" img="../css/navImg/6-3.gif" />
            <item code="A0804" onclick="go('pages/datastandard/dataMeta.jsp','数据元管理')" text="数据元管理" img="../css/navImg/6-4.gif" />
            <item code="A0805" onclick="go('pages/datastandard/standardCodeset_list.jsp','代码集管理')" text="代码集管理" img="../css/navImg/6-5.gif" />
    </gw:menu2>

    <gw:menu2 code="A99" id="div9" title="系统管理" img="../css/navImg/set.gif">
            <item code="A9901" onclick="go('system/sysOrg.do','组织机构管理','house_big.gif')" text="组织机构管理" img="../css/navImg/7-1.gif" />
            <item code="A9902" onclick="go('system/sysRole.do','角色权限管理','people.gif')" text="角色权限管理" img="../css/navImg/7-2.gif" />
            <item code="A9903" onclick="go('system/sysUser.do','用户管理','men.gif')" text="用 户 管 理" img="../css/navImg/7-3.gif" />
            <item code="A9904" onclick="go('system/ResourceTable.do?action=MAIN','资源目录管理')" text="资源目录管理" img="../css/navImg/7-4.gif" />
            <item code="A9905" onclick="go('querycfg/subjectCfg.do','主题管理','build_all.gif')" text="主 题 管 理" img="../css/navImg/7-5.gif" />
            <item code="A9907" onclick="go('config/sysCode.do','系统代码管理','code_mod.gif')" text="系统代码管理" img="../css/navImg/7-7.gif" />
            <item code="A9908" onclick="go('query/searchAudit.do','查询审计','code_mod.gif')" text="查询审计" img="../css/navImg/7-7.gif" />
            <item code="A9909" onclick="go('blacklist/blacklistDeclarationAction.do','黑名单管理','code_mod.gif')" text="黑名单管理" img="../css/navImg/7-7.gif" />
            
    </gw:menu2>
   menustr+="</menu>";
   menu.loadXMLString(menustr);
   menu.attachEvent("onClick", menuclick);
   
   function menuclick(id){
   	eval(menu.getUserData(id,"onclick"));
   } 

</script>
<div id="mainFrame">
    <iframe id="container" style="overflow-y:auto;overflow-x:hidden!important;width:100%;height:100%;" frameborder="no" src="../blank.jsp" ></iframe>
</div>
<script type="text/javascript">
	 centerPanel.hideHeader();
	 centerPanel.attachObject("mainFrame");
   var dhxToolbar = centerPanel.attachToolbar();
   dhxToolbar.addText("title", 0,"");
   centerPanel.hideToolbar();
</script>
</body>
</html>