package com.liusy.datapp.web.login;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.liusy.core.util.Const;
import com.liusy.core.util.Session;

public class LogOutAction extends Action{
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		//Çå³ýSession
		HttpSession session = request.getSession(false);
    	if(session!=null){
	    	Session usersession = (Session) session.getAttribute(Const.SESSION) ;
	    	
	    	if(usersession!=null){
	    		session.removeAttribute(Const.SESSION) ;
	    		
	    	}	    	
	    	session.invalidate() ;
	    	
    	}
    	return mapping.findForward("logout") ;
	}

}
