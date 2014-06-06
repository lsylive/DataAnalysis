<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="/WEB-INF/config/tag/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/config/tag/struts-template.tld"
	prefix="template"%>
<%@ taglib uri="/WEB-INF/config/tag/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/config/tag/struts-html.tld" prefix="html"%>

<%@ taglib uri="/WEB-INF/config/tag/gw-tag.tld" prefix="gw"%>
<%
	String path = request.getContextPath();
	String CONTEXT_PATH = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	String msg = (String) request.getAttribute("msg");
	if (msg == null)
		msg = "";
%>



<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<link rel="stylesheet" type="text/css" href="<%=CONTEXT_PATH%>dhtmlxSuite/dhtmlx/dhtmlx.css" />
		<script src="<%=CONTEXT_PATH%>dhtmlxSuite/dhtmlx/dhtmlx.js"></script>
		<link rel="stylesheet" type="text/css" href="<%=CONTEXT_PATH%>css/main.css" />
		<script src="<%=CONTEXT_PATH%>js/ctrl_util.js"></script>
		<script src="<%=CONTEXT_PATH%>js/date_validate.js"></script>
		<script language="javascript" src="<%= CONTEXT_PATH %>js/datapicker/WdatePicker.js"></script>
		
		<script language="javascript"> 
      var contextpath = "<%=CONTEXT_PATH%>";
      var hyperlink = contextpath + "blacklist/blacklistAlarmAction.do";
      var fulllink = contextpath + "blacklist/blacklistAlarmAction.do";
      
		
    	
		function renew() {
			var order = getElement("query.order");                  order.value="";
			var desc =  getElement("query.orderDirection");         desc.value="";
			var pn =    getElement("query.pageNumber");             pn.value="1";
			var ps =    getElement("query.pageSize");               ps.value="10";
			var v0 =    getElement("query.parameters(memoTitle)");    v0.value="";
			var v1 =    getElement("query.parameters(createDateFrom)");    v1.value="";
			var v2 =    getElement("query.parameters(createDateTo)");    v2.value="";
    		gosearch();
		}

		function goQuery() {
			var pn = getElement("query.pageNumber");             
			pn.value="1";
			gosearch();
		}

		function closedialog(ret) {
			dhxWins.window(winName).close();
      		editMode="";
			if(ret=='true') {
				gosearch();
			}	
		}
		

		function goDel()  {
	  		var id = findMultiSelected("ID","删除");
	  		if(id == "") return;
	  		var res = confirm("是否真的要删除?");
	  		if(res == true) {
       			document.forms[0].action = hyperlink + "?action=DELETE&ids=" + id;
       			document.forms[0].target = "_self";
       			document.forms[0].submit();
    }
    }
    
   
function alertMsg(msgtxt,heigth){
      	var alerttab='<table cellpadding="0" cellspacing="0" width="100%" class="formTable"><tbody><tr><td width="60px" class="textR"><span><img src="'+contextpath+'common/images/info.gif"/></span></td><td class="textL"><span><b>'+msgtxt+'</b></span></td></tr><tr class="btnTr"><td class="textC" colspan="2"><a id="closebtn" href="javascript:onClick=closedialog()" class="btnStyle" name="btnRefresh" ><strong>关闭</strong></a></td></tr></tbody></table>';       
			openSingleWindow('个人空间',alerttab,300,120);
			$$("closebtn").focus();
      }		

		var msg = "<%=msg%>";
		if (msg != "")
			alert(msg);
			

	</script>

	</head>

	<body>
	<div class="special-padding">   
		<html:form action="/blacklist/blacklistAlarmAction.do" method="post">
			<table border="0" width="100%" cellpadding="0" cellspacing="0" align="center">
				<tr>
            		<td width="100%" valign="top">
						<table cellspacing="1" cellpadding="1" class="controlTable">
							<tr>
								<td valign="top" align="left">
									<gw:button styleClass="sbuBtnStyle" icon="delIcon" onClick="goDel()">删除报警信息</gw:button>&nbsp;
								</td>
							</tr>
						</table>
						<html:hidden property="query.order" />
						<html:hidden property="query.orderDirection" />
						<html:hidden property="query.pageNumber" />
						<html:hidden property="query.recordCount" />
						<html:hidden property="query.pageCount" />
						<html:hidden property="query.parameters(classId)" />
						
						<gw:grid2 cellPadding="0" cellSpacing="0" width="100%" styleClass="listTable"
	    					   name="blacklistAlarmForm" property="query.recordSet" parameters="query" rowEventHandle="false" 
	      						 fixRows="false">
	      						 <header style="" height="27"  />
    										<column width="5%" itemType="checkbox" property="ID" align="center" selectAll="true"  headerAlign="center"/>
  											  <column width="10%" name="申报代码" property="DEC_CODE" align="center" 
    	    							      headerOnMouseOut="headerOut(this)"
											  headerOnClick="query('DEC_CODE')"
											  headerOnMouseOver="headerOver(this)"/>
											<column width="30%" name="命中表名称" property="TABLE_CN_NAME" align="center" 
    	    							      headerOnMouseOut="headerOut(this)"
											  headerOnClick="query('TABLE_CN_NAME')"
											  headerOnMouseOver="headerOver(this)"/>
											<column width="10%" name="命中指标名称" property="IND_CN_NAME" align="center" 
    	    							      headerOnMouseOut="headerOut(this)"
											  headerOnClick="query('IND_CN_NAME')"
											  headerOnMouseOver="headerOver(this)"/>
											<column width="30%" name="命中值" property="HIT_VALUE" align="center" 
    	    							      headerOnMouseOut="headerOut(this)"
											  headerOnClick="query('HIT_VALUE')"
											  headerOnMouseOver="headerOver(this)"/>
    										<column width="15%" name="命中时间" property="HIT_TIME" align="center" 
											  headerOnClick="query('HIT_TIME')"
    	    							      headerOnMouseOut="headerOut(this)"
											  headerOnMouseOver="headerOver(this)"/>
											
    										<rooter height="30" width="100%" showType="all" />      
						</gw:grid2>
					</td>
				</tr>
			</table>
		</html:form>
		</div>
		<%@include file="/common/dialog1.jsp"%>
	</body>
</html>