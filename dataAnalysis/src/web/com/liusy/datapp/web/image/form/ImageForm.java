package com.liusy.datapp.web.image.form;

import org.apache.struts.action.ActionForm;

public class ImageForm extends ActionForm {
	private static final long serialVersionUID = 1L;
	
	private String fileName;

   public String getFileName() {
      return fileName;
   }

   public void setFileName(String fileName) {
      this.fileName = fileName;
   }
}