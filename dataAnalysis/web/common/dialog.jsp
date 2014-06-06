<div id="dialog_background" style="display:none;position:absolute;left:0pt;top:0pt;height:100%;width:100%;background-color:#778899;filter: Alpha(opacity=30);opacity:0.3;">  
 <iframe href="#" style="width:100%;height:100%;" frameborder="0" scrolling="auto"></iframe>
</div>  
<div id="dialog_div" style="display:none;position:absolute;background-color:#333333;">  
</div> 
<script>
	function dialog_open(action,title,w,h)  {
		var    frmStr="<table cellspacing=\"0\" cellpadding=\"0\" style=\"border:2px solid black;width:100%;height:100%\">";
	  frmStr=frmStr+"<tr height=\"25\" style=\"background-color:#6688cc;color:white;font-size:11pt;\">";
	  frmStr=frmStr+"<td width=\"100%\">&nbsp;"+title+"</td>";
	  frmStr=frmStr+"<td width=\"16\">*</td>";
		frmStr=frmStr+"</tr>";
	  frmStr=frmStr+"<tr><td colspan=\"2\">";
	  frmStr=frmStr+"<iframe id=\"container\" href=\"#\" style=\"width:100%;height:100%\" frameborder=\"0\" scrolling=\"auto\"></iframe>";
		frmStr=frmStr+"</td></tr>";
		frmStr=frmStr+"</table>";
	  
    var d=$$("dialog_background");
    var dd=$$("dialog_div");
    d.style.display="block";
    dd.style.display="block";
    dd.style.width=w;
    dd.style.height=h;
    dd.style.left=(d.offsetWidth -dd.offsetWidth)/2;
    dd.style.top=(d.offsetHeight-dd.offsetHeight)/2;
    dd.innerHTML=frmStr;
    var frm=$$("container");
    frm.src=action;
  }

	function dialog_close()  {
    var d=$$("dialog_background");
    var dd=$$("dialog_div");
    d.style.display="none";
    dd.style.display="none";
    dd.innerHTML="";
  }
</script>	 