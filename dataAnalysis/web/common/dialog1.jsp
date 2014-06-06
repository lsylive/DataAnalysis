<script type="text/javascript">
	  var dhxWins = new dhtmlXWindows();
    dhxWins.enableAutoViewport(true);
    dhxWins.setImagePath("<%=CONTEXT_PATH%>dhtmlxSuite/dhtmlxWindows/codebase/imgs/");
    
    var winName="win";  
    var editMode;       
    
    function openWindow(title,urlink,width,height){
       var w = dhxWins.createWindow(winName, 0, 0, width, height);
       w.setText(title);
       w.keepInViewport(true);
       w.setModal(true);
       w.centerOnScreen();
       //w.button("minmax1").hide();
       //w.button("minmax2").hide();
       w.button("park").hide();
       w.attachURL(urlink);
       return w;    
    }
    function openSingleWindow(title,divhtml,width,height){
       if(height==null || height=='')
       		height=100;
       	if(width==null || width=='')
       		width=300;
   	   var win2 = dhxWins.createWindow(winName, 0, 0, width, height);
       win2.setText(title);
       win2.keepInViewport(true);
       win2.setModal(true);
       win2.centerOnScreen();
       //win2.button("minmax1").hide();
      // win2.button("minmax2").hide();
       win2.button("park").hide();
       win2.attachHTMLString(divhtml);
       return win2;    
    }

    function openWindowById(title,objId,width,height){
	       if(height==null || height=='')
	       		height=100;
	       	if(width==null || width=='')
	       		width=300;
	   	   var win2 = dhxWins.createWindow(winName, 0, 0, width, height);
	   	   win2.button("close").attachEvent("onClick", function(){
	   		dhxWins.window(winName).close();
		   	   });
	   	   win2.maximize();
	       win2.setText(title);
	       win2.keepInViewport(true);
	       win2.setModal(true);
	       win2.centerOnScreen();
	       win2.button("minmax1").hide();
	       win2.button("minmax2").hide();
	       win2.button("park").hide();
	       win2.attachObject(objId);;
	       return win2;    
	    }
   
</script> 
