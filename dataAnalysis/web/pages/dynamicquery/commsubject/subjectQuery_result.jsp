<%@ page language="java" contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="/WEB-INF/config/tag/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/config/tag/struts-template.tld" prefix="template" %>
<%@ taglib uri="/WEB-INF/config/tag/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/config/tag/struts-html.tld" prefix="html" %>

<%@ taglib uri="/WEB-INF/config/tag/gw-tag.tld" prefix="gw" %>
<%
String path = request.getContextPath();
String CONTEXT_PATH = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"> 
<html>
<head>
	<link rel="stylesheet" type="text/css" href="<%=CONTEXT_PATH%>css/main.css" />
	 <link rel="stylesheet" type="text/css" href="<%=CONTEXT_PATH%>dhtmlxSuite/dhtmlx/dhtmlx.css">
<script src="<%=CONTEXT_PATH%>dhtmlxSuite/dhtmlx/dhtmlx.js"></script>
<script language="javascript" src="<%= CONTEXT_PATH %>/js/date_validate.js"></script>
<script language="javascript" src="<%= CONTEXT_PATH %>/js/ctrl_util.js"></script>
<script language="javascript" src="<%= CONTEXT_PATH %>/js/jquery.js"></script>
<script language="javascript" src="<%= CONTEXT_PATH %>/js/jquery.form.js"></script>
	
	<script language="javascript">  
      var contextpath = "<%=CONTEXT_PATH%>";	
      var hyperlink = "../query/commSubjectQuery.do";	
      var fulllink = contextpath + "/query/commSubjectQuery.do";		
      


	  function $NAME(name){
	  	return document.getElementsByName(name)[0];
	  }
	  function view(id) {
	  		alert(id);
    		var url_link=fulllink+'?action=showdetail&uid='+id+'&tabId='+$NAME("tabId").value;
    		editMode="VIEW";
    		openWindow("查看详细信息",url_link,600,380);	  
	}
	function closedialog(ret){
	     dhxWins.window(winName).close();
	}
	
	</script>	
   <style type="text/css">
html, body {
        width: 100%;
        height: 100%;
        margin: 0px;
        overflow: hidden;
}
</style>

</head>

<body onload="init()">
<div id="formDiv" class="formDiv">
<html:form styleId="commSubjectQueryForm" action="/query/commSubjectQuery.do" method="post">
<html:hidden property="querySql" />
 <html:hidden property="query.order" />
                  	<html:hidden property="query.orderDirection" />
                  	<html:hidden property="query.pageNumber" />
                  	<html:hidden property="query.recordCount"/>
                  	<html:hidden property="query.pageCount" />
                  	<html:hidden property="action" />
                  	<html:hidden property="tabId" />
                  	<html:hidden property="uid" />
                  	
<table width="100%" border="0" cellspacing="0" cellpadding="0" height="100%" >
  <tr height="*">
    <td align="center" valign="top">
      
      <table border="0" width="100%" cellpadding="0" cellspacing="0" align="center">
          <tr>
            <td width="100%" valign="top">
       	  	<!--  <div id="gridbox" style="width:100%;height:100%;overflow:hidden"></div>-->

       	  	  
             <script>
			function init(){
			 var dhxLayout = new dhtmlXLayoutObject(document.body, "1C");
			 dhxLayout.cells("a").setText('查询结果');
			 dhxLayout.cells("a").hideHeader();
			var mygrid = dhxLayout.cells("a").attachGrid();
			
			//var mygrid = new dhtmlXGridObject('gridbox');
			mygrid.setImagePath("<%=CONTEXT_PATH%>dhtmlxSuite/dhtmlxGrid/codebase/imgs/");
			mygrid.setColumnIds("<bean:write name='columnIds' />");
			mygrid.setHeader("<bean:write name='columnNames' />");
			mygrid.setInitWidths("<bean:write name='columnWidths' />");
			mygrid.setColAlign("<bean:write name='alignModes' />");
			//mygrid.setColTypes("dyn,ed,txt,price,ch,coro,ch,ro");
			mygrid.setSkin("dhx_skyblue");
			mygrid.setColSorting("<bean:write name='colTypes' />");
			//mygrid.setDateFormat("%Y-%m-%d");     
			mygrid.init();
			var xmlstr='<bean:write name="xmlstr" filter="false" />';
			mygrid.parse(xmlstr);
			
			mygrid.attachEvent("onBeforeSorting",function(ind,type,dir){
                this.clearAll();                                            // clear grid
                var column=this.getColumnId(ind);
                document.getElementsByName("query.order")[0].value=column;
                document.getElementsByName("query.orderDirection")[0].value=dir;
                var url=fulllink+"?"+$('#commSubjectQueryForm').formSerialize();
                alert(url);
                this.loadXML(url); // load a new dataset from the server, with necessary order
                this.setSortImgState(true,ind,dir);        //set a correct sorting image
                return false;   
            });
            mygrid.attachEvent("onRowDblClicked", function(rId,cInd){
            	view(rId);
            });  
            }
            
             </script>

             </td>
           </tr>
       </table>
    </td>
  </tr>
</table>

</html:form>
</div>
<script type="text/javascript">
    var dhxWins = new dhtmlXWindows();
    dhxWins.enableAutoViewport(true);
    dhxWins.setImagePath("<%=CONTEXT_PATH%>dhtmlxSuite/dhtmlxWindows/codebase/imgs/");
    
    var winName="win";  
    var editMode;       
    
    function openWindow(title,urlink,w,h){
    	 var w = dhxWins.createWindow(winName, 0, 0, w, h);
       w.setText(title);
       w.keepInViewport(true);
       w.setModal(true);
       w.centerOnScreen();
       w.button("minmax1").hide();
       w.button("minmax2").hide();
       w.button("park").hide();
       w.attachURL(urlink);
       
       return w;    
    }
</script>
</body>
</html>