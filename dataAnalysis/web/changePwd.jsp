<%@ page language="java" contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt" %>

<script type="text/javascript">
   function changePassword() {
		var winWidth  = 400 ;
		var winHeight = 250 ;
	    
	    var oldPwd = new Ext.form.Field({    
	    		inputType: 'password', 
	    		fieldLabel: '<fmt:message key="label.oldPwd"/>',  
	    		allowBlank: false,
	    		anchor: '90%',
				//autoHeight: true,
	    		//height: '25',
	    		name: 'oldPwd'
	    }) ;
	    
	    var newPwd = new Ext.form.Field({    
	    		inputType: 'password', 
	    		fieldLabel: '<fmt:message key="label.newPwd"/>',  
	    		allowBlank: false,
				//autoHeight: true,
	    		//height: '25',
	    		anchor: '90%',
	    		name: 'newPwd'
	    }) ;
	    
	    var confirmPwd = new Ext.form.Field({    
	    		inputType: 'password', 
	    		fieldLabel: '<fmt:message key="label.confirmPwd"/>',  
	    		allowBlank: false,
				//autoHeight: true,
	    		//height: '25',
	    		anchor: '90%',
	    		name: 'confirmPwd'
	    }) ;
	    
	    newPwd.on('blur', function(){
	    	if (oldPwd.getValue() == newPwd.getValue()){
	    		Ext.MessageBox.alert('<fmt:message key="message.info" />','<fmt:message key="oldPwd.equals.newPwd" />') ;
	    	}
	    });
	    
	    confirmPwd.on('blur', function(){
	    	if (newPwd.getValue() != "" && confirmPwd.getValue() != "" 
	    		&& confirmPwd.getValue() != newPwd.getValue()){
	    		Ext.MessageBox.alert('<fmt:message key="message.info" />','<fmt:message key="newPwd.dismatch" />') ;
	    	}
	    });
	          	            
	 	var formPanel = new Ext.form.FormPanel({          
	 		//baseCls: 'x-plain',        
			labelAlign: 'right',
	 		labelWidth: 80,        
	 		width: winWidth,        
	 		height:winHeight-20,         		
	 		url:'<%= request.getContextPath() %>/sysChangePwdAction.do',
	 		method: 'post',    		
	 		border: false,  
	 		region: 'center',
	 		bodyStyle:'padding:25px 20px 5px',
	 		//defaultType: 'textfield',
	 		items: [   
			    oldPwd,newPwd,confirmPwd
	 		]  
	 	});    
	 	// define window and show it
	 	var window = new Ext.Window({
	 		title: '<fmt:message key="user.changePwd" />',    
	 		width: winWidth,        
	 		height:winHeight,        
	 		minWidth: 300,        
	 		minHeight: 250,        
	 		layout: 'border',        
	 		plain:true,        
	 		buttonAlign:'center',        
	 		items: formPanel,
	 		modal: true,   
	 		buttons: [{            
	 			text: '<fmt:message key="button.save" />',             
	 			handler: function() {                
	 				// check form value                 
	 				if (formPanel.form.isValid()) {	 
	 					if(newPwd.getValue() == oldPwd.getValue()){
	 						Ext.MessageBox.alert('<fmt:message key="message.info" />','<fmt:message key="oldPwd.equals.newPwd" />') ;
	 						return ;
	 					}else if(newPwd.getValue() !== confirmPwd.getValue()){
	 						Ext.MessageBox.alert('<fmt:message key="message.info" />','<fmt:message key="newPwd.dismatch" />') ;
	 						return ;
	 					}	
	 					//this.disabled=true;	        
	 					formPanel.form.submit({			      			            
	 						waitMsg:'<fmt:message key="process.wait.hint" />',	            
	 						failure: function(form, action) {						    
	 							Ext.MessageBox.alert('<fmt:message key="message.error" />', action.result.errorInfo);	
	 							//this.disabled=false;    					
	 						},						
	 						success: function(form, action) {						    
	 							Ext.MessageBox.alert('<fmt:message key="message.confirm" />', action.result.info, function(){
		 							window.hide();
		 							window.destroy();	
	 							});									    	 											
	 						}
	 					});                                   
	 				} else{		
	 					Ext.MessageBox.alert('<fmt:message key="message.error" />', '<fmt:message key="errors.form.check" />');				
	 				}   	        
	 			}
	 		},{            
		 			text: '<fmt:message key="button.cancel" />',
		 			handler: function(){
		 				window.hide();
		 				window.destroy();
		 			}     
	 		}]    
		});    
	 	window.show('main');
 	};
 	</script>