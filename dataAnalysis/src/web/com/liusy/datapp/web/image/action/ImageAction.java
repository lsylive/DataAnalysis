package com.liusy.datapp.web.image.action;

import java.awt.image.ImageProducer;
import java.net.URL;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.sun.jimi.core.Jimi;
import com.sun.jimi.core.JimiWriter;
import com.sun.jimi.core.options.JPGOptions;
import com.liusy.datapp.web.BaseAction;

public class ImageAction extends BaseAction {
   @Override
   public ActionForward doExecute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
      String CONTEXT_PATH = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() + "/";
      ActionForward forward = null;
      String fName = request.getParameter("fileName");
      response.setContentType("image/jpeg");
      response.setHeader("Pragma", "No-cache");
      response.setHeader("Cache-Control", "no-cache");
      response.setDateHeader("Expires", 0);
      try {
         ServletOutputStream sos = response.getOutputStream();
         URL url=new URL(CONTEXT_PATH + fName);
         ImageProducer imageProducer = Jimi.getImageProducer(url);

         JimiWriter writer = Jimi.createJimiWriter("image/jpeg", sos);
         JPGOptions options = new JPGOptions();
//         options.setQuality(70);

         writer.setSource(imageProducer);
//         writer.setOptions(options);
         writer.putImage(sos);
         sos.close();

      }
      catch (Exception e) {
      }
      return forward;
   }
}
