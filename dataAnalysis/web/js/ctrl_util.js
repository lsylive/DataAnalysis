/**
 * 响应页面控件的回车键事件，跳转到下一控件

 * 用法：在控件的onkeypress事件中调用该方法
 * onkeypress="enterNext(this, window.event);"
 */
 
 /*    
     王川 2004-6-25 
 不用这么麻烦：	if (event.keyCode == 13) event.keyCode == 9；就可以了。
 */
function enterNext(property, event)  {
	var count = 0;
	if (event.keyCode == 13)
	{
		var elm = document.forms[0].elements;
		for (i = 0; i < elm.length; i++)
		{
			event.keyCode = 0;
			if (property == elm[i])
			{
				while (true)
				{
					if (elm[(i + 1) % elm.length].type.indexOf("select") == -1 && elm[(i + 1) % elm.length].readOnly != false)
					{
						i++;
						continue;
					}
					try
					{
						elm[(i + 1) % elm.length].focus();
						break;
					}
					catch (errorObj)
					{
						i++;
						continue;
					}
				}
			}
		}
	}
}

function windowopen(urllink,para,w,h)  {
	var res;
	var t=urllink.charAt(0);
  if(t=="/")  
    res=window.showModalDialog(contextpath+"/common/dframe.htm",urllink,"dialogHeight:"+h+"px;dialogWidth:"+w+"px;status:no");
  else
    res=window.showModalDialog(contextpath+"/common/dframe.htm","../"+urllink,"dialogHeight:"+h+"px;dialogWidth:"+w+"px;status:no");
  return res;
}

function getElements(name)  {
   return window.document.getElementsByName(name);
}

function getElement(name)  {

   return window.document.getElementsByName(name)[0];
}

function findSelected(name,type) {
	 var group = getElements(name);
	 if(group==null || group.length==0) {
     alert("没有记录可以" + type + "。");
 	 	 return "";
	 }	
	 var count = 0;
	 for(var i=0;i<group.length;i++) {
	    if(group[i].checked == true) count++;
	 }
	 if(count==0)  { 
      alert("请选择要" + type + "的记录。");
	    return "";
	 }
	 if(count>1) {
      alert("只能" + type + "一条记录。");
	    return "";
	 }
	 for(var i=0;i<group.length;i++) {
	    if(group[i].checked == true) return group[i].value;
	 }
}

function findMultiSelected(name,type) {
	 var group = getElements(name);
	 if(group==null || group.length==0) {
     alert("没有记录可以" + type + "。");
 	 	 return "";
	 }	
	 var count = 0;
	 for(var i=0;i<group.length;i++) {
	    if(group[i].checked == true) count++;
	 }
	 if(count==0)  { 
      alert("请选择要" + type + "的记录。");
	    return "";
	 }
	 var ret = "";
	 for(var i=0;i<group.length;i++) {
	    if(group[i].checked == true) {
	    	if(ret.length > 0) ret = ret + ";";
	    	ret = ret + group[i].value;
	    }	
	 }
	 return ret;
}

function gosearch(act)  {
	  var rc = getElement("query.pageSize");
    if(rc!=null) {
	     if(!checkNumeric(rc,"[每页记录数]",false,true)) return false;
    }
    if(act==null) {
    	 document.forms[0].action = hyperlink + "?action=list";
    }
    else{
    	 document.forms[0].action = hyperlink + "?action="+act;
    }
    document.forms[0].target = "_self";
    document.forms[0].submit();
}

function query(field)  {
    var order = getElement("query.order");
    var desc  = getElement("query.orderDirection");
	  var pn =    getElement("query.pageNumber");

    order.value = field;
    if(desc.value == "") desc.value = "asc";
    else if(desc.value == "asc") desc.value = "desc"; 
    else desc.value = "asc";
    pn.value="1";
    gosearch();
}

function goFirstPage()  {
	  var rc = getElement("query.recordCount");
	  if(rc.value == 0) return;
	  var pn = getElement("query.pageNumber");
	  pn.value = "1";
    gosearch();
}

function goPreviousPage()  {
	  var i = 0;

	  var rc = getElement("query.recordCount");
	  if(rc.value == 0) return;
	  var pn = getElement("query.pageNumber");
	  i = pn.value;
	  pn.value--;

    gosearch();
}

function goNextPage()  {
	  var rc = getElement("query.recordCount");
	  if(rc.value == 0) return;
	  var pn = getElement("query.pageNumber");
	  pn.value++;
    gosearch();
}

function goLastPage()  {
	  var rc = getElement("query.recordCount");
	  if(rc.value == 0) return;
	  var pn = getElement("query.pageNumber");
	  var pc = getElement("query.pageCount");
	  pn.value = pc.value;
    gosearch();
}

var rowMouseOverBgColor = "#99eedd";
var rowBgColor = "#d9edff";

function setpagesize()  {
	 if(event.keyCode == 13) gosearch();
}

function headerOver(obj) {
   obj.style.cursor = "hand";
   obj.style.color  = "#bb0000";
}

function headerOut(obj) {
   obj.style.cursor = "default";
   obj.style.color  = "#333333";
}

function $$(id){
	return window.document.getElementById(id);
}

function $NAME(name){
	return window.document.getElementsByName(name);
}
	
function goReset(){
   document.forms[0].reset();
}
	
function showMessage(message){	
	if(""!=message){alert(message);}
}
	
function changeSelection(name){	
	 var group = $NAME(name);
	 if(group==null) return "";
	 
	 var sl=$$('selectAll');

	 for(var i=0;i<group.length;i++) {
	 	  if(sl.disabled==false) {
	       if(sl.checked) group[i].checked = true; else group[i].checked = false;
	    }
	 }
}

//iframe 自动适应脚本 frmid  iframe id
function dynamicFrame(frmid){   
 var frm=document.getElementById(frmid);   
	//frm.contentWindow.document为IE下 使用，获得子页面各个对象   
	var sub=frm.contentDocument ? frm.contentDocument:frm.contentWindow.document;    
 
 	if(frm!=null&&sub!=null){   
 		if (frm.contentDocument && frm.contentDocument.body.offsetHeight) //如果用户的浏览器是NetScape
     	 frm.height = sub.body.offsetHeight; 
   		 else if (frm.Document && frm.Document.body.scrollHeight){ //如果用户的浏览器是IE
		{
			frm.height = sub.body.scrollHeight+10;
		}
      }
 	}   
}   

function rowMouseOver(obj) {
	var tmp=obj.className;
	obj.className=tmp+" selectItem";
}

function rowMouseOut(obj) {
	var tmp=obj.className;
  obj.className=tmp.replace(" selectItem","");
}

function secondRowMouseOver(obj) {
	var tmp=obj.className;
	obj.className=tmp+" selectItem";
}

function secondRowMouseOut(obj) {
	var tmp=obj.className;
  obj.className=tmp.replace(" selectItem","");
}
